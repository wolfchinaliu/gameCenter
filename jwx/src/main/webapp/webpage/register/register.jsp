<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<t:base type="jquery"></t:base>
	<link href="index/home.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="plug-in/jquery/jquery.form.min.js"></script>
	<style type="text/css">
		.field{
			padding-top:10px;padding-bottom: 20px;
		}
		.field label{
			float: left;width: 100px;margin-top:6px;
		}
		.field input{
			width: 250px;height: 30px;
		}
		.reg_form{
			padding-left: 300px;padding-top: 50px;border:1px;border-color:green;padding-bottom: 10px;
		}
	</style>
</head>
<body>

<DIV id=header class=head>
	<DIV class=head_box>
		<DIV class="inner wrp">
			<H2 class=logo><A title=微营销平台 href="index.jsp"></A> </H2>
			<DIV class=account>
				<DIV class="account_meta account_faq">已有账号？ <A href="loginController.do?goLogin">立即登录</A></DIV>
			</DIV>
		</DIV>
	</DIV>

<div class="reg_form">
	<form accept-charset="UTF-8" action="registerController.do?doRegister" class="register-form" id="new_user" method="post" onsubmit="return checkForm();return false;">


	<div class="field">
		<label for="loginName">用户名</label>
		<input id="loginName" name="loginName" placeholder="只能输入6-30个以字母开头的字串" required="required" size="30" type="text">
		<i id="statu_user_name1">请输入6-30个以字母开头的字串作为登录帐号</i>
	</div>
	
	<div class="field inline">
		<label for="ligonPass">密码</label>
		<input id="ligonPass" name="ligonPass" placeholder="只能输入6-20个字母、数字、下划线" required="required" size="30" type="password">
		<i id="statu_user_password"></i>
	</div>
	
	<div class="field inline">
		<label for="confirmPass">确认密码</label>
		<input id="confirmPass" name="confirmPass" placeholder="和密码一致" required="required" size="30" type="password">
		<i id="statu_user_password_confirmation"></i>
	</div>
	
	
	<div class="field inline">
		<label for="email">Email</label>
		<input id="email" name="email" placeholder="请输入您的邮箱地址" required="required" size="30" type="text" value="">
		<i id="statu_user_email">请填写您常用的邮箱地址以便于忘记密码时召回密码</i>
	</div>
	
	<div class="field inline">
		<label for="mobilePhone">手机号码</label>
		<input id="mobilePhone" name="mobilePhone" placeholder="请输入您的手机号码" required="required" size="30" type="text" value="">
		<i id="statu_user_mobilePhone"></i>
	</div>
	
	<div class="field">
		<label for="accountNumber">公众号</label>
		<input id="accountNumber" name="accountNumber" placeholder="请输入在微信公众平台申请的公众号" size="30" type="text">
		<i id="statu_user_name">可不填</i>
	</div>
	
	<div class="field inline">
	<label>
	&nbsp;&nbsp;&nbsp;
	</label>
	<input  class="btn_login" id="submit-btn" name="commit" type="submit" value="注 册">
	&nbsp;&nbsp;&nbsp;
	<a href="loginController.do?goLogin">已有帐号点此登录</a>
	</div>
	</form>
</div>
<script type="text/javascript">

	function checkForm(){

		var loginName = $('#loginName').val();
		//var patrn=/^[a-zA-Z]{1,30}$/;
		var patrn=/^[0-9A-Za-z_]{6,30}$/
		if (!patrn.exec(loginName)){
			
			alert("用户名格式错误!只能输入6-30个以字母开头的字串");
			return false;
		}

		var ligonPass = $('#ligonPass').val();
		var patrn=/^(\w){6,20}$/; 
		if (!patrn.exec(ligonPass)){
			
			alert("密码格式错误!只能输入6-20个字母、数字、下划线");
			return false;
		}
		
		var confirmPass = $('#confirmPass').val();
		if(confirmPass!=ligonPass){
			
			alert("确认密码不一致");
			return false;
		}
		
		var email = $('#email').val();
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(!myreg.test(email)){
			
			alert("请输入有效的Email");
			return false;
		}
		
		var mobilePhone = $('#mobilePhone').val();
		var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){10,12})+$/; 
		if (!patrn.exec(mobilePhone)){
			
			alert("请输入有效的手机号码");
			return false;
		}
		
		//检测用户名是否已注册
		$.ajax({
			url : "registerController.do?checkRegisterName",
			method : "POST",
			dataType : "JSON",
			data : {
				"loginName" : loginName
			},
			success : function(data) {
				alert(data.msg);
				if(data.msg =="true" ){
					alert("该用户名已经被注册，请重新输入");
					return false;
				}
			}
		});
	}
</script>
<script type="text/javascript">
	$("#loginName").blur(function(){
		 
		//检测用户名是否已注册
		$.ajax({
			url : "registerController.do?checkRegisterName",
			method : "POST",
			dataType : "JSON",
			data : {
				"loginName" : $("#loginName").val()
			},
			success : function(data) {
				
				alert(data.msg);
				
			}
		});
	});
</script>
	<%-- 
	<div align="center"><p>&nbsp;</p><p>&nbsp;</p>
	<p>
		<a href="" style="color: black;text-decoration: none;">联系我们 </a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="" style="color: black;text-decoration: none;">产品介绍</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="" style="color: black;text-decoration: none;">申请入驻</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="" style="color: black;text-decoration: none;">帮助中心 </a>
	</p>
	<p style="font-size: 10px;color: #666666;"><br>Copyright©杰微 粤ICP备13021836号-1</p>
	</div>
	--%>
</DIV>

</body>
</html>