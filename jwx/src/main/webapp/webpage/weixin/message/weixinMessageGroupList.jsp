<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinMessageGroupList" checkbox="true" fitColumns="false" title="群发消息记录" actionUrl="weixinMessageGroupController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="消息内容"  field="note"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="发送对象"  field="sendType"  hidden="true"   replace="全部_0,指定组_1,指定用户_2" query="true" width="120"></t:dgCol>
   <t:dgCol title="消息类型"  field="msgType"  hidden="true"   query="true" replace="图文_mpnews,文本_text,图片_image,语音_voice,视频_video,卡券_wxcard" width="120"></t:dgCol>
   <t:dgCol title="发送时间"  field="createTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属公众号"  field="accountid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinMessageGroupController.do?doDel&id={id}" />
   
   
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinMessageGroupController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <%--<t:dgToolBar title="查看" icon="icon-search" url="weixinMessageGroupController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/message/weixinMessageGroupList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#weixinMessageGroupListtb").find("input[name='createTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#weixinMessageGroupListtb").find("input[name='createTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinMessageGroupController.do?upload', "weixinMessageGroupList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinMessageGroupController.do?exportXls","weixinMessageGroupList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinMessageGroupController.do?exportXlsByT","weixinMessageGroupList");
}
 </script>