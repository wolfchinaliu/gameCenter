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
             layout="table" action="weixinLotteryController.do?doAdd" tiptype="1">
    <input id="id" name="id" type="hidden"
           value="${weixinLotteryPage.id }">
    <input id="lotteryType" name="lotteryType" type="hidden"
           value="${lotteryType==""?"1":lotteryType }">
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <tr>
            <td align="right"><label class="Validform_label"> 活动名称:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" id="title" datatype="*"
                                                 name="title" type="text"> <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 活动描述:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt"
                                                 id="description" style="width: 350px" name="description"
                                                 ignore="ignore" type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 一等奖流量值:
            </label></td>
            <td class="value"><input id="firstprize" name="firstprize"
                                     type="text" style="width: 150px" class="inputxt">M<span
                    class="Validform_checktip"></span> <label class="Validform_label"
                                                              style="display: none;">一等奖流量值</label></td>
            <td align="right"><label class="Validform_label">
                一等奖数量: </label></td>
            <td class="value"><input id="firstprizetotal"
                                     name="firstprizetotal" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">一等奖数量</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">
                一等奖概率: </label></td>
            <td class="value" colspan="3"><input id="firstprizeProb"
                                                 name="firstprizeProb" type="text" style="width: 150px"
                                                 class="easyui-numberbox inputxt" datatype="n" precision="0">‱（万分之）
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">一等奖概率</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 二等奖流量值:
            </label></td>
            <td class="value"><input id="secondprize" name="secondprize"
                                     type="text" style="width: 150px" class="inputxt">M <span
                    class="Validform_checktip"></span> <label class="Validform_label"
                                                              style="display: none;">二等奖流量值</label></td>
            <td align="right"><label class="Validform_label">
                二等奖数量: </label></td>
            <td class="value"><input id="secondprizetotal" name="secondprizetotal"
                                     type="text" style="width: 150px" class="easyui-numberbox inputxt"
                                     datatype="n" precision="0"> <span
                    class="Validform_checktip"></span> <label class="Validform_label"
                                                              style="display: none;">二等奖数量</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">
                二等奖概率: </label></td>
            <td class="value" colspan="3"><input id="secondprizeProb"
                                                 name="secondprizeProb" type="text" style="width: 150px"
                                                 class="easyui-numberbox inputxt" datatype="n" precision="0">‱（万分之）
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">二等奖概率</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 三等奖流量值:
            </label></td>
            <td class="value"><input id="thirdprize" name="thirdprize"
                                     type="text" style="width: 150px" class="inputxt">M <span
                    class="Validform_checktip"></span> <label class="Validform_label"
                                                              style="display: none;">三等奖流量值</label></td>
            <td align="right"><label class="Validform_label">
                三等奖数量: </label></td>
            <td class="value"><input id="thirdprizetotal"
                                     name="thirdprizetotal" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">三等奖数量</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">
                三等奖概率: </label></td>
            <td class="value" colspan="3"><input id="thirdprizeProb"
                                                 name="thirdprizeProb" type="text" style="width: 150px"
                                                 class="inputxt" class="easyui-numberbox inputxt" datatype="n"
                                                 precision="0">‱（万分之） <span class="Validform_checktip"></span></td>
        <tr>
            <td align="right"><label class="Validform_label"> 开始时间:
            </label></td>
            <td class="value"><input class="Wdate"
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
                每人抽奖次数: </label></td>
            <td class="value"><input id="lotterynumberday"
                                     name="lotterynumberday" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">每人抽奖次数</label></td>
            <td align="right"><label class="Validform_label">
                总抽奖次数: </label></td>
            <td class="value"><input id="lotterynumber"
                                     name="lotterynumber" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">总抽奖次数</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 参与频次:
            </label></td>

            <td class="value"><t:dictSelect field="frequency" typeGroupCode="frequency" hasLabel="false"
                                            defaultVal="1"></t:dictSelect>
                <span class="Validform_checktip"></span></td>

            <td align="right"><label class="Validform_label">
                流量类型: </label></td>
            <td class="value">
                <input type="radio" value="1" name="flowtype" checked="checked"/>全国流量
                <input type="radio" value="2" name="flowtype"/>省内流量
            </td>
        </tr>
        <tr>
            <td colspan="4">
                *所有概率的和小于10000，概率为0表示不会中奖\n
                *总抽奖次数影响中奖概率
            </td>
        </tr>

    </table>
</t:formvalid>
</body>
<script src="webpage/weixin/lottery/weixinLottery.js"></script>