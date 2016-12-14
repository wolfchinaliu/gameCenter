package weixin.lottery.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
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
import weixin.lottery.entity.*;
import weixin.lottery.service.WeixinGuessRiddleServiceI;
import weixin.lottery.service.WeixinanswerRecordforRiddlesI;
import weixin.lottery.service.WeixinriddleWinningListServiceI;
import weixin.lottery.service.impl.RiddlesNum;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.tenant.service.WeixinMerchantCoverAreaServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by xiaona on 2016/2/17.
 */
@Scope("prototype")
@Controller
@RequestMapping("/guessRiddlesController")
public class GuessRiddlesController extends BaseController implements PageAuthHandler {
    private static final Logger LOGGER = Logger.getLogger(GuessRiddlesController.class);

    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinGuessRiddleServiceI weixinGuessRiddleService;
    @Autowired
    private WeixinriddleWinningListServiceI weixinriddleWinningListService;
    @Autowired
    private WeixinanswerRecordforRiddlesI weixinanswerRecordforRiddles;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private WeixinMerchantCoverAreaServiceI weixinMerchantCoverAreaService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;

    /**
     * 授权页面
     *
     * @param request
     */
    @RequestMapping(params = "startLottery")
    public ModelAndView startLottery(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long start = System.currentTimeMillis();  //
        StringBuffer sb = new StringBuffer();
        sb.append("weixinGuessRIddlesController_startLottery");
        String hdid = request.getParameter("hdid");   //
        String openId = request.getParameter("openId");
        sb.append("_hdid:" + hdid + "_openId:" + openId);
        String url = null;


        Date currDate = new Date();
        try {
            WeixinGuessRiddleEntity hdEntity = this.systemService.get(WeixinGuessRiddleEntity.class, hdid);
            if (hdEntity == null) {
                LOGGER.info(MessageFormat.format("活动[{0}]信息不存在, 跳转到404页面", hdid));
                return new ModelAndView("common/404");
            }

            /** 总的题数 */
            int totalRiddles = 0;
            totalRiddles = RiddlesNum.getRiddlesNum(hdid, hdEntity.getAccountid());
            request.setAttribute("totalRiddles", totalRiddles);

            /** 根据状态判断活动是否开始 */
            if (hdEntity.getState().equals("2")) {
                String time1 = hdEntity.getStarttime().toString();
                String time2 = hdEntity.getEndtime().toString();
                time1 = time1.substring(0, time1.length() - 2);
                time2 = time2.substring(0, time2.length() - 2);
                request.setAttribute("starttime", time1);
                request.setAttribute("endtime", time2);
                return new ModelAndView("weixin/lottery/guessRiddlesPage/beforeFlowforRiddle");
            }

            String accountid = hdEntity.getAccountid();
            request.setAttribute("accountid", accountid);
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("hdid", hdid);  //用户后台的参数传递
            url = "";
            if (org.apache.commons.lang3.StringUtils.isBlank(openId)) {
                url = weixinAccountService.pageAuth2(accountid, properties, this);   //
            } else {
                url = weixinAccountService.pageAuth2(accountid, properties, this, openId);
            }
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


    @Override
    public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request) throws Exception {
        return this.followAndBind(msg, request, false);
    }

    @Override
    public ModelAndView follow(PageAuthCallback msg, HttpServletRequest request) throws Exception {
        return this.followAndBind(msg, request, true);
    }

    public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request, boolean allowNotBindPhoneNumber) {
        Long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        sb.append("weixinGuessRiddlesController_followAndBind");
        try {
            String hdid = msg.getProperties().get("hdid");
            if (hdid == null || "".equals(hdid)) {
                return new ModelAndView("common/404");
            }

            //点击者的openID
            String openId = msg.getOpenId();
            sb.append("_hdid:" + hdid + "_openId:" + openId);
            /** 获取当前活动 */
            WeixinGuessRiddleEntity hdEntity = this.systemService.get(WeixinGuessRiddleEntity.class, hdid);

            /** 粉丝信息 */
            WeixinMemberEntity memberEntity = this.weixinMemberService.getWeixinMember(openId, hdEntity.getAccountid());
            request.setAttribute("memberEntity", memberEntity);

            int totalRiddles = 0;  //
            totalRiddles = RiddlesNum.getRiddlesNum(hdid, hdEntity.getAccountid());
            request.setAttribute("totalRiddles", totalRiddles);

            /** 根据状态判断活动是否过期 */
            if (hdEntity.getState().equals("0")) {
                request.setAttribute("message", "活动已经结束了");
                request.setAttribute("openId", openId);
                request.setAttribute("accountId", hdEntity.getAccountid());
                return new ModelAndView("weixin/lottery/guessRiddlesPage/overDueforRiddle");
            }

            /** 是否流量不足 */
            if (hdEntity.getAllFlow() - hdEntity.getGetFlow() < totalRiddles * hdEntity.getRiddleFlow()) {
                request.setAttribute("openId", openId);
                request.setAttribute("accountId", hdEntity.getAccountid());
                return new ModelAndView("weixin/lottery/guessRiddlesPage/overFlowforRiddle");
            }

            /** 商户的微信账号信息 */
            WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
            weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", hdEntity.getAccountid());
            request.setAttribute("accountname", weixinAccountEntity.getAccountname());

            WeixinMerchantCoverAreaEntity ww1 = new WeixinMerchantCoverAreaEntity();
            if (weixinAccountEntity.getId() != null) {
                ww1 = weixinMerchantCoverAreaService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
            }
            String cityName = ww1.getCityname();

            /**
             * xiaona--2016年4月30日
             * 添加运营商
             * 提示语添加运营商的类型
             */
            String businessArea = ww1.getBusinessArea();
            if (StringUtils.isBlank(businessArea) || "三网通".equals(businessArea)) {
                businessArea = "所有运营商";
            }

            if (cityName == null) {
                request.setAttribute("provinceAccount", ww1.getProvincename() + "内" + businessArea);
            } else {
                request.setAttribute("provinceAccount", ww1.getProvincename() + ww1.getCityname() + "内" + businessArea);
            }

            /** 每题流量值 */
            Double everyFlow = hdEntity.getRiddleFlow();
            request.setAttribute("everyFlow", everyFlow);

            /** 灯谜集合 */
            String hql = "from WeixinLanternRiddlesBankEntity r where r.hdid='" + hdid + "' and r.accountId='" + hdEntity.getAccountid() + "'";
            List<WeixinLanternRiddlesBankEntity> lisRiddles = this.systemService.findHql(hql, null);

            request.setAttribute("lisRiddles", lisRiddles);

            /** 查询流量账户值 */
            if (!allowNotBindPhoneNumber || StringUtils.isNotBlank(memberEntity.getPhoneNumber())) {
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
            } else {
                request.setAttribute("provinceFlowValue", "0");
                request.setAttribute("countryFlowValue", "0");
            }

            request.setAttribute("hdEntity", hdEntity);
            request.getSession().setAttribute("hdId", hdEntity.getId());
            request.setAttribute("openId", openId);

            //查询是否已经参加了本地活动.
            StringBuffer buffer = new StringBuffer();
            buffer.append("SELECT COUNT(*) count FROM weixin_riddleWinningList t where 1=1 ");
            buffer.append(" and t.openid=").append("'").append(openId).append("'");
            buffer.append(" and t.hdid=").append("'").append(hdid).append("'");
            buffer.append(" and t.accountid=").append("'").append(hdEntity.getAccountid()).append("'");
            int count = weixinriddleWinningListService.getCount(buffer.toString());
            // 表示已经参加了
            if ((1 - count) <= 0) {
                request.setAttribute("ishave", "1");
            }
            String accountid = hdEntity.getAccountid();
            request.setAttribute("accountid", accountid);
            request.getSession().setAttribute("openId", openId);
            String hdUrl = "weixin/lottery/guessRiddlesPage/guessRiddlePage";  //灯谜页面
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
            String link = ResourceUtil.getConfigByName("domain") + "/" + "guessRiddlesController.do?startLottery&hdid=" + hdid;
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
     * 未关注进入二维码
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
    }

    /**
     * 提交灯谜
     *
     * @return
     */
    @RequestMapping(params = "submitRiddleAnswer")
    @ResponseBody
    public AjaxJson submitRiddleAnswer(HttpServletRequest request) throws Exception {
        String message = "";
        StringBuilder drawRedPacket = new StringBuilder();
        long startTime = System.currentTimeMillis();//
        drawRedPacket.append("startTime_" + startTime + "ms" + "_");
        AjaxJson j = new AjaxJson();
        Map<String, Object> params = new HashMap<String, Object>();


        /**
         * 定义答题的集合
         */
        String openId = request.getParameter("openId");
        String phoneNumber = request.getParameter("phoneNumber");
        String hdid = request.getParameter("hdid");
        String accountId = request.getParameter("accountId");
        Double everyFlow = Double.parseDouble(request.getParameter("everyFlow"));
        String nickName = request.getParameter("nickName");

        /** 解决手机返回事件 */
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT COUNT(*) count FROM weixin_riddleWinningList t where 1=1 ");
        buffer.append(" and t.openid=").append("'").append(openId).append("'");
        buffer.append(" and t.hdid=").append("'").append(hdid).append("'");
        buffer.append(" and t.accountid=").append("'").append(accountId).append("'");
        int count = weixinriddleWinningListService.getCount(buffer.toString());
        if ((1 - count) <= 0) {
            j.setMsg("already");
            j.setSuccess(false);
            j.setAttributes(params);
            return j;
        }
        /** 解析json字符串 */
        String pagetextinfo = request.getParameter("answer");   //获取到的答案

        JSONArray jsonArray = JSONArray.fromObject(pagetextinfo);//解析json数组
        List<AnswerRiddle> membersAnswer = jsonArray.toList(jsonArray, AnswerRiddle.class);

        /** 将字符集不为空的添加 */
        List<AnswerRiddle> realList = new ArrayList<AnswerRiddle>();
        for (int i = 0; i < membersAnswer.size(); i++) {
            if (!membersAnswer.get(i).getAnswer().equals("")) {
                realList.add(membersAnswer.get(i));
            }
        }

//        for(int i=0 ;i<jsonArray.size();i++){
//            JSONObject.fromObject(jsonArray.get(i)).get("aa");
//        }
        //todo 拿到list集合后判断是不是为空，如果为空，那么则提示至少回答一道题

//        AnswerList test = new AnswerList();
//        test.setId("402881e752edabbc0152edb8a4cb000f");
//        test.setLanternRdown("hell");
//        membersAnswer.add(test);

        /*将获取到的json转为list集合的*/
        /** 总的题数 */
        int totalRiddles = 0;  //
        totalRiddles = RiddlesNum.getRiddlesNum(hdid, accountId);
        request.setAttribute("totalRiddles", totalRiddles);
//        int totalRiddles = Integer.parseInt(request.getParameter("totalRiddles"));
//        request.setAttribute("totalRiddles", totalRiddles);
        /** 获取的答案 */
        /** 获取灯谜的答案 */
        String hql = "from WeixinLanternRiddlesBankEntity r where r.hdid='" + hdid + "' and r.accountId='" + accountId + "'";
        List<WeixinLanternRiddlesBankEntity> weixinLanternRiddlesBankEntityList = this.systemService.findHql(hql, null);

        /** 获取剩余的流量值 */
        WeixinGuessRiddleEntity wxRiddles = weixinGuessRiddleService.getEntity(WeixinGuessRiddleEntity.class, hdid);

        Double leftFlowValue = wxRiddles.getAllFlow() - wxRiddles.getGetFlow();   //ֵ猜灯谜活动剩余流量值
        /**
         *分两种：第一种是流量不足，无法满足获取到的流量值
         * 第二种正常
         * 第三种：手机归属地不符合
         */

        List<WeixinanswerRecordforRiddlesEntity> listAnswers = new ArrayList<WeixinanswerRecordforRiddlesEntity>();
        WeixinanswerRecordforRiddlesEntity Answers = new WeixinanswerRecordforRiddlesEntity();
        message = "流量值不足ֵ";
        int succeed = 0;
        int error = 0;

        try {
            for (int i = 0; i < realList.size(); i++) {
                for (int m = 0; m < weixinLanternRiddlesBankEntityList.size(); m++) {
                    if (realList.get(i).getId().equals(weixinLanternRiddlesBankEntityList.get(m).getId())) {

                        String LanternRdowns = weixinLanternRiddlesBankEntityList.get(m).getLanternRdown();
                        /**
                         * 因为题库可能存在多个答案，需要分隔循环
                         */
                        String mAnswer = realList.get(i).getAnswer();
                        for (String LanternRdown : LanternRdowns.split(";")) {
//                            表示的是答题正确的
                            if (LanternRdown.equals(mAnswer)) {
                                succeed += 1;
                                Answers.setNickname(nickName);
                                Answers.setAccountid(accountId);
                                Answers.setAddtime(new Date());
                                Answers.setHdid(hdid);
                                Answers.setIsRight("yes");
                                Answers.setMobile(phoneNumber);
                                Answers.setOpenid(openId);
                                Answers.setRiddleflow(everyFlow);
                                Answers.setUserAnswer(mAnswer);
                                listAnswers.add(Answers);  //添加到集合
                            } else {   //答题不对的
                                Answers.setNickname(nickName);
                                Answers.setAccountid(accountId);
                                Answers.setAddtime(new Date());
                                Answers.setHdid(hdid);
                                Answers.setIsRight("no");
                                Answers.setMobile(phoneNumber);
                                Answers.setOpenid(openId);
                                Answers.setRiddleflow(everyFlow);
                                Answers.setUserAnswer(mAnswer);
                                listAnswers.add(Answers);  //添加到集合
                            }

                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            error += 1;
            message = "添加失败";
            throw new BusinessException(e.getMessage());
        }
        request.setAttribute("succeed", succeed); //
        params.put("succeed", succeed);
        params.put("totalRiddles1", totalRiddles);
        params.put("numRiddle", realList.size());
        if (StringUtils.isNotBlank(phoneNumber)) {
            // 查询手机归属地是否统一
            String url1 = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/getCoverAndLocation";
            Gson gson1 = new Gson();
            JSONObject myJson1 = new JSONObject();
            myJson1.accumulate("phoneNumber", phoneNumber);
            myJson1.accumulate("id", accountId);
            JSONObject jsonObject1 = HttpUtil.httpPost(url1, myJson1, false);
            String strFlow1 = gson1.toJson(jsonObject1);
            Type type1 = new TypeToken<Update>() {
            }.getType();
            Update update1 = gson1.fromJson(strFlow1, type1);

            //code为“10025”代表商户区域为空，为“20015”代表不是正确的手机号，
            // “200”代表在商户的覆盖区域内，messge的值为商户的覆盖区域，
            // “201"代表的不在商户的覆盖区域内，message的值为商户的覆盖区域
            if (update1.getCode().equals("201")) {
                j.setMsg("illegal");
                j.setSuccess(false);
                j.setAttributes(params);
                return j;
            }
        }
        // 中奖纪录实体
        WeixinriddleWinningListEntity wxWinnerList = new WeixinriddleWinningListEntity();
        //将信息保存
        Double totalFlowGet = everyFlow * succeed;
        params.put("totalFlowGet", totalFlowGet);
        if (totalFlowGet > leftFlowValue) {
            j.setSuccess(false);
            j.setMsg("no");
            j.setAttributes(params);
            return j;
        } else {
            /** 此时才进行保存 */
            wxRiddles.setGetFlow(wxRiddles.getGetFlow() + totalFlowGet);
            weixinGuessRiddleService.save(wxRiddles);
            // 保存获奖记录
            wxWinnerList.setOpenid(openId);
            wxWinnerList.setMobile(phoneNumber);
            wxWinnerList.setAccountid(accountId);
            wxWinnerList.setAddtime(new Date());
            wxWinnerList.setHdid(hdid);
            wxWinnerList.setNickname(nickName);
            wxWinnerList.setWinedFlow(totalFlowGet);
            weixinriddleWinningListService.save(wxWinnerList);

            // 将之前的答题记录进行中奖id的填写
            for (WeixinanswerRecordforRiddlesEntity listAnswer : listAnswers) {
                listAnswer.setJoinId(wxWinnerList.getId());
                weixinanswerRecordforRiddles.save(listAnswer);
            }

            /** 更新流量账户 */
            String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "userGetFlow/UpdateUserFlow";
            Gson gson = new Gson();
            JSONObject myJson = new JSONObject();
            myJson.element("phoneNumber", phoneNumber);
            myJson.element("flowValue", totalFlowGet);
            myJson.element("id", accountId);
            myJson.element("opreateType", "猜灯谜");
            myJson.element("flowType", wxRiddles.getFlowtype());
            myJson.element("nickName", nickName);
            myJson.element("openid", openId);
            JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
            String strFlow = gson.toJson(contentFlow);
            Type type = new TypeToken<Update>() {
            }.getType();
            Update update = gson.fromJson(strFlow, type);

            /**
             * 验证是否保存成功
             */
            if (!update.getCode().equals("200")) {
                params.put("msg", "未能成功保存");
                j.setSuccess(false);
                j.setAttributes(params);
                j.setMsg("yes");
            } else {
                if (StringUtils.isBlank(phoneNumber)) {
                    // 手机号码为空,提示页面,手机号码尚未绑定
                    j.setSuccess(false);
                    j.setMsg("noPhoneNumber");
                    j.setAttributes(params);
                } else {
                    j.setSuccess(true);
                    j.setAttributes(params);
                    j.setMsg("yes");
                }
            }

        }
        return j;
    }

    /**
     * 核查答案
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "checkAnswers")
    public ModelAndView checkAnswers(HttpServletRequest request) throws Exception {

        String accountId = request.getParameter("accountid");
        String hdid = request.getParameter("hdid");
        String openId = request.getParameter("openId");

        /** 总的题数 */
        int totalRiddles = 0;  //
        totalRiddles = RiddlesNum.getRiddlesNum(hdid, accountId);
        request.setAttribute("totalRiddles", totalRiddles);

        /** 获取灯谜的答案 */
        String hql = "from WeixinLanternRiddlesBankEntity r where r.hdid='" + hdid + "' and r.accountId='" + accountId + "'";
        List<WeixinLanternRiddlesBankEntity> lisRiddles = this.systemService.findHql(hql);
        request.setAttribute("lisRiddles", lisRiddles);

        /** 粉丝信息 */
        WeixinMemberEntity memberEntity = this.weixinMemberService.getWeixinMemberEntity(openId, accountId);
        request.setAttribute("memberEntity", memberEntity);

        /**
         * 商户的微信账号信息
         */
        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
        weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "id", accountId);
        request.setAttribute("accountname", weixinAccountEntity.getAccountname());

        WeixinMerchantCoverAreaEntity ww1 = new WeixinMerchantCoverAreaEntity();
        if (weixinAccountEntity.getId() != null) {
            ww1 = weixinMerchantCoverAreaService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAccountEntity.getAcctId());
        }
        String cityName = ww1.getCityname();

        /**
         * xiaona--2016年4月30日
         * 添加运营商
         * 提示语添加运营商的类型
         */
        String businessArea = ww1.getBusinessArea();
        if (StringUtils.isBlank(businessArea) || "三网通".equals(businessArea)) {
            businessArea = "所有运营商";
        }
        if (cityName == null) {
            request.setAttribute("provinceAccount", ww1.getProvincename() + "内" + businessArea);
        } else {
            request.setAttribute("provinceAccount", ww1.getProvincename() + ww1.getCityname() + "内" + businessArea);
        }

        /**
         * 查询流量账户值
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
        request.setAttribute("openId", openId);

        return new ModelAndView("weixin/lottery/guessRiddlesPage/checkRiddlePage");
    }

}
