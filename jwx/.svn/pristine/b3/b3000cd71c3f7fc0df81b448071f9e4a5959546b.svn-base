<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinIndividualizationList" checkbox="true" fitColumns="true" title="管理微信公众帐号" actionUrl="weixinIndividualizationController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="0"></t:dgCol>
   <t:dgCol title="名称"  field="name" query="false" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="Logo"  field="logo"  hidden="true" image="true" imageSize="40,40" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  hidden="true" width="120"></t:dgCol>
   <t:dgToolBar title="新增" icon="icon-add" url="weixinIndividualizationController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinIndividualizationController.do?goEdit" funname="update"></t:dgToolBar>  
   <t:dgToolBar title="删除"  icon="icon-remove" url="weixinIndividualizationController.do?doDel" funname="deleteALLSelect"></t:dgToolBar>    
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/guanjia/account/weixinAccountList.js"></script>		
 <script type="text/javascript">

 function toInit(){
		
		//获取datagrid中复选框值
		var checkedItems = $('#weixinIndividualizationList').datagrid('getChecked');
		var checked_ids = [];
		$.each(checkedItems, function(index, item){
			checked_ids.push(item.id);
		});
			
		if(checked_ids.length<1){
			
			alert("请至少选择一条记录!");
			return;
		}
		
		if(checked_ids.length>1){
			
			alert("请选择一条记录!");
			return;
		}
		
	  	$.dialog.confirm("您确定要初始化数据?", function(){
	  		
	  		//AJAX异步执行批量操作
	  		$.ajax({
	            type: "POST",
	            url: "weixinIndividualizationList.do?doInit",
	            data: {id:checked_ids.toString()},
	            dataType: "json",
	            success: function(data){
	            	
					alert(data.msg);
	            }        
	        });
	  		
		}, function(){
		}).zindex();
	}
 </script>