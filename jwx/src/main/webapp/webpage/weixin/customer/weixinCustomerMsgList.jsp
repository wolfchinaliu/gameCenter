<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinCustomerMsgList" checkbox="true" fitColumns="false" title="客服消息表" actionUrl="weixinCustomerMsgController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发送人ID"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="发送时间"  field="createDate"  hidden="true" formatter="yyyy-MM-dd hh:mm:ss" queryMode="group"  query="true" width="130"></t:dgCol>
   <t:dgCol title="发送人昵称"  field="sendNickName"  hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
   
   <t:dgCol title="微信主表"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发送ID"  field="sendOpenId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="接收人ID"  field="receiveOpenId"  hidden="false"  queryMode="single" width="120"></t:dgCol>
   <t:dgCol title="接收人昵称"  field="receiveNickName"  hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="消息内容"  field="content"  hidden="true"  queryMode="single"  width="300"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinCustomerMsgController.do?doDel&id={id}" />
    <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinCustomerMsgController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> 
   <%-- <t:dgToolBar title="录入" icon="icon-add" url="weixinCustomerMsgController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinCustomerMsgController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinCustomerMsgController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinCustomerMsgController.do?goUpdate" funname="detail"></t:dgToolBar>
<%--    <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/customer/weixinCustomerMsgList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
		//给时间控件加上样式
		$("#weixinCustomerMsgListtb").find("input[name='createDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#weixinCustomerMsgListtb").find("input[name='createDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	});

//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinCustomerMsgController.do?upload', "weixinCustomerMsgList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinCustomerMsgController.do?exportXls","weixinCustomerMsgList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinCustomerMsgController.do?exportXlsByT","weixinCustomerMsgList");
}
 </script>