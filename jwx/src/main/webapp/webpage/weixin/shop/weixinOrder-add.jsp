<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinOrderController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinOrderPage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinOrderPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinOrderPage.createDate }">
					<input id="checkName" name="checkName" type="hidden" value="${weixinOrderPage.checkName }">
					<input id="checkDate" name="checkDate" type="hidden" value="${weixinOrderPage.checkDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属公众号:
						</label>
					</td>
					<td class="value">
					     	 <input id="accountid" name="accountid" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属公众号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							订单金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderAmount" name="orderAmount" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单金额</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							实收金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="payAmount" name="payAmount" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实收金额</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							支付状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付状态</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发货状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="deliverStatus" name="deliverStatus" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发货状态</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							运费:
						</label>
					</td>
					<td class="value">
					     	 <input id="freight" name="freight" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运费</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							粉丝ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="openId" name="openId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">粉丝ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							支付方式:
						</label>
					</td>
					<td class="value">
					     	 <input id="payType" name="payType" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付方式</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收货人:
						</label>
					</td>
					<td class="value">
					     	 <input id="deliveryName" name="deliveryName" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收货人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							收货电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="deliveryPhone" name="deliveryPhone" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收货电话</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收货省:
						</label>
					</td>
					<td class="value">
					     	 <input id="province" name="province" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收货省</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							收货市:
						</label>
					</td>
					<td class="value">
					     	 <input id="city" name="city" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收货市</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收货区:
						</label>
					</td>
					<td class="value">
					     	 <input id="district" name="district" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收货区</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							邮编:
						</label>
					</td>
					<td class="value">
					     	 <input id="postcode" name="postcode" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮编</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							买家留言:
						</label>
					</td>
					<td class="value">
					     	 <input id="leaveWord" name="leaveWord" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">买家留言</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/shop/weixinOrder.js"></script>		