package weixin.member.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.controller.flowcontroller.ShareController;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.MoreFlow.shareFlow;
import weixin.liuliangbao.jsonbean.NewsItemBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.oauth2.AdvancedUtil;
import weixin.oauth2.WeixinOauth2Token;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by aa on 2015/12/12. 分享送流量
 * @deprecated {@link ShareController}
 */
@Controller
@RequestMapping("/shareFlowController")
public class ShareFlowController extends BaseController {
    /**
     * Logger for this class write for LOGGER
     */
    private String path = ResourceUtil.getConfigByName("jfinalUrl-config");

    private static final Logger LOGGER = Logger.getLogger(WeixinCoinJournalController.class);
    @Autowired
    private WeixinAccountServiceI weixinAccountService;   //引入微信公众账号的信息
    @Autowired
    private WeixinMemberServiceI weixinMemberService; //引入的是粉丝管理实体
    @Autowired
    private WeixinAcctServiceI weixinAcctService;
    @Autowired
    private SystemService systemService;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    //   关注和游戏以及分享和签到的按钮都是在一个页面的

    /**
     * 用户点击关注时的跳转页面显示,跳转页面的时候是不需要添加注解	@ResponseBody
     *
     * @return
     */
    @RequestMapping(params = "myShare")
    public ModelAndView subscribeAcctListPage(HttpServletRequest request) throws Exception {
        StringBuilder subscribeAcctListPage = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        subscribeAcctListPage.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志


        String ph = request.getParameter("phone");
        request.setAttribute("phoneNumber", ph);
        String openId = request.getParameter("openId");

        request.setAttribute("openId", openId);

        subscribeAcctListPage.append("当前用户所在商户的手机号" + ph + "_");  //添加的日志
        subscribeAcctListPage.append("当前用户openId" + openId + "_");  //添加的日志
        String messageInfo = null;  //用于接收没有查询到结果集的列表
//从页面获取OpenId
//        String mOpenId1 = request.getParameter("MopenId");

//        模拟测试使用的效果图
//        String mOpenId1 = "ov5lNs4x6VIE2ZasPfsCyezGZZnc";
//        根据用户关注某个商户获取到的隶属于这个商户的openId,来查询微信公众账号的信息
        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
        WeixinMemberEntity weixinMemberEntity = new WeixinMemberEntity();
//        WeixinMemberEntity weixinMemberEntity = new WeixinMemberEntity();

//        weixinMemberEntity = weixinMemberService.findUniqueByProperty(WeixinMemberEntity.class, "openId", mOpenId1);
        String account_id = request.getParameter("accountid");

//        WeixinMemberEntity weixinMemberEntity = new WeixinMemberEntity();

//        weixinMemberEntity = weixinMemberService.findUniqueByProperty(WeixinMemberEntity.class, "openId", mOpenId1);
//        直接就可以获取商户id
        if (account_id == null || account_id.equals("")) {
            weixinMemberEntity = weixinMemberService.findUniqueByProperty(WeixinMemberEntity.class, "openId", openId);
            account_id = weixinMemberEntity.getAccountId();
        }
        subscribeAcctListPage.append("当前商户account_id" + account_id + "_");  //添加的日志
        weixinAccountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, account_id);
//根据公众账号的实体可以获取到租户或者说是商户管理表

