<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>

<head>
    <title>添加题目</title>
    <t:base type="jquery,easyui,tools"></t:base>
    <script type="text/javascript">


    </script>
</head>
<body >
<t:formvalid formid="formid" dialog="true"
layout="div" action="weixinGuessRiddlesController.do?doAddRiddle" tiptype="1" >
<div>

    <input id="hdid" name="hdid" type="hidden"
           value="${hdid}">
    <input id="accountid" name="accountid" type="hidden"
           value="${accountid}">
    <table style="width: 400px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <tr>
            <td align="right"><label class="Validform_label"> 谜面:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" id="lanternRon" datatype="*" style="width: 350px"
                                                 name="lanternRon" type="text"> <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 谜目:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt"
                                                 id="lanternReyes" datatype="*" style="width: 350px" name="lanternReyes"
                                                 type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 谜底:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt"
                                                 id="lanternRdown" datatype="*" style="width: 350px" name="lanternRdown"
                                                 type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td colspan="4">
                *谜底如果是多个，请用英文“;”隔开，；例如：元宵;汤圆
            </td>
        </tr>


    </table>

</div>
    </t:formvalid>
</body>
