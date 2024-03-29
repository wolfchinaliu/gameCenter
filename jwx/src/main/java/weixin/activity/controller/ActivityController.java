package weixin.activity.controller;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.JsonUtil;
import org.jeecgframework.core.util.MD5Utils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;
import sdk.jfinal.JFinalUtils;
import sdk.weishenghuo.RequestEncapsulation;
import weixin.activity.entity.LotterRule;
import weixin.activity.entity.QuestionRule;
import weixin.activity.entity.WeixinAcountOuterEntity;
import weixin.activity.entity.WeixinActivityEntity;
import weixin.activity.entity.WeixinActivityQuestionEntity;
import weixin.activity.entity.WeixinGameDetailEntity;
import weixin.activity.entity.WeixinPracticalityRecordEntity;
import weixin.activity.service.IWeixinActivityService;
import weixin.advertisement.service.AdvertisementServiceI;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.RedisUtil;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.HttpUtil;
import weixin.lottery.service.WeixinWinningrecordServiceI;
import weixin.lottery.service.WeixinWinningrecordlxcServiceI;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.util.DataDictionaryUtil.AdPublishPosition;
import weixin.util.DataDictionaryUtil.FlowType;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 微信活动
 * @date 2015-02-05 14:26:01
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
@RequestMapping("/activityController")
public class ActivityController extends BaseController implements PageAuthHandler {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActivityController.class);
	private String path = ResourceUtil.getConfigByName("jfinalUrl-config");
	@Autowired
	private SystemService systemService;
	@Autowired
	private WeixinWinningrecordServiceI winningrecordService;
	@Autowired
	private WeixinWinningrecordlxcServiceI winningrecordlxcService;
	@Autowired
	private WeixinMemberServiceI weixinMemberService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	private AdvertisementServiceI adService;
	@Autowired
	private IWeixinActivityService weixinActivityService;

	// private final Logger LOGGER=Logger.getLogger("");
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@SuppressWarnings("unused")
	private String findAccountId(HttpServletRequest request) {
		// 非法请求，直接返回
		if (request == null) {
			return "";
		}
		// request请求中拿到accountid,直接返返回，如果拿不到。则从上下文中获取
		String accountid = request.getParameter("accountid");
		if (accountid != null && !"".equals(accountid)) {
			return accountid;
		} else {
			return ResourceUtil.getWeiXinAccountId();
		}
	}

	/**
	 * 引导授权界面
	 *
	 * @param request
	 */
	@RequestMapping(params = "startActivity")
	public ModelAndView startLottery(HttpServletRequest request, HttpServletResponse response) {
		Long start = System.currentTimeMillis(); // 方法开始时间
		StringBuffer sb = new StringBuffer();
		sb.append("ActivityController.startActivity():");
		String hdid = request.getParameter("hdid");
		String openId = request.getParameter("openId");

		logger.info("信息 ：" + hdid);
		String url = null;
		try {
			WeixinActivityEntity hdEntity = this.systemService.get(WeixinActivityEntity.class, hdid);
			// 活动不存在时的处理页面
			if (hdEntity == null) {
				return new ModelAndView("common/404");
			}
			sb.append("用户[" + openId + "]参与了活动[{" + hdid + ":" + hdEntity.getTitle() + "}]");
			String accountid = hdEntity.getAccountid();
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("hdid", hdid); // 活动ID，传给后面用
			if (StringUtils.isBlank(openId)) {
				url = weixinAccountService.pageAuth2(accountid, properties, this); // 调用授权封装:商户ID，
			} else {
				url = weixinAccountService.pageAuth2(accountid, properties, this, openId);
			}
			sb.append(", 重定向地址:" + url);     
			return new ModelAndView("redirect:" + url);
		} catch (Exception e) {
			logger.error("开始进入游戏异常--",e);
			return new ModelAndView("common/404");
		} finally {
			Long end = System.currentTimeMillis();
			sb.append(", 方法耗时:" + (end - start) + "ms");
			logger.info(sb.toString());
		}

	}

	@Override
	public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request) throws Exception {
		return this.followAndBind(msg, request, false);
	}

	@Override
	public ModelAndView follow(PageAuthCallback msg, HttpServletRequest request) throws Exception {
		return this.followAndBind(msg, request, true);
	}

	public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request,
			boolean allowNotBindPhoneNumber) {
		Long start = System.currentTimeMillis(); // 方法开始时间
		StringBuffer sb = new StringBuffer();
		sb.append("ActivityController.followAndBind()");
		ModelAndView view = new ModelAndView();
		try {
			// 判断活动id是否存在
			String hdid = msg.getProperties().get("hdid");
			if (hdid == null || "".equals(hdid)) {
				sb.append("活动ID为空，进入404页面");
				return new ModelAndView("common/404");
			}
			// 获取openID,获取的是点击者的openId
			String openId = msg.getOpenId();
			// 根据本次活动id查询活动
			WeixinActivityEntity activityEntity = this.systemService.get(WeixinActivityEntity.class, hdid);
			view.addObject("activity", activityEntity);
			sb.append("用户[" + openId + "]参与了活动[{" + hdid + ":" + activityEntity.getTitle() + "}]");
			// 获取当前公众帐号
			String accountid = activityEntity.getAccountid();
			Map<String, Object> ad = null;
			// 获取用户信息
			WeixinMemberEntity memberEntity = new WeixinMemberEntity();
			String hql = "from WeixinMemberEntity t where t.accountId='" + accountid + "' and t.openId='" + openId
					+ "'";
			List<WeixinMemberEntity> weixinMemberList = weixinMemberService.findByQueryString(hql);
			if (weixinMemberList != null && weixinMemberList.size() > 0) {
				memberEntity = weixinMemberList.get(0);
			}
			WeixinAccountEntity accountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, accountid);
			view.addObject("account", accountEntity);
			view.addObject("activity", activityEntity);			
			view.addObject("member", memberEntity);
			String phoneNumber = memberEntity.getPhoneNumber();
			if (StringUtil.isNotEmpty(phoneNumber)) {
                MerchantInfoBean userFlowAccoun = JFinalUtils.getUserFlowAccount(openId, accountid);
                if (userFlowAccoun != null && userFlowAccoun.getCode().equals("200")) {
					request.getSession().setAttribute("provinceFlowValue",
							userFlowAccoun.getData().get(0).getProvinceFlowValue());
					request.getSession().setAttribute("countryFlowValue",
							userFlowAccoun.getData().get(0).getCountryFlowValue());
				} else {
					request.getSession().setAttribute("provinceFlowValue", 0);
					request.getSession().setAttribute("countryFlowValue", 0);
				}
			} else {
				request.getSession().setAttribute("provinceFlowValue", 0);
				request.getSession().setAttribute("countryFlowValue", 0);
			}
			
			int activityType = activityEntity.getType();
			String hdUrl = null;
			// 判断活动类型
			switch (activityType) {
			case 1:
				hdUrl = "weixin/activity/roulette";
				ad = this.adService.getPublishedAd(accountEntity.getAcctId(), AdPublishPosition.zhuanpan.getCode());
				break;
			case 101:
				hdUrl = "weixin/activity/question";
				List<WeixinActivityQuestionEntity> entities = weixinActivityService.getQuestions(hdid);
				view.addObject("questions", entities);
				view.addObject("totalQuestion", entities.size());
				break;
			case 201:
				//获取排名记录
				String sql = "select opendid , max(score) as score ,nickname,addtime from (select * from weixin_game_detail d where d.hdid = '"+activityEntity.getId()+"' and d.addtime > '"+getLastTime(activityEntity.getFrequency())+"'  order by score desc ,addtime asc) t group by t.opendid order by score desc ,addtime asc";
	        	List<WeixinGameDetailEntity> detailEntities = systemService.findObjForJdbc( sql, 1,10,WeixinGameDetailEntity.class);
	        	view.addObject("rankList", detailEntities);
				hdUrl = "weixin/activity/eatMoonCakes";
				break;
			case 202:
				//获取排名记录
				String sql202 = "select opendid , max(score) as score ,nickname,addtime from (select * from weixin_game_detail d where d.hdid = '"+activityEntity.getId()+"' and d.addtime > '"+getLastTime(activityEntity.getFrequency())+"'  order by score desc ,addtime asc) t group by t.opendid order by score desc ,addtime asc";
				List<WeixinGameDetailEntity> detailEntities202 = systemService.findObjForJdbc( sql202, 1,10,WeixinGameDetailEntity.class);
				view.addObject("rankList", detailEntities202);
				hdUrl = "weixin/activity/liujiaoping";
				ad = this.adService.getPublishedAd(accountEntity.getAcctId(), AdPublishPosition.sixPin.getCode());
				break;
			default:
				hdUrl = "weixin/activity/roulette";
				ad = this.adService.getPublishedAd(accountEntity.getAcctId(), AdPublishPosition.zhuanpan.getCode());
				break;
			}
			view.setViewName(hdUrl);
		 	view.addObject("accountType",accountEntity.getAccounttype());
			request.getSession().setAttribute("ad", ad);
			
			request.setAttribute("map", weixinAccountService.getAccountJsticket(request, accountid));
			// 链接
			String link = ResourceUtil.getConfigByName("domain") + "/" + "activityController.do?startActivity&hdid="
					+ hdid;
			request.setAttribute("link", link);
			// 获取活动范围
			WeixinMerchantCoverAreaEntity ww1 = weixinAccountService
					.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", accountEntity.getAcctId());
			String cityName = ww1.getCityname();
			String businessArea = ww1.getBusinessArea();
			if (StringUtils.isBlank(businessArea) || "三网通".equals(businessArea)) {
				businessArea = "所有运营商";
			}
			if (cityName == null) {
				request.setAttribute("provinceAccount", ww1.getProvincename() + "内" + businessArea);
			} else {
				request.setAttribute("provinceAccount", ww1.getProvincename() + ww1.getCityname() + "内" + businessArea);
			}
			// 判断是否在活动日期
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date now = new Date();
			if (now.before(activityEntity.getStartTime())) {
				view.addObject("code", 1);
				view.addObject("msg", "活动还未开始   开始时间为：" + fm.format(activityEntity.getStartTime()));
				return view;
			}
			if (now.after(activityEntity.getEndTime())) {
				view.addObject("code", 1);
				view.addObject("msg", "活动已结束   ");
				return view;
			}
			if(!weixinActivityService.inTimePart(activityEntity)){
				view.addObject("code", 1);
				view.addObject("msg", "活动还未到开始时间段  请稍后!");
				return view;
			}
			String sql = "SELECT * FROM weixin_prac_record  WHERE hdid='" + hdid + "' ORDER BY addtime DESC ";
			List<WeixinPracticalityRecordEntity> weixinPracticalityRecordEntities = systemService.findObjForJdbc(sql, 1,
					10, WeixinPracticalityRecordEntity.class);
			view.addObject("record", weixinPracticalityRecordEntities);
			// 活动总次数
			int total = surplusGameTimes(activityEntity, null);
			if (now.after(activityEntity.getEndTime()) || total <= 0) {
				view.addObject("code", 2);
				view.addObject("msg", "本次活动已结束，敬请您关注下次活动！");
				return view;
			}
			// 个人次数
			int count = surplusGameTimes(activityEntity, openId);
			if (count <= 0) {
				view.addObject("code", 3);
				view.addObject("msg", "您本次活动的抽奖次数已用完，敬请关注下次活动！");
				return view;
			}
			// 验证手机号是否在覆盖区
			if (StringUtil.isNotEmpty(memberEntity.getPhoneNumber())) {
				String url1 = path + "userGetFlow/getCoverAndLocation";
				Gson gson1 = new Gson();
				JSONObject myJson1 = new JSONObject();
				myJson1.accumulate("phoneNumber", memberEntity.getPhoneNumber());
				myJson1.accumulate("id", memberEntity.getAccountId());
				JSONObject jsonObject1 = HttpUtil.httpPost(url1, myJson1, false);
				String strFlow1 = gson1.toJson(jsonObject1);
				Type type1 = new TypeToken<Update>() {
				}.getType();
				Update update1 = gson1.fromJson(strFlow1, type1);
				if (update1 == null) {
					view.addObject("code", 4);
					view.addObject("msg", "手机号验证失败！");
					return view;
				}
				if (update1.getCode().equals("201")) {
					view.addObject("code", 5);
					view.addObject("msg", "手机号不在活动覆盖区");
					return view;
				}
			}
			view.addObject("count", count);
			view.addObject("code", 0);
			sb.append(", 内部跳转到活动地址:" + hdUrl);
			request.getSession().setAttribute("openId", openId);
			request.getSession().setAttribute("hdId", hdid); 
			return view;
		} catch (Exception e) {
			logger.error("进入活动异常场", e);
			return new ModelAndView("common/404");
		} finally {
			Long end = System.currentTimeMillis();
			sb.append(MessageFormat.format(", 方法耗时:{0}ms", end - start));
			logger.info(sb.toString());
		}
	}
	@RequestMapping(params = "playActivity" ,method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson playActivity(HttpSession session) {
		Long start = System.currentTimeMillis(); // 方法开始时间
		StringBuffer sb = new StringBuffer();
		sb.append("ActivityController_playGame");
		AjaxJson j = new AjaxJson();
		String hdId = (String) session.getAttribute("hdId");
		String openId = (String) session.getAttribute("openId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("continueFlag", false);
		//
		if (StringUtil.isEmpty(openId) || StringUtil.isEmpty(hdId)) {
			params.put("error", "invalid");
			params.put("msg", "请求超时，请重新打开该页面！");
			j.setSuccess(false);
			j.setAttributes(params);
			sb.append("openIdIsIllegal");
			return j;
		}
		sb.append("_hdid:" + hdId + "_openId:" + openId);

		try {
			WeixinActivityEntity activityEntity = systemService.get(WeixinActivityEntity.class, hdId);
			Date now = new Date();
			if(now.after(activityEntity.getEndTime()) || now.before(activityEntity.getStartTime())){
				params.put("error", "invalid");
				params.put("msg", "本次活动已结束，敬请关注下次活动！");
				j.setSuccess(false);
				j.setAttributes(params);
				sb.append("userTimeOver:" + activityEntity.getEvenNumber() + "---hdid:" + hdId + "---openId:" + openId);
				return j;
			}
			int count = surplusGameTimes(activityEntity, openId);
			params.put("count", count);
			if (count <= 0) {
				// 已经超过总抽奖数
				params.put("error", "invalid");
				params.put("msg", "您本次活动的次数已用完，敬请关注下次活动！");
				params.put("total", count);
				j.setSuccess(false);
				j.setAttributes(params);
				sb.append("userTimeOver:" + activityEntity.getEvenNumber() + "---hdid:" + hdId + "---openId:" + openId);
				return j;
			}
			WeixinMemberEntity weixinMember = weixinMemberService.getWeixinMember(openId,
					activityEntity.getAccountid());
			List<LotterRule> list = JSON.parseArray(activityEntity.getActivityRule(), LotterRule.class);
			int level = getRand(list);
			if (level < list.size()) {// 抽取中奖
				int pizse = surplusPrize(hdId, level, null,list.get(level).getNumber());
				if (pizse > 0) { // 中奖
					if (1 == list.get(level).getType()) {// 判断是否为流量
						String hql = "from weixinAcctFlowAccountEntity t where t.accountId=?";
						List<weixinAcctFlowAccountEntity> weixinAcctFlows = systemService.findHql(hql,
								activityEntity.getAccountid());
						if (activityEntity.getFlowType().equals(FlowType.country.getCode())) {
							if (weixinAcctFlows.get(0).getCountryFlowValue() <= Integer
									.valueOf(list.get(level).getValue())) {
								level = 7;
								sb.append("merchant-provinceflow-notenough:"
										+ weixinAcctFlows.get(0).getCountryFlowValue() + "---hdid:" + hdId
										+ "---openId:" + openId);
							}
						} else {
							if (weixinAcctFlows.get(0).getProvinceFlowValue() <= Integer
									.valueOf(list.get(level).getValue())) {
								level = 7;
								sb.append("merchant-countryflow-notenough:"
										+ weixinAcctFlows.get(0).getProvinceFlowValue() + "---hdid:" + hdId
										+ "---openId:" + openId);
							}
						}
						// 判断手机是否覆盖
						if (level < list.size()) {
							if (StringUtil.isNotEmpty(weixinMember.getPhoneNumber())) {
								/**
								 * 查询手机归属地是否统一
								 */
								String url1 = path
										+ "userGetFlow/getCoverAndLocation";
								Gson gson1 = new Gson();
								JSONObject myJson1 = new JSONObject();
								myJson1.accumulate("phoneNumber", weixinMember.getPhoneNumber());
								myJson1.accumulate("id", weixinMember.getAccountId());
								JSONObject jsonObject1 = HttpUtil.httpPost(url1, myJson1, false);
								String strFlow1 = gson1.toJson(jsonObject1);
								Type type1 = new TypeToken<Update>() {
								}.getType();
								Update update1 = gson1.fromJson(strFlow1, type1);

								// code为“10025”代表商户区域为空，为“20015”代表不是正确的手机号，
								// “200”代表在商户的覆盖区域内，messge的值为商户的覆盖区域，
								// “201"代表的不在商户的覆盖区域内，message的值为商户的覆盖区域
								// if (wxRiddles.getFlowtype().equals("省内流量") &&
								// update1.getCode().equals("201")) {
								if (update1 == null) {
									logger.info("验证手机号 覆盖失败  未连接上 jfinal");
									params.put("error", "invalid");
									params.put("msg", "意外的错误！");
									j.setSuccess(false);
									j.setAttributes(params);
									return j;
								}
								if (update1.getCode().equals("201")) {
									params.put("error", "invalid");
									params.put("msg", "手机号不在商户覆盖区域内，不予赠送流量");
									j.setSuccess(false);
									j.setAttributes(params);
									return j;
								}
							}
							// 充值流量
							String url = path
									+ "userGetFlow/UpdateFlowAndAddFlowRecord";
							Gson gson = new Gson();
							JSONObject myJson = new JSONObject();
							myJson.accumulate("phoneNumber", weixinMember.getPhoneNumber());
							myJson.accumulate("flowValue", list.get(level).getValue());
							myJson.accumulate("id", activityEntity.getAccountid());
							myJson.accumulate("opreateType", "新大转盘");
							myJson.accumulate("openid", openId);
							myJson.accumulate("flowType", activityEntity.getFlowType()); // 省内：省内流量；全国：全国流量
							myJson.element("nickName", weixinMember.getNickName());
							JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
							String strFlow = gson.toJson(contentFlow);
							Type type = new TypeToken<Update>() {
							}.getType();
							// 是否中奖参数
							params.put("continueFlag", true);
							Update update = gson.fromJson(strFlow, type);
							sb.append("jfinal：" + myJson.toString() + "url:" + url + "---hdid：" + hdId + "---openId:"
									+ openId);
							if (StringUtils.isNotBlank(weixinMember.getPhoneNumber())) {
								if (!"200".equals(update.getCode()) && !"10026".equals(update.getCode())) {
									params.put("msg", "流量更新失败！");
									j.setSuccess(false);
									j.setAttributes(params);
									return j;
								}
							}
							sb.append("jfinal-result:" + update);
						}
					}/*else if(3 == list.get(level).getType()){ //微生活券
						String templateid = list.get(level).getTempId();
						//先获取卡号，在请求发券
						WeixinAcountOuterEntity acountOuterEntity = systemService.get(WeixinAcountOuterEntity.class, activityEntity.getAccountid());
						if(acountOuterEntity == null || StringUtil.isEmpty(acountOuterEntity.getWshAppid())){
							params.put("msg", "对不起该商户不支持代金券" );
							params.put("error", "invalid");
							j.setSuccess(false);
							j.setAttributes(params);
							return j;
						}
						com.alibaba.fastjson.JSONObject userJSON  = RequestEncapsulation.getUserFromOPenid(openId, acountOuterEntity.getWshAppid(), acountOuterEntity.getWshAppKey());
						if(userJSON.getInteger("errcode") != 0){
							params.put("msg", "赠送代金券失败 " + userJSON.getString("errmsg"));
							params.put("error", "invalid");
							j.setSuccess(false);
							j.setAttributes(params);
							return j;
						}
						String wshCno = userJSON.getJSONObject("res").getString("cno");
						com.alibaba.fastjson.JSONObject resJSON = RequestEncapsulation.sendTicket(wshCno, templateid, 1, acountOuterEntity.getWshAppid(), acountOuterEntity.getWshAppKey());
						if(resJSON.getInteger("errcode") != 0){
							params.put("msg", "赠送代金券失败 " + resJSON.getString("errmsg"));
							j.setSuccess(false);
							j.setAttributes(params);
							return j;
						}
						
					}*/
				} else {
					// 奖品数没了的时候 为没中奖
					level = 7;
				}
			}
			String msg = "";
			if (level < list.size()) {
				// 保存获奖记录
				savePracticalityRecord(list.get(level), activityEntity, weixinMember, level);
				if (1 == list.get(level).getType()) {
					msg = activityEntity.getFlowDraw();
					msg = msg.replaceAll("\\$value", "流量" + list.get(level).getValue() + "M");
				} else {
					msg = activityEntity.getPreDraw();
					msg = msg.replaceAll("\\$value", list.get(level).getValue().split("/")[0]);
				}
				
				msg = msg.replaceAll("\\$level", list.get(level).getName());
			} else {
				msg = activityEntity.getNoDraw();
			}
			msg = msg.replaceAll("\\$name", weixinMember.getNickName() == null ? "" : weixinMember.getNickName());

			// 保存抽奖纪录
			Timestamp nowTime = new Timestamp(new Date().getTime());
			WeixinGameDetailEntity detail = new WeixinGameDetailEntity();
			detail.setAccountid(activityEntity.getAccountid());
			detail.setAddtime(nowTime);
			detail.setHdid(hdId);
			detail.setScore(level< list.size() ? level+1:0);
			detail.setOpendid(openId);
			detail.setNickname(weixinMember.getNickName());
			detail.setMsg(level < list.size() ?  list.get(level).getValue() : "未中奖");
			this.systemService.save(detail);
			params.put("level", level);
			params.put("type", list.get(level).getType());
			params.put("activityUrl", activityEntity.getActivityUrl());
			params.put("count", count - 1);
			params.put("msg", msg);
			j.setAttributes(params);
			return j;
		} catch (Exception e) {
			logger.error("玩游戏异常", e);
			params.put("error", "invalid");
			params.put("msg", "意外的错误！");
			j.setSuccess(false);
			j.setAttributes(params);
			return j;
		} finally {
			Long end = System.currentTimeMillis();
			sb.append("_time:" + (end - start));
			logger.info(sb.toString());
		}
	}

	///////////////////////////////////////// 公共方法/begin////////////////////////////////////////////

	// 查询游戏剩余次数
	public int surplusGameTimes(WeixinActivityEntity activityEntity, String openId) {
		if (StringUtils.isEmpty(openId) && activityEntity.getTotalNumber() == 0) 
				return 1;
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dm = new SimpleDateFormat("yyyy-MM");
		String table = "weixin_game_detail";
		/*if(activityEntity.getType() < 100)
			table = "weixin_draw_detail";
		else if(activityEntity.getType() < 200)
			table = "weixin_draw_detail";
		else if(activityEntity.getType() < 300)
			table = "weixin_game_detail";
		else
			table = "weixin_game_detail";*/
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT COUNT(*) count FROM "+table+" t where 1=1 ");
		buffer.append(" and t.HDID=").append("'").append(activityEntity.getId()).append("'");
		int count = 0;
		int leftcount = 0;
		if (StringUtils.isEmpty(openId)) {
			count = winningrecordService.getCount(buffer.toString());
			leftcount = activityEntity.getTotalNumber() - count;
			return leftcount > 0 ? leftcount : 0;
		}
		int frequency = activityEntity.getFrequency();
		switch (frequency) {
		case 1:
			buffer.append(" and t.OPENDID=").append("'").append(openId).append("'");
			buffer.append(" and DATE_FORMAT(t.ADDTIME,'%Y-%m-%d')= ").append("'").append(d.format(new java.util.Date()))
					.append("'");
			break;
		case 2:
			buffer.append(" and t.OPENDID=").append("'").append(openId).append("'");
			buffer.append(" and DATE_FORMAT(t.ADDTIME,'%Y-%m-%d')>= ").append("'").append(convertWeekByDate())
					.append("'");
			break;
		case 3:
			buffer.append(" and t.OPENDID=").append("'").append(openId).append("'");
			buffer.append(" and DATE_FORMAT(t.ADDTIME,'%Y-%m-%d')>= ").append("'").append(dm.format(new Date()))
					.append("-01'");
			break;
		default:
			buffer.append(" and t.OPENDID=").append("'").append(openId).append("'");
			break;
		}
		count = winningrecordService.getCount(buffer.toString());
		leftcount = activityEntity.getEvenNumber() - count;
		return leftcount > 0 ? leftcount : 0;
	}

	// 查询游戏奖项剩余数量
	public int surplusPrize(String hdid, int level,String prizelevel, int toalCount) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT COUNT(*) count FROM weixin_prac_record t where 1=1 ");
		buffer.append(" and t.hdid=").append("'").append(hdid).append("'");
		buffer.append(" and t.level=").append(level);
		if(!StringUtil.isEmpty(prizelevel))
			buffer.append(" and t.prizelevel='").append(prizelevel).append("'");
		int count = 0;
		count = winningrecordlxcService.getCount(buffer.toString());
		return toalCount - count > 0 ? toalCount - count : 0;
	}

	// 判断当前日期的周一的日期
	public String convertWeekByDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// LOGGER.info("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		// LOGGER.info("所在周星期一的日期:" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		// LOGGER.info("所在周星期日的日期:" + imptimeEnd);
		return imptimeBegin;
	}

	// 根据概率获取奖项
	private Integer getRand(List<LotterRule> list) {
		int randomNum = new Random().nextInt(10000);
		int pro = 0;
		int level = 0;
		for (; level < list.size(); level++) {
			pro += list.get(level).getProbability();
			if (randomNum < pro)
				break;

		}
		return level;
	}

	private void savePracticalityRecord(LotterRule rule, WeixinActivityEntity activityEntity,
			WeixinMemberEntity memberEntity, int level) {
		WeixinPracticalityRecordEntity entity = new WeixinPracticalityRecordEntity();
		entity.setAccountid(activityEntity.getAccountid());
		entity.setAddtime(new Date());
		entity.setHdid(activityEntity.getId());
		entity.setMobile(memberEntity.getPhoneNumber());
		entity.setNickname(memberEntity.getNickName());
		entity.setPrizevalue(rule.getType() == 1 ? "流量 " + rule.getValue() + "M" : rule.getValue().split("/")[0]);
		entity.setPrizelevel(rule.getName());
		entity.setLevel(level);
		entity.setType(rule.getType());
		entity.setIsSend(rule.getType() == 2 ? 1 : 2);
		entity.setOpenid(memberEntity.getOpenId());
		entity.setSendTime(rule.getType() == 2 ?  null :new Date());
		systemService.save(entity);
	}

	@RequestMapping(params = "subQuestionAnswer",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson suQuestionAnswer(HttpServletRequest request) {

		StringBuffer sb = new StringBuffer();
		long start = System.currentTimeMillis();
		sb.append("ActivityController_playQuestionGame");
		AjaxJson returnJson = new AjaxJson();
		String answer = request.getParameter("answer");
		String activityId = request.getParameter("activityId");
		String openId = request.getParameter("openId");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtil.isEmpty(activityId) || StringUtil.isEmpty(activityId) || StringUtil.isEmpty(answer)) {
			params.put("error", "invalid");
			params.put("msg", "错误的请求！");
			returnJson.setSuccess(false);
			returnJson.setAttributes(params);
			RedisUtil.del(openId + activityId);
			return returnJson;
		}
		try {
			String isproce = RedisUtil.getRedis(openId + activityId);
			if (StringUtil.isNotEmpty(isproce)) {
				params.put("error", "invalid");
				params.put("msg", "您已经提交答案正在处理！");
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				return returnJson;
			}
			sb.append(" user:[").append(openId).append("],activtyId:[").append(activityId).append("],answer:[")
					.append(answer).append("]");
			RedisUtil.setRedis(openId + activityId, 20, "1");
			WeixinActivityEntity activityEntity = systemService.getEntity(WeixinActivityEntity.class, activityId);
			WeixinMemberEntity weixinMember = weixinMemberService.getWeixinMember(openId,
					activityEntity.getAccountid());
			if (activityEntity == null || weixinMember == null) {
				params.put("error", "invalid");
				params.put("msg", "错误的请求！");
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				RedisUtil.del(openId + activityId);
				return returnJson;
			}
			Date now = new Date();
			if (now.before(activityEntity.getStartTime())) {
				params.put("error", "invalid");
				params.put("msg", "活动还未开始！");
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				return returnJson;
			}
			if (now.after(activityEntity.getEndTime())) {
				params.put("error", "invalid");
				params.put("msg", "活动已结束！");
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				return returnJson;
			}
			QuestionRule rule = JSON.parseObject(activityEntity.getActivityRule(), QuestionRule.class);
			int count = surplusGameTimes(activityEntity, openId);
			params.put("count", count);
			if (count <= 0) {
				// 已经超过总抽奖数
				params.put("error", "invalid");
				params.put("msg", "您本次活动的答题次数已用完，敬请关注下次活动！");
				params.put("total", count);
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				RedisUtil.del(openId + activityId);
				return returnJson;
			}
			List<WeixinActivityQuestionEntity> entities = weixinActivityService.getQuestions(activityId);
			Map<Object, Object> aswerMap = JsonUtil.parseJSON2Map(answer);
			int rightNumber = 0;
			String rigthQueNo ="";
			for (WeixinActivityQuestionEntity activityQuestionEntity : entities) {
				// 因为是选择题 没有进行 大小写忽略
				if (activityQuestionEntity.getAnswer().equals(aswerMap.get(activityQuestionEntity.getId()))){
					rightNumber++;
					rigthQueNo += " " + activityQuestionEntity.getSerial();
				}
			}
			int flow = rightNumber * rule.getOneFlow();
			int level = weixinActivityService.getLevel(activityEntity.getFrequency(), activityEntity.getStartTime());
			String freString = "下次";
			switch (activityEntity.getFrequency()) {
			case 1:
				freString = "明天";
				break;
			case 2:
				freString = "下周";
				break;
			case 3:
				freString = "下月";
				break;
			default:
				break;
			}

			// 判断中奖人数
			if (rightNumber == entities.size()) {
				// 先判断是否领过
				if (isWin(openId, activityId,"答对"+entities.size()+"题",null) && flow == 0) {
					params.put("error", "invalid");
					params.put("msg", "本次活动你已经获得奖励，欢迎关注下次活动！");
					returnJson.setSuccess(false);
					returnJson.setAttributes(params);
					RedisUtil.del(openId + activityId);
					return returnJson;
				}

				if (surplusPrize(activityId, level, "答对"+entities.size()+"题",rule.getEvenNumber()) <= 0 && flow == 0) {
					params.put("error", "invalid");
					params.put("msg", "本次活动的名额已送完 ，" + freString + "再来！");
					returnJson.setSuccess(false);
					returnJson.setAttributes(params);
					RedisUtil.del(openId + activityId);
					return returnJson;
				}
				flow += rule.getAllFlow();
			}
			// 游戏记录表
			WeixinGameDetailEntity detail = new WeixinGameDetailEntity();
			detail.setAccountid(activityEntity.getAccountid());
			detail.setAddtime(new Date());
			detail.setHdid(activityId);
			detail.setScore(entities.size());
			detail.setNickname(weixinMember.getNickName());
			detail.setOpendid(openId);
			detail.setMsg("答对"+rigthQueNo+"题");
			this.systemService.save(detail);
			// 保存中奖记录
			if (flow > 0) {
				String url1 = path
						+ "userGetFlow/getCoverAndLocation";
				Gson gson1 = new Gson();
				JSONObject myJson1 = new JSONObject();
				myJson1.accumulate("phoneNumber", weixinMember.getPhoneNumber());
				myJson1.accumulate("id", weixinMember.getAccountId());
				JSONObject jsonObject1 = HttpUtil.httpPost(url1, myJson1, false);
				String strFlow1 = gson1.toJson(jsonObject1);
				Type type1 = new TypeToken<Update>() {
				}.getType();
				Update update1 = gson1.fromJson(strFlow1, type1);
				if (update1 == null) {
					logger.info("验证手机号 覆盖失败  未连接上 jfinal");
					params.put("error", "invalid");
					params.put("msg", "意外的错误！");
					returnJson.setSuccess(false);
					returnJson.setAttributes(params);
					return returnJson;
				}
				if (update1.getCode().equals("201")) {
					params.put("error", "invalid");
					params.put("msg", "手机号不在商户覆盖区域内，不予赠送流量");
					returnJson.setSuccess(false);
					returnJson.setAttributes(params);
					return returnJson;
				}
				String recordId = savePracticalityRecord(flow, level, rightNumber, activityEntity, weixinMember);
				String url = path
						+ "userGetFlow/UpdateFlowAndAddFlowRecord";
				Gson gson = new Gson();
				JSONObject myJson = new JSONObject();
				myJson.accumulate("phoneNumber", weixinMember.getPhoneNumber());
				myJson.accumulate("flowValue", flow);
				myJson.accumulate("id", activityEntity.getAccountid());
				myJson.accumulate("opreateType", "答题");
				myJson.accumulate("openid", openId);
				myJson.accumulate("flowType", activityEntity.getFlowType()); // 省内：省内流量；全国：全国流量
				myJson.element("nickName", weixinMember.getNickName());
				JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
				String strFlow = gson.toJson(contentFlow);
				Type type = new TypeToken<Update>() {
				}.getType();
				// 是否中奖参数
				params.put("continueFlag", true);
				Update update = gson.fromJson(strFlow, type);
				sb.append(
						"jfinal：" + myJson.toString() + "url:" + url + "---hdid：" + activityId + "---openId:" + openId);
				if (StringUtils.isNotBlank(weixinMember.getPhoneNumber())) {
					if (!"200".equals(update.getCode()) && !"10026".equals(update.getCode())) {
						systemService.deleteEntityById(WeixinPracticalityRecordEntity.class, recordId);
						params.put("msg", "流量更新失败！");
						params.put("error", "invalid");
						returnJson.setSuccess(false);
						returnJson.setAttributes(params);
						RedisUtil.del(openId + activityId);
						return returnJson;
					}
				}
				sb.append("jfinal-result:" + update);
			}
			params.put("flow", flow);
			params.put("right", rightNumber);
			returnJson.setSuccess(false);
			returnJson.setAttributes(params);
			RedisUtil.del(openId + activityId);
			return returnJson;
		} catch (Exception e) {
			logger.error("玩游戏异常", e);
			params.put("error", "invalid");
			params.put("msg", "意外的错误！");
			returnJson.setSuccess(false);
			returnJson.setAttributes(params);
			return returnJson;
		} finally {
			Long end = System.currentTimeMillis();
			sb.append("_time:" + (end - start));
			logger.info(sb.toString());
		}
	}
	@RequestMapping(params = "subGameRecord",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson subPlayGameRecord(HttpServletRequest request){
		//TODO 游戏规则
		// 控制 每周期内只有一次 得分奖励
		// 名次奖励
		StringBuffer sb = new StringBuffer();
		long start = System.currentTimeMillis();
		sb.append("ActivityController_playQuestionGame");
		AjaxJson returnJson = new AjaxJson();
		String scoreString = request.getParameter("score");
		String activityId = request.getParameter("activityId");
		String openId = request.getParameter("openId");
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtil.isEmpty(activityId) || StringUtil.isEmpty(activityId) || StringUtil.isEmpty(scoreString)) {
			params.put("error", "invalid");
			params.put("msg", "错误的请求！");
			returnJson.setSuccess(false);
			returnJson.setAttributes(params);
			RedisUtil.del(openId + activityId);
			return returnJson;
		}
		int score = Integer.valueOf(scoreString);
		try {
			String isproce = RedisUtil.getRedis(openId + activityId);
			if (StringUtil.isNotEmpty(isproce)) {
				params.put("error", "invalid");
				params.put("msg", "您已经提交答案正在处理！");
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				return returnJson;
			}
			sb.append(" user:[").append(openId).append("],activtyId:[").append(activityId).append("],score:[")
					.append(score).append("]");
			RedisUtil.setRedis(openId + activityId, 10, "1");
			WeixinActivityEntity activityEntity = systemService.getEntity(WeixinActivityEntity.class, activityId);
			WeixinMemberEntity weixinMember = weixinMemberService.getWeixinMember(openId,
					activityEntity.getAccountid());
			if (activityEntity == null || weixinMember == null) {
				params.put("error", "invalid");
				params.put("msg", "错误的请求！");
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				RedisUtil.del(openId + activityId);
				return returnJson;
			}
			Date now = new Date();
			if (now.before(activityEntity.getStartTime())) {
				params.put("error", "invalid");
				params.put("msg", "活动还未开始！");
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				return returnJson;
			}
			if (now.after(activityEntity.getEndTime())) {
				params.put("error", "invalid");
				params.put("msg", "活动已结束！");
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				return returnJson;
			}
			//判断吃月饼异常记录
			if(activityEntity.getType() == 201 && score > 150 ){
				WeixinGameDetailEntity detail = new WeixinGameDetailEntity();
				detail.setAccountid(activityEntity.getAccountid());
				detail.setAddtime(new Date());
				detail.setHdid(activityId);
				detail.setScore(-1);
				detail.setNickname(weixinMember.getNickName());
				detail.setOpendid(openId);
				detail.setMsg("异常操作");
				this.systemService.save(detail);
				params.put("error", "invalid");
				params.put("msg", "检测你的游戏记录异常，多次发现 将对账户进行冻结！");
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				RedisUtil.del(openId + activityId);
				return returnJson;
			}
			//增加MD5校验（暂时是六角拼拼）
			if(activityEntity.getType() > 201  ){
				String deCode =  request.getParameter("deCode");
				if(StringUtil.isEmpty(deCode)||!deCode.toUpperCase().equals(MD5Utils.md5(scoreString+openId))){
					WeixinGameDetailEntity detail = new WeixinGameDetailEntity();
					detail.setAccountid(activityEntity.getAccountid());
					detail.setAddtime(new Date());
					detail.setHdid(activityId);
					detail.setScore(-1);
					detail.setNickname(weixinMember.getNickName());
					detail.setOpendid(openId);
					detail.setMsg("非正常请求");
					this.systemService.save(detail);
					params.put("error", "invalid");
					params.put("msg", "非正常游戏 ，下次发现将进行流量清零处理！");
					returnJson.setSuccess(false);
					returnJson.setAttributes(params);
					RedisUtil.del(openId + activityId);
					return returnJson;
				}
				
			}
			Map<Object, Object> rule = JsonUtil.parseJSON2Map(activityEntity.getActivityRule());
			// 获取游戏规则
			
			int count = surplusGameTimes(activityEntity, openId);
			params.put("count", count);
			if (count <= 0) {
				// 已经超过总抽奖数
				params.put("error", "invalid");
				params.put("msg", "您本次活动的游戏次数已用完，敬请关注下次活动！");
				params.put("total", count);
				returnJson.setSuccess(false);
				returnJson.setAttributes(params);
				RedisUtil.del(openId + activityId);
				return returnJson;
			}
			@SuppressWarnings("unchecked")
			Map<Object, Object> gradeRule = (Map<Object, Object>)rule.get("gradeRule");
			Integer evenFrequency = (Integer)rule.get("evenFrequency");
			int flow = 0;
			if(gradeRule != null && !gradeRule.isEmpty()){
				 List<Integer> garde = new ArrayList<Integer>();
				 for(Object key : gradeRule.keySet()){
					 garde.add(Integer.valueOf(key.toString()));
				 }
				 Collections.sort(garde);
				 for(int i = garde.size() - 1 ; i >= 0 ;i--){
					 if(score >= garde.get(i)){
						 flow = Integer.valueOf(gradeRule.get(garde.get(i)+"").toString());
						 break;
					 }
				 }
				 params.put("msg", "得分太低没有获得分数奖励吗 ，别气馁 继续加油");
			}else{
				params.put("msg", "您的分数已记录本次排名，继续冲刺啊");
			}
			int level = weixinActivityService.getLevel(activityEntity.getFrequency(), activityEntity.getStartTime());
			String freString = "本次";
			switch (activityEntity.getFrequency()) {
			case 1:
				freString = "今天";
				break;
			case 2:
				freString = "本周";
				break;
			case 3:
				freString = "本月";
				break;
			default:
				break;
			}
			// 游戏记录表
						WeixinGameDetailEntity detail = new WeixinGameDetailEntity();
						detail.setAccountid(activityEntity.getAccountid());
						detail.setAddtime(new Date());
						detail.setHdid(activityId);
						detail.setScore(score);
						detail.setNickname(weixinMember.getNickName());
						detail.setOpendid(openId);
						detail.setMsg(weixinActivityService.getOpreateType(activityEntity.getType()));
						this.systemService.save(detail);
			if(flow > 0){
				
			// 判断中奖人数
				if (isWin(openId, activityId,null,level) ) {
					params.put("error", "noflow");
					params.put("msg", freString+"您已经获得分数奖励，您还可继续冲刺排名奖励哦！");
					returnJson.setSuccess(false);
					returnJson.setAttributes(params);
					RedisUtil.del(openId + activityId);
					return returnJson;
				}

				if (surplusPrize(activityId, level, null,evenFrequency) <= 0 ) {
					params.put("error", "noflow");
					params.put("msg", freString+"的分数获奖名额已送完 ， 您还可继续冲刺排名奖励哦！");
					returnJson.setSuccess(false);
					returnJson.setAttributes(params);
					RedisUtil.del(openId + activityId);
					return returnJson;
				}
				String url1 = path
						+ "userGetFlow/getCoverAndLocation";
				Gson gson1 = new Gson();
				JSONObject myJson1 = new JSONObject();
				myJson1.accumulate("phoneNumber", weixinMember.getPhoneNumber());
				myJson1.accumulate("id", weixinMember.getAccountId());
				JSONObject jsonObject1 = HttpUtil.httpPost(url1, myJson1, false);
				String strFlow1 = gson1.toJson(jsonObject1);
				Type type1 = new TypeToken<Update>() {
				}.getType();
				Update update1 = gson1.fromJson(strFlow1, type1);
				if (update1 == null) {
					logger.info("验证手机号 覆盖失败  未连接上 jfinal");
					params.put("error", "invalid");
					params.put("msg", "意外的错误！");
					returnJson.setSuccess(false);
					returnJson.setAttributes(params);
					return returnJson;
				}
				if (update1.getCode().equals("201")) {
					params.put("error", "invalid");
					params.put("msg", "手机号不在商户覆盖区域内，不予赠送流量");
					returnJson.setSuccess(false);
					returnJson.setAttributes(params);
					return returnJson;
				}
			// 保存中奖记录
				String recordId = savePracticalityRecord(flow, level, score, activityEntity, weixinMember);
				String url = path
						+ "userGetFlow/UpdateFlowAndAddFlowRecord";
				Gson gson = new Gson();
				JSONObject myJson = new JSONObject();
				myJson.accumulate("phoneNumber", weixinMember.getPhoneNumber());
				myJson.accumulate("flowValue", flow);
				myJson.accumulate("id", activityEntity.getAccountid());
				myJson.accumulate("opreateType", weixinActivityService.getOpreateType(activityEntity.getType()));
				myJson.accumulate("openid", openId);
				myJson.accumulate("flowType", activityEntity.getFlowType()); // 省内：省内流量；全国：全国流量
				myJson.element("nickName", weixinMember.getNickName());
				JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
				String strFlow = gson.toJson(contentFlow);
				Type type = new TypeToken<Update>() {
				}.getType();
				// 是否中奖参数
				params.put("continueFlag", true);
				Update update = gson.fromJson(strFlow, type);
				sb.append(
						"jfinal：" + myJson.toString() + "url:" + url + "---hdid：" + activityId + "---openId:" + openId);
				if (StringUtils.isNotBlank(weixinMember.getPhoneNumber())) {
					if (!"200".equals(update.getCode()) && !"10026".equals(update.getCode())) {
						weixinActivityService.executeSql("update WEIXIN_PRAC_RECORD set is_send =  1 where id = ?", recordId);
						params.put("msg", "流量更新失败！");
						params.put("error", "invalid");
						returnJson.setSuccess(false);
						returnJson.setAttributes(params);
						RedisUtil.del(openId + activityId);
						return returnJson;
					}
				}
				sb.append("jfinal-result:" + update);
			}
			params.put("flow", flow);
			if(flow <= 0  ) {
				params.put("error", "noflow");
			}
			returnJson.setSuccess(false);
			returnJson.setAttributes(params);
			RedisUtil.del(openId + activityId);
			return returnJson;
		} catch (Exception e) {
			logger.error("玩游戏异常", e);
			params.put("error", "invalid");
			params.put("msg", "意外的错误！");
			returnJson.setSuccess(false);
			returnJson.setAttributes(params);
			return returnJson;
		} finally {
			Long end = System.currentTimeMillis();
			sb.append("_time:" + (end - start));
			logger.info(sb.toString());
		}
	}
	
	
	

	/**
	 * 保存中奖记录
	 * 
	 * @param flow
	 * @param level
	 * @param rightNumber
	 * @param activityEntity
	 * @param memberEntity
	 * @return
	 */
	private String savePracticalityRecord(int flow, int level, int rightNumber, WeixinActivityEntity activityEntity,
			WeixinMemberEntity memberEntity) {
		WeixinPracticalityRecordEntity entity = new WeixinPracticalityRecordEntity();
		entity.setAccountid(activityEntity.getAccountid());
		entity.setAddtime(new Date());
		entity.setHdid(activityEntity.getId());
		entity.setMobile(memberEntity.getPhoneNumber());
		entity.setNickname(memberEntity.getNickName());
		entity.setPrizevalue("流量 " + flow + "M");
		if(100 < activityEntity.getType() && activityEntity.getType()< 200)
			entity.setPrizelevel("答对" + rightNumber + "题");
		else if(200 < activityEntity.getType() && activityEntity.getType()< 300)
			entity.setPrizelevel("获得" + rightNumber + "分");
		entity.setLevel(level);
		entity.setType(1);
		entity.setIsSend(2);
		entity.setOpenid(memberEntity.getOpenId());
		entity.setSendTime(new Date());
		return (String) systemService.save(entity);
	}

	/**
	 * 判断是否中奖过
	 * 
	 * @param openid
	 * @param activityId
	 * @return
	 */
	private boolean isWin(String openid, String activityId ,String prizelevel,Integer level) {
		String sql = "SELECT COUNT(*) count FROM weixin_prac_record t where openid='" + openid + "' and hdid = '"
				+ activityId + "'";
		if(!StringUtil.isEmpty(prizelevel))
			sql +=	" and PRIZELEVEL ='"+prizelevel+"'";
		if(level != null)
			sql +=	" and LEVEL ="+level;
		int count = winningrecordlxcService.getCount(sql);
		return count > 0;
	}
	
	public String getLastTime(int fre){
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dm = new SimpleDateFormat("yyyy-MM");
		switch (fre) {
		case 1:
			return d.format(new java.util.Date());
		case 2:
			return convertWeekByDate();
		case 3:
			return dm.format(new Date())+"-01";
		default:
			return "2015-01-01";
		}
	}
	
}
