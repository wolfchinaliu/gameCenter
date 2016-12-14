<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
  <t:base type="jquery,easyui,tools"></t:base>
  <link rel="stylesheet" type="text/css" href="plug-in/lhgDialog/skins/default.css">
  <script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
 </head>
 <body>
	<c:if  test="${empty weixinAccountList}">
  		<p>暂时无其他公众号，请添加公众号再操作</p>
   </c:if>
   
   <c:if  test="${not empty weixinAccountList}">
  		<p style="padding-top:15px;"><h4>复制给公众号：</h4></p>
   </c:if>
   <br>
<t:formvalid formid="formobj" layout="div" dialog="true" action="newsTemplateController.do?doCopy" refresh="true" >
 
   <input id="ids" name="ids" type="hidden" value="${ids}">
  
  
   
   <c:if test='${WEIXIN_ACCOUNT.id != weixinAccountEntity.id}'>
	<div class="form">
		<label class="form"> </label> 
		&nbsp;&nbsp;<input type="radio" value="${weixinAccountEntity.id }" id="accountid" name="accountid">
		&nbsp;&nbsp;${weixinAccountEntity.accountname }
	</div>
	</c:if>

   <c:forEach items="${weixinAccountList}" var="weixinAccount">
	
		<c:if test='${WEIXIN_ACCOUNT.id != weixinAccount.id}'>
			<div class="form">
				<label class="form"> </label>
				&nbsp;&nbsp;
				<input type="radio" id="accountid" name="accountid" value="${weixinAccount.id }">
				&nbsp;&nbsp;${weixinAccount.accountname}
			</div>
		</c:if>
	</c:forEach>
	
  </t:formvalid>
 </body>