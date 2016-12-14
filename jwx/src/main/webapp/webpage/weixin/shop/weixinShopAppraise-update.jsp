<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp" />
<!DOCTYPE html>
<html>
 <head>
  <title>商品评价</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>

  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinShopAppraiseController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinShopAppraisePage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinShopAppraisePage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr height="35px">
						<td align="right" width="100px">
							<label class="Validform_label">
								商品名称:
							</label>
						</td>
						<td class="value">
						     ${weixinShopAppraisePage.weixinShopGoodsEntity.title }
						</td>
					</tr>
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								评价内容:
							</label>
						</td>
						<td class="value">
						     ${weixinShopAppraisePage.notes}
						</td>
					</tr>
					<%-- 
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								星级:
							</label>
						</td>
						<td class="value">
						     	${weixinShopAppraisePage.star}
						</td>
					</tr>
					--%>
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								粉丝昵称:
							</label>
						</td>
						<td class="value">
						     	${weixinShopAppraisePage.openName}
						</td>
					</tr>				
					
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								审核状态:
							</label>
						</td>
						<td class="value">
							<c:if test="${weixinShopAppraisePage.status == 0}">未核准</c:if>
						    <c:if test="${weixinShopAppraisePage.status == 1}">已核准</c:if>
						</td>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/shop/weixinShopAppraise.js"></script>		