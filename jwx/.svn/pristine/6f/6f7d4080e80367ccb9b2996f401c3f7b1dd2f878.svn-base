<%--
  Created by IntelliJ IDEA.
  User: 易维宝
  Date: 2016/1/29
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="UTF-8">

  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
  <meta content="yes" name="apple-mobile-web-app-capable"/>
  <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
  <meta name="format-detection" content="telphone=no, email=no"/>

  <title>中奖纪录</title>
  <link rel="stylesheet" href="plug-in/liuliangbao/shake/css/myDialog.css">
  <script type="text/javascript" src="plug-in/liuliangbao/shake/js/jquery.min.js"></script>
  <%--<script type="text/javascript" src="plug-in/liuliangbao/shake/js/howler.min.js"></script>
  <script type="text/javascript" src="plug-in/liuliangbao/shake/js/fastclick.js"></script>
  <script type="text/javascript" src="plug-in/liuliangbao/shake/js/myDialog.js"></script>--%>

  <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0304/css/normalize.css">
  <script src="plug-in/liuliangbao/css/0304/js/common.js"></script>

</head>
<body>

<article class="container">
    <header class="header header-makered">
      <figure class="photo p140-140">
        <a href="#"><img src="${headImgUrl}" alt="" /></a>
      </figure>
      <ul class="header-msg-makered">
        <li>昵称：${nickname}</li>
        <li>手机号：${phoneNumber}</li>
        <li>省内流量：${provinceFlowValue}M</li>
        <li>全国流量：${countryFlowValue}M</li>
        <p id="hdid" style="display: none">${hdid}</p>
        <p id="accountId" style="display:none">${accountid}</p>
      </ul>
    </header>

  </header>

  <section class="main">

    <section class="main">
      <dl class="redlist">
        <dt>
          <span>昵称</span>
          <span>时间</span>
          <span>摇取数额</span>
        </dt>

        <c:forEach items="${winningrecord}" var="item">
          <dd class="ptrem01" style="font-size: 16px;">
            <span title="某某某">${item.nickname}</span>
            <span>${item.addtime}</span>
            <span>领取${item.prizevalue}M</span>
          </dd>
        </c:forEach>
      </dl>
    </section>
  </section>
</article>
</body>
</html>
