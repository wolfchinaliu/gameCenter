<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinShopAddressList" checkbox="true" fitColumns="false" title="收货地址管理" actionUrl="weixinShopAddressController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="粉丝ID"  field="openId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货人"  field="deliveryName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货电话"  field="deliveryPhone"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货省"  field="province"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货市"  field="city"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货区"  field="district"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="详细地址"  field="address"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="邮编"  field="postcode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="默认"  field="isDefault"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户ID"  field="userId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinShopAddressController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinShopAddressController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinShopAddressController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinShopAddressController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinShopAddressController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/shop/weixinShopAddressList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinShopAddressController.do?upload', "weixinShopAddressList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinShopAddressController.do?exportXls","weixinShopAddressList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinShopAddressController.do?exportXlsByT","weixinShopAddressList");
}
 </script>