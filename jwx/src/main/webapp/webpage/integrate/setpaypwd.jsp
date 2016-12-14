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
    <title>设置密码</title>
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
        <div class="">
            <img class="logo-f" src="static/img/logo.png" alt="">
        </div>
    </header>
    <section class="main">
        <div class="valid-box">
	        <p class="td-24 cl-333"> 为确保您的账户安全，请您设置流量提取密码，此密码应用于平台上所有的流量提取：</p>
        </div>
        <section class="form form-home" style="margin: 1rem auto;">
            <form method="post" action="#">
                <ul class="form-list form-list-valid">
                    <li class="clx mb-2">
                        <label class="label fl bg-ccc"><img src="static/img/phone.png" alt=""></label>
                        <input id="pwd" name= "paymentpwd" type="password" class="text fl bg-ccc" placeholder="6个数字" value=""/>
                    </li>
                    <li class="clx mb-2">
                        <label class="label fl bg-ccc"><img src="static/img/phone.png" alt=""></label>
                        <input type="text" id="phoneNumber" name="phoneNumber" value="${phoneNumber}" class="text fl bg-ccc" />
                    </li>
                    <li class="clx mb-2">
                        <label class="label fl bg-ccc"><img src="static/img/phone.png" alt=""></label>
                       <input type="text" id="phoneCode" name="phoneCode" placeholder="请输入验证码" value="" class="text fl bg-ccc" style="width: 3rem;" />
							<input type="button" value="获取验证码" class="from-btn from-btn-1 j-btn1" id="j-btn1" />
                    </li>
                    <!-- <li class="clx warn show">
                        <label class="label fl"><img src="static/img/icon_warn.png" alt=""></label>
                        <p>您输入的验证码不正确，请重新输入</p>
                    </li> -->
                </ul>
                <div class="form-btn">
                    <a href="javascript:;" class="btn-default jq-bind" id="j-btn2">提交</a>
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
        $('.jq-bind').on('click',function(){
        	var pwd=$('#pwd').val();
        	
    		var patrn= /^([0-9a-zA-Z]){6}$/;
   			if (!patrn.exec(pwd)){
   				alert("密码错误!只能输入6位密码");
   				return false;
   			}
   	            var myreg = /^(((106)|(13[0-9]{1})|(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\d{8})$/;
   	            var phoneNumber=document.getElementById("phoneNumber").value;
   	            if(!myreg.test(phoneNumber)) {
   	                $.mobTips('验证的手机号码不合法,请再次检查手机号！');
   	                return false;
   	            } else {
   	            	var phoneCodes = document.getElementById("phoneCode").value;
   	            	var phoneNumber = document.getElementById("phoneNumber").value;
   	            	var paymentpwd = document.getElementById("pwd").value;
   	          $.ajax({
   	           type : "post",// 请求方式
   	           dataType : "json",
   	           url : "integrate.do?appSetPayPwd",// 发送请求地址
   	           data:{phoneCode:phoneCodes,phoneNumber:phoneNumber,pwd:paymentpwd},
   	        success: function (data) {
                if(data.flag == true){
                    $.mobTips(data.msg);
                    window.setTimeout(function(){
                       
                            window.location.href = "integrate.do?goAppUserCharge&acctId=${acctId}&data=${userData}";
                        
                    },2500);
                }else{
                    $.mobTips(data.msg);
                }
            }, error: function (error) {
                $.mobTips('手机验证失败,请稍后重试！');
            }
   	       });

   	     
   	                    return false;
   	            }
   	       
        });
    });
</script>
</body>
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
        

    });
</script>
</html>