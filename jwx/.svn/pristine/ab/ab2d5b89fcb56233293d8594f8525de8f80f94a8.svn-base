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
</head>
<body>
  <div style="display: none;"></div>
  <div class="main_bd">
    <div class="media_edit_area" id="js_appmsg_editor">
      <div class="appmsg_editor" style="margin-top: 0px;">
        <div class="inner" align="center">
        <label><strong >广告名称:${ad.title }</strong></label>
        <label align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;覆盖商户</label>
          <t:formvalid formid="formobjNew" dialog="true" usePlugin="password" refresh="true" layout="table" action="advertisementController.do?addOrUpdate">
            <input id="id" name="id" type="hidden" value="${ad.id }">
            <table style="width: 450px;" border="1" cellpadding="0" cellspacing="1" class="formtable">
              <tr style="height:30px;">
                <td align="center"><strong> 序号</strong></td>
                <td align="center"><strong>商家名称</strong></td>
              </tr>
              <c:forEach items="${merchantList }" var="m" varStatus="v">
              <tr style="height:30px;">
                <td  align="center">${v.index+1 }</td>
                <td align="center">${m.name }</td>
              </tr>        
              </c:forEach>
            </table>
          </t:formvalid>
        </div>
      </div>
    </div>
  </div>
</body>