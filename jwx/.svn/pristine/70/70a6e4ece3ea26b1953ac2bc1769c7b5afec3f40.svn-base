<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>卡券领取记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinUsergetcardController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinUsergetcardPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinUsergetcardPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							公众号:
						</label>
					</td>
					<td class="value">
					     	 <input id="accountid" name="accountid" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							领券方帐号用户:
						</label>
					</td>
					<td class="value">
					     	 <input id="openId" name="openId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">领券方帐号用户</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							优惠券ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="cardId" name="cardId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">优惠券ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							卡券code:
						</label>
					</td>
					<td class="value">
					     	 <input id="userCardCode" name="userCardCode" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">卡券code</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否为转赠:
						</label>
					</td>
					<td class="value">
					     	 <input id="byFriend" name="byFriend" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否为转赠</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							领取场景值:
						</label>
					</td>
					<td class="value">
					     	 <input id="outerId" name="outerId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">领取场景值</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							赠送方账号:
						</label>
					</td>
					<td class="value">
					     	 <input id="friendUser" name="friendUser" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">赠送方账号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							转赠前的code:
						</label>
					</td>
					<td class="value">
					     	 <input id="oldCardCode" name="oldCardCode" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">转赠前的code</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							消费时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="consumeTime" name="consumeTime" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">消费时间</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							核销来源:
						</label>
					</td>
					<td class="value">
					     	 <input id="consumeSource" name="consumeSource" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">核销来源</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/payment/weixinUsergetcard.js"></script>		