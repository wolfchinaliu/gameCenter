<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true" style="border: 0px;">
<div data-options="region:'north',border:false" style="padding:10px; color: red;">
    温馨提示：设置用户每次关注您的公众号时，回复的信息，包括文本，图片，图文；请在素材管理中设置相关素材。
</div>
  <div region="center" style="padding:1px;border: 0px;" >
<t:datagrid name="gzlist" actionUrl="subscribeController.do?datagrid" fit="true" title="关注时回复" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false" ></t:dgCol>
	<t:dgCol title=" 模板名称" field="templateName"  width="100"></t:dgCol>
	<t:dgCol title="类型" field="msgType" replace="文本_text,图文_news,图片_image" width="100"></t:dgCol>
	<t:dgCol title="时间" field="addTime" width="100"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgDelOpt title="删除" url="subscribeController.do?del&id={id}" />
	 <%--<t:dgToolBar title="信息录入" icon="icon-add" url="subscribeController.do?jumpSuView" funname="add"></t:dgToolBar>&ndash;%&gt;--%>
 	<t:dgToolBar height="100" title="编辑" icon="icon-edit" url="subscribeController.do?jumpSuView" funname="update"></t:dgToolBar>
 	<t:dgToolBar title="增加" icon="icon-edit" url="subscribeController.do?jumpSuView" funname="myadd"></t:dgToolBar>
</t:datagrid>
</div></div>
<script type="text/javascript">
<!--
//-->
function myadd(title,addurl,gname,width,height) {
		gridname=gname;
		var getData = $('#'+gridname).datagrid('getData');
		if(getData.total!=0){
			tip('一个用户只能创建一个关注欢迎语');
			return;
		}
		createwindow(title, addurl,width,height);
	}
</script>

