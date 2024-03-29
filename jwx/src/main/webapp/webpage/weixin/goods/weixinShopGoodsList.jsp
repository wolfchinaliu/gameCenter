<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinShopGoodsList" checkbox="true" fitColumns="false" title="商品信息" actionUrl="weixinShopGoodsController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id" hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="预览图片"  field="titleImg"  hidden="true" image="true" imageSize="40,40" queryMode="single"  width="60" align="center"></t:dgCol>
   <t:dgCol title="商品名称"  field="title"  hidden="true"  queryMode="single" query="true" width="200"></t:dgCol>
  
   <t:dgCol title="所属分类"  field="weixinShopCategoryEntity.id"  hidden="true" query="true" replace="${weixinShopCategoryList}" queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="商品详情"  field="descriptions"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品原价"  field="price"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品现价"  field="realPrice"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="折扣"  field="sale"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="销售数量"  field="sellCount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评价数量"  field="discussCount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="好评数量"  field="goodCount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="差评数量"  field="badCount"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="热销"  field="isHot"  hidden="true" dictionary="is_hot" queryMode="single" query="false"  width="120"></t:dgCol>
   <t:dgCol title="新品"  field="isNew"  hidden="true" dictionary="is_hot" queryMode="single" query="false"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="statement"  hidden="true" replace="在售_1,停售_0" queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="上架时间"  field="shelveTime"  hidden="true"  queryMode="single" formatter="yyyy-MM-dd hh:mm:ss" width="120"></t:dgCol>
   <t:dgCol title="下架时间"  field="removeTime"  hidden="true"  queryMode="single" formatter="yyyy-MM-dd hh:mm:ss" width="120"></t:dgCol>
   <t:dgCol title="快递名称"  field="expressName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="快递费用"  field="expressPrice"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="卖家ID"  field="sellerId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinShopGoodsController.do?doDel&id={id}" />
   
   <t:dgToolBar title="录入" icon="icon-add" url="weixinShopGoodsController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinShopGoodsController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   
   <t:dgToolBar title="发售"  icon="icon-ok" url="weixinShopGoodsController.do?doBatchStartSale" funname="startSaleALLSelect"></t:dgToolBar>
   <t:dgToolBar title="停售"  icon="icon-remove" url="weixinShopGoodsController.do?doBatchStopSale" funname="stopSaleALLSelect"></t:dgToolBar>
   
   <t:dgToolBar title="删除"  icon="icon-no" url="weixinShopGoodsController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   
   <t:dgToolBar title="刷新" icon="icon-reload" funname="goReload"></t:dgToolBar>
   
   <t:dgToolBar title="查看" icon="icon-search" url="weixinShopGoodsController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   
    <%-- 
	<t:dgToolBar title="导入" icon="icon-putout" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>

    <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   --%>
  </t:datagrid>
  </div>
 </div>

 <script src = "webpage/weixin/goods/weixinShopGoodsList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinShopGoodsController.do?upload', "weixinShopGoodsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinShopGoodsController.do?exportXls","weixinShopGoodsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinShopGoodsController.do?exportXlsByT","weixinShopGoodsList");
}
//批量上架
function startSaleALLSelect(){
	
	//获取datagrid中复选框值
	var checkedItems = $('#weixinShopGoodsList').datagrid('getChecked');
	var checked_ids = [];
	$.each(checkedItems, function(index, item){
		checked_ids.push(item.id);
	});
		
	if(checked_ids.length<1){
		
		alert("请至少选择一条记录!");
		return;
	}
	
  	$.dialog.confirm("您确定要发售所选择的商品?", function(){
  		
  		//AJAX异步执行批量操作
  		$.ajax({
            type: "POST",
            url: "weixinShopGoodsController.do?doBatchStartSale",
            data: {ids:checked_ids.toString()},
            dataType: "json",
            success: function(data){
            	
				alert(data.msg);
				$('#weixinShopGoodsList').datagrid('reload');//刷新datagrid数据
            }        
        });
  		
	}, function(){
	}).zindex();
}
//批量下架
function stopSaleALLSelect(){
	
	var checkedItems = $('#weixinShopGoodsList').datagrid('getChecked');
		var checked_ids = [];
		$.each(checkedItems, function(index, item){
			checked_ids.push(item.id);
		});
		
	if(checked_ids.length<1){
		
		alert("请至少选择一条记录!");
		return;
	}
	
  	$.dialog.confirm("您确定要下架所选择的商品?", function(){
  		
  		$.ajax({
            type: "POST",
            url: "weixinShopGoodsController.do?doBatchStopSale",
            data: {ids:checked_ids.toString()},
            dataType: "json",
            success: function(data){
            	
				alert(data.msg);
				$('#weixinShopGoodsList').datagrid('reload');
            }        
        });
  		
	}, function(){
	}).zindex();
}

function goReload(){
	
	$('#weixinShopGoodsList').datagrid('reload');
}
 </script>