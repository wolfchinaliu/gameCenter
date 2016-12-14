<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<title>新增广告素材</title>
<t:base type="jquery,easyui,tools"></t:base>
<link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
<link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="plug-in/weixin/button.css" />
<!--     <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script> -->

<!--fileupload-->
<script type="text/javascript" src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="plug-in/weixin/js/jquery.iframe-transport.js"></script>
<!--UEditor-->
<script type="text/javascript" src="plug-in/jquery-plugs/form/jquery.form.js"></script>

<script type="text/javascript">
        $(function () {
        	 $('#materialType').combobox({
         		url: 'dataDict.do?materialType',
         		editable: false, //不可编辑状态
         		cache: false,
         		valueField: 'code',
         		textField: 'name',
         		resizable:true
         	});
        	 $('#status').combobox({
         		url: 'dataDict.do?status',
         		editable: false, //不可编辑状态
         		cache: false,
         		valueField: 'code',
         		textField: 'name',
         		resizable:true
         	});
        	 $('#auditStatus').combobox({
           		url: 'dataDict.do?auditStatus',
           		editable: false, //不可编辑状态
           		cache: false,
           		valueField: 'code',
           		textField: 'name',
           		resizable:true
           	});
        	 $("#materialType").combobox("setValue", "${material.materialType}");
        	 $("#status").combobox("setValue", "${material.status}");
        });
    </script>
</head>
<body>
  <div style="display: none;"></div>
  <div class="main_bd">
    <div class="media_edit_area" id="js_appmsg_editor">
      <div class="appmsg_editor" style="margin-top: 0px;">
        <div class="inner">
        <t:formvalid formid="formobjNew" dialog="true" layout="table" action="materialController.do?audit&id=${material.id }" tiptype="1">
            <input id="ID" name="id" type="hidden" value="">
            
            <table style="width: 450px;" cellpadding="0" cellspacing="1" class="formtable">
              <tr>
                <td align="right"><label class="Validform_label">缩略图:</label></td>
                <td class="value"><img src="${pic}" height="200"></td>
              </tr>
              <tr>
                <td align="right"><label class="Validform_label">素材名称:</label></td>
                <td class="value">${material.title }</td>
              </tr>
              <tr>
              <td align="right"><label class="Validform_label"> 素材类型: </label></td>
              <td class="value"><input class="inputxt" id="materialType" name="materialType" disabled="disabled" style="width: 305px; height: 30px;" /></td>
              </tr>
              <tr>
                <td align="right"><label class="Validform_label">制作商户: </label></td>
                <td class="value">${material.merchantName }</td>
              </tr>
              <tr>
                <td align="right"><label>链接地址</label></td>
                <td class="value"><a target="_blank" href="${url }">  ${urlTitle } </a></td>
              </tr>
              <tr>
                <td align="right"><label>提交时间</label></td>
                <td class="value">${material.commitDate }</td>
              </tr>
              <tr>
                <td align="right"><label>当前状态</label></td>
                <td class="value"><input class="inputxt" id="status" name="status" style="width: 305px; height: 30px;"/></td>
              </tr>
              <tr>
                <td align="right"><label>审核结果</label></td>
                <td class="value"><input class="inputxt" id="auditStatus" name="auditStatus"  style="width: 305px; height: 30px;"/></td>
              </tr>
              <tr>
                <td align="right"><label>简介</label></td>
                <td class="value"><input id=auditInfo name="auditInfo" type="text" value='<c:if test="${empty material.auditInfo }">简单说明原因</c:if>' 
                                  onfocus="if(value=='简单说明原因'){value=''}" onblur="if(value=='') {value='简单说明原因'} " style="width: 300px;height:30px;" class="inputxt" /></td>
              </tr>
              </tbody>
            </table>
          </t:formvalid>
        </div>
      </div>
    </div>
  </div>
</body>