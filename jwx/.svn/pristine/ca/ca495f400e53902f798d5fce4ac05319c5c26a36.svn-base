<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinLotteryList" sortName="starttime" sortOrder="desc" fitColumns="false" title="${lotteryType eq '2'?'刮刮乐':'大转盘' }" actionUrl="weixinLotteryController.do?datagrid&lotteryType=${lotteryType}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="活动主题"  field="title"  hidden="true" query='true'  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="活动描述"  field="description"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="一等奖流量值"  field="firstprize"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="一等奖数量"  field="firstprizetotal"  hidden="true"  queryMode="single"  width="60"></t:dgCol>--%>
   <%--<t:dgCol title="一等奖概率"  field="firstprizeProb"  hidden="true"  queryMode="single"  width="70"></t:dgCol>--%>
   <%--<t:dgCol title="二等奖流量值"  field="secondprize"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="二等奖数量"  field="secondprizetotal"  hidden="true"  queryMode="single"  width="60"></t:dgCol>--%>
   <%--<t:dgCol title="二等奖概率"  field="secondprizeProb"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
 <%--<t:dgCol title="三等奖"  field="thirdprize"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="三等奖数量"  field="thirdprizetotal"  hidden="true"  queryMode="single"  width="60"></t:dgCol>--%>
   <%--<t:dgCol title="三等奖概率"  field="thirdprizeProb"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="启用其他奖项"  field="abledotherprize"  hidden="false"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="开始时间"  field="starttime"  hidden="true"  formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"  width="140"></t:dgCol>
   <t:dgCol title="结束时间"  field="endtime"  hidden="true"  formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"  width="140"></t:dgCol>
   <%--<t:dgCol title="每天抽奖次数"  field="lotterynumberday"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="总抽奖次数"  field="lotterynumber"  hidden="true"  queryMode="single"  width="60"></t:dgCol>--%>
   <%--<t:dgCol title="微信公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>--%>
      <t:dgCol title="状态" field="state" hidden="true" replace="进行中_1,已结束_0,尚未开始_2" queryMode="single"
               width="140"></t:dgCol>

   <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
   <t:dgFunOpt funname="showPrize(id)" title="中奖记录" ></t:dgFunOpt>
   <%--<t:dgFunOpt funname="showRecord(id)" title="抽奖记录"></t:dgFunOpt>--%>
     <%--<t:dgFunOpt funname="updateLottery(id)"  title="编辑"></t:dgFunOpt>--%>
      <t:dgDelOpt title="删除" url="weixinLotteryController.do?doDel&id={id}" />

   <t:dgToolBar title="录入" icon="icon-add" url="weixinLotteryController.do?goAdd&lotteryType=${lotteryType }" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinLotteryController.do?goUpdate&lotteryType=${lotteryType }" funname="update"></t:dgToolBar>
 <%--   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinLotteryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinLotteryController.do?goUpdate&lotteryType=${lotteryType }" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="活动链接" icon="icon-edit"
			 funname="popMenuLink"	></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/lottery/weixinLotteryList.js"></script>		
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
 </script>
 <script type="text/javascript">
     function updateLottery(id){

     }

  	function showPrize(id){
  		var addurl = "weixinWinningrecordController.do?goPrizeRecord&lotteryType=${lotteryType}&hdId="+id;
  		createdetailwindow("中奖记录列表", addurl, 750, 450);
  	}
  	
  	function showRecord(id){
  		var addurl = "weixinDrawDetailController.do?hdRecord&hdId="+id;
  		createdetailwindow("抽奖记录列表", addurl, 650, 450);
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
 			content: "url:weixinLotteryController.do?weixinLotteryAddress&hdid="+rowsData[0].id,
 			drag :false,
 			lock : true,
 			title:'活动链接',
 			opacity : 0.3,
 			width:650,
 			height:80,drag:false,min:false,max:false
 		}).zindex();
  	}
  	
  	
  	 
  </script>