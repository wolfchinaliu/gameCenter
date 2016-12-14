package weixin.customer.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.DataUtils;
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
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.model.message.IndustryTemplateMessageSend;
import org.jeewx.api.core.req.model.message.TemplateData;
import org.jeewx.api.core.req.model.message.TemplateMessage;
import org.jeewx.api.wxsendmsg.JwTemplateMessageAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import weixin.customer.entity.WeixinCustomerEntity;
import weixin.customer.entity.WeixinCustomerMsgEntity;
import weixin.customer.entity.WeixinCustomerTempEntity;
import weixin.customer.service.WeixinCustomerMsgServiceI;
import weixin.customer.service.WeixinCustomerServiceI;
import weixin.customer.service.WeixinCustomerTempServiceI;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.util.DateUtils;

/**
 * @Title: Controller
 * @Description: 客服消息表
 * @author onlineGenerator
 * @date 2015-09-01 17:58:33
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinCustomerMsgController")
public class WeixinCustomerMsgController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(WeixinCustomerMsgController.class);

	@Autowired
	private WeixinCustomerMsgServiceI weixinCustomerMsgService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinMemberServiceI weixinMemberService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	private WeixinCustomerServiceI weixinCustomerService;
	@Autowired
	private WeixinCustomerTempServiceI weixinCustomerTempService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 客服消息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinCustomerMsg")
	public ModelAndView weixinCustomerMsg(HttpServletRequest request) {
		return new ModelAndView("weixin/customer/weixinCustomerMsgList");
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
	public void datagrid(WeixinCustomerMsgEntity weixinCustomerMsg, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinCustomerMsgEntity.class, dataGrid);
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());// 根据公众ID进行数据隔离
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinCustomerMsg,
				request.getParameterMap());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		String createDate_begin = request.getParameter("createDate_begin");
		if (createDate_begin != null&&!"".equals(createDate_begin)) {
			Timestamp beginValue = null;
			try {
				beginValue = DataUtils.parseTimestamp(createDate_begin, "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cq.ge("createDate", beginValue);
		}
		String createDate_end = request.getParameter("createDate_end");
		if (createDate_end != null&&!"".equals(createDate_end)) {
			if (createDate_end.length() == 10) {
				createDate_end = createDate_end + " 23:59:59";
			}
			Timestamp endValue = null;
			try {
				endValue = DataUtils.parseTimestamp(createDate_end, "yyyy-MM-dd hh:mm:ss");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cq.le("createDate", endValue);
		}
		cq.add();
		this.weixinCustomerMsgService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除客服消息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinCustomerMsgEntity weixinCustomerMsg, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinCustomerMsg = systemService.getEntity(WeixinCustomerMsgEntity.class, weixinCustomerMsg.getId());
		message = "客服消息表删除成功";
		try {
			weixinCustomerMsgService.delete(weixinCustomerMsg);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客服消息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除客服消息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "客服消息表删除成功";
		try {
			for (String id : ids.split(",")) {
				WeixinCustomerMsgEntity weixinCustomerMsg = systemService.getEntity(WeixinCustomerMsgEntity.class, id);
				weixinCustomerMsgService.delete(weixinCustomerMsg);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "客服消息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加客服消息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinCustomerMsgEntity weixinCustomerMsg, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "客服消息表添加成功";
		try {
			weixinCustomerMsgService.save(weixinCustomerMsg);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客服消息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新客服消息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinCustomerMsgEntity weixinCustomerMsg, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "客服消息表更新成功";
		WeixinCustomerMsgEntity t = weixinCustomerMsgService.get(WeixinCustomerMsgEntity.class,
				weixinCustomerMsg.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinCustomerMsg, t);
			weixinCustomerMsgService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客服消息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 客服消息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinCustomerMsgEntity weixinCustomerMsg, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinCustomerMsg.getId())) {
			weixinCustomerMsg = weixinCustomerMsgService.getEntity(WeixinCustomerMsgEntity.class,
					weixinCustomerMsg.getId());
			req.setAttribute("weixinCustomerMsgPage", weixinCustomerMsg);
		}
		return new ModelAndView("weixin/customer/weixinCustomerMsg-add");
	}

	/**
	 * 客服消息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinCustomerMsgEntity weixinCustomerMsg, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinCustomerMsg.getId())) {
			weixinCustomerMsg = weixinCustomerMsgService.getEntity(WeixinCustomerMsgEntity.class,
					weixinCustomerMsg.getId());
			req.setAttribute("weixinCustomerMsgPage", weixinCustomerMsg);
		}
		return new ModelAndView("weixin/customer/weixinCustomerMsg-update");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/customer/weixinCustomerMsgUpload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinCustomerMsgEntity weixinCustomerMsg, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "客服消息表";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader("content-disposition",
						"attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
				response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			CriteriaQuery cq = new CriteriaQuery(WeixinCustomerMsgEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinCustomerMsg,
					request.getParameterMap());

			List<WeixinCustomerMsgEntity> weixinCustomerMsgs = this.weixinCustomerMsgService.getListByCriteriaQuery(cq,
					false);
			workbook = ExcelExportUtil.exportExcel(
					new ExcelTitle("客服消息表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
					WeixinCustomerMsgEntity.class, weixinCustomerMsgs);
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
	public void exportXlsByT(WeixinCustomerMsgEntity weixinCustomerMsg, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "客服消息表";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader("content-disposition",
						"attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
				response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			workbook = ExcelExportUtil.exportExcel(
					new ExcelTitle("客服消息表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
					WeixinCustomerMsgEntity.class, null);
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
				List<WeixinCustomerMsgEntity> listWeixinCustomerMsgEntitys = (List<WeixinCustomerMsgEntity>) ExcelImportUtil
						.importExcelByIs(file.getInputStream(), WeixinCustomerMsgEntity.class, params);
				for (WeixinCustomerMsgEntity weixinCustomerMsg : listWeixinCustomerMsgEntitys) {
					weixinCustomerMsgService.save(weixinCustomerMsg);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				LOGGER.error(ExceptionUtil.getExceptionMessage(e));
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

	/**
	 * 请求客服帮组方法
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "talk")
	public ModelAndView talk(HttpServletRequest req) {
		String receiveOpenId = req.getParameter("receiveOpenId");
		String t = req.getParameter("t");
		String AId = req.getParameter("AId");
		String sendFlag = req.getParameter("sendFlag");
		StringBuffer buff = new StringBuffer();

		if (StringUtils.equals("false", sendFlag)) {
			// 客服进入
			buff.append("from WeixinCustomerMsgEntity t where 1=1");
			buff.append(" and t.accountid=").append("'").append(t).append("'");
			buff.append(" and (t.createName=").append("'").append(receiveOpenId).append("'");
			buff.append(" or t.receiveOpenId=").append("'").append(receiveOpenId).append("'").append(")");
			WeixinMemberEntity weixinMember = weixinMemberService
					.getWeixinMemberEntityByOpenIdAndAccountId(receiveOpenId, t);
			if (null != weixinMember) {
				req.setAttribute("headImg", weixinMember.getHeadImgUrl());
				req.setAttribute("nickName", weixinMember.getNickName());
			}
		} else {
			if (AId == null || "".equals(AId)) {

			}
			buff.append("from WeixinCustomerMsgEntity t where 1=1");
			buff.append(" and t.accountid=").append("'").append(t).append("'");
			buff.append(" and (t.createName=").append("'").append(AId).append("'");
			buff.append(" or t.receiveOpenId=").append("'").append(AId).append("'").append(")");
			WeixinMemberEntity weixinMember = weixinMemberService.getWeixinMemberEntityByOpenIdAndAccountId(AId, t);
			req.setAttribute("nickName", weixinMember.getNickName());
		}

		if (sendFlag == null || sendFlag.equals("")) {
			sendFlag = "true";
		}
		req.setAttribute("sendFlag", sendFlag);
		List<WeixinCustomerMsgEntity> list = weixinCustomerMsgService.findByQueryString(buff.toString());
		req.setAttribute("msgList", list);
		req.setAttribute("receiveOpenId", receiveOpenId);
		req.setAttribute("sWeimobId", t);
		req.setAttribute("AId", AId);
		WeixinMemberEntity weixinMember = weixinMemberService.getWeixinMemberEntityByOpenIdAndAccountId(AId, t);
		if (null != weixinMember) {
			req.setAttribute("currentHeadImg", weixinMember.getHeadImgUrl());
		}
		return new ModelAndView("weixin/customer/talk/talk");
	}

	@RequestMapping(params = "GetMessageData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson GetMessageData(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String accountId = request.getParameter("weimobid");
		String openId = request.getParameter("AId");
		WeixinMemberEntity entity = weixinMemberService.getWeixinMemberEntityByOpenIdAndAccountId(openId, accountId);
		if (entity == null || entity.getSubscribe() != "1") {
			j.setSuccess(false);
			j.setMsg("会员不存在");
			return j;
		}
		String hql = "from WeixinCustomerMsgEntity t where t.accountid='" + accountId + "' and t.sendOpenId='" + openId
				+ "'";
		List list = weixinCustomerMsgService.findByQueryString(hql);
		j.setSuccess(true);
		j.setObj(list);
		return j;
	}

	private String getTempId(String shortId, String openId, String accountId, String token) {
		WeixinCustomerTempEntity tempEntity = weixinCustomerTempService
				.getWeixinCustomerTempEntityByShortAndAccountId(shortId, accountId);
		String tempId = "";
		if (tempEntity != null) {
			tempId = tempEntity.getTemplateId();
		} else {
			try {
				tempId = JwTemplateMessageAPI.addTemplate(token, shortId);
			} catch (WexinReqException e) {
				return "";
			}
			WeixinCustomerTempEntity addEntity = new WeixinCustomerTempEntity();
			addEntity.setCreateDate(new Date());
			addEntity.setCreateName(openId);
			addEntity.setTemplateId(tempId);
			addEntity.setTemplateIdShort(shortId);
			addEntity.setAccountid(accountId);
			weixinCustomerTempService.saveOrUpdate(addEntity);
		}
		return tempId;
	}

	@RequestMapping(params = "sendmessage")
	@ResponseBody
	public AjaxJson sendmessage(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		// 如果这个有值而且是True说明，是需要帮组
		String sendFlag = request.getParameter("sendFlag");
		if (StringUtils.equals(sendFlag, "true")) {
			// 如果是发送消息
			j = question(request, response);
		} else {
			j = answer(request, response);
		}
		return j;
	}

	private AjaxJson question(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			String accountId = request.getParameter("weimobid");
			String openId = request.getParameter("AId");
			String content = request.getParameter("content");
			String receiveOpenId = request.getParameter("receiveOpenId");
			String nickName = request.getParameter("nickName");
			String hql = "from WeixinCustomerEntity t where t.accountid='" + accountId + "' and t.receiveMessages='Y' order by sorts ";
			List<WeixinCustomerEntity> list = weixinCustomerService.findByQueryString(hql);
			String toUser = "";
			if (list != null && list.size() > 0) {
				WeixinCustomerEntity en = list.get(0);
				toUser = en.getOpenId();
			}
			if (toUser == null || toUser.equals("")) {
				// 客服不在線
				j.setSuccess(false);
				message = "当前没有客服在线";
				j.setMsg(message);
				return j;
			}
			WeixinCustomerMsgEntity weixinCustomerMsg = new WeixinCustomerMsgEntity();
			weixinCustomerMsg.setAccountid(accountId);
			weixinCustomerMsg.setContent(content);
			weixinCustomerMsg.setCreateDate(new Date());
			weixinCustomerMsg.setCreateName(openId);
			weixinCustomerMsg.setSendOpenId(openId);
			weixinCustomerMsg.setSendNickName(nickName);
			weixinCustomerMsg.setReceiveOpenId(receiveOpenId);
			weixinCustomerMsgService.save(weixinCustomerMsg);
			systemService.addLog(toUser + "请求客服交流", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			String token = weixinAccountService.getAccessTokenByPrimaryKey(accountId);
			String tempId = getTempId("OPENTM207178317", openId, accountId, token);
			// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
			if (tempId == null || tempId.equals("")) {
				// 沒有模板ID
			}

			IndustryTemplateMessageSend industryTemplateMessageSend = new IndustryTemplateMessageSend();
			industryTemplateMessageSend.setAccess_token(token);
			industryTemplateMessageSend.setTemplate_id(tempId);
			industryTemplateMessageSend.setTouser(toUser);
			StringBuffer url = new StringBuffer();
			url.append(ResourceUtil.getDomain()).append("/").append("weixinCustomerMsgController.do?talk");
			url.append("&receiveOpenId=").append(openId);
			url.append("&sendFlag=").append("false");
			url.append("&t=").append(accountId);
			url.append("&AId=").append(toUser);
			industryTemplateMessageSend.setUrl(url.toString());
			industryTemplateMessageSend.setTopcolor("#ffAADD");
			TemplateMessage data = new TemplateMessage();
			TemplateData first = new TemplateData();
			first.setColor("#173177");
			first.setValue(nickName);

			TemplateData keynote1 = new TemplateData();
			keynote1.setColor("#173177");
			keynote1.setValue(content);

			TemplateData keynote2 = new TemplateData();
			keynote2.setColor("#173122");
			keynote2.setValue(DateUtils.formatDate());

			data.setFirst(first);
			data.setKeynote1(keynote1);
			data.setKeynote2(keynote2);
			industryTemplateMessageSend.setData(data);
			String msg = JwTemplateMessageAPI.sendTemplateMsg(industryTemplateMessageSend);
			LOGGER.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "客服消息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	private AjaxJson answer(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			String accountId = request.getParameter("weimobid");
			String openId = request.getParameter("AId");
			String content = request.getParameter("content");
			String receiveOpenId = request.getParameter("receiveOpenId");
			String toUser = receiveOpenId;
			WeixinCustomerMsgEntity weixinCustomerMsg = new WeixinCustomerMsgEntity();
			weixinCustomerMsg.setAccountid(accountId);
			weixinCustomerMsg.setContent(content);
			weixinCustomerMsg.setCreateDate(new Date());
			weixinCustomerMsg.setCreateName(openId);
			weixinCustomerMsg.setSendOpenId(openId);
			weixinCustomerMsg.setReceiveOpenId(receiveOpenId);
			WeixinMemberEntity kefuEntity=weixinMemberService.getWeixinMemberEntityByOpenIdAndAccountId(openId, accountId);
			if(kefuEntity!=null){
				weixinCustomerMsg.setSendNickName(kefuEntity.getNickName());
			}
			WeixinMemberEntity helpEntity=weixinMemberService.getWeixinMemberEntityByOpenIdAndAccountId(receiveOpenId, accountId);
			if(kefuEntity!=null){
				weixinCustomerMsg.setReceiveNickName(helpEntity.getNickName());
			}
			weixinCustomerMsgService.save(weixinCustomerMsg);
			systemService.addLog("客服回复用户消息", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			String token = weixinAccountService.getAccessTokenByPrimaryKey(accountId);
			String tempId = getTempId("TM00631", openId, accountId, token);
			// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
			IndustryTemplateMessageSend industryTemplateMessageSend = new IndustryTemplateMessageSend();
			industryTemplateMessageSend.setAccess_token(token);
			industryTemplateMessageSend.setTemplate_id(tempId);
			industryTemplateMessageSend.setTouser(toUser);
			StringBuffer url = new StringBuffer();
			url.append(ResourceUtil.getDomain()).append("/").append("weixinCustomerMsgController.do?talk");
			url.append("&receiveOpenId=").append(openId);
			url.append("&sendFlag=").append("true");
			url.append("&t=").append(accountId);
			url.append("&AId=").append(toUser);
			industryTemplateMessageSend.setUrl(url.toString());
			industryTemplateMessageSend.setTopcolor("#ffAADD");
			TemplateMessage data = new TemplateMessage();
			TemplateData first = new TemplateData();
			first.setColor("#173177");
			first.setValue("有新的回复");

			TemplateData createDate = new TemplateData();
			createDate.setColor("#173177");
			createDate.setValue(DateUtils.formatDate());

			TemplateData processingResults = new TemplateData();
			processingResults.setColor("#173122");
			processingResults.setValue(content);

			TemplateData remark = new TemplateData();
			remark.setColor("#173122");
			remark.setValue("");

			data.setFirst(first);
			data.setCreateDate(createDate);
			data.setProcessingResults(processingResults);
			data.setRemark(remark);
			industryTemplateMessageSend.setData(data);
			String msg = JwTemplateMessageAPI.sendTemplateMsg(industryTemplateMessageSend);
			LOGGER.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客服消息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

}
