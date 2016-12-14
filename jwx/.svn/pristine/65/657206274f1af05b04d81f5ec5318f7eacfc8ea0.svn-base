<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinMembershipList" checkbox="true" fitColumns="false" title="会员信息" actionUrl="weixinMembershipController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号"  field="accountid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机号码"  field="mobilePhone"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="fullName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户名"  field="loginName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="密码"  field="loginPassword"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="sex"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="邮箱"  field="email"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="身份证"  field="cardid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="会员类型"  field="cardType"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="address"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="积分"  field="coin"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="加入时间"  field="joinTime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电话"  field="phone"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="存款"  field="balance"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="description"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinMembershipController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinMembershipController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinMembershipController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinMembershipController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinMembershipController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/member/weixinMembershipList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinMembershipController.do?upload', "weixinMembershipList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinMembershipController.do?exportXls","weixinMembershipList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinMembershipController.do?exportXlsByT","weixinMembershipList");
}
 </script>