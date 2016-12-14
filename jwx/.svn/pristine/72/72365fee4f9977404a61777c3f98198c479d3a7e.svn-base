<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinPracticalityRecordList" fitColumns="false" title="中奖记录" actionUrl="weixinActivityController.do?recordDatagrid&hdid=${hdId}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="openID"  field="openid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="昵称"  field="nickname"  hidden="true" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机"  field="mobile"  hidden="true"  queryMode="single"  width="100" query="true"></t:dgCol>
   <t:dgCol title="奖项"  field="prizelevel"  hidden="true" query="true" queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="奖品(单位:M)"  field="prizevalue"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="中奖时间"  formatter="yyyy-MM-dd hh:mm:ss" field="addtime"  hidden="true"  queryMode="single"  width="140" query="false"></t:dgCol>
   <t:dgCol title="是否发货"  field="isSend"  hidden="true"  queryMode="group"  width="80" replace="未发货_1,已发货_2" ></t:dgCol>
   <t:dgCol title="发货时间"  formatter="yyyy-MM-dd hh:mm:ss" field="sendTime"  hidden="true"  queryMode="single"  width="140" query="false"></t:dgCol>
   <t:dgCol title="活动ID"  field="hdid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="商户ID"  field="accountid"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
   <t:dgConfOpt  title="标识已发货" url="weixinActivityController.do?sendPro&id={id}" exp="isSend#eq#1" message="确认标识发货"/>
   <t:dgConfOpt  title="获取用户绑定手机号" url="weixinActivityController.do?getMemberNumber&id={id}" exp="mobile#empty#true" message="获取用户手机号"/>
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