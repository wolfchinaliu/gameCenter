<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>我的订单</title>
	<link rel="stylesheet" href="shop/css/wap/index.css" type="text/css" />
	<script type="text/javascript" src="shop/js/jquery-1.7.1.js"></script>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0,minimum-scale=1.0, maximum-scale=1.0">
	<meta content="telephone=no" name="format-detection" />
	<style type="text/css">
		.but-op{color:orange;font-weight: 700;font-size: 14px;}
	</style>
</head>
<body id="body_id">

	<nav class="topBar">

		<div class="l">

			<a class="arrow" href="shopController.do?userindex"></a>

		</div>

		<h1>
			我的订单
		</h1>

	</nav>
	
	<c:if test="${empty weixinOrderList}">
			<p class="grey">您还没有下过订单呢，赶紧去选购商品吧</p>
	</c:if>
		
	<ul class="infoLis">
		<c:forEach items="${weixinOrderList}" var="weixinOrder">
		<li>
			
			<h1 style="font-size: 14px"><fmt:formatDate value="${weixinOrder.createDate}" type="date" dateStyle="medium"/> <span style="float: right;font-weight: 100;margin-right: 5px;">订单编号：${weixinOrder.orderNo }</span> </h1> 
			<c:forEach items="${weixinOrder.weixinOrderDetailList}" var="weixinOrderDetail">
				<a href="shopGoodsController.do?goodsinfo&id=${weixinOrderDetail.weixinShopGoodsEntity.id}">
				<img src="${mediaUrlPrefix }/${weixinOrderDetail.weixinShopGoodsEntity.titleImg}" width="80px" height="80px" style="margin: 5px 5px 5px 5px;"></a>
				
			</c:forEach>
			<c:if test="${weixinOrder.status == 0}">
				<span style="float: right;margin-top:50px;margin-right: 20px;"><a href="shopController.do?toPay&orderId=${weixinOrder.id}" class="but-op"><span class="but-op">立即付款</span></a></span>
			</c:if>
			<c:if test="${weixinOrder.status == 1 && weixinOrder.deliverStatus == 1}">
				<span style="float: right;margin-top:50px;margin-right: 20px;"><a href="#" onclick="confirmDelivery('${weixinOrder.id}')" class="but-op"><span class="but-op">确认收货</span></a></span>
			</c:if>
			<c:if test="${weixinOrder.isAppraise=='0'}">
			
				<span style="float: right;margin-top:50px;margin-right: 20px;"><a href="shopController.do?toAppraise&orderId=${weixinOrder.id}" class="but-op"><span class="but-op">评价</span></a></span>
			
			</c:if>
			
			<p style="margin-left: 5px;"><span style="color: red;">¥ ${weixinOrder.orderAmount }</span> (含运费:${weixinOrder.freight }) &nbsp;
			
			</p>
			<p style="margin-left: 5px;">
				<c:if test="${weixinOrder.status == 0}">待付款</c:if>
				<c:if test="${weixinOrder.status == 1}">已付款</c:if>
				<c:if test="${weixinOrder.status == 2}">交易成功</c:if>
				<c:if test="${weixinOrder.status == 3}">退款中</c:if>
				<c:if test="${weixinOrder.status == 4}">退货中</c:if>
				<c:if test="${weixinOrder.status == 5}">已退款</c:if>
				<c:if test="${weixinOrder.status == 9}">已取消</c:if>
				&nbsp;
				<c:if test="${weixinOrder.deliverStatus == 1}">已发货</c:if>
				<c:if test="${weixinOrder.deliverStatus == 2}">已收货</c:if>
				&nbsp;
				<c:if test="${weixinOrder.isAppraise == 1}">已评价</c:if>
				
				&nbsp;<a href="shopController.do?orderdetail&orderId=${weixinOrder.id}">订单详情</a>
				
				<c:if test="${weixinOrder.status == 9 || weixinOrder.status == 2}">
					<span style="float: right;margin-right: 10px;"><a href="#" onclick="confirmDel('${weixinOrder.id}')">删除订单</a></span>
				</c:if>
				<%--
				<c:if test="${weixinOrder.status == 1 && weixinOrder.deliverStatus == 0}">
					<span style="float: right;margin-right: 10px;"><a href="#" onclick="confirmRefund('${weixinOrder.id}')">申请退款</a></span>
				</c:if>
				 
				<c:if test="${weixinOrder.status == 2}">
					<span style="float: right;margin-right: 10px;"><a href="#" onclick="confirmReturn('${weixinOrder.id}')">申请退货</a></span>
				</c:if>
				--%>
				<c:if test="${weixinOrder.status == 0}">
					<span style="float: right;margin-right: 10px;"><a href="#" onclick="confirmCel('${weixinOrder.id}')">取消订单</a></span>
				</c:if>
				
			</p>
		</li>
		</c:forEach>
	</ul>
</body>
</html>
<script type="text/javascript">

	function confirmDelivery(id){
		
		if(window.confirm("您确认已经收到货了？")) {
			
			window.location.href="shopController.do?confirmdelivery&orderId="+id;
		}
	}
	
	function confirmRefund(id){
		
		if(window.confirm("您确认要申请退款？")) {
			
			window.location.href="shopController.do?refundorder&orderId="+id;
		}
	}
	
	function confirmReturn(id){
		
		if(window.confirm("您确认要申请退货？")) {
			
			window.location.href="shopController.do?refundorder&orderId="+id;
		}
	}
	
	function confirmCel(id){
		
		if(window.confirm("您确认要取消该订单？")) {
			
			window.location.href="shopController.do?celorder&orderId="+id;
		}
	}
	
	function confirmDel(id){
		
		if(window.confirm("您确认要删除该订单？")) {
			
			window.location.href="shopController.do?delorder&orderId="+id;
		}
	}
           
</script>
