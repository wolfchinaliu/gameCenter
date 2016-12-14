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
    <title>流量中心</title>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0314/css/normalize.css">
    <script src="plug-in/liuliangbao/css/0314/js/common.js"></script>
</head>
<body>
<article class="container">
    <!--页头-->
    <header class="header header-photo header-ll">
        <figure class="photo p140-140">
            <a href="#"><img src="${headimgUrl}" alt="" /></a>
        </figure>
        <ul class="header-user-msg">
            <li>${nickname}</li>
            <li>${phoneNumber}</li>
        </ul>
    </header>

    <section>
        <input id="provinceValue" type="hidden" value="${provinceFlowValue}">
        <input id="countryValue" type="hidden" value="${countryFlowValue}">
        <ul class="liuliang-btn-list clx">
            <li onclick="goFlowGetRecord()">
                <a href="#"><img src="plug-in/liuliangbao/css/0314/images/btn01.png" alt="流量银行" /></a>
                <p class="ll1">省内流量：${provinceFlowValue}Mb</p>
                <p class="ll2">全国流量：${countryFlowValue}Mb</p>
            </li>
            <li onclick="goGetFlow()"><a href="#"><img src="plug-in/liuliangbao/css/0314/images/btn02.png" alt="领取流量" /></a></li>
            <li onclick="BackOne()"><a href="#"><img src="plug-in/liuliangbao/css/0314/images/btn03.png" alt="我的优惠卷" /></a></li>
            <li onclick="goMakeMoreFlow()"><a href="#"><img src="plug-in/liuliangbao/css/0314/images/btn04.png" alt="更多免费流量" /></a></li>
            <li onclick="BackOne()"><a href="#"><img src="plug-in/liuliangbao/css/0314/images/btn05.png" alt="更多优惠卷领取" /></a></li>
            <li onclick="BackOne()"><a href="#"><img src="plug-in/liuliangbao/css/0314/images/btn06.png" alt="敬请期待" /></a></li>
        </ul>

    </section>

</article>


</body>
</html>

<script src="plug-in/liuliangbao/css/0304/js/lib/jquery-1.10.1.min.js"></script>
<script src="plug-in/liuliangbao/css/0304/js/lib/util.js"></script>
<script type="text/javascript">
    /*领取流量事件*/
    function goGetFlow() {
        window.location.href = "userChargeController.do?userChargeView&accountid=${accountId}&openId=${openid}";
    }
    function goMakeMoreFlow() {
        /*赚取更多流量*/
        window.location.href = "earnFlowController.do?moreFlow&accountid=${accountId}&openId=${openid}";
    }
    /*流量记录-获取用户流量的领取记录和充值记录事件*/
    function goFlowGetRecord() {
        var provinceValue = $("#provinceValue").val();
        var countryValue = $("#countryValue").val();
        $.ajax({
            type: 'POST',
            url: "personFlowCenterController.do?goPersonFlowRecord",
            dataType: 'json',
            data: {
                "nickname1": '${nickname}',
                "provinceFlowValue": provinceValue,
                "countryFlowValue": countryValue
            },
            cache: false,
            error: function (error) {
                $.mobTips(error);
            },
            success: function (data) {
                window.location.href = "userFlowRecordController.do?goUserFlowRecord";
            }
        });
    }

    function BackOne() {
        $.mobTips("此功能暂未开放，敬请期待！");
    }

</script>
