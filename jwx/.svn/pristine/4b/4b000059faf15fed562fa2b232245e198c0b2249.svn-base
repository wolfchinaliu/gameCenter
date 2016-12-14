<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>商户管理表</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    
    <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
    <script type="text/javascript">
    </script>
</head>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table"
             action="weixinAcctFlowAccountController.do?doAdd"
             tiptype="1" beforeSubmit="checkFlow();return false;">
    <%--设置隐藏值以便于向充值记录中插入表--%>
<input id="fromAcc_id" name="fromAcc_id" type="hidden" value="${weixinAcctFlowChargePage.id }">
<input id="fromAcc_tententid" name="fromAcc_tententid" type="hidden" value="${weixinAcctFlowChargePage.tenantId }">
<input id="toAcc_id" name="toAcc_id" type="hidden" value="${weixinCurrAcctFlowChargePage.id }">
<input id="toAcc_tententid" name="toAcc_tententid" type="hidden" value="${weixinCurrAcctFlowChargePage.tenantId }">
<input id="toAcc_countryFlowValue" name="toAcc_countryFlowValue" type="hidden" value="${weixinCurrAcctFlowChargePage.countryFlowValue }">
<input id="toAcc_provinceFlowValue" name="toAcc_provinceFlowValue" type="hidden" value="${weixinCurrAcctFlowChargePage.provinceFlowValue }">
<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
    <tr>
        <td align="right">
            <label class="Validform_label">
                被充值商户：
            </label>
        </td>
        <td class="value" colspan="8">
            <input id="currAcc" name="currAcc" readonly="true" type="text" style="width: 150px;border: none"
                   class="inputxt"

                   value="${weixinCurrAcctFlowChargePage.accountName}">
            <span class="Validform_checktip"></span>
            <label class="Validform_label" style="display: none;">省内流量</label>
        </td>
    </tr>
    <tr>

        <td align="right">
            <label class="Validform_label">
                您当前剩余 全国流量：
            </label>
        </td>
        <td class="value">
            <input id="countryFlowValue" name="countryFlowValue" readonly="true" type="text"
                   style="width: 150px;border: none"
                   class="inputxt"

                   value="${weixinAcctFlowChargePage.countryFlowValue}">

            <span class="Validform_checktip"></span>
            <label class="Validform_label" style="display: none;">全国流量</label>
        </td>

        <td align="right">
            <label class="Validform_label">
                省内流量：
            </label>
        </td>
        <td class="value">
            <input id="provinceFlowValue" name="provinceFlowValue" readonly="true" type="text"
                   style="width: 150px;border: none;"
                   class="
                   inputxt"

                   value="${weixinAcctFlowChargePage.provinceFlowValue}">
            <span class="Validform_checktip"></span>
            <label class="Validform_label" style="display: none;">省内流量</label>
        </td>
    </tr>


    <tr>

        <td align="right">
            <label class="Validform_label">
                商户存量 全国流量：
            </label>
        </td>
        <td class="value">
            <input id="CurcountryFlowValue" name="CurcountryFlowValue" readonly="true" type="text"
                   style="width: 150px;border: none"
                   class="inputxt"

                   value="${weixinCurrAcctFlowChargePage.countryFlowValue}">


            <span class="Validform_checktip"></span>
            <label class="Validform_label" style="display: none;">全国流量</label>
        </td>

        <td align="right">
            <label class="Validform_label">
                省内流量：
            </label>
        </td>
        <td class="value">
            <input id="CurprovinceFlowValue" name="CurprovinceFlowValue" readonly="true" type="text"
                   style="width: 150px;border: none"
                   class="inputxt"
                   value="${weixinCurrAcctFlowChargePage.provinceFlowValue}">
            <span class="Validform_checktip"></span>
            <label class="Validform_label" style="display: none;">省内流量</label>
        </td>
    </tr>
    <tr>
        <td align="right">
            <label class="Validform_label">
                流量类型:
            </label>
        </td>
        <td class="value" colspan="8">

            <t:dictSelect field="flowtype" typeGroupCode="flowtype" hasLabel="false"
                          defaultVal="${weixinAcctPage.flowtype}" id="flowtype"></t:dictSelect>
            <span class="Validform_checktip">不选默认为省内</span>
            <label class="Validform_label" style="display: none;">流量类型</label>
        </td>
    </tr>
    <tr>
        <td align="right">
            <label class="Validform_label">
                充值流量:
            </label>
        </td>
        <td class="value" colspan="8">
            <input id="flowValue" name="flowValue" type="text"
                   style="width: 150px;ime-mode:Disabled" class="inputxt"

                   onkeyup="this.value=/^\-?\d*\.?\d?$/.test(this.value) ? this.value: ''"
                   onafterpaste="this.value=/^\-?\d*\.?\d?$/.test(this.value) ? this.value: ''">
                <%--<input value="不超过xxx.0M" onFocus="if(value==defaultValue){value='';this.style.color='#000'}"--%>
                <%--onBlur="if(!value){value=defaultValue; this.style.color='#999'}" style="color:#999"--%>
                <%--id="flowValue" name="flowValue"/>--%>
            <span class="Validform_checktip">只能输入数字</span>
            <label class="Validform_label" style="display: none;">充值流量</label>
        </td>
    </tr>
    <tr>
        <td align="right">
            <label class="Validform_label">
                描述:
            </label>
        </td>
        <td class="value" colspan="8">
            <input id="des" name="des" type="text"
                   style="width: 150px; class="inputxt">
                <%--<input value="不超过xxx.0M" onFocus="if(value==defaultValue){value='';this.style.color='#000'}"--%>
                <%--onBlur="if(!value){value=defaultValue; this.style.color='#999'}" style="color:#999"--%>
                <%--id="flowValue" name="flowValue"/>--%>
            <label class="Validform_label" style="display: none;">描述</label>
        </td>
    </tr>
</table>
    <%--<button type="submit" onclick=></button>--%>
</t:formvalid>
<script type="text/javascript">

    function checkFlow() {
        var myreg = /^(\-?)+(0|0.[1-9]{0,1}|([1-9][0-9]*)+(\.{0,1}[0-9]{0,1}))$/;
        var flowValue = document.getElementById("flowValue").value;
        if (!myreg.test(flowValue)) {
            alert('请输入有效的数值,只能输入到小数点后一位！');
            return false;
        }else{return true;}
    }
    $("#flowValue").blur(function () {
            $.ajax({
                url: "weixinAcctFlowAccountController.do?checkFlowValue",
                method: "POST",
                dataType: "JSON",
                data: {
                    "flowValue": $("#flowValue").val(),
                    "flowtype": $("#flowtype").val()
                },
                success: function (data) {
                    if (data.msg != "操作成功") {
                        $("#flowValue").val("");
                        alert(data.msg);
                    }
                }
            });
    });
</script>


