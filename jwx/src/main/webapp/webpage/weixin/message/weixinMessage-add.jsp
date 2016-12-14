<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>单发消息</title>
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
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinMessageController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinMessagePage.id }">
		<table style="width: 700px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="20%" nowrap>
						<label class="Validform_label">
							消息内容:
						</label>
					</td>
					<td class="value" width="80%">
						  	 <textarea name="note" id="note" style="width:350px;height:300px"></textarea>
							
						  	 <span class="Validform_checktip">请输入群发消息内容</span>
							<label class="Validform_label" style="display: none;">消息内容</label>
						</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							发送对象:
						</label>
					</td>
					<td class="value">
					     	<input id="receiveUserId" name="receiveUserId" type="hidden" style="width: 150px" class="inputxt" >
					     	<input name="nickName" class="inputxt" value="${nickName}" id="nickName" readonly="readonly" datatype="*" /> 
					     	
					     	<t:choose hiddenName="receiveUserId" hiddenid="openId" url="weixinMessageController.do?members" name="memberList" icon="icon-search" title="用户列表" textname="nickName" isclear="true"></t:choose>
							<span class="Validform_checktip">请选择发送对象,用户可多选</span>
							
							<label class="Validform_label" style="display: none;">消息接收用户ID</label>
						</td>
				</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/message/weixinMessage.js"></script>		