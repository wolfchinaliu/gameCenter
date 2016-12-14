<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>系统公告</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css" />
  
  <!--UEditor-->
  <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
  <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
  
  <!--fileupload-->
  <script type="text/javascript" src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/load-image.min.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-process.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-image.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.iframe-transport.js"></script>
  
  <script type="text/javascript">

  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinAnnouncementController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinAnnouncementPage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinAnnouncementPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinAnnouncementPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 450px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							摘要:
						</label>
					</td>
					<td class="value">
					     		               
							<textarea name="summary" id="summary" style="width:450px;height:50px"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">摘要</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							详细内容:
						</label>
					</td>
					<td class="value">
							<textarea name="notes" id="notes" style="width:450px;height:200px"></textarea>
							<script type="text/javascript">
								var editor = UE.getEditor('notes');
							</script>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							公告类型:
						</label>
					</td>
					<td class="value">
					     	 
							<select id="type" name="type">
								<option value="1">系统公告</option>
								<option value="2">新功能推荐</option>
							</select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公告类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							公布状态:
						</label>
					</td>
					<td class="value">
							<select id="status" name="status">
								<option value="1">公布</option>
								<option value="0">待发</option>
							</select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/tenant/weixinAnnouncement.js"></script>		