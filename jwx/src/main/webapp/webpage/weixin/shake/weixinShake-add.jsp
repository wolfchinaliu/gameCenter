<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>

<head>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
    <script type="text/javascript" src="easyui/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="easyui/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<t:formvalid formid="messagefrom" dialog="true" usePlugin="password" layout="table" action="weixinShakeController.do?doAdd" tiptype="1">

    <input id="lotteryType" name="lotteryType" type="hidden"
           value="4">
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
            <td align="right"><label class="Validform_label">
                活动总次数: </label></td>
            <td class="value"><input id="lotterynumber"
                                     name="lotterynumber" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">活动总次数</label></td>
            <td align="right"><label class="Validform_label">
                每人活动次数: </label></td>
            <td class="value"><input id="lotterynumberday"
                                     name="lotterynumberday" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">每人活动次数</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 活动总流量:
            </label></td>
            <td class="value"><input id="abledotherprize"
                                     name="abledotherprize" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">活动总流量</label></td>


            <td align="right"><label class="Validform_label">
                流量类型: </label></td>
            <td class="value">
                <input type="radio" value="1" name="flowtype" checked="checked"/>全国流量
                <input type="radio" value="2" name="flowtype"/>省内流量
            </td>
        </tr>
            <%--<tr>--%>
            <%--<td colspan="4">--%>
            <%--*所有概率的和小于10000，概率为0表示不会中奖\n--%>
            <%--*总抽奖次数影响中奖概率--%>
            <%--</td>--%>
            <%--</tr>--%>
        <tr>
            <td align="right"><label class="Validform_label"> 开始时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                     style="width: 150px" id="starttime" name="starttime"
                                     value="<fmt:formatDate value='${weixinLotteryPage.starttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
                <span class="Validform_checktip"></span></td>
            <td align="right"><label class="Validform_label"> 结束时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                     style="width: 150px" id="endtime" name="endtime"
                                     value="<fmt:formatDate value='${weixinLotteryPage.endtime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
                <span class="Validform_checktip"></span></td>
        </tr>

    </table>
</t:formvalid>
<p id="note" style="font-size:14px">活动说明:该活动最低奖项为0.1M，所以在设置活动总流量与活动总次数时应当保证比例在1:10以上</p>

</body>

