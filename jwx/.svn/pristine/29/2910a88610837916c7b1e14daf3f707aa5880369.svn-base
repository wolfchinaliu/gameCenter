<%@ page import="org.apache.commons.lang.ObjectUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>签到</title>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/car-popup.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/util.js"></script>
	<script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>
	<link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/sign/index.css">   
    <script>
	function onBridgeReady(){
		WeixinJSBridge.call('hideOptionMenu');
	}

	if(typeof WeixinJSBridge == "undefined"){
		if( document.addEventListener ){
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		} else if (document.attachEvent){
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	} else {
		onBridgeReady();
	}
	</script>  
	</head>
	<body>
		<div class="container">
			<header class="header">
				<div class="header-user">
					<figure>
						<a href="javascript:;"><img src="${headImgUrl}" alt="" /></a>
					</figure>
					<dl>
						<dt>${nickName}</dt>
						<dd><%=ObjectUtils.defaultIfNull(request.getAttribute("phoneNumber"), "尚未绑定&nbsp;&nbsp;")%></dd>
					</dl>
				</div>
				<div class="header-msg">
					账户-全国流量：${countryFlow}M<span>|</span>账户-省内流量：${provinceFlow}M
				</div>
			</header>
			
			<main class="sign">
				<a href="javascript:;" class="btn btn-1 jq-signIn">签到领取${merchantInfoBean.data.get(0).countryFlowValue}M流量</a>
				<p class="sign-msg">（本公众号赠送流量适合${merchantInfoBean.data.get(0).province}${businessArea}手机号码）</p>
			</main>
			
			<div class="banner">
				<a href="${ad.adDetailUrl }"><img src="${ad.pic }" alt="" /></a>
			</div>
			
			<footer class="footer">
				<a href="javascript:;"><img src="${cdnHost}/plug-in/liuliangbao/20160701/images/footer/footer.jpg" alt="" /></a>
			</footer>
		</div>
		<script type="text/javascript">
          var dialog = null;
          $(document).ready(function(){
              $('.jq-signIn').on('click',function(){
                  sign();
              });
          });
      
          function bindPhoneNumber() {
              var url ='bindingController.do?redirectBinding' ;
              url += "&openId=${openId}";
              url += "&merchantId=${accountid}";
              url += "&nickName=${nickName}";
              url += "&phoneNumber=${phoneNumber}";
              url += "&operateType=签到";
              window.location.href = url;
          }
      
          function sign() {
              $.ajax({
                  type: 'POST',
                  url: 'signController.do?sign',
                  dataType: 'JSON',
                  data: {
                      "openId": '${openId}',
                      "accountid": '${accountid}',
                      "nickname": '${nickName}',
                      "phoneNumber": '${phoneNumber}'
                  },
                  cache: false,
                  error: function () {
                      $.mobTips("签到失败，请重试！");
                      return false;
                  },
                  success: function (data) {
                      var okText = '${phoneNumber}' == '' ? "去绑定" : "去充值";
                      if (data.flag == false) {
                          dialog = Zepto('body').popup({
                              title:'提示',
                              message: data.msg,
                              id:'pop-2',
                              ok:okText,
                              closeOnOk: true,
                              onOk:function(){
                                  if ('${phoneNumber}' == '') {
                                      bindPhoneNumber();
                                  } else {
                                      window.location.href = "userChargeController.do?userChargeView&accountid=${accountid}&openId=${openId}";
                                  }
                              }
                          });
                          return;
                      }
                      dialog = Zepto('body').popup({
                          title:'提示',
                          message:'<p>恭喜您获得${merchantInfoBean.data.get(0).countryFlowValue}M流量，已存入您的账户。</p>',
                          id:'pop-2',
                          ok:okText,
                          closeOnOk: false,
                          onOk:function(){
                              if ('${phoneNumber}' == '') {
                                  bindPhoneNumber();
                              } else {
                                  window.location.href = "userChargeController.do?userChargeView&accountid=${accountid}&openId=${openId}";
                              }
                          }
                      });
                  }
              });
          }
      </script>
	</body>
</html>
