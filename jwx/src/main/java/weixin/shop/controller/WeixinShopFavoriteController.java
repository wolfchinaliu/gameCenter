package weixin.shop.controller;
import weixin.shop.entity.WeixinShopFavoriteEntity;
import weixin.shop.service.WeixinShopFavoriteServiceI;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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



/**   
 * @Title: Controller
 * @Description: 收藏夹、关注
 * @author onlineGenerator
 * @date 2015-04-24 09:40:00
 * @version V1.0   
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/weixinShopFavoriteController")
public class WeixinShopFavoriteController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinShopFavoriteController.class);

	@Autowired
	private WeixinShopFavoriteServiceI weixinShopFavoriteService;
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
	 * 收藏夹列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinShopFavorite")
	public ModelAndView weixinShopFavorite(HttpServletRequest request) {
		return new ModelAndView("weixin/shop/weixinShopFavoriteList");
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
	public void datagrid(WeixinShopFavoriteEntity weixinShopFavorite,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinShopFavoriteEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopFavorite, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinShopFavoriteService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除收藏夹
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinShopFavoriteEntity weixinShopFavorite, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinShopFavorite = systemService.getEntity(WeixinShopFavoriteEntity.class, weixinShopFavorite.getId());
		message = "收藏夹删除成功";
		try{
			weixinShopFavoriteService.delete(weixinShopFavorite);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收藏夹删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除收藏夹
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "收藏夹删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinShopFavoriteEntity weixinShopFavorite = systemService.getEntity(WeixinShopFavoriteEntity.class, 
				id
				);
				weixinShopFavoriteService.delete(weixinShopFavorite);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "收藏夹删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加收藏夹
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinShopFavoriteEntity weixinShopFavorite, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "收藏夹添加成功";
		try{
			weixinShopFavoriteService.save(weixinShopFavorite);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收藏夹添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新收藏夹
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinShopFavoriteEntity weixinShopFavorite, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "收藏夹更新成功";
		WeixinShopFavoriteEntity t = weixinShopFavoriteService.get(WeixinShopFavoriteEntity.class, weixinShopFavorite.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinShopFavorite, t);
			weixinShopFavoriteService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "收藏夹更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 收藏夹新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinShopFavoriteEntity weixinShopFavorite, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinShopFavorite.getId())) {
			weixinShopFavorite = weixinShopFavoriteService.getEntity(WeixinShopFavoriteEntity.class, weixinShopFavorite.getId());
			req.setAttribute("weixinShopFavoritePage", weixinShopFavorite);
		}
		return new ModelAndView("weixin/shop/weixinShopFavorite-add");
	}
	/**
	 * 收藏夹编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinShopFavoriteEntity weixinShopFavorite, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinShopFavorite.getId())) {
			weixinShopFavorite = weixinShopFavoriteService.getEntity(WeixinShopFavoriteEntity.class, weixinShopFavorite.getId());
			req.setAttribute("weixinShopFavoritePage", weixinShopFavorite);
		}
		return new ModelAndView("weixin/shop/weixinShopFavorite-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/shop/weixinShopFavoriteUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinShopFavoriteEntity weixinShopFavorite,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "收藏夹";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinShopFavoriteEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopFavorite, request.getParameterMap());
			
			List<WeixinShopFavoriteEntity> weixinShopFavorites = this.weixinShopFavoriteService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("收藏夹列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopFavoriteEntity.class, weixinShopFavorites);
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
	public void exportXlsByT(WeixinShopFavoriteEntity weixinShopFavorite,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "收藏夹";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("收藏夹列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopFavoriteEntity.class, null);
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
				List<WeixinShopFavoriteEntity> listWeixinShopFavoriteEntitys = 
					(List<WeixinShopFavoriteEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinShopFavoriteEntity.class,params);
				for (WeixinShopFavoriteEntity weixinShopFavorite : listWeixinShopFavoriteEntitys) {
					weixinShopFavoriteService.save(weixinShopFavorite);
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
