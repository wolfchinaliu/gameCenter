<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>灵动移动CRM</title>
    <style type="text/css">
		.image{
			width: 16px;
			height: 16px;
			border: 0;
			margin-right: 5px;
			float: left;
		}
		#m-list .li a{
			font-size: 14px;
			font-weight: bold;
		}
	</style>
</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<div class="m-title">
					个人办公
				</div>
                <div class="m-left">
                    
                </div>
                <div class="m-right">
                    
                    <a href="javascript:void(0)" class="easyui-menubutton" data-options="iconCls:'icon-more',plain:true,hasDownArrow:false,menu:'#mm',menuAlign:'right'"></a>
                </div>
				
			</div>
		</header>

		<ul id="list" class="m-list">
			<li>
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/groups2.png"/>
				<div class="header">待办事项</div>
				<div class="content">1个事项</div>
				</a>
			</li>
			<li class="m-list-group"></li>
			<li>
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/live-essenger.png"/>
				<div class="header">圈子</div>
				<div class="content">企业信息互动</div>
				</a>
			</li>
			<li onclick="window.location.href='customController.do?contactlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/personal.png"/>
				<div class="header">通讯录</div>
				<div class="content">企业联系</div>
				</a>
			</li>
            <li onclick="window.location.href='customController.do?contactlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/livesync.png"/>
				<div class="header">审批</div>
				<div class="content">请假、报销、各类审批</div>
				</a>
			</li>
			<li onclick="window.location.href='customController.do?contactlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/calendar.png"/>
				<div class="header">日程</div>
				<div class="content">任务、日志</div>
				</a>
			</li>
			<li onclick="window.location.href='customController.do?contactlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/clock.png"/>
				<div class="header">签到</div>
				<div class="content">考勤</div>
				</a>
			</li>
			<li onclick="window.location.href='customController.do?contactlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/notepad.png"/>
				<div class="header">知识库</div>
				<div class="content">学习、分享</div>
				</a>
			</li>
           
        </ul>
       	<footer id="footer">
			<a href="crmLoginController.do?index"><div class="bottom1"><div class="home"></div><span>主页</span></div></a>
			<a href="crmController.do?messagelist"><div class="bottom2"><div class="msg"></div><span>消息</span></div></a>
			<a href="crmController.do?crmlist"><div class="bottom3"><div class="wo"></div><span>客户</span></div></a>
			<a href="crmController.do?oalist"><div class="bottom4"><div class="oa"></div><span id="yellow">办公</span></div></a>
			<a href="crmController.do?morelist"><div class="bottom5"><div class="geng"></div><span>更多</span></div></a>
		</footer>
		<%-- 
		<footer>
            <div class="m-buttongroup m-buttongroup-justified" style="width:100%">
                <a href="crmLoginController.do?index" class="easyui-linkbutton" data-options="iconCls:'icon-home',size:'large',iconAlign:'top',plain:true">主页</a>
                <a href="crmController.do?messagelist" class="easyui-linkbutton" data-options="iconCls:'icon-msg',size:'large',iconAlign:'top',plain:true">消息</a>
                <a href="crmController.do?crmlist" class="easyui-linkbutton" data-options="iconCls:'icon-custom',size:'large',iconAlign:'top',plain:true">客户</a>
                <a href="crmController.do?oalist" class="easyui-linkbutton" data-options="iconCls:'icon-oa',size:'large',iconAlign:'top',plain:true">办公</a>
                <a href="crmController.do?user" class="easyui-linkbutton" data-options="iconCls:'icon-user',size:'large',iconAlign:'top',plain:true">个人</a>
            </div>
		</footer>
		--%>
	</div>
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div data-options="iconCls:'icon-add'">新增流程</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-add'">新增日程</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-add'">新增任务</div>
	</div>
</body>
</html>