        String id = weixinAccountEntity.getAcctId();
        WeixinAcctEntity weixinAcctEntity = new WeixinAcctEntity();
        try {
            weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, id);
//            weixinAcctEntity = weixinAcctService.findUniqueByProperty(WeixinAcctEntity.class, "id", tenantId);
            if (weixinAcctEntity.getId() != null || !weixinAcctEntity.getId().equals("")) {
                message = "查询结果不为空";
            } else {
                message = "查询结果为空值";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //当前的商业类型
        String accType = weixinAcctEntity.getBusinessType();


//        获取当前的商户所在的省份
        String provinceName = weixinAcctEntity.getProvince();
//        获取当前的商户id
        String accId = weixinAcctEntity.getId();
        subscribeAcctListPage.append("当前商户所在省" + provinceName + "_");  //添加的日志
        subscribeAcctListPage.append("当前商户商业类型" + accType + "_");  //添加的日志
        subscribeAcctListPage.append("当前商户id" + accId + "_");  //添加的日志
//        定义接收的实体集合
//        List<NewsItem> lisShare = new ArrayList<NewsItem>();
        List<shareFlow> lisShare = new ArrayList<shareFlow>();

//        读取配置文件使用原始的jdbc进行实现
//        String sql = " select m.* from weixin_newsitem m join weixin_account w join weixin_acct a where m.accountid=w.id and w.acct_id=a.id and a.province='" + provinceName + "' and (a.business_type<>'" + accType + "' or a.id='" + accId + "')";
//        String sql = " select m.*,r.flowValue from shareItem m join weixin_account w join weixin_acct a join merchantFlowGiveRules r where r.merchantID=m.accountid and  m.accountid=w.id and w.acct_id=a.id and a.province='" + provinceName + "'and (a.business_type<>'" + accType + "' or a.id='" + accId + "')and r.operateType='分享'";

//        String sql = "select m.*,r.flowValue from weixin_main m join weixin_account w join weixin_acct a join merchantFlowGiveRules r where r.merchantID=m.accountid and  m.accountid=w.id and w.acct_id=a.id and a.province='" + provinceName + "'and (a.business_type<>'" + accType + "' or a.id='" + accId + "')and r.operateType='分享' and m.isShare='1'";
        String sql = "select m.*,r.flowValue from weixin_main m join weixin_account w join weixin_acct a join merchantFlowGiveRules r where r.merchantID=m.accountid and  m.accountid=w.id and w.acct_id=a.id and (a.business_type<>'" + accType + "' or a.id='" + accId + "')and r.operateType='分享' and m.isShare='1'";  //只是保留行业互斥

//        String sql = " select m.*,r.flowValue from shareItem m join weixin_account w join weixin_acct a join merchantFlowGiveRules r where r.merchantID=m.accountid and  m.accountid=w.id and w.acct_id=a.id and a.province='江西省' and (a.business_type<>'2' or a.id='4028b881516b211101516b22db350004')and r.operateType='分享'";
        subscribeAcctListPage.append("查询语句本省的并且是不是同一个商业类型，但是包含自己的分享列表查询语句" + sql + "_");  //添加的日志
        LogUtil.info("查询的是分享有关的sql语句----------------------" + sql);

        LOGGER.info(sql);
//        获取jdbc连接
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            es.last();  //指针移到最后一行
            if (es.getRow() == 0) {
                messageInfo = "暂时没有商家相关分享，不好意思";
            }
            es.beforeFirst();  //复位结果集

//            读取图片的地址,domain就是网站的根目录，也就是部署在tomcat下面的项目
            String prefix = ResourceUtil.getConfigByName("media.url.prefix");

//            此处将while换为if……else，从而将可以进行得到的集合是否为空，从而进行判断
            while (es.next()) {
//                NewsItem en = new NewsItem();
                shareFlow en = new shareFlow();
                en.setId(es.getString("id"));
                en.setTitle(es.getString("title"));
//                en.setContent(es.getString("content"));
//                en.setDescription(es.getString("description"));
                en.setImagepath(es.getString("imagepath"));   //读取分享网络的根目录地址
                en.setShareTitle(es.getString("shareTitle"));
                en.setAccountid(es.getString("accountid"));
//                en.setNewsTemplate(es.getString("newsTemplate"));
                en.setFlowValue(es.getString("flowValue"));
//                en.setCreateDate(es.getDate("create_date"));
                lisShare.add(en);
            }
            subscribeAcctListPage.append("查询语句本省的并且是不是同一个商业类型，但是包含自己的分享列表查询结果集个数" + lisShare.size() + "_");  //添加的日志
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            es.close();
            stmt.close();
            connection.close();
        }

        request.setAttribute("message", messageInfo);  //查询不到数据集合的时候将提示信息存放到request
        request.setAttribute("listFor", lisShare);   //查询到数据集合的时候将其存放到request
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        subscribeAcctListPage.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        subscribeAcctListPage.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        LOGGER.info("shareFlowController的__subscribeAcctListPage__方法执行过程中的各个操作的输入输出参数以及结果_" + subscribeAcctListPage.toString());

