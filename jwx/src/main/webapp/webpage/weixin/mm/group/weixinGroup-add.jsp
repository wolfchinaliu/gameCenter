<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>微信组</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinGroupController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinGroupPage.id }">
					<input id="groupId" name="groupId" type="hidden" value="${weixinGroupPage.groupId }">
					<input id="accountid" name="accountid" type="hidden" value="${weixinGroupPage.accountid }">
					<input id="synchStatu" name="synchStatu" type="hidden" value="${weixinGroupPage.synchStatu }">
					<input id="createName" name="createName" type="hidden" value="${weixinGroupPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinGroupPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							分组名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="groupName" name="groupName" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分组名称</label>
						</td>
				</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/mm/weixinGroup.js"></script>		