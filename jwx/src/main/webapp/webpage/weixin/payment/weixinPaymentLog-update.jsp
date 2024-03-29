<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>支付记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinPaymentLogController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinPaymentLogPage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinPaymentLogPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinPaymentLogPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单号:
							</label>
						</td>
						<td class="value">
						     	 <input id="orderId" name="orderId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinPaymentLogPage.orderId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								支付类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="payType" name="payType" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinPaymentLogPage.payType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付类型</label>
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
									               
									                 value='${weixinPaymentLogPage.accountid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								支付金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinPaymentLogPage.amount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								微信订单号:
							</label>
						</td>
						<td class="value">
						     	 <input id="transactionId" name="transactionId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinPaymentLogPage.transactionId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">微信订单号</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/payment/weixinPaymentLog.js"></script>		