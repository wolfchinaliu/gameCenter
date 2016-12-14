<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
  <t:base type="jquery,easyui,tools"></t:base>
  <script type="text/javascript">
  	var textList = "${textList}";
  	var newsList = "${newsList}";
	var imgList="${imgList}";
  	$(document).ready(function(){
  		
  		var newsHtml = $("#newsId").html();
  	    var textHtml = $("#textId").html();
		var imgHtml=$("#imgId").html();
  	    
  		$("#msgType").change(function(){
  			var value = $(this).val();
  			
  			if("text"==value){
  				$("#textId").removeAttr("style");
  				$("#textContent").removeAttr("disabled");
  				$("#newsId").attr("style","display:none");
  				$("#newsContent").attr("disabled","disabled");
				$("#imgId").attr("style","display:none");
				$("#imgContent").attr("disabled","disabled");
  			}
			if("news"==value){
				$("#newsId").removeAttr("style");
				$("#newsContent").removeAttr("disabled");
  				$("#textId").attr("style","display:none");
  				$("#textContent").attr("disabled","disabled");
				$("#imgId").attr("style","display:none");
				$("#imgContent").attr("disabled","disabled");
  			}
			if("image"==value){
				$("#imgId").removeAttr("style");
				$("#imgContent").removeAttr("disabled");
				$("#textId").attr("style","display:none");
				$("#textContent").attr("disabled","disabled");
				$("#newsId").attr("style","display:none");
				$("#newsContent").attr("disabled","disabled");
			}
  			
  		});
  	  
  	  var lx = "${lx}";
  	  if(lx=='text'){
  	  	$("#textId").removeAttr("style");
		$("#textContent").removeAttr("disabled");
		$("#newsId").attr("style","display:none");
		$("#newsContent").attr("disabled","disabled");
		  $("#imgId").attr("style","display:none");
		  $("#imgContent").attr("disabled","disabled");
  	  }else if(lx=='news'){
  	  	$("#textId").attr("style","display:none");
		$("#textContent").attr("disabled","disabled");
		$("#newsId").removeAttr("style");	
		$("#newsContent").removeAttr("disabled");
		  $("#imgId").attr("style","display:none");
		  $("#imgContent").attr("disabled","disabled");
  	  }else if(lx=='image'){
		  $("#textId").attr("style","display:none");
		  $("#textContent").attr("disabled","disabled");
		  $("#newsId").attr("style","display:none");
		  $("#newsContent").attr("disabled","disabled");
		  $("#imgId").removeAttr("style");
		  $("#imgContent").removeAttr("disabled");
	  }
  	  
  	});
  </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">

  <t:formvalid formid="gzfrom" dialog="true" usePlugin="password" layout="table" action="subscribeController.do?su">
   <input id="id" name="id" type="hidden" value="${id}">
   <input id="accountid" name="accountid" type="hidden"  value="${subscribe.accountid}">
   <input id="templateName" name="templateName"  type="hidden" value="${subscribe.templateName}"/>
   <table style="width:600px;" cellpadding="0" cellspacing="1" class="formtable">
    
    <tr>
     <td align="right" width="100px">
      <label class="Validform_label">
     消息类型:
      </label>
     </td>
     <td class="value">
          <select name="msgType" id="msgType">
           
          	<option value="text"  <c:if test="${subscribe.msgType=='text'}">selected="selected"</c:if>>文本消息</option>
          	<option value="image"  <c:if test="${subscribe.msgType=='image'}">selected="selected"</c:if>>图片消息</option>
          	<option value="news"  <c:if test="${subscribe.msgType=='news'}">selected="selected"</c:if>>图文消息</option>

          </select>
     </td>
    </tr>
    
      <tr>
     <td align="right">
      <label class="Validform_label">
    	 消息模板:
      </label>
     </td>
     <td class="value" id="textId">
          <select name="templateId" id="textContent">
          		<c:forEach items="${textList}" var="textVal">
          			<option value="${textVal.id}" name="textMsg" <c:if test="${templateId==textVal.id}">selected="selected"</c:if>>${textVal.templateName}</option>
          		</c:forEach>
          </select> (如果没有消息模板，请在素材管理-文本管理中创建)
     </td>

		<td class="value" style="display:none"  id="imgId">
		  <select name="templateId" id="imgContent"  disabled="disabled">
			  <c:forEach items="${imgList}" var="imgVal">
				  <option value="${imgVal.id}" name="textMsg" <c:if test="${templateId==imgVal.id}">selected="selected"</c:if>>${imgVal.mediaId}</option>
			  </c:forEach>
		  </select> (如果没有消息模板，请在素材管理-文本管理中创建)
		</td>

     <td class="value" style="display:none" id="newsId">
          <select name="templateId" id="newsContent" disabled="disabled">
          		<c:forEach items="${newsList}" var="newsVal">
          			<option value="${newsVal.id}" <c:if test="${templateId==newsVal.id}">selected="selected"</c:if>>${newsVal.templateName}</option>
          		</c:forEach>
          </select> (如果没有消息模板，请在素材管理-文本管理中创建)
     </td>
    </tr>
    
   </table>
  </t:formvalid>
 </body>