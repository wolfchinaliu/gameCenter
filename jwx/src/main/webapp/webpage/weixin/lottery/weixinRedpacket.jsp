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
    <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lottery/lottery.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/liuliangbao/20160701/css/packet/open.css">

    <script type="application/javascript">
        //判断公众号类型 确认是否分享
        if ('${accountType}' == '1') {
            wx.config({
                debug: false,
                appId: '${map.appId}',
                timestamp: '${map.timestamp}',
                nonceStr: '${map.nonceStr}',
                signature: '${map.signature}',
                jsApiList: [
                    'checkJsApi',
                    'onMenuShareTimeline',
                    'onMenuShareAppMessage',
                    'showOptionMenu'
                ]
            });

            wx.ready(function () {
                wx.onMenuShareAppMessage({
                    title: '拆红包',
                    desc: '玩游戏领免费手机流量。我已领取，你也来参加吧。',
                    link: '${domain}/lotteryController.do?startLottery&hdid=${hdId}',
                    imgUrl: '${webRoot}/plug-in/liuliangbao/20160701/images/shareimg/redpacketshare.jpg',
                    //imgUrl: "http://scimg.jb51.net/allimg/160716/105-160G61F250436.jpg",
                    trigger: function (res) {
                        // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
                        // alert('用户点击发送给朋友');
                    },
                    success: function (res) {
                        //share("1");
                    },
                    fail: function (res) {
                        alert(JSON.stringify(res));
                    }
                });

                wx.onMenuShareTimeline({
                    title: '玩游戏领免费手机流量。我已领取，你也来参加吧。',
                    link: '${domain}/lotteryController.do?startLottery&hdid=${hdId}',
                    imgUrl: '${webRoot}/plug-in/liuliangbao/20160701/images/shareimg/redpacketshare.jpg',
                    //  imgUrl: "http://scimg.jb51.net/allimg/160716/105-160G61F250436.jpg",
                    success: function (res) {
                        //share("2");
                    }
                });
                wx.showOptionMenu();
            });
            wx.error(function (res) {
                alert("error: " + res.errMsg);
            });
        } else {
            function onBridgeReady() {
                WeixinJSBridge.call('hideOptionMenu');
            }

            if (typeof WeixinJSBridge == "undefined") {
                if (document.addEventListener) {
                    document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                } else if (document.attachEvent) {
                    document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                    document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                }
            } else {
                onBridgeReady();
            }
        }
    </script>

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
    	if ("1" != "${code}") {
        	processResult("${message}","${code}",'${phoneNumber}' != '');
            //showDialog2('${message}', null, null);
    	}
        //isOpen();// 判断是否已经拆过红包了?
        $("#j-packet-chai").click(function () {
            openRedpacket();// 拆开红包
        });
    });

    function isOpen() {
        // 是否已经拆过红包了?
        var isOpened = '${ishave}';
        if (isOpened == "1") {
        	processResult("您本次拆红包的次数已用完,敬请关注下次活动!");
            //showDialog('您本次拆红包的次数已用完,敬请关注下次活动!', '关闭', null, true);
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
            	processResult("出错了, 请稍后再试");
                //showDialog('出错了, 请稍后再试', '关闭', null, true);
            },
            success: function (result) {
                if (result.code != '1' && result.code!='7') {
                	processResult(result.message,result.code,'${phoneNumber}' != '');
                    //showDialog(result.message, '关闭', null, true);
                    flag = false;
                    return;
                }
                processResult(result.message,result.code,'${phoneNumber}' != '');
                //showDialog(result.message, '关闭', null, true);
                flag = false;
            }
        });
    }

    function showDialog(message, cancelText, okText, goBack) {
        if (0 == '${phoneNumber}'.length) {
            okText = "验证手机";
            message +='<br><span style="color: red;  font-size: 12px;text-align:center;">(24小时内未验证手机，获得的流量清零)<span>';
        }
        var dialog = Zepto('body').popup({
            title: '提示',
            message: message,
            id: 'pop-2',
            ok: okText || "充值",
            cnacel: cancelText || "关闭",
            closeOnOk: false,
            onOk: function () {
                if ('${phoneNumber}' == '') {
                    bindPhoneNumber();
                    return;
                }
                window.location.href = "userChargeController.do?userChargeView&accountid=${accountid}&openId=${openId}";
            },
            onCancel: function () {
                if (goBack) {
                	window.location.href = "mainController.do?load&accountid=${accountid}&openId=${openId}";
                    return;
                }
                window.location.reload();
            }
        });
    }

    function bindPhoneNumber() {
        var url = 'bindingController.do?redirectBinding';
        url += "&openId=${openId}";
        url += "&merchantId=${accountid}";
        url += "&nickName=${nickName}";
        url += "&phoneNumber=${phoneNumber}";
        url += "&operateType=红包";
        window.location.href = url;
    }

    var goBind = {};
    goBind.text = "验证手机";
    goBind.fun = function(){
    	var url = 'bindingController.do?redirectBinding';
        url += "&openId=${openId}";
        url += "&merchantId=${accountid}";
        url += "&nickName=${nickName}";
        url += "&phoneNumber=${phoneNumber}";
        url += "&operateType=大转盘";
        window.location.href = url;
    }

    var goUserCharge = {};
    goUserCharge.text = "充值";
    goUserCharge.fun = function(){
    	window.location.href = "userChargeController.do?userChargeView&accountid=${accountid}&openId=${openId}";
    }

    var goAcctList = {};
    goAcctList.text = "赚取更多流量";
    goAcctList.fun = function(){
    	window.location.href = "acctListController.do?weixinacctList&accountid=${accountid}&openId=${openId}";
    }

    var goMain = {};
    goMain.text="关闭";
    goMain.fun = function(){
    	window.location.href = "mainController.do?load&accountid=${accountid}&openId=${openId}";
    }

    var goOn = {};
    goOn.text="继续活动";
    goOn.fun = function(){
    	window.location.reload();
    }

</script>

</body>
</html>
