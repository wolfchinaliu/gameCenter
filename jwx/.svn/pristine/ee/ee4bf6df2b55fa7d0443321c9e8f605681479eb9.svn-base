<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinFoodCategoryList" checkbox="true" fitColumns="false" title="菜品分类" actionUrl="weixinFoodCategoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="分类名称"  field="name"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="分类图片"  field="imgurl"  hidden="true"  queryMode="single" image="true" imageSize="40,40" width="120"></t:dgCol>
   <t:dgCol title="标签"  field="tags"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="分类上级ID"  field="parentid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="卖家ID"  field="sellerId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinFoodCategoryController.do?doDel&id={id}" />
   
   
   
   <t:dgToolBar title="录入" icon="icon-add" url="weixinFoodCategoryController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinFoodCategoryController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinFoodCategoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="刷新" icon="icon-reload" funname="goReload"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinFoodCategoryController.do?goUpdate" funname="detail"></t:dgToolBar>
   
  
  </t:datagrid>
  </div>
 </div>
	
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinFoodCategoryController.do?upload', "weixinFoodCategoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinFoodCategoryController.do?exportXls","weixinFoodCategoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinFoodCategoryController.do?exportXlsByT","weixinFoodCategoryList");
}
function goReload(){
	
	$('#weixinFoodCategoryList').datagrid('reload');
}
 </script>