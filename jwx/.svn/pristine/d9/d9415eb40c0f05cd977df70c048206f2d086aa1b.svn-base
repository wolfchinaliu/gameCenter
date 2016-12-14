<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>微信活动</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    
    <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
    <script type="text/javascript">
        //编写自定义JS代码
    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password"
             layout="table" action="weixinRedPacketsController.do?doUpdate"
             tiptype="1">
    <input id="id" name="id" type="hidden"
           value="${weixinLotteryPage.id }">
    <input id="createName" name="createName" type="hidden"
           value="${weixinLotteryPage.createName }">
    <input id="createDate" name="createDate" type="hidden"
           value="${weixinLotteryPage.createDate }">
    <input id="lotteryType" name="lotteryType" type="hidden"
           value="${weixinLotteryPage.lotteryType }">
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">

        <c:choose>
            <c:when test="${hd=='-1'}">
                <tr>
                    <td align="right"><label class="Validform_label"> 红包名称:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" id="title" datatype="*"
                                                         name="title" type="text" value='${weixinLotteryPage.title}'>
                        <span class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 红包描述:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" style="width:350px"
                                                         id="description" name="description" ignore="ignore" type="text"
                                                         value='${weixinLotteryPage.description}'> <span
                            class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 开始时间:
                    </label></td>
                    <td class="value"><input class="Wdate" datatype="*"
                                             onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                             style="width: 150px" id="starttime" name="starttime"

                                             value="<fmt:formatDate value='${weixinLotteryPage.starttime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
                        <span class="Validform_checktip"></span></td>
                    <td align="right"><label class="Validform_label"> 结束时间:
                    </label></td>
                    <td class="value"><input class="Wdate"
                                             onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                             style="width: 150px" id="endtime" name="endtime"
                                             value="<fmt:formatDate value='${weixinLotteryPage.endtime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
                        <span class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        总流量值: </label></td>
                    <td class="value"><input id="abledotherprize"
                                             value='${weixinLotteryPage.abledotherprize}'
                                             name="abledotherprize" type="text" style="width: 150px"
                                             class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">总流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        红包个数: </label></td>
                    <td class="value"><input id="lotterynumber"
                                             value='${weixinLotteryPage.lotterynumber}' name="lotterynumber"
                                             type="text" style="width: 150px" class="easyui-numberbox inputxt"
                                             datatype="n" precision="0"> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">红包个数</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        流量类型: </label></td>
                    <td class="value" colspan="3">
                        <input type="radio" value="1" readonly="true" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='1'}">checked="checked"</c:if>/>全国流量
                        <input type="radio" value="2" readonly="true" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='2'}">checked="checked"</c:if>/>省内流量
                    </td>
                </tr>

            </c:when>
            <c:when test="${hd=='0'}">
                <tr>
                    <td align="right"><label class="Validform_label"> 红包名称:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" datatype="*"
                                                         name="title" type="text" value='${weixinLotteryPage.title}'>
                        <span class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 红包描述:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" style="width:350px"
                                                         name="description" ignore="ignore" type="text"
                                                         value='${weixinLotteryPage.description}'> <span
                            class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 开始时间:
                    </label></td>
                        <%--<td class="value"><input class="Wdate" datatype="*"--%>
                        <%--onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"--%>
                        <%--style="width: 150px" name="starttime"--%>

                        <%--value="<fmt:formatDate value='${weixinLotteryPage.starttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">                   --%>
                    <td class="value"><input class="inputxt" datatype="*"
                                             name="starttime" type="text" value='<fmt:formatDate value='${weixinLotteryPage.starttime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>'
                                             readonly="true">
                        <span class="Validform_checktip"></span></td>
                    <td align="right"><label class="Validform_label"> 结束时间:
                    </label></td>
                    <td class="value"><input class="Wdate"
                                             onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                             style="width: 150px" name="endtime"
                                             value="<fmt:formatDate value='${weixinLotteryPage.endtime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
                        <span class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        总流量值: </label></td>
                    <td class="value"><input
                            value='${weixinLotteryPage.abledotherprize}'
                            name="abledotherprize" type="text" readonly="true" style="width: 150px"
                            class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">总流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        红包个数: </label></td>
                    <td class="value"><input
                            value='${weixinLotteryPage.lotterynumber}' name="lotterynumber"
                            type="text" style="width: 150px" readonly="true" class="easyui-numberbox inputxt"
                            datatype="n" precision="0"> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">红包个数</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        流量类型: </label></td>
                    <td class="value" colspan="3">
                        <input type="radio" value="1" disabled="true" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='1'}">checked="checked"</c:if>/>全国流量
                        <input type="radio" value="2" disabled="true" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='2'}">checked="checked"</c:if>/>省内流量
                    </td>
                </tr>

            </c:when>
            <c:when test="${hd=='1'}">
                <tr>
                    <td align="right"><label class="Validform_label"> 红包名称:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" datatype="*"
                                                         name="title" type="text" readonly="true"
                                                         value='${weixinLotteryPage.title}'>
                        <span class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 红包描述:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" readonly="true" style="width:350px"
                                                         name="description" ignore="ignore" type="text"
                                                         value='${weixinLotteryPage.description}'> <span
                            class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 开始时间:
                    </label></td>
                        <%--<td class="value"><input class="Wdate" datatype="*"--%>
                        <%--onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"--%>
                        <%--style="width: 150px" disabled="true" name="starttime"--%>

                        <%--value="<fmt:formatDate value='${weixinLotteryPage.starttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">--%>

                    <td class="value"><input class="inputxt" datatype="*"
                                             name="starttime" type="text" value='${weixinLotteryPage.starttime}'
                                             readonly="true">
                        <span class="Validform_checktip"></span></td>
                    <td align="right"><label class="Validform_label"> 结束时间:
                    </label></td>
                        <%--<td class="value"><input class="Wdate"--%>
                        <%--onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"--%>
                        <%--style="width: 150px" disabled="true" name="endtime"--%>
                        <%--value="<fmt:formatDate value='${weixinLotteryPage.endtime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">--%>

                    <td class="value"><input class="inputxt" datatype="*"
                                             name="endtime" type="text" value='${weixinLotteryPage.endtime}'
                                             readonly="true">
                        <span class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        总流量值: </label></td>
                    <td class="value"><input
                            value='${weixinLotteryPage.abledotherprize}'
                            name="abledotherprize" type="text" readonly="true" style="width: 150px"
                            class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">总流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        红包个数: </label></td>
                    <td class="value"><input
                            value='${weixinLotteryPage.lotterynumber}' name="lotterynumber"
                            type="text" style="width: 150px" readonly="true" class="easyui-numberbox inputxt"
                            datatype="n" precision="0"> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">红包个数</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        流量类型: </label></td>
                    <td class="value" colspan="3">
                        <input type="radio" value="1" disabled="true" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='1'}">checked="checked"</c:if>/>全国流量
                        <input type="radio" value="2" disabled="true" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='2'}">checked="checked"</c:if>/>省内流量
                    </td>
                </tr>

            </c:when>
        </c:choose>
        <tr>
            <td colspan="4">
                *用户红包的最小数量是0.1M,所以红包个数不得多于流量的10倍
            </td>
        </tr>
        <tr>
            <td colspan="4">
                *结束时间到期后，剩余红包作废
            </td>
        </tr>
    </table>
</t:formvalid>
</body>
<script src="webpage/weixin/lottery/weixinLottery.js"></script>