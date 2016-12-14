package weixin.personalredpacket.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.util.CheckPic;
import weixin.guanjia.core.util.SignUtil;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.jsonbean.UserFlowAccountBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.lottery.service.WeixinLotteryServiceI;
import weixin.lottery.service.WeixinWinningrecordServiceI;
import weixin.lottery.service.WeixinWinningrecordlxcServiceI;
import weixin.member.controller.ConnectionsManager;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.personalredpacket.entity.PersonalRedpacketEntity;
import weixin.personalredpacket.entity.PersonalRedpacketSetEntity;
import weixin.personalredpacket.entity.Personalredpacketrecords;
import weixin.personalredpacket.entity.PersonalredpacketrecordsEntity;
import weixin.personalredpacket.service.PersonalRedpacketServiceI;
import weixin.personalredpacket.service.PersonalRedpacketSetServiceI;
import weixin.personalredpacket.service.PersonalredpacketrecordsServiceI;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.tenant.service.WeixinMerchantCoverAreaServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by aa on 2016/1/25.
 */
@Scope("prototype")
@Controller
@RequestMapping("/getRedpacketController")
public class GetRedpacketController extends BaseController implements PageAuthHandler {
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(MakeRedpacketController.class);

    @Autowired
    private PersonalRedpacketServiceI personalRedpacketService;
    @Autowired
    private WeixinLotteryServiceI weixinLotteryService;

    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinWinningrecordServiceI winningrecordService;
    @Autowired
    private WeixinWinningrecordlxcServiceI winningrecordlxcService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;
    @Autowired
    private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccountService;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;

    @Autowired
    private PersonalredpacketrecordsServiceI personalredpacketrecordsService;

    @Autowired
    private PersonalRedpacketSetServiceI personalRedpacketSetService;
    @Autowired
    private WeixinMerchantCoverAreaServiceI weixinMerchantCoverAreaService;
//	@Autowired
//	private PageAuthHandler pageAuthHandler;

    //	private final Logger LOGGER=Logger.getLogger("");
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 引导授权界面
     *
     * @param request
     */
    @RequestMapping(params = "getRedpacket")
    public ModelAndView getRedpacket(HttpServletRequest request, HttpServletResponse response) {
        Long start = System.currentTimeMillis();  //方法开始时间
        StringBuffer sb = new StringBuffer();
        sb.append("getRedpacketController_getRedpacket");
        String hdid = request.getParameter("hdid");
        String first = request.getParameter("first");
//        String hdid = "4028b881526439d80152643e4ffc0012";
        String url = null;
        try {
            /**
             * 红包的当前实体
             */
            PersonalRedpacketEntity hdEntity = this.systemService.get(PersonalRedpacketEntity.class, hdid);
            //活动不存在时的处理页面
            if (hdEntity == null) {
                return new ModelAndView("common/404");
            }
            String accountid = hdEntity.getAccountId();   //商户id
            request.setAttribute("accountid", accountid);
//            /**
//             * 发送者的祝福语
//             */
//            String blessword = hdEntity.getBlessing();
//            request.setAttribute("blessword", blessword);
//            /**
//             * 根据openID和accountId查询粉丝的唯一记录，可以通过活动拿到发送者的openId
//             */
//            List<WeixinMemberEntity> memberEntities = this.systemService.findHql("from WeixinMemberEntity t where 1=1  and t.openId='" + hdEntity.getOpenId() + "' and t.accountId='" + accountid + "'", null);
//            WeixinMemberEntity memberEntity = null;
//            if (memberEntities.size() > 0) {
//                memberEntity = memberEntities.get(0);
//            }
//
//            /**
//             * 1、发送者昵称
//             * 2、发送者头像
//             */
//
//            String nikename = memberEntity.getNickName();
//
//            request.setAttribute("nikename", nikename);
//            String headImg = memberEntity.getHeadImgUrl();
//            request.setAttribute("headImg", headImg);
//            /**
//             * 计算获取的流量
//             * //1.查询剩余流量leftflow
//             * //2.查询红包剩余个数leftcountredpacket
//             * //3.计算红包流量
//             */
//
//            //1.查询剩余流量leftflow
//            Double sendflow = null;
//            Double leftflow = null;
//            Double totalflow = hdEntity.getFlowvalue();
//            String sqlflow = "select sum(redFlowValue) as sendflow from weixin_personalredpacketrecords where redpackId='" + hdid + "'and accountId='" + accountid + "'";
//            Connection connection = null;
//            Statement stmt = null;
//            ResultSet es = null;
//            try {
//                //创建的jdbc连接语句
//                connection = ConnectionsManager.getMysqlConn();
//                stmt = connection.createStatement();
//                es = stmt.executeQuery(sqlflow);
//                while (es.next()) {
//                    sendflow = es.getDouble("sendflow");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                es.close();
//                stmt.close();
//                connection.close();
//            }
//            leftflow = totalflow - sendflow;
            //2.查询红包剩余个数leftcountredpacket
//            String sqlleftcount = "select count(*) from weixin_personalredpacketrecords where redpackId='" + hdid + "'and accountId='" + accountid + "'";
//            int countredpacket = personalredpacketrecordsService.getCount(sqlleftcount);
//            int leftcountredpacket = hdEntity.getRedpacketNum() - countredpacket;
//
//            /**
//             * 红包的个数小于等于0表示此时红包已经抢完了
//             */
//            if (leftcountredpacket <= 0) {
//                return new ModelAndView("weixin/personalredpacket/overFullRedpacket");
//            }
//            //3.计算红包流量
//            Double redpacketflow = null;
//            //如果剩余次数大于1，计算红包流量值，否则把剩下的流量都给最后一个红包
//            if (leftcountredpacket > 1) {
//                //计算随机范围，从1——平均数的2倍
//                int range = (int) ((leftflow / leftcountredpacket) * 20);
//                redpacketflow = (new Random().nextInt(range) / 10.0);
//                if (redpacketflow < 1.0) {
//                    redpacketflow = 1.0;
//                }
//                if ((leftflow - redpacketflow) / (leftcountredpacket - 1) < 1) {
//                    redpacketflow = leftflow / leftcountredpacket;
//                }
//            } else {
//                redpacketflow = leftflow;
//            }
//
//            java.text.DecimalFormat df = new java.text.DecimalFormat("######0.0");
//            redpacketflow = Double.parseDouble(df.format(redpacketflow));
//
////        根据个人红包制作类型查询后台红包的流量类型
//            PersonalRedpacketSetEntity personalRedpacketSet = new PersonalRedpacketSetEntity();
//            personalRedpacketSet = personalRedpacketSetService.getEntity(PersonalRedpacketSetEntity.class, hdEntity.getRedpacketsetId());
//            /**
//             * 第一种情况是：流量已经不足了，此时是相当于红包已经抢完了
//             */
//            if (leftflow <= redpacketflow) {
//                return new ModelAndView("weixin/personalredpacket/overFullRedpacket");
//            }
//            request.setAttribute("redpacketflow", redpacketflow);
//            /**
//             *  第二种情况是：红包已经过期了，此时同样是无法进行红包的拆操作
//             */
//
//            if (personalRedpacketSet.getState().equals("0")) {
//                request.setAttribute("message", "已经来晚了，红包过期了");
//                return new ModelAndView("weixin/personalredpacket/overDueRedpacket");
//            }
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("hdid", hdid);  //活动ID，传给后面用
            properties.put("first", first);  //标志制作红包的人第一次进入
            url = "";
            url = weixinAccountService.pageAuth(accountid, properties, this);   //调用授权封装:商户ID，

            sb.append("_url:" + url);
            return new ModelAndView("redirect:" + url);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/404");
        } finally {
            Long end = System.currentTimeMillis();
            sb.append("_time:" + (end - start));
            LOGGER.info(sb.toString());
        }

    }

