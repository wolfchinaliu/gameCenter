<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>领取成功</title>
    <link rel="stylesheet" type="text/css" href="plug-in/integrate/css/normalize.css">
    <script src="plug-in/liuliangbao/css/0304/js/common.js"></script>
</head>
<body>
<div class="hidden" hidden>
    <input id="businessKey" type="text" hidden="hidden" value="${businessKey}">
    <input id="accountName" type="text" hidden="hidden" value="${accountName}">
</div>
<article class="container">
    <!--页头-->
    <header class="header-valid-f">
        <div class="">
            <img class="logo-f" src="${accountHeadImg }" alt="">
            <H2>${accountName }</H2>
        </div>
    </header>
    <section class="main">
        <div class="valid-box">
        	<c:if test="${flowType =='national' }">
	        	<p class="td-24 cl-333">恭喜您成功领取全国流量<span class="cl-red">${flowValue }M</span></p>
        	</c:if>
            <c:if test="${flowType =='provincial' }">
	        	<p class="td-24 cl-333">恭喜您成功领取省内流量<span class="cl-red">${flowValue }M</span></p>
        	</c:if>
        </div>
        <section class="form form-home" style="margin: 1rem auto;">
            <form method="post" action="#">
                <div class="form-btn clearfix">
                    <a href="userChargeController.do?userChargeView&accountid=${acctid}&openId=${openid}" class="btn-default" style="float:right;width:45%;">去充值</a>
                </div>
            </form>
        </section>
    </section>
</article>
</body>
</html>