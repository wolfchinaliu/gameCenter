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
	<form action="https://api.mch.weixin.qq.com/pay/unifiedorder" method="POST" id="subform" name="subform">
		<input type="text" id="appid" name="appid" value="${wxPayBean.appid }">
		<input type="text" id="attach" name="attach" value="${wxPayBean.attach}">
		<input type="text" id="body" name="body" value="${wxPayBean.body }">
		<input type="text" id="device_info" name="device_info" value="${wxPayBean.device_info }">
		<input type="text" id="mch_id" name="mch_id" value="${wxPayBean.mch_id }">		
		<input type="text" id="nonce_str" name="nonce_str" value="${wxPayBean.nonce_str }">
		<input type="text" id="notify_url" name="notify_url" value="${wxPayBean.notify_url }">
		<input type="text" id="openid" name="openid" value="${wxPayBean.openid }">
		<input type="text" id="out_trade_no" name="out_trade_no" value="${wxPayBean.out_trade_no }">
		<input type="text" id="spbill_create_ip" name="spbill_create_ip" value="${wxPayBean.spbill_create_ip }">
		<input type="text" id="total_fee" name="total_fee" value="${wxPayBean.total_fee }">
		<input type="text" id="trade_type" name="trade_type" value="${wxPayBean.trade_type }">
		<input type="text" id="sign" name="sign" value="${wxPayBean.sign }">
		
		
		
		
		<input type="text" id="detail" name="detail" value="">
		<input type="text" id="fee_type" name="fee_type" value="">
		<input type="text" id="time_start" name="time_start" value="">
		<input type="text" id="time_expire" name="time_expire" value="">
		<input type="text" id="goods_tag" name="goods_tag" value="">
		<input type="text" id="product_id" name="product_id" value="">
		<input type="text" id="limit_pay" name="limit_pay" value="">
		
		
		<p>签名:${stringSign }</p>
		<p>订单编号：${weixinOrderEntity.orderNo }</p>
		<p>付款金额：${weixinOrderEntity.orderAmount }</p>
		<p>支付方式：微信支付</p>
		<br>
		<div class="go-bug"><a href="#" onclick="toCheckForm()">确认付款</a></div>
		
	</form>
</body>
</html>

 <script type="text/javascript">
	function toCheckForm(){
				
		document.getElementById('subform').submit();
	
	}
</script>