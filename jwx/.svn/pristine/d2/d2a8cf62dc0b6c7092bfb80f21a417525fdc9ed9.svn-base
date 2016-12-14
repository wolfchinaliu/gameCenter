<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<!DOCTYPE html>
<html>
<head>
    <title>当前商户流量</title>

</head>

<style type="text/css">
    body {
        /*text-align: center;*/
        align:center;
    }

    table {
        border-collapse: collapse;
        border: 1px solid black;

    }

    th {
        border: 1px solid black;
    }

    td {
        border: 1px solid black;
    }
</style>
<body >
<table style="width: 25%;height: 10%;margin-top:30;margin-left:20;" vertical-align="middle">
    <tr style="border:none">
        <th style="width: 80px;" valign="middle"></th>
        <th style="width: 80px;" valign="middle">全国流量</th>
        <th style="width: 80px;" valign="middle">省内流量</th>
    </tr>
    <tr style="border:none">
        <td style="width: 80px;" align="center" valign="middle">账户剩余流量值</td>
        <td style="width: 80px;" align="center" valign="middle">${weixinAcctFlowChargePage.countryFlowValue==null?0.0:weixinAcctFlowChargePage.countryFlowValue}M</td>
        <td style="width: 80px;" align="center" valign="middle">${weixinAcctFlowChargePage.provinceFlowValue==null?0.0:weixinAcctFlowChargePage.provinceFlowValue}M</td>
    </tr>
    <tr style="border:none">
        <td style="width: 80px;" align="center" valign="middle">账户累计充值总流量值</td>
        <td style="width: 80px;" align="center" valign="middle">${totalCharge.country==null?0.0:totalCharge.country}M</td>
        <td style="width: 80px;" align="center" valign="middle">${totalCharge.province==null?0.0:totalCharge.province}M</td>
    </tr>
</table>


<table style="width: 25%;height: 10%;margin-top:50;margin-left:20;" vertical-align="middle">
    <tr style="border:none">
        <th style="width: 80px;" valign="middle">流量卡(全国)</th>
        <th style="width: 80px;" valign="middle">流量卡(省内)</th>
    </tr>
    <tr style="border:none">
        <td style="width: 80px;" align="center" valign="middle">${weixinAcctFlowChargePage.countryFlowCardValue==null?0.0:weixinAcctFlowChargePage.countryFlowCardValue}M</td>
        <td style="width: 80px;" align="center" valign="middle">${weixinAcctFlowChargePage.provinceFlowCardValue==null?0.0:weixinAcctFlowChargePage.provinceFlowCardValue}M</td>
    </tr>
</table>
</body>
</html>
