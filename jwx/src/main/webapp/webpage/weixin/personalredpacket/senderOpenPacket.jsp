<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
    <title>制作红包 - 个人主页</title>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/index.css">
    <script src="plug-in/liuliangbao/js/0201/lib/common.js"></script>
</head>
<body>
<article class="container">
    <!--页头-->
    <header class="header header-makered">
        <figure class="photo p140-140">
            <a href="#"><img src="${imgpic}" alt="" /></a>
        </figure>
        <ul class="header-msg-makered">
            <li>昵称：${nickname}</li>
            <li>手机号：${phonenumber}</li>
            <li>省内流量：${provinceFlowValue}M <span class="plrem02">全国流量：${countryFlowValue}M</span></li>
        </ul>
    </header>

    <section class="main">

        <p class="main-text main-test-535353">
            红包内流量：${redPacketFlow}M，共<span class="main-text-red1">${redPacketNum}</span>份 <br />
            已领取<span class="main-text-red1">${countredpacket}</span>份，${sendflow}M.
        </p>

        <section class="main">
            <dl class="redlist">
                <dt>
                    <span>昵称</span>
                    <span>时间</span>
                    <span>领取数额</span>
                </dt>
                <c:forEach items="${list}" var="lisRedPacktet">
                    <dd class="ptrem01">
                        <span title="${lisRedPacktet.nickname}">${lisRedPacktet.nickname}</span>
                        <span>${lisRedPacktet.createTime}</span>
                        <span>领取${lisRedPacktet.redFlowValue}M</span>
                    </dd>
                </c:forEach>
            </dl>
        </section>
        <p class="main-text ptrem02 main-test-535353">
            发出的红包，如在${endtime}还未领完，余额将自动返还给商家。所以赶紧让您的朋友领取吧.
        </p>

    </section>

</article>


</body>
</html>