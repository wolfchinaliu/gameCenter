<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
	<div region="center" style="padding: 1px; border: 0px;">
		<t:datagrid name="weixinSafetyRulesList" checkbox="true" fitColumns="false" title="安全规则设定"
			actionUrl="safetyRulesController.do?mydatagrid" idField="id" fit="true" queryMode="group">

			<t:dgCol title="规则名称" field="ruleName" query="true" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="活动ID" field="id" hidden="true" queryMode="single" width="240"></t:dgCol>

			<t:dgCol title="频率" field="frequencyUnit" query="true" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="频率内最大调用次数" field="frequencyNum" hidden="true" queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="充值流量最大值M" field="charegeFlow" queryMode="single" width="120"></t:dgCol>

			<%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol> --%>
			<%-- <t:dgConfOpt title="删除" url="safetyRulesController.do?doDel&id={id}" message="是否确定删除吗？"/> --%>

			<%-- <t:dgToolBar title="编辑" icon="icon-edit" url="safetyRulesController.do?goUpdate" funname="update" ></t:dgToolBar> --%>
			<t:dgToolBar title="新增" icon="icon-add" url="safetyRulesController.do?goAdd" funname="add"></t:dgToolBar>

			<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>

		</t:datagrid>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(
			function() {
				//给时间控件加上样式
				$("#pptListtb").find("input[name='operatetime_begin']").attr(
						"class", "Wdate").attr("style",
						"height:20px;width:90px;").click(function() {
					WdatePicker({
						dateFmt : 'yyyy-MM-dd'
					});
				});
				$("#pptListtb").find("input[name='operatetime_end']").attr(
						"class", "Wdate").attr("style",
						"height:20px;width:90px;").click(function() {
					WdatePicker({
						dateFmt : 'yyyy-MM-dd'
					});
				});
			});

	//导出
	function ExportXls() {
		JeecgExcelExport("safetyRulesController.do?exportXls",
				"weixinSafetyRulesList");
	}
</script>