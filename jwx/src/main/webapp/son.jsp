<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>子窗口方法</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  	
  	    	function bingPhoneNumber(){
				parent.bindPhoneNumber();
			}
			function recharge(){
				parent.drawFlow();
			}
			var code = '<%=request.getParameter("code")%>' 
			function test(){
				if(code==0){
					recharge();
				}else{
					bingPhoneNumber();
				}
			}
			window.onload = test();
  </script>
  <body>
    	
  </body>
</html>
