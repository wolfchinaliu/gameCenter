<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>群发消息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinMessageGroupController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinMessageGroupPage.id }">
					<input id="accountid" name="accountid" type="hidden" value="${weixinMessageGroupPage.accountid }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right" width="20%">
							<label class="Validform_label">
								消息内容:
							</label>
						</td>
						<td class="value">
						  	 	<textarea id="note" style="width:500px;" class="inputxt" rows="6" name="note">${weixinMessageGroupPage.note}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">消息内容</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发送时间:
							</label>
						</td>
						<td class="value">
									  <input id="createTime" name="createTime" type="text" style="width: 150px" 
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${weixinMessageGroupPage.createTime}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建时间</label>
						</td>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/message/weixinMessageGroup.js"></script>		