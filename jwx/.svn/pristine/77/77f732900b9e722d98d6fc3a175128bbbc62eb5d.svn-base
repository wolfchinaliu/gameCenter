package weixin.liuliangbao.business.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.SignUtil;
import weixin.liuliangbao.business.entity.FamilyNumberEntity;
import weixin.liuliangbao.jsonbean.UserFlowAccountBean;
import weixin.liuliangbao.jsonbean.ViewBean.BusinessCommonJsonBean;
import weixin.liuliangbao.jsonbean.ViewBean.BusinessInterfaceBean;
import weixin.liuliangbao.jsonbean.ViewBean.FlowMealBean;
import weixin.liuliangbao.jsonbean.ViewBean.PhoneLocationBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.FamilyNumberServiceI;
import weixin.member.service.WeixinMemberServiceI;
import weixin.util.DataDictionaryUtil.FlowType;

/**
 * Created by aa on 2015/12/3.
 */

@Controller
@RequestMapping("/userChargeController")
public class UserChargeController extends BaseController implements PageAuthHandler {

    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(UserChargeController.class);

    @Autowired
    private SystemService systemService;
    private String message;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;

    @Autowired
    private WeixinMemberServiceI weixinMemberService;

    @Autowired
    private FamilyNumberServiceI familyNumberService;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /////////////////////////////////修改默认页面start/////////////////////////////////////
    @RequestMapping(params = "userChargeView")
    public ModelAndView UserChargeView(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("UserChargeView");

        String accountid = request.getParameter("accountid");

        String openId = request.getParameter("openId");

        builder.append(accountid + openId);
        String url = "";
        Map<String, String> properties = new HashMap<>();
        properties.put("accountid", accountid);
        if (StringUtils.isBlank(openId)) {
            url = weixinAccountService.pageAuth(accountid, properties, this);   //调用授权封装：商户ID，
        } else {
            url = weixinAccountService.pageAuth(accountid, properties, this, openId);
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("debug_2016_0206_" + startTime + builder.toString() + endTime);
        return new ModelAndView("redirect:" + url);
    }

    @Override
    public ModelAndView follow(PageAuthCallback msg, HttpServletRequest request) throws Exception {
        return this.followAndBind(msg, request);
    }

    @Override
    public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request) throws Exception {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("UserChargeController_followAndBind");

        ModelAndView mav = new ModelAndView();
        Gson gson = new Gson();
        Type userFlowType = new TypeToken<UserFlowAccountBean>() {
        }.getType();
        /*String provinceFlowValue=request.getParameter("provinceFlowValue");
        String countryFlowValue=request.getParameter("countryFlowValue");*/
        /*String phoneNumber = request.getParameter("phoneNumber");*/
        String phoneNumber = ((String) request.getSession().getAttribute("phoneNumber"));

        builder.append("phoneNumber" + phoneNumber);
        String accountId = msg.getAccountId();
        if (StringUtils.isBlank(accountId)) {
            accountId = (String) request.getSession().getAttribute("accountid");
        }
        String openId = msg.getOpenId();
        if (StringUtils.isBlank(openId)) {
            openId = (String) request.getSession().getAttribute("openId");
        }

        if (StringUtils.isBlank(phoneNumber)) {
            String redirectURL = "bindingController.do?redirectBinding&merchantId={0}&openId={1}&operateType={2}";
            return new ModelAndView("redirect:" + MessageFormat.format(redirectURL, accountId, openId, "用户充值"));
        }

        //根据手机号码查询手机号码归属地
        builder.append("1.根据手机号码查询手机号码归属地");
        Type phoneType = new TypeToken<PhoneLocationBean>() {
        }.getType();
        Type flowMealType = new TypeToken<FlowMealBean>() {
        }.getType();

        //调用后台获取手机号码归属地接口
        builder.append("2.调用后台获取手机号码归属地接口");
        String phoneUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "phoneLocation/getPhonelocation";

        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        JSONObject phoneContent = HttpUtil.httpPost(phoneUrl, myJsonObject, false);

        String strPhoneContent = gson.toJson(phoneContent);
        //LOGGER.info(strPhoneContent);

        PhoneLocationBean phoneJson = gson.fromJson(strPhoneContent, phoneType);

        String provinceName = phoneJson.getData().getProvinceName();
        String businessName = phoneJson.getData().getBusinessName();
        String strBusinessCode = phoneJson.getData().getBusinessCode();
        String strProvinceCode = phoneJson.getData().getProvinceCode();
        builder.append("_provinceName" + provinceName + "_businessName" + businessName + "_strBusinessCode" + strBusinessCode + "_strProvinceCode" + strProvinceCode);

        //根据手机号码查询 流量值
        builder.append("3.根据手机号码查询 流量值");
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
        JSONObject myJsonObjectFlow = new JSONObject();
        myJsonObjectFlow.accumulate("phoneNumber", phoneNumber);
        JSONObject content = HttpUtil.httpPost(url, myJsonObjectFlow, false);
        String strContent = gson.toJson(content);

        UserFlowAccountBean.DataEntity accountBean = ((UserFlowAccountBean) gson.fromJson(strContent, userFlowType)).getData();

        String provinceFlowValue = "0";
        String countryFlowValue = "0";
        if (null != accountBean) {
            provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
            countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
        }

        //LOGGER.info(provinceFlowValue + countryFlowValue);
        builder.append("_provinceFlowValue:" + provinceFlowValue + "_countryFlowValue:" + countryFlowValue);


        //根据手机号码和运营商类型查询流量套餐
        builder.append("4.根据手机号码和运营商类型查询流量套餐");
        String flowMealUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "flowMeal/QueryFlowMealByBusiness";
        JSONObject myJsonObject1 = new JSONObject();
        myJsonObject1.accumulate("businessCode", strBusinessCode);
        myJsonObject1.accumulate("provinceCode", strProvinceCode);
        JSONObject flowMealContent = HttpUtil.httpPost(flowMealUrl, myJsonObject1, false);

        String strFlowMealContent = gson.toJson(flowMealContent);

        FlowMealBean flowMealJson = gson.fromJson(strFlowMealContent, flowMealType);
        List<FlowMealBean.DataEntity> flowMealJsonBean = new ArrayList<FlowMealBean.DataEntity>();
        flowMealJsonBean = flowMealJson.getData();
        mav.addObject("flowMealJsonBean", flowMealJsonBean);

        ////////////////////--给页面赋值---/////////////////////
        String accountid=(String) request.getSession().getAttribute("accountid");
        request.setAttribute("provinceFlowValue", provinceFlowValue);
        request.setAttribute("countryFlowValue", countryFlowValue);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("openid", openId);

        request.setAttribute("provinceName", provinceName);
        request.setAttribute("businessName", businessName);
        request.setAttribute("provinceCode", strProvinceCode);
        request.setAttribute("businessCode", strBusinessCode);
        request.setAttribute("accountId", (String) request.getSession().getAttribute("accountid"));

        /**
         * 分享功能，为了规避微信自带分享使得出现个人信息透露的现象
         */
        WeixinAccountEntity weixinAccountEntity=this.systemService.getEntity(WeixinAccountEntity.class,accountid);
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
        String link = ResourceUtil.getConfigByName("domain") + "/" + "getRedpacketController.do?NoattentionPublicNum&accountid=" + accountid;
        request.setAttribute("link", link);
        /** 分享功能，为了规避微信自带分享使得出现个人信息透露的现象 */
        long runTime = System.currentTimeMillis() - startTime;

        builder.append("_accountid" + (String) request.getSession().getAttribute("accountid"));
        LOGGER.info("debug_2016_0206_startTime" + startTime + "runTime:" + runTime + builder);
        mav.setViewName("liuliangbao/weigatedoor/usercharge");
        return mav;
    }


