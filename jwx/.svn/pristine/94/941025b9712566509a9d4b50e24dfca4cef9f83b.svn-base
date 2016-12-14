<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
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
    <title>验证手机</title>
      <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
      <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>
      <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/util.js"></script>
      <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
      <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/car-popup.js"></script>
	  <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/lib/normalize.css?_t=1" />
      <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/phone/index.css">
      <script>
 function onBridgeReady(){
WeixinJSBridge.call('hideOptionMenu');
}

if (typeof WeixinJSBridge == "undefined"){
if( document.addEventListener ){
document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
}else if (document.attachEvent){
document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
}
}else{
onBridgeReady();
} 
</script> 
	</head>
	<body>
      <input id="openid" type="text" hidden="hidden" value="${openId}">
      <input id="accountid" type="text" hidden="hidden" value="${accountid}">
      <input id="nickname" type="text" hidden="hidden" value="${nickName}">
      <input id="operateType" type="text" hidden="hidden" value="${operateType}">
      <input id="accountName" type="text" hidden="hidden" value="${accountName}">
      <input id="reURL" type="text" hidden="hidden" value="${reURL}">
      <input id="shiliuOpenId" type="text" hidden="hidden" value="${shiliuOpenId}">
		<div class="container">
			<header class="flowcard-header">
				<h1 class="flowcard-logo">
					<a href="javascript:;"><img src="${url}" /></a>
					<span>${accountName}</span>
				</h1>
			</header>
			<section class="flowcard-section">
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${accountName}免费赠流量啦！为保证流量顺利到达您的个人账户，请先验证您的手机号码。验证成功后，立刻赠送您 <span class="red">${merchantInfoBean.data.get(0).countryFlowValue}M</span> 关注流量。
				</p>
			</section>
			<div class="form-box">
				<form action="#" method="post">
					<div class="form-item">
						<i class="form-icon form-icon-1"></i>
						<p>
							<input type="text" id="phoneNumber" name="phoneNumber" placeholder="请输入手机号码" value="" class="input-text" />
						</p>
					</div>
					<div class="form-item">
						<i class="form-icon form-icon-2"></i>
						<p>
							<input type="text" id="phoneCode" name="phoneCode" placeholder="请输入验证码" value="" class="input-text" style="width: 3rem;" />
							<input type="button" value="获取验证码" class="from-btn from-btn-1 j-btn1" id="j-btn1" />
						</p>
					</div>
					<div class="form-item">
						<input type="button" value="提交" class="from-btn from-btn-2" id="j-btn2" />
					</div>
					<p class="receive-msg">（本公众号赠送流量适合${merchantInfoBean.data.get(0).province}${businessArea}${areaMerchant}手机号码）</p>
				</form>
			</div>
			
			<footer class="footer">
				<a href="javascript:;"><img src="${cdnHost}/plug-in/liuliangbao/20160701/images/footer/footer.jpg" alt="" /></a>
			</footer>
		</div>
    <script type="text/javascript">
    $(document).ready(function(){
        $('#j-btn1').on('click',function(){
            if( $(this).prop('disabled')==true){
                return false;
            }
            var phoneNumber=document.getElementById("phoneNumber").value;
            var myreg = /^(((106)|(13[0-9]{1})|(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\d{8})$/;
            if(!myreg.test(phoneNumber))
            {
                $.mobTips('验证的手机号码不合法,请再次检查手机号！');
                return false;
            }else {
                if(!this.interval) {
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
                    }
                });
            }

        }).bind('countDown', function() {
            var that = this;
            $(that).prop('disabled', true);
            that.count = 60;
            $(that).val('（' + that.count + 's）');
            that.interval = window.setInterval(function() {
                if(that.count == 1) {
                    window.clearInterval(that.interval);
                    delete that.interval;
                    $(that).prop('disabled', false).val('发送验证码');
                } else {
                    that.count--;
                    $(that).val('（' + that.count + 's）');
                }
            }, 1000);
        });
        $('#j-btn2').on('click',function(){
            var myreg = /^(((106)|(13[0-9]{1})|(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\d{8})$/;
            var phoneNumber=document.getElementById("phoneNumber").value;
            if(!myreg.test(phoneNumber)) {
                $.mobTips('验证的手机号码不合法,请再次检查手机号！');
                return false;
            } else {
                    Zepto('body').popup({
                        title:'手机验证'
                        ,message:'<p>是否确认验证此号码?</p>'
                        ,id:'pop-2'
                        ,ok:"确认"
                        ,onOk:function(){
                            // 确认按钮的回调函数
                            var phoneNumber = document.getElementById("phoneNumber").value;
                            var phoneCode = document.getElementById("phoneCode").value;
                            var openid = document.getElementById("openid").value;
                            var accountid = document.getElementById("accountid").value;
                            var nickname = document.getElementById("nickname").value;
                            var operateType = document.getElementById("operateType").value;
                            var accountName = document.getElementById("accountName").value;
                            var reURL = document.getElementById("reURL").value;
                            var shiliuOpenId  =  document.getElementById("shiliuOpenId").value;
                            $.ajax({
                                type: "post",
                                url: "bindingController.do?binding",
                                dataType: "json",
                                async: false,
                                data: {
                                    "openId": openid,
                                    "accountid": accountid,
                                    "nickname": nickname,
                                    "phoneNumber": phoneNumber,
                                    "phoneCode": phoneCode,
                                    "operateType": operateType,
                                    "accountName": accountName,
                                    "reURL": reURL,
                                    "shiliuOpenId": shiliuOpenId
                                },
                                success: function (data) {
                                    if(data.flag == true){
                                        $.mobTips(data.msg);
                                        window.setTimeout(function(){
                                            if (reURL && reURL.length) {
                                                window.location.href = decodeURIComponent(reURL);
                                            } else {
                                                window.location.href = "mainController.do?load&accountid=${accountid}&openId=${openId}";
                                            }
                                        },2500);
                                    }else{
                                        $.mobTips(data.msg);
                                    }
                                }, error: function (error) {
                                    $.mobTips('手机验证失败,请稍后重试！');
                                }
                            });
                        }
                        ,cancel2:"取消"
                        ,onCancel2: function(){
                            console.log('cancel');
                        }
                    });
                    return false;
            }
        });

    });
</script>
	</body>
</html>
