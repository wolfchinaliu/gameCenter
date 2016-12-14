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
	//$('#evenFrequency').val(evenFrequency);
	<c:if test="${code == 0}">
	$('input').attr("readonly","readonly");
	</c:if>
});
function submit(){
	var code = ${code};
	if(code == 0){
		frameElement.api.close();
		return false;
	}
	
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
             layout="table" action="weixinOtherGameController.do?doSave" tiptype="1" beforeSubmit="submit">
    <input id="id" name="id" type="hidden"
           value="${game.id }"/>
    <input id="gameType" name="gameType" type="hidden"
           value='${game.gameType  }'/>
           <input id="activityRule" name="activityRule" type="hidden"/>
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <tr>
            <td align="right"><label class="Validform_label"> 活动名称:
            </label></td>
            <td class="value" ><input class="inputxt" id="title" datatype="*" value="${game.title }"
                                                 name="title" type="text"> <span class="Validform_checktip"></span></td>
                                                  <td align="right"><label class="Validform_label">
                流量类型: </label></td>
            <td class="value">
                <input type="radio" value="1" name="flowType" <c:if test="${game.flowType == 1 || activity.flowType == null}">checked="checked"</c:if> /> 全国流量
                <input type="radio" value="2" name="flowType" <c:if test="${game.flowType == 2}">checked="checked"</c:if>/>省内流量
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 开始时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                     style="width: 150px" id="startTime" name="startTime"
                                     value="<fmt:formatDate value='${game.startTime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
                <span class="Validform_checktip"></span></td>
            <td align="right"><label class="Validform_label"> 结束时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                     style="width: 150px" id="endTime" name="endTime"
                                     value="<fmt:formatDate value='${game.endTime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
                <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
                <td align="right"><label class="Validform_label"> 流量规则:
            </label></td>

            <td class="value" colspan="3"><input class="inputxt" id="ruleId"  value="${game.ruleId }" style="width: 350px"
                                                 name="ruleId" type="text" onclick="selectRule()"> <span class="Validform_checktip"></span></td>
                
        </tr>
        </table >
</t:formvalid>
<script type="text/javascript">
function submit(){
	if($.trim($('#ruleId').val())==''){
	$.messager.alert("温馨提示！  ", "规则Id不能为空");
	return false;}
}
function selectRule(){
	 $.dialog.setting.zIndex = 2005;
	 url = "weixinGameSafeRuleController.do?goList"
     $.dialog({
         content: "url:" + url,
         lock: true,
         title: "选择规则",
         width: 1000,
         height: 500,
         cache: false,
         ok: function () {
             iframe = this.iframe.contentWindow;
             var selected = iframe.getSelectRows();
             if (selected == '' || selected == null) {
                 alert("请选择");
                 return false;
             } else if (selected.length > 1) {
       	        tip('只能选择一条规则');
      	        return false;
      	    }
             $('#ruleId').val(selected[0].id);
                 return true;

         },
         cancelVal: '关闭',
         cancel: true /*为true等价于function(){}*/
     }).zindex();
}
</script>
</body>
</html>