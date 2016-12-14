<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<title>幸运大转盘抽奖</title>
<link href="plug-in/weixin/zp/activity-style.css" rel="stylesheet"
	type="text/css">
</head>

<body class="activity-lottery-winning">
	<div class="main">
		<script type="text/javascript">
			var loadingObj = new loading(document.getElementById('loading'), {
				radius : 20,
				circleLineWidth : 8
			});
			loadingObj.show();
		</script>
		<div id="outercont">
			<div id="outer-cont">
				<div id="outer" style="-webkit-transform: rotate(2136deg);">
					<img src="plug-in/weixin/images/activity-lottery-1.png"
						width="310px">
				</div>
			</div>
			<div id="inner-cont">
				<div id="inner">
					<img src="plug-in/weixin/images/activity-lottery-2.png">
				</div>
			</div>
		</div>
		<div class="content">
			<div class="boxcontent boxyellow" id="result" >
				<div class="box">
					<div class="title-orange">
						<span>提示</span>
					</div>
					<div class="Detail">
							<p class="red">
								当前活动不可用，请联系提供服务管理员
							</p>
					</div>
				</div>
			</div>


	</div>
	<script src="plug-in/weixin/zp/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
		function closeMyWindow() {

			if (weixinBridge()) {
				WeixinJSBridge.call('closeWindow');
			} else {
				window.close();
			}
		}
		function weixinBridge() {
			var ua = navigator.userAgent.toLowerCase();
			if (ua.match(/MicroMessenger/i) == "micromessenger") {
				return true;
			} else {
				return false;
			}
		}
		$(function() {
			$("#result").slideToggle(500);
			if ($("#BridgeType").val() != "") {
				if (!weixinBridge()) {
					alert("请用微信浏览器打开");
					window.close();
				}
			}
		});
	</script>
	<input type="text" name="hidden" value="${param.type }" id="BridgeType">
</body>
</html>