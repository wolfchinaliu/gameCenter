<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid name="userGiveFlowList" checkbox="true" fitColumns="false" title="用户流量币领取记录"
                    actionUrl="allFlowCollectController.do?FlowCollectdatagrid" idField="id" fit="true" queryMode="group">
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
           <t:dgCol title="日期:"  field="gettime" hidden="true"  queryMode="group" query="true" width="120"></t:dgCol>
           <c:if  test="${level !=3}">
           <t:dgCol title="商户名称"  field="accountname" hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
           </c:if>
          <c:if  test="${level !=3}">
            <t:dgCol title="商户等级" field="level" width="100" replace="A级_1,B级_2,C级_3" query="true" hidden="true"></t:dgCol>
            </c:if>
            <c:if  test="${level !=3}">
           <t:dgCol title="所属商户"  field="belogAcct" hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
           </c:if>
           <t:dgCol title="运营商"  field="merchant" hidden="true" replace="移动_1,联通_2,电信_3" queryMode="single" query="true" width="120"></t:dgCol>
            <%-- <t:dgCol title="流量类型" field="flowType" hidden="true" queryMode="single" query="true" width="120"></t:dgCol> --%>
            <t:dgCol title="流量类型" field="flowType" width="100" replace="省内流量_2,全国流量_1" query="true" hidden="true"></t:dgCol>
            <t:dgCol title="累计成功赠送(M)" field="flowValue" hidden="true" query="false" queryMode="single"
                     width="120"></t:dgCol>
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
			$('#userGiveFlowListtb').find("input[name='belogAcct']").combobox({
		  		url: 'weixinAcctController.do?NotCAllSubAcct',
		  		editable: false, //不可编辑状态
		  		cache: false,
		  		valueField: 'code',
		  		textField: 'name',
		  		resizable:true,
		  		multiple:false
		  	});
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
        JeecgExcelExport("allFlowCollectController.do?exportXls", "userGiveFlowList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("weixinAcctController.do?exportXlsByT", "weixinAcctList");
    }
</script>