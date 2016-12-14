<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>客服消息模板</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinCustomerTempController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinCustomerTempPage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinCustomerTempPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinCustomerTempPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<%-- <tr>
						<td align="right">
							<label class="Validform_label">
								微信主表:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountid" name="accountid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCustomerTempPage.accountid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">微信主表</label>
						</td>
					</tr> --%>
					<tr>
						<td align="right">
							<label class="Validform_label">
								模板ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="templateId" name="templateId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCustomerTempPage.templateId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模板ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								模板名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="templateIdShort" name="templateIdShort" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCustomerTempPage.templateIdShort}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模板名称</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/customer/weixinCustomerTemp.js"></script>		