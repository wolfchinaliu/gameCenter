package weixin.lottery.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import weixin.lottery.entity.WeixinWinningrecordEntity;
import weixin.lottery.entity.WeixinWinningrecordlxcEntity;
import weixin.lottery.service.WeixinWinningrecordServiceI;
import weixin.util.WeiXinConstants;



/**   
 * @Title: Controller
 * @Description: 中奖记录
 * @author onlineGenerator
 * @date 2015-02-05 15:53:42
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinWinningrecordController")
public class WeixinWinningrecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinWinningrecordController.class);

	@Autowired
	private WeixinWinningrecordServiceI weixinWinningrecordService;
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
	 * 中奖纪录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "shakePrize")
	public ModelAndView goPrizeRecord(HttpServletRequest request) {
		String hdId = request.getParameter("hdId");
		request.setAttribute("hdId", hdId);
		return new ModelAndView("weixin/lottery/weixinWinningrecordList");
	}


	@RequestMapping(params = "goPrizeRecord")
	public ModelAndView goPrizeRecordd(HttpServletRequest request) {
        String hdId = request.getParameter("hdId");
        String lotteryType = request.getParameter("lotteryType");
        request.setAttribute("hdId", hdId);
        request.setAttribute("lotteryType", lotteryType);
        return new ModelAndView("weixin/lottery/weixinWinningrecordList");
    }



	/**
	 * 中奖记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinWinningrecord")
	public ModelAndView weixinWinningrecord(HttpServletRequest request) {
		return new ModelAndView("weixin/lottery/weixinWinningrecordList");
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
	public void datagrid(WeixinWinningrecordlxcEntity weixinWinningrecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinWinningrecordlxcEntity.class, dataGrid);
		String lotteryType = request.getParameter("lotteryType"); 
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinWinningrecord, request.getParameterMap());
		Map<String,Object> order =new HashMap<>(); //组装器排序使用map
		order.put("addtime", "desc");
		try{
//			if("1".equals(lotteryType) || "2".equals(lotteryType)){
//				cq.notEq("prizelevel", " ");
//			}
		    String[] status = {WeiXinConstants.SUCCESS,WeiXinConstants.SUCCESS_NOBIND};
		    cq.in("status", status);
		cq.setOrder(order);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinWinningrecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

//	/**
//	 * 删除中奖记录
//	 * 
//	 * @return
//	 */
//	@RequestMapping(params = "doDel")
//	@ResponseBody
//	public AjaxJson doDel(WeixinWinningrecordEntity weixinWinningrecord, HttpServletRequest request) {
//		AjaxJson j = new AjaxJson();
//		weixinWinningrecord = systemService.getEntity(WeixinWinningrecordEntity.class, weixinWinningrecord.getId());
//		message = "中奖记录删除成功";
//		try{
//			weixinWinningrecordService.delete(weixinWinningrecord);
//			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
//		}catch(Exception e){
//			e.printStackTrace();
//			message = "中奖记录删除失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}
//	
//	/**
//	 * 批量删除中奖记录
//	 * 
//	 * @return
//	 */
//	 @RequestMapping(params = "doBatchDel")
//	@ResponseBody
//	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
//		AjaxJson j = new AjaxJson();
//		message = "中奖记录删除成功";
//		try{
//			for(String id:ids.split(",")){
//				WeixinWinningrecordEntity weixinWinningrecord = systemService.getEntity(WeixinWinningrecordEntity.class, 
//				id
//				);
//				weixinWinningrecordService.delete(weixinWinningrecord);
//				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			message = "中奖记录删除失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}
//
//
//	/**
//	 * 添加中奖记录
//	 * 
//	 * @param ids
//	 * @return
//	 */
//	@RequestMapping(params = "doAdd")
//	@ResponseBody
//	public AjaxJson doAdd(WeixinWinningrecordEntity weixinWinningrecord, HttpServletRequest request) {
//		AjaxJson j = new AjaxJson();
//		message = "中奖记录添加成功";
//		try{
//			weixinWinningrecordService.save(weixinWinningrecord);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
//		}catch(Exception e){
//			e.printStackTrace();
//			message = "中奖记录添加失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}
//	
//	/**
//	 * 更新中奖记录
//	 * 
//	 * @param ids
//	 * @return
//	 */
//	@RequestMapping(params = "doUpdate")
//	@ResponseBody
//	public AjaxJson doUpdate(WeixinWinningrecordEntity weixinWinningrecord, HttpServletRequest request) {
//		AjaxJson j = new AjaxJson();
//		message = "中奖记录更新成功";
//		WeixinWinningrecordEntity t = weixinWinningrecordService.get(WeixinWinningrecordEntity.class, weixinWinningrecord.getId());
//		try {
//			MyBeanUtils.copyBeanNotNull2Bean(weixinWinningrecord, t);
//			weixinWinningrecordService.saveOrUpdate(t);
//			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "中奖记录更新失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}
//	
//
//	/**
//	 * 中奖记录新增页面跳转
//	 * 
//	 * @return
//	 */
//	@RequestMapping(params = "goAdd")
//	public ModelAndView goAdd(WeixinWinningrecordEntity weixinWinningrecord, HttpServletRequest req) {
//		if (StringUtil.isNotEmpty(weixinWinningrecord.getId())) {
//			weixinWinningrecord = weixinWinningrecordService.getEntity(WeixinWinningrecordEntity.class, weixinWinningrecord.getId());
//			req.setAttribute("weixinWinningrecordPage", weixinWinningrecord);
//		}
//		return new ModelAndView("weixin/lottery/weixinWinningrecord-add");
//	}
//	/**
//	 * 中奖记录编辑页面跳转
//	 * 
//	 * @return
//	 */
//	@RequestMapping(params = "goUpdate")
//	public ModelAndView goUpdate(WeixinWinningrecordEntity weixinWinningrecord, HttpServletRequest req) {
//		if (StringUtil.isNotEmpty(weixinWinningrecord.getId())) {
//			weixinWinningrecord = weixinWinningrecordService.getEntity(WeixinWinningrecordEntity.class, weixinWinningrecord.getId());
//			req.setAttribute("weixinWinningrecordPage", weixinWinningrecord);
//		}
//		return new ModelAndView("weixin/lottery/weixinWinningrecord-update");
//	}
//	
//	/**
//	 * 导入功能跳转
//	 * 
//	 * @return
//	 */
//	@RequestMapping(params = "upload")
//	public ModelAndView upload(HttpServletRequest req) {
//		return new ModelAndView("weixin/lottery/weixinWinningrecordUpload");
//	}
//	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinWinningrecordEntity weixinWinningrecord,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "中奖记录";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinWinningrecordEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinWinningrecord, request.getParameterMap());
			
			List<WeixinWinningrecordEntity> weixinWinningrecords = this.weixinWinningrecordService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("中奖记录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinWinningrecordEntity.class, weixinWinningrecords);
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
	public void exportXlsByT(WeixinWinningrecordEntity weixinWinningrecord,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "中奖记录";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("中奖记录列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinWinningrecordEntity.class, null);
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
//	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
//	@ResponseBody
//	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
//		AjaxJson j = new AjaxJson();
//		
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//			MultipartFile file = entity.getValue();// 获取上传文件对象
//			ImportParams params = new ImportParams();
//			params.setTitleRows(2);
//			params.setSecondTitleRows(1);
//			params.setNeedSave(true);
//			try {
//				List<WeixinWinningrecordEntity> listWeixinWinningrecordEntitys = 
//					(List<WeixinWinningrecordEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinWinningrecordEntity.class,params);
//				for (WeixinWinningrecordEntity weixinWinningrecord : listWeixinWinningrecordEntitys) {
//					weixinWinningrecordService.save(weixinWinningrecord);
//				}
//				j.setMsg("文件导入成功！");
//			} catch (Exception e) {
//				j.setMsg("文件导入失败！");
//				logger.error(ExceptionUtil.getExceptionMessage(e));
//			}finally{
//				try {
//					file.getInputStream().close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return j;
//	}
}
