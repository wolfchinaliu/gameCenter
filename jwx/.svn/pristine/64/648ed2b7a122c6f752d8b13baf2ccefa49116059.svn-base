<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="weixinDrawDetailList" checkbox="false"
			fitColumns="false" title="抽奖记录表"
			actionUrl="weixinDrawDetailController.do?datagrid&hdid=${hdid }&opendid=${opendid }"
			idField="id" fit="true" queryMode="group">
			<t:dgCol title="id" field="id" hidden="false" queryMode="group"
				width="120"></t:dgCol>
			<t:dgCol title="抽奖时间" field="addtime" formatter="yyyy-MM-dd"
				hidden="true" queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="抽奖ID" field="drawrecordid" hidden="false" query="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="accountid" field="accountid" hidden="false"
				queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="抽奖人ID" field="opendid" hidden="true"
				queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="抽奖角度" field="angle" hidden="true" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="信息" field="msg" hidden="true" queryMode="single"
				width="120"></t:dgCol>
			<%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinDrawDetailController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinDrawDetailController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinDrawDetailController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinDrawDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinDrawDetailController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
		</t:datagrid>
	</div>
</div>
<script src="webpage/weixin/lottery/weixinDrawDetailList.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				//给时间控件加上样式
				$("#weixinDrawDetailListtb")
						.find("input[name='addtime_begin']").attr("class",
								"Wdate").attr("style",
								"height:20px;width:90px;").click(function() {
							WdatePicker({
								dateFmt : 'yyyy-MM-dd'
							});
						});
				$("#weixinDrawDetailListtb").find("input[name='addtime_end']")
						.attr("class", "Wdate").attr("style",
								"height:20px;width:90px;").click(function() {
							WdatePicker({
								dateFmt : 'yyyy-MM-dd'
							});
						});
			});

	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'weixinDrawDetailController.do?upload',
				"weixinDrawDetailList");
	}

	//导出
	function ExportXls() {
		JeecgExcelExport("weixinDrawDetailController.do?exportXls",
				"weixinDrawDetailList");
	}

	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("weixinDrawDetailController.do?exportXlsByT",
				"weixinDrawDetailList");
	}
</script>