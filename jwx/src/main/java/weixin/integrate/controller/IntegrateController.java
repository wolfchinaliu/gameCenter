package weixin.integrate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;
import sdk.jfinal.JFinalUtils;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.integrate.entity.UserFlowAccountEntity;
import weixin.integrate.entity.WxIntegrateBusinessEntity;
import weixin.integrate.service.IntegrateService;
import weixin.integrate.util.IntegrateUtils;
import weixin.integrate.util.WxIntegrateConstant;
import weixin.liuliangbao.business.entity.FamilyNumberEntity;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.UserFlowAccountBean;
import weixin.liuliangbao.jsonbean.ViewBean.BusinessCommonJsonBean;
import weixin.liuliangbao.jsonbean.ViewBean.PhoneLocationBean;
import weixin.liuliangbao.jsonbean.ViewBean.UserChargedFlowRecordsBean;
import weixin.liuliangbao.jsonbean.ViewBean.UserFlowGiveBean;
import weixin.liuliangbao.util.DESUtil;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.safetyRules.entity.SafetyRulesEntity;
import weixin.safetyRules.service.SafetyRulesServiceI;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.util.CommonUtils;
import weixin.util.DataDictionaryUtil.FlowType;

/**
 * Created by dyt
 */

@Controller
@RequestMapping("integrate")
public class IntegrateController extends BaseController {
	private static final Logger logger = Logger.getLogger(IntegrateController.class);
	@Autowired
	private CommonService commonService;
	@Autowired
	private IntegrateService integrateService;
	@Autowired
	private WeixinAccountServiceI accountService;
	@Autowired
	private SafetyRulesServiceI ruleService;
	@Autowired
	private WeixinMemberServiceI weixinMemberService;

	@RequestMapping(params = "goreceive")
	public ModelAndView goreceive() {
		ModelAndView view = new ModelAndView("integrate/receivepage");
		return view;
	}

	@RequestMapping(params = "receive")
	@ResponseBody
	public JSONObject receive(HttpServletRequest request) throws Exception {
		String acctId = request.getParameter("acctId");
		String openId = request.getParameter("openId");
		String secret = request.getParameter("secret");
		String activityId = request.getParameter("activityId");
		String flowType = request.getParameter("flowType");
		String flowValue = request.getParameter("flowValue");
		JSONObject data = new JSONObject();
		data.put("acctId", acctId);
		data.put("openId", openId);
		data.put("activityId", activityId);
		data.put("flowType", flowType);
		data.put("flowValue", flowValue);
		logger.info(data.toString());
		String userData = DESUtil.toHexString(DESUtil.encrypt(data.toString(), secret)).toUpperCase();
		String path = ResourceUtil.getConfigByName("jwxUrl-config");
		String url = path + "integrate.do?wx_userReceive_getBusinessKey";
		JSONObject myJson = new JSONObject();
		myJson.accumulate("data", userData);
		myJson.accumulate("acctId", acctId);
		JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
		return contentFlow;
	}

	@RequestMapping(params = "gotest")
	public ModelAndView gotest() {
		ModelAndView view = new ModelAndView("integrate/testpage");
		return view;
	}

	@RequestMapping(params = "test")
	@ResponseBody
	public JSONObject test(HttpServletRequest request) throws Exception {
		String acctId = request.getParameter("acctId");
		String openId = request.getParameter("openId");
		String secret = request.getParameter("secret");
		String activityId = request.getParameter("activityId");
		String flowType = request.getParameter("flowType");
		String flowValue = request.getParameter("flowValue");
		String interfaceName = request.getParameter("interfaceName");
		String phoneNumber = request.getParameter("phoneNumber");
		JSONObject data = new JSONObject();
		data.put("acctId", acctId);
		data.put("openId", openId);
		data.put("activityId", activityId);
		data.put("flowType", flowType);
		data.put("flowValue", flowValue);
		data.put("phoneNumber", phoneNumber);
		logger.info(data.toString());
		String userData = DESUtil.toHexString(DESUtil.encrypt(data.toString(), secret)).toUpperCase();
		String path = ResourceUtil.getConfigByName("jwxUrl-config");
		String url = path + "integrate.do?" + interfaceName;
		JSONObject myJson = new JSONObject();
		myJson.accumulate("data", userData);
		myJson.accumulate("acctId", acctId);
		JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
		String u = null;
		switch (interfaceName) {
		case "wx_userReceive_getBusinessKey":
			u = "wx_userReceive_receive";
			break;
		case "wx_userPay_getBusinessKey":
			u = "wx_userPay_pay";
			break;
		case "app_userReceive_getBusinessKey":
			u = "app_userReceive_receive";
			break;
		case "app_userPay_getBusinessKey":
			u = "app_userPay_pay";
			break;
		default:
			u = "";
			break;
		}
		contentFlow.put("u", u);
		return contentFlow;
	}

