<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="system_role_roleList" class="easyui-layout" fit="true" style="border:0;">
<div region="center" style="border:0;"><t:datagrid name="roleList" title="角色列表" actionUrl="roleController.do?roleGrid" idField="id">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="角色编码" field="roleCode"></t:dgCol>
	<t:dgCol title="角色名称" field="roleName" query="false"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgFunOpt funname="delRole(id)" title="删除"></t:dgFunOpt>
	<t:dgFunOpt funname="setfunbyrole(id,roleName)" title="权限设置"></t:dgFunOpt>
	<t:dgToolBar title="角色录入" icon="icon-add" url="roleController.do?addorupdate" funname="add"></t:dgToolBar>
	<t:dgToolBar title="角色编辑" icon="icon-edit" url="roleController.do?addorupdate" funname="update"></t:dgToolBar>
</t:datagrid></div>
</div>
<div data-options="region:'east',
	title:'权限设置',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
	style="width: 400px; overflow: hidden;">
<div class="easyui-panel" style="padding: 1px;" fit="true" border="false" id="operationDetailpanel"></div>
</div>
</div>

<script type="text/javascript">
<%--   update-start--Author:anchao  Date:20130415 for：按钮权限控制--%>
$(function() {
	var li_east = 0;
});
function setfunbyrole(id,roleName)
{

	if(li_east == 0){
	   $('#system_role_roleList').layout('expand','east'); 
	}
	$('#operationDetailpanel').panel("refresh", "roleController.do?fun&roleId=" +id);
}
//删除角色
function delRole(id){
	var tabName= 'roleList';
	var url= 'roleController.do?delRole&id='+id;
	$.dialog.confirm('确定删除该记录吗', function(){
		doSubmit(url,tabName);
		rowid = '';
		$("#function-panel").html("");//删除角色后，清空对应的权限
	}, function(){
	});
}
</script>
<%-- 
<div region="east" style="width: 400px;" split="true">
<div tools="#tt" class="easyui-panel" title="权限设置" style="padding: 10px;" fit="true" border="false" id="function-panel"></div>
</div>
<div id="tt"></div>
</div>
<script type="text/javascript">
function setfunbyrole(id,roleName) {
	$("#function-panel").panel(
		{
			title :roleName+":当前权限",
			href:"roleController.do?fun&roleId=" + id
		}
	);
	$('#function-panel').panel("refresh");
	
}
//删除角色
function delRole(id){
	var tabName= 'roleList';
	var url= 'roleController.do?delRole&id='+id;
	$.dialog.confirm('确定删除该记录吗', function(){
		doSubmit(url,tabName);
		rowid = '';
		$("#function-panel").html("");//删除角色后，清空对应的权限
	}, function(){
	});
}
</script>
--%>