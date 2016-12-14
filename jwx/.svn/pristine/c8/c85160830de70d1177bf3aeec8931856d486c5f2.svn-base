<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微官网信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css" />

 </head>
 <body>

  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinCmsSiteController.do?doUpdateTemplate" tiptype="1">
		<input id="id" name="id" type="hidden" value="${weixinCmsSitePage.id }">
					
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				
				<tr>

					<td class="value">
					
					<table>
						<tr>
							<c:forEach items="${weixinCmsStyleList}" var="weixinCmsStyle">
								<td align="center" style="background: white;">
								
									<div style="width:180px;height:230px;border:#CCCCCC solid 1px;background: white;margin-right: 10px; margin-top:10px;font-size: 12px;font-weight: 700;">
									<img src="${weixinCmsStyle.reviewImgUrl}" style="width:180px;height: 230px;">
									${weixinCmsStyle.templateName}
									</div>
									<div style="padding-top: 15px;">
									<input  type="radio" id="siteTemplateStyle" name="siteTemplateStyle" value="${weixinCmsStyle.id}" <c:if test='${weixinCmsSitePage.siteTemplateStyle eq weixinCmsStyle.id}'>checked="checked"</c:if>>
									</div>
								</td>
							</c:forEach>
						</tr>
						
					</table>
					</td>
				</tr>
				
			</table>
		</t:formvalid>

 </body>