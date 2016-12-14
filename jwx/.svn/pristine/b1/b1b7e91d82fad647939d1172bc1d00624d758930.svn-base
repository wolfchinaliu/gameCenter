<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,DatePicker"></t:base>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>免费流量</title>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/index.css">
    <script src="plug-in/liuliangbao/js/0201/lib/common.js"></script>
</head>
<body>
<article class="container">
    <!--页头-->
    <header class="header">

    </header>

    <section class="main">

        <p class="main-text">
            ${message}下面这些商家以各种姿势赠送免费流量
        </p>

        <c:forEach var="item" items="${moreGZEntities}">
            <section class="adbox">
                <article class="aditem">
                    <a href="mainController.do?load&accountid=${item.id}&openId=${openId}" class="clx">
                        <figure class="aditem-img">
                            <img src="${item.logoAccount}" alt=""/>
                        </figure>
                        <section class="aditem-msg">
                            <p>
                                首次关注并验证手机<br/>${item.accountName}
                            </p>

                            <h2>${item.flowValue}M</h2>
                        </section>
                    </a>
                </article>
            </section>
        </c:forEach>

        <c:forEach var="item" items="${moreQDEntities}">
            <section class="adbox">
                <article class="aditem">
                    <a href="signController.do?startLoad&accountid=${item.id}&openId=${openId}" class="clx">
                        <figure class="aditem-img">
                            <img src="${item.logoAccount}" alt=""/>
                        </figure>
                        <section class="aditem-msg">
                            <p>
                                签到<br/>${item.accountName}
                            </p>

                            <h2>${item.flowValue}M</h2>
                        </section>
                    </a>
                </article>
            </section>
        </c:forEach>

        <c:forEach var="item" items="${moreGameEntities}">
            <section class="adbox">
                <article class="aditem">
                    <a href="lotteryController.do?startLottery&accountid=${item.accountid}&hdid=${item.id}&openId=${openId}"
                       class="clx">
                        <figure class="aditem-img">
                            <img src="${item.logoAccount}" alt=""/>
                        </figure>
                        <section class="aditem-msg">
                            <p>
                                    ${item.accountName}--${mer.title}<br/>
                            </p>

                            <h2>${item.flowValue}M</h2>
                        </section>
                    </a>
                </article>
            </section>
        </c:forEach>

        <c:forEach var="item" items="${moreShareEntities}">
            <section class="adbox">
                <article class="aditem">
                    <a href="shareController.do?startLoad&accountid=${item.accountid}&phone=${phoneNumber}&openId=${openId}&shareId=${item.shareTitle}"
                       class="clx">
                        <figure class="aditem-img">
                            <img src="${item.imagepath}" alt=""/>
                        </figure>
                        <section class="aditem-msg">
                            <p>
                                    ${item.title}<br/>
                            </p>

                            <h2>${item.flowValue}M</h2>
                        </section>
                    </a>
                </article>
            </section>
        </c:forEach>
    </section>
</article>
</body>
</html>

