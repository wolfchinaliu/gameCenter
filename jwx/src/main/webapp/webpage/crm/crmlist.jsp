<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>灵动移动CRM</title>
</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<div class="m-title">
					客户管理
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
				<div class="header">客户跟踪</div>
				<div class="content">5个跟踪</div>
				</a>
			</li>
			
			<li class="m-list-group"></li>
			<li onclick="window.location.href='customController.do?contactlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/whatsapp.png"/>
				<div class="header">联系人</div>
				<div class="content">客户联系人信息</div>
				</a>
			</li>
			<li onclick="window.location.href='customController.do?customlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/customer.png"/>
				<div class="header">客户</div>
				<div class="content">客户信息</div>
				</a>
			</li>
			<li onclick="window.location.href='customController.do?customlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/contacts.png"/>
				<div class="header">机会管理</div>
				<div class="content">潜在客户开发</div>
				</a>
			</li>
			<li onclick="window.location.href='customController.do?customlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/groups1.png"/>
				<div class="header">业务管理</div>
				<div class="content">跟踪、业务</div>
				</a>
			</li>			
			<li onclick="window.location.href='customController.do?customlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/groups4.png"/>
				<div class="header">销售管理</div>
				<div class="content">成交</div>
				</a>
			</li>
			<li onclick="window.location.href='customController.do?customlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/groups3.png"/>
				<div class="header">合同管理</div>
				<div class="content"></div>
				</a>
			</li>
			<li onclick="window.location.href='customController.do?customlist'">
				<a href="javascript:void(0)">
				<img class="image" src="crm/icon/tu.png"/>
				<div class="header">销售统计</div>
				<div class="content">业绩、指标</div>
				</a>
			</li>
        </ul>
       	<footer id="footer">
			<a href="crmLoginController.do?index"><div class="bottom1"><div class="home"></div><span>主页</span></div></a>
			<a href="crmController.do?messagelist"><div class="bottom2"><div class="msg"></div><span>消息</span></div></a>
			<a href="crmController.do?crmlist"><div class="bottom3"><div class="wo"></div><span id="yellow">客户</span></div></a>
			<a href="crmController.do?oalist"><div class="bottom4"><div class="oa"></div><span>办公</span></div></a>
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
		<div onclick="window.location.href='customController.do?addContact'" data-options="iconCls:'icon-add'">添加联系人</div>
		<div class="menu-sep"></div>
		<div onclick="window.location.href='customController.do?addCustom'" data-options="iconCls:'icon-add'">添加客户</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-add'">添加机会</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-add'">添加合同</div>
	</div>
</body>
</html>