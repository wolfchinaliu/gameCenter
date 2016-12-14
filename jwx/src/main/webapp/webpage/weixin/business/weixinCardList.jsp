<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>

<c:if test="${isShowpoint == '0'}">
<div style="border: solid #f7cc8f 1px;background: #FFEFCE;margin:1px 1px 1px 1px;" id="point_title">
 	<p style="padding-left: 15px;">优惠券自定义菜单链接地址：<%=basePath%>/shopController.do?cardList&accountid=${WEIXIN_ACCOUNT.id}</p>
 	
</div>
</c:if>

<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinCardList" checkbox="true" fitColumns="false" title="优惠券" actionUrl="weixinCardController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="卡券类型"  field="cardType"  hidden="true" query="true" replace="团购券_1,代金券_2,折扣券_3,礼品券_4,优惠券_0" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商户LOGO"  field="logoUrl" hidden="false" image="true" imageSize="40,40" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="码展示类型"  field="codeType"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商户名称"  field="brandName"  hidden="false" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="卡券名称"  field="title"  hidden="true" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="券名的副标题"  field="subTitle"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="券颜色"  field="color"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="使用提醒"  field="notice"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="使用说明"  field="description"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="适用门店"  field="businessName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="启用时间"  field="beginTimestamp"  hidden="true" formatter="yyyy-MM-dd" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="到期时间"  field="endTimestamp"  hidden="true" formatter="yyyy-MM-dd" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客服电话"  field="servicePhone"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="卡券数量"  field="quantity"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当前余量"  field="nowquantity"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="面值"  field="cost"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="状态"  field="status"  hidden="true" query="true" replace="未生效_0,已生效_1,已删除_3,已过期_4" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   
   
   <t:dgFunOpt funname="goSendCard(id)" title="投放" exp="status#eq#1"></t:dgFunOpt>
   
   <t:dgDelOpt title="删除" url="weixinCardController.do?doDel&id={id}"/>
   
   <t:dgToolBar title="创建优惠券" icon="icon-add" url="weixinCardController.do?goAdd&cardType=GENERAL_COUPON" funname="add" width="80%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="创建团购券" icon="icon-add" url="weixinCardController.do?goAdd&cardType=GROUPON" funname="add" width="80%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="创建代金券" icon="icon-add" url="weixinCardController.do?goAdd&cardType=CASH" funname="add" width="80%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="创建折扣券" icon="icon-add" url="weixinCardController.do?goAdd&cardType=DISCOUNT" funname="add" width="80%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="创建礼品券" icon="icon-add" url="weixinCardController.do?goAdd&cardType=GIFT" funname="add" width="80%" height="100%"></t:dgToolBar>
   
   <%-- 
    <t:dgDelOpt title="删除" url="weixinCardController.do?doDel&id={id}" exp="status#eq#1"/>
   <t:dgToolBar title="录入" icon="icon-add" url="weixinCardController.do?goAdd" funname="add" width="80%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinCardController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinCardController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   --%>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinCardController.do?goView" funname="detail" width="80%" height="100%"></t:dgToolBar>
    
   <t:dgToolBar title="同步优惠券" icon="icon-reload" url="weixinCardController.do?loadCard" funname="loadCard"></t:dgToolBar>
   <%--
   <t:dgToolBar title="同步优惠券" icon="icon-reload" url="weixinCardController.do?loadCard" funname="loadCard"></t:dgToolBar>
   --%>
   <%-- 
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/business/weixinCardList.js"></script>		
 <script type="text/javascript">
 function loadCard(){

		//进度条
		$.messager.progress();
		$.ajax({
         type: "POST",
         url: "weixinCardController.do?loadCard",
         dataType: "json",
         success: function(data){
         	
         	$.messager.progress('close');
         	$('#weixinCardList').datagrid('reload');            	
         	tip(data.msg);
         }        
     });
}
 
 
 function loadBizwifiList(){
		
		$.dialog.confirm("您确定要同步微信优惠券?", function(){
	  		
			//进度条
			$.messager.progress();
	  		$.ajax({
	            type: "POST",
	            url: "weixinCardController.do?loadCard",
	            dataType: "json",
	            success: function(data){
	            	
	            	$.messager.progress('close');
	            	$('#weixinCardList').datagrid('reload');            	
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
	openuploadwin('Excel导入', 'weixinCardController.do?upload', "weixinCardList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinCardController.do?exportXls","weixinCardList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinCardController.do?exportXlsByT","weixinCardList");
}
function goSendCard(id){
		
	$.dialog({
		content: "url:weixinCardController.do?goSendCard&id="+id,
		drag :false,
		lock : true,
		title:'卡券发放',
		opacity : 0.3,
		width:1000,
		height:500,drag:false,min:false,max:false
	}).zindex();
	
	//createdetailwindow('卡券发放','weixinCardController.do?goSendCard&id='+id,'weixinCardList','80%', '30%');
}
 </script>