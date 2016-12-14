<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinPaymentLogList" checkbox="true" fitColumns="false" title="支付记录" actionUrl="weixinPaymentLogController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="商户号"  field="mchId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单号"  field="orderId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付流水号"  field="transactionId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付类型"  field="payType"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付金额"  field="amount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="付款日期"  field="createDate"  formatter="yyyy-MM-dd" hidden="true"  queryMode="group" query="true"  width="120"></t:dgCol>
   <t:dgCol title="支付人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>   
   
   <%-- 
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinPaymentLogController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinPaymentLogController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinPaymentLogController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinPaymentLogController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinPaymentLogController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  --%>
  	<t:dgToolBar title="查看" icon="icon-search" url="weixinPaymentLogController.do?goUpdate" funname="detail"></t:dgToolBar>
   	<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/payment/weixinPaymentLogList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
		//给时间控件加上样式
		$("#weixinPaymentLogListtb").find("input[name='createDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#weixinPaymentLogListtb").find("input[name='createDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
});
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinPaymentLogController.do?upload', "weixinPaymentLogList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinPaymentLogController.do?exportXls","weixinPaymentLogList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinPaymentLogController.do?exportXlsByT","weixinPaymentLogList");
}
 </script>