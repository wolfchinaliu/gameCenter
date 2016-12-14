<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding: 1px;">
    <t:datagrid name="userGetFlowList" checkbox="true" fitColumns="false" title="新增粉丝记录"
      actionUrl="reportCountController.do?newMemberDatagrid" idField="id" fit="true" queryMode="group">
      <t:dgCol title="主键" field="id" hidden="false" width="120"></t:dgCol>
      <t:dgCol title="新增数量" field="num" hidden="true" query="false" width="120"></t:dgCol>
      <t:dgCol title="时间" field="time" hidden="true" query="false" width="200"></t:dgCol>
      <t:dgCol title="时间" field="querytime" hidden="false" queryMode="group" query="true" width="100"></t:dgCol>
      <t:dgCol title="统计频率" field="unit" hidden="false" width="100" replace="全部_all,日_day,周_week,月_month,年_year" query="true"></t:dgCol>
    </t:datagrid>
  </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    //给时间控件加上样式
    $("#userGetFlowListtb").find("input[name='querytime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
    $("#userGetFlowListtb").find("input[name='querytime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
});
	//导出
	function ExportXls() {
		JeecgExcelExport("reportCountController.do?exportXls",
				"userGiveFlowList");
	}
</script>