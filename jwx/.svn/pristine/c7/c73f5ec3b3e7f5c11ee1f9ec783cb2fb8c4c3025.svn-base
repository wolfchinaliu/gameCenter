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
	<link rel="stylesheet" href="shop//css/wap/details.css" type="text/css" />
	<style type="text/css">
		.tabV2{margin:-5px -10px 0;}
		.tabV2{border-top:1px solid #D8D8D8;background-color:#ECECEC;background:-webkit-gradient(linear,left top,left bottom,color-stop(0,#ECECEC),color-stop(100%,#E3E3E3))}
		.tabV2 li{float:left;width:33.33%;height:44px;line-height:44px;text-align:center}
		.tabV2 li a{display:block;color:#666}
		.tabV2 .cur a{color:#333}
		.tabV2 .cur{background-color:#fff;color:#333}
		.clearfix:after{display:block;content:".";height:0;visibility:hidden;clear:both;font-size:0;line-height:0}
	</style>
</head>
<body>
<nav class="topBar">
	<div class="l"><a class="arrow" href="shopController.do?shopindex"></a></div>
	<h1>商品评价</h1>
</nav>

<ul class="tabV2 clearfix">
	<li><a href="shopGoodsController.do?goodsinfo&id=${weixinShopGoods.id}">基本信息</a></li>
	<li><a href="shopGoodsController.do?godetail&id=${weixinShopGoods.id}">商品详情</a></li>
	<li class="cur"><a href="shopGoodsController.do?goodsappraise&id=${weixinShopGoods.id}">评价（${appraiseNum }）</a></li>
</ul>

<div class="detailsHead">
	<div class="info">
		<c:if test="${empty weixinShopAppraiseList }">
			<p class="grey">该商品还没有人写评价呢</p>
		</c:if>
		<div class="commtList">
		    <ul>
		    	<c:forEach items="${weixinShopAppraiseList }" var="weixinShopAppraise">
		      	<li style="margin-bottom: 5px;">
	                <%-- <p class="grey">评分：<span class="star star${weixinShopAppraise.star }"></span></p>--%>
	                <p class="mt">评价内容：${weixinShopAppraise.notes }</p>
	                <p class="grey mt">${weixinShopAppraise.openName }&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${weixinShopAppraise.createDate}" type="date" dateStyle="medium"/></p>
		       </li>
		       </c:forEach>
		  </ul>
		 </div>
	</div>
</div>

</body>
</html>