<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp" />
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinShopList" checkbox="false" fitColumns="false" title="微信商城" actionUrl="weixinShopController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商城名称"  field="shopName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商城logo"  field="shopLogo"  hidden="true" image="true" imageSize="40,40" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电话"  field="telephone"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="address"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公众号ID"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="介绍"  field="introduction"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   
   <t:dgFunOpt funname="goUpdateItem(id)" title="设置"></t:dgFunOpt>
   
   <t:dgToolBar title="录入" icon="icon-add" url="weixinShopController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinShopController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinShopController.do?goUpdate" funname="detail"  width="100%" height="100%"></t:dgToolBar>
   
   <t:dgDelOpt title="删除" url="weixinShopController.do?doDel&id={id}" />
   
   <%-- 
   
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinShopController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinShopController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/shop/weixinShopList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });

function goUpdateItem(id){
		add('商城设置','weixinShopController.do?goUpdate&id='+id,'weixinShopList' ,'100%', '100%');
}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinShopController.do?upload', "weixinShopList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinShopController.do?exportXls","weixinShopList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinShopController.do?exportXlsByT","weixinShopList");
}
 </script>