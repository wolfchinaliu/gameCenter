<%@ taglib prefix="t" uri="/easyui-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <meta charset="utf-8"/>
        <title>${activity.title }</title>
        <meta name="apple-mobile-web-app-capable" content="yes"/>
        <meta name="viewport" id="viewport" content="target-densitydpi=device-dpi,width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    	<meta name="full-screen" content="yes"/>
    	<meta name="screen-orientation" content="portrait"/>
    	<meta name="x5-fullscreen" content="true"/>
    	<meta name="360-fullscreen" content="true"/>
        <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0218/zimi/normalize.css">
    	<script src="plug-in/liuliangbao/js/0218/lib/common.js"></script>
    	<script type="text/javascript" src="plug-in/liuliangbao/js/0218/zepto/zepto-all-min.js"></script>
		<script type="text/javascript" src="plug-in/liuliangbao/js/0218/zepto/car-popup.js"></script>
		<style type="text/css">
    	@font-face {
 	 	font-family: 'MyNewFont'; 
  		src: url('${cdnHost}/plug-in/activity/font/yegen.TTF'); 
		}
   		 </style>
        <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/activity/js/liujiaoping/jQuery.js"></script>
    <script type="text/javascript">
    
    var code = ${code};
    var hostAddress = '${cdnHost}/';
    //var hostAddress = '';
    var userImagePath = '${activity.imagePath}' == '' ? '' : '${activity.imagePath}/';
    var rule =  ${activity.activityRule};
    var errorMsg = '${msg}';
    var anniuText = '去充值';
    var hasPhone = true;
    var flag = true;
    var is_worng = false;
    var adImage = '${ad.pic}';
    var adDetailUrl = '${ad.adDetailUrl}';
    var openId = '${member.openId}';
    var rediceUrl = "userChargeController.do?userChargeView&accountid=${activity.accountid}&openId=${member.openId}";
    //图片目录 
    //分享图片  (团片为 shareLogo.jpg) 暂时用了上传至微信了 所以引用的微信的地址 （生产上可上传主号上）
    var shareImage = "https://mmbiz.qlogo.cn/mmbiz_png/f9LRzHGicKglIhSJp0licibSnZWd2kibuhnXfKGGIMkg1zgwwCAATtf1ouHqLicJw6aiaWp3THp6RwUMe174IuYqOzicw/0?wx_fmt=png";
    var rankNames = ['第一名','第二名','第三名','第四名','第五名','第六名','第七名','第八名','第九名','第十名'];
    if ('${member.phoneNumber}' == '') {
    	anniuText = '验证手机';
    	hasPhone = false;
    }
    var eatImgIndex = "_1";
    var msg = '${msg}';
    var accountId = "${activity.accountid}";
    var hdid = "${activity.id}";
    var activityTitle = "${activity.title}";
    if('${accountType}' == '1'){
        //关于分享链接的一些配置
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: '${map.appId}', // 必填，公众号的唯一标识
            timestamp:'${map.timestamp}', // 必填，生成签名的时间戳
            nonceStr: '${map.nonceStr}', // 必填，生成签名的随机串
            signature: '${map.signature}',// 必填，签名，见附录1
            jsApiList: [
                'onMenuShareTimeline',
                'onMenuShareAppMessage'
            ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
        	wx.showOptionMenu();
            // 发送给好友
            WeixinJSBridge.on('menu:share:appmessage', function(argv){
                shareFriend();
            });
            // 分享到朋友圈
            WeixinJSBridge.on('menu:share:timeline', function(argv){
                shareTimeline();
            });
        }, false);
    }else {
     	function onBridgeReady() {
 			WeixinJSBridge.call('hideOptionMenu');
 		}
 	
 		if (typeof WeixinJSBridge == "undefined") {
 			if (document.addEventListener) {
 				document.addEventListener('WeixinJSBridgeReady',
 						onBridgeReady, false);
 			} else if (document.attachEvent) {
 				document.attachEvent('WeixinJSBridgeReady',
 						onBridgeReady);
 				document.attachEvent('onWeixinJSBridgeReady',
 						onBridgeReady);
 			}
 		} else {
 			onBridgeReady();
 		}
     }
        var title="${activity.title} ，来与我一较高下吧！";
      
        var link = '${link}';
        var desc = ""
        function shareTimeline(){
        	WeixinJSBridge.invoke('shareTimeline',{
                "img_url": shareImage,
                "img_width": "300",
                "img_height": "300",
                "link": link,
                "desc": desc,
                "title": title
            });
        }
        function shareFriend(){
        	 WeixinJSBridge.invoke('sendAppMessage',{
        	        "appid": '${ret.appId}',
        	        "img_url": shareImage,
        	        "img_width": "300",
        	        "img_height": "300",
        	        "link": link,
        	        "desc": desc,
        	        "title":"${activity.title} ",
        	    });
        }
        function bindPhoneNumber() {
            var url ='bindingController.do?redirectBinding';
            url += "&openId=${member.openId}";
            url += "&merchantId=${activity.accountid}";
            url += "&nickName=${member.nickName}" ;
            url += "&phoneNumber=${member.phoneNumber}";
            url += "&operateType=" + encodeURIComponent('六角拼拼');
            window.location.href = url;
        }
    </script>
