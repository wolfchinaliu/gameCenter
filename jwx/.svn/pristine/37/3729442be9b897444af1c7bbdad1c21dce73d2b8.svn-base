package weixin.member.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.util.CheckPic;
import weixin.liuliangbao.jsonbean.MoreFlow.attentionAndsignInFlowEntity;
import weixin.liuliangbao.jsonbean.MoreFlow.gameFlow;
import weixin.liuliangbao.jsonbean.MoreFlow.shareFlow;
import weixin.liuliangbao.weigatedoor.entity.WeidoorpptEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.member.util.MoreFlowListService;
import weixin.oauth2.AdvancedUtil;
import weixin.oauth2.WeixinOauth2Token;
import weixin.source.controller.WeixinSourceController;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aa on 2015/12/9.()关注和签到送流量
 */
@Controller
@RequestMapping("/earnFlowController")
public class EarnFlowController extends BaseController {
    /**
     * Logger for this class write for LOGGER
     */
    private static final Logger LOGGER = Logger.getLogger(EarnFlowController.class);
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

    @RequestMapping(params = "moreFlow")
    public ModelAndView earnFlowPage(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        ModelAndView mav = new ModelAndView();

        String linkType = "更多赚取流量";
        //通过页面位置和公账号id去查图片，返回一个list(图片，图片跳转url)
        String accountid = (String) request.getSession().getAttribute("accountid");
        String nickname = (String) request.getSession().getAttribute("nickname");
        String headimgUrl = (String) request.getSession().getAttribute("headimgUrl");
/*        LOGGER.info(accountid);
        LOGGER.info(nickname);
        LOGGER.info(headimgUrl);*/

        String openId = request.getParameter("openId");

        request.setAttribute("openId", openId);
        request.setAttribute("accountid", accountid);
        //根据accountId和linkType去查图片名称
        String hql = "from WeidoorpptEntity where accountid='" + accountid + "' and pageLocation='" + linkType + "'";
        List<WeidoorpptEntity> weidoorpptList = this.systemService.findHql(hql, null);
        LOGGER.info(weidoorpptList);


        //获得图片路径
        String prefixUrl = ResourceUtil.getMediaUrlPrefix();
        LOGGER.info(prefixUrl);

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
            LOGGER.info(weidoorpptListDefault);

            //获得图片路径
            String prefixUrlDefault = ResourceUtil.getMediaUrlPrefix();
            LOGGER.info(prefixUrl);

            WeidoorpptEntity weidoorDefault = new WeidoorpptEntity();
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
        }

          /*//图片名称和url拼接号url
        String imagePathName = prefixUrl + "/" + doorImgUrl;
        LOGGER.info(imagePathName);
*/
        mav.addObject("weidoorpptlist", weidoorpptListResult);
        mav.setViewName("weixin/member/moreFlow");
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        LOGGER.info("主页的goMain方法运行时间：----" + (endTime - startTime) + "ms");
        return mav;


//        return new ModelAndView("weixin/member/moreFlow");
    }

    /**
     * 用户点击关注时的跳转页面显示,跳转页面的时候是不需要添加注解	@ResponseBody
     *
     * @return
     */
    @RequestMapping(params = "mysubscribe")
    public ModelAndView subscribeAcctListPage(HttpServletRequest request) throws Exception {
        StringBuilder subscribeAcctListPage = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        subscribeAcctListPage.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

        String bid = request.getParameter("bid");
        if (bid.equals("1")) {
            bid = "关注";

        } else {
            bid = "签到";
        }
        subscribeAcctListPage.append("bid" + bid + "_");  //添加的日志
        String openId = request.getParameter("openId");
        subscribeAcctListPage.append("获取的openId" + openId + "_");  //添加的日志
        request.setAttribute("openId", openId);
        request.setAttribute("bid", bid);       //页面标题的显示，是显示关注还是签到

        String messageInfo = null;
//        直接从前面进行获取
////从页面获取OpenId
//        String mOpenId1 = request.getParameter("MopenId");
//
////        模拟测试使用的效果图
////        String mOpenId1 = "ov5lNs4x6VIE2ZasPfsCyezGZZnc";
////        根据用户关注某个商户获取到的隶属于这个商户的openId,来查询微信公众账号的信息
        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();

        WeixinMemberEntity weixinMemberEntity = new WeixinMemberEntity();

//        weixinMemberEntity = weixinMemberService.findUniqueByProperty(WeixinMemberEntity.class, "openId", openId);
        String account_id = request.getParameter("accountid");
        if (account_id == null || account_id.equals("")) {
            weixinMemberEntity = weixinMemberService.findUniqueByProperty(WeixinMemberEntity.class, "openId", openId);
            account_id = weixinMemberEntity.getAccountId();
        }
//         Double flowvalue=
        weixinAccountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, account_id);
