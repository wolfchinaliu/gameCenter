<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp"%>
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
        <title>摇一摇</title>
		<link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
	    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/liuliangbao/20160701/css/shake/index.css">

		<script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>
        <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
        <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/shake/js/howler.min.js"></script>
        <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/shake/js/fastclick.js"></script>
        <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
        <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
        <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/zepto/car-popup.js"></script>
        <script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lottery/lottery.js"></script>

        <script>
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
                        title: '摇一摇',
                        desc: '玩游戏领免费手机流量。我已领取，你也来参加吧。',
                        link: '${domain}/lotteryController.do?startLottery&hdid=${hdId}',
                        imgUrl: '${webRoot}/plug-in/liuliangbao/20160701/images/shareimg/shakehandshare.jpg',
                        trigger: function (res) {
                            // alert('用户点击发送给朋友');
                        },
                        success: function (res) {
                            // share("1");
                        },
                        fail: function (res) {
                            alert(JSON.stringify(res));
                        }
                    });

                    wx.onMenuShareTimeline({
                        title: '玩游戏领免费手机流量。我已领取，你也来参加吧。',
                        link: '${domain}/lotteryController.do?startLottery&hdid=${hdId}',
                        imgUrl: '${webRoot}/plug-in/liuliangbao/20160701/images/shareimg/shakehandshare.jpg',
                        success: function (res) {
                            // share("2");
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
        <script type="text/javascript">
        var SHAKE_THRESHOLD = 2000;
        var last_update = 0;
        var last_time = 0;
        var x;
        var y;
        var z;
        var last_x;
        var last_y;
        var last_z;
        var sound = new Howl({urls: ['${webRoot}/plug-in/liuliangbao/shake/sound/shake_sound.mp3']}).load();
        var findsound = new Howl({urls: ['${webRoot}/plug-in/liuliangbao/shake/sound/shake_match.mp3']}).load();
        var curTime;
        var isShakeble = true;

        function init() {
            if (window.DeviceMotionEvent) {
                window.addEventListener('devicemotion', deviceMotionHandler, false);
            } else {
                $("#cantshake").show();
            }
        }

        function deviceMotionHandler(eventData) {
            curTime = new Date().getTime();
            var diffTime = curTime - last_update;
            if (diffTime > 100) {
                var acceleration = eventData.accelerationIncludingGravity;
                last_update = curTime;
                x = acceleration.x;
                y = acceleration.y;
                z = acceleration.z;
                var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD && curTime - last_time > 1100 && isShakeble) {
                    shake();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

        var continueActivity = true;
        function startShakeResult() {
            if (continueActivity == false) {
                return;
            }
            continueActivity = false;

            var phoneNumber = $("#phoneNumber").val();
            var openid = $("#openId").val();
            var hdid = $("#hdid").val();
            var nickname = $("#nickname").val();

            $.ajax({
                type: 'POST',
                url: "lotteryController.do?shakeHand",
                dataType: "json",
                data: {
                    "phoneNumber": phoneNumber,
                    "hdid": hdid,
                    "openId": openid,
                    "nickname": nickname
                },
                cache: false,
                error: function () {
                    continueActivity = true;
                },
                success: function (result) {
					if (result.code != '1' && result.code != '7') {
						processResult(result.message,result.code,'${phoneNumber}' != '');
						//showDialog(result.message, null, null, true);
						continueActivity = true;
                    } else {
                    	var leftTime = result.attributes.count;
                    	processResult(result.message, result.code, '${phoneNumber}' != '',leftTime);
                        /* if (leftTime && leftTime > 0) {
                        	showDialog(result.message, null, "继续游戏");
                        } else {
                        	showDialog(result.message, null, "关闭",true);
                        } */
						continueActivity = true;
					}
                }
            });
        }

        function showDialog(message, okText, cancelText, goBack) {
            if ('${phoneNumber}' == '' && !okText) {
                okText = "验证手机";
                message +='<br><span style="color: red;  font-size: 12px;text-align:center;">(24小时内未验证手机，获得的流量清零)<span>';
            }

            var phoneNumber = $("#phoneNumber").val();
            var openid = $("#openId").val();
            var nickname = $("#nickname").val();

			dialog = Zepto('body').popup({
				title:'提示'
				,message: message
				,id:'pop-2'
				,ok: okText || "充值",
				cnacel: cancelText || "关闭",
				closeOnOk: false,
				onOk: function(){
					continueActivity = true;
					if ('${phoneNumber}' == '') {
						var url ='bindingController.do?redirectBinding';
						url += "&openId=" + openid;
						url += "&merchantId=${accountId}";
						url += "&nickName=" + nickname;
						url += "&phoneNumber=" + phoneNumber;
						url += "&operateType=摇一摇";
						window.location.href = url;
					} else {
						window.location.href = "userChargeController.do?userChargeView&accountid=${accountId}&openId=${openId}";
					}
				},
				onCancel: function () {
					if (goBack) {
						window.location.href = "mainController.do?load&accountid=${accountId}&openId=${openId}";
					} else {
						window.location.reload();
					}
				}
			});
		}

        function shake() {
            last_time = curTime;
            $("#loading").attr('class', 'loading loading-show');

            $("#shakeup").animate({top: 0}, 700, function () {
                $("#shakeup").animate({top: "8%"}, 700, function () {
                    $("#loading").attr('class', 'loading');
                    findsound.play();
                    startShakeResult();
                });
            });
            $("#shakedown").animate({top: "16%"}, 700, function () {
                $("#shakedown").animate({top: "8%"}, 700, function () {
                });
            });
            sound.play();
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

        //各种初始化
        $(document).ready(function () {
            Howler.iOSAutoEnable = false;
            init();

            if ("1" != "${code}") {
            	processResult("${message}","${code}",'${phoneNumber}' != '');
                //showDialog('${message}', null, null, true);
            }
            $('#yaoyiyao').on('click', function () {
            	shake();
            });
        });
    </script>
	</head>
	<body>
        <input type="hidden" name="openId" id="openId" value="${openId}">
        <input type="hidden" name="hdid" id="hdid" value="${hdid}">
        <input type="hidden" name="phoneNumber" id="phoneNumber" value="${phoneNumber}">
        <input type="hidden" name="nickname" id="nickname" value="${nickname}">

		<div class="container">
                <main class="shake">
				<div class="shake-main">
					<a href="javascript:;" class="btn-xq" id="j-shake-xq">活动详情</a>
					<div class="shake-box">
						<img src="${webRoot}/plug-in/liuliangbao/20160701/images/shake/shake.png" alt="" class="shake-up" id="shakeup"/>
						<img src="${webRoot}/plug-in/liuliangbao/20160701/images/shake/shake.png" alt="" class="shake-down" id="shakedown"/>
					</div>
				</div>
				<p class="zhuanpan-msg">
					您剩余 <span class="red">${leftcount }</span> 次机会
				</p>
              <!-- <a href="javascript:;" id="yaoyiyao" class="ui-btn">摇</a> -->
				<div class="zhuanpan-gd">
					<ul class="zhuanpan-list" id="j-zhuanpan-list">
                    <c:forEach items="${winningRecordList }" var="record">
                      <li>${record.nickname }获得${record.prizevalue }M<span><fmt:formatDate value="${record.addtime }" dateStyle="default" timeStyle="default"/></span></li>
                    </c:forEach>
					</ul>
				</div>
			</main>
			<div class="banner">
				<a href="${ad.adDetailUrl }"><img src="${ad.pic }" alt="" /></a>
			</div>
			<footer class="footer">
				<a href="javascript:;"><img src="${webRoot}/plug-in/liuliangbao/20160701/images/footer/footer.jpg" alt="" /></a>
			</footer>
		</div>
		<script type="text/javascript">
		$('#j-shake-xq').click(function() {
			var header = '<h1 class="dialog-header-title1"><img src="${webRoot}/plug-in/liuliangbao/20160701/images/dialog/title3.png" alt="" style="width: 4.05rem;" /></h1>\
				<h2 class="dialog-header-title2">活动详情</h2>';
			var main = '<h3><span>温馨提示</span></h3>\
						<p class="dialog-msg">${hdEntity.description}</p>';
			$.dialog({
				header: header,
				main: main
			});
		});
		</script>
	</body>

	<script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/util.js"></script>
	<script type="text/javascript" src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/scroll.js"></script>
</html>
