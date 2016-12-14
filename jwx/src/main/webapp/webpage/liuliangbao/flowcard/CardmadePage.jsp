<%@ taglib prefix="t" uri="/easyui-tags" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>商户制作流量卡</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
    <script type="text/javascript">
       /*  function viewAll() {
            $('#dgquyu').dialog('open').dialog('setTitle', '选择区域');
        } */

/*         function myfunction() {
            $.ajax({

                type: "post",
                url: "flowCardInfoController.do?setTimeoutData",
                dataType: "json",
                success: function (data) {
                    var ms = data.msg;
                    $("#status").value = ms;
                }
            })
        }
        var t = setInterval("myfunction()", 1000);  //隔1秒就查询一次数据
         */
    </script>
</head>
<body onload="myfunction()">
<div style="padding:5px 15px;color:#a94442;font-size:x-large">
    温馨提示：<br> <strong>1、制卡会消耗您的流量值;<br>2、您每天可制作1000次卡，每次最多制作10000张卡;<br>3、如果到截止日期还有流量卡没有兑换，则自动返回到您的流量账户</strong>
</div>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="flowCardInfoController.do?doAdd"
             refresh="true" tiptype="1">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <strong>流量类型：</strong>
            </td>
            <td class="value">
                全国流量： <input type="radio" checked="checked" name="type" id="jihuo" value="1"/>
                &nbsp;&nbsp;&nbsp;
                省内流量： <input type="radio" name="type" id="suoding" value="2">
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    您的制卡前缀:
                </label>
            </td>
            <td class="value">
                <input id="cardPrefix" name="cardPrefix" type="text" style="width: 150px" class="inputxt"

                       value="${acctCode}" readonly="true">
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">您的制卡前缀</label>
            </td>

            <td align="right">
                <label class="Validform_label">
                    批次号:
                </label>
            </td>
            <td class="value">
                <input id="batchNo" name="batchNo" type="text" style="width: 150px" class="inputxt"

                       value="${batchT}" readonly="true">
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">批次号</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    制卡编号:
                </label>
            </td>
            <td class="value">
                <input id="cardCode" name="cardCode" type="text" style="width: 150px" class="inputxt"

                       value="00000" readonly="true">
                    <%--<input value="起000000" onFocus="if(value==defaultValue){value='';this.style.color='#000'}"--%>
                    <%--onBlur="if(!value){value=defaultValue; this.style.color='#999'}" style="color:#999"--%>
                    <%--id="cardCode" name="cardCode"/>--%>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">制卡编号</label>
            </td>
            <td align="right">
                <label class="Validform_label">
                    张数:
                </label>
            </td>
            <td class="value">
                    <%--<input id="putFlowCardCount" name="putFlowCardCount" type="text" style="width: 150px" class="inputxt"--%>

                    <%-->--%>
                <input value="不超过10000" onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
                       onBlur="if(!value){value=defaultValue; this.style.color='#999'}" style="color:#999"
                       id="putFlowCardCount" name="putFlowCardCount"/>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">张数</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    制卡流量:
                </label>
            </td>
            <td class="value">
                <input id="putFlowTotal" name="putFlowTotal" type="text" style="width: 150px" class="inputxt"

                        >
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">制卡流量</label>
            </td>
            <td align="right">
                <label class="Validform_label">
                    状态:
                </label>
            </td>
            <td class="value">
                    <%--<input id="city" name="city" type="text" style="width: 150px" class="inputxt"--%>

                    <%-->--%>
                <%--<t:dictSelect field="status" typeGroupCode="staFlow" hasLabel="false"--%>
                              <%--defaultVal="${flowCardBatchEntity.status}"></t:dictSelect>--%>

                        <select name="status" class="select" id="status" datatype="*">
                            <option></option>
                            <option>激活</option>
                            <option>锁定</option>
                        </select>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">状态</label>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 开始时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                     style="width: 150px" id="enableDate" name="enableDate"
                                     value="">
                <span class="Validform_checktip"></span></td>
                <%--<td class="value"><input id="createTime" name="createTime" type="text" style="width: 150px"--%>
                <%--class="inputxt"--%>

                <%--value="${zhizuo}"></td>--%>
            <td align="right"><label class="Validform_label"> 结束时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                     style="width: 150px" id="disabledDate" name="disabledDate"
                                     value="">
                <span class="Validform_checktip"></span></td>
        </tr>
    </table>
</t:formvalid>
</body>
<script src="webpage/weixin/tenant/weixinAcct.js"></script>
