<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>灵动移动CRM</title>
    <style type="text/css">

	</style>
</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<div class="m-title">消息窗口</div>
				<div class="m-left">
					<a href="javascript:history.go(-1);" class="easyui-linkbutton m-back" plain="true" outline="false"></a>
				</div>
				<div class="m-right">
					<a href="javascript:void(0)" class="easyui-menubutton" 
                    data-options="iconCls:'icon-more',plain:true,hasDownArrow:false,menu:'#mm',menuAlign:'right'"></a>
				</div>
			</div>
		</header>
		<ul>
			<li>你好，在吗？</li>
		</ul>
       <footer style="padding:2px 3px">
            <input class="easyui-textbox" style="width:100%;height:32px;" data-options="prompt:'请输入消息内容',buttonText:'<span style=\'padding:0 15px\'>发送</span>'">
    
		</footer>
	</div>
	<div id="mm" class="easyui-menu" style="width:150px;">
		
		<div onclick="window.location.href='customController.do?addContact'" data-options="iconCls:'icon-add'">群发消息</div>
		
	</div>
</body>
</html>