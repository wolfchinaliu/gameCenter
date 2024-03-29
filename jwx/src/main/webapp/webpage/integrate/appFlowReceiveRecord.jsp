<%--
  Created by WangPeng
  Date: 2016/10/31
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
    <script type="text/javascript" src="plug-in/jquery/jquery-1.10.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0304/css/normalize.css">
    <script src="plug-in/liuliangbao/css/0304/js/common.js"></script>
</head>
<body>

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
            </ul>
    </nav>
    </section>
</article>
<script type="text/javascript">
    $(document).ready(function() {
        $('.table-list li').on('click',function(){
            $('.table-list .drop').removeClass('expand');
            $(this).find('.drop').addClass('expand');

        });

        $('.btn-normal').on('click',function(){
            window.location.href = "integrate.do?app_userGiveFlow_getRecords&acctId=${acctId}&data=${data}";
        });
    });
</script>

</body>
</html>

