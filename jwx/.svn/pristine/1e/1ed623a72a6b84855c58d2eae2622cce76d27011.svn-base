<%@ taglib prefix="t" uri="/easyui-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
        <title>${gameTypeEntity.gameName }</title>
        <meta name="keywords" content=""/>
        <meta name="description" content=""/>
        <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    	<script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
        <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
    	<script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/js/0218/zepto/car-popup.js"></script>    
    	<link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/css/0218/zimi/normalize.css">
    	<script type="text/javascript">
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
        var title="${gameTypeEntity.gameName} ，来与我一较高下吧！";
        var rediceUrl = "<%=basePath%>/userChargeController.do?userChargeView&accountid=${game.accountId}&openId=${member.openId}";
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
        	        "title":title,
        	    });
        }
        
        function bindPhoneNumber() {
            var url ='<%=basePath%>/bindingController.do?redirectBinding';
            url += "&openId=${member.openId}";
            url += "&merchantId=${game.accountId}";
            url += "&nickName=${member.nickName}" ;
            url += "&phoneNumber=${member.phoneNumber}";
            url += "&operateType=" + encodeURIComponent('${gameTypeEntity.gameName}');
            window.location.href = url;
        }
        function drawFlow(){
        	window.location.href = rediceUrl;
        }
        function changeFrameHeight(){
            var ifm= document.getElementById("playGameIf"); 
            ifm.height=document.documentElement.clientHeight;
            ifm.width=document.documentElement.clientWidth;
        }
        </script >
        </head>
        
       <BODY >
<FORM id="myform" METHOD=POST ACTION="${gameTypeEntity.playUrl }" TARGET="playGame">
    <INPUT TYPE="hidden" NAME="gameId" value="${game.id}">
    <INPUT TYPE="hidden" NAME="openId" value="${member.openId}">
    <INPUT TYPE="hidden" NAME="nickName" value="${member.nickName}">
    <INPUT TYPE="hidden" NAME="acconutId" value="${game.accountId}">
</FORM>
<IFRAME NAME="playGame" id="playGameIf"  frameborder="0" border="0" marginwidth="0" marginheight="0"  allowtransparency="yes" onload="changeFrameHeight()" ></IFRAME>


<script type="text/javascript">
$(document).ready(function(){
	var errorCode = ${code};
	 var errorMsg = '${msg}';
	 var anniuText = '去充值';
	 if ('${member.phoneNumber}' == '') {
	    	anniuText = '验证手机';
	    	hasPhone = false;
	    }
	if(errorCode != 0)
		Zepto('body').popup({
         title:'提示',
         message:errorMsg,
         id:'pop-2',
         ok:anniuText,
         cnacel:"关闭",
         closeOnOk: false,
         onCancel: function() {
           flag = true;
         },
         onOk:function(){
           flag=true;
           if ('${member.phoneNumber}' == '') {
             bindPhoneNumber();
             return;
           }
           window.location.href = rediceUrl;
         }
        });
else{
	$('#myform').submit();
}
});
window.onresize=function(){  
	changeFrameHeight();
} 
</script>

</BODY>
</html>
