<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp" />
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinOrderList" checkbox="false" fitColumns="false" title="发货管理" actionUrl="weixinOrderController.do?datagridDelivery" idField="id" fit="true" queryMode="group">
   
   <t:dgCol title="订单编号"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="订单编号"  field="orderNo"  hidden="true"  queryMode="single"  width="150" query="true"></t:dgCol>
   <%-- <t:dgCol title="买家昵称"  field="weixinMemberEntity.nickName"  hidden="true" query="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="买家昵称"  field="createName"  hidden="true"  queryMode="single"  width="120" query="true"></t:dgCol>
   <t:dgCol title="下单日期"  field="createDate" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="single"  width="150" query="false" ></t:dgCol>
  
  <t:dgCol title="快递单号"  field="expressNum"  hidden="true"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="快递公司"  field="expressCompany"  hidden="true"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="发货日期"  field="expressDate" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="group" query="true"  width="150"></t:dgCol>
   <t:dgCol title="发货状态"  field="deliverStatus"  hidden="true"  queryMode="single"  width="120" query="true" dictionary="deliv_stat"></t:dgCol>
   
   <t:dgCol title="运费"  field="freight"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="粉丝"  field="openId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付方式"  field="payType"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货人"  field="deliveryName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货电话"  field="deliveryPhone"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货省"  field="province"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货市"  field="city"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货区"  field="district"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="address"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="邮编"  field="postcode"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="买家留言"  field="leaveWord"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="发货" funname="toDelivery(id)" exp="deliverStatus#eq#0"/>
   
   <%-- 
   <t:dgDelOpt title="删除" url="weixinOrderController.do?doDel&id={id}" exp="deliverStatus#ne#0"/>
   <t:dgToolBar title="录入" icon="icon-add" url="weixinOrderController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinOrderController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   
   
   <t:dgToolBar title="发货 " icon="icon-edit" url="weixinOrderController.do?goDelivery" funname="update"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinOrderController.do?goView" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/shop/weixinOrderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
		//给时间控件加上样式
			$("#weixinOrderListtb").find("input[name='expressDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
			$("#weixinOrderListtb").find("input[name='expressDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
});
 function toDelivery(id){
		
		var url = "weixinOrderController.do?toDelivery&id="+id;
		createwindow('发货',url);
}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinOrderController.do?upload', "weixinOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinOrderController.do?exportXls&type=2","weixinOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinOrderController.do?exportXlsByT","weixinOrderList");
}
 </script>