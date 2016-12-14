<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>我的购物车</title>
	<link rel="stylesheet" href="shop/css/wap/index.css" type="text/css" />
	<script type="text/javascript" src="shop/js/jquery-1.7.1.js"></script>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0,minimum-scale=1.0, maximum-scale=1.0">
	<meta content="telephone=no" name="format-detection" />
	<style type="text/css">
		
		.but-op{color:orange;font-size: 12px;}
		.title{font-size: 12px;}
	</style>
</head>
<body id="body_id">

	<nav class="topBar">

		<div class="l">

			<a class="arrow" href="shopController.do?userindex"></a>

		</div>

		<h1>
			我的购物车
		</h1>

	</nav>
	
	<c:if test="${empty weixinShopCartList}">
			<p class="grey">您的购物车里面空空的，赶紧去选购商品吧</p>
	</c:if>
	<c:if test="${not empty weixinShopCartList}">
	<form action="shopController.do?confirmOrder" method="post" id="subform" name="subform">
	<ul class="infoLis">
	<table  width="100%" border="0">
			<tr height="40px">
				<td></td>
				<td colspan="2">商品信息</td>
				<td align="center">单价</td>
				<td align="center">数量</td>
				<td>操作</td>
			</tr>
		<c:forEach items="${weixinShopCartList}" var="weixinShopCart">
			<tr>
				<td width="10%"><input type="checkbox" name="cartids" id="cartids" value="${weixinShopCart.id}" checked="checked"/></td>
				<td width="10%"><a href="shopGoodsController.do?goodsinfo&id=${weixinShopCart.weixinShopGoodsEntity.id}"><img src="${mediaUrlPrefix }/${weixinShopCart.weixinShopGoodsEntity.titleImg}" width="50px" height="50px"></a></td>
				<td width="40%"><span class="title">${weixinShopCart.weixinShopGoodsEntity.title}</span></td>
				<td width="10%" align="center"><span style="color: red;">
				
				
				<c:if test="${not empty weixinShopCart.weixinShopGoodsEntity.realPrice}">${weixinShopCart.weixinShopGoodsEntity.realPrice}</c:if>
				<c:if test="${empty weixinShopCart.weixinShopGoodsEntity.realPrice}">${weixinShopCart.weixinShopGoodsEntity.price}</c:if>
				
				
				</span></td>				
				<td width="20%" align="center"> ${weixinShopCart.quantity} </td>
				<td width="10%"><a href="shopController.do?delshopcar&id=${weixinShopCart.id}"><span class="but-op">删除</span></a></td>
			</tr>
		</c:forEach>
	</table>
	</ul>
	<div class="go-bug"><a href="#" onclick="toCheckForm()">结算</a></div>
	</form>
	</c:if>
</body>
</html>
<script type=text/javascript>
	function toCheckForm(){
		
		var chks=document.getElementsByTagName('input');
		var bl=true;
		for(var i=0;i<chks.length;i++)
		{
		    if(chks[i].checked) 
		    {
		        bl=false;
		        break;
		    }
		} 

		if(bl){
			
			alert('至少选择一条商品信息');
			return;
		}
		
		document.getElementById('subform').action="shopController.do?confirmOrder";
		document.getElementById('subform').submit();
		
	}
</script>