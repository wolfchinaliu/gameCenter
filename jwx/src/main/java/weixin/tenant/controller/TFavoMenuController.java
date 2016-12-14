package weixin.tenant.controller;
import weixin.tenant.entity.TFavoMenuEntity;
import weixin.tenant.service.TFavoMenuServiceI;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
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
 * @Description: 快捷菜单
 * @author onlineGenerator
 * @date 2015-08-07 11:09:25
 * @version V1.0   
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/tFavoMenuController")
public class TFavoMenuController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TFavoMenuController.class);

	@Autowired
	private TFavoMenuServiceI tFavoMenuService;
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
	 * 快捷菜单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tFavoMenu")
	public ModelAndView tFavoMenu(HttpServletRequest request) {
		return new ModelAndView("weixin/tenant/tFavoMenuList");
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
	public void datagrid(TFavoMenuEntity tFavoMenu,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TFavoMenuEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tFavoMenu, request.getParameterMap());
		cq.eq("userId", ResourceUtil.getSessionUserName().getId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tFavoMenuService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除快捷菜单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TFavoMenuEntity tFavoMenu, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tFavoMenu = systemService.getEntity(TFavoMenuEntity.class, tFavoMenu.getId());
		message = "快捷菜单删除成功";
		try{
			tFavoMenuService.delete(tFavoMenu);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "快捷菜单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除快捷菜单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "快捷菜单删除成功";
		try{
			for(String id:ids.split(",")){
				TFavoMenuEntity tFavoMenu = systemService.getEntity(TFavoMenuEntity.class, 
				id
				);
				tFavoMenuService.delete(tFavoMenu);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "快捷菜单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加快捷菜单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TFavoMenuEntity tFavoMenu, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "快捷菜单添加成功";
		try{
			tFavoMenuService.save(tFavoMenu);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "快捷菜单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新快捷菜单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TFavoMenuEntity tFavoMenu, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "快捷菜单更新成功";
		TFavoMenuEntity t = tFavoMenuService.get(TFavoMenuEntity.class, tFavoMenu.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tFavoMenu, t);
			tFavoMenuService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "快捷菜单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 快捷菜单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TFavoMenuEntity tFavoMenu, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tFavoMenu.getId())) {
			tFavoMenu = tFavoMenuService.getEntity(TFavoMenuEntity.class, tFavoMenu.getId());
			req.setAttribute("tFavoMenuPage", tFavoMenu);
		}
		return new ModelAndView("weixin/tenant/tFavoMenu-add");
	}
	/**
	 * 快捷菜单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TFavoMenuEntity tFavoMenu, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tFavoMenu.getId())) {
			tFavoMenu = tFavoMenuService.getEntity(TFavoMenuEntity.class, tFavoMenu.getId());
			req.setAttribute("tFavoMenuPage", tFavoMenu);
		}
		return new ModelAndView("weixin/tenant/tFavoMenu-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/tenant/tFavoMenuUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(TFavoMenuEntity tFavoMenu,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "快捷菜单";
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
			CriteriaQuery cq = new CriteriaQuery(TFavoMenuEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tFavoMenu, request.getParameterMap());
			
			List<TFavoMenuEntity> tFavoMenus = this.tFavoMenuService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("快捷菜单列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), TFavoMenuEntity.class, tFavoMenus);
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
	public void exportXlsByT(TFavoMenuEntity tFavoMenu,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "快捷菜单";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("快捷菜单列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), TFavoMenuEntity.class, null);
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
				List<TFavoMenuEntity> listTFavoMenuEntitys = 
					(List<TFavoMenuEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),TFavoMenuEntity.class,params);
				for (TFavoMenuEntity tFavoMenu : listTFavoMenuEntitys) {
					tFavoMenuService.save(tFavoMenu);
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
	
	/**
	 * 添加快捷菜单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "addFavoMenu")
	@ResponseBody
	public AjaxJson addFavoMenu(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		try{
			
			String menu_id = request.getParameter("menu_id");
			TSUser user = ResourceUtil.getSessionUserName();
			
			List<TFavoMenuEntity> tFavoMenuList = tFavoMenuService.findByQueryString("from TFavoMenuEntity t where t.userId='"+user.getId()+"' and t.menuId='"+menu_id+"'");
			
			if(tFavoMenuList !=null && tFavoMenuList.size() > 0){
				
				message = "快捷菜单已添加";
			}else{

				TFavoMenuEntity tFavoMenu = new TFavoMenuEntity();
				tFavoMenu.setMenuId(menu_id);
				tFavoMenu.setUserId(user.getId());
				
				tFavoMenuService.save(tFavoMenu);
				message = "快捷菜单添加成功";
			}			
		}catch(Exception e){
			e.printStackTrace();
			message = "快捷菜单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 取消快捷菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "delFavoMenu")
	@ResponseBody
	public AjaxJson delFavoMenu(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		try{
			
			String menu_id = request.getParameter("menu_id");
			TSUser user = ResourceUtil.getSessionUserName();
			
			List<TFavoMenuEntity> tFavoMenuList = tFavoMenuService.findByQueryString("from TFavoMenuEntity t where t.userId='"+user.getId()+"' and t.menuId='"+menu_id+"'");
			
			if(tFavoMenuList!=null && tFavoMenuList.size()>0){
				
				tFavoMenuService.deleteAllEntitie(tFavoMenuList);
			}
			
			message = "快捷菜单取消成功";
		}catch(Exception e){
			e.printStackTrace();
			message = "快捷菜单取消失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
}
