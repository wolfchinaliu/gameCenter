<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<style>
<!--
ul{list-style-type:none; margin:0;width:100%; }
ul li{float:left;margin-left:10px;text-align: center;margin-right: 10px;margin-bottom: 10px;border: 1;font-weight: 700;border:1px solid #ccc;}
.img-1 {width: 150px;height: 80px;}
.img-2 {width: 150px;height: 26px;}
-->
</style>
<body onload="loadRadio()">
<ul id="img">
<c:forEach items="${newsTemplateList}" var="newsTemplate" varStatus="s">

<a href="javascript:void(0);" >
	<li onclick="clickfn(this)">
	<c:forEach items="${newsTemplate.newsItemList}" var="newsItem" varStatus="c">
	
		<c:if test="${c.count == 1}">
				<img alt="${newsItem.title}" src="${mediaurl}/${newsItem.imagePath}" class="img-1">
		</c:if>
		<c:if test="${c.count == 2}">
				<br>
				<img alt="${newsItem.title}" src="${mediaurl}/${newsItem.imagePath}" class="img-2">
		</c:if>
		<c:if test="${fn:length(newsTemplate.newsItemList)<2}"><br><br></c:if>
	
	</c:forEach>
	<br><input type="radio" name="media_id" id="media_id" value="${newsTemplate.mediaId}"  >
	${newsTemplate.templateName}
	</li>
</a>
</c:forEach>
</ul>

</body>
<script type="text/javascript">
function clickfn(objL){
	 
	objL.style.borderColor="gray";
	//objL.style.backgroundColor="gray";
	//objL.style.color="#FFF";
	
}
	
function readNews(id){

	var url = "weixinArticleController.do?goMessage&templateId="+id;
	createdetailwindow('图文编辑','weixinArticleController.do?goMessage&templateId='+id,'newstemplatelist','100%', '100%');
}

function loadRadio(){
	
	$('input:radio:first').attr('checked', 'checked');

}
function getCheckedNews(){
	
	//var media_id = $("#media_id").val();
	//var imagePath = $("#media_id").val();

	var media_id = $("input[type='radio']:checked").val();

	var result=[media_id,media_id];

	return result;
}
</script>