//根据公众账号的实体可以获取到租户或者说是商户管理表

        String id = weixinAccountEntity.getAcctId();
//        String tennaId= ResourceUtil.getSessionUserName().getTenantId();
//        weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, tenantId);
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
//获取的商业类型
        String accType = weixinAcctEntity.getBusinessType();
        subscribeAcctListPage.append("获取的商业类型accType" + accType + "_");  //添加的日志
//获取省名
        String provinceName = weixinAcctEntity.getProvince();
        subscribeAcctListPage.append("获取的当前商户所在省provinceName" + provinceName + "_");  //添加的日志
        String accId = weixinAcctEntity.getId();   //本省的商户的id
        subscribeAcctListPage.append("本省的商户的id" + accId + "_");  //添加的日志
//        List<weixinAcctFlowAccountEntity> lisFlow = new ArrayList<weixinAcctFlowAccountEntity>();
        List<attentionAndsignInFlowEntity> lisFlow = new ArrayList<attentionAndsignInFlowEntity>();

//        String hql0 = "select m from weixinAcctFlowAccountEntity m join m.weixinAcctEntity weixinAcctEntity where (m.countryFlowValue>0 OR m.provinceFlowValue>0) and weixinAcctEntity.province='" + provinceName + "' and weixinAcctEntity.businessType<>'" + accType + "'";
//查询数据库的sql语句
//        String sql0 = " select m.*,r.flowValue from MerchantFlowAccount m join weixin_acct w Join weixin_account a join merchantFlowGiveRules r where r.merchantID=m.accountId and a.id=m.accountId and w.id=a.acct_id and(m.countryFlowValue>0 OR m.provinceFlowValue>0) and w.province='" + provinceName + "' and w.business_type<>'" + accType + "'and r.operateType='" + bid + "'";
//        String sql0 = " select m.*,r.flowValue from MerchantFlowAccount m join weixin_acct w Join weixin_account a join merchantFlowGiveRules r where r.merchantID=m.accountId and a.id=m.accountId and w.id=a.acct_id and(m.countryFlowValue>0 OR m.provinceFlowValue>0) and w.province='" + provinceName + "' and w.business_type<>'1'and r.operateType='" + bid + "'";
//        String sql0 = "select m.* from MerchantFlowAccount m join weixin_acct w where (m.countryFlowValue>0 OR m.provinceFlowValue>0) and w.province='江西省' and w.business_type<>'1'";

//        String sql = " select w.*,r.flowValue from weixin_account w join MerchantFlowAccount m join weixin_acct a join merchantFlowGiveRules r where w.id=m.accountId and w.id=r.merchantID and(m.countryFlowValue>0 OR m.provinceFlowValue>0) and w.acct_id=a.id and a.province='" + provinceName + "' and (a.business_type<>'" + accType + "' or a.id='" + accId + "') and r.operateType='" + bid + "'";
        String sql = " select w.*,r.flowValue from weixin_account w join MerchantFlowAccount m join weixin_acct a join merchantFlowGiveRules r where w.id=m.accountId and w.id=r.merchantID and(m.countryFlowValue>0 OR m.provinceFlowValue>0) and w.acct_id=a.id and (a.business_type<>'" + accType + "' or a.id='" + accId + "') and r.operateType='" + bid + "'";   //只是保留行业互斥
        LogUtil.info("查询的是关注或者是签到的sql语句----------------------" + sql);
        subscribeAcctListPage.append("查询的所有本省的并且商业类型不一样的但是包含自己的商户集合的sql语句" + sql + "_");  //添加的日志
        LOGGER.info(sql);
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
//            创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            es.last();  //指针移到最后一行
            if (es.getRow() == 0) {
                messageInfo = "暂时没有商家相关列表，不好意思";
            }
            es.beforeFirst();  //复位结果集
