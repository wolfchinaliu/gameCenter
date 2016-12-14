<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" layout="div" dialog="true" action="helpController.do?doSaveStyleSet" beforeSubmit="validaterForm">
<input type="hidden" id="showPoint" name="showPoint">
	
	<div class="form">
		<label class="form"> 显示操作提示： </label> 
		
			<input type="checkbox" id=checkboxID name="checkboxID" value="0" <c:if test='${isShowpoint == 0}'>checked="checked"</c:if>>
		
		
	</div>
	
	
	
</t:formvalid>
</body>
</html>

<SCRIPT type="text/javascript">
	function validaterForm(){

		if(document.getElementById("checkboxID").checked){
			
			document.getElementById("showPoint").value = "0";
		}else{

			document.getElementById("showPoint").value = "1";
		}
	}
</SCRIPT>