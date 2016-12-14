<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>充值成功</title>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0517/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0517/css/flowcard.css">
    <script src="plug-in/liuliangbao/css/0517/js/common.js"></script>
</head>
<body style="height: 100%;">
<article class="flowcard">
    <header class="flowcard-header">
        <h1 class="flowcard-logo">
            <a href="javascript:;"><img src="${url}" /></a>
            <span>${accountName}</span>
        </h1>
    </header>
    <section class="flowcard-section">
        <p style="display: block; text-align: center;">您获得<span class="cl-red">${flowValue}M</span>流量，已经存入到您的账户。</p>
    </section>
    <div class="flowcard-btn" style="padding-top: 1.8rem;">
        <a href="personFlowCenterController.do?goPersonCenter&accountid=${accountid}&openId=${openId}" class="flowcard-btn-1">去充值</a>
    </div>
</article>
</body>
</html>
