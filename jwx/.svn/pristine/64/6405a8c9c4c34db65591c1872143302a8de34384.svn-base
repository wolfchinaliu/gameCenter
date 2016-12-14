package weixin.integrate.util;

import java.util.HashMap;

/**
 * 常量数据
 */
public class WxIntegrateConstant {
    public static final String flowtype_national = "1";
    public static final String flowtype_provincial = "2";
    public static final String resultcode = "resultCode";
    public static final String resultmsg = "resultMsg";
    public static final String businesskey = "businessKey";
    public static final String status_getbusinesskey = "1";
    public static final String status_success = "2";
    public static final String status_fail = "3";
    public static final String status_timeout = "4";

    public static final String businesstype_wxuserreceive = "WxUserReceive";
    public static final String businesstype_wxuserpay = "WxUserPay";
    public static final String businesstype_appuserreceive = "AppUserReceive";
    public static final String businesstype_appuserpay = "AppUserPay";

    public static final String session_verifypaypwd = "verifyphone_paypwd";
    public static final String session_businessKey = "businessKey";

    public static final String status_subscribe = "1";
    public static final String status_unsubscribe = "0";

    private static final HashMap<String, String> resultCodeMap = new HashMap<>();

    static {
        resultCodeMap.put("0", "成功");
        resultCodeMap.put("101", "IP鉴权失败");
        resultCodeMap.put("102", "鉴权失败");
        resultCodeMap.put("103", "商户ID非法");
        resultCodeMap.put("104", "活动ID非法");
        resultCodeMap.put("105", "流量类型非法");
        resultCodeMap.put("106", "流量值非法");
        resultCodeMap.put("107", "手机号非法");

        resultCodeMap.put("201", "商户流量余额不足");
        resultCodeMap.put("202", "不符合安全规则");
        resultCodeMap.put("203", "商户未进行微信公众号授权");
        resultCodeMap.put("204", "该微信号未关注公众号");

        resultCodeMap.put("500", "其他错误");
    }

    public static String getResultMsg(String code) {
        return resultCodeMap.get(code);
    }
}
