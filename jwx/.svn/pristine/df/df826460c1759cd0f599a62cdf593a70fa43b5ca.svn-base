<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinBlacklistList" sortName="enabledTime" sortOrder="desc" fitColumns="false" title="黑名单" actionUrl="weixinBlacklistController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="手机"  field="id"  hidden="true"  query="true" queryMode="single"  width="130" ></t:dgCol>
   <t:dgCol title="添加时间"  field="addTime"  hidden="true" query="false" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="state"  hidden="true"  queryMode="group"  width="80" replace="已启用_1,已关闭_2" query=""></t:dgCol>
   <t:dgCol title="启用时间"  field="enabledTime"  hidden="true"  queryMode="single"  width="120" query="false"></t:dgCol>
   <t:dgCol title="启用原因"  field="enabledDec"  hidden="true"  queryMode="single"  width="200" query="false"></t:dgCol>
    <t:dgCol title="关闭时间"  field="disableTime"  hidden="true"  queryMode="single"  width="120" query="false"></t:dgCol>
   <t:dgCol title="关闭原因"  field="disableDec"  hidden="true"  queryMode="single"  width="200" query="false"></t:dgCol>
    <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
    <t:dgFunOpt  title="启用" exp="state#ne#1"  funname="edit(id)" ></t:dgFunOpt> 
   <t:dgFunOpt  funname="edit(id)" title="关闭" exp="state#eq#1"></t:dgFunOpt> 
   <t:dgToolBar title="录入" icon="icon-add"  funname="editBlacklist"></t:dgToolBar>
   <%--<t:dgToolBar title="查看" icon="icon-search" url="weixinWinningrecordController.do?goUpdate" funname="detail"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/lottery/weixinWinningrecordList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){

 });
 
 function edit(phoneNumber,index){
	 $.dialog({
			content: "url:weixinBlacklistController.do?goEdit&phoneNumber="+phoneNumber,
			drag :false,
			lock : true,
			title:'黑名单编辑',
			opacity : 0.3,
			width:650,
			height:180,drag:false,min:false,max:false
		});
 }
function editBlacklist(title,url, id,width,height){
	edit('',0);
}
 </script>