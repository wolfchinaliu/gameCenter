<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp" />
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinOrderController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinOrderPage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinOrderPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinOrderPage.createDate }">
					<input id="checkName" name="checkName" type="hidden" value="${weixinOrderPage.checkName }">
					<input id="checkDate" name="checkDate" type="hidden" value="${weixinOrderPage.checkDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								订单编号:
							</label>
						</td>
						<td class="value">
						     	${weixinOrderPage.orderNo}
						</td>
						<td align="right">
							<label class="Validform_label">
								订单金额:
							</label>
						</td>
						<td class="value">
						     ${weixinOrderPage.orderAmount}(含运费：${weixinOrderPage.freight})
						</td>
					</tr>
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								实收金额:
							</label>
						</td>
						<td class="value">
						     	${weixinOrderPage.payAmount}
						</td>
						<td align="right">
							<label class="Validform_label">
								支付状态:
							</label>
						</td>
						<td class="value">
							<c:if test="${weixinOrderPage.status == 0}">未支付</c:if>
						    <c:if test="${weixinOrderPage.status == 1}">已付款</c:if>
						    <c:if test="${weixinOrderPage.status == 2}">交易成功</c:if>
						    <c:if test="${weixinOrderPage.status == 3}">退款中</c:if>
						    <c:if test="${weixinOrderPage.status == 4}">退货中</c:if>
						    <c:if test="${weixinOrderPage.status == 5}">已退款</c:if>
						    <c:if test="${weixinOrderPage.status == 9}">已取消</c:if>
						</td>
					</tr>
					
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								买家昵称:
							</label>
						</td>
						<td class="value">
						     ${weixinOrderPage.weixinMemberEntity.nickName}
						</td>
						<td align="right">
							<label class="Validform_label">
								支付方式:
							</label>
						</td>
						<td class="value">
						     	微信支付
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
								收货地址:
							</label>
						</td>
						<td class="value">
						     	${weixinOrderPage.province}${weixinOrderPage.city}${weixinOrderPage.district}
						</td>
						<td align="right">
							<label class="Validform_label">
								街道:
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
								发货状态:
							</label>
						</td>
						<td class="value">
							<c:if test="${weixinOrderPage.deliverStatus == 0}">未发货</c:if>
						    <c:if test="${weixinOrderPage.deliverStatus == 1}">已发货</c:if>
						    <c:if test="${weixinOrderPage.deliverStatus == 2}">已收货</c:if>
						  
						</td>
						<td align="right">
							<label class="Validform_label">
								发货日期:
							</label>
						</td>
						<td class="value">
						     <fmt:formatDate value="${weixinOrderPage.expressDate}" type="date"/>
						     
						</td>
					</tr>
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								快递单号:
							</label>
						</td>
						<td class="value">
						     	${weixinOrderPage.expressNum}
						</td>
						<td align="right">
							<label class="Validform_label">
								物流公司:
							</label>
						</td>
						<td class="value">
						     ${weixinOrderPage.expressCompany}
						</td>
					</tr>
			</table>
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr height="35px">
					<td colspan="3" align="center"><label class="Validform_label">订单明细</label></td>
				</tr>
				<tr height="25px">
					<td align="center">商品标题</td>
					<td align="center">购买数量</td>
					<td align="center">购买价格</td>
				</tr>
				<c:forEach items="${weixinOrderPage.weixinOrderDetailList}" var="weixinOrderDetail">
					<tr height="25px" style="background: white;">
						<td align="center">${weixinOrderDetail.weixinShopGoodsEntity.title}</td>
						<td align="center">${weixinOrderDetail.quantity}</td>
						<td align="center">${weixinOrderDetail.price}</td>
					</tr>	    	
				</c:forEach>				
			</table>
			
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/shop/weixinOrder.js"></script>		