<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinAnnouncementList" checkbox="true" fitColumns="false" title="系统公告" actionUrl="weixinAnnouncementController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"  hidden="true"  queryMode="single"  width="250"></t:dgCol>
   <t:dgCol title="摘要"  field="summary"  hidden="true"  queryMode="single"  width="450"></t:dgCol>
   <t:dgCol title="详细内容"  field="notes"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="公告类型"  field="type"  hidden="true"  queryMode="single" replace="系统公告_1,新功能推荐_2" width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"  hidden="true"  queryMode="single" replace="待发_0,公布_1" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinAnnouncementController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinAnnouncementController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinAnnouncementController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinAnnouncementController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinAnnouncementController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/tenant/weixinAnnouncementList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinAnnouncementController.do?upload', "weixinAnnouncementList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinAnnouncementController.do?exportXls","weixinAnnouncementList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinAnnouncementController.do?exportXlsByT","weixinAnnouncementList");
}
 </script>