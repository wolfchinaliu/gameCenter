<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订餐首页</title>
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

<script type="text/javascript">
$(document).ready(function(){

	//顶部弹出菜单
	$("#btnJdkey").click(function() {
		if ($("#jdkey").css("display") == "none") {
			$("#jdkey").show()
		} else {
			$("#jdkey").hide()
		}
	});
	
});
</script>

</head>

<body ondragstart="return false;" onselectstart="return false;" oncontextmenu="return false" onselectstart="return false" 
ondragstart="return false" onbeforecopy="return false" oncopy=document.selection.empty() onselect=document.selection.empty()> 

<div class="new-header">
	<a href="javascript:history.back()" class="new-a-back"><span>返回</span></a>
	<h2>订餐首页</h2>
	<a href="javascript:void(0)" id="btnJdkey" class="new-a-jd"><span>京东键</span></a>
</div>

	<div id="content">
	
	
		<div class="box_exp" id="jdkey">
			<ul>
				<li style="width:25%"><div class="line3"><a href="weixinCateController.do?index"><i class="icon-home blue"></i><span>订餐首页</span></a></div></li>
				<li style="width:25%"><div class="line"><a href="weixinCateController.do?location"><i class="icon-reorder yellow"></i><span>门店查找</span></a></div></li>
				<li style="width:25%"><div class="line3"><a href="weixinCateController.do?cart"><i class="icon-shopping-cart pink"></i><span>购物车</span></a></div></li>
				<li style="width:25%"><a href="weixinCateController.do?userindex"><i class="icon-info-sign greens"></i><span>个人中心</span></a></li>
			</ul>
		</div>	
	
		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="title"><i class="icon-bookmark-empty"></i>促销菜品</span>
			</div>
			<div style="display: block; overflow: hidden; opacity: 1;">
				<div class="info_child">
					<ul>
					<c:forEach items="${hotList }" var="weixin">
						<li style="width:33%"><div class="line3"><a href="Controller.do?godetail&id=${weixin.id }"><i><img src="${weixin.titleImg }" width="130" height="100"></i><span>${weixin.title }</span><em>${weixin.realPrice }</em></a></div></li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>

	
	
	
	
	

		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="title"><i class="icon-bookmark-empty"></i>新品推荐</span>
			</div>
			<div style="display: block; overflow: hidden; opacity: 1;">
				<div class="info_child">
					<ul>
					<c:forEach items="${newList }" var="weixin">
						<li style="width:33%"><div class="line3"><a href="Controller.do?godetail&id=${weixin.id }"><i><img src="${weixin.titleImg }" width="130" height="100"></i><span>${weixin.title }</span><em>${weixin.realPrice }</em></a></div></li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="title"><i class="icon-bookmark-empty"></i>热销菜品</span>
			</div>
			<div style="display: block; overflow: hidden; opacity: 1;">
				<div class="info_child">
					<ul>
					<c:forEach items="${hotSaleList }" var="weixin">
						<li style="width:33%"><div class="line3"><a href="Controller.do?godetail&id=${weixin.id }"><i><img src="${weixin.titleImg }" width="130" height="100"></i><span>${weixin.title }</span><em>${weixin.realPrice }</em></a></div></li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		
		
		
		
		
		<div style="padding:15px;"></div>
	</div>
	<%-- <div id="nav-footer">
		<ul>
			<li style="width:25%" class="on"><a href="index.html"><i class="icon-home"></i></a></li>
			<li style="width:25%"><a href="class.html"><i class="icon-reorder"></i></a></li>
			<li style="width:25%"><a href="cart.html"><i class="icon-ping-cart"></i></a></li>
			<li style="width:25%"><a href="index.html"><i class="icon-info-sign"></i></a></li>
		</ul>
	</div>--%>
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