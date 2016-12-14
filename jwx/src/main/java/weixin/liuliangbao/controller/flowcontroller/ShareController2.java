package weixin.liuliangbao.controller.flowcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.star.util.DateTime;

import net.sf.json.JSONObject;
import sdk.jfinal.JFinalUtils;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinOpenPlatformEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.service.WeixinOpenPlatformServiceI;
import weixin.guanjia.account.util.CheckPic;
import weixin.guanjia.core.util.PageAuthRedisCache;
import weixin.guanjia.core.util.SignUtil;
import weixin.guanjia.openplatform.controller.WeixinOpenPlatformController.AccountIdCache;
import weixin.guanjia.openplatform.controller.WeixinOpenPlatformController.PageAuthPropertyCache;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.NewsItemBean;
import weixin.liuliangbao.jsonbean.ShareItem;
import weixin.liuliangbao.jsonbean.ShareRecordEntity;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.jsonbean.WeixinMainEntity;
import weixin.liuliangbao.service.flowcontroller.ShareAccessService;
import weixin.liuliangbao.service.flowcontroller.ShareItemServiceI;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.oauth2.AdvancedUtil;
import weixin.oauth2.WeixinOauth2Token;
import weixin.util.CommonUtils;
import weixin.util.DateUtils;
@Scope("prototype")
@Controller
@RequestMapping("/shareController2")
public class ShareController2 {
	private static final Logger LOGGER = Logger.getLogger(ShareController2.class);
    public static final String SHILIU_ACCOUNT_ID = ResourceUtil.getShiliuAccountId();
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    private String PATH = ResourceUtil.getConfigByName("jfinalUrl-config");
    private String message;
    @Autowired
    private ShareItemServiceI shareItemService;
    @Autowired
    private ShareAccessService shareAccessService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinOpenPlatformServiceI weixinOpenPlatformService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;

