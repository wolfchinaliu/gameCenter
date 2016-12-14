package weixin.message.controller;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinGroupServiceI;
import weixin.member.service.WeixinMemberServiceI;
import weixin.message.entity.WeixinMessageGroupEntity;
import weixin.message.service.WeixinMessageGroupServiceI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
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

import weixin.guanjia.core.util.WeixinUtil;



/**   
 * @Title: Controller
 * @Description: 群发消息
 * @author onlineGenerator
 * @date 2015-01-21 11:29:31
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinMessageGroupController")
public class WeixinMessageGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinMessageGroupController.class);

	@Autowired
	private WeixinMessageGroupServiceI weixinMessageGroupService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	@Autowired
	private WeixinMemberServiceI weixinMemberService;
	
	@Autowired
	WeixinGroupServiceI weixinGroupService;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 群发消息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinMessageGroup")
	public ModelAndView weixinMessageGroup(HttpServletRequest request) {
		return new ModelAndView("weixin/message/weixinMessageGroupList");
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
	public void datagrid(WeixinMessageGroupEntity weixinMessageGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinMessageGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMessageGroup, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinMessageGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除群发消息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinMessageGroupEntity weixinMessageGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinMessageGroup = systemService.getEntity(WeixinMessageGroupEntity.class, weixinMessageGroup.getId());
		message = "群发消息删除成功";
		try{
			weixinMessageGroupService.delete(weixinMessageGroup);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "群发消息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除群发消息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "群发消息删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinMessageGroupEntity weixinMessageGroup = systemService.getEntity(WeixinMessageGroupEntity.class, 
				id
				);
				weixinMessageGroupService.delete(weixinMessageGroup);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "群发消息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加群发消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinMessageGroupEntity weixinMessageGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		try{
			
			//群发对象，0：全部，1：按分组选择
			if(("0").equals(weixinMessageGroup.getSendType())){
				
				//全部发送
				if(sendMessageToAll(weixinMessageGroup.getNote())){
					
					message = "消息群发成功，但用户收到消息可能需要一定时间！";
				}
			}
			if(("1").equals(weixinMessageGroup.getSendType())){
				
				//获取分组
				String weixinGroup = request.getParameter("weixinGroup");
				
				if(seadMessageToGroup(weixinMessageGroup.getNote(), weixinGroup)){
					
					message = "消息群发成功，但用户收到消息可能需要一定时间！";
				}
			}
			
			weixinMessageGroup.setCreateTime(new Date());
			weixinMessageGroupService.save(weixinMessageGroup);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "消息群发失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 按组群发文本消息
	 * @param note
	 * @param groupId
	 * @return
	 */
	public boolean seadMessageToGroup(String note, String groupId){
		
		//文本信息群发参数
		JSONObject jsonObj = new JSONObject();
		
		JSONObject filterObj = new JSONObject();
		filterObj.put("is_to_all", false);
		filterObj.put("group_id", groupId);
		
		JSONObject contentObj = new JSONObject();
		contentObj.put("content", note);
		
		jsonObj.put("filter", filterObj);
		jsonObj.put("msgtype", "text");
		jsonObj.put("text", contentObj);
		
		//高级群发接口
        String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
        if(StringUtil.isNotEmpty(accessTocken)){
        	
        	String url = WeixinUtil.send_group_message_url.replace("ACCESS_TOKEN",accessTocken);
        	JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonObj.toString());
        	
        	if(jsonObject!=null){
    			if (("0").equals(jsonObject.get("errcode").toString())) {
    				
    				return true;
    			}
    		}
        }
        
        return false;
	}
	
	/**
	 * 群发文本消息
	 * @param note
	 * @return
	 */
	public boolean sendMessageToAll(String note){
		
		//查询所有关注用户,每次最多10000个 
		String hql = "from WeixinMemberEntity where accountId='"+ResourceUtil.getWeiXinAccountId()+"'";
		List<WeixinMemberEntity> weixinMemberList = this.systemService.findByQueryString(hql);
		
		String[] boolArray = new String[weixinMemberList.size()];
		
		for(int i=0;i<weixinMemberList.size();i++){
			
			WeixinMemberEntity weixinMember = weixinMemberList.get(i);
			
			boolArray[i] = weixinMember.getOpenId();
			
		}
		
		JSONArray jsonArray1 = JSONArray.fromObject(boolArray);

		//文本信息群发参数
		JSONObject jsonObj = new JSONObject();
		
		JSONObject contentObj = new JSONObject();
		contentObj.put("content", note);
		
		jsonObj.put("text", contentObj);
		jsonObj.put("msgtype", "text");
		jsonObj.put("touser", jsonArray1);
		
		//高级群发接口
        String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
        if(StringUtil.isNotEmpty(accessTocken)){
        	
        	String url = WeixinUtil.send_openid_message_url.replace("ACCESS_TOKEN",accessTocken);
        	JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonObj.toString());
        	
        	if(jsonObject!=null){
        		if (("0").equals(jsonObject.get("errcode").toString())) {
    				
    				return true;
    			}
    		}
        }
        
        return false;
	}
	
	/**
	 * 更新群发消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinMessageGroupEntity weixinMessageGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "群发消息更新成功";
		WeixinMessageGroupEntity t = weixinMessageGroupService.get(WeixinMessageGroupEntity.class, weixinMessageGroup.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinMessageGroup, t);
			weixinMessageGroupService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "群发消息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 群发消息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinMessageGroupEntity weixinMessageGroup, HttpServletRequest request) {
		if (StringUtil.isNotEmpty(weixinMessageGroup.getId())) {
			weixinMessageGroup = weixinMessageGroupService.getEntity(WeixinMessageGroupEntity.class, weixinMessageGroup.getId());
			request.setAttribute("weixinMessageGroupPage", weixinMessageGroup);
		}
		
		WeixinGroupEntity weixinGroupEntity = new WeixinGroupEntity();
		weixinGroupEntity.setAccountid(ResourceUtil.getWeiXinAccountId());
		//分组列表
		CriteriaQuery cq = new CriteriaQuery(WeixinGroupEntity.class);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinGroupEntity, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		List<WeixinGroupEntity> weixinGroupList = weixinGroupService.getListByCriteriaQuery(cq, false);
		request.setAttribute("weixinGroupList", weixinGroupList);
				
		return new ModelAndView("weixin/message/weixinMessageGroup-add");
	}
	/**
	 * 群发消息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinMessageGroupEntity weixinMessageGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinMessageGroup.getId())) {
			weixinMessageGroup = weixinMessageGroupService.getEntity(WeixinMessageGroupEntity.class, weixinMessageGroup.getId());
			req.setAttribute("weixinMessageGroupPage", weixinMessageGroup);
		}
		return new ModelAndView("weixin/message/weixinMessageGroup-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/message/weixinMessageGroupUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinMessageGroupEntity weixinMessageGroup,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "群发消息";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinMessageGroupEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMessageGroup, request.getParameterMap());
			
			cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
			cq.add();
			
			List<WeixinMessageGroupEntity> weixinMessageGroups = this.weixinMessageGroupService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("群发消息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinMessageGroupEntity.class, weixinMessageGroups);
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
	public void exportXlsByT(WeixinMessageGroupEntity weixinMessageGroup,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "群发消息";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("群发消息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinMessageGroupEntity.class, null);
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
				List<WeixinMessageGroupEntity> listWeixinMessageGroupEntitys = 
					(List<WeixinMessageGroupEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinMessageGroupEntity.class,params);
				for (WeixinMessageGroupEntity weixinMessageGroup : listWeixinMessageGroupEntitys) {
					weixinMessageGroupService.save(weixinMessageGroup);
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
