package weixin.message.controller;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.model.TextItem;
import weixin.guanjia.message.model.TextMessageKf;
import weixin.guanjia.message.service.CustomerMessageService;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.message.entity.WeixinMessageEntity;
import weixin.message.service.WeixinMessageServiceI;

import java.util.ArrayList;
import java.util.Date;
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
import java.io.UnsupportedEncodingException;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;
import java.net.URLDecoder;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

import org.jeecgframework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: 单发消息
 * @author onlineGenerator
 * @date 2015-01-21 11:29:58
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinMessageController")
public class WeixinMessageController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinMessageController.class);

	@Autowired
	private WeixinMessageServiceI weixinMessageService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private CustomerMessageService customerMessageService;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	@Autowired
	private WeixinMemberServiceI weixinMemberService;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 单发消息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinMessage")
	public ModelAndView weixinMessage(HttpServletRequest request) {
		return new ModelAndView("weixin/message/weixinMessageList");
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
	public void datagrid(WeixinMessageEntity weixinMessage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinMessageEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMessage, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinMessageService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 选择消息发送对象跳转页面
	 * @return
	 */
	@RequestMapping(params = "members")
	public String members() {
		return "weixin/message/members";
	}

	/**
	 * 关注用户显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridMember")
	public void datagridMember(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		CriteriaQuery cq = new CriteriaQuery(WeixinMemberEntity.class, dataGrid);
		cq.eq("accountId", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		cq.eq("subscribe", "1");//是否订阅1是0否
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除单发消息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinMessageEntity weixinMessage, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinMessage = systemService.getEntity(WeixinMessageEntity.class, weixinMessage.getId());
		message = "单发消息删除成功";
		try{
			weixinMessageService.delete(weixinMessage);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "单发消息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除单发消息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "单发消息删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinMessageEntity weixinMessage = systemService.getEntity(WeixinMessageEntity.class, 
				id
				);
				weixinMessageService.delete(weixinMessage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "单发消息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	 /**
	 * 单发消息新增页面跳转
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HttpServletRequest request) throws UnsupportedEncodingException {

		String ids = request.getParameter("openids");
		//String nickNames = new String(request.getParameter("nickNames").getBytes("ISO-8859-1"),"UTF-8");
		String nickNames = request.getParameter("nickNames");
		String nickName = URLDecoder.decode(nickNames, "UTF-8");



		
		request.setAttribute("receiveUserId", ids);
		request.setAttribute("nickNames", nickName);
		
		return new ModelAndView("weixin/message/goSendMessagePage");
	}
		
	/**
	 * 发消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinMessageEntity weixinMessage, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "消息发送成功";
		try{
			
			//获取openId数组
			String[] receiveUserIds = request.getParameter("receiveUserId").split(",");
			
			String msgtype = request.getParameter("msgtype");//文本消息为text，语音为voice, 图片为image，视频为video
			//String str = new String(request.getParameter("param").getBytes("ISO-8859-1"),"utf-8");//文本输入内容,如果选择的是图片、语音、视频，则为medio_id
			//String msgcontent = request.getParameter("param");
			//String str = URLDecoder.decode(msgcontent, "UTF-8");
			String str = weixinMessage.getNote();//文本输入内容,如果选择的是图片、语音、视频，则为medio_id
			
//			for (int i = 0; i < receiveUserIds.length; i++) {
				
//				String openId = receiveUserIds[i].trim();
				String[] openId =receiveUserIds;

				JSONObject jsonObj = new JSONObject();
				
				//图文消息
				if(("mpnews").equals(msgtype)){
					
					JSONObject mpnewsObjObj = new JSONObject();
					mpnewsObjObj.put("media_id", str);
					
					jsonObj.put("mpnews", mpnewsObjObj);
					jsonObj.put("msgtype", "mpnews");
					jsonObj.put("touser", openId);
				}

				//文本消息
				if(("text").equals(msgtype)){
					
					JSONObject contentObj = new JSONObject();
					contentObj.put("content", str);
					
					jsonObj.put("text", contentObj);
					jsonObj.put("msgtype", "text");
					jsonObj.put("touser", openId);
				}
				
				//图片
				if(("image").equals(msgtype)){
					
					JSONObject mediaObj = new JSONObject();
					mediaObj.put("media_id", str);
					
					jsonObj.put("image", mediaObj);
					jsonObj.put("msgtype", "image");
					jsonObj.put("touser", openId);
				}
				
				//语音
				if(("voice").equals(msgtype)){
					
					JSONObject voiceObj = new JSONObject();
					voiceObj.put("media_id", str);
					
					jsonObj.put("voice", voiceObj);
					jsonObj.put("msgtype", "voice");
					jsonObj.put("touser", openId);
				}
				
				//视频
				if(("video").equals(msgtype)){
					
					JSONObject videoObj = new JSONObject();
					videoObj.put("media_id", str);
					
					jsonObj.put("video", videoObj);
					jsonObj.put("msgtype", "video");
					jsonObj.put("touser", openId);
				}
				
				String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
				//根据OpenID列表群发
				String url = WeixinUtil.send_openid_message_url.replace("ACCESS_TOKEN",accessTocken);
				JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonObj.toString());
	        	
	        	if(jsonObject!=null){
	    			if (("0").equals(jsonObject.get("errcode").toString())) {
	    				
	    				String accountId = ResourceUtil.getWeiXinAccountId();
	    	        	//保存发送的消息
						List<WeixinMessageEntity> weixinMessageEntities=new ArrayList<WeixinMessageEntity>();

						for (int i = 0; i <openId.length ; i++) {
							WeixinMemberEntity weixinMemberEntity = weixinMemberService.getWeixinMemberEntityByOpenIdAndAccountId(openId[i], accountId);
							weixinMessage.setReceiveUserId(openId[i]);
							weixinMessage.setReceiveUserId(openId[i]);
							weixinMessage.setReceiveUserName(weixinMemberEntity.getNickName());
							weixinMessage.setCreateTime(new Date());
							weixinMessage.setAccountid(accountId);
							weixinMessage.setNote(str);
							weixinMessageEntities.add(weixinMessage);
						}

						weixinMessageService.batchSave(weixinMessageEntities);
	    				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
	    				
	    				message = "消息发送成功";
	    			}
	    		}
//			}
		}catch(Exception e){
			e.printStackTrace();
			message = "消息发送失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新单发消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinMessageEntity weixinMessage, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "单发消息更新成功";
		WeixinMessageEntity t = weixinMessageService.get(WeixinMessageEntity.class, weixinMessage.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinMessage, t);
			weixinMessageService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "单发消息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 单发消息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinMessageEntity weixinMessage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinMessage.getId())) {
			weixinMessage = weixinMessageService.getEntity(WeixinMessageEntity.class, weixinMessage.getId());
			req.setAttribute("weixinMessagePage", weixinMessage);
		}
		return new ModelAndView("weixin/message/weixinMessage-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/message/weixinMessageUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinMessageEntity weixinMessage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "单发消息";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinMessageEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMessage, request.getParameterMap());
			
			cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
			cq.add();
			
			List<WeixinMessageEntity> weixinMessages = this.weixinMessageService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("单发消息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinMessageEntity.class, weixinMessages);
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
	public void exportXlsByT(WeixinMessageEntity weixinMessage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "单发消息";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("单发消息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinMessageEntity.class, null);
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
				List<WeixinMessageEntity> listWeixinMessageEntitys = 
					(List<WeixinMessageEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinMessageEntity.class,params);
				for (WeixinMessageEntity weixinMessage : listWeixinMessageEntitys) {
					weixinMessageService.save(weixinMessage);
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
