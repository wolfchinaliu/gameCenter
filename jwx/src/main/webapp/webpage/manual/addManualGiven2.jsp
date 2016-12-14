<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>添加手工送流量</title>
  <t:base type="jquery,easyui,tools"></t:base>
	 <link rel="stylesheet" type="text/css" href="easyui/jquery-easyui-theme/default/easyui.css">
	 <link rel="stylesheet" type="text/css" href="easyui/jquery-easyui-theme/icon.css">
	 <%--<script type="text/javascript" src="easyui/jquery/jquery-2.1.1.min.js"></script>--%>
	 <script type="text/javascript" src="easyui/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
	 <script type="text/javascript" src="easyui/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
 <script type="text/javascript">
 	$(document).ready(function(){ 
 	   var msgType = "${msgType}";
 	   var templateId = "${templateId}";
 	   var supMenuId="${fatherId}";
 	   if(templateId){
 	   		var templateObj = $("#templateId");
	 		templateObj.html("");
	 		$.ajax({
	 			url:"menuManagerController.do?gettemplate",
	 			data:{"msgType":msgType},
	 			dataType:"JSON",
	 			type:"POST",
	 			success:function(data){
	 				var msg="";
	 				for(var i=0;i<data.length;i++){
	 				   
	 				    if(templateId == data[i].id){
	 				    	if(msgType=="expand"){
	 				    		msg += "<option value='"+data[i].id+"' selected='selected'>"+data[i].name+"</option>";
	 				    	}else{
	 				    		msg += "<option value='"+data[i].id+"' selected='selected'>"+data[i].templateName+"</option>";
	 				    	}
	 				    }else{
		 				    if(msgType=="expand"){
		 				    	msg += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
		 				    }else{
		 				    	msg += "<option value='"+data[i].id+"'>"+data[i].templateName+"</option>";
		 				    }
	 				    }
	 				}
	 				templateObj.html(msg);
	 			}
	 		});
 	   }
 	
 	
 	   
 	   if("${menuEntity.id}"==""){
 	   		var supMenuIdObj = $("#fatherName");
	 		supMenuIdObj.html("");
	 		$.ajax({
	 			url:"menuManagerController.do?getSubMenuByGroup",
	 			data:{"msgType":msgType,"menuGroupId":"${menuGroupId}"},
	 			dataType:"JSON",
	 			type:"POST",
	 			success:function(data){
	 				var msg="<option value=''>一级菜单</option>";
	 				for(var i=0;i<data.length;i++){
	 							//判断是否为修改之前选择的上级,更改为选中状态
								if(data[i].id==supMenuId){
									msg += "<option value='"+data[i].id+"' selected='selected'>"+data[i].name+"</option>";
								}else{
									msg += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
								}	 				   			
	 				    		
	 				   
	 				}
	 				supMenuIdObj.html(msg);
	 			}
	 		});
 	   }
 	   function setView(){
 		  $("#msgType").removeAttr("datatype");
			$("#templateId").removeAttr("datatype");
			$("#url").removeAttr("disabled");
			$("#trurl").removeAttr("style");
			$("#url").attr("datatype","url");
			$("#xxtr").attr("style","display:none");
			var inputAttr = $("input[name='msgType']");
			for(var i=0;i<inputAttr.length;i++){
				$(inputAttr[i]).attr("disabled","disabled");
			}
			
			$("#templateTr").attr("style","display:none");
			$("#templateId").attr("disabled","disabled");
		   $("#pagetype").attr("disabled","disabled");
		   $("#pagetr").attr("style","display:none");

		   $("#pageurl").attr("disabled","disabled");
		   $("#pagetrr").attr("style","display:none");
 	   }
 	   function setClick(){
 		  $("#url").attr("disabled","disabled");
			$("#trurl").attr("style","display:none");
		   $("#pagetr").attr("style","display:none");
		   $("#pagetype").attr("disabled","disabled");

		   $("#pageurl").attr("disabled","disabled");
		   $("#pagetrr").attr("style","display:none");

			$("#xxtr").removeAttr("style");
			var inputAttr = $("input[name='msgType']");
			for(var i=0;i<inputAttr.length;i++){
				$(inputAttr[i]).removeAttr("disabled");
			}
			$("#templateTr").removeAttr("style");
			$("#templateId").removeAttr("disabled");
			//设置消息类型和验证
			$("#msgType").attr("datatype","*");
			$("#templateId").attr("datatype","*");
			$("#url").removeAttr("datatype");
 	   }
		function setViewToo(){
			$("#templateTr").removeAttr("style");
			$("#templateId").removeAttr("disabled");
			$("#templateId").removeAttr("datatype");
			$("#msgType").removeAttr("datatype");
			$("#url").attr("disabled","disabled");
			$("#trurl").attr("style","display:none");
			$("#templateTr").attr("style","display:none");
			$("#templateId").attr("disabled","disabled");
			$("#url").removeAttr("datatype");

			$("#pagetype").removeAttr("disabled");
			$("#pagetr").removeAttr("style");

			$("#pageurl").attr("disabled","disabled");
			$("#pagetrr").attr("style","display:none");
			$("#xxtr").attr("style","display:none");
//			$("#pagetype").combobox("setValue", '');
//			var jumptypevalue = document.getElementById("type").value;
//			if(jumptypevalue=="viewtoo") {
				$.ajax({
					type: "POST",
					url: 'weixinUrlController.do?weixinUrlFlow',
					cache: false,
					async: true,
					dataType: "json",
					success: function (data) {
						$("#pagetype").combobox("loadData", data);
						return;
					}
				});
//			}else {


			}

		function setViewPlay(){
			$("#templateTr").removeAttr("style");
			$("#templateId").removeAttr("disabled");

			$("#templateId").removeAttr("datatype");
			$("#msgType").removeAttr("datatype");
			$("#url").attr("disabled","disabled");
			$("#trurl").attr("style","display:none");
			$("#templateTr").attr("style","display:none");
			$("#templateId").attr("disabled","disabled");
			$("#url").removeAttr("datatype");

			$("#pageurl").removeAttr("disabled");
			$("#pagetrr").removeAttr("style");

			$("#pagetype").attr("disabled","disabled");
			$("#pagetr").attr("style","display:none");
			$("#xxtr").attr("style","display:none");


			$.ajax({
				type: "POST",
				url: 'weixinUrlController.do?weixinUrlLottery',
				cache: false,
				async: true,
				dataType: "json",
				success: function (data) {
					$("#pageurl").combobox("loadData", data);
					return;
				}
			});
		}

			$('#pagetype').combobox({
				editable: false, //不可编辑状态
				cache: false,
				valueField: 'url',
				resizable:true,
				textField: 'name',
				itemIndex:0
			});

		$('#pageurl').combobox({
			editable: false, //不可编辑状态
			cache: false,
			valueField: 'url',
			resizable:true,
			textField: 'name',
			itemIndex:0
		});

			var inputAttr = $("input[name='msgType']");
			for(var i=0;i<inputAttr.length;i++){
				$(inputAttr[i]).attr("disabled","disabled");
			}
			$("#xxtr").attr("style","display:none");





 	  if($("#type").val()=='view'){
  		 setView();
  	   }else if($("#type").val()=='click'){
  		  setClick();
  	   }else if( $("#type").val()=='viewtoo'){
		  setViewToo();
	  }else{
		  setViewPlay();
	  }
 		$("#type").click(function(){
 			var selectValue = $(this).children('option:selected').val();
 			console.info(selectValue);
 			if("click"==selectValue){
 				setClick();
 			}else if("view"==selectValue){
 				setView();
 				//取消验证。防止无法提交
 			}else if("viewtoo"==selectValue){
				setViewToo();
			}else{
				setViewPlay();
			}
 		});
 		
 		var inputAttr = $("input[name='msgType']");
		for(var i=0;i<inputAttr.length;i++){
			$(inputAttr[i]).click(function(){
			   var textVal = $(this).val();
			   if("text"==textVal){
			   		getTemplates("text");
			   }else if("expand"==textVal){
			   		getTemplates("expand");
			   }else if("news"==textVal){
			   		getTemplates("news");
			   }
			});
		}
	
		//获取动作设置选中的项,用于初始化依赖dom元素
		var typeVal = $("#type").val(); // 动作设置选中项的值
		if("click" == typeVal){
			$("#xxtr").show();
			$("#trurl").hide();
			$("#pagetr").hide();
			$("#pagetrr").hide();
		}else if("view" == typeVal){
			$("#xxtr").hide();
			$("#trurl").show();
			$("#pagetr").hide();
			$("#pagetrr").hide();
		}else if("viewtoo"==typeVal){
			$("#xxtr").hide();
			$("#trurl").hide();
			$("#pagetr").show();
			$("#pagetrr").hide();
		}else{
			$("#xxtr").hide();
			$("#trurl").hide();
			$("#pagetrr").show();
			$("#pagetr").hide();
		}
			
			
 	});
 	
 	function getTemplates(msgType){
 		var templateObj = $("#templateId");
 		templateObj.html("");
 		$.ajax({
 			url:"menuManagerController.do?gettemplate",
 			data:{"msgType":msgType},
 			dataType:"JSON",
 			type:"POST",
 			success:function(data){
 				var msg="";
 				msg += "<option value=''>------</option>";
 				for(var i=0;i<data.length;i++){
 					if(msgType=="expand"){
 					 	msg += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
 					}else{
 					 	msg += "<option value='"+data[i].id+"'>"+data[i].templateName+"</option>";
 					}
 				}
 				templateObj.html(msg);
 			}
 		});
 	}

