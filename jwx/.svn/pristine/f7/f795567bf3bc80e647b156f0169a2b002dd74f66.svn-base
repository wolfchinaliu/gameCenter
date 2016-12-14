<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" href="plug-in/accordion/css/icons.css" type="text/css"></link>
<link rel="stylesheet" href="plug-in/accordion/css/menu-icons.css" type="text/css"></link>
<link rel="stylesheet" href="plug-in/accordion/css/accordion.css" type="text/css"></link>
<style type="text/css">
.style0{
	background-color:#F2F2F2;padding-left: 15px;padding-bottom: 10px;padding-top: 5px;cursor:pointer;
}
.style1{
	padding-left: 15px;padding-bottom: 10px;padding-top: 5px;cursor:pointer;
}
.menu_title{
	padding-left: 15px;padding-bottom: 10px;padding-top: 5px;cursor:pointer;
}
</style>

<script type="text/javascript" src="plug-in/accordion/js/leftmenu.js"></script>
<script type="text/javascript">

$('#aa').accordion({    
	animate:true,
	fit:true,
	border:false
	});
	

</script>

    
<div id="aa" class="easyui-accordion">
     

   	
   	<%-- 
   	
   	<c:forEach items="${functionList}" var="function" varStatus="count">
 	<c:if test="${function.functionLevel == 0}">
	   	<div title="${function.functionName}" data-options="iconCls:'pictures'" style="overflow:auto;padding:10px;">
	   		<c:forEach items="${functionList}" var="function2" varStatus="count2">
	   		<c:if test="${function2.functionLevel == 1 && function2.TSFunction.id == function.id}">
	 			<a href="#" onclick="addTab('${function2.functionName}','${function2.functionUrl}','');"><div class="menu_title" onmousemove="this.className='style0';" onmouseout="this.className='style1'"><img src="${function2.TSIcon.iconPath}" style="vertical-align:bottom;"> ${function2.functionName}</div></a>
			</c:if>
			</c:forEach>
	 	</div>
	</c:if>
   	</c:forEach>
   	
   	<c:forEach items="${functionList}" var="function" varStatus="count">
 	<c:if test="${function.functionLevel == 0}">
	   	<div title="${function.functionName}" data-options="iconCls:'pictures'" style="overflow:auto;padding:10px;">
	   		<c:forEach items="${functionList}" var="function2" varStatus="count2">
	   		<c:if test="${function2.functionLevel == 1 && function2.TSFunction.id == function.id}">
	 			<a href="#" onclick="addTab('${function2.functionName}','${function2.functionUrl}','');"><div class="menu_title" onmousemove="this.className='style0';" onmouseout="this.className='style1'"><img src="${function2.TSIcon.iconPath}" style="vertical-align:bottom;"> ${function2.functionName}</div></a>
			</c:if>
			</c:forEach>
	 	</div>
	</c:if>
   	</c:forEach>
   	--%>
   	<c:forEach items="${functionList}" var="function" varStatus="count">
 	<c:if test="${function.functionLevel == 0}">
	   	<div title=" ${function.functionName}" data-options="iconCls:'${function.accordionCoin}'" style="overflow:auto;padding:20px;" >
	   		<c:forEach items="${functionList}" var="function2" varStatus="count2">
	   		<c:if test="${function2.functionLevel == 1 && function2.TSFunction.id == function.id}">
	 			<a href="#" onclick="addTab('${function2.functionName}','${function2.functionUrl}','');"><div class="menu_title" onmousemove="this.className='style0';" onmouseout="this.className='style1'"><img src="${function2.menuCoin}" style="vertical-align:bottom;border: 0;"> ${function2.functionName}</div></a>
			</c:if>
			</c:forEach>
	 	</div>
	</c:if>
   	</c:forEach>
</div>

