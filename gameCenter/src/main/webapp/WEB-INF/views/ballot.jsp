        <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>问卷调查页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
  </head>
  
  <body>
    	<table align="center" border="1" cellpadding="2" cellspacing="0" >
    			<tr>
    				<td>请输入手机号：</td>
    				<td><input id="phoneid" type="text" name="phone" /></td>
    			</tr>
    			<tr>
    				<td align="center" colspan="2" ><input type="button" id="pid" value="验证手机"/></td>
    			</tr>
    			<div>
	    			<tr>
	    				<td>最喜爱的直播APP</td>
	    				<td>
	    					<select id="appID" >
	    							<option>${sessionScope.appList[0]}</option>
	    							<option>${sessionScope.appList[1]}</option>
	    							<option>${sessionScope.appList[2]}</option>
	    							<option>${sessionScope.appList[3]}</option>
	    							<option>${sessionScope.appList[4]}</option>
	    							<option>${sessionScope.appList[5]}</option>
	    							<option>${sessionScope.appList[6]}</option>
	    							<option>${sessionScope.appList[7]}</option>
	    							<option>${sessionScope.appList[8]}</option>
	    							<option>${sessionScope.appList[9]}</option>
	    					</select>
	    				</td>
	    			</tr>
	    			<tr>
	    					<td align="center" colspan="2" ><input id="bt" type="button" value="提交" disabled="disabled" /></td>
	    			</tr>
    	</table>
  </body>
  <script type="text/javascript">
	  	var trim = function (s) {
			  return s.replace(/(^\s*)|(\s*$)/g, "");
			}
		$("#pid").click(function(){
			 if(trim(document.getElementById("phoneid").value)==""){
			    alert('手机号码必填');
				 return false;
			  }
				var phone = $("#phoneid").val();
			  	
		jQuery.ajax({
				url:'${pageContext.request.contextPath}/ballot/selectPhone.htm',
				data:{'phone':phone},
				dataType:'json',
			 	type:'post',
			 	contentType: "application/x-www-form-urlencoded;charset=utf-8",
			 	success:function(data){
		    	 	if(data.message=="success"){
		    	 		$("#bt").removeAttr("disabled");
		    	 	}else if(data.message=="error"){
		    	 		alert("该号码已经参与过");
		    	 	}else{
		    	 		alert(data.message);
		    	 	}
		        }
			});
		});
		$("#bt").click(function(){
					var app = $("#appID").val();
				  	
			jQuery.ajax({
					url:'${pageContext.request.contextPath}/ballot/app.htm',
					data:{'app':app},
					dataType:'json',
				 	type:'post',
				 	contentType: "application/x-www-form-urlencoded;charset=utf-8"
				});
			});
	</script> 
</html>
