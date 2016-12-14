<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <title>幸运大转盘抽奖</title>
    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/liuliangbao/20160701/css/zhuanpan/index.css">
    <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>

    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
    <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
    <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/zepto/car-popup.js"></script>
    <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lottery/lottery.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/js/0112/jQueryRotate.js"></script>

    <script type="text/javascript">
        var flag = true;
        $(function () {
            if ("1" != "${code}") {
            	processResult("${message}","${code}",'${phoneNumber}' != '');
                //showDialog2('${message}', null, null);
            } else {
                $("#jq-turntable-tip").click(function () {
                    lottery();  //绑定转盘事件
                });
            }
        });

        //转盘旋转事件
        var start = function (angle, msg, win, leftTime,code) {
            $("#jq-turntable-img").rotate({ //inner内部指针转动，outer外部转盘转动
                duration: 5000, //转动时间
                angle: 0, //开始角度
                animateTo: 3600 + angle, //转动角度
                callback: function () {
                    $('#prizetime').text(leftTime); //修改页面剩余次数
                    if (!win) {
                    	processResult(msg, code,'${phoneNumber}' != '', leftTime);
//                        showDialog(msg || "不要灰心，请再来一次！", null, "继续游戏");
                        // 如果没有剩余次数,会立即提示,不会等到这里
                        flag = true;
                    } else {
                        flag = true;
                        var cancelText = '关闭';
                        if (leftTime && leftTime > 0) {
                            cancelText = '继续游戏';
                        }
                        var okText = '充值';
                        /* if('${phoneNumber}' == ''){
                        	okText ='验证手机';
                        	msg +='<br><span style="color: red;  font-size: 12px;text-align:center;">(24小时内未验证手机，获得的流量清零)<span>';
                        } */
                        processResult(msg, code,'${phoneNumber}' != '', leftTime);
                        /* Zepto('body').popup({
                            title: '提示',
                            message: msg,
                            id: 'pop-2',
                            ok: okText || "充值",
                            cnacel: cancelText || "关闭",
                            closeOnOk: false,
                            onCancel: function () {
                                if (leftTime && leftTime > 0) {
                                    // refresh the draw list
                                    window.location.reload();
                                }else{
                                	window.location.href = "mainController.do?load&accountid=${accountid}&openId=${openId}";
                                }
                            },
                            onOk: function () {
                                if ('${phoneNumber}' == '') {
                                    bindPhoneNumber();
                                } else {
                                    window.location.href = "userChargeController.do?userChargeView&accountid=${accountid}&openId=${openId}";
                                }
                            }
                        }); */
                    }
                }
            });
        };

        //点击转盘执行后台绑定事件
        function lottery() {
            if (false == flag) {
                return;
            }
            flag = false;
            $.ajax({
                type: 'POST',
                url: "lotteryController.do?luckyTurntable",
                dataType: "json",
                data: {
                    "hdId": '${hdId}',
                    "openId": '${openId}',
                    "accountId": '${accountid}',
                    "nickName": '${nickName}',
                    "phoneNumber": '${phoneNumber}'
                },
                cache: false,
                error: function () {
                	processResult("出错了");
                    return false;
                },
                success: function (result) {
                    if (result.code != '1' && result.code != '7') {
                    	processResult(result.message,result.code,'${phoneNumber}' != '');
                    	//                        showDialog(result.message);
                        return;
                    }
                    var win = false;
                    if (result.attributes.prizeLevel) {
                        win = true;
                    }
                    var prizetime = result.attributes.count;  //剩余次数
                    var angle = getAngle(result.attributes.prizeLevel);
                    //调用转盘旋转函数
                    start(angle, result.message, win, prizetime,result.code);
                }
            });
        }

        function getAngle(prizeLevel) {
            if (prizeLevel > 0) {
                var prizeArr = [345, 290, 230, 160, 110, 65];
                return prizeArr[prizeLevel - 1];
            } else {
                var noPrizeArr = [322, 262, 195, 127, 37, 15];
                return noPrizeArr[parseInt(Math.random() * noPrizeArr.length)];
            }
        }

 /*        function showDialog(message, okText, cancelText, goBack) {
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
                onCancel: function () {
                    if (goBack) {
                    	window.location.href = "mainController.do?load&accountid=${accountid}&openId=${openId}";
                        //window.history.back();
                        return;
                    }
                    flag = true;
                    window.location.reload();
                },
                onOk: function () {
                    flag = true;
                    if ('${phoneNumber}' == '') {
                        bindPhoneNumber();
                        return;
                    }
                    window.location.href = "userChargeController.do?userChargeView&accountid=${accountid}&openId=${openId}";
                }
            });
        }
 */
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

        function bindPhoneNumber() {
            var url = 'bindingController.do?redirectBinding';
            url += "&openId=${openId}";
            url += "&merchantId=${accountid}";
            url += "&nickName=${nickName}";
            url += "&phoneNumber=${phoneNumber}";
            url += "&operateType=大转盘";
            window.location.href = url;
        }
    </script>

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
                    title: '幸运大转盘',
                    desc: '玩游戏领免费手机流量。我已领取，你也来参加吧。',
                    link: '${domain}/lotteryController.do?startLottery&hdid=${hdId}',
                    imgUrl: '${webRoot}/plug-in/liuliangbao/20160701/images/shareimg/zhuanpanshare.jpg',
                    trigger: function (res) {
                    },
                    success: function (res) {
                    },
                    fail: function (res) {
                        alert(JSON.stringify(res));
                    }
                });

                wx.onMenuShareTimeline({
                    title: '玩游戏领免费手机流量。我已领取，你也来参加吧。',
                    link: '${domain}/lotteryController.do?startLottery&hdid=${hdId}',
                    imgUrl: '${webRoot}/plug-in/liuliangbao/20160701/images/shareimg/zhuanpanshare.jpg',
                    success: function (res) {
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
        <div class="circle">
            <a href="javascript:;" class="btn-xq" id="j-zhuanpan-xq">活动详情</a>
            <div class="circle-box">
                <div class="circle-outer">
                    <div class="circle-outer-img"><img id="jq-turntable-img" src="${webRoot}/plug-in/liuliangbao/20160701/images/zhuanpan/rote.png" alt="" /></div>
                </div>
                <div class="circle-inner">
                    <div class="circle-inner-btn"><img id="jq-turntable-tip" src="${webRoot}/plug-in/liuliangbao/20160701/images/zhuanpan/btn.png" alt="" /></div>
                </div>
            </div>
        </div>
        <p class="zhuanpan-msg">
            您剩余 <span class="red" id="prizetime">${leftcount}</span> 次机会
            <%--<span class="zhuanpan-msg-tips"><img src="${webRoot}/plug-in/liuliangbao/20160701/images/zhuanpan/icon1.png" alt="" />已中<em class="red">${winCount}</em>次</span>--%>
        </p>
        <div class="zhuanpan-gd">
            <ul class="zhuanpan-list" id="j-zhuanpan-list">
                <c:forEach items="${winningRecordList }" var="record">
                    <li>${record.nickname }获得${record.prizelevel}等奖（${record.prizevalue }M）<span><fmt:formatDate value="${record.addtime }" pattern="yyyy-MM-dd"/></span></li>
                </c:forEach>
            </ul>
        </div>
    </main>

    <div class="banner">
        <a href="${ad.adDetailUrl }"><img src="${ad.pic}" alt="" /></a>
    </div>

    <footer class="footer">
        <a href="javascript:;"><img src="${webRoot}/plug-in/liuliangbao/20160701/images/footer/footer.jpg" alt="" /></a>
    </footer>
</div>

<article class="dialog">
    <a href="javascript:;" class="dialog-close">close</a>
    <header class="dialog-header">
        <h1 class="dialog-header-title1"><img src="${webRoot}/plug-in/liuliangbao/20160701/images/dialog/title1.png" alt="" /></h1>
        <h2 class="dialog-header-title2">活动详情</h2>
    </header>
    <section class="dialog-main">
        <h3><span>奖项设置</span></h3>
        <ul class="dialog-list clx">
            <li>
                <span class="left">一等奖</span>
                <span class="right">${hdEntity.firstprize}</span>
            </li>
            <li>
                <span class="left">二等奖</span>
                <span class="right">${hdEntity.secondprize}</span>
            </li>
            <li>
                <span class="left">三等奖</span>
                <span class="right">${hdEntity.thirdprize}</span>
            </li>
            <li>
                <span class="left">四等奖</span>
                <span class="right">${hdEntity.forthprize}</span>
            </li>
            <li>
                <span class="left">五等奖</span>
                <span class="right">${hdEntity.fifthprize}</span>
            </li>
            <li>
                <span class="left">六等奖</span>
                <span class="right">${hdEntity.sixthprize}</span>
            </li>
        </ul>
        <h3><span>温馨提示</span></h3>
        <p class="dialog-msg">${hdEntity.description}</p>
    </section>
</article>

<script type="text/javascript">
    $('#j-zhuanpan-xq').click(function () {
        $.dialog({
            header: $('.dialog-header').html(),
            main: $('.dialog-main').html()
        });
    });
</script>
<script type="text/javascript">
    var defaultHeadPhoto = '${webRoot}/plug-in/liuliangbao/20160701/images/1.jpg';
    $(function () {
        if ($('#headImgUrl').attr('src').length <= 0) {
            $('#headImgUrl').attr('src', defaultHeadPhoto);
        }
        if ($('#phoneNumber').text().length <= 0) {
            $('#phoneNumber').text('尚未绑定');
        }
    });

</script>


<script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/util.js"></script>
<script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/scroll.js"></script>

</body>
</html>
