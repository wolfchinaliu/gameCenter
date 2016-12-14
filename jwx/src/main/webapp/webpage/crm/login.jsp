<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<title>灵动移动CRM</title>
</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<span class="m-title">Leader CRM 会员登录</span>
			</div>
		</header>
		<div style="margin:20px auto;width:100px;height:100px;border-radius:100px;overflow:hidden">
			<img src="crm/icon/006.gif" style="margin:0" width="100px" height="100px">
		</div>
		<div style="padding:0 20px">
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" data-options="prompt:'登录账号(手机号码)',iconCls:'icon-man'" style="width:100%;height:38px">
			</div>
			<div>
				<input class="easyui-textbox" type="password" data-options="prompt:'密码',iconCls:'icon-lock'" style="width:100%;height:38px">
			</div>
			<div style="text-align:center;margin-top:30px">
				<a href="crmLoginController.do?index" class="easyui-linkbutton" style="width:100%;height:40px"><span style="font-size:16px">登录</span></a>
			</div>
			<div style="text-align:right;margin-top:30px">
				您还没有 Leader CRM 账号？ <a href="crmLoginController.do?register" class="easyui-linkbutton" plain="true" outline="true" style="width:80px;height:35px">
				<span style="font-size:16px">注册使用</span></a> 
			</div>
			<div style="text-align:right;margin-top:10px">
				无需注册，快速体验？ <a href="crmLoginController.do?index" class="easyui-linkbutton" plain="true" outline="true" style="width:80px;height:35px">
				<span style="font-size:16px">快速体验</span></a> 
			</div>
		</div>
	</div>
</body>
</html>