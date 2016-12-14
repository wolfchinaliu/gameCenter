<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>收货地址管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>

  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinShopAddressController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinShopAddressPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinShopAddressPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								粉丝ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="openId" name="openId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinShopAddressPage.openId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">粉丝ID</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								收货人:
							</label>
						</td>
						<td class="value">
						     	 <input id="deliveryName" name="deliveryName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinShopAddressPage.deliveryName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收货人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								收货电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="deliveryPhone" name="deliveryPhone" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinShopAddressPage.deliveryPhone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收货电话</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								收货省:
							</label>
						</td>
						<td class="value">
						     	 <input id="province" name="province" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinShopAddressPage.province}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收货省</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								收货市:
							</label>
						</td>
						<td class="value">
						     	 <input id="city" name="city" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinShopAddressPage.city}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收货市</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								收货区:
							</label>
						</td>
						<td class="value">
						     	 <input id="district" name="district" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinShopAddressPage.district}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收货区</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								详细地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinShopAddressPage.address}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">详细地址</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								邮编:
							</label>
						</td>
						<td class="value">
						     	 <input id="postcode" name="postcode" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinShopAddressPage.postcode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮编</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								默认:
							</label>
						</td>
						<td class="value">
						     	 <input id="isDefault" name="isDefault" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinShopAddressPage.isDefault}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">默认</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinShopAddressPage.userId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户ID</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/shop/weixinShopAddress.js"></script>		