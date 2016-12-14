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
var regulation = ${activity.activityRule == null ? "{'evenNumber':'','allFlow':'',oneFlow:''}":activity.activityRule};
var j=0;
$(document).ready(function(){
		$('#evenWinNumber').val(regulation.evenNumber);
		$('#allRight').val(regulation.allFlow);
		$('#evenQuestion').val(regulation.oneFlow);
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
	var regulationSave = '{"evenNumber":'+$('#evenWinNumber').val()+',"allFlow":'+$('#allRight').val()+',"oneFlow":'+$('#evenQuestion').val()+'}';
	//校验
	$('#activityRule').val(regulationSave);
}

</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password"
             layout="table" action="weixinActivityController.do?doSave" tiptype="1" beforeSubmit="submit">
    <input id="id" name="id" type="hidden"
           value="${activity.id }"/>
    <input id="type" name="type" type="hidden"
           value='${activity.type== null?101:activity.type }'/>
           <input id="activityRule" name="activityRule" type="hidden"/>
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <tr>
            <td align="right"><label class="Validform_label"> 活动名称:
            </label></td>
            <td class="value" ><input class="inputxt" id="title" datatype="*" value="${activity.title }"
                                                 name="title" type="text"> <span class="Validform_checktip"></span></td>
                                                  <td align="right"><label class="Validform_label">
                流量类型: </label></td>
            <td class="value">
                <input type="radio" value="1" name="flowType" <c:if test="${activity.flowType eq'1' || activity.flowType == null}">checked="checked"</c:if> /> 全国流量
                <input type="radio" value="2" name="flowType" <c:if test="${activity.flowType eq '2'}">checked="checked"</c:if>/>省内流量
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 活动描述:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" value="${activity.description }"
                                                 id="description" style="width: 350px" name="description"
                                                 ignore="ignore" type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 开始时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                     style="width: 150px" id="startTime" name="startTime"
                                     value="<fmt:formatDate value='${activity.startTime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
                <span class="Validform_checktip"></span></td>
            <td align="right"><label class="Validform_label"> 结束时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                     style="width: 150px" id="endTime" name="endTime"
                                     value="<fmt:formatDate value='${activity.endTime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
                <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
         <td align="right"><label class="Validform_label"> 参与频次:
            </label></td>

            <td class="value"><t:dictSelect field="frequency" typeGroupCode="frequency" hasLabel="false"
                                            defaultVal="${activity.frequency == null ? 1 :activity.frequency }"></t:dictSelect>
                <span class="Validform_checktip"></span></td>
            <td align="right"><label class="Validform_label">
                每人答题次数: </label></td>
            <td class="value"><input id="evenNumber"
                                     name="evenNumber" type="text" style="width: 150px" value="${activity.evenNumber }"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">每人抽奖次数</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">
                总参与次数: </label></td>
            <td class="value"><input id="totalNumber"
                                     name="totalNumber" type="text" style="width: 150px" value="${activity.totalNumber }"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">总抽奖次数</label></td>
                             <td align="right"><label class="Validform_label">
                每周期得奖人数(全对): </label></td>
            <td class="value"><input id="evenWinNumber"
                                     name="evenWinNumber" type="text" style="width: 150px" "
                                     class="easyui-numberbox inputxt" datatype="n" precision="0" >
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;"> 每周期得奖人数</label></td>

        </tr>
        <tr>
         <td align="right"><label class="Validform_label">
                每题流量值(M): </label></td>
            <td class="value"><input id="evenQuestion"
                                     name="evenQuestion" type="text" style="width: 150px" 
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">每题流量值</label></td>
                             <td align="right"><label class="Validform_label">
                全对额外流量值(M): </label></td>
            <td class="value"><input id="allRight"
                                     name="allRight" type="text" style="width: 150px" 
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;"> 全对流量值</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 时间段设置:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" value="${activity.timePart }"
                                                 id="timePart" style="width: 300px" name="timePart"
                                                 ignore="ignore" type="text"> <span>9:00-13:00;18:20-20:05/1,2,3</span></td>
        </tr>
        </table >
</t:formvalid>
</body>
</html>