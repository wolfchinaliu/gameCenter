package weixin.liuliangbao.business.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.liuliangbao.jsonbean.ViewBean.*;
import weixin.liuliangbao.util.DES;
import weixin.liuliangbao.util.DESUtil;
import weixin.liuliangbao.util.DesSecurity;
import weixin.liuliangbao.util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by aa on 2016/1/23.
 */
@Controller
@RequestMapping("/rechargeAsHandler")
public class ExternalRechargeAsController extends BaseController {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(UserChargeController.class);


    /*第一种方法-临时在用的接口--周六开发*/
    @RequestMapping(params = "rechargeExternal")
    public void Recharge(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("rechargeExternal");

        Gson gson = new Gson();
        Type type = new TypeToken<RechargeJsonBean>() {
        }.getType();

        Post post = new Post();
        String str = post.getJson(request);


        //String str = "";
        /*加密*/
        //String strstrstr = DES.encrypt(str, "12345678901g45gfsgrwsd64643");

        /*解密*/
        String strstr = DES.decrypt(str, "12345678901g45gfsgrwsd64643");

        RechargeExternalBean recharge = StringToObject.getObject(strstr, RechargeExternalBean.class);
        String phoneNumber = recharge.getPhoneNumber();
        String flowValue = recharge.getFlowValue();
        String flowType = "1";
        String accountId = recharge.getAccountId();


        /*//TODO:给对方一个测试页面，临时测一下
        String phoneNumber=request.getParameter("phoneNumber");
        String flowValue=request.getParameter("flowValue");
        String accountId=request.getParameter("accountId");
        String flowType="全国流量";*/

        //调用jFianl查询手机号码归属地的接口
        Type phoneType = new TypeToken<PhoneLocationBean>() {
        }.getType();
        String phoneUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "phoneLocation/getPhonelocation";
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        JSONObject phoneContent = HttpUtil.httpPost(phoneUrl, myJsonObject, false);
        String strPhoneContent = gson.toJson(phoneContent);
        PhoneLocationBean phoneJson = gson.fromJson(strPhoneContent, phoneType);

        String businessCode = phoneJson.getData().getBusinessCode();
        String provinceCode = phoneJson.getData().getProvinceCode();


        //调用jfinal充值接口
        String chargeUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "asRechargeExternal/ChargeExternalFlow";
        JSONObject chargeJsonObject = new JSONObject();
        chargeJsonObject.accumulate("phoneNumber", phoneNumber);
        chargeJsonObject.accumulate("businessCode", businessCode);
        chargeJsonObject.accumulate("provinceCode", provinceCode);
        chargeJsonObject.accumulate("flowValue", flowValue);
        chargeJsonObject.accumulate("flowType", flowType);
        chargeJsonObject.accumulate("accountid", accountId);

        JSONObject chargeContent = HttpUtil.httpPost(chargeUrl, chargeJsonObject, false);
        // String chargeMessage = chargeContent.get("msg").toString();
        //LOGGER.info(chargeMessage);

        //LOGGER.info("phoneNumber:" + phoneNumber + "flowValue:" + flowValue + "flowType" + flowType + "msg:" + chargeContent.get("msg").toString());
        logger.info("phoneNumber:" + phoneNumber + "flowValue:" + flowValue + "flowType" + flowType);

        String strChargeContent = gson.toJson(chargeContent);
        //LOGGER.info(strChargeContent);
        /*RechargeJsonBean userChargedJson = gson.fromJson(strChargeContent, type);*/


        response.getOutputStream().write(strChargeContent.getBytes("utf-8"));


    }

    @RequestMapping(params = "goTest")
    public ModelAndView goTest(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("liuliangbao/test");
    }


