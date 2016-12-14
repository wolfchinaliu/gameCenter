package weixin.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by GuoLiang on 2016/6/15 19:41 11:06.
 *
 */
public class AvoidRepeatOperationFilter implements HandlerInterceptor {
    public static final Logger LOGGER = Logger.getLogger(AvoidRepeatOperationFilter.class);

    /**
     * key为openId, value为操作的信息
     */
    private static final Set<AvoidRepeatInfo> AVOID_REPEAT_INFO_MAP = new HashSet<>();


    private Map<String, String> avoidURLMap;


    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteHost();
        }
        return ip;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse resp, Object o) throws Exception {
        String merchantId = request.getParameter("accountid");
        if (StringUtils.isBlank(merchantId)) {
            merchantId = request.getParameter("accountId");
        }
        if (StringUtils.isBlank(merchantId)) {
            merchantId = request.getParameter("merchantId");
        }
        String openId = request.getParameter("openId");
        openId = StringUtils.isBlank(openId) ? request.getParameter("openid") : openId;
        String phoneNumber = request.getParameter("phoneNumber");
        String shiliuOpenId = request.getParameter("shiliuOpenId");

        Map<String, String[]> parameterMap = request.getParameterMap();
        LOGGER.debug("preHandle" + parameterMap);

        if (StringUtils.isNotBlank(phoneNumber) && parameterMap.containsKey("testChargeFlow")) {
            AvoidRepeatInfo avoidRepeatInfo = new AvoidRepeatInfo(openId, merchantId, "充值", phoneNumber);
            if (AVOID_REPEAT_INFO_MAP.contains(avoidRepeatInfo)) {
                String remoteIPAddress = getIpAddr(request);
                resp.getWriter().print("操作不要过于频繁!");
                LOGGER.warn("[并发充值]:IP地址为:" + remoteIPAddress + ", " + request.getQueryString());
                return false;
            } else {
                AVOID_REPEAT_INFO_MAP.add(avoidRepeatInfo);
                LOGGER.info("充值开始: " + AVOID_REPEAT_INFO_MAP);
                return true;
            }
        }

        if (StringUtils.isNotBlank(openId)){
            String containsKey = CommonUtils.containsAny(parameterMap.keySet(), avoidURLMap.keySet());
            if (StringUtils.isBlank(containsKey) || StringUtils.isBlank(avoidURLMap.get(containsKey))) return true;

            AvoidRepeatInfo avoidRepeatInfo = new AvoidRepeatInfo(openId, merchantId, avoidURLMap.get(containsKey), phoneNumber);
            if (AVOID_REPEAT_INFO_MAP.contains(avoidRepeatInfo)) {
                String remoteIPAddress = getIpAddr(request);
                resp.getWriter().print("操作不要过于频繁!");
                LOGGER.warn("[并发" + avoidURLMap.get(containsKey) + "]:IP地址为:" + remoteIPAddress + ", " + request.getQueryString());
                return false;
            } else {
                AVOID_REPEAT_INFO_MAP.add(avoidRepeatInfo);
                LOGGER.info(avoidURLMap.get(containsKey) + "开始: " + AVOID_REPEAT_INFO_MAP);
                return true;
            }
        } else if (StringUtils.isNotBlank(shiliuOpenId)) {
            String containsKey = CommonUtils.containsAny(parameterMap.keySet(), avoidURLMap.keySet());
            if (StringUtils.isBlank(containsKey) || StringUtils.isBlank(avoidURLMap.get(containsKey))) return true;

            AvoidRepeatInfo avoidRepeatInfo = new AvoidRepeatInfo(merchantId, null, shiliuOpenId, avoidURLMap.get(containsKey), phoneNumber);
            if (AVOID_REPEAT_INFO_MAP.contains(avoidRepeatInfo)) {
                String remoteIPAddress = getIpAddr(request);
                resp.getWriter().print("操作不要过于频繁!");
                LOGGER.warn("[并发" + avoidURLMap.get(containsKey) + "]:IP地址为:" + remoteIPAddress + ", " + request.getQueryString());
                return false;
            } else {
                AVOID_REPEAT_INFO_MAP.add(avoidRepeatInfo);
                LOGGER.info(avoidURLMap.get(containsKey) + "开始: " + AVOID_REPEAT_INFO_MAP);
                return true;
            }
        } else  {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse resp, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse resp, Object o, Exception e) throws Exception {
        String merchantId = request.getParameter("accountid");
        if (StringUtils.isBlank(merchantId)) {
            merchantId = request.getParameter("accountId");
        }
        if (StringUtils.isBlank(merchantId)) {
            merchantId = request.getParameter("merchantId");
        }
        String openId = request.getParameter("openId");
        openId = StringUtils.isBlank(openId) ? request.getParameter("openid") : openId;
        String shiliuOpenId = request.getParameter("shiliuOpenId");
        String phoneNumber = request.getParameter("phoneNumber");
        Map<String, String[]> parameterMap = request.getParameterMap();
        LOGGER.debug("afterCompletion" + parameterMap);

        if (StringUtils.isNotBlank(phoneNumber) && parameterMap.containsKey("testChargeFlow")) {
            AvoidRepeatInfo avoidRepeatInfo = new AvoidRepeatInfo(openId, merchantId, "充值", phoneNumber);
            AVOID_REPEAT_INFO_MAP.remove(avoidRepeatInfo);
            LOGGER.info("充值结束: " + AVOID_REPEAT_INFO_MAP);
        } else if (StringUtils.isNotBlank(openId)) {
            String containsKey = CommonUtils.containsAny(parameterMap.keySet(), avoidURLMap.keySet());
            if (StringUtils.isBlank(containsKey) || StringUtils.isBlank(avoidURLMap.get(containsKey))) return;

            AvoidRepeatInfo avoidRepeatInfo = new AvoidRepeatInfo(openId, merchantId, avoidURLMap.get(containsKey), phoneNumber);
            AVOID_REPEAT_INFO_MAP.remove(avoidRepeatInfo);
            LOGGER.info(String.format("%s结束: %s", avoidURLMap.get(containsKey), AVOID_REPEAT_INFO_MAP));
        } else if (StringUtils.isNotBlank(shiliuOpenId)) {
            String containsKey = CommonUtils.containsAny(parameterMap.keySet(), avoidURLMap.keySet());
            if (StringUtils.isBlank(containsKey) || StringUtils.isBlank(avoidURLMap.get(containsKey))) return;

            AvoidRepeatInfo avoidRepeatInfo = new AvoidRepeatInfo(merchantId, null, shiliuOpenId, avoidURLMap.get(containsKey), phoneNumber);
            AVOID_REPEAT_INFO_MAP.remove(avoidRepeatInfo);
            LOGGER.info(String.format("%s结束: %s", avoidURLMap.get(containsKey), AVOID_REPEAT_INFO_MAP));
        }
    }

    public Map<String, String> getAvoidURLMap() {
        return avoidURLMap;
    }

    public void setAvoidURLMap(Map<String, String> avoidURLMap) {
        this.avoidURLMap = avoidURLMap;
    }
}
