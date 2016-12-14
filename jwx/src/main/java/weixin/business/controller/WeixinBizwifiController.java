package weixin.business.controller;
import weixin.business.entity.WeixinBizwifiEntity;
import weixin.business.entity.WeixinLocationEntity;
import weixin.business.service.WeixinBizwifiServiceI;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;

import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
 * @Description: 微信WIFI
 * @author onlineGenerator
 * @date 2015-07-08 13:47:06
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinBizwifiController")
public class WeixinBizwifiController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinBizwifiController.class);

	@Autowired
	private WeixinBizwifiServiceI weixinBizwifiService;
	
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
	 * 微信WIFI列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinBizwifi")
	public ModelAndView weixinBizwifi(HttpServletRequest request) {
		return new ModelAndView("weixin/business/weixinBizwifiList");
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
	public void datagrid(WeixinBizwifiEntity weixinBizwifi,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinBizwifiEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinBizwifi, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinBizwifiService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除微信WIFI
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinBizwifiEntity weixinBizwifi, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinBizwifi = systemService.getEntity(WeixinBizwifiEntity.class, weixinBizwifi.getId());
		
		try{
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("bssid", weixinBizwifi.getBssid());
			
			String accessTocken = weixinAccountService.getAccessToken();
	        if(StringUtil.isNotEmpty(accessTocken)){
	        	
	        	String url = WeixinUtil.del_bizwifi_url.replace("ACCESS_TOKEN",accessTocken);
				JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonobj.toString());
				if(jsonObject!=null){
		    		if (("0").equals(jsonObject.get("errcode").toString())) {
		    			
		    			weixinBizwifiService.delete(weixinBizwifi);
		    			message = "微信WIFI设备删除成功";
		    		}
		    	}
		    }
		}catch(Exception e){
			e.printStackTrace();
			message = "微信WIFI设备删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除微信WIFI
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "微信WIFI删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinBizwifiEntity weixinBizwifi = systemService.getEntity(WeixinBizwifiEntity.class, 
				id
				);
				weixinBizwifiService.delete(weixinBizwifi);
				//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "微信WIFI删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 查询设备列表
	 * @return
	 */
	@RequestMapping(params = "loadBizwifiList")
	@ResponseBody
	public AjaxJson loadBizwifiList(){
		
		AjaxJson j = new AjaxJson();
		
		try{
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("pageindex", 1);
			jsonobj.put("pagesize", 20);
			
			String accessTocken = weixinAccountService.getAccessToken();
	        if(StringUtil.isNotEmpty(accessTocken)){
	        	
	        	String url = WeixinUtil.load_bizwifi_url.replace("ACCESS_TOKEN",accessTocken);
				JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonobj.toString());
				if(jsonObject!=null){
		    		if (("0").equals(jsonObject.get("errcode").toString())) {
		    			
		    			JSONObject jdata = (JSONObject) jsonObject.get("data");
		    			
		    			JSONArray jsonArrayRecords = jdata.getJSONArray("records");
		    			int recordcount = jdata.getInt("recordcount");
		    			
		    			for (int i = 0; i < jsonArrayRecords.size(); i++) {
		    				 
		    				 JSONObject wifiObject = jsonArrayRecords.getJSONObject(i);
		    				 String shop_id = wifiObject.getString("shop_id");
		    				 String ssid = wifiObject.getString("ssid");
		    				 String bssid = wifiObject.getString("bssid");
		    				 
		    				 WeixinBizwifiEntity weixinBizwifi = systemService.findUniqueByProperty(WeixinBizwifiEntity.class, "ssid", ssid);
		    				 if(weixinBizwifi == null){
		    					 
		    					 WeixinLocationEntity weixinLocationEntity = systemService.findUniqueByProperty(WeixinLocationEntity.class, "poiId",shop_id);
		    					 
		    					 weixinBizwifi.setWeixinLocationEntity(weixinLocationEntity);
		    					 weixinBizwifi.setSsid(ssid);
		    					 weixinBizwifi.setBssid(bssid);
		    					 
		    					 weixinBizwifiService.save(weixinBizwifi);
		    				 }
		    			 }
		    			 
		    			if(recordcount == 0){
		    				message = "未查询到任何信息";
		    			}else{
		    				message = "同步完成,共查询到"+recordcount+"条设备记录";
		    			}		    			
		    		}
				}
	        }
		}catch(Exception e){
			e.printStackTrace();
			message = "同步完成微信WIFI设备失败";
			throw new BusinessException(e.getMessage());
		}
        
        j.setMsg(message);
		return j;
	}
	 
	/**
	 * 添加微信WIFI
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinBizwifiEntity weixinBizwifi, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		int locationIdList = Integer.parseInt(request.getParameter("locationIdList"));

		try{
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("shop_id", locationIdList);
			jsonobj.put("ssid", weixinBizwifi.getSsid());
			jsonobj.put("password", weixinBizwifi.getPassword());
			jsonobj.put("bssid", weixinBizwifi.getBssid());
			
			String accessTocken = weixinAccountService.getAccessToken();
	        if(StringUtil.isNotEmpty(accessTocken)){
	        	
	        	String url = WeixinUtil.create_bizwifi_url.replace("ACCESS_TOKEN",accessTocken);
				JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonobj.toString());
				if(jsonObject!=null){
		    		if (("0").equals(jsonObject.get("errcode").toString())) {

		    			//获取物料二维码
		    			String qrcodeImg0 = this.getQrcode_url(locationIdList, 0);
		    			if(StringUtils.isNotEmpty(qrcodeImg0)){
		    				weixinBizwifi.setQrcodeImg0(qrcodeImg0);
		    			}
		    			
		    			String qrcodeImg1 = this.getQrcode_url(locationIdList, 1);
		    			if(StringUtils.isNotEmpty(qrcodeImg1)){
		    				weixinBizwifi.setQrcodeImg1(qrcodeImg1);
		    			}
		    			
		    			WeixinLocationEntity weixinLocationEntity = systemService.findUniqueByProperty(WeixinLocationEntity.class, "poiId", request.getParameter("locationIdList"));
		    			weixinBizwifi.setWeixinLocationEntity(weixinLocationEntity);
		    			
		    			weixinBizwifiService.save(weixinBizwifi);
		    			message = "微信WIFI设备添加成功";
		    			j.setMsg(message);
		    			return j;
					}
				}
	        }
	        
	        message = "微信WIFI设备添加失败";
		}catch(Exception e){
			e.printStackTrace();
			message = "微信WIFI设备添加失败";
			throw new BusinessException(e.getMessage());
		}
		
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 获取物料二维码
	 * @param shopId
	 * @param type
	 * @return
	 */
	public String getQrcode_url(int shopId, int type){
		
		try{
			
			//获取二维码
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("shop_id", shopId);
			jsonobj.put("img_id", type);
			
			String accessTocken = weixinAccountService.getAccessToken();
	        if(StringUtil.isNotEmpty(accessTocken)){
	        	
	        	String url = WeixinUtil.get_qrcode_url.replace("ACCESS_TOKEN",accessTocken);
				JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonobj.toString());
				if(jsonObject!=null){
		    		if (("0").equals(jsonObject.get("errcode").toString())) {
						
		    			JSONObject jdata = (JSONObject) jsonObject.get("data");
		    			String qrcode_url = jdata.getString("qrcode_url");
		    			return qrcode_url;
					}
				}
	        }
		}catch(Exception e){
			return null;
		}
        return null;
	}
	
	/**
	 * 更新微信WIFI
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinBizwifiEntity weixinBizwifi, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "微信WIFI更新成功";
		WeixinBizwifiEntity t = weixinBizwifiService.get(WeixinBizwifiEntity.class, weixinBizwifi.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinBizwifi, t);
			weixinBizwifiService.saveOrUpdate(t);
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "微信WIFI更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 微信WIFI新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinBizwifiEntity weixinBizwifi, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinBizwifi.getId())) {
			weixinBizwifi = weixinBizwifiService.getEntity(WeixinBizwifiEntity.class, weixinBizwifi.getId());
			req.setAttribute("weixinBizwifiPage", weixinBizwifi);
		}
		return new ModelAndView("weixin/business/weixinBizwifi-add");
	}
	/**
	 * 微信WIFI编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinBizwifiEntity weixinBizwifi, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinBizwifi.getId())) {
			weixinBizwifi = weixinBizwifiService.getEntity(WeixinBizwifiEntity.class, weixinBizwifi.getId());
			req.setAttribute("weixinBizwifiPage", weixinBizwifi);
		}
		return new ModelAndView("weixin/business/weixinBizwifi-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/business/weixinBizwifiUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinBizwifiEntity weixinBizwifi,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "微信WIFI";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinBizwifiEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinBizwifi, request.getParameterMap());
			
			List<WeixinBizwifiEntity> weixinBizwifis = this.weixinBizwifiService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信WIFI列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinBizwifiEntity.class, weixinBizwifis);
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
	public void exportXlsByT(WeixinBizwifiEntity weixinBizwifi,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "微信WIFI";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信WIFI列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinBizwifiEntity.class, null);
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
				List<WeixinBizwifiEntity> listWeixinBizwifiEntitys = 
					(List<WeixinBizwifiEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinBizwifiEntity.class,params);
				for (WeixinBizwifiEntity weixinBizwifi : listWeixinBizwifiEntitys) {
					weixinBizwifiService.save(weixinBizwifi);
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
