<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinWinningrecordList" fitColumns="false" title="中奖记录" actionUrl="weixinWinningrecordController.do?datagrid&hdid=${hdId}&lotteryType=${lotteryType}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="openID"  field="openid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="昵称"  field="nickname"  hidden="true" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机"  field="mobile"  hidden="true"  queryMode="single"  width="120" query="true"></t:dgCol>
   <c:if test="${lotteryType ==1}">
   <t:dgCol title="奖项"  field="prizelevel"  hidden="true" query="true" replace="一等奖_1,二等奖_2,三等奖_3,四等奖_4,五等奖_5,六等奖_6" queryMode="single"  width="120"></t:dgCol>
   </c:if>
   <c:if test="${lotteryType ==2 }">
   <t:dgCol title="奖项"  field="prizelevel"  hidden="true" query="true" replace="一等奖_1,二等奖_2,三等奖_3" queryMode="single"  width="120"></t:dgCol>
   </c:if>
   <%-- <c:if test="${!lotteryType}">
   <t:dgCol title="奖项"  field="prizelevel"  hidden="true" query="false"   width="120"></t:dgCol>
   </c:if> --%>
   <t:dgCol title="奖品(单位:M)"  field="prizevalue"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="中奖时间"  field="addtime"  hidden="true" formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"  width="120" query="false"></t:dgCol>
   <t:dgCol title="中奖角度"  field="angle"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="活动ID"  field="hdid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="商户ID"  field="accountid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <%--<t:dgToolBar title="查看" icon="icon-search" url="weixinWinningrecordController.do?goUpdate" funname="detail"></t:dgToolBar>--%>
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