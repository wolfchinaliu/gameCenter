<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>积分流水</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinCoinJournalController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinCoinJournalPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinCoinJournalPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								交易金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="coin" name="coin" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinJournalPage.coin}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								唯一码:
							</label>
						</td>
						<td class="value">
						     	 <input id="uniqueCode" name="uniqueCode" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinJournalPage.uniqueCode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">唯一码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								摘要:
							</label>
						</td>
						<td class="value">
						     	 <input id="notes" name="notes" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinJournalPage.notes}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">摘要</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="openid" name="openid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinJournalPage.openid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建人:
							</label>
						</td>
						<td class="value">
						     	 <input id="createrName" name="createrName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinJournalPage.createrName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								交易类别:
							</label>
						</td>
						<td class="value">
						     	 <input id="dealType" name="dealType" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinJournalPage.dealType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易类别</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								交易时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="dealDate" name="dealDate" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCoinJournalPage.dealDate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易时间</label>
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
									               
									                 value='${weixinCoinJournalPage.accountid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众号</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/member/weixinCoinJournal.js"></script>		