    /////////////////////////////////修改默认页面end/////////////////////////////////////


    /**
     * is exist province business interface
     * xudan
     * 2015-12-9 11:39:08
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "validateIsHaveInterface")
    public void validateIsHaveInterface(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("UserChargeController_validateIsHaveInterface");

        Gson gson = new Gson();
        Type type = new TypeToken<BusinessInterfaceBean>() {
        }.getType();

        String phoneNumber = request.getParameter("phoneNumber");
        String strBusinessCode = request.getParameter("businessCode");
        String strProvinceCode = request.getParameter("provinceCode");

        //获取手机号码归属地之前判断该省是否存在可用的接口
        String businessurl = ResourceUtil.getConfigByName("jfinalUrl-config") + "business/QueryBusinessInterfaceByCode";
        JSONObject myJsonObjectbusiness = new JSONObject();

        myJsonObjectbusiness.accumulate("businessCode", strBusinessCode);
        myJsonObjectbusiness.accumulate("provinceCode", strProvinceCode);
        JSONObject contentInterface = HttpUtil.httpPost(businessurl, myJsonObjectbusiness, false);

        String strcontent = gson.toJson(contentInterface);
        BusinessInterfaceBean interfaceBean = gson.fromJson(strcontent, type);


        List<BusinessInterfaceBean.DataEntity> dataEntityList = new ArrayList<BusinessInterfaceBean.DataEntity>();
        dataEntityList = interfaceBean.getData();
        String returnStr;
        if (dataEntityList != null && dataEntityList.size() > 0) {

            returnStr = String.valueOf("1");
            builder.append("provinceinterface" + dataEntityList.size());
        } else {
            returnStr = "请选择去充值全国流量";
        }


        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("returnMsg", returnStr);
        String json = objectMapper.writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        long runTime = System.currentTimeMillis() - startTime;

        try {
            //LOGGER.info("-----转json后的结果是------------");
            PrintWriter out = response.getWriter();
            out.write(json);
            LOGGER.info("debug_2016_0206  runTime" + runTime + builder.toString());
            //LOGGER.info(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(params = "validateHaveInterface")
    public int validateHaveInterface(String businessCode, String provinceCode) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("UserChargeController_validateHaveInterface");
        Gson gson = new Gson();
        Type type = new TypeToken<BusinessInterfaceBean>() {
        }.getType();


        //获取手机号码归属地之前判断该省是否存在可用的接口
        String businessurl = ResourceUtil.getConfigByName("jfinalUrl-config") + "business/QueryBusinessInterfaceByCode";
        JSONObject myJsonObjectbusiness = new JSONObject();

        myJsonObjectbusiness.accumulate("businessCode", businessCode);
        myJsonObjectbusiness.accumulate("provinceCode", provinceCode);
        JSONObject contentInterface = HttpUtil.httpPost(businessurl, myJsonObjectbusiness, false);

        String strcontent = gson.toJson(contentInterface);
        BusinessInterfaceBean interfaceBean = gson.fromJson(strcontent, type);

        List<BusinessInterfaceBean.DataEntity> dataEntityList = new ArrayList<BusinessInterfaceBean.DataEntity>();
        dataEntityList = interfaceBean.getData();
        int returnStr;
        if (dataEntityList != null) {
            returnStr = dataEntityList.size();
            builder.append("接口：" + returnStr);
        } else {
            returnStr = 0;
            builder.append("没有接口");
        }
        long runTime = System.currentTimeMillis() - startTime;
        LOGGER.info("debug_2016_0206runTime" + runTime + builder.toString());
        return returnStr;
    }


    /**
     * get phoneNumber location
     * xudan-2015-12-9 11:38:05
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "testValidate")
    public void testValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        //String flowType = request.getParameter("flowType");
        //LOGGER.info(flowType);

        Gson gson = new Gson();
        Type type = new TypeToken<PhoneLocationBean>() {
        }.getType();
        Type type1 = new TypeToken<FlowMealBean>() {
        }.getType();

        //获取页面输入的手机号码的值
        LOGGER.info(phoneNumber);


        //调用后台获取手机号码归属地接口
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "phoneLocation/getPhonelocation";

        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
        //LOGGER.info(content);

        String strcontent = gson.toJson(content);
        LOGGER.info(strcontent);

        PhoneLocationBean phoneJson = gson.fromJson(strcontent, type);

        String strProvince = phoneJson.getData().getProvinceName();
        String strBusinessName = phoneJson.getData().getBusinessName();
        String strBusinessCode = phoneJson.getData().getBusinessCode();
        String strProvinceCode = phoneJson.getData().getProvinceCode();

        LOGGER.info(strProvince);
        LOGGER.info(strBusinessName);


        //根据运营商类型调用流量套餐接口
        String flowUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "flowMeal/QueryFlowMealByBusiness";
        JSONObject myJsonObject1 = new JSONObject();
        myJsonObject1.accumulate("businessCode", strBusinessCode);
        JSONObject content1 = HttpUtil.httpPost(flowUrl, myJsonObject1, false);

        String strcontent1 = gson.toJson(content1);
        LOGGER.info(strcontent1);

        FlowMealBean flowJson = gson.fromJson(strcontent1, type1);

        String strFlowValue = "";

        for (int i = 0; i < flowJson.getData().size(); i++) {
            strFlowValue = strFlowValue + flowJson.getData().get(i).getFlowValue() + "M  ";
        }

        LOGGER.info(strFlowValue);

        // request.setAttribute("flowMealBean",flowJson);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("provinceName", strProvince + " " + strBusinessName);
        map.put("businessName", strBusinessName);
        /*map.put("provinceBusiness","北京市 移动");*/
        map.put("flowValue1", "10M");
        map.put("flowValue2", "30M");
        map.put("flowValue3", "50M");
        map.put("flowValue", strFlowValue);
        map.put("businessCode", strBusinessCode);
        map.put("provinceCode", strProvinceCode);

