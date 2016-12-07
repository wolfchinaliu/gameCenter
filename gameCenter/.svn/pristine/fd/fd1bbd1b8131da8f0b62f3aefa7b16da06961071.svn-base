package com.shiliu.game.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shiliu.game.domain.Game;
import com.shiliu.game.domain.UserJHDX;
import com.shiliu.game.domain.UserRecord;
import com.shiliu.game.interceptor.Token;
import com.shiliu.game.service.IGameService;
import com.shiliu.game.service.IUserJHDXService;
import com.shiliu.game.service.IUserRecordService;
import com.shiliu.game.utils.LeadingInExcel;
import com.shiliu.game.utils.LeadingOutExcel;

/**
 * @author wkr
 * @Date 2016-11-28 15:25
 * 客户导入用户信息
 */
@Controller
@RequestMapping(value="/addCustomer")
public class AddCustomerDataController {
	@Resource
	private IGameService gameService;			//Game
	@Resource
	private IUserJHDXService userJHDXService;	//白名单
	@Resource
	private IUserRecordService userRecord;		//用户记录信息
	//log4j
	private Logger log =Logger.getLogger(AddCustomerDataController.class);
	
	/**
	 * 自动转换日期类型的字段格式
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
             
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
     }
	
	/**
	 * 初始化信息
	 * @return
	 */
	@RequestMapping(value="/init",method=RequestMethod.GET)
	@Token(save=true)
	public String initMethod(){
		
		return "JHDX/addCustomerData";
	}
	
	/**
	 * Game进入页面
	 * @param gameId
	 * @param creatTime
	 * @param endTimes
	 * @param gameName
	 * @param gameType
	 * @param session
	 * @return
	 */
	//@RequestMapping(value="/insertGam/{type}",method=RequestMethod.POST)
	@RequestMapping(value="/insertGam/{type}")
	@Token(save=true)
	public String insertGamMethod(
									@RequestParam(value="gameId") String gameId,
									@RequestParam(value="startTime") Date creatTime,
									@RequestParam(value="endTime") Date endTimes,
									@RequestParam(value="title") String gameName,
									@PathVariable(value="type") Integer gameType,
									HttpSession session
	){
		Game game = null;
		game = gameService.getGameById(gameId);
		if(game==null){
			game = new Game(gameId, gameName, gameType, creatTime, endTimes);
			gameService.insertSelective(game);
		}
		session.setAttribute("gameID", gameId);
		session.setAttribute("gameType", gameType);
		return "JHDX/addCustomerData";
	}
	
	/**
	 * 单个添加用户信息
	 * @param phone
	 * @param uptodate
	 * @param flux
	 * @param total
	 * @param session
	 * @param pw
	 * @return
	 */
	@RequestMapping(value="/insertFlux")
	//@ResponseBody
	public void insertFluxMethod(
									@RequestParam(value="phone") String phone,
									@RequestParam(value="uptodate") String uptodate,
									@RequestParam(value="flux") String flux,
									@RequestParam(value="totalOrStatus") String totalOrStatus,
									HttpSession session,
									PrintWriter pw
	){
		//测试输出System.out.println("\t"+phone+"\t"+uptodate+"\t"+flux+"\t"+total);
		String gameid = (String) session.getAttribute("gameID");
		Integer gameType = (Integer) session.getAttribute("gameType");
		
		UserJHDX user = new UserJHDX( phone, uptodate, flux, gameid);
		if (gameType.equals(1)) {
			user.setTotal(Integer.parseInt(totalOrStatus));
		} else if(gameType.equals(2)){
			user.setStatus(totalOrStatus);
		}
		
		int i=0;
		try {
			i = userJHDXService.insertSelective(user);
		} catch (Exception e) {
			log.error("添加用户失败！", e);
			pw.println("日期格式错误！！");
		}
		
		if(i>0){
			pw.println("添加成功！");
		} else {
			pw.println("添加失败！");
		}
		
		//return "JHDX/addCustomerData";
	}
	
