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
        <title>中秋吃月饼大赛</title>
        <meta name="keywords" content=""/>
        <meta name="description" content=""/>
        <meta name="format-detection" content="telephone=no"/>
        <meta name="format-detection" content="address=no"/>
        <meta name="viewport" id="viewport" content="target-densitydpi=device-dpi,width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
        <meta name="author" content="cgi.beijing"/>
        <meta name="copyright" content=""/>
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
    <link href="${cdnHost}/plug-in/activity/css/eatMoonCakes/main.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
    var userImagePath = '${activity.imagePath}' == '' ? '' : '${activity.imagePath}/';
    var code = ${code};
    var rule =  ${activity.activityRule};
    var errorMsg = '${msg}';
    var anniuText = '去充值';
    var hasPhone = true;
    var flag = true;
    var is_worng = false;
    var openId = '${member.openId}';
    var rediceUrl = "userChargeController.do?userChargeView&accountid=${activity.accountid}&openId=${member.openId}";
    //图片目录 
    var imagePath = "${cdnHost}/plug-in/activity/images/eatMoonCakes/";
    //分享图片  (团片为 shareLogo.jpg) 暂时用了上传至微信了 所以引用的微信的地址 （生产上可上传主号上）
    var shareImage = "https://mmbiz.qlogo.cn/mmbiz_jpg/f9LRzHGicKgkoMy8cGaw9qAqKicCz8M3WEibc9ibw9OEJO086ALp1UhNsekwm2PJlCqTgtiaJ0Q4DE8oIuJbjv8WSVA/0?wx_fmt=jpeg";
    var rankNames = ['第一名','第二名','第三名','第四名','第五名','第六名','第七名','第八名','第九名','第十名'];
    if ('${member.phoneNumber}' == '') {
    	anniuText = '验证手机';
    	hasPhone = false;
    }
    var msg = '${msg}';
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
        var accountId = "${activity.accountid}";
        var hdid = "${activity.id}";
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
        
        function subscore(score) {
            if (flag == false) {
                return;
            }
            if(is_worng) {score = 0;}else{
            title = "我在${activity.title}吃了 " + score +"个月饼，还能获得海量免费流量，不服来战！";
            desc ="我一口气吃了 " + score +"个月饼，还能获得免费流量，不服来战！"
            }
            flag = false;
            //1、获取用户的答题情况
                $.ajax({
                    type: 'POST',
                    url: "activityController.do?subGameRecord",
                    data: {
                        "score": score,
                        "openId": openId,
                        "accountId": accountId,
                        "activityId": hdid
                    },
                    dataType: 'json',
                    success: function (data) {
                    	
                    	$("#c36").css("display","block");
                    	$("#c10").css("display","none");
                		$("#c11").css("display","block");
                		if(is_worng){
                    		Zepto('body').popup({
                                title: '提示'
                                , message: '<h2>警告:</h2><p> 检测您非法操作，如果多次操作将进行账户冻结 </p>'
                                , id: 'pop-2'
                                , ok: anniuText
                                , onOk: function () {
                                    // 确认按钮的回调函数
                                	if(hasPhone){
                                		window.location.href = "userChargeController.do?userChargeView&accountid="+accountId+"&openId="+openId;
                                		console.log('ok');
                                	}
                                	else
                                		bindPhoneNumber();
                                	 flag = true;
                                }
                                , cnacel2: "关闭"
                                , onCancel2: function () {
                                	 flag = true;
                                    console.log('cancel');
                                }
                            });
                    	}else if (data.attributes.error == "invalid") {
                             Zepto('body').popup({
                                 title: '提示'
                                 , message: '<h2>尊敬的用户您好:</h2><p>'+data.attributes.msg+'</p>'
                                 , id: 'pop-2'
                                 , ok: anniuText
                                 , onOk: function () {
                                     // 确认按钮的回调函数
                                 	if(hasPhone){
                                 		window.location.href = "userChargeController.do?userChargeView&accountid="+accountId+"&openId="+openId;
                                 		console.log('ok');
                                 	}
                                 	else
                                 		bindPhoneNumber();
                                 	 flag = true;
                                 }
                                 , cnacel2: "关闭"
                                 , onCancel2: function () {
                                 	 flag = true;
                                     console.log('cancel');
                                 }
                             });
                         } else if (data.attributes.error == "noflow") {
                        	 if(data.attributes.count > 0){
                         		$("#c19").css("display","block");
                         	}
                             Zepto('body').popup({
                                 title: '提示'
                                 , message: '<h2>尊敬的用户您好:</h2><p>'+data.attributes.msg+'</p>'
                                 , id: 'pop-2'
                                 , ok: anniuText
                                 , onOk: function () {
                                     // 确认按钮的回调函数
                                 	if(hasPhone){
                                 		window.location.href = "userChargeController.do?userChargeView&accountid="+accountId+"&openId="+openId;
                                 		console.log('ok');
                                 	}
                                 	else
                                 		bindPhoneNumber();
                                 	 flag = true;
                                 }
                                 , cnacel2: "继续冲刺"
                                 , onCancel2: function () {
                                 	 flag = true;
                                     console.log('cancel');
                                 }
                             });
                         } else {
                        	if(data.attributes.count > 0){
                        		$("#c19").css("display","block");
                        	}
                            Zepto('body').popup({
                                title: '提示'
                                ,
                                message: '<h2>共吃了' +score + '个月饼</h2><p>获得' + data.attributes.flow + 'M流量。</p>'
                                ,
                                id: 'pop-2'
                                ,
                                ok: anniuText
                                ,
                                onOk: function () {
                                	if(hasPhone){
                                		window.location.href = rediceUrl;
                                		console.log('ok');
                                	}
                                	else
                                		bindPhoneNumber();
                                	 flag = true;
                                }
                                ,
                                cnacel2: "继续冲刺"
                                ,
                                onCancel2: function () {
                                	 flag = true;
                                }
                                ,
                                onCancel: function () {
                                	 flag = true;
                                }
                            });
                        }
                    },
                    error: function () {
                        alert("请重试");
                        flag = true;
                    },
                    complete: function () {
                        flag = true;
                    }
                });
            }
        function bindPhoneNumber() {
            var url ='bindingController.do?redirectBinding';
            url += "&openId=${member.openId}";
            url += "&merchantId=${activity.accountid}";
            url += "&nickName=${member.nickName}" ;
            url += "&phoneNumber=${member.phoneNumber}";
            url += "&operateType=" + encodeURIComponent('吃月饼');
            window.location.href = url;
        }
    </script>
