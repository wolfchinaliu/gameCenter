package weixin.lottery.controller;

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
import weixin.guanjia.core.util.SignUtil;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.jsonbean.UserFlowAccountBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.liuliangbao.weigatedoor.controller.WeiDoorController;
import weixin.lottery.entity.WeixinDrawDetailEntity;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.lottery.entity.WeixinWinningrecordlxcEntity;
import weixin.lottery.service.WeixinDrawDetailServiceI;
import weixin.lottery.service.WeixinLotteryServiceI;
import weixin.lottery.service.WeixinWinningrecordServiceI;
import weixin.lottery.service.WeixinWinningrecordlxcServiceI;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.tenant.service.WeixinMerchantCoverAreaServiceI;
import weixin.util.DataDictionaryUtil.FlowType;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xuxiaoguai on 2016/1/20.
 */
@Scope("prototype")
@Controller
@RequestMapping("/shakeController")
public class ShakeController extends BaseController implements PageAuthHandler {
    private static final Logger LOGGER = Logger.getLogger(WeiDoorController.class);

    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private WeixinLotteryServiceI weixinLotteryService;
    @Autowired
    private WeixinWinningrecordServiceI winningrecordService;
    @Autowired
    private WeixinWinningrecordlxcServiceI winningrecordlxcService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;
    @Autowired
    private WeixinDrawDetailServiceI drawDetailServiceI;
    @Autowired
    private WeixinMerchantCoverAreaServiceI weixinMerchantCoverAreaService;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @RequestMapping(params = "goShake")
    public ModelAndView goShake(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("ShakeController_goShake");

        //String hdid = request.getParameter("hdid");
        String hdid = "402895e5522fce1c01522ff9d7800001";
        String openId = request.getParameter("openId");
        builder.append("_hdid:" + hdid + "_openId:" + openId);
        String url = null;
        try {
            WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);
            //活动不存在时的处理页面
            if (hdEntity == null) {
                return new ModelAndView("common/404");
            }

            String accountid = hdEntity.getAccountid();
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("hdid", hdid);  //活动ID，传给后面用
            url = "";
            if (org.apache.commons.lang3.StringUtils.isBlank(openId)) {
                url = weixinAccountService.pageAuth(accountid, properties, this);   //调用授权封装:商户ID，
            } else {
                url = weixinAccountService.pageAuth(accountid, properties, this, openId);
            }
            builder.append("_url:" + url);
            return new ModelAndView("redirect:" + url);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/404");
        } finally {
            Long end = System.currentTimeMillis();
            builder.append("_time:" + (end - startTime));
            LOGGER.info(builder.toString());
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
        StringBuilder builder = new StringBuilder();
        builder.append("ShakeController_followAndBind");
        try {
            //判断活动id是否存在
            String hdid = msg.getProperties().get("hdid");
            if (hdid == null || "".equals(hdid)) {
                builder.append("活动ID为0，进入404页面");
                return new ModelAndView("common/404");
            } else {
                request.setAttribute("hdid", hdid);
            }

            //获取openID
            String openId = msg.getOpenId();
            builder.append("_hdid:" + hdid + "_openId:" + openId);

            Gson gson = new Gson();
            Type type = new TypeToken<UserFlowAccountBean>() {
            }.getType();
            /*String accountid = (String) request.getSession().getAttribute("accountid");*/
            String accountid = msg.getAccountId();
            String nickname = (String) request.getSession().getAttribute("nickName");
            String phoneNumber = (String) request.getSession().getAttribute("phoneNumber");
            String headimgUrl = (String) request.getSession().getAttribute("headImgUrl");
            request.setAttribute("nickname", nickname);
            request.setAttribute("openid", openId);
            request.setAttribute("accountid", accountid);
            request.setAttribute("headimgUrl", headimgUrl);

            //todo:根据手机号查询用户流量获取的省内和全国流量值
            if (!allowNotBindPhoneNumber) {
                String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
                JSONObject myJsonObject = new JSONObject();
                myJsonObject.accumulate("phoneNumber", phoneNumber);
                JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
                String strContent = gson.toJson(content);
                LOGGER.info(content);

                UserFlowAccountBean jsonBean = gson.fromJson(strContent, type);

                UserFlowAccountBean.DataEntity accountBean = jsonBean.getData();
                String provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
                String countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());

                LOGGER.info(provinceFlowValue + countryFlowValue);

                request.setAttribute("provinceFlowValue", provinceFlowValue);
                request.setAttribute("countryFlowValue", countryFlowValue);
                builder.append("_provinceFlowValue:" + provinceFlowValue + "_countryFlowValue:" + countryFlowValue);
            }

            request.setAttribute("accountId", accountid);
            request.setAttribute("openid", openId);

            //////////////////////xiaoguai判断活动是否过期和是否摇完了 start ///////////////////////
            WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);
            WeixinAccountEntity accountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, accountid);
            Integer hdLeftTime = hdEntity.getLotterynumber();
            //如果活动剩余次数小于等于0那就跳转到 摇完了界面
            if (hdLeftTime <= 0) {
                LOGGER.info("此活动所有次数已经摇完了，来迟了");
            }

            //判断活动是否过期
            //判断是否处于活动日期
            Date date = new Date();
            if (hdEntity.getStarttime().after(date)) {
                builder.append("活动未开始");
                return new ModelAndView("weixin/lottery/shakeyaowanle");
            }
            if (hdEntity.getEndtime().before(date)) {
                builder.append("活动已结束");
                return new ModelAndView("weixin/lottery/shakeyaowanle");
            }

            /////////分享start
            String ticket = weixinAccountService.getSignature(accountid);
            String urll = request.getRequestURL().toString();
            String params = request.getQueryString();
            urll = urll + "?" + params;

            String domain = ResourceUtil.getConfigByName("domain");
            String urlll = domain + "/" + "shakeController.do?goShake&hdid=" + hdid;
            request.getSession().setAttribute("url", urlll);
            Map<String, String> shakeMap = SignUtil.sign(ticket, urll);
            shakeMap.put("appId", accountEntity.getAccountappid());
            request.setAttribute("shakeMap", shakeMap);

            //////////////////////xiaoguai判断活动是否过期和是否摇完了 end ///////////////////////

            //查询用户剩余游戏次数
            request.setAttribute("leftcount", leftGameTime(hdid, openId));
            return new ModelAndView("weixin/shake/weixinShakeHand");

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/404");
        } finally {
            Long end = System.currentTimeMillis();
            builder.append("_time:" + (end - startTime));
            LOGGER.info(builder.toString());
        }

    }


    @RequestMapping(params = "goShakeMotion")
    public ModelAndView GoShakeMotion(HttpServletRequest request) {
        return new ModelAndView("weixin/shake/weixinShakeHand");
    }


    //查询每个人的游戏剩余次数
    public int leftGameTime(String hdid, String openId) {
        WeixinLotteryEntity hdEntity = this.systemService.get(WeixinLotteryEntity.class, hdid);
        Integer leftpersontime = hdEntity.getLotterynumberday();

        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dm = new SimpleDateFormat("yyyy-MM");

        Date d1 = new Date();
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT COUNT(*) count FROM weixin_draw_detail t where 1=1 ");
        buffer.append(" and t.HDID=").append("'").append(hdEntity.getId()).append("'");
        buffer.append(" and t.OPENDID=").append("'").append(openId).append("'");
        Integer count = 0;
        Integer leftcount = 0;

        //查询游戏剩余次数
        if (openId != null || openId != "") {
            count = winningrecordService.getCount(buffer.toString());
            leftcount = hdEntity.getLotterynumberday() - count;
            return leftcount;
        }


        //粉丝游戏剩余次数
        return leftcount > 0 ? leftcount : 0;
    }


    //判断当前日期的周一的日期----目前还没用上这个方法
    public String convertWeekByDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        LOGGER.info("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdf.format(cal.getTime());
        LOGGER.info("所在周星期一的日期:" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        LOGGER.info("所在周星期日的日期:" + imptimeEnd);

        return imptimeBegin;
    }


    /**
     * 摇一摇之后的结果方法
     */
    @RequestMapping(params = "goShakeResult")
    public ModelAndView ShakeResult(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("debug_0220_shake_ShakeController_goShakeResult");

        String phoneNumber1 = request.getParameter("phoneNumber");

        ///////////////////////count ShakeFlowValue start/////////////////////////////
        //1.剩余流量：leftshakeflow
//        String hdId = "402895e5522fce1c01522ff9d7800001";
        String hdid = request.getParameter("hdid");
        String openid = request.getParameter("openid");

        WeixinLotteryEntity huodongEntity = weixinLotteryService.get(WeixinLotteryEntity.class, hdid);
        String sendflowtype = huodongEntity.getFlowtype();//获得活动赠送流量的类型，如果是全国流量直接继续，如果是省内流量，判断商户是否覆盖手机号码归属地了
        WeixinMemberEntity weixinMember = new WeixinMemberEntity();
        weixinMember = (WeixinMemberEntity) weixinMemberService.findHql("from WeixinMemberEntity t where t.openId=?", openid).get(0);


        Gson gson = new Gson();
        Type type = new TypeToken<UserFlowAccountBean>() {
        }.getType();
        //获取用户的手机号码，根据手机号码去重新查询账户流量值
        String phoneNumber = weixinMember.getPhoneNumber();
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
        String strContent = gson.toJson(content);


        UserFlowAccountBean jsonBean = gson.fromJson(strContent, type);
        UserFlowAccountBean.DataEntity accountBean = new UserFlowAccountBean.DataEntity();
        accountBean = jsonBean.getData();
        String provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
        String countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
        builder.append("_provinceFlowValue:" + provinceFlowValue + "_countryFlowValue:" + countryFlowValue);


//        if (sendflowtype.equals("省内流量")) {
            String urlzero = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/getCoverAndLocation";

            JSONObject myJson = new JSONObject();
            myJson.accumulate("phoneNumber", phoneNumber);
            myJson.accumulate("id", huodongEntity.getAccountid());
            JSONObject jsonObject = HttpUtil.httpPost(urlzero, myJson, false);
            String strFlow = gson.toJson(jsonObject);
            Type typeZero = new TypeToken<Update>() {
            }.getType();
            Update update = gson.fromJson(strFlow, typeZero);
            String codeZero = update.getCode();
            //String codeZero="201";

            //code为“10025”代表商户区域为空，为“20015”代表不是正确的手机号，
            // “200”代表在商户的覆盖区域内，messge的值为商户的覆盖区域，
            // “201"代表的不在商户的覆盖区域内，message的值为商户的覆盖区域


            //首先判断手机号是否被此次活动所覆盖
            // boolean flag = phoneIsBelongArea(phoneNumber1, hdid);
            if (codeZero.equals("201")) {//如果手机号码不在活动范围内的话*/
                String retrunMessage = "根据您的手机号码所在区域，目前没有商家免费赠送流量，真是悲了个剧。小榴会积极的帮您去发展。";
                request.setAttribute("retrunMessage", retrunMessage);
                LOGGER.info(builder.toString());


                return new ModelAndView("weixin/shake/shakeBeiJu");//返回不在覆盖区域的页面
            }
//        }

        //////start 商户归属地///////////////
        WeixinMerchantCoverAreaEntity ww1 = new WeixinMerchantCoverAreaEntity();
        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
        weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", huodongEntity.getAccountid());
        if (weixinAccountEntity.getId() != null) {
            ww1 = weixinMerchantCoverAreaService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
        }
        String cityName=ww1.getCityname();
        if(cityName==null){
            request.setAttribute("provinceAccount", ww1.getProvincename() + "内");
        }else{
            request.setAttribute("provinceAccount", ww1.getProvincename() + ww1.getCityname()+"内");
        }


        String shakeflow = huodongEntity.getAbledotherprize();//当前活动的总流量
        String accountid = huodongEntity.getAccountid();
        Double firstprize = Double.valueOf(huodongEntity.getFirstprize() == null ? "0" : huodongEntity.getFirstprize()); //当前活动已经被摇到的流量，也可以去获奖记录表查然后汇总

        //判断剩余流量是否充足
        Double leftshakeflow = Double.valueOf(shakeflow) - firstprize;
        if (leftshakeflow <= 0) {
            builder.append("摇完了，其实是流量不足了 ，这种情况应该不会出现的吧！");
            LOGGER.info(builder.toString());
            return new ModelAndView("weixin/shake/shakeyaowanle");
        }


        //查询游戏剩余次数，如果为0 则活动摇完了
        int firstprizetotal = huodongEntity.getFirstprizetotal() == null ? 0 : huodongEntity.getFirstprizetotal();
        if (firstprizetotal <= 0) {
            builder.append("真的摇完了");
            LOGGER.info(builder.toString());
            return new ModelAndView("weixin/shake/shakeyaowanle");
        }


        //判断是否处于活动日期
        Date date = new Date();
        if (huodongEntity.getStarttime().after(date)) {
            builder.append("活动未开始");
            request.setAttribute("accountId", accountid);
            request.setAttribute("openId", openid);
            LOGGER.info(builder.toString());
            return new ModelAndView("weixin/shake/shakelaichile");
        }
        if (huodongEntity.getEndtime().before(date)) {
            builder.append("活动已结束");
            request.setAttribute("accountId", accountid);
            request.setAttribute("openId", openid);
            LOGGER.info(builder.toString());
            return new ModelAndView("weixin/shake/shakelaichile");
        }

        //查询每个用户每次活动摇一摇的次数
        Integer lotterynumberday = huodongEntity.getLotterynumberday();
        int leftgametimeperson = leftGameTime(hdid, openid);
        if (leftgametimeperson <= 0) {
            builder.append("您的次数真的摇完了");//个人摇一摇次数用完，应该是有一个弹出框   TODO：弹出框

            //手机号，昵称，头像
            String nickname = (String) request.getSession().getAttribute("nickName");
            String headimgUrl = (String) request.getSession().getAttribute("headImgUrl");
            request.setAttribute("nickname", nickname);
            request.setAttribute("headimgUrl", headimgUrl);
            request.setAttribute("provinceFlowValue", provinceFlowValue);
            request.setAttribute("countryFlowValue", countryFlowValue);
            request.setAttribute("leftcount", leftgametimeperson);
            request.setAttribute("hdid", hdid);
            request.setAttribute("openId", openid);
            LOGGER.info(builder.toString());
            return new ModelAndView("weixin/shake/shakeCishubugou");
        }


        //3.计算摇一摇流量
        //如果剩余次数大于1，计算流量值，否则把剩下的流量都给最后一个
        Double leftshakeflowvalue = Double.valueOf(leftshakeflow);//摇一摇剩余总流量
        Integer lotterynumber = huodongEntity.getLotterynumber();//摇一摇剩余总次数
        Integer frequency = Integer.valueOf(huodongEntity.getFrequency() == null ? "0" : huodongEntity.getFrequency());//已经摇过的次数
        Integer leftcountshake = lotterynumber - frequency;//还有几次摇一摇

        String msg = "";
        double shakeflowvalue;//摇出来的流量值
        if (leftcountshake > 1) {
            //计算随机范围，从0.1——平均数的2倍
            int range = (int) ((leftshakeflowvalue / leftcountshake) * 20);
            shakeflowvalue = (new Random().nextInt(range) / 10.0) + 0.1;
            if ((leftshakeflowvalue - shakeflowvalue) / (leftcountshake - 1) < 0.1) {
                shakeflowvalue = leftshakeflowvalue / leftcountshake;
            }
        } else {
            shakeflowvalue = leftshakeflowvalue;
        }
        //将摇出来的流量保留一位小数（四舍五入）
        BigDecimal b = new BigDecimal(shakeflowvalue);
        shakeflowvalue = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        frequency += 1;
        firstprizetotal -= 1;
        firstprize += shakeflowvalue;
        leftgametimeperson -= 1;
        huodongEntity.setFirstprize(firstprize.toString());
        huodongEntity.setFirstprizetotal(firstprizetotal);
        huodongEntity.setFrequency(frequency.toString());
        systemService.saveOrUpdate(huodongEntity);

        msg = "" + shakeflowvalue;
        ///////////////////////count ShakeFlowValue end/////////////////////////////

        ////-----------------------------UpdateFlowValue start-----------------------------////
        //查询粉丝信息


        //保存抽奖记录  是好的  可以实现
        WeixinWinningrecordlxcEntity weixinWinningrecordxxg = new WeixinWinningrecordlxcEntity();
        weixinWinningrecordxxg.setAccountid(accountid);
        weixinWinningrecordxxg.setOpenid(openid);
        weixinWinningrecordxxg.setNickname(weixinMember.getNickName());
        weixinWinningrecordxxg.setMobile(weixinMember.getPhoneNumber());
        weixinWinningrecordxxg.setAddtime(new Date());
        weixinWinningrecordxxg.setPrizevalue("" + shakeflowvalue);
        weixinWinningrecordxxg.setHdid(hdid);
        winningrecordlxcService.save(weixinWinningrecordxxg);

        //保存获奖纪录---不管有没有中奖，都要保存一条摇一摇的抽奖记录
        WeixinDrawDetailEntity weixinDrawDetailxxg = new WeixinDrawDetailEntity();
        weixinDrawDetailxxg.setAccountid(accountid);
        weixinDrawDetailxxg.setOpendid(openid);
        weixinDrawDetailxxg.setAddtime(new Date());
        weixinDrawDetailxxg.setMsg(msg);
        weixinDrawDetailxxg.setHdid(hdid);
        drawDetailServiceI.save(weixinDrawDetailxxg);


        //更新商户或者活动剩余流量
        //更新用户账户流量余额
        String url2 = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/UpdateFlowAndAddFlowRecord";
        Gson gson2 = new Gson();
        JSONObject myJson1 = new JSONObject();
        myJson1.accumulate("phoneNumber", weixinMember.getPhoneNumber());
        myJson1.accumulate("flowValue", shakeflowvalue);
        myJson1.accumulate("id", accountid);
        myJson1.accumulate("opreateType", "摇一摇");
        myJson1.accumulate("openid", openid);
        myJson1.accumulate("flowType", huodongEntity.getFlowtype());
        JSONObject contentFlow = HttpUtil.httpPost(url2, myJson1, false);
        String strFlow1 = gson2.toJson(contentFlow);
        Type type2 = new TypeToken<Update>() {
        }.getType();
        Update update1 = gson2.fromJson(strFlow1, type2);
        builder.append("_shake-jfinal-parm:" + myJson.toString());
        if (!update1.getCode().equals("200")) {
            msg = "来晚了，已经被摇散了！";
            builder.append("flowupfail------errorcode：" + update1.getCode());
        }


        ////-----------------------------UpdateFlowValue end-----------------------------////


        msg = "恭喜你，摇出了" + shakeflowvalue + "M流量！";
        //手机号，昵称，头像
        String nickname = (String) request.getSession().getAttribute("nickName");
        String headimgUrl = (String) request.getSession().getAttribute("headImgUrl");
        request.setAttribute("nickname", nickname);
        request.setAttribute("headimgUrl", headimgUrl);

        ///////////////////添加方法，判断赠送流量是全国还是省内，对应加上摇出来的流量////////////////////////
        if (sendflowtype.equals(FlowType.province.getCode())) {
            provinceFlowValue=String.valueOf(Double.parseDouble(provinceFlowValue) + shakeflowvalue);
            BigDecimal c=new BigDecimal(provinceFlowValue);
            provinceFlowValue=String.valueOf(c.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
        }else{
            countryFlowValue=String.valueOf(Double.parseDouble(countryFlowValue) + shakeflowvalue);
            BigDecimal c = new BigDecimal(countryFlowValue);
            countryFlowValue=String.valueOf(c.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());

        }
        //////////////////////////////////////////

        request.setAttribute("provinceFlowValue", provinceFlowValue);
        request.setAttribute("countryFlowValue", countryFlowValue);
        request.setAttribute("ShakeFlowValue", shakeflowvalue);
        builder.append(phoneNumber+"摇出流量："+shakeflowvalue+"M");
        request.setAttribute("leftcount", leftgametimeperson);
        request.setAttribute("hdid", hdid);
        LOGGER.info(builder.toString());
        return new ModelAndView("weixin/shake/weixinShakeFlowResult");

    }

    @RequestMapping(params = "goCiShuBuGou")
    public ModelAndView goCiShuBuGou(HttpServletRequest request){
        Gson gson = new Gson();
        Type type = new TypeToken<UserFlowAccountBean>() {
        }.getType();

        String hdid = request.getParameter("hdid");
        String openid = request.getParameter("openId");
        String phoneNumber = request.getParameter("phoneNumber");


        //手机号，昵称，头像
        String nickname = (String) request.getSession().getAttribute("nickName");
        String headimgUrl = (String) request.getSession().getAttribute("headImgUrl");
        request.setAttribute("nickname", nickname);
        request.setAttribute("headimgUrl", headimgUrl);


        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
        String strContent = gson.toJson(content);
        UserFlowAccountBean jsonBean = gson.fromJson(strContent, type);
        UserFlowAccountBean.DataEntity accountBean = new UserFlowAccountBean.DataEntity();
        accountBean = jsonBean.getData();
        String provinceFlowValue = "0";
        String countryFlowValue = "0";

        if (null != accountBean) {
            provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
            countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
        }

        int leftgametimeperson = leftGameTime(hdid, openid);


        request.setAttribute("provinceFlowValue", provinceFlowValue);
        request.setAttribute("countryFlowValue", countryFlowValue);
        request.setAttribute("leftcount", leftgametimeperson);
        request.setAttribute("hdid", hdid);
        request.setAttribute("openId", openid);
        return new ModelAndView("weixin/shake/shakeCishubugou");
    }


    @RequestMapping(params = "goLaiChiLe")
    public ModelAndView goLaiChiLe(HttpServletRequest request){
        return new ModelAndView("weixin/shake/shakelaichile");
    }

    @RequestMapping(params = "goBeiJu")
    public ModelAndView goBeiJu(HttpServletRequest request){
        return new ModelAndView("weixin/shake/shakeBeiJu");
    }


    @RequestMapping(params = "goYaoWanLe")
    public ModelAndView goYaoWanLe(HttpServletRequest request){
        return new ModelAndView("weixin/shake/shakeyaowanle");
    }

    @RequestMapping(params = "goResult")
    public ModelAndView goResult(HttpServletRequest request){

        Gson gson = new Gson();
        Type type = new TypeToken<UserFlowAccountBean>() {
        }.getType();

        String hdid = request.getParameter("hdid");
        String openid = request.getParameter("openId");
        String phoneNumber = request.getParameter("phoneNumber");
        String ShakeFlowValue=request.getParameter("ShakeFlowValue");


        //手机号，昵称，头像
        String nickname = (String) request.getSession().getAttribute("nickName");
        String headimgUrl = (String) request.getSession().getAttribute("headImgUrl");
        request.setAttribute("nickname", nickname);
        request.setAttribute("headimgUrl", headimgUrl);

        String provinceFlowValue = "0";
        String countryFlowValue = "0";

        if (StringUtils.isNotBlank(phoneNumber)) {
            String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
            JSONObject myJsonObject = new JSONObject();
            myJsonObject.accumulate("phoneNumber", phoneNumber);
            JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
            String strContent = gson.toJson(content);
            UserFlowAccountBean jsonBean = gson.fromJson(strContent, type);
            UserFlowAccountBean.DataEntity accountBean = jsonBean.getData();
            provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
            countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
        }

        int leftgametimeperson = leftGameTime(hdid, openid);


        request.setAttribute("provinceFlowValue", provinceFlowValue);
        request.setAttribute("countryFlowValue", countryFlowValue);
        request.setAttribute("leftcount", leftgametimeperson);
        request.setAttribute("hdid", hdid);
        request.setAttribute("openId", openid);

        //////start 商户归属地///////////////

        WeixinLotteryEntity huodongEntity = weixinLotteryService.get(WeixinLotteryEntity.class, hdid);
        String sendflowtype = huodongEntity.getFlowtype();//获得活动赠送流量的类型，如果是全国流量直接继续，如果是省内流量，判断商户是否覆盖手机号码归属地了

        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
        weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", huodongEntity.getAccountid());
//        if (huodongEntity.getFlowtype().equals("省内流量")) {
//            WeixinMerchantCoverAreaEntity ww1 = new WeixinMerchantCoverAreaEntity();
//            if (weixinAccountEntity.getId() != null) {
//                ww1 = weixinMerchantCoverAreaService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
//            }
//            request.setAttribute("provinceAccount", ww1.getProvincename() + "内");
//        } else {
//            request.setAttribute("provinceAccount", "全国任意地区");
//        }

//        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
//        weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", hdEntity.getAccountid());
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
        } else {
            request.setAttribute("provinceAccount", ww1.getProvincename() + ww1.getCityname()+"内"+businessArea);
        }

        request.setAttribute("ShakeFlowValue",ShakeFlowValue);


        return new ModelAndView("weixin/shake/weixinShakeFlowResult");
    }


}
