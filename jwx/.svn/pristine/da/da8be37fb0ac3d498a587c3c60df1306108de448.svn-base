<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<t:base type="jquery"></t:base>
	<link href="index/home.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">     
	function countDown(secs,surl){     
	    
	 var jumpTo = document.getElementById('jumpTo');
	 jumpTo.innerHTML=secs;  
	 if(--secs>0){     
	     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
	     }     
	 else{       
	     location.href=surl;     
	     }     
	 }
	</script>
	<style type="text/css">
		.reg_form{
			padding-left: 300px;padding-top: 50px;border:1px;border-color:green;padding-bottom: 10px;
		}
		.field{
			padding-top:10px;padding-bottom: 20px;
		}
	</style>
</head>
<body>
	<DIV id=header class=head>
		<DIV class=head_box>
			<DIV class="inner wrp">
				<H1 class=logo><A title=微营销平台 href="">微营销平台</A> </H1>
				<DIV class=account>
					<DIV class="account_meta account_faq">已有账号？ <A href="loginController.do?goLogin">立即登录</A></DIV>
				</DIV>
			</DIV>
		</DIV>
	
		<div class="reg_form">
		<div class="field">
			<h3>注册失败</h3>
			<p>失败原因：${msg}</p>
			<p><a href="registerController.do?goRegister">请返回上一步重新注册</a></p>
			<br><br>
		
			<span id="jumpTo">5</span>秒后自动跳转
			<script type="text/javascript">countDown(5,'registerController.do?goRegister');</script>
		</div>
		</div>
	</DIV>
</body>
</html>