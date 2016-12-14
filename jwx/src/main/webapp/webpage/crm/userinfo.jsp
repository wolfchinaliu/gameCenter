<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>灵动移动CRM</title>
</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<div class="m-title">
					个人信息
				</div>
                <div class="m-left">
                   <a href="javascript:history.go(-1);" class="easyui-linkbutton m-back" plain="true" outline="false"></a>
                </div>
                <div class="m-right">                    
                    <a href="#" class="easyui-linkbutton" plain="true" outline="false">编辑</a>
                </div>
				
			</div>
		</header>
		<div style="margin:20px auto;width:100px;height:100px;border-radius:100px;overflow:hidden">
			<img src="crm/icon/xiao.jpg" style="margin:0" width="100px" height="100px">			
		</div>
		<div style="text-align: center;">石少 总经理</div>
		<ul class="m-list">
			<li></li>
			<li>签名：我靠！</li>
			<li>电话：</li>
			<li>手机：18620508961</li>
			<li>QQ：785863737</li>
			<li>微信：yanhong_2010</li>
			<li>邮箱：785863737@qq.com</li>
			<li>地址：</li>
		</ul>
		<%-- 
		<div style="margin:50px 0 0;text-align:center">
            <a href="javascript:void(-1)" class="easyui-linkbutton" style="width:100px;height:30px" onclick="$.mobile.back()">返回</a>
        </div>
		--%>
	</div>
</body>
</html>