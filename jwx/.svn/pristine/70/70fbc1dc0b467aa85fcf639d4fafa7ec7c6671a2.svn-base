package weixin.liuliangbao.controller.flowcontroller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.liuliangbao.jsonbean.MerchantBean;
import weixin.liuliangbao.jsonbean.MerchantFlowGiveRuleBean;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.HttpUtil;
import weixin.util.WeiXinConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aa on 2015/11/25.
 */
@Scope("prototype")
@Controller
@RequestMapping("/merchantFlowController")
public class MerchantFlowController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(MerchantFlowController.class);
    private String path = ResourceUtil.getConfigByName("jfinalUrl-config");
   private WeiXinConstants wc = new WeiXinConstants();
    //查询商户的剩余流量
    @RequestMapping(params = "merchantFlow")
    public ModelAndView merchantFlow(HttpServletRequest request) {
        Gson gson = new Gson();
//        String url = "http://localhost:8080/jwx/flowCoin/QueryMerchantAccountInfo";
        String url = path + "flowCoin/QueryMerchantAccountInfo";
        //String content= HttpUtil.httpGet(url).toString();
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("id", "2");
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);

        String reStr = gson.toJson(content);
//        String content = httpUtil.h(url, HttpUtil.HTTP_TYPE.GET,null);
        //LOGGER.info(content);

        Type type = new TypeToken<MerchantInfoBean>() {
        }.getType();
        MerchantInfoBean merchantInfoBean = gson.fromJson(reStr, type);
        LOGGER.info(gson.toJson(content));

        // LOGGER.info("------------------------"+content.getData().get(0).getId()+"----------------------------");
        request.setAttribute("merchantInfoBean", merchantInfoBean);
        return new ModelAndView("liuliangbao/flowmanager/binding");
    }

    //查询本商户级别下的商户信息
    @RequestMapping(params = "merchantCharge")
    public ModelAndView merchantCharge(HttpServletRequest request) {
        Gson gson = new Gson();
//        String url = "http://localhost:8080/jwx/flowCoin/queryMerchant";
        String url = path + "flowCoin/queryMerchant";
        //String content= HttpUtil.httpGet(url).toString();
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("id", "2");
        myJsonObject.accumulate("level", "A级");
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);

