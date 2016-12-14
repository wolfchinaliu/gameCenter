<%@ taglib prefix="t" uri="/easyui-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page import="org.jeecgframework.core.util.ResourceUtil"%>
<% 
String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
String basePath = request.getScheme()+"://"+request.getServerName()+path;
String resourcePath=ResourceUtil.getResourcePath();
String mediaUrlPrefix=ResourceUtil.getMediaUrlPrefix();
String cdnHost=ResourceUtil.getCdnHost();
%>
<c:set var="webRoot" value="<%=basePath%>" />
<c:set var="resourcePath" value="<%=resourcePath%>" />
<c:set var="mediaUrlPrefix" value="<%=mediaUrlPrefix%>" />
<c:set var="cdnHost" value="<%=cdnHost%>" />