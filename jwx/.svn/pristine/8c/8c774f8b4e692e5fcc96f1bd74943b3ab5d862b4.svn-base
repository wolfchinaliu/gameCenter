<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会员信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinMembershipController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinMembershipPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinMembershipPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众号:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountid" name="accountid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.accountid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								手机号码:
							</label>
						</td>
						<td class="value">
						     	 <input id="mobilePhone" name="mobilePhone" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.mobilePhone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">手机号码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
						     	 <input id="fullName" name="fullName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.fullName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								用户名:
							</label>
						</td>
						<td class="value">
						     	 <input id="loginName" name="loginName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.loginName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								密码:
							</label>
						</td>
						<td class="value">
						     	 <input id="loginPassword" name="loginPassword" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.loginPassword}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">密码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
						     	 <input id="sex" name="sex" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.sex}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								邮箱:
							</label>
						</td>
						<td class="value">
						     	 <input id="email" name="email" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.email}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								身份证:
							</label>
						</td>
						<td class="value">
						     	 <input id="cardid" name="cardid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.cardid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">身份证</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								会员类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="cardType" name="cardType" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.cardType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">会员类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.address}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.status}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								积分:
							</label>
						</td>
						<td class="value">
						     	 <input id="coin" name="coin" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.coin}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">积分</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								加入时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="joinTime" name="joinTime" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.joinTime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">加入时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.phone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								存款:
							</label>
						</td>
						<td class="value">
						     	 <input id="balance" name="balance" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.balance}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">存款</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinMembershipPage.description}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/member/weixinMembership.js"></script>		