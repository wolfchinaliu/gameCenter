<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixingameDetialList" fitColumns="false" title="中奖记录" actionUrl="weixinActivityController.do?gameDetailDatagrid&hdid=${hdId}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="openID"  field="openid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="昵称"  field="nickname"  hidden="true" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="参与时间"  field="addtime"  hidden="true"  queryMode="single"  formatter="yyyy-MM-dd hh:mm:ss" width="160" query="true"></t:dgCol>
   <t:dgCol title="获得分值"  field="score"  hidden="true" query="true" queryMode="single"  width="120" ></t:dgCol>
   <t:dgCol title="详情"  field="msg"  hidden="true" query="true" queryMode="single"  width="160" ></t:dgCol>
   <t:dgCol title="活动ID"  field="hdid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="商户ID"  field="accountid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/lottery/weixinWinningrecordList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#weixinWinningrecordListtb").find("input[name='addtime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#weixinWinningrecordListtb").find("input[name='addtime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinWinningrecordController.do?upload', "weixinWinningrecordList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinWinningrecordController.do?exportXls","weixinWinningrecordList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinWinningrecordController.do?exportXlsByT","weixinWinningrecordList");
}
 </script>