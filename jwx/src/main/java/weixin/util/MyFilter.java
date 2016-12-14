package weixin.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import weixin.guanjia.core.util.MessageUtil;
import weixin.guanjia.core.util.PageAuthRedisCache;
import weixin.lottery.entity.WeixinCommonforhdEntity;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.mailmanager.entity.ViewAddressEntity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * Created by xiaona on 2016/5/10.
 */
public class MyFilter implements Filter {
    public static final transient Logger LOGGER = Logger.getLogger(MyFilter.class);

    //tomcat启动，或者context重新加载的时候调用init（先destroy再init）
    public void init(FilterConfig filterConfig) throws ServletException {
        // LOGGER.info("MyFilter init...");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            request = new MyHttpServletRequestWrapper((HttpServletRequest) request);


            // LOGGER.info("filtering................................");
            StringBuffer requestURL = ((HttpServletRequest) request).getRequestURL();
            if (!StringUtils.contains(requestURL, ".do")) {
                LOGGER.debug(String.format("不是接口，不记录其信息：%s", requestURL));
                chain.doFilter(request, response);
            } else {
                String queryString = ((HttpServletRequest) request).getQueryString();  //获取参数
                LOGGER.info(String.format("开始记录请求信息：%s， %s", requestURL + "?" + queryString, ((HttpServletRequest) request).getMethod()));
                Map<String, String> requestParamMap = null;
                int length = request.getContentLength();

                String contentType = request.getContentType();
                InputStream is = null;
                if (length > 0 && StringUtils.contains(contentType, "text/xml")) {
                    try {
                        is = request.getInputStream();
                        byte[] buffer;
                        buffer = new byte[length];

                        int pad = 0;
                        while (pad < length) {
                            pad += is.read(buffer, pad, length);
                        }
                        requestParamMap = MessageUtil.parseStringXML(buffer);
                        LOGGER.info("请求体：" + requestParamMap);
                        if (StringUtils.isNotBlank(requestParamMap.get("FromUserName"))) {
                            LOGGER.info(String.format("用户%s触发了%s，访问：%s", requestParamMap.get("FromUserName"), requestParamMap.get("MsgType"), StringUtils.trim(requestParamMap.get("EventKey"))));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                SystemService systemService = (SystemService) ApplicationContextUtil.getContext().getBean("systemService");  //获取到对应的服务

                /** 获取到的操作人商户账户的相关的id */
                String accountid = ResourceUtil.getWeiXinAccountId();   //获取的是后台的登陆的商户的id,当然这个id还是需要进一步进行获取的
                //request到达的时候执行下面的代码
                Integer count = 0;
                HttpServletRequest hsreq = (HttpServletRequest) request;   //servletrequest强转为httpServletRequest 目的是为了使用第二种的形式获取到请求的url
                StringBuffer curUrl = hsreq.getRequestURL();//获得客户端发送请求的完整url,
                String hdid = hsreq.getParameter("hdid");
                WeixinLotteryEntity weixinLotteryEntity = null;
                WeixinCommonforhdEntity weixinCommonforhdEntity = null;
                if (StringUtils.isBlank(accountid)) {
                    accountid = hsreq.getParameter("accountid");
                    if (StringUtils.isBlank(accountid)) {
                        if (StringUtils.isNotBlank(hdid)) {
                            weixinLotteryEntity = systemService.getEntity(WeixinLotteryEntity.class, hdid);
                            if (null == weixinLotteryEntity) {
                                weixinLotteryEntity = new WeixinLotteryEntity();
                                weixinCommonforhdEntity = systemService.getEntity(WeixinCommonforhdEntity.class, hdid);
                                if (null == weixinCommonforhdEntity) {
                                    weixinCommonforhdEntity = new WeixinCommonforhdEntity();
                                } else {
                                    accountid = weixinCommonforhdEntity.getAccountid();
                                }
                            } else {
                                accountid = weixinLotteryEntity.getAccountid();
                            }
                        } else {
                            accountid = request.getParameter("t");
                            if (StringUtils.isBlank(accountid)) {
                                String stateValue = request.getParameter("state");
                                if (StringUtils.isNotBlank(stateValue)) {
                                    accountid = PageAuthRedisCache.getAccountIdCache(stateValue).getAccountId();
                                }
                            }
                        }
                    }
                }
                //根据活动id获取到商户id
                // WeixinAccountEntity weixinAccountEntity = null;
                String requestURI = hsreq.getRequestURI(); //获得客户端发送请求的完整uri
                // 读取配置文件
                if (requestURI.contains(".do")) {
                    ViewAddressEntity viewAddressEntity = new ViewAddressEntity();
                    viewAddressEntity.setAccountid(accountid);  //后端访问的时候存储的id
                    viewAddressEntity.setViewAddress(requestURI + "?" + queryString);
                    viewAddressEntity.setRequestBody(null == requestParamMap ? "" : requestParamMap.toString());
                    viewAddressEntity.setOpenId(null != requestParamMap ? requestParamMap.get("FromUserName") : "");
                    viewAddressEntity.setAddDate(new Date());
                    systemService.save(viewAddressEntity);
                    // LOGGER.info(requestURI);
                }
                //转给过滤器链中的下一个filter，如果是最后一个filter，调用要访问的资源
                // LOGGER.info("ending filtering................................");
                chain.doFilter(request, response);
            }
        }
    }
    //tomcat关闭或者context重新加载的时候调用destroy
    public void destroy() {
        LOGGER.info("MyFilter destroy...");
    }

//    /**
//     * 将xml文件转换为list集合
//     * @param xml
//     * @param cls
//     * @return
//     * @throws Exception
//     */
//    public static List<String> parseXml2List(String xml, Class<?> cls)
//            throws Exception {
//        List<String> lists = null;
//
//        Document doc = DocumentHelper.parseText(xml);
//
//        return lists;
//
//    }

}
