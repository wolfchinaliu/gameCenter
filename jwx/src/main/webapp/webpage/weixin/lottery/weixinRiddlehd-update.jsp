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
        //编写自定义JS代码
    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password"
             layout="table" action="weixinGuessRiddlesController.do?doUpdate" tiptype="1">
    <input id="lotteryType" name="lotteryType" type="hidden"
           value="3">

    <input id="id" name="id" type="hidden" value="${riddleEntity.id}">
    <input type="hidden" id="endtimeOld" name="endtimeOld" value="${riddleEntity.endtime}">
    <input type="hidden" id="allFlowOld" name="allFlowOld" value="${riddleEntity.allFlow}">
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <tr>
            <td align="right"><label class="Validform_label"> 活动名称:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" disabled="true" id="title" datatype="*"
                                                 name="title" type="text" value="${riddleEntity.title}"> <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 活动描述:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" disabled="true" value="${riddleEntity.description}"
                                                 id="description" style="width: 350px" name="description"
                                                 ignore="ignore" type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>

        <tr>
            <td align="right"><label class="Validform_label"> 开始时间:
            </label></td>
            <td class="value"><input style="width: 150px" id="starttime" name="starttime"
                                     value='<fmt:formatDate value='${riddleEntity.starttime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="true">
                <span class="Validform_checktip"></span></td>
            <td align="right"><label class="Validform_label"> 结束时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                     style="width: 150px" id="endtime" name="endtime"
                                     value="<fmt:formatDate value='${riddleEntity.endtime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
                <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">
                每题赠送流量: </label></td>
            <td class="value"><input id="riddleFlow" value="${riddleEntity.riddleFlow}" disabled="true"
                                     name="riddleFlow" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">M
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">每题赠送流量</label></td>
            <td align="right"><label class="Validform_label">
                送出流量总值: </label></td>
            <td class="value"><input id="allFlow" value="${riddleEntity.allFlow}"
                                     name="allFlow" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">M
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">送出流量总值</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">
                流量类型: </label></td>
            <td  class="value" colspan="3">
                <input disabled="true" type="radio" value="1" name="flowtype"
                       <c:if test="${riddleEntity.flowtype=='1'}">checked="checked"</c:if>/>全国流量
                <input disabled="true" type="radio" value="2" name="flowtype"
                       <c:if test="${riddleEntity.flowtype=='2'}">checked="checked"</c:if>/>省内流量
            </td>
        </tr>
            <tr>
            <td colspan="4">
            *提示：只可以修改结束时间（向后延期）和送出流量总值（增加）。
            </td>
            </tr>
            <tr>
            <td colspan="4">
            *结束时间到期后，剩余流量将返回
            </td>
            </tr>

    </table>
</t:formvalid>
</body>
