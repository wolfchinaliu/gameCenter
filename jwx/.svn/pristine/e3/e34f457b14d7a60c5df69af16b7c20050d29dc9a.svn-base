<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div id="main_depart_list" class="easyui-layout" fit="true"
	style="border: 0px;">
	<div region="center" style="padding: 1px; border: 0px;">
		<t:datagrid name="weixinMenuGroupList" title="微信菜单组" actionUrl="weixinMenuGroupController.do?datagrid" idField="menuGroupId" pagination="false" onClick="queryMenuListByRowData">
			<t:dgCol title="编号" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="菜单组名称" field="groupName" hidden="true" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="最后同步时间" field="synchDate" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="公众帐号ID" field="accountid" hidden="false" queryMode="single" width="120"></t:dgCol>
			<t:dgToolBar title="菜单组录入" icon="icon-add" url="weixinMenuGroupController.do?goAdd" funname="add"></t:dgToolBar>
			<t:dgToolBar title="菜单组编辑" icon="icon-edit" url="weixinMenuGroupController.do?goUpdate" funname="update"></t:dgToolBar>
			<t:dgToolBar title="同步到微信" icon="icon-ok" funname="synchWeixin"></t:dgToolBar>
			<t:dgCol title="操作" field="opt"></t:dgCol>
			<t:dgDelOpt title="删除" url="weixinMenuGroupController.do?doDel&id={id}" />
		</t:datagrid>
		<div id="departListtb"
			style="padding: 3px; height: 25px; display: none;">
			<div style="float: left;"></div>
		</div>
	</div>
</div>
<div data-options="region:'east',
	title:'成员列表',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
	style="width: 500px; overflow: hidden;">
<div class="easyui-panel" style="padding: 1px;" fit="true" border="false" id="menuListpanel">


	</div>
</div>

<script type="text/javascript">
<!--
$(function() {
	var li_east = 0;
});

function queryMenuListByRowData(rowIndx,rowData){
	if(li_east == 0){
	   $('#main_depart_list').layout('expand','east'); 
	}
	$('#menuListpanel').panel("refresh", "weixinMenuGroupController.do?menuInfoList&menuGroupId=" + rowData.id);
}

function queryMenuByGroupId(menuGroupId){
	if(li_east == 0){
	   $('#main_depart_list').layout('expand','east'); 
	}
	$('#menuListpanel').panel("refresh", "weixinMenuGroupController.do?menuInfoList&menuGroupId=" + menuGroupId);
}

function synchWeixin(){
	var rowsData = $('#weixinMenuGroupList').datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择编辑项目');
		return;
	}
	if (rowsData.length>1) {
		tip('请选择一条记录再同步');
		return;
	}
	var menuGroupId=rowsData[0].id;
	$.dialog.confirm("确认同步该组菜单数据?", function(){
		$.ajax({
			url:"weixinMenuGroupController.do?synchMenu",
			type:"POST",
			dataType:"JSON",
			data:{menuGroupId:menuGroupId},
			success:function(data){
				if(data.success){
					tip(data.msg);
				}
			}
		});
	});
}
//-->
</script>