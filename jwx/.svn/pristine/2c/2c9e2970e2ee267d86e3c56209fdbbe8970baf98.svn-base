<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinFoodOrderList" checkbox="true" fitColumns="false" title="餐饮订单" actionUrl="weixinFoodOrderController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="订单编号"  field="id"  hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
   
   <t:dgCol title="姓名"  field="userName"  hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="性别"  field="sex"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机号码"  field="mobilphone"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="就餐时间"  field="preDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="座位类别"  field="type"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预订人数"  field="number"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单状态"  field="status"  hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="支付状态"  field="ispay"  hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="下单日期"  field="createDate"  hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinFoodOrderController.do?doDel&id={id}" />
   <%-- 
   <t:dgToolBar title="录入" icon="icon-add" url="weixinFoodOrderController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinFoodOrderController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinFoodOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinFoodOrderController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/business/weixinFoodOrderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinFoodOrderController.do?upload', "weixinFoodOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinFoodOrderController.do?exportXls","weixinFoodOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinFoodOrderController.do?exportXlsByT","weixinFoodOrderList");
}
 </script>