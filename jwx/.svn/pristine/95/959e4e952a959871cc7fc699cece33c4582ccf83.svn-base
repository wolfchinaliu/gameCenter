<%--
  Created by IntelliJ IDEA.
  User: aa
  Date: 2015/12/10
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>微门户</title>
    <meta charset=utf-8>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <title>流量门户</title>
    <script type="text/javascript" src="plug-in/jquery/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="plug-in/jquery/jquery.slides.min.js"></script>
    <link type="text/css" rel="stylesheet" href="plug-in/liuliangbao/css/1214/personcenter.css" />


    <script type="text/javascript">
        function goFlowCenter() {
            //alert("123");
            $.ajax({
                type: 'POST',
                url: "weiDoorController.do?goFlowCenter",
                dataType: "json",
                data: {
                    "openId": '${openId}',
                    "accountid": '${accountid}',
                    "nickname": '${nickname}',
                    "headimgUrl": '${headimgUrl}'
                },
                cache: false,
                error: function (error) {
                    alert(error);
                },
                success: function (data) {
                    //alert(data);
                    if (data != null) {
                        window.location.href = "personFlowCenterController.do?goPersonCenter";
                    } else {
                        window.location.href = "personFlowCenterController.do?goPersonCenter";
                    }
                }
            });
        }
    </script>
</head>

<body>
<section>
    <figure class="gallery-wrap">
        <%--<div >
            <img src="http://xiaoguaii.ngrok.natapp.cn/xxg/1449798010624.jpg"/>&lt;%&ndash;weidoorpptListResult&ndash;%&gt;
            <figcaption>分享得流量，优惠多多</figcaption>
        </div>--%>
        <c:forEach items="${weidoorpptlist}" var="item">
            <div >
                <a href="${item.jumpUrl}"><img src="${item.pictureUrl}" /></a>
                <figcaption>分享得流量，优惠多多</figcaption>
            </div>
        </c:forEach>
    </figure>
    <nav class="nav-list">
        <ul>
            <li>
                <div>
                    <i class="icon-sign-in"></i>

                    <div>
                        <h2>签到领流量</h2>

                        <p>Sign in led traffic</p>
                    </div>
                </div>
            </li>
            <li>
                <div>
                    <i class="icon-share"></i>

                    <div>
                        <h2>分享得流量</h2>

                        <p>Share to flow</p>
                    </div>
                </div>
            </li>
            <li>
                <div>
                    <i class="icon-coupon"></i>
                    <div>
                        <h2>优惠券领取</h2>
                        <p>Coupon to receive</p>
                    </div>
                </div>
            </li>
            <li  onclick="goFlowCenter()">
                <div>
                    <i class="icon-mana"></i>
                    <div>
                        <h2>流量管理</h2>
                        <label hidden="hidden">${accountid}${openid}${headimgUrl}${nickname}</label>
                        <p>Traffic management</p>
                    </div>
                </div>
            </li>
            <li >
                <div>
                    <i class="icon-join"></i>
                    <div>
                        <h2>最新加盟</h2>
                        <p>The latest to join</p>
                    </div>
                </div>
            </li>
        </ul>
    </nav>
</section>
<script type="text/javascript">

    $(document).ready(function(){
        /*$("img").click(function(){
            //点击图片后发送跳转到指定页面的事件。
            var jumpUrl=document.getElementById()
            window.location.href="http://baidu.com";
        })*/
        $('.gallery-wrap').slidesjs({
            navigation: false
        });
        $('.nav-list li').on('click',function(){
            var idx = $(this).index();
            switch (idx){
                case 0:
                    window.location.href = 'signIn.html';
                    break;
                case 1:
                    window.location.href = 'share.html';
                    break;

            }
        });
    });
</script>
</body>
</html>

