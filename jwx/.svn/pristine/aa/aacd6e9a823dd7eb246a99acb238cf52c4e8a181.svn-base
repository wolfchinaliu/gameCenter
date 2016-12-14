<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微信公众帐号信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinAccountController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinAccountPage.id }">
		<table style="width: 700px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="userName" name="userName" type="hidden" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAccountPage.userName}'>${weixinAccountPage.userName}
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众帐号名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众帐号名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountname" name="accountname" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accountname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众帐号名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众帐号TOKEN:
							</label>
						</td>
						<td class="value">
						     	 <input id="accounttoken" name="accounttoken" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accounttoken}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众帐号TOKEN</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众微信号:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountnumber" name="accountnumber" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accountnumber}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众微信号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众号类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="accounttype" name="accounttype" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accounttype}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众号类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								电子邮箱:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountemail" name="accountemail" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accountemail}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电子邮箱</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众帐号描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountdesc" name="accountdesc" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accountdesc}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众帐号描述</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众帐号APPID:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountappid" name="accountappid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accountappid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众帐号APPID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众帐号APPSECRET:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountappsecret" name="accountappsecret" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accountappsecret}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众帐号APPSECRET</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								URL(服务器地址)
							</label>
						</td>
						<td class="value">
							<span title="微信只能识别80端口">${webRoot }/weixin.do?api&t=${weixinAccountPage.id}</span>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								Token(令牌)
							</label>
						</td>
						<td class="value">
							<span>${weixinAccountPage.accounttoken}</span>
						</td>
					</tr>
					
					<%-- <tr>
						<td align="right">
							<label class="Validform_label">
								公众帐号ACCESS_TOKEN:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountaccesstoken" name="accountaccesstoken" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accountaccesstoken}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">ACCESS_TOKEN</label>
						</td>
					</tr> --%>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/account/weixinAccount.js"></script>		