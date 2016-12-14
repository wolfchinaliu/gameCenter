<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinAcctList" checkbox="true" fitColumns="false" title="商户管理表" actionUrl="weixinAcctController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="endDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商户名称"  field="acctName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电话"  field="mobilePhone"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="邮箱"  field="email"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="QQ"  field="qqNumber"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="群发次数"  field="smsnum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="图文次数"  field="newsnum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="请求次数"  field="requestnum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="员工账号个数"  field="usernum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号个数"  field="accountnum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="域名地址"  field="domainurl"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinAcctController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinAcctController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinAcctController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinAcctController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinAcctController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/tenant/weixinAcctList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinAcctController.do?upload', "weixinAcctList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinAcctController.do?exportXls","weixinAcctList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinAcctController.do?exportXlsByT","weixinAcctList");
}
 </script>