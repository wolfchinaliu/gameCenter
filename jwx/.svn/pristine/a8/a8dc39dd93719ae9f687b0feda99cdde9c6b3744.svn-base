<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>门店详情</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="shop/css/style.css">
<link rel="stylesheet" type="text/css" href="shop/css/buttons.css">
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
	<h2>门店详情</h2>
	<a href="javascript:void(0)" id="btnJdkey" class="new-a-jd"><span>键</span></a>
</div>
<div class="new-jd-tab" style="display:none" id="jdkey">
	<div class="new-tbl-type">
		<a href="#" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p></a>
		<a href="#" class="new-tbl-cell"><span class="icon2">分类搜索</span><p style="color:#6e6e6e;">分类搜索</p></a>
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
		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="title"><i class="icon-bookmark-empty"></i>${weixinLocationEntity.businessName }</span>
			</div>
			<div style="display: block; overflow: hidden; opacity: 1;">
				<div class="info_child">
					<p><img src="${weixinLocationEntity.titleLogo}" width="540px" height="240px"></p>
				</div>
				<div class="info_child_txt">
					
					<span class=" fb f14 red">人均消费：${weixinLocationEntity.avgPrice }元</span>
					
					<a href="weixinCateController.do?toOrder&id=${weixinLocationEntity.id}" class="button button-rounded button-flat-caution right"><i class="icon-shopping-cart"></i> 在线预订</a>
				</div>
				<div class="info_child">
					<p>联系电话：${weixinLocationEntity.telephone }</p>
					<p>营业时间：${weixinLocationEntity.openTime }</p>
					<p>门店地址：${weixinLocationEntity.province }${weixinLocationEntity.city }${weixinLocationEntity.district }${weixinLocationEntity.address }</p>
				</div>
			</div>
		</div>
	
	 
		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="title"><i class="icon-bookmark-empty"></i>本店简介</span>
			</div>
			<div style="display: block; overflow: hidden; opacity: 1;">
				<div class="info_cdetail">
					<p>${weixinLocationEntity.introduction}</p>
				</div>
			</div>
		</div>
		
		<c:if test="${not empty weixinLocationEntity.special}">
		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="title"><i class="icon-bookmark-empty"></i>特色服务</span>
			</div>
			<div style="display: block; overflow: hidden; opacity: 1;">
				<div class="info_cdetail">
					<p>${weixinLocationEntity.special}</p>
				</div>
			</div>
		</div>
		</c:if>
		
		<c:if test="${not empty weixinLocationEntity.recommend}">
		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="title"><i class="icon-bookmark-empty"></i>新品推荐</span>
			</div>
			<div style="display: block; overflow: hidden; opacity: 1;">
				<div class="info_cdetail">
					<p>${weixinLocationEntity.recommend}</p>
				</div>
			</div>
		</div>
		</c:if>
		<%--
		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="title"><i class="icon-time"></i><font color="#FF3300">促销信息</font></span>
			</div>
			<div style="display: block; overflow: hidden; opacity: 1;">
				<div class="info_child">
					<p>已经优惠：100元</p>
				</div>
			</div>
		</div>
		--%>
		
		
		
		
		
		<div style="margin:8px 0;"><a href="weixinCateController.do?toOrder&id=${weixinLocationEntity.id}" class="button button-block button-rounded button-caution button-large"><i class="icon-shopping-cart"></i>&nbsp;&nbsp;在线预订</a></div>
		
		
		<div style="padding:15px;"></div>
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