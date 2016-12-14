<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid name="userGiveFlowList" checkbox="true" fitColumns="false" title="用户流量币领取记录"
                    actionUrl="reportCountController.do?userFlowdatagrid" idField="id" fit="true" queryMode="group">
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="用户昵称" field="nickname" hidden="true" query="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="手机号" field="phoneNumber" hidden="true" queryMode="single" width="120" query="true"></t:dgCol>
            <t:dgCol title="流量类型" field="flowType" replace="全国流量_1,省内流量_2" hidden="true" queryMode="single" query="true" width="120"></t:dgCol>
            <t:dgCol title="活动类型" field="hdType" hidden="true" query="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="领取时间"  field="gettime" hidden="true"  queryMode="group" query="true" width="120"></t:dgCol>
            <t:dgCol title="领取流量" field="flowValue" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="商户id" field="accountId" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgToolBar title="excel导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
        $("#userGiveFlowListtb").css("height","60px"); 
 
 
    function edit(id) {
        var url = "weixinAcctController.do?goUpdateMyacct&id=" + id;
//        createdetailwindow('充值页面', 'weixinAcctFlowAccountController.do?goCharge&id=' + id, 'weixinAcctFlowAccountList', '100%', '100%');
        add('编辑页面', 'weixinAcctController.do?goUpdateMyacct&id=' + id, 'weixinAcctList', '100%', '100%');
    }
    $(document).ready(function(){
		//给时间控件加上样式
			$("#userGiveFlowListtb").find("input[name='gettime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
			$("#userGiveFlowListtb").find("input[name='gettime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
}); 
    function editPWD(id) {
        var url = "weixinAcctController.do?goUpdateMyacct&id=" + id;
//        createdetailwindow('密码重置页面', 'weixinAcctFlowAccountController.do?goCharge&id=' + id, 'weixinAcctFlowAccountList', '100%', '100%');
        add('密码重置页面', 'weixinAcctController.do?Acctchangepassword&id=' + id, 'weixinAcctList', '100%', '100%');
    }
    //    }
    $(document).ready(function () {
        //给时间控件加上样式
    });
    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'weixinAcctController.do?upload', "weixinAcctList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("reportCountController.do?exportXls", "userGiveFlowList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("weixinAcctController.do?exportXlsByT", "weixinAcctList");
    }
</script>