<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<title>微信活动</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript">
	//编写自定义JS代码
</script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table"
		action="safetyRulesController.do?doUpdate" tiptype="1">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right"><label class="Validform_label"> 规则名称:</label></td>
				<td class="value" colspan="3"><input class="inputxt" id="ruleName" datatype="*" name="ruleName" type="text">
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 频率: </label></td>
				<td class="value" colspan="3"><select class="combo-box" id="frequencyUnit" name="frequencyUnit"
					style="width: 150px">
						<option>日</option>
						<option>周</option>
						<option>月</option>
						<option>年</option>
				</select> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 频率内最大接口调用次数: </label></td>
				<td class="value"><input id="frequencyNum" name="frequencyNum" type="text" style="width: 150px"
					class="easyui-numberbox inputxt" datatype="n" precision="0"> <span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">频率内最大接口调用次数</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 每次充值流量最大值: </label></td>
				<td class="value"><input id="charegeFlow" name="charegeFlow" type="text" style="width: 150px"
					class="easyui-numberbox inputxt" datatype="n" precision="0">M <span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">每次充值流量最大值</label></td>
			</tr>
		</table>
	</t:formvalid>
</body>