<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
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
		layout="table" action="weixinCustomerController.do?doAdd" tiptype="4">
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
				<td class="value"><div class="input-append">
						<input id="openId" name="openId" type="text" style="width: 150px"
							class="inputxt" datatype="*" readonly="readonly">
						<button class="btn" type="button" style="height: 20px;"
							onclick="choose_8a792db34d5fd236014d85591dcb00d9();">请选择</button><span id="openid_name"></span>
					</div> <span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">微信唯一标识</label> <script
						type="text/javascript">
						var windowapi = frameElement.api, W = windowapi.opener;
						function choose_8a792db34d5fd236014d85591dcb00d9() {
							$
									.dialog({
										content : 'url:weixinMemberController.do?weixinMemberChoose',
										zIndex : 2100,
										title : '挑选客服列表',
										lock : true,
										width : 500,
										height : 400,
										left : '55%',
										top : '65%',
										opacity : 0.4,
										button : [
												{
													name : '选择',
													callback : clickcallback_8a792db34d5fd236014d85591dcb00d9,
													focus : true
												}, {
													name : '取消',
													callback : function() {
													}
												} ]
									});

						}
						function clickcallback_8a792db34d5fd236014d85591dcb00d9() {
							iframe = this.iframe.contentWindow;
						/* 	var name = iframe.getcategoryListSelections('name');
							if ($('#carSeriesEntityName').length >= 1) {
								$('#carSeriesEntityName').val(name);
								$('#carSeriesEntityName').blur();
							}
							if ($("input[name='carSeriesEntityName']").length >= 1) {
								$("input[name='carSeriesEntityName']")
										.val(name);
								$("input[name='carSeriesEntityName']").blur();
							} */
							var id = iframe.getcategoryListSelections('openId');
							if (id !== undefined && id != "") {
								$('#openId').val(id);
								$('#openid_name').html(iframe.getcategoryListSelections('nickName'));
							}
						}
					</script></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 优先顺序:
				</label></td>
				<td class="value"><input id="sorts" name="sorts" type="text" datatype="/^[1-9]+$/"
					style="width: 150px" class="inputxt"> <span
					class="Validform_checktip">请输入</span> <label class="Validform_label"
					style="display: none;">优先顺序</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">
						是否接收消息: </label></td>
				<td class="value"><t:dictSelect field="receiveMessages"
						type="radio" typeGroupCode="yesorno" defaultVal="Y"
						hasLabel="false" title="是否接收消息"></t:dictSelect> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">是否接收消息</label></td>
			</tr>
		</table>
	</t:formvalid>
</body>
<script src="webpage/weixin/customer/weixinCustomer.js"></script>