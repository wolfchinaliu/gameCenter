<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>订单详情</title>
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

			<a class="arrow" href="shopController.do?orders"></a>

		</div>

		<h1>
			订单详情
		</h1>

	</nav>
	
	<p>订单编号：${weixinOrderEntity.orderNo }</p>
	<p>订单金额：${weixinOrderEntity.orderAmount }</p>
	<p>运费:${weixinOrderEntity.freight}</p>
	<c:if test="${weixinOrderEntity.status == 1 || weixinOrderEntity.status == 2}"><p>实付金额:${weixinOrderEntity.payAmount }</p></c:if>
	<p>交易状态：<c:if test="${weixinOrderEntity.status == 0}">待付款</c:if>
				<c:if test="${weixinOrderEntity.status == 1}">已付款</c:if>
				<c:if test="${weixinOrderEntity.status == 2}">交易成功</c:if>
				<c:if test="${weixinOrderEntity.status == 3}">退款中</c:if>
				<c:if test="${weixinOrderEntity.status == 4}">退货中</c:if>
				<c:if test="${weixinOrderEntity.status == 5}">已退款</c:if>
				<c:if test="${weixinOrderEntity.status == 9}">已取消</c:if>
	</p>
	
	<p>收货地址：${weixinOrderEntity.province }${weixinOrderEntity.city }${weixinOrderEntity.district }${weixinOrderEntity.address }</p>
	<p>收货人：${weixinOrderEntity.deliveryName }</p>
	<p>联系电话：${weixinOrderEntity.deliveryPhone }</p>
	<p>留言：${weixinOrderEntity.leaveWord }</p>
	
	
	<p>发货状态：
		<c:if test="${weixinOrderEntity.deliverStatus == 0}">未发货</c:if>
		<c:if test="${weixinOrderEntity.deliverStatus == 1}">已发货</c:if>
		<c:if test="${weixinOrderEntity.deliverStatus == 2}">已收货</c:if>
	</p>
	<c:if test="${weixinOrderEntity.deliverStatus != 0}">
		<p>快递公司：${weixinOrderEntity.expressCompany}</p>
		<p>快递单号：${weixinOrderEntity.expressNum}</p>
	</c:if>
	<p>购买商品:</p>
	<p>
	<table  width="100%" border="0">
			<tr height="40px">
				<td colspan="2">&nbsp;&nbsp;商品信息</td>
				<td align="center">单价</td>
				<td align="center">数量</td>
			</tr>
		<c:forEach items="${weixinOrderEntity.weixinOrderDetailList}" var="weixinOrderDetail">
			<tr>
				<td width="10%"><a href="shopGoodsController.do?goodsinfo&id=${weixinOrderDetail.weixinShopGoodsEntity.id}"><img src="${mediaUrlPrefix }/${weixinOrderDetail.weixinShopGoodsEntity.titleImg}" width="50px" height="50px"></a></td>
				<td width="40%"><span class="title">${weixinOrderDetail.weixinShopGoodsEntity.title}</span></td>
				<td width="10%" align="center"><span style="color: red;">¥${weixinOrderDetail.price}</span></td>				
				<td width="20%" align="center"> ${weixinOrderDetail.quantity} </td>
			</tr>
		</c:forEach>
	</table>
	</p>
	
	<c:if test="${weixinOrderEntity.status == 0 }">
		<div class="go-bug"><a href="shopController.do?toPay&orderId=${weixinOrderEntity.id}">付款</a></div>
	</c:if>
</body>
</html>