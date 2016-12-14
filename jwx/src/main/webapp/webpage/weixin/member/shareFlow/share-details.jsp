<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <title>分享文章的名字</title>
    <script src="plug-in/liuliangbao/js/1220/jquery-1.10.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/1220/style.css">
</head>
<body>
<section class="share-wrap">
    <header>
        <div class="share-title">分享文章的名字</div>
        <p>2015-11-07</p>
        <a class="btn btn-primary share-link"
           href="shareFlowController.do?QRcodePage&sub=${sub}&openId=${openId}&accountid=${accountid}">关注后分享赠送X.XM流量</a>
    </header>
</section>

<script type="text/javascript">
</script>
</body>
</html>
