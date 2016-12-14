<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>素材管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinSourceController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinSourcePage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinSourcePage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinSourcePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${weixinSourcePage.updateName }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								传至微信服务器媒体文件ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="mediaId" name="mediaId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinSourcePage.mediaId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">传至微信服务器媒体文件ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								所属公众号:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountid" name="accountid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinSourcePage.accountid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属公众号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								素材类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="sourceType" type="list"
										typeGroupCode="" defaultVal="${weixinSourcePage.sourceType}" hasLabel="false"  title="素材类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">素材类型</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/source/weixinSource.js"></script>		