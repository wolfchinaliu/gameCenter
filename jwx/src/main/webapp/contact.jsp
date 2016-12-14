<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0021)http://www.soft8.com.cn/ -->
<HTML><HEAD><TITLE>小马微信-微营销-微信公众号一站式服务平台-微信公众号管理</TITLE>
<META charset=UTF-8>
<META name=keywords 
content=小马微信,微信营销,微信代运营,微信托管,微网站,微商城,微营销,微信定制开发,公众平台登录,微站,微店,公众号服务平台>
<META name=description 
content=小马微信,国内最大的微营销-微信公众号一站式服务平台-微信公众号管理,小马微信十大微体系:微菜单、微官网、微会员、微活动、微商城、微推送、微服务、微统计、微支付、微客服,企业微营销必备。><LINK 
rel="shortcut icon" href="/favicon.ico"><LINK rel=icon href="/favicon.ico"><LINK 
rel=stylesheet type=text/css href="index_images/base.css">
<SCRIPT type=text/javascript src="index_images/jquery.tools.min.js"></SCRIPT>

<SCRIPT type=text/javascript src="index_images/slides.js"></SCRIPT>
<LINK rel=stylesheet type=text/css href="index_images/index.css">
<SCRIPT type=text/javascript src="index_images/newsScroll.js"></SCRIPT>

<SCRIPT>
        $(function() {
            var sliderElement = $('.slider-carousel');
            sliderElement.slidesjs({
                width: 1000,
                height: 533,
                play: {
                    auto: true,
                    effect: 'fade'
                },
                navigation: {
                    effect: "fade"
                },
                pagination: {
                    effect: "fade"
                },
                effect: {
                    fade: {
                        speed: 400
                    }
                }
            });
            var slidernav = sliderElement.find('.slidesjs-navigation');
            sliderElement.hover(function() {
                slidernav.stop().animate({
                    opacity: 1
                })
            },function() {
                slidernav.stop().animate({
                    opacity: 0
                });
            });
        });
    </SCRIPT>

<META name=GENERATOR content="MSHTML 9.00.8112.16633"></HEAD>
<BODY 
onload="if(window.parent && window.parent != window){window.parent.location.href = location.href;}">
<DIV id=header>
<DIV class=g>
<DIV id=logo class=l><A title=小马微信-微营销-微信公众号一站式服务平台-微信公众号管理 
href="www.soft8.com.cn"></A></DIV><!-- <div id="area" class="l">
			<a id="areaBtn">临沂</a>
		</div> -->
<UL id=nav class=l>
  <LI><A class=on href="index.jsp">首页</A> </LI>
  <LI><A href="function.jsp">功能介绍</A> </LI>
  <LI><A href="contact.jsp">联系我们</A> </LI>
  <LI><A href="loginController.do?goLogin">免费试用</A> </LI>
  <LI><A href="loginController.do?goLogin">登录/注册</A> </LI>
  </UL>
<DIV id=loginBox class=r><A class=loginBtn 
href="loginController.do?goLogin" target=_blank>登陆</A> <A class=regBtn 
href="registerController.do?goRegister" target=_blank>注册</A> </DIV></DIV></DIV>

<div class="slider">

	<hr style="width: 100%;height:1px;background: green;border: 0;">
	<DIV style="padding-left: 20%;padding-top: 10px;padding-bottom: 10px;">
	<h2><b>业务联系</b></h2>
	<br>
	<p>南京马行空信息技术有限公司 南京总部</p>
	<p>地址：南京市秦淮区太平南路2号</p>
	<p>电话：025-52265542</p>
	<p>业务联系 ：13405838713</p>
	<p>QQ：303563038，微信：kaicheng5618</p>
	<br>
	<p>南京马行空信息技术有限公司 广州分部</p>
	<p>地址：广州市天河区儒林大街6号</p>
	<p>业务联系：18620508961</p>
	<p>QQ：785863737，微信：yanhong_2010</p>
	</div>
</DIV>
<DIV id=footer>
<DIV class=g>
<DIV class="ftLogo l"><A href="http://www.soft8.com.cn/static/wxgjcn/"></A> </DIV>
<DIV class="ftNav l">
<DIV>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<A title=关于小马微信 
href="about.jsp">关于小马微信</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <A 
title=帮助中心 href="helpController.do?list" 
target=_blank>帮助中心</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <A 
title=联系我们 
href="contact.jsp">联系我们</A>

<SCRIPT language=javascript type=text/javascript src=""></SCRIPT>
 </DIV>
<DIV class=copyright></DIV></DIV>
<DIV class="code r"></DIV></DIV></DIV>
<DIV id=service><A class="srvLog png24" 
href="registerController.do?goRegister">免费试用</A> <A class="srvCns png24" 
href="http://wpa.qq.com/msgrd?v=3&amp;uin=1553294881&amp;site=qq&amp;menu=yes" 
target=_blank>在线咨询</A> </DIV><!--[if IE]>
<SCRIPT src="index_images/DD_belatedPNG.js"></SCRIPT>

<SCRIPT>
		DD_belatedPNG.fix('.png24');
	</SCRIPT>
<![endif]--><!--滚动 -->
<SCRIPT>
	$(function(){
		$("#ftrigger").tabs("#focus li", {effect:'fade', fadeInSpeed:500, event:'mouseover', rotate:true}).slideshow({autoplay:true, interval:4000});
		$("#chained").scrollable({circular: true}).autoscroll({interval:3000});
		$("#info").rowScroll({line:1,speed:500,left:"infoBtnL",right:"infoBtnR",timer:"3000"});
		$("#highlight li").hover(
			function(){
				$(this).addClass("hover");
			},
			function(){
				$(this).removeClass("hover");
			}
		);
		$("a[rel]").overlay({
			mask: '#44af35',
			effect: 'apple',
			top:"center",
			onBeforeLoad: function() {
				var wrap = this.getOverlay().find(".contentWrap");
				wrap.load(this.getTrigger().attr("href")+" #mdCnt");
				$(".oImgBox").html(this.getTrigger().children("img").clone());
				$(".oTitle h3").html(this.getTrigger().children("span").html());
				$(".oTitle p").html(this.getTrigger().parent().next().html().replace(/<br[^>]*>/ig, "，"));
				$(".newW").attr("href",this.getTrigger().attr("href"));
			}
		});
	});
	</SCRIPT>
</BODY></HTML>
