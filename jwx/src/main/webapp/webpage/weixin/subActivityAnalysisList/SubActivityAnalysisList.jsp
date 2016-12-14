<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid name="subActivityAnalysisList" checkbox="true" fitColumns="false" title="活动分析"
                    actionUrl="subActivityAnalysisController.do?datagrid" fit="true" queryMode="group">
            <t:dgCol title="商户名称" field="subacctForName" hidden="true" query="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="商户等级" field="acctLevel" width="100" replace="A级_1,B级_2,C级_3" query="true" hidden="true"></t:dgCol>
            <t:dgCol title="所属商户"  field="acctForName" hidden="true" queryMode="single" query="true" width="140"></t:dgCol>        
            <t:dgCol title="活动类型" field="reason" hidden="true" queryMode="single" query="true" width="120"></t:dgCol>
            <t:dgCol title="参与人次" field="sumId" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="赠送总流量" field="sumValue" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="平均赠送流量" field="avgValue" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="商户id" field="accountId" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="日期"  field="operateDate" hidden="true"  queryMode="group" query="true" width="120"></t:dgCol>
            <t:dgToolBar title="excel导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>
<script type="text/javascript">

$(document).ready(function(){
	//给时间控件加上样式
//	$("#activityAnalysisListtb").css("height", "60px");
		$("#subActivityAnalysisListtb").find("input[name='operateDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#subActivityAnalysisListtb").find("input[name='operateDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$('#subActivityAnalysisListtb').find("input[name='acctForName']").combobox({
	  		url: 'weixinAcctController.do?NotCAllSubAcct',
	  		editable: false, //不可编辑状态
	  		cache: false,
	  		valueField: 'code',
	  		textField: 'name',
	  		resizable:true,
	  		multiple:false
	  	});  

}); 
   
    //导出
    function ExportXls() {
        JeecgExcelExport("subActivityAnalysisController.do?exportXls", "subActivityAnalysisList");
    }

</script>