<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>抽奖记录表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinDrawDetailController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinDrawDetailPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								抽奖时间:
							</label>
						</td>
						<td class="value">
									  <input id="addtime" name="addtime" type="text" style="width: 150px" 
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${weixinDrawDetailPage.addtime}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">抽奖时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								抽奖ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="drawrecordid" name="drawrecordid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinDrawDetailPage.drawrecordid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">抽奖ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								accountid:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountid" name="accountid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinDrawDetailPage.accountid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">accountid</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								抽奖人ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="opendid" name="opendid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinDrawDetailPage.opendid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">抽奖人ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								抽奖角度:
							</label>
						</td>
						<td class="value">
						     	 <input id="angle" name="angle" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinDrawDetailPage.angle}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">抽奖角度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								提示信息:
							</label>
						</td>
						<td class="value">
						     	 <input id="msg" name="msg" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinDrawDetailPage.msg}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提示信息</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/lottery/weixinDrawDetail.js"></script>		