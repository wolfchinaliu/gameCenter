package weixin.payment.controller;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.payment.entity.WeixinUsergetcardEntity;
import weixin.payment.service.WeixinUsergetcardServiceI;

import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
 * @Description: 卡券领取记录
 * @author onlineGenerator
 * @date 2015-06-17 14:54:14
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinUsergetcardController")
public class WeixinUsergetcardController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinUsergetcardController.class);

	@Autowired
	private WeixinUsergetcardServiceI weixinUsergetcardService;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
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
	 * 卡券领取记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinUsergetcard")
	public ModelAndView weixinUsergetcard(HttpServletRequest request) {
		return new ModelAndView("weixin/payment/weixinUsergetcardList");
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
	public void datagrid(WeixinUsergetcardEntity weixinUsergetcard,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinUsergetcardEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinUsergetcard, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinUsergetcardService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除卡券领取记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinUsergetcardEntity weixinUsergetcard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinUsergetcard = systemService.getEntity(WeixinUsergetcardEntity.class, weixinUsergetcard.getId());
		message = "卡券领取记录删除成功";
		try{
			weixinUsergetcardService.delete(weixinUsergetcard);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "卡券领取记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除卡券领取记录
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "卡券领取记录删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinUsergetcardEntity weixinUsergetcard = systemService.getEntity(WeixinUsergetcardEntity.class, 
				id
				);
				weixinUsergetcardService.delete(weixinUsergetcard);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "卡券领取记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加卡券领取记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinUsergetcardEntity weixinUsergetcard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "卡券领取记录添加成功";
		try{
			weixinUsergetcardService.save(weixinUsergetcard);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "卡券领取记录添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新卡券领取记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinUsergetcardEntity weixinUsergetcard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "卡券领取记录更新成功";
		WeixinUsergetcardEntity t = weixinUsergetcardService.get(WeixinUsergetcardEntity.class, weixinUsergetcard.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinUsergetcard, t);
			weixinUsergetcardService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "卡券领取记录更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 卡券领取记录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinUsergetcardEntity weixinUsergetcard, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinUsergetcard.getId())) {
			weixinUsergetcard = weixinUsergetcardService.getEntity(WeixinUsergetcardEntity.class, weixinUsergetcard.getId());
			req.setAttribute("weixinUsergetcardPage", weixinUsergetcard);
		}
		return new ModelAndView("weixin/payment/weixinUsergetcard-add");
	}
	/**
	 * 卡券领取记录编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinUsergetcardEntity weixinUsergetcard, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinUsergetcard.getId())) {
			weixinUsergetcard = weixinUsergetcardService.getEntity(WeixinUsergetcardEntity.class, weixinUsergetcard.getId());
			req.setAttribute("weixinUsergetcardPage", weixinUsergetcard);
		}
		return new ModelAndView("weixin/payment/weixinUsergetcard-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/payment/weixinUsergetcardUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinUsergetcardEntity weixinUsergetcard,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "卡券领取记录";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinUsergetcardEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinUsergetcard, request.getParameterMap());
			
			List<WeixinUsergetcardEntity> weixinUsergetcards = this.weixinUsergetcardService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("卡券领取记录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinUsergetcardEntity.class, weixinUsergetcards);
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
	public void exportXlsByT(WeixinUsergetcardEntity weixinUsergetcard,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "卡券领取记录";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("卡券领取记录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinUsergetcardEntity.class, null);
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
				List<WeixinUsergetcardEntity> listWeixinUsergetcardEntitys = 
					(List<WeixinUsergetcardEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinUsergetcardEntity.class,params);
				for (WeixinUsergetcardEntity weixinUsergetcard : listWeixinUsergetcardEntitys) {
					weixinUsergetcardService.save(weixinUsergetcard);
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
	 * 跳转到卡券核销页面
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goConsume")
	public ModelAndView goConsume(String id, HttpServletRequest req) {
		
		WeixinUsergetcardEntity weixinUsergetcard = weixinUsergetcardService.getEntity(WeixinUsergetcardEntity.class, id);
		req.setAttribute("weixinUsergetcardPage", weixinUsergetcard);
		
		return new ModelAndView("weixin/payment/weixinUsergetcard-consume");
	}
	
	/**
	 * 卡券核销
	 * @param weixinUsergetcard
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doConsume")
	@ResponseBody
	public AjaxJson doConsume(WeixinUsergetcardEntity weixinUsergetcard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		WeixinUsergetcardEntity t = weixinUsergetcardService.get(WeixinUsergetcardEntity.class, weixinUsergetcard.getId());
		try {
			
			if("0".equals(t.getStatus())){
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("code", t.getUserCardCode());
				
				String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
				if(StringUtil.isNotEmpty(accessTocken)){
		        	
		        	String url = WeixinUtil.consume_card_url.replace("TOKEN",accessTocken);
					JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonobj.toString());
					
					if(jsonObject!=null){
			    		if (("0").equals(jsonObject.get("errcode").toString())) {
			    			
			    			t.setStatus("1");
							weixinUsergetcardService.saveOrUpdate(t);
							
							message = "卡券消费成功";
							j.setMsg(message);
							return j;
			    		}
					}
				}
			}
				
			message = "卡券消费失败";						
		} catch (Exception e) {
			e.printStackTrace();
			message = "卡券消费失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
}
