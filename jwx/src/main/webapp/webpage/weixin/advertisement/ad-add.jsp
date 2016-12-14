<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<title>新增广告</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
<link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css" />

<script type="text/javascript" src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="plug-in/weixin/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="plug-in/jquery-plugs/form/jquery.form.js"></script>
<script type="text/javascript">
        $(function () {
            $('#materialId').combobox({
        		url: 'materialController.do?usefulMaterial',
        		editable: false, //不可编辑状态
        		cache: false,
        		valueField: 'code',
        		textField: 'name',
        		resizable:true
        	});
            $('#merchants').combobox({
        		url: 'weixinAcctController.do?subAcct',
        		editable: false, //不可编辑状态
        		cache: false,
        		valueField: 'code',
        		textField: 'name',
        		resizable:true,
        		multiple:true
        	});
            $("#materialId").combobox("setValue", "${ad.materialId}");
            $("#merchants").combobox("setValues", ${merchants});
        });
    </script>
</head>
<body>
  <div style="display: none;"></div>
  <div class="main_bd">
    <div class="media_edit_area" id="js_appmsg_editor">
      <div class="appmsg_editor" style="margin-top: 0px;">
        <div class="inner">
          <t:formvalid formid="formobjNew" dialog="true" usePlugin="password" refresh="true" layout="table" action="advertisementController.do?addOrUpdate">
            <input id="id" name="id" type="hidden" value="${ad.id }">
            <table style="width: 450px;" cellpadding="0" cellspacing="1" class="formtable">
              <tr>
                <td align="right"><label class="Validform_label">广告名称:</label></td>
                <td class="value"><input id="title" name="title" type="text" value="${ad.title }" style="width: 250px;height:30px;">
                                  <span class="Validform_checktip"></span></td>
              </tr>
              <tr>
                <td align="right"><label class="Validform_label">广告素材: </label></td>
                <td class="value"><input class="inputxt" id="materialId" name="materialId" style="width: 250px; height: 30px;"
                    value=""> <span class="Validform_checktip"></span></td>
              </tr>
              <tr>
                <td align="right"><label class="Validform_label">投放位置: </label></td>
                <td class="value"><t:dictSelect field="position" typeGroupCode="ad_pos" hasLabel="false" extendJson="{style:'width:250px'}" defaultVal="${ad.position }"></t:dictSelect></td>
              </tr>
              <c:if test="${currAcct.adAgency=='1' }">
              <tr>
                <td align="right"><label class="Validform_label">投放商户: </label></td>
                <td class="value"><input class="inputxt" id="merchants" name="merchants" style="width: 250px; height: 30px;"
                    value="">
              </tr>
              </c:if>
              <tr>
                <td align="right"><label class="Validform_label">开始时间: </label></td>
                <td class="value"><input class="Wdate" onClick="WdatePicker({minDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"
                                             style="width: 250px;height:30px;" id="periodBegin" name="periodBegin"
                                             value="<fmt:formatDate value='${ad.periodBegin}' type="date" pattern="yyyy-MM-dd"/>"> 00:00:00
                        <span class="Validform_checktip"></span></td>
              </tr>
              <tr>
                <td align="right"><label class="Validform_label">结束时间: </label></td>
                <td class="value"><input class="Wdate" onClick="WdatePicker({minDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"
                                             style="width: 250px;height:30px;" id="periodEnd" name="periodEnd"
                                             value="<fmt:formatDate value='${ad.periodEnd}' type="date" pattern="yyyy-MM-dd"/>"> 23:59:59
                        <span class="Validform_checktip"></span></td>
              </tr>
            </table>
          </t:formvalid>
        </div>
      </div>
    </div>
  </div>
</body>