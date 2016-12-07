<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	 <base href="<%=basePath%>">
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	    <meta name="format-detection" content="telphone=no, email=no"/>
	    <title>注册领取流量</title>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basegz.css">
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
	</head>
	<body>
		<div class="container">
			<header class="Invitecard-header"> 
				<p class="Invitecard-logo">
					<a href="javascript:;"><img src="${pageContext.request.contextPath}/img/logo.png" title="logo" alt="logo"/></a>
				</p>
			</header>
			<div class="form-box">
				<form action="#" method="post">
					<div class="form-item">
						<i class="form-icon form-icon-1"></i>
						<p>
							<input id="lab2" type="text" name="lab2" placeholder="手机后四位" value="" class="input-text" />
						</p>
					</div>
					
					<div class="form-item-bootom form-item">
						<i class="form-icon form-icon-2"></i>
						<p>
							<input id="lab1" type="text" name="lab1" placeholder="姓名" value="" class="input-text" style="width:3rem;"/>
							<!--<input type="button" value="获取验证码" class="from-btn from-btn-1" id="j-btn1" />-->
						</p>
					</div>
					
					<div class="tips">填写充值用户信息</div>
					
					<div class="submit_btn">
						<input type="reset" value="重置" class="from-btn1 from-btn"/>
						<input type="button" value="领取" class="from-btn" id="j-btn2" />
					</div>
				</form>
			</div>
			
			<footer class="footer">
			<p>长按/扫描二维码进行注册</p>
				<img src="${pageContext.request.contextPath}/img/resCode.png" alt="qrcode" title="qrcode"/>
			</footer>
	
			<!--消息窗-->
		</div>		
		
	</body>

	<!--
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/popup.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-migrate-1.4.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jbox/jquery.jBox-2.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jbox/jquery.jBox-zh-CN.js"></script>
  	<link id="skin" rel="stylesheet" href="${pageContext.request.contextPath}/css/jbox/jbox.css"/>
  
    <script type="text/javascript">
	  	var trim = function (s) {
			  return s.replace(/(^\s*)|(\s*$)/g, "");
			}
		$("#j-btn2").click(function(){
			 if(trim(document.getElementById("lab1").value)==""){
				 tipMessage('请填写姓名');
				 return false;
			  }
			  if(trim(document.getElementById("lab2").value)==""){
				  tipMessage('请填写手机号后四位');
				 return false;
			  }
			  if(trim(document.getElementById("lab2").value).length !=4){
				  tipMessage('请正确填写手机号后四位');
				 return false;
			  }
				var lab1 = $("#lab1").val();
			  	var lab2 = $("#lab2").val();
			  	
		jQuery.ajax({
				url:'${pageContext.request.contextPath}/clientFolw/flowOperator.htm',
				data:{'lab1':lab1,'lab2':lab2},
				dataType:'json',
			 	type:'post',
			 	contentType: "application/x-www-form-urlencoded;charset=utf-8",
			 	success:function(data){
		    	 	var url = "http://"+data.url+"/jwx/son.jsp?code=0";
		    	 	var alertString = "";
		    	 	if(data.code==0 || data.code==1){
		    	 		alertString = "你已领取"+data.flowValue+"M流量";
		    	 		
		    	 	}else{
		    	 		alertString = "领取失败："+data.message;
		    	 	}
		    	 	popup("温馨提示",alertString,url,"关闭","去提取");
		        }
			});
		});
	</script> 
</html>
