<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
<t:datagrid name="newstemplatelist" checkbox="true" actionUrl="newsTemplateController.do?datagrid" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false" ></t:dgCol>
	<t:dgCol title="图文名称" field="templateName" query="true" width="100"></t:dgCol>
	<t:dgCol title="上传时间" field="uploadtime"  formatter="yyyy-MM-dd hh:mm:ss" width="100"></t:dgCol>
	<t:dgCol title="素材状态" field="status" width="100" replace="已审核_1,未审核_0" query="true"></t:dgCol>
	
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<%-- <t:dgDelOpt title="删除" url="newsTemplateController.do?del&id={id}" />--%>
	<t:dgFunOpt funname="addItem(id)" title="添加图文"></t:dgFunOpt>
	<t:dgFunOpt funname="readNews(id)" title="编辑图文"></t:dgFunOpt>
	<%-- <t:dgFunOpt funname="uploadItem(id)" title="上传图文"></t:dgFunOpt>--%>
	
	
	<t:dgToolBar title="创建" icon="icon-add" url="newsTemplateController.do?goSuView" funname="add"></t:dgToolBar>
 	<t:dgToolBar title="编辑" icon="icon-edit" url="newsTemplateController.do?goSuView" funname="update"></t:dgToolBar>
 	<t:dgToolBar title="删除"  icon="icon-remove" url="newsTemplateController.do?doBatchDel" funname="deleteALLSelect" ></t:dgToolBar>
 	
 	<t:dgToolBar title="上传审核"  icon="icon-redo"  funname="doBatchUpload" ></t:dgToolBar>
 	<%--<t:dgToolBar title="复制素材"  icon="icon-redo"  funname="doBatchCopy" ></t:dgToolBar>--%>
 	
</t:datagrid>
</div></div>
<script type="text/javascript">
function readNews(id){
	var url = "weixinArticleController.do?goMessage&templateId="+id;
	createdetailwindow('图文编辑','weixinArticleController.do?goMessage&templateId='+id,'newstemplatelist','100%', '100%');
}

function addItem(id){
	add('添加图文','weixinArticleController.do?goAdd&templateId='+id,'newstemplatelist' ,'100%', '100%');
}

function uploadItem(id){ 
	
	//进度条
	$.messager.progress();
	//AJAX异步执行上传图文至微信服务器
	$.ajax({
        type: "POST",
        url: "weixinArticleController.do?doUploadPermanentNews",
        data: {templateId:id},
        dataType: "json",
        success: function(data){
        	
        	$.messager.progress('close');
        	tip(data.msg);
			$('#newstemplatelist').datagrid('reload');//刷新datagrid数据
        }        
    });
}
//上传选中图文素材
function doBatchUpload(){
	
	//获取datagrid中复选框值
	var checkedItems = $('#newstemplatelist').datagrid('getChecked');
	var checked_ids = [];
	$.each(checkedItems, function(index, item){
		checked_ids.push(item.id);
	});   
		
	if(checked_ids.length<1){
		
		tip("请至少选择一条记录!");
		return;
	}
	
  	

 	//进度条
	$.messager.progress();
 	
 		$.ajax({
           type: "POST",
           url: "weixinArticleController.do?doBatchUploadPermanentNews",
           data: {ids:checked_ids.toString()},
           dataType: "json",
           success: function(data){
           	
           	$.messager.progress('close');
			$('#newstemplatelist').datagrid('reload');//刷新datagrid数据
			tip(data.msg);
           }        
       });
 		
	
}

function doSendToAll(){
	
	
  	$.dialog.confirm("您确定要群发选中的图文消息吗?", function(){
  		
  		//获取datagrid中复选框值
  		var checkedItems = $('#newstemplatelist').datagrid('getChecked');
  		var checked_ids = [];
  		$.each(checkedItems, function(index, item){
  			checked_ids.push(item.id);
  		});   
  		
  		//AJAX异步执行批量操作
  		$.ajax({
            type: "POST",
            url: "weixinArticleController.do?doSendToAll",
            data: {ids:checked_ids.toString()},
            dataType: "json",
            success: function(data){
            	
				alert(data.msg);
				$('#newstemplatelist').datagrid('reload');//刷新datagrid数据
            }        
        });
  		
	}, function(){
	}).zindex();
}

function doSendByGroup(id){
	
	var url = "weixinArticleController.do?goSeadMessageByGroup&id="+id;
	createdetailwindow('图文编辑','weixinArticleController.do?goSeadMessageByGroup&id='+id,'newstemplatelist','100%', '100%');
}

function doBatchCopy(){
	
	//获取datagrid中复选框值
	var checkedItems = $('#newstemplatelist').datagrid('getChecked');
	var checked_ids = [];
	$.each(checkedItems, function(index, item){
		checked_ids.push(item.id);
	});   
		
	if(checked_ids.length<1){
		
		tip("请至少选择一条记录!");
		return;
	}
	
	var url = "newsTemplateController.do?goCopy&ids="+checked_ids.toString();
	createwindow('图文复制',url,'newstemplatelist','100%', '50%');
}
</script>

