<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>门店图片</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinLocationphotoController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinLocationphotoPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinLocationphotoPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							图片地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="photoUrl" name="photoUrl" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图片地址</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							门店ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="locationId" name="locationId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">门店ID</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/business/weixinLocationphoto.js"></script>		