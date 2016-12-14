package weixin.guanjia.openplatform.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import sdk.jfinal.JFinalUtils;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinOpenPlatformEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.service.WeixinOpenPlatformServiceI;
import weixin.guanjia.account.util.CheckPic;
import weixin.guanjia.core.service.impl.OpenPlatformPushMsgService;
import weixin.guanjia.core.util.PageAuthRedisCache;
import weixin.guanjia.openplatform.util.OpenPlatformMessageUtil;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.service.flowcontroller.BindingService;
import weixin.liuliangbao.util.HttpUtil;
import weixin.liuliangbao.util.RedisConnectionPoolFactory;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.oauth2.AdvancedUtil;
import weixin.oauth2.WeixinOauth2Token;
import weixin.report.service.UserGiveFlowServiceI;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Scope("prototype")
@Controller
@RequestMapping("weixinOpenPlatform")
public class WeixinOpenPlatformController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinOpenPlatformController.class);

	// 全功能授权第三方APP_ID
	private static final String COMPONENT_APP_ID = ResourceUtil.getConfigByName("COMPONENT_APP_ID");

	// 单网页授权第三方APP_ID
	private static final String COMPONENT_APP_ID2 = ResourceUtil.getConfigByName("COMPONENT_APP_ID2");
    public static final String SHILIU_SECRET = ResourceUtil.getShiliuSecret();
    public static final String SHILIU_APP_ID = ResourceUtil.getShiliuAppId();
    public static final String SHILIU_ACCOUNT_ID = ResourceUtil.getShiliuAccountId();

	@Autowired
	private WeixinOpenPlatformServiceI weixinOpenPlatformService;

	@Autowired
	private WeixinAccountServiceI weixinAccountService;

	@Autowired
	private OpenPlatformPushMsgService openPlatformPushMsgService;

	@Autowired
	private WeixinMemberServiceI weixinMemberService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private UserGiveFlowServiceI userGiveFlowService;

    @Autowired
    private BindingService bindingService;

	// 全功能授权第三方平台授权事件回调接口
	@RequestMapping(params = "authEvent", method = RequestMethod.POST)
	@ResponseBody
	public void authEvent(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            builder.append(entry.getKey()).append(":").append(Arrays.asList(entry.getValue())).append(",");
        }

        logger.info("全功能授权第三方平台授权事件回调接口:[{}]" + builder);
		try {
			WeixinOpenPlatformEntity platform = weixinOpenPlatformService.findByAppId(COMPONENT_APP_ID);
			if (platform == null) {
				logger.info(String.format("第三方平台 %s 不存在", COMPONENT_APP_ID));
			} else {
				Map<String, String> event = OpenPlatformMessageUtil.parseXml(request, COMPONENT_APP_ID, platform.getComponentValidateToken(), platform.getComponentSymmetricKey());
				logger.info(String.format("收到事件:%s", OpenPlatformMessageUtil.map2String(event)));
				if (event.containsKey("InfoType")) {
					if (event.get("InfoType").equalsIgnoreCase("component_verify_ticket")) {
						String appId = event.get("AppId");
						String componentVerifyTicket = event.get("ComponentVerifyTicket");
						Date updateTime = new Date(Long.valueOf(event.get("CreateTime")));
						this.weixinOpenPlatformService.updateComponentVerifyTicket(appId, componentVerifyTicket, new Date());
					} else if (event.get("InfoType").equalsIgnoreCase("unauthorized")) {
						logger.info("取消授权通知:" + OpenPlatformMessageUtil.map2String(event));
						String authorizerAppid = event.get("AuthorizerAppid");
						if (authorizerAppid.equalsIgnoreCase("wx570bc396a51b8ff8")) {
							logger.info("全网发布专用测试公众号消息, 直接返回");
							response.addHeader("Content-type", "text/plain");
							PrintWriter out = response.getWriter();
							out.print("success");
							out.flush();
							out.close();
							return;
						}
						
						WeixinAccountEntity account = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "accountappid", authorizerAppid);
						account.setAuthorizationType("0");
						account.setUnauthorizeTime(new Date());
						weixinAccountService.saveOrUpdate(account);
					} else if (event.get("InfoType").equalsIgnoreCase("authorized")) {
						String authorizerAppid = event.get("AuthorizerAppid");
						String authCode = event.get("AuthorizationCode");
						if (authorizerAppid.equalsIgnoreCase("wx570bc396a51b8ff8")) {
							Jedis jedis = RedisConnectionPoolFactory.getResource();
							jedis.set("internet_publish_authorization_code", authCode);
							logger.info("全网发布专用测试公众号消息, 直接返回");
							response.addHeader("Content-type", "text/plain");
							PrintWriter out = response.getWriter();
							out.print("success");
							out.flush();
							out.close();
							return;
						}
					} else {
						logger.info("unknow msg type");
					}
				} else {
					logger.info("非法的消息事件");
				}
			}
		} catch (Exception e) {
			logger.error(String.format("授权事件推送失败", e.getMessage()), e);
		}

		try {
			response.addHeader("Content-type", "text/plain");
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("输出返回值出错" + e.getMessage(), e);
		}

	}

	// 单网页授权第三方平台授权事件回调接口
	@RequestMapping(params = "authEvent2", method = RequestMethod.POST)
	@ResponseBody
	public void authEvent2(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            builder.append(entry.getKey()).append(":").append(Arrays.asList(entry.getValue())).append(",");
        }
        logger.info("单网页授权第三方平台授权事件回调的参数列表:[{}]" + builder);
        try {
			WeixinOpenPlatformEntity platform = weixinOpenPlatformService.findByAppId(COMPONENT_APP_ID2);
			if (platform == null) {
				logger.info(String.format("第三方平台 %s 不存在", COMPONENT_APP_ID2));
			} else {
				Map<String, String> event = OpenPlatformMessageUtil.parseXml(request, COMPONENT_APP_ID2, platform.getComponentValidateToken(), platform.getComponentSymmetricKey());
				logger.info(String.format("收到事件:%s", OpenPlatformMessageUtil.map2String(event)));
				if (event.containsKey("InfoType")) {
					if (event.get("InfoType").equalsIgnoreCase("component_verify_ticket")) {
						String appId = event.get("AppId");
						String componentVerifyTicket = event.get("ComponentVerifyTicket");
						Date updateTime = new Date(Long.valueOf(event.get("CreateTime")));
						this.weixinOpenPlatformService.updateComponentVerifyTicket(appId, componentVerifyTicket, new Date());
					} else if (event.get("InfoType").equalsIgnoreCase("unauthorized")) {
						logger.info("取消授权通知:" + OpenPlatformMessageUtil.map2String(event));
						String authorizerAppid = event.get("AuthorizerAppid");
						if (authorizerAppid.equalsIgnoreCase("wx570bc396a51b8ff8")) {
							logger.info("全网发布专用测试公众号消息, 直接返回");
							response.addHeader("Content-type", "text/plain");
							PrintWriter out = response.getWriter();
							out.print("success");
							out.flush();
							out.close();
							return;
						}
						
						WeixinAccountEntity account = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "accountappid", authorizerAppid);
						account.setAuthorizationType("0");
						account.setUnauthorizeTime(new Date());
						weixinAccountService.saveOrUpdate(account);
					} else {
						logger.info("unknow msg type");
					}
				} else {
					logger.info("非法的消息事件");
				}
			}
		} catch (Exception e) {
			logger.error(String.format("授权事件推送失败", e.getMessage()), e);
		}

		try {
			response.addHeader("Content-type", "text/plain");
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("输出返回值出错" + e.getMessage(), e);
		}
	}


    // 第三方授权消息回调接口
    @RequestMapping(value = "/{appId}/weixinMsg", method = RequestMethod.POST)
    public void weixinMsg(@PathVariable("appId") String appId, HttpServletRequest request,HttpServletResponse response) throws IOException {
        logger.info(String.format("第三方授权消息回调接口:收到公众号[%s]的消息", appId));
        String respMessage=null;
        if (appId.equalsIgnoreCase("wx570bc396a51b8ff8")) {
            logger.info("全网发布专用测试号消息");
            String componentAppId = request.getParameter("appId");
            WeixinOpenPlatformEntity platform = weixinOpenPlatformService.findByAppId(componentAppId);
            respMessage= openPlatformPushMsgService.coreService(request, platform.getAppId(), platform.getComponentValidateToken(), platform.getComponentSymmetricKey());
        } else {

            WeixinAccountEntity account = null;
            try {
                account = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "accountappid", appId);
            } catch (NonUniqueResultException e) {
                List<WeixinAccountEntity> dumplicateAppId = weixinAccountService.findHql("from WeixinAccountEntity where accountappid=?", appId);
                String dumplicateAppIds = "";
                for (WeixinAccountEntity weixinAccountEntity : dumplicateAppId) {
                    dumplicateAppIds += weixinAccountEntity.getId() + "|";
                }
                logger.error(MessageFormat.format("根据appId[{0}]查询到多个公众号信息:accountId={1}", appId, dumplicateAppIds));
            } catch (Exception e) {
                logger.error(MessageFormat.format("根据appId[{0}]查询公众号信息失败:", appId), e);
            }
            if (account == null) {
                logger.info("未找到对应的微信账号");
                respMessage="";
            }

            WeixinOpenPlatformEntity platform = weixinOpenPlatformService.getEntity(WeixinOpenPlatformEntity.class,  account.getOpenPlatformId());
            respMessage = openPlatformPushMsgService.coreService(request, platform.getAppId(), platform.getComponentValidateToken(), platform.getComponentSymmetricKey());
        }
        if(respMessage!=null){
            respMessage = "\""+respMessage+"\"";
        }else{
            respMessage="";
        }
            PrintWriter out = response.getWriter();
            out.print(respMessage);
            out.close();
    }

	// 全功能授权发起接口
	@RequestMapping(params = "auth", method = RequestMethod.GET)
	public ModelAndView auth() {
		String id = ResourceUtil.getWeiXinAccountId();
		WeixinAccountEntity account = this.weixinAccountService.getEntity(WeixinAccountEntity.class, id);
		WeixinOpenPlatformEntity platform = this.weixinOpenPlatformService.findByAppId(COMPONENT_APP_ID);
		account.setOpenPlatformId(platform.getId());
		this.weixinAccountService.saveOrUpdate(account);
		String preAuthCode = this.weixinOpenPlatformService.getPreAuthCode(platform.getId());
		String redirectUrl = String.format("https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%s&pre_auth_code=%s&redirect_uri=%s", COMPONENT_APP_ID, preAuthCode, ResourceUtil.getConfigByName("domain1")+ "/weixinOpenPlatform.do?authRedirect=" + id);
		/*try {
			redirectUrl = URLEncoder.encode(redirectUrl, "utf8");
		} catch (Exception e) {
			LOGGER.error(String.format("URLEncode 失败。 url:%s, error:%s", redirectUrl, e.getMessage()), e);
		}*/

		return new ModelAndView("redirect:" + redirectUrl);
	}

	// 单网页授权发起接口
	@RequestMapping(params = "auth2", method = RequestMethod.GET)
	public ModelAndView auth2() {
		String id = ResourceUtil.getWeiXinAccountId();
		WeixinAccountEntity account = this.weixinAccountService.getEntity(WeixinAccountEntity.class, id);
		WeixinOpenPlatformEntity platform = this.weixinOpenPlatformService.findByAppId(COMPONENT_APP_ID2);
		account.setOpenPlatformId(platform.getId());
		this.weixinAccountService.saveOrUpdate(account);
		String preAuthCode = this.weixinOpenPlatformService.getPreAuthCode(platform.getId());
		String redirectUrl = String.format("https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%s&pre_auth_code=%s&redirect_uri=%s", COMPONENT_APP_ID2, preAuthCode,ResourceUtil.getConfigByName("domain1") + "/weixinOpenPlatform.do?authRedirect=" + id);
		/*try {
			redirectUrl = URLEncoder.encode(redirectUrl, "utf8");
		} catch (Exception e) {
			LOGGER.error(String.format("URLEncode 失败。 url:%s, error:%s", redirectUrl, e.getMessage()), e);
		}*/

		return new ModelAndView("redirect:" + redirectUrl);
	}

	@RequestMapping(params = "authRedirect", method = RequestMethod.GET)
	public ModelAndView authRedirect(HttpServletRequest request) {
		String id = request.getParameter("authRedirect");
		String authCode = request.getParameter("auth_code");
		String expiresIn = request.getParameter("expires_in");
		if (StringUtils.isBlank(id)) {
			// 非法请求 跳转到主页
			return new ModelAndView("main/main");
		} else {
			WeixinAccountEntity account = this.weixinAccountService.getEntity(WeixinAccountEntity.class, id);
			if (StringUtils.isBlank(authCode)) {
				logger.info(String.format("微信账号第三方授权失败 . id:%s", id));
				account.setAuthorizationType("-1");
				this.weixinAccountService.saveOrUpdate(account);
				// 跳转到一个授权失败的页面
				return new ModelAndView("common/noweixinauthority");
			} else {
				this.weixinAccountService.fillWeixinAccountInfo(id, account.getOpenPlatformId(), authCode, Integer.valueOf(expiresIn));
				// 授权成功 刚才主页
				return new ModelAndView("main/main");
			}
		}
	}

    /**
     * 1.获取到商家的openId
     * 2.获取用户在商家绑定的手机号
     * 3.如果已经绑定了手机号, 继续原有的流程
     * 4.如果没有绑定手机号码, 获取石榴科技的openId, 获取石榴科技的手机号码
     * 5.石榴科技中有手机号码,绑定商家的openId和手机号码,返回手机号码,继续走已绑手机号码的流程
     * 6.石榴科技中没有绑定手机号码,继续原有的逻辑
     *
     * @param request
     * @return
     * @throws Exception
     */
	@RequestMapping(params = "pageAuth", method = RequestMethod.GET)
	public ModelAndView pageAuth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String appid = request.getParameter("appid");
		String type = request.getParameter("type");

        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            builder.append(entry.getKey()).append(":").append(Arrays.asList(entry.getValue())).append(",");
        }

        logger.info("WeixinOpenPlatformController.pageAuth:[{}]" + builder);
        if (StringUtils.isBlank(state)) {
            response.addHeader("Content-type", "text/plain");
            PrintWriter out = response.getWriter();
            out.print("error");
            out.flush();
            out.close();
            return null;
        }
        String accountId = PageAuthRedisCache.getAccountIdCache(state).getAccountId();
		String openId = "";
		WeixinAccountEntity account = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", accountId);
		if (StringUtils.isBlank(type) && PageAuthRedisCache.getOpenIdCache(state) == null) {
			// 0 普通公众号授权 1 第三方平台授权
			int authType = 0;
			if (StringUtils.isBlank(appid)) {
				logger.info("普通公众号网页授权");
			} else {
				logger.info("第三方平台网页授权");
				authType = 1;
			}
	
			WeixinOauth2Token token = null;
			if (authType == 1) {
				WeixinOpenPlatformEntity platform = weixinOpenPlatformService.get(WeixinOpenPlatformEntity.class, account.getOpenPlatformId());
				token = AdvancedUtil.getOauth2AccessToken(account.getAccountappid(), code, platform.getAppId(), weixinOpenPlatformService.getComponentAccessToken(platform.getId()));
			} else {
				token = AdvancedUtil.getOauth2AccessToken(account.getAccountappid(), account.getAccountappsecret(), code);
			}
            if (null == token) {
                logger.error("网页授权获取openId失败,请检查日志信息");
                return new ModelAndView("common/404");
            }
			openId = token.getOpenId();
			OpenIdCache cache = new OpenIdCache();
			cache.setOpenId(openId);
			cache.setLastUpdated(new Date());
			PageAuthRedisCache.setOpenIdCache(state, cache);
		} else {
			openId = PageAuthRedisCache.getOpenIdCache(state).getOpenId();
			// 2 认证订阅号 3 未认证服务号  4 未认证订阅号
			if (StringUtils.equalsIgnoreCase(account.getAccounttype(), "2") ||StringUtils.equalsIgnoreCase(account.getAccounttype(), "3") ||StringUtils.equalsIgnoreCase(account.getAccounttype(), "4")) {
				List<WeixinMemberEntity> member = this.weixinMemberService.findHql("from WeixinMemberEntity w where w.openId = ? and w.accountId = ?", openId, accountId);
				// openId不属于该公众号 跳转到二维码关注页
				if (CollectionUtils.isEmpty(member)) {
					Gson gson = new Gson();
					String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
					JSONObject myJson = new JSONObject();
					myJson.accumulate("id", account.getId());
					myJson.accumulate("opreateType", "关注");
					JSONObject contentBinding = HttpUtil.httpPost(url, myJson, false);
					String reStrBinding = gson.toJson(contentBinding);
					Type typeBinding = new TypeToken<MerchantInfoBean>() {
					}.getType();
					MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
					//request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);
					request.getSession().setAttribute("accountid",accountId);
					request.setAttribute("merchantInfoBean",merchantInfoBean);
					String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
					String impUrl = account.getQRcode();
					if(CheckPic.checkImg(impUrl)){
						impUrl = urlprefix + "/" + impUrl;
					}
					request.setAttribute("url", impUrl);
					if(!impUrl.equals(null)){
	                    String logoAccount =account.getLogoAccount();
	                    if(CheckPic.checkImg(logoAccount)){
	                    	logoAccount = urlprefix + "/" + logoAccount;
						}
	                    request.setAttribute("logo", logoAccount);
	                } else {
	                    request.setAttribute("logo", "plug-in/liuliangbao/css/0422/images/logo-1.png");
	                }
					request.setAttribute("accountName",account.getAccountname());
					return new ModelAndView("weixin/member/NoattentionPublicNum");
				}
			}
		}
		// [1].获取到商家的openId
		request.getSession().setAttribute("openId", openId);
		Map<String, String> property = PageAuthRedisCache.getPropertyCache(state).getProperties();   //获取到设置到property中的数据
        property.put("allowEmptyMobile", "false");
		PageAuthHandler handler = PageAuthRedisCache.getPageAuthHandlerCache(state).getHandler();
		

		Gson gson = new Gson();
        WeixinMemberEntity weixinMemberEntity = weixinMemberService.getWeixinMemberEntity(openId, accountId);

		String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
		JSONObject myJson = new JSONObject();
		myJson.accumulate("id", account.getId());
		myJson.accumulate("opreateType", "关注");
		JSONObject contentBinding = HttpUtil.httpPost(url, myJson, false);
		String reStrBinding = gson.toJson(contentBinding);
		Type typeBinding = new TypeToken<MerchantInfoBean>() {
		}.getType();
		MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);

        /**
         * 新增商户运营商流量类型的展示------xiaona
         */
        WeixinAccountEntity weixinAccountEntity=this.systemService.getEntity(WeixinAccountEntity.class,accountId);
        String acctId =weixinAccountEntity.getAcctId(); //获取的是商户的id
        WeixinMerchantCoverAreaEntity weixinMerchantCoverAreaEntity=null;   //null与"null"不是一回事
        weixinMerchantCoverAreaEntity=this.systemService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class,"accountID",acctId);
