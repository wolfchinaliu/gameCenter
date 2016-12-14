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
    <title>手机验证</title>
    <link rel="stylesheet" type="text/css" href="plug-in/integrate/css/normalize.css">
	<script src="plug-in/liuliangbao/css/0304/js/lib/jquery-1.10.1.min.js"></script>
	<script src="plug-in/liuliangbao/css/0304/js/common.js"></script>
	<script src="plug-in/liuliangbao/css/0304/js/lib/util.js"></script>
	<script type="text/javascript"
		src="plug-in/liuliangbao/css/0304/js/zepto/zepto-all-min.js"></script>
	<script type="text/javascript"
		src="plug-in/liuliangbao/css/0304/js/zepto/car-popup.js"></script>

</head>
<body>

	<div >
	 <ul class="form-list form-list-valid">
	 <li >
	 	<label>商户id</label><input id="acctId" style="width:520px;" type="text" value="297eae2354943d13015494527b6c0001" />
	 </li>
	 <li >
	 	<label>openid</label><input id="openId" style="width:520px;" type="text"	value="95GvwLcFAAtJlb46L_GOW5fyPc"/>
	 </li>
	 <li >
	 	<label>手机号</label><input  id="phoneNumber" style="width:520px;" type="text" value="15233750636" />
	 </li>
	 <li >
	 	<label>活动id</label><input id="activityId" style="width:520px;" type="text" value="297eae2355436f1b01554373f2810001"/> 
	 </li>
	 <li >
	 	<label>密钥</label><input  id="secret" style="width:520px;" type="text" value="4028b881" />
	 </li>
	 <li >
	 	<label>流量类型:</label>
	 	<select id="flowType">
	 	<option value="national">全国流量</option>
	 	<option value="provincial">省内流量</option>
	 	</select>
	 </li>
	 <li >
	 	<label>流量值</label><input id="flowValue" style="width:520px;" type="text" value="20"/>
	 </li>
	 <li>
	 	<label>接口</label>
	 	<select id="interfaceName">
	 	<option value="wx_userReceive_getBusinessKey">商家支出用户获取（公众号）</option>
	 	<option value="wx_userPay_getBusinessKey">商家获取用户支出（公众号）</option>
	 	<option value="app_userReceive_getBusinessKey">商家支出用户获取（APP）</option>
	 	<option value="app_userPay_getBusinessKey">商家获取用户支出（APP）</option>
	 	</select>
	 </li>
	 </ul>
	</div>
	<div class="form-btn">
		<a href="javascript:;" class="btn-default jq-commit">提交</a>
	</div>
<script type="text/javascript">
    $(document).ready(function(){
        $('.jq-commit').on('click',function(){
        	var param = {};
        	param.acctId=$("#acctId").val();
        	param.openId=$("#openId").val();
        	param.secret=$("#secret").val();
        	param.activityId=$("#activityId").val();
        	param.phoneNumber=$("#phoneNumber").val();
        	param.flowType=$("#flowType").val();
        	param.flowValue=$("#flowValue").val();
            param.interfaceName=$("#interfaceName").val();

            $.ajax({
                type: "post",
                url: "integrate.do?test",
                dataType: "JSON",
                async: false,
                data: param,
                success: function (data) {
                    if(data.resultCode==0){
                        $.mobTips(data.resultMsg);
                        window.setTimeout(function(){
                            window.location.href = "http://localhost/jwx/integrate.do?"+data.u+"&businessKey="+data.businessKey;
                        },2500);
                    }else{
                        $.mobTips(data.resultMsg);
                    }
                }, error: function (error) {
                    alert('失败');
                }
            });
        });
    });
</script>
</body>
</html>