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
		.tabV2{margin:-5px -10px 0}
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
	<h1>商品信息</h1>
</nav>

<ul class="tabV2 clearfix">
	<li class="cur"><a href="shopGoodsController.do?goodsinfo&id=${weixinShopGoods.id}">基本信息</a></li>
	<li><a href="shopGoodsController.do?godetail&id=${weixinShopGoods.id}">商品详情</a></li>
	<li><a href="shopGoodsController.do?goodsappraise&id=${weixinShopGoods.id}">评价（${appraiseNum }）</a></li>
</ul>
			
<div class="detailsHead">
	<div class="img">
		<img src="${mediaUrlPrefix }/${weixinShopGoods.titleImg}" alt="">
	</div>
	<h1>${weixinShopGoods.title }</h1>
	<div class="info">
		<span class="l">
			<em>￥</em> <em class="taobao">
			<c:if test="${not empty weixinShopGoods.realPrice && weixinShopGoods.realPrice != 0}">${weixinShopGoods.realPrice}</c:if>
			<c:if test="${empty weixinShopGoods.realPrice || weixinShopGoods.realPrice == 0}">${weixinShopGoods.price}</c:if>
			</em><c:if test="${not empty weixinShopGoods.realPrice && weixinShopGoods.realPrice != 0}">促销</c:if>
		</span>
	</div>
	
	<div class="cl"></div>
</div>
<div class="go-bug"><a href="shopController.do?addshopcar&goodsid=${weixinShopGoods.id }">加入购物车</a></div>
<div class="det comment">
	<%-- <p>${weixinShopGoods.descriptions}</p>--%>
</div>
<div class="det baby">
		<h3 class="tit">相关宝贝<span class="showHide"><font><a href="shopGoodsController.do?golist&categoryId=${weixinShopGoods.weixinShopCategoryEntity.id}">更多</a></font><span class="hideSpan"></span></span></h3>
		<ul>
			<c:forEach items="${hotGoodsList }" var="weixinShopGoods" begin="0" end="2">
				<li>
					<a href="shopGoodsController.do?goodsinfo&id=${weixinShopGoods.id }" title="${weixinShopGoods.title }">
						<img src="${mediaUrlPrefix }/${weixinShopGoods.titleImg }" alt="${weixinShopGoods.title }">
					</a>
				</li>
			</c:forEach>
		</ul>
</div>
<div class="cl"></div>

</body>
</html>