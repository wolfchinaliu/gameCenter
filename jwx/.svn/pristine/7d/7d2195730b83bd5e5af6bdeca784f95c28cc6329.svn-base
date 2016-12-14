package weixin.liuliangbao.controller.flowcontroller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinOpenPlatformEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.service.WeixinOpenPlatformServiceI;
import weixin.guanjia.account.util.CheckPic;
import weixin.guanjia.core.util.PageAuthRedisCache;
import weixin.guanjia.openplatform.controller.WeixinOpenPlatformController;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.jsonbean.WeixinMainEntity;
import weixin.liuliangbao.util.HttpUtil;
import weixin.liuliangbao.weigatedoor.entity.WeidoorpptEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.oauth2.AdvancedUtil;
import weixin.oauth2.WeixinOauth2Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by aa on 2015/12/4.
 */
@Scope("prototype")
@Controller
@RequestMapping("/mainController")
public class MainController extends BaseController implements PageAuthHandler {
    private String path = ResourceUtil.getConfigByName("jfinalUrl-config");
    private static final Logger LOGGER = Logger.getLogger(MainController.class);
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinOpenPlatformServiceI weixinOpenPlatformService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;


    @RequestMapping(params = "center")
    public ModelAndView center(HttpServletRequest request) {
        return new ModelAndView("liuliangbao/flowmanager/FlowCenter");
    }

