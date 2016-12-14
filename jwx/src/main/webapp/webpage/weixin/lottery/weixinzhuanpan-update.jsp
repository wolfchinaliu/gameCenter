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
             layout="table" action="weixinLotteryController.do?doUpdate"
             tiptype="1">
    <input id="id" name="id" type="hidden"
           value="${weixinLotteryPage.id }">
    <input id="createName" name="createName" type="hidden"
           value="${weixinLotteryPage.createName }">
    <input id="createDate" name="createDate" type="hidden"
           value="${weixinLotteryPage.createDate }">
    <input id="lotteryType" name="lotteryType" type="hidden"
           value="${weixinLotteryPage.lotteryType }">
    <%--< style="width: 600px;" cellpadding="0" cellspacing="1"--%>
    <%--class="formtable">--%>
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <c:choose>
            <c:when test="${hd=='-1'}">
                <tr>
                    <td align="right"><label class="Validform_label"> 活动名称:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" id="title" datatype="*"
                                                         name="title" type="text" value='${weixinLotteryPage.title}'>
                        <span class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 活动描述:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" style="width:350px"
                                                         id="description" name="description" ignore="ignore" type="text"
                                                         value='${weixinLotteryPage.description}'> <span
                            class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 一等奖流量值:
                    </label></td>
                    <td class="value"><input id="firstprize" name="firstprize"
                                             type="text" style="width: 150px" class="inputxt"
                                             value='${weixinLotteryPage.firstprize}'>M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">一等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        一等奖数量: </label></td>
                    <td class="value"><input id="firstprizetotal"
                                             name="firstprizetotal" type="text" style="width: 150px"
                                             class="easyui-numberbox inputxt" datatype="n" precision="0"
                                             value='${weixinLotteryPage.firstprizetotal}'> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">一等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        一等奖概率: </label></td>
                    <td class="value" colspan="3"><input id="firstprizeProb"
                                                         value='${weixinLotteryPage.firstprizeProb}'
                                                         name="firstprizeProb"
                                                         type="text" style="width: 150px"
                                                         class="easyui-numberbox inputxt"
                                                         datatype="n" precision="0">‱（万分之） <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">一等奖概率</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 二等奖流量值:
                    </label></td>
                    <td class="value"><input id="secondprize" name="secondprize"
                                             value='${weixinLotteryPage.secondprize}' type="text"
                                             style="width: 150px" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">二等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        二等奖数量: </label></td>
                    <td class="value"><input id="secondprizetotal" name="secondprizetotal"
                                             type="text" style="width: 150px" class="easyui-numberbox inputxt"
                                             datatype="n" precision="0" value='${weixinLotteryPage.secondprizetotal}'>
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">二等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        二等奖概率: </label></td>
                    <td class="value" colspan="3"><input id="secondprizeProb"
                                                         value='${weixinLotteryPage.secondprizeProb}'
                                                         name="secondprizeProb"
                                                         type="text" style="width: 150px"
                                                         class="easyui-numberbox inputxt"
                                                         datatype="n" precision="0">‱（万分之） <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">二等奖概率</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 三等奖流量值:
                    </label></td>
                    <td class="value"><input id="thirdprize" name="thirdprize"
                                             value='${weixinLotteryPage.thirdprize}' type="text"
                                             style="width: 150px" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">三等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        三等奖数量: </label></td>
                    <td class="value"><input id="thirdprizetotal"
                                             name="thirdprizetotal" type="text" style="width: 150px"
                                             class="easyui-numberbox inputxt" datatype="n" precision="0"
                                             value='${weixinLotteryPage.thirdprizetotal}'> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">三等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        三等奖概率: </label></td>
                    <td class="value" colspan="3"><input id="thirdprizeProb"
                                                         value='${weixinLotteryPage.thirdprizeProb}'
                                                         name="thirdprizeProb"
                                                         type="text" style="width: 150px" class="inputxt"
                                                         class="easyui-numberbox inputxt" datatype="n" precision="0">‱（万分之）
                        <span class="Validform_checktip"></span> <label class="Validform_label"
                                                                        style="display: none;">三等奖概率</label></td>
                </tr>


                <tr>
                    <td align="right"><label class="Validform_label"> 四等奖流量值: </label></td>
                    <td class="value">
                        <input id="forthprize" name="forthprize" value="${weixinLotteryPage.forthprize}" type="text" style="width: 150px" class="inputxt">M
                        <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;">四等奖流量值</label>
                    </td>
                    <td align="right"><label class="Validform_label"> 四等奖数量: </label></td>
                    <td class="value">
                        <input id="forthprizetotal" value="${weixinLotteryPage.forthprizetotal}" name="forthprizetotal" type="text" style="width: 150px" class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;">四等奖数量</label>
                    </td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 四等奖概率: </label></td>
                    <td class="value" colspan="3">
                        <input id="forthprizeProb" value="${weixinLotteryPage.forthprizeProb}" name="forthprizeProb" type="text" style="width: 150px" class="inputxt" class="easyui-numberbox inputxt" datatype="n" precision="0">
                        ‱（万分之） <span class="Validform_checktip"></span>
                    </td>
                </tr>

                <tr>
                    <td align="right"><label class="Validform_label"> 五等奖流量值: </label></td>
                    <td class="value">
                        <input id="fifthprize" name="fifthprize" value="${weixinLotteryPage.fifthprize}" type="text" style="width: 150px" class="inputxt">M
                        <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;">五等奖流量值</label> </td>
                    <td align="right"><label class="Validform_label"> 五等奖数量: </label></td>
                    <td class="value">
                        <input id="fifthprizetotal" value="${weixinLotteryPage.fifthprizetotal}" name="fifthprizetotal" type="text" style="width: 150px" class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;">五等奖数量</label>
                    </td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 五等奖概率: </label></td>
                    <td class="value" colspan="3">
                        <input id="fifthprizeProb" value="${weixinLotteryPage.fifthprizeProb}" name="fifthprizeProb" type="text" style="width: 150px" class="inputxt" class="easyui-numberbox inputxt" datatype="n" precision="0">
                        ‱（万分之） <span class="Validform_checktip"></span>
                    </td>
                </tr>

                <tr>
                    <td align="right"><label class="Validform_label"> 六等奖流量值:</label></td>
                    <td class="value">
                        <input id="sixthprize" name="sixthprize" value="${weixinLotteryPage.sixthprize}" type="text" style="width: 150px" class="inputxt">M
                        <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;"> 六等奖流量值</label>
                    </td>
                    <td align="right"><label class="Validform_label"> 六等奖数量: </label></td>
                    <td class="value">
                        <input id="sixthprizetotal" value="${weixinLotteryPage.sixthprizetotal}" name="sixthprizetotal" type="text" style="width: 150px" class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;"> 六等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 六等奖概率: </label></td>
                    <td  class="value" colspan="3">
                        <input id="sixthprizeProb" value="${weixinLotteryPage.sixthprizeProb}" name="sixthprizeProb" type="text" style="width: 150px" class="inputxt" class="easyui-numberbox inputxt" datatype="n" precision="0">
                        ‱（万分之） <span class="Validform_checktip"></span>
                    </td>
                </tr>

                <tr>
                    <td align="right"><label class="Validform_label"> 开始时间:
                    </label></td>
                    <td class="value"><input class="Wdate"
                                             onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                             style="width: 150px" name="endtime"
                                             value="<fmt:formatDate value='${weixinLotteryPage.starttime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                             disabled="disabled">
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
                        每人抽奖次数: </label></td>
                    <td class="value"><input id="lotterynumberday"
                                             value='${weixinLotteryPage.lotterynumberday}'
                                             name="lotterynumberday" type="text" style="width: 150px"
                                             class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">每人抽奖次数</label></td>
                    <td align="right"><label class="Validform_label">
                        总抽奖次数: </label></td>
                    <td class="value"><input id="lotterynumber"
                                             value='${weixinLotteryPage.lotterynumber}' name="lotterynumber"
                                             type="text" style="width: 150px" class="easyui-numberbox inputxt"
                                             datatype="n" precision="0"> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">总抽奖次数</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 参与频次:
                    </label></td>

                    <td class="value"><t:dictSelect field="frequency" typeGroupCode="frequency" hasLabel="false"
                                                    defaultVal="${weixinLotteryPage.frequency}"></t:dictSelect>
                        <span class="Validform_checktip"></span></td>
                    <td align="right"><label class="Validform_label">
                        流量类型: </label></td>
                    <td class="value">
                        <input type="radio" value="1" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='1'}">checked="checked"</c:if>/>全国流量
                        <input type="radio" value="2" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='2'}">checked="checked"</c:if>/>省内流量
                    </td>
                </tr>
            </c:when>

            <c:when test="${hd=='0'}">
                <tr>
                    <td align="right"><label class="Validform_label"> 活动名称:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" datatype="*"
                                                         name="title" type="text" value='${weixinLotteryPage.title}'>
                        <span class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 活动描述:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" style="width:350px"
                                                         name="description" ignore="ignore" type="text"
                                                         value='${weixinLotteryPage.description}'> <span
                            class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 一等奖流量值:
                    </label></td>
                    <td class="value"><input name="firstprize"
                                             type="text" style="width: 150px" readonly="true" class="inputxt"
                                             value='${weixinLotteryPage.firstprize}'>M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">一等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        一等奖数量: </label></td>
                    <td class="value"><input
                            name="firstprizetotal" type="text" readonly="true" style="width: 150px"
                            class="easyui-numberbox inputxt" datatype="n" precision="0"
                            value='${weixinLotteryPage.firstprizetotal}'> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">一等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        一等奖概率: </label></td>
                    <td class="value" colspan="3"><input
                            value='${weixinLotteryPage.firstprizeProb}' readonly="true" name="firstprizeProb"
                            type="text" style="width: 150px" class="easyui-numberbox inputxt"
                            datatype="n" precision="0">‱（万分之） <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">一等奖概率</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 二等奖流量值:
                    </label></td>
                    <td class="value"><input name="secondprize"
                                             value='${weixinLotteryPage.secondprize}' type="text"
                                             style="width: 150px" readonly="true" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">二等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        二等奖数量: </label></td>
                    <td class="value"><input name="secondprizetotal"
                                             type="text" readonly="true" style="width: 150px"
                                             class="easyui-numberbox inputxt"
                                             datatype="n" precision="0" value='${weixinLotteryPage.secondprizetotal}'>
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">二等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        二等奖概率: </label></td>
                    <td class="value" colspan="3"><input
                            value='${weixinLotteryPage.secondprizeProb}' name="secondprizeProb"
                            type="text" readonly="true" style="width: 150px" class="easyui-numberbox inputxt"
                            datatype="n" precision="0">‱（万分之） <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">二等奖概率</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 三等奖流量值:
                    </label></td>
                    <td class="value"><input name="thirdprize"
                                             value='${weixinLotteryPage.thirdprize}' type="text"
                                             style="width: 150px" readonly="true" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">三等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        三等奖数量: </label></td>
                    <td class="value"><input
                            name="thirdprizetotal" type="text" readonly="true" style="width: 150px"
                            class="easyui-numberbox inputxt" datatype="n" precision="0"
                            value='${weixinLotteryPage.thirdprizetotal}'> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">三等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        三等奖概率: </label></td>
                    <td class="value" colspan="3"><input
                            value='${weixinLotteryPage.thirdprizeProb}' name="thirdprizeProb"
                            type="text" readonly="true" style="width: 150px" class="inputxt"
                            class="easyui-numberbox inputxt" datatype="n" precision="0">‱（万分之）
                        <span class="Validform_checktip"></span></td>
                </tr>


                <tr>
                    <td align="right"><label class="Validform_label"> 四等奖流量值:
                    </label></td>
                    <td class="value"><input name="forthprize" readonly="true" value="${weixinLotteryPage.forthprize}"
                                             type="text" style="width: 150px" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">四等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        四等奖数量: </label></td>
                    <td class="value"><input readonly="true" value="${weixinLotteryPage.forthprizetotal}"
                                             name="forthprizetotal" type="text" style="width: 150px"
                                             class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">四等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        四等奖概率: </label></td>
                    <td class="value" colspan="3"><input readonly="true" value="${weixinLotteryPage.forthprizeProb}"
                                                         name="forthprizeProb" type="text" style="width: 150px"
                                                         class="inputxt" class="easyui-numberbox inputxt" datatype="n"
                                                         precision="0">‱（万分之） <span class="Validform_checktip"></span></td>
                </tr>

                <tr>
                    <td align="right"><label class="Validform_label"> 五等奖流量值:
                    </label></td>
                    <td class="value"><input name="fifthprize" readonly="true" value="${weixinLotteryPage.fifthprize}"
                                             type="text" style="width: 150px" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">五等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        五等奖数量: </label></td>
                    <td class="value"><input readonly="true" value="${weixinLotteryPage.fifthprizetotal}"
                                             name="fifthprizetotal" type="text" style="width: 150px"
                                             class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">五等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        五等奖概率: </label></td>
                    <td class="value" colspan="3"><input readonly="true" value="${weixinLotteryPage.fifthprizeProb}"
                                                         name="fifthprizeProb" type="text" style="width: 150px"
                                                         class="inputxt" class="easyui-numberbox inputxt" datatype="n"
                                                         precision="0">‱（万分之） <span class="Validform_checktip"></span></td>
                </tr>

                <tr>
                    <td align="right"><label class="Validform_label"> 六等奖流量值:
                    </label></td>
                    <td class="value"><input  name="sixthprize" readonly="true" value="${weixinLotteryPage.sixthprize}"
                                             type="text" style="width: 150px" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;"> 六等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        六等奖数量: </label></td>
                    <td class="value"><input readonly="true" value="${weixinLotteryPage.sixthprizetotal}"
                                             name="sixthprizetotal" type="text" style="width: 150px"
                                             class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;"> 六等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        六等奖概率: </label></td>
                    <td  class="value" colspan="3"><input readonly="true" value="${weixinLotteryPage.sixthprizeProb}"
                                                       name="sixthprizeProb" type="text" style="width: 150px"
                                                       class="inputxt" class="easyui-numberbox inputxt" datatype="n"
                                                       precision="0">‱（万分之） <span class="Validform_checktip"></span></td>
                </tr>

                <tr>
                    <td align="right"><label class="Validform_label"> 开始时间:
                    </label></td>
                        <td class="value"><input class="Wdate"
                                             onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                             style="width: 150px" name="endtime"
                                             value="<fmt:formatDate value='${weixinLotteryPage.starttime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                             disabled="disabled">
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
                        每人抽奖次数: </label></td>
                    <td class="value"><input
                            value='${weixinLotteryPage.lotterynumberday}'
                            name="lotterynumberday" type="text" readonly="true" style="width: 150px"
                            class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">每人抽奖次数</label></td>
                    <td align="right"><label class="Validform_label">
                        总抽奖次数: </label></td>
                    <td class="value"><input
                            value='${weixinLotteryPage.lotterynumber}' name="lotterynumber"
                            type="text" readonly="true" style="width: 150px" class="easyui-numberbox inputxt"
                            datatype="n" precision="0"> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">总抽奖次数</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 参与频次:
                    </label></td>

                    <td class="value"><t:dictSelect field="frequency" typeGroupCode="frequency" hasLabel="false"
                                                    defaultVal="${weixinLotteryPage.frequency}" extendJson="{'disabled':'disabled'}"></t:dictSelect>
                        <span class="Validform_checktip"></span></td>
                    <td align="right"><label class="Validform_label">
                        流量类型: </label></td>
                    <td class="value">
                        <input type="radio" value="1" disabled="true" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='1'}">checked="checked"</c:if>/>全国流量
                        <input type="radio" value="2" disabled="true" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='2'}">checked="checked"</c:if>/>省内流量
                    </td>
                </tr>
            </c:when>


            <c:when test="${hd=='1'}">
                <tr>
                    <td align="right"><label class="Validform_label"> 活动名称:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" readonly="true" datatype="*"
                                                         name="title" type="text" value='${weixinLotteryPage.title}'>
                        <span class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 活动描述:
                    </label></td>
                    <td class="value" colspan="3"><input class="inputxt" readonly="true" style="width:350px"
                                                         name="description" ignore="ignore" type="text"
                                                         value='${weixinLotteryPage.description}'> <span
                            class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 一等奖流量值:
                    </label></td>
                    <td class="value"><input name="firstprize"
                                             type="text" style="width: 150px" readonly="true" class="inputxt"
                                             value='${weixinLotteryPage.firstprize}'>M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">一等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        一等奖数量: </label></td>
                    <td class="value"><input
                            name="firstprizetotal" type="text" readonly="true" style="width: 150px"
                            class="easyui-numberbox inputxt" datatype="n" precision="0"
                            value='${weixinLotteryPage.firstprizetotal}'> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">一等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        一等奖概率: </label></td>
                    <td class="value" colspan="3"><input
                            value='${weixinLotteryPage.firstprizeProb}' readonly="true" name="firstprizeProb"
                            type="text" style="width: 150px" class="easyui-numberbox inputxt"
                            datatype="n" precision="0">‱（万分之） <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">一等奖概率</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 二等奖流量值:
                    </label></td>
                    <td class="value"><input name="secondprize"
                                             value='${weixinLotteryPage.secondprize}' type="text"
                                             style="width: 150px" readonly="true" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">二等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        二等奖数量: </label></td>
                    <td class="value"><input name="secondprizetotal"
                                             type="text" readonly="true" style="width: 150px"
                                             class="easyui-numberbox inputxt"
                                             datatype="n" precision="0" value='${weixinLotteryPage.secondprizetotal}'>
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">二等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        二等奖概率: </label></td>
                    <td class="value" colspan="3"><input
                            value='${weixinLotteryPage.secondprizeProb}' name="secondprizeProb"
                            type="text" readonly="true" style="width: 150px" class="easyui-numberbox inputxt"
                            datatype="n" precision="0">‱（万分之） <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">二等奖概率</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 三等奖流量值:
                    </label></td>
                    <td class="value"><input name="thirdprize"
                                             value='${weixinLotteryPage.thirdprize}' type="text"
                                             style="width: 150px" readonly="true" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">三等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        三等奖数量: </label></td>
                    <td class="value"><input
                            name="thirdprizetotal" type="text" readonly="true" style="width: 150px"
                            class="easyui-numberbox inputxt" datatype="n" precision="0"
                            value='${weixinLotteryPage.thirdprizetotal}'> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">三等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        三等奖概率: </label></td>
                    <td class="value" colspan="3"><input
                            value='${weixinLotteryPage.thirdprizeProb}' name="thirdprizeProb"
                            type="text" readonly="true" style="width: 150px" class="inputxt"
                            class="easyui-numberbox inputxt" datatype="n" precision="0">‱（万分之）
                        <span class="Validform_checktip"></span></td>
                </tr>

                <tr>
                    <td align="right"><label class="Validform_label"> 四等奖流量值:
                    </label></td>
                    <td class="value"><input name="forthprize" readonly="true" value="${weixinLotteryPage.forthprize}"
                                             type="text" style="width: 150px" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">四等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        四等奖数量: </label></td>
                    <td class="value"><input readonly="true" value="${weixinLotteryPage.forthprizetotal}"
                                             name="forthprizetotal" type="text" style="width: 150px"
                                             class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">四等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        四等奖概率: </label></td>
                    <td class="value" colspan="3"><input readonly="true" value="${weixinLotteryPage.forthprizeProb}"
                                                         name="forthprizeProb" type="text" style="width: 150px"
                                                         class="inputxt" class="easyui-numberbox inputxt" datatype="n"
                                                         precision="0">‱（万分之） <span class="Validform_checktip"></span></td>
                </tr>

                <tr>
                    <td align="right"><label class="Validform_label"> 五等奖流量值:
                    </label></td>
                    <td class="value"><input name="fifthprize" readonly="true" value="${weixinLotteryPage.fifthprize}"
                                             type="text" style="width: 150px" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">五等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        五等奖数量: </label></td>
                    <td class="value"><input readonly="true" value="${weixinLotteryPage.fifthprizetotal}"
                                             name="fifthprizetotal" type="text" style="width: 150px"
                                             class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">五等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        五等奖概率: </label></td>
                    <td class="value" colspan="3"><input readonly="true" value="${weixinLotteryPage.fifthprizeProb}"
                                                         name="fifthprizeProb" type="text" style="width: 150px"
                                                         class="inputxt" class="easyui-numberbox inputxt" datatype="n"
                                                         precision="0">‱（万分之） <span class="Validform_checktip"></span></td>
                </tr>

                <tr>
                    <td align="right"><label class="Validform_label"> 六等奖流量值:
                    </label></td>
                    <td class="value"><input  name="sixthprize" readonly="true" value="${weixinLotteryPage.sixthprize}"
                                              type="text" style="width: 150px" class="inputxt">M <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;"> 六等奖流量值</label></td>
                    <td align="right"><label class="Validform_label">
                        六等奖数量: </label></td>
                    <td class="value"><input readonly="true" value="${weixinLotteryPage.sixthprizetotal}"
                                             name="sixthprizetotal" type="text" style="width: 150px"
                                             class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;"> 六等奖数量</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        六等奖概率: </label></td>
                    <td class="value" colspan="3"><input readonly="true" value="${weixinLotteryPage.sixthprizeProb}"
                                                       name="sixthprizeProb" type="text" style="width: 150px"
                                                       class="inputxt" class="easyui-numberbox inputxt" datatype="n"
                                                       precision="0">‱（万分之） <span class="Validform_checktip"></span></td>
                </tr>

                <tr>
                    <td align="right"><label class="Validform_label"> 开始时间:
                    </label></td>
                    <td class="value"><input class="Wdate"
                                             onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                             style="width: 150px" name="endtime"
                                             value="<fmt:formatDate value='${weixinLotteryPage.starttime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                             disabled="disabled">
                        <span class="Validform_checktip"></span></td>
                    <td align="right"><label class="Validform_label"> 结束时间:
                    </label></td>
                    <td class="value"><input class="Wdate"
                                             onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                             style="width: 150px" name="endtime"
                                             value="<fmt:formatDate value='${weixinLotteryPage.endtime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                             disabled="disabled">
                        <span class="Validform_checktip"></span></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label">
                        每人抽奖次数: </label></td>
                    <td class="value"><input
                            value='${weixinLotteryPage.lotterynumberday}'
                            name="lotterynumberday" type="text" readonly="true" style="width: 150px"
                            class="easyui-numberbox inputxt" datatype="n" precision="0">
                        <span class="Validform_checktip"></span> <label
                                class="Validform_label" style="display: none;">每人抽奖次数</label></td>
                    <td align="right"><label class="Validform_label">
                        总抽奖次数: </label></td>
                    <td class="value"><input
                            value='${weixinLotteryPage.lotterynumber}' name="lotterynumber"
                            type="text" readonly="true" style="width: 150px" class="easyui-numberbox inputxt"
                            datatype="n" precision="0"> <span
                            class="Validform_checktip"></span> <label class="Validform_label"
                                                                      style="display: none;">总抽奖次数</label></td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 参与频次:
                    </label></td>

                    <td class="value"><t:dictSelect field="frequency" typeGroupCode="frequency" hasLabel="false"
                                                    defaultVal="${weixinLotteryPage.frequency}" extendJson="{'disabled':'disabled'}"></t:dictSelect>
                        <span class="Validform_checktip"></span></td>
                    <td align="right"><label class="Validform_label">
                        流量类型: </label></td>
                    <td class="value">
                        <input type="radio" value="1" disabled="true" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='1'}">checked="checked"</c:if>/>全国流量
                        <input type="radio" value="2" disabled="true" name="flowtype"
                               <c:if test="${weixinLotteryPage.flowtype=='2'}">checked="checked"</c:if>/>省内流量
                    </td>
                </tr>
            </c:when>
        </c:choose>

        <tr>
            <td align="right" colspan="4">*所有概率的和需要小于10000，概率为0表示不会中这个奖项</td>
        </tr>
    </table>
</t:formvalid>
</body>
<script src="webpage/weixin/lottery/weixinLottery.js"></script>