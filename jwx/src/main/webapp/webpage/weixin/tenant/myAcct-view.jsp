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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="" tiptype="1">
					
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right" height="35px">
							<label class="Validform_label">
								商户名称:
							</label>
						</td>
						<td class="value">
						     	${weixinAcctPage.acctName}
						</td>
					<tr>
						<td align="right" height="35px">
							<label class="Validform_label">
								电话:
							</label>
						</td>
						<td class="value">
						     	${weixinAcctPage.mobilePhone}
						</td>
					</tr>
					<tr>
						<td align="right" height="35px">
							<label class="Validform_label">
								邮箱:
							</label>
						</td>
						<td class="value">
						     	${weixinAcctPage.email}
						</td>
					<tr>
						<td align="right" height="35px">
							<label class="Validform_label">
								QQ:
							</label>
						</td>
						<td class="value">
						     	 ${weixinAcctPage.qqNumber}
						</td>
					</tr>
					<tr>
						<td align="right" height="35px">
							<label class="Validform_label">
								群发次数:
							</label>
						</td>
						<td class="value">
						     	${weixinAcctPage.smsnum}
						</td>
					<tr>
						<td align="right" height="35px">
							<label class="Validform_label">
								图文次数:
							</label>
						</td>
						<td class="value">
						     	${weixinAcctPage.newsnum}
						</td>
					</tr>
					<tr>
						<td align="right" height="35px">
							<label class="Validform_label">
								请求次数:
							</label>
						</td>
						<td class="value">
						     	 ${weixinAcctPage.requestnum}
						</td>
					<tr>
						<td align="right" height="35px">
							<label class="Validform_label">
								员工账号个数:
							</label>
						</td>
						<td class="value">
						     	 ${weixinAcctPage.usernum}
						</td>
					</tr>
					<tr>
						<td align="right" height="35px">
							<label class="Validform_label">
								公众号个数:
							</label>
						</td>
						<td class="value">
						     	${weixinAcctPage.accountnum}
						</td>
					<tr>
						<td align="right" height="35px">
							<label class="Validform_label">
								域名地址:
							</label>
						</td>
						<td class="value">
						     	 ${weixinAcctPage.domainurl}
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/tenant/weixinAcct.js"></script>		