<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

  <t:datagrid name="tFavoLinkList" checkbox="true" fitColumns="false" title="个人收藏链接" actionUrl="tFavoLinkController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户ID"  field="userId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="name"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="链接"  field="link"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tFavoLinkController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tFavoLinkController.do?goAdd" funname="add"></t:dgToolBar>
   
   <%-- 
   <t:dgToolBar title="编辑" icon="icon-edit" url="tFavoLinkController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tFavoLinkController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tFavoLinkController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
  </t:datagrid>

 <script src = "webpage/weixin/tenant/tFavoLinkList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'tFavoLinkController.do?upload', "tFavoLinkList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tFavoLinkController.do?exportXls","tFavoLinkList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tFavoLinkController.do?exportXlsByT","tFavoLinkList");
}
 </script>