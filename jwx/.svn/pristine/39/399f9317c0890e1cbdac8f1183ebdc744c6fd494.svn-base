package weixin.util;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.ResourceUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 记录日志信息的Filter
 *
 * Created by guoliang on 2016/8/2.
 */
public class AccessLogFilter implements Filter {
    public static final transient Logger LOGGER = Logger.getLogger(AccessLogFilter.class);
    public static String format = "%RA %openId %RM %SC %D %TM %CL %CT %R %RU %U %UA";
    public static final Map<String, Integer> accessTimeMap = new HashMap<>();

    private synchronized int incrMapValue(String key) {
        int value = MapUtils.getIntValue(accessTimeMap, key) + 1;
        accessTimeMap.put(key, value);
        return value;
    }

    private synchronized void descrMapValue(String key) {
        accessTimeMap.put(key, MapUtils.getIntValue(accessTimeMap, key) - 1);
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String parameters = request.getQueryString();
        parameters = StringUtils.isBlank(parameters) ? "" : "?" + parameters;
        String url = request.getRequestURL() + parameters + " ";

        int currentTimes = incrMapValue(url);
        chain.doFilter(req, resp);
        descrMapValue(url);

        long end = System.currentTimeMillis();

        String openId = getOpenId(request);

        int status = response.getStatus();
        status = 0 == status ? 200 : status;
        // %RA %openId %RM %SC %T %CL %CT %R %RU %U %UA
        String myFormat = format;
        myFormat = myFormat.replace("%RA ", StringUtils.defaultIfBlank(IpUtil.getIpAddr(request), "X") + " ");
        myFormat = myFormat.replace("%openId ", StringUtils.defaultIfBlank(openId, "X") + " ");
        myFormat = myFormat.replace("%RM ", StringUtils.defaultIfBlank(request.getMethod(), "X") + " ");
        myFormat = myFormat.replace("%SC ", StringUtils.defaultIfBlank(status + "", "X") + " ");
        myFormat = myFormat.replace("%D ", (end - start)/1000 + "ms ");
        myFormat = myFormat.replace("%TM ", currentTimes + " ");
        myFormat = myFormat.replace("%CL ", StringUtils.defaultIfBlank(response.getHeader("Content-Length"), "X") + " ");
        myFormat = myFormat.replace("%CT ", StringUtils.defaultIfBlank(response.getHeader("Content-Type"), "X") + " ");
        myFormat = myFormat.replace("%R ", StringUtils.defaultIfBlank(response.getHeader("Referer"), "X") + " ");
        myFormat = myFormat.replace("%RU ", request.getRequestURL() + " ");
        myFormat = myFormat.replace("%U ", url);
        myFormat = myFormat.replace("%UA", "\"" + StringUtils.defaultIfBlank(request.getHeader("User-Agent") + "\"", "X"));
        CommonUtils.SERVICE_LOGGER.info(myFormat);
    }

    private String getOpenId(HttpServletRequest request) {
        String openId = request.getParameter("openId");
        if (StringUtils.isBlank(openId)) {
            request.getParameter("openid");
        }
        if (StringUtils.isBlank(openId)) {
            openId = ((String) request.getSession(true).getAttribute("openId"));
        }
        if (StringUtils.isBlank(openId)) {
            openId = ((String) request.getSession().getAttribute("openid"));
        }
        return openId;
    }

    public void init(FilterConfig config) throws ServletException {
        format = StringUtils.defaultIfBlank(ResourceUtil.getLog_format(), format);
        LOGGER.info(String.format("日志格式配置已加载:format=%s", format));
    }

}
