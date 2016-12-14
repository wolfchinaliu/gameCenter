<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>支付方式配置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
 
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinPaymentConController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinPaymentConPage.id }">
					<input id="payType" name="payType" type="hidden" value='${weixinPaymentConPage.payType}'>
					<input id="payName" name="payName" type="hidden" value='${weixinPaymentConPage.payName}'>
					 <input id="accountid" name="accountid" type="hidden" value='${weixinPaymentConPage.accountid}'>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								支付名称:
							</label>
						</td>
						<td class="value">
						     	 ${weixinPaymentConPage.payName}
						</td>
					</tr>
						
						<%--微信支付配置项目 --%>
						<c:if test="${weixinPaymentConPage.payType eq '1'}">
							<tr>	
								<td align="right">
									<label class="Validform_label">
										公众账号APP_ID:
									</label>
								</td>
								<td class="value">
								     	 <input id="appId" name="appId" type="text" style="width: 150px" class="inputxt"  
											               
											                 value='${weixinPaymentConPage.appId}'>
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">公众账号APP_ID</label>
								</td>
							</tr>
							<%--
							<tr>
								<td align="right">
									<label class="Validform_label">
										微信支付双向认证证书:
									</label>
								</td>
								<td class="value">
								     	 <input id="certFileName" name="certFileName" type="text" style="width: 150px" class="inputxt"  
											               
											                 value='${weixinPaymentConPage.certFileName}'>
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">微信支付双向认证证书</label>
								</td>
							</tr>
							 --%>
							 <tr>
								<td align="right">
									<label class="Validform_label">
										财付通商户号(PARTNER):
									</label>
								</td>
								<td class="value">
								     	 <input id="partnerId" name="partnerId" type="text" style="width: 150px" class="inputxt"  
											               
											                 value='${weixinPaymentConPage.partnerId}'>
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">财付通商户号</label>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										财付通密钥(PARTNER_KEY):
									</label>
								</td>
								<td class="value">
								     	 <input id="partnerKey" name="partnerKey" type="text" style="width: 150px" class="inputxt"  
											               
											                 value='${weixinPaymentConPage.partnerKey}'>
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">财付通密钥</label>
								</td>
							</tr>
							
						</c:if>
						
						<%--支付宝配置项目 --%>
						<c:if test="${weixinPaymentConPage.payType eq '2'}">
							<tr>
								<td align="right">
									<label class="Validform_label">
										合作身份者ID:
									</label>
								</td>
								<td class="value">
								     	 <input id="partner" name="partner" type="text" style="width: 150px" class="inputxt"  
											               
											                 value='${weixinPaymentConPage.partner}'>
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">合作身份者ID</label>
								</td>
							</tr>
							<tr>
								<td align="right">
									<label class="Validform_label">
										卖家支付宝帐户:
									</label>
								</td>
								<td class="value">
								     	 <input id="sellerEmail" name="sellerEmail" type="text" style="width: 150px" class="inputxt"  
											               
											                 value='${weixinPaymentConPage.sellerEmail}'>
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">卖家支付宝帐户</label>
								</td>
								<td align="right">
									<label class="Validform_label">
										支付宝key:
									</label>
								</td>
								<td class="value">
								     	 <input id="sellerKey" name="sellerKey" type="text" style="width: 150px" class="inputxt"  
											               
											                 value='${weixinPaymentConPage.sellerKey}'>
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">支付宝key</label>
								</td>
							</tr>
						</c:if>
						
						<%--财付通配置项目 --%>
						<c:if test="${weixinPaymentConPage.payType eq '3'}">
							<tr>
								<td align="right">
									<label class="Validform_label">
										财付通商户号:
									</label>
								</td>
								<td class="value">
								     	 <input id="bargainorId" name="bargainorId" type="text" style="width: 150px" class="inputxt"  
											               
											                 value='${weixinPaymentConPage.bargainorId}'>
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">财付通商户号</label>
								</td>
								<td align="right">
									<label class="Validform_label">
										财付通密钥:
									</label>
								</td>
								<td class="value">
								     	 <input id="bargainorKey" name="bargainorKey" type="text" style="width: 150px" class="inputxt"  
											               
											                 value='${weixinPaymentConPage.bargainorKey}'>
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">财付通密钥</label>
								</td>
							</tr>
						</c:if>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/payment/weixinPaymentCon.js"></script>		