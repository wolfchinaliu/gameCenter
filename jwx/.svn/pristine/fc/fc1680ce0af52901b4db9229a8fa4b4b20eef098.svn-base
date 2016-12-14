<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html >
<html>
<head>
<title>门店集合</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:datagrid name="locationList" title="可选择一个或者多个门店" actionUrl="weixinLocationController.do?datagridLocation" idField="poiId"  checkbox="true" showRefresh="false">
	<t:dgCol title="编号" field="poiId" hidden="false"></t:dgCol>
	<t:dgCol title="门店名称" field="businessName" width="50"></t:dgCol>
</t:datagrid>
</body>
</html>