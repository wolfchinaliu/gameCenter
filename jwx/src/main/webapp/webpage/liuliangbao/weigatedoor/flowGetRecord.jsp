<%--
  Created by IntelliJ IDEA.
  User: aa
  Date: 2015/12/12
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>流量记录</title>
   <%--<link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/1218/flow-list.css">--%>
    <script src="plug-in/liuliangbao/js/1218/jquery-1.10.1.min.js"></script>
   <%-- <script src="plug-in/liuliangbao/js/1218/jquery.slides.min.js"></script>--%>
    <%--<script type="text/javascript" src="plug-in/liuliangbao/js/1218/jQueryRotate.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/js/1218/util.js"></script>--%>
    <%--<link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/1218/style.css">--%>

    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0304/css/normalize.css">
    <script src="plug-in/liuliangbao/css/0304/js/common.js"></script>

<script>
 function onBridgeReady(){
WeixinJSBridge.call('hideOptionMenu');
}

if (typeof WeixinJSBridge == "undefined"){
if( document.addEventListener ){
document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
}else if (document.attachEvent){
document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
}
}else{
onBridgeReady();
} 
</script> 
</head>
<body>

<%--<section class="flow-list">--%>
<article class="container">
    <section class="main main-list">
    <nav class="nav-tab">
        <div>
            <%--<a class="btn btn-primary">获取记录</a> --%>
            <a class="btn on">领取记录</a>
        </div>
        <div>
            <a class="btn btn-normal">充值记录</a>
        </div>
    </nav>
    <nav class="table-list flowget">
        <ul>

            <c:forEach items="${userFlowJson}" var="item">
                <li class="table-cell">
                    <div>
                        <span>${item.operateDate} ${item.reason}</span>
                        <p>${item.merchantName}</p>
                    </div>
                    <div class="t-right">+${item.flowValue}M</div>
                </li>
            </c:forEach>

        </ul>


    </nav>

    </section>
</article>
<%--</section>--%>

<script type="text/javascript">
    $(document).ready(function () {
        $('.btn-normal').on('click',function(){
            window.location.href = "userFlowRecordController.do?goUserReceiveRecord";
        });
    });
</script>


</body>
</html>
