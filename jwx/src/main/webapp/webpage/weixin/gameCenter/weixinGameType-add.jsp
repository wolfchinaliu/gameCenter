<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>微信活动</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
$(document).ready(function(){
		<c:if test="${code == 0}">
		$('input').attr("readonly","readonly");
		</c:if>
});
function submit(){
	<c:if test="${code == 0}">
	frameElement.api.close();
	return false;
	</c:if>
}

</script>
    <style type="text/css">
    .anniu{
	color:#01599d;
	text-decoration: none;
	padding:6px;
	border-radius: 5px;
	background: #C4C4C4;
	margin: 1px;
	line-height: 14px;
	font-size: 12px;
	cursor: pointer;
}
.anniu:hover{
	color:#e2800c;
	font-size: 13px;
	text-decoration: none;}
    </style>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password"
             layout="table" action="weixinGameTypeController.do?doSave" tiptype="1" beforeSubmit="submit">
    <input id="id" name="id" type="hidden"
           value="${gameType.id }"/>
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <tr>
            <td align="right"><label class="Validform_label"> 类型名称:
            </label></td>
            <td class="value" ><input class="inputxt" id="gameName" datatype="*" value="${gameType.gameName }"
                                                 name="gameName" type="text"> <span class="Validform_checktip"></span></td>
                                                  <td align="right"><label class="Validform_label">
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 游戏进入地址:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" value="${gameType.playUrl }"
                                                 id="playUrl" style="width: 350px" name="playUrl"
                                                 ignore="ignore" type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 游戏编辑地址:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" value="${gameType.editUrl }"
                                                 id="editUrl" style="width: 350px" name="editUrl"
                                                 ignore="ignore" type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> logo图片url:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" value="${gameType.logoPath }"
                                                 id="logoPath" style="width: 350px" name="logoPath"
                                                 ignore="ignore" type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>
       
</t:formvalid>
</body>
</html>