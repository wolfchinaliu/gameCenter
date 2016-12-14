<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${WeixinShopEntity.shopName }</title>
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
			优惠券
		</h1>

		

	</nav>
	
	<c:if test="${empty weixinCardList}">
			<p class="grey">暂时没有可领取的优惠券呢</p>
	</c:if>
		
	<ul class="infoLis">
		<c:forEach items="${weixinCardList}" var="weixinCard">
		<li>
			
			<h1 style="font-size: 14px">${weixinCard.title } </h1>
				<div class="img">
					<img src="${weixinCard.logoUrl }" alt="">
				</div>
				<h2>
					<span class="d"></span>
					<c:if test="${weixinCard.cardType == '1'}">团购券</c:if>
					<c:if test="${weixinCard.cardType == '2'}">代金券</c:if>
					<c:if test="${weixinCard.cardType == '3'}">折扣券</c:if>
					<c:if test="${weixinCard.cardType == '4'}">礼品券</c:if>
					<c:if test="${weixinCard.cardType == '0'}">优惠券</c:if>
					
					 <span class="r"><a href="#" onclick="addCard(${weixinCard.cardId })">立即领取</a></span>
				</h2>
				<p>
					
					<c:if test="${weixinCard.cardType == '1'}">${weixinCard.dealDetail }</c:if>
					<c:if test="${weixinCard.cardType == '2'}">抵用金额：${weixinCard.reduce_cost }</c:if>
					<c:if test="${weixinCard.cardType == '3'}">享受折扣：${weixinCard.discount }</c:if>
					<c:if test="${weixinCard.cardType == '4'}">免费领取礼品：${weixinCard.gift }</c:if>
					<c:if test="${weixinCard.cardType == '0'}">${weixinCard.default_detail }</c:if>
				</p>
			
		</li>
		</c:forEach>
	</ul>
<script type="text/javascript">
	
	function addCard(card_id){
		
		
	}
</script>
</body>
</html>