<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微信WIFI</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
 
 <c:if test="${isShowpoint == '0'}">
<div style="border: solid #f7cc8f 1px;background: #FFEFCE;margin:1px 0px 1px 0px;" id="point_title">
 	<p style="padding-left: 15px;">提示：微信WIFI二维码图片包括普通二维码和桌贴两种样式。将WIFI二维码图片设在线下门店里，可供用户扫码上网。
</div>
</c:if>

  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinBizwifiController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinBizwifiPage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinBizwifiPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinBizwifiPage.createDate }">
					<input id="accountid" name="accountid" type="hidden" value="${weixinBizwifiPage.accountid }">
		<table style="width: 800px;" cellpadding="0" cellspacing="1" class="formtable">

					<tr>
						<td align="right" width="100px">
							<label class="Validform_label">
								适用门店:
							</label>
						</td>
						<td class="value">
						     	${weixinBizwifiPage.weixinLocationEntity.businessName}
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								无线ssid:
							</label>
						</td>
						<td class="value">
								${weixinBizwifiPage.ssid}
						     	 
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								无线密码:
							</label>
						</td>
						<td class="value">
						     	${weixinBizwifiPage.password}
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								mac地址:
							</label>
						</td>
						<td class="value">
						     	${weixinBizwifiPage.bssid}
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								二维码:
							</label>
						</td>
						<td class="value">
						     <img alt="" src="${weixinBizwifiPage.qrcodeImg0}" width="150px" height="150px">
						     
						     <span class="Validform_checktip"><a href="${weixinBizwifiPage.qrcodeImg0}" target="_blank">查看原图</a></span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								桌贴二维码:
							</label>
						</td>
						<td class="value">
								<img alt="" src="${weixinBizwifiPage.qrcodeImg1}" width="150px" height="150px">
						     	
						     	<span class="Validform_checktip"><a href="${weixinBizwifiPage.qrcodeImg1}" target="_blank">查看原图</a></span>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/business/weixinBizwifi.js"></script>		