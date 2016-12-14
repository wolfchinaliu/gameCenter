package weixin.business.controller;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

import org.jeecgframework.core.util.ExceptionUtil;

import weixin.business.entity.WeixinFoodCategoryEntity;
import weixin.business.entity.WeixinFoodEntity;
import weixin.business.service.WeixinFoodCategoryServiceI;
import weixin.business.service.WeixinFoodServiceI;



/**   
 * @Title: Controller
 * @Description: 菜品信息
 * @author onlineGenerator
 * @date 2015-01-20 17:31:31
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinFoodController")
public class WeixinFoodController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinFoodController.class);

	@Autowired
	private WeixinFoodServiceI weixinFoodService;
	
	@Autowired
	private WeixinFoodCategoryServiceI weixinFoodCategoryService;
	
	@Autowired
	private SystemService systemService;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 菜品信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView weixinFood(HttpServletRequest request) {
		
		List weixinFoodCategoryList = weixinFoodCategoryService.findByProperty(WeixinFoodCategoryEntity.class, "sellerId", ResourceUtil.getWeiXinAccountId());
		
		if(null != weixinFoodCategoryList && weixinFoodCategoryList.size()>0){
			
			request.setAttribute("weixinFoodCategoryList", RoletoJson.listToReplaceStr(weixinFoodCategoryList, "name", "id"));
		}else{
			
			request.setAttribute("weixinFoodCategoryList", "未知_-1");
		}
		
		return new ModelAndView("weixin/food/weixinFoodList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinFoodEntity weixinFood,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinFoodEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinFood, request.getParameterMap());
		cq.eq("sellerId", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinFoodService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除菜品信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinFoodEntity weixinFood, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinFood = systemService.getEntity(WeixinFoodEntity.class, weixinFood.getId());
		message = "菜品信息删除成功";
		try{
			weixinFoodService.delete(weixinFood);
			//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "菜品信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 菜品批量上架
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchStartSale")
	@ResponseBody
	public AjaxJson doBatchStartSale(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		
		try{
			for(String id:ids.split(",")){
				WeixinFoodEntity weixinFood = systemService.getEntity(WeixinFoodEntity.class, id);
				
				weixinFood.setStatement("1");
				weixinFood.setShelveTime(new Date());
				weixinFoodService.saveOrUpdate(weixinFood);
				
				message = "菜品信息上架成功";
				//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "菜品信息上架失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 
	 /**
		 * 菜品批量下架
		 * 
		 * @return
		 */
		 @RequestMapping(params = "doBatchStopSale")
		@ResponseBody
		public AjaxJson doBatchStopSale(String ids,HttpServletRequest request){
			AjaxJson j = new AjaxJson();
			
			try{
				for(String id:ids.split(",")){
					WeixinFoodEntity weixinFood = systemService.getEntity(WeixinFoodEntity.class, id);
					
					weixinFood.setStatement("0");
					weixinFood.setRemoveTime(new Date());
					weixinFoodService.saveOrUpdate(weixinFood);
					
					message = "菜品信息下架成功";
					//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}
			}catch(Exception e){
				e.printStackTrace();
				message = "菜品信息下架失败";
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
	
	/**
	 * 批量删除菜品信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "菜品信息删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinFoodEntity weixinFood = systemService.getEntity(WeixinFoodEntity.class, 
				id
				);
				weixinFoodService.delete(weixinFood);
				//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "菜品信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加菜品信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinFoodEntity weixinFood, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		try{
			
			if (StringUtil.isNotEmpty(weixinFood.getId())) {

				message = "菜品信息更新成功";
				WeixinFoodEntity t = weixinFoodService.get(WeixinFoodEntity.class, weixinFood.getId());
				MyBeanUtils.copyBeanNotNull2Bean(weixinFood, t);
				
				t.setUpdateName(ResourceUtil.getSessionUserName().getRealName());
				t.setUpdateDate(new Date());
				weixinFoodService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} else {
				
				message = "菜品信息添加成功";
				weixinFood.setSellerId(ResourceUtil.getWeiXinAccountId());
				weixinFood.setCreateDate(new Date());
				weixinFood.setCreateName(ResourceUtil.getSessionUserName().getRealName());
				weixinFood.setStatement("1");
				weixinFoodService.save(weixinFood);
				//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "菜品信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新菜品信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinFoodEntity weixinFood, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "菜品信息更新成功";
		WeixinFoodEntity t = weixinFoodService.get(WeixinFoodEntity.class, weixinFood.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinFood, t);
			weixinFoodService.saveOrUpdate(t);
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "菜品信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 菜品信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinFoodEntity weixinFood, HttpServletRequest req) {
		
		if (StringUtil.isNotEmpty(weixinFood.getId())) {
			weixinFood = weixinFoodService.getEntity(WeixinFoodEntity.class, weixinFood.getId());
			req.setAttribute("weixinFoodPage", weixinFood);
		}
		
		//获取菜品类型列表
		List<WeixinFoodCategoryEntity> goodsCategoryList= weixinFoodCategoryService.findByProperty(WeixinFoodCategoryEntity.class, "sellerId", ResourceUtil.getWeiXinAccountId());
		req.setAttribute("goodsCategoryList", goodsCategoryList);
		
		return new ModelAndView("weixin/food/weixinFood-add");
	}
	/**
	 * 菜品信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinFoodEntity weixinFood, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinFood.getId())) {
			weixinFood = weixinFoodService.getEntity(WeixinFoodEntity.class, weixinFood.getId());
			req.setAttribute("weixinFoodPage", weixinFood);
		}
		return new ModelAndView("weixin/food/weixinFood-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/food/weixinFoodUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinFoodEntity weixinFood,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "菜品信息";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinFoodEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinFood, request.getParameterMap());
			
			cq.eq("sellerId", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
			cq.add();
			
			List<WeixinFoodEntity> weixinFoods = this.weixinFoodService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("菜品信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinFoodEntity.class, weixinFoods);
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
	public void exportXlsByT(WeixinFoodEntity weixinFood,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "菜品信息";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("菜品信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinFoodEntity.class, null);
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
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setSecondTitleRows(1);
			params.setNeedSave(true);
			try {
				List<WeixinFoodEntity> listWeixinFoodEntitys = 
					(List<WeixinFoodEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinFoodEntity.class,params);
				for (WeixinFoodEntity weixinFood : listWeixinFoodEntitys) {
					
					weixinFood.setSellerId(ResourceUtil.getWeiXinAccountId());
					weixinFoodService.save(weixinFood);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
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
