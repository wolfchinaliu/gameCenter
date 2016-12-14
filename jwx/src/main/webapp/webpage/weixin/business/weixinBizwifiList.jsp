<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinBizwifiList" checkbox="true" fitColumns="false" title="微信WIFI" actionUrl="weixinBizwifiController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="适用门店"  field="weixinLocationEntity.businessName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="无线ssid"  field="ssid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="无线密码"  field="password"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="mac地址"  field="bssid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="二维码"  field="qrcodeImg0"  hidden="true" image="true" imageSize="40,40" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="桌贴二维码"  field="qrcodeImg1"  hidden="true"  image="true" imageSize="40,40" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinBizwifiController.do?doDel&id={id}" />
   
   
   
   <t:dgToolBar title="添加设备" icon="icon-add" url="weixinBizwifiController.do?goAdd" funname="add"></t:dgToolBar>
   
   <t:dgToolBar title="查看二维码" icon="icon-search" url="weixinBizwifiController.do?goUpdate" funname="detail" width="800" height="500"></t:dgToolBar>
   <t:dgToolBar title="同步设备" icon="icon-reload" url="weixinBizwifiController.do?loadBizwifiList" funname="loadBizwifiList"></t:dgToolBar>
   <%--
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinBizwifiController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinBizwifiController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/business/weixinBizwifiList.js"></script>		
 <script type="text/javascript">
 function loadBizwifiList(){
		
		$.dialog.confirm("您确定要同步微信WIFI设备?", function(){
	  		
			//进度条
			$.messager.progress();
	  		$.ajax({
	            type: "POST",
	            url: "weixinBizwifiController.do?loadBizwifiList",
	            dataType: "json",
	            success: function(data){
	            	
	            	$.messager.progress('close');
	            	$('#weixinBizwifiList').datagrid('reload');            	
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
	openuploadwin('Excel导入', 'weixinBizwifiController.do?upload', "weixinBizwifiList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinBizwifiController.do?exportXls","weixinBizwifiList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinBizwifiController.do?exportXlsByT","weixinBizwifiList");
}
 </script>