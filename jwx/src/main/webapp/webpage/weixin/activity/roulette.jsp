<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
      <meta charset=utf-8>
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
      <meta content="yes" name="apple-mobile-web-app-capable" />
      <meta content="black" name="apple-mobile-web-app-status-bar-style" />
      <title>幸运大转盘抽奖</title>
      <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>
      <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
      <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
      <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
      <script type="text/javascript" src="plug-in/liuliangbao/js/0112/jQueryRotate.js"></script>
      <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/car-popup.js"></script>
      <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/util.js"></script>
      <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
      <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/zhuanpan/index.css">
      <link href="plug-in/activity/css/style.css" rel="stylesheet" type="text/css">
        	<script type="text/javascript">
        		//加载图片背景图片
        	  	$(document).ready(function(){
        			  var imgUrl = '${activity.imagePath}'
				      if(imgUrl!=null&&imgUrl!=""){
				      	var styleStr = "width: 7.5rem;background: url("+imgUrl+") 0 0 no-repeat;background-size:contain;"
		  				$(".container").attr("style",styleStr);
		  				}else{
		  					imgUrl = '${pageContext.request.contextPath }/plug-in/liuliangbao/20160701/images/zhuanpan/bg1.jpg';
		  					var styleStr = "width: 7.5rem;background: url('${pageContext.request.contextPath }/plug-in/liuliangbao/20160701/images/zhuanpan/bg1.jpg') 0 0 no-repeat;background-size:contain;"
		  					$(".container").attr("style",styleStr);
		  				} 
				});   
	  	</script>
      <script type="text/javascript">
       //判断公众号类型 确认是否分享 
        //关于分享链接的一些配置
         if('${accountType}' == '1'){
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: '${map.appId}', // 必填，公众号的唯一标识
            timestamp: '${map.timestamp}', // 必填，生成签名的时间戳 
            nonceStr: '${map.nonceStr}', // 必填，生成签名的随机串
            signature: '${map.signature}',// 必填，签名，见附录1
            jsApiList: [
                'onMenuShareTimeline',
                'onMenuShareAppMessage'
            ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.ready(function () {
            wx.onMenuShareTimeline({
                title: '玩游戏领免费手机流量。我已领取，你也来参加吧。',
                link: '${link}', // 分享链接
                imgUrl: '${cdnHost}/plug-in/liuliangbao/20160701/images/shareimg/zhuanpanshare.jpg', // 分享图标
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });
            wx.onMenuShareAppMessage({    
                title: '实物大转盘',
                desc: '玩游戏领免费手机流量。我已领取，你也来参加吧。',
                link: '${link}', // 分享链接
                imgUrl: '${cdnHost}/plug-in/liuliangbao/20160701/images/shareimg/zhuanpanshare.jpg', // 分享图标
                type: '', // 分享类型,music、video或link，不填默认为link
                dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });
            wx.showOptionMenu();
        });
        wx.error(function (res) {

        });     
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
      </script>
	<script type="text/javascript" src="plug-in/activity/js/awardRotate.js"></script>

      <script type="text/javascript">
      	var rule = ${activity.activityRule};
      	var length = rule.length;
      	var errorCode = ${code};
      	var errorMsg = '${msg}';
      	for(var i = 0;i < length ;i ++){
      		if(rule[i].type == 1){
      			rule[i].value = "流量"+rule[i].value+"M";
      		}
      	}
      	var noWin = [["谢谢参与",""],["要加油哦",""],["还差一点",""],["不要灰心",""],["再接再厉",""],["匆匆过客",""]];
      	var rest = new Array();
      	if(length == 1){
      		rest.push([rule[0].name,rule[0].value],noWin[0]);
      		rest.push([rule[0].name,rule[0].value],noWin[0]);
      		rest.push([rule[0].name,rule[0].value],noWin[0]);
      	}else if(length == 2){
      		rest.push([rule[0].name,rule[0].value],noWin[0]);
      		rest.push([rule[1].name,rule[1].value],noWin[1]);
      		rest.push([rule[0].name,rule[0].value],noWin[0]);
      		rest.push([rule[1].name,rule[1].value],noWin[1]);
      	}else if(length%2 == 0){
      		rest.push(noWin[0]);
      		for(var i = 0;i < length/2 ;i ++){
      			rest.push([rule[i].name,rule[i].value]);
      		}
      		rest.push(noWin[1]);
      		for(var i =  length/2 ;i < length ;i ++){
      			rest.push([rule[i].name,rule[i].value]);
      		}
      	}else {
      		rest.push(noWin[0]);
      		for(var i = 0;i < length ;i ++){
      			rest.push([rule[i].name,rule[i].value]);
      		}
      	}
        var flag=true;
        var turnplat={
        		restaraunts:rest,				
        		colors:["#FFF4D6","#FFFFFF", "#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF", "#FFF4D6", "#FFFFFF"],					
        		outsideRadius:192,			//大转盘外圆的半径
        		textRadius:155,				//大转盘奖品位置距离圆心的距离
        		insideRadius:68,			//大转盘内圆的半径
        		startAngle:0,				//转盘开始角度
        		bRotate:false				//false:停止;ture:旋转
        };
        var rediceUrl = "userChargeController.do?userChargeView&accountid=${activity.accountid}&openId=${member.openId}";
        var anniuText = '去充值';
        if ('${member.phoneNumber}' == '') {
        	anniuText = '验证手机';
        }
    $(document).ready(function(){
        var rotateFn = function (item, txt){
        	var goTo = true;
    		var angleIm = 360 / turnplat.restaraunts.length ;
    		if(length == 1) angleIm=360/6;
    		if(length == 2) angleIm=360/8;
    		var angles = 0;
    		if(item < length){
    			//中奖了
    			if(rule[item].type == 2){
    				if('${member.phoneNumber}' != ''){
    					goTo = false;
    				}else{
    					txt += "<br/><span style='color: red;font-weight: bold;'>温馨提示 ：只有验证手机后 商家才能联系你领取奖品哦</sapn>";	
    				}
    				
    			}
    			if(length < 3){
    				angles = rnd(632 - angleIm * 2 * item - angleIm,628 - angleIm * 2 * item );
    			}else if(length % 2 == 0 && item >= length/2){
    					angles = rnd(632 - (item +3) * angleIm  , 628 -  (item + 2)  * angleIm ) ;
    			}
    			else{
    				angles = rnd (632 - (item +2)  * angleIm ,628 - angleIm * item - angleIm);
    			}
    			//angles = rnd(632 - angleIm * 2 * item - angleIm,628 - angleIm * 2 * item ); //防止在线上
    		}else{
    			//var index = rnd(1,length);
    			angles = rnd(632 - angleIm   ,628  );
    		}
    		$('#wheelcanvas').stopRotate();
    		$('#wheelcanvas').rotate({
    			angle:0,    //开始的旋转角度
    			animateTo:angles+1800, //请求超时返回的角度
    			duration:8000,   //旋转持续的时间
    			callback:function (){
    				if(goTo)
    				var dialog = Zepto('body').popup({
                        title:'提示',
                        message:txt.replace(/\\n/g, '<br/>'),
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
    				else
    	    				var dialog = Zepto('body').popup({
    	                        title:'提示',
    	                        message:txt.replace(/\\n/g, '<br/>'),
    	                        id:'pop-2',
    	                  cnacel:"关闭",
    	                  closeOnOk: true,
    	                  onCancel: function() {
    	                    flag = true;
    	                  },
                          onOk:function(){
                              flag=true;
                            }
    	                    });
    				turnplat.bRotate = !turnplat.bRotate;
    			}
    		});
    		
    			
    	};
    	
    	//点击指针触发旋转。
    	$('.pointer').click(function (){
    		turnplat.bRotate = !turnplat.bRotate;
    		//获取随机数(奖品个数范围内)
    		if(errorCode != 0){
    	    	 var dialog = Zepto('body').popup({
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
    		return ;
    		}
    		 if(false == flag){
    	            return;
    	          }
    	          flag = false;
    	          $.ajax({
    	            type : 'POST',
    	            url : "activityController.do?playActivity",
    	            dataType : "json",
    	            cache : false,
    	            error : function() {
    	              //$.mobAlert("出错了！");
    	              var dialog = Zepto('body').popup({
    	                title:'提示',
    	                message:anniuText,
    	                id:'pop-2',
    	                ok:"去充值",
    	                cnacel:"关闭",
    	                closeOnOk: false,
    	                onCancel: function() {
    	                  flag = true;
    	                  // alert('cnaceled');
    	                },
    	                onOk:function(){
    	                  flag=true;
    	                  if ('${member.phoneNumber}' == '') {
    	                    bindPhoneNumber();
    	                    return;
    	                  }
    	                  window.location.href = rediceUrl;
    	                },
    	              });
    	              return false;
    	            },
    	            success : function(result) {
    	              if(result.attributes.type==3){
    	              	rediceUrl = result.attributes.activityUrl;
    	             	anniuText = "去查看";
    	              }
    	              if (result.attributes.error == "invalid") {
    	                //$.mobAlert(result.attributes.msg);
    	                var dialog = Zepto('body').popup({
    	                        title:'提示',
    	                        message:result.attributes.msg,
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
    	                return;
    	              }
    	              //活动未开始
    	             //手机号不在商户覆盖区域内
    	             if (result.attributes.error == "illegal") {
    	                var dialog = Zepto('body').popup({
    	                title:'提示',
    	                message:result.attributes.msg,
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
    	               return;
    	               }

    	              var level = parseInt(result.attributes.level); //等级
    	              var msg = result.attributes.msg;
    	              var count = result.attributes.count;  //剩余次数
    	              document.getElementById("prizetime").innerText = count; //修改页面剩余次数
    	              //调用转盘旋转函数
    	              rotateFn(level , msg);
    	            }
    	          });
    		
    		
    	});
    	
    	//渲染画布转盘图形
    	
    	drawRouletteWheel();
    	
    	if(errorCode != 0)
    	 var dialog = Zepto('body').popup({
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
    });
        //转盘旋转事件
function rnd(min, max){
	var random = Math.floor(Math.random()*(max-min+1)+min);
	return random;
}

/*window.onload=function(){
};*/

//绘制转盘图形
function drawRouletteWheel() {    
  var canvas = document.getElementById("wheelcanvas");    
  if (canvas.getContext) {
	  var arc = Math.PI / (turnplat.restaraunts.length/2);
	  var ctx = canvas.getContext("2d");
	  ctx.strokeStyle = "#FFBE04";
	  ctx.font = '16px Microsoft YaHei';      
	  for(var i = 0; i < turnplat.restaraunts.length; i++) {       
		  var angle = turnplat.startAngle + i * arc;
		  ctx.fillStyle = turnplat.colors[i];
		  ctx.beginPath();
		 
		  ctx.arc(211, 211, turnplat.outsideRadius, angle, angle + arc, false);    
		  ctx.arc(211, 211, turnplat.insideRadius, angle + arc, angle, true);
		  ctx.stroke();  
		  ctx.fill();
		  ctx.save();   
		  
		  ctx.fillStyle = "#E5302F";
		  var line_height = 17;
		  
		  //重新映射中心定位到画布
		  ctx.translate(211 + Math.cos(angle + arc / 2) * turnplat.textRadius, 211 + Math.sin(angle + arc / 2) * turnplat.textRadius);
		  
		  ctx.rotate(angle + arc / 2 + Math.PI / 2);
		  ctx.font = 'bold 18px Microsoft YaHei';
		  ctx.fillText(turnplat.restaraunts[i][0], -ctx.measureText(turnplat.restaraunts[i][0]).width / 2, 0);
		  ctx.font = '14px Microsoft YaHei';
		  ctx.fillText(turnplat.restaraunts[i][1], -ctx.measureText(turnplat.restaraunts[i][1]).width / 2, line_height);
		  /** 根据奖品类型、奖品名称长度渲染不同效果**/
		  
		  ctx.restore();
		  //----绘制奖品结束----
	  }     
  } 
}
        //点击转盘执行后台绑定事件
        function bindPhoneNumber() {
            var url = 'bindingController.do?redirectBinding';
            url += "&openId=${member.openId}";
            url += "&merchantId=${activity.accountid}";
            url += "&nickName=${member.nickName}";
            url += "&phoneNumber=${member.phoneNumber}";
            url += "&operateType=新大转盘";
            window.location.href = url;
        }
        
  </script>
  <style type="text/css">
  .this-dialog-list li span {
    display: inline-block;
    float: left;
  }
  



.this-dialog-list li {
    width: 2.8rem;
    height: 0.5rem;
    line-height: 0.5rem;
    float: left;
    color: #FA6958;
    font-size: 0.20rem;
    overflow: hidden;
}
  </style>

  	
	</head>
	<body>
		<div class="container"  >
			<main class="zhuanpan">
			
				<div class="circle" style="background:rgba(255,255,255,0);">
					<a href="javascript:;" class="btn-xq" id="j-zhuanpan-xq" style="z-index: 20">活动详情</a>
			  <img src="plug-in/activity/images/1.png" id="shan-img" style="display:none;" />
  			 <img src="plug-in/activity/images/2.png" id="sorry-img" style="display:none;" />
		<div class="banner1">
		<div class="turnplate" style="background-image:url(plug-in/activity/images/turnplate-bg.png);background-size:100% 100%;" >
			<canvas class="item" id="wheelcanvas" width="422px" height="422px" >对不起，当前你的浏览器不支持此效果</canvas>
			<img class="pointer" src="plug-in/activity/images/turnplate-pointer.png"/>
		</div>
		</div>
				</div>
				<p class="zhuanpan-msg">
					您剩余 <span class="red" id="prizetime">${count == null ? 0 : count}</span> 次机会
				</p>
				<div class="zhuanpan-gd">
					<ul class="zhuanpan-list" id="j-zhuanpan-list">
                    <c:forEach items="${record }" var="item">
                      <li>${item.nickname }获得${item.prizelevel} （${item.prizevalue }）<span><fmt:formatDate value="${item.addtime }" dateStyle="default" timeStyle="default"/></span></li>
                    </c:forEach>
					</ul>
				</div>
				
			</main>
			
			<div class="banner">
				<a href="${ad.adDetailUrl }"><img src="${ad.pic}" alt="" /></a>
			</div>
			
			<footer class="footer">
				<a href="javascript:;"><img src="${cdnHost}/plug-in/liuliangbao/20160701/images/footer/footer.jpg" alt="" /></a>
			</footer>
			</div>
		

		<script type="text/javascript">
		$('#j-zhuanpan-xq').click(function() {
			
			var header = '<h1 class="dialog-header-title1"><img src="${cdnHost}/plug-in/liuliangbao/20160701/images/dialog/title1.png" alt="" /></h1>\
				<h2 class="dialog-header-title2">活动详情</h2>';
			var main = '<h3><span>奖项设置</span></h3>\
						<ul class="this-dialog-list clx" style="font-size:12px">';
						for(var i = 0 ;i < length;i++){
							main +=	'<li>\
								<span class="left">'+rule[i].name+':&nbsp;</span>\
								<span class="right">'+rule[i].value+'</span>\
							</li>';
						}
						main +=	'</ul>\
						<h3><span>温馨提示</span></h3>\
						<p class="dialog-msg">${activity.description}</p>';
			$.dialog({
				header: header,
				main: main
			});
		});
		</script>
    <script type="text/javascript">
    function scrollTop() {
    	
    	var $box = $('#j-zhuanpan-list');
    	var timer = null;
    	var items = $box.children();
    	var len = items.length;
    	var i = 0;
    	
    	function animateScroll() {
    		
    	}
    	
    	if(len > 1) {
    		setInterval(function() {
    			if(i === (len - 1)) {
    				$box.css("top", "0.44rem");
    			}
    			i = i >= (len - 1) ? 0 : (i + 1);
    			$box.animate({ "top" : -i * 0.44 + "rem" }, 200);
    		}, 3000);
    	}
    	
    }

    scrollTop();



    </script>
	</body>
</html>
