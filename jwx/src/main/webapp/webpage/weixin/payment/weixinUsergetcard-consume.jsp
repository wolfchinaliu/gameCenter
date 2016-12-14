<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>卡券核销页面</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.mins.css" />
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinUsergetcardController.do?doConsume" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinUsergetcardPage.id }">
					
		<table style="width: 500px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr height="35px">
						<td align="right" width="200px">
							<label class="Validform_label">
								优惠券:
							</label>
						</td>
						<td class="value">
						     	 ${weixinUsergetcardPage.weixinCardEntity.title }
						</td>
					</tr>
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								优惠券号:
							</label>
						</td>
						<td class="value">
						     	 ${weixinUsergetcardPage.userCardCode}
						</td>
					</tr>
					<tr height="35px">
						<td align="right">
							<label class="Validform_label">
								领券人昵称:
							</label>
						</td>
						<td class="value">
						     	${weixinUsergetcardPage.weixinMemberEntity.nickName }
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>	