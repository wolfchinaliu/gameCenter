<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>制作红包</title>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zimi/index.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/car-popup.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	
    <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/packet/index.css">
  </head>
  <body>
    <div class="container">
      <header class="header">
        <div class="header-user">
          <figure>
            <a href="javascript:;"><img src="${headImgUrl }" alt="" /></a>
          </figure>
          <dl>
            <dt>${nickName }</dt>
            <dd>${phoneNumber }</dd>
          </dl>
        </div>
        <div class="header-msg">
                 账户-全国流量：${countryFlowValue}M<span>|</span>账户-省内流量：${provinceFlowValue}M
        </div>
      </header>
      
      <main class="zimi">
        <form action="#">
          <input type="hidden" name="flowType" id="flowType" value="${hdEntity.flowtype}">
          <input type="hidden" name="redpacketNum" id="redpacketNum" value="${hdEntity.redpacketNum}">
          <input type="hidden" name="flowValue" id="flowValue" value="${hdEntity.redpacketValue}">
          <input type="hidden" name="openId" id="openId" value="${memberEntity.openId}">
          <input type="hidden" name="accountId" id="accountId" value="${memberEntity.accountId}">
          <input type="hidden" name="hdId" id="hdId" value="${hdEntity.id}">
          <input type="hidden" name="count" id="count" value="${count}">
          <input id="acctName" type="hidden" value="${acctEntity.acctName}">
        <section class="zimi-box">
          <h1 class="red">制作红包</h1>
            <ul class="packet-box">
              <li>活动时间：${starttime} - ${endtime}</li>
              <li>流量类型：${hdEntity.flowtype}</li>
              <li>流<span class="w1"></span>量：${hdEntity.redpacketValue}M</li>
              <li>个<span class="w1"></span>数：${hdEntity.redpacketNum}个</li>
              <li>祝<span class="w2"></span>福<span class="w2"></span>语：<input name="blessing" id="blessing" type="text" value="事业蒸蒸日上" /></li>
            </ul>
          </form>
        </section>
        <p class="packet-msg c999"> ${acctEntity.acctName}提供免费流量供您惠赠好友，商家提供流量<sapn class="red">${leftValue}M</sapn>，点击制作红包。</p>
        <input type="submit" id="j-btn-make" class="btn btn-1" value="制作红包" />
      </main>
      <div class="banner">
        <a href="${ad.adDetailUrl }"><img src="${ad.pic}" alt="" /></a>
      </div>
      <footer class="footer">
        <a href="javascript:;"><img src="${cdnHost}/plug-in/liuliangbao/20160701/images/footer/footer.jpg" alt="" /></a>
      </footer>
    </div>

    <script>
    
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
            'showOptionMenu',
         // 'onMenuShareQQ', 'onMenuShareWeibo', 'hideMenuItems', 'showMenuItems', 'hideAllNonBaseMenuItem', 'showAllNonBaseMenuItem', 'translateVoice', 'startRecord', 'stopRecord', 'onRecordEnd', 'playVoice', 'pauseVoice', 'stopVoice', 'uploadVoice', 'downloadVoice', 'chooseImage', 'previewImage', 'uploadImage', 'downloadImage', 'getNetworkType', 'openLocation', 'getLocation', 'hideOptionMenu', 'showOptionMenu', 'closeWindow', 'scanQRCode', 'chooseWXPay', 'openProductSpecificView', 'addCard', 'chooseCard', 'openCard'
        ]
    });
    
    if('${accountType}' == '1'){
    	wx.ready(function () {
    	 wx.showOptionMenu();
    	});
    }else{
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
    
    
    var flag=true;
    $(document).ready(function(){
        $('#j-btn-make').click(function(){
            var count=$("#count").val();
            if(count=='0'){
                $('body').popup({
                    title:'提示'
                    ,message:'<h2>尊敬的用户您好:</h2><p>您在该商家发送的红包超过3个，不能再发了，请去其他商家发吧。</p>'
                    ,id:'pop-2'
                    ,ok:"去别处发"
                    ,onOk:function(){
                        // 确认按钮的回调函数
                        window.location.href = "moreRedpacketController.do?moreRedpacketList&phoneNumber=${memberEntity.phoneNumber}";
                    }
                    ,cnacel:"知道了"
                    ,onCancel: function(){
                    }
                });
                return;
            }
            if(flag==false){
                return;
            }
            flag=false;
            $.ajax({
                type: 'POST',
                url: 'makeRedpacketController.do?doMake',
                dataType: 'JSON',
                data: {
                    "openId":$('#openId').val(),
                    "accountId":$('#accountId').val(),
                    "flowType":$('#flowType').val(),
                    "redpacketNum":$('#redpacketNum').val(),
                    "flowValue":$('#flowValue').val(),
                    "hdId":$('#hdId').val(),
                    "blessing":$('#blessing').val(),
                },
                cache: false,
                error: function () {
                    alert("请重试！");
                    return false;
                },
                success: function (data) {
                    //data = JSON.parse(data);
                    if(data.attributes.result=='true'){
                        var id=data.attributes.id;
                        window.location.href = "getRedpacketController.do?getRedpacket&hdid="+id+"&first=true";  //跳转到拆红包页面
                    }else{
                    	Zepto('body').popup({
                            title:'提示'
                            ,message:'<h2>尊敬的用户您好:</h2><p>'+$('#acctName').val()+'的补贴流量不足,此活动后续会不定期开展,敬请期待,您还可以去别的公众号发。</p>'
                            ,id:'pop-2'
                            ,ok:"去别处发"
                            ,onOk:function(){
                                // 确认按钮的回调函数
                                window.location.href = "moreRedpacketController.do?moreRedpacketList&phoneNumber=${memberEntity.phoneNumber}";
                            }
                            ,cnacel:"知道了"
                            ,onCancel: function(){
                                flag=true;
                            }
                        });
                    }
                }
            });
        });
    });
</script>
  </body>
</html>