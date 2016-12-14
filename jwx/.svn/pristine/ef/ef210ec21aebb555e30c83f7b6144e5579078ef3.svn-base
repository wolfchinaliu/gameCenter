package weixin.shop.controller;
import weixin.shop.entity.WeixinShopAppraiseEntity;
import weixin.shop.service.WeixinShopAppraiseServiceI;

import java.util.List;
import java.text.SimpleDateFormat;

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
 * @Description: 商品评价
 * @author onlineGenerator
 * @date 2015-04-28 14:49:45
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinShopAppraiseController")
public class WeixinShopAppraiseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinShopAppraiseController.class);

	@Autowired
	private WeixinShopAppraiseServiceI weixinShopAppraiseService;
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
	 * 商品评价列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinShopAppraise")
	public ModelAndView weixinShopAppraise(HttpServletRequest request) {
		return new ModelAndView("weixin/shop/weixinShopAppraiseList");
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
	public void datagrid(WeixinShopAppraiseEntity weixinShopAppraise,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinShopAppraiseEntity.class, dataGrid);
		
		if(StringUtils.isNotEmpty(weixinShopAppraise.getOpenName())){
			
			weixinShopAppraise.setOpenName("*"+weixinShopAppraise.getOpenName()+"*");
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopAppraise, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinShopAppraiseService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商品评价
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinShopAppraiseEntity weixinShopAppraise, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinShopAppraise = systemService.getEntity(WeixinShopAppraiseEntity.class, weixinShopAppraise.getId());
		message = "商品评价删除成功";
		try{
			weixinShopAppraiseService.delete(weixinShopAppraise);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品评价删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除商品评价
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "商品评价删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinShopAppraiseEntity weixinShopAppraise = systemService.getEntity(WeixinShopAppraiseEntity.class, 
				id
				);
				weixinShopAppraiseService.delete(weixinShopAppraise);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商品评价删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商品评价
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinShopAppraiseEntity weixinShopAppraise, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "商品评价添加成功";
		try{
			weixinShopAppraiseService.save(weixinShopAppraise);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品评价添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新商品评价
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinShopAppraiseEntity weixinShopAppraise, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "商品评价更新成功";
		WeixinShopAppraiseEntity t = weixinShopAppraiseService.get(WeixinShopAppraiseEntity.class, weixinShopAppraise.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinShopAppraise, t);
			weixinShopAppraiseService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "商品评价更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 商品评价新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinShopAppraiseEntity weixinShopAppraise, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinShopAppraise.getId())) {
			weixinShopAppraise = weixinShopAppraiseService.getEntity(WeixinShopAppraiseEntity.class, weixinShopAppraise.getId());
			req.setAttribute("weixinShopAppraisePage", weixinShopAppraise);
		}
		return new ModelAndView("weixin/shop/weixinShopAppraise-add");
	}
	/**
	 * 商品评价编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinShopAppraiseEntity weixinShopAppraise, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinShopAppraise.getId())) {
			weixinShopAppraise = weixinShopAppraiseService.getEntity(WeixinShopAppraiseEntity.class, weixinShopAppraise.getId());
			req.setAttribute("weixinShopAppraisePage", weixinShopAppraise);
		}
		return new ModelAndView("weixin/shop/weixinShopAppraise-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/shop/weixinShopAppraiseUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinShopAppraiseEntity weixinShopAppraise,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "商品评价";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinShopAppraiseEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinShopAppraise, request.getParameterMap());
			
			List<WeixinShopAppraiseEntity> weixinShopAppraises = this.weixinShopAppraiseService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("商品评价列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopAppraiseEntity.class, weixinShopAppraises);
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
	public void exportXlsByT(WeixinShopAppraiseEntity weixinShopAppraise,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "商品评价";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("商品评价列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinShopAppraiseEntity.class, null);
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
				List<WeixinShopAppraiseEntity> listWeixinShopAppraiseEntitys = 
					(List<WeixinShopAppraiseEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinShopAppraiseEntity.class,params);
				for (WeixinShopAppraiseEntity weixinShopAppraise : listWeixinShopAppraiseEntitys) {
					weixinShopAppraiseService.save(weixinShopAppraise);
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
	 * 批量审核
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doBatchAudit")
	@ResponseBody
	public AjaxJson doBatchAudit(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "商品评价审核成功";
		try{
			for(String id:ids.split(",")){
				WeixinShopAppraiseEntity weixinShopAppraise = systemService.getEntity(WeixinShopAppraiseEntity.class, 
				id
				);
				weixinShopAppraise.setStatus(1);

				weixinShopAppraiseService.saveOrUpdate(weixinShopAppraise);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商品评价审核失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
}
