<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<script src="http://www.lanrenzhijia.com/ajaxjs/jquery.min.js"></script>
	<script src="crm/index/js/idangerous.swiper-1.9.1.min.js"></script>
	<script src="crm/index/js/idangerous.swiper.scrollbar-1.2.js"></script>
	<script src="crm/index/js/swiper-demos.js"></script>
	<title>灵动移动CRM</title>
	<style type="text/css">
		#plan-list a{
			width:80px;
			height:70px;
			padding-top: 10px;
		}
		.main {
			width: 820px;
			margin: 0 auto;
			padding: 60px 40px;
			background: #fff;
			border-left: 1px solid #ccc;
			border-right: 1px solid #ccc;
			border-bottom: 1px solid #ccc;
		}
		.home-device {
			width:640px;
			height:300px;
			margin:0 auto;
			position:relative;
			box-shadow: 0px 0px 5px #000;
		}
		.home-device .arrow-left {
			background:url(crm/index/images/arrows.png) no-repeat left top;
			position:absolute;
			left:10px;
			top:50%;
			margin-top:-15px;
			width:17px;
			height:30px;
			z-index:888;
		}
		.home-device .arrow-right {
			background:url(crm/index/images/arrows.png) no-repeat left bottom;
			position:absolute;
			right:10px;
			top:50%;
			margin-top:-15px;
			width:17px;
			height:30px;
			z-index:888;
		}
		.swiper-main {
			width: 640px;
			height: 300px;
			position: relative;
		}
		.sm-shadow {
			position: absolute;
			bottom: -15px;
			width: 606px;
			height: 38px;
			left: 17px;
			background: url(crm/index/images/shadow.png);
		}
		.sm-free {
			width: 105px;
			height: 61px;
			background: url(crm/index/images/free.png);
			position: absolute;
			right: -2px;
			top: -2px;
			z-index: 2;
		}
		.swiper1, .swiper1 .swiper-slide {
			width: 640px;
			height: 300px;
		}
		.pagination1 {
			text-align: center;
			margin-top: 5px;
		}
	</style>

