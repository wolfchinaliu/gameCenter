<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<meta name="format-detection" content="telphone=no, email=no" />
<title><c:if test="${acctListEntity.accountname ==null}">
		${acctListEntity.acctlistName}
		</c:if> <c:if test="${not empty acctListEntity.accountname}">
		${acctListEntity.accountname}
		</c:if></title>
<script src="plug-in/acctlist/js/common.js"></script>
<link rel="stylesheet" type="text/css"
	href="${cdnHost}/plug-in/acctlist/css/normalize.css">
<link rel="stylesheet" type="text/css"
	href="${cdnHost}/plug-in/acctlist/css/index.css">
</head>
<body>

	<article class="details"> <c:if
		test="${empty acctListEntity.pictureUrl}">
		<figure class="details-img">
		<img src="plug-in/acctlist/images/acct_background.jpg" alt=""
			height="200" /></figure>

	</c:if> <c:if test="${not empty acctListEntity.pictureUrl}">
		<figure class="details-img">
		<img src='${acctListEntity.pictureUrl}' alt="" height="200" /></figure>
	</c:if> <nav class="details-nav">
	<ul class="clx">
		<li>
			<c:if test="${acctListEntity.focusflow =='0.0'}">
				<figure>
				<img src="plug-in/acctlist/images/btn01.1.png" alt="" /></figure>
				<p class="Validform_checktip1" style="color: #999">关注+${acctListEntity.focusflow}</p>
			</c:if> 
			<c:if test="${acctListEntity.focusflow ne '0.0'}">
				     <c:if test="${acctListEntity.accounttype == '1'}">
							<a
								href="mainController.do?load&accountid=${acctListEntity.accountid}">
								<figure>
								<img src="plug-in/acctlist/images/btn01.png" alt="" /></figure>
								<p>关注+${acctListEntity.focusflow}</p>
							</a>
					</c:if> 
					<c:if test="${acctListEntity.accounttype ne '1'}">
							<a
								href="acctListController.do?goAcctQRcode&accountid=${acctListEntity.accountid} ">
								<figure>
								<img src="plug-in/acctlist/images/btn01.png" alt="" /></figure>
								<p>关注+${acctListEntity.focusflow}</p>
							</a>
					</c:if>
			</c:if></li>
		<li>
			<c:if test="${acctListEntity.signflow =='0.0'}">
					<figure>
					<img src="plug-in/acctlist/images/btn02.1.png" alt="" /></figure>
					<p class="Validform_checktip1" style="color: #999">签到+${acctListEntity.signflow}</p>
			</c:if>
			<c:if test="${acctListEntity.signflow ne'0.0'}">
				 	<c:if test="${acctListEntity.accounttype == '1'}">
						<a
							href="signController.do?startLoad&accountid=${acctListEntity.accountid}">
							<figure>
							<img src="plug-in/acctlist/images/btn02.png" alt="" /></figure>
							<p>签到+${acctListEntity.signflow}</p>
						</a>
					</c:if> 
					<c:if test="${acctListEntity.accounttype ne '1'}">
						<a
						href="acctListController.do?goAcctQRcode&accountid=${acctListEntity.accountid}">
						<figure>
						<img src="plug-in/acctlist/images/btn02.png" alt="" /></figure>
						<p>签到+${acctListEntity.signflow}</p>
						</a>
					</c:if>
			</c:if>
		</li>
		<li>
			<c:if test="${acctListEntity.shareflow =='0.0'}">
				<figure>
				<img src="plug-in/acctlist/images/btn03.1.png" alt="" /></figure>
				<p class="Validform_checktip1" style="color: #999">分享+${acctListEntity.shareflow}</p>
			</c:if> 
			<c:if test="${acctListEntity.shareflow ne'0.0'}">
				<c:if test="${acctListEntity.accounttype == '1'}">
						<a
							href="shareController.do?startLoad&accountid=${acctListEntity.accountid}&shareId=${acctListEntity.shareId}">
							<figure>
							<img src="plug-in/acctlist/images/btn03.png" alt="" /></figure>
							<p>分享+${acctListEntity.shareflow}</p>
						</a>
				</c:if>
				<c:if test="${acctListEntity.accounttype ne '1'}">
						<a
							href="acctListController.do?goAcctQRcode&accountid=${acctListEntity.accountid}">
							<figure>
							<img src="plug-in/acctlist/images/btn03.png" alt="" /></figure>
							<p>分享+${acctListEntity.shareflow}</p>
						</a>
				</c:if>
			</c:if>
		</li>
		<li>
		  <c:if test="${acctListEntity.isPlay ==null}">
				<figure>
				<img src="plug-in/acctlist/images/btn04.png" alt="" /></figure>
				<p class="Validform_checktip1" style="color: #999">游戏+10.0M</p>
		  </c:if> 
		  <c:if test="${not empty acctListEntity.isPlay}">
			    <c:if test="${acctListEntity.accounttype == '1'}">
					<a
						href="mainController.do?load&accountid=${acctListEntity.accountid}">
						<figure>
						<img src="plug-in/acctlist/images/btn04.1.png" alt="" /></figure>
						<p>游戏+10.0M</p>
					</a>
				</c:if>
				<c:if test="${acctListEntity.accounttype ne '1'}">
					<a
						href="acctListController.do?goAcctQRcode&accountid=${acctListEntity.accountid}">
						<figure>
						<img src="plug-in/acctlist/images/btn04.1.png" alt="" /></figure>
						<p>游戏+10.0M</p>
					</a>
				</c:if>
			</c:if>
		</li>
	</ul>
	</nav>
	<address class="details-address">
		<ul>
			<li><i class="phone"></i><a href='tel://${acctListEntity.phone}'>${acctListEntity.phone}</a></li>
			<li><i class="address"></i><a
				href="addressController.do?viewMap&acctId=${acctListEntity.acctId}">${acctListEntity.point}</a></li>
		</ul>
	</address>
	<article class="details-introduce">
	<h2>
		<i class="introduce"></i>商家介绍
	</h2>
	<p>${acctListEntity.description}</p>
	</article> <footer class="footer2"> <c:if
		test="${acctListEntity.accountname ==null}">
		      <c:if test="${acctListEntity.accounttype == '1'}">
						<a
							href="mainController.do?load&accountid=${acctListEntity.accountid}" class="a1">
