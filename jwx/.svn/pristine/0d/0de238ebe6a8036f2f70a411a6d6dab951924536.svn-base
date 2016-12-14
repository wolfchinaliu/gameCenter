<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp" />
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinShopAppraiseList" checkbox="true" fitColumns="false" title="商品评价" actionUrl="weixinShopAppraiseController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评价日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="group" query="true" width="120"></t:dgCol>
   <t:dgCol title="评价内容"  field="notes"  hidden="true"  queryMode="single"  width="500"></t:dgCol>
   <t:dgCol title="商品名称"  field="weixinShopGoodsEntity.title" query="true"  hidden="true"  queryMode="single"  width="300"></t:dgCol>
   <t:dgCol title="粉丝"  field="openId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="粉丝昵称"  field="openName" query="true" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核状态"  field="status"  hidden="true" query="true" replace="已核准_1,未核准_0"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="星级"  field="star"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinShopAppraiseController.do?doDel&id={id}" exp="status#eq#0"/>
   <%-- 
   <t:dgToolBar title="录入" icon="icon-add" url="weixinShopAppraiseController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinShopAppraiseController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinShopAppraiseController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   --%>
   
   <t:dgToolBar title="审核通过" icon="icon-edit" url="weixinShopAppraiseController.do?doBatchAudit" funname="doBatchAudit"></t:dgToolBar>
   
   <t:dgToolBar title="查看" icon="icon-search" url="weixinShopAppraiseController.do?goUpdate" funname="detail"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/shop/weixinShopAppraiseList.js"></script>		
 <script type="text/javascript">
 function doBatchAudit(){
		
		//获取datagrid中复选框值
		var checkedItems = $('#weixinShopAppraiseList').datagrid('getChecked');
		var checked_ids = [];
		$.each(checkedItems, function(index, item){
			checked_ids.push(item.id);
		});
			
		if(checked_ids.length<1){
			
			alert("请至少选择一条记录!");
			return;
		}
		
	  	$.dialog.confirm("您确定要审核选择的评价?", function(){
	  		
	  		//AJAX异步执行批量操作
	  		$.ajax({
	            type: "POST",
	            url: "weixinShopAppraiseController.do?doBatchAudit",
	            data: {ids:checked_ids.toString()},
	            dataType: "json",
	            success: function(data){
	            	
					alert(data.msg);
					$('#weixinShopAppraiseList').datagrid('reload');//刷新datagrid数据
	            }        
	        });
	  		
		}, function(){
		}).zindex();
	}
 $(document).ready(function(){
		//给时间控件加上样式
			$("#weixinShopAppraiseListtb").find("input[name='createDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
			$("#weixinShopAppraiseListtb").find("input[name='createDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
});
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinShopAppraiseController.do?upload', "weixinShopAppraiseList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinShopAppraiseController.do?exportXls","weixinShopAppraiseList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinShopAppraiseController.do?exportXlsByT","weixinShopAppraiseList");
}
 </script>