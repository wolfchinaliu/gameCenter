<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js" language="javascript" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/showClient.js" language="javascript" ></script>
<title>显示客户信息</title>
<style type="text/css">
#ver-minimalist
{
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
	font-size: 12px;
	margin: 0px auto;
	width: 100%;
	text-align: left;
	border-collapse: collapse;

}
#ver-minimalist th
{
	padding: 8px 2px;
	font-weight: normal;
	font-size: 14px;
	border-bottom: 2px solid #6678b1;
	border-right: 30px solid #fff;
	border-left: 30px solid #fff;

	background-color:#FFF;
}
#ver-minimalist td
{
	padding: 10px 2px 0px 2px;
	border-right: 30px solid #fff;
	border-left: 30px solid #fff;

	background-color:#FFF;
}
#ver-minimalist .status
{
	padding: 10px 2px 0px 2px;
	border-right: 30px solid #fff;
	border-left: 30px solid #fff;

	background-color:#FFF;
	width:160px;
}
</style>
</head>
<body>
<!-- 隐藏域 -->
<input type="hidden" value="${pageContext.request.contextPath}/showClient/paging.htm" id="pagingUrl" >
<table id="ver-minimalist">
			<thead>
				<tr>
					<th>
						lab1
					</th>
					<th>
						lab2
					</th>
					<th>
						lab3
					</th>
					<th>
						state
					</th>
					<th>
						rechargeTime
					</th>
				</tr>
			</thead>
			<tbody id="mainTbody">
			<c:forEach items="${requestScope.usersList }" var="user">
				<tr>
					<td>
						${user.lab1 }
					</td>
					<td>
						${user.lab2 }
					</td>
					<td>
						${user.lab3 }
					</td>
					<td>
						<c:choose>
							<c:when test="${user.state==1 }">
								已充值
							</c:when>
							<c:when test="${user.state==2 }">
								充值成功但是要等待48小时
							</c:when>
							<c:otherwise>
								未充值
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${user.rechargeTime==null }">
								未充值没有充值日期
							</c:when>
							<c:otherwise>
								${user.rechargeTime }
							</c:otherwise>
						</c:choose>
					</td>
			</c:forEach>
			</tbody>
			<thead>
				<tr>
					<th colspan="5" align="center">
						Query
					</th>
				</tr>
			</thead>
			<tr>
				<td colspan="5"  align="center">
					<input type="button" value="首页" id="firstpage" />
                    <input type="button" value="上页" id="beforepage" />
                    
					<input type="text" value="1" size="1" id="index" disabled="disabled" >\
					<input type="text" value="${requestScope.totalPage}" size="1" id="totalPage" disabled="disabled">
					
					<input type="button" value="下页" id="nextpage" />
                    <input type="button" value="尾页" id="lastpage" />
                    &nbsp;
                    <a href="../client/init.htm">返回上一页...</a>
				</td>
			</tr>
			</tbody>
		</table>
</body>
</html>