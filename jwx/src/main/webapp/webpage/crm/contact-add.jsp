<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>灵动移动CRM</title>
	<style type="text/css">
		form label{
			display: block;
			margin: 10px 0 5px 0;
		}
	</style>
</head>
<body>

	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<div class="m-title">
					创建联系人
				</div>
                <div class="m-left">
                    <a href="javascript:history.go(-1);" class="easyui-linkbutton m-back" plain="true" outline="false"></a>
                </div>
                <div class="m-right">
                </div>
				
			</div>
		</header>
		<form id="ff">
		<div style="padding:0 20px;margin-top: 10px;">
			<div>
				<label>姓名</label>
				<input class="easyui-textbox" prompt="姓名" style="width:100%">
			</div>
			<div>
				<label>公司名称</label>
				<input class="easyui-textbox" prompt="公司名称" iconCls="icon-search" style="width:100%">
			</div>
			<div>
				<label>部门</label>
				<input class="easyui-textbox" prompt="部门" style="width:100%">
			</div>
			<div>
				<label>职务</label>
				<input class="easyui-textbox" prompt="职务" style="width:100%">
			</div>
			<div>
				<label>电话</label>
				<input class="easyui-numberbox" prompt="电话" style="width:100%">
			</div>
			<div>
				<label>手机号码</label>
				<input class="easyui-numberbox" prompt="手机号码" style="width:100%">
			</div>
			<div align="center" id="show-more">
				<a href="javascript:void(0)" onclick="showMoreColumn()" class="easyui-linkbutton m-next" plain="true" outline="true" 
        	style="width:100px;margin-top: 10px;margin-bottom: 10px;margin-right: 5px;">添加更多条目</a>
			</div>
					
			<div>
				<label>性别</label>
				<select class="easyui-combobox" prompt="性别" style="width:100%;height:30px">
					<option>请选择性别</option>
					<option>男</option>					
					<option>女</option>
				</select>
			</div>	
			<div>
				<label>地址</label>
				<input class="easyui-numberbox" prompt="地址" style="width:100%">
			</div>			
			<div>
				<label>邮箱</label>
				<input class="easyui-numberbox" prompt="邮箱" style="width:100%">
			</div>
			<div>
				<label>微信</label>
				<input class="easyui-numberbox" prompt="微信" style="width:100%">
			</div>
			<div>
				<label>QQ</label>
				<input class="easyui-numberbox" prompt="QQ" style="width:100%">
			</div>
			<div>
				<label>生日</label>
				<input class="easyui-datebox" prompt="拜访时间" data-options="editable:false,panelWidth:220,panelHeight:240,iconWidth:30" style="width:100%">
			</div>			
			<div>
				<label>备注</label>
				<input id="remark" name="remark" class="easyui-textbox" prompt="Full name" style="width:100%">
			</div>			
			
		</div>
		</form>
		
		<div class="clear-d"></div>
		<footer>            
               <a href="crmLoginController.do?index" class="easyui-linkbutton" style="width:100%;height:40px;border:0;"><span style="font-size:16px">提交</span></a>          
		</footer>
	</div>

</body>
</html>