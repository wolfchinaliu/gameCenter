<%--
  Created by IntelliJ IDEA.
  User: xuxiaoguai
  Date: 2016/1/22
  Time: 16:55
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

    <title>摇一摇</title>
    <%--<link rel="stylesheet" href="plug-in/liuliangbao/shake/css/myDialog.css">
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/jquery.min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/howler.min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/fastclick.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/myDialog.js"></script>

    <script src="plug-in/liuliangbao/js/1218/jquery-1.10.1.min.js"></script>
    <script src="plug-in/liuliangbao/js/1218/jquery.slides.min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/js/0104/jQueryRotate.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/js/0104/util.js"></script>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0104/style.css">--%>
    <link rel="stylesheet" href="plug-in/liuliangbao/shake/css/myDialog.css">
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/jquery.min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/howler.min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/fastclick.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/myDialog.js"></script>

    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/shake/dev/src/css/yaoyiyao/normalize.css">
    <script src="plug-in/liuliangbao/shake/dev/src/js/lib/common.js"></script>

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
        <%--<figure class="yao-jieguo">
            <ul class="yao-jieguo-list">
                <li>恭喜您摇出<span class="main-text-red">X.XM</span></li>
                <li>流量,不信您看上方</li>
                <li>您的流量账户.</li>
            </ul>
        </figure>--%>
        <figure class="yao-img">
            <img src="plug-in/liuliangbao/shake/dev/res/images/yaoyiyao/img01.png" alt="" />
        </figure>
        <p class="main-text t-center">
            今天还可以摇<span class="main-text-red">${leftcount}</span>次
        </p>

    </section>

    <div class="main-btn">
        <a onclick="LookOthers()" href="javascript:;" class="ui-btn ui-btn-3">看别人摇</a>
        <a onclick="ShakeOnceMore()" href="javascript:;" class="ui-btn ui-btn-4 mlrem08">再摇一次</a>
    </div>

</article>

<script type="text/javascript" src="plug-in/liuliangbao/shake/dev/src/js/zepto/zepto-all-min.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/shake/dev/src/js/zepto/car-popup.js"></script>

<script>
    Zepto('body').popup({
        title: '提示'
        , message: '<h2>尊敬的用户您好:</h2><p>虽然摇流量的次数用完了，不过我们还有大把的免费流量等着你来拿</p>'
        , id: 'pop-2'
        , ok: "不拿白不拿"
        , onOk: function () {
            // 确认按钮的回调函数
            window.location.href = "earnFlowController.do?moreFlow&accountid=${accountId}&openId=${openid}";
            console.log('ok');
        }
        , cnacel2: "爷是壕"
        , onCancel2: function () {
            console.log('cancel');
        }
    });

</script>

</body>


<script type="text/javascript">
    function LookOthers() {
        var hdid=$("#hdid").text();
        window.location.href = "weixinShakeController.do?shakeRecord&hdId="+hdid;
    }
    function ShakeOnceMore() {
        javascript:history.go(-1);
    }
</script>
</html>
