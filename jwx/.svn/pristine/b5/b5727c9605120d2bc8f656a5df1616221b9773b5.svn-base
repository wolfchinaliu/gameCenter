<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinCoinJournalList" checkbox="true" fitColumns="false" title="积分流水" actionUrl="weixinCoinJournalController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="交易金额"  field="coin"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="唯一码"  field="uniqueCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="摘要"  field="notes"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户ID"  field="openid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createrName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="交易类别"  field="dealType"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="交易时间"  field="dealDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号"  field="accountid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinCoinJournalController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinCoinJournalController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinCoinJournalController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinCoinJournalController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinCoinJournalController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/member/weixinCoinJournalList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinCoinJournalController.do?upload', "weixinCoinJournalList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinCoinJournalController.do?exportXls","weixinCoinJournalList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinCoinJournalController.do?exportXlsByT","weixinCoinJournalList");
}
 </script>