//	 $(function(){
////		 var pagetype=document.getElementById("hidpage").value;
////		 var a="jwx/"
////		 var subpagetype=pagetype.substring(pagetype.indexOf(a)+4,pagetype.indexOf("&"));
//////		 alert(subpagetype);
////		 $("#pagetype").combobox("setValue", "");
//	 });
 </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">


 <input id="hidpage" name="hidpage" type="hidden" value="${pagetype}">
 <input id="hidpageurl" name="hidpageurl" type="hidden" value="${pageurl}">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="menuManagerController.do?su" tiptype="1">
	<input id="menuEntity_id" name="weixinMenuGroupEntity.id" type="hidden" value="${menuGroupId}">
  <c:if test="${menuEntity.id!=null}">
  	<input id="id" name="id" type="hidden" value="${menuEntity.id}">
  </c:if>
  
   <c:if test="${fatherId!=null}">
  	<input id="fatherId" name="fatherId" type="hidden" value="${fatherId}">
  </c:if>
  <c:if test="${accountid!=null}">
  	<input id="accountid" name="accountid" type="hidden" value="${accountid}">
  </c:if>
   <table style="width:100%" cellpadding="0" cellspacing="1" class="formtable"> 
     <tr>
     <td align="right" style="width:65px">
      <label class="Validform_label">
     	  上级菜单:
      </label>
     </td>
     <td colspan="3" class="value">
     <c:if test="${menuEntity.id!=null}">
   	  	${fatherName==null?"一级菜单":fatherName}
     </c:if>
      <c:if test="${menuEntity.id==null}">
         <select name="fatherName" id="fatherName">
      	
     	 </select>
     	  <span class="Validform_checktip">请输入上级菜单！</span>
     </c:if>
     <!--   <input id="c" class="inputxt"  name="fatherName"  value="${fatherName}">-->
     
     </td>
    </tr>
    
    <tr>
     <td align="right" style="width:65px">
      <label class="Validform_label">
      动作设置:
      </label>
     </td>
     <td colspan="3" class="value">
      <select name="type" id="type">
        <c:forEach></c:forEach>
      	<option value="click"  <c:if test="${type=='click'}">selected="selected"</c:if>>消息触发类</option>
      	<option value="view"  <c:if test="${type=='view'}">selected="selected"</c:if>>网页链接类</option>
		  <option value="viewtoo"  <c:if test="${type=='viewtoo'}">selected="selected"</c:if>>流量页面类</option>
		  <option value="viewplay"  <c:if test="${type=='viewplay'}">selected="selected"</c:if>>游戏页面类</option>
      </select>
      <span class="Validform_checktip">请设置动作</span>
     </td>
    </tr>
   
    <tr id="trurl">
     <td align="right" style="width:65px">
      <label class="Validform_label">
       URL:
      </label>
     </td>
     <td colspan="3" class="value">
      <input id="url" class="inputxt" name="url" value="${url}"   style="width: 300px"><!-- disabled="disabled"  地址不能编辑？？ 业务不了解。所以先注释掉 --> 
      <span class="Validform_checktip">填写url</span>
     </td>
    </tr>
    
    <tr id="xxtr" style="width:65px">
     <td align="right">
      <label class="Validform_label">
       消息类型:
      </label>
     </td>
     <td class="value" colspan="3">
        <input type="radio" value="text" name="msgType" id="msgType" datatype="*"  <c:if test="${msgType=='text'}">checked="checked"</c:if>/>文本
        <input type="radio" value="news" name="msgType"  <c:if test="${msgType=='news'}">checked="checked"</c:if>/>图文
        <%--<input type="radio" value="expand" name="msgType"  <c:if test="${msgType=='expand'}">checked="checked"</c:if>/>扩展--%>
      <span class="Validform_checktip">选择消息类型</span>
     </td>
    </tr>
    
    <tr id="templateTr" style="width:65px">
     <td align="right">
      <label class="Validform_label">
       选择模板:
      </label>
     </td>
     <td class="value" colspan="3">
      <select name="templateId" id="templateId" datatype="*">
      	
      </select>
      <span class="Validform_checktip">选择模板</span>
     </td>
    </tr>
    
    <tr id="pagetr">
     <td align="right" style="width:65px">
      <label class="Validform_label">
        页面类型:
      </label>
     </td>
     <td class="value" colspan="3">
      <%--<input id="pageurl" class="inputxt" name="pageurl" value="${pageurl}" datatype="*" nullmsg="页面类型不能为空！">--%>
		 <input id="pagetype" name="pagetype" style="width:200px"	class="easyui-combobox" value="${pagetype}">
      <span class="Validform_checktip">请选择页面类型</span>
		  <%--<label class="Validform_label" style="display: none;">页面类型</label>--%>
     </td>
    </tr>

	   <tr id="pagetrr">
		   <td align="right" style="width:65px">
			   <label class="Validform_label">
				   页面类型:
			   </label>
		   </td>
		   <td class="value" colspan="3">
				   <%--<input id="pageurl" class="inputxt" name="pageurl" value="${pageurl}" datatype="*" nullmsg="页面类型不能为空！">--%>
			   <input id="pageurl" name="pageurl" style="width:200px"	class="easyui-combobox" value="${pageurl}">
					   <span class="Validform_checktip">请选择页面类型</span>
				   <%--<label class="Validform_label" style="display: none;">页面类型</label>--%>
		   </td>
	   </tr>
     
    <tr>
     <td align="right" style="width:65px">
      <label class="Validform_label">
        顺序:
      </label>
     </td>
     <td class="value" colspan="3">
      <input id="orders" class="inputxt" name="orders" value="${orders}" >
      <span class="Validform_checktip">填写显示顺序</span>
     </td>
    </tr> 
 
   </table>
  </t:formvalid>
 
 </body>