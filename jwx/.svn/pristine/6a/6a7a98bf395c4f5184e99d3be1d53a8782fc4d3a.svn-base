<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>餐饮订单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinFoodOrderController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinFoodOrderPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinFoodOrderPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
						     	 <input id="userName" name="userName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinFoodOrderPage.userName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
						     	 <input id="sex" name="sex" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinFoodOrderPage.sex}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								手机号码:
							</label>
						</td>
						<td class="value">
						     	 <input id="mobilphone" name="mobilphone" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinFoodOrderPage.mobilphone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">手机号码</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								就餐时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="preDate" name="preDate" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinFoodOrderPage.preDate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">就餐时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								座位类别:
							</label>
						</td>
						<td class="value">
						     	 <input id="type" name="type" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinFoodOrderPage.type}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">座位类别</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								预订人数:
							</label>
						</td>
						<td class="value">
						     	 <input id="number" name="number" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinFoodOrderPage.number}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预订人数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinFoodOrderPage.status}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单状态</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								支付状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="ispay" name="ispay" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinFoodOrderPage.ispay}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付状态</label>
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
									               
									                 value='${weixinFoodOrderPage.accountid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众号</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinFoodOrderPage.remark}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/business/weixinFoodOrder.js"></script>		