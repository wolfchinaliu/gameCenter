<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div id="main_depart_list" class="easyui-layout" fit="true" style="border: 0px;">
<div data-options="region:'north',border:false" style="padding:10px; color: red;">
    <strong>温馨提示：</strong>每次您修改自定义菜单后，请点击【同步到微信】按钮，同步您的菜单到公众号，由于微信客户端缓存，需要24小时微信客户端才会展现出来。如果您想马上看到效果，可以尝试取消关注公众账号后再次关注，则可以看到修改后的效果。
</div>
	<div region="center" style="padding: 1px; border: 0px;">
<t:datagrid  actionUrl="weixinMenuGroupController.do?menuDatagrid&menuGroupId=${menuGroupId}"  name="menuList" queryMode="group" treegrid="true" fitColumns="true" idField="id">
	<t:dgCol field="id" treefield="id" title="主键" hidden="false"></t:dgCol>
	<t:dgCol field="name" treefield="text" title="菜单名称" query="false" width="100"></t:dgCol>
	<t:dgCol field="type" treefield="src" title="菜单类型" replace="消息触发类_click,网页链接类_view,流量页面类_viewtoo,游戏页面类_viewplay" width="100" ></t:dgCol>
	<t:dgCol field="orders" treefield="order" title="顺序" width="100"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgDelOpt title="删除" url="menuManagerController.do?del&id={id}" />
	<t:dgToolBar title="菜单录入" icon="icon-add" url="menuManagerController.do?addorupdate&menuGroupId=${menuGroupId}" funname="add"></t:dgToolBar>
	<t:dgToolBar title="菜单编辑" icon="icon-edit" url="menuManagerController.do?addorupdate&menuGroupId=${menuGroupId}" funname="update"></t:dgToolBar>
	<t:dgToolBar title="同步到微信" icon="icon-ok" funname="synchWeixin"></t:dgToolBar>

</t:datagrid>
	</div>
</div>

<!-- <script type="text/javascript">
$(document).ready(function(){
	$.messager.alert("温馨提示！  ","现新增同步微信端菜单的功能，可先将微信菜单同步到本平台后进行编辑  ");
});
</script> -->

<script>
	
	function synchWeixin(){

		var menuGroupId="${menuGroupId}";
		$.dialog.confirm("确认同步菜单到微信吗?", function(){
			$.ajax({
				url:"weixinMenuGroupController.do?synchMenu",
				type:"POST",
				dataType:"JSON",
				data:{menuGroupId:menuGroupId},
				success:function(data){
					if(data.success){
						tip(data.msg);
					}
				}
			});
		});
	}
	
/**	function downloadMenu(){
		$.dialog.confirm("确认同步微信端菜单吗?", function(){
			$.ajax({
				url:"weixinMenuGroupController.do?downloadMenu",
				type:"GET",
				dataType:"JSON",
				data:{menuGroupId: "${menuGroupId}"},
				success:function(data){
					if(data.success){
						$('#menuList').datagrid('reload');
				        $('#menuList').treegrid('reload');
						tip(data.msg);
					}
				}
			});
		});
	}   */
</script>



