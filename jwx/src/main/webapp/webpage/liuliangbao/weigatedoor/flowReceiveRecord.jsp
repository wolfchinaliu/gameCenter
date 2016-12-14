<%--
  Created by IntelliJ IDEA.
  User: aa
  Date: 2015/12/16
  Time: 9:34
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
    <%--<link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/1218/style.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/1228/style.css">
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/1219/flow-list.css">--%>
    <%--<script type="text/javascript" src="plug-in/jquery/jquery.slides.min.js"></script>--%>
    <script type="text/javascript" src="plug-in/jquery/jquery-1.10.1.min.js"></script>


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
            <a class="btn btn-normal">领取记录</a>
        </div>
        <div>
            <a class="btn on">充值记录</a>
        </div>
    </nav>
    <nav class="table-list">
            <ul>
                <c:forEach items="${userFlowChargedBean}" var="item">
                    <li>
                        <div class="table-cell">
                            <div>
                                <span>${item.disposeDate}提现</span>
                                <p>${item.phoneNumber}</p>
                            </div>
                            <div class="t-right">
                                <span>+${item.flowValue}M</span>
                                <p style="width: auto;font-size: 10px;" align="right">${item.chargeState}</p>
                            </div>
                        </div>
                        <div class="drop">
                            <div class="progress">
                                <div>
                                    <span></span>
                                    <p>${item.getStatus}</p>
                                </div>
                                <div>
                                    <span></span>
                                    <p>处理中</p>
                                </div>
                                <div>
                                    <span></span>
                                    <p>${item.chargeState}(原因:${item.resultReason})</p>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>

                <%--<li>
                    <div class="table-cell">
                        <div>
                            <span>2015-11-22领取方式</span>

                            <p>号码</p>
                        </div>
                        <div class="t-right">
                            <span>+X.XM</span>

                            <p>已成功</p>
                        </div>
                    </div>
                    <div class="drop">
                        <div class="progress">
                            <div>
                                <span></span>

                                <p>从某某获得</p>
                            </div>
                            <div>
                                <span></span>

                                <p>处理中</p>
                            </div>
                            <div>
                                <span></span>

                                <p>未成功(原因:不明)</p>
                            </div>
                        </div>
                    </div>
                </li>--%>
            </ul>
    </nav>


    </section>

</article>
<%--</section>--%>

<script type="text/javascript">
    $(document).ready(function() {
        $('.table-list li').on('click',function(){
            $('.table-list .drop').removeClass('expand');
            $(this).find('.drop').addClass('expand');

        });

        $('.btn-normal').on('click',function(){
            //alert("123");
            window.location.href = "userFlowRecordController.do?goUserFlowRecord";
        });
    });
</script>

</body>
</html>

