<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid name="userGetFlowList" checkbox="true" fitColumns="false" title="用户充值到账记录"
                    actionUrl="reportCountController.do?userGetFlowdatagrid" idField="id" fit="true" queryMode="group">
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="流量类型" field="flowType" hidden="true" queryMode="single" query="true" width="120"></t:dgCol>
            <t:dgCol title="充值时间" field="gettime" hidden="true" query="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="充值流量" field="flowValue" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="手机号码" field="phoneNumber" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="手机号码归属地" field="phoneNumberLocation" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="充值状态" field="state" hidden="true" query="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="商户id" field="accountId" hidden="false" queryMode="single" width="120"></t:dgCol>
        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
    function edit(id) {
        var url = "weixinAcctController.do?goUpdateMyacct&id=" + id;
//        createdetailwindow('充值页面', 'weixinAcctFlowAccountController.do?goCharge&id=' + id, 'weixinAcctFlowAccountList', '100%', '100%');
        add('编辑页面', 'weixinAcctController.do?goUpdateMyacct&id=' + id, 'weixinAcctList', '100%', '100%');
    }
    $(document).ready(function () {
        //给时间控件加上样式
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
        JeecgExcelExport("weixinAcctController.do?exportXls", "weixinAcctList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("weixinAcctController.do?exportXlsByT", "weixinAcctList");
    }
</script>