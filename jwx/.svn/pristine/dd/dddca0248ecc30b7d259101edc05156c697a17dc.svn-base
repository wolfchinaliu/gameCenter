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
             layout="table" action="ipLimitController.do?doUpdate" tiptype="1">
  <input id="id" name="id" type="hidden" value="${ipLimit.id}">
  <input type="hidden" id="createDate" name="createDate" value="${ipLimit.createDate}" >
  <input type="hidden" id="acctid" name="acctid" value="${ipLimit.acctid}">

  <table style="width: 600px;" cellpadding="0" cellspacing="1"
         class="formtable">
    <tr>
      <td align="right"><label class="Validform_label"> ip地址:
      </label></td>
      <td class="value" colspan="3"><input class="inputxt" id="ip" datatype="*"
                                           name="ip" type="text" value="${ipLimit.ip}"> <span class="Validform_checktip"></span></td>
    </tr>
  </table>
</t:formvalid>
</body>
