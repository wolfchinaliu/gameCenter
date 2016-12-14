package weixin.member.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import org.jeecgframework.core.util.LogUtil;
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

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.service.WeixinGroupServiceI;



/**   
 * @Title: Controller
 * @Description: 微信组
 * @author onlineGenerator
 * @date 2015-01-16 16:17:43
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinGroupController")
public class WeixinGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinGroupController.class);

	@Autowired
	 WeixinGroupServiceI weixinGroupService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	 SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 微信组列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinGroup")
	public ModelAndView weixinGroup(HttpServletRequest request) {
		return new ModelAndView("weixin/mm/group/weixinGroupList");
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
	public void datagrid(WeixinGroupEntity weixinGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinGroup, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除微信组
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinGroupEntity weixinGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		String accessToken = weixinAccountService.getAccessToken();
		String url = WeixinUtil.del_group_url.replace("ACCESS_TOKEN", accessToken);

		JSONObject jsonName= new JSONObject();
		jsonName.put("name", weixinGroup.getGroupName());

		JSONObject jsonO= new JSONObject();
		jsonO.put("group", jsonName);

		//获取接口返回结果
		JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET",jsonO.toString());

		weixinGroup = systemService.getEntity(WeixinGroupEntity.class, weixinGroup.getId());
		message = "45016:系统分组，不允许修改";
		if(jsonObject!=null){
			if (null == jsonObject.get("errcode")) {
				try{
					weixinGroupService.delete(weixinGroup);
					message = "微信组删除成功";
					//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}catch(Exception e){
					e.printStackTrace();
					message = "微信组删除失败";
					throw new BusinessException(e.getMessage());
				}
			}
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除微信组
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "微信组删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinGroupEntity weixinGroup = systemService.getEntity(WeixinGroupEntity.class, 
				id
				);
				weixinGroupService.delete(weixinGroup);
				//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "微信组删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加微信组
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinGroupEntity weixinGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		try{
			
			String accessToken = weixinAccountService.getAccessToken();
			String url = WeixinUtil.create_group_url.replace("ACCESS_TOKEN", accessToken);
			
			JSONObject jsonName= new JSONObject();
			jsonName.put("name", weixinGroup.getGroupName());
			
			JSONObject jsonO= new JSONObject();
			jsonO.put("group", jsonName);
			
			//获取接口返回结果
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET",jsonO.toString());
			if(jsonObject!=null){
				if (null == jsonObject.get("errcode")) {

					//获取返回结果
					JSONObject jsonResult = jsonObject.getJSONObject("group");
					
					weixinGroup.setGroupId(jsonResult.getInt("id"));
					weixinGroup.setGroupName(jsonResult.getString("name"));
					weixinGroupService.save(weixinGroup);
					message = "微信组添加成功";
				}
			}
			
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "微信组添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新微信组
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinGroupEntity weixinGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		WeixinGroupEntity t = weixinGroupService.get(WeixinGroupEntity.class, weixinGroup.getId());
		try {
			
			String accessToken = weixinAccountService.getAccessToken();
			String url = WeixinUtil.update_group_url.replace("ACCESS_TOKEN", accessToken);
			
			JSONObject jsonName= new JSONObject();
			jsonName.put("name", weixinGroup.getGroupName());
			jsonName.put("id", weixinGroup.getGroupId());
			
			JSONObject jsonO= new JSONObject();
			jsonO.put("group", jsonName);
			
			//获取接口返回结果
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET",jsonO.toString());
			if(jsonObject!=null){
				if (("0").equals(jsonObject.get("errcode").toString())) {

					MyBeanUtils.copyBeanNotNull2Bean(weixinGroup, t);
					weixinGroupService.saveOrUpdate(t);
					message = "微信组更新成功";
					//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "微信组更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}




	/**
	 * 微信组新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinGroupEntity weixinGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinGroup.getId())) {
			weixinGroup = weixinGroupService.getEntity(WeixinGroupEntity.class, weixinGroup.getId());
			req.setAttribute("weixinGroupPage", weixinGroup);
		}
		return new ModelAndView("weixin/mm/group/weixinGroup-add");
	}
	/**
	 * 微信组编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinGroupEntity weixinGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinGroup.getId())) {
			weixinGroup = weixinGroupService.getEntity(WeixinGroupEntity.class, weixinGroup.getId());
			req.setAttribute("weixinGroupPage", weixinGroup);
		}
		return new ModelAndView("weixin/mm/group/weixinGroup-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/mm/group/weixinGroupUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinGroupEntity weixinGroup,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "微信组";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinGroupEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinGroup, request.getParameterMap());
			
			List<WeixinGroupEntity> weixinGroups = this.weixinGroupService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信组列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinGroupEntity.class, weixinGroups);
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
	public void exportXlsByT(WeixinGroupEntity weixinGroup,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "微信组";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信组列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinGroupEntity.class, null);
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
				List<WeixinGroupEntity> listWeixinGroupEntitys = 
					(List<WeixinGroupEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinGroupEntity.class,params);
				for (WeixinGroupEntity weixinGroup : listWeixinGroupEntitys) {
					weixinGroupService.save(weixinGroup);
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
	 * 同步微信组
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doSynch")
	@ResponseBody
	public AjaxJson doSynch(WeixinGroupEntity weixinGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "微信组更新成功";
		String accountid = ResourceUtil.getWeiXinAccountId();
		if(!StringUtil.isNotEmpty(accountid)){
			message = "微信公众号原始ID不存在";
			j.setMsg(message);
			return j;
		}
		
		String accessToken = weixinAccountService.getAccessToken();
		String url = WeixinUtil.group_getall_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject = WeixinUtil.httpRequest(url, "POST",null);
			//jsonObject=JSONObject.fromObject("{\"groups\":[{\"id\":10,\"count\":8,\"name\":\"2\"},{\"id\":0,\"count\":8,\"name\":\"未1\"},{\"id\":1,\"count\":2,\"name\":\"黑名单12\"},{\"id\":2,\"count\":2,\"name\":\"黑3名单-3\"}]}");
			LogUtil.info(jsonObject);
			if(jsonObject!=null){
				if (null == jsonObject.get("errcode")) {
					 
					 JSONArray jsonArrayGroups=jsonObject.getJSONArray("groups");
					 List<WeixinGroupEntity> listGroup=new ArrayList<WeixinGroupEntity>();
					 for (int i = 0; i < jsonArrayGroups.size(); i++) {
						 JSONObject jo = (JSONObject) jsonArrayGroups.get(i);
						
						 WeixinGroupEntity saveGroup=new WeixinGroupEntity();
						 saveGroup.setGroupId(jo.getInt("id"));
						 saveGroup.setAccountid(accountid);
						 saveGroup.setCreateDate(new Date());
						 saveGroup.setCreateName(ResourceUtil.getSessionUserName().getUserName());
						 saveGroup.setSynchStatu("1");
						 saveGroup.setGroupName(jo.getString("name"));
						 saveGroup.setCount(jo.getInt("count"));
						 
						 //查询是否已经存在本地
						 WeixinGroupEntity t=weixinGroupService.getGroupEntityByParam(saveGroup);
						 if(t!=null){
							 
							 //更新
							 t.setGroupName(saveGroup.getGroupName());
							 t.setCount(saveGroup.getCount());

							 listGroup.add(t);
						 }else{
							 
							 //新增
							 listGroup.add(saveGroup);
						 }
					 }

					 //批量更新或新增
					 weixinGroupService.batchSave(listGroup);
					 message = "同步分组信息数据成功！";
					// systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				}
				else {
					message = "同步分组信息数据失败！错误码为："+jsonObject.getInt("errcode")+"错误信息为："+jsonObject.getString("errmsg");
				}
			}else{
				message = "同步分组信息数据失败！";
			}
		} catch (Exception e) {
			message = "同步分组信息数据失败！";
		}finally{
			//systemService.addLog(message, Globals.Log_Type_DEL,
			//		Globals.Log_Leavel_INFO);
			j.setMsg(this.message);
		}
		return j;
		
	
		
	}
}
