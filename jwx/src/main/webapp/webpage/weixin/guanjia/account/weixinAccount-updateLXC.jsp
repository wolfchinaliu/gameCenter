<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

  <%--<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinAccountController.do?doUpdate" tiptype="1">--%>
  <form action="weixinAccountController.do?doUpdate" method="post">
					<input id="id" name="id" type="hidden" value="${weixinAccountPage.id }">
					<input id="addtoekntime" name="addtoekntime" type="hidden" value="${weixinAccountPage.addtoekntime }"  />
					<input id="accountaccesstoken" name="accountaccesstoken" value="${weixinAccountPage.accountaccesstoken }" type="hidden"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众帐号名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountname" name="accountname" type="text" style="width: 250px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accountname}'><br>
							<span class="Validform_checktip1"  style="color:#999">此信息需登录 微信公众平台-公众号设置 获取</span>
							<label class="Validform_label" style="display: none;">公众帐号名称</label>
						</td>
					</tr>
					<%--<tr>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--公众帐号TOKEN:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
						     	 <%--<input id="accounttoken" name="accounttoken" type="text" style="width: 250px" class="inputxt"  --%>
									               <%----%>
									                 <%--value='${weixinAccountPage.accounttoken}' readonly="readonly">--%>
							<%--<br><span class="Validform_checktip1">必填</span>--%>
							<%--<label class="Validform_label" style="display: none;">公众帐号TOKEN</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--公众微信号:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
						     	 <%--<input id="accountnumber" name="accountnumber" type="text" style="width: 250px" class="inputxt"  --%>
									               <%----%>
									                 <%--value='${weixinAccountPage.accountnumber}'>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">公众微信号</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr>
						<td align="right">
							<label class="Validform_label">
								原始ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="weixin_accountid" name="weixin_accountid" type="text" style="width: 250px" class="inputxt"  
									               
									                 value='${weixinAccountPage.weixin_accountid}'>
							<br><span class="Validform_checktip1"  style="color:#999">此信息需登录 微信公众平台-公众号设置 获取，请认真填写原始ID，填写后原始ID将不能修改</span>
							<label class="Validform_label" style="display: none;">原始ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众号类型:
							</label>
						</td>
						<td class="value">
						    <t:dictSelect field="accounttype" typeGroupCode="weixintype" hasLabel="false" defaultVal="${weixinAccountPage.accounttype}"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公众号类型</label>
						</td>
					</tr>
					<%--<tr>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--电子邮箱:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
						     	 <%--<input id="accountemail" name="accountemail" type="text" style="width: 250px" class="inputxt"  --%>
									               <%----%>
									                 <%--value='${weixinAccountPage.accountemail}'>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">电子邮箱</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--公众帐号描述:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
						     	 <%--<input id="accountdesc" name="accountdesc" type="text" style="width: 250px" class="inputxt"  --%>
									               <%----%>
									                 <%--value='${weixinAccountPage.accountdesc}'>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">公众帐号描述</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众帐号APPID:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountappid" name="accountappid" type="text" style="width: 250px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accountappid}'>
							<br><span class="Validform_checktip1 "  style="color:#999">此信息需登录 微信公众平台-开发者中心 获取</span>
							<label class="Validform_label" style="display: none;">公众帐号APPID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公众帐号APPSECRET:
							</label>
						</td>
						<td class="value">
						     	 <input id="accountappsecret" name="accountappsecret" type="text" style="width: 250px" class="inputxt"  
									               
									                 value='${weixinAccountPage.accountappsecret}'>
							<br><span class="Validform_checktip1" style="color:#999">此信息需登录 微信公众平台-开发者中心 获取</span>
							<label class="Validform_label" style="display: none;">公众帐号APPSECRET</label>
						</td>
					</tr>
			</table>
	  		<input type="button" onclick="submit()" value="保存">
		</form>
		<%--</t:formvalid>--%>
<script>
	function saveform(){
		var nameq= $('#accountname').val();
		$.ajax({
			url:"weixinAccountController.do?doUpdate",
			type:"POST",
			data:nameq,
			success:function(data){
				alert(data);
			}
		});
	}
</script>
  <script src = "webpage/weixin/guanjia/account/weixinAccount.js"></script>		