    /*方案二的方法-带待发库*/
    @RequestMapping(params = "chargeRestful")
    public void RechargeSecond(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("_ExternalRechargeController:chargeReful");


        RechargeJsonBean jsonBean = new RechargeJsonBean();
        String returnmsg = "";
        int code = 0;
        Gson gson = new Gson();
        Post post = new Post();
        Type type = new TypeToken<RechargeJsonBean>() {
        }.getType();

        try {
            String str = post.getJson(request);


            String key = ResourceUtil.getConfigByName("shiliuflowkeybankhrcrept");

            DesSecurity du = new DesSecurity();
            String strstr = du.decrypt(key, str);//解密出来的字符串


            //获取json串中的参数
            RechargeExternalBean recharge = StringToObject.getObject(strstr, RechargeExternalBean.class);
            String phoneNumber = recharge.getPhoneNumber();
            String flowValue = recharge.getFlowValue();
            String flowType = "2";
            /*String accountId = recharge.getAccountId();
            String customerProductNo=recharge.getFlowkeyno();*/
            String accountId = recharge.getFlowKeyNumber();

//            String accountId = ResourceUtil.getCuConfigByName(recharge.getFlowKeyNumber());

            String customerProductNo = recharge.getTimeStamp();

            builder.append("_phoneNumber:" + phoneNumber + "_flowValue:" + flowValue);

            //首先做手机号码的验证，判断是否为11位
            int lenphone = phoneNumber.length();
            if (lenphone != 11) {
                code = -111;
                returnmsg = "手机号码不符合规范";
            } else { //如果是11位可以继续

                //调用jFianl查询手机号码归属地的接口。得到手机号码的省code和运营商code   例如：江西14 移动1
                Type phoneType = new TypeToken<PhoneLocationBean>() {
                }.getType();
                String phoneUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "phoneLocation/getPhonelocation";
                JSONObject myJsonObject = new JSONObject();
                myJsonObject.accumulate("phoneNumber", phoneNumber);
                JSONObject phoneContent = HttpUtil.httpPost(phoneUrl, myJsonObject, false);

                if (phoneContent == null) {
                    code = -112;
                    returnmsg = "无效的手机号码";
                } else {
                    String strPhoneContent = gson.toJson(phoneContent);
                    PhoneLocationBean phoneJson = gson.fromJson(strPhoneContent, phoneType);

                    String businessCode = phoneJson.getData().getBusinessCode();
                    String provinceCode = phoneJson.getData().getProvinceCode();

                    //调用jfinal充值接口
                    String chargeUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "asRechargeExternalFinal/ChargeExternalFlow";
                    JSONObject chargeJsonObject = new JSONObject();
                    chargeJsonObject.accumulate("phoneNumber", phoneNumber);
                    chargeJsonObject.accumulate("businessCode", businessCode);
                    chargeJsonObject.accumulate("provinceCode", provinceCode);
                    chargeJsonObject.accumulate("flowValue", flowValue);
                    chargeJsonObject.accumulate("flowType", flowType);
                    chargeJsonObject.accumulate("accountid", accountId);
                    chargeJsonObject.accumulate("flowkeyno", customerProductNo);

                    JSONObject chargeContent = HttpUtil.httpPost(chargeUrl, chargeJsonObject, false);
                    String strChargeContent = gson.toJson(chargeContent);
                    RechargeJsonBean userChargedJson = gson.fromJson(strChargeContent, type);
                    code = userChargedJson.getCode();
                    returnmsg = userChargedJson.getMsg();
                    builder.append("_直充状态：" + code + returnmsg);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            code = -200;
            returnmsg = "充值失败，内部原因";
            builder.append("_直充状态：" + code + returnmsg);

        } finally {
            jsonBean.setCode(code);
            jsonBean.setMsg(returnmsg);
            String strJsonBean = gson.toJson(jsonBean);
            response.getOutputStream().write(strJsonBean.getBytes("utf-8"));

            long runTime = System.currentTimeMillis() - startTime;
            logger.info("_debug_0301_runTime" + runTime + builder.toString());
        }
    }


    /*提供的查询接口*/
    @RequestMapping(params = "queryChargeState")
    public void QueryChargeState(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("_ExternalRechargeController:QueryChargeState");

        RechargeJsonBean jsonBean = new RechargeJsonBean();
        String returnmsg = "";
        int code = 0;
        Gson gson = new Gson();
        Post post = new Post();
        Type type = new TypeToken<RechargeJsonBean>() {
        }.getType();

        try {
            String str = post.getJson(request);

            RechargeExternalBean chargeState = StringToObject.getObject(str, RechargeExternalBean.class);
//            String accountId = chargeState.getFlowKeyNumber();
            String accountId = ResourceUtil.getCuConfigByName(chargeState.getFlowKeyNumber());
            String flowkeyno = chargeState.getTimeStamp();

            builder.append("_accountId:" + accountId + "_flowkeyno:" + flowkeyno);

            //调用jfinal查询直充充值状态接口
            String stateUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "asRechargeExternalFinal/QueryChargeState";
            JSONObject stateJsonObject = new JSONObject();
            stateJsonObject.accumulate("accountid", accountId);
            /*stateJsonObject.accumulate("password", password);*/
            stateJsonObject.accumulate("flowkeyno", flowkeyno);

            JSONObject chargeContent = HttpUtil.httpPost(stateUrl, stateJsonObject, false);
            String strChargeContent = gson.toJson(chargeContent);
            RechargeJsonBean userChargedJson = gson.fromJson(strChargeContent, type);
            code = userChargedJson.getCode();
            returnmsg = userChargedJson.getMsg();
            builder.append("_查询直充状态：" + code + returnmsg);

        } catch (Exception e) {
            e.printStackTrace();
            code = -2001;
            returnmsg = "系统异常";
            builder.append("_查询直充状态：" + code + returnmsg);
        } finally {
            jsonBean.setCode(code);
            jsonBean.setMsg(returnmsg);
            String strJsonBean = gson.toJson(jsonBean);
            response.getOutputStream().write(strJsonBean.getBytes("utf-8"));

            long runTime = System.currentTimeMillis() - startTime;
            logger.info("_debug_0301_runTime" + runTime + builder.toString());
        }


    }


    /*方案二的方法-带待发库*/
    @RequestMapping(params = "chargeReful")
    public void RechargeSecondSe(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("debug_0313_chargeReful_begin");
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("_ExternalRechargeController:chargeReful");


        RechargeJsonBean jsonBean = new RechargeJsonBean();
        String returnmsg = "";
        int code = 0;
        Gson gson = new Gson();
        Post post = new Post();
        Type type = new TypeToken<RechargeJsonBean>() {
        }.getType();

        try {
            String str = post.getJson(request);
            if (str == "") {
                code = -116;
                returnmsg = "请求参数不正确";
            } else {
                RechargeExternalBean recharge = StringToObject.getObject(str, RechargeExternalBean.class);
                if (recharge == null) {
                    code = -116;
                    returnmsg = "请求参数不正确";
                } else {
                    //获取json串中的参数
                    String phoneNumber = recharge.getPhoneNumber();
                    String flowValue = recharge.getFlowValue();
                    String flowType = "2";
                    String accountId = recharge.getFlowKeyNumber();

                    String customerProductNo = recharge.getTimeStamp();
                    String sign = recharge.getSign();
                    builder.append("_phoneNumber:" + phoneNumber + "_flowValue:" + flowValue + "_TimeStamp" + customerProductNo);


                    String key = ResourceUtil.getConfigByName(accountId);
                    //String key = "r5dh6jk4";
                    //LOGGER.info(key);

                    if (key == "" || key == null) {
                        code = -115;
                        returnmsg = "商户唯一标识不正确";
                    } else {
                        //String strstr = DESUtil.decrypt(key, sign);//解密出来的字符串
                        String strstr = java.net.URLDecoder.decode(DESUtil.decrypt(sign, key), "utf-8");
                        //判断排序方式
                        String validate = strstr.substring(0, 13);
                        String timeStamp = strstr.substring(strstr.length() - 20, strstr.length());

                        String strBefore = timeStamp.substring(0, 14);
//                        String strCenter = timeStamp.substring(10, 14);
//                        Date now = new Date();
//                        String year = new SimpleDateFormat("yyyy").format(now);
//                        String month = new SimpleDateFormat("MM").format(now);
//                        String day = new SimpleDateFormat("dd").format(now);
//                        String hour = new SimpleDateFormat("HH").format(now);
//                        String minute = new SimpleDateFormat("mm").format(now);
//                        String second = new SimpleDateFormat("ss").format(now);
//                        String validateBefore = year + month + day + hour;
//                        String validateCenter = minute + second;
//                        int intValidateCenter = Integer.parseInt(validateCenter) - Integer.parseInt(strCenter);

                        //首先做手机号码的验证，判断是否为11位
                        int lenphone = phoneNumber.length();
                        if (!validate.equalsIgnoreCase("flowKeyNumber")) {
                            code = -113;
                            returnmsg = "签名验证不通过";
                        } else if (!IsSuccess(strBefore)) {
                            code = -114;
                            returnmsg = "请求时间超时";
                        } else if (lenphone != 11) {
                            code = -111;
                            returnmsg = "手机号码不符合规范";
                        } else { //如果是11位可以继续

                            //调用jFianl查询手机号码归属地的接口。得到手机号码的省code和运营商code   例如：江西14 移动1
                            Type phoneType = new TypeToken<PhoneLocationBean>() {
                            }.getType();
                            String phoneUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "phoneLocation/getPhonelocation";
                            JSONObject myJsonObject = new JSONObject();
                            myJsonObject.accumulate("phoneNumber", phoneNumber);
                            logger.info("debug_0313_diaoJfinal_begin");
                            JSONObject phoneContent = HttpUtil.httpPost(phoneUrl, myJsonObject, false);
                            logger.info("debug_0313_diaoJfinal_phoneLocation/getPhonelocation_end");
                            if (phoneContent == null) {
                                code = -112;
                                returnmsg = "无效的手机号码";
                            } else {
                                String strPhoneContent = gson.toJson(phoneContent);
                                PhoneLocationBean phoneJson = gson.fromJson(strPhoneContent, phoneType);

                                String businessCode = phoneJson.getData().getBusinessCode();
                                String provinceCode = phoneJson.getData().getProvinceCode();

                                logger.info("debug_0313_diaoJfinal_asRechargeExternalFinal/ChargeExternalFlow_begin");
                                //调用jfinal充值接口
                                String chargeUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "asRechargeExternalFinal/ChargeExternalFlow";

                                accountId = ResourceUtil.getCuConfigByName(recharge.getFlowKeyNumber());

                                JSONObject chargeJsonObject = new JSONObject();
                                chargeJsonObject.accumulate("phoneNumber", phoneNumber);
                                chargeJsonObject.accumulate("businessCode", businessCode);
                                chargeJsonObject.accumulate("provinceCode", provinceCode);
                                chargeJsonObject.accumulate("flowValue", flowValue);
                                chargeJsonObject.accumulate("flowType", flowType);
                                chargeJsonObject.accumulate("accountid", accountId);
                                chargeJsonObject.accumulate("flowkeyno", customerProductNo);

                                JSONObject chargeContent = HttpUtil.httpPost(chargeUrl, chargeJsonObject, false);
                                logger.info("debug_0313_diaoJfinal_asRechargeExternalFinal/ChargeExternalFlow_end");
                                String strChargeContent = gson.toJson(chargeContent);
                                RechargeJsonBean userChargedJson = gson.fromJson(strChargeContent, type);
                                code = userChargedJson.getCode();
                                returnmsg = userChargedJson.getMsg();
                                builder.append("_直充状态：" + code + returnmsg);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            code = -200;
            returnmsg = "内部原因或者参数不正确";
            builder.append("_直充状态：" + code + returnmsg);

        } finally {
            jsonBean.setCode(code);
            jsonBean.setMsg(returnmsg);
            String strJsonBean = gson.toJson(jsonBean);
            response.getOutputStream().write(strJsonBean.getBytes("utf-8"));

            long runTime = System.currentTimeMillis() - startTime;
            logger.info("_debug_0301_runTime直充接口" + runTime + builder.toString());
        }
    }


    @RequestMapping(params = "chargeJiangXiTest")
    public void chargeJiangXiTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("_ExternalRechargeController:chargeJiangXiTest");


        RechargeJsonBean jsonBean = new RechargeJsonBean();
        String returnmsg = "";
        int code = 0;
        Gson gson = new Gson();
        Post post = new Post();
        Type type = new TypeToken<RechargeJsonBean>() {
        }.getType();

        String str = post.getJson(request);

        //获取json串中的参数
        RechargeExternalBean recharge = StringToObject.getObject(str, RechargeExternalBean.class);
        String phoneNumber = recharge.getPhoneNumber();
        String flowValue = recharge.getFlowValue();

        builder.append("_phoneNumber:" + phoneNumber + "_flowValue:" + flowValue);


        //调用jfinal充值接口
        String chargeUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "rechargeExternal/chargeJiangXiTest";
        JSONObject chargeJsonObject = new JSONObject();
        chargeJsonObject.accumulate("phoneNumber", phoneNumber);

        chargeJsonObject.accumulate("flowValue", flowValue);


        JSONObject chargeContent = HttpUtil.httpPost(chargeUrl, chargeJsonObject, false);
        if (chargeContent == null) {
            code = 0;
            returnmsg = "请求为空";
        } else {
            String strChargeContent = gson.toJson(chargeContent);
            RechargeJsonBean userChargedJson = gson.fromJson(strChargeContent, type);
            code = userChargedJson.getCode();
            returnmsg = userChargedJson.getMsg();
        }
        builder.append("_直充状态：" + code + returnmsg);

        jsonBean.setCode(code);
        jsonBean.setMsg(returnmsg);
        String strJsonBean = gson.toJson(jsonBean);
        response.getOutputStream().write(strJsonBean.getBytes("utf-8"));
        logger.info("_debug_0306_jiangxitest" + builder.toString());
    }

    @RequestMapping(params = "chargeJiangXiTestSec")
    public void chargeJiangXiTestSec(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("_ExternalRechargeController:chargeJiangXiTestSec");


        RechargeJsonBean jsonBean = new RechargeJsonBean();
        String returnmsg = "";
        int code = 0;
        Gson gson = new Gson();
        Post post = new Post();
        Type type = new TypeToken<RechargeJsonBean>() {
        }.getType();

        String str = post.getJson(request);

        //获取json串中的参数
        RechargeExternalBean recharge = StringToObject.getObject(str, RechargeExternalBean.class);
        String phoneNumber = recharge.getPhoneNumber();
        String flowValue = recharge.getFlowValue();

        builder.append("_phoneNumber:" + phoneNumber + "_flowValue:" + flowValue);


        //调用jfinal充值接口
        String chargeUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "rechargeExternal/chargeJiangXiTestSec";
        JSONObject chargeJsonObject = new JSONObject();
        chargeJsonObject.accumulate("phoneNumber", phoneNumber);

        chargeJsonObject.accumulate("flowValue", flowValue);


        JSONObject chargeContent = HttpUtil.httpPost(chargeUrl, chargeJsonObject, false);
        if (chargeContent == null) {
            code = 0;
            returnmsg = "请求为空";
        } else {
            String strChargeContent = gson.toJson(chargeContent);
            RechargeJsonBean userChargedJson = gson.fromJson(strChargeContent, type);
            code = userChargedJson.getCode();
            returnmsg = userChargedJson.getMsg();
        }
        builder.append("_直充状态：" + code + returnmsg);

        jsonBean.setCode(code);
        jsonBean.setMsg(returnmsg);
        String strJsonBean = gson.toJson(jsonBean);
        response.getOutputStream().write(strJsonBean.getBytes("utf-8"));
        logger.info("_debug_0306_jiangxitest" + builder.toString());
    }


    @RequestMapping(params = "queryChargeJiangXiTest")
    public void queryChargeJiangXiTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("_ExternalRechargeController:queryChargeJiangXiTest");


        RechargeJsonBean jsonBean = new RechargeJsonBean();
        String returnmsg = "";
        int code = 0;
        Gson gson = new Gson();
        Post post = new Post();
        Type type = new TypeToken<RechargeJsonBean>() {
        }.getType();

        String str = post.getJson(request);

        //获取json串中的参数
        RechargeExternalBean recharge = StringToObject.getObject(str, RechargeExternalBean.class);
        String phoneNumber = recharge.getPhoneNumber();

        builder.append("_phoneNumber:" + phoneNumber);

        //调用jfinal充值接口
        String chargeUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "rechargeExternal/queryChargeJiangXiStatus";
        JSONObject chargeJsonObject = new JSONObject();
        chargeJsonObject.accumulate("phoneNumber", phoneNumber);

        JSONObject chargeContent = HttpUtil.httpPost(chargeUrl, chargeJsonObject, false);

        if (chargeContent == null) {
            code = 0;
            returnmsg = "请求为空";
        } else {

            String strChargeContent = gson.toJson(chargeContent);
            RechargeJsonBean userChargedJson = gson.fromJson(strChargeContent, type);
            code = userChargedJson.getCode();
            returnmsg = userChargedJson.getMsg();
        }
        builder.append("_直充状态：" + code + returnmsg);
        jsonBean.setCode(code);
        jsonBean.setMsg(returnmsg);
        String strJsonBean = gson.toJson(jsonBean);
        response.getOutputStream().write(strJsonBean.getBytes("utf-8"));
        logger.info("_debug_0306_jiangxitest" + builder.toString());
    }

    @RequestMapping(params = "queryChargeJiangXiTestSec")
    public void queryChargeJiangXiTestSec(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("_ExternalRechargeController:queryChargeJiangXiTest");


        RechargeJsonBean jsonBean = new RechargeJsonBean();
        String returnmsg = "";
        int code = 0;
        Gson gson = new Gson();
        Post post = new Post();
        Type type = new TypeToken<RechargeJsonBean>() {
        }.getType();

        String str = post.getJson(request);

        //获取json串中的参数
        RechargeExternalBean recharge = StringToObject.getObject(str, RechargeExternalBean.class);
        String phoneNumber = recharge.getPhoneNumber();
        String flowkeyno=recharge.getFlowkeyno();

        builder.append("_phoneNumber:" + phoneNumber);

        //调用jfinal充值接口
        String chargeUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "rechargeExternal/queryChargeJiangXiStatusSec";
        JSONObject chargeJsonObject = new JSONObject();
        chargeJsonObject.accumulate("phoneNumber", phoneNumber);
        chargeJsonObject.accumulate("flowkeyno",flowkeyno);

        JSONObject chargeContent = HttpUtil.httpPost(chargeUrl, chargeJsonObject, false);

        if (chargeContent == null) {
            code = 0;
            returnmsg = "请求为空";
        } else {

            String strChargeContent = gson.toJson(chargeContent);
            RechargeJsonBean userChargedJson = gson.fromJson(strChargeContent, type);
            code = userChargedJson.getCode();
            returnmsg = userChargedJson.getMsg();
        }
        builder.append("_直充状态：" + code + returnmsg);
        jsonBean.setCode(code);
        jsonBean.setMsg(returnmsg);
        String strJsonBean = gson.toJson(jsonBean);
        response.getOutputStream().write(strJsonBean.getBytes("utf-8"));
        logger.info("_debug_0306_jiangxitest" + builder.toString());
    }



    private boolean IsSuccess(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dt= null;
        try {
            dt = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = new Date();

        if(dt.getTime()<(now.getTime()+1000*60*5) && dt.getTime()>(now.getTime()-1000*60*5)){
           return  true;//没有超时
        }else{
           return false;//超时
        }
    }

}


