package weixin.goods.controller;
import weixin.goods.entity.WeixinShopCategoryEntity;
import weixin.goods.entity.WeixinShopGoodsEntity;
import weixin.goods.service.WeixinShopCategoryServiceI;
import weixin.goods.service.WeixinShopGoodsServiceI;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.jeecgframework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: 商品信息
 * @author onlineGenerator
 * @date 2015-01-20 17:31:31
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinShopGoodsController")
public class WeixinShopGoodsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(WeixinShopGoodsController.class);

	@Autowired
	private WeixinShopGoodsServiceI weixinShopGoodsService;
	
	@Autowired
	private WeixinShopCategoryServiceI weixinShopCategoryService;
	
	@Autowired
	private SystemService systemService;
	
	private String message;
	private static String fileUrlPrefix = null;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 商品信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinShopGoods")
	public ModelAndView weixinShopGoods(HttpServletRequest request) {
		
		List weixinShopCategoryList = weixinShopCategoryService.findByProperty(WeixinShopCategoryEntity.class, "sellerId", ResourceUtil.getWeiXinAccountId());
		
		if(null != weixinShopCategoryList && weixinShopCategoryList.size()>0){
			
			request.setAttribute("weixinShopCategoryList", RoletoJson.listToReplaceStr(weixinShopCategoryList, "name", "id"));
		}else{
			
			request.setAttribute("weixinShopCategoryList", "未知_-1");
		}
		
		return new ModelAndView("weixin/goods/weixinShopGoodsList");
	}
	//工具方法:从配置文件mediaFile.properties中,读取文件url的前缀,比如:http://localhost/xampp/images
//	public static String getFileUrlPrefix() {
//		if (fileUrlPrefix == null) {
//			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mediaFile.properties");
//			Properties p = new Properties();
//			try {
//				p.load(is);
//				fileUrlPrefix = p.getProperty("media.url.prefix");
//				is.close();//关闭
//				is = null;
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return fileUrlPrefix;
//	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinShopGoodsEntity weixinShopGoods,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinShopGoodsEntity.class, dataGrid);
		//查询条件组装器
		
