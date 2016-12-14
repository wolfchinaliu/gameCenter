package weixin.liuliangbao.weigatedoor.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sdk.jfinal.JFinalUtils;
import weixin.liuliangbao.jsonbean.ViewBean.UserChargedFlowRecordsBean;
import weixin.liuliangbao.jsonbean.ViewBean.UserFlowGiveBean;
import weixin.liuliangbao.util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aa on 2015/12/14.
 */
@Controller
@RequestMapping("/userFlowRecordController")
public class UserFlowRecordController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(WeiDoorController.class);

    @Autowired
    private SystemService systemService;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * get user getFlow record --xudan- 2015-12-16
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "goUserFlowRecord")
    public ModelAndView goUserFlowRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("UserFlowRecordController_goUserFlowRecord");

        ModelAndView mav = new ModelAndView();

        String openid = (String) request.getSession().getAttribute("openId");
        String accountid = (String) request.getSession().getAttribute("accountid");
        String nickname = (String) request.getSession().getAttribute("nickname");
        if (StringUtils.isBlank(nickname)) {
            nickname = ((String) request.getSession().getAttribute("nickName"));
        }
        String phoneNumber = (String) request.getSession().getAttribute("phoneNumber");
        builder.append("openid_" + openid + "accountid_" + accountid + "phoneNumber_" + phoneNumber + "nickname_" + nickname);

        request.setAttribute("openid", openid);
        request.setAttribute("accountid", accountid);
        request.setAttribute("nickname", nickname);
        request.setAttribute("phoneNumber", phoneNumber);

        UserFlowGiveBean userFlowJson = JFinalUtils.getUserFlowGiveRecord(accountid, openid, phoneNumber);
        List<UserFlowGiveBean.DataEntity> userFlowGiveBean = new ArrayList<UserFlowGiveBean.DataEntity>();
        if (userFlowJson != null) {
            userFlowGiveBean = userFlowJson.getData();
            mav.addObject("userFlowJson", userFlowGiveBean);
            builder.append("record_not_null");
            long runTime = System.currentTimeMillis() - startTime;
            LOGGER.info("runTime" + runTime + builder.toString());

            mav.setViewName("liuliangbao/weigatedoor/flowGetRecord");
            return mav;
        } else {
            mav.addObject("userFlowJson", "");
            builder.append("record_is_null");
            long runTime = System.currentTimeMillis() - startTime;
            LOGGER.info("runTime" + runTime + builder.toString());

            mav.setViewName("liuliangbao/weigatedoor/flowGetRecord");
            return mav;
        }

        //return new ModelAndView("liuliangbao/weigatedoor/flowGetRecord");
    }


    /**
     * get User charge record -xudan -2015-012-16
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "goUserReceiveRecord")
    public ModelAndView goUserReceiveRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mav = new ModelAndView();
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("UserFlowRecordController_goUserReceiveRecord");

        String phoneNumber = (String) request.getSession().getAttribute("phoneNumber");

        Gson gson = new Gson();
        Type type = new TypeToken<UserChargedFlowRecordsBean>() {
        }.getType();

        //调用接口查询用户流量充值记录以及充值进度
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/getUserChargeRecords";
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        builder.append("phoneNumber"+phoneNumber);

        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
        LOGGER.info(content);

        String strcontent = gson.toJson(content);
        LOGGER.info(strcontent);
        UserChargedFlowRecordsBean userChargedJson = gson.fromJson(strcontent, type);

        List<UserChargedFlowRecordsBean.DataEntity> userFlowChargedBean = new ArrayList<UserChargedFlowRecordsBean.DataEntity>();

        if (userChargedJson != null) {
            userFlowChargedBean = userChargedJson.getData();
            mav.addObject("userFlowChargedBean", userFlowChargedBean);
            mav.setViewName("liuliangbao/weigatedoor/flowReceiveRecord");

            builder.append("record_not_null");
            long runTime = System.currentTimeMillis() - startTime;
            LOGGER.info("runTime" + runTime + builder.toString());
            return mav;
        } else {
            mav.addObject("userFlowChargedBean", null);
            mav.setViewName("liuliangbao/weigatedoor/flowReceiveRecord");

            builder.append("record_is_null");
            long runTime = System.currentTimeMillis() - startTime;
            LOGGER.info("runTime" + runTime + builder.toString());
            return mav;
        }
    }


    /*暂时不用*/
    @RequestMapping(params = "goUserChargeFlowRecord")
    public ModelAndView goUserChargeFlowRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("UserFlowRecordController_goUserChargeFlowRecord");
        ModelAndView mav = new ModelAndView();

        String openid = (String) request.getSession().getAttribute("openId");
        String accountid = (String) request.getSession().getAttribute("accountid");
        String nickname = (String) request.getSession().getAttribute("nickname");
        String phoneNumber = (String) request.getSession().getAttribute("phoneNumber");

        request.setAttribute("openid", openid);
        request.setAttribute("accountid", accountid);
        request.setAttribute("nickname", nickname);
        request.setAttribute("phoneNumber", phoneNumber);

        Gson gson = new Gson();
        Type type = new TypeToken<UserFlowGiveBean>() {
        }.getType();


        //调用接口查询用户流量获取记录
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "chargeflow/getUserChargeRecords";
        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("phoneNumber", phoneNumber);


        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
        LOGGER.info(content);

        String strcontent = gson.toJson(content);
        LOGGER.info(strcontent);
        UserFlowGiveBean userFlowJson = gson.fromJson(strcontent, type);

        List<UserFlowGiveBean.DataEntity> userFlowGiveBean = new ArrayList<UserFlowGiveBean.DataEntity>();
        userFlowGiveBean = userFlowJson.getData();

        if (userFlowGiveBean != null) {
            mav.addObject("userFlowJson", userFlowGiveBean);

            mav.setViewName("liuliangbao/weigatedoor/flowGetRecord");
            long runTime = System.currentTimeMillis() - startTime;
            LOGGER.info("runTime" + runTime + builder.toString());
            return mav;
        } else {
            mav.addObject("userFlowJson", "");

            mav.setViewName("liuliangbao/weigatedoor/flowGetRecord");
            long runTime = System.currentTimeMillis() - startTime;
            LOGGER.info("runTime" + runTime + builder.toString());
            return mav;
        }


    }


}
