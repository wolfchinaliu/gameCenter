package weixin.member.controller;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.entity.NewsItem;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.service.NewsItemServiceI;
import weixin.guanjia.message.service.NewsTemplateServiceI;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.entity.WeixinMemberCardEntity;
import weixin.member.service.WeixinGroupServiceI;
import weixin.member.service.WeixinMemberCardServiceI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
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
 * @Description: 会员卡
 * @author onlineGenerator
 * @date 2015-06-30 10:50:44
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinMemberCardController")
public class WeixinMemberCardController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(WeixinMemberCardController.class);

	@Autowired
	private WeixinMemberCardServiceI weixinMemberCardService;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	@Autowired
	private NewsTemplateServiceI newsTemplateService;
	
	@Autowired
	private NewsItemServiceI newsItemService;
	
	@Autowired
	WeixinGroupServiceI weixinGroupService;
	
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
	 * 会员卡列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinMemberCard")
	public ModelAndView weixinMemberCard(HttpServletRequest request) {
		return new ModelAndView("weixin/member/weixinMemberCardList");
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
	public void datagrid(WeixinMemberCardEntity weixinMemberCard,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinMemberCardEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMemberCard, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinMemberCardService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除会员卡
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WeixinMemberCardEntity weixinMemberCard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinMemberCard = systemService.getEntity(WeixinMemberCardEntity.class, weixinMemberCard.getId());
		message = "会员卡删除成功";
		try{
			weixinMemberCardService.delete(weixinMemberCard);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "会员卡删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除会员卡
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "会员卡删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinMemberCardEntity weixinMemberCard = systemService.getEntity(WeixinMemberCardEntity.class, 
				id
				);
				weixinMemberCardService.delete(weixinMemberCard);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "会员卡删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	 /**
	  * 上传会员卡信息至微信
	  * @param weixinMemberCard
	  * @param logoUrl
	  * @return
	  * @throws ParseException
	  */
	 public String createCard(WeixinMemberCardEntity weixinMemberCard, String logoUrl) throws ParseException{
		
		//base_info字段
		JSONObject date_infoObj = new JSONObject();
		date_infoObj.put("type", "DATE_TYPE_FIX_TIME_RANGE");
		date_infoObj.put("begin_timestamp", this.getLongTime(weixinMemberCard.getBeginTimestamp()));
		date_infoObj.put("end_timestamp", this.getLongTime(weixinMemberCard.getEndTimestamp()));
		
		JSONObject skuObj = new JSONObject();
		skuObj.put("quantity", weixinMemberCard.getQuantity());
		
		JSONArray jsonArray = new JSONArray();
		String[] location_ids = weixinMemberCard.getLocationIdList().split(",");
		for(int i=0;i<location_ids.length;i++){
			
			jsonArray.add(new Long(location_ids[i]));
		}
		
		JSONObject cardObj = new JSONObject();
		
		cardObj.put("logo_url", logoUrl);
		cardObj.put("brand_name", weixinMemberCard.getBrandName());
		cardObj.put("code_type", weixinMemberCard.getCodeType());
		cardObj.put("title", weixinMemberCard.getTitle());
		//cardObj.put("sub_title", weixinMemberCard.getSubTitle());
		cardObj.put("color", weixinMemberCard.getColor());
		cardObj.put("notice", weixinMemberCard.getNotice());
		cardObj.put("service_phone", weixinMemberCard.getServicePhone());
		cardObj.put("description", weixinMemberCard.getDescription());
		cardObj.put("date_info", date_infoObj);
		cardObj.put("sku", skuObj);
		cardObj.put("get_limit", weixinMemberCard.getGetLimit());
		cardObj.put("use_custom_code", false);
		if("true".equals(weixinMemberCard.getCanGiveFriend())){
			cardObj.put("can_give_friend", true);
		}else{
			cardObj.put("can_give_friend", false);
		}
		cardObj.put("location_id_list", jsonArray);
		//base_info字段
		
		//member_card 字段
		JSONObject grouponObj = new JSONObject();
		grouponObj.put("base_info", cardObj);
		grouponObj.put("prerogative", weixinMemberCard.getPrerogative());
		if("true".equals(weixinMemberCard.getSupplyBonus())){
			grouponObj.put("supply_bonus", true);
		}else{
			grouponObj.put("supply_bonus", false);
		}
		
		if("true".equals(weixinMemberCard.getSupplyBalance())){
			grouponObj.put("supply_balance", true);
		}else{
			grouponObj.put("supply_balance", false);
		}
		//member_card 字段

		JSONObject card_infoObj = new JSONObject();
		card_infoObj.put("card_type", weixinMemberCard.getCardType());
		card_infoObj.put("member_card", grouponObj);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("card", card_infoObj);
		
		String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
        if(StringUtil.isNotEmpty(accessTocken)){
        	
        	String url = WeixinUtil.create_card_url.replace("ACCESS_TOKEN",accessTocken);
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonobj.toString());
			if(jsonObject!=null){
	    		if (("0").equals(jsonObject.get("errcode").toString())) {
					
	    			return jsonObject.getString("card_id");//卡券ID
				}
			}
        }
		return null;
	 }
	 
	/**
	 * 创建会员卡
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinMemberCardEntity weixinMemberCard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		try{
			
//			String accessToken = weixinAccountService.getAccessToken();
//			
//			//上传门店LOGO
//			String localhosturl = ResourceUtil.getDomain()+"/";
//			String logoUrl = WeixinUtil.uploadLocationLogo(accessToken, WeixinUtil.upload_card_logo_url, localhosturl + weixinMemberCard.getLogoUrl());
			
			
			//上传图片
			String accessToken = weixinAccountService.getAccessToken();			
			String realPath = request.getSession().getServletContext().getRealPath("/") ;
			String fileImageUrl=realPath+ weixinMemberCard.getLogoUrl();
			String logoUrl=	WeixinUtil.uploadLocationLogoByLocal(accessToken,WeixinUtil.upload_card_logo_url, fileImageUrl);
			
			
			String cardId = createCard(weixinMemberCard, logoUrl);
			if(StringUtils.isNotEmpty(cardId)){
				
				weixinMemberCard.setCardId(cardId);
				weixinMemberCard.setCreateDate(new Date());
				weixinMemberCard.setStatus("0");
				
				weixinMemberCardService.save(weixinMemberCard);
				message = "会员卡添加成功";
			}else{
				message = "会员卡添加失败";
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "会员卡添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新会员卡
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinMemberCardEntity weixinMemberCard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "会员卡更新成功";
		WeixinMemberCardEntity t = weixinMemberCardService.get(WeixinMemberCardEntity.class, weixinMemberCard.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinMemberCard, t);
			weixinMemberCardService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "会员卡更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 会员卡新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinMemberCardEntity weixinMemberCard, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinMemberCard.getId())) {
			weixinMemberCard = weixinMemberCardService.getEntity(WeixinMemberCardEntity.class, weixinMemberCard.getId());
			req.setAttribute("weixinMemberCardPage", weixinMemberCard);
		}
		return new ModelAndView("weixin/member/weixinMemberCard-add");
	}
	/**
	 * 会员卡编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinMemberCardEntity weixinMemberCard, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinMemberCard.getId())) {
			weixinMemberCard = weixinMemberCardService.getEntity(WeixinMemberCardEntity.class, weixinMemberCard.getId());
			req.setAttribute("weixinMemberCardPage", weixinMemberCard);
		}
		return new ModelAndView("weixin/member/weixinMemberCard-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("weixin/member/weixinMemberCardUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(WeixinMemberCardEntity weixinMemberCard,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "会员卡";
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
			CriteriaQuery cq = new CriteriaQuery(WeixinMemberCardEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMemberCard, request.getParameterMap());
			
			List<WeixinMemberCardEntity> weixinMemberCards = this.weixinMemberCardService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("会员卡列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinMemberCardEntity.class, weixinMemberCards);
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
	public void exportXlsByT(WeixinMemberCardEntity weixinMemberCard,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "会员卡";
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
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("会员卡列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), WeixinMemberCardEntity.class, null);
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
				List<WeixinMemberCardEntity> listWeixinMemberCardEntitys = 
					(List<WeixinMemberCardEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),WeixinMemberCardEntity.class,params);
				for (WeixinMemberCardEntity weixinMemberCard : listWeixinMemberCardEntitys) {
					weixinMemberCardService.save(weixinMemberCard);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				LOGGER.error(ExceptionUtil.getExceptionMessage(e));
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
	  * Date转为为时间戳
	  * @param time
	  * @return
	  * @throws ParseException
	  */
	 public long getLongTime(Date time) throws ParseException{
		 
		 LOGGER.info(time.getTime());
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String str_date = format.format(time);
		 
		 Date date = format.parse(str_date);
		 
		 LOGGER.info(str_date+"/"+date.getTime());

		 
		 return date.getTime()/1000;
	 }
	 
	 /**
		 * 跳转到优惠券发放页面
		 * @param id
		 * @param req
		 * @return
		 */
		@RequestMapping(params = "toSendCard")
		public ModelAndView toSendCard(String id, HttpServletRequest req) {
			
			WeixinMemberCardEntity weixinCard = weixinMemberCardService.getEntity(WeixinMemberCardEntity.class, id);//当前卡券
			
			String sendType = req.getParameter("sendType");//发送类型：1，群发；2，嵌入图文；3，下载二维码
			
			//直接群发卡券
			if("1".equals(sendType)){
				
				WeixinGroupEntity weixinGroupEntity = new WeixinGroupEntity();
				weixinGroupEntity.setAccountid(ResourceUtil.getWeiXinAccountId());
				//分组列表
				CriteriaQuery cq = new CriteriaQuery(WeixinGroupEntity.class);
				//查询条件组装器
				org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinGroupEntity, req.getParameterMap());
				cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
				List<WeixinGroupEntity> weixinGroupList = weixinGroupService.getListByCriteriaQuery(cq, false);
				req.setAttribute("weixinGroupList", weixinGroupList);
				
				//当前卡券
				req.setAttribute("weixinCardPage", weixinCard);
				
				return new ModelAndView("weixin/business/weixinCard-send-message");
			}
			
			//嵌入图文消息
			if("2".equals(sendType)){
				
				JSONObject cardobj = new JSONObject();
				cardobj.put("card_id", weixinCard.getCardId());
				
				String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
				if(StringUtil.isNotEmpty(accessTocken)){
		        	
		        	String url = WeixinUtil.create_cardarticle_url.replace("ACCESS_TOKEN",accessTocken);
					JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", cardobj.toString());
					if(jsonObject!=null){
			    		if (("0").equals(jsonObject.get("errcode").toString())) {
							
			    			//返回一段html代码，可以直接嵌入到图文消息的正文里。即可以把这段代码嵌入到上传图文消息素材接口中的content字段里
			    			String content = jsonObject.getString("content");
			    			
			    			//创建图文
			    			NewsItem newsItem = new NewsItem();
			    			newsItem.setTitle(weixinCard.getTitle());
			    			newsItem.setContent(content);
			    			newsItem.setCreateDate(new Date());
			    			
			    			List<NewsItem> newsItemList = new ArrayList<NewsItem>();
			    			newsItemList.add(newsItem);
			    			 
			    			NewsTemplate newsTemplate = new NewsTemplate();
			    			newsTemplate.setTemplateName(weixinCard.getTitle());
			    			newsTemplate.setType("common");
			    			newsTemplate.setStatus("0");
			    			newsTemplate.setNewsItemList(newsItemList);

			    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    			newsTemplate.setAddTime(sdf.format(new Date()));
			    			
			    			newsTemplateService.save(newsTemplate);
			    			
			    			return new ModelAndView("weixinArticleController.do?goAdd&templateId="+newsTemplate.getId());
						}
					}
		        }
			}

			//下载二维码
			if("3".equals(sendType)){
				
				JSONObject cardobj = new JSONObject();
				cardobj.put("card_id", weixinCard.getCardId());			
				
				JSONObject action_infoObj = new JSONObject();
				action_infoObj.put("card", cardobj);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("action_name", "QR_CARD");
				jsonobj.put("action_info", action_infoObj);
							
				String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
		        if(StringUtil.isNotEmpty(accessTocken)){
		        	
		        	String url = WeixinUtil.create_cardqrcode_url.replace("ACCESS_TOKEN",accessTocken);
					JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonobj.toString());
					if(jsonObject!=null){
			    		if (("0").equals(jsonObject.get("errcode").toString())) {
							
			    			String ticket = jsonObject.getString("ticket");//获取的二维码ticket
			    			req.setAttribute("TICKET", ticket);
			    			return new ModelAndView("weixin/business/weixinCard-send-qrcode");
						}
					}
		        }
			}
				
			return new ModelAndView("weixin/business/weixinCard-toSend");
		}
}