        return new ModelAndView("weixin/member/shareFlow/shareFlow");
    }


    //        携带商户的id从而查询商户的accountId
    //增加授权页面
    @RequestMapping(params = "startShare")
    public ModelAndView startShare(HttpServletRequest request, HttpServletResponse response) {

////        携带的商户的id
//        String acct_id = request.getParameter("id");
////        根据商户管理表id查询商户的微信账号id
//        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
//        weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId", acct_id);
//        获取的商户的微信账号id
        String accountid = request.getParameter("accountid");
//        String accountid = weixinAccountEntity.getId();

        String rdUrl = "shareFlowController.do?goShare";
//		String accountid = hdEntity.getAccountid();
        WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
        String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        requestUrl = requestUrl.replace("APPID", account.getAccountappid());
        requestUrl = requestUrl.replace("SCOPE", "snsapi_base");
        requestUrl = requestUrl.replace("STATE", accountid);
        String path = request.getContextPath();
        String localhosturl = request.getScheme() + "://" + request.getServerName() + path + "/";
        String url = "";
        try {
            url = URLEncoder.encode(localhosturl + rdUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        requestUrl = requestUrl.replace("REDIRECT_URI", url);
        return new ModelAndView("redirect:" + requestUrl);
    }

    //展示拿到的用户信息
    @RequestMapping(params = "goShare")
    public ModelAndView goShare(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("进入关注");

        String accountid = request.getParameter("state");
        request.setAttribute("accountid", accountid);
//        String accountid = request.getParameter("accountid");
        String test = request.getParameter("test");
        String code = request.getParameter("code");
        if (!"authdeny".equals(code)) {
//            WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);
//            String accountid = hdEntity.getAccountid();
            String aa = null;
            WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);

            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(account.getAccountappid(),
                    account.getAccountappsecret(), code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
//                SNSUserInfo snsUserInfo = AdvancedUtil.getSnsUserInfo(accessToken, openId); // 设置要传递的参数
//            request.getSession().setAttribute("hdId", hdid);
            request.getSession().setAttribute("openId", openId);
            request.getSession().setAttribute("accountid", accountid);
//                request.getSession().setAttribute("nickname", snsUserInfo.getNickName());

            request.setAttribute("openId", openId);

            //判断是否 绑定 是进入转盘，否进入 绑定 页面
            WeixinMemberEntity weixinMemberEntity = null;

            //二维码的地址
            String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
            String impUrl = account.getQRcode();
            String url = urlprefix + "/" + impUrl;
            request.setAttribute("url", url);
            List<WeixinMemberEntity> weixinMemberEntities;
            Gson gson = new Gson();
            String hql1 = "from WeixinMemberEntity t where t.openId='" + openId + "' and t.accountId='" + accountid + "'";

            weixinMemberEntities = weixinMemberService.findHql(hql1, null);
//
            if (weixinMemberEntities.size() > 0) {
                weixinMemberEntity = weixinMemberEntities.get(0);
            }
            if (weixinMemberEntity != null && weixinMemberEntity.getSubscribe().equals("1")) {
                String phoneNumber = weixinMemberEntity.getPhoneNumber();
                request.setAttribute("phoneNumber", phoneNumber);
                if (phoneNumber != null || phoneNumber != "") {
//                    return new ModelAndView("weixin/member/shareFlow/shareBind-details");
                    String rdUrl = "shareController.do?load&accountid=" + accountid + "&phone=" + phoneNumber;
                    return new ModelAndView("redirect:" + rdUrl);
                }
                //关注可以获赠的流量
                //Gson gsonNew = new Gson();
                String urll = path + "userGetFlow/QueryFlowRule";
                JSONObject myJson = new JSONObject();
                myJson.accumulate("id", accountid);
                myJson.accumulate("opreateType", "关注");
                JSONObject contentBinding = HttpUtil.httpPost(urll, myJson, false);
                String reStrBinding = gson.toJson(contentBinding);
                Type typeBinding = new TypeToken<MerchantInfoBean>() {
                }.getType();
                MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
                //LOGGER.info(gson.toJson(contentBinding));
                // LOGGER.info("------------------------"+content.getData().get(0).getId()+"----------------------------");
                request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);

                //分享可以获赠的流量
                JSONObject myJsonShare = new JSONObject();
                myJsonShare.accumulate("id", accountid);
                myJsonShare.accumulate("opreateType", "分享");
                JSONObject contentShare = HttpUtil.httpPost(urll, myJsonShare, false);
                String reStrShare = gson.toJson(contentShare);
                Type typeShare = new TypeToken<MerchantInfoBean>() {
                }.getType();
                MerchantInfoBean merchantInfoBeanShare = gson.fromJson(reStrShare, typeShare);
                //LOGGER.info(gson.toJson(contentBinding));
                // LOGGER.info("------------------------"+content.getData().get(0).getId()+"----------------------------");
                request.getSession().setAttribute("merchantInfoBeanShare", merchantInfoBeanShare);

                //分享的内容
                String flowType = "默认页面";
//        String url = "http://localhost:8080/jwx/share/QueryActicle";
                String urlll = path + "share/QueryActicle";
                JSONObject myJsonObject = new JSONObject();
                myJsonObject.accumulate("id", accountid);
                myJsonObject.accumulate("flowType", flowType);
                JSONObject content = HttpUtil.httpPost(urlll, myJsonObject, false);
                String reStr = gson.toJson(content);
                Type type = new TypeToken<NewsItemBean>() {
                }.getType();
                // MerchantInfoBean userFlowAccountBean = gson.fromJson(reStrFlowAccount,typeFlowAccount);
                NewsItemBean newsItemBean = gson.fromJson(reStr, type);

                request.getSession().setAttribute("newsItem", newsItemBean);
                request.setAttribute("sub", "true");
//                return new ModelAndView("weixin/member/shareFlow/share-details");
                return new ModelAndView("/liuliangbao/flowmanager/share1");
            }

            String urll = path + "userGetFlow/QueryFlowRule";
            JSONObject myJson = new JSONObject();
            myJson.accumulate("id", accountid);
            myJson.accumulate("opreateType", "关注");
            JSONObject contentBinding = HttpUtil.httpPost(urll, myJson, false);
            String reStrBinding = gson.toJson(contentBinding);
            Type typeBinding = new TypeToken<MerchantInfoBean>() {
            }.getType();
            MerchantInfoBean merchantInfoBean = gson.fromJson(reStrBinding, typeBinding);
            //LOGGER.info(gson.toJson(contentBinding));
            // LOGGER.info("------------------------"+content.getData().get(0).getId()+"----------------------------");
            request.getSession().setAttribute("merchantInfoBean", merchantInfoBean);

            //分享可以获赠的流量
            JSONObject myJsonShare = new JSONObject();
            myJsonShare.accumulate("id", accountid);
            myJsonShare.accumulate("opreateType", "分享");
            JSONObject contentShare = HttpUtil.httpPost(urll, myJsonShare, false);
            String reStrShare = gson.toJson(contentShare);
            Type typeShare = new TypeToken<MerchantInfoBean>() {
            }.getType();
            MerchantInfoBean merchantInfoBeanShare = gson.fromJson(reStrShare, typeShare);
            //LOGGER.info(gson.toJson(contentBinding));
            // LOGGER.info("------------------------"+content.getData().get(0).getId()+"----------------------------");
            request.getSession().setAttribute("merchantInfoBeanShare", merchantInfoBeanShare);

            //分享的内容
            String flowType = "默认页面";
//        String url = "http://localhost:8080/jwx/share/QueryActicle";
            String urlll = path + "share/QueryActicle";
            JSONObject myJsonObject = new JSONObject();
            myJsonObject.accumulate("id", accountid);
            myJsonObject.accumulate("flowType", flowType);
            JSONObject content = HttpUtil.httpPost(urlll, myJsonObject, false);
            String reStr = gson.toJson(content);
            Type type = new TypeToken<NewsItemBean>() {
            }.getType();
            // MerchantInfoBean userFlowAccountBean = gson.fromJson(reStrFlowAccount,typeFlowAccount);
            NewsItemBean newsItemBean = gson.fromJson(reStr, type);

            request.getSession().setAttribute("newsItem", newsItemBean);

//            这是即没有关注，也没有绑定的页面
            request.setAttribute("sub", "false");
//            return new ModelAndView("weixin/member/shareFlow/share-details");
            return new ModelAndView("/liuliangbao/flowmanager/share");
        } else {

            return new ModelAndView("weixin/404");
        }

    }

    @RequestMapping(params = "QRcodePage")
    public ModelAndView QRcodePage(HttpServletRequest request) throws Exception {
        String sub = request.getParameter("sub");
        String openId = request.getParameter("openId");
        String accountid = request.getParameter("accountid");

        WeixinAccountEntity account = new WeixinAccountEntity();
        account = weixinAccountService.getEntity(WeixinAccountEntity.class, accountid);
        request.setAttribute("accountName", account.getAccountname());
//        request.setAttribute("account", account);
        String sql = "select flowValue from merchantFlowGiveRules where operateType='关注' and merchantID='" + accountid + "'";
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
//            创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                String valueSub = es.getString("flowValue");
                request.setAttribute("flowValue", valueSub);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }


        //二维码的地址
        String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
        String impUrl = account.getQRcode();
        String url = urlprefix + "/" + impUrl;
        request.setAttribute("url", url);

        if (Objects.equals(sub, "true")) {
            request.setAttribute("openId", openId);
            return new ModelAndView("liuliangbao/flowmanager/binding");
        } else {

            return new ModelAndView("weixin/member/shareFlow/noSubQRCode");
        }

    }
}
