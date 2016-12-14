<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
<t:datagrid name="autoresponseDefaultlist" actionUrl="autoResponseDefaultController.do?datagrid" title="未识别回复" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false" ></t:dgCol>
	<t:dgCol title=" 模板名称" field="templateName"  width="100"></t:dgCol>
	<t:dgCol title="类型" field="msgType" replace="文本_text,图文_news" width="100"></t:dgCol>
	<t:dgCol title="时间" field="addTime" width="100"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgDelOpt title="删除" url="autoResponseDefaultController.do?del&id={id}" />
	<t:dgToolBar title="录入" icon="icon-add" url="autoResponseDefaultController.do?jumpSuView" funname="add"></t:dgToolBar>
 	<t:dgToolBar title="编辑" icon="icon-edit" url="autoResponseDefaultController.do?jumpSuView" funname="update"></t:dgToolBar>
</t:datagrid>
</div></div>
<script type="text/javascript">
<!--
//-->
function myadd(title,addurl,gname,width,height) {
		gridname=gname;
		var getData = $('#'+gridname).datagrid('getData');
		if(getData.total!=0){
			tip('一个用户只能创建一个关注未识别响应信息');
			return;
		}
		createwindow(title, addurl,width,height);
	}
</script>


