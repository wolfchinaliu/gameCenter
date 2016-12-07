package com.shiliu.game.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.shiliu.game.domain.ExcelUser;
import com.shiliu.game.domain.GameExcel;
import com.shiliu.game.interceptor.Token;
import com.shiliu.game.service.IExcelUserService;
import com.shiliu.game.service.IGameExcelService;
import com.shiliu.game.utils.ReadExcel;
import com.shiliu.game.utils.WDWUtil;


/**
 * @author wkr
 * @Date 2016-11-18
 */
@Controller
@RequestMapping("/client")
public class ClientController {

	@Resource
	private IExcelUserService excelUserService;
	@Resource
	private IGameExcelService gameExcelService;
	
	private static Log log = LogFactory.getLog(ClientController.class);
	/**
     * 自动转换日期类型的字段格式
     */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
             
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
     }
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value="/init",method=RequestMethod.GET)
	@Token(save=true)
	public String init(){
		
		return "client/client";
	}
	/**
	 * Game进入页面
	 * @return
	 */
	@RequestMapping(value="/insertGam")
	@Token(save=true)
	public String insertGam(GameExcel gameExcel,Model model,HttpSession session){
		log.debug(gameExcel.getGameId());
		GameExcel game = gameExcelService.selectByPrimaryKey(gameExcel.getGameId());
		if(game==null || game.getFlux()==null){
			if(game==null)gameExcelService.insertSelective(gameExcel);
			String gameId = gameExcel.getGameId();
			session.setAttribute("getGameId", gameId);
			session.setAttribute("signal",-1);
		}else{
			session.setAttribute("signal",game.getFlux());
			session.setAttribute("getGameId", gameExcel.getGameId());
		}
		return "client/client";
	}
	/**
	 * 当客户未填写流量值时则可以进入添加
	 * @param flux
	 * @param getGameId
	 * @return
	 */
	@RequestMapping(value="/insertFlux",method = RequestMethod.POST)
	public String insertFlux(@RequestParam(value="flux") String flux,
			@ModelAttribute("getGameId") String getGameId,
			Model model){
		GameExcel gameExcel=new GameExcel();
		gameExcel.setGameId(getGameId);
		gameExcel.setFlux(flux);
		gameExcelService.updateByPrimaryKeySelective(gameExcel);
		model.addAttribute("signal",flux);
		return "client/client";
	}
	/**
	 * 上传Excel,读取Excel中内容
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/batchimport",method = RequestMethod.POST)
	@Token(remove=true)
    public String batchimport(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response) throws IOException{
        log.info("ClientController ..batchimport() start");
        String Msg =null;
        boolean b = false;
        //取出session中的GameID
        HttpSession session = request.getSession();
        String attribute = (String) session.getAttribute("getGameId");
        
        //判断文件是否为空
        if(file==null){
        	Msg ="文件是为空！";
            request.getSession().setAttribute("msg",Msg);
        	return "client/client";
        }
        
        //获取文件名
        String name=file.getOriginalFilename();
        
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）验证文件名是否合格
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0 && !WDWUtil.validateExcel(name)){
        	Msg ="文件格式不正确！请使用.xls或.xlsx后缀文档。";
            request.getSession().setAttribute("msg",Msg);
        	return "client/client";
        }
        
        //创建处理EXCEL
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取客户信息集合。
        List<ExcelUser> customerList = readExcel.getExcelInfo(name,file,attribute);
        if(customerList != null && !customerList.toString().equals("[]") && customerList.size()>=1){
            b = true;
        }
        
        if(b){
        	//把客户信息分为没100条数据为一组迭代添加客户信息（注：将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
        	//int count=0;
        	int listSize=customerList.size();
        	int toIndex=100;
        	for (int i = 0; i < listSize; i+=100) {
        		if(i+100>listSize){
        			toIndex=listSize-i;
        		}
        		List<ExcelUser> subList = customerList.subList(i, i+toIndex);
        		//count=count+subList.size();
        		//System.out.println("subList长度："+subList.size()+"\t总长度："+count);
        		excelUserService.insertClient(subList);
			}
        	
             Msg ="批量导入EXCEL成功！";
             request.getSession().setAttribute("msg",Msg);    
        }else{
             Msg ="批量导入EXCEL失败！";
             request.getSession().setAttribute("msg",Msg);
        } 
       return "redirect:init.htm";
    }
	/**
	 * 下载Excel模板
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping("/download")
	public String download(String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="+ fileName);
		try {
			/*String paths = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath()
					+ "download";//这个download目录为啥建立在classes下的
			  System.out.println("目录："+paths);
			*/
			
			String path=request.getSession().getServletContext().getRealPath("\\download");
			InputStream inputStream = new FileInputStream(new File(path+ File.separator + fileName));

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}

			 // 这里主要关闭。
			os.close();

			inputStream.close();
		} catch (FileNotFoundException e) {
			log.error("文件没找到异常", e);
		} catch (IOException e) {
			log.error("IO流异常", e);
		}
            //  返回值要注意，要不然就出现下面这句错误！
            //java+getOutputStream() has already been called for this response
		return null;
	}
	
}