//		if(StringUtils.isNotEmpty(weixinShopGoods.getTitle())){
//			
//			weixinShopGoods.setTitle("*"+weixinShopGoods.getTitle()+"*");
//		}
		
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopGoods, request.getParameterMap());
		cq.eq("sellerId", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinShopGoodsService.getDataGridReturn(cq, true);

		//将数据库中取出来的图片,加上前缀
		if(dataGrid.getResults() != null){
			WeixinShopGoodsEntity tempEntity = null;
			for(Object obj : dataGrid.getResults()){
			    this.weixinShopGoodsService.getSession().evict(obj);
				tempEntity = (WeixinShopGoodsEntity)obj;
				tempEntity.setTitleImg(ResourceUtil.getMediaUrlPrefix() + "/" + tempEntity.getTitleImg());
				// 接口返回数据太多，删掉多余信息
				tempEntity.setDescriptions("");
			}
		}
        LOGGER.info(dataGrid);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商品信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinShopGoodsEntity weixinShopGoods, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinShopGoods = systemService.getEntity(WeixinShopGoodsEntity.class, weixinShopGoods.getId());
		message = "商品信息删除成功";
		try{
			weixinShopGoodsService.delete(weixinShopGoods);
			//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 商品批量上架
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchStartSale")
	@ResponseBody
	public AjaxJson doBatchStartSale(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		
		try{
			int count=0;
			for(String id:ids.split(",")){
				WeixinShopGoodsEntity weixinShopGoods = systemService.getEntity(WeixinShopGoodsEntity.class, id);
				
				weixinShopGoods.setStatement("1");
				weixinShopGoods.setShelveTime(new Date());
				if(weixinShopGoods.getPrice()!=null){
					weixinShopGoodsService.saveOrUpdate(weixinShopGoods);
					count++;
				}
				//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
			message = "商品信息上架成功"+count+"个";
		}catch(Exception e){
			e.printStackTrace();
			message = "商品信息上架失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 
	 /**
		 * 商品批量下架
		 * 
		 * @return
		 */
		 @RequestMapping(params = "doBatchStopSale")
		@ResponseBody
		public AjaxJson doBatchStopSale(String ids,HttpServletRequest request){
			AjaxJson j = new AjaxJson();
			
			try{
				for(String id:ids.split(",")){
					WeixinShopGoodsEntity weixinShopGoods = systemService.getEntity(WeixinShopGoodsEntity.class, id);
					
					weixinShopGoods.setStatement("0");
					weixinShopGoods.setRemoveTime(new Date());
					weixinShopGoodsService.saveOrUpdate(weixinShopGoods);
					
					message = "商品信息下架成功";
					//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}
			}catch(Exception e){
				e.printStackTrace();
				message = "商品信息下架失败";
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
	
	/**
	 * 批量删除商品信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "商品信息删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinShopGoodsEntity weixinShopGoods = systemService.getEntity(WeixinShopGoodsEntity.class, 
				id
				);
				weixinShopGoodsService.delete(weixinShopGoods);
				//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商品信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

    /**
     * 过滤非法字符的-------------xiaona------2016年5月3日
     */
    private static Pattern FilePattern = Pattern.compile("[\\\\/:*?\"<>|]");

    public static String filenameFilter(String str) {
        return str;
//        return str == null ? null : FilePattern.matcher(str).replaceAll("");
    }
	/**
	 * 添加商品信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinShopGoodsEntity weixinShopGoods, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		try{
			
			if (StringUtil.isNotEmpty(weixinShopGoods.getId())) {

				message = "商品信息更新成功";
				WeixinShopGoodsEntity t = weixinShopGoodsService.get(WeixinShopGoodsEntity.class, weixinShopGoods.getId());
				MyBeanUtils.copyBeanNotNull2Bean(weixinShopGoods, t);
				
				t.setUpdateName(ResourceUtil.getSessionUserName().getRealName());
				t.setUpdateDate(new Date());
				/**
				 * 新添加的保存图片地址的路径
				 */
				String imageRelativeUrl = request.getParameter("titleImg");
                String description=t.getDescriptions();
                /**
                 * 使用filenameFilter方法进行过滤，如果存在了|这样的非法字符，则进行替换为""
                 */
                description=this.filenameFilter(description);
                t.setDescriptions(description);
				t.setTitleImg(imageRelativeUrl);

				weixinShopGoodsService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} else {

				message = "商品信息添加成功";
				/**
				 * 新添加的保存图片地址的路径
				 */
				String imageRelativeUrl = request.getParameter("titleImg");

				weixinShopGoods.setTitleImg(imageRelativeUrl);
				weixinShopGoods.setSellerId(ResourceUtil.getWeiXinAccountId());
				weixinShopGoods.setCreateDate(new Date());
				weixinShopGoods.setCreateName(ResourceUtil.getSessionUserName().getRealName());
				weixinShopGoods.setStatement("0");
                String description=weixinShopGoods.getDescriptions();
                /**
                 * 使用filenameFilter方法进行过滤，如果存在了|这样的非法字符，则进行替换为""
                 */
                description=this.filenameFilter(description);
                weixinShopGoods.setDescriptions(description);
				weixinShopGoodsService.save(weixinShopGoods);
				//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商品信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新商品信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinShopGoodsEntity weixinShopGoods, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "商品信息更新成功";
		WeixinShopGoodsEntity t = weixinShopGoodsService.get(WeixinShopGoodsEntity.class, weixinShopGoods.getId());
		try {


			MyBeanUtils.copyBeanNotNull2Bean(weixinShopGoods, t);
			
			String price = request.getParameter("price");
			String realPrice = request.getParameter("realPrice");
			String expressPrice = request.getParameter("expressPrice");
			String is_new = request.getParameter("is_new");
			if(StringUtils.isNotEmpty(is_new))
				t.setIsNew(Integer.parseInt(is_new));
			String is_hot = request.getParameter("is_hot");
			
			if(StringUtils.isNotEmpty(is_hot))
				t.setIsHot(Integer.parseInt(is_hot));
			
			t.setPrice(new BigDecimal(price));
			t.setRealPrice(new BigDecimal(realPrice));
			t.setExpressPrice(new BigDecimal(expressPrice));

			String imageRelativeUrl = request.getParameter("titleImg");
			if(StringUtils.isNotBlank(imageRelativeUrl)){
			    t.setTitleImg(imageRelativeUrl);   
			}
            String description=t.getDescriptions();
            /**
             * 使用filenameFilter方法进行过滤，如果存在了|这样的非法字符，则进行替换为""
             */
            description=this.filenameFilter(description);
            t.setDescriptions(description);

			weixinShopGoodsService.saveOrUpdate(t);
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "商品信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 商品信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinShopGoodsEntity weixinShopGoods, HttpServletRequest req) {
		
		if (StringUtil.isNotEmpty(weixinShopGoods.getId())) {
			weixinShopGoods = weixinShopGoodsService.getEntity(WeixinShopGoodsEntity.class, weixinShopGoods.getId());
			req.setAttribute("weixinShopGoodsPage", weixinShopGoods);
		}
		
		//获取商品类型列表
		List<WeixinShopCategoryEntity> goodsCategoryList= weixinShopCategoryService.findByProperty(WeixinShopCategoryEntity.class, "sellerId", ResourceUtil.getWeiXinAccountId());
		req.setAttribute("goodsCategoryList", goodsCategoryList);
		
		return new ModelAndView("weixin/goods/weixinShopGoods-add");
	}
	/**
	 * 商品信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinShopGoodsEntity weixinShopGoods, HttpServletRequest req) {
		
		if (StringUtil.isNotEmpty(weixinShopGoods.getId())) {
			weixinShopGoods = weixinShopGoodsService.getEntity(WeixinShopGoodsEntity.class, weixinShopGoods.getId());
			req.setAttribute("weixinShopGoodsPage", weixinShopGoods);
		}
		
		//获取商品类型列表
		List<WeixinShopCategoryEntity> goodsCategoryList= weixinShopCategoryService.findByProperty(WeixinShopCategoryEntity.class, "sellerId", ResourceUtil.getWeiXinAccountId());
		req.setAttribute("goodsCategoryList", goodsCategoryList);
				
		return new ModelAndView("weixin/goods/weixinShopGoods-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		
		TSUser user = ResourceUtil.getSessionUserName();
		req.setAttribute("username", user.getUserName());
		req.setAttribute("accountid", ResourceUtil.getWeiXinAccountId());
		return new ModelAndView("weixin/goods/weixinShopGoodsUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinShopGoodsEntity weixinShopGoods,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "商品信息";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			CriteriaQuery cq = new CriteriaQuery(WeixinShopGoodsEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopGoods, request.getParameterMap());
			
			cq.eq("sellerId", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
			cq.add();
			
			List<WeixinShopGoodsEntity> weixinShopGoodss = this.weixinShopGoodsService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("商品信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopGoodsEntity.class, weixinShopGoodss);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public void exportXlsByT(WeixinShopGoodsEntity weixinShopGoods,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "商品信息";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("商品信息列表", "导出人:"+ResourceUtil.getSessionUserName().getUserName(),
					"导出信息"), WeixinShopGoodsEntity.class, null);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String accountid = request.getParameter("accountid");
		String username = request.getParameter("username");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setSecondTitleRows(2);
			params.setNeedSave(true);
			try {
				int i=0;
				StringBuffer msg=new StringBuffer();
				List<WeixinShopGoodsEntity> listWeixinShopGoodsEntitys = (List<WeixinShopGoodsEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinShopGoodsEntity.class,params);
				for (WeixinShopGoodsEntity weixinShopGoods : listWeixinShopGoodsEntitys) {
					WeixinShopCategoryEntity cate=weixinShopGoods.getWeixinShopCategoryEntity();
					cate.setSellerId(accountid);
					WeixinShopCategoryEntity en=weixinShopCategoryService.getWeixinShopCategoryEntity(cate);
					if(en==null){
						
						//分类不存在
						msg.append('[');
						msg.append(cate.getName());
						msg.append(']');
						continue;
					}
					weixinShopGoods.setWeixinShopCategoryEntity(en);
					weixinShopGoods.setSellerId(accountid);
					weixinShopGoods.setStatement("0");
					
					/**图片批量处理*/
					String title_img = "";
					String desc_imgs = "";
					String folder_name = weixinShopGoods.getFolderName();
					
					String localhosturl = ResourceUtil.getDomain()+"/";		
					
					String requestPath = request.getSession().getServletContext().getRealPath("/upload/user");
					String realpath = requestPath + "/"+ username + "/" + folder_name + "/";
					File tempfolder = new File(realpath);
					if (!tempfolder.exists()) {
						
						msg.append("[图片文件夹不存在]");
					}else{
						
						if (tempfolder.isDirectory()) {
							
							String[] filelist = tempfolder.list();
							 for (int k = 0; k < filelist.length; k++) {

								 File readfile = new File(realpath + "\\" + filelist[k]);
								 
								 if (!readfile.isDirectory()) {
									 
	                                 LOGGER.info("path=" + readfile.getPath());
	                                 LOGGER.info("absolutepath=" + readfile.getAbsolutePath());
	                                 LOGGER.info("name=" + readfile.getName());

	                                 //desc_imgs += "<p><img src='"+readfile.getPath()+"' style='float:none;' title='"+readfile.getName()+"'/></p>";
	                                 desc_imgs += "<p><img src='upload/user/"+ username +"/"+ folder_name+"/"+readfile.getName()+"' style='float:none;' title='"+readfile.getName()+"'/></p>";
	                                 
	                                 if(k==0)
	                                	 title_img = "upload/user/"+ username +"/"+folder_name+"/"+readfile.getName();
		                         }
							 }
						}else{
							msg.append("[图片文件夹不存在]");
						}
					}
										
					//2.把第一张图片拼接图片地址写入商品LOGO
					weixinShopGoods.setTitleImg(title_img);
					
					//3.拼接图片路径按格式写入商品详情
					weixinShopGoods.setDescriptions(desc_imgs+"<p>"+weixinShopGoods.getDescriptions()+"</p>");
					
					weixinShopGoodsService.save(weixinShopGoods);
					i++;
				}
				if(StringUtils.isEmpty(msg.toString())){
					j.setMsg("文件导入成功!");
				}else{
					j.setMsg("成功导入"+i+"条记录,失败数据原因："+msg.toString());
				}
				
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				LOGGER.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}



}
