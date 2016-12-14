<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<c:if test="${isShowpoint == '0'}">
<div style="border: solid #f7cc8f 1px;background: #FFEFCE;margin:1px 1px 1px 1px;" id="point_title">
 	<p style="padding-left: 15px;">提示：自定义菜单目前限制只能3个一级菜单，5个二级菜单。菜单保存好后，需要同步到微信才能正式生效。由于微信客户端缓存，需要24小时微信客户端才会展现出来。
 	<span style="float: right;padding-right: 5px;"><a href="#" onclick="closePoint();">关闭提示</a></span></p>
</div>
</c:if>

<div id="system_function_functionList" class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  
<t:datagrid actionUrl="customMenuManagerController.do?datagrid"  name="menuList" queryMode="group" treegrid="true" fitColumns="true" idField="id">
<t:dgCol field="id" treefield="id" title="主键" hidden="false"></t:dgCol>
<t:dgCol field="name" treefield="text" title="菜单名称" query="true" width="100"></t:dgCol> 
<t:dgCol field="type" treefield="src" title="菜单类型" replace="消息触发类_click,网页链接类_view" width="100" ></t:dgCol>
<t:dgCol field="orders" treefield="order" title="顺序" width="100"></t:dgCol>
 
<t:dgCol title="操作" field="opt"></t:dgCol>
<t:dgDelOpt title="删除" url="customMenuManagerController.do?del&id={id}" />
<t:dgToolBar title="录入菜单" icon="icon-add" url="customMenuManagerController.do?jumpSuView" funname="addFun()"></t:dgToolBar>
<t:dgToolBar title="菜单编辑" icon="icon-edit" url="customMenuManagerController.do?jumpSuView" funname="update"></t:dgToolBar>
<t:dgToolBar title="同步到微信" icon="icon-redo" url="customMenuManagerController.do?sameMenu" funname="sameMenu()"></t:dgToolBar>
</t:datagrid>
</div></div>

<script type="text/javascript">
function closePoint(){
	document.getElementById("point_title").style.display="none";
}
function addFun() {

    var row = $('#menuList').datagrid('getSelected');
    var url = "customMenuManagerController.do?jumpSuView";
    if(row){
    	url += "&fatherId="+row.id;
    }
	add("编辑信息",url,"menuList","","");
}

function edite(id) {
    var url = "customMenuManagerController.do?jumpSuView&id="+id;
	add("编辑信息",url,"menuList","","");
}


function sameMenu(){
	$.ajax({
		url:"customMenuManagerController.do?sameMenu",
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
