<%@ taglib prefix="t" uri="/easyui-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,DatePicker"></t:base>
<%--<t:base type="jquery,easyui,tools,DatePicker"></t:base>--%>
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
    <title>猜灯谜拿流量</title>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0218/zimi/normalize.css">
    <script src="plug-in/liuliangbao/js/0218/lib/common.js"></script>
</head>
<body>
<article class="container">
    <!--页头-->
    <header class="header header-makered">
        <figure class="photo p140-140">
            <a href="#"><img src="${memberEntity.headImgUrl}" alt=""/></a>
        </figure>
        <ul class="header-msg-makered">
            <li>昵称：${memberEntity.nickName}</li>
            <li>手机号：${memberEntity.phoneNumber}</li>
            <li>省内流量：${provinceFlowValue}M </li>
            <li>全国流量：${countryFlowValue}M</li>
        </ul>
    </header>

    <section class="main pbrem03">

        <p class="main-text main-text-red mbrem02 t-center">
            此活动仅对${provinceAccount}手机用户有效
        </p>

        <section class="zimi-form">
            <header class="zimi-form-header">
                <ul class="zimi-form-header-list" id="j-zimi-header">
                    <c:forEach begin="1" end="${totalRiddles}" var="ite">
                        <li>${ite}/${totalRiddles}题</li>
                    </c:forEach>
                </ul>
            </header>
            <div class="zimi-content">
                <div class="zimi-box" id="j-zimi-box">
                    <!--一个谜题-->
                    <c:forEach var="item" items="${lisRiddles}">
                        <div class="zimi-item">
                            <dl class="zimi-content-list">
                                <dt>谜面：</dt>
                                <dd>${item.lanternRon}</dd>
                                <dt>谜目：</dt>
                                <dd>${item.lanternReyes}</dd>
                            </dl>
                            <div class="zimi-content-box">
                                <input type="text" class="ui-text" value="${item.lanternRdown}" readonly="readonly"/>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="zimi-content-btn pbrem03">
                    <a id="j-btn-prev" href="javascript:;" class="ui-btn ui-btn-3" style="margin-right: 0.6rem;">上一题</a>
                    <a id="j-btn-next" href="javascript:;" class="ui-btn ui-btn-4">下一题</a>
                </div>

            </div>
        </section>


    </section>

</article>
<script type="text/javascript" src="plug-in/liuliangbao/js/0218/zepto/zepto-all-min.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/js/0218/zepto/car-popup.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/js/0218/zimi/index.js"></script>
<input type="hidden" name="openId" id="openId" value="${memberEntity.openId}">
<input type="hidden" name="phoneNumber" id="phoneNumber" value="${memberEntity.phoneNumber}">
<input type="hidden" name="nickName" id="nickName" value="${memberEntity.nickName}">
<input type="hidden" name="hdid" id="hdid" value="${hdEntity.id}">
<input type="hidden" name="accountid" id="accountid" value="${hdEntity.accountid}">
<input type="hidden" name="everyFlow" id="everyFlow" value="${everyFlow}">
<input type="hidden" name="totalRiddles" id="totalRiddles" value="${totalRiddles}">
<input type="hidden" name="provinceAccount" id="provinceAccount" value="${provinceAccount}">
</body>
</html>