//        if("null".equals(weixinMerchantCoverAreaEntity))
        if(null==weixinMerchantCoverAreaEntity){
            weixinMerchantCoverAreaEntity=new WeixinMerchantCoverAreaEntity();  //防止为空时出现异常
        }else {
            String businessArea=weixinMerchantCoverAreaEntity.getBusinessArea();
            if(StringUtils.isBlank(businessArea)|| "三网通".equals(businessArea)){
                businessArea="所有运营商";
            }else {
                businessArea=weixinMerchantCoverAreaEntity.getBusinessArea();
            }
            request.setAttribute("businessArea",businessArea);
        }
        /**
         * 新增商户运营商流量类型的展示------xiaona
         */
		//request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);
		request.getSession().setAttribute("accountid",accountId);

        if (null != weixinMemberEntity && weixinMemberEntity.getSubscribe().equals("1")) {
            String phoneNumber = weixinMemberEntity.getPhoneNumber();
            String nickName = weixinMemberEntity.getNickName();
            String headImgUrl = weixinMemberEntity.getHeadImgUrl();
            // [2].获取用户在商家绑定的手机号
            PageAuthCallback callback = new PageAuthCallback();
            callback.setAccountId(accountId);
            callback.setOpenId(openId);
            callback.setProperties(property);
            PageAuthRedisCache.setPageAuthCallbackCache(state, callback);

            request.getSession().setAttribute("phoneNumber", phoneNumber);
            request.getSession().setAttribute("nickName", nickName);
            request.getSession().setAttribute("headImgUrl", headImgUrl);

            if (StringUtils.isBlank(phoneNumber)) {
                // [3].如果没有绑定手机号码, 获取石榴科技的openId, 获取石榴科技的手机号码
                String shiliuOpenId = weixinMemberEntity.getShiliuOpenId();
                if (StringUtils.isNotBlank(shiliuOpenId)) {
                    WeixinMemberEntity memberOfShiliu = this.weixinMemberService.getWeixinMemberEntity(shiliuOpenId, SHILIU_ACCOUNT_ID);

                    // 可以忽略是否关注的问题(因为在之前的业务中,已经自动跳转到关注页面了,如果还出现空指针等异常, 请检查之前代码的逻辑是否正确), 只获取手机号码即可
                    if (null != memberOfShiliu && StringUtils.isNotBlank(memberOfShiliu.getPhoneNumber())) {
                        // 石榴科技中有手机号码,绑定商家的openId和手机号码,返回手机号码,继续走已绑手机号码的流程
                        // weixinMemberEntity.setPhoneNumber(memberOfShiliu.getPhoneNumber());
                        // this.weixinMemberService.updateEntitie(weixinMemberEntity);
                        try {
                        	Map<String, Object> result = JFinalUtils.bind(openId, shiliuOpenId, "关注", memberOfShiliu.getPhoneNumber(), nickName, accountId, SHILIU_ACCOUNT_ID);
                            logger.info(MessageFormat.format("用户首次关注公众号并获取主公众号的手机号码以后,不在页面中仅在log中给予提示:{0}, " + memberOfShiliu.getPhoneNumber() + ", " + accountId, result));
                        } catch (Exception e) {
                            logger.error("用户在静默绑定时,赠送用户关注的流量时发生异常,赠送失败, 请检查jfinal服务是否正常启动", e);
                        }
                        request.getSession().setAttribute("phoneNumber", weixinMemberEntity.getPhoneNumber());
                    }
                    return handler.followAndBind(callback, request);
                }
                // 如果用户没有在商家的公众号中绑定手机号码, 则先获取石榴科技的openId, 并获取石榴科技的手机号码,
                return new ModelAndView("redirect:weixinOpenPlatform.do?getShiliuOpenId&state=" + state);
            } else {
                return handler.followAndBind(callback, request);
            }
        }
        //未关注进入二维码页面 TODO:流量赠送未读取
        request.setAttribute("merchantInfoBean",merchantInfoBean);
        String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
        
        //根据二维码的格式选择链接
        String qrcode = account.getQRcode();
        String accountLogo = account.getLogoAccount();
        if(CheckPic.checkImg(qrcode)){
        	qrcode = urlprefix + "/" +qrcode;
        }
        if(CheckPic.checkImg(accountLogo)){
        	accountLogo = urlprefix + "/" +accountLogo;
        }
        request.setAttribute("url",qrcode);
        request.setAttribute("urllogo", accountLogo);
        request.setAttribute("accountName",account.getAccountname());

        /**
         * 未关注的时候页面的跳转
         */
        String flowType = property.get("type");
        if("flowcard".equals(flowType)){
            return new ModelAndView("weixin/member/NoattentionPublicNumCard");
        }
        if(!qrcode.equals(null)){
            if(CheckPic.checkImg(accountLogo)){
            	accountLogo = urlprefix + "/" +accountLogo;
            }
            request.setAttribute("logo", accountLogo);
        } else {
            request.setAttribute("logo", "plug-in/liuliangbao/css/0422/images/logo-1.png");
        }
        return new ModelAndView("weixin/member/NoattentionPublicNum");
    }

    /**
     * 1.获取到商家的openId
     * 2.获取用户在商家绑定的手机号
     * 3.如果已经绑定了手机号, 继续原有的流程
     * 4.如果没有绑定手机号码, 通过网页授权,获取石榴科技的openId, 获取石榴科技的手机号码,
     * 5.石榴科技中有手机号码,绑定商家的openId和手机号码,继续走已绑手机号码的流程
     * 6.石榴科技中没有绑定手机号码,继续原有的逻辑,(重点:并且要带上石榴科技的openId,以便绑定手机号时,顺便将石榴科技的openId和手机号一块绑定了!!!, 不用再绑定的时候再次跳转获取石榴科技的openId了!)
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	@RequestMapping(params = "pageAuth2", method = RequestMethod.GET)
	public ModelAndView pageAuth2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            builder.append(entry.getKey()).append(":").append(Arrays.asList(entry.getValue())).append(",");
        }

        logger.info("WeixinOpenPlatformController.pageAuth2:[{}]" + builder);

		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String appid = request.getParameter("appid");
		String type = request.getParameter("type");
        if (StringUtils.isBlank(state)) {
            response.addHeader("Content-type", "text/plain");
            PrintWriter out = response.getWriter();
            out.print("error");
            out.flush();
            out.close();
            return null;
        }
        String accountId = PageAuthRedisCache.getAccountIdCache(state).getAccountId();
		String openId = "";
		WeixinAccountEntity account = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", accountId);
		if (StringUtils.isBlank(type) && PageAuthRedisCache.getOpenIdCache(state) == null) {
			// 0 普通公众号授权 1 第三方平台授权
			int authType = 0;
			if (StringUtils.isBlank(appid)) {
				logger.info("普通公众号网页授权");
			} else {
				logger.info("第三方平台网页授权");
				authType = 1;
			}
	
			WeixinOauth2Token token = null;
			if (authType == 1) {
				WeixinOpenPlatformEntity platform = weixinOpenPlatformService.get(WeixinOpenPlatformEntity.class, account.getOpenPlatformId());
				token = AdvancedUtil.getOauth2AccessToken(account.getAccountappid(), code, platform.getAppId(), weixinOpenPlatformService.getComponentAccessToken(platform.getId()));
			} else {
				token = AdvancedUtil.getOauth2AccessToken(account.getAccountappid(), account.getAccountappsecret(), code);
			}
            if (null == token) {
                logger.error("网页授权获取openId失败,请检查日志信息");
                return new ModelAndView("common/404");
            }
			openId = token.getOpenId();
			OpenIdCache cache = new OpenIdCache();
			cache.setOpenId(openId);
			cache.setLastUpdated(new Date());
			PageAuthRedisCache.setOpenIdCache(state, cache);
		} else {
			openId = PageAuthRedisCache.getOpenIdCache(state).getOpenId();
			// 2 认证订阅号 3 未认证服务号  4 未认证订阅号
			if (account.getAccounttype().equalsIgnoreCase("2") || account.getAccounttype().equalsIgnoreCase("3") || account.getAccounttype().equalsIgnoreCase("4")) {
				List<WeixinMemberEntity> member = this.weixinMemberService.findHql("from WeixinMemberEntity w where w.openId = ? and w.accountId = ?", openId, accountId);
				// openId不属于该公众号 跳转到二维码关注页
				if (CollectionUtils.isEmpty(member)) {
					Gson gson = new Gson();
					String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
					JSONObject myJson = new JSONObject();
					myJson.accumulate("id", account.getId());
					myJson.accumulate("opreateType", "关注");
					JSONObject contentBinding = HttpUtil.httpPost(url, myJson, false);
					String reStrBinding = gson.toJson(contentBinding);
					Type typeBinding = new TypeToken<MerchantInfoBean>() {
					}.getType();
					MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
					//request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);
					request.getSession().setAttribute("accountid",accountId);
					request.setAttribute("merchantInfoBean",merchantInfoBean);
					String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
					String impUrl = account.getQRcode();
					if(CheckPic.checkImg(impUrl )){
						impUrl = urlprefix + "/" + impUrl;
					}
					request.setAttribute("url", impUrl);
					if(!impUrl.equals(null)){
						String logoAccount = account.getLogoAccount();
						if(CheckPic.checkImg(logoAccount )){
							logoAccount = urlprefix + "/" + logoAccount;
						}
	                    request.setAttribute("logo", logoAccount);
	                } else {
	                    request.setAttribute("logo", "plug-in/liuliangbao/css/0422/images/logo-1.png");
	                }
					request.setAttribute("accountName",account.getAccountname());
					return new ModelAndView("weixin/member/NoattentionPublicNum");
				}
			}
		}
        // 1.获取到商家的openId
		request.getSession().setAttribute("openId", openId);
		Map<String, String> property = PageAuthRedisCache.getPropertyCache(state).getProperties();
        property.put("allowEmptyMobile", "true");
        PageAuthHandler handler = PageAuthRedisCache.getPageAuthHandlerCache(state).getHandler();


		Gson gson = new Gson();
        WeixinMemberEntity weixinMemberEntity = weixinMemberService.getWeixinMemberEntity(openId, accountId);

		String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
		JSONObject myJson = new JSONObject();
		myJson.accumulate("id", account.getId());
		myJson.accumulate("opreateType", "关注");
		JSONObject contentBinding = HttpUtil.httpPost(url, myJson, false);
		String reStrBinding = gson.toJson(contentBinding);
		Type typeBinding = new TypeToken<MerchantInfoBean>() {
		}.getType();
		MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
		request.getSession().setAttribute("accountid",accountId);
        if (null != weixinMemberEntity && weixinMemberEntity.getSubscribe().equals("1")) {
            String phoneNumber = weixinMemberEntity.getPhoneNumber();
            String nickName = weixinMemberEntity.getNickName();
            String headImgUrl = weixinMemberEntity.getHeadImgUrl();
            // 2.获取用户在商家绑定的手机号
            PageAuthCallback callback = new PageAuthCallback();
            callback.setAccountId(accountId);
            callback.setOpenId(openId);
            callback.setProperties(property);
            PageAuthRedisCache.setPageAuthCallbackCache(state, callback);

            request.getSession().setAttribute("phoneNumber", phoneNumber);
            request.getSession().setAttribute("nickName", nickName);
            request.getSession().setAttribute("headImgUrl", headImgUrl);

            if (StringUtils.isBlank(phoneNumber)) {
                String shiliuOpenId = weixinMemberEntity.getShiliuOpenId();
                if (StringUtils.isNotBlank(shiliuOpenId)) {
                    WeixinMemberEntity memberOfShiliu = this.weixinMemberService.getWeixinMemberEntity(shiliuOpenId, SHILIU_ACCOUNT_ID);

                    // 可以忽略是否关注的问题(因为在之前的业务中,已经自动跳转到关注页面了,如果还出现空指针等异常, 请检查之前代码的逻辑是否正确), 只获取手机号码即可
                    if (null != memberOfShiliu && StringUtils.isNotBlank(memberOfShiliu.getPhoneNumber())) {
                        // 石榴科技中有手机号码,绑定商家的openId和手机号码,返回手机号码,继续走已绑手机号码的流程
                        // weixinMemberEntity.setPhoneNumber(memberOfShiliu.getPhoneNumber());
                        // this.weixinMemberService.updateEntitie(weixinMemberEntity);
                        try {
                            // String userFlowMessage = this.userGiveFlowService.giveUserFlow(openId, memberOfShiliu.getPhoneNumber(), accountId, "关注");
                        	Map<String, Object> result = JFinalUtils.bind(openId, shiliuOpenId, "关注", memberOfShiliu.getPhoneNumber(), nickName, accountId, SHILIU_ACCOUNT_ID);
                            logger.info(MessageFormat.format("用户首次关注公众号并获取主公众号的手机号码以后,不在页面中仅在log中给予提示:{0}, " + memberOfShiliu.getPhoneNumber() + ", " + accountId, result));
                        } catch (Exception e) {
                            logger.error("用户在静默绑定时,赠送用户关注的流量时发生异常,赠送失败, 请检查jfinal服务是否正常启动", e);
                        }
                        request.getSession().setAttribute("phoneNumber", weixinMemberEntity.getPhoneNumber());
                    }
                    return handler.follow(callback, request);
                }
                // 如果用户没有在商家的公众号中绑定手机号码, 则先获取石榴科技的openId, 并获取石榴科技的手机号码,
                return new ModelAndView("redirect:weixinOpenPlatform.do?getShiliuOpenId&state=" + state);
            } else {
                return handler.follow(callback, request);
            }
        }
        //未关注进入二维码页面 TODO:流量赠送未读取
        request.setAttribute("merchantInfoBean",merchantInfoBean);
        String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
        String impUrl = account.getQRcode();
        if(CheckPic.checkImg(impUrl)){
        	impUrl = urlprefix + "/" + impUrl;
        }
        request.setAttribute("url", impUrl);
        if(!impUrl.equals(null)){
            String logoAccount = account.getLogoAccount();
            if(CheckPic.checkImg(logoAccount)){
            	logoAccount = urlprefix + "/" + logoAccount;
            }
            request.setAttribute("logo", logoAccount);
        } else {
            request.setAttribute("logo", "plug-in/liuliangbao/css/0422/images/logo-1.png");
        }
        request.setAttribute("accountName",account.getAccountname());
        return new ModelAndView("weixin/member/NoattentionPublicNum");
    }

    @RequestMapping(params = "getShiliuOpenId")
    public ModelAndView getShiliuOpenId(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            builder.append(entry.getKey()).append(":").append(Arrays.asList(entry.getValue())).append(",");
        }

        logger.info("WeixinOpenPlatformController.getShiliuOpenId:" + builder);
        try {
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
            String previousState = request.getParameter("state");

            if (StringUtils.isNotBlank(previousState)) {
                String domainbase=ResourceUtil.getConfigByName("domain");
                String redirectURL = URLEncoder.encode(domainbase+"/weixinOpenPlatform.do?shiliuOpenIdCallback", "utf-8");
                url = String.format(url, SHILIU_APP_ID, redirectURL, "snsapi_base"/*scope*/, previousState/*state*/);
                logger.info("访问微信的接口地址如下:" + url);
                return new ModelAndView("redirect:" + url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("common/404");
    }

    @RequestMapping(params = "shiliuOpenIdCallback")
    public ModelAndView shiliuOpenIdCallback(HttpServletRequest request) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            builder.append(entry.getKey()).append(":").append(Arrays.asList(entry.getValue())).append(",");
        }
        logger.info("WeixinOpenPlatformController.shiliuOpenIdCallback:[{}]" + builder);

        String code = request.getParameter("code");
        String previousState = request.getParameter("state");

        PageAuthCallback pageAuthCallback = PageAuthRedisCache.getPageAuthCallbackCache(previousState);
        PageAuthHandlerCache pageAuthHandlerCache = PageAuthRedisCache.getPageAuthHandlerCache(previousState);

        WeixinOauth2Token token = AdvancedUtil.getOauth2AccessToken(SHILIU_APP_ID, SHILIU_SECRET, code);
        if (null != token && StringUtils.isNotBlank(token.getOpenId())) {
            String shiliuOpenId = token.getOpenId();
            logger.info(MessageFormat.format("拾流科技的openId->{0}, 商家公众号的openId->{1}", shiliuOpenId, pageAuthCallback.getOpenId()));
            WeixinMemberEntity memberOfShiliu = this.weixinMemberService.getWeixinMemberEntity(shiliuOpenId, SHILIU_ACCOUNT_ID);
            WeixinMemberEntity memberOfAccount = this.weixinMemberService.getWeixinMemberEntity(pageAuthCallback.getOpenId(), pageAuthCallback.getAccountId());

            if (StringUtils.isNotBlank(shiliuOpenId) && StringUtils.isNotBlank(pageAuthCallback.getOpenId())) {
                // RedisUtil.setRedis(pageAuthCallback.getOpenId(), PageAuthRedisCache.KEY_EXPIRE_TIME_ONE_WEEK, shiliuOpenId);
                memberOfAccount.setShiliuOpenId(shiliuOpenId);
                this.weixinMemberService.updateEntitie(memberOfAccount);
            }

            // 可以忽略是否关注的问题(因为在之前的业务中,已经自动跳转到关注页面了,如果还出现空指针等异常, 请检查之前代码的逻辑是否正确), 只获取手机号码即可
            if (null != memberOfShiliu && StringUtils.isNotBlank(memberOfShiliu.getPhoneNumber())) {
                // 石榴科技中有手机号码,绑定商家的openId和手机号码,返回手机号码,继续走已绑手机号码的流程
                if (null != memberOfAccount && StringUtils.isBlank(memberOfAccount.getPhoneNumber())) {
//                    memberOfAccount.setPhoneNumber(memberOfShiliu.getPhoneNumber());
//                    this.weixinMemberService.updateEntitie(memberOfAccount);
                    try {
                        // String userFlowMessage = this.userGiveFlowService.giveUserFlow(pageAuthCallback.getOpenId(), memberOfAccount.getPhoneNumber(), memberOfAccount.getAccountId(), "关注");
                    	Map<String, Object> result = JFinalUtils.bind(pageAuthCallback.getOpenId(), shiliuOpenId, "关注", memberOfShiliu.getPhoneNumber(), memberOfAccount.getNickName(), pageAuthCallback.getAccountId(), SHILIU_ACCOUNT_ID);
                        logger.info(MessageFormat.format("用户首次关注公众号并获取主公众号的手机号码以后,不在页面中仅在log中给予提示:{0}.", result));
                    } catch (Exception e) {
                        logger.error("用户在静默绑定时,赠送用户关注的流量时发生异常,赠送失败, 请检查jfinal服务是否正常启动", e);
                    }
                    request.getSession().setAttribute("phoneNumber", memberOfAccount.getPhoneNumber());
                    request.getSession().setAttribute("nickName", memberOfAccount.getNickName());
                    request.getSession().setAttribute("headImgUrl", memberOfAccount.getHeadImgUrl());
                }
            }
        } else {
            logger.error("获取拾流科技的openId发生异常，具体信息请查看上方的日志信息");
        }

        if ("true".equals(pageAuthCallback.getProperties().get("allowEmptyMobile"))) {
            return pageAuthHandlerCache.getHandler().follow(pageAuthCallback, request);
        } else {
            return pageAuthHandlerCache.getHandler().followAndBind(pageAuthCallback, request);
        }
    }
	
	public static class PageAuthCache {
		private Date lastUpdated;

		public Date getLastUpdated() {
			return lastUpdated;
		}

		public void setLastUpdated(Date lastUpdated) {
			this.lastUpdated = lastUpdated;
		}
	}
	
	public static class PageAuthPropertyCache extends PageAuthCache implements Serializable {
		private Map<String, String> properties;

		public Map<String, String> getProperties() {
			return properties;
		}

		public void setProperties(Map<String, String> properties) {
			this.properties = properties;
		}
	}
	
	public static class AccountIdCache extends PageAuthCache implements Serializable {
		private String accountId;
        private String shiliuAccountId;

		public String getAccountId() {
			return accountId;
		}

		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

        public String getShiliuAccountId() {
            return shiliuAccountId;
        }

        public void setShiliuAccountId(String shiliuAccountId) {
            this.shiliuAccountId = shiliuAccountId;
        }
    }

	public static class PageAuthHandlerCache extends PageAuthCache implements Serializable {
		private PageAuthHandler handler;

		public PageAuthHandler getHandler() {
			return handler;
		}

		public void setHandler(PageAuthHandler handler) {
			this.handler = handler;
		}
	}

	public static class PageAuthCallbackCache extends PageAuthCache implements Serializable {
		private PageAuthCallback pageAuthCallback;

        public PageAuthCallback getPageAuthCallback() {
            return pageAuthCallback;
        }

        public void setPageAuthCallback(PageAuthCallback pageAuthCallback) {
            this.pageAuthCallback = pageAuthCallback;
        }
    }

	public static class OpenIdCache extends PageAuthCache implements Serializable {
		private String openId;
        private String shiliuOpenId;

        public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}

        public void setShiliuOpenId(String shiliuOpenId) {
            this.shiliuOpenId = shiliuOpenId;
        }

        public String getShiliuOpenId() {
            return shiliuOpenId;
        }
    }
}


