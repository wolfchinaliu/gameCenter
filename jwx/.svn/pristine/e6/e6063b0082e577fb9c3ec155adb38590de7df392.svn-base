<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>关注用户管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinMemberController.do?doUpdate" tiptype="1">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户的标识:
							</label>
						</td>
						<td class="value">
						     	 <input id="id" name="id" type="text" style="width: 150px" class="inputxt"  
									               datatype="*"
									                 value='${weixinMemberPage.id}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户的标识</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								昵称:
							</label>
						</td>
						<td class="value">
						     	 <input id="nickName" name="nickName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMemberPage.nickName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">昵称</label>
						</td>
						
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
						     	 <input id="sex" name="sex" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMemberPage.sex}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别1男2女0未知</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								城市:
							</label>
						</td>
						<td class="value">
						     	 <input id="city" name="city" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMemberPage.city}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">city</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								省份:
							</label>
						</td>
						<td class="value">
						     	 <input id="province" name="province" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMemberPage.province}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户所在省份</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								国家:
							</label>
						</td>
						<td class="value">
						     	 <input id="country" name="country" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMemberPage.country}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户所在国家</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								语言:
							</label>
						</td>
						<td class="value">
						     	 <input id="language" name="language" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMemberPage.language}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户的语言</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								头像:
							</label>
						</td>
						<td class="value">
						     	 <input id="headImgUrl" name="headImgUrl" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMemberPage.headImgUrl}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户头像</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								关注时间:
							</label>
						</td>
						<td class="value">
									  <input id="subscribeTime" name="subscribeTime" type="text" style="width: 150px" 
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${weixinMemberPage.subscribeTime}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关注时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								unionid:
							</label>
						</td>
						<td class="value">
						     	 <input id="unionid" name="unionid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMemberPage.unionid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">unionid</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否订阅:
							</label>
						</td>
						<td class="value">
						     	 <input id="subscribe" name="subscribe" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMemberPage.subscribe}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否订阅</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								所属公众号:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountId" name="accountId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMemberPage.accountId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属公众号</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/member/weixinMember.js"></script>		