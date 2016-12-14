<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>订单发货</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinOrderController.do?doDelivery" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinOrderPage.id }">
					
		<table style="width: 700px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr height="35px">
						<td align="right" width="100">
							<label class="Validform_label">
								订单编号:
							</label>
						</td>
						<td class="value">
						     	${weixinOrderPage.orderNo}
						</td>
						<td align="right">
							<label class="Validform_label">
								支付日期:
							</label>
						</td>
						<td class="value">
						     	${weixinOrderPage.checkDate}
						</td>
					</tr>

					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								收货人:
							</label>
						</td>
						<td class="value">
						     	${weixinOrderPage.deliveryName}
						</td>
						<td align="right">
							<label class="Validform_label">
								收货电话:
							</label>
						</td>
						<td class="value">
						     	${weixinOrderPage.deliveryPhone}
						</td>
					</tr>
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								收货地区:
							</label>
						</td>
						<td class="value">
						     	${weixinOrderPage.province}${weixinOrderPage.city}${weixinOrderPage.district}
						</td>
						<td align="right">
							<label class="Validform_label">
								街道地址:
							</label>
						</td>
						<td class="value">
						    ${weixinOrderPage.address}
						</td>
					</tr>
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								邮编:
							</label>
						</td>
						<td class="value">
						     	${weixinOrderPage.postcode}
						</td>
						<td align="right">
							<label class="Validform_label">
								买家留言:
							</label>
						</td>
						<td class="value">
						     	 ${weixinOrderPage.leaveWord}
						</td>
					</tr>
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								快递单号:
							</label>
						</td>
						<td class="value">
						     	 <input id="expressNum" name="expressNum" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinOrderPage.expressNum}' datatype="*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">快递单号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								快递公司:
							</label>
						</td>
						<td class="value">
						     	 <input id="expressCompany" name="expressCompany" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinOrderPage.expressCompany}' datatype="*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">快递公司</label>
						</td>
					</tr>
					<c:if test="${not empty weixinOrderPage.expressNum}">
					<tr height="35px">
						
						<td class="value" colspan="4">
						   (该订单已经发货，允许修改发货信息 )
						</td>
					</tr>
					</c:if>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/shop/weixinOrder.js"></script>		