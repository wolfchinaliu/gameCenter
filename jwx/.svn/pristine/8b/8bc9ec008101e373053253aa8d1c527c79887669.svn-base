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
<form id="searchForm" action="shopGoodsController.do?golist" method="post">
		<nav class="topBar cfx">
			<div class="l">
				<a class="logo" href="shopController.do?shopindex" style="background:#fff url(${mediaUrlPrefix }/${WeixinShopEntity.shopLogo });-moz-background-size:100% 100%;background-size:100% 100%;"></a>
			</div>
			<h1>
				<input class="search sIcon" type="text" name="key_word" value="输入关键词搜索" id="key_word" onClick="clearkeyword();"/>
			</h1>

			<div class="r"><a onClick="subSearchForm()">搜索</a></div>
		</nav>
</form>

<ul class="sortList">
	<c:forEach items="${weixinShopCategoryList}" var="weixinShopCategory">
	<li>
		<a href="shopGoodsController.do?golist&categoryId=${weixinShopCategory.id}">

			<span class="icon icon1"><img alt="" src="${mediaUrlPrefix }/${weixinShopCategory.imgurl}" style="width:40px;height:40px"/></span>

			<h3 style="padding-left: 10px;">${weixinShopCategory.name}</h3>

			<p style="padding-left: 10px;">
				${weixinShopCategory.tags}
			</p>

			<span class="arrow"></span>

		</a>
	</li>
	</c:forEach>
</ul>

</header>
</body>
	