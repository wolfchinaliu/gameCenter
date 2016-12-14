<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinLotteryList" sortName="createTime" sortOrder="desc" fitColumns="false" title="${type == 1?'大转盘':'大转盘' }" actionUrl="weixinActivityController.do?datagrid&type=${type}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="type"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="活动主题"  field="title"  hidden="true" query='true'  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="活动描述"  field="description"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createTime"  hidden="true"  formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"  width="140"></t:dgCol>
   <t:dgCol title="开始时间"  field="starttime"  hidden="true"  formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"  width="140"></t:dgCol>
   <t:dgCol title="结束时间"  field="endtime"  hidden="true"  formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"  width="140"></t:dgCol>
      <t:dgCol title="频次/周期" field="frequency" hidden="true" replace="天_1,周_2,月_3,无_0" queryMode="single"
               width="140"></t:dgCol>

   <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
   <t:dgFunOpt funname="showQuestion(id)" title="试题列表" exp="type#eq#101,102"></t:dgFunOpt>
   <t:dgFunOpt funname="showPrize(id)" title="中奖记录" ></t:dgFunOpt>
  <t:dgFunOpt funname="showRecord(id)" title="参与记录" ></t:dgFunOpt>
     <%--<t:dgFunOpt funname="updateLottery(id)"  title="编辑"></t:dgFunOpt>--%>
      <t:dgDelOpt title="删除" url="weixinActivityController.do?doDel&id={id}" />

   <t:dgToolBar title="录入" icon="icon-add" url="weixinActivityController.do?goEdit&type=${type }" funname="add" height="560" width="640"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinActivityController.do?goEdit&type=${type }" funname="update" height="560" width="640"></t:dgToolBar>
 <%--   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinLotteryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinActivityController.do?goEdit&type=${type }" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="活动链接" icon="icon-edit"
			 funname="popMenuLink"	></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinLotteryController.do?upload', "weixinLotteryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinLotteryController.do?exportXls","weixinLotteryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinLotteryController.do?exportXlsByT","weixinLotteryList");
}
function showQuestion(id){
		var addurl = "weixinActivityController.do?goQuestionList&hdId="+id;
		addGroupMember("题目列表", addurl, 850, 850);
	}
 </script>
 <script type="text/javascript">
     function updateLottery(id){

     }

  	function showPrize(id){
  		var addurl = "weixinActivityController.do?goPrizeRecord&hdId="+id;
  		createdetailwindow("中奖记录列表", addurl, 1200, 600);
  	}
  	
  	function showRecord(id){
  		var addurl = "weixinActivityController.do?hdDetail&hdId="+id;
  		createdetailwindow("参与记录列表", addurl, 850, 550);
  	}
  	function popMenuLink(id,gname){
  		
 	}
  	
  	function popMenuLink(title,url, id,width,height) {
  		gridname=id;
  		var rowsData = $('#'+id).datagrid('getSelections');
  		if (!rowsData || rowsData.length==0) {
  			tip('请选择相关活动');
  			return;
  		}
  		if (rowsData.length>1) {
  			tip('请选择相关活动');
  			return;
  		}
  		$.dialog({
 			content: "url:weixinActivityController.do?weixinActivityAddress&hdid="+rowsData[0].id,
 			drag :false,
 			lock : true,
 			title:'活动链接',
 			opacity : 0.3,
 			width:650,
 			height:80,drag:false,min:false,max:false
 		});
  	}
  	
  	
  	 
  </script>