<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>群发消息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>

  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css" />

  <!--fileupload-->
  <script type="text/javascript" src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/load-image.min.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-process.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-image.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.iframe-transport.js"></script>
  <!--UEditor-->
  <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
  <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript">
  	function showSelect(){
  		
  		var send_type = document.getElementById("sendType").value;
  		if(send_type==0){
  			
  			document.getElementById("div1").style.display="none";
  		}
		if(send_type==1){
  			
			document.getElementById("div1").style.display="block";
  		}
  	}
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinArticleController.do?toSeadMessageByGroup" tiptype="1">
					<input id="id" name="id" type="hidden" value="${newsTemplate.id }">
		<table style="width:700px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="20%" nowrap>
						<label class="Validform_label">
							群发对象:
						</label>
					</td>
					<td class="value" width="80%">
						<div style="width:160px; height:auto; float:left; display:inline">
						  	 <select id="sendType" name="sendType" onchange="showSelect()">
						  	 	<option value="0">全部</option>
						  	 	<option value="1">按分组选择</option>
						  	 </select>
						 </div>  	 
						 <div id="div1" style="display: none;width: 50;height:auto; float:left;">
						  	 <select id="weixinGroup" name="weixinGroup" >
						  	 	<c:forEach items="${weixinGroupList }" var="group">
						  	 		<option value="${group.groupId}">${group.groupName}</option>
						  	 	</c:forEach>
						  	 </select>
						 </div>
					</td>
				</tr>
				
				<input type="submit" value="发送"/>
			</table>
		</t:formvalid>
 </body>	