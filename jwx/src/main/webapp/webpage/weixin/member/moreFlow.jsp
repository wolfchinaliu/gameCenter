<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <title>更多免费流量</title>
    <script src="plug-in/liuliangbao/js/1220/jquery-1.10.1.min.js"></script>
    <script src="plug-in/liuliangbao/js/1220/jquery.slides.min.js"></script>
    <%--<link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/1220/style.css">--%>
    <script src="plug-in/liuliangbao/js/0104/util.js"></script>
    <link type="text/css" rel="stylesheet" href="plug-in/liuliangbao/css/0104/style.css" />
</head>

<%--<%@include file="/context/mytags.jsp" %>--%>
<%--<jsp:include page="/inc.jsp"></jsp:include>--%>
<%--<t:base type="jquery,easyui,tools,DatePicker"></t:base>--%>

<body>
<section class="more-flow">
    <figure class="gallery-wrap">
        <%--<div>--%>
        <%--<img src="plug-in/liuliangbao/css/1215/images/1.jpg"/>--%>
        <%--<figcaption>分享得流量，优惠多多</figcaption>--%>
        <%--</div>--%>

        <%--<c:forEach items="${weidoorpptlist}" var="item">--%>
            <%--<div>--%>
                <%--<a href="javascript:;" onclick="jumpFun()"><img src="${item.pictureUrl}"/></a>--%>
                <%--&lt;%&ndash;<a href="${item.jumpUrl}"><img src="${item.pictureUrl}"/></a>&ndash;%&gt;--%>
                <%--<figcaption>分享得流量，优惠多多</figcaption>--%>
                <%--<input id="itemJumpType" type="hidden" value="${item.jumpType}"/>--%>
                <%--<input id="itemPageContent" type="hidden" value="${item.pageContent}"/>--%>
                <%--<input id="itemJumpUrl" type="hidden" value="${item.jumpUrl}"/>--%>
            <%--</div>--%>
        <%--</c:forEach>--%>

            <c:forEach items="${weidoorpptlist}" var="item">
                <div>
                    <a onclick="window.location.href = 'pptjumpPageController.do?goPPTJumpPage&pptid=${item.id}'"><img src="${item.pictureUrl}" /></a >
                    <figcaption>分享得流量，优惠多多</figcaption>
                </div>
            </c:forEach>
        <%--<div>--%>
        <%--<img src="plug-in/liuliangbao/css/1215/images/test.jpg"/>--%>
        <%--<figcaption>分享得流量，优惠多多</figcaption>--%>
        <%--</div>--%>

    </figure>
    <nav>
        <ul>
            <li>
                <div>
                    <span class="icon-heart"></span>

                    <p>关注</p>
                </div>
            </li>
            <li>
                <div>
                    <span class="icon-edit"></span>

                    <p>签到</p>
                </div>
            </li>
            <li>
                <div>
                    <span class="icon-game"></span>

                    <p>游戏</p>
                </div>
            </li>
            <li>
                <div>
                    <span class="icon-share"></span>

                    <p>分享</p>
                </div>
            </li>
            <li>
                <div>
                    <span class="icon-flow"></span>

                    <p>流量卡</p>
                </div>
            </li>
            <li>
                <div>
                    <span class="icon-more"></span>
                </div>
            </li>
            <!-- <li>
                <div>
                    <a href="weixin://contacts/profile/kugounet">一点关注微信</a>
                    <br>
                    <a href="http://www.weixin//profile/gh_c1b63b38cea0">微信聚</a>
                    <br>
                    <a href="weixin://profile/kugounet">关注test</a>

                    <a href="weixin://contacts/profile/gh_bf8de102ad66">别人能看到的链接文字</a>
                </div>
            </li> -->
        </ul>
    </nav>
</section>

<script type="text/javascript">

    function jumpFun() {
        var strJumpType = document.getElementById("itemJumpType").value;
        //alert(strJumpType);
        var strPageContent = document.getElementById("itemPageContent").value;
        //alert(strPageContent);
        var strJumpUrl = document.getElementById("itemJumpUrl").value;
        //alert(strJumpUrl);

        if (strJumpType == "默认页面") {
            window.location.href = "pptjumpPageController.do?goPPTJumpPage&pageContent=" + strPageContent;
        } else {
            window.location.href = strJumpUrl;
        }

    }
    $(document).ready(function () {
        $('.gallery-wrap').slidesjs({
            navigation: false
        });
        $('.more-flow nav li').on('click', function () {
            var idx = $(this).index();
            switch (idx) {
                case 0:
                    window.location.href = 'earnFlowController.do?mysubscribe&bid=1&accountid=${accountid}&openId=${openId}';
                    break;
                case 1:
                    window.location.href = 'earnFlowController.do?mysubscribe&bid=2&accountid=${accountid}&openId=${openId}';
                    break;
                case 2:
                    window.location.href = 'gameFlowController.do?myLotty&accountid=${accountid}&openId=${openId}';
                    break;
                case 3:
                    window.location.href = 'shareFlowController.do?myShare&accountid=${accountid}&phone=${phoneNumber}&openId=${openId}';
                    break;
                case 4:
                    $.mobAlert("此功能暂未开放，敬请期待！");
                    break;
                case 5:
                    window.location.href = 'earnFlowController.do?myallActivity&accountid=${accountid}&phone=${phoneNumber}&openId=${openId}';
                    break;

            }
        });
    });
</script>
</body>
</html>

<%--gergserhsejrsjrsj--%>

<%--<div class="easyui-layout" fit="true">--%>
<%--<tr>--%>
<%--<td>--%>
<%--<a href="earnFlowController.do?mysubscribe&bid=关注"><img--%>
<%--src="easyui/common/images/QRcode.jpg"/>关注</a>--%>
<%--</td>--%>
<%--<td>--%>
<%--<a href="earnFlowController.do?mysubscribe&bid=签到"><img--%>
<%--src="easyui/common/images/QRcode.jpg"/>签到</a>--%>
<%--</td>--%>
<%--</tr>--%>

<%--<tr>--%>
<%--<td>--%>
<%--<a href="gameFlowController.do?myLotty"><img--%>
<%--src="easyui/common/images/QRcode.jpg"/>游戏</a>--%>

<%--</td>--%>
<%--<td>--%>
<%--<a href="shareFlowController.do?myShare"><img--%>
<%--src="easyui/common/images/QRcode.jpg"/>分享</a>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</div>--%>


