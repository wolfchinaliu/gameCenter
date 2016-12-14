<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinProductList" checkbox="true" fitColumns="false" title="套餐类型管理" actionUrl="weixinProductController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="套餐名称"  field="productName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="价格(月)"  field="price"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="remark"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="请求上限"  field="requestNum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="群发次数"  field="groupSMSNum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="图文素材上限"  field="newsTemplateNum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="文本素材上限"  field="textTemplateNum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinProductController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinProductController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinProductController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinProductController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinProductController.do?goUpdate" funname="detail"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/tenant/weixinProductList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinProductController.do?upload', "weixinProductList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinProductController.do?exportXls","weixinProductList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinProductController.do?exportXlsByT","weixinProductList");
}
 </script>