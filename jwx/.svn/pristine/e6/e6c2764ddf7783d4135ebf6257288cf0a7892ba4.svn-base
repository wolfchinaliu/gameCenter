<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" id="main_depart_list">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="weixinCustomerList" checkbox="true"
			fitColumns="false" title="客服表"
			actionUrl="weixinCustomerController.do?datagrid" idField="id"
			fit="true" queryMode="group" onClick="queryMenuListByRowData">
			<t:dgCol title="主键" field="id" hidden="false" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="创建人名称" field="createName" hidden="false"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="创建日期" field="createDate" hidden="false"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="优先顺序" field="sorts" hidden="true" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="是否接收消息" field="receiveMessages" hidden="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="微信主表" field="accountid" hidden="false"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="微信openId" field="openId" hidden="true"
				queryMode="single" query="true" width="120"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
			<t:dgDelOpt title="删除"
				url="weixinCustomerController.do?doDel&id={id}" />
			<t:dgToolBar title="录入" icon="icon-add"
				url="weixinCustomerController.do?goAdd" funname="add"></t:dgToolBar>
			<t:dgToolBar title="编辑" icon="icon-edit"
				url="weixinCustomerController.do?goUpdate" funname="update"></t:dgToolBar>
			<%--    <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinCustomerController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
			<t:dgToolBar title="查看" icon="icon-search"
				url="weixinCustomerController.do?goUpdate" funname="detail"></t:dgToolBar>
			<t:dgToolBar title="客服链接" icon="icon-search"
				 funname="popMenuLink"></t:dgToolBar>	
				
			<%--    <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
		</t:datagrid>
		<div id="departListtb"
			style="padding: 3px; height: 25px; display: none;">
			<div style="float: left;"></div>
		</div>
	</div>
</div>
<div
	data-options="region:'east',
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
	style="width:530px; overflow: hidden;">
	<div class="easyui-panel" style="padding: 1px;" fit="true"
		border="false" id="menuListpanel"></div>
</div>
</div>
</div>
<script src="webpage/weixin/customer/weixinCustomerList.js"></script>
<script type="text/javascript">
	$(function() {
		var li_east = 0;
	});
	function queryMenuListByRowData(rowIndx, rowData) {
		if (li_east == 0) {
			$('#main_depart_list').layout('expand', 'east');
		}
		$('#menuListpanel').panel("refresh", "weixinMemberController.do?memberInfoList&openId=" + rowData.openId);
	}
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'weixinCustomerController.do?upload',
				"weixinCustomerList");
	}

	//导出
	function ExportXls() {
		JeecgExcelExport("weixinCustomerController.do?exportXls",
				"weixinCustomerList");
	}

	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("weixinCustomerController.do?exportXlsByT",
				"weixinCustomerList");
	}
	function popMenuLink(id){
        $.dialog({
			content: "url:weixinCustomerController.do?weixinCustomerAddress",
			drag :false,
			lock : true,
			title:'客服链接',
			opacity : 0.3,
			width:650,
			height:80,drag:false,min:false,max:false
		}).zindex();
	}
</script>