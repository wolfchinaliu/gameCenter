<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>个人收藏链接</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tFavoLinkController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${tFavoLinkPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${tFavoLinkPage.userId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								链接:
							</label>
						</td>
						<td class="value">
						     	 <input id="link" name="link" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${tFavoLinkPage.link}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">链接</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="createTime" name="createTime" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${tFavoLinkPage.createTime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建时间</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/tenant/tFavoLink.js"></script>		