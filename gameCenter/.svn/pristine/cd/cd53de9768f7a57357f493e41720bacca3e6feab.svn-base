<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>投票页面编辑页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 引入css文件，不限顺序 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }\themes/default/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath }\themes/icon.css" type="text/css"></link>
  
  	<!-- 引入js文件，有顺序限制 -->
    <script type="text/javascript" src="${pageContext.request.contextPath }\js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }\js/jquery.easyui.min.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath }\js/easyui-lang-zh_CN.js"></script>
	   
  </head>
  
  <body>   
    	<form action="${pageContext.request.contextPath }\ballot\ballotJsp.htm" method="post" >
    		<table align="center" border="1" cellpadding="2" cellspacing="0">
    				<tr>
    					<th align="center" colspan="2" >问卷调查表编辑页面</th>
    				</tr>
    				<tr>
    					<td>问卷调查名称</td>
    					<td><input type="text" name="title" /></td>
    				</tr>
    				<tr>
    					<td>问卷调查开始时间</td>
    					<td><input id="startTime" type="text" name="startTime" /></td>
    				</tr>
    				<tr>
    					<td>问卷调查结束时间</td>
    					<td><input id="endTime" type="text" name="endTime" /></td>
    				</tr>
    				<tr>
    					<td colspan="2" align="center" >十款APP的名字</td>
    				</tr>
    				<tr>
    					<td><input type="text" name="app1" /></td>
    					<td><input type="text" name="app2" /></td>
    				</tr>
    				<tr>
    					<td><input type="text" name="app3" /></td>
    					<td><input type="text" name="app4" /></td>
    				</tr>
    				<tr>
    					<td><input type="text" name="app5" /></td>
    					<td><input type="text" name="app6" /></td>
    				</tr>
    				<tr>
    					<td><input type="text" name="app7" /></td>
    					<td><input type="text" name="app8" /></td>
    				</tr>
    				<tr>
    					<td><input type="text" name="app9" /></td>
    					<td><input type="text" name="app10" /></td>
    				</tr>
 					<tr>
    					<td align="center" colspan="2" >
    					<input type="reset" value="重置" />
    					<input type="submit" value="提交" />
    					</td>
    				</tr>   				
    		</table>
    	</form>
  </body>
      <script type="text/javascript">
    	$('#startTime').datetimebox({       
		    editable:false,
		    required: true,    
		    showSeconds: true   
		});
		$('#endTime').datetimebox({    
		    editable:false,
		    required: true,    
		    showSeconds: true   
		});  
    
    </script>
</html>
