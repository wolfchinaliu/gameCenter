package weixin.lottery.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
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

import weixin.lottery.entity.WeixinDrawDetailEntity;
import weixin.lottery.entity.WeixinDrawrecordParam;
import weixin.lottery.service.WeixinDrawDetailServiceI;

/**
 * @Title: Controller
 * @Description: 抽奖记录表
 * @author onlineGenerator
 * @date 2015-02-07 11:20:39
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinDrawDetailController")
public class WeixinDrawDetailController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(WeixinDrawDetailController.class);

	@Autowired
	private WeixinDrawDetailServiceI weixinDrawDetailService;
	@Autowired
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@RequestMapping(params = "hdRecord")
	public ModelAndView hdRecord(HttpServletRequest request) {
		String hdId = request.getParameter("hdId");
		request.setAttribute("hdId", hdId);
		return new ModelAndView("weixin/lottery/weixinDrawrecordList");
	}
	
	@RequestMapping(params = "datagridBySql")
	public void datagridBySql(WeixinDrawrecordParam weixinDrawrecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String hdid = request.getParameter("hdid");
		request.setAttribute("hdid", hdid);
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT  COUNT(1) counts,hdid , opendid , accountid FROM weixin_draw_detail t where 1=1 " );
		if(hdid!=null&&!"".equals(hdid)){
			sql.append(" and t.hdid=").append("'").append(hdid).append("'");
		}
		sql.append(" and t.accountid=").append("'").append(ResourceUtil.getWeiXinAccountId()).append("'");
		sql.append(" GROUP BY t.hdid ,t.opendid,t.accountid");
		HqlQuery hqlQuery=new HqlQuery(WeixinDrawrecordParam.class,sql.toString(),dataGrid);
		PageList pageList=this.weixinDrawDetailService.getPageListBySql(hqlQuery, false);
		List<Object[]> list= pageList.getResultList();
		List<WeixinDrawrecordParam> param=new ArrayList<WeixinDrawrecordParam>();
		int i=0;
		for (Object[] objects : list) {
			WeixinDrawrecordParam wd=new WeixinDrawrecordParam();
			Object counts=objects[0];
			if(counts!=null){
				wd.setCounts(Integer.valueOf(counts.toString()));
			}
			Object hdi=objects[1];
			if(hdi!=null){
				wd.setHdid(hdi.toString());
			}
			Object opendid=objects[2];
			if(opendid!=null){
				wd.setOpendid(opendid.toString());
			}
			Object accountid=objects[3];
			if(accountid!=null){
				wd.setAccountid(accountid.toString());
			}
			wd.setId(i+"");
			i++;
			param.add(wd);
			
		}
	
		dataGrid.setResults(param);
		dataGrid.setTotal(pageList.getCount());
		dataGrid.setPage(pageList.getCurPageNO());
		dataGrid.setRows(pageList.getOffset());
		TagUtil.datagrid(response, dataGrid);
		
	}
	/**
	 * 抽奖记录表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinDrawDetail")
	public ModelAndView weixinDrawDetail(HttpServletRequest request) {
		request.setAttribute("hdid",
				request.getParameter("hdid"));
		request.setAttribute("opendid",
				request.getParameter("opendid"));
		return new ModelAndView("weixin/lottery/weixinDrawDetailList");
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
	public void datagrid(WeixinDrawDetailEntity weixinDrawDetail,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinDrawDetailEntity.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				weixinDrawDetail, request.getParameterMap());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinDrawDetailService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除抽奖记录表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinDrawDetailEntity weixinDrawDetail,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinDrawDetail = systemService.getEntity(
				WeixinDrawDetailEntity.class, weixinDrawDetail.getId());
		message = "抽奖记录表删除成功";
		try {
			weixinDrawDetailService.delete(weixinDrawDetail);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "抽奖记录表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除抽奖记录表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "抽奖记录表删除成功";
		try {
			for (String id : ids.split(",")) {
				WeixinDrawDetailEntity weixinDrawDetail = systemService
						.getEntity(WeixinDrawDetailEntity.class, id);
				weixinDrawDetailService.delete(weixinDrawDetail);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "抽奖记录表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加抽奖记录表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinDrawDetailEntity weixinDrawDetail,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "抽奖记录表添加成功";
		try {
			weixinDrawDetailService.save(weixinDrawDetail);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "抽奖记录表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新抽奖记录表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinDrawDetailEntity weixinDrawDetail,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "抽奖记录表更新成功";
		WeixinDrawDetailEntity t = weixinDrawDetailService.get(
				WeixinDrawDetailEntity.class, weixinDrawDetail.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinDrawDetail, t);
			weixinDrawDetailService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "抽奖记录表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 抽奖记录表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinDrawDetailEntity weixinDrawDetail,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinDrawDetail.getId())) {
			weixinDrawDetail = weixinDrawDetailService.getEntity(
					WeixinDrawDetailEntity.class, weixinDrawDetail.getId());
			req.setAttribute("weixinDrawDetailPage", weixinDrawDetail);
		}
		return new ModelAndView("weixin/lottery/weixinDrawDetail-add");
	}

	/**
	 * 抽奖记录表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinDrawDetailEntity weixinDrawDetail,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinDrawDetail.getId())) {
			weixinDrawDetail = weixinDrawDetailService.getEntity(
					WeixinDrawDetailEntity.class, weixinDrawDetail.getId());
			req.setAttribute("weixinDrawDetailPage", weixinDrawDetail);
		}
		return new ModelAndView("weixin/lottery/weixinDrawDetail-update");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/lottery/weixinDrawDetailUpload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinDrawDetailEntity weixinDrawDetail,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "抽奖记录表";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinDrawDetailEntity.class,
					dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil
					.installHql(cq, weixinDrawDetail, request.getParameterMap());

			List<WeixinDrawDetailEntity> weixinDrawDetails = this.weixinDrawDetailService
					.getListByCriteriaQuery(cq, false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("抽奖记录表列表",
					"导出人:" + ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinDrawDetailEntity.class, weixinDrawDetails);
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
	public void exportXlsByT(WeixinDrawDetailEntity weixinDrawDetail,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "抽奖记录表";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("抽奖记录表列表",
					"导出人:" + ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinDrawDetailEntity.class, null);
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
	public AjaxJson importExcel(HttpServletRequest request,
			HttpServletResponse response) {
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
				List<WeixinDrawDetailEntity> listWeixinDrawDetailEntitys = (List<WeixinDrawDetailEntity>) ExcelImportUtil
						.importExcelByIs(file.getInputStream(),
								WeixinDrawDetailEntity.class, params);
				for (WeixinDrawDetailEntity weixinDrawDetail : listWeixinDrawDetailEntitys) {
					weixinDrawDetailService.save(weixinDrawDetail);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
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