点击进入商家公众号：${acctListEntity.acctlistName}
						</a>
				</c:if>
				<c:if test="${acctListEntity.accounttype ne '1'}">
						<a
							href="acctListController.do?goAcctQRcode&accountid=${acctListEntity.accountid}" class="a1">
点击进入商家公众号：${acctListEntity.acctlistName}
						</a>
				</c:if>
	</c:if> <c:if test="${not empty acctListEntity.accountname}">
		<c:if test="${acctListEntity.accounttype == '1'}">
						<a
							href="mainController.do?load&accountid=${acctListEntity.accountid}" class="a1">
点击进入商家公众号：${acctListEntity.accountname}
						</a>
				</c:if>
				<c:if test="${acctListEntity.accounttype ne '1'}">
						<a
							href="acctListController.do?goAcctQRcode&accountid=${acctListEntity.accountid}" class="a1">
点击进入商家公众号：${acctListEntity.accountname}
						</a>
				</c:if>
	</c:if> <a href="javascript:;" class="a2"><img
		src="plug-in/acctlist/images/footer2.jpg" alt="" /></a>
	<ul class="footer-nav">
		<c:choose>
			<c:when test="${not empty acctListEntity.shoppAddress}">
				<c:set var="width" value="width:33.33%"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="width" value="width:50%"></c:set>
			</c:otherwise>
		</c:choose>

		<li style="<c:out value="${width }"/>"><a href="#">门店介绍</a></li>
		<c:if test="${acctListEntity.shoppAddress ==null}">
		</c:if>
		<c:if test="${not empty acctListEntity.shoppAddress}">
			<li style="<c:out value="${width }"/>"><a
				href="${acctListEntity.shoppAddress}">商城</a></li>
		</c:if>
		<li style="<c:out value="${width }"/>"><a
			href='tel://${acctListEntity.phone}'>一键呼叫</a></li>
	</ul>
	</footer> </article>


</body>
</html>