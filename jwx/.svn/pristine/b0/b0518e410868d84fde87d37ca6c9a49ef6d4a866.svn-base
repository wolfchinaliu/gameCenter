<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>关注用户管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinMemberController.do?doUpdate" tiptype="1">
		<table style="width: 700px;" cellpadding="0" cellspacing="1" class="formtable">

					<tr height="35">
						<td align="right" width="200">
							<label class="Validform_label">
								昵称:
							</label>
						</td>
						<td class="value"  width="300">
						     	 ${weixinMemberPage.nickName}
						</td>
						<td  class="value" rowspan="9" width="300"><img src="${weixinMemberPage.headImgUrl}" width="350" height="350"></td>
					</tr>
					<tr height="35">
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
						     	 <c:if test="${weixinMemberPage.sex == 1}">男</c:if>
						     	 <c:if test="${weixinMemberPage.sex == 2}">女</c:if>
						</td>
						
					</tr>
					
					<tr height="35">
						<td align="right">
							<label class="Validform_label">
								省份:
							</label>
						</td>
						<td class="value">
						     	${weixinMemberPage.province}
						</td>
					</tr>
					
					<tr height="35">
						<td align="right">
							<label class="Validform_label">
								城市:
							</label>
						</td>
						<td class="value">
						     	${weixinMemberPage.city}
						</td>
					</tr>
					<tr height="35">
						<td align="right">
							<label class="Validform_label">
								语言:
							</label>
						</td>
						<td class="value">
						     	 ${weixinMemberPage.language}
						</td>
					</tr>
					
					<tr height="35">
						<td align="right">
							<label class="Validform_label">
								国家:
							</label>
						</td>
						<td class="value">
						     	 ${weixinMemberPage.country}
						</td>
					</tr>

					<tr height="35">
						<td align="right">
							<label class="Validform_label">
								关注时间:
							</label>
						</td>
						<td class="value">
								<fmt:formatDate value='${weixinMemberPage.subscribeTime}' type="date" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					
					<tr height="35">
						<td align="right">
							<label class="Validform_label">
								是否订阅:
							</label>
						</td>
						<td class="value">
						     <c:if test="${weixinMemberPage.subscribe == 1}">是</c:if>
						</td>
					</tr>
					<tr height="35">
						<td align="right">
							<label class="Validform_label">
								备份:
							</label>
						</td>
						<td class="value" >
								
						</td>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/member/weixinMember.js"></script>		