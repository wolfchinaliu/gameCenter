<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图片上传测试</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<!-- 引入css文件，不限顺序 -->
			<script type="text/javascript" src="${pageContext.request.contextPath }/easyui\jquery\jquery-1.3.2.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath }/easyui\jquery\ajaxfileupload.js"></script>
			
  			<script type="text/javascript">
		        $(function () {
		            $(":button").click(function () {
		                ajaxFileUpload();
		            })
		        })
		        function strToJson(str){ 
				var json = eval('(' + str + ')'); 
				return json; 
				} 
		        function ajaxFileUpload() {
		            $.ajaxFileUpload
		            (
		                {
		                    url: 'weixinSourceController.do?uploadthumbMike', //用于文件上传的服务器端请求地址
		                    secureuri: false, //是否需要安全协议，一般设置为false
		                    fileElementId: 'file1', //文件上传域的ID
		                    dataType: 'AjaxJson', //返回值类型 一般设置为json
		                    success: function (data, status, e)  //服务器成功响应处理函数
		                    {                    	
		                    	data = strToJson(data);
		                        $("#img1").attr("src", data.attributes.imgUrl);
		                    } ,
		                    error: function (data, status, e)//服务器响应失败处理函数
		                    {
		                        alert("上传图片失败！");
		                    } 
		                }
		            )
		            return false;
		        }
		    </script>
  </head>
			<body>
			    <p><input type="file" id="file1" name="file" /></p>
			    <input type="button" value="上传" />
			    <p><img id="img1" alt="图片为上传" src="" /></p>
			</body>
			
</html>
