<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>刮刮乐</title>
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1, maximum-scale=1">
<meta http-equiv="cache-control" content="no-cache">
<link href="plug-in/weixin/ggl/ggl.css" rel="stylesheet" type="text/css" />
<link href="plug-in/weixin/zp/activity-style.css" rel="stylesheet"
	type="text/css">
<script src="plug-in/weixin/zp/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="plug-in/weixin/ggl/wScratchPad.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				var prize = "";
				var isfirst = 0;
				var iscontinue = 0;
				var prizeMsg = "";
				var picPath = "plug-in/weixin/images/ggl/nothing.png";
				$.ajax({
					url : "lotteryController.do?scratchMove",
					method : "POST",
					data : {
						"hdId" : '${hdId}',
						"openId" : '${openId}'
					},
					dataType : "JSON",
					async : false,
					success : function(result) {
						iscontinue = 1;
						prize = result.attributes.prize;
						prizeMsg = result.attributes.msg;
						var win = result.attributes.continueFlag; //是否中奖
						if (result.attributes.error == "invalid") {
							alert(result.attributes.msg);
							return

						}
						if (result.attributes.error == "getsn") {
							alert('本次活动你已经中过奖，不能再参加活动！');
							$('#wScratchPad2').wScratchPad('clear');
							return

						}
						if (prize == '1') {
							picPath = "plug-in/weixin/images/ggl/one.png";
						} else if (prize == '2') {
							picPath = "plug-in/weixin/images/ggl/two.png";
						} else if (prize == '3') {
							picPath = "plug-in/weixin/images/ggl/three.png";
						} else {
							picPath = "plug-in/weixin/images/ggl/nothing.png";
						}
						init(picPath);
					},
					error : function() {
						return;
					}
				});

				function init(picPath) {
					$("#wScratchPad2").wScratchPad({
						color : 'grey',//覆盖的刮刮层的颜色
						image : picPath, //刮奖结果图片
						cursor : 'plug-in/weixin/images/coin.gif', //刮的笔刷图片
						scratchMove : function(e, percent) {
							if (percent > 60) {
								this.clear();
								if (isfirst == 0) {
									if (prize == '1') {
										message = "恭喜你获得一等奖-";
										$("#result").slideToggle(500);
										$("#card").slideUp(500);
									} else if (prize == '2') {
										message = "恭喜你获得二等奖-";
										$("#result").slideToggle(500);
										$("#card").slideUp(500);
									} else if (prize == '3') {
										message = "恭喜你获得三等奖-";
										$("#result").slideToggle(500);
										$("#card").slideUp(500);
									} else {
										message = "再接再厉！";
										alert(message);
									}
									$("#prizetype").html(message + prizeMsg);
								}
								isfirst = 1;
							}
						}
					});
				}
</script>
</head>

<body>
	<div class="c_main">
		<div id="card" class="c100">
			<img src="plug-in/weixin/images/ggl/card.png" width="100%"
				height="100%" />
			<div id="gj">
				<div id="wScratchPad2" style="display: inline-block;"></div>
			</div>
		</div>
		<!--  	<div id="wScratchPad2" style="display:inline-block; position:relative; border:solid black 1px;"></div> -->


		<div class="content">
			<div class="boxcontent boxyellow" id="result" style="display: none">
				<div class="box">
					<div class="Detail">
						<div class="Detail">
							<c:if test="${noStart}">
								<p class="red">
									当前活动不可用，活动开始时间为
									<fmt:formatDate value="${hdEntity.starttime}" type="both" />
								</p>
							</c:if>
							<c:if test="${endError }">
								<p class="red">
									当前活动不可用，活动结束时间为
									<fmt:formatDate value="${hdEntity.endtime}" type="both" />
									
								</p>
							</c:if>
							<p>
								<input class="pxbtn" id="save-btn" name="提 交" type="button"
									onclick="closeMyWindow();" value="关闭">
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="boxcontent boxyellow">
				<div class="box">
					<div class="title-green">
						<span>奖项设置：</span>
					</div>
					<div class="Detail">
						<p>一等奖：${hdEntity.firstprize}
							。奖品数量：${hdEntity.firstprizetotal}</p>
						<p>二等奖：${hdEntity.secondprize} 。奖品数量：${hdEntity.secondprizetotal}</p>
						<p>三等奖：${hdEntity.thirdprize}
							。奖品数量：${hdEntity.thirdprizetotal}</p>
					</div>
				</div>
			</div>
			<div class="boxcontent boxyellow">
				<div class="box">
					<div class="title-green">活动说明：</div>

				</div>
			</div>
		</div>
		<div id="loading"></div>
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
</body>
</html>