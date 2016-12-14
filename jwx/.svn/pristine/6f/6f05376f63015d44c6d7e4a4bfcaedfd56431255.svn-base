<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<t:datagrid name="userList" title="员工账号管理" actionUrl="weixinUserController.do?datagrid" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="用户名" sortable="false" field="userName" query="true" width="120"></t:dgCol>
	
	<t:dgCol title="真实姓名" field="realName" query="true" width="120"></t:dgCol>
	<t:dgCol title="手机号码" field="mobilePhone" query="true" width="120"></t:dgCol>
	<t:dgCol title="办公电话" field="officePhone" query="true" width="120"></t:dgCol>
	<t:dgCol title="电子邮箱" field="email" query="true" width="120"></t:dgCol>
	<t:dgCol title="状态" sortable="true" field="status" replace="正常_1,禁用_0,超级管理员_-1" width="120"></t:dgCol>
	<%--<t:dgCol title="操作" field="opt" width="120"></t:dgCol>
	 <t:dgDelOpt title="删除" url="weixinUserController.do?del&id={id}&userName={userName}" />--%>
	<t:dgToolBar title="添加员工" icon="icon-add" url="weixinUserController.do?addorupdate" funname="myadd"></t:dgToolBar>
	<t:dgToolBar title="修改信息" icon="icon-edit" url="weixinUserController.do?addorupdate" funname="update"></t:dgToolBar>
	<t:dgToolBar title="重置密码" icon="icon-edit" url="weixinUserController.do?changepasswordforuser" funname="update"></t:dgToolBar>
	<t:dgToolBar title="禁用账户" icon="icon-no" url="weixinUserController.do?lock" funname="lockObj"></t:dgToolBar>
	<t:dgToolBar title="启用账户" icon="icon-ok" url="weixinUserController.do?deblocking" funname="delockObj"></t:dgToolBar>
	
</t:datagrid>
<script type="text/javascript">

function lockObj(title,url, id) {

	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择禁用项目');
		return;
	}
		url += '&id='+rowsData[0].id;

	$.dialog.confirm('您确定要禁用该员工账号吗？', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}

function delockObj(title,url, id) {

	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择启用项目');
		return;
	}
		url += '&id='+rowsData[0].id;

	$.dialog.confirm('您确定要启用该员工账号吗？', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}

function lockuploadify(url, id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		
		
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
			var msg = d.msg;
				tip(msg);
				reloadTable();
			}
			
		}
	});
}

function myadd(title,addurl,gname,width,height) {
	
	gridname=gname;
	
	var getData = $('#'+gridname).datagrid('getData');
	
	if(getData.total < 100){

		createwindow(title, addurl,width,height);
	}else{
		
		tip('您目前的版本只能创建'+100+'个员工账号，如需创建多个员工账号，请升级!');
		return;
	}

}
</script>