<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html >
<html>
<head>
<title>关注用户集合</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:datagrid name="memberList" title="按角色选择" actionUrl="weixinMessageController.do?datagridMember" idField="id" checkbox="true" showRefresh="false">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="编号" field="openId" hidden="false"></t:dgCol>
	<t:dgCol title="昵称" field="nickName" width="50"></t:dgCol>
	<t:dgCol title="头像"  field="headImgUrl"  hidden="true" image="true" imageSize="40,40" width="120"></t:dgCol>
</t:datagrid>
</body>
</html>