    /**
     * 点击不放过的时候进入二维码的关注页
     *
     * @return
     */
    @RequestMapping(params = "NoattentionPublicNum")
    public ModelAndView subPage(HttpServletRequest request) {
        String accountId = request.getParameter("accountid");
        WeixinAccountEntity account = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", accountId);
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
        request.getSession().setAttribute("accountid", accountId);
        request.setAttribute("merchantInfoBean", merchantInfoBean);
        String urlprefix = ResourceUtil.getConfigByName("media.url.prefix");
        String impUrl = account.getQRcode();
        if(CheckPic.checkImg(impUrl)){
        	impUrl = urlprefix + "/" + impUrl;
        }
        request.setAttribute("url", impUrl);
        if(!account.getQRcode().equals(null)){
            String logoAccount =  account.getLogoAccount();
            if(CheckPic.checkImg(logoAccount)){
            	logoAccount = urlprefix + "/" + logoAccount;
            }
            request.setAttribute("logo", logoAccount);
        } else {
            request.setAttribute("logo", "plug-in/liuliangbao/css/0422/images/logo-1.png");
        }
        request.setAttribute("accountName", account.getAccountname());
        return new ModelAndView("weixin/member/NoattentionPublicNum");
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
        Long start = System.currentTimeMillis();  //方法开始时间
        StringBuffer sb = new StringBuffer();
        sb.append("lotteryController_followAndBind");
        try {
            //判断活动id是否存在
            String hdid = msg.getProperties().get("hdid");
            String first = msg.getProperties().get("first");
            request.setAttribute("first", first);

            if (hdid == null || "".equals(hdid)) {
                sb.append("活动ID为空，进入404页面");
                return new ModelAndView("common/404");
            }

            //获取openID
            String openId = msg.getOpenId();   //获取的是点击者的openId
            request.setAttribute("openId", openId);
            sb.append("_hdid:" + hdid + "_openId:" + openId);

            //点击的是他人分享的红包
            PersonalRedpacketEntity hdEntity = this.systemService.get(PersonalRedpacketEntity.class, hdid);
            request.setAttribute("hdEntity", hdEntity);

            String accountid = hdEntity.getAccountId();   //商户id
            request.setAttribute("accountid", accountid);
            /**
             * 发送者的祝福语
             */
            String blessword = hdEntity.getBlessing();
            request.setAttribute("blessword", blessword);
            /**
             * 根据openID和accountId查询粉丝的唯一记录，可以通过活动拿到发送者的openId
             */
            List<WeixinMemberEntity> memberEntities = this.systemService.findHql("from WeixinMemberEntity t where 1=1  and t.openId='" + hdEntity.getOpenId() + "' and t.accountId='" + accountid + "'", null);
            WeixinMemberEntity memberEntity = null;
            if (memberEntities.size() > 0) {
                memberEntity = memberEntities.get(0);
            }

            /**
             * 1、发送者昵称
             * 2、发送者头像
             */

            String nikename = memberEntity.getNickName();

            request.setAttribute("nikename", nikename);
            String headImg = memberEntity.getHeadImgUrl();
            request.setAttribute("headImg", headImg);




            /**
             * 计算获取的流量
             * //1.查询剩余流量leftflow
             * //2.查询红包剩余个数leftcountredpacket
             * //3.计算红包流量
             */

            //1.查询剩余流量leftflow
            Double sendflow = null;
            Double leftflow = null;
            Double totalflow = hdEntity.getFlowvalue();
            String sqlflow = "select sum(redFlowValue) as sendflow from weixin_personalredpacketrecords where redpackId='" + hdid + "'and accountId='" + hdEntity.getAccountId() + "'";
            Connection connection = null;
            Statement stmt = null;
            ResultSet es = null;
            try {
                //创建的jdbc连接语句
                connection = ConnectionsManager.getMysqlConn();
                stmt = connection.createStatement();
                es = stmt.executeQuery(sqlflow);
                while (es.next()) {
                    sendflow = es.getDouble("sendflow");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                es.close();
                stmt.close();
                connection.close();
            }
            leftflow = totalflow - sendflow;

            String sqlleftcount = "select count(*) from weixin_personalredpacketrecords where redpackId='" + hdid + "'and accountId='" + hdEntity.getAccountId() + "'";
            int countredpacket = personalredpacketrecordsService.getCount(sqlleftcount);
            int leftcountredpacket = hdEntity.getRedpacketNum() - countredpacket;

            /**
             * 红包的个数小于等于0表示此时红包已经抢完了
             */
            if (leftcountredpacket <= 0) {
                return new ModelAndView("weixin/personalredpacket/overFullRedpacket");
            }
            //3.计算红包流量
            Double redpacketflow = null;
            //如果剩余次数大于1，计算红包流量值，否则把剩下的流量都给最后一个红包
            if (leftcountredpacket > 1) {
                //计算随机范围，从1——平均数的2倍
                int range = (int) ((leftflow / leftcountredpacket) * 20);
                redpacketflow = (new Random().nextInt(range) / 10.0);
                if (redpacketflow < 1.0) {
                    redpacketflow = 1.0;
                }
                if ((leftflow - redpacketflow) / (leftcountredpacket - 1) < 1) {
                    redpacketflow = leftflow / leftcountredpacket;
                }
            } else {
                redpacketflow = leftflow;
            }

            java.text.DecimalFormat df = new java.text.DecimalFormat("######0.0");
            redpacketflow = Double.parseDouble(df.format(redpacketflow));

//        根据个人红包制作类型查询后台红包的流量类型
            PersonalRedpacketSetEntity personalRedpacketSetOld = new PersonalRedpacketSetEntity();
            personalRedpacketSetOld = personalRedpacketSetService.getEntity(PersonalRedpacketSetEntity.class, hdEntity.getRedpacketsetId());
            /**
             * 第一种情况是：流量已经不足了，此时是相当于红包已经抢完了
             */
            if (leftflow <= redpacketflow) {
                return new ModelAndView("weixin/personalredpacket/overFullRedpacket");
            }
            request.setAttribute("redpacketflow", redpacketflow);
            /**
             *  第二种情况是：红包已经过期了，此时同样是无法进行红包的拆操作
             */

            if (personalRedpacketSetOld.getState().equals("0")) {
                request.setAttribute("message", "已经来晚了，红包过期了");
                return new ModelAndView("weixin/personalredpacket/overDueRedpacket");
            }



            /**
             * //根据商户id查询商户所在的省市
             */
            WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
            weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", hdEntity.getAccountId());
            request.setAttribute("accountname", weixinAccountEntity.getAccountname());

            WeixinMerchantCoverAreaEntity ww = new WeixinMerchantCoverAreaEntity();
            if (weixinAccountEntity.getId() != null) {
                ww = weixinMerchantCoverAreaService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
            }
            /**
             *    商户的覆盖区域省份是
             */
            request.setAttribute("provinceAccount", ww.getProvincename());

//            /**
//             * 发送者的祝福语
//             */
//            String blessword = hdEntity.getBlessing();
//            request.setAttribute("blessword", blessword);
//            //粉丝信息
//            List<WeixinMemberEntity> memberEntities = this.systemService.findHql("from WeixinMemberEntity t where 1=1  and t.openId='" + hdEntity.getOpenId() + "' and t.accountId='" + hdEntity.getAccountId() + "'", null);
//            WeixinMemberEntity memberEntity = null;
//            if (memberEntities.size() > 0) {
//                memberEntity = memberEntities.get(0);
//            }
//
            request.setAttribute("memberEntity", memberEntity);
//            /**
//             * 1、发送者昵称
//             * 2、发送者头像
//             */
//
//            String nikename = memberEntity.getNickName();
//
//            request.setAttribute("nikename", nikename);
//            String headImg = memberEntity.getHeadImgUrl();
//            request.setAttribute("headImg", headImg);
            //商户信息
            WeixinAccountEntity accountEntity = this.systemService.getEntity(WeixinAccountEntity.class, hdEntity.getAccountId());
            request.setAttribute("accountEntity", accountEntity);
//        根据个人红包制作类型查询后台红包的流量类型
            PersonalRedpacketSetEntity personalRedpacketSet = new PersonalRedpacketSetEntity();
            personalRedpacketSet = personalRedpacketSetService.getEntity(PersonalRedpacketSetEntity.class, hdEntity.getRedpacketsetId());

//            if (personalRedpacketSet.getFlowtype().equals("省内流量")) {
//                WeixinMerchantCoverAreaEntity ww1 = new WeixinMerchantCoverAreaEntity();
//                if (weixinAccountEntity.getId() != null) {
//                    ww1 = weixinMerchantCoverAreaService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
//                }
//                request.setAttribute("flowArea", ww1.getProvincename() + "内");
//            } else {
//                request.setAttribute("flowArea", "全国任意地区");
//            }


//            WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
//            weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", hdEntity.getAccountid());
            WeixinMerchantCoverAreaEntity ww1 = new WeixinMerchantCoverAreaEntity();
            if (weixinAccountEntity.getId() != null) {
                ww1 = weixinMerchantCoverAreaService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
            }
            String cityName=ww1.getCityname();

            /**
             * 添加运营商----xiaona--2016年4月30日
             * 提示语添加运营商的类型
             */

            String businessArea=ww1.getBusinessArea();
            if(StringUtils.isBlank(businessArea) ||"三网通".equals(businessArea)){
                businessArea="所有运营商";
            }
            if(cityName==null){
                request.setAttribute("flowArea", ww1.getProvincename() + "内"+businessArea);
            }else{
                request.setAttribute("flowArea", ww1.getProvincename() + ww1.getCityname()+"内"+businessArea);
            }
            /**
             * 添加运营商----xiaona--2016年4月30日
             * 提示语添加运营商的类型
             */
            String sqltotalcount = "select count(*) from weixin_personalredpacketrecords where openId='" + openId + "'and accountId='" + accountEntity.getId() + "' and redpacksetId='" + personalRedpacketSet.getId() + "'";
            int getCountredpacket = personalredpacketrecordsService.getCount(sqltotalcount);
            request.setAttribute("leftTime", 3 - getCountredpacket > 0 ? 3 - getCountredpacket : 0);
            /**
             * //获取到活动的开始时间以及结束时间
             */

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String starttime = format.format(personalRedpacketSet.getStarttime());
            String endtime = format.format(personalRedpacketSet.getEndtime());
            request.setAttribute("startime", starttime);
            request.setAttribute("endtime", endtime);


            if (!allowNotBindPhoneNumber) {
                //根据手机号码查询 流量值
                Gson gson = new Gson();
                Type userFlowType = new TypeToken<UserFlowAccountBean>() {
                }.getType();
                String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
                JSONObject myJsonObjectFlow = new JSONObject();
                myJsonObjectFlow.accumulate("phoneNumber", memberEntity.getPhoneNumber());
                JSONObject content = HttpUtil.httpPost(url, myJsonObjectFlow, false);
                String strContent = gson.toJson(content);

                UserFlowAccountBean jsonBean = gson.fromJson(strContent, userFlowType);
                UserFlowAccountBean.DataEntity accountBean;
                accountBean = jsonBean.getData();
                String provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
                String countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
                request.setAttribute("provinceFlowValue", provinceFlowValue);
                request.setAttribute("countryFlowValue", countryFlowValue);
            }
            String hdUrl = "weixin/personalredpacket/getRedpacket";
            String hdNotUrl = "common/404";


            //分享
            String jsapi_ticket = weixinAccountService.getSignature(weixinAccountEntity.getId());
            // 注意 URL 一定要动态获取，不能 hardcode
            String requrl = request.getRequestURL().toString();
            String param = request.getQueryString();
            requrl = requrl + "?" + param;

            Map<String, String> ret = SignUtil.sign(jsapi_ticket, requrl);
            for (Map.Entry entry : ret.entrySet()) {
                LOGGER.info(entry.getKey() + ", " + entry.getValue());
            }
            ret.put("appId", weixinAccountEntity.getAccountappid());
            request.setAttribute("ret", ret);

            //链接
            String link = ResourceUtil.getConfigByName("domain") + "/" + "getRedpacketController.do?getRedpacket&hdid=" + hdid;
            request.setAttribute("link", link);

            sb.append("_hdUrl:" + hdUrl);
            return new ModelAndView(hdUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/404");
        } finally {
            Long end = System.currentTimeMillis();
            sb.append("_time:" + (end - start));
            LOGGER.info(sb.toString());
        }
    }


    /**
     * 拆红包的一些判断
     *
     * @return
     */
    @RequestMapping(params = "openRedPacket")
    @ResponseBody
    public AjaxJson openRedPacket(HttpServletRequest request) throws Exception {

        StringBuilder openRedPacket = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        openRedPacket.append("startTime_" + startTime + "ms" + "_");  //添加的日志
        AjaxJson j = new AjaxJson();
        Map<String, Object> params = new HashMap<String, Object>();
        String openId = request.getParameter("openId");  //领取人openID
        String sendOpenId = request.getParameter("sendOpenId");   //发送者openID
        String accountId = request.getParameter("accountId");
        String hdid = request.getParameter("hdid");
//        String redpacketflow = request.getParameter("redpacketflow");
        openRedPacket.append("openId" + openId + "_");  //添加的日志
        openRedPacket.append("您的ip是" + request.getRemoteHost() + "_");  //添加的日志
        /**
         * 根据活动id和accountid来唯一确定一条记
         */
        String hql = "from PersonalRedpacketEntity p where p.accountId='" + accountId + "' and p.id='" + hdid + "'";
        List<PersonalRedpacketEntity> personalRedpacket = personalRedpacketService.findHql(hql, null);
        PersonalRedpacketEntity ss = new PersonalRedpacketEntity();
        /**
         *  当前活动中红包的数量以及红包的流量值（单个的）
         */
        if (personalRedpacket.size() > 0) {
            ss = personalRedpacket.get(0);
        }
        /**
         * 发送者的祝福语
         */
        String blessword = ss.getBlessing();
        request.setAttribute("blessword", blessword);
        /**
         * //根据商户id查询商户所在的省市
         */
        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
        weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", accountId);


        /**
         *    商户的覆盖区域省份是
         */
        WeixinMerchantCoverAreaEntity ww1 = new WeixinMerchantCoverAreaEntity();
        if (weixinAccountEntity.getId() != null) {
            ww1 = weixinMerchantCoverAreaService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
        }
        String cityName=ww1.getCityname();

        /**
         * 添加运营商----xiaona--2016年4月30日
         * 提示语添加运营商的类型
         */

        String businessArea=ww1.getBusinessArea();
        if(StringUtils.isBlank(businessArea) ||"三网通".equals(businessArea)){
            businessArea="所有运营商";
        }
        if(cityName==null){
            request.setAttribute("provinceAccount", ww1.getProvincename() + "内"+businessArea);
        }else{
            request.setAttribute("provinceAccount", ww1.getProvincename() + ww1.getCityname()+"内"+businessArea);
        }



//        request.setAttribute("provinceAccount", ww.getProvincename());
        /**
         * 根据openID查询的是当前点击的相关信息
         */
        List<WeixinMemberEntity> members = this.systemService.findHql("from WeixinMemberEntity t where 1=1  and t.openId='" + openId + "' and t.accountId='" + accountId + "'", null);
        WeixinMemberEntity member = null;
        if (members.size() > 0) {
            member = members.get(0);
        }
        /**
         * 根据sendopenID和accountId查询粉丝的唯一记录,查询的是发送者的信息
         */
        List<WeixinMemberEntity> memberEntities = this.systemService.findHql("from WeixinMemberEntity t where 1=1  and t.openId='" + sendOpenId + "' and t.accountId='" + accountId + "'", null);
        WeixinMemberEntity memberEntity = null;
        if (memberEntities.size() > 0) {
            memberEntity = memberEntities.get(0);
        }

        /**
         * 1、发送者昵称
         * 2、发送者头像
         */

        String nikename = memberEntity.getNickName();

        request.setAttribute("nikename", nikename);
        String headImg = memberEntity.getHeadImgUrl();
        request.setAttribute("headImg", headImg);
//        根据个人红包制作类型查询后台红包的流量类型
        PersonalRedpacketSetEntity personalRedpacketSet = new PersonalRedpacketSetEntity();
        personalRedpacketSet = personalRedpacketSetService.getEntity(PersonalRedpacketSetEntity.class, ss.getRedpacketsetId());
//       增加对于活动日期的判断--xiaona
        Date curtime=new Date();
        if(curtime.getTime()>personalRedpacketSet.getEndtime().getTime()){
            params.put("error", "invalid");
            params.put("msg", "活动过期！");
            j.setSuccess(false);
            j.setAttributes(params);
            return j;
        }
        /**
         *  TODO  :如果您已领取过红包，那么则不允许再领取
         *  查询的是领取记录表，如果对于同一样商户id和OpenId的记录为三条，那么则会提示，你的领取次数已经超过了三次，无法领取
         *
         */
        String sqlgetcount = "select count(*) from weixin_personalredpacketrecords where openId='" + openId + "'and accountId='" + accountId + "' and redpackId='" + ss.getId() + "'";
        int getredpacket = personalredpacketrecordsService.getCount(sqlgetcount);
        if (getredpacket >= 1) {

            j.setMsg("already");
            j.setSuccess(false);
            return j;
//            return new ModelAndView("weixin/personalredpacket/gotNoRedpacket");   //此时提示你已经超过领取的上限了
        }
        /**
         *  TODO  :如果领取的次数已经超过了三次，那么则不允许再领取
         *  查询的是领取记录表，如果对于同一样商户id和OpenId的记录为三条，那么则会提示，你的领取次数已经超过了三次，无法领取
         *
         */
        String sqltotalcount = "select count(*) from weixin_personalredpacketrecords where openId='" + openId + "'and accountId='" + accountId + "' and redpacksetId='" + personalRedpacketSet.getId() + "'";
        int getCountredpacket = personalredpacketrecordsService.getCount(sqltotalcount);
        if (getCountredpacket >= 3) {
            j.setMsg("late");
            j.setSuccess(false);
            return j;
//            return new ModelAndView("weixin/personalredpacket/gotNoRedpacket");   //此时提示你已经超过领取的上限了
        }
        /**
         * 计算获取的流量
         * //1.查询剩余流量leftflow
         * //2.查询红包剩余个数leftcountredpacket
         * //3.计算红包流量
         */

        //1.查询剩余流量leftflow
        Double sendflow = null;
        Double leftflow = null;
        Double totalflow = ss.getFlowvalue();
//        对当前红包的流量值进行判断---xiaona
//        查询后台设置的当前红包的流量值----xiaona
//        PersonalRedpacketSetEntity personalRedpacketSetEntity=this.systemService.getEntity(PersonalRedpacketSetEntity.class,ss.getRedpacketsetId());
        if(totalflow>personalRedpacketSet.getRedpacketValue()){
            params.put("error", "invalid");
            params.put("msg", "您暂时无法领取流量，请稍后再试！");
            j.setSuccess(false);
            j.setAttributes(params);
            return j;
        }
        String sqlflow = "select sum(redFlowValue) as sendflow from weixin_personalredpacketrecords where redpackId='" + hdid + "'and accountId='" + accountId + "'";
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sqlflow);
            if (es.next()) {
                sendflow = es.getDouble("sendflow");
            }
            if (null == sendflow || sendflow < 0) {
                params.put("error", "invalid");
                params.put("msg", "流量币值非法！");
                j.setSuccess(false);
                j.setAttributes(params);
                return j;
            }
        } catch (Exception e) {
            e.printStackTrace();
            params.put("error", "invalid");
            params.put("msg", "意外的错误！");
            j.setSuccess(false);
            j.setAttributes(params);
//            return j;
        } finally {
            if (null != es) {
                try {
                    es.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (null != stmt) {
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        leftflow = totalflow - sendflow;
//        对于剩余的流量进行再次的判断--xiaona
        if(leftflow>personalRedpacketSet.getSubsidyValue()){
            params.put("error", "invalid");
            params.put("msg", "您暂时无法领取流量，请稍后再试！");
            j.setSuccess(false);
            j.setAttributes(params);
            return j;
        }
        //2.查询红包剩余个数leftcountredpacket
        String sqlleftcount = "select count(*) from weixin_personalredpacketrecords where redpackId='" + hdid + "'and accountId='" + accountId + "'";
        int countredpacket = personalredpacketrecordsService.getCount(sqlleftcount);
        int leftcountredpacket = ss.getRedpacketNum() - countredpacket;


        //3.计算红包流量
        Double redpacketflow = null;
        //如果剩余次数大于1，计算红包流量值，否则把剩下的流量都给最后一个红包
        if (leftcountredpacket > 1) {
            //计算随机范围，从1——平均数的2倍
            int range = (int) ((leftflow / leftcountredpacket) * 20);
            redpacketflow = (new Random().nextInt(range) / 10.0);
            if (redpacketflow < 1.0) {
                redpacketflow = 1.0;
            }
            if ((leftflow - redpacketflow) / (leftcountredpacket - 1) < 1) {
                redpacketflow = leftflow / leftcountredpacket;
            }
        } else {
            redpacketflow = leftflow;
        }

        java.text.DecimalFormat df = new java.text.DecimalFormat("######0.0");
        redpacketflow = Double.parseDouble(df.format(redpacketflow));

        // TODO 用户领取的流量值不能超过500M
        if (redpacketflow <= 0 || redpacketflow > 499) {
            params.put("error", "invalid");
            params.put("msg", "您暂时无法领取流量，请稍后再试！");
            j.setSuccess(false);
            j.setAttributes(params);
            return j;
        }
        /**
         * 根据手机号码的归属地判断是否在商户覆盖区域内，否则不赠与流量，在获取的时候再次验证-----xiaona------2016年5月3日
         */
        //判断归属地是否和商户统一，
        String url1=ResourceUtil.getConfigByName("jfinalUrl-config")+"userGetFlow/getCoverAndLocation";
        Gson gson1=new Gson();
        JSONObject myJson1 = new JSONObject();
        myJson1.accumulate("phoneNumber", member.getPhoneNumber());
        myJson1.accumulate("id", weixinAccountEntity.getId());
        JSONObject jsonObject = HttpUtil.httpPost(url1, myJson1, false);
        String strFlow1 = gson1.toJson(jsonObject);
        Type type1 = new TypeToken<Update>() {
        }.getType();
        Update update1 = gson1.fromJson(strFlow1, type1);
        if (update1.getCode().equals("201")) {
            j.setMsg("noArea");
            j.setSuccess(false);
            return j;
        }
        /**
         *  1、将用户的操作结果进行保存
         *  2、 从总的红包的流量值中进行扣除,前提是红包没有过期并且是没有被领完的情况下是可以进行拆并且成功获取到相应的流量的
         *  3、在领取记录中添加当前的用户的领取记录
         */
        PersonalredpacketrecordsEntity personalredpacketrecords = new PersonalredpacketrecordsEntity();
        personalredpacketrecords.setAccountId(accountId);
        personalredpacketrecords.setCreateTime(new Date());
        personalredpacketrecords.setOpenId(openId);
        personalredpacketrecords.setNickname(member.getNickName());
        personalredpacketrecords.setPhoneNumber(member.getPhoneNumber());
        personalredpacketrecords.setRedpackId(ss.getId());
        personalredpacketrecords.setRedpacksetId(ss.getRedpacketsetId());
        personalredpacketrecords.setRedFlowValue(redpacketflow);  //红包的流量值
        personalredpacketrecordsService.save(personalredpacketrecords);


        /**
         * 更新用户流量账户和流量账户记录
         */

        //TODO：更新流量余额-联调时放开-部署时放开
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/UpdateUserFlow";
        Gson gson = new Gson();
        JSONObject myJson = new JSONObject();
        myJson.accumulate("phoneNumber", member.getPhoneNumber());
        myJson.accumulate("flowValue", redpacketflow);
        myJson.accumulate("id", accountId);
        myJson.accumulate("opreateType", "个人红包");
        myJson.accumulate("flowType", personalRedpacketSet.getFlowtype());
        JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
        String strFlow = gson.toJson(contentFlow);
        Type type = new TypeToken<Update>() {
        }.getType();
        Update update = gson.fromJson(strFlow, type);
        /**
         * 主要是看领取记录是否已经更新了
         */
        if (!update.getCode().equals("200")) ;
        params.put("msg", "来晚了，红包被抢光了！");
        j.setSuccess(false);
        j.setAttributes(params);
        /**
         *  //当点击拆红包的时候，需要进行的相关的步骤是：
         */
        request.setAttribute("openId", openId);
        request.setAttribute("accountId", accountId);
        request.setAttribute("sendOpenId", sendOpenId);
        request.setAttribute("hdid", hdid);
        request.setAttribute("redpacketflow", redpacketflow);
        //根据openid查询昵称
        params.put("openid", openId);
        params.put("accountid", accountId);
        params.put("redpacket", redpacketflow);
        params.put("hdid", hdid);
        j.setAttributes(params);
        message = "normal";
        j.setMsg(message);
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        openRedPacket.append("endTime" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        openRedPacket.append("totaltime" + totaltime + "ms");  //添加的日志

        LOGGER.info("getRedpacketController__doBatchAdd___" + openRedPacket.toString());
//        return new ModelAndView("weixin/personalredpacket/gotRedpacket");
        return j;
    }


    /**
     * 发送者打开红包
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "senderOpenPacket")
    public ModelAndView senderOpenPacket(HttpServletRequest request) throws Exception {
        String openId = request.getParameter("openId");  //领取人openID
        String sendOpenId = request.getParameter("sendOpenId");   //发送者openID
        String accountId = request.getParameter("accountId");
        String hdid = request.getParameter("hdid");
//根据红包的真正的拥有者也就是发出者查询其相关的信息
        //粉丝信息
        List<WeixinMemberEntity> memberEntities = this.systemService.findHql("from WeixinMemberEntity t where 1=1  and t.openId='" + sendOpenId + "' and t.accountId='" + accountId + "'", null);
        WeixinMemberEntity memberEntity = null;
        if (memberEntities.size() > 0) {
            memberEntity = memberEntities.get(0);
        }
        /**
         * //根据红包的真正的拥有者也就是发出者查询其相关的信息
         */
        String nickname = memberEntity.getNickName();
        String phonenumber = memberEntity.getPhoneNumber();
        String imgpic = memberEntity.getHeadImgUrl();
        /**
         * 根据手机号码查询流量值
         */
        Gson gson = new Gson();
        Type userFlowType = new TypeToken<UserFlowAccountBean>() {
        }.getType();
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
        JSONObject myJsonObjectFlow = new JSONObject();
        myJsonObjectFlow.accumulate("phoneNumber", memberEntity.getPhoneNumber());
        JSONObject content = HttpUtil.httpPost(url, myJsonObjectFlow, false);
        String strContent = gson.toJson(content);

        UserFlowAccountBean jsonBean = gson.fromJson(strContent, userFlowType);
        UserFlowAccountBean.DataEntity accountBean;
        accountBean = jsonBean.getData();
        String provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
        String countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
        request.setAttribute("provinceFlowValue", provinceFlowValue);
        request.setAttribute("countryFlowValue", countryFlowValue);
/**
 * 根据活动id查询个人红包的流量以及份数
 */
        PersonalRedpacketEntity personalRedpacketEntity = new PersonalRedpacketEntity();

        personalRedpacketEntity = personalRedpacketService.getEntity(PersonalRedpacketEntity.class, hdid);

        Double redPacketFlow = personalRedpacketEntity.getFlowvalue();   //总的流量值

        int redPacketNum = personalRedpacketEntity.getRedpacketNum();  //红包的份数

        /**
         * 查询剩余红包的个数
         */
        //2.查询红包剩余个数leftcountredpacket
        String sqlleftcount = "select count(*) from weixin_personalredpacketrecords where redpackId='" + hdid + "'and accountId='" + accountId + "'";
        int countredpacket = personalredpacketrecordsService.getCount(sqlleftcount);
        int leftcountredpacket = redPacketNum - countredpacket;

        /**
         * 领取的流量值
         */
        /**
         * 计算获取的流量
         * //1.查询剩余流量leftflow
         * //2.查询红包剩余个数leftcountredpacket
         * //3.计算红包流量
         */

        //1.查询剩余流量leftflow
        Double sendflow = null;
        Double leftflow = null;
        String sqlflow = "select sum(redFlowValue) as sendflow from weixin_personalredpacketrecords where redpackId='" + hdid + "' and accountId='" + accountId + "'";
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sqlflow);
            while (es.next()) {
                sendflow = es.getDouble("sendflow");
            }
        } catch (Exception e) {
            e.printStackTrace();
//            return j;
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }

        /**
         * 查询所有的领取提取记录
         */

        List<PersonalredpacketrecordsEntity> lisRedPacktet = new ArrayList<PersonalredpacketrecordsEntity>();
        String hql = "from PersonalredpacketrecordsEntity r where r.redpackId='" + hdid + "' and r.accountId='" + accountId + "'";

        lisRedPacktet = personalredpacketrecordsService.findHql(hql, null);
        List<Personalredpacketrecords> list = new ArrayList<Personalredpacketrecords>();
        for (int i = 0; i < lisRedPacktet.size(); i++) {
            Personalredpacketrecords p = new Personalredpacketrecords();
            p.setId(lisRedPacktet.get(i).getId());
            p.setAccountId(lisRedPacktet.get(i).getAccountId());
            p.setNickname(lisRedPacktet.get(i).getNickname());
            p.setOpenId(lisRedPacktet.get(i).getOpenId());
            p.setPhoneNumber(lisRedPacktet.get(i).getPhoneNumber());
            p.setRedFlowValue(lisRedPacktet.get(i).getRedFlowValue());
            p.setRedpackId(lisRedPacktet.get(i).getRedpackId());
            p.setRedpacksetId(lisRedPacktet.get(i).getRedpacksetId());
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String createtime = format.format(lisRedPacktet.get(i).getCreateTime());
            p.setCreateTime(createtime);
            list.add(p);
        }
        request.setAttribute("list",list);
        /**
         * nickname:点击者昵称
         * phonenumber：手机号码
         * redPacketFlow：红包的总流量值
         * redPacketNum：红包的总个数
         * countredpacket：已经领取的份数
         * sendflow：已经领取的流量值
         * lisRedPacktet:list集合，是一个领取记录的集合
         */
//        根据个人红包制作类型查询后台红包的流量类型
        PersonalRedpacketSetEntity personalRedpacketSet = new PersonalRedpacketSetEntity();
        personalRedpacketSet = personalRedpacketSetService.getEntity(PersonalRedpacketSetEntity.class, personalRedpacketEntity.getRedpacketsetId());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endtime = format.format(personalRedpacketSet.getEndtime());
        request.setAttribute("endtime", endtime);

        request.setAttribute("imgpic", imgpic);
        request.setAttribute("nickname", nickname);
        request.setAttribute("phonenumber", phonenumber);
        request.setAttribute("redPacketFlow", redPacketFlow);
        request.setAttribute("redPacketNum", redPacketNum);
        request.setAttribute("countredpacket", countredpacket);
        request.setAttribute("sendflow", sendflow);
        request.setAttribute("lisRedPacktets", lisRedPacktet);
        return new ModelAndView("weixin/personalredpacket/senderOpenPacket");
    }

}
