<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <%--<meta charset=utf-8>--%>
    <%--<meta name="viewport"--%>
          <%--content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>--%>
    <%--<meta content="yes" name="apple-mobile-web-app-capable"/>--%>
    <%--<meta content="black" name="apple-mobile-web-app-status-bar-style"/>--%>
    <%--<title>流量门户</title>--%>
        <meta charset=utf-8>
        <!-- 优先使用最新版本 IE 和 Chrome -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
        <meta content="yes" name="apple-mobile-web-app-capable"/>
        <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
        <meta name="format-detection" content="telphone=no, email=no"/>
        <title>流量门户</title>
    <%--<script src="plug-in/liuliangbao/js/0112/jquery-1.10.1.min.js"></script>--%>
    <%--<script src="plug-in/liuliangbao/js/0112/jquery.slides.min.js"></script>--%>
    <%--<script src="plug-in/liuliangbao/js/0112/util.js"></script>--%>
    <%--<link type="text/css" rel="stylesheet" href="plug-in/liuliangbao/css/0112/style.css" />--%>

    <script src="plug-in/liuliangbao/js/0201/common.js"></script>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0301/normalize.css">
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/index.css">
    <script>

		function onBridgeReady() {
			WeixinJSBridge.call('hideOptionMenu');
		}
	
		if (typeof WeixinJSBridge == "undefined") {
			if (document.addEventListener) {
				document.addEventListener('WeixinJSBridgeReady',
						onBridgeReady, false);
			} else if (document.attachEvent) {
				document.attachEvent('WeixinJSBridgeReady',
						onBridgeReady);
				document.attachEvent('onWeixinJSBridgeReady',
						onBridgeReady);
			}
		} else {
			onBridgeReady();
		}
	</script>
</head>

<body>
<div hidden="hidden">
    <input id="openid" type="text" hidden="hidden" value="${openId}">
    <input id="accountid" type="text" hidden="hidden" value="${accountid}">
    <input id="nickname" type="text" hidden="hidden" value="${nickname}">
    <input id="headimgUrl" type="text" hidden="hidden" value="${headimgUrl}">
</div>

<article class="container">
    <!--轮播-->
    <section class="ui-slider" id="j-scroll">
        <c:forEach items="${weidoorpptlist}" var="item">
        <figure class="scroll-img">

                <a onclick="window.location.href ='pptjumpPageController.do?goPPTJumpPage&pptid=${item.id}'"><img src="${item.pictureUrl}"  lazyload="${item.pictureUrl}" alt="" /></a >
                <figcaption>${item.title}</figcaption>
        </figure>
        </c:forEach>

    </section>
    <!--导航-->
    <nav class="nav">
        <ul class="nav-list clx">
            <li>
                <a href="personFlowCenterController.do?goPersonCenter&accountid=${accountid}&openId=${openId}" class="nav-btn">
                    <img src="plug-in/liuliangbao/img/1215/123456789.png" alt="" />
                    <span>流量中心</span>
                </a>
            </li>
            <c:forEach items="${weixinMain}" var="weixin">
                <li>
                    <a href="${weixin.pagetype}&openId=${openId}" class="nav-btn">
                        <img src="${weixin.imagepath}" alt="" />
                        <span>${weixin.title}</span>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </nav>

</article>
<script type="text/javascript" src="plug-in/liuliangbao/js/0201/zepto-all-min.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/js/0201/slider.js"></script>
<script>
    $(function () {
        $('#j-scroll').slider({ loop: true, autoPlay: false, showArr: false });


    });

</script>
</body>


</html>
