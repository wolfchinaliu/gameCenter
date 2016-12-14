<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>我的优惠券</title>
	<link rel="stylesheet" href="shop/css/wap/index.css" type="text/css" />
	<script type="text/javascript" src="shop/js/jquery-1.7.1.js"></script>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0,minimum-scale=1.0, maximum-scale=1.0">
	<meta content="telephone=no" name="format-detection" />
</head>
<body id="body_id">

	<nav class="topBar">

		<div class="l">

			<a class="arrow" href="javascript:history.go(-1);"></a>

		</div>

		<h1>
			我的优惠券
		</h1>

	</nav>
	
	<c:if test="${empty weixinOrderList}">
			<p class="grey">您还没有优惠券呢，赶紧去选购商品吧</p>
	</c:if>
		
	<ul class="infoLis">
		<c:forEach items="${myCardList}" var="myCard">
		<li>
			
			<h1 style="font-size: 14px">优惠券号：${myCard.userCardCode}</h1>

			
		</li>
		</c:forEach>
	</ul>
</body>
</html>