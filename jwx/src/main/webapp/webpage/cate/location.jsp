<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>门店查找结果</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="shop/css/style.css">
<link rel="stylesheet" type="text/css" href="shop/css/font-awesome.min.css">
<script type="text/javascript" src="shop/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="shop/js/jquery.accordion.js"></script>
<script type="text/javascript" src="shop/js/unslider.min.js"></script>

<link rel="stylesheet" type="text/css" href="shop/jd/css/style.css" />
</head>

<body ondragstart="return false;" onselectstart="return false;" oncontextmenu="return false" onselectstart="return false" 
ondragstart="return false" onbeforecopy="return false" oncopy=document.selection.empty() onselect=document.selection.empty()> 

<div class="new-header">
	<a href="javascript:history.back()" class="new-a-back"><span>返回</span></a>
	<h2>查询结果</h2>
	<a href="javascript:void(0)" id="btnJdkey" class="new-a-jd"><span>键</span></a>
</div>
<div class="new-jd-tab" style="display:none" id="jdkey">
	<div class="new-tbl-type">
		<a href="#" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p></a>
		<a href="#" class="new-tbl-cell"><span class="icon2">门店搜索</span><p style="color:#6e6e6e;">门店搜索</p></a>
		<a href="javascript:void(0)" id="html5_cart" class="new-tbl-cell"><span class="icon3">购物车</span><p style="color:#6e6e6e;">购物车</p></a>
		<a href="#" class="new-tbl-cell"><span class="icon4 on">个人中心</span><p style="color:#6e6e6e;" class="on">个人中心</p></a>
	</div>
</div>


<div id="page">
	<%-- 
	<div class="banner" style="">
		<ul>
			<li style="background-image: url('shop/images/4.jpg');"></li>
			<li style="background-image: url('shop/images/3.jpg');"></li>
			<li style="background-image: url('shop/images/1.jpg');"></li>
			<li style="background-image: url('shop/images/2.jpg');"></li>
		</ul>
	</div>
	--%>
	
	<div id="content">
	
	
		<div class="box_exp" id="jdkey">
			<ul>
				<li style="width:25%"><div class="line3"><a href="weixinCateController.do?index"><i class="icon-home blue"></i><span>订餐首页</span></a></div></li>
				<li style="width:25%"><div class="line"><a href="weixinCateController.do?location"><i class="icon-reorder yellow"></i><span>门店查找</span></a></div></li>
				<li style="width:25%"><div class="line3"><a href="weixinCateController.do?cart"><i class="icon-shopping-cart pink"></i><span>购物车</span></a></div></li>
				<li style="width:25%"><a href="weixinCateController.do?userindex"><i class="icon-info-sign greens"></i><span>个人中心</span></a></li>
			</ul>
		</div>	
		
		<div class="info_head info_light">
		<c:forEach items="${locationList }" var="location">
			<div class="info_card">
				<a href="weixinCateController.do?locationDetail&id=${location.id }">
				<i><img src="${location.titleLogo }"></i>
				<h1>${location.businessName }</h1>
				<span class="titles">人均消费：￥${location.avgPrice }</span>
				</a>
			</div>
		</c:forEach>
		</div>



		
		
	</div>
</div>







<script type="text/javascript">
$(document).ready(function() {
	$('.banner').unslider({
		arrows: true,
		fluid: true,
		dots: true
	});

	$("#content").accordion({
		alwaysOpen: false,
		autoheight: false,
		header: '.info_integral',
		clearStyle: true
	});
});
</script>
</body>
</html>