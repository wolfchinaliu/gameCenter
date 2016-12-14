<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<title>灵动移动CRM</title>
	<style type="text/css">
		form label{
			display: block;
			margin: 10px 0 5px 0;
		}
		select{
		font-size: 12px;}
	</style>
</head>
<body>

	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<div class="m-title">
					创建客户
				</div>
                <div class="m-left">
                    <a href="javascript:history.go(-1);" class="easyui-linkbutton m-back" plain="true" outline="false"></a>
                </div>
                <div class="m-right">
                	<a href="#" class="easyui-linkbutton" plain="true" outline="false" data-options="iconCls:'icon-ok'">保存</a>
                </div>
				
			</div>
		</header>
		<form id="ff">
		<div style="padding:0 20px;margin-top: 10px;">
			<div>
				<label>客户名称</label>
				<input class="easyui-textbox" prompt="Full name" style="width:100%">
			</div>
			<div>
				<label>客户级别</label>
				<select  prompt="级别" style="width:100%;height:30px;">
					<option>请选择客户级别</option>
					<option>重点</option>
					<option>普通</option>
				</select>	
			</div>
			<div>
				<label>客户来源</label>
				<select class="textbox" prompt="来源" style="width:100%;height:30px">
					<option>请选择客户来源</option>
					<option>电话</option>
					<option>网络</option>
				</select>
			</div>
			<div>
				<label>行业</label>
				<select class="textbox" prompt="类型" style="width:100%;height:30px">
					<option>请选择行业</option>
					<option>金融</option>
					<option>电信</option>
					<option>教育</option>
					<option>房地产</option>
					<option>基础能源</option>
					<option>农业</option>
					<option>服务业</option>
					<option>高科技</option>
				</select>
			</div>
			<div>
				<label>联系电话</label>
				<input class="easyui-numberbox" prompt="Number" style="width:100%">
			</div>
			<div>
				<label>地址</label>
				<input class="easyui-numberbox" prompt="Number" style="width:100%">
			</div>
			<div align="center" id="show-more">
				<a href="javascript:void(0)" onclick="showMoreColumn()" class="easyui-linkbutton m-next" plain="true" outline="true" 
        	style="width:100px;margin-top: 10px;margin-bottom: 10px;margin-right: 5px;">添加更多条目</a>
			</div>
		</div>
		<div id="more-column" style="padding:0 20px;margin-top: 10px;">
			
			<div>
				<label>总人数</label>
				<input id="remark" name="remark" class="easyui-textbox" prompt="Full name" style="width:100%">
			</div>
			<div>
				<label>销售额</label>
				<input id="remark" name="remark" class="easyui-textbox" prompt="Full name" style="width:100%">
			</div>
			<div>
				<label>备注</label>
				<input id="remark" name="remark" class="easyui-textbox" prompt="Full name" style="width:100%">
			</div>
		</div>
		</form>
		
		<footer>
            
               <a href="crmLoginController.do?index" class="easyui-linkbutton" style="width:100%;height:40px;border:0;"><span style="font-size:16px">提交</span></a>
          
		</footer>
	</div>
<script type="text/javascript">
document.getElementById("more-column").style.display="none";
function showMoreColumn(){
	
	document.getElementById("more-column").style.display="block";
	document.getElementById("show-more").style.display="none";
}
</script>
</body>
</html>