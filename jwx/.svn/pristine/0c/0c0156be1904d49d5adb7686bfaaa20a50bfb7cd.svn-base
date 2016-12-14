<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp" />
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinOrderList" checkbox="true" fitColumns="false" title="订单管理" actionUrl="weixinOrderController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="订单编号"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单编号"  field="orderNo"  hidden="true"  queryMode="single"  width="150" query="true"></t:dgCol>
   <%-- <t:dgCol title="买家昵称"  field="weixinMemberEntity.nickName"  hidden="true"  queryMode="single"  width="150"></t:dgCol>--%>
   <t:dgCol title="买家昵称"  field="createName"  hidden="true"  queryMode="single"  width="120" query="true"></t:dgCol>
   <t:dgCol title="下单日期"  field="createDate" formatter="yyyy-MM-dd hh:mm:ss"  width="150" hidden="true"  queryMode="group" query="true" ></t:dgCol>
   <t:dgCol title="支付人名称"  field="checkName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
  
   <t:dgCol title="支付日期"  field="checkDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单金额"  field="orderAmount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="运费"  field="freight"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="实收金额"  field="payAmount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单状态"  field="status"  hidden="true"  queryMode="single"  width="120" query="true" dictionary="order_stat"></t:dgCol>
   <t:dgCol title="发货状态"  field="deliverStatus"  hidden="false"  queryMode="single"  width="120" query="false" dictionary="deliv_stat"></t:dgCol>
   
   
   <t:dgCol title="支付方式"  field="payType"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货人"  field="deliveryName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货电话"  field="deliveryPhone"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货省"  field="province"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货市"  field="city"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货区"  field="district"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="address"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="邮编"  field="postcode"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="买家留言"  field="leaveWord"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="快递公司"  field="expressCompany"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="快递单号"  field="expressNum"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   
   
   <%-- 
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinOrderController.do?doDel&id={id}" exp="deliverStatus#eq#0"/>
   <t:dgToolBar title="录入" icon="icon-add" url="weixinOrderController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinOrderController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinOrderController.do?goView" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/shop/weixinOrderList.js"></script>		
     <script type="text/javascript">
 $(document).ready(function(){
		//给时间控件加上样式
			$("#weixinOrderListtb").find("input[name='createDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
			$("#weixinOrderListtb").find("input[name='createDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
}); 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinOrderController.do?upload', "weixinOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinOrderController.do?exportXls&type=1","weixinOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinOrderController.do?exportXlsByT","weixinOrderList");
}
 </script>