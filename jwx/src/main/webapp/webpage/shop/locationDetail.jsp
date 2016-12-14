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
<link rel="stylesheet" type="text/css" href="shop0/css/style.css">
<link rel="stylesheet" type="text/css" href="shop0/css/font-awesome.min.css">
<script type="text/javascript" src="shop0/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="shop0/js/jquery.accordion.js"></script>
<script type="text/javascript" src="shop0/js/unslider.min.js"></script>

<link rel="stylesheet" type="text/css" href="shop0/jd/css/style.css" />


<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

body, button, input, select, textarea {
	font: 12px/16px Verdana, Helvetica, Arial, sans-serif;
}

p {
	width: 603px;
	padding-top: 3px;
	overflow: hidden;
}
</style>




</head>

<body>



	<div class="new-header">
		<a href="javascript:history.back()" class="new-a-back"><span>返回</span></a>
		<h2>门店详情</h2>
		<a href="javascript:history.back()" id="btnJdkey" class="new-a-jd"><span>键</span></a>
	</div>
	<div class="new-jd-tab" style="display: none" id="jdkey">
		<div class="new-tbl-type">
			<a href="#" class="new-tbl-cell"><span class="icon">门店搜索</span>
				<p style="color: #6e6e6e;">门店搜索</p></a>
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
					<span class="title"><%-- <i class="icon-bookmark-empty"></i>--%>${weixinLocationEntity.businessName }</span>
				</div>
				<div style="display: block; overflow: hidden; opacity: 1;">
					<div class="info_child">
						<p>
							<i><img src="${weixinLocationEntity.titleLogo }"></i>
						</p>
					</div>
					<div class="info_child_txt">

						<span class=" fb f14 red">人均消费：${weixinLocationEntity.avgPrice }元</span>

					</div>
					<div class="info_child">
						<p>联系电话：${weixinLocationEntity.telephone }</p>
						<p>营业时间：${weixinLocationEntity.openTime }</p>
						<p>
							门店地址：<a href="http://apis.map.qq.com/uri/v1/marker?marker=coord:${weixinLocationEntity.latitude},${weixinLocationEntity.longitude};title:${weixinLocationEntity.businessName };addr:${weixinLocationEntity.businessName }&name=${weixinLocationEntity.address }&output=html&src=weiba|weiweb">${weixinLocationEntity.city }${weixinLocationEntity.district }${weixinLocationEntity.address }</a>
						</p>
						<p><a href="http://apis.map.qq.com/uri/v1/marker?marker=coord:${weixinLocationEntity.latitude},${weixinLocationEntity.longitude};title:${weixinLocationEntity.businessName };addr:${weixinLocationEntity.businessName }&name=${weixinLocationEntity.address }&output=html&src=weiba|weiweb">点击此处开始地图导航></a></p>
					</div>
				</div>
			</div>


			<div class="box_exp info_light">
				<div class="info_integral">
					<span class="title"><%-- <i class="icon-bookmark-empty"></i>--%>本店简介</span>
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
						<span class="title"><%-- <i class="icon-bookmark-empty"></i>--%>特色服务</span>
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
						<span class="title"><%-- <i class="icon-bookmark-empty"></i>--%>新品推荐</span>
					</div>
					<div style="display: block; overflow: hidden; opacity: 1;">
						<div class="info_cdetail">
							<p>${weixinLocationEntity.recommend}</p>
						</div>
					</div>
				</div>
			</c:if>

			<div class="box_exp info_light">
				<div class="info_integral" onclick="geolocation_latlng()">
					<span class="title"><%-- <i class="icon-bookmark-empty"></i>--%>
					门店图片
					</span>
				</div>
				<div style="display: block; overflow: hidden; opacity: 1;">
					<div class="info_cdetail">
						<img src="${weixinLocationEntity.titleLogo}" width="540px"
								height="240px">

					</div>
				</div>
			</div>
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







			<div style="padding: 15px;"></div>
		</div>



	</div>







	<script type="text/javascript">
		$(document).ready(function() {
			$('.banner').unslider({
				arrows : true,
				fluid : true,
				dots : true
			});

			$("#content").accordion({
				alwaysOpen : false,
				autoheight : false,
				header : '.info_integral',
				clearStyle : true
			});
		});
	</script>
</body>
</html>