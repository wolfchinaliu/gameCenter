<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>门店查找</title>
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
	<a href="javascript:history.back()" class="new-a-back"><span>返回</span></a>
	<h2>门店查询结果</h2>
	<a href="javascript:void(0)" id="btnJdkey" class="new-a-jd"><span>键</span></a>
</div>
<div class="new-jd-tab" style="display:none" id="jdkey">
	<div class="new-tbl-type">
		<a href="" class="new-tbl-cell"><span class="icon">门店搜索</span><p style="color:#6e6e6e;">门店搜索</p></a>
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
	
	<%-- 
		<div class="box_exp" id="jdkey">
			<ul>
				<li style="width:25%"><div class="line3"><a href="shopController.do?location"><i class="icon-home blue"></i><span>门店搜索</span></a></div></li>
			</ul>
		</div>	
	--%>	
		<div class="info_head info_light">
		<c:if test="${empty locationList}">未搜索到任何门店</c:if>
		
		<c:forEach items="${locationList }" var="location">
			<div class="info_card" style="margin-left: 5px;">
				<a href="shopController.do?locationDetail&id=${location.id }">
				<i><img src="${location.titleLogo }"></i>
				<h1 style="margin-left: 5px;">${location.businessName }</h1>
				<span style="margin-left: 5px;" class="titles">人均消费：${location.avgPrice }￥</span>
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