<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>商家列表</title>

    <script src="plug-in/acctlist/js/common.js"></script>
    <link rel="stylesheet" type="text/css" href="http://cdn.1shiliu.com/plug-in/acctlist/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.1shiliu.com/plug-in/acctlist/css/index.css?_t=11122">
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
<article class="list">
    <h1>下面这些商家以各种姿势赠送免费流量：</h1>
    <section class="list-box">
        <ul class="list-list">
            <c:forEach items="${listAcct}" var="acct">
            <c:if test="${acct.focusflow !='0.0' or acct.signflow != '0.0' or acct.shareflow != '0.0' or acct.isPlay !=null}">
                         
                <li class="clx">
                    <a href="acctListController.do?showAcct&acctId=${acct.acctId}&openId=${openId}&accountid=${acct.accountid}">
                        <%--<figure class="list-img"><img src="plug-in/acctlist/images/acct_background.jpg" alt=""/>
                        </figure>--%>
                        <figure class="list-img">
                            <c:if test="${empty acct.pictureUrl}">
                                <img src="plug-in/acctlist/images/acct_background.jpg" alt=""/>
                            </c:if>
                            <c:if test="${not empty acct.pictureUrl}">
                                <img src=${imgpath}${acct.pictureUrl} alt=""/>
                            </c:if>
                        </figure>
                        <dl class="list-msg">
                            <dt>${acct.acctlistName}</dt>
                            <dd>
                                    <%--<c:choose>
                                        <c:when test="${acct.focusflow !=null} ">
                                            <span>首次关注+${acct.focusflow}M</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span></span>
                                        </c:otherwise>
                                    </c:choose>--%>
                                <c:if test="${acct.focusflow =='0.0'}">
                         
                                </c:if>
                                <c:if test="${acct.focusflow ne '0.0'}">
                                    <span>首次关注+${acct.focusflow}M</span>
                                </c:if>

                                <c:if test="${acct.signflow =='0.0'}">
                                   
                                </c:if>
                                <c:if test="${acct.signflow ne '0.0'}">
                                    <span>签到+${acct.signflow}M</span>
                                </c:if>

                                <c:if test="${acct.shareflow =='0.0'}">
                                   
                                </c:if>
                                <c:if test="${acct.shareflow ne '0.0'}">
                                    <span>分享+${acct.shareflow}M</span>
                                </c:if>

                                <c:if test="${acct.isPlay ==null}">
                                    
                                </c:if>
                                <c:if test="${not empty acct.isPlay}">
                                    <span>参加游戏</span>
                                </c:if>

                                <%--<span>首次关注+${acct.focusflow}M</span>--%>
                                <%--<span>每日签到+${acct.signflow}M</span>--%>
                                <%--<span>分享+${acct.shareflow}M</span>--%>
                                <%--<span>参加游戏</span>--%>
                            </dd>
                        </dl>
                    </a>
                </li>
                </c:if>
            </c:forEach>
        </ul>
    </section>
</article>
</body>
</html>
