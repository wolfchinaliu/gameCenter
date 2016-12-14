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
	<script type="text/javascript">
		function showfilter(){
			
			var divD = document.getElementById("filter");
			if(divD.style.display=="none"){
				
				divD.style.display = "block";     
			}else{
				
				divD.style.display = "none";
			} 
		}
	</script>
</head>
<body id="body_id">
<div class="filter" id="filter">
			<div class="filterEvn"></div>
			<div class="filterConten" id="filterConten">
				<div class="filterNav">

					<ul>
						<li class="ok"><a href="shopController.do?category">所有分类</a></li>
					</ul>

				</div>

				<div class="filterCon">
					<ul class="attr" style="display: block;">
							<li>
								<a href="shopGoodsController.do?searchGoods&key_word=">
								<span  class="dot" > </span>全部</a>
							</li>
							<c:forEach items="${weixinShopCategoryList}" var="weixinShopCategory">
							<li>
								<a href="">
								<span > </span>${weixinShopCategory.name}</a>
							</li>
							</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	<nav class="topBar">

		<div class="l">

			<a class="arrow" href="javascript:history.go(-1);"></a>

		</div>

		<h1>
			促销商品
		</h1>

		<div class="r">
			<%-- <a href="javascript:showfilter();">筛选<span class="rightArr"></span></a>--%>

		</div>

	</nav>
	
	<div class="b5mConLis" id="mainDiv">
		<ul class="clear-fix">
			<c:forEach items="${goodsList }" var="weixinShopGoods">
				<li>
					<a href="shopGoodsController.do?goodsinfo&id=${weixinShopGoods.id }">
						<img src="${mediaUrlPrefix }/${weixinShopGoods.titleImg }" alt="" style="height:150px"/>
						<h3>${weixinShopGoods.title }</h3>
						<section>
							<div class="new">
								<span>￥<em>
									<c:if test="${not empty weixinShopGoods.realPrice}">${weixinShopGoods.realPrice}</c:if>
									<c:if test="${empty weixinShopGoods.realPrice}">${weixinShopGoods.price}</c:if>
								</em><c:if test="${not empty weixinShopGoods.realPrice}">促销</c:if></span>
							</div>
						</section>
					</a>
				</li>
			</c:forEach>
		</ul>
		<div class="cl"></div>
	</div>
	
	<%-- 
	<ul class="infoLis">
		<c:forEach items="${goodsList }" var="weixinShopGoods">
		<li>
			<a href="shopGoodsController.do?goodsinfo&id=${weixinShopGoods.id }">
			<h1 style="font-size: 14px">${weixinShopGoods.title } </h1>
				<div class="img">
					<img src="${weixinShopGoods.titleImg }" alt="">
				</div>
				<h2>
					<span class="d">￥</span>${weixinShopGoods.realPrice} <span class="r">关注</span>
				</h2>
				<p>原价：${weixinShopGoods.price}</p>
			</a>
		</li>
		</c:forEach>
	</ul>
--%>
	<br>
<%-- 
	<div class="infoPages">
			<a href="javascript:void(0);" class="grey">上一页</a>
		<span>1/1</span>
			<a

				href="2.html">下一页</a>
	</div>

	<br>
--%>
</body>
</html>