<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link rel="stylesheet" type="text/css" href="crm/themes/default/easyui.css">  
<link rel="stylesheet" type="text/css" href="crm/themes/mobile.css">  
<link rel="stylesheet" type="text/css" href="crm/themes/icon.css">
<link rel="stylesheet" type="text/css" href="crm/themes/color.css">
<script type="text/javascript" src="crm/jquery.min.js"></script>  
<script type="text/javascript" src="crm/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="crm/jquery.easyui.mobile.js"></script>
<style type="text/css">
   #list .header{
		font-size: 14px;
		font-weight: bold;
	}
	#list .content{
		text-overflow: ellipsis;
		overflow: hidden;
	}
	#list .image{
		width: 32px;
		height: 32px;
		border: 0;
		margin-right: 5px;
		float: left;
	}
	
	#footer{ width:100%; height:47px; border-top:solid 1px #cacaca; background-color:white;position:fixed;bottom:0; }
	.bottom1,.bottom2,.bottom3,.bottom4,.bottom5{ width:20%; height:40px; float:left; margin-top:2px;}
	#yellow{font-size:0.75em; text-align:center; display:block; margin:0 auto; color:#ff8000; }
	footer span{ font-size:0.75em; text-align:center; color:#969696; display:block; margin:0 auto; }
	.tui,.jia,.wo,.geng,.msg,.home,.msg1,.oa{ width:26px; height:25px; margin:0 auto; background-repeat:no-repeat; background-position:center; background-size:26px auto;}
	
	.msg{ background-image:url(crm/themes/icons/msg.png);}
	.tui{ background-image:url(crm/themes/icons/iconpen.png);}
	.jia{ background-image:url(crm/themes/icons/home.png);}
	.wo{ background-image:url(crm/themes/icons/iconmy.png);}
	.geng{ background-image:url(crm/themes/icons/iconduo.png);}
	
	.msg1{ background-image:url(crm/themes/icons/msg1.png);}
	.home{ background-image:url(crm/themes/icons/home.png);}
	.oa{ background-image:url(crm/themes/icons/oa.png);}
</style>