<style>
		*{margin:0;padding:0;}
        body, canvas, div {
		-moz-user-select: none;
		-webkit-user-select: none;
		-ms-user-select: none;
		-khtml-user-select: none;
		-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
        }
		ul,li{list-style-type:none;}
		selector,document,body {cursor:pointer;}
		table{font-size:11px;border-collapse:collapse;color:#555555;margin-top:4px;display:inline-table;}
		table td{height:20px;}
		h5{margin-top:10px;font-size:15px;font-family:Microsoft YaHei;}
		#game_btn{position:absolute;z-index:500;width:100%;margin:0 auto;top:2%;}
		#game_btn li{color:white;cursor:pointer;width:112px;height:30px;line-height:30px;font-size:13px;}
		#game_btn li:nth-child(1){display:inline-table;}
		#game_btn li:nth-child(2){display:inline-table;}
		
	    .main{position:absolute;z-index:999;box-sizing:border-box;width:250px;background:white;display:none;overflow:hidden;border:rgba(0,0,0,0.55) 5px solid;border-radius:4px;padding:3px 1px 10px 5px;}
	    .mask{background-color:rgba(0,0,0,0.5);position:absolute;z-index:888;top:0;left:0;}
		.close{margin:0 auto;width:20px;height:20px;line-height:20px;border-radius:10px;background:red;color:white;font-family:Microsoft YaHei;font-size:11px;position:absolute;top:8px;right:6px;}
		.sort td:nth-child(2){box-sizing:border-box;padding-right:12px;}
		.sort td:nth-child(2) p{white-space:nowrap;}
		.nikname{text-align:left;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;width:58px;}
		.sort td:nth-child(3){text-align:left;white-space:nowrap;}
		.game_info{font-size:10px;color:#666666;line-height:15px;margin-top: 4px}
		
		.active{margin-right:34px;}
		
		.zoomInDown{
		-webkit-animation-play-state:running;
		 animation-play-state:running;
		}
	  @-webkit-keyframes zoomInDown {
	   0% {
		-webkit-transform: scale(.1) translateY(-2000px);
		opacity: 0;
		 }
	   60% {
		-webkit-transform: scale(.475) translateY(60px);
		opacity: 1;
	   }
	  }
	  @keyframes zoomInDown {
	   0% {
		opacity: 0;
		transform: scale(.1) translateY(-2000px);
		animation-timing-function: ease-in-out;
	   }

	  60% {
		opacity: 1;
		transform: scale(.475) translateY(60px);
		animation-timing-function: ease-out;
		}
	   }
		.zoomInDown{
		-webkit-animation-duration: 1s;
		animation-duration: 1s;
		-webkit-animation-fill-mode: both;
		animation-fill-mode: both;
		-webkit-animation-name: zoomInDown;
		animation-name: zoomInDown;
		animation-timing-function: ease-in-out;}
    </style>
</head>
<body style="padding:0; margin: 0; background: #FFFFFF;" align="center">
<div class="mask" style="width: 100%;height: 100%;display: none"></div>
<script type="text/javascript" src="${cdnHost}/plug-in/activity/js/liujiaoping/play_sdk.js"></script>
<script type="text/javascript" src="${cdnHost}/plug-in/activity/js/liujiaoping/play68.js"></script>
	 <ul id="game_btn">
		<li>* 查看排名</li>
		<li>* 查看规则</li>
	 </ul>
	<div class="main">
		<p class="close">X</p>
		<h5>排行榜</h5>
		<table>
		<c:forEach items="${rankList }" var="item">
			<tr class="sort">
				<td><p class="nikname">${fn:escapeXml(item.nickname)}</p></td>
				<td><p><fmt:formatDate value="${item.addtime }" pattern="MM/dd HH:mm:ss"/></p></td>
				<td><b>${item.score}</b> 分</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="main" align="top">
		<p class="close">X</p>
		<h5 style="margin-bottom:1px;color:red;">奖励规则</h5>
		<!--名次得流量-->
		<table class="active" id="rankRule">
			<tr><th>名次</th><th>流量</th></tr>
		</table>
		
		<!--拿分得流量-->
		<table id = "scoreRule">
			<tr><th>分数</th><th>流量</th></tr>
		</table>
		<h5 style="margin-bottom:1px;color:red;">游戏说明</h5>
		<p class="game_info">${activity.description}</p>
	</div>
<div id="loadingImg" style="top:35%;width: 50%;position:absolute; ">
    <div style="margin-top:10%">
        <img src="${cdnHost }/plug-in/activity/images/liujiaoping/loading.gif" width="7%" height="7%"/>
    </div>
</div>
<canvas id="gameCanvas" width="720" height="1280"></canvas>
<div class="banner" id="adDiv">
				<a href="${ad.adDetailUrl }"><img id='adimgedis'src="${ad.pic}" alt="" /></a>
	</div>
<script cocos src="${cdnHost }/plug-in/activity/js/liujiaoping/game.min.js"></script>
<script>
$(function(){
	
	var rankRule = rule.rankRule;
	var gradeRule = rule.gradeRule;
	for(var i in rankRule){
		$("#rankRule tr:last").after('<tr><td>'+rankNames[i]+':</td><td>'+rankRule[i]+'M</td></tr>')
	}
	for(var i in gradeRule){
		$("#scoreRule tr:last").after('<tr><td>'+i+'分:</td><td>'+gradeRule[i]+'M</td></tr>')
	}
	$('#game_btn').find('li').click(function(ev){
		var index=$(this).index();
		$('.mask').css('display','block');
		var pLeft = ($('.mask').width() - $('.main').outerWidth(true))/2;
		var pTop = ($('.mask').height() - $('.main').outerHeight(true))/4;
		$('.main').eq(index).css({'left':pLeft+'px','top':pTop+'px','display':'block'}).addClass('zoomInDown').siblings('.main').css('display','none');
		return false;
	});
	$(document).click(function(ev){
		$('.main').css('display','none');
		$('.mask').css('display','none');
	});
	
});
    var game_max_score = 11895;
</script>

</body>
</html>