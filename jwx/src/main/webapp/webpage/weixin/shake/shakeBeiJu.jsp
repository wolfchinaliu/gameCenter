<%--
  Created by IntelliJ IDEA.
  User: xuxiaoguai
  Date: 2016/1/22
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta name="format-detection" content="telphone=no, email=no"/>

    <title>悲了个剧</title>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/jquery.min.js"></script>
    <%--<link rel="stylesheet" href="plug-in/liuliangbao/shake/css/myDialog.css">
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/howler.min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/fastclick.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/shake/js/myDialog.js"></script>--%>


    <%--<link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/shake/dev/src/css/yaoyiyao/normalize.css">
    <script src="plug-in/liuliangbao/shake/dev/src/js/lib/common.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0304/css/normalize.css">
    <script src="plug-in/liuliangbao/css/0304/js/common.js"></script>


</head>
<body>
<article class="container">
    <!--页头-->
    <header class="header"></header>

    <section class="main">
        <section class="hbbox">
            <img src="plug-in/liuliangbao/shake/dev/res/images/hb02.jpg" alt="" />
        </section>
        <p class="main-text ptrem02">
            <%--${retrunMessage}--%>根据您的手机号码所在区域，目前没有商家免费赠送流量，真是悲了个剧。小榴会积极的帮您去发展。
        </p>

        <div class="main-btn">
            <%-- <a id="j-btn-cancel" href="javascript:;" class="ui-btn ui-btn-3">浪费表情</a>--%>
            <a id="j-btn-ok" href="javascript:;" class="ui-btn ui-btn-4 mlrem08">走就走</a>
        </div>
    </section>
</article>
</body>
</html>

<script type="text/javascript">
    /*走就走的事件*/
    $("#j-btn-ok").click(function(){
        window.location.href = "earnFlowController.do?moreFlow&accountid=${accountId}&openId=${openid}";
    });

</script>
