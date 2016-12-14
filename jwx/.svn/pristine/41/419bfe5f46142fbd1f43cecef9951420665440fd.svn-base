<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>客服表</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
	//编写自定义JS代码
</script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="weixinCustomerController.do?doUpdate"
		tiptype="1">
		<input id="id" name="id" type="hidden"
			value="${weixinCustomerPage.id }">
		<input id="createName" name="createName" type="hidden"
			value="${weixinCustomerPage.createName }">
		<input id="createDate" name="createDate" type="hidden"
			value="${weixinCustomerPage.createDate }">
		<input id="accountid" name="accountid" type="hidden"
			value="${weixinCustomerPage.accountid }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="right"><label class="Validform_label">
						微信唯一标识: </label></td>
				<td class="value"><input id="openId" name="openId" type="hidden" 
					style="width: 150px" class="inputxt" readonly="readonly"
					value='${weixinCustomerPage.openId}'> ${weixinCustomerPage.openId}<span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">微信唯一标识</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 优先顺序:
				</label></td>
				<td class="value"><input id="sorts" name="sorts" type="text"
					style="width: 150px" class="inputxt"
					value='${weixinCustomerPage.sorts}'> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">优先顺序</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">
						是否接收消息: </label></td>
				<td class="value"><t:dictSelect field="receiveMessages" 
						type="radio" typeGroupCode="yesorno" defaultVal="${weixinCustomerPage.receiveMessages}"
						hasLabel="false" title="是否接收消息"></t:dictSelect> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">是否接收消息</label></td>
			
			</tr>
		</table>
	</t:formvalid>
</body>
<script src="webpage/weixin/customer/weixinCustomer.js"></script>