<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinSourceList" checkbox="true" fitColumns="false" title="素材管理" actionUrl="weixinSourceController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="微信资源编号"  field="mediaId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="素材类型"  field="sourceType"  hidden="true" query="true" queryMode="single" replace="图片_image,语音_voice,视频_video"  width="120"></t:dgCol>
   <t:dgCol title="资源预览"  field="sourcePath"  hidden="true" image="true" imageSize="40,40" queryMode="single"  width="120">

   </t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="所属公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinSourceController.do?doDel&id={id}" />
   <t:dgToolBar title="图片上传" icon="icon-add" url="weixinSourceController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinSourceController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinSourceController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinSourceController.do?goUpdate" funname="detail"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/source/weixinSourceList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinSourceController.do?upload', "weixinSourceList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinSourceController.do?exportXls","weixinSourceList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinSourceController.do?exportXlsByT","weixinSourceList");
}
 </script>