package weixin.liuliangbao.weigatedoor.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.jsonbean.UserFlowAccountBean;
import weixin.liuliangbao.util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.HashMap;

/**
 * Created by aa on 2015/12/11.
 */

@Controller
@RequestMapping("/personFlowCenterController")
public class PersonFlowCenterController extends BaseController implements PageAuthHandler {
    private static final Logger logger = Logger.getLogger(WeiDoorController.class);

    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @RequestMapping(params = "goPersonCenter")
    public ModelAndView goPersonCenter(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("PersonFlowCenterController_goPersonCenter");

        String accountid = request.getParameter("accountid");
        String openId = request.getParameter("openId");
        builder.append("accountid" + accountid + "_openId" + openId);

        String url = "";
        if (StringUtils.isBlank(openId)) {
            url = weixinAccountService.pageAuth(accountid, new HashMap<>(), this);
        } else {
            url = weixinAccountService.pageAuth(accountid, new HashMap<>(), this, openId);
        }
        long runTime = System.currentTimeMillis() - startTime;
        logger.info("runTime" + runTime + builder.toString());
        return new ModelAndView("redirect:" + url);
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
        msg.getAccountId();
        msg.getOpenId();
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("PersonFlowCenterController_followAndBind");

        Gson gson = new Gson();
        Type type = new TypeToken<UserFlowAccountBean>() {
        }.getType();
        String openid = (String) request.getSession().getAttribute("openId");
        String accountid = (String) request.getSession().getAttribute("accountid");
        String nickname = (String) request.getSession().getAttribute("nickName");
        String phoneNumber = (String) request.getSession().getAttribute("phoneNumber");
        //LOGGER.info(phoneNumber);
        // TODO 有漏洞
        String headimgUrl = (String) request.getSession().getAttribute("headImgUrl");
        //LOGGER.info(openid);
        //LOGGER.info(accountid+nickname+headimgUrl);

        builder.append("openId_" + openid + "accountId_" + accountid + "nickname_" + nickname + "phoneNumber_" + phoneNumber);
        request.setAttribute("openid", openid);
        request.setAttribute("accountid", accountid);


        //todo:根据openID和accountid去查用户的手机号

        //String phoneNumber="15201015003";
        request.getSession().setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("nickname", nickname);
        request.setAttribute("headimgUrl", headimgUrl);
        request.setAttribute("phoneNumber", phoneNumber);

        //todo:根据手机号查询用户流量获取的省内和全国流量值
        if (!allowNotBindPhoneNumber) {
            if (StringUtils.isBlank(phoneNumber)) {
                String redirectURL = "bindingController.do?redirectBinding&merchantId={0}&openId={1}&operateType={2}";
                return new ModelAndView("redirect:" + MessageFormat.format(redirectURL, accountid, openid, "个人中心"));
            }
            String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/QueryFlowAccountbyPhone";
            JSONObject myJsonObject = new JSONObject();
            myJsonObject.accumulate("phoneNumber", phoneNumber);
            JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
            String strContent = gson.toJson(content);

            UserFlowAccountBean.DataEntity accountBean = ((UserFlowAccountBean) gson.fromJson(strContent, type)).getData();
            String provinceFlowValue = "0";
            String countryFlowValue = "0";
            if (null != accountBean) {
                provinceFlowValue = String.valueOf(accountBean.getProvinceFlowValue());
                countryFlowValue = String.valueOf(accountBean.getCountryFlowValue());
            }

            request.setAttribute("provinceFlowValue", provinceFlowValue);
            request.setAttribute("countryFlowValue", countryFlowValue);
            builder.append("provinceFlowValue_" + provinceFlowValue + "countryFlowValue_" + countryFlowValue);
        }

        request.setAttribute("accountId", accountid);
        request.setAttribute("openid", openid);

        long runTime = System.currentTimeMillis() - startTime;
        logger.info("runTime" + runTime + builder.toString());
        return new ModelAndView("liuliangbao/weigatedoor/personFlowCenter");
    }

    @RequestMapping(params = "goflowGetChargeRecord")
    public ModelAndView goflowGetChargeRecord(HttpServletRequest request) {
        return new ModelAndView("liuliangbao/weigatedoor/flowGetRecord");
    }


    /**
     * TODO by 亮: 手机号码写死? 这个方法是不是不用了?
     * 领取流量跳转方法--xudan--2015-12-15
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "goGetFlowCharge")
    public void goGetFlowCharge(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*String provinceFlowValue=request.getParameter("provinceFlowValue");
        LOGGER.info(provinceFlowValue);*/
        String phoneNumber = "15201015003";
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();
        JSONObject myJsonObject = new JSONObject();
        String json = objectMapper.writeValueAsString(phoneNumber);
        PrintWriter out = response.getWriter();
        out.write(json);
    }


}
