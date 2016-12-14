<%@ page import="org.apache.commons.lang.ObjectUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
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
    <title>签到</title>

    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0304/css/normalize.css">
    <script src="plug-in/liuliangbao/css/0304/js/common.js"></script>
</head>
<body>
<article class="container">
    <!--页头-->
    <header class="header header-sign">
        <figure class="photo p140-140">
            <a href="#"><img src="${headImgUrl}" alt="" /></a>
        </figure>
    </header>

    <section class="main">

        <div class="sign-user">
            <p>昵称：${nickName}</p>
            <p>${province}${city}</p>
        </div>

        <ul class="sign-list clx">
            <li>
                <p>手机号码：</p>
                <p><%=ObjectUtils.defaultIfNull(request.getAttribute("phoneNumber"), "尚未绑定&nbsp;&nbsp;")%></p>
            </li>
            <li>
                <p>全国流量：</p>
                <p>${countryFlow}M</p>
            </li>
            <li>
                <p>省内流量：</p>
                <p>${provinceFlow}M</p>
            </li>
        </ul>

        <div class="sign-btn">
            <a href="javascript:;" id="j-btn-make" class="ui-btn ui-btn-2 jq-signIn">签到领取${merchantInfoBean.data.get(0).countryFlowValue}M流量</a>
            <p>（本公众号赠送流量适合${merchantInfoBean.data.get(0).province}${businessArea}手机号码）</p>
        </div>

    </section>

</article>
<script src="plug-in/liuliangbao/css/0304/js/lib/jquery-1.10.1.min.js"></script>
<script src="plug-in/liuliangbao/css/0304/js/lib/util.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/css/0304/js/zepto/zepto-all-min.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/css/0304/js/zepto/car-popup.js"></script>
<script type="text/javascript">
    var dialog = null;
    $(document).ready(function(){
        $('.jq-signIn').on('click',function(){
            sign();
        });
    });

    function bindPhoneNumber() {
        var url ='bindingController.do?redirectBinding' ;
        url += "&openId=${openId}";
        url += "&merchantId=${accountid}";
        url += "&nickName=${nickName}";
        url += "&phoneNumber=${phoneNumber}";
        url += "&operateType=签到";
        window.location.href = url;
    }

    function sign() {
        $.ajax({
            type: 'POST',
            url: 'signController.do?sign',
            dataType: 'JSON',
            data: {
                "openId": '${openId}',
                "accountid": '${accountid}',
                "nickname": '${nickName}',
                "phoneNumber": '${phoneNumber}'
            },
            cache: false,
            error: function () {
                $.mobTips("签到失败，请重试！");
                return false;
            },
            success: function (data) {
                if (data.flag == false) {
                    dialog = Zepto('body').popup({
                        title:'提示'
                        ,message: data.msg
                        ,id:'pop-2'
                        ,ok:"去领取"
                        ,closeOnOk: true
                        ,onOk:function(){
                            if ('${phoneNumber}' == '') {
                                bindPhoneNumber();
                            } else {
                                window.location.href = "userChargeController.do?userChargeView&accountid=${accountid}&openId=${openId}";
                            }
                        }
                    });
                    return;
                }
                dialog = Zepto('body').popup({
                    title:'提示'
                    ,message:'<p>恭喜您获得${merchantInfoBean.data.get(0).countryFlowValue}M${merchantInfoBean.data.get(0).province}${businessArea}省内流量。</p>'
                    ,id:'pop-2'
                    ,ok:"去充值"
                    ,closeOnOk: false
                    ,onOk:function(){
                        if ('${phoneNumber}' == '') {
                            bindPhoneNumber();
                        } else {
                            window.location.href = "userChargeController.do?userChargeView&accountid=${accountid}&openId=${openId}";
                        }
                    }
                });
            }
        });
    }
</script>
</body>

</html>