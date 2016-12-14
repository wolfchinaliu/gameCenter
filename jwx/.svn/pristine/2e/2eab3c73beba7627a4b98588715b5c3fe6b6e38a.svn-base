<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${WeixinShopEntity.shopName }</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0,minimum-scale=1.0, maximum-scale=1.0">
	<meta content="telephone=no" name="format-detection" />
	<link rel="stylesheet" href="shop/css/wap/index.css" type="text/css" />
	<script type="text/javascript" src="shop/js/jquery-1.7.1.js"></script>
	<script type="text/javascript">
		function clearkeyword(){
			
			var key_word = document.getElementById("key_word").value;
			
			if(key_word == "输入关键词搜索"){
				document.getElementById("key_word").value = "";
			}
		}
		function subSearchForm(){
			
			var key_word = document.getElementById("key_word").value;
			
			if(key_word == "输入关键词搜索"){

				document.getElementById("key_word").value="";
			}
			document.getElementById('searchForm').action="shopGoodsController.do?golist";
			document.getElementById('searchForm').submit();
		}
	</script>
</head>
<body>
<header>
<form id="searchForm" action="shopGoodsController.do?searchGoods" method="post">
		<nav class="topBar cfx">
			<div class="l">
				<a class="logo" href="shopController.do?shopindex" style="background:#fff url(${mediaUrlPrefix}/${WeixinShopEntity.shopLogo });-moz-background-size:100% 100%;background-size:100% 100%;"></a>
			</div>
			<h1>
				<input class="search sIcon" type="text" name="key_word" value="输入关键词搜索" id="key_word" onClick="clearkeyword();"/>
			</h1>

			<div class="r"><a onClick="subSearchForm()">搜索</a></div>
		</nav>
</form>

<section class="nav">

	<a class="nav1" href="shopController.do?category"><img src="shop/images/blank.gif" alt="" /><span>商品分类</span>

	</a><a class="nav2" href="shopController.do?promotion" ><img src="shop/images/blank.gif" alt="" /><span>促销商品</span>

	</a><a class="nav3" href="shopController.do?weixincard" ><img src="shop/images/blank.gif" alt="" /><span>优惠券</span>

	</a><a class="nav4" href="shopController.do?userindex" ><img src="shop/images/blank.gif" alt="" /><span>流量中心</span>
	</a>

</section>

</header>

<div class="content">
	<h3 class="tit">
		<img class="icBuy" src="shop/images/blank.gif" alt="" />新品推荐
	</h3>
	<div class="b5mConLis" id="mainDiv">
		<ul class="clear-fix">
			<c:forEach items="${newGoodsList }" var="weixinShopGoods" begin="0" end="3">
				<li>
					<a href="shopGoodsController.do?goodsinfo&id=${weixinShopGoods.id }">
						<img src="${mediaUrlPrefix }/${weixinShopGoods.titleImg }" alt="" style="height:150px"/>
						<h3>${weixinShopGoods.title }</h3>
						<section>
							<div class="new">
								<span>￥<em>
									<c:if test="${not empty weixinShopGoods.realPrice && weixinShopGoods.realPrice != 0}">${weixinShopGoods.realPrice}</c:if>
									<c:if test="${empty weixinShopGoods.realPrice || weixinShopGoods.realPrice == 0}">${weixinShopGoods.price}</c:if>
								</em><c:if test="${not empty weixinShopGoods.realPrice && weixinShopGoods.realPrice != 0}">促销</c:if></span>
							</div>
						</section>
					</a>
				</li>
			</c:forEach>
		</ul>
		<div class="cl"></div>
	</div>
	<h3 class="tit">
		<img class="icSale" src="shop/images/blank.gif" alt="" />促销商品
	</h3>
	<div class="b5mConLis" id="mainDiv">
		<ul class="clear-fix">
			<c:forEach items="${hotGoodsList }" var="weixinShopGoods" begin="0" end="3">
				<li>
					<a href="shopGoodsController.do?goodsinfo&id=${weixinShopGoods.id }">
						<img src="${mediaUrlPrefix }/${weixinShopGoods.titleImg }" alt="" style="height:150px"/>
						<h3>${weixinShopGoods.title }</h3>
						<section>
							<div class="new">
								<span>￥<em>
									<c:if test="${not empty weixinShopGoods.realPrice && weixinShopGoods.realPrice != 0}">${weixinShopGoods.realPrice}</c:if>
									<c:if test="${empty weixinShopGoods.realPrice || weixinShopGoods.realPrice == 0}">${weixinShopGoods.price}</c:if>
								</em><c:if test="${not empty weixinShopGoods.realPrice && weixinShopGoods.realPrice != 0}">促销</c:if></span>
							</div>
						</section>
					</a>
				</li>
			</c:forEach>
		</ul>
		<div class="cl"></div>
	</div>

	<h3 class="tit">
		<img class="icHot" src="shop/images/blank.gif" alt="" />热门分类
	</h3>
	<div class="icHot sec">
		<ul>
			<c:forEach items="${weixinShopCategoryList}" var="weixinShopCategory" begin="0" end="9">
			<li>
				<a href="shopGoodsController.do?golist&categoryId=${weixinShopCategory.id}">
					<img src="${mediaUrlPrefix }/${weixinShopCategory.imgurl}" alt="" />
					<h4>${weixinShopCategory.name}</h4>
					<p>${weixinShopCategory.tags}</p>
				</a>
			</li>
			</c:forEach>
		</ul>
	</div>
</div>
<%-- 
<footer class="footer">
	<div class="top">
		 
		<c:if test="${empty memberid}">
			<a href="login.html" onClick="_gaq.push([ '_trackEvent', 'm.b5m.com', 'clicked', '登录' ]);">登录</a>
			|<a href="regist.html" onClick="_gaq.push([ '_trackEvent', 'm.b5m.com', 'clicked', '注册' ]);">注册</a><a class="btn" href="#">回到顶部</a>
		</c:if>
		
		<c:if test="${not empty memberid}">
			欢迎来到${WeixinShopEntity.shopName}，${weixinMember.nickName }您已登录
		</c:if>
			
	</div>
	<div>
		<div style="float: left;width:60px;"><img src="${WeixinShopEntity.shopLogo}" style="width: 50px;height: 50px;margin-left: 10px;"></div>
		<p>
	
	${WeixinShopEntity.shopName}&nbsp;&nbsp;
	<c:if test="${not empty WeixinShopEntity.telephone}">咨询热线：${WeixinShopEntity.telephone}</c:if></p>
	<p>${WeixinShopEntity.introduction}</p>
	
	
	</div>
</footer>
--%>
</body>
</html>