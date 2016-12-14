<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>灵动移动CRM</title>
</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<div class="m-title">
					风格设置
				</div>
                <div class="m-left">
                    <a href="javascript:history.go(-1);" class="easyui-linkbutton m-back" plain="true" outline="false"></a>
                </div>
                <div class="m-right">
                    
                    
                </div>
				
			</div>
		</header>
		<div style="padding:20px">
			<p>Style</p>
			<a href="#" class="easyui-linkbutton c1" style="width:80px">Normal</a>
			<a href="#" class="easyui-linkbutton c2" plain="true" outline="true" style="width:80px">Outline</a>
			<a href="#" class="easyui-linkbutton c3" disabled style="width:80px">Disabled</a>

			<p>Colors<p>
			<p><a href="#" class="easyui-linkbutton c1" style="width:100%">Button1</a></p>
			<p><a href="#" class="easyui-linkbutton c2" style="width:100%">Button2</a></p>
			<p><a href="#" class="easyui-linkbutton c3" style="width:100%">Button3</a></p>
			<p><a href="#" class="easyui-linkbutton c4" style="width:100%">Button4</a></p>
			<p><a href="#" class="easyui-linkbutton c5" style="width:100%">Button5</a></p>
			<p><a href="#" class="easyui-linkbutton c6" style="width:100%">Button6</a></p>
		</div>
		
       
		
	</div>
</body>
</html>