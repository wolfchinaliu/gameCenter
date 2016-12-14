<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>优惠券发放</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
   <link rel="stylesheet" type="text/css" href="plug-in/lhgDialog/skins/default.css">
    <script type=text/javascript src="plug-in/clipboard/ZeroClipboard.js"></script>
    <script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
	<script>
        $(function(){

            $("#closeBtn").click(function(){
                frameElement.api.close();
            });
        });
    </script>
 </head>
 <body>
  <form action="weixinCardController.do?toSendCard" method="post" >
		<input id="id" name="id" type="hidden" value="${weixinCardPage.id }">
			<div>
				你可以通过以下方式投放卡券:
			</div>
			<div>
				<p><input type="radio" id="sendType" name="sendType" value="1" checked="checked">直接群发卡券(通过公众号消息，直接投放)</p>
				<%-- <p><input type="radio" id="sendType" name="sendType" value="2">嵌入图文消息(将卡券嵌入图文消息，再由公众号投放)</p>--%>
				<p><input type="radio" id="sendType" name="sendType" value="3">下载二维码 (下载卡券二维码，通过打印张贴或其他渠道发放)</p>
						    
				<p>也可通过公众号自定义菜单等方式灵活投放卡券</p>
			</div>
			<div class="ui_buttons" style="padding-left:5px;text-align: left;">
				<input type="submit" id="copyBtn" value="确定" class="ui_state_highlight">
        		<input type="button" id="closeBtn" value="取消" onclick='close();'>
        	</div>
		</form>
 </body>