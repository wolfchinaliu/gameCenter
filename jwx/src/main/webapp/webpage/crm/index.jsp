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
	<title>灵动移动CRM</title>
	<style type="text/css">
		#plan-list a{
			width:80px;
			height:70px;
			padding-top: 10px;
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
                </div>
				
			</div>
		</header>
		<div class="easyui-tabs" data-options="fit:true,border:false,pill:false,justified:true,tabWidth:80,tabHeight:35">
		<div title="导航" style="padding:10px">
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
		</div>
		<div title="动态" style="padding:10px">
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
		</div>
		<div title="待办" style="padding:10px">
			<ul id="list" class="m-list">
	            <li><a href="">财务第三季度需求<span style="float: right;">2015-09-05</span></a></li>
	            <li><a href="">出差补助财务报销<span style="float: right;">2015-09-04</span></a></li>
	            
	        </ul>
		</div>
		<div title="日程" style="padding:10px">
			
		</div>
		
	</div>
		<footer id="footer">
			<a href="crmLoginController.do?index"><div class="bottom1"><div class="home"></div><span id="yellow">主页</span></div></a>
			<a href="crmController.do?messagelist"><div class="bottom2"><div class="msg"></div><span>消息</span></div></a>
			<a href="crmController.do?crmlist"><div class="bottom3"><div class="wo"></div><span>客户</span></div></a>
			<a href="crmController.do?oalist"><div class="bottom4"><div class="oa"></div><span>办公</span></div></a>
			<a href="crmController.do?morelist"><div class="bottom5"><div class="geng"></div><span>更多</span></div></a>
		</footer>
	</div>
	
</body>
</html>