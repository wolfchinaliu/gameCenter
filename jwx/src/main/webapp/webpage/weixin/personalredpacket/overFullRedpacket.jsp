<%@ taglib prefix="t" uri="/easyui-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

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
    <title>个人红包 - 领完了</title>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/index.css">
    <script src="plug-in/liuliangbao/js/0201/lib/common.js"></script>
</head>
<body>
<article class="container">
    <!--页头-->
    <header class="header header-photo">
        <figure class="photo p140-140">
            <a href="#"><img src="${headImg}" alt="" /></a>
        </figure>
        <div class="header-msg">
            ${nikename}给您发送了一个流量红包
        </div>
    </header>

    <section class="main">

        <p class="main-text main-text-center">
            祝您 :${blessword}
        </p>

        <section class="hbbox">
            <img src="plug-in/liuliangbao/css/images/hb02.jpg" alt="" />
        </section>

        <p class="main-msg main-text-red">
            领完了没关系，我们还有一大波免费流量赠送
        </p>

        <div class="main-btn">
            <%--<a href="javascript:;" class="ui-btn ui-btn-3">浪费表情</a>--%>
            <a onclick="makeMore()" href="javascript:;" class="ui-btn ui-btn-4 mlrem08">不放过</a>
        </div>

    </section>
    <input type="hidden" id="accountid" name="accountid" value="${accountid}">
</article>


</body>
</html>
<script>
    function makeMore() {
//        window.location.href = "getRedpacketController.do?NoattentionPublicNum&accountid=" + $('#accountid').val() + "";
        window.location.href = "earnFlowController.do?moreFlow&accountid=${accountid}&openId=${openId}";
    }


</script>