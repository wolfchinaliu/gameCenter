<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>商品评价</title>
	<link rel="stylesheet" href="shop/css/wap/index.css" type="text/css" />
	<script type="text/javascript" src="webpage/shop/jsAddress.js"></script>
	<script type="text/javascript" src="shop/js/jquery-1.7.1.js"></script>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0,minimum-scale=1.0, maximum-scale=1.0">
	<meta content="telephone=no" name="format-detection" />
	<style type="text/css">
	p{padding-top: 10px;padding-left: 5px;}
	input{height: 30px;vertical-align: middle;}

	</style>
</head>
<body id="body_id">

	<nav class="topBar">

		<div class="l">

			<a class="arrow" href="shopController.do?orders"></a>

		</div>

		<h1>
			商品评价
		</h1>

	</nav>
	<form action="" method="post" id="subform" name="subform">
		<input type="hidden" id="orderId" name="orderId" value="${weixinOrderEntity.id }">

		<p>已购商品:</p>
		<p>

			<c:forEach items="${weixinOrderEntity.weixinOrderDetailList}" var="weixinOrderDetail">
				
				<a href="shopGoodsController.do?goodsinfo&id=${weixinOrderDetail.weixinShopGoodsEntity.id}"><img src="${mediaUrlPrefix }/${weixinOrderDetail.weixinShopGoodsEntity.titleImg}" width="50px" height="50px"></a>
				${weixinOrderDetail.weixinShopGoodsEntity.title}</span>
				<br>
				<input type="hidden" id="goodsId" name="goodsId" value="${weixinOrderDetail.weixinShopGoodsEntity.id }">
				评价内容：<br>
				<textarea id="note" name="note" cols="30" rows="4"></textarea>
				<br><br>
			</c:forEach>

		</p>
		<br>
		<div class="go-bug"><a href="#" onclick="toCheckForm()">提交评价</a></div>
		
	

	
	</form>
</body>
</html>

 <script type="text/javascript">
	addressInit('province', 'city', 'district');
	
	function toCheckForm(){
		
		alert("感谢您的评价！");
		document.getElementById('subform').action="shopController.do?doAppraise";
		document.getElementById('subform').submit();
	
	}
</script>