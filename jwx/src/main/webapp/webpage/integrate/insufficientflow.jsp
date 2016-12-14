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
    <title>流量不足</title>
    <link rel="stylesheet" type="text/css" href="plug-in/integrate/css/normalize.css">
    <script src="plug-in/liuliangbao/css/0304/js/common.js"></script>
</head>
<body>
<div class="hidden" hidden>
    <input id="businessKey" type="text" hidden="hidden" value="${businessKey}">
</div>
<article class="container">
    <!--页头-->
    <header class="header-valid-f">
        <div class="">
            <img class="logo-f" src="static/img/logo.png" alt="">
        </div>
    </header>
    <section class="main">
        <div class="valid-box">
        	<c:if test="${flowType =='1' }">
	        	<p class="td-24 cl-333">您本次交易支付省全国流量${flowValue }M。您的余额不足，您可以去赚更多流量用来支付。</p>
        	</c:if>
            <c:if test="${flowType =='2' }">
	        	<p class="td-24 cl-333">您本次交易支付省省内流量${flowValue }M。您的余额不足，您可以去赚更多流量用来支付。</p>
        	</c:if>
        </div>
        <section class="form form-home" style="margin: 1rem auto;">
            <form method="post" action="#">
                <div class="form-btn clearfix">
                    <a href="javascript:;" class="btn-default" style="float:left;width:45%;">不用啦</a>
                    <a href="javascript:;" class="btn-default" style="float:right;width:45%;">去赚流量</a>
                </div>
            </form>
        </section>
    </section>
</article>
</body>
</html>