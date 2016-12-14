<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>订单确认</title>
	<link rel="stylesheet" href="shop/css/wap/index.css" type="text/css" />
	<script type="text/javascript" src="webpage/shop/jsAddress.js"></script>
	<script type="text/javascript" src="shop/js/jquery-1.7.1.js"></script>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0,minimum-scale=1.0, maximum-scale=1.0">
	<meta content="telephone=no" name="format-detection" />
	<style type="text/css">
	p{padding-top: 10px;padding-left: 5px;}
	input{height: 30px;}
	select{height: 35px;}
	</style>
</head>
<body id="body_id">

	<nav class="topBar">

		<div class="l">

			<a class="arrow" href="javascript:history.go(-1);"></a>

		</div>

		<h1>
			订单确认
		</h1>

	</nav>
	<form action="shopController.do?createOrder" method="post" id="subform" name="subform">
		<input type="hidden" id="amount" name="amount" value="${amount }">
		<input type="hidden" id="expressPrice" name="expressPrice" value="${expressPrice }">
		<input type="hidden" id="totalAmount" name="totalAmount" value="${totalAmount }">
		<input type="hidden" id="cartids" name="cartids" value="${cartids }">
	
	
	
	<c:if test="${not empty weixinShopAddressList}">
		<c:forEach items="${weixinShopAddressList}" var="weixinShopAddress">
			<input type="radio" name="addressId" value="${weixinShopAddress.id}"> ${weixinShopAddress.province}${weixinShopAddress.city}${weixinShopAddress.district} ${weixinShopAddress.address}
			${weixinShopAddress.deliveryName} ${weixinShopAddress.deliveryPhone}
		</c:forEach>		
	</c:if>
	<c:if test="${empty weixinShopAddressList}">
		
		<p>收货地址：<br><select id="province" name="province" style="width: 100px"></select>
		<select id="city" name="city" style="width: 100px"></select>
		<select id="district" name="district" style="width: 100px"></select></p>
		
		<p>详细街道：<br><input type="text" id="address" name="address" style="width:300px"></p>
		<p>收货人：&nbsp;&nbsp;&nbsp;<br><input type="text" id="deliveryName" name="deliveryName" style="width:300px"></p>
		<p>手机号码：<br><input type="text" id="deliveryPhone" name="deliveryPhone" style="width:300px"></p>
		<p>留言：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><input type="text" id="leaveWord" name="leaveWord" style="width:300px"></p>
	</c:if>
	
	
	<p>购买商品:</p>
	<p><c:forEach items="${weixinShopCartList}" var="weixinShopCart">
		 <img src="${mediaUrlPrefix }/${weixinShopCart.weixinShopGoodsEntity.titleImg}" width="50px" height="50px"> 
	</c:forEach>
	</p>
	<p>商品金额：${amount }</p>
	<p>运费:${expressPrice}</p>
	<p>实付金额:${totalAmount }</p>
	
	<div class="go-bug"><a href="#" onclick="toCheckForm()">确认提交</a></div>
	</form>
</body>
</html>

 <script type="text/javascript">
	addressInit('province', 'city', 'district');
	
	function toCheckForm(){
		
		var province = document.getElementById("province").value;
		var city = document.getElementById("city").value;
		var district = document.getElementById("district").value;
		var address = document.getElementById("address").value;
		var deliveryName = document.getElementById("deliveryName").value;
		var deliveryPhone = document.getElementById("deliveryPhone").value;
		
		if(province == null || province == ''){
			
			alert("请选择收货省份!");
			return;
		}
		
		if(address == null || address == ''){
			
			alert("请填写收货详细地址!");
			return;
		}
		
		if(deliveryName == null || deliveryName == ''){
			
			alert("请填写收货人!");
			return;
		}
		
		if(deliveryPhone == null || deliveryPhone == ''){
			
			alert("请填写手机号码!");
			return;
		}
		
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test(deliveryPhone)) {     
			
			alert('请输入有效的手机号码！');
			return;
		} 

		
		document.getElementById('subform').action="shopController.do?createOrder";
		document.getElementById('subform').submit();
	
	}
</script>