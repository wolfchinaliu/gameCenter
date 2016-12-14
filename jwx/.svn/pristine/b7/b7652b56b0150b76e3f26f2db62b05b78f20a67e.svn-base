<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{height:80%;width:100%;}
		#r-result{width:100%; font-size:14px;}
	</style>
	<script type="text/javascript" src="http://lbsyun.baidu.com/skins/MySkin/resources/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GmI3dTdWX1S4U37qLqAIcpHzYwRlC4Rt"></script>
		<script src="plug-in/acctlist/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="plug-in/acctlist/css/normalize.css">
	    <link rel="stylesheet" type="text/css" href="plug-in/acctlist/css/index.css">
	<title>${acctListEntity.acctlistName}</title>
</head>
<body>
	<div id="allmap"></div>
	<div id="r-result">
		 <input id="longitude" type="hidden" value="${lng }" style="width:100px; margin-right:10px;" />
		 <input id="latitude" type="hidden" value="${lat }" style="width:100px; margin-right:10px;" />
		<input id="address" type="hidden" value="${address }" style="width:100px; margin-right:10px;" />
	</div>
		<ul class="footer-nav">
		<c:choose>
			<c:when test="${not empty acctListEntity.shoppAddress}">
				<c:set var="width" value="width:33.33%"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="width" value="width:50%"></c:set>
			</c:otherwise>
		</c:choose>
		
			<li style="<c:out value="${width }"/>"><a href="javascript:history.go(-1);">门店介绍</a></li>
			<c:if test="${acctListEntity.shoppAddress ==null}">
			</c:if>
			<c:if test="${not empty acctListEntity.shoppAddress}">
			<li style="<c:out value="${width }"/>"><a href="${acctListEntity.shoppAddress}">商城</a></li>
			</c:if>
			<li style="<c:out value="${width }"/>"><a href='tel://${acctListEntity.phone}'>一键呼叫</a></li>
		</ul>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(116.396885,39.922692),15);
	map.enableScrollWheelZoom(true);
	map.addControl(new BMap.NavigationControl());    
	map.addControl(new BMap.ScaleControl());    
	map.addControl(new BMap.OverviewMapControl());    
	map.addControl(new BMap.MapTypeControl());
	map.clearOverlays();
	var coordinate =new BMap.Point(document.getElementById("longitude").value,document.getElementById("latitude").value);
	var marker = new BMap.Marker(coordinate);  // 创建标注
	map.addOverlay(marker);              // 将标注添加到地图中
	 var opts = {title : '<span style="font-size:14px;color:#0A8021">商家地址：</span>'};
	  var infoWindow =new BMap.InfoWindow(document.getElementById("address").value); // 创建信息窗口对象，引号里可以书写任意的html语句。
	  marker.addEventListener("click", function(){
	   this.openInfoWindow(infoWindow);
	  });
	  map.addOverlay(marker);
	map.panTo(coordinate);
</script>