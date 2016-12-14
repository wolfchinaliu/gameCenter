<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>微信菜单组</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinMenuGroupController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinMenuGroupPage.id }">
					<input id="synchDate" name="synchDate" type="hidden" value="${weixinMenuGroupPage.synchDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							组名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="groupName" name="groupName" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">组名称</label>
						</td>
				</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/guanjia.menu/weixinMenuGroup.js"></script>		