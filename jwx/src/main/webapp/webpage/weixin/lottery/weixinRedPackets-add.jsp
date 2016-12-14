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
             layout="table" action="weixinRedPacketsController.do?doAdd" tiptype="1">
    <input id="lotteryType" name="lotteryType" type="hidden"
           value="3">
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <tr>
            <td align="right"><label class="Validform_label"> 红包名称:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" id="title" datatype="*"
                                                 name="title" type="text"> <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 红包描述:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt"
                                                 id="description" style="width: 350px" name="description"
                                                 ignore="ignore" type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>

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
                总流量值: </label></td>
            <td class="value"><input id="abledotherprize"
                                     name="abledotherprize" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">总流量值</label></td>
            <td align="right"><label class="Validform_label">
                红包个数: </label></td>
            <td class="value"><input id="lotterynumber"
                                     name="lotterynumber" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">红包个数</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">
                流量类型: </label></td>
            <td class="value" colspan="3">
                <input type="radio" value="1" name="flowtype" checked="checked"/>全国流量
                <input type="radio" value="2" name="flowtype"/>省内流量
            </td>
        </tr>
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
