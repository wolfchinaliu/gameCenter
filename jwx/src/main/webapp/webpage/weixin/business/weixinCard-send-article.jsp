<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>图文发放</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  $(function() {
  $("#isToAll").change(function() {
		var isToAll = $("#isToAll").find("option:selected").val();
		if (isToAll == "false") {
			$(".c_two").css("display", "block");
		} else {
			$(".c_two").css("display", "none");
		}
	});
  });
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinCardController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinCardPage.id }">
					
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								优惠券:
							</label>
						</td>
						<td class="value">
						    ${weixinCardPage.brandName}
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发放对象:
							</label>
						</td>
						<td class="value">
						     <select name="isToAll" id="isToAll">
						     	<option value="true">全部用户</option>
								<option value="false">指定用户</option>
						     </select>
						    
						    <div class="c_two" style="display: none"> 
						    	<input id="receiveUserId" name="receiveUserId" type="hidden" style="width: 150px" class="inputxt" >
					     		<input name="nickName" class="inputxt" value="${nickName}" id="nickName" readonly="readonly" datatype="*" /> 
					     	
					     		<t:choose hiddenName="receiveUserId" hiddenid="openId" url="weixinMessageController.do?members" name="memberList" icon="icon-search" title="用户列表" textname="nickName" isclear="true"></t:choose>
								<span class="Validform_checktip">请选择发送对象,用户可多选</span>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发放数量:
							</label>
						</td>
						<td class="value">
						     	 <input id="quantity" name="quantity" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCardPage.quantity}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">卡券数量</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/business/weixinCard.js"></script>		