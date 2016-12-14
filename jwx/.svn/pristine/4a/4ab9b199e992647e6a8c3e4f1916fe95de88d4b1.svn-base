package weixin.business.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
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
import org.jeewx.api.coupon.location.JwLocationAPI;
import org.jeewx.api.coupon.location.model.Batchget;
import org.jeewx.api.coupon.location.model.LocationList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weixin.business.entity.WeixinLocationEntity;
import weixin.business.service.WeixinLocationServiceI;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;

/**
 * @Title: Controller
 * @Description: 门店信息
 * @author onlineGenerator
 * @date 2015-04-30 16:58:59
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinLocationController")
public class WeixinLocationController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinLocationController.class);

	@Autowired
	private WeixinLocationServiceI weixinLocationService;

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
	 * 门店信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinLocation")
	public ModelAndView weixinLocation(HttpServletRequest request) {
		return new ModelAndView("weixin/business/weixinLocationList");
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
	public void datagrid(WeixinLocationEntity weixinLocation, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinLocationEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLocation,
				request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());// 根据公众ID进行数据隔
		cq.notEq("status", "4");
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinLocationService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除门店信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinLocationEntity weixinLocation, HttpServletRequest request) {

		AjaxJson j = new AjaxJson();
		weixinLocation = systemService.getEntity(WeixinLocationEntity.class, weixinLocation.getId());

		try {

			if (!"0".equals(weixinLocation.getStatus())) {

				// 删除服务器上的门店
				JSONObject locationObj = new JSONObject();
				locationObj.put("poi_id", weixinLocation.getPoiId());

				String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
				if (StringUtil.isNotEmpty(accessTocken)) {

					String url = WeixinUtil.del_location_url.replace("ACCESS_TOKEN", accessTocken);
					JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", locationObj.toString());
					if (jsonObject != null) {
						if (("0").equals(jsonObject.get("errcode").toString())) {

							weixinLocation.setStatus("4");//删除标识
							weixinLocationService.delete(weixinLocation);
							message = "门店信息删除成功";
						}
					}
				}
			} else {

				weixinLocationService.delete(weixinLocation);
				message = "门店信息删除成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "门店信息删除失败";
			throw new BusinessException(e.getMessage());
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除门店信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "门店信息删除成功";
		try {
			for (String id : ids.split(",")) {
				WeixinLocationEntity weixinLocation = systemService.getEntity(WeixinLocationEntity.class, id);
				weixinLocationService.delete(weixinLocation);
				// systemService.addLog(message, Globals.Log_Type_DEL,
				// Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "门店信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 门店上传
	 * 
	 * @param weixinLocation
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doUpload")
	@ResponseBody
	public AjaxJson doUpload(String id, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		try {

			WeixinLocationEntity weixinLocation = weixinLocationService.get(WeixinLocationEntity.class, id);

			String accessToken = weixinAccountService.getAccessToken();

			String localhosturl = ResourceUtil.getDomain()+"/";

			// String basePath = request.getScheme() + "://" +
			// request.getServerName() + ":" + request.getServerPort() +
			// request.getContextPath();

			// String localhosturl = "http://112.74.94.108:8080/wx/";
			// 上传门店LOGO
			String logoUrl = WeixinUtil.uploadLocationLogo(accessToken, WeixinUtil.upload_card_logo_url,
					localhosturl + weixinLocation.getTitleLogo());

			if (StringUtils.isEmpty(logoUrl)) {

				message = "门店信息添加失败，原因：门店图片上传失败,图片路径：" + localhosturl + weixinLocation.getTitleLogo();
				j.setMsg(message);
				return j;
			}

			JSONArray cObj = new JSONArray();
			cObj.add(weixinLocation.getCategory());

			JSONObject photoObj = new JSONObject();
			photoObj.put("photo_url", logoUrl);

			JSONArray photo_listObj = new JSONArray();
			photo_listObj.add(photoObj);

			JSONObject locationObj = new JSONObject();
			locationObj.put("sid", weixinLocation.getId());
			locationObj.put("business_name", weixinLocation.getBusinessName());
			locationObj.put("branch_name", weixinLocation.getBranchName());
			locationObj.put("province", weixinLocation.getProvince());
			locationObj.put("city", weixinLocation.getCity());
			locationObj.put("district", weixinLocation.getDistrict());
			locationObj.put("address", weixinLocation.getAddress());
			locationObj.put("telephone", weixinLocation.getTelephone());
			locationObj.put("categories", cObj);
			locationObj.put("offset_type", 1);
			locationObj.put("longitude", weixinLocation.getLongitude());
			locationObj.put("latitude", weixinLocation.getLatitude());
			locationObj.put("photo_list", photo_listObj);
			locationObj.put("recommend", weixinLocation.getRecommend());
			locationObj.put("special", weixinLocation.getSpecial());
			locationObj.put("introduction", weixinLocation.getIntroduction());
			locationObj.put("open_time", weixinLocation.getOpenTime());
			locationObj.put("avg_price", weixinLocation.getAvgPrice());

			JSONObject base_infoObj = new JSONObject();
			base_infoObj.put("base_info", locationObj);

			JSONObject businessObj = new JSONObject();
			businessObj.put("business", base_infoObj);

			String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
			if (StringUtil.isNotEmpty(accessTocken)) {

				String url = WeixinUtil.create_location_url.replace("ACCESS_TOKEN", accessTocken);

				JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", businessObj.toString());

				if (jsonObject != null) {
					if (("0").equals(jsonObject.get("errcode").toString())) {

						message = "门店上传成功，待腾讯进行审核才能正式生效";
					} else {
						message = "门店上传失败";
					}
				}
			}
		} catch (Exception e) {
			message = "门店上传失败";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 上传门店信息具体实现方法
	 * 
	 * @param weixinLocation
	 * @param logoUrl
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public boolean doUpLoadLocation(WeixinLocationEntity weixinLocation, String logoUrl)
			throws UnsupportedEncodingException {

		JSONArray cObj = new JSONArray();
		cObj.add(weixinLocation.getCategory());

		JSONObject photoObj = new JSONObject();
		photoObj.put("photo_url", logoUrl);

		JSONArray photo_listObj = new JSONArray();
		photo_listObj.add(photoObj);

		JSONObject locationObj = new JSONObject();
		locationObj.put("sid", weixinLocation.getId());
		locationObj.put("business_name", weixinLocation.getBusinessName());
		locationObj.put("branch_name", weixinLocation.getBranchName());
		locationObj.put("province", weixinLocation.getProvince());
		locationObj.put("city", weixinLocation.getCity());
		locationObj.put("district", weixinLocation.getDistrict());
		locationObj.put("address", weixinLocation.getAddress());
		locationObj.put("telephone", weixinLocation.getTelephone());
		locationObj.put("categories", cObj);
		locationObj.put("offset_type", 1);
		locationObj.put("longitude", Double.parseDouble(weixinLocation.getLongitude()));
		locationObj.put("latitude", Double.parseDouble(weixinLocation.getLatitude()));
		locationObj.put("photo_list", photo_listObj);
		locationObj.put("recommend", weixinLocation.getRecommend());
		locationObj.put("special", weixinLocation.getSpecial());
		locationObj.put("introduction", weixinLocation.getIntroduction());
		locationObj.put("open_time", weixinLocation.getOpenTime());
		locationObj.put("avg_price", weixinLocation.getAvgPrice());

		JSONObject base_infoObj = new JSONObject();
		base_infoObj.put("base_info", locationObj);

		JSONObject businessObj = new JSONObject();
		businessObj.put("business", base_infoObj);

		String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
		if (StringUtil.isNotEmpty(accessTocken)) {

			String url = WeixinUtil.create_location_url.replace("ACCESS_TOKEN", accessTocken);
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", businessObj.toString());
			if (jsonObject != null) {
				if (("0").equals(jsonObject.get("errcode").toString())) {

					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 添加门店信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinLocationEntity weixinLocation, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		try {

			String accessToken = weixinAccountService.getAccessToken();

			String realPath = request.getSession().getServletContext().getRealPath("/");
			String fileImageUrl = realPath + weixinLocation.getTitleLogo();
			String logoUrl = WeixinUtil.uploadLocationLogoByLocal(accessToken, WeixinUtil.upload_card_logo_url,
					fileImageUrl);
			if (StringUtils.isEmpty(logoUrl)) {

				message = "门店图片上传失败";
				j.setMsg(message);
				return j;
			}

			// 上传门店信息
			if (doUpLoadLocation(weixinLocation, logoUrl)) {

				// 本地保存
				weixinLocation.setStatus("0");// 审核中
				weixinLocation.setCreateDate(new Date());
				weixinLocationService.save(weixinLocation);
				message = "门店信息添加成功，待腾讯进行审核才能正式生效";
			} else {

				message = "门店信息添加失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "门店信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新门店信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinLocationEntity weixinLocation, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		WeixinLocationEntity t = weixinLocationService.get(WeixinLocationEntity.class, weixinLocation.getId());
		try {


				String accessToken = weixinAccountService.getAccessToken();

				String realPath = request.getSession().getServletContext().getRealPath("/");
				String fileImageUrl = realPath + weixinLocation.getTitleLogo();
				String logoUrl = WeixinUtil.uploadLocationLogoByLocal(accessToken, WeixinUtil.upload_card_logo_url,
						fileImageUrl);
				if (StringUtils.isEmpty(logoUrl)) {

					message = "门店图片上传失败";
					j.setMsg(message);
					return j;
				}

				JSONObject photoObj = new JSONObject();
				photoObj.put("photo_url", logoUrl);
				JSONArray photo_listObj = new JSONArray();
				photo_listObj.add(photoObj);
				
				

				JSONObject locationObj = new JSONObject();
				locationObj.put("poi_id", weixinLocation.getPoiId());
				locationObj.put("telephone", weixinLocation.getTelephone());
				locationObj.put("photo_list", photo_listObj);
				locationObj.put("recommend", weixinLocation.getRecommend());
				locationObj.put("special", weixinLocation.getSpecial());
				locationObj.put("introduction", weixinLocation.getIntroduction());
				locationObj.put("open_time", weixinLocation.getOpenTime());
				locationObj.put("avg_price", weixinLocation.getAvgPrice());

				JSONObject baseinfoObj = new JSONObject();
				baseinfoObj.put("base_info", locationObj);

				JSONObject businessObj = new JSONObject();
				businessObj.put("business", baseinfoObj);

				if (StringUtil.isNotEmpty(accessToken)) {

					String url = WeixinUtil.update_location_url.replace("ACCESS_TOKEN", accessToken);
					JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", businessObj.toString());
					if (jsonObject != null) {
						if (("0").equals(jsonObject.get("errcode").toString())) {

							t.setTelephone(weixinLocation.getTelephone());
							t.setRecommend(weixinLocation.getRecommend());
							t.setSpecial(weixinLocation.getSpecial());
							t.setOpenTime(weixinLocation.getOpenTime());
							t.setAvgPrice(weixinLocation.getAvgPrice());
							t.setIntroduction(weixinLocation.getIntroduction());
							t.setTitleLogo(logoUrl);
							weixinLocationService.saveOrUpdate(t);
							message = "门店信息更新成功";
							
							j.setMsg(message);
							return j;
						}
					}
				}
			
			message = "微信公众平台不允许修改该门店";
		} catch (Exception e) {
			e.printStackTrace();
			message = "门店信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 门店信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinLocationEntity weixinLocation, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinLocation.getId())) {
			weixinLocation = weixinLocationService.getEntity(WeixinLocationEntity.class, weixinLocation.getId());
			req.setAttribute("weixinLocationPage", weixinLocation);
		}
		return new ModelAndView("weixin/business/weixinLocation-add");
	}

	/**
	 * 门店信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinLocationEntity weixinLocation, HttpServletRequest req) {

		if (StringUtil.isNotEmpty(weixinLocation.getId())) {
			weixinLocation = weixinLocationService.getEntity(WeixinLocationEntity.class, weixinLocation.getId());
			req.setAttribute("weixinLocationPage", weixinLocation);

			if ("1".equals(weixinLocation.getStatus())) {

				return new ModelAndView("weixin/business/weixinLocation-view");
			}

		}
		return new ModelAndView("weixin/business/weixinLocation-update");
	}

	@RequestMapping(params = "toUpdate")
	public ModelAndView toUpdate(String id, HttpServletRequest req) {

		WeixinLocationEntity weixinLocation = weixinLocationService.getEntity(WeixinLocationEntity.class, id);
		req.setAttribute("weixinLocationPage", weixinLocation);

		return new ModelAndView("weixin/business/weixinLocation-update");
	}

	@RequestMapping(params = "goView")
	public ModelAndView goView(String id, HttpServletRequest req) {
		WeixinLocationEntity weixinLocation = weixinLocationService.getEntity(WeixinLocationEntity.class, id);
		req.setAttribute("weixinLocationPage", weixinLocation);

		return new ModelAndView("weixin/business/weixinLocation-view");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/business/weixinLocationUpload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinLocationEntity weixinLocation, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "门店信息";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinLocationEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLocation,
					request.getParameterMap());

			List<WeixinLocationEntity> weixinLocations = this.weixinLocationService.getListByCriteriaQuery(cq, false);
			workbook = ExcelExportUtil.exportExcel(
					new ExcelTitle("门店信息列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
					WeixinLocationEntity.class, weixinLocations);
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
	public void exportXlsByT(WeixinLocationEntity weixinLocation, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "门店信息";
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
					new ExcelTitle("门店信息列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
					WeixinLocationEntity.class, null);
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
				List<WeixinLocationEntity> listWeixinLocationEntitys = (List<WeixinLocationEntity>) ExcelImportUtil
						.importExcelByIs(file.getInputStream(), WeixinLocationEntity.class, params);
				for (WeixinLocationEntity weixinLocation : listWeixinLocationEntitys) {
					weixinLocationService.save(weixinLocation);
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

	@RequestMapping(params = "showCateUrl")
	public ModelAndView showCateUrl(HttpServletRequest request) {

		request.setAttribute("accountid", ResourceUtil.getWeiXinAccountId());
		return new ModelAndView("weixin/business/showcateurl");
	}

	/**
	 * 跳转到门店多选页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "locations")
	public String locations() {
		return "weixin/business/locations";
	}

	/**
	 * 获取已审核门店
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridLocation")
	public void datagridLocation(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

		CriteriaQuery cq = new CriteriaQuery(WeixinLocationEntity.class, dataGrid);
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());// 根据公众ID进行数据隔离
		cq.eq("status", "1");
		cq.isNotNull("poiId");
		cq.add();

		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 拉取微信服务器上面的门店列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "loadWeixinLocation")
	@ResponseBody
	public AjaxJson loadWeixinLocation(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		try {

			JSONObject postObj = new JSONObject();
			postObj.put("begin", 0);
			postObj.put("limit", 50);

			String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
			if (StringUtil.isNotEmpty(accessTocken)) {

				String url = WeixinUtil.load_location_url.replace("ACCESS_TOKEN", accessTocken);
				JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", postObj.toString());
				if (jsonObject != null) {
					if (("0").equals(jsonObject.get("errcode").toString())) {

						JSONArray jsonArray = jsonObject.getJSONArray("business_list");
						// JSONArray jsonArray =
						// businessObj.getJSONArray(0);//("base_info");

						for (int i = 0; i < jsonArray.size(); i++) {

							JSONObject jSONObject = (JSONObject) jsonArray.get(i);

							JSONObject obj = jSONObject.getJSONObject("base_info");

							String location_id = obj.getString("sid");
							String available_state = obj.getString("available_state");// 3:审核通过，4：审核不通过
							// 1 表示系统错误、2 表示审核中、3 审核通过、4 审核驳回
							if ("3".equals(available_state)) {

								String poi_id = obj.getString("poi_id");
								String business_name = obj.getString("business_name");
								String branch_name = obj.getString("branch_name");
								String address = obj.getString("address");
								String province = obj.getString("province");
								String city = obj.getString("city");
								String district = obj.getString("district");
								String telephone = obj.getString("telephone");
								String categories = obj.getString("categories");
								String longitude = obj.getString("longitude");
								String latitude = obj.getString("latitude");
								String recommend = obj.getString("recommend");
								String special = obj.getString("special");
								String introduction = obj.getString("introduction");
								String open_time = obj.getString("open_time");
								String avg_price = obj.getString("avg_price");

								String title_logo = "";
								JSONArray photo_list = obj.getJSONArray("photo_list");
								if (photo_list != null && photo_list.size() > 0) {

									for (int z = 0; z < photo_list.size(); z++) {

										JSONObject photoObj = (JSONObject) photo_list.get(z);

										if (StringUtils.isNotEmpty(photoObj.getString("photo_url")))
											title_logo = photoObj.getString("photo_url");
									}

								}

								WeixinLocationEntity weixinLocation = weixinLocationService
										.get(WeixinLocationEntity.class, location_id);
								if (null != weixinLocation) {

									weixinLocation.setPoiId(poi_id);
									weixinLocation.setBusinessName(business_name);
									weixinLocation.setBranchName(branch_name);
									weixinLocation.setAddress(address);
									weixinLocation.setAvgPrice(Integer.parseInt(avg_price));
									weixinLocation.setCategory(categories);
									weixinLocation.setCity(city);
									weixinLocation.setCreateDate(new Date());
									weixinLocation.setDistrict(district);
									weixinLocation.setIntroduction(introduction);
									weixinLocation.setLatitude(latitude);
									weixinLocation.setLongitude(longitude);
									weixinLocation.setOpenTime(open_time);
									weixinLocation.setProvince(province);
									// weixinLocation.setQrcodeLogo(qrcodeLogo);
									weixinLocation.setRecommend(recommend);
									weixinLocation.setSpecial(special);
									weixinLocation.setTelephone(telephone);
									if (StringUtils.isEmpty(weixinLocation.getTitleLogo()))
										weixinLocation.setTitleLogo(title_logo);
									weixinLocation.setStatus("1");

									weixinLocationService.saveOrUpdate(weixinLocation);
								} else {

									weixinLocation = weixinLocationService
											.findUniqueByProperty(WeixinLocationEntity.class, "poiId", poi_id);
									if (null != weixinLocation) {

										weixinLocation.setStatus("1");
										weixinLocation.setBusinessName(business_name);
										weixinLocation.setBranchName(branch_name);
										weixinLocation.setAddress(address);
										weixinLocation.setAvgPrice(Integer.parseInt(avg_price));
										weixinLocation.setCategory(categories);
										weixinLocation.setCity(city);
										weixinLocation.setCreateDate(new Date());
										weixinLocation.setDistrict(district);
										weixinLocation.setIntroduction(introduction);
										weixinLocation.setLatitude(latitude);
										weixinLocation.setLongitude(longitude);
										weixinLocation.setOpenTime(open_time);
										weixinLocation.setProvince(province);
										// weixinLocation.setQrcodeLogo(qrcodeLogo);
										weixinLocation.setRecommend(recommend);
										weixinLocation.setSpecial(special);
										weixinLocation.setTelephone(telephone);
										weixinLocation.setTitleLogo(title_logo);

										weixinLocationService.saveOrUpdate(weixinLocation);
									} else {

										weixinLocation = new WeixinLocationEntity();

										weixinLocation.setId(location_id);
										weixinLocation.setPoiId(poi_id);
										weixinLocation.setBusinessName(business_name);
										weixinLocation.setBranchName(branch_name);
										weixinLocation.setAddress(address);
										weixinLocation.setAvgPrice(Integer.parseInt(avg_price));
										weixinLocation.setCategory(categories);
										weixinLocation.setCity(city);
										weixinLocation.setCreateDate(new Date());
										weixinLocation.setDistrict(district);
										weixinLocation.setIntroduction(introduction);
										weixinLocation.setLatitude(latitude);
										weixinLocation.setLongitude(longitude);
										weixinLocation.setOpenTime(open_time);
										weixinLocation.setProvince(province);
										// weixinLocation.setQrcodeLogo(qrcodeLogo);
										weixinLocation.setRecommend(recommend);
										weixinLocation.setSpecial(special);
										weixinLocation.setTelephone(telephone);
										weixinLocation.setTitleLogo(title_logo);

										weixinLocation.setStatus("1");
										weixinLocationService.save(weixinLocation);
									}
								}
							}
						}
						message = "门店信息获取成功";
					} else {

						message = "未获取到任何门店信息";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "门店信息获取失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 拉取微信服务器上面的门店列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "loadWeixinLocationBatch")
	@ResponseBody
	public AjaxJson loadWeixinLocationBatch(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		try {

			JSONObject postObj = new JSONObject();
			postObj.put("begin", 0);
			postObj.put("limit", 50);

			String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
			if (StringUtil.isNotEmpty(accessTocken)) {

				String url = WeixinUtil.load_location_url.replace("ACCESS_TOKEN", accessTocken);
				JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", postObj.toString());
				if (jsonObject != null) {
					if (("0").equals(jsonObject.get("errcode").toString())) {

						JSONArray jsonArray = jsonObject.getJSONArray("business_list");
						// JSONArray jsonArray =
						// businessObj.getJSONArray(0);//("base_info");

						for (int i = 0; i < jsonArray.size(); i++) {

							JSONObject jSONObject = (JSONObject) jsonArray.get(i);

							JSONObject obj = jSONObject.getJSONObject("base_info");

							String available_state = obj.getString("available_state");// 3:审核通过，4：审核不通过
							// 1 表示系统错误、2 表示审核中、3 审核通过、4 审核驳回
							if ("1".equals(available_state)) {
								continue;
							}
							
							
							String poi_id = obj.getString("poi_id");
							
							JSONObject videoObj = new JSONObject();
							videoObj.put("poi_id", poi_id);
							 url = "https://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=ACCESS_TOKEN";
							url = url.replace("ACCESS_TOKEN", accessTocken);
							JSONObject json = WeixinUtil.httpRequest(url, "POST", videoObj.toString());
							JSONObject jsonArraybusiness = json.getJSONObject("business");
							obj=jsonArraybusiness.getJSONObject("base_info");
						
							String business_name = obj.getString("business_name");
							String branch_name = obj.getString("branch_name");
							String address = obj.getString("address");
							String province = obj.getString("province");
							String city = obj.getString("city");
							String district = obj.getString("district");
							String telephone = obj.getString("telephone");
							String categories = obj.getString("categories");
							String longitude = obj.getString("longitude");
							String latitude = obj.getString("latitude");
							String recommend = obj.getString("recommend");
							String special = obj.getString("special");
							String introduction = obj.getString("introduction");
							String open_time = obj.getString("open_time");
							String avg_price = obj.getString("avg_price");

							StringBuffer title_logo =new StringBuffer();;
							JSONArray photo_list = obj.getJSONArray("photo_list");
							if (photo_list != null && photo_list.size() > 0) {

								for (int z = 0; z < photo_list.size(); z++) {

									JSONObject photoObj = (JSONObject) photo_list.get(z);

									if (StringUtils.isNotEmpty(photoObj.getString("photo_url"))){
										title_logo.append( photoObj.getString("photo_url"));
										title_logo.append("?wx_fmt=jpeg");
										break;
									}
								}

							}
							// 说明是我们系统新建的门店
							WeixinLocationEntity weixinLocation  = weixinLocationService.findUniqueByProperty(WeixinLocationEntity.class,
										"poiId", poi_id);
						
							if (null == weixinLocation) {
								weixinLocation = new WeixinLocationEntity();
							}
							weixinLocation.setPoiId(poi_id);
							if ("3".equals(available_state)) {
								weixinLocation.setStatus("1");
							} else {
								weixinLocation.setStatus("0");
							}
							weixinLocation.setBusinessName(business_name);
							weixinLocation.setBranchName(branch_name);
							weixinLocation.setAddress(address);
							weixinLocation.setAvgPrice(Integer.parseInt(avg_price));
							weixinLocation.setCategory(categories);
							weixinLocation.setCity(city);
							weixinLocation.setCreateDate(new Date());
							weixinLocation.setDistrict(district);
							weixinLocation.setIntroduction(introduction);
							weixinLocation.setLatitude(latitude);
							weixinLocation.setLongitude(longitude);
							weixinLocation.setOpenTime(open_time);
							weixinLocation.setProvince(province);
							// weixinLocation.setQrcodeLogo(qrcodeLogo);
							weixinLocation.setRecommend(recommend);
							weixinLocation.setSpecial(special);
							weixinLocation.setTelephone(telephone);
							weixinLocation.setTitleLogo(title_logo.toString());
							if ("3".equals(available_state)) {
								weixinLocation.setStatus("1");
								weixinLocation.setPoiId(poi_id);
							} else if("2".equals(available_state)){
								weixinLocation.setStatus("0");
							}else{
								weixinLocation.setStatus("3");
							}
							weixinLocationService.saveOrUpdate(weixinLocation);
						}
						message = "门店信息获取成功";
					} else {

						message = "未获取到任何门店信息";
					}
				}
			}
		} catch (

		Exception e)

		{
			e.printStackTrace();
			message = "门店信息获取失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;

	}

	/**
	 * 拉取门店列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doLoad")
	@ResponseBody
	public AjaxJson doLoad(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		try {

			String accessToken = weixinAccountService.getAccessToken();

			Batchget batchget = new Batchget();
			batchget.setOffset(0);
			batchget.setCount(100);

			List<LocationList> locationList = JwLocationAPI.doBatchget(accessToken, batchget).getLocation_list();

			if (locationList != null) {

				for (int i = 0; i < locationList.size(); i++) {

					LocationList locationTemp = locationList.get(i);

					WeixinLocationEntity t = weixinLocationService.get(WeixinLocationEntity.class,
							locationTemp.getSid());

					if (t != null) {
						t.setId(locationTemp.getSid());
					}

					t.setBusinessName(locationTemp.getBusiness_name());
					t.setBranchName(locationTemp.getBranch_name());

					weixinLocationService.saveOrUpdate(t);

				}

				message = "门店信息加载成功";
			}
			message = "未获取到门店信息";
		} catch (Exception e) {
			e.printStackTrace();
			message = "门店信息加载失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
}
