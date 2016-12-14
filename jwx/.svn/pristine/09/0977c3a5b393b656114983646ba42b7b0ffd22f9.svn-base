<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>流量卡下发</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
    <script type="text/javascript">
        function viewAll() {
            $('#dgquyu').dialog('open').dialog('setTitle', '选择区域');
        }
    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="flowCardInfoController.do?doAdd"
             tiptype="1">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <strong>批次号：</strong>
            </td>
            <td class="value">
                <select name="batchNo" class="select1" id="batchNo">
                    <c:forEach items="${lis}" var="itemeach">
                        <option value="${itemeach.id}">${itemeach.batchNo}</option>
                    </c:forEach>
                </select>
            </td>


        </tr>
        <tr>
            <td align="right">
                <strong>张数: </strong>
            </td>
            <td class="value">
                <input id="callPhone" name="callPhone" type="text"
                       style="width: 150px" class="inputxt"
                        >
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">电话</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <strong>下发商户：</strong>
            </td>
            <td class="value">
                <select name="accountName" class="select1" id="accountName">
                    <c:forEach items="${liRb}" var="itemeach">
                        <option value="${itemeach.id}">${itemeach.accountName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
</t:formvalid>

</body>
</html>
