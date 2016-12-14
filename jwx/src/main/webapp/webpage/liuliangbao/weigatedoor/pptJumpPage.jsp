<%--
  Created by IntelliJ IDEA.
  User: aa
  Date: 2015/12/29
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <title></title>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="plug-in/jquery/jquery-1.10.1.min.js"></script>

    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/1218/style.css">
</head>
<body>
<section class="share-wrap">

    <div class="text" id="js_content">
        ${doorPPT.pageContent}
        <input id="inpagejumpType" type="text" style="display:none;" value="${doorPPT.jumpType}">
    </div>

    <div class="text" id="pageurlll">
        <iframe src="${doorPPT.jumpUrl}" style="height :600px;width: 450px;;"></iframe>
        <input id="inpageurl" type="text" style="display:none;" value="${doorPPT.jumpUrl}">
    </div>
</section>

<script type="text/javascript">
    document.getElementById("pageurlll").style.display = "none";
    window.onload = ready;
    function ready() {
//        var a = document.getElementById("inpageContent").value;
        var b = document.getElementById("inpagejumpType").value;
//        alert(a);
        /*if (a == null || a == "" || a == undefined) {*/
        if (b == "默认页面") {
            document.getElementById("pageurlll").style.display = "none";
            document.getElementById("js_content").style.display = "block";

        } else {
            document.getElementById("js_content").style.display = "none";
            window.location.href = "${doorPPT.jumpUrl}";
            //document.getElementById("pageurlll").style.display = "block";
        }
    }

</script>

</body>
</html>