    @RequestMapping(params = "load")
    public ModelAndView load(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();//获取开始当前的时间
        String accountid = request.getParameter("accountid");
        String openId = request.getParameter("openId");
        StringBuffer buffer = new StringBuffer();
        buffer.append("mainController_load:__accountid__" + accountid + "__openId__" + openId);
        LOGGER.info(buffer.toString());
        String redirectUrl = "";
        try {
            if (StringUtils.isBlank(openId)) {
                redirectUrl = pageAuth(accountid);
            } else {
                redirectUrl = pageAuth(accountid, openId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();//获取结束的当前时间
            buffer.append("redirectUrl__" + redirectUrl);
            buffer.append("time:__" + (endTime - startTime));
            LOGGER.info("主页的Load方法运行时间：----" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            return new ModelAndView("redirect:" + redirectUrl);
        }

    }

    @RequestMapping(params = "goRedirectMain")
//    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView goRedirectMain(HttpServletRequest request) throws Exception {
        long startTime = System.currentTimeMillis();//获取开始当前的时间
        StringBuffer buffer = new StringBuffer();

        buffer.append("mainController_goRedirectMain_");
        String code = request.getParameter("code");
        buffer.append("code_" + code);
        String state = request.getParameter("state");
        buffer.append("_state_" + state);
        String appid = request.getParameter("appid");
        buffer.append("_appid_" + appid);
        String type = request.getParameter("type");
        buffer.append("_type_" + type);
        String accountid = PageAuthRedisCache.getAccountIdCache(state).getAccountId();
        buffer.append("_accountid_" + accountid);
        String openId = "";
        WeixinAccountEntity account = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", accountid);
        if (StringUtils.isBlank(type)) {
            // 0 普通公众号授权 1 第三方平台授权
            int authType = 0;
            if (StringUtils.isBlank(appid)) {
                LOGGER.info("mainController_goRedirectMain_普通公众号网页授权");
            } else {
                LOGGER.info("mainController_goRedirectMain_第三方平台网页授权");
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
                LOGGER.warn(MessageFormat.format("无法获取APPId为:{0}微信的token", account.getAccountappid()));
                return new ModelAndView("common/404");
            }
            openId = token.getOpenId();
            buffer.append("_type_isBlank_openId" + openId);
        } else {
            openId = PageAuthRedisCache.getOpenIdCache(state).getOpenId();
            buffer.append("_type_isNotBlank_openId_" + openId);
            // 2 认证订阅号 3 未认证服务号  4 未认证订阅号
            if (account.getAccounttype().equalsIgnoreCase("2") || account.getAccounttype().equalsIgnoreCase("3") || account.getAccounttype().equalsIgnoreCase("4")) {
                List<WeixinMemberEntity> member = this.weixinMemberService.findHql("from WeixinMemberEntity w where w.openId = ? and w.accountId = ?", openId, accountid);
                // openId不属于该公众号 跳转到二维码关注页
                if (member != null && member.size() == 0) {
                    try {
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
                        request.getSession().setAttribute("accountid", accountid);
                        request.setAttribute("merchantInfoBean", merchantInfoBean);
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        buffer.append("_accountName_" + account.getAccountname());
                        long endTime = System.currentTimeMillis();//获取结束的当前时间
                        buffer.append("_Go_NoattentionPublicNum_time:__" + (endTime - startTime) + "ms");
                        LOGGER.info(buffer.toString());
                        return new ModelAndView("weixin/member/NoattentionPublicNum");
                    }

                }
            }
        }

        request.getSession().setAttribute("openId", openId);

        WeixinMemberEntity memberEntity = null;
        Gson gson = new Gson();
        String hql = "from WeixinMemberEntity t where t.accountId='" + accountid + "' and t.openId='" + openId + "'";
        List<WeixinMemberEntity> weixinMemberList = weixinMemberService.findByQueryString(hql);
        if (weixinMemberList != null && weixinMemberList.size() > 0) {
            memberEntity = weixinMemberList.get(0);
        }

        String hdNotUrl = "liuliangbao/flowmanager/noMain";//TODO:404

        if (memberEntity == null) {
            String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
            JSONObject myJson = new JSONObject();
            myJson.accumulate("id", account.getId());
            myJson.accumulate("opreateType", "关注");
            JSONObject contentBinding = HttpUtil.httpPost(url, myJson, false);
            String reStrBinding = gson.toJson(contentBinding);
            Type typeBinding = new TypeToken<MerchantInfoBean>() {
            }.getType();
            MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
            if (merchantInfoBean != null) {
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
                request.setAttribute("merchantInfoBean", merchantInfoBean);
                buffer.append("_accountName_" + account.getAccountname());
                long endTime = System.currentTimeMillis();//获取结束的当前时间
                buffer.append("_Go_NoattentionPublicNum_time:__" + (endTime - startTime) + "ms");
                LOGGER.info(buffer.toString());
                return new ModelAndView("weixin/member/NoattentionPublicNum");
            } else {
                return new ModelAndView(hdNotUrl);
            }

        } else {
            if (memberEntity.getSubscribe().equals("0")) {

                String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/QueryFlowRule";
                JSONObject myJson = new JSONObject();
                myJson.accumulate("id", account.getId());
                myJson.accumulate("opreateType", "关注");
                JSONObject contentBinding = HttpUtil.httpPost(url, myJson, false);
                String reStrBinding = gson.toJson(contentBinding);
                Type typeBinding = new TypeToken<MerchantInfoBean>() {
                }.getType();
                MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
                if (merchantInfoBean != null) {

                    //未关注进入二维码页面 TODO:流量赠送未读取
                    request.setAttribute("flowValue", merchantInfoBean.getData().get(0).getCountryFlowValue());
                    String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
                    String impUrl = account.getQRcode();
                    if(CheckPic.checkImg(impUrl)){
                    	impUrl =  urlprefix + "/" + impUrl;
                    }
                    request.setAttribute("url", impUrl);
                    if(!impUrl.equals(null)){
                        String logoAccount = account.getLogoAccount();
                        if(CheckPic.checkImg(logoAccount)){
                        	logoAccount =  urlprefix + "/" + logoAccount;
                        }
                        request.setAttribute("logo", logoAccount);
                    } else {
                        request.setAttribute("logo", "plug-in/liuliangbao/css/0422/images/logo-1.png");
                    }
                    request.setAttribute("accountName", account.getAccountname());
                    request.setAttribute("merchantInfoBean", merchantInfoBean);
                    buffer.append("_accountName_" + account.getAccountname());
                    long endTime = System.currentTimeMillis();//获取结束的当前时间
                    buffer.append("_Go_NoattentionPublicNum_time:__" + (endTime - startTime) + "ms");
                    LOGGER.info(buffer.toString());
                    return new ModelAndView("weixin/member/NoattentionPublicNum");
                } else {
                    return new ModelAndView(hdNotUrl);
                }
            } else {
                request.getSession().setAttribute("openId", openId);
                request.getSession().setAttribute("accountid", accountid);
                request.getSession().setAttribute("headimgUrl", memberEntity.getHeadImgUrl());
                long endTime = System.currentTimeMillis();//获取结束的当前时间
                LOGGER.info("Go_mainController_goMain：----" + (endTime - startTime) + "ms");
                buffer.append("_Go_mainController_goMain：----" + (endTime - startTime) + "ms");
                LOGGER.info(buffer.toString());
                request.getSession().setAttribute("nickname", memberEntity.getNickName());
                return new ModelAndView("redirect:" + "mainController.do?goMain");
            }
        }

    }

    @RequestMapping(params = "goMain")
//    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView goMain(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();//获取开始当前的时间
        StringBuffer buffer = new StringBuffer();
        ModelAndView mav = new ModelAndView();
        try {
            String linkType = "门户";
            //通过连接类型和公众号ID去查图片，返回一个list（图片、图片跳转url）
            String accountid = (String) request.getSession().getAttribute("accountid");

            buffer.append("MainController.goMain(): accountId=" + accountid);
            //根据accountId和linkType去查图片名称
            String hql = "from WeidoorpptEntity where accountid='" + accountid + "' and pageLocation='" + linkType + "'";
            List<WeidoorpptEntity> weidoorpptList = this.systemService.findHql(hql, null);

            //获得图片路径
            String prefixUrl = ResourceUtil.getMediaUrlPrefix();
            buffer.append(", 图片路径前缀:" + prefixUrl);
            //TODO：根据weidoorpptList中的图片url放入一个数组中或者一个String  list中   每个url加上图片路径prefixUrl

            List<WeidoorpptEntity> weidoorpptListResult = new ArrayList<WeidoorpptEntity>();
            if (weidoorpptList.size() > 0) {
                for (int i = 0; i < weidoorpptList.size(); i++) {
                    WeidoorpptEntity weidoor = new WeidoorpptEntity();
                    weidoor.setId(weidoorpptList.get(i).getId());
                    weidoor.setTitle(weidoorpptList.get(i).getTitle());
                    weidoor.setPictureName(weidoorpptList.get(i).getPictureName());

                    weidoor.setPictureUrl(prefixUrl + "/" + weidoorpptList.get(i).getPictureUrl());

                    weidoor.setJumpType(weidoorpptList.get(i).getJumpType());
                    weidoor.setJumpUrl(weidoorpptList.get(i).getJumpUrl());
                    weidoor.setOperatetime(weidoorpptList.get(i).getOperatetime());
                    weidoor.setAccountid(weidoorpptList.get(i).getAccountid());
                    weidoor.setDescription(weidoorpptList.get(i).getDescription());
                    weidoor.setPageLocation(weidoorpptList.get(i).getPageLocation());
                    weidoorpptListResult.add(weidoor);
                }
            } else {
                String linkTypeDefalut = "首页默认";

                //根据accountId和linkType去查图片名称
                String hqlDefault = "from WeidoorpptEntity where pageLocation='" + linkTypeDefalut + "'";
                List<WeidoorpptEntity> weidoorpptListDefault = this.systemService.findHql(hqlDefault, null);
                // LOGGER.info(weidoorpptListDefault);

                WeidoorpptEntity weidoorDefault = new WeidoorpptEntity();

                if (weidoorpptListDefault.size() > 0) {
                    weidoorDefault.setId(weidoorpptListDefault.get(0).getId());
                    weidoorDefault.setTitle(weidoorpptListDefault.get(0).getTitle());
                    weidoorDefault.setPictureName(weidoorpptListDefault.get(0).getPictureName());

                    weidoorDefault.setPictureUrl(prefixUrl + "/" + weidoorpptListDefault.get(0).getPictureUrl());

                    weidoorDefault.setJumpType(weidoorpptListDefault.get(0).getJumpType());
                    weidoorDefault.setJumpUrl(weidoorpptListDefault.get(0).getJumpUrl());
                    weidoorDefault.setOperatetime(weidoorpptListDefault.get(0).getOperatetime());
                    weidoorDefault.setAccountid(weidoorpptListDefault.get(0).getAccountid());
                    weidoorDefault.setDescription(weidoorpptListDefault.get(0).getDescription());
                    weidoorDefault.setPageLocation(weidoorpptListDefault.get(0).getPageLocation());
                    weidoorpptListResult.add(weidoorDefault);
                } else {
                    weidoorpptListResult = null;
                }


            }
            String hqll = "from WeixinMainEntity where accountid='" + accountid + "'";
            List<WeixinMainEntity> weixinMainEntityList = this.systemService.findHql(hqll, null);
            if (weixinMainEntityList.size() > 0) {
                mav.addObject("weidoorpptlist", weidoorpptListResult);
                mav.addObject("weixinMain", weixinMainEntityList);
                mav.setViewName("liuliangbao/flowmanager/Main");
            } else {
                mav.addObject("weidoorpptlist", weidoorpptListResult);
                mav.setViewName("liuliangbao/flowmanager/Main");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();//获取结束的当前时间
            buffer.append(MessageFormat.format("方法耗时：{0}ms", endTime - startTime));
            LOGGER.info(buffer.toString());
            return mav;
        }
    }


    /**
     * 判断是否绑定
     * 请求参数中含有当前的操作名称 operatename：
     * 1. 如果已经绑定手机，则跳转到 operatename对应的页面
     * 2. 如果没有绑定手机，则跳转到绑定手机号码的页面
     *
     * @param request  http servlet 请求对象
     * @param response http 响应对象
     * @throws IOException
     */
    @RequestMapping(params = "IsBindng")
    public void IsBindng(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        LOGGER.info("--------------------IsBindng---begin-----------------");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //HttpSession session=request.getSession();
            String openid = request.getParameter("openId");
            String accountid = request.getParameter("accountid");
            String nickname = request.getParameter("nickname");
            String operatename = request.getParameter("operatename");

            buffer.append("mainController_IsBingng_openId" + openid + "_accountid_" + accountid);
            Gson gson = new Gson();
//        String url = "http://localhost:8080/jwx/userGetFlow/IsBinding";
            String url = path + "userGetFlow/IsBinding";
            buffer.append("_url" + url);
            JSONObject myJsonObject = new JSONObject();
            myJsonObject.accumulate("id", openid);
            myJsonObject.accumulate("accountid", accountid);
            JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
            String reStr = gson.toJson(content);

            Type type = new TypeToken<Update>() {
            }.getType();
            //TODO:写一个bean ,接收值
            Update update = gson.fromJson(reStr, type);

            request.getSession().setAttribute("openId", openid);
            request.getSession().setAttribute("accountid", accountid);
            request.getSession().setAttribute("nickname", nickname);

            if (update.getCode().equals("200")) {
                //绑定，跳转到一个可以关注获取流量的页面
                String phoneNumber = update.getMessage();
                request.getSession().setAttribute("phoneNumber", phoneNumber);
                buffer.append("_phoneNumber_" + phoneNumber);
                switch (operatename) {
                    case "关注":
                        map.put("flag", "关注");
                        map.put("msg", "webpage/liuliangbao/flowmanager/FlowCenter");
                        break;
                    case "签到":
                        map.put("flag", "签到");
                        map.put("phoneNumber", phoneNumber);
                        map.put("msg", "webpage/liuliangbao/flowmanager/sign");
                        break;
                    case "分享":
                        map.put("flag", "分享");
                        map.put("phoneNumber", phoneNumber);
                        map.put("msg", "webpage/liuliangbao/flowmanager/share");
                        break;
                    case "流量中心":
                        map.put("flag", "流量中心");
                        map.put("phoneNumber", phoneNumber);
                        map.put("msg", "webpage/liuliangbao/flowmanager/share");
                        break;
                    default:
                        map.put("flag", "其他");
                        map.put("msg", "webpage/liuliangbao/flowmanager/qita");
                        break;
                }

            } else {
                //没有绑定，跳转到绑定页面
                map.put("flag", "绑定");
                map.put("msg", "webpage/liuliangbao/flowmanager/binding");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            buffer.append("IsBindg_time:_" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            String json = objectMapper.writeValueAsString(map);
            PrintWriter out = response.getWriter();
            out.write(json);
        }


    }

    //跳转的绑定页面
    @RequestMapping(params = "goBinding")
    public ModelAndView goBinding(HttpServletRequest request) {
        return new ModelAndView("/liuliangbao/flowmanager/binding");
    }

    @Override
    public ModelAndView follow(PageAuthCallback msg, HttpServletRequest request) throws Exception {
        return null;
    }

    @Override
    public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request) {
        return null;
    }


    private String pageAuth(String accountid) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("accountid", accountid);  //活动ID，传给后面用

        //        String url=weixinAccountService.pageAuth(accountid,properties,this);   //调用授权封装：商户ID，
        WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
//            String key = UUID.randomUUID().toString();
        WeixinOpenPlatformController.AccountIdCache accountIdCache = new WeixinOpenPlatformController.AccountIdCache();
        accountIdCache.setLastUpdated(new Date());
        accountIdCache.setAccountId(account.getId());
        WeixinOpenPlatformController.PageAuthPropertyCache propertyCache = new WeixinOpenPlatformController.PageAuthPropertyCache();
        propertyCache.setLastUpdated(new Date());
        propertyCache.setProperties(properties);
        WeixinOpenPlatformController.PageAuthHandlerCache handlerCache = new WeixinOpenPlatformController.PageAuthHandlerCache();
        handlerCache.setLastUpdated(new Date());
        handlerCache.setHandler(this);
        String key = accountid;
        PageAuthRedisCache.setAccountIdCache(key, accountIdCache);
        PageAuthRedisCache.setPropertyCache(key, propertyCache);
        PageAuthRedisCache.setPageAuthHandlerCache(key, handlerCache);
        String redirectUrl = "";
        if (account.getAuthorizationType().equalsIgnoreCase("0")) {
            redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
            redirectUrl = redirectUrl.replace("APPID", account.getAccountappid());
            redirectUrl = redirectUrl.replace("SCOPE", "snsapi_base");
            redirectUrl = redirectUrl.replace("STATE", accountid);
            // TODO 暂时用localhost
            String localhosturl = ResourceUtil.getConfigByName("domain") + "/mainController.do?goRedirectMain";
            String url = "";
            try {
                url = URLEncoder.encode(localhosturl, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            redirectUrl = redirectUrl.replace("REDIRECT_URI", url);
        } else {
            redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&component_appid=%s#wechat_redirect";
            WeixinOpenPlatformEntity platform = weixinOpenPlatformService.get(WeixinOpenPlatformEntity.class, account.getOpenPlatformId());
            // TODO 暂时用localhost
            String localhosturl = ResourceUtil.getConfigByName("domain") + "/mainController.do?goRedirectMain";
            String url = "";
            try {
                url = URLEncoder.encode(localhosturl, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            redirectUrl = String.format(redirectUrl, account.getAccountappid(), url, "snsapi_base", key, platform.getAppId());
        }
        return redirectUrl;

    }

    private String pageAuth(String accountid, String openId) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("accountid", accountid);  //活动ID，传给后面用

        List<WeixinMemberEntity> member = this.weixinMemberService.findHql("from WeixinMemberEntity w where w.openId = ? and w.accountId = ?", openId, accountid);
//            WeixinAccountEntity account = this.get(WeixinAccountEntity.class, accountid);
        // 如果是认证服务号，openId不属于该公众号, 直接网页授权
        WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
        if (account.getAccounttype().equalsIgnoreCase("1") && member != null && member.size() == 0) {
            return pageAuth(accountid);
        }


        WeixinOpenPlatformController.AccountIdCache accountIdCache = new WeixinOpenPlatformController.AccountIdCache();
        accountIdCache.setLastUpdated(new Date());
        accountIdCache.setAccountId(accountid);
        WeixinOpenPlatformController.PageAuthPropertyCache propertyCache = new WeixinOpenPlatformController.PageAuthPropertyCache();
        propertyCache.setLastUpdated(new Date());
        propertyCache.setProperties(properties);
        WeixinOpenPlatformController.PageAuthHandlerCache handlerCache = new WeixinOpenPlatformController.PageAuthHandlerCache();
        handlerCache.setLastUpdated(new Date());
        handlerCache.setHandler(this);
        WeixinOpenPlatformController.OpenIdCache openIdCache = new WeixinOpenPlatformController.OpenIdCache();
        openIdCache.setLastUpdated(new Date());
        openIdCache.setOpenId(openId);
        String key = UUID.randomUUID().toString();

        PageAuthRedisCache.setAccountIdCache(key, accountIdCache);
        PageAuthRedisCache.setPropertyCache(key, propertyCache);
        PageAuthRedisCache.setPageAuthHandlerCache(key, handlerCache);
        PageAuthRedisCache.setOpenIdCache(key, openIdCache);
        String redirectUrl = "mainController.do?goRedirectMain&type=direct&state=" + key;

        return redirectUrl;
    }
}
