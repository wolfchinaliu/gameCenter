<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>订单支付</title>
	<link rel="stylesheet" href="shop/css/wap/index.css" type="text/css" />
	<script type="text/javascript" src="webpage/shop/jsAddress.js"></script>
	<script type="text/javascript" src="shop/js/jquery-1.7.1.js"></script>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0,minimum-scale=1.0, maximum-scale=1.0">
	<meta content="telephone=no" name="format-detection" />
	<style type="text/css">
	p{padding-top: 10px;padding-left: 5px;}
	input{height: 30px;vertical-align: middle;width:100%}

	</style>
</head>
<body id="body_id">

	<nav class="topBar">

		<div class="l">

			<a class="arrow" href="shopController.do?orders"></a>

		</div>

		<h1>
			订单支付
		</h1>

	</nav>
	
	<form action="" method="post" > 		
		
		<p>订单编号：${weixinOrderEntity.orderNo }</p>
		<p>付款金额：${weixinOrderEntity.orderAmount }</p>
		<p>支付方式：微信支付</p>
		<br>
		<div class="go-bug">
		<a href="#" onclick="toCheckForm()" id="test">确认付款</a></div>
	</form>
</body>
</html>
<script type="text/javascript">  
    var basePath = "<%=basePath%>";  
    $("#test").one("click",function(){
    	
        $.ajax({
            url:"shopController.do?doPay&orderId=${weixinOrderEntity.id }"            //<span style="font-family:微软雅黑;">ajax调用微信统一接口获取prepayId</span>  
        }).done(function(data){ 
        	
            var objdata = eval('(' + data + ')');
            if(parseInt(objdata.agent)<5){  
                alert("您的微信版本低于5.0无法使用微信支付");  
                return;
            }
            if (typeof WeixinJSBridge == "undefined"){
         	   if( document.addEventListener ){
         	       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
         	   }else if (document.attachEvent){
         	       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
         	       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
         	   }
         	}else{
         	   onBridgeReady(objdata);
         	}
        });

        function onBridgeReady(objdata){
        	var obj=JSON.parse(objdata);
        	   WeixinJSBridge.invoke(
        	       'getBrandWCPayRequest', {
        	    	   "appId" : obj.appId,                  //公众号名称，由商户传入  
                       "timeStamp":obj.timeStamp,          //时间戳，自 1970 年以来的秒数  
                       "nonceStr" : obj.nonceStr,         //随机串  
                       "package" : obj.packageValue,      //商品包信息
                       "signType" : obj.signType,        //微信签名方式:  
                       "paySign" : obj.paySign           //微信签名  
        	       },
        	       function(res){
                       if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                       	$.ajax({
                       		url: "shopController.do?payOrder&orderId=${weixinOrderEntity.id}",
                               success: function (xhr) {
                               },
                               error: function (xhr) {
                               }
                       	});
                       	alert("订单支付成功!");
                           window.location.href="shopController.do?orders";  
                       }else{
                           alert("订单支付失败！");  
                           window.location.href="shopController.do?orders";     
                           //当失败后，继续跳转该支付页面让用户可以继续付款，贴别注意不能直接调转jsp，不然会报 system:access_denied 
                       }  
                   }
        	   ); 
        	}
    });
</script>