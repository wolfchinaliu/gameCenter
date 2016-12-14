package weixin.interfacedistribute.callback;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import weixin.liuliangbao.util.HttpUtil;
import weixin.util.LogUtil;

@Controller
@RequestMapping("/callbackController")
@SuppressWarnings("all")
public class CallbackController {

    public static final Logger LOGGER = Logger.getLogger(CallbackController.class);
    public static String path = ResourceUtil.getConfigByName("jfinalUrl-config");

    @RequestMapping(params = "wangsu", method = RequestMethod.POST)
    @ResponseBody
    public String callBack(HttpServletRequest request) {
        String sender = request.getHeader("Sender");
        if (!StringUtils.equals(sender, "FDN")) {
            return "ok";
        }
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            // 读取请求内容
            inputStream = request.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String reqBody = stringBuilder.toString();
            JSONObject req = JSONObject.fromObject(reqBody);
            String orderId = MapUtils.getString(req, "orderId");
//            String cpOrderNo = MapUtils.getString(req, "cpOrderNo");
            String transNo = MapUtils.getString(req, "transNo");
            String resultCode = MapUtils.getString(req, "resultCode");
            String resultMsg = MapUtils.getString(req, "resultMsg");
            String url = path + "chargeflow/finishUserCharge";

            JSONObject myJsonObject = new JSONObject();
            myJsonObject.element("recordId", transNo);
            myJsonObject.element("ispOrderId", orderId);
            myJsonObject.element("reason", "resultCode:"+resultCode+"_resultMsg:"+resultMsg);
            if(StringUtils.equals(resultCode, "20407")){
                return "ok";
            }
            if(StringUtils.equals(resultCode, "10100")){
                myJsonObject.element("chargeState", "充值成功");
            } else {
                myJsonObject.element("chargeState", "充值失败");
            }
            JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);
            LOGGER.info(content);
        } catch (Exception ex) {
            LOGGER.info(LogUtil.printStackTrace(ex));
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ex) {
                LOGGER.info(LogUtil.printStackTrace(ex));
            }
        }
        // 返回 ok
        return "ok";
    }
}