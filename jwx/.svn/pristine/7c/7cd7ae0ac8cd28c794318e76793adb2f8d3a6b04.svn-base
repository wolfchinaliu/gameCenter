<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid name="weixinAcctList" checkbox="true" fitColumns="false" title="商户管理表" sortName="createDate" sortOrder="desc"
                    actionUrl="weixinAcctController.do?maccdatagrid" idField="id" fit="true" queryMode="group"> 
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="登录名称" field="username" hidden="true" query="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="商户名称" field="acctForName" hidden="true" query="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="所属商户" field="belogAcct" hidden="true" query="true" queryMode="single" width="120"></t:dgCol>
            <%--<t:dgCol title="商户级别" field="acct_level" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
            <t:dgCol title="商户级别" dictionary="sAcclevel" field="acctLevel" query="true" hidden="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="商业类型" dictionary="busiType" field="businessType" query="true" hidden="true"
                     queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="省份" field="province" hidden="true" queryMode="single" query="true" width="120"></t:dgCol>
            <t:dgCol title="市名" field="city" hidden="true" queryMode="single" query="true" width="120"></t:dgCol>
            <%--<t:dgCol title="市名" dictionary="city_jx" field="city" hidden="true" query="true" queryMode="single"--%>
            <%--width="120"></t:dgCol>--%>
            <%--<t:dgCol title="市/区" field="city" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
            <t:dgCol title="可开商户数" field="totalAccount" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="已开商户数" field="opendedAccount" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="状态" field="status" hidden="true" queryMode="single" width="120"></t:dgCol>

            <t:dgCol title="操作" field="opt" width="200"></t:dgCol>
            <t:dgFunOpt funname="edit(id)" title="编辑"></t:dgFunOpt>
            <%--<t:dgFunOpt funname="InCharge(id)" title="直管"></t:dgFunOpt>--%>
            <t:dgFunOpt funname="editPWD(id)" title="密码重置"></t:dgFunOpt>
            <t:dgCol title="电话" field="mobile_phone" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="邮箱" field="email" hidden="false" queryMode="single" width="120"></t:dgCol>
            <%--<t:dgCol title="QQ" field="qqNumber" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
            <%--<t:dgCol title="群发次数" field="smsnum" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
            <%--<t:dgCol title="图文次数" field="newsnum" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
            <%--<t:dgCol title="请求次数" field="requestnum" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
            <%--<t:dgCol title="员工账号个数" field="usernum" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
            <%--<t:dgCol title="公众号个数" field="accountnum" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
            <%--<t:dgCol title="域名地址" field="domainurl" hidden="false" queryMode="single" width="120"></t:dgCol>--%>

            <%--<t:dgToolBar title="编辑" icon="icon-edit" url="weixinAcctController.do?goUpdateMyacct"--%>
            <%--funname="update"></t:dgToolBar>--%>

            <%--<t:dgToolBar title="查看" icon="icon-search" url="weixinAcctController.do?myacctView"--%>
            <%--funname="detail"></t:dgToolBar>--%>

            <t:dgToolBar title="新增" icon="icon-add" url="weixinAcctController.do?goAdd"
                         funname="add" width="1000" height="550"></t:dgToolBar>
            <t:dgToolBar title="生成apiId和apiKey" icon="icon-ruby" url="weixinAcctController.do?goProduceIdKey"
                         funname="add"></t:dgToolBar>

        </t:datagrid>
    </div>
</div>
<script src="webpage/weixin/tenant/weixinAcctList.js"></script>
<script type="text/javascript">

	$("#weixinAcctListtb").css("height", "60px");

    function edit(id) {
        //var url = "weixinAcctController.do?goUpdateMyacct&id=" + id;
//        createdetailwindow('充值页面', 'weixinAcctFlowAccountController.do?goCharge&id=' + id, 'weixinAcctFlowAccountList', '100%', '100%');
        add('编辑页面', 'weixinAcctController.do?goUpdateMyacct&id=' + id, 'weixinAcctList', '100%', '100%');
    }
    $(document).ready(function () {
        //给时间控件加上样式
    });

    function editPWD(id) {
        //var url = "weixinAcctController.do?goUpdateMyacct&id=" + id;
//        createdetailwindow('密码重置页面', 'weixinAcctFlowAccountController.do?goCharge&id=' + id, 'weixinAcctFlowAccountList', '100%', '100%');
        add('密码重置页面', 'weixinAcctController.do?Acctchangepassword&id=' + id, 'weixinAcctList', '100%', '100%');
    }

    <%--function add(title, addurl, gname, width, height) {--%>
    <%--var acclevel = "${currAcct.acctLevel}"--%>
    <%--if (acclevel != '3') {--%>

    <%--createwindow(title, addurl, width, height);--%>
    <%--} else {--%>

    <%--tip('您目前的权限是C级，无权进行商户添加操作!');--%>
    <%--return;--%>
    <%--}--%>
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