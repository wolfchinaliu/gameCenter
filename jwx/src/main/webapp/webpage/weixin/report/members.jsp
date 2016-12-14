<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>


<t:base type="jquery,easyui"></t:base>
<script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/highcharts.src.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/modules/exporting.src.js"></script>

<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function() {
		$(document).ready(function() {
			var chart;
			$.ajax({
				type : "POST",
				url : "reportDemoController.do?studentCount&reportType=line",
				success : function(jsondata) {
					data = eval(jsondata);
					chart = new Highcharts.Chart({
						chart : {
							renderTo : 'containerline',
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false
						},
						title : {
							text : '班级学生人数比例分析-<b>折线图</b>'
						},
						xAxis : {
							categories : [ '1班', '2班', '3班', '4班', '5班']
						},
						tooltip : {
							shadow: false,
							percentageDecimals : 1,
							formatter: function() {
            					return  '<b>'+this.point.name + '</b>:' +  Highcharts.numberFormat(this.percentage, 1) +'%';
         					}

						},
						exporting:{  
			                filename:'pie',  
			                 url:'${ctxPath}/reportDemoController.do?export'  
			            },  
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								showInLegend : true,
								dataLabels : {
									enabled : true,
									color : '#000000',
									connectorColor : '#000000',
									formatter : function() {
										return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 1)+"%";
									}
								}
							}
						},
						series : data
					});
				}
			});
		});
	});
</script>


<script type="text/javascript">
	$(function() {
		$(document).ready(function() {
			var chart;
			$.ajax({
				type : "POST",
				url : "reportDemoController.do?studentCount&reportType=column",
				success : function(jsondata) {
					
					data = eval(jsondata);
					//console.log(data);
					chart = new Highcharts.Chart({
						chart : {
							renderTo : 'containerCol',
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false
						},
						title : {
							text : '班级学生人数比例分析-<b>柱状图</b>'
						},
						xAxis : {
							categories : [ '1班', '2班', '3班', '4班', '5班']
						},
						tooltip : {
							 percentageDecimals : 1,
							 formatter: function() {
            					return  '<b>'+this.point.name + '</b>:' +  Highcharts.numberFormat(this.percentage, 1) +'%';
         					}

						},
						exporting:{ 
			                filename:'column',  
			                url:'${ctxPath}/reportDemoController.do?export'//
			            },
						plotOptions : {
							column : {
								allowPointSelect : true,
								cursor : 'pointer',
								showInLegend : true,
								dataLabels : {
									enabled : true,
									color : '#000000',
									connectorColor : '#000000',
									formatter : function() {
										return '<b>' + this.point.name + '</b>: ' +Highcharts.numberFormat(this.percentage, 1)+"%";
									}
								}
							}
						},
						series : data
					});
				}
			});
		});
	});
</script>


<script type="text/javascript">
	$(function() {
		$(document).ready(function() {
			var chart;
			$.ajax({
				type : "POST",
				url : "reportDemoController.do?studentCount&reportType=pie",
				success : function(jsondata) {
					data = eval(jsondata);
					chart = new Highcharts.Chart({
						chart : {
							renderTo : 'containerPie',
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false
						},
						title : {
							text : '班级学生人数比例分析-<b>饼状图</b>'
						},
						xAxis : {
							categories : [ '1班', '2班', '3班', '4班', '5班']
						},
						tooltip : {
							shadow: false,
							percentageDecimals : 1,
							formatter: function() {
            					return  '<b>'+this.point.name + '</b>:' +  Highcharts.numberFormat(this.percentage, 1) +'%';
         					}

						},
						exporting:{  
			                filename:'pie',  
			                 url:'${ctxPath}/reportDemoController.do?export'  
			            },  
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								showInLegend : true,
								dataLabels : {
									enabled : true,
									color : '#000000',
									connectorColor : '#000000',
									formatter : function() {
										return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 1)+"%";
									}
								}
							}
						},
						series : data
					});
				}
			});
		});
	});
</script>
<span id="containerline" style="float: left; width: 30%; height: 60%"></span>
<span id="containerCol" style="float: left; width: 38%; height: 60%"></span>
<span id="containerPie" style="width: 30%; height: 60%"></span>


<div style="width: 98%; height: 280px">
日期：<input id="dd" type="text" class="easyui-datebox" required="required"> - 
<input id="dd" type="text" class="easyui-datebox" required="required"> 
<input type="button" value="分析">

<c:forEach items="${memberList}" var="membe">

</c:forEach>

<t:datagrid name="memberList" title="关注粉丝分析" actionUrl="reportController.do?listMember" idField="id" fit="true">
	<t:dgCol title="日期" field="ref_date"></t:dgCol>
	<t:dgCol title="关注渠道" field="user_source"></t:dgCol>
	<t:dgCol title="新增关注" field="new_user" width="130"></t:dgCol>
	<t:dgCol title="取消关注" field="cancel_user" width="130"></t:dgCol>
	<t:dgCol title="总粉丝量" field="cumulate_user" width="130"></t:dgCol>
</t:datagrid></div>