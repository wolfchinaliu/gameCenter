<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>单发消息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinMessageController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinMessagePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								消息内容:
							</label>
						</td>
						<td class="value">
						  	 	${weixinMessagePage.note}
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发送时间:
							</label>
						</td>
						<td class="value">
									 <fmt:formatDate value='${weixinMessagePage.createTime}' type="date" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								消息接收用户:
							</label>
						</td>
						<td class="value">
						     	 ${weixinMessagePage.receiveUserName}
						</td>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/message/weixinMessage.js"></script>		