//        String content = httpUtil.h(url, HttpUtil.HTTP_TYPE.GET,null);
        LOGGER.info(content);


        Type type = new TypeToken<MerchantBean>() {
        }.getType();
        //MerchantBean testJson = gson.fromJson(content,type);
        LOGGER.info(gson.toJson(content));
        return null;
    }

    @RequestMapping(params = "merchantFlowRule")
    public ModelAndView merchantFlowRule(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        String accountid = ResourceUtil.getWeiXinAccountId();
        buffer.append("merchantFlowController_merchangFlowRule_begin_");
        buffer.append("accountid_" + accountid);
        Gson gson = new Gson();
        long startFLowRule = System.currentTimeMillis();
        String url = path + "flowCoin/QueryFlowRule";
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("id", accountid);
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
        String reStr = gson.toJson(content);
        Type type = new TypeToken<MerchantFlowGiveRuleBean>() {
        }.getType();
        MerchantFlowGiveRuleBean rule = gson.fromJson(reStr, type);
        long endFLowRule = System.currentTimeMillis();
        buffer.append("_queryFlowRule_time:_" + (endFLowRule - startFLowRule) + "ms");
        if (rule != null) {
            switch (rule.getCode()) {
                case "200":
                    request.setAttribute("accountid", accountid);

                    request.setAttribute("subsribeFlow", rule.getSubscribeFlow());//关注送流量值
                    request.setAttribute("subscribeFlowType", rule.getSubscribeFlowType());//关注的流量类型
                    request.setAttribute("subsribeCount", rule.getSubscribeCount());//关注的最大人数

                    request.setAttribute("signFlow", rule.getSignFlow());//签到送流量值
                    request.setAttribute("signFlowType", rule.getSignFlowType());//签到的流量类型
                    request.setAttribute("signCount", rule.getSignCount()); //签到的最大人数

                    request.setAttribute("shareFlow", rule.getShareFlow()); //分享送流量值
                    request.setAttribute("shareFlowType", rule.getShareFlowType());//分享的流量类型
                    request.setAttribute("shareCount", rule.getShareCount());//分享的最大人数

                    request.setAttribute("shareBindFlow", rule.getShareBindFlow());//分享绑定送流量值
                    request.setAttribute("shareBindFlowType", rule.getShareBindFlowType());//分享绑定的流量类型
                    request.setAttribute("shareBindCount", rule.getShareBindCount());//分享绑定的最大人数

                    return new ModelAndView("liuliangbao/flowmanager/merchantFlowRule");
                case "20015":
                    request.setAttribute("accountid", accountid);
                    request.setAttribute("subsribeFlow", null);//关注送流量值
                    request.setAttribute("subscribeFlowType", null);//关注的流量类型
                    request.setAttribute("subsribeCount", null);//关注的最大人数

                    request.setAttribute("signFlow", null);//签到送流量值
                    request.setAttribute("signFlowType", null);//签到的流量类型
                    request.setAttribute("signCount", null);//签到的最大人数

                    request.setAttribute("shareFlow", null);//分享送流量值
                    request.setAttribute("shareFlowType", null);//分享的流量类型
                    request.setAttribute("shareCount", null);//分享的最大人数

                    request.setAttribute("shareBindFlow", null);//分享绑定送流量值
                    request.setAttribute("shareBindFlowType", null);//分享绑定的流量类型
                    request.setAttribute("shareBindCount", null);//分享绑定的最大人数
                    return new ModelAndView("liuliangbao/flowmanager/merchantFlowRule");
                default:
                    long endTime = System.currentTimeMillis();
                    buffer.append("_merchantFlowController_merchangFlowRule_end_Time:" + (endTime - startTime) + "ms");
                    LOGGER.info(buffer.toString());
                    return new ModelAndView("liuliangbao/flowmanager/error");
            }
        } else {
            long endTime = System.currentTimeMillis();
            buffer.append("_merchantFlowController_merchangFlowRule_end_Time:" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            return new ModelAndView("liuliangbao/flowmanager/error");
        }

    }


    @RequestMapping(params = "saveMerchantFlowRule")
    public void saveMerchantFlowRule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();

        TSUser tsUser = ResourceUtil.getSessionUserName();

        String username = tsUser.getUserName();
        String json = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<>();


            String accountid = request.getParameter("accountid");
            String subscribe = request.getParameter("subscribe");
            String subscribeFlowType = request.getParameter("subscribeFlowType");
            if(WeiXinConstants.ProvinceFlow.equals(subscribeFlowType)){
            	subscribeFlowType = WeiXinConstants.ProvinceFlow_code;
            } else if (WeiXinConstants.CountryFlow.equals(subscribeFlowType)) {
            	subscribeFlowType = WeiXinConstants.CountryFlow_code;
			} 
            String subCount = request.getParameter("subCount");

            String sign = request.getParameter("sign");
            String signCount = request.getParameter("signCount");
            String signFlowType = request.getParameter("signFlowType");
            if(WeiXinConstants.ProvinceFlow.equals(signFlowType)){
            	signFlowType = WeiXinConstants.ProvinceFlow_code;
            }else if (WeiXinConstants.CountryFlow.equals(signFlowType)) {
            	signFlowType = WeiXinConstants.CountryFlow_code;
            }
            String share = request.getParameter("share");
            String shareCount = request.getParameter("shareCount");
            String shareFlowType = request.getParameter("shareFlowType");
            if(WeiXinConstants.ProvinceFlow.equals(shareFlowType)){
            	shareFlowType = WeiXinConstants.ProvinceFlow_code;
            } else if (WeiXinConstants.CountryFlow.equals(shareFlowType)){
            	shareFlowType = WeiXinConstants.CountryFlow_code;
            }
            String shareBind = request.getParameter("shareBind");
            String shareBindCount = request.getParameter("shareBindCount");
            String shareBindFlowType = request.getParameter("shareBindFlowType");
            if(WeiXinConstants.ProvinceFlow.equals(shareBindFlowType)){
            	shareBindFlowType = WeiXinConstants.ProvinceFlow_code;
            } else if (WeiXinConstants.CountryFlow.equals(shareBindFlowType)){
            	shareBindFlowType = WeiXinConstants.CountryFlow_code;
            }

            buffer.append("_accountid_" + accountid);
            Gson gson = new Gson();
            String url = path + "flowCoin/SaveFlowRule";
            JSONObject myJsonObject = new JSONObject();
            myJsonObject.accumulate("id", accountid);
            myJsonObject.accumulate("nickname", subscribe);//关注送的流量值
            myJsonObject.accumulate("phoneNumber", sign);//签到送的流量值
            myJsonObject.accumulate("subCount", subCount);//关注最大人数
            myJsonObject.accumulate("signCount", signCount);//签到最大人数
            myJsonObject.accumulate("share", share);//分享送流量
            myJsonObject.accumulate("shareCount", shareCount);//分享的最大人数
            myJsonObject.accumulate("subscribeFlowType", subscribeFlowType);//关注送流量的类型
            myJsonObject.accumulate("signFlowType", signFlowType);//签到送流量的类型
            myJsonObject.accumulate("shareFlowType", shareFlowType);//分享送流量的类型

            myJsonObject.accumulate("shareBind", shareBind);//分享绑定送流量值
            myJsonObject.accumulate("shareBindCount", shareBindCount);//分享绑定的最大人数
            myJsonObject.accumulate("shareBindFlowType", shareBindFlowType);//分享绑定送流量的类型

            myJsonObject.accumulate("curOperator", username);//当前的操作人--xiaona
            JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
            String reStr = gson.toJson(content);
            Type type = new TypeToken<Update>() {
            }.getType();
            //TODO:写一个bean ,接收值
            Update update = gson.fromJson(reStr, type);
            if (null != update && update.getCode().equals("200")) {
                map.put("flag", true);
                map.put("msg", "保存成功！");
            } else {
                map.put("flag", false);
                map.put("msg", "保存失败，请重试！");
            }
            json = objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            buffer.append("_merchantFlowController_saveMerchantFlowRule_end_Time:" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());

            PrintWriter out = response.getWriter();
            out.write(json);
        }


    }

}
