<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<title>刮刮卡</title>
<script type="text/javascript"	src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>
<script type="text/javascript"	src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
<script type="text/javascript"	src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/jquery.slides.min.js"></script>
<script type="text/javascript"	src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/jquery.eraser.js"></script>
<script type="text/javascript"	src="${webRoot}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
<script type="text/javascript"	src="${webRoot}/plug-in/liuliangbao/20160701/js/zepto/car-popup.js"></script>
<script type="text/javascript"  src="${webRoot}/plug-in/liuliangbao/20160701/js/lottery/lottery.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<link rel="stylesheet" type="text/css"	href="${webRoot}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
<link rel="stylesheet" type="text/css"	href="${webRoot}/plug-in/liuliangbao/20160701/css/card/index.css?t=1111">
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
				title: '刮刮卡',
				desc: '玩游戏领免费手机流量。我已领取，你也来参加吧。',
				link: '${domain}/lotteryController.do?startLottery&hdid=${hdId}',
				imgUrl: '${webRoot}/plug-in/liuliangbao/20160701/images/shareimg/gglshare.jpg',
				trigger: function (res) {
					// 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
					// alert('用户点击发送给朋友');
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
				imgUrl: '${webRoot}/plug-in/liuliangbao/20160701/images/shareimg/gglshare.jpg',
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
	<!-- background: url(../../images/card/btn.png) no-repeat left top; -->
	<div class="container">
		<header class="header-card">
			<a href="javascript:;" class="btn-xq" id="j-card-xq">活动详情</a>
			<!-- <div class="card-prize">一等奖</div> -->
			<div class="card-scratchpad">
				<img id="ggl-result-over" alt=""
					style="width: 100%; height: 100%; position: absolute; top: 0; left: 0; z-index: 10;"
					src="${webRoot}/plug-in/liuliangbao/20160701/images/card/btn.png">
				<img id="ggl-result" alt=""
					style="width: 100%; height: 100%; position: absolute; top: 0; left: 0; z-index: 1;">
			</div>
		</header>
		<main class="card">
		<p class="zhuanpan-msg" style="text-align: center;">
			您剩余 <span class="red" id="prizetime">${leftcount}</span> 次机会
		</p>
		<div class="zhuanpan-gd">
			<ul class="zhuanpan-list" id="j-card-list">
				<c:forEach var="item" items="${winningRecordList}"
					varStatus="status">
					<li>${item.nickname}获得${item.prizelevel}等奖（${item.prizevalue}M）<span><fmt:formatDate
								value="${item.addtime }" pattern="yyyy-MM-dd" /></span></li>
				</c:forEach>
			</ul>
		</div>
		</main>

		<div class="banner">
			<a href="${ad.adDetailUrl }"><img src="${ad.pic}" alt="" /></a>
		</div>
		<footer class="footer">
			<a href="javascript:;"><img
				src="${webRoot}/plug-in/liuliangbao/20160701/images/footer/footer.jpg"
				alt="" /></a>
		</footer>
	</div>

	<script type="text/javascript">
		$('#j-card-xq')
				.click(
						function() {
							var header = '<h1 class="dialog-header-title1"><img src="${webRoot}/plug-in/liuliangbao/20160701/images/dialog/title2.png" alt="" /></h1>\
				<h2 class="dialog-header-title2">活动详情</h2>';
							var main = '<h3><span>奖项设置</span></h3>\
						<ul class="dialog-list clx">\
							<li style="width: 100%;">\
								<span class="left">一等奖</span>\
								<span class="right">${hdEntity.firstprize}M</span>\
							</li>\
							<li style="width: 100%;">\
								<span class="left">二等奖</span>\
								<span class="right">${hdEntity.secondprize}M</span>\
							</li>\
							<li style="width: 100%;">\
								<span class="left">三等奖</span>\
								<span class="right">${hdEntity.thirdprize}M</span>\
							</li>\
						</ul>\
						<h3><span>温馨提示</span></h3>\
						<p class="dialog-msg">${hdEntity.description}</p>';
							$.dialog({
								header : header,
								main : main
							});
						});
	</script>
	<script type="text/javascript">
		var bodyStyle = document.body.style;
		bodyStyle.mozUserSelect = 'none';
		bodyStyle.webkitUserSelect = 'none';
		$(document)
				.ready(
						function() {
							$('#ggl-result')
									.attr("src",
											'${webRoot}/plug-in/liuliangbao/css/0112/images/p_01.jpg');//图片默认不中奖

							var prizeLevel = "";
							var message = "";
							var flag = true;
							var win = true;
							var prizevalue = 0;
							var flag1 = true;
							var prizetime= "";

							var pics = [
									'${webRoot}/plug-in/liuliangbao/css/1220/images/p_nothing.jpg',
									'${webRoot}/plug-in/liuliangbao/css/0112/images/p_one.jpg',
									'${webRoot}/plug-in/liuliangbao/css/0112/images/p_two.jpg',
									'${webRoot}/plug-in/liuliangbao/css/0112/images/p_three.jpg' ];

							$.ajax({
								url : "lotteryController.do?scratchCard",
								method : "POST",
								data : {
									"hdId" : '${hdId}',
									"openId" : '${openId}'
								},
								dataType : "JSON",
								async : false,
								success : function(result) {
									prizetime = result.attributes.count;
									prizeLevel = result.attributes.prizeLevel;
									if (result.code != '1' && result.code != '11' && result.code != '7') {
										$('#ggl-result').attr("src", pics[0]);
										processResult(result.message,result.code,'${phoneNumber}' != '',prizetime);
										//showDialog(result.message, null, null,true);
										flag = false;
										return;
									}

									$('#prizetime').text(result.attributes.count); //修改页面剩余次数
									message = result.message;
									win = prizeLevel ? true : false; //是否中奖
									/* prizevalue = result.attributes.prizevalue; */

									$('#ggl-result').attr("src",
											pics[prizeLevel]);
								},
								error : function() {
									return;
								}
							});

							$('.tabs li').on('click', function() {
								var idx = $(this).index();
								$('.tabs li').removeClass('current');
								$(this).addClass('current');
								$('.tabs-content').hide();
								$('.tabs-content').eq(idx).show();
							});

							$('#ggl-result-over').eraser({
												size : 20,
												//			completeRatio: 0.3,
												progressFunction : function(p) {
													if (p < 0.2)
														return;
													//				$('#ggl-result').show();
													if (flag == false) {
														return;
													}
													if (flag1 == true) {
														if (flag1 == false) {
															return;
														}
														flag1 = false;
														$.ajax({
																	url : "lotteryController.do?saveScratchRecord",
																	method : "POST",
																	data : {
																		"hdId" : '${hdId}',
																		"openId" : '${openId}'
																	},
																	dataType : "JSON",
																	async : true,
																	success : function(result) {
																		prizevalue = result.attributes.prizevalue;
																		var leftTime = result.attributes.count;
																		/* showDialog(result.code+":"+result.message); */
																		if (result.code != '1' && result.code != '11'&& result.code != '7') {
																			processResult(result.message,result.code,'${phoneNumber}' != '',leftTime);
																			//showDialog(result.message);
																			flag = false;
																			return;
																		}
																		if (result.code == '7') {
																			processResult(result.message,result.code,'${phoneNumber}' != '',leftTime);
																			//showDialog(result.message,"继续游戏",null,false);
																			flag = false;
																			return;
																		}

																		// $('#prizetime').text(parseInt($('#prizetime').text()) - 1); //修改页面剩余次数
																		if (win) {
																			processResult(result.message,result.code,'${phoneNumber}' != '',leftTime);	
																			//showDialog(result.message,'继续游戏','充值');
																		} else {
																			processResult(result.message,result.code,'${phoneNumber}' != '',leftTime);
																				//showDialog(result.message,'关闭','充值');
																			flag = false;
																			return;
																		}
																		$('#ggl-result-over').eraser(
																						'clear'); //清除图层
																	},
																	error : function(xhr) {
																		processResult("出错了,请重试!");
																		//showDialog("出错了,请重试!");
																	}
																});
													}
												}
											});

							$('#ggl-result-over').css({
								position : 'absolute',
								top : 0,
								left : 0,
								'z-index' : 10
							});
						});

		function showDialog(message, cancelText, okText, goBack) {
			if (0 == '${phoneNumber}'.length) {
				okText = "验证手机";
				message +='<br><span style="color: red;  font-size: 12px;text-align:center;">(24小时内未验证手机，获得的流量清零)<span>';
			}
			var dialog = Zepto('body')
					.popup(
							{
								title : '提示',
								message : message,
								id : 'pop-2',
								ok : okText || "充值",
								cnacel : cancelText || "关闭",
								closeOnOk : false,
								onOk : function() {
									if ('${phoneNumber}' == '') {
										bindPhoneNumber();
										return;
									}
									window.location.href = "userChargeController.do?userChargeView&accountid=${accountid}&openId=${openId}";
								},
								onCancel : function() {
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
			url += "&operateType=刮刮卡";
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
	<script type="text/javascript"
		src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/util.js"></script>
	<script type="text/javascript"
		src="${webRoot}/plug-in/liuliangbao/20160701/js/lib/scroll.js"></script>
</body>
</html>