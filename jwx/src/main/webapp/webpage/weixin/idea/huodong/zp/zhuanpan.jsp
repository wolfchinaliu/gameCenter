<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<title>幸运大转盘抽奖</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link href="plug-in/jquery-plugs/jueryRotate/activity-style.css"
	rel="stylesheet" type="text/css">
<script src="plug-in/weixin/zp/jquery.js" type="text/javascript"></script>
<script type="text/javascript"
	src="plug-in/jquery-plugs/jueryRotate/jQueryRotate.2.2.js"></script>
<script type="text/javascript"
	src="plug-in/jquery-plugs/jueryRotate/jquery.easing.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#inner").click(function() {
			$("#outer").rotate();
		});
		$("#save-btn").bind("click",
				function() {
					var btn = $(this);
					var name = $("#name").val();
					if (name == '') {
						$("#name").focus();
						alert("请输入收货人");
						return

					}
					var address = $("#address").val();
					if (address == '') {
						$("#address").focus();
						alert("请输入配送地址");
						return

					}
					var tel = $("#tel").val();
					if (tel == '') {
						$("#tel").focus();
						alert("请输入手机号码");
						return

					}
					var regu = /^[1][0-9]{10}$/;
					var re = new RegExp(regu);
					if (!re.test(tel)) {
						$("#tel").focus();
						alert("请输入正确手机号码");
						return

					}
					var submitData = {
						mobile : tel,
						name : name,
						address : address
					};

					$.post('lotteryController.do?saveZpPrize', submitData,
							function(data) {
								if (data.success == true) {
									alert("提交成功，谢谢您的参与");
									WeixinJSBridge.call('closeWindow');
									return

								} else {
									alert("失败");
								}
							}, "json")
		});
		var timeOut = function() { //超时函数
			$("#outer").rotate({
				angle : 0,
				duration : 10000,
				animateTo : 2160, //这里是设置请求超时后返回的角度，所以应该还是回到最原始的位置，2160是因为我要让它转6圈，就是360*6得来的
				callback : function() {
					alert('网络超时')
				}
			});
		};
		var rotateFunc = function(awards, angle, text) { //awards:奖项，angle:奖项对应的角度，text:提示文字
			$('#outer').stopRotate();
			$("#outer").rotate({
				angle : 0,
				duration : 5000,
				animateTo : angle + 1440, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
				callback : function() {
					alert(text)
				}
			});
		};
		$("#inner").click(function() {
			lottery();
		});
	});
	var start = function(angle, result,win) {
		$("#outer").rotate({ //inner内部指针转动，outer外部转盘转动
			duration : 5000, //转动时间 
			angle : 0, //开始角度 
			animateTo : 3600 + angle, //转动角度 
			easing : $.easing.easeInOutExpo, //动画扩展 
			callback : function() {
				if(win){
					$.confirm(result.attributes.msg + ',还要再来一次?', function(){
						lottery();
					});
				}else{
					$("#prizetype").html(result.attributes.msg);
					$("#result").slideToggle(500);
					$("#outercont").slideUp(500);
				}
			}
		});
	}
	function lottery() {
		$.ajax({
			type : 'POST',
			url : "lotteryController.do?drawreLottery",
			dataType : "json",
			data : {
				"hdId" : '${hdId}',
				"openId" : '${openId}'
			},
			cache : false,
			error : function() {
				alert('出错了！');
				return false;
			},
			success : function(result) {
				 $("#inner").unbind('click').css("cursor", "default");
				var win = result.attributes.continueFlag; //是否中奖
				var angle = parseInt(result.attributes.angle); //角度 
				if (result.attributes.error == "invalid") {
					alert(result.attributes.msg);
					return
				}
				if (result.attributes.error == "getsn") {
					alert('本次活动你已经中过奖，不能再参加活动！,本次显示上次中奖结果');
					start(angle,result,win);
					$("#outer").rotate({ //inner内部指针转动，outer外部转盘转动
						duration : 5000, //转动时间 
						angle : 0, //开始角度 
						animateTo : 3600 + angle, //转动角度 
						easing : $.easing.easeInOutExpo, //动画扩展 
						callback : function() {
						}
					});
					return
				} 
				start(angle,result,win);
			}
		});
	}
</script>
</head>

<body class="activity-lottery-winning">

	<div class="main">
		<script type="text/javascript">
			var loadingObj = new loading(document.getElementById('loading'), {
				radius : 20,
				circleLineWidth : 8
			});
			loadingObj.show();
		</script>

		<div id="outercont">
			<div id="outer-cont" style="overflow: hidden;">
				<div id="outer" style="-webkit-transform: rotate(2136deg);">
					<img src="plug-in/jquery-plugs/jueryRotate/activity-lottery-1.png"
						width="310px">
				</div>
			</div>
			<div id="inner-cont">
				<div id="inner">
					<img src="plug-in/jquery-plugs/jueryRotate/activity-lottery-2.png">
				</div>
			</div>
		</div>
		<div class="content">
			<div class="boxcontent boxyellow" id="result" style="display: none">
				<div class="box">
					<div class="title-orange">
						<span>恭喜${nickname }你中奖了</span>
					</div>
					<div class="Detail">
						<a class="ui-link" href="#" id="opendialog" style="display: none;"
							data-rel="dialog"></a>
						<p>
							你中了：<span class="red" id="prizetype"></span>
						</p>
						<p class="red">本次兑奖已经关联你的微信号，你可向公众号发送 兑奖 进行查询!</p>

						<p>
							<input name="" class="px" id="name" type="text"
								placeholder="输入收货人姓名"> <input name="" class="px"
								id="address" type="text" placeholder="输入配送地址"> <input
								name="" class="px" id="tel" type="text" placeholder="输入您的手机号码">
						</p>
						<p>
							<input class="pxbtn" id="save-btn" name="提 交" type="button"
								value="提 交">
						</p>
					</div>
				</div>
			</div>
			<div class="boxcontent boxyellow">
				<div class="box">
					<div class="title-green">
						<span>奖项设置：</span>
					</div>
					<div class="Detail">
						<p>一等奖：${hdEntity.firstprize}
							。奖品数量：${hdEntity.firstprizetotal}</p>
						<p>二等奖：${hdEntity.secondprize} 。奖品数量：${hdEntity.secondprizetotal}</p>
						<p>三等奖：${hdEntity.thirdprize}
							。奖品数量：${hdEntity.thirdprizetotal}</p>
					</div>
				</div>
			</div>
			<div class="boxcontent boxyellow">
				<div class="box">
					<div class="title-green">活动说明：</div>
					<div class="Detail">
						<p>${hdEntity.description}</p>
					</div>
				</div>
			</div>
		</div>
		<div id="loading"></div>
	</div>
<input type="hidden" name="hidden" value="${param.type }" id="BridgeType">
</body>
</html>
