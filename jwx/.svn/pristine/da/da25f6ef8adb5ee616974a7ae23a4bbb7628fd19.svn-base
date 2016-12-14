<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinAccountList" checkbox="true" fitColumns="false" title="公众帐号管理" actionUrl="customerWeixinAccountController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="0"></t:dgCol>
   <t:dgCol title="用户名称"  field="userName"  hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="公众帐号"  field="accountname"  hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="公众帐号TOKEN"  field="accounttoken"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众微信号"  field="accountnumber"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号类型"  field="accounttype"  hidden="true" replace="(1_公众号,2_订阅号)" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电子邮箱"  field="accountemail"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众帐号描述"  field="accountdesc"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众帐号APPID"  field="accountappid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众帐号APPSECRET"  field="accountappsecret"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="ACCESS_TOKEN"  field="accountaccesstoken"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
    <%-- <t:dgToolBar title="添加公众账号" icon="icon-add" url="weixinAccountController.do?goAdd" funname="add"></t:dgToolBar>--%>
    
    <t:dgToolBar title="注册公众账号" icon="icon-add"  funname="addAcct"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="customerWeixinAccountController.do?goUpdate" funname="update"></t:dgToolBar>
   
   <t:dgToolBar title="查看" icon="icon-search" url="customerWeixinAccountController.do?goUpdate" funname="detail"></t:dgToolBar>
   
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar> 
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/account/weixinAccountList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
function addAcct(){
	window.open("registerController.do?goRegister", "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");
	//openwindow('注册公众帐号', 'registerController.do?goRegister','', '100%', '100%');
}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'customerWeixinAccountController.do?upload', "weixinAccountList");
}

//导出
function ExportXls() {
	JeecgExcelExport("customerWeixinAccountController.do?exportXls","weixinAccountList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("customerWeixinAccountController.do?exportXlsByT","weixinAccountList");
}

function weixinAuth() {
	window.open("weixinOpenPlatform.do?auth");
}
 </script>