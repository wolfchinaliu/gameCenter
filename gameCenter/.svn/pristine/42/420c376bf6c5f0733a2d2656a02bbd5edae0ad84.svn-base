<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>投票结果显示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/Highcharts/highcharts.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath }/js/Highcharts/highcharts-3d.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath }/js/Highcharts/modules/exporting.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath }/js/Highcharts/themes/sand-signika.js"></script>
  </head>
  
  <body>
    	<div id="container" style="height: 400px"></div>
  </body>
  	<script type="text/javascript">
  			$(function () {
			    $('#container').highcharts({
			        chart: {
			            type: 'pie',
			            options3d: {
			                enabled: true,
			                alpha: 45,
			                beta: 0
			            }
			        },
			        title: {
			            text: '直播APP排行'
			        },
			        tooltip: {
			           formatter: function() {
						return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+
						Highcharts.numberFormat(this.y, 0, ',') +' 个)';
						}
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                depth: 35,
			                dataLabels: {
			                    enabled: true,
			                    format: '{point.name}'
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: 'Browser share',
			            data: [
			                [${sessionScope.countList[0].appName},   ${sessionScope.countList[0].appCount}],
			                [${sessionScope.countList[1].appName},   ${sessionScope.countList[1].appCount}],
			                [${sessionScope.countList[2].appName},   ${sessionScope.countList[2].appCount}],
			               	[${sessionScope.countList[3].appName},   ${sessionScope.countList[3].appCount}],
			               	[${sessionScope.countList[4].appName},   ${sessionScope.countList[4].appCount}],
			               	[${sessionScope.countList[5].appName},   ${sessionScope.countList[5].appCount}],
			               	[${sessionScope.countList[6].appName},   ${sessionScope.countList[6].appCount}],
			               	[${sessionScope.countList[7].appName},   ${sessionScope.countList[7].appCount}],
			               	[${sessionScope.countList[8].appName},   ${sessionScope.countList[8].appCount}],     
			                [${sessionScope.countList[9].appName},   ${sessionScope.countList[9].appCount}]
			            ]
			        }]
			    });
			});
  	</script>
</html>
