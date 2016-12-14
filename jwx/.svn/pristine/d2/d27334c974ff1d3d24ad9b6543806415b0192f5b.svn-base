<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinGroupList" checkbox="true" fitColumns="false" title="" sortOrder="desc" sortName="createDate" actionUrl="weixinGroupController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>

   <t:dgCol title="分组编号"  field="groupId"  hidden="true" query="false" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="分组名称"  field="groupName" query="true"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="人数" field="count" hidden="true" queryMode="single" width="120"></t:dgCol>
   <t:dgCol title="同步状态"  field="synchStatu"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="微信Id"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" query="true"  formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  sortable="true"   width="120"></t:dgCol>

   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
      <%--<t:dgDelOpt title="删除" url="weixinGroupController.do?doDel&id={id}" />--%>

   <t:dgToolBar title="分组与微信同步" icon="icon-reload" url="weixinGroupController.do?doSynch" funname="doSynch()"></t:dgToolBar>
   <t:dgToolBar title="创建分组" icon="icon-add" url="weixinGroupController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinGroupController.do?goUpdate" funname="update"></t:dgToolBar>
   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinGroupController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinGroupController.do?goUpdate" funname="detail"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/mm/weixinGroupList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#weixinGroupListtb").find("input[name='createDate']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinGroupController.do?upload', "weixinGroupList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinGroupController.do?exportXls","weixinGroupList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinGroupController.do?exportXlsByT","weixinGroupList");
}

function doSynch(){
	
	$.messager.progress();
	$.ajax({
		url:"weixinGroupController.do?doSynch",
		type:"POST",
		dataType:"JSON",
		success:function(data){
			if(data.success){
				
				$.messager.progress('close');
				$('#weixinGroupList').datagrid('reload');
				tip(data.msg);
			}
		}
	});
}
 </script>