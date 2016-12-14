<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>系统公告</title>

 </head>
 <body>
	<p><h2 align="center">${weixinAnnouncementPage.title}</h2></p>
	<p align="center" style="color: gray;font-size: 10px;"><fmt:formatDate value="${weixinAnnouncementPage.createDate}" pattern="yyyy-MM-dd"/></p>
	<p align="center" style="font-size: 12px;">摘要：${weixinAnnouncementPage.summary}</p>
	<p style="line-height:25px;">&nbsp;&nbsp;&nbsp;&nbsp;${weixinAnnouncementPage.notes}</p>


 </body>