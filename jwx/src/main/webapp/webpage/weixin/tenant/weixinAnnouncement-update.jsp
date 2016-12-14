<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>系统公告</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  
  <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
  <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
  
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinAnnouncementController.do?doUpdate" tiptype="1">
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
									                 value='${weixinAnnouncementPage.title}'>
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
							<textarea name="summary" id="summary" style="width:450px;height:50px">${weixinAnnouncementPage.summary}</textarea>
	
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
								<textarea name="notes" id="notes" style="width:450px;height:200px">${weixinAnnouncementPage.notes}</textarea>
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
									<option value="1" <c:if test="${weixinAnnouncementPage.type == '1'}">selected="selected"</c:if> >系统公告</option>
									<option value="2" <c:if test="${weixinAnnouncementPage.type == '2'}">selected="selected"</c:if> >新功能推荐</option>
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
									<option value="1" <c:if test='${weixinAnnouncementPage.status == 1}'>selected="selected"</c:if> >公布</option>
									<option value="0" <c:if test='${weixinAnnouncementPage.status == 0}'>selected="selected"</c:if> >待发</option>
								</select>
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">状态</label>
							</td>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/tenant/weixinAnnouncement.js"></script>		