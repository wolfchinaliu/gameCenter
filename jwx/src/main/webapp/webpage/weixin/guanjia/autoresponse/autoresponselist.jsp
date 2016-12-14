<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
<t:datagrid name="autoresponselist" actionUrl="autoResponseController.do?datagrid" fit="true" title="关键字回复" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false" ></t:dgCol>
	<t:dgCol title="关键字" field="keyWord" query="true" width="100"></t:dgCol>
	<t:dgCol title="名称" field="templateName" width="100"></t:dgCol>
	<t:dgCol title="时间" field="addTime" width="100"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgDelOpt title="删除" url="autoResponseController.do?del&id={id}" />
	<t:dgToolBar title="关键字录入" icon="icon-add" url="autoResponseController.do?addOrUpdate" funname="add"></t:dgToolBar>
 	<t:dgToolBar title="关键字编辑" icon="icon-edit" url="autoResponseController.do?addOrUpdate" funname="update"></t:dgToolBar>
</t:datagrid>
</div></div>

