<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div id="system_function_functionList" class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  
<t:datagrid actionUrl="menuManagerController.do?datagrid" title="自定义菜单" name="menuList" queryMode="group" treegrid="true" fitColumns="true" idField="id">
<t:dgCol field="id" treefield="id" title="主键" hidden="false"></t:dgCol>
<t:dgCol field="name" treefield="text" title="菜单名称" query="true" width="100"></t:dgCol> 
<t:dgCol field="type" treefield="src" title="菜单类型" query="true" replace="消息触发类_click,网页链接类_view" width="100" ></t:dgCol>
<t:dgCol field="orders" treefield="order" title="顺序" width="100"></t:dgCol>
<t:dgCol field="status" treefield="status" query="true" title="菜单状态"  width="100" >
</t:dgCol>
 
<t:dgCol title="操作" field="opt"></t:dgCol>
<t:dgFunOpt funname="openStatus(id)" title="启用"></t:dgFunOpt>
<t:dgFunOpt funname="closeStatus(id)" title="禁用"></t:dgFunOpt>

<t:dgDelOpt title="删除" url="menuManagerController.do?del&id={id}" />

<t:dgToolBar title="录入菜单" icon="icon-add" url="menuManagerController.do?jumpSuView" funname="addFun()"></t:dgToolBar>
<t:dgToolBar title="菜单编辑" icon="icon-edit" url="menuManagerController.do?jumpSuView" funname="update"></t:dgToolBar>
<t:dgToolBar title="生成菜单" icon="icon-redo" url="menuManagerController.do?sameMenu" funname="sameMenu()"></t:dgToolBar>
</t:datagrid>
</div></div>

<script type="text/javascript">
function closePoint(){
	document.getElementById("point_title").style.display="none";
}
function addFun() {

    var row = $('#menuList').datagrid('getSelected');
    var url = "menuManagerController.do?jumpSuView";
    if(row){
    	url += "&fatherId="+row.id;
    }
	add("编辑信息",url,"menuList","","");
}

function edite(id) {
    var url = "menuManagerController.do?jumpSuView&id="+id;
	add("编辑信息",url,"menuList","","");
}

function openStatus(id){
	
	$.dialog.confirm("您确定要启用该菜单吗?", function(){
		$.ajax({
			url:"menuManagerController.do?upStatus&type=1",
			data: {id:id},
			type:"GET",
			dataType:"JSON",
			success:function(data){
				if(data.success){
					tip(data.msg);
				}
			}
		});
	}, function(){
	}).zindex();
}

function closeStatus(id){
	
	$.dialog.confirm("您确定要禁用该菜单吗?", function(){
		$.ajax({
			url:"menuManagerController.do?upStatus&type=0",
			data: {id:id},
			type:"GET",
			dataType:"JSON",
			success:function(data){
				if(data.success){
					tip(data.msg);
				}
			}
		});
	}, function(){
	}).zindex();
}

function sameMenu(){
	$.ajax({
		url:"menuManagerController.do?sameMenu",
		type:"GET",
		dataType:"JSON",
		success:function(data){
			if(data.success){
				tip(data.msg);
			}
		}
	});
}

</script>
