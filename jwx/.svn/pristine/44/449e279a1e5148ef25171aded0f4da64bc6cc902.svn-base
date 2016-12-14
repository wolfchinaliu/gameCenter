<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui"></t:base>

<div>

<form action="reportController.do?memberList" method="post" >
日期：<input id="begin_date" name="begin_date" type="text" class="easyui-datebox" required="required"> - 
<input id="end_date" name="end_date" type="text" class="easyui-datebox" required="required"> 
<input type="submit" value="分析">
</form>

<table  cellpadding="0" cellspacing="1" style="border:1 solid;width: 600px;">
	<tr>
		<td>月份</td>
		<td>渠道</td>
		<td>新增关注</td>
		<td>取消关注</td>
		<td>总粉丝量</td>
	</tr>
	<c:forEach items="${memberList}" var="member">
	<tr>
		<td>${member.ref_date }</td>
		<td>${member.user_source }</td>
		<td>${member.new_user }</td>
		<td>${member.cancel_user }</td>
		<td>${member.cumulate_user }</td>
	</tr>
	</c:forEach>
</table>
</div>