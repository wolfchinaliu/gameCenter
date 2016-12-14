<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinCustomerTempList" checkbox="true" fitColumns="false" title="客服消息模板" actionUrl="weixinCustomerTempController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="模板名称"  field="templateIdShort"  hidden="true" query="true" queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="微信主表"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="模板ID"  field="templateId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinCustomerTempController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinCustomerTempController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinCustomerTempController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinCustomerTempController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinCustomerTempController.do?goUpdate" funname="detail"></t:dgToolBar>
  <%-- 
  <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> 
   --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/customer/weixinCustomerTempList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinCustomerTempController.do?upload', "weixinCustomerTempList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinCustomerTempController.do?exportXls","weixinCustomerTempList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinCustomerTempController.do?exportXlsByT","weixinCustomerTempList");
}
 </script>