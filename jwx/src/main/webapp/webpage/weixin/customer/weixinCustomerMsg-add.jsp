<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>客服消息表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinCustomerMsgController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinCustomerMsgPage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinCustomerMsgPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinCustomerMsgPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							微信主表:
						</label>
					</td>
					<td class="value">
					     	 <input id="accountid" name="accountid" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">微信主表</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="sendOpenId" name="sendOpenId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送ID</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							接收ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="receiveOpenId" name="receiveOpenId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接收ID</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							消息内容:
						</label>
					</td>
					<td class="value">
					     	 <input id="content" name="content" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">消息内容</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/customer/weixinCustomerMsg.js"></script>		