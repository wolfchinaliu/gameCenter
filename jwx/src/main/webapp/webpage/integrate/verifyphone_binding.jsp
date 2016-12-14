<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>验证手机</title>
    <link rel="stylesheet" type="text/css" href="plug-in/integrate/css/normalize.css">
    <script src="plug-in/liuliangbao/css/0304/js/common.js"></script>
</head>
<body>
<div class="hidden" hidden>
    <input id="businessKey" type="text" hidden="hidden" value="${businessKey}">
    <input id="accountName" type="text" hidden="hidden" value="${accountName}">
</div>
<article class="container">
    <!--页头-->
    <header class="header-valid-f">
        <div class="clx">
            <img class="logo fr" src="static/img/logo.png" alt="">
        </div>
        <div class="">
            <img class="logo-f" src="${accountHeadImg }" alt="">
        </div>
    </header>
    <section class="main">
        <div class="valid-box">
        <c:if test="${operate=='wx_userReceive_receive' }">
        	<c:if test="${flowType =='1' }">
	        	<p class="td-24 cl-333"> 恭喜您参与${accountName }${activityName }活动，获得全国流量<span class="cl-red">${flowValue }M</span>，为保证流量能顺利的达到您的手机，需要对您的手机进行验证，请输入您接收到的短信验证码，即可领取。</p>
	            <p>验证手机号码后，可参与${accountName }更多赠送流量活动。</p>
        	</c:if>
            <c:if test="${flowType =='2' }">
	        	<p class="td-24 cl-333"> 恭喜您参与${accountName }${activityName }活动，获得省内流量<span class="cl-red">${flowValue }M</span>，为保证流量能顺利的到达您的手机，需要对您的手机进行验证，请输入您接收到的短信验证码，即可领取。</p>
	            <p>验证手机号码后，可参与${accountName }更多赠送流量活动。</p>
        	</c:if>
        </c:if>
        <c:if test="${operate=='wx_userPay_pay' }">
        	<p class="td-24 cl-333"> 您还未开通拾流流量银行账户，开通账户后，您在拾流商盟平台下所有商家都能获赠免费流量，并统一计入您的账户下，供使用和消费。</p>
        </c:if>	
        </div>
        <section class="form form-home" style="margin: 1rem auto;">
            <form method="post" action="#">
                <ul class="form-list form-list-valid">
                    <li class="clx mb-2">
                        <label class="label fl bg-ccc"><img src="static/img/phone.png" alt=""></label>
                        <input id="phoneNumber" type="text" class="text fl bg-ccc" placeholder="请输入手机号码" value="${phoneNumber }"/>
                    </li>
                    <li class="clx mb-2">
                        <label class="label fl bg-ccc"><img src="static/img/pass.png" alt=""></label>
                        <input id="captcha" type="text" class="text-f fl bg-ccc" placeholder="请输入验证码" value=""/>
                        <a class="code fr jq-validate" href="javascript:;">发送验证码</a>
                    </li>
                    <!-- <li class="clx warn show">
                        <label class="label fl"><img src="static/img/icon_warn.png" alt=""></label>
                        <p>您输入的验证码不正确，请重新输入</p>
                    </li> -->
                </ul>
                <div class="form-btn">
                    <a href="javascript:;" class="btn-default jq-bind">提交</a>
                </div>
            </form>
        </section>
    </section>
</article>
<script src="plug-in/liuliangbao/css/0304/js/lib/jquery-1.10.1.min.js"></script>
<script src="plug-in/liuliangbao/css/0304/js/lib/util.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/css/0304/js/zepto/zepto-all-min.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/css/0304/js/zepto/car-popup.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('.jq-validate').on('click',function(){
            if( $(this).prop('disabled')==true){
                return false;
            }
            var phoneNumber=$("#phoneNumber").val()
            var myreg = /^(((106)|(13[0-9]{1})|(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\d{8})$/;
            if(!myreg.test(phoneNumber))
            {
                $.mobTips('请输入有效的手机号码！');
                return false;
            }else {
                if(!!!this.interval) {
                    $(this).trigger('countDown');
                }
                var param = {"phoneNumber": phoneNumber};
                $.ajax({
                    type: "POST",
                    url: "bindingController.do?sendMessage",
                    dataType: "JSON",
                    async: false,
                    data: param,
                    success: function (data) {
                        if (data.flag == "true") {
                            $.mobTips('验证码发送成功,请注意查收!');
                        } else {
                            $.mobTips('验证码发送失败，请稍后重试！');
                        }
                    }, error: function (error) {
                        $.mobTips('验证码发送失败，请稍后重试！');
                    },
                });
            }
        }).bind('countDown', function() {
            var that = this;
            $(that).prop('disabled', true);
            that.count = 60;
            $(that).text('请在' + that.count + '秒内输入');
            that.interval = window.setInterval(function() {
                if(that.count == 1) {
                    window.clearInterval(that.interval);
                    delete that.interval;
                    $(that).prop('disabled', false).text('发送验证码');
                    return;
                } else {
                    that.count--;
                    $(that).text('请在' + that.count + '秒内输入');
                }
            }, 1000);
        });
        $('.jq-bind').on('click',function(){
            var myreg = /^(((106)|(13[0-9]{1})|(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\d{8})$/;
            var phoneNumber=$("#phoneNumber").val();
            if(!myreg.test(phoneNumber))
            {
                $.mobTips('请输入有效的手机号码！');
                return false;
            }else{
                    var phoneNumber=$("#phoneNumber").val();
                    var captcha=$("#captcha").val();
                    var businessKey=$("#businessKey").val();
                    $.ajax({
                        type: "POST",
                        url: "integrate.do?${bindgingUrl}",
                        dataType: "JSON",
                        async: false,
                        data: {
                            "businessKey": businessKey,
                            "captcha": captcha,
                            "phoneNumber": phoneNumber
                        },
                        success: function (data) {
                            if(data.flag==true){
                                window.setTimeout(function(){
                                    window.location.href = "integrate.do?${operate}&businessKey=${businessKey}";
                                },1500);
                            }else{
                                $.mobTips(data.msg);
                            }
                        }, error: function (error) {
                            $.mobTips('手机验证失败,请稍后重试！');
                        }
                    });
                }
        });
    });
</script>
</body>
</html>