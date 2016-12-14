<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>积分帐户</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinCoinBalanceController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinCoinBalancePage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinCoinBalancePage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								余额:
							</label>
						</td>
						<td class="value">
						     	 <input id="balance" name="balance" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinBalancePage.balance}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">余额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="openid" name="openid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinBalancePage.openid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								积分类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="coinType" name="coinType" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinBalancePage.coinType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">积分类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众号:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountid" name="accountid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinBalancePage.accountid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众号</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/member/weixinCoinBalance.js"></script>		