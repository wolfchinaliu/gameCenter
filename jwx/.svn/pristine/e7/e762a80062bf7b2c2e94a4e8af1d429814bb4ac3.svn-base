<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinBizwifiController.do?doAdd" tiptype="1" beforeSubmit="toValidateForm">
					<input id="id" name="id" type="hidden" value="${weixinBizwifiPage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinBizwifiPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinBizwifiPage.createDate }">
					<input id="accountid" name="accountid" type="hidden" value="${weixinBizwifiPage.accountid }">
					<input name="weixinLocationEntity.id" type="hidden" id="weixinLocationEntity.id" value="${weixinBizwifiPage.weixinLocationEntity.id }"> 
		<table style="width: 700px;" cellpadding="0" cellspacing="1" class="formtable">
				
				<tr>
					<td align="right" width="100">
						<label class="Validform_label">
							适用门店:
						</label>
					</td>
					<td class="value">
					     	
					     	<input name="locationIdList" type="hidden" value="${poiId}" id="locationIdList" datatype="*"> 
							<input name="businessName" class="inputxt" value="${businessName }" id="businessName" readonly="readonly" datatype="*" /> 
							<t:choose hiddenName="locationIdList" hiddenid="poiId" url="weixinLocationController.do?locations" name="locationList" icon="icon-search" title="门店列表" textname="businessName" isclear="true"></t:choose>
							<span class="Validform_checktip">请选择一个门店</span>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							无线ssid:
						</label>
					</td>
					<td class="value">
					     	 <input id="ssid" name="ssid" type="text" style="width: 150px" class="inputxt"  
								               
								               datatype="*">
							<span class="Validform_checktip">无线网络设备的ssid，不能包含中文字符，必需是“WX”开头(“WX”为大写字母)</span>
							<label class="Validform_label" style="display: none;">无线ssid</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							无线密码:
						</label>
					</td>
					<td class="value">
					     	 <input id="password" name="password" type="text" style="width: 150px" class="inputxt"  
								               
								               datatype="*">
							<span class="Validform_checktip">无线网络设备的密码，大于8个字符，不能包含中文字符</span>
							<label class="Validform_label" style="display: none;">无线密码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							mac地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="bssid" name="bssid" type="text" style="width: 150px" class="inputxt"  
								               
								               datatype="*">
							<span class="Validform_checktip">无线网络设备无线mac地址，格式冒号分隔，字符长度17个，并且字母小写，例如：00:1f:7a:ad:5c:a8</span>
							<label class="Validform_label" style="display: none;">mac地址</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
 <script src = "webpage/weixin/business/weixinBizwifi.js"></script>
 <script type="text/javascript">
 	function toValidateForm(){
 		
 		var pid = $("#locationIdList").attr("value");
 		if(pid.indexOf(",") > 0 ){
 			
 			alert("只能选择一个门店");
 			return false;
 		}
 		
 		return true;
 	}
 </script>