</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar"  >
				<div class="m-title">
					Leader CRM 让工作更高效！
				</div>
                <div class="m-left">
                    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true"></a>
                </div>
                <div class="m-right">
                    
                    <a href="javascript:void(0)" class="easyui-menubutton" data-options="iconCls:'icon-more',plain:true,hasDownArrow:false,menu:'#mm',menuAlign:'right'"></a>
                </div>
				
			</div>
		</header>
		
		
		 <div class="home-device"><a class="arrow-left" href="#"></a> <a class="arrow-right" href="#"></a>
		    <div class="swiper-main">
		      <div class="swiper-container swiper1">
		        <div class="swiper-wrapper">
		          <div class="swiper-slide"> <img src="crm/index/images/slider1-1.png"> </div>
		          <div class="swiper-slide"> <img src="crm/index/images/slider1-2.png"> </div>
		          <div class="swiper-slide">
		            <div class="content-slide">
		              <h2 style="margin-top: 0;">触屏手机幻灯片代码</h2>
		              <p>这里地方的幻灯片是可以自己定义一个内容网页幻灯！</p>
		            </div>
		            <div class="content-slide cs-1">
		              <p style="margin: 0;">触屏手机幻灯片代码.</p>
		            </div>
		            <div class="content-slide cs-2">
		              <p style="margin: 0;">触屏手机幻灯片代码.</p>
		            </div>
		            <div class="clearfix"></div>
		          </div>
		        </div>
		      </div>
		    </div>
		<div class="pagination pagination1"></div>
		  </div>
		
		
		<div style="padding:20px 20px" class="plan-list" id="plan-list">
			<a href="crmController.do?customlist" class="easyui-linkbutton" data-options="iconCls:'icon-large-announce',iconAlign:'top',size:'large'" style="margin:10px 10px 10px 10px;">
				&nbsp;&nbsp;公告&nbsp;&nbsp;<span class="m-badge">1</span>
			</a>
			<a href="crmController.do?customlist" class="easyui-linkbutton" data-options="iconCls:'icon-large-approve',iconAlign:'top',size:'large'" style="margin:10px 10px 10px 10px;">
				&nbsp;&nbsp;审批&nbsp;&nbsp;<span class="m-badge">3</span>
			</a>
			<a href="crmController.do?customlist" class="easyui-linkbutton" data-options="iconCls:'icon-large-task',iconAlign:'top',size:'large'" style="margin:10px 10px 10px 10px;">
				&nbsp;&nbsp;任务&nbsp;&nbsp;<span class="m-badge">2</span>
			</a>
			<a href="crmController.do?customlist" class="easyui-linkbutton" data-options="iconCls:'icon-large-checkin',iconAlign:'top',size:'large'" style="margin:10px 10px 10px 10px;">
				&nbsp;&nbsp;签到&nbsp;&nbsp;
			</a>
			<a href="customController.do?customlist" class="easyui-linkbutton" data-options="iconCls:'icon-large-custom',iconAlign:'top',size:'large'" style="margin:10px 10px 10px 10px;">
				&nbsp;&nbsp;客户&nbsp;&nbsp;
			</a>
			<a href="customController.do?contantlist" class="easyui-linkbutton" data-options="iconCls:'icon-large-contact',iconAlign:'top',size:'large'" style="margin:10px 10px 10px 10px;">
				&nbsp;&nbsp;联系人&nbsp;&nbsp;
			</a>
		</div>
		<ul id="list" class="m-list">			
			<li>
				<img class="image" src="crm/images/10.jpg" width="32px" height="32px"/>
				<div class="header">侯哥</div>
				<div class="subtitle">今天14:01</div>
				<div class="content" onclick="window.location.href='setController.do?invite'">
					<p>早安分享小时候总觉得做个女人，漂亮很重要，后来长大点觉得，有品位和气质很重要，再长大一些觉得一生有个男人疼你很重要 ，直到现在才明白，作为一个女人，健康独立精彩的活着最重要</p>
				</div>
			</li>
			<li>				
				<img class="image" src="crm/images/11.jpg" width="32px" height="32px"/>
				<div class="header">小萌</div>
				<div class="subtitle">今天11:30</div>
				<div class="content">
					<p>北京阅兵 </p>
					<p><img src="crm/images/01.jpg" width="250px" height="200px"></p>
				</div>
			</li>
			<li>				
				<img class="image" src="crm/images/12.jpg" width="32px" height="32px"/>
				<div class="header">夏天</div>
				<div class="subtitle">昨天10:59</div>
				<div class="content">
					<p>同样是女人，差别咋这样大呢❓会保养的女人，男人带出去🈶面子，自己也🈶价值！🇨🇳国母，今天亮瞎全世界女人男人的眼</p>
					<p><img src="crm/images/02.jpg" width="250px" height="200px"></p>
				</div>
			</li>
        </ul>
		<footer id="footer">
			<a href="crmLoginController.do?index"><div class="bottom1"><div class="home"></div><span id="yellow">主页</span></div></a>
			<a href="crmController.do?messagelist"><div class="bottom2"><div class="msg"></div><span>消息</span></div></a>
			<a href="crmController.do?crmlist"><div class="bottom3"><div class="wo"></div><span>客户</span></div></a>
			<a href="crmController.do?oalist"><div class="bottom4"><div class="oa"></div><span>办公</span></div></a>
			<a href="crmController.do?morelist"><div class="bottom5"><div class="geng"></div><span>更多</span></div></a>
		</footer>
		<%-- 
		<footer >
            <div class="m-buttongroup m-buttongroup-justified" style="width:100%;background: #333333;color: white;">
                <a style="background: #333333;color: white;font-size: 10px;" href="crmLoginController.do?index" class="easyui-linkbutton" data-options="iconCls:'icon-home',size:'large',iconAlign:'top',plain:true">主页</a>
                <a style="background: #333333;color: white;font-size: 10px;" href="crmController.do?messagelist" class="easyui-linkbutton" data-options="iconCls:'icon-msg',size:'large',iconAlign:'top',plain:true">消息</a>
                <a style="background: #333333;color: white;font-size: 10px;" href="crmController.do?crmlist" class="easyui-linkbutton" data-options="iconCls:'icon-custom',size:'large',iconAlign:'top',plain:true">客户</a>
                <a style="background: #333333;color: white;font-size: 10px;" href="crmController.do?oalist" class="easyui-linkbutton" data-options="iconCls:'icon-oa',size:'large',iconAlign:'top',plain:true">办公</a>
                <a style="background: #333333;color: white;font-size: 10px;" href="crmController.do?user" class="easyui-linkbutton" data-options="iconCls:'icon-user',size:'large',iconAlign:'top',plain:true">个人</a>
            </div>
		</footer>
		--%>
	</div>
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div data-options="iconCls:'icon-mini-refresh'" onclick="window.location.href='crmLoginController.do?index'">刷新</div>
	</div>

	<div id="p2" class="easyui-navpanel">
        <header>
            <div class="m-toolbar">
                <span id="p2-title" class="m-title">详情</span>
                <div class="m-left">
                    <a href="javascript:void(0)" class="easyui-linkbutton m-back" plain="true" outline="true" style="width:50px" onclick="$.mobile.back()">返回</a>
                </div>
            </div>
        </header>
		<p style="font-size:14px">公司介绍</p>
		<ul>
			<li>企业规模：3000人以上</li>
			<li>经营范围：钢铁</li>
			<li>公司性质：民营企业</li>
			<li>企业法人：张三</li>
			<li>联系电话：020-90020229</li>
			<li>联系地址：北京市</li>
		</ul>
        <div style="margin:50px 0 0;text-align:center">
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px" onclick="$.mobile.back()">返回首页</a>
        </div>
    </div>
</body>
</html>