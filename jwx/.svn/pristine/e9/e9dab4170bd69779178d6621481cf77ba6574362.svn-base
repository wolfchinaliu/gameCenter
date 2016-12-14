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
             layout="table" action="safetyRulesController.do?doUpdate" tiptype="1">
  <input id="id" name="id" type="hidden" value="${safetyRules.id}">
  <input type="hidden" id="createDate" name="createDate" value="${safetyRules.createDate}">
    <input type="hidden" id="acctid" name="acctid" value="${safetyRules.acctid}">

  <table style="width: 600px;" cellpadding="0" cellspacing="1"
         class="formtable">
    <tr>
      <td align="right"><label class="Validform_label"> 规则名称:
      </label></td>
      <td class="value" colspan="3"><input class="inputxt" id="ruleName" datatype="*" disabled="true"
                                           name="ruleName" type="text" value="${safetyRules.ruleName}"> <span class="Validform_checktip"></span></td>
    </tr>
    <tr>
      <td align="right"><label class="Validform_label"> 频率:
      </label></td>
      <td class="value" colspan="3">
        <select class="combo-box" id="frequencyUnit"  name="frequencyUnit" style="width: 150px" disabled="true" >
          <option <c:if test="${safetyRules.frequencyUnit=='天'}">selected="selected"</c:if> >天</option>
          <option <c:if test="${safetyRules.frequencyUnit=='周'}">selected="selected"</c:if> >周</option>
          <option <c:if test="${safetyRules.frequencyUnit=='月'}">selected="selected"</c:if> >月</option>
          <option <c:if test="${safetyRules.frequencyUnit=='年'}">selected="selected"</c:if> >年</option>
        </select>
        <span class="Validform_checktip"></span></td>
    </tr>


    <tr>
      <td align="right"><label class="Validform_label">
        频率内最大接口调用次数: </label></td>
      <td class="value"><input id="frequencyNum" disabled="true"
                               name="frequencyNum" type="text" style="width: 150px"
                               class="easyui-numberbox inputxt" datatype="n" precision="0" value="${safetyRules.frequencyNum}">
        <span class="Validform_checktip"></span> <label
                class="Validform_label" style="display: none;">频率内最大接口调用次数</label></td>
    </tr>
    <tr>
      <td align="right"><label class="Validform_label">
        每次充值流量最大值: </label></td>
      <td class="value"><input id="charegeFlow"
                               name="charegeFlow" type="text" style="width: 150px"
                               class="easyui-numberbox inputxt" datatype="n" precision="0" value="${safetyRules.charegeFlow}">M
        <span class="Validform_checktip"></span> <label
                class="Validform_label" style="display: none;">每次充值流量最大值</label></td>
    </tr>


  </table>
</t:formvalid>
</body>
