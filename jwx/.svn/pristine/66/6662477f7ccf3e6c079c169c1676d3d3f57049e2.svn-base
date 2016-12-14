<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" type="text/css" href="plug-in/lhgDialog/skins/default.css">
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
    <script>
        $(function(){

            $("#closeBtn").click(function(){
                frameElement.api.close();
            });
        });
    </script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" layout="div" dialog="true" action="" refresh="true" btnsub="toreloadpw">
	
	<div class="form">
		<label class="form"> </label> 
		&nbsp;&nbsp;<input type="radio" value="${weixinAccountEntity.id }" id="accountid" name="accountid" <c:if test='${WEIXIN_ACCOUNT.id eq weixinAccountEntity.id}'>checked="checked"</c:if>>
		&nbsp;&nbsp;${weixinAccountEntity.accountname }<c:if test="${WEIXIN_ACCOUNT.id eq weixinAccountEntity.id}">（当前）</c:if>
	</div>
	<c:forEach items="${weixinAccountList}" var="weixinAccount">
	
		<div class="form">
			<label class="form"> </label> 
			&nbsp;&nbsp;<input type="radio" id="accountid" name="accountid" value="${weixinAccount.id }" <c:if test='${WEIXIN_ACCOUNT.id eq weixinAccount.id}'>checked="checked"</c:if> >
			&nbsp;&nbsp;${weixinAccount.accountname } <c:if test="${WEIXIN_ACCOUNT.id eq weixinAccount.id}">（当前）</c:if>
		</div>
	</c:forEach>
	
	
</t:formvalid>
	<div class="ui_buttons" style="padding-left:50px;padding-top: 10px;">
		<input type="button" value="确定" onclick="toreloadpw();" class="ui_state_highlight">
		
		 <input type="button" id="closeBtn" value="取消" onclick='close();'>
	</div>
</body>
</html>
<SCRIPT type="text/javascript">
	function toreloadpw(){
		
		var aid = $("input[name='accountid']:checked").val();
		
		if(aid == '${WEIXIN_ACCOUNT.id}'){

			alert("当前已经是公众账号${WEIXIN_ACCOUNT.accountname}！");
		}else{
			
			var url = "helpController.do?dochangeaccount";
		   	url += "&accountid="+aid;
			$.ajax({
				url:url,
				type:"GET",
				dataType:'json',
				success:function(data){
					if(data.success){
						parent.location="loginController.do?login";
					}
				}
			});
		}
	}
  

</SCRIPT>