    // cms部分
    @RequestMapping(params = "setShare")
    public ModelAndView setShare(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        try {
            buffer.append("shareController2_setShare_begin:_");
            String accountid = ResourceUtil.getWeiXinAccountId();
            buffer.append("accountid_" + accountid);
            Gson gson = new Gson();
//        String url = "http://localhost:8080/jwx/share/QueryActicle";
            String url = PATH + "share/QueryActicle";
            String flowType = "默认页面";
            JSONObject myJsonObject = new JSONObject();
            myJsonObject.accumulate("id", accountid);
            myJsonObject.accumulate("flowType", flowType);
            JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
            String reStr = gson.toJson(content);

            Type type = new TypeToken<NewsItemBean>() {
            }.getType();
            // MerchantInfoBean userFlowAccountBean = gson.fromJson(reStrFlowAccount,typeFlowAccount);
            NewsItemBean newsItemBean = gson.fromJson(reStr, type);

            if (newsItemBean.getCode().equals("200")) {
                request.setAttribute("shareItem", newsItemBean);
                request.setAttribute("mediaurl", ResourceUtil.getConfigByName("media.url.prefix"));
                buffer.append("_setShare_");
                long endTime = System.currentTimeMillis();
                buffer.append("_time:_" + (endTime - startTime) + "ms");
                LOGGER.info(buffer.toString());
                return new ModelAndView("/liuliangbao/flowmanager/setShare");
            } else {
                buffer.append("_no_setShare_");
                long endTime = System.currentTimeMillis();
                buffer.append("_time:_" + (endTime - startTime) + "ms");
                LOGGER.info(buffer.toString());
                return new ModelAndView("/liuliangbao/flowmanager/noMain");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/common/404");


    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(ShareItem shareItem, HttpServletRequest request) throws BusinessException {

        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        buffer.append("shareController2_setShare_doUpdate_begin:_");
        try {
            AjaxJson j = new AjaxJson();
            message = "微信单图消息更新成功";
            String name = ResourceUtil.getWeiXinAccount().getAccountname();
            buffer.append("_accountName_" + name);
            if (StringUtils.isBlank(shareItem.getId())) {
//            String id= UUID.randomUUID().toString();
//            shareItem.setId(id);
                String pathurl = ResourceUtil.getConfigByName("media.url.prefix");
                shareItem.setImagepath(pathurl + "/" + shareItem.getImagepath());
                shareItem.setAccountName(name);
                shareItem.setReadNumber(0);
                shareItem.setRedirectType("默认页面");
                shareItem.setCreateDate(new Date());
                shareItemService.save(shareItem);
                buffer.append("_add_");
            } else {
                try {
                    String pathurl = ResourceUtil.getConfigByName("media.url.prefix");
                    String imagepath = shareItem.getImagepath();
                    if (imagepath.contains(pathurl)) {

                    } else {
                        shareItem.setImagepath(pathurl + "/" + shareItem.getImagepath());
                    }

                    shareItem.setOperateDate(new Date());
                    shareItem.setAccountName(name);
                    shareItem.setReadNumber(0);
                    ShareItem t = shareItemService.get(ShareItem.class, shareItem.getId());
                    MyBeanUtils.copyBeanNotNull2Bean(shareItem, t);
                    shareItemService.saveOrUpdate(t);
                    buffer.append("_update_");

                } catch (Exception e) {
                    e.printStackTrace();
                    message = "微信单图消息更新失败";
                    throw new BusinessException(e.getMessage());
                }
            }

            long endTime = System.currentTimeMillis();
            buffer.append("_time:_" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            j.setMsg(message);
            return j;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(params = "shareList")
    public ModelAndView ShareList(HttpServletRequest request) throws Exception {
        return new ModelAndView("liuliangbao/flowmanager/shareList");
    }


    @RequestMapping(params = "mydatagrid")
    @ResponseBody
    public void datagrid(ShareItem shareItem, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(ShareItem.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, shareItem, request.getParameterMap());

        try {
            //自定义追加查询条件
            //只查询该公众号的幻灯片
            cq.eq("accountid", ResourceUtil.getWeiXinAccountId());
            LOGGER.info(ResourceUtil.getWeiXinAccountId());

        } catch (Exception e) {
            throw new org.jeecgframework.core.common.exception.BusinessException(e.getMessage());
        }
        cq.add();
        this.shareItemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);

    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(HttpServletRequest request) {
        return new ModelAndView("liuliangbao/flowmanager/addShare");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(ShareItem shareItem, HttpServletRequest request) {
        if (StringUtil.isNotEmpty(shareItem.getId())) {
            shareItem = shareItemService.getEntity(ShareItem.class, shareItem.getId());
            //request.setAttribute("mediaurl", ResourceUtil.getConfigByName("media.url.prefix"));
            request.setAttribute("title", shareItem.getTitle());
            request.setAttribute("description", shareItem.getDescription());
            request.setAttribute("shareItem", shareItem);
            request.setAttribute("imagepath", shareItem.getImagepath());
            request.setAttribute("content", shareItem.getContent());

        }
        return new ModelAndView("/liuliangbao/flowmanager/updateShare");
    }

    @RequestMapping(params = "shareContent")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void shareContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        List<ShareItem> shareItemList = this.systemService.findHql("from ShareItem t where t.accountid=?", ResourceUtil.getWeiXinAccountId());

        String json = gson.toJson(shareItemList);
        out.write(json);
    }

    @RequestMapping(params = "goDelete")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void goDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        List<WeixinMainEntity> weixinMainEntityList = this.systemService.findHql("from WeixinMainEntity t where t.shareTitle=?", id);
        if (weixinMainEntityList.size() > 0) {
            map.put("flag", true);//分享的内容被使用，不可以删除
        } else {
            map.put("flag", false);
        }
        String json = objectMapper.writeValueAsString(map);
        PrintWriter out = response.getWriter();
        out.write(json);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        ShareItem shareItem = systemService.getEntity(ShareItem.class, id);
        message = "文章删除成功";
        try {
            shareItemService.delete(shareItem);
            map.put("flag", true);//分享的文章被删除成功
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "文章删除失败";
            map.put("flag", false);//分享的文章被删除成功
            throw new org.jeecgframework.core.common.exception.BusinessException(e.getMessage());
        }
        String json = objectMapper.writeValueAsString(map);
        PrintWriter out = response.getWriter();
        out.write(json);
    }

    // tag - weixin wechat 微信h5 部分

    /**
     * http://weixina.1shiliu.com/jwx/shareController2.do?startLoad&accountid=ea84c08653c7aeb30153cbb2288f06d8&shareId=ea84c086555e5224015561c7d2dc0e84
     * 用户自己点击链接进入分享流量页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "startLoad")
    public ModelAndView startLoad(HttpServletRequest request) {
        String shareId = request.getParameter("shareId");
        try {
            String accountid = request.getParameter("accountid");// 分享人的所在商户的Id
            String openId = request.getParameter("openId");// 这个openId为分享人的openId
            WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
            if (account == null /*|| account.getAccounttype().equals("2") || account.getAccounttype().equals("3") || account.getAccounttype().equals("4")*/) {//2为订阅号不能使用分享，3和4都为未认证的。
                String hdNotUrl = "liuliangbao/flowmanager/noShare";
                request.setAttribute("messageInfo", "无法获取商家信息。");
                return new ModelAndView(hdNotUrl);
            }

            Map<String, String> properties = new HashMap<String, String>();
            properties.put("accountid", accountid);
            properties.put("openId", openId);
            properties.put("shareId", shareId);
            String url = pageAuth(properties);   //调用授权封装：商户ID
            return new ModelAndView("redirect:" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/common/404");
    }

    private String pageAuth(Map<String, String> properties) {
        String accountid = properties.get("accountid");
        // WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
        WeixinAccountEntity shiliuAccount = this.systemService.get(WeixinAccountEntity.class, SHILIU_ACCOUNT_ID);

        AccountIdCache accountIdCache = new AccountIdCache();
        accountIdCache.setLastUpdated(new Date());
        accountIdCache.setAccountId(accountid);
        PageAuthPropertyCache propertyCache = new PageAuthPropertyCache();
        propertyCache.setLastUpdated(new Date());
        propertyCache.setProperties(properties);

        String key = UUID.randomUUID().toString();
        PageAuthRedisCache.setAccountIdCache(key, accountIdCache);
        PageAuthRedisCache.setPropertyCache(key, propertyCache);
        String redirectUrl;
        if ("0".equals(shiliuAccount.getAuthorizationType())) {
            redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
            String url = CommonUtils.encodeURL(ResourceUtil.getConfigByName("domain") + "/shareController2.do?showSharePage");
            redirectUrl = String.format(redirectUrl, shiliuAccount.getAccountappid(), url, "snsapi_base", key);
        } else {
            redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&component_appid=%s#wechat_redirect";
            WeixinOpenPlatformEntity platform = weixinOpenPlatformService.get(WeixinOpenPlatformEntity.class, shiliuAccount.getOpenPlatformId());
            String url = CommonUtils.encodeURL(ResourceUtil.getConfigByName("domain") + "/shareController2.do?showSharePage");
            redirectUrl = String.format(redirectUrl, shiliuAccount.getAccountappid(), url, "snsapi_base", key, platform.getAppId());
        }
        return redirectUrl;
    }

    @RequestMapping(params = "showSharePage", method = RequestMethod.GET)
    public ModelAndView showSharePage(HttpServletRequest request) throws Exception {
        CommonUtils.printRequestParam("shareController2.showSharePage", request);
        long startTime = System.currentTimeMillis();//获取开始当前的时间
        StringBuffer buffer = new StringBuffer();
        buffer.append("shareController2_showSharePage_begin_");

        try {
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            String appid = request.getParameter("appid");
            String type1 = request.getParameter("type");

            AccountIdCache accountIdCache = PageAuthRedisCache.getAccountIdCache(state);
            if (null == accountIdCache) {
                return new ModelAndView("common/404");
            }
            String accountid = accountIdCache.getAccountId();
            Map<String, String> properties = PageAuthRedisCache.getPropertyCache(state).getProperties();
            String shareId = properties.get("shareId");
            buffer.append("_code_" + code + "_state_" + state + "_appid_" + appid + "_type_" + type1 + "_accountid_" + accountid);
            String shiliuOpenId = ""; // 当前用户的openId
            // WeixinAccountEntity account = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", accountid);
            WeixinAccountEntity shiliuAccount = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", SHILIU_ACCOUNT_ID);
            if (StringUtils.isBlank(type1)) {
                // 0 普通公众号授权 1 第三方平台授权
                int authType = 0;
                if (StringUtils.isBlank(appid)) {
                    LOGGER.info("shareController2_showSharePage普通1公众号网页授权");
                } else {
                    LOGGER.info("shareController2_showSharePage第三3方平台网页授权");
                    authType = 1;
                }

                WeixinOauth2Token token = null;
                if (authType == 1) {
                    WeixinOpenPlatformEntity platform = weixinOpenPlatformService.get(WeixinOpenPlatformEntity.class, shiliuAccount.getOpenPlatformId());
                    token = AdvancedUtil.getOauth2AccessToken(shiliuAccount.getAccountappid(), code, platform.getAppId(), weixinOpenPlatformService.getComponentAccessToken(platform.getId()));
                } else {
                    token = AdvancedUtil.getOauth2AccessToken(shiliuAccount.getAccountappid(), shiliuAccount.getAccountappsecret(), code);
                }

                if (null == token) {
                    return new ModelAndView("common/404");
                }

                shiliuOpenId = token.getOpenId();
                buffer.append("_openid_" + shiliuOpenId);
            } else {
                shiliuOpenId = PageAuthRedisCache.getOpenIdCache(state).getOpenId();
            }
            if (StringUtils.isNotBlank(properties.get("sharedLinkClicked"))) {
                try {
                    this.shareAccessService.addShareAccess(properties.get("sharerOpenId"), shiliuOpenId, accountid, shareId);
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }

            WeixinMemberEntity shiliuMember = this.weixinMemberService.getWeixinMember(shiliuOpenId, SHILIU_ACCOUNT_ID);
            if (null == shiliuMember || StringUtils.isBlank(shiliuMember.getPhoneNumber())) {
                shiliuMember = this.weixinMemberService.getWeixinMember(shiliuOpenId, SHILIU_ACCOUNT_ID, "2");
            }
            String phoneNumber = null != shiliuMember ? shiliuMember.getPhoneNumber() : null;
            String nickName = null != shiliuMember ? shiliuMember.getNickName() : "";

            WeixinMemberEntity memberEntity = null;
            if (StringUtils.isNotBlank(properties.get("openId"))) {
                memberEntity = this.weixinMemberService.getWeixinMemberEntity(properties.get("openId"), accountid);
            } else {
                memberEntity = this.weixinMemberService.getWeixinMemberByPhone(phoneNumber, accountid);
            }

            long startflowrule = System.currentTimeMillis();
            MerchantInfoBean merchantInfoBean = JFinalUtils.getMerchantFlowRule(accountid, "关注");
            double subscribeFlowValue = merchantInfoBean.getData().get(0).getCountryFlowValue();
            request.getSession().setAttribute("subscribeFlowValue", subscribeFlowValue);


            MerchantInfoBean merchantInfoBeanShareBind = JFinalUtils.getMerchantFlowRule(accountid, "分享绑定");
            double shareBindFlowValue = merchantInfoBeanShareBind.getData().get(0).getCountryFlowValue();
            request.getSession().setAttribute("shareBindFlowValue", shareBindFlowValue);


            long endflowrule = System.currentTimeMillis();
            buffer.append("_queryFLowRule_time:_" + (endflowrule - startflowrule) + "ms");

            //分享可以获赠的流量
            MerchantInfoBean merchantInfoBeanShare = JFinalUtils.getMerchantFlowRule(accountid, "分享");
            double shareFlowValue = merchantInfoBeanShare.getData().get(0).getCountryFlowValue();
            // request.getSession().setAttribute("flowValue", shareFlowValue);

            ShareItem shareItem = null != shareId ? shareItemService.get(ShareItem.class, shareId) : null;
            if (shareItem != null) {
                int number = shareItem.getReadNumber() + 1;
                shareItem.setReadNumber(number);
                shareItemService.saveOrUpdate(shareItem);

                boolean subscribeStatus = null != memberEntity && "1".equals(memberEntity.getSubscribe());

                URIBuilder builder = new URIBuilder("shareController2.do?forwardShare");
                JSONObject params = new JSONObject();
                String openId = ((String) params.get("openId"));
                if (StringUtils.isBlank(openId) && null != memberEntity) {
                    openId = memberEntity.getOpenId();
                }
                params.put("openId", openId);
                params.put("sharerOpenId", properties.get("sharerOpenId"));
                params.put("shareId", shareId);
                params.put("shiliuOpenId", shiliuOpenId);
                params.put("phoneNumber", phoneNumber);
                params.put("newsItem.title", shareItem.getTitle());
                params.put("newsItem.description", shareItem.getDescription());
                params.put("newsItem.accountName", shareItem.getAccountName());
                request.getSession().setAttribute("newsItem.content", shareItem.getContent());
                params.put("flowValue", shareFlowValue);
                params.put("shareBindFlowValue", shareBindFlowValue);
                params.put("newsItem.pageurl", shareItem.getPageurl());
                params.put("subscribeStatus", subscribeStatus + "");
                params.put("accountid", accountid);
                params.put("nickname", nickName);
                params.put("date", DateUtils.formatDate(shareItem.getCreateDate()));
                params.put("readNumber", shareItem.getReadNumber());
                params.put("shareImgUrl", shareItem.getImagepath());
                builder.setParameter("p", CommonUtils.encode(params.toString(), 133));
				ShareRecordEntity shareRecord = new ShareRecordEntity();
				shareRecord.setAccountid(accountid);
				shareRecord.setShareId(shareId);
				shareRecord.setAccountName(shareItem.getAccountName());
				shareRecord.setReason("1");
                shareRecord.setTitle(shareItem.getTitle());
                shareRecord.setCreatetime(new Date());
				this.systemService.save(shareRecord);

                String forwardUrl = builder.build().toString();

                if (memberEntity == null || memberEntity.getSubscribe().equals("0")) {
                    // 没有关注，跳到需要点击按钮弹出二维码的页面
                    long endTime = System.currentTimeMillis();//获取结束的当前时间
                    buffer.append("_noSub_").append("shareController2_showSharePage_end_time:" + (endTime - startTime) + "ms");
                    LOGGER.info(buffer.toString());
                    return new ModelAndView("redirect:" + forwardUrl);
                } else {
                    // 关注了
                    if (StringUtils.isBlank(memberEntity.getPhoneNumber())) {
                        // 没有绑定，跳到点击按钮弹出绑定的页面
                        long endTime = System.currentTimeMillis();//获取结束的当前时间
                        buffer.append("_noBinding_").append("shareController2_showSharePage_end_time:" + (endTime - startTime) + "ms");
                        LOGGER.info(buffer.toString());
                        return new ModelAndView("redirect:" + forwardUrl);
                    } else {
                        // 绑定了，跳到只有一个按钮的页面，允许分享
                        long endTime = System.currentTimeMillis();//获取结束的当前时间
                        buffer.append("_share_").append("shareController2_showSharePage_end_time:" + (endTime - startTime) + "ms");
                        LOGGER.info(buffer.toString());
                        return new ModelAndView("redirect:" + forwardUrl);
                    }
                }
            } else {
                long endTime = System.currentTimeMillis();
                buffer.append("_noShare_").append("shareController2_showSharePage_end_time:" + (endTime - startTime) + "ms");
                LOGGER.info(buffer.toString());
                request.setAttribute("messageInfo", "商户还没有设置分享的文章，敬请期待。");
                return new ModelAndView("liuliangbao/flowmanager/noShare");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/common/404");
    }

    @RequestMapping(params = "forwardShare")
    public ModelAndView forwardSharePage(HttpServletRequest request) throws Exception {
        CommonUtils.printRequestParam("shareController2.forwardSharePage", request);
        String p = CommonUtils.decode(request.getParameter("p"), 133);
        JSONObject params = JSONObject.fromObject(p);
       
        String accountid = params.optString("accountid");
        String shareImgUrl = params.optString("shareImgUrl");
        String shareId = params.optString("shareId");
        String shiliuOpenId = params.optString("shiliuOpenId");

        Map<String, String> map = generateShareLink(request, accountid, shareImgUrl, shareId, shiliuOpenId);
       
        request.getSession().setAttribute("map", map);
        request.getSession().setAttribute("url", map.get("url"));
        request.getSession().setAttribute("shareImgUrl", map.get("shareImgUrl"));
        request.getSession().setAttribute("shareId", shareId);

        request.getSession().setAttribute("openId", params.optString("openId"));
        request.getSession().setAttribute("shiliuOpenId", shiliuOpenId);
        // only for shared link clicked
        request.getSession().setAttribute("sharerOpenId", params.optString("sharerOpenId"));
        request.getSession().setAttribute("phoneNumber", params.optString("phoneNumber"));
        ShareItem shareItem = new ShareItem();
        shareItem.setTitle(params.optString("newsItem.title"));
        shareItem.setDescription(params.optString("newsItem.description"));
        shareItem.setAccountName(params.optString("newsItem.accountName"));
        shareItem.setPageurl(params.optString("newsItem.pageurl"));
        shareItem.setContent(((String) request.getSession().getAttribute("newsItem.content")));
        request.getSession().setAttribute("newsItem", shareItem);

        request.getSession().setAttribute("flowValue", params.optString("flowValue"));
        request.getSession().setAttribute("subscribeStatus", params.optString("subscribeStatus"));
        request.getSession().setAttribute("shareBindFlowValue", params.optString("shareBindFlowValue"));
        request.getSession().setAttribute("accountid", accountid);
        request.getSession().setAttribute("nickname", params.optString("nickname"));
        request.getSession().setAttribute("date", params.optString("date"));
        request.getSession().setAttribute("readNumber", params.optString("readNumber"));
        return new ModelAndView("/liuliangbao/flowmanager/share");
         
    }


    /**
     * 查看人点击分享的链接时, 调用此接口
     * http://weixina.1shiliu.com/jwx/shareController2.do?load
     * &accountid=ea84c08653c7aeb30153cbb2288f06d8 // 分享人所在商户Id
     * &phone=15910593931                          // 分享人的手机号码
     * &shareId=ea84c086555e5224015561c7d2dc0e84  // 分享内容Id
     *
     * @throws Exception
     */
    @RequestMapping(params = "load")
    public ModelAndView load(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommonUtils.printRequestParam("shareController2.load", request);
        String shareId = request.getParameter("shareId");
        try {
            String sharerShiliuOpenId = request.getParameter("sharerOpenId");//原分享人的石榴的openId
            request.getSession().setAttribute("sharerOpenId", sharerShiliuOpenId);
            String accountid = request.getParameter("accountid");
            request.getSession().setAttribute("param", request.getQueryString());

            HashMap<String, String> properties = new HashMap<>();
            properties.put("sharerOpenId", sharerShiliuOpenId);
            properties.put("accountid", accountid);
            properties.put("param", request.getQueryString());
            properties.put("shareId", shareId);
            properties.put("sharedLinkClicked", "true");
            return new ModelAndView("redirect:" + pageAuth(properties));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/common/404");
    }

    @RequestMapping(params = "QRcode")
    public ModelAndView QRcode(HttpServletRequest request) throws Exception {
        String accountid = request.getParameter("accountid");
        WeixinAccountEntity account = weixinAccountService.getEntity(WeixinAccountEntity.class, accountid);
        request.setAttribute("accountName", account.getAccountname());

        Gson gson = new Gson();
        String urll = PATH + "userGetFlow/QueryFlowRule";
        JSONObject myJson = new JSONObject();
        myJson.accumulate("id", accountid);
        myJson.accumulate("opreateType", "关注");
        JSONObject contentBinding = HttpUtil.httpPost(urll, myJson, false);
        String reStrBinding = gson.toJson(contentBinding);
        Type typeBinding = new TypeToken<MerchantInfoBean>() { }.getType();
        MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
        request.getSession().setAttribute("flowValue", merchantInfoBean.getData().get(0).getCountryFlowValue());
        request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);

        //二维码的地址
        String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
        String qrcode = account.getQRcode();
        if(CheckPic.checkImg(qrcode)){
        	qrcode = urlprefix + "/" + account.getQRcode();
        }
        request.setAttribute("url", qrcode);
        if(!qrcode.equals(null)){
            String logoAccount = account.getLogoAccount();
            if(CheckPic.checkImg(logoAccount)){
            	logoAccount = urlprefix + "/" + logoAccount;
            }
            request.setAttribute("logo", logoAccount);
        } else {
            request.setAttribute("logo", "plug-in/liuliangbao/css/0422/images/logo-1.png");
        }
        return new ModelAndView("weixin/member/NoattentionPublicNum");

    }

    public Map<String, String> generateShareLink(HttpServletRequest requesturl, String accountid, String imgPath, String shareId, String shiliuOpenId) throws Exception {
        WeixinAccountEntity account = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", accountid);
        // 1 认证服务号 2 认证订阅号 3 未认证服务号  4 未认证订阅号
        String accounttype = account.getAccounttype();
        WeixinAccountEntity shiliuAccount = account;
        if (!"1".equals(accounttype)) {
            shiliuAccount = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", SHILIU_ACCOUNT_ID);
        }

        String appId = shiliuAccount.getAccountappid();
        String ticket = weixinAccountService.getSignature(shiliuAccount.getId());

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = requesturl.getRequestURL().toString();
        String domain = ResourceUtil.getConfigByName("domain");
        String param = requesturl.getQueryString();
        url = url + "?" + param;
        String url2 = domain + "/" + "shareController2.do?load&accountid=" + accountid + "&sharerOpenId=" + shiliuOpenId + "&shareId=" + shareId;
        requesturl.getSession().setAttribute("url", url2);
        LOGGER.info("生成分享链接:" + url2);
        requesturl.getSession().setAttribute("shareImgUrl", imgPath);
        Map<String, String> ret = SignUtil.sign(ticket, url);
        ret.put("appId", appId);
        ret.put("url", url2);
        ret.put("shareImgUrl", imgPath);
        return ret;
    }

    /**
     * 微信内, 用户点击页面右上角的菜单进行分享, 调用此方法赠送用户分享的流量
     * 场景一: 用户在公众号内,可以获取当前用户的openId
     * 场景二: 用户在公众号外,是通过别人分享的链接进入,然后进行分享
     * <p>
     * 场景一可以有openId, 场景二中,如果商户的公众号是非认证服务号, 则不能通过网页授权获取用户的openId,这个要使用对用主公众号的openId.
     * 所以, 在进入分享页面前,要将两个openId都带入到页面中(实际上只有一个openId是有效的), 在页面中调用此接口传递两个openId,这样才能满足两个场景都能分享送流量的需求.
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(params = "share")
    public void share(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String openId = request.getParameter("openId");
        String shiliuOpenId = request.getParameter("shiliuOpenId");
        String accountid = request.getParameter("accountid");
        String shareId = request.getParameter("shareId");
        String nickname = request.getParameter("nickname");
        String shareWay = request.getParameter("shareWay");
        JSONObject shareResult = JFinalUtils.share(openId, shiliuOpenId, SHILIU_ACCOUNT_ID, accountid, shareId, nickname, shareWay);
        if (null == shareResult) {
            Update object = new Update();
            object.setCode("400");
            object.setMessage("分享失败,请稍后再试.");
            shareResult = JSONObject.fromObject(object);
        }
        PrintWriter writer = response.getWriter();
        writer.print(StringUtils.defaultString(shareResult.toString()));
        writer.flush();
    }
}
