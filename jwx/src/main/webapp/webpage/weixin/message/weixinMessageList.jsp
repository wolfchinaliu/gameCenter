<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinMessageList" checkbox="true" fitColumns="false" title="单发消息" actionUrl="weixinMessageController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="消息内容"  field="note"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="发送时间"  field="createTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="接收用户"  field="receiveUserName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属公众号"  field="accountid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinMessageController.do?doDel&id={id}" />
  
   
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinMessageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinMessageController.do?goUpdate" funname="detail"></t:dgToolBar>
  
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/message/weixinMessageList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#weixinMessageListtb").find("input[name='createTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#weixinMessageListtb").find("input[name='createTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinMessageController.do?upload', "weixinMessageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinMessageController.do?exportXls","weixinMessageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinMessageController.do?exportXlsByT","weixinMessageList");
}
 </script>