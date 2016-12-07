<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String importMsg = "";
	if (request.getSession().getAttribute("msg") != null) {
		importMsg = request.getSession().getAttribute("msg").toString();
	}
	request.getSession().setAttribute("msg", "");
%>
<head>
<title>批量导入客户</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/addCustomerData.js" language="javascript" ></script>

<body>
	<!-- 隐藏域 -->
	<input type="hidden" value="${pageContext.request.contextPath}/addCustomer/insertFlux.htm" id="insertClientUrl" >

	<a href="${pageContext.request.contextPath}/addCustomer/batchExport/all.htm?fileName=participationList">导出参与者名单</a>
	&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/addCustomer/batchExport/award.htm?fileName=winningList">导出中奖名单</a>
	<br>
	<div>
		<font color="bule">批量导入客户</font>
	</div>
	<form action="${pageContext.request.contextPath}/addCustomer/batchimport.htm" method="post"
		enctype="multipart/form-data" onsubmit="return check();">
		<div style="margin: 30px;">
			<input type="hidden" name="token" value="${token}"> <input
				id="excel_file" type="file" name="filename" accept="xlsx" size="80" />
			<input id="excel_button" type="submit" value="导入Excel" />
		</div>
		<font id="importMsg" color="red"><%=importMsg%></font>
	</form>
			<div>
				<font color="bule">单个用户信息添加：</font>
			</div>
			<form  method="post" id="fluxForm">
				手机号：		<input type='text' onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" name="phone" />
				截至日期：	<input type='text' name="uptodate">
				使用流量：	<input type='text' onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" name="flux" />
				
				
				<c:choose>
					<c:when test="${sessionScope.gameType==1}">
						总次数：
					</c:when>
					<c:otherwise>
						状态：
					</c:otherwise>
				</c:choose>
				
				<input type='text' onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" name="totalOrStatus" />
				<input type="button" value="提交" onclick="insertClient()">
			</form>
</body>
</html>