	@RequestMapping(params = "wx_userReceive_getBusinessKey", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject wx_userReceive_getBusinessKey(@RequestBody JSONObject json, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		String userData = MapUtils.getString(json, "data");
		String acctId = MapUtils.getString(json, "acctId");
		String clientIp = IpUtil.getIpAddr(request);
		StringBuilder builder = new StringBuilder();
		builder.append("wx_userReceive_getBusinessKey--clientip:");
		builder.append(clientIp);
		builder.append("-密文:" + userData);
		boolean isAllow = this.integrateService.IsAllowAccess(acctId, clientIp);
		if (!isAllow) {
			result.put(WxIntegrateConstant.resultcode, "101");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("101"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		WeixinAcctEntity acctEntity = this.integrateService.getWeixinAcct(acctId);
		if (acctEntity == null) {
			result.put(WxIntegrateConstant.resultcode, "103");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("103"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		String secret = this.integrateService.getCustomerSecret(acctId);
		if (StringUtils.isEmpty(secret)) {
			result.put(WxIntegrateConstant.resultcode, "102");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("102"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		JSONObject integrate = null;
		String decData = null;
		try {
			decData = java.net.URLDecoder.decode(DESUtil.decrypt(userData, secret), "utf-8");
			integrate = JSONObject.fromObject(decData);
		} catch (Exception e) {
			logger.error("wx_userReceive_getBusinessKey-acctId:" + acctId + "-解密过程出错", e);
			result.put(WxIntegrateConstant.resultcode, "102");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("102"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		String activityId = MapUtils.getString(integrate, "activityId");
		if (StringUtils.isBlank(activityId)) {
			result.put(WxIntegrateConstant.resultcode, "104");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("104"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		if (!StringUtils.equals(acctId, MapUtils.getString(integrate, "acctId"))) {
			result.put(WxIntegrateConstant.resultcode, "103");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("103"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		String flowType = MapUtils.getString(integrate, "flowType");
		String flowValueStr = MapUtils.getString(integrate, "flowValue");
		double flowValue = NumberUtils.toDouble(flowValueStr, 0);
		if (!WxIntegrateConstant.flowtype_national.equals(IntegrateUtils.toFlowTypeName(flowType))
				&& !WxIntegrateConstant.flowtype_provincial.equals(IntegrateUtils.toFlowTypeName(flowType))) {
			result.put(WxIntegrateConstant.resultcode, "105");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("105"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		if (flowValue <= 0) {
			result.put(WxIntegrateConstant.resultcode, "106");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("106"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		boolean isSufficient = this.integrateService.isAcctFlowSufficient(acctId, flowType, flowValue);
		if (!isSufficient) {
			result.put(WxIntegrateConstant.resultcode, "201");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("201"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		boolean isComplyRules = this.ruleService.isComplyRules(acctId, activityId, new Date(), flowValue);
		if (!isComplyRules) {
			result.put(WxIntegrateConstant.resultcode, "202");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("202"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		WeixinAccountEntity accountEntity = this.integrateService.getWeixinAccount(acctId);
		if (accountEntity == null) {
			result.put(WxIntegrateConstant.resultcode, "203");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("203"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		String openId = MapUtils.getString(integrate, "openId");
		WeixinMemberEntity memberEntity = this.weixinMemberService.getWeixinMember(openId, accountEntity.getId());
		if (memberEntity == null) {
			result.put(WxIntegrateConstant.resultcode, "204");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("204"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		WxIntegrateBusinessEntity integrateEntity = this.integrateService.saveIntegrateBusiness(acctId,
				WxIntegrateConstant.businesstype_wxuserreceive, integrate);
		this.ruleService.reduceNum(acctId, activityId, new Date());
		result.put(WxIntegrateConstant.businesskey, integrateEntity.getId());
		result.put(WxIntegrateConstant.resultcode, "0");
		result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("0"));
		builder.append("-return:" + result.toString());
		logger.info(builder);
		return result;
	}

	@RequestMapping(params = "wx_userReceive_receive")
	public ModelAndView wx_userReceive_receive(@RequestParam("businessKey") String businessKey, HttpSession session) {
		StringBuilder builder = new StringBuilder();
		builder.append("wx_userReceive_receive-businessKey:" + businessKey);
		ModelAndView view = null;
		WxIntegrateBusinessEntity entity = this.integrateService.getIntegrateBusiness(businessKey);
		if (entity == null) {
			logger.error("businessKey error:" + businessKey);
			builder.append("-businessKey error:" + businessKey);
			logger.info(builder);
			view = new ModelAndView("integrate/error");
			return view;
		} else {
			if (!WxIntegrateConstant.businesstype_wxuserreceive.equals(entity.getBusinessType())) {
				logger.error("wx_userReceive_receive-businessType error-businessKey:" + businessKey + "-businessType:"
						+ entity.getBusinessType());
				builder.append("-businessType error");
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				return view;
			}
			if (!WxIntegrateConstant.status_getbusinesskey.equals(entity.getStatus())) {
				logger.error("wx_userReceive_receive-status error-businessKey:" + businessKey + "-status:"
						+ entity.getStatus());
				builder.append("-status error");
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				return view;
			}
		}
		// session 保存 businessKey
		session.setAttribute(WxIntegrateConstant.session_businessKey, businessKey);
		String openId = entity.getOpenId();
		String acctId = entity.getAcctId();
		WeixinAccountEntity account = this.accountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId",
				acctId);
		String bindingPhone = this.integrateService.getBindingPhone(openId, account.getId());
		String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
		String impUrl = account.getQRcode();
		String urll = urlprefix + "/" + impUrl;
		SafetyRulesEntity rule = this.ruleService.getSafetyRuleById(entity.getActivityId());
		if (StringUtils.isBlank(bindingPhone)) {
			view = new ModelAndView("integrate/verifyphone_binding");

			view.addObject("businessKey", businessKey);
			view.addObject("accountHeadImg", urll);
			view.addObject("accountName", account.getAccountname());
			view.addObject("activityName", rule.getRuleName());
			view.addObject("flowValue", entity.getFlowValue());
			view.addObject("flowType", entity.getFlowType());
			view.addObject("operate", "wx_userReceive_receive");
			builder.append("-view:integrate/verifyphone_binding");
			logger.info(builder);
			return view;
		} else {
			this.integrateService.receiveSuccess(businessKey, rule);
			view = new ModelAndView("integrate/recevicesuccess");
			view.addObject("flowType", entity.getFlowType());
			view.addObject("flowValue", entity.getFlowValue());
			view.addObject("accountHeadImg", urll);
			view.addObject("accountName", account.getAccountname());
			view.addObject("openid", openId);
			view.addObject("acctid", account.getId());
			builder.append("-view:integrate/recevicesuccess");
			logger.info(builder);
			return view;
		}
	}

	@RequestMapping(params = "wx_bindingPhone", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject wx_bindingPhone(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		String businessKey = (String) request.getSession().getAttribute(WxIntegrateConstant.session_businessKey);
		String phoneNumber = request.getParameter("phoneNumber");
		String captcha = request.getParameter("captcha");
		WxIntegrateBusinessEntity entity = this.integrateService.getIntegrateBusiness(businessKey);
		if (entity == null || !entity.getStatus().equals(WxIntegrateConstant.status_getbusinesskey)) {
			result.put("flag", false);
			result.put("msg", "业务码非法或超时，请重新开始业务");
			return result;
		}
		String openId = entity.getOpenId();
		String acctId = entity.getAcctId();
		WeixinAccountEntity account = this.accountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId",
				acctId);
		String bindingPhone = this.integrateService.getBindingPhone(openId, account.getId());
		if (!StringUtils.isBlank(bindingPhone)) {
			if (bindingPhone.equals(phoneNumber)) {
				result.put("flag", true);
				result.put("code", 3);
				result.put("msg", "");
				return result;
			} else {
				result.put("flag", false);
				result.put("code", 4);
				result.put("msg", "绑定失败，此账号已经绑定其他手机号！");
				return result;
			}
		} else {
			HttpSession session = request.getSession();
			if (session.getAttribute("code") == null) {
				result.put("flag", false);
				result.put("code", 2);
				result.put("msg", "验证码过期，请重新发送后验证！");
				return result;
			} else {
				String phone_session = (String) session.getAttribute("phoneNumber");
				String captcha_session = (String) session.getAttribute("code");

				if (!(captcha.equals(captcha_session) && phoneNumber.equals(phone_session))) {
					result.put("flag", false);
					result.put("code", 1);
					result.put("msg", "验证码不正确，请重新输入");
					return result;
				} else {
					// 手动过期验证码
					session.removeAttribute("code");
					this.integrateService.bindingPhone(openId, account.getId(), phoneNumber);
					result.put("flag", true);
					result.put("msg", "绑定成功");
					return result;
				}
			}
		}
	}

	@RequestMapping(params = "wx_userPay_getBusinessKey")
	@ResponseBody
	public JSONObject wx_userPay_getBusinessKey(@RequestBody JSONObject json, HttpServletRequest request) {
		// json flowValue,flowType,acctId,secret,openId,
		JSONObject result = new JSONObject();
		String userData = MapUtils.getString(json, "data");
		String acctId = MapUtils.getString(json, "acctId");
		String clientIp = IpUtil.getIpAddr(request);
		StringBuilder builder = new StringBuilder();
		builder.append("wx_userPay_getBusinessKey--clientip:");
		builder.append(clientIp);
		builder.append("-密文:" + userData);
		boolean isAllow = this.integrateService.IsAllowAccess(acctId, clientIp);
		if (!isAllow) {
			result.put(WxIntegrateConstant.resultcode, "101");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("101"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		WeixinAcctEntity acctEntity = this.integrateService.getWeixinAcct(acctId);
		if (acctEntity == null) {
			result.put(WxIntegrateConstant.resultcode, "103");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("103"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		String secret = this.integrateService.getCustomerSecret(acctId);
		if (StringUtils.isEmpty(secret)) {
			result.put(WxIntegrateConstant.resultcode, "102");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("102"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		// 用secret 解密数据
		JSONObject integrate = null;
		String decData = null;
		try {
			decData = java.net.URLDecoder.decode(DESUtil.decrypt(userData, secret), "utf-8");
			integrate = JSONObject.fromObject(decData);
		} catch (Exception e) {
			logger.error("wx_userPay_getBusinessKey-acctId:" + acctId + "-解密过程出错", e);
			result.put(WxIntegrateConstant.resultcode, "102");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("102"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		if (!StringUtils.equals(acctId, MapUtils.getString(integrate, "acctId"))) {
			result.put(WxIntegrateConstant.resultcode, "103");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("103"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		String flowType = MapUtils.getString(integrate, "flowType");
		String flowValueStr = MapUtils.getString(integrate, "flowValue");
		double flowValue = NumberUtils.toDouble(flowValueStr, 0);
		if (!WxIntegrateConstant.flowtype_national.equals(IntegrateUtils.toFlowTypeName(flowType))
				&& !WxIntegrateConstant.flowtype_provincial.equals(IntegrateUtils.toFlowTypeName(flowType))) {
			result.put(WxIntegrateConstant.resultcode, "105");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("105"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		if (flowValue <= 0) {
			result.put(WxIntegrateConstant.resultcode, "106");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("106"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		WeixinAccountEntity accountEntity = this.integrateService.getWeixinAccount(acctId);
		if (accountEntity == null) {
			result.put(WxIntegrateConstant.resultcode, "203");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("203"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		String openId = MapUtils.getString(integrate, "openId");
		WeixinMemberEntity memberEntity = this.weixinMemberService.getWeixinMember(openId, accountEntity.getId());
		if (memberEntity == null) {
			result.put(WxIntegrateConstant.resultcode, "204");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("204"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		WxIntegrateBusinessEntity integrateEntity = this.integrateService.saveIntegrateBusiness(acctId,
				WxIntegrateConstant.businesstype_wxuserpay, integrate);
		result.put(WxIntegrateConstant.businesskey, integrateEntity.getId());
		result.put(WxIntegrateConstant.resultcode, "0");
		result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("0"));
		builder.append("-return:" + result.toString());
		logger.info(builder);
		return result;
	}

	@RequestMapping(params = "wx_userPay_pay")
	public ModelAndView wx_userPay_pay(@RequestParam("businessKey") String businessKey, HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append("wx_userPay_pay-businessKey:" + businessKey);
		ModelAndView view = null;
		request.getSession().setAttribute(WxIntegrateConstant.session_businessKey, businessKey);
		WxIntegrateBusinessEntity entity = this.integrateService.getIntegrateBusiness(businessKey);
		if (entity == null) {
			logger.error("businessKey error:" + businessKey);
			builder.append("-businessKey error:" + businessKey);
			logger.info(builder);
			view = new ModelAndView("integrate/error");
			return view;
		} else {
			if (!WxIntegrateConstant.businesstype_wxuserpay.equals(entity.getBusinessType())) {
				logger.error("wx_userPay_pay-businessType error-businessKey:" + businessKey + "-businessType:"
						+ entity.getBusinessType());
				builder.append("-businessType error");
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				return view;
			}
			if (!WxIntegrateConstant.status_getbusinesskey.equals(entity.getStatus())) {
				logger.error(
						"wx_userPay_pay-status error-businessKey:" + businessKey + "-status:" + entity.getStatus());
				builder.append("-status error");
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				return view;
			}
		}
		String openId = entity.getOpenId();
		String acctId = entity.getAcctId();
		WeixinAccountEntity account = this.accountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId",
				acctId);
		String bindingPhone = this.integrateService.getBindingPhone(openId, account.getId());
		// 是否验证了手机
		if (StringUtils.isBlank(bindingPhone)) {
			String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
			String impUrl = account.getQRcode();

			view = new ModelAndView("integrate/verifyphone_binding");
			String urll = urlprefix + "/" + impUrl;
			view.addObject("bindgingUrl", "wx_bindingPhone");
			view.addObject("businessKey", businessKey);
			view.addObject("accountHeadImg", urll);
			view.addObject("accountName", account.getAccountname());
			view.addObject("flowValue", entity.getFlowValue());
			view.addObject("flowType", entity.getFlowType());
			view.addObject("operate", "wx_userPay_pay");
			builder.append("-view:integrate/verifyphone_binding");
			logger.info(builder);
			return view;
		}

		boolean isSufficient = this.integrateService.isMemberFlowSufficient(openId, account.getId(),
				entity.getFlowType(), Double.parseDouble(entity.getFlowValue()));
		if (!isSufficient) {
			view = new ModelAndView("integrate/insufficientflow");
			view.addObject("flowValue", entity.getFlowValue());
			view.addObject("flowType", entity.getFlowType());
			builder.append("-view:integrate/insufficientflow");
			logger.info(builder);
			return view;
		}
		boolean isSetTradePwd = this.integrateService.isUserSetTradePwd(bindingPhone);
		if (!isSetTradePwd) {
			view = new ModelAndView("integrate/verifyphone_paypwd");
			view.addObject("businessKey", businessKey);
			view.addObject("phoneNumber", bindingPhone);
			builder.append("-view:integrate/verifyphone_paypwd");
			logger.info(builder);
			return view;
		}
		// 输入交易密码
		view = new ModelAndView("integrate/paypwd");
		view.addObject("flowType", entity.getFlowType());
		view.addObject("flowValue", entity.getFlowValue());
		view.addObject("phoneNumber", entity.getPhoneNumber());
		builder.append("-view:integrate/paypwd");
		logger.info(builder);
		return view;
	}

	@RequestMapping(params = "wx_pay", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject wx_pay(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		String payPwd = request.getParameter("pwd");
		String businessKey = (String) session.getAttribute(WxIntegrateConstant.session_businessKey);
		if (StringUtils.isBlank(businessKey)) {
			result.put("flag", false);
			return result;
		}
		WxIntegrateBusinessEntity entity = this.integrateService.getIntegrateBusiness(businessKey);
		WeixinAccountEntity account = this.integrateService.getWeixinAccount(entity.getAcctId());
		String phoneNumber = this.integrateService.getBindingPhone(entity.getOpenId(), account.getId());
		boolean isPaypwdRight = this.integrateService.isPaypwdRight(phoneNumber, payPwd);
		if (!isPaypwdRight) {
			result.put("flag", false);
			result.put("msg", "密码错误");
			return result;
		}

		boolean isSufficient = this.integrateService.isMemberFlowSufficient(entity.getOpenId(), account.getId(),
				entity.getFlowType(), Double.parseDouble(entity.getFlowValue()));
		if (!isSufficient) {
			result.put("flag", false);
			result.put("code", "1");
			result.put("msg", "流量不足");
			return result;
		}
		this.integrateService.pay(businessKey);
		result.put("flag", true);
		result.put("msg", "支付成功");
		return result;
	}

	@RequestMapping(params = "verifyphone_paypwd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject verifyphone_paypwd(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		String phoneNumber = request.getParameter("phoneNumber");
		String captcha = request.getParameter("captcha");
		boolean isSetTradePwd = this.integrateService.isUserSetTradePwd(phoneNumber);
		if (isSetTradePwd) {
			result.put("flag", false);
			result.put("msg", "失败，已经设置过支付密码");
			return result;
		}

		HttpSession session = request.getSession();
		if (session.getAttribute("code") == null) {
			result.put("flag", false);
			result.put("code", 2);
			result.put("msg", "验证码过期，请重新发送验证码！");
			return result;
		} else {
			String phone_session = (String) session.getAttribute("phoneNumber");
			String captcha_session = (String) session.getAttribute("code");

			if (!(captcha.equals(captcha_session) && phoneNumber.equals(phone_session))) {
				result.put("flag", false);
				result.put("code", 1);
				result.put("msg", "验证码不正确，请重新输入");
				return result;
			} else {
				// 手动过期验证码
				session.removeAttribute("code");
				session.setAttribute(WxIntegrateConstant.session_verifypaypwd, "true");
				result.put("flag", true);
				result.put("msg", "成功");
				return result;
			}
		}
	}

	@RequestMapping(params = "go_setsaypwd")
	public ModelAndView go_setsaypwd(HttpSession session, @RequestParam("operate") String operate) {
		ModelAndView view = new ModelAndView();
		view.addObject("operate", operate);
		String businessKey = (String) session.getAttribute(WxIntegrateConstant.session_businessKey);
		if (StringUtils.isBlank(businessKey)) {
			view.setViewName("integrate/error");
			view.addObject("msg", "业务出错");
			return view;
		}
		String verifypaypwd = (String) session.getAttribute(WxIntegrateConstant.session_verifypaypwd);
		if (StringUtils.equals(verifypaypwd, "true")) {
			view.setViewName("integrate/setpaypwd");
		} else {
			WxIntegrateBusinessEntity entity = this.integrateService.getIntegrateBusiness(businessKey);
			WeixinAccountEntity account = this.integrateService.getWeixinAccount(entity.getAcctId());
			String phoneNumber = this.integrateService.getBindingPhone(entity.getOpenId(), account.getId());
			view.setViewName("integrate/verifyphone_paypwd");
			view.addObject("phoneNumber", phoneNumber);
		}
		return view;
	}

	@RequestMapping(params = "setpaypwd", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject setpaypwd(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		HttpSession session = request.getSession();
		String businessKey = (String) session.getAttribute(WxIntegrateConstant.session_businessKey);
		if (StringUtils.isBlank(businessKey)) {
			result.put("flag", false);
			result.put("code", "1");
			result.put("msg", "业务超时，请重新进行支付");
			return result;
		}
		String verifypaypwd = (String) session.getAttribute(WxIntegrateConstant.session_verifypaypwd);
		if (!StringUtils.equals(verifypaypwd, "true")) {
			result.put("flag", false);
			result.put("code", "2");
			result.put("msg", "先获取手机验证码");
			return result;
		}

		String pwd = request.getParameter("pwd");
		String repwd = request.getParameter("repwd");
		if (StringUtils.isBlank(pwd) || !StringUtils.equals(pwd, repwd)) {
			result.put("flag", false);
			result.put("code", "3");
			result.put("msg", "密码不合法");
			return result;
		}
		WxIntegrateBusinessEntity entity = this.integrateService.getIntegrateBusiness(businessKey);
		WeixinAccountEntity account = this.accountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId",
				entity.getAcctId());
		String phoneNumber = this.integrateService.getBindingPhone(entity.getOpenId(), account.getId());
		this.integrateService.setpaypwd(phoneNumber, pwd);
		result.put("flag", true);
		result.put("msg", "设置成功");
		result.put("businessKey", businessKey);
		return result;
	}

	@RequestMapping(params = "app_userReceive_getBusinessKey", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject app_userReceive_getBusinessKey(@RequestBody JSONObject json, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		Date now = new Date();
		String userData = MapUtils.getString(json, "data");
		String acctId = MapUtils.getString(json, "acctId");
		String clientIp = IpUtil.getIpAddr(request);
		StringBuilder builder = new StringBuilder();
		builder.append("app_userReceive_getBusinessKey--clientip:");
		builder.append(clientIp);
		builder.append("-密文:" + userData);
		boolean isAllow = this.integrateService.IsAllowAccess(acctId, clientIp);
		if (!isAllow) {
			result.put(WxIntegrateConstant.resultcode, "101");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("101"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		WeixinAcctEntity acctEntity = this.integrateService.getWeixinAcct(acctId);
		if (acctEntity == null) {
			result.put(WxIntegrateConstant.resultcode, "103");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("103"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		String secret = this.integrateService.getCustomerSecret(acctId);
		if (StringUtils.isEmpty(secret)) {
			result.put(WxIntegrateConstant.resultcode, "102");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("102"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		JSONObject integrate = null;
		String decData = null;
		try {
			decData = java.net.URLDecoder.decode(DESUtil.decrypt(userData, secret), "utf-8");
			integrate = JSONObject.fromObject(decData);
		} catch (Exception e) {
			logger.error("app_userReceive_getBusinessKey-acctId:" + acctId + "-解密过程出错", e);
			result.put(WxIntegrateConstant.resultcode, "102");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("102"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		String activityId = MapUtils.getString(integrate, "activityId");
		if (StringUtils.isBlank(activityId)) {
			result.put(WxIntegrateConstant.resultcode, "104");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("104"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		if (!StringUtils.equals(acctId, MapUtils.getString(integrate, "acctId"))) {
			result.put(WxIntegrateConstant.resultcode, "103");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("103"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		String phoneNumber = MapUtils.getString(integrate, "phoneNumber");
		if (!IntegrateUtils.validatePhone(phoneNumber)) {
			result.put(WxIntegrateConstant.resultcode, "107");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("107"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		String flowType = MapUtils.getString(integrate, "flowType");
		String flowValueStr = MapUtils.getString(integrate, "flowValue");
		double flowValue = NumberUtils.toDouble(flowValueStr, 0);
		if (!WxIntegrateConstant.flowtype_national.equals(IntegrateUtils.toFlowTypeName(flowType))
				&& !WxIntegrateConstant.flowtype_provincial.equals(IntegrateUtils.toFlowTypeName(flowType))) {
			result.put(WxIntegrateConstant.resultcode, "105");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("105"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		if (flowValue <= 0) {
			result.put(WxIntegrateConstant.resultcode, "106");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("106"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		boolean isSufficient = this.integrateService.isAcctFlowSufficient(acctId, flowType, flowValue);
		if (!isSufficient) {
			result.put(WxIntegrateConstant.resultcode, "201");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("201"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		boolean isComplyRules = this.ruleService.isComplyRules(acctId, activityId, now, flowValue);
		if (!isComplyRules) {
			result.put(WxIntegrateConstant.resultcode, "202");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("202"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		WeixinAccountEntity accountEntity = this.integrateService.getWeixinAccount(acctId);
		if (accountEntity == null) {
			result.put(WxIntegrateConstant.resultcode, "203");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("203"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		WxIntegrateBusinessEntity integrateEntity = this.integrateService.saveIntegrateBusiness(acctId,
				WxIntegrateConstant.businesstype_appuserreceive, integrate);
		this.ruleService.reduceNum(acctId, activityId, now);
		// this.integrateService.receiveSuccess(integrateEntity.getId());
		result.put(WxIntegrateConstant.businesskey, integrateEntity.getId());
		result.put(WxIntegrateConstant.resultcode, "0");
		result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("0"));
		builder.append("-return:" + result.toString());
		logger.info(builder);
		return result;
	}

	@RequestMapping(params = "app_userReceive_receive")
	public ModelAndView app_userReceive_receive(HttpSession session, @RequestParam("businessKey") String businessKey) {
		StringBuilder builder = new StringBuilder();
		builder.append("app_userReceive_receive-businessKey:" + businessKey);
		ModelAndView view = null;
		WxIntegrateBusinessEntity entity = this.integrateService.getIntegrateBusiness(businessKey);

		if (entity == null) {
			logger.error("businessKey error:" + businessKey);
			builder.append("-businessKey error:" + businessKey);
			logger.info(builder);
			view = new ModelAndView("integrate/error");
			return view;
		} else {
			if (!WxIntegrateConstant.businesstype_appuserreceive.equals(entity.getBusinessType())) {
				logger.error("app_userReceive_receive-businessType error-businessKey:" + businessKey + "-businessType:"
						+ entity.getBusinessType());
				builder.append("-businessType error");
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				return view;
			}
			if (!WxIntegrateConstant.status_getbusinesskey.equals(entity.getStatus())) {
				logger.error("app_userReceive_receive-status error-businessKey:" + businessKey + "-status:"
						+ entity.getStatus());
				builder.append("-status error");
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				return view;
			}
		}
		// session 保存 businessKey
		session.setAttribute(WxIntegrateConstant.session_businessKey, businessKey);
		String phoneNumber = entity.getPhoneNumber();
		String acctId = entity.getAcctId();
		WeixinAccountEntity account = this.accountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId",
				acctId);
		WeixinMemberEntity member = this.weixinMemberService.getWeixinMemberByPhone(phoneNumber, account.getId());
		String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
		String impUrl = account.getQRcode();
		String urll = urlprefix + "/" + impUrl;
		SafetyRulesEntity rule = this.ruleService.getSafetyRuleById(entity.getActivityId());
		if (member == null) {
			WeixinMemberEntity e = new WeixinMemberEntity();
			e.setOpenId(UUID.randomUUID().toString());
			// e.setPhoneNumber(phoneNumber);
			e.setSubscribe(WxIntegrateConstant.status_subscribe);
			e.setSubscribeTime(new Date());
			e.setAccountId(account.getId());
			this.weixinMemberService.saveOrUpdate(e);
			entity.setOpenId(e.getOpenId());
			this.integrateService.saveIntegrateBusiness(entity);
			view = new ModelAndView("integrate/verifyphone_binding");

			view.addObject("bindgingUrl", "app_bindingPhone");
			view.addObject("businessKey", businessKey);
			view.addObject("accountHeadImg", urll);
			view.addObject("accountName", account.getAccountname());
			view.addObject("activityName", rule.getRuleName());
			view.addObject("flowValue", entity.getFlowValue());
			view.addObject("flowType", entity.getFlowType());
			view.addObject("phoneNumber", entity.getPhoneNumber());
			view.addObject("operate", "app_userReceive_receive");
			builder.append("-view:integrate/verifyphone_binding");
			logger.info(builder);
			return view;
		} else {
			this.integrateService.receiveSuccess(businessKey, rule);
			view = new ModelAndView("integrate/recevicesuccess");
			view.addObject("accountHeadImg", urll);
			view.addObject("accountName", account.getAccountname());
			view.addObject("flowValue", entity.getFlowValue());
			view.addObject("flowType", entity.getFlowType());
			view.addObject("openid", member.getOpenId());
			view.addObject("acctid", account.getId());
			builder.append("-view:integrate/recevicesuccess");
			logger.info(builder);
			return view;
		}
	}

	@RequestMapping(params = "app_bindingPhone", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject app_bindingPhone(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		String businessKey = (String) request.getSession().getAttribute(WxIntegrateConstant.session_businessKey);
		String phoneNumber = request.getParameter("phoneNumber");
		String captcha = request.getParameter("captcha");
		WxIntegrateBusinessEntity entity = this.integrateService.getIntegrateBusiness(businessKey);
		if (entity == null || !entity.getStatus().equals(WxIntegrateConstant.status_getbusinesskey)) {
			result.put("flag", false);
			result.put("msg", "业务码非法或超时，请重新开始业务");
			return result;
		}
		String openId = entity.getOpenId();
		String acctId = entity.getAcctId();
		WeixinAccountEntity account = this.accountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId",
				acctId);
		String bindingPhone = this.integrateService.getBindingPhone(openId, account.getId());
		if (!StringUtils.isBlank(bindingPhone)) {
			if (bindingPhone.equals(phoneNumber)) {
				result.put("flag", true);
				result.put("code", 4);
				result.put("msg", "");
				return result;
			} else {
				result.put("flag", false);
				result.put("code", 5);
				result.put("msg", "绑定失败，此账号已经绑定其他手机号！");
				return result;
			}
		} else {
			HttpSession session = request.getSession();
			if (session.getAttribute("code") == null) {
				result.put("flag", false);
				result.put("code", 3);
				result.put("msg", "验证码过期，请重新发送后验证！");
				return result;
			} else {
				String phone_session = (String) session.getAttribute("phoneNumber");
				String captcha_session = (String) session.getAttribute("code");
				String phone_business = entity.getPhoneNumber();
				if (!StringUtils.equals(phone_session, phoneNumber)
						|| !StringUtils.equals(phone_business, phoneNumber)) {
					result.put("flag", false);
					result.put("code", 2);
					result.put("msg", "不能修改手机号");
					result.put("phoneNumber", phone_business);
					return result;
				}
				if (!(captcha.equals(captcha_session) && phoneNumber.equals(phone_session))) {
					result.put("flag", false);
					result.put("code", 1);
					result.put("msg", "验证码不正确，请重新输入");
					return result;
				} else {
					// 手动过期验证码
					session.removeAttribute("code");
					this.integrateService.bindingPhone(openId, account.getId(), phoneNumber);
					result.put("flag", true);
					result.put("msg", "绑定成功");
					return result;
				}
			}
		}
	}

	@RequestMapping(params = "app_userPay_getBusinessKey")
	@ResponseBody
	public JSONObject app_userPay_getBusinessKey(@RequestBody JSONObject json, HttpServletRequest request) {
		// json flowValue,flowType,acctId,secret,openId,
		JSONObject result = new JSONObject();
		String userData = MapUtils.getString(json, "data");
		String acctId = MapUtils.getString(json, "acctId");
		String clientIp = IpUtil.getIpAddr(request);
		StringBuilder builder = new StringBuilder();
		builder.append("wx_userPay_getBusinessKey--clientip:");
		builder.append(clientIp);
		builder.append("-密文:" + userData);
		boolean isAllow = this.integrateService.IsAllowAccess(acctId, clientIp);
		if (!isAllow) {
			result.put(WxIntegrateConstant.resultcode, "101");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("101"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		WeixinAcctEntity acctEntity = this.integrateService.getWeixinAcct(acctId);
		if (acctEntity == null) {
			result.put(WxIntegrateConstant.resultcode, "103");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("103"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		String secret = this.integrateService.getCustomerSecret(acctId);
		if (StringUtils.isEmpty(secret)) {
			result.put(WxIntegrateConstant.resultcode, "102");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("102"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		// 用secret 解密数据
		JSONObject integrate = null;
		String decData = null;
		try {
			decData = java.net.URLDecoder.decode(DESUtil.decrypt(userData, secret), "utf-8");
			integrate = JSONObject.fromObject(decData);
		} catch (Exception e) {
			logger.error("wx_userPay_getBusinessKey-acctId:" + acctId + "-解密过程出错", e);
			result.put(WxIntegrateConstant.resultcode, "102");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("102"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		if (!StringUtils.equals(acctId, MapUtils.getString(integrate, "acctId"))) {
			result.put(WxIntegrateConstant.resultcode, "103");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("103"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		String flowType = MapUtils.getString(integrate, "flowType");
		String flowValueStr = MapUtils.getString(integrate, "flowValue");
		double flowValue = NumberUtils.toDouble(flowValueStr, 0);
		if (!WxIntegrateConstant.flowtype_national.equals(IntegrateUtils.toFlowTypeName(flowType))
				&& !WxIntegrateConstant.flowtype_provincial.equals(IntegrateUtils.toFlowTypeName(flowType))) {
			result.put(WxIntegrateConstant.resultcode, "105");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("105"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		if (flowValue <= 0) {
			result.put(WxIntegrateConstant.resultcode, "106");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("106"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		WeixinAccountEntity accountEntity = this.integrateService.getWeixinAccount(acctId);
		if (accountEntity == null) {
			result.put(WxIntegrateConstant.resultcode, "203");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("203"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}
		String phoneNumber = MapUtils.getString(integrate, "phoneNumber");
		if (!IntegrateUtils.validatePhone(phoneNumber)) {
			result.put(WxIntegrateConstant.resultcode, "107");
			result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("107"));
			builder.append("-return:" + result.toString());
			logger.info(builder);
			return result;
		}

		WxIntegrateBusinessEntity integrateEntity = this.integrateService.saveIntegrateBusiness(acctId,
				WxIntegrateConstant.businesstype_appuserpay, integrate);
		result.put(WxIntegrateConstant.businesskey, integrateEntity.getId());
		result.put(WxIntegrateConstant.resultcode, "0");
		result.put(WxIntegrateConstant.resultmsg, WxIntegrateConstant.getResultMsg("0"));
		builder.append("-return:" + result.toString());
		logger.info(builder);
		return result;
	}

	@RequestMapping(params = "app_userPay_pay")
	public ModelAndView app_userPay_pay(@RequestParam("businessKey") String businessKey, HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append("wx_userPay_pay-businessKey:" + businessKey);
		ModelAndView view = null;
		request.getSession().setAttribute(WxIntegrateConstant.session_businessKey, businessKey);
		WxIntegrateBusinessEntity entity = this.integrateService.getIntegrateBusiness(businessKey);
		if (entity == null) {
			logger.error("businessKey error:" + businessKey);
			builder.append("-businessKey error:" + businessKey);
			logger.info(builder);
			view = new ModelAndView("integrate/error");
			return view;
		} else {
			if (!WxIntegrateConstant.businesstype_appuserpay.equals(entity.getBusinessType())) {
				logger.error("wx_userPay_pay-businessType error-businessKey:" + businessKey + "-businessType:"
						+ entity.getBusinessType());
				builder.append("-businessType error");
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				return view;
			}
			if (!WxIntegrateConstant.status_getbusinesskey.equals(entity.getStatus())) {
				logger.error(
						"wx_userPay_pay-status error-businessKey:" + businessKey + "-status:" + entity.getStatus());
				builder.append("-status error");
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				return view;
			}
		}
		String phoneNumber = entity.getPhoneNumber();
		String acctId = entity.getAcctId();
		WeixinAccountEntity account = this.accountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId",
				acctId);
		String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
		String impUrl = account.getQRcode();
		String urll = urlprefix + "/" + impUrl;
		WeixinMemberEntity member = this.weixinMemberService.getWeixinMemberByPhone(phoneNumber, account.getId());
		if (member == null) {
			WeixinMemberEntity e = new WeixinMemberEntity();
			e.setOpenId(UUID.randomUUID().toString());
			// e.setPhoneNumber(phoneNumber);
			e.setSubscribe(WxIntegrateConstant.status_subscribe);
			e.setSubscribeTime(new Date());
			e.setAccountId(account.getId());
			e.setIsRealOpenid((short) 0);
			this.weixinMemberService.saveOrUpdate(e);

			entity.setOpenId(e.getOpenId());
			this.integrateService.saveIntegrateBusiness(entity);

			view = new ModelAndView("integrate/verifyphone_binding");
			SafetyRulesEntity rule = this.ruleService.getSafetyRuleById(entity.getActivityId());

			view.addObject("businessKey", businessKey);
			view.addObject("accountHeadImg", urll);
			view.addObject("accountName", account.getAccountname());
			view.addObject("activityName", rule.getRuleName());
			view.addObject("flowValue", entity.getFlowValue());
			view.addObject("flowType", entity.getFlowType());
			view.addObject("phoneNumber", entity.getPhoneNumber());
			view.addObject("operate", "app_userPay_pay");
			builder.append("-view:integrate/app_userPay_pay");
			logger.info(builder);
			return view;
		}

		boolean isSufficient = this.integrateService.isMemberFlowSufficient(phoneNumber, entity.getFlowType(),
				Double.parseDouble(entity.getFlowValue()));
		if (!isSufficient) {
			view = new ModelAndView("integrate/insufficientflow");
			view.addObject("flowValue", entity.getFlowValue());
			view.addObject("flowType", entity.getFlowType());
			builder.append("-view:integrate/insufficientflow");
			logger.info(builder);
			return view;
		}
		boolean isSetTradePwd = this.integrateService.isUserSetTradePwd(phoneNumber);
		if (!isSetTradePwd) {
			view = new ModelAndView("integrate/verifyphone_paypwd");
			view.addObject("businessKey", businessKey);
			view.addObject("phoneNumber", phoneNumber);

			view.addObject("operate", "app_userPay_pay");
			builder.append("-view:integrate/verifyphone_paypwd");
			logger.info(builder);
			return view;
		}
		// 输入交易密码
		view = new ModelAndView("integrate/paypwd");
		view.addObject("flowType", entity.getFlowType());
		view.addObject("flowValue", entity.getFlowValue());
		view.addObject("phoneNumber", entity.getPhoneNumber());
		
		builder.append("-view:integrate/paypwd");
		logger.info(builder);
		return view;
	}

	@RequestMapping(params = "app_pay", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject app_pay(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		String payPwd = request.getParameter("pwd");
		String businessKey = (String) session.getAttribute(WxIntegrateConstant.session_businessKey);
		if (StringUtils.isBlank(businessKey)) {
			result.put("flag", false);
			return result;
		}
		WxIntegrateBusinessEntity entity = this.integrateService.getIntegrateBusiness(businessKey);
		WeixinAccountEntity account = this.integrateService.getWeixinAccount(entity.getAcctId());
		String phoneNumber = this.integrateService.getBindingPhone(entity.getOpenId(), account.getId());
		boolean isPaypwdRight = this.integrateService.isPaypwdRight(phoneNumber, payPwd);
		if (!isPaypwdRight) {
			result.put("flag", false);
			return result;
		}

		boolean isSufficient = this.integrateService.isMemberFlowSufficient(entity.getOpenId(), account.getId(),
				entity.getFlowType(), Double.parseDouble(entity.getFlowValue()));
		if (!isSufficient) {
			result.put("flag", false);
			result.put("code", "1");
			result.put("msg", "流量不足");
			return result;
		}
		this.integrateService.pay(businessKey);
		return result;
	}

	// app提取流量页面跳转
	@RequestMapping(params = "goAppUserCharge")
	public ModelAndView goAppUserCharge(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder builder = new StringBuilder();
		Gson gson = new Gson();
		 Type phoneType = new TypeToken<PhoneLocationBean>() {
       }.getType();
		String userData = request.getParameter("data");
		String acctId = request.getParameter("acctId");
		ModelAndView view = new ModelAndView();
		try {
			String secret = this.integrateService.getCustomerSecret(acctId);
			if (StringUtils.isEmpty(secret)) {
				logger.error("acctId error:" + acctId);
				builder.append("-acctId error:" + acctId);
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				view.addObject("msg", "缺少acctId");
				return view;
			}
			builder.append(" acctId : "+ acctId);
			JSONObject integrate = null;
			String decData = null;
			try {
				decData = java.net.URLDecoder.decode(DESUtil.decrypt(userData, secret), "utf-8");
				integrate = JSONObject.fromObject(decData);
			} catch (Exception e) {
				logger.error("wx_userPay_getBusinessKey-acctId:" + acctId + "-解密过程出错", e);
				view = new ModelAndView("integrate/error");
				view.addObject("msg", "密钥错误");
				return view;
			}
			if (!StringUtils.equals(acctId, MapUtils.getString(integrate, "acctId"))) {
				logger.error("acctId error:" + acctId);
				builder.append("-acctId error:" + acctId);
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				view.addObject("msg", "acctId不一致");
				return view;
			}
			String phonenumber = MapUtils.getString(integrate, "phoneNumber");
			if (StringUtils.isEmpty(phonenumber) || !phonenumber.matches("^1[3-9][0-9]{9}$")) {
				logger.error("acctId error:" + phonenumber);
				builder.append("-acctId error:" + phonenumber);
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				view.addObject("msg", "手机号不对");
				return view;
			}
			builder.append(" phonenumber: " + phonenumber);
			String times = MapUtils.getString(integrate, "times");
			Long nowTimes = System.currentTimeMillis();
			if (StringUtil.isEmpty(times) || !times.matches("^[1-9]+[0-9]*$")
					|| Math.abs(nowTimes - Long.parseLong(times)) > 900000) {
				builder.append("nowTimes : " + nowTimes + "  times : " + times);
				logger.info(builder );
				view = new ModelAndView("integrate/error");
				view.addObject("msg", "时间不对");
				return view;
			}
			builder.append(" times: " + times);
			MerchantInfoBean userFlowAccount = JFinalUtils.getUserFlowAccount(null, phonenumber);
			if (userFlowAccount == null || userFlowAccount.getData() == null || userFlowAccount.getData().isEmpty()) {
				logger.error("acctId error:" + phonenumber);
				builder.append("-acctId error:" + phonenumber);
				logger.info(builder);
				view = new ModelAndView("integrate/error");
				view.addObject("msg", "用户不存在");
				return view;
			}

			String paymentpwd = userFlowAccount.getData().get(0).getPaymentpwd();
			if (StringUtils.isEmpty(paymentpwd)) {
				view.addObject("phoneNumber", phonenumber);
				view.addObject("userData", userData);
				view.addObject("acctId", acctId);
				view.setViewName("integrate/setpaypwd");
				return view;
			}
			view.addObject("userData", userData);
			view.addObject("acctId", acctId);
			view.addObject("phoneNumber", phonenumber);
			String phoneUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "phoneLocation/getPhonelocation";

	        JSONObject myJsonObject = new JSONObject();
	        myJsonObject.accumulate("phoneNumber", phonenumber);
	        JSONObject phoneContent = HttpUtil.httpPost(phoneUrl, myJsonObject, false);

	        String strPhoneContent = gson.toJson(phoneContent);
	        //LOGGER.info(strPhoneContent);

	        PhoneLocationBean phoneJson = gson.fromJson(strPhoneContent, phoneType);

	        String strBusinessCode = phoneJson.getData().getBusinessCode();
	        String strProvinceCode = phoneJson.getData().getProvinceCode();
	        builder.append("_strBusinessCode" + strBusinessCode + "_strProvinceCode" + strProvinceCode);
	        view.addObject("bussinessCode",strBusinessCode);
	        view.addObject("strProvinceCode",strProvinceCode);
			view.setViewName("integrate/appusercharge");
			logger.info(builder);
			return view;
		} catch (Exception e) {
			logger.error("app流量提取异常", e);
			view.addObject("msg", "出现异常");
			view = new ModelAndView("integrate/error");
			return view;
		}
	}

	// 开始给手机充值，判断用户的 流量余额是否足够、交易密码是否正确
	@RequestMapping(params = "appUserGetTrueFlow")
	@ResponseBody
	public void appUserGetTrueFlow(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject result = new JSONObject();
		Gson gson = new Gson();
		Type type = new TypeToken<BusinessCommonJsonBean>() {
		}.getType();
		String strFlowValue = request.getParameter("flowTrueValue");
		String phoneNumber = request.getParameter("phoneNumber");
		String familyNumber = request.getParameter("familyNumber");
		String businessCode = request.getParameter("businessCode");
		String provinceCode = request.getParameter("provinceCode");
		String flowType = request.getParameter("flowType");
		String payPwd = request.getParameter("pwd");
		String phoneNumbeqr = ((String) session.getAttribute("phoneNumber"));

		if (ValidateFlowIsEnough(phoneNumber, flowType, strFlowValue) == true) {

			payPwd = PasswordUtil.encrypt(phoneNumber, payPwd, PasswordUtil.getStaticSalt());
			MerchantInfoBean userFlowAccount = JFinalUtils.getUserFlowAccount(null, phoneNumber);
			String paymentpwd = userFlowAccount.getData().get(0).getPaymentpwd();

			if (payPwd.equals(paymentpwd)) {
				StringBuilder builder = new StringBuilder();
				String code = FlowType.province.getCode();
				if (flowType.equals(FlowType.province.getCode())) {
					builder.append("充值省内流量");
					// 自己给自己充值

					String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/ChargeUserFlow";
					JSONObject myJsonObject2 = new JSONObject();
					myJsonObject2.accumulate("phoneNumber", phoneNumber);
					myJsonObject2.accumulate("familyNumber", familyNumber);
					myJsonObject2.accumulate("businessCode", businessCode);
					myJsonObject2.accumulate("provinceCode", provinceCode);
					myJsonObject2.accumulate("flowValue", strFlowValue);
					myJsonObject2.accumulate("flowType", flowType);
					JSONObject content1 = HttpUtil.httpPost(url, myJsonObject2, false);

					String strcontent1 = gson.toJson(content1);
					logger.info(strcontent1);

					BusinessCommonJsonBean userChargedJson = gson.fromJson(strcontent1, type);
					String restrnMsg = userChargedJson.getMsg();

					ObjectMapper objectMapper = new ObjectMapper();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("returnMessage", restrnMsg);

					String json;
					try {
						json = objectMapper.writeValueAsString(map);
						logger.info("-----转json后的结果是------------");
						PrintWriter out = response.getWriter();
						out.write(json);
						logger.info(json);
					} catch (JsonGenerationException e1) {
						e1.printStackTrace();
					} catch (JsonMappingException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					response.setContentType("application/json;charset=UTF-8");
				} else {// 选择的是全国流量
					flowType = "1";
					builder.append("选择的是全国流量");
					String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/ChargeUserFlow";
					JSONObject myJsonObject2 = new JSONObject();
					myJsonObject2.accumulate("phoneNumber", phoneNumber);
					myJsonObject2.accumulate("familyNumber", familyNumber);
					myJsonObject2.accumulate("businessCode", businessCode);
					myJsonObject2.accumulate("provinceCode", provinceCode);
					myJsonObject2.accumulate("flowValue", strFlowValue);
					myJsonObject2.accumulate("flowType", flowType);

					JSONObject content1 = HttpUtil.httpPost(url, myJsonObject2, false);
					String strcontent1 = gson.toJson(content1);
					logger.info(strcontent1);
					BusinessCommonJsonBean userChargedJson = gson.fromJson(strcontent1, type);
					String restrnMsg = userChargedJson.getMsg();
					ObjectMapper objectMapper = new ObjectMapper();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("returnMessage", "sucess");
					builder.append(restrnMsg);
					String json;
					try {
						json = objectMapper.writeValueAsString(map);
						PrintWriter out = response.getWriter();
						out.write(json);
						logger.info(json);

					} catch (JsonGenerationException e1) {
						e1.printStackTrace();
					} catch (JsonMappingException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					response.setContentType("application/json;charset=UTF-8");
				}
			} else {
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("returnMessage", "fail");
				String json;
				try {
					json = objectMapper.writeValueAsString(map);
					PrintWriter out = response.getWriter();
					out.write(json);
					logger.info(json);
				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} else {
			logger.error("error:" + phoneNumber + flowType + strFlowValue);
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("returnMessage", "flowNotEnough");
			String str;
			try {
				str = objectMapper.writeValueAsString(map);
				logger.info("-----转json后的结果是------------");
				PrintWriter out = response.getWriter();
				out.write(str);

			} catch (JsonGenerationException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			response.setContentType("application/json;charset=UTF-8");
		}

	}

	// 流量余额是否足够
	public boolean ValidateFlowIsEnough(String phoneNumber, String flowType, String flowValue) {
		long startTime = System.currentTimeMillis();
		StringBuilder builder = new StringBuilder();
		builder.append("ValidateFlowIsEnough");

		Gson gson = new Gson();
		String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
		JSONObject myJsonObject = new JSONObject();
		Type typeValFlow = new TypeToken<UserFlowAccountBean>() {
		}.getType();
		myJsonObject.accumulate("phoneNumber", phoneNumber);
		JSONObject contentValFlow = HttpUtil.httpPost(url, myJsonObject, false);
		String strContent = gson.toJson(contentValFlow);
		logger.info(contentValFlow);

		UserFlowAccountBean jsonBean = gson.fromJson(strContent, typeValFlow);

		UserFlowAccountBean.DataEntity accountBean = new UserFlowAccountBean.DataEntity();
		accountBean = jsonBean.getData();
		String provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
		String countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
		// builder.append("provinceFlowValue" + provinceFlowValue +
		// "_countryFlowValue" + countryFlowValue);

		if ("1".equals(flowType)) {
			builder.append("countryFlowValue");
			if (Double.valueOf(flowValue) <= Double.valueOf(countryFlowValue)) {
				builder.append("Enough");
				long runTime = System.currentTimeMillis() - startTime;
				logger.info("runTime" + runTime + builder.toString());
				return true;

			} else {
				builder.append("notEnough");
				long runTime = System.currentTimeMillis() - startTime;
				logger.info("runTime" + runTime + builder.toString());
				return false;
			}
		} else {
			if (Double.valueOf(flowValue) <= Double.valueOf(provinceFlowValue)) {
				builder.append("Enough");
				long runTime = System.currentTimeMillis() - startTime;
				logger.info("runTime" + runTime + builder.toString());
				return true;
			} else {
				builder.append("notEnough");
				long runTime = System.currentTimeMillis() - startTime;
				logger.info("runTime" + runTime + builder.toString());
				return false;
			}
		}
	}

	// 设置支付密码
	@RequestMapping(params = "appSetPayPwd")
	@ResponseBody
	public void appSetPayPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long startTime = System.currentTimeMillis();// 获取开始当前的时间
		StringBuffer buffer = new StringBuffer();
		HttpSession session = request.getSession(true);
		StringBuffer logBuffer = new StringBuffer();
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attrKey = attributeNames.nextElement();
			logBuffer.append(attrKey).append("=").append(session.getAttribute(attrKey)).append(";");
		}
		logger.info("IntegrateController.appSetPayPwd:" + logBuffer);
		CommonUtils.printRequestParam("BindingController.binding:param:", request);
		buffer.append("bindingController_binding_");
		Map<String, Object> map = new HashMap<>();
		try {
			if (session.getAttribute("code") == null) {
				map.put("flag", false);
				map.put("msg", "验证码过期，请重新发送！");
				response.getWriter().write(new ObjectMapper().writeValueAsString(map));
				return;
			}
			int times = (Integer) session.getAttribute("codeTimes");
			if (times <= 0) {
				map.put("flag", false);
				map.put("code", 3);
				map.put("msg", "验证码已失效，请重新发送后验证！");
				response.getWriter().write(new ObjectMapper().writeValueAsString(map));
				return;
			}
			session.setAttribute("codeTimes", times - 1);

			String phoneNumbeqr = ((String) session.getAttribute("phoneNumber"));
			String codeold = ((String) session.getAttribute("code"));
			String code = request.getParameter("phoneCode");
			String openId = request.getParameter("openId");
			String operateType = StringUtils.defaultString(request.getParameter("operateType"), "关注");
			String phoneNumber = request.getParameter("phoneNumber");
			String merchantID = request.getParameter("accountid");
			String paymentpwd = request.getParameter("pwd");

			buffer.append("phoneNumbqer_" + phoneNumbeqr + "_oldCode_" + codeold + "_newCode_" + code + "_phoneNumber_"
					+ phoneNumber + "_openid_" + openId + "_accountid_" + merchantID);
			if (!(Objects.equals(code, codeold) && Objects.equals(phoneNumbeqr, phoneNumber))) {
				map.put("flag", false);
				map.put("msg", "手机号与验证码不匹配，请重新输入！");
			} else {
				session.removeAttribute("code");
				this.integrateService.setpaypwd(phoneNumber, paymentpwd);
				map.put("flag", true);
				map.put("msg", "交易密码设置成功，可进行交易");
			}
			response.getWriter().write(new ObjectMapper().writeValueAsString(map));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			buffer.append("bingdingController_end_time:" + (endTime - startTime) + "ms");
			logger.info(buffer.toString());
		}
	}

	// 找回密码
	@RequestMapping(params = "findPwd")
	public ModelAndView findPwd(HttpServletRequest request, HttpServletResponse response) {
		String phoneNumber = request.getParameter("phoneNumber");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("phoneNumber", phoneNumber);
		modelAndView.setViewName("integrate/updatepaypwd");
		return modelAndView;
	}

	/**
	 * Created by wangpeng app流量获取记录接口
	 */
	@RequestMapping(params = "app_userGiveFlow_getRecords")
	public ModelAndView app_userGiveFlow_getRecords(HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		ModelAndView mav = new ModelAndView();
		String userData = request.getParameter("data");
		String acctId = request.getParameter("acctId");
		StringBuilder builder = new StringBuilder();
		builder.append("app_userGiveFlow_getRecords:");
		builder.append("-密文:" + userData);
		WeixinAcctEntity acctEntity = this.integrateService.getWeixinAcct(acctId);
		if (acctEntity == null) {
			logger.error("acctId error:" + acctId);
			builder.append("-acctId error:" + acctId);
			logger.info(builder);
			mav = new ModelAndView("integrate/error");
			return mav;
		}
		String secret = this.integrateService.getCustomerSecret(acctId);
		if (StringUtils.isEmpty(secret)) {
			logger.error("userData error:" + userData);
			builder.append("-userData error:" + userData);
			logger.info(builder);
			mav = new ModelAndView("integrate/error");
			return mav;
		}
		JSONObject integrate = null;
		String decData = null;
		try {
			decData = java.net.URLDecoder.decode(DESUtil.decrypt(userData, secret), "utf-8");
			integrate = JSONObject.fromObject(decData);
		} catch (Exception e) {
			logger.error("app_userGiveFlow_getRecords-acctId:" + acctId + "-解密过程出错", e);
			builder.append("-app_userGiveFlow_getRecords-acctId:" + acctId + "-解密过程出错");
			logger.info(builder);
			mav = new ModelAndView("integrate/error");
			mav.addObject("msg", "业务出错");
			return mav;
		}
		if (!StringUtils.equals(acctId, MapUtils.getString(integrate, "acctId"))) {
			logger.error("acctId error:" + acctId);
			builder.append("-acctId error:" + acctId);
			logger.info(builder);
			mav = new ModelAndView("integrate/error");
			return mav;
		}
		String phoneNumber = MapUtils.getString(integrate, "phoneNumber");
		WeixinAccountEntity accountEntity = this.integrateService.getWeixinAccount(acctId);
		String accounit = accountEntity.getId();
		// 调用接口查询用户流量获取记录
		UserFlowGiveBean userFlowJson = JFinalUtils.getUserFlowGiveRecord(accounit, null, phoneNumber);
		List<UserFlowGiveBean.DataEntity> userFlowGiveBean = new ArrayList<UserFlowGiveBean.DataEntity>();
		mav.addObject("data", userData);
		mav.addObject("acctId", acctId);
		if (userFlowJson != null) {
			userFlowGiveBean = userFlowJson.getData();
			mav.addObject("userFlowJson", userFlowGiveBean);
			builder.append("record_not_null");
			long runTime = System.currentTimeMillis() - startTime;
			logger.info("runTime" + runTime + builder.toString());
			mav.setViewName("integrate/appFlowGetRecord");
			return mav;
		} else {
			mav.addObject("userFlowJson", "");
			builder.append("record_is_null");
			long runTime = System.currentTimeMillis() - startTime;
			logger.info("runTime" + runTime + builder.toString());
			mav.setViewName("integrate/appFlowGetRecord");
			return mav;
		}
	}

	/**
	 * Created by wangpeng app流量充值记录接口
	 */
	@RequestMapping(params = "app_userChargedFlow_getRecords")
	public ModelAndView app_userChargedFlow_getRecords(HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		ModelAndView mav = new ModelAndView();
		String userData = request.getParameter("data");
		String acctId = request.getParameter("acctId");
		StringBuilder builder = new StringBuilder();
		builder.append("app_userChargedFlow_getRecords:");
		builder.append("-密文:" + userData);
		WeixinAcctEntity acctEntity = this.integrateService.getWeixinAcct(acctId);
		if (acctEntity == null) {
			logger.error("acctId error:" + acctId);
			builder.append("-acctId error:" + acctId);
			logger.info(builder);
			mav = new ModelAndView("integrate/error");
			return mav;
		}
		String secret = this.integrateService.getCustomerSecret(acctId);
		if (StringUtils.isEmpty(secret)) {
			logger.error("userData error:" + userData);
			builder.append("-userData error:" + userData);
			logger.info(builder);
			mav = new ModelAndView("integrate/error");
			return mav;
		}
		JSONObject integrate = null;
		String decData = null;
		try {
			decData = java.net.URLDecoder.decode(DESUtil.decrypt(userData, secret), "utf-8");
			integrate = JSONObject.fromObject(decData);
		} catch (Exception e) {
			logger.error("app_userChargedFlow_getRecords-acctId:" + acctId + "-解密过程出错", e);
			builder.append("-app_userChargedFlow_getRecords-acctId:" + acctId + "-解密过程出错");
			logger.info(builder);
			mav = new ModelAndView("integrate/error");
			mav.addObject("msg", "业务出错");
			return mav;
		}
		if (!StringUtils.equals(acctId, MapUtils.getString(integrate, "acctId"))) {
			logger.error("acctId error:" + acctId);
			builder.append("-acctId error:" + acctId);
			logger.info(builder);
			mav = new ModelAndView("integrate/error");
			return mav;
		}
		String phoneNumber = MapUtils.getString(integrate, "phoneNumber");
		Gson gson = new Gson();
		Type type = new TypeToken<UserChargedFlowRecordsBean>() {
		}.getType();
		// 调用接口查询用户流量充值记录以及充值进度
		String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/getUserChargeRecords";
		JSONObject myJsonObject = new JSONObject();
		myJsonObject.accumulate("phoneNumber", phoneNumber);
		builder.append("phoneNumber" + phoneNumber);

		JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
		logger.info(content);

		String strcontent = gson.toJson(content);
		logger.info(strcontent);
		UserChargedFlowRecordsBean userChargedJson = gson.fromJson(strcontent, type);
		List<UserChargedFlowRecordsBean.DataEntity> userFlowChargedBean = new ArrayList<UserChargedFlowRecordsBean.DataEntity>();
		mav.addObject("data", userData);
		mav.addObject("acctId", acctId);
		if (userChargedJson != null) {
			userFlowChargedBean = userChargedJson.getData();
			mav.addObject("userFlowChargedBean", userFlowChargedBean);
			mav.setViewName("integrate/appFlowReceiveRecord");
			builder.append("record_not_null");
			long runTime = System.currentTimeMillis() - startTime;
			logger.info("runTime" + runTime + builder.toString());
			return mav;
		} else {
			mav.addObject("userFlowChargedBean", null);
			mav.setViewName("integrate/addFlowReceiveRecord");

			builder.append("record_is_null");
			long runTime = System.currentTimeMillis() - startTime;
			logger.info("runTime" + runTime + builder.toString());
			return mav;
		}

	}

}
