<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- 优先使用最新版本 IE 和 Chrome -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta name="format-detection" content="telphone=no, email=no" />
<title>输入交易密码</title>
<link rel="stylesheet" type="text/css"
	href="plug-in/integrate/css/normalize.css">
<script src="plug-in/liuliangbao/css/0304/js/common.js"></script>

</head>
<body>
	<input id="provinceFlowValueid" type="hidden"
		value="${provinceFlowValue}">
	<input id="countryFlowValueid" type="hidden"
		value="${countryFlowValue}">
	<input type="hidden" id="phoneNumber" name="phoneNumber"
		value="${phoneNumber}" readonly="true">
	<input id="businessCodeid" type="hidden" value="${businessCode}">
	<input id="provinceCodeid" type="hidden" value="${provinceCode}">
	<input id="accountId" type="hidden" value="${accountId}">
	<input id="strFlowValue" type="hidden" value="${flowValue}">

	<input id="flowvalueresult" type="hidden" value="">
	<input id="flowtyperesult" type="hidden" value="1">
	<div class="hidden" hidden>
		<input id="businessKey" type="text" hidden="hidden"
			value="${businessKey}"> <input id="accountName" type="text"
			hidden="hidden" value="${accountName}">
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
				<c:if test="${flowType =='1' }">
					<p class="td-24 cl-333">您本次交易支付全国流量${flowValue }M。请输入交易密码进行确认</p>
				</c:if>
				<c:if test="${flowType =='2' }">
					<p class="td-24 cl-333">您本次交易支付省内流量${flowValue }M。请输入交易密码进行确认</p>
				</c:if>
				<p class="td-24 cl-333"></p>
			</div>
			<section class="form form-home" style="margin: 1rem auto;">
				<form method="post" action="">
					<ul class="form-list form-list-valid">
						<li class="clx mb-2"><label class="label fl bg-ccc"><img
								src="static/img/phone.png" alt=""></label> <input id="pwd"
							name="pwd" type="password" class="text fl bg-ccc"
							placeholder="请输入支付密码" value="" /></li><span><a href="integrate.do?findPwd" target="_blank" class="ui-btn">忘记密码？</a></span>
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
	<script type="text/javascript"
		src="plug-in/liuliangbao/css/0304/js/zepto/zepto-all-min.js"></script>
	<script type="text/javascript"
		src="plug-in/liuliangbao/css/0304/js/zepto/car-popup.js"></script>
	<script type="text/javascript">
    $(document).ready(function(){
        $('.jq-bind').on('click',function(){
        
        	var pwd=$('#pwd').val();
        	 var getFlowValue = document.getElementById("strFlowValue").value;
        	
             var str1 = document.getElementById("provinceFlowValueid").value;
         
             var str2 = document.getElementById("countryFlowValueid").value;
             var str3 = document.getElementById("phoneNumber").value;
             var str4 = document.getElementById("businessCodeid").value;
             var str5 = document.getElementById("provinceCodeid").value;

             var FlowType = document.getElementById("flowtyperesult").value;
             
    		var patrn= /^([0-9a-zA-Z]){6,10}$/;
   			if (!patrn.exec(pwd)){
   				alert("密码错误!请输入6-10位密码");
   				return false;
   			}
            $.ajax({
                type: "POST",
                url: "integrate.do?appUserGetTrueFlow",
                dataType: "JSON",
                async: false,
                data: {
                	"pwd":pwd,
                	"provinceFlowValue": str1,
                    "countryFlowValue": str2,
                    "flowTrueValue": getFlowValue,
                    "phoneNumber": str3,
                    "businessCode": str4,
                    "provinceCode": str5,
                    "flowType": FlowType
                },
                success: function (data) {
                    if(data.flag==true){
                        $.mobTips(data.msg);
                    }else{
                        $.mobTips(data.msg);
                    }
                }, error: function (error) {
                    $.mobTips('密码错误，请重新输入');
                }
            });
        });
    });
</script>
</body>
</html>