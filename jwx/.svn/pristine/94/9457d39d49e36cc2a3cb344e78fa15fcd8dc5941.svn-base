package weixin.liuliangbao.controller.flowcontroller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sdk.jfinal.JFinalUtils;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.util.CheckPic;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.UserFlowAccountBean;
import weixin.liuliangbao.service.flowcontroller.BindingService;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.oauth2.AdvancedUtil;
import weixin.oauth2.WeixinOauth2Token;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.util.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by aa on 2015/12/4.
 */
@Scope("prototype")
@Controller
@RequestMapping("/bindingController")
public class BindingController extends BaseController implements PageAuthHandler {
    private static final Logger LOGGER = Logger.getLogger(MainController.class);
    private String path = ResourceUtil.getConfigByName("jfinalUrl-config");
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;


    @RequestMapping(params = "load")
    public ModelAndView load(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();//获取开始当前的时间
        StringBuffer buffer = new StringBuffer();
        String url = "";
        try {
            String accountid = request.getParameter("accountid");
            String openId = request.getParameter("openId");
            LOGGER.info("绑定页的Load方法开始" + accountid + "__openid__" + openId);
            buffer.append("bindingController_load_begin:_accountid_" + accountid + "_openId_" + openId);
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("accountid", accountid);  //活动ID，传给后面用

            if (StringUtils.isBlank(openId)) {
                url = weixinAccountService.pageAuth2(accountid, properties, this);   //调用授权封装：商户ID，
            } else {
                url = weixinAccountService.pageAuth2(accountid, properties, this, openId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();//获取结束的当前时间
            LOGGER.info("绑定页的Load方法运行时间：----" + (endTime - startTime) + "ms");
            buffer.append("bindingController_load_end_time:" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            return new ModelAndView("redirect:" + url);
        }
    }

    @RequestMapping(params = "goBinding")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView goBinding(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();//获取开始当前的时间

        String accountid = request.getParameter("state");
        if (accountid == null || "".equals(accountid)) {
            return new ModelAndView("liuliangbao/flowmanager/noMain");//TODO:404
        }
        String hdNotUrl = "liuliangbao/flowmanager/noMain";//TODO:404
        String code = request.getParameter("code");
        if (!"authdeny".equals(code)) {
            WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
            /**
             * 商户的运营商流量类型------xiaona--2016年4月30日
             */
            String acctId = account.getAcctId();
            WeixinMerchantCoverAreaEntity weixinMerchantCoverAreaEntity = null;
            weixinMerchantCoverAreaEntity = this.systemService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", acctId);
            if (null == weixinMerchantCoverAreaEntity) {
                weixinMerchantCoverAreaEntity = new WeixinMerchantCoverAreaEntity();  //防止为空时出现异常
            } else {
                String businessArea = weixinMerchantCoverAreaEntity.getBusinessArea();
                if (StringUtils.isBlank(businessArea) || "三网通".equals(businessArea)) {
                    businessArea = "所有运营商";
                } else {
                    businessArea = weixinMerchantCoverAreaEntity.getBusinessArea();
                }
                request.setAttribute("businessArea", businessArea);
            }
            /**
             * 商户的运营商流量类型------xiaona--2016年4月30日
             */
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(account.getAccountappid(), account.getAccountappsecret(), code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();

            WeixinMemberEntity memberEntity = this.systemService.findUniqueByProperty(WeixinMemberEntity.class, "openId", openId);
            Gson gson = new Gson();
            String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
            JSONObject myJson = new JSONObject();
            myJson.accumulate("id", account.getId());
            myJson.accumulate("opreateType", StringUtils.defaultString(request.getParameter("operateType"), "关注"));
            JSONObject contentBinding = HttpUtil.httpPost(url, myJson, false);
            String reStrBinding = gson.toJson(contentBinding);
            Type typeBinding = new TypeToken<MerchantInfoBean>() {
            }.getType();
            MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
            if (memberEntity == null) {
                //未关注进入二维码页面 TODO:流量赠送未读取
                request.setAttribute("flowValue", merchantInfoBean.getData().get(0).getCountryFlowValue());
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
                request.setAttribute("accountName", account.getAccountname());
                return new ModelAndView("weixin/member/NoattentionPublicNum");
            } else {
                if (memberEntity.getSubscribe().equals("0")) {
                    //未关注进入二维码页面 TODO:流量赠送未读取
                    request.setAttribute("flowValue", merchantInfoBean.getData().get(0).getCountryFlowValue());
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
                    request.setAttribute("accountName", account.getAccountname());
                    return new ModelAndView("weixin/member/NoattentionPublicNum");
                } else {
                    if (memberEntity.getPhoneNumber().equals("") || memberEntity.getPhoneNumber() == null) {
                        request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);
                        long endTime = System.currentTimeMillis();//获取结束的当前时间
                        LOGGER.info("绑定的goBinding方法运行时间：----" + (endTime - startTime) + "ms");
                        return new ModelAndView("liuliangbao/flowmanager/binding");

                    } else {
                        String redirectUrl = "mainController.do?load&accountid=" + accountid;
                        return new ModelAndView("redirect:" + redirectUrl);
                    }

                }
            }

        } else {
            return new ModelAndView(hdNotUrl);
        }
    }

    /**
     * 跳转到绑定手机号码的页面
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "redirectBinding")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView redirectBinding(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();//获取开始当前的时间

        String queryString = request.getQueryString();
        String previousOperateType = null;
        String nickName = null;

        // 处理url地址中的中文字符串
        if (StringUtils.isNotBlank(queryString)) {
            String[] parameterNames = queryString.split("&");
            if (parameterNames.length > 0) {
                for (String parameterName : parameterNames) {
                    try {
                        if (parameterName.startsWith("operateType=")) {
                            previousOperateType = URLDecoder.decode(parameterName.substring(parameterName.indexOf("=") + 1), "UTF-8");
                        } else if (parameterName.startsWith("nickName=")) {
                            nickName = URLDecoder.decode(parameterName.substring(parameterName.indexOf("=") + 1), "UTF-8");
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        String accountid = request.getParameter("merchantId");
        if (StringUtils.isBlank(accountid)) {
            accountid = (String) request.getAttribute("merchantId");
        }
        String openId = request.getParameter("openId");
        if (StringUtils.isBlank(openId)) {
            openId = ((String) request.getAttribute("openId"));
        }
        if (StringUtils.isBlank(previousOperateType)) {
            previousOperateType = (String) request.getAttribute("operateType");
        }
        previousOperateType = StringUtils.defaultString(previousOperateType, "关注");

        if (StringUtils.isBlank(nickName)) {
            nickName = (String) request.getAttribute("nickName");
        }

        WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
        WeixinMemberEntity memberEntity = this.weixinMemberService.getWeixinMember(openId, accountid);

        request.setAttribute("accountid", accountid);
        request.setAttribute("openId", openId);
        request.setAttribute("operateType", previousOperateType);
        request.setAttribute("nickName", nickName);
        request.setAttribute("accountName", account.getAccountname());
        request.setAttribute("shiliuOpenId", null != memberEntity ? memberEntity.getShiliuOpenId() : "");
        String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
        String impUrl = account.getLogoAccount();
        if(CheckPic.checkImg(impUrl)){
        	impUrl = urlprefix + "/" + impUrl;
        }
        request.setAttribute("url", impUrl);
        Gson gson = new Gson();
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
        JSONObject myJson = new JSONObject().element("id", account.getId()).element("opreateType", "关注");
        String flowRuleResult = gson.toJson(HttpUtil.httpPost(url, myJson, false));
        Type token = new TypeToken<MerchantInfoBean>() {
        }.getType();

        MerchantInfoBean merchantInfoBean = gson.fromJson(flowRuleResult, token);
        if (memberEntity == null || "0".equals(memberEntity.getSubscribe())) {
            DataSourceType ds = DataSourceSwitcher.getDataSource();
            request.setAttribute("flowValue", merchantInfoBean.getData().get(0).getCountryFlowValue());
            String mediaURL = ResourceUtil.getConfigByName("media.url.prefix");
            String QRcode = account.getQRcode();
            if(CheckPic.checkImg(QRcode)){
            	QRcode = mediaURL + "/" + QRcode;
            }
            request.setAttribute("url", QRcode);
            if(!QRcode.equals(null)){
                String logoAccount = account.getLogoAccount();
                if(CheckPic.checkImg(logoAccount)){
                	logoAccount = mediaURL + "/" + logoAccount;
                }
                request.setAttribute("logo", logoAccount);
            } else {
                request.setAttribute("logo", "plug-in/liuliangbao/css/0422/images/logo-1.png");
            }
            return new ModelAndView("weixin/member/NoattentionPublicNum");
        } else if (StringUtils.isBlank(memberEntity.getPhoneNumber())) {
            request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);
            request.getSession().setAttribute("reURL", request.getParameter("redirectURL"));
            long endTime = System.currentTimeMillis();//获取结束的当前时间
            LOGGER.info("绑定的redirectBinding方法运行时间：----" + (endTime - startTime) + "ms");
            return new ModelAndView("liuliangbao/flowmanager/binding");
        } else {
            LOGGER.info("bindingController_go_mainController_accountid_" + accountid + "_openid_" + openId);
            LOGGER.info("绑定页的跳转主页的方法" + accountid + "__openid__" + openId);
            String redirectUrl = "mainController.do?load&accountid=" + accountid + "&openId=" + openId;
            return new ModelAndView("redirect:" + redirectUrl);
        }
    }


    @RequestMapping(params = "binding")
    @ResponseBody
    public void binding(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();//获取开始当前的时间
        StringBuffer buffer = new StringBuffer();
        HttpSession session = request.getSession(true);
        StringBuffer logBuffer = new StringBuffer();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrKey = attributeNames.nextElement();
            logBuffer.append(attrKey).append("=").append(session.getAttribute(attrKey)).append(";");
        }
        LOGGER.info("BindingController.binding:session:" + logBuffer);
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

            String phoneNumbeqr = ((String) session.getAttribute("phoneNumber"));
            String codeold = ((String) session.getAttribute("code"));
            String code = request.getParameter("phoneCode");
            String openId = request.getParameter("openId");
            String operateType = StringUtils.defaultString(request.getParameter("operateType"), "关注");
            String phoneNumber = request.getParameter("phoneNumber");
            String userName = request.getParameter("nickname");
            String merchantID = request.getParameter("accountid");
            String shiliuOpenId = request.getParameter("shiliuOpenId");

            buffer.append("phoneNumbqer_" + phoneNumbeqr + "_oldCode_" + codeold + "_newCode_" + code + "_phoneNumber_" + phoneNumber + "_openid_" + openId + "_accountid_" + merchantID);
            if (!(Objects.equals(code, codeold) && Objects.equals(phoneNumbeqr, phoneNumber))) {
                map.put("flag", false);
                map.put("msg", "手机号与验证码不匹配，请重新输入！");
            } else {
                session.removeAttribute("code");
                map = JFinalUtils.bind(openId, shiliuOpenId, operateType, phoneNumbeqr, userName, merchantID, ResourceUtil.getShiliuAccountId());
            }
            response.getWriter().write(new ObjectMapper().writeValueAsString(map));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            buffer.append("bingdingController_end_time:" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
        }
    }

    @RequestMapping(params = "sendMessage")
    @ResponseBody
    public void sendMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        buffer.append("bingdingController_sendMessage_begin_");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            AjaxJson j = new AjaxJson();
            HttpSession session = request.getSession();
            String phoneNumber = request.getParameter("phoneNumber");
            buffer.append("_phoneNumber_" + phoneNumber);
            Gson gson = new Gson();
            String url = path + "userGetFlow/GetCode";
            JSONObject myJsonObject = new JSONObject();
            myJsonObject.accumulate("id", phoneNumber);
            JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
            Type type = new TypeToken<UserFlowAccountBean>() {
            }.getType();
            String reStr = gson.toJson(content);
            UserFlowAccountBean userFlowAccountBean = gson.fromJson(reStr, type);

            if (userFlowAccountBean == null) {
                map.put("flag", "false");
                buffer.append("sendFlag_false");
            } else {
                if (userFlowAccountBean.getCode() == 200) {
                    j.setSuccess(true);
                    map.put("flag", "true");
                    session.setAttribute("phoneNumber", userFlowAccountBean.getData().getId());//手机号
                    session.setAttribute("code", userFlowAccountBean.getData().getUserID());//验证码
                    buffer.append("_code_" + userFlowAccountBean.getData().getUserID());
                    System.out.println("--------------------------->手机号:"+userFlowAccountBean.getData().getId()
                    		+"验证码:"+userFlowAccountBean.getData().getUserID()
                    		+"返回值:"+userFlowAccountBean.getData().getUserName());
                    session.setMaxInactiveInterval(100);//100s
                    session.setAttribute("codeTimes", 3);
                } else {
                    map.put("flag", "false");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            buffer.append("bingdingController_sendMessage_end_time:" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            String json = objectMapper.writeValueAsString(map);
            LOGGER.info(json);
            PrintWriter out = response.getWriter();
            out.write(json);
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

    public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request, boolean allowNotBindPhoneNumber) {
        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        String redirectUrl = "";
        try {
            String accountid = ((String) request.getSession().getAttribute("accountid"));
            String openId = ((String) request.getSession().getAttribute("openId"));
            String nickName = ((String) request.getSession().getAttribute("nickName"));
            String nickname = (String) request.getSession().getAttribute("nickname");
            nickName = StringUtils.isNotBlank(nickName) ? nickName : nickname;
            String operateType = StringUtils.defaultString(((String) request.getSession().getAttribute("operateType")), "关注");
            request.setAttribute("merchantId", accountid);
            request.setAttribute("openId", openId);
            request.setAttribute("nickName", nickName);
            request.setAttribute("operateType", operateType);
            return this.redirectBinding(request);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:" + redirectUrl);
        } finally {
            long endTime = System.currentTimeMillis();
            buffer.append("end_time:" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
        }
    }
}
