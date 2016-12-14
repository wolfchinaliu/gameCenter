<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinMemberCardList" checkbox="true" fitColumns="true" title="会员卡" actionUrl="weixinMemberCardController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="会员卡类型"  field="cardType"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="特权说明"  field="prerogative"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
  
   <t:dgCol title="商户logo"  field="logoUrl"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="展示类型"  field="codeType"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商户名字"  field="brandName"  hidden="true" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="卡券名"  field="title"  hidden="true" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="副标题"  field="subTitle"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="券颜色"  field="color"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="显示积分"  field="supplyBonus"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="使用提醒"  field="notice"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="使用说明"  field="description"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="到期时间"  field="endTimestamp" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="起用时间"  field="beginTimestamp" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="门店位置ID"  field="locationIdList"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="库存数量"  field="quantity"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="每人数量限制"  field="getLimit"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否可转赠"  field="canGiveFriend"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客服电话"  field="servicePhone"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"  hidden="true" query="true" replace="未审核_0,已审核_1"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt funname="goSendCard(id)" title="投放" exp="status#eq#1"></t:dgFunOpt>
   
   <t:dgToolBar title="创建会员卡" icon="icon-add" url="weixinMemberCardController.do?goAdd" funname="add" width="80%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinMemberCardController.do?goUpdate" funname="detail" width="80%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="同步会员卡" icon="icon-reload" url="weixinMemberCardController.do?loadMemberCard" funname="loadMemberCard"></t:dgToolBar>
   <%--
   
    
   <t:dgDelOpt title="删除" url="weixinMemberCardController.do?doDel&id={id}" />
   
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinMemberCardController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinMemberCardController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/member/weixinMemberCardList.js"></script>		
 <script type="text/javascript">
 function loadBizwifiList(){
		
		$.dialog.confirm("您确定要同步微信会员卡?", function(){
	  		
			//进度条
			$.messager.progress();
	  		$.ajax({
	            type: "POST",
	            url: "weixinMemberCardController.do?loadMemberCard",
	            dataType: "json",
	            success: function(data){
	            	
	            	$.messager.progress('close');
	            	$('#weixinMemberCardList').datagrid('reload');            	
	            	tip(data.msg);
	            }
	        });
	  		
		}, function(){
		}).zindex();
 }
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinMemberCardController.do?upload', "weixinMemberCardList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinMemberCardController.do?exportXls","weixinMemberCardList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinMemberCardController.do?exportXlsByT","weixinMemberCardList");
}
//投放
function goSendCard(id){
	
	$.dialog({
		content: "url:weixinMemberCardController.do?goSendCard&id="+id,
		drag :false,
		lock : true,
		title:'会员卡发放',
		opacity : 0.3,
		width:1000,
		height:500,drag:false,min:false,max:false
	}).zindex();
	
	//createwindow('卡券发放','weixinMemberCardController.do?goSendCard&id='+id,'weixinCardList','80%', '30%');
}
 </script>