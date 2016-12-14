<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<%--<div style="padding:5px 15px;color:#a94442;font-size:x-large">--%>
    <%--<strong>当前商户的全国流量币值是：${weixinAcctFlowChargePage.countryFlowValue}M;本省流量币值是：${weixinAcctFlowChargePage.provinceFlowValue}M</strong>--%>
<%--</div>--%>
<div class="easyui-layout" fit="true" style="border: 0px;">
    <strong>当前商户的全国流量币值是：${weixinAcctFlowChargePage.countryFlowValue}M;本省流量币值是：${weixinAcctFlowChargePage.provinceFlowValue}M</strong>

    <div region="center" style="padding:1px;border: 0px;">
        <t:datagrid name="weixinAcctFlowAccountList" checkbox="true" fitColumns="false" title="商户流量账户表管理" sortName="createDate" sortOrder="desc"
                    actionUrl="weixinAcctFlowAccountController.do?datagrid" idField="id" fit="true" queryMode="group">
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="商户名称" field="acctForName" hidden="true" query="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="登录名称" field="username" hidden="true" query="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="商户级别" dictionary="sAcclevel" field="acctlevel" query="true" hidden="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="所属商户" field="belogAcct" hidden="true" query="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="全国流量（M）" field="countryFlowValue" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="省内流量（M）" field="provinceFlowValue" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="创建日期"  field="createDate" query="false"  formatter="yyyy-MM-dd" hidden="flase"  queryMode="single"  sortable="true"   width="120"></t:dgCol>
            
            <t:dgCol title="所属商户id" field="acct_id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <%--<t:dgCol title="所属商户id" dictionary="city_jx" field="cityname" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
            <%--false相当于不显示true是显示--%>
            <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
            <t:dgFunOpt funname="setCharge(id)" title="充值"></t:dgFunOpt>

        </t:datagrid>
    </div>
</div>
<script src="webpage/weixin/tenant/weixinProductList.js"></script>
<script type="text/javascript">

$(document).ready(function(){
		//给时间控件加上样式
			$("#weixinAcctFlowAccountListtb").find("input[name='createDate']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
});

    function setCharge(id) {
        var url = "weixinAcctFlowAccountController.do?goCharge&id=" + id;
//        createdetailwindow('充值页面', 'weixinAcctFlowAccountController.do?goCharge&id=' + id, 'weixinAcctFlowAccountList', '100%', '100%');
        add('充值页面', 'weixinAcctFlowAccountController.do?goCharge&id=' + id, 'weixinAcctFlowAccountList', '50%', '100%');
    }
</script>