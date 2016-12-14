<%@ taglib prefix="t" uri="/easyui-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<%--<t:base type="jquery,DatePicker"></t:base>--%>
<t:base type="jquery,easyui,DatePicker"></t:base>
<%--<t:base type="jquery,easyui,tools,DatePicker"></t:base>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>猜灯谜拿流量</title>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0218/zimi/normalize.css">
    <script src="plug-in/liuliangbao/js/0218/lib/common.js"></script>
    <%--分享--%>
    <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript">
        //关于分享链接的一些配置
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: '${ret.appId}', // 必填，公众号的唯一标识
            timestamp:${ret.timestamp}, // 必填，生成签名的时间戳
            nonceStr: '${ret.nonceStr}', // 必填，生成签名的随机串
            signature: '${ret.signature}',// 必填，签名，见附录1
            jsApiList: [
                'onMenuShareTimeline',
                'onMenuShareAppMessage'
            ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.ready(function () {
            wx.onMenuShareTimeline({
                title: '${hdEntity.title} 向${provinceAccount}的手机用户赠送流量。', // 分享标题
                link: '${link}', // 分享链接
                imgUrl: '${memberEntity.headImgUrl}', // 分享图标
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });
            wx.onMenuShareAppMessage({
                title: '${hdEntity.title}', // 分享标题
                desc: '向${provinceAccount}的手机用户赠送流量。', // 分享描述
                link: '${link}', // 分享链接
                imgUrl: '${memberEntity.headImgUrl}', // 分享图标
                type: '', // 分享类型,music、video或link，不填默认为link
                dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });
        });
        wx.error(function (res) {

        });

        $(function () {
           if ('${phoneNumber}' == '') {
               $("#phoneNumberContent").html("尚未绑定");
           }
        });
    </script>

</head>
<body>
<article class="container">
    <!--页头-->
    <header class="header header-makered">
        <figure class="photo p140-140">
            <a href="#"><img src="${memberEntity.headImgUrl}" alt=""/></a>
        </figure>
        <ul class="header-msg-makered">
            <li>昵称：${memberEntity.nickName}</li>
            <li>手机号：<span id="phoneNumberContent">${memberEntity.phoneNumber}</span> </li>
            <li>省内流量：<span id="j-liuliang-shen">${provinceFlowValue}</span>M </li>
            <li>全国流量：<span id="j-liuliang-guo">${countryFlowValue}</span>M</li>
        </ul>
    </header>

    <section class="main">

        <p class="main-text main-text-red mbrem02 t-center">
            此活动对${provinceAccount}手机用户赠送流量
        </p>

        <form method="post" action="#">
            <section class="zimi-form">
                <header class="zimi-form-header">
                    <ul class="zimi-form-header-list" id="j-zimi-header">
                        <c:forEach begin="1" end="${totalRiddles}" var="ite">
                            <li>${ite}/${totalRiddles}题</li>
                        </c:forEach>
                    </ul>
                </header>
                <div class="zimi-content">
                    <div class="zimi-box" id="j-zimi-box">
                        <!--一个谜题-->
                        <c:forEach var="item" items="${lisRiddles}">
                            <div class="zimi-item">
                                <dl class="zimi-content-list">
                                    <dt>谜面：</dt>
                                    <dd>${item.lanternRon}</dd>
                                    <dt>谜目：</dt>
                                    <dd>${item.lanternReyes}</dd>
                                </dl>
                                <div class="zimi-content-box">
                                    <input type="text" class="ui-text" data-id="${item.id}" placeholder="请输入谜底"
                                           value="">
                                </div>

                            </div>
                        </c:forEach>
                    </div>
                    <div class="zimi-content-btn">
                        <a id="j-btn-prev" href="javascript:;" class="ui-btn ui-btn-3"
                           style="margin-right: 0.6rem;">上一题</a>
                        <a id="j-btn-next" href="javascript:;" class="ui-btn ui-btn-4">下一题</a>
                    </div>
                    <p class="main-msg main-text-red t-center">
                        答对一题得${everyFlow}M流量，共${totalRiddles}题，已答<span id="j-zimi-yida">0</span>题
                    </p>
                </div>
            </section>
            <div class="form-btn pbrem03">
                <a id="j-btn-submit" href="javascript:;" class="ui-btn ui-btn-4">提交</a>
            </div>
        </form>


    </section>

</article>


<script type="text/javascript" src="plug-in/liuliangbao/js/0218/zepto/zepto-all-min.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/js/0218/zepto/car-popup.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/js/0218/zimi/index.js"></script>


<input type="hidden" name="openId" id="openId" value="${memberEntity.openId}">
<input type="hidden" name="phoneNumber" id="phoneNumber" value="${memberEntity.phoneNumber}">
<input type="hidden" name="nickName" id="nickName" value="${memberEntity.nickName}">
<input type="hidden" name="hdid" id="hdid" value="${hdEntity.id}">
<input type="hidden" name="accountid" id="accountid" value="${hdEntity.accountid}">
<input type="hidden" name="everyFlow" id="everyFlow" value="${everyFlow}">
<input type="hidden" name="totalRiddles" id="totalRiddles" value="${totalRiddles}">
<input type="hidden" name="provinceAccount" id="provinceAccount" value="${provinceAccount}">
<input type="hidden" name="ishave" id="ishave" value="${ishave}">


</body>
</html>
