<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建订单</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="shop/css/style.css">
<link rel="stylesheet" type="text/css" href="shop/css/buttons.css">
<link rel="stylesheet" type="text/css" href="shop/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="shop/jd/css/style.css" />
<link rel="stylesheet" href="mobiltime/css/jqueryMobile.css">
		<!-- 加载jquery文件 -->
		<script type="text/javascript" src="mobiltime/js/jquery.js"></script>
		<!-- 加载jqueryMobile文件 -->
		<script type="text/javascript" src="mobiltime/js/jqueryMobile.js"></script>
		
		<!--mobiscroll日期插件-->
		<link href="mobiltime/css/mobiscroll.css" rel="stylesheet" type="text/css" />
		<script src="mobiltime/js/mobiscroll.js" type="text/javascript"></script>
	    <script type="text/javascript">
	    	// 初始化插件内容
	    	var opt_data = {
	            preset: 'datetime', //日期格式 date（日期）|datetime（日期加时间）
	            theme: 'jqm', //皮肤样式
	            display: 'modal', //显示方式
	            mode: 'clickpick', //日期选择模式
	            dateFormat: 'yy-mm-dd', // 日期格式
	            setText: '确定', //确认按钮名称
	            cancelText: '取消',//取消按钮名籍我
	            dateOrder: 'yymmdd', //面板中日期排列格式
	            dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
	            yearText: '年', monthText: '月',  dayText: '日',  //面板中年月日文字
	            endYear:2020, //结束年份
	            showNow:true,
	            nowText:'今天',
	            hourText:'小时',
	            minuteText:'分'
	        };
			// 使用定义插件
	        $(document).on("pageinit", "#pageone", function(){
	        	$("#txtBirthday").mobiscroll(opt_data);
	        	
	        });
	    </script>
</head>

<body> 

<div class="new-header">
	<a href="javascript:history.back()" class="new-a-back"><span>返回</span></a>
	<h2>在线预订</h2>
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

<form action="weixinFoodOrderController.do?doAdd" method="post">
<div id="page">
	
	<div id="content">
		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="title"><i class="icon-bookmark-empty"></i>在线预订</span>
			</div>
			<div style="display: block; overflow: hidden; opacity: 1;">

				
				<div class="info_card">
				
					<i><img src="${weixinLocationEntity.titleLogo }"></i>
					<h1>${weixinLocationEntity.businessName }</h1>
					<span class="titles">人均消费：￥${weixinLocationEntity.avgPrice }</span>
	
				</div>
			
				<div class="info_child_txt">
					
					<span class=" fb f14 red">填写预订信息</span>

				</div>
				<div class="info_child">

					<p>您的姓名：<input id="userName" name="userName" type="text" style="width: 150px"></p>
					<p>手机号码：<input id="mobilphone" name="mobilphone" type="text" style="width: 150px"></p>
					<p>就餐时间：<input type="text" data-role="datebox" id="txtBirthday" name="txtBirthday" /></p>
					<p>坐席类别：<select id="type" name="type">
						<option value="1">普通</option>
						<option value="2">包厢</option>
					</select>
					</p>
					<p>预订人数：<input id="number" name="number" type="text" style="width: 150px" value="2"></p>
					
				</div>
			</div>
		</div>

		
		
		
		
		<div style="margin:8px 0;"><input type="submit" value="提交订单" class="button button-block button-rounded button-caution button-large"></div>
		
		
		<div style="padding:15px;"></div>
	</div>
	</form>
		

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