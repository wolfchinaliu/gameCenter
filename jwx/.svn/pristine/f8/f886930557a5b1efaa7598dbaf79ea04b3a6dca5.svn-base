<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid name="merchantChargeList" checkbox="true" fitColumns="false" title="我的流量充值记录"
                    actionUrl="merchantChargeReportController.do?chargeRecordsList" idField="id" fit="true" queryMode="group">
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
         	<%-- <t:dgCol title="商户名称"  field="acctName" hidden="true"  queryMode="single" query="false" width="120"></t:dgCol>
            <t:dgCol title="商户等级" field="acctLevel" width="100" replace="A_1,B_2,C_3" query="false" hidden="true"></t:dgCol> --%>
            <t:dgCol title="流量类型" field="flowType" width="100" replace="全国流量_1,省内流量_2" query="false" hidden="true"></t:dgCol>
            <t:dgCol title="流量值" field="flowValue" hidden="true" query="false"></t:dgCol>
            <t:dgCol title="充值时间"  field="chargetime" hidden="true"  queryMode="group" query="true" width="140" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
            <t:dgCol title="描述" field="des" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgToolBar title="excel导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
    //给时间控件加上样式
      $("#merchantChargeListtb").find("input[name='chargetime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
      $("#merchantChargeListtb").find("input[name='chargetime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	});
    //导出
    function ExportXls() {
        JeecgExcelExport("merchantChargeReportController.do?exportMerchantChargeXls", "merchantChargeList");
    }

</script>
