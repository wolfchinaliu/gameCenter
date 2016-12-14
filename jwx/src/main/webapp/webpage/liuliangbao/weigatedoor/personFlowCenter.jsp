<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>流量中心</title>
		<script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>
		<link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
	    <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/center/index.css">
	    <script>
			 function onBridgeReady(){
			WeixinJSBridge.call('hideOptionMenu');
			}
			
			if (typeof WeixinJSBridge == "undefined"){
			if( document.addEventListener ){
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
			}else if (document.attachEvent){
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			}
			}else{
			onBridgeReady();
			} 
</script> 
	</head>
	<body>
		<div class="container">
			<header class="header">
				<div class="header-user">
					<figure>
						<a href="javascript:;"><img src="${headImgUrl}" alt="" /></a>
					</figure>
					<dl>
						<dt>${nickName}</dt>
						<dd>${phoneNumber}</dd>
					</dl>
				</div>
				<div class="header-msg">
					账户-全国流量：${countryFlowValue}M<span>|</span>账户-省内流量：${provinceFlowValue}M
				</div>
			</header>
			
			<nav class="center-nav">
				<ul class="center-nav-list clx">
					<li>
						<a href="userFlowRecordController.do?goUserFlowRecord">
							<p class="center-nav-img"><img src="${cdnHost}/plug-in/liuliangbao/20160701/images/btn1.png" alt="" /></p>
							<p class="center-nav-msg">流量记录</p>
						</a>
					</li>
					<li>
						<a href="userChargeController.do?userChargeView&accountid=${accountId}&openId=${openid}">
							<p class="center-nav-img"><img src="${cdnHost}/plug-in/liuliangbao/20160701/images/btn2.png" alt="" /></p>
							<p class="center-nav-msg">流量充值</p>
						</a>
					</li>
					<li>
						<a href="acctListController.do?weixinacctList&accountid=${accountId}&openId=${openid}">
							<p class="center-nav-img"><img src="${cdnHost}/plug-in/liuliangbao/20160701/images/btn3.png" alt="" /></p>
							<p class="center-nav-msg">更多免费流量</p>
						</a>
					</li>
					<li>
						<a href="#" onclick="BackOne()">
							<p class="center-nav-img"><img src="${cdnHost}/plug-in/liuliangbao/20160701/images/btn4.png" alt="" /></p>
							<p class="center-nav-msg">敬请期待</p>
						</a>
					</li>
				</ul>
			</nav>
		</div>
		<script src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
		<script src="${cdnHost}/plug-in/liuliangbao/20160701/js/zimi/index.js"></script>
		<script src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/util.js"></script>
		<script type="text/javascript">
		function BackOne() {
	        $.mobTips("此功能暂未开放，敬请期待！");
	    }
		</script>
	</body>
</html>
