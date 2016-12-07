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
<script type="text/javascript">
	function check() {
		var excel_file = $("#excel_file").val();
		if (excel_file == "" || excel_file.length == 0) {
			alert("请选择文件路径！");
			return false;
		} else {
			return true;
		}
	}

	$(document).ready(function() {
		var msg = "";
		if ($("#importMsg").text() != null) {
			msg = $("#importMsg").text();
		}
		if (msg != "") {
			alert(msg);
		}
	});

	function fluxCheck() {
		var value = $("input[name=flux]").val()
		var reg = /^\d+$/;
		if ($.trim(value) != "") {//去前后空格判断
			var re = value.match(reg);
			if (re == null) {
				alert("请输入数字！")
			} else {
				return true;
			}
		} else {
			alert("请输入流量值！");
		}
		return false;
	}
</script>
<body>
	<a href="download.htm?fileName=muban.xls">下载Exel模板</a> &nbsp;&nbsp;
	<a href="../showClient/init.htm">查看已导入客户</a>
	<br>
	<div>
		<font color="bule">批量导入客户</font>
	</div>
	<form action="batchimport.htm" method="post"
		enctype="multipart/form-data" onsubmit="return check();">
		<div style="margin: 30px;">
			<input type="hidden" name="token" value="${token}"> <input
				id="excel_file" type="file" name="filename" accept="xlsx" size="80" />
			<input id="excel_button" type="submit" value="导入Excel" />
		</div>
		<font id="importMsg" color="red"><%=importMsg%></font>
	</form>
	<c:choose>
		<c:when test="${sessionScope.signal==-1}">
			<div>
				<font color="bule">请设置流量：</font>
			</div>
			<form action="insertFlux.htm" method="post" id="fluxForm" onsubmit="return fluxCheck()">
				<input type='text'
					onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
					onblur="this.v();" name="flux" />
				<input type="submit" value="提交">
			</form>
		</c:when>
		<c:otherwise>
					您设置的流量值为：${sessionScope.signal}
		</c:otherwise>
	</c:choose>
</body>
</html>