	/**
	 * 读取数据库中用户信息，写入Excel文件中
	 * @param fileName
	 * @param type
	 * @param response
	 */
	//@ResponseBody
	@RequestMapping("/batchExport/{type}")
	public void batchExportMtehod(
									@RequestParam(value="fileName")String fileName,
									@PathVariable(value="type")String type,
									HttpServletResponse response
	){
		List<UserRecord> dataSet = null;
		LeadingOutExcel leadingOutExcel = null;	//工具类
		
		//配置信息
		String format = "yyyy-MM-dd";
		String title = "";
		String[] rowName = { "编号","微信号","微信名称", "手机号", "参与活动时间", "获得奖品" };
		
	    if("all".equals(type)){
	    	title = "参与用户信息";
	    	dataSet = userRecord.selectAll();	//参与者
	    }else if("award".equals(type)){
	    	title = "中奖用户信息";
	    	dataSet = userRecord.selectAward(); //中奖者
	    }
	    
	    List<Object[]>  dataList = new ArrayList<Object[]>();  
	    Object[] objs = null;  
	    for (int i = 0; i < dataSet.size(); i++) {  
	         UserRecord man = dataSet.get(i);  
	        objs = new Object[rowName.length];  
	        objs[0] = i;  
	        objs[1] = man.getOpenId();
	        objs[2] = man.getNickName();
	        objs[3] = man.getPhone();
	        	//日期类型处理
	            Date date = man.getPlayTime();
	            String dateStr = "";
	            if(date!=null){
	            	SimpleDateFormat df = new SimpleDateFormat(format);  
		        	dateStr = df.format(man.getPlayTime());
	            }
	        objs[4] = dateStr;
	        objs[5] = man.getReserved1();
	          
	        dataList.add(objs);  
	    }  
	    
	    leadingOutExcel = new LeadingOutExcel(fileName,title, rowName, dataList,response);
	    try {
			leadingOutExcel.export();
		} catch (Exception e) {
			log.error("写入Excle出错！", e);
		}
	}
	
	/**
	 * 读取Excel中的用户信息插入数据库
	 * @param multipart
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/batchimport")
	public String batchImportMethod(@RequestParam(value="filename") MultipartFile multipart,HttpSession session){
		
		LeadingInExcel<UserJHDX> testExcel=null;
		List<UserJHDX> uploadAndRead=null;
		boolean judgement = false;
		Integer gameType = null;
		String gameId = null;
		String Msg =null;
		
		//定义需要读取的数据
		String formart = "yyyy-MM-dd";
		String propertiesFileName = "config";
		String kyeName = "file_path";
		int sheetIndex = 0;
		Map<String, String> titleAndAttribute=null;
		Class<UserJHDX> clazz=UserJHDX.class;
		
			//定义对应的标题名与对应属性名
			titleAndAttribute=new HashMap<String, String>();
			titleAndAttribute.put("手机号码", "phone");
			titleAndAttribute.put("截止日期", "uptodate");
			titleAndAttribute.put("消耗总流量", "flux");
			// 抽奖页面、送奖页面
			gameType = (Integer) session.getAttribute("gameType");
			if (gameType.equals(1)) {
				titleAndAttribute.put("总抽奖次数", "total");
			} else if(gameType.equals(2)){
				titleAndAttribute.put("状态", "status");
			}
		
		//调用解析工具包
		testExcel=new LeadingInExcel<UserJHDX>(formart);
		
		//解析excel，获取客户信息集合
		try {
			uploadAndRead = testExcel.uploadAndRead(multipart, propertiesFileName, kyeName, sheetIndex, titleAndAttribute, clazz);
		} catch (Exception e) {
			log.error("读取Excel文件错误！",e);
		}
		
        if(uploadAndRead != null && !"[]".equals(uploadAndRead.toString()) && uploadAndRead.size()>=1){
        	judgement = true;
        }
        
        if(judgement){
        	
        	//把客户信息分为没100条数据为一组迭代添加客户信息（注：将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
        	//int count=0;
        	int listSize=uploadAndRead.size();
        	int toIndex=100;
        	for (int i = 0; i < listSize; i+=100) {
        		if(i+100>listSize){
        			toIndex=listSize-i;
        		}
        		List<UserJHDX> subList = uploadAndRead.subList(i, i+toIndex);
        		/*
            	 * 测试数据：
            	 	count=count+subList.size();
        			System.out.println("subList长度："+subList.size()+"\t总长度："+count);
            	 * 
            	 	for (UserJHDX userJHDX : subList) {
            			System.out.println("手机号："+userJHDX.getPhone()+"截止日期:"+userJHDX.getUptodate()+"流量值"+userJHDX.getFlux()+"总次数"+userJHDX.getTotal());
    				}
            	 */
        		
        		gameId = (String) session.getAttribute("gameID");
        		/** 此处执行集合添加 */
        		userJHDXService.insertClient(subList,gameId);
			}
        	
             Msg ="批量导入EXCEL成功！";
             session.setAttribute("msg",Msg);    
        }else{
             Msg ="批量导入EXCEL失败！";
             session.setAttribute("msg",Msg);
        }
		return "redirect:init.htm"; 
	}
	
}