        String json = objectMapper.writeValueAsString(map);
        LOGGER.info(json);
        response.setContentType("application/json;charset=UTF-8");
        try {
            LOGGER.info("-----转json后的结果是------------");
            PrintWriter out = response.getWriter();
            out.write(json);
            LOGGER.info(json);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * user chargeFlow
     * xudan-2015-12-9 11:38:25
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "testChargeFlow")
    public void testChargeFlow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("UserChargeController_testChargeFlow");

        Gson gson = new Gson();
        Type type = new TypeToken<BusinessCommonJsonBean>() {
        }.getType();
        String strFlowValue = request.getParameter("flowTrueValue");

        String phoneNumber = request.getParameter("phoneNumber");
        String businessCode = request.getParameter("businessCode");
        String provinceCode = request.getParameter("provinceCode");
        String flowType = request.getParameter("flowType");
        String familyNumber = request.getParameter("familyNumber");
        String accountid=request.getParameter("accountId");
        String openid = request.getParameter("openid");
        if(!FlowType.province.getCode().equals(flowType) && !FlowType.country.getCode().equals(flowType)){
            builder.append(MessageFormat.format("流量类型有误", flowType));

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("returnMessage", "流量类型有误");

            String json = objectMapper.writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");

            try {
                PrintWriter out = response.getWriter();
                out.write(json);
                LOGGER.info(json);
                long runTime = System.currentTimeMillis() - startTime;
                LOGGER.info("debug_2016_0618runTime" + runTime + builder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return;
        }
        if (weixinMemberService.getPhoneNumberInBlackList(phoneNumber) || weixinMemberService.getPhoneNumberInBlackList(familyNumber)) {
            builder.append(MessageFormat.format("用户的手机号{0}为黑名单列表中的手机号码，禁止其进行操作", phoneNumber));

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("returnMessage", "您的权限不足，无法操作");

            String json = objectMapper.writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");

            try {
                PrintWriter out = response.getWriter();
                out.write(json);
                LOGGER.info(json);
                long runTime = System.currentTimeMillis() - startTime;
                LOGGER.info("debug_2016_0618runTime" + runTime + builder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return;
        }
        
        if(StringUtils.isBlank(openid)){
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("returnMessage", "您的权限不足（openId为空），无法操作");

            String json = objectMapper.writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");

            try {
                PrintWriter out = response.getWriter();
                out.write(json);
                LOGGER.info(json);
                long runTime = System.currentTimeMillis() - startTime;
                LOGGER.info("debug_2016_0618runTime" + runTime + builder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            builder.append(MessageFormat.format("用户的手机号{0}没传递openid，冒领流量", phoneNumber));
            return;
        }
        WeixinMemberEntity member = this.weixinMemberService.getWeixinMember(openid, accountid);

        if(member == null || !StringUtils.equals(phoneNumber, member.getPhoneNumber())){
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("returnMessage", "冒领流量");

            String json = objectMapper.writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");

            try {
                PrintWriter out = response.getWriter();
                out.write(json);
                LOGGER.info(json);
                long runTime = System.currentTimeMillis() - startTime;
                LOGGER.info("debug_2016_0618runTime" + runTime + builder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            builder.append(MessageFormat.format("openid {0} 与phonenumber {0} 不符，冒领流量", openid,phoneNumber));
            return;
        }
        builder.append("phoneNumber" + phoneNumber + "familyNumber" + familyNumber + "_businessCode" + businessCode + "_provinceCode" + provinceCode + "_flowType" + flowType);
        
        /*
         *限制全国流量的移动通道 
         * String phonelocationurl = ResourceUtil.getConfigByName("jfinalUrl-config") + "phoneLocation/getPhonelocation";
        Gson phonegson = new Gson();
        Type typephone = new TypeToken<PhoneLocationBean>() {
        }.getType();
        JSONObject phonejson = new JSONObject();
        phonejson.accumulate("phoneNumber", familyNumber);
        JSONObject content = HttpUtil.httpPost(phonelocationurl, phonejson, false);
        //LOGGER.info(content);

        String strcontent = phonegson.toJson(content);
        LOGGER.info(strcontent);

        PhoneLocationBean phoneJson = phonegson.fromJson(strcontent, typephone);
        String strBusinessCode = phoneJson.getData().getBusinessCode();
        String strProvinceCode = phoneJson.getData().getProvinceCode();
        if("1".equals(strBusinessCode)){
            if(!(StringUtils.equals(flowType, "省内") && StringUtils.equals(strProvinceCode, "14"))){
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = new HashMap<String, Object>();
                String msg = "受运营商限制。流量领取暂停服务1～2天，给您带来不便，敬请谅解";
                map.put("returnMessage", msg);
                builder.append("");
                String json = objectMapper.writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");

                try {
                    LOGGER.info("-----转json后的结果是------------");
                    PrintWriter out = response.getWriter();
                    out.write(json);
                    LOGGER.info(json);
                    LOGGER.info("debug_2016_0206_runTime" + builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ;
            }
        }*/
        
        //如果选择的是省内流量套餐的话，首先要查该生是否有接口
        if (flowType.equals(FlowType.province.getCode())) {
            builder.append("充值省内流量");
            if (phoneNumber.equals(familyNumber)) {//自己给自己充值
                //根据运营商Code和省Code去查接口
               /* int returnStr = this.validateHaveInterface(businessCode, provinceCode);
                if (returnStr > 0) { //>0说明有省内接口*/

                String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/ChargeUserFlow";
                JSONObject myJsonObject2 = new JSONObject();
                myJsonObject2.accumulate("phoneNumber", phoneNumber);

                /////////////////add 判断此用户账户流量是否充足 start/////////
                builder.append("判断此用户账户流量是否充足");
                if (ValidateFlowIsEnough(phoneNumber, "provinceFlowValue", strFlowValue) == true) {
                    builder.append("：充足");
                    myJsonObject2.accumulate("businessCode", businessCode);
                    myJsonObject2.accumulate("provinceCode", provinceCode);
                    myJsonObject2.accumulate("flowValue", strFlowValue);
                    myJsonObject2.accumulate("flowType", flowType);
                    myJsonObject2.accumulate("familyNumber", familyNumber);
                    myJsonObject2.accumulate("accountid",accountid);
                    JSONObject content1 = HttpUtil.httpPost(url, myJsonObject2, false);

                    String strcontent1 = gson.toJson(content1);
                    LOGGER.info(strcontent1);


                    BusinessCommonJsonBean userChargedJson = gson.fromJson(strcontent1, type);
                    String restrnMsg = userChargedJson.getMsg();


                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("returnMessage", restrnMsg);

                    String json = objectMapper.writeValueAsString(map);
                    response.setContentType("application/json;charset=UTF-8");

                    try {
                        LOGGER.info("-----转json后的结果是------------");
                        PrintWriter out = response.getWriter();
                        out.write(json);
                        LOGGER.info(json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> map = new HashMap<String, Object>();
                    //map.put("returnMessage", "您的后台流量余额不足，您可以选择继续赚取流量，当前网页刷新不及时请您谅解");
                    map.put("returnMessage", "flowNotEnough");
                    builder.append("后台流量余额不足");
                    String json = objectMapper.writeValueAsString(map);
                    response.setContentType("application/json;charset=UTF-8");

                    try {
                        LOGGER.info("-----转json后的结果是------------");
                        PrintWriter out = response.getWriter();
                        out.write(json);
                        LOGGER.info(json);
                        long runTime = System.currentTimeMillis() - startTime;
                        LOGGER.info("debug_2016_0206_runTime" + runTime + builder.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /////////////////add 判断此用户账户流量是否充足 end/////////


               /* } else { //否则说明没有省内接口
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("returnMessage", "请选择去充值国内流量");
                    builder.append("没有省内接口");
                    String json = objectMapper.writeValueAsString(map);
                    response.setContentType("application/json;charset=UTF-8");

                    try {
                        LOGGER.info("-----转json后的结果是------------");
                        PrintWriter out = response.getWriter();
                        out.write(json);
                        LOGGER.info(json);
                        long runTime = System.currentTimeMillis() - startTime;
                        LOGGER.info("debug_2016_0206_runTime" + runTime + builder.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/

            } else {//自己给亲情号冲值
                builder.append("自己给亲情号冲值");
                List<FamilyNumberEntity> familyNumberEntityList = this.systemService.findHql("from FamilyNumberEntity t where t.mobilePhone='" + phoneNumber + "' and t.familyNumber='" + familyNumber + "'");
                FamilyNumberEntity familyNumberEntity = new FamilyNumberEntity();
                if (familyNumberEntityList.size() > 0) {
                    familyNumberEntity = familyNumberEntityList.get(0);
                }
                if (familyNumberEntity.getStatus().equals("1")) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("returnMessage", "不能赠送给本省以外的区域，请谅解。");

                    String json = objectMapper.writeValueAsString(map);
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.write(json);
                } else {
                    //根据运营商Code和省Code去查接口
                    int returnStr = this.validateHaveInterface(businessCode, provinceCode);
                    if (returnStr > 0) { //>0说明有省内接口

                        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/ChargeUserFlow";
                        JSONObject myJsonObject2 = new JSONObject();
                        myJsonObject2.accumulate("phoneNumber", phoneNumber);

                        /////////////////add 判断此用户账户流量是否充足 start/////////
                        if (ValidateFlowIsEnough(phoneNumber, "provinceFlowValue", strFlowValue) == true) {
                            myJsonObject2.accumulate("businessCode", businessCode);
                            myJsonObject2.accumulate("provinceCode", provinceCode);
                            myJsonObject2.accumulate("flowValue", strFlowValue);
                            myJsonObject2.accumulate("flowType", flowType);
                            myJsonObject2.accumulate("familyNumber", familyNumber);
                            myJsonObject2.accumulate("accountid",accountid);
                            JSONObject content1 = HttpUtil.httpPost(url, myJsonObject2, false);

                            String strcontent1 = gson.toJson(content1);
                            LOGGER.info(strcontent1);


                            BusinessCommonJsonBean userChargedJson = gson.fromJson(strcontent1, type);
                            String restrnMsg = userChargedJson.getMsg();


                            ObjectMapper objectMapper = new ObjectMapper();
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("returnMessage", restrnMsg);

                            String json = objectMapper.writeValueAsString(map);
                            response.setContentType("application/json;charset=UTF-8");

                            try {
                                LOGGER.info("-----转json后的结果是------------");
                                PrintWriter out = response.getWriter();
                                out.write(json);
                                LOGGER.info(json);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            ObjectMapper objectMapper = new ObjectMapper();
                            Map<String, Object> map = new HashMap<String, Object>();
                            //map.put("returnMessage", "您的后台流量余额不足，您可以选择继续赚取流量，当前网页刷新不及时请您谅解");
                            map.put("returnMessage", "flowNotEnough");
                            builder.append("后台流量余额不足");
                            String json = objectMapper.writeValueAsString(map);
                            response.setContentType("application/json;charset=UTF-8");

                            try {
                                LOGGER.info("-----转json后的结果是------------");
                                PrintWriter out = response.getWriter();
                                out.write(json);
                                LOGGER.info(json);
                                long runTime = System.currentTimeMillis() - startTime;
                                LOGGER.info("debug_2016_0206_runTime" + runTime + builder.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        /////////////////add 判断此用户账户流量是否充足 end/////////


                    } else { //否则说明没有省内接口
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("returnMessage", "请选择去充值全国流量");

                        String json = objectMapper.writeValueAsString(map);
                        response.setContentType("application/json;charset=UTF-8");

                        try {
                            LOGGER.info("-----转json后的结果是------------");
                            PrintWriter out = response.getWriter();
                            out.write(json);
                            LOGGER.info(json);
                            long runTime = System.currentTimeMillis() - startTime;
                            LOGGER.info("debug_2016_0206runTime" + runTime + builder.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } else {//选择的是全国流量
            flowType="1";
            builder.append("选择的是全国流量");
            String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/ChargeUserFlow";
            JSONObject myJsonObject2 = new JSONObject();
            myJsonObject2.accumulate("phoneNumber", phoneNumber);


            if (ValidateFlowIsEnough(phoneNumber, "countryFlowValue", strFlowValue) == true) {
                myJsonObject2.accumulate("businessCode", businessCode);
                myJsonObject2.accumulate("provinceCode", provinceCode);
                myJsonObject2.accumulate("flowValue", strFlowValue);
                myJsonObject2.accumulate("flowType", flowType);
                myJsonObject2.accumulate("familyNumber", familyNumber);
                myJsonObject2.accumulate("accountid",accountid);
                JSONObject content1 = HttpUtil.httpPost(url, myJsonObject2, false);

                String strcontent1 = gson.toJson(content1);
                LOGGER.info(strcontent1);


                BusinessCommonJsonBean userChargedJson = gson.fromJson(strcontent1, type);
                String restrnMsg = userChargedJson.getMsg();


                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("returnMessage", restrnMsg);
                builder.append(restrnMsg);

                String json = objectMapper.writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");

                try {
                    LOGGER.info("-----转json后的结果是------------");
                    PrintWriter out = response.getWriter();
                    out.write(json);
                    LOGGER.info(json);
                    long runTime = System.currentTimeMillis() - startTime;
                    LOGGER.info("debug_2016_0206runTime" + runTime + builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("returnMessage", "flowNotEnough");

                String json = objectMapper.writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");

                try {
                    LOGGER.info("-----转json后的结果是------------");
                    PrintWriter out = response.getWriter();
                    out.write(json);
                    LOGGER.info(json);
                    long runTime = System.currentTimeMillis() - startTime;
                    LOGGER.info("debug_2016_0206runTime" + runTime + builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }


    }

    /////////////////add 判断此用户账户流量是否充足 start///////////////////
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
        LOGGER.info(contentValFlow);

        UserFlowAccountBean jsonBean = gson.fromJson(strContent, typeValFlow);

        UserFlowAccountBean.DataEntity accountBean = new UserFlowAccountBean.DataEntity();
        accountBean = jsonBean.getData();
        String provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
        String countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
        //builder.append("provinceFlowValue" + provinceFlowValue + "_countryFlowValue" + countryFlowValue);

        if (flowType == "countryFlowValue") {
            builder.append("countryFlowValue");
            if (Double.valueOf(flowValue) <= Double.valueOf(countryFlowValue)) {
                builder.append("Enough");
                long runTime = System.currentTimeMillis() - startTime;
                LOGGER.info("runTime" + runTime + builder.toString());
                return true;

            } else {
                builder.append("notEnough");
                long runTime = System.currentTimeMillis() - startTime;
                LOGGER.info("runTime" + runTime + builder.toString());
                return false;
            }
        } else {
            if (Double.valueOf(flowValue) <= Double.valueOf(provinceFlowValue)) {
                builder.append("Enough");
                long runTime = System.currentTimeMillis() - startTime;
                LOGGER.info("runTime" + runTime + builder.toString());
                return true;
            } else {
                builder.append("notEnough");
                long runTime = System.currentTimeMillis() - startTime;
                LOGGER.info("runTime" + runTime + builder.toString());
                return false;
            }
        }

    }


    public static void main(String[] args) throws Exception {
        /*Gson gson = new Gson();
        String url = "http://localhost:8080/jfinal/FlowOpenService/ChargeUserAccountRequest";
        JSONObject myJsonObject = new JSONObject();
        String apiId = "c4bd81929d";
        String apiKey = "0d5419b184c8495b92231e8ebc994324";


        String phoneNumber = "15201015005";
        String flowType = "countryFlowValue";
        String flowValue = "30";
        long currentTime = new Date().getTime();
        String currentTimestr = String.valueOf(currentTime);

        String strValidate = phoneNumber + "|" + flowType + "|" + flowValue + "|" + currentTimestr;
        LOGGER.info(strValidate);

        String validate = DES.encrypt(strValidate, apiKey);

        myJsonObject.accumulate("apiId", apiId);
        myJsonObject.accumulate("validate", validate);
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);

        String strcontent1 = gson.toJson(content);
        LOGGER.info(strcontent1);*/
        UserChargeController c = new UserChargeController();
    }

    public void testParse() throws ParserConfigurationException, IOException, SAXException {
        String flowValue = "30";
        String phoneNumber = "15201015003";
        Gson gson = new Gson();

        //读取到运营商接口配置文件xml

        String strUrl = "http://59.54.202.172:8082/flow/service/flow_if";
        String username = "shiliutech";
        String password = "shiliutech@123";
        String taskname = "shiliuliangbao";

        //年月日时分秒+6位随机数字 =20位
        Date now = new Date();
        String year = new SimpleDateFormat("yyyy").format(now);
        String month = new SimpleDateFormat("MM").format(now);
        String day = new SimpleDateFormat("dd").format(now);
        String hour = new SimpleDateFormat("HH").format(now);
        String minute = new SimpleDateFormat("mm").format(now);
        String second = new SimpleDateFormat("ss").format(now);

        Random random = new Random();
        String redomnum = Integer.toString(random.nextInt(899999) + 100000);
        String sid = (year + month + day + hour + minute + second + redomnum);
        LOGGER.info(sid);

        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("sid", sid);
        myJsonObject.accumulate("username", username);
        myJsonObject.accumulate("password", password);
        myJsonObject.accumulate("taskname", taskname);

        myJsonObject.accumulate("dataplan", flowValue);
        myJsonObject.accumulate("tels", phoneNumber);


        JSONObject content = HttpUtil.httpPost(strUrl, myJsonObject, false);
        LOGGER.info(content);
        String strContent = gson.toJson(content);

        LOGGER.info(strContent);


    }

    /**
     * 点击添加亲情号，返回到该Controller，判断该用户的亲情号设置是否已经超过了4个，
     * 如果超过了4个，则不允许再添加亲情号
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "testFamilyNumber")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void testFamilyNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        response.setContentType("application/json;charset=UTF-8");
        StringBuilder builder = new StringBuilder();
        builder.append("class:userChargeController_function:testFamilyNumber");
        String phoneNumber = request.getParameter("phoneNumber");
        List<FamilyNumberEntity> lisn = new ArrayList<FamilyNumberEntity>();
        lisn = familyNumberService.findByProperty(FamilyNumberEntity.class, "mobilePhone", phoneNumber);
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        if (lisn.size() >= 4) {
            map.put("status", 1);
            map.put("msg", "您添加的亲情号已经达到4个，不能再添加了");
            String json = objectMapper.writeValueAsString(map);
            out.write(json);
            return;
        }
        map.put("status", 0);
        map.put("msg", "您可以绑定亲情号");
        String json = objectMapper.writeValueAsString(map);
        double runtime = (System.currentTimeMillis() - startTime) / 1000.0;
        out.write(json);
        LOGGER.info("绑定亲情号,耗时" + runtime + "秒");
        LOGGER.info("debug_2016_0206runTime" + runtime + builder.toString());


        //    List<WeixinMemberEntity> lism = new ArrayList<>();

        //    lism = weixinMemberService.findByProperty(WeixinMemberEntity.class, "phoneNumber", phoneNumber);
        //      String hql = "from WeixinMemberEntity m where m.phoneNumber='" + phoneNumber + "'";

        //    lism = weixinMemberService.findHql(hql, null);
        //      LOGGER.info(phoneNumber);
        //   Gson gson=new Gson();
        //   Type type = new TypeToken<BusinessCommonJsonBean>() {
        //    }.getType();
      /*  ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "ddfdf");
        String json = objectMapper.writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        LOGGER.info("-----转json后的结果是------------");
        PrintWriter out = response.getWriter();
        out.write(json);
        LOGGER.info(json);*/


    }


    /**
     * 绑定确定按钮，绑定亲情号到数据库
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "bindFamilyNumber")
    public void bindFamilyNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("class:userChargeController_function:bindFamilyNumber");
        response.setContentType("application/json;charset=UTF-8");
        String familyNumber = request.getParameter("familyNumber");
        String phoneNumber = request.getParameter("phoneNumber");
//        FamilyNumberEntity hasFamilyNumber=familyNumberService.findUniqueByProperty(FamilyNumberEntity.class,"familyNumber",familyNumber);
        List<FamilyNumberEntity> familyNumberEntityList = this.systemService.findHql("from FamilyNumberEntity t where t.mobilePhone=" + phoneNumber);
        if (familyNumberEntityList.size() <= 4) {
            boolean contained = false;
            for (FamilyNumberEntity familyNumberEntity : familyNumberEntityList) {
                if (StringUtils.isNotBlank(familyNumberEntity.getFamilyNumber()) && familyNumberEntity.getFamilyNumber().equals(familyNumber)) {
                    contained = true;
                    break;
                }
            }
            // 如果用户添加的亲情号码已经存在，提示用户不能再添加
            if (contained) {
            Map<String, Object> map = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            map.put("msg", "该亲情号已存在");
            builder.append("该亲情号已存在");
            String json = objectMapper.writeValueAsString(map);
            PrintWriter out = response.getWriter();
            out.print(json);
            return;
            }
        } else { // > 4
            Map<String, Object> map = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            map.put("msg", "亲情号码最多只能添加四个");
            builder.append("添加失败");
            String json = objectMapper.writeValueAsString(map);
            PrintWriter out = response.getWriter();
            out.print(json);
            double runtime = (System.currentTimeMillis() - startTime) / 1000.0;
            LOGGER.info(MessageFormat.format("debug_2016-04-13 13:33:25——runTime{0}{1}", runtime, builder.toString()));
            return;
        }
        Gson gson = new Gson();
        Gson gsonF = new Gson();
        //根据手机号码查询手机号码归属地
        builder.append("根据手机号码查询手机号码归属地");
        Type phoneType = new TypeToken<PhoneLocationBean>() {
        }.getType();
        Type phoneTypeF = new TypeToken<PhoneLocationBean>() {
        }.getType();
        Type flowMealType = new TypeToken<FlowMealBean>() {
        }.getType();

        //调用后台获取手机号码归属地接口
        String phoneUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "phoneLocation/getPhonelocation";

        JSONObject myJsonObject = new JSONObject();
        JSONObject myJsonObjectF = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        myJsonObjectF.accumulate("phoneNumber", familyNumber);
        JSONObject phoneContent = HttpUtil.httpPost(phoneUrl, myJsonObject, false);
        JSONObject phoneContentF = HttpUtil.httpPost(phoneUrl, myJsonObjectF, false);

        String strPhoneContent = gson.toJson(phoneContent);
        String strPhoneContentF = gsonF.toJson(phoneContentF);
        LOGGER.info(strPhoneContent);

        PhoneLocationBean phoneJson = gson.fromJson(strPhoneContent, phoneType);
        PhoneLocationBean phoneJsonF = gsonF.fromJson(strPhoneContentF, phoneTypeF);

        String provinceName = phoneJson.getData().getProvinceName();
        String privinceNameF = phoneJsonF.getData().getProvinceName();

        //设置亲情号码的状态为，省份相同为0，不同为1
        builder.append("设置亲情号码的状态为，省份相同为0，不同为1");
        FamilyNumberEntity fne = new FamilyNumberEntity();
        fne.setStatus("2");
        if (provinceName.equals(privinceNameF)) {
            fne.setStatus("0");
        } else {
            fne.setStatus("1");
        }
//            WeixinMemberEntity wme = weixinMemberService.findUniqueByProperty(WeixinMemberEntity.class, "phoneNumber", phoneNumber);
//            String nickname = wme.getNickName();
        fne.setAddTime(new Date());
        fne.setFamilyNumber(familyNumber);
        fne.setMobilePhone(phoneNumber);
//            fne.setNickname(nickname);
        familyNumberService.save(fne);
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        map.put("msg", "添加成功");
        builder.append("添加成功");
        String json = objectMapper.writeValueAsString(map);
        PrintWriter out = response.getWriter();
        out.print(json);
        double runtime = (System.currentTimeMillis() - startTime) / 1000.0;
        LOGGER.info("debug_2016_0206runTime" + runtime + builder.toString());
    }

    /**
     * 加载亲情号列表到option
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "loadFamilyNumber")
//    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void loadFamilyNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("class:userChargeController_function:loadFamilyNumber");
        response.setContentType("application/json;charset=UTF-8");
        String phoneNumber = request.getParameter("phoneNumber");
        List<FamilyNumberEntity> list = familyNumberService.findByProperty(FamilyNumberEntity.class, "mobilePhone", phoneNumber);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);
        PrintWriter out = response.getWriter();
        builder.append(json);
        //   Gson gson = new Gson();
        //     String json = gson.toJson(list);
        out.write(json);
        double runtime = (System.currentTimeMillis() - startTime) / 1000.0;
        LOGGER.info("debug_2016_0206runTime" + runtime + builder.toString());
    }

    @RequestMapping(params = "laodFlowMeal")
    public void laodFlowMeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("class:userChargeController_function:laodFlowMeal");
        String phoneNumber = request.getParameter("phoneNumber").toString();
        Gson gson = new Gson();
        //根据手机号码查询手机号码归属地
        builder.append("根据手机号码查询手机号码归属地");
        Type phoneType = new TypeToken<PhoneLocationBean>() {
        }.getType();
        Type flowMealType = new TypeToken<FlowMealBean>() {
        }.getType();

        //调用后台获取手机号码归属地接口
        String phoneUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "phoneLocation/getPhonelocation";

        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        JSONObject phoneContent = HttpUtil.httpPost(phoneUrl, myJsonObject, false);

        String strPhoneContent = gson.toJson(phoneContent);
        //LOGGER.info(strPhoneContent);

        PhoneLocationBean phoneJson = gson.fromJson(strPhoneContent, phoneType);


        String provinceName = phoneJson.getData().getProvinceName();
        String businessName = phoneJson.getData().getBusinessName();
        String strBusinessCode = phoneJson.getData().getBusinessCode();
        String strProvinceCode = phoneJson.getData().getProvinceCode();
        builder.append("businesscode:" + provinceName + businessName);
        String flowMealUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "flowMeal/QueryFlowMealByBusiness";
        JSONObject myJsonObject1 = new JSONObject();
        myJsonObject1.accumulate("businessCode", strBusinessCode);
        myJsonObject1.accumulate("provinceCode", strProvinceCode);
        JSONObject flowMealContent = HttpUtil.httpPost(flowMealUrl, myJsonObject1, false);

        String strFlowMealContent = gson.toJson(flowMealContent);
        //LOGGER.info(strFlowMealContent);

        FlowMealBean flowMealJson = gson.fromJson(strFlowMealContent, flowMealType);
        List<FlowMealBean.DataEntity> flowMealJsonBean = new ArrayList<FlowMealBean.DataEntity>();
        flowMealJsonBean = flowMealJson.getData();
//        mav.addObject("flowMealJsonBean", flowMealJsonBean);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(flowMealJsonBean);
        PrintWriter out = response.getWriter();
        out.write(json);
        double runtime = (System.currentTimeMillis() - startTime) / 1000.0;
        LOGGER.info("debug_2016_0206_runTime" + runtime + builder.toString());
    }
}