//获取读取配置文件的地址，从而加上图片的名字就是我们需要查找的地址

            String prefixurl = ResourceUtil.getConfigByName("media.url.prefix");
            while (es.next()) {
//                weixinAcctFlowAccountEntity en = new weixinAcctFlowAccountEntity();
                attentionAndsignInFlowEntity en = new attentionAndsignInFlowEntity();
                en.setId(es.getString("id"));
                en.setAccountName(es.getString("accountname"));
                en.setLogoAccount(prefixurl + "/" + es.getString("logoAccount"));   //企业logo的目录文件名
//                en.setCountryFlowValue(es.getDouble("countryFlowValue"));
//                en.setProvinceFlowValue(es.getDouble("provinceFlowValue"));
//                en.setTenantId(es.getString("tenantId"));
//                en.setAccountId(es.getString("accountId"));
//                en.setQRcode(es.getString("QRcode"));
                en.setFlowValue(es.getString("flowValue"));
                lisFlow.add(en);
            }
//            }
//        else {
//                messageInfo = "暂时没有商家相关列表，不好意思";
//            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        subscribeAcctListPage.append("查询的所有本省的并且商业类型不一样的但是包含自己的商户的个数" + lisFlow.size() + "_");  //添加的日志
        request.setAttribute("message", messageInfo);    //将提示信息写到request里面
        request.setAttribute("listFor", lisFlow);
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        subscribeAcctListPage.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        subscribeAcctListPage.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        LOGGER.info("earnFlowController的__subscribeAcctListPage__方法执行过程中的各个操作的输入输出参数以及结果_" + subscribeAcctListPage.toString());
        if ("关注".equals(bid)) {
            return new ModelAndView("weixin/member/attentionFlow/attentionFlow");
        } else {
            return new ModelAndView("weixin/member/SignIn/signInFlow");     //签到的跳转页面
        }

    }


    @RequestMapping(params = "myPage")
    public ModelAndView myPage(HttpServletRequest request) {
        return new ModelAndView("weixin/member/myPage");
    }

    /**
     * 这是现实的点击关注的时候弹出的商家的列表
     *
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "mydatagrid")
    @ResponseBody
    public void myacctListForflow(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//从页面获取OpenId
//        String mOpenId = request.getParameter("MopenId");

//        模拟测试使用的效果图
        String mOpenId1 = "ov5lNs4x6VIE2ZasPfsCyezGZZnc";
//        根据用户关注某个商户获取到的隶属于这个商户的openId,来查询微信公众账号的信息
        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();

        WeixinMemberEntity weixinMemberEntity = new WeixinMemberEntity();

        weixinMemberEntity = weixinMemberService.findUniqueByProperty(WeixinMemberEntity.class, "openId", mOpenId1);
        String account_id = weixinMemberEntity.getAccountId();

        weixinAccountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, account_id);
//根据公众账号的实体可以获取到租户或者说是商户管理表

        String id = weixinAccountEntity.getAcctId();
//        String tennaId= ResourceUtil.getSessionUserName().getTenantId();
//        weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, tenantId);
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

        String accType = weixinAcctEntity.getBusinessType();


//        String hql=" from WeixinAcctEntity where 1 = 1 and id="+accType

//        根据省的来查询所在省的所有商户但是商户类型不能重复
//        List<WeixinAcctEntity> lisAcct = new ArrayList<WeixinAcctEntity>();
        String provinceName = weixinAcctEntity.getProvince();


//        这是之前查询不含有流量账户的查询
//        String hql = " from WeixinAcctEntity t where t.province='" + provinceName + "' and t.businessType<>'" + accType + "'";

//        lisAcct = systemService.findHql(hql, null);


        List<weixinAcctFlowAccountEntity> lisFlow = new ArrayList<weixinAcctFlowAccountEntity>();

        String hql0 = "select m from weixinAcctFlowAccountEntity m join m.weixinAcctEntity weixinAcctEntity where (m.countryFlowValue>0 OR m.provinceFlowValue>0) and weixinAcctEntity.province='" + provinceName + "' and weixinAcctEntity.businessType<>'" + accType + "'";
        //可以使用el表达式接受或者是datagrid表格进行接收


        lisFlow = systemService.findHql(hql0, null);
        dataGrid.setResults(lisFlow);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "acctPage")
    public ModelAndView acctPage(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (id != null || !id.equals("")) {
            WeixinAcctEntity weixinAcctEntity = new WeixinAcctEntity();
            weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, id);
            return new ModelAndView("weixin/member/acctPage");
        } else {
            message = "请先扫描二维码";
//            LOGGER.info(response);
            return new ModelAndView("weixin/member/testQRCode");
        }
    }


    //        携带商户的id从而查询商户的accountId
    //增加授权页面
    @RequestMapping(params = "startSubscribe")
    public ModelAndView startSubscribe(HttpServletRequest request, HttpServletResponse response) {

//        携带的商户的id
//        String acct_id = request.getParameter("id");
////        根据商户管理表id查询商户的微信账号id
//        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
//        weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId", acct_id);
//        String accountid = request.getParameter("accountid");
        String accountid = request.getParameter("id");
//		WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);
//		if (hdEntity == null) {
//			return new ModelAndView("weixin/lottery/zhuanpanNotExists");
//		}
        String rdUrl = "earnFlowController.do?goSubscribe";
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
    @RequestMapping(params = "goSubscribe")
    public ModelAndView goSubscribe(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("进入关注");

        String accountid = request.getParameter("state");
////        根据微信账号id查询商户名称
//        WeixinAccountEntity weixinAccountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, accountid);
//        String accid = weixinAccountEntity.getAcctId();
//        WeixinAcctEntity weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, accid);
////        将获取的商户管理表信息存储到request中去
//        request.setAttribute("weixinAcct", weixinAcctEntity);
        String code = request.getParameter("code");
        if (!"authdeny".equals(code)) {
//            WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);
//            String accountid = hdEntity.getAccountid();
            WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);


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

            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(account.getAccountappid(),
                    account.getAccountappsecret(), code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();

            // 获取用户信息
//            SNSUserInfo snsUserInfo = AdvancedUtil.getSnsUserInfo(accessToken, openId); // 设置要传递的参数
//            request.getSession().setAttribute("hdId", hdid);
            request.getSession().setAttribute("openId", openId);
            request.getSession().setAttribute("accountid", accountid);
//            request.getSession().setAttribute("nickname", snsUserInfo.getNickName());
//判断是否 绑定 是进入转盘，否进入 绑定 页面
            WeixinMemberEntity weixinMemberEntity = null;
            List<WeixinMemberEntity> weixinMemberEntities;
            Gson gson = new Gson();
            String hql1 = "from WeixinMemberEntity t where t.openId='" + openId + "' and t.accountId='" + accountid + "'";

            weixinMemberEntities = weixinMemberService.findHql(hql1, null);
//二维码的地址，上传的二维码的图片的存放地址的读取配置路径是sysConfig.properties
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
//            request.setAttribute();
            if (weixinMemberEntities.size() > 0) {
                weixinMemberEntity = weixinMemberEntities.get(0);
//                if(weixinMemberEntities.get(0))

            }
            if (weixinMemberEntity != null) {
                if (weixinMemberEntity.getSubscribe() == "1") {

                    return new ModelAndView("weixin/member/attentionPublicNum");
                }
            }
            request.setAttribute("openId", openId);
//            request.setAttribute("nickname", snsUserInfo.getNickName());
            return new ModelAndView("weixin/member/NoattentionPublicNum");
//request.setAttribute("acc ");

        } else {
            return new ModelAndView("common/404");
        }

    }

    /**
     * 三个省略号显示的信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "myallActivity")
    public ModelAndView myallActivity(HttpServletRequest request) throws Exception{
        String phoneNumber = request.getParameter("phone");
        String accountid = request.getParameter("accountid");
        String openId = request.getParameter("openId");
        request.setAttribute("phoneNumber", phoneNumber);    //用户的手机号
        request.setAttribute("openId", openId);  //用户的openId


//        根据accountid查询当前商户的商业类型
        WeixinAccountEntity weixinAccountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, accountid);

        WeixinAcctEntity weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, weixinAccountEntity.getAcctId());

        String tyep = weixinAcctEntity.getBusinessType();
        String accId=weixinAccountEntity.getAcctId();
        MoreFlowListService moreFlowService = new MoreFlowListService();
        List<attentionAndsignInFlowEntity> moreGZEntities = moreFlowService.queryMoreGZ(accId, tyep);  //关注签到的集合获取
        List<attentionAndsignInFlowEntity> moreQDEntities = moreFlowService.queryMoreQD(accId, tyep);  //关注签到的集合获取
        List<gameFlow> moreGameEntities = moreFlowService.queryMoreGame(accId, tyep);
        List<shareFlow> moreShareEntities = moreFlowService.queryMoreShare(accId, tyep);
        request.setAttribute("mediaurl", ResourceUtil.getConfigByName("media.url.prefix") + "/");
        request.setAttribute("url", ResourceUtil.getConfigByName("domain") + "/");
        request.setAttribute("moreGZEntities", moreGZEntities);
        request.setAttribute("moreQDEntities", moreQDEntities);
        request.setAttribute("moreGameEntities", moreGameEntities);
        request.setAttribute("moreShareEntities", moreShareEntities);

        return new ModelAndView("weixin/member/moreFlowQuery");     //签到的跳转页面
    }


}
