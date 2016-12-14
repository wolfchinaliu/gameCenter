<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>拆红包</title>
    <script src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>
    <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
    <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/zepto/car-popup.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/liuliangbao/20160701/css/packet/open.css">
   </head>
<body>

<div class="container">

    <main class="zhuanpan">
        <div class="packet-box">
            <a href="javascript:;" class="btn-xq" id="j-zhuanpan-xq">活动详情</a>
            <div class="packet-chai" id="j-packet-chai"></div>
        </div>
        <p class="zhuanpan-msg">
            您剩余 <span class="red" id="prizetime">${leftcount}</span> 次机会
        </p>
        <div class="zhuanpan-gd">
            <ul class="zhuanpan-list" id="j-zhuanpan-list">
                <c:forEach var="item" items="${winningRecordList}" varStatus="status">
                    <li>${item.nickname}获得（${item.prizevalue}M）流量<span><fmt:formatDate value="${item.addtime }" dateStyle="default" timeStyle="default"/></span></li>
                </c:forEach>
            </ul>
        </div>
    </main>

    <div class="banner">
        <a href="javascript:;"><img src="${webRoot}/plug-in/liuliangbao/20160701/images/banner/02.jpg" alt="" /></a>
    </div>

    <footer class="footer">
        <a href="javascript:;"><img src="${webRoot}/plug-in/liuliangbao/20160701/images/footer/footer.jpg" alt="" /></a>
    </footer>

</div>

<!--弹框html示例-->
<article class="dialog">
    <a href="javascript:;" class="dialog-close">close</a>
    <header class="dialog-header">
        <h1 class="dialog-header-title1"><img src="${webRoot}/plug-in/liuliangbao/20160701/images/dialog/title4.png" alt="" /></h1>
        <h2 class="dialog-header-title2">活动详情</h2>
    </header>
    <section class="dialog-main">
        <h3><span>温馨提示</span></h3>
        <p class="dialog-msg">${hdEntity.description}</p>
    </section>
</article>

<script src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
<script src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/util.js"></script>
<script src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/scroll.js"></script>
<script type="application/javascript">
    // 显示活动详情对话框
    $('#j-zhuanpan-xq').click(function() {
        $.dialog({
            header: $('.dialog-header').html(),
            main: $('.dialog-main').html()
        });
    });
</script>
<script>
    var flag = true;
    $(function () {
        isOpen();// 判断是否已经拆过红包了?
        $("#j-packet-chai").click(function () {
            openRedpacket();// 拆开红包
        });
    });

    function isOpen() {
        // 是否已经拆过红包了?
        var isOpened = '${ishave}';
        if (isOpened == "1") {
            showDialog('您抢过红包了，不能再抢了！', '关闭', null, false);
        }
    }
    function openRedpacket() {
        if (flag == false) {
            return;
        }
        flag = false;
        $.ajax({
            type: 'POST',
            url: "lotteryController.do?openRedpacket",
            dataType: "json",
            data: {
                "hdid": '${hdId}',
                "openId": '${openId}',
                "accountId": '${accountid}',
                "nickName": '${nickName}',
                "phoneNumber": '${phoneNumber}'
            },
            cache: false,
            error: function () {
                showDialog('出错了, 请稍后再试', '关闭', null, true);
            },
            success: function (result) {
                if (result.code != '1') {
                    showDialog(result.message, '关闭', null, true);
                    flag = false;
                    return;
                }
                showDialog('恭喜获得' + result.attributes.flowValue + 'M流量', '关闭', null, true);
                flag = false;

            }
        });
    }

    function showDialog(message, cancelText, okText, goBack) {
        if (!okText && 0 == '${phoneNumber}'.length) {
            okText = "去绑定";
        }
        var dialog = Zepto('body').popup({
            title: '提示',
            message: message,
            id: 'pop-2',
            ok: okText || "去充值",
            cnacel: cancelText || "关闭",
            closeOnOk: false,
            onOk: function () {
                window.location.href = "integrate.do?goAppUserCharge&acctId=${acctId}&data=${endata}";
            },
            onCancel: function () {
                 window.location.reload();
            }
        });
    }

</script>

</body>
</html>
