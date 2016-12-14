<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinUsergetcardList" checkbox="true" fitColumns="false" title="卡券领取记录" actionUrl="weixinUsergetcardController.do?datagrid" idField="id" fit="true" queryMode="group">
   
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt funname="goConsume(id)" exp="status#eq#0" title="消费"></t:dgFunOpt>
   
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="领券方帐号用户"  field="openId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="领券用户昵称"  field="weixinMemberEntity.nickName"  hidden="true" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="优惠券ID"  field="cardId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="优惠券"  field="weixinCardEntity.title"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="优惠券号"  field="userCardCode"  hidden="true" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否为转赠"  field="byFriend"  hidden="true" replace="是_1,否_0" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="领取场景值"  field="outerId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="赠送方账号"  field="friendUser"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="转赠前的code"  field="oldCardCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"  hidden="true" replace="未消费_0,已消费_1,已删除_3" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="消费时间"  field="consumeTime" formatter="yyyy/MM/dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="核销来源"  field="consumeSource"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
   
   <%-- 
   <t:dgDelOpt title="删除" url="weixinUsergetcardController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinUsergetcardController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinUsergetcardController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinUsergetcardController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinUsergetcardController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/payment/weixinUsergetcardList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
 //打开核销页面
 function goConsume(id){
		
	 createwindow("卡券消费确认", "weixinUsergetcardController.do?goConsume&id="+id, "500px", "200px");
 }
 function goConsume_bak(id){

		$.dialog({
			content: "url:weixinUsergetcardController.do?goConsume&id="+id,
			drag :false,
			lock : true,
			title:'卡券消费确认',
			opacity : 0.3,
			width:600,
			height:300,
			min:false,max:false
		}).zindex();
		
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinUsergetcardController.do?upload', "weixinUsergetcardList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinUsergetcardController.do?exportXls","weixinUsergetcardList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinUsergetcardController.do?exportXlsByT","weixinUsergetcardList");
}
 </script>