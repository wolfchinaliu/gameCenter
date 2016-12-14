<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>安全规则</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
$(document).ready(function(){
		<c:if test="${code == 0}">
		$('input').attr("readonly","readonly");
		</c:if>
});
var fatherpag;
function submit(){
	<c:if test="${code == 0}">
	frameElement.api.close();
	return false;
	</c:if>
}
function callback(data){
	if(data.success==true){frameElement.api.close();fatherpag.tip(data.msg);}
	  else{if(data.responseText==''||data.responseText==undefined){$.messager.alert('错误', data.msg);$.Hidemsg();}
	  else{try{var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'),data.responseText.indexOf('错误信息')); $.messager.alert('错误',emsg);$.Hidemsg();}catch(ex){$.messager.alert('错误',data.responseText+"");$.Hidemsg();}} return false;}
	fatherpag.reloadTable();
}
function fatherPag(pag){
	fatherpag = pag;
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
             layout="table" action="weixinGameSafeRuleController.do?doSave" tiptype="1" beforeSubmit="submit" callback="@Override callback">
    <input id="id" name="id" type="hidden"
           value="${safeRule.id }"/>
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <tr>
            <td align="right"><label class="Validform_label"> 最大赠送流量值:
            </label></td>
            <td class="value" ><input class="easyui-numberbox inputxt" id="maxFlow" datatype="n" value="${safeRule.maxFlow }"
                                                 name="maxFlow" type="text">M <span class="Validform_checktip"></span></td>
           <td align="right"><label class="Validform_label"> 参与频次:
            </label></td>

            <td class="value"><t:dictSelect field="frequency" typeGroupCode="frequency" hasLabel="false"
                                            defaultVal="${safeRule.frequency == null ? 1 :safeRule.frequency }"></t:dictSelect>
                <span class="Validform_checktip"></span></td>
                                                  
        </tr>
       <tr>
         <td align="right"><label class="Validform_label"> 每周期最大总次数:
            </label></td>
            <td class="value" ><input class="easyui-numberbox inputxt" id="maxTimes" datatype="n" value="${safeRule.maxTimes }"
                                                 name="maxTimes" type="text"> <span class="Validform_checktip"></span></td>
            <td align="right"><label class="Validform_label">
              每周期没人最大次数: </label></td>
            <td class="value"><input id="everyoneTimes"
                                     name="everyoneTimes" type="text" style="width: 150px" value="${safeRule.everyoneTimes }"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">每人抽奖次数</label></td>
        </tr>
</t:formvalid>
</body>
</html>