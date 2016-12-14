<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="memberIncrease" checkbox="true" fitColumns="false" title="" sortName="created" sortOrder="desc" actionUrl="memberStatController.do?datagrid"  idField="id" fit="true" queryMode="group"  >
   <t:dgCol title="用户的标识"  field="id" hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="新增粉丝人数"  field="addCount"  hidden="true"  query="false" width="120" ></t:dgCol>
   <t:dgCol title="取消关注粉丝人数"  field="cancelCount"  hidden="true"  query="false" width="120" ></t:dgCol>
   <t:dgCol title="净增粉丝人数"  field="netCount"  hidden="true"  query="false" width="120" ></t:dgCol>
   <t:dgCol title="当日绑定粉丝"  field="bindedCount"  hidden="true"  query="false" width="120" ></t:dgCol>
   <t:dgCol title="累计关注人数"  field="subscribeCount"  hidden="true"  query="false" width="120" ></t:dgCol>
   <t:dgCol title="日期"  field="created" formatter="yyyy-MM-dd" hidden="true"  queryMode="group" query="true" width="120" sortable="true"></t:dgCol> 
   <t:dgToolBar title="excel导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/member/weixinMemberList.js"></script>		
 <script type="text/javascript">

 
 $(document).ready(function(){
		//给时间控件加上样式
//		$("#memberIncreasetb").css("height", "60px");
			$("#memberIncreasetb").find("input[name='created_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
			$("#memberIncreasetb").find("input[name='created_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
}); 
 

//导出
function ExportXls() {
	JeecgExcelExport("memberStatController.do?exportXls","memberIncrease");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("memberStatController.do?exportXlsByT","memberIncrease");
}

 </script>