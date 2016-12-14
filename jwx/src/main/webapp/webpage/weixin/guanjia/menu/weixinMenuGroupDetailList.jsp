<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
	
<t:datagrid  actionUrl="weixinMenuGroupController.do?menuDatagrid&menuGroupId=${menuGroupId}"  name="menuList" queryMode="group" treegrid="true" fitColumns="true" idField="id">
	<t:dgCol field="id" treefield="id" title="主键" hidden="false"></t:dgCol>
	<t:dgCol field="name" treefield="text" title="菜单名称" query="true" width="100"></t:dgCol> 
	<t:dgCol field="type" treefield="src" title="菜单类型" replace="消息触发类_click,网页链接类_view" width="100" ></t:dgCol>
	<t:dgCol field="orders" treefield="order" title="顺序" width="100"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgDelOpt title="删除" url="menuManagerController.do?del&id={id}" />
	<t:dgToolBar title="菜单录入" icon="icon-add" url="menuManagerController.do?addorupdate&menuGroupId=${menuGroupId}" funname="add"></t:dgToolBar>
	<t:dgToolBar title="菜单编辑" icon="icon-edit" url="menuManagerController.do?addorupdate&menuGroupId=${menuGroupId}" funname="update"></t:dgToolBar>
</t:datagrid>



