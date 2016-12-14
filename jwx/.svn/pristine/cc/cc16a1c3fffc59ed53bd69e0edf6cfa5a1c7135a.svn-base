<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <title>分享得流量</title>
    <script src="plug-in/liuliangbao/js/1220/jquery-1.10.1.min.js"></script>
    <script src="plug-in/liuliangbao/js/1220/jquery.slides.min.js"></script>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/1220/style.css">
</head>
<body>
<section class="gain-flow">

    <ul class="join-list">
        <c:forEach items="${listFor}" var="mer">
            <li>
                <figure>
                    <div>
                            <%--<a href="shareFlowController.do?startShare&accountid=${mer.accountid}"><img--%>
                            <%--src="${mer.imagepath}"/></a> --%>
                        <a href="shareController.do?startLoad&accountid=${mer.accountid}&phone=${phoneNumber}&openId=${openId}&shareId=${mer.shareTitle}"><img
                                src="${mer.imagepath}"/></a>
                    </div>
                    <figcaption><span class="flow">${mer.flowValue}M</span><span
                            class="name">${mer.title}</span>
                    </figcaption>
                </figure>
            </li>
        </c:forEach>
    </ul>
    <div>
        <strong>${message}</strong>

    </div>

</section>

<script type="text/javascript">
    $(document).ready(function () {

    });
</script>
</body>
</html>

