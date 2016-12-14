<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
	    <meta name="format-detection" content="telphone=no, email=no"/>
		<title>关注${accountName}</title>
		<script src="plug-in/liuliangbao/20160701/js/follow/common.js"></script>
		<link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/20160701/css/follow/normalize.css">  
	    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/20160701/css/follow/index.css">
	</head>
	<body>
		
		<div class="container">
			<div class="follow">
				<h1 class="follow-logo">
					<img src="${logo}" alt="" />
				</h1>
				<div class="follow-text">
					<p class="follow-text-center">
						欢迎您来到${accountName}
					</p>
					<p class="follow-text-tip">惊喜等待您的到来！</p>
				</div>
		     	<div class="follow-code">
					<img src="${url}" alt="" >
				</div>  
			<!--  	<div class="lh-erweima">
                  <img src="${url}" alt="" />
      		    </div> -->
				<div class="follow-tips">
					长按二维码、识别、进入公众号
					<div class="follow-tips-left"></div>
					<div class="follow-tips-right"></div>
				</div>
			</div>
		</div>
		
	</body>
</html>
