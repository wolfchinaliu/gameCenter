<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinPaymentConList" checkbox="true" fitColumns="false" title="支付方式配置" actionUrl="weixinPaymentConController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付名称"  field="payName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="说明"  field="payDescription"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付类型"  field="payType"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众账号APP_ID"  field="appId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="微信支付双向认证证书"  field="certFileName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="财付通密钥"  field="partnerKey"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="财付通商户号"  field="partnerId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="合作身份者ID"  field="partner"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="卖家支付宝帐户"  field="sellerEmail"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付宝key"  field="sellerKey"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="财付通商户号"  field="bargainorId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="财付通密钥"  field="bargainorKey"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   
   <t:dgFunOpt title="参数配置" funname="goUpdate(id)"/>
   <t:dgDelOpt title="删除" url="weixinPaymentConController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinPaymentConController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinPaymentConController.do?goUpdate" funname="update"></t:dgToolBar>
  	
  	<%-- 
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinPaymentConController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinPaymentConController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/payment/weixinPaymentConList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });

 function goUpdate(id){
		add('支付参数配置','weixinPaymentConController.do?goUpdate&id='+id,'weixinPaymentConList' ,'800px', '500px');
	}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinPaymentConController.do?upload', "weixinPaymentConList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinPaymentConController.do?exportXls","weixinPaymentConList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinPaymentConController.do?exportXlsByT","weixinPaymentConList");
}
 </script>