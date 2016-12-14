<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="shop0/css/style.css">
<link rel="stylesheet" type="text/css" href="shop0/css/font-awesome.min.css">
<script type="text/javascript" src="shop0/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="shop0/js/jquery.accordion.js"></script>
<script type="text/javascript" src="shop0/js/unslider.min.js"></script>

<link rel="stylesheet" type="text/css" href="shop0/jd/css/style.css" />
</head>

<body ondragstart="return false;" onselectstart="return false;" oncontextmenu="return false" onselectstart="return false" 
ondragstart="return false" onbeforecopy="return false" oncopy=document.selection.empty() onselect=document.selection.empty()> 

<div class="new-header">
	<a href="shopController.do?shopindex" class="new-a-back"><span>返回</span></a>
	<h2>个人中心</h2>
	<a href="javascript:void(0)" id="btnJdkey" class="new-a-jd"><span>键</span></a>
</div>



<div id="page">
	
	
	
	<div id="content">
	
		<div class="info_head info_light">
		
			<div class="info_card" style="margin-left: 5px;">
				
				<i><img src="${weixinMember.headImgUrl }"></i>
				<h1  style="margin-left: 5px;">${weixinMember.nickName}</h1>
				<span  style="margin-left: 5px;" class="titles">[普通会员]</span>
				</a>
			</div>
		
		</div>

		
		<div class="round">
			<ul>
				<li><a href="shopController.do?orders"><span>我的订单</span></a></li>
				<li><a href="shopController.do?coin"><span>我的积分</span></a></li>
				<li><a href="shopController.do?shopcar"><span>我的购物车</span></a></li>
				<%-- <li><a href="shopController.do?mycard"><span>我的优惠券</span></a></li>--%>
			</ul>
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