<style type="text/css">
</style>
</head><body>
<div id="loading">5%</div>
        <div id="container" ></div>
        <script src="plug-in/activity/js/eatMoonCakes/ping_tcss_ied.js"></script>
        <script type="text/javascript">if (typeof (pgvMain) == "function") {
        pgvMain()
    }
    ;</script>
        <script src="${cdnHost}/plug-in/activity/js/jquery-1.10.2.js"></script>
        <script src="${cdnHost}/plug-in/activity/js/eatMoonCakes/jquery.preload.min.js"></script>
        <script src="${cdnHost}/plug-in/activity/js/eatMoonCakes/resize.js"></script>
        <script src="${cdnHost}/plug-in/activity/js/eatMoonCakes/page.js"></script>
        <script>
        $(function () {
        var baozi_max=50;
				var eat_max_time=25;
        $("#loading").css("line-height", $(window).innerHeight() + "px");
        $("#loading").css("opacity", 0.6);
        init();
      //解析游戏规则
    	var rankRule = rule.rankRule;
    	var gradeRule = rule.gradeRule;
    	var ruleHtmlString = "";
    	ruleHtmlString += '<ul class="rules_left rules_list">';
    	for(var i in rankRule){
    		ruleHtmlString += '<li><span>'+rankNames[i]+':</span>'+rankRule[i]+'M</li>';
    	}
    	ruleHtmlString += '</ul><ul class="rules_right rules_list">';
    	for(var i in gradeRule){
    		ruleHtmlString += '<li><span>'+i+'分:</span>'+gradeRule[i]+'M</li>';
    	}
    	ruleHtmlString += '</ul>'
        $('#ruleDiv').html(ruleHtmlString);
    	
        preloadImg()
    });
    var items = [];
    function init() {
        items.push({name: "c1", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c1.jpg"});
        items.push({name: "c2", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c2.png"});
        items.push({name: "c3", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c3.png"});
        items.push({name: "c4", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c4.png"});
        items.push({name: "c5", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c5.png"});
        items.push({name: "c6", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c6.png"});
        items.push({name: "c7", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c7.png"});
        items.push({name: "c8", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c8.png"});
        items.push({name: "c9", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c9.png"});
        items.push({name: "c10", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c10.png"});
        items.push({name: "c11", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c11.png"});
        items.push({name: "c12", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c12.png"});
        items.push({name: "c13", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c13.png"});
        items.push({name: "c14", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c14.png"});
        items.push({name: "c16", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c16.png"});
        items.push({name: "c17", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c17.png"});
        items.push({name: "c19", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c19.png"});
        items.push({name: "c20", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c20.png"});
        items.push({name: "c21", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c21.png"});
        items.push({name: "c22", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c22.png"});
        items.push({name: "c23", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c23.png"});
        items.push({name: "c24", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c24.png"});
        items.push({name: "c25", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c25.png"});
        items.push({name: "c26", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c26.png"});
        items.push({name: "c27", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c27.png"});
        items.push({name: "c28", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c28.png"});
        items.push({name: "c29", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c29.png"});
        items.push({name: "c30", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c30.png"});
        items.push({name: "c31", rect: {t: 0, l: 30, w: 0, h: 0}, img: "c31.png"});
        items.push({name: "c32", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c32.png"});
        items.push({name: "c33", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c33.png"});
        items.push({name: "c34", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c34.png"});
        items.push({name: "c35", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c35.png"});
        items.push({name: "c31m", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c31.png"});
        items.push({name: "c32m", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c32.png"});
        items.push({name: "c33m", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c33.png"});
        items.push({name: "c34m", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c34.png"});
        items.push({name: "c35m", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c35.png"});
        items.push({name: "c36", rect: {t: 0, l: 0, w: 0, h: 0}, img: "c36.png"});
        var a = "";
        items.forEach(function (b) {
            a += '<div class="imgElement" id="' + b.name + '" style="background:url(\''+imagePath+b.img +'\')"></div>'
        });
        a += '<div id="c14_">x 0</div>';
    	a += '<div id="c16_" onclick="game_rules()">游戏规则</div>';
    	a += '<div id="c18_"><p class="top" style="background:url(\''+imagePath +'pic_top.png\')"></p><div style="background-color: #c8bb98"><h4 style="color: red;font-family:MyNewFont;font-size:1.5em;">英雄榜</h4><ul>';
    	<c:forEach items="${rankList }" var="item">
    	a += '<li class="sort_item"><ul><li>${fn:escapeXml(item.nickname)}</li><li><fmt:formatDate value="${item.addtime }" pattern="MM/dd HH:mm:ss"/></li><li>${item.score}分</li></ul></li>';
    	</c:forEach>
    	a +='</ul></div><p class="bottom" style="background:url(\''+imagePath +'pic_bottom.png\')"></p></div>';
		a += '<div id="c17_"><p class="top"style="background:url(\''+imagePath+'pic_top.png\')"></p>\
		<div style="background-color: #c8bb98"><div class="rules_top" id="ruleDiv"></div> <div class="rules_bottom"><h4 style="color: red;font-family:MyNewFont;font-size:1.5em;">游戏说明</h4>${activity.description}</div> </div><p class="bottom" style="background:url(\''+imagePath +'pic_bottom.png\')"></p></div>';
        a += '<div id="c15_">${account.accountname}<br/>${activity.title}</div>'
        $("#container").html(a);
        setTimeout(function () {
            resizeArea();
            $(window).resize(function () {
                resizeArea()
            });
            window.onorientationchange = function () {
                resizeArea()
            };
            load_page_1()
        }, 50)
    }
    var imgArr = [imagePath+"c1.jpg", 
                  imagePath+"c2.png",
                  imagePath+"c4.png",
                  imagePath+"c5.png",
                  imagePath+"c6.png",
                  imagePath+"c7.png",
                  imagePath+"c8.png",
                  imagePath+"c9.png",
                  imagePath+"c10.png",
                  imagePath+"c11.png",
                  imagePath+"c12.png",
                  imagePath+"c13.png",
                  imagePath+"c14.png",
                  imagePath+"c16.png",
                  imagePath+"c17.png",
                  imagePath+"c19.png",
                  imagePath+"c20.png",
                  imagePath+"c21.png",
                  imagePath+"c22.png",
                  imagePath+"c23.png",
                  imagePath+"c24.png",
                  imagePath+"c25.png",
                  imagePath+"c26.png",
                  imagePath+"c27.png",
                  imagePath+"c28.png",
                  imagePath+"c29.png",
                  imagePath+"c30.png",
                  imagePath+"c31.png",
                  imagePath+"c32.png",
                  imagePath+"c33.png",
                  imagePath+"c34.png",
                  imagePath+"c35.png",
                  imagePath+"c36.png"]
    var loadCount = 0;
    function preloadImg() {
        $.preload(imgArr, 1, function () {
            loadCount++;
            var a = Math.round((loadCount / imgArr.length) * 100);
            $("#loading").html(a + "%");
            if (loadCount >= imgArr.length) {
                $("#loading").css("display", "none")
            }
        })
    }
    ;</script>
    </body>
</html>
