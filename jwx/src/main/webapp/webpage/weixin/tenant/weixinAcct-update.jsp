<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商户管理表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinAcctController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinAcctPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinAcctPage.createDate }">
					<input id="endDate" name="endDate" type="hidden" value="${weixinAcctPage.endDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								商户名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="acctName" name="acctName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAcctPage.acctName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商户名称</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="mobilePhone" name="mobilePhone" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAcctPage.mobilePhone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
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
									               
									                 value='${weixinAcctPage.email}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								QQ:
							</label>
						</td>
						<td class="value">
						     	 <input id="qqNumber" name="qqNumber" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAcctPage.qqNumber}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">QQ</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								群发次数:
							</label>
						</td>
						<td class="value">
						     	 <input id="smsnum" name="smsnum" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAcctPage.smsnum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">群发次数</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								图文次数:
							</label>
						</td>
						<td class="value">
						     	 <input id="newsnum" name="newsnum" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAcctPage.newsnum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图文次数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								请求次数:
							</label>
						</td>
						<td class="value">
						     	 <input id="requestnum" name="requestnum" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAcctPage.requestnum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请求次数</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								员工账号个数:
							</label>
						</td>
						<td class="value">
						     	 <input id="usernum" name="usernum" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAcctPage.usernum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">员工账号个数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众号个数:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountnum" name="accountnum" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAcctPage.accountnum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众号个数</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								域名地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="domainurl" name="domainurl" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinAcctPage.domainurl}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">域名地址</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/tenant/weixinAcct.js"></script>		