<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
<t:datagrid name="rtList" actionUrl="receiveTextController.do?datagrid" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false" sortable="false"></t:dgCol>
	<t:dgCol title="昵称" field="nickName" query="true" width="100" sortable="false"></t:dgCol>
	<t:dgCol title="类型" field="MsgType" replace="文本_text,图片_image,位置_location,链接_link" width="100" sortable="false"></t:dgCol>
	<t:dgCol title="时间" field="createTime"  queryMode="group" query="true"  width="100" sortable="false"></t:dgCol>
	<t:dgCol title="内容" field="Content" width="100" sortable="false"></t:dgCol>
	<t:dgCol title="回复内容" field="rescontent" width="100" sortable="false"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<t:dgDelOpt title="删除" url="receiveTextController.do?del&id={id}" />
	<t:dgFunOpt title="快捷回复" funname="responseMessage(id)"/>
	<t:dgToolBar title="刷新" icon="icon-reload" funname="goReload"></t:dgToolBar>
</t:datagrid>
</div></div>
<script type="text/javascript">
	$(document).ready(function(){
		//给时间控件加上样式
		$("#rtListtb").find("input[name='createTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#rtListtb").find("input[name='createTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	});
	function responseMessage(id){
		var url = "receiveTextController.do?jumpsendmessage&id="+id
		add("快捷回复",url,"rtList",700,400);
	}
	function goReload(){
		$('#rtList').datagrid('reload');
	}
</script>
