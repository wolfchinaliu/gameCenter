<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>关注用户管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinMemberController.do?doUpdateGroup" tiptype="1">
	  <input type="hidden" id="openIds" name="openIds" value="${openIds}">
		<table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								昵称:
							</label>
						</td>
						<td class="value">
						     	${nickNames}
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								分组:
							</label>
						</td>
						<td class="value">
						     <select id="groupId" name="groupId" >
						  	 	<c:forEach items="${weixinGroupList }" var="group">
						  	 		<option value="${group.id}">${group.groupName}</option>
						  	 	</c:forEach>
						  	 </select>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/member/weixinMember.js"></script>		