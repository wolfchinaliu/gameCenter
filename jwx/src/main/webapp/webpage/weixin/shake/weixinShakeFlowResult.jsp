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

    <title>摇流量</title>
    <link rel="stylesheet" href="plug-in/liuliangbao/shake/css/myDialog.css">
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/jquery.min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/howler.min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/fastclick.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/myDialog.js"></script>

    <%--<link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/shake/dev/src/css/yaoyiyao/normalize.css">
    <script src="plug-in/liuliangbao/shake/dev/src/js/lib/common.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0304/css/normalize.css">
    <script src="plug-in/liuliangbao/css/0304/js/common.js"></script>


</head>

<body>
<article class="container">
    <!--页头-->
    <header class="header header-makered">
        <figure class="photo p140-140">
            <a href="#"><img src="${headImgUrl}" alt="" /></a>
        </figure>
        <ul class="header-msg-makered">
            <li>昵称：${nickname}</li>
            <li>手机号：${phoneNumber}</li>
            <%--<li>省内流量：${provinceFlowValue}M <span class="plrem02">全国流量：${countryFlowValue}M</span></li>--%>
            <li>省内流量：${provinceFlowValue}M</li>
            <li>全国流量：${countryFlowValue}M</li>
            <p id="hdid" style="display: none">${hdid}</p>
            <p id="accountId" style="display:none">${accountid}</p>
        </ul>
    </header>

    <section class="main">

        <p class="main-text main-text-red mbrem02 t-center">
            <%--此活动仅对江西省手机用户有效--%>向${provinceAccount}的手机用户赠送流量
        </p>
        <figure class="yao-jieguo">
            <ul class="yao-jieguo-list">
                <li>恭喜您摇出<span class="main-text-red" style="font-size: 18px;">${ShakeFlowValue}M</span></li>
                <li>流量,不信您看上方</li>
                <li>您的流量账户.</li>
            </ul>
        </figure>
        <p class="main-text t-center">
            今天还可以摇<span class="main-text-red" style="font-size: 16px;">${leftcount}</span>次
        </p>

    </section>

    <div class="main-btn">
        <a id="j-btn-lookother" class="ui-btn ui-btn-3">看别人摇</a>
        <c:if test="${leftcount >0 }">
        <a id="j-btn-oncemore" href="javascript:;" class="ui-btn ui-btn-4 mlrem08">再摇一次</a>
        </c:if>
        <c:if test="${leftcount == 0 }">
        <a id="j-btn-lq" href="javascript:;" class="ui-btn ui-btn-4 mlrem08">去充值</a>
        </c:if>
    </div>

</article>
<script type="text/javascript">
    $("#j-btn-lookother").click(function(){
        //alert("546546");
       /* var hdid=$("#hdid").text();
        alert(hdid);
        window.location.href = "weixinShakeController.do?shakeRecord&hdId="+hdid;*/
        window.location.href = "weixinShakeController.do?shakeRecord&hdId=${hdid}&phoneNumber=${phoneNumber}";
    });
    /*不放过关注二维码的事件*/
    $("#j-btn-oncemore").click(function(){
        //alert("12");
      /* var hdid=$("#hdid").text();
        window.location.href = "lotteryController.do?startLottery&hdId="+${hdid};
        window.location.href = "earnFlowController.do?moreFlow&accountid=${accountId}&openId=${openid}";
        window.location.href = "lotteryController.do?startLottery&hdId=${hdid}&accountid=${accountId}";*/
        //javascript:history.go(-1);
        window.location.href = "lotteryController.do?startLottery&hdid=${hdid}&openId=${openid}";
    });
    $("#j-btn-lq").click(function(){
        //alert("12");
      /* var hdid=$("#hdid").text();
        window.location.href = "lotteryController.do?startLottery&hdId="+${hdid};
        window.location.href = "earnFlowController.do?moreFlow&accountid=${accountId}&openId=${openid}";
        window.location.href = "lotteryController.do?startLottery&hdId=${hdid}&accountid=${accountId}";*/
        //javascript:history.go(-1);
        window.location.href = "userChargeController.do?userChargeView&accountid=${accountid}&openId=${openId}";
    });

</script>
</body>


</html>
