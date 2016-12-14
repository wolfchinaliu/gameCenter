<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link href="index/home.css" rel="stylesheet" type="text/css">
 	<script type="text/javascript" src="plug-in/login/js/jquery.1.8.3.min.js"></script>
    <script type="text/javascript" src="plug-in/login/js/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="plug-in/login/js/jquery.jrumble.min.js"></script>
    <script type="text/javascript" src="plug-in/login/js/jquery.tipsy.cdn.js"></script>
    <script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
    <script type="text/javascript" src="plug-in/login/js/login.js"></script>
    <script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
</head>
<body>

<DIV id=header class=head>
	<DIV class=head_box>
		<DIV class="inner wrp">
			<H2 class="logo" id="logoId" style="padding-top: 15px;">石榴商盟管理平台</H2>
			<DIV class=account>
				<%--<DIV class="account_meta account_faq">第一次使用微营销？ <A href="registerController.do?goRegister">立即注册</A></DIV>--%>
			</DIV>
		</DIV>
	</DIV>
	<DIV class=banner>
		<DIV class="inner wrp">
			<DIV class=login_frame>
				<H3>登录</H3>
				<div id="alertMessage" style="color: red;"></div>
    			<div id="successLogin"></div>
				<DIV style="DISPLAY: none" id=err class=login_err_panel></DIV>
				<form name="formLogin" id="formLogin" action="loginController.do?login" check="loginController.do?checkuser" method="post">
					<input name="userKey" type="hidden" id="userKey" value="D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900" />
					<DIV id=js_mainContent class=login_input_panel>
						<DIV class=login_input><I class="icon_login un"></I><INPUT id="userName" name="userName" type=text placeholder="用户名或邮箱" nullmsg="请输入用户名!" iscookie="true"> </DIV>
						<DIV class=login_input><I class="icon_login pwd"></I><INPUT id="password" name="password" type=password placeholder="密码" nullmsg="请输入密码!"> </DIV>
						
						
						<DIV style="DISPLAY:block" id=verifyDiv class=verifycode>
							<SPAN class=frm_input_box><INPUT id="randCode" name="randCode" class=frm_input type=text nullmsg="请输入验证码!"> </SPAN>
							<IMG id="randCodeImage" src="randCodeImage" style="width: 120px;"> <A id=verifyChange href="javascript:void(0);" onclick="reloadRandCodeImage();">换一张</A>
						</DIV>
						
						<DIV class=login_help_panel>
							<LABEL class=frm_checkbox_label for=on_off>
							<input type="checkbox" id="on_off" name="remember" checked="ture" value="0" /> 记住帐号 </LABEL>
							<%--<A class=login_forget_pwd href="">无法登录？</A> --%>
						</DIV>
						<DIV class=login_btn_panel>
							<a class="btn_login" href="#" id="but_login" onclick="toLogin();">登录</a>
						</DIV>
					</DIV>
				</form>
			</DIV>
		</DIV>
	</DIV>
	<div align="center">
	<p style="font-size: 14px;color: gray;padding-right:10%;text-align: right;">
		(推荐使用谷歌，360，IE8+等高速浏览器 ，获得更快响应速度和安全性)
	</p>
	<br><br><br>
	<p>
		<%--<a href="index.jsp" style="color: black;text-decoration: none;">官网首页</a>&nbsp;&nbsp;&nbsp;--%>
		<a href="" style="color: black;text-decoration: none;"></a>&nbsp;&nbsp;&nbsp;
		<a href="" style="color: black;text-decoration: none;"></a>
	</p>
	
	<p style="font-size: 10px;color: #666666;"><br></p>
	
	</div>
</DIV>


</body>
<script type="text/javascript">
var id= '${param.id }';
    $.ajax({
        type : "post",// 请求方式
        dataType : "json",
        url : "loginController.do?getLogo",// 发送请求地址
        data:{data:id},
        success : function(data) {
        	$("#logoId").html(data.msg+'管理平台');
            
        } 
    });
 </script>

</html>