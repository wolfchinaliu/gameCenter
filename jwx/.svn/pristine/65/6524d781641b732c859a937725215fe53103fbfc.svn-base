<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<html>
<head>
<script type="text/javascript">     
	function countDown(secs,surl){     
	    
	 var jumpTo = document.getElementById('jumpTo');
	 jumpTo.innerHTML=secs;  
	 if(--secs>0){     
	     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
	     }     
	 else{       
		 window.top.location.href=surl;     
	     }     
	 }
</script>
</head>
<body>
<div style="text-align: center;vertical-align: middle;width:100%;height: 100%;padding-top:200px;font-size: 12px;">
	<p>提醒：登录超时，请重新登录!</p>
 
	<span id="jumpTo">5</span>秒后自动跳转
	<script type="text/javascript">countDown(5,'loginController.do?login');</script>
</div>
</body>
</html>

