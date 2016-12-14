package weixin.business.controller;
import weixin.business.entity.WeixinCardEntity;
import weixin.business.entity.WeixinLocationEntity;
import weixin.business.service.WeixinCardServiceI;
import weixin.business.service.WeixinLocationServiceI;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.entity.NewsItem;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.service.NewsItemServiceI;
import weixin.guanjia.message.service.NewsTemplateServiceI;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinGroupServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Date;
import java.util.UUID;
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
import org.jeewx.api.coupon.location.JwLocationAPI;
import org.jeewx.api.coupon.location.model.BaseInfo;
import org.jeewx.api.coupon.location.model.CardInfo;
import org.jeewx.api.coupon.location.model.CardInfoRtn;
import org.jeewx.api.coupon.location.model.DataInfo;
import org.jeewx.api.coupon.location.model.GeneralCoupon;



/**   
 * @Title: Controller
 * @Description: 优惠券
 * @author onlineGenerator
 * @date 2015-06-02 16:29:43
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinCardController")
public class WeixinCardController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(WeixinCardController.class);

	@Autowired
	private WeixinCardServiceI weixinCardService;
	
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
	
	@Autowired
	private WeixinLocationServiceI weixinLocationService;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 优惠券列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinCard")
	public ModelAndView weixinCard(HttpServletRequest request) {
		return new ModelAndView("weixin/business/weixinCardList");
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
	public void datagrid(WeixinCardEntity weixinCard,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinCardEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinCard, request.getParameterMap());
		cq.eq("accountid", ResourceUtil.getWeiXinAccountId());//根据公众ID进行数据隔离
		cq.notEq("status", "3");//已经删除的不查询
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinCardService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除优惠券
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String id, HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		WeixinCardEntity weixinCard = systemService.getEntity(WeixinCardEntity.class, id);
		
		try{
			
			//下线
			JSONObject cardObj = new JSONObject();
			cardObj.put("card_id", weixinCard.getCardId());
			
			String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
			String card_url = WeixinUtil.del_card_url.replace("ACCESS_TOKEN", accessTocken);
			JSONObject jsonObject = WeixinUtil.httpRequest(card_url, "POST", cardObj.toString());
			if (jsonObject != null) {
				if (("0").equals(jsonObject.get("errcode").toString())) {
					
					
					weixinCard.setStatus("3");
					weixinCardService.saveOrUpdate(weixinCard);
					
					message = "优惠券删除成功";
					j.setMsg(message);
					return j;
				}
			}
			
			message = "优惠券删除失败";
			//weixinCardService.delete(weixinCard);
			//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "优惠券删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除优惠券
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "优惠券删除成功";
		try{
			for(String id:ids.split(",")){
				WeixinCardEntity weixinCard = systemService.getEntity(WeixinCardEntity.class, 
				id
				);
				weixinCardService.delete(weixinCard);
				//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "优惠券删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
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
	 * 添加优惠券
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinCardEntity weixinCard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		try{
			
			String accessToken = weixinAccountService.getAccessToken();
			
			//上传门店LOGO
			String localhosturl = ResourceUtil.getDomain()+"/";
			String logoUrl = WeixinUtil.uploadLocationLogo(accessToken, WeixinUtil.upload_card_logo_url, localhosturl + weixinCard.getLogoUrl());
			
			String cardId = createCard(weixinCard, logoUrl);
			if(StringUtils.isNotEmpty(cardId)){
				
				weixinCard.setId(cardId);
				weixinCard.setCardId(cardId);
				weixinCard.setCreateDate(new Date());
				if("GROUPON".equals(weixinCard.getCardType())){
					weixinCard.setCardType("1");
				}
				if("CASH".equals(weixinCard.getCardType())){
					weixinCard.setCardType("2");
				}
				if("DISCOUNT".equals(weixinCard.getCardType())){
					weixinCard.setCardType("3");
				}
				if("GIFT".equals(weixinCard.getCardType())){
					weixinCard.setCardType("4");
				}
				if("GENERAL_COUPON".equals(weixinCard.getCardType())){
					weixinCard.setCardType("0");
				}
				
				weixinCard.setNowquantity(weixinCard.getQuantity());
				weixinCard.setStatus("0");
				weixinCardService.save(weixinCard);
				message = "创建优惠券成功";
			}else{
				message = "创建优惠券失败";
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "优惠券创建失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 创建卡券，获取卡券ID
	 * @param weixinCard
	 * @param logoUrl
	 * @return
	 * @throws ParseException 
	 */
	public String createCard(WeixinCardEntity weixinCard, String logoUrl) throws ParseException{
				
		JSONObject date_infoObj = new JSONObject();
		date_infoObj.put("type", 1);
		date_infoObj.put("begin_timestamp", this.getLongTime(weixinCard.getBeginTimestamp()));
		date_infoObj.put("end_timestamp", this.getLongTime(weixinCard.getEndTimestamp()));
		
		JSONObject skuObj = new JSONObject();
		skuObj.put("quantity", weixinCard.getQuantity());
		
		JSONArray jsonArray = new JSONArray();
		String[] location_ids = weixinCard.getLocationIdList().split(",");
		for(int i=0;i<location_ids.length;i++){
			
			jsonArray.add(new Long(location_ids[i]));
		}
		
		JSONObject cardObj = new JSONObject();
		
		cardObj.put("brand_name", weixinCard.getBrandName());
		cardObj.put("code_type", weixinCard.getCodeType());
		cardObj.put("title", weixinCard.getTitle());
		cardObj.put("sub_title", weixinCard.getSubTitle());
		cardObj.put("color", weixinCard.getColor());
		cardObj.put("notice", weixinCard.getNotice());
		cardObj.put("service_phone", weixinCard.getServicePhone());
		cardObj.put("description", weixinCard.getDescription());
		cardObj.put("date_info", date_infoObj);
		cardObj.put("sku", skuObj);
		cardObj.put("location_id_list", jsonArray);
		cardObj.put("get_limit", weixinCard.getGetLimit());
		cardObj.put("logo_url", logoUrl);
		
		JSONObject grouponObj = new JSONObject();
		grouponObj.put("base_info", cardObj);
		//团购券
		if("GROUPON".equals(weixinCard.getCardType())){			
			grouponObj.put("deal_detail", weixinCard.getDealDetail());//团购详情
		}		
		//代金券
		if("CASH".equals(weixinCard.getCardType())){			
			grouponObj.put("least_cost", weixinCard.getLeast_cost()*100);//起用金额。（单位为分）
			grouponObj.put("reduce_cost", weixinCard.getReduce_cost()*100);//减免金额。（单位为分）
		}
		//折扣券
		if("DISCOUNT".equals(weixinCard.getCardType())){	
			grouponObj.put("discount", weixinCard.getDiscount());//表示打折额度（百分比）。填30就是七折
		}		
		//礼品券
		if("GIFT".equals(weixinCard.getCardType())){			
			grouponObj.put("gift", weixinCard.getGift());//礼品的名称
		}		
		//优惠券
		if("GENERAL_COUPON".equals(weixinCard.getCardType())){			
			grouponObj.put("default_detail", weixinCard.getDefault_detail());//优惠详情
		}
		
		JSONObject card_infoObj = new JSONObject();
		card_infoObj.put("card_type", weixinCard.getCardType());
		
		//团购券
		if("GROUPON".equals(weixinCard.getCardType())){			
			card_infoObj.put("groupon", grouponObj);
		}		
		//代金券
		if("CASH".equals(weixinCard.getCardType())){			
			card_infoObj.put("sash", grouponObj);
		}
		//折扣券
		if("DISCOUNT".equals(weixinCard.getCardType())){	
			card_infoObj.put("discount", grouponObj);
		}		
		//礼品券
		if("GIFT".equals(weixinCard.getCardType())){			
			card_infoObj.put("gift", grouponObj);
		}		
		//优惠券
		if("GENERAL_COUPON".equals(weixinCard.getCardType())){			
			card_infoObj.put("general_coupon", grouponObj);
		}
		
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
	 * 更新优惠券
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinCardEntity weixinCard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "优惠券更新成功";
		WeixinCardEntity t = weixinCardService.get(WeixinCardEntity.class, weixinCard.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(weixinCard, t);
			weixinCardService.saveOrUpdate(t);
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "优惠券更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 优惠券新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WeixinCardEntity weixinCard, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinCard.getId())) {
			weixinCard = weixinCardService.getEntity(WeixinCardEntity.class, weixinCard.getId());
			req.setAttribute("weixinCardPage", weixinCard);
		}
		
		String cardType = req.getParameter("cardType");
		//团购券
		if("GROUPON".equals(weixinCard.getCardType())){
			
			return new ModelAndView("weixin/business/weixinCard-add-GROUPON");
		}
		
		//代金券
		if("CASH".equals(weixinCard.getCardType())){
			
			return new ModelAndView("weixin/business/weixinCard-add-CASH");
		}

		//折扣券
		if("DISCOUNT".equals(weixinCard.getCardType())){
	
			return new ModelAndView("weixin/business/weixinCard-add-DISCOUNT");
		}
		
		//礼品券
		if("GIFT".equals(weixinCard.getCardType())){
			
			return new ModelAndView("weixin/business/weixinCard-add-GIFT");
		}
		
		//优惠券
		if("GENERAL_COUPON".equals(weixinCard.getCardType())){
			
			return new ModelAndView("weixin/business/weixinCard-add-GENERAL_COUPON");
		}
				
		return new ModelAndView("weixin/business/weixinCard-add");
	}
	/**
	 * 优惠券编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WeixinCardEntity weixinCard, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinCard.getId())) {
			weixinCard = weixinCardService.getEntity(WeixinCardEntity.class, weixinCard.getId());
			req.setAttribute("weixinCardPage", weixinCard);
			
			if("GROUPON".equals(weixinCard.getCardType())){
				
				return new ModelAndView("weixin/business/weixinCard-add-GROUPON");
			}
			
			//代金券
			if("CASH".equals(weixinCard.getCardType())){
				
				return new ModelAndView("weixin/business/weixinCard-add-CASH");
			}

			//折扣券
			if("DISCOUNT".equals(weixinCard.getCardType())){
		
				return new ModelAndView("weixin/business/weixinCard-add-DISCOUNT");
			}
			
			//礼品券
			if("GIFT".equals(weixinCard.getCardType())){
				
				return new ModelAndView("weixin/business/weixinCard-add-GIFT");
			}
			
			//优惠券
			if("GENERAL_COUPON".equals(weixinCard.getCardType())){
				
				return new ModelAndView("weixin/business/weixinCard-add-GENERAL_COUPON");
			}
		}
		
		return new ModelAndView("weixin/business/weixinCard-update");
	}
	
	/**
	 * 查看
	 * @param weixinCard
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goView")
	public ModelAndView goView(WeixinCardEntity weixinCard, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinCard.getId())) {
			weixinCard = weixinCardService.getEntity(WeixinCardEntity.class, weixinCard.getId());
			req.setAttribute("weixinCardPage", weixinCard);
		}
		
		//团购券_1,     代金券_2,     折扣券_3,     礼品券_4,     优惠券_0
		//团购券
		if("1".equals(weixinCard.getCardType())){
			
			return new ModelAndView("weixin/business/weixinCard-add-GROUPON");
		}
		
		//代金券
		if("2".equals(weixinCard.getCardType())){
			
			return new ModelAndView("weixin/business/weixinCard-add-CASH");
		}

		//折扣券
		if("3".equals(weixinCard.getCardType())){
	
			return new ModelAndView("weixin/business/weixinCard-add-DISCOUNT");
		}
		
		//礼品券
		if("4".equals(weixinCard.getCardType())){
			
			return new ModelAndView("weixin/business/weixinCard-add-GIFT");
		}
		
		//优惠券
		if("0".equals(weixinCard.getCardType())){
			
			return new ModelAndView("weixin/business/weixinCard-add-GENERAL_COUPON");
		}
		
		return new ModelAndView("weixin/business/weixinCard-update");
	}
	
	/**
	 * 投放方式选择页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goSendCard")
	public ModelAndView goSendCard(String id, HttpServletRequest req) {
		
		WeixinCardEntity weixinCard = weixinCardService.getEntity(WeixinCardEntity.class, id);
		req.setAttribute("weixinCardPage", weixinCard);
		
		return new ModelAndView("weixin/business/weixinCard-toSend");
	}
	
	/**
	 * 跳转到优惠券发放页面
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "toSendCard")
	public ModelAndView toSendCard(String id, HttpServletRequest req) {
		
		WeixinCardEntity weixinCard = weixinCardService.getEntity(WeixinCardEntity.class, id);//当前卡券
		
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
	
	/**
	 * 发放卡券处理
	 * @param weixinCard
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doSendCard")
	@ResponseBody
	public AjaxJson doSendCard(WeixinCardEntity weixinCard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		WeixinCardEntity t = weixinCardService.get(WeixinCardEntity.class, weixinCard.getId());
		try {
			
			String isToAll = request.getParameter("isToAll");
			if("true".equals(isToAll)){
				
				
			}
			if("false".equals(isToAll)){
				
				//发放用户列表
				String receiveUserId = request.getParameter("receiveUserId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "优惠券发放失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 拉去优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "loadCard")
	@ResponseBody
	public AjaxJson loadCard(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		try {

			JSONArray statusList = new JSONArray();
			statusList.add("CARD_STATUS_VERIFY_OK");
			//statusList.add("CARD_STATUS_DISPATCH");
			
			JSONObject postObj = new JSONObject();
			postObj.put("begin", 0);
			postObj.put("limit", 49);
			postObj.put("status_list", statusList);

			String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
			if (StringUtil.isNotEmpty(accessTocken)) {

				String url = WeixinUtil.load_card_url.replace("ACCESS_TOKEN", accessTocken);
				JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", postObj.toString());
				if (jsonObject != null) {
					if (("0").equals(jsonObject.get("errcode").toString())) {
						
						JSONArray jsonArray = jsonObject.getJSONArray("card_id_list");
						
						if(jsonArray!=null && jsonArray.size()>0){
							
							for (int i = 0; i < jsonArray.size(); i++) {
								
								String cardId = jsonArray.get(i).toString();
								
								getCardInfoByCardId(cardId);
								
								//保存后要更新sessionfactory
								weixinCardService.findByProperty(WeixinCardEntity.class, "accountid", ResourceUtil.getWeiXinAccountId());
							}
							
							message = "卡券信息获取成功";
							j.setMsg(message);
							return j;
						}
					}
				}
			}
			message = "未获取到卡券信息";
		} catch (Exception e){
			e.printStackTrace();
			message = "卡券信息获取失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 拉取卡券详情
	 * @param cardId
	 * @throws ParseException 
	 */
	public void getCardInfoByCardId(String cardId) throws ParseException{
		
		JSONObject cardObj = new JSONObject();
		cardObj.put("card_id", cardId);
		
		String accessTocken = weixinAccountService.getAccessToken();// 调用接口获取access_token
		String card_url = WeixinUtil.load_cardinfo_url.replace("ACCESS_TOKEN", accessTocken);
		JSONObject jsonObject = WeixinUtil.httpRequest(card_url, "POST", cardObj.toString());
		if (jsonObject != null) {
			if (("0").equals(jsonObject.get("errcode").toString())) {
				
				JSONObject cardobj = jsonObject.getJSONObject("card");
				
				String card_type = cardobj.getString("card_type");
				
				JSONObject groupon = null;
				
				//优惠券类别判断 开始
				if(cardobj.containsKey("groupon"))
					groupon = cardobj.getJSONObject("groupon");
				
				if(cardobj.containsKey("gift"))
					groupon = cardobj.getJSONObject("gift");
				
				if(cardobj.containsKey("cash"))
					groupon = cardobj.getJSONObject("cash");
				
				if(cardobj.containsKey("discount"))
					groupon = cardobj.getJSONObject("discount");
				
				if(cardobj.containsKey("groupon"))
					groupon = cardobj.getJSONObject("groupon");
				
				if(cardobj.containsKey("general_coupon"))
					groupon = cardobj.getJSONObject("general_coupon");
				
				//优惠券类别判断 结束
				
				
				if(groupon.containsKey("base_info")){
					
					JSONObject base_info = groupon.getJSONObject("base_info");
					
					String status = "";
					if(base_info.containsKey("status"))
						status = base_info.getString("status");
					
					//String id = base_info.getString("id");
					
					WeixinCardEntity weixinCard = weixinCardService.findUniqueByProperty(WeixinCardEntity.class, "cardId", cardId);
					if(null != weixinCard){
						
						if(base_info.containsKey("sku")){
							JSONObject sku = base_info.getJSONObject("sku");
							
							if(sku.containsKey("total_quantity")){
								int quantity = sku.getInt("quantity");
								weixinCard.setNowquantity(quantity);
							}
							
							if(sku.containsKey("total_quantity")){
								int total_quantity = sku.getInt("total_quantity");
								weixinCard.setQuantity(total_quantity);
							}
						}
						
						//待审核
						if("CARD_STATUS_NOT_VERIFY".equals(status)){
							weixinCard.setStatus("0");
						}
						//审核失败
						if("CARD_STATUS_VERIFY_FAIL".equals(status)){
							weixinCard.setStatus("0");			
						}
						//通过审核
						if("CARD_STATUS_VERIFY_OK".equals(status)){
							weixinCard.setStatus("1");
						}
						//卡券被商户删除
						if("CARD_STATUS_USER_DELETE".equals(status)){
							weixinCard.setStatus("3");
						}
						//在公众平台投放过的卡券
						if("CARD_STATUS_DISPATCH".equals(status)){
							weixinCard.setStatus("1");						
						}
						
						if(base_info.containsKey("date_info")){
							JSONObject date_info = base_info.getJSONObject("date_info");
							//String type = date_info.getString("type");
							String begin_timestamp = date_info.getString("begin_timestamp");
							String end_timestamp = date_info.getString("end_timestamp");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							int aa = Integer.parseInt(begin_timestamp);
							String begin_time = sdf.format(aa * 1000L);
							Date begin_date = sdf.parse(begin_time);
							
							int bb = Integer.parseInt(end_timestamp);
							String end_time = sdf.format(bb * 1000L);
							Date end_date = sdf.parse(end_time);
							
							weixinCard.setBeginTimestamp(begin_date);
							weixinCard.setEndTimestamp(end_date);
							
							Date now_date = new Date();
							if(now_date.after(end_date)){
								
								weixinCard.setStatus("4");//已经过期
							}
						}
						
						weixinCardService.saveOrUpdate(weixinCard);
					}else{
						
						//创建
						weixinCard = new WeixinCardEntity();
						weixinCard.setId(cardId);
						weixinCard.setAccountid(ResourceUtil.getWeiXinAccountId());
						weixinCard.setCardId(cardId);
						
						if(base_info.containsKey("logo_url")){
							String logo_url=base_info.getString("logo_url");
							weixinCard.setLogoUrl(logo_url);
						}
						
						//String appid = base_info.getString("appid");
						
						if(base_info.containsKey("code_type")){
							String code_type=base_info.getString("code_type");
							weixinCard.setCodeType(code_type);
						}
						
						
						if(base_info.containsKey("brand_name")){
							
							String brand_name=base_info.getString("brand_name");
							weixinCard.setBrandName(brand_name);
						}
							
						
						if(base_info.containsKey("title")){
							String title=base_info.getString("title");
							weixinCard.setTitle(title);
						}
						
						if(base_info.containsKey("sub_title")){
							String sub_title=base_info.getString("sub_title");
							weixinCard.setSubTitle(sub_title);
						}
						
						
						
						if(base_info.containsKey("color")){
							String color=base_info.getString("color");
							weixinCard.setColor(color);
						}
						
						if(base_info.containsKey("notice")){
							String notice=base_info.getString("notice");
							weixinCard.setNotice(notice);
						}
						
						if(base_info.containsKey("service_phone")){
							String service_phone=base_info.getString("service_phone");
							weixinCard.setServicePhone(service_phone);
						}
						
						
						
						if(base_info.containsKey("description")){
							String description=base_info.getString("description");
							weixinCard.setDescription(description);	
						}
						
						//int use_limit = base_info.getLong("use_limit");
						
						
						if(base_info.containsKey("get_limit")){
							int get_limit=base_info.getInt("get_limit");
							weixinCard.setGetLimit(get_limit);
						}
						
						
						//Boolean can_share = base_info.getBoolean("can_share");
						
						if(base_info.containsKey("location_id_list")){
							JSONArray location_id_list = base_info.getJSONArray("location_id_list");
							weixinCard.setLocationIdList(location_id_list.toString());
							
							StringBuffer sb = new StringBuffer();
							for (int i = 0; i < location_id_list.size(); i++) {
								
								String location_id = location_id_list.get(i).toString();
								
								WeixinLocationEntity weixinLocation = weixinLocationService.findUniqueByProperty(WeixinLocationEntity.class, "poiId", location_id);
								if(null != weixinLocation){
									
									if(i==0)
										sb.append(weixinLocation.getBusinessName());
									else{
										sb.append(",");
										sb.append(weixinLocation.getBusinessName());
									}
								}								
							}
							if(null != new String(sb)){
								weixinCard.setBusinessName(sb.toString());
							}							
						}
						
						//String custom_url_name = base_info.getString("custom_url_name");
						//String custom_url = base_info.getString("custom_url");
						//String source = base_info.getString("source");
						
						if(cardobj.containsKey("deal_detail")){
							String deal_detail = cardobj.getString("deal_detail");
							weixinCard.setDealDetail(deal_detail);
						}
						
						
						if(base_info.containsKey("sku")){
							JSONObject sku = base_info.getJSONObject("sku");
							
							if(sku.containsKey("total_quantity")){
								int quantity = sku.getInt("quantity");
								weixinCard.setNowquantity(quantity);
							}
							
							if(sku.containsKey("total_quantity")){
								int total_quantity = sku.getInt("total_quantity");
								weixinCard.setQuantity(total_quantity);
							}
						}
						
						
						if(base_info.containsKey("least_cost")){
							int least_cost=base_info.getInt("least_cost");
							weixinCard.setLeast_cost(least_cost);
						}
						
						if(base_info.containsKey("reduce_cost")){
							int reduce_cost=base_info.getInt("reduce_cost");
							weixinCard.setReduce_cost(reduce_cost);
						}
						
						if(base_info.containsKey("discount")){
							int discount=base_info.getInt("discount");
							weixinCard.setDiscount(discount);
						}
						
						if(base_info.containsKey("gift")){
							String gift=base_info.getString("gift");
							weixinCard.setGift(gift);	
						}
						
						if(base_info.containsKey("default_detail")){
							String default_detail=base_info.getString("default_detail");
							weixinCard.setDefault_detail(default_detail);	
						}
						
						
						//待审核
						if("CARD_STATUS_NOT_VERIFY".equals(status)){
							weixinCard.setStatus("0");
						}
						//审核失败
						if("CARD_STATUS_VERIFY_FAIL".equals(status)){
							weixinCard.setStatus("0");			
						}
						//通过审核
						if("CARD_STATUS_VERIFY_OK".equals(status)){
							weixinCard.setStatus("1");
						}
						//卡券被商户删除
						if("CARD_STATUS_USER_DELETE".equals(status)){
							weixinCard.setStatus("3");
						}
						//在公众平台投放过的卡券
						if("CARD_STATUS_DISPATCH".equals(status)){
							weixinCard.setStatus("1");						
						}
						
						//
						if(base_info.containsKey("date_info")){
							JSONObject date_info = base_info.getJSONObject("date_info");
							//String type = date_info.getString("type");
							String begin_timestamp = date_info.getString("begin_timestamp");
							String end_timestamp = date_info.getString("end_timestamp");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							int aa = Integer.parseInt(begin_timestamp);
							String begin_time = sdf.format(aa * 1000L);
							Date begin_date = sdf.parse(begin_time);
							
							int bb = Integer.parseInt(end_timestamp);
							String end_time = sdf.format(bb * 1000L);
							Date end_date = sdf.parse(end_time);
							
							weixinCard.setBeginTimestamp(begin_date);
							weixinCard.setEndTimestamp(end_date);
							
							Date now_date = new Date();
							if(now_date.after(end_date)){
								
								weixinCard.setStatus("4");//已经过期
							}
						}
						
						//团购券_1,代金券_2,折扣券_3,礼品券_4,优惠券_0
						//通用券
						if("GENERAL_COUPON".equals(card_type))
							weixinCard.setCardType("0");
						
						//团购券：GROUPON
						if("GROUPON".equals(card_type))
							weixinCard.setCardType("1");
						
						//代金券：CASH
						if("CASH".equals(card_type))
							weixinCard.setCardType("2");
						
						//折扣券：DISCOUNT
						if("DISCOUNT".equals(card_type))
							weixinCard.setCardType("3");
						
						//礼品券：GIFT
						if("GIFT".equals(card_type))
							weixinCard.setCardType("4");
						
						
						weixinCardService.save(weixinCard);
					}
				}
			}
		}
	}
}
