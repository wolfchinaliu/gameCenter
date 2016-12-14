<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="weixinDrawrecordList" fitColumns="false"
			title="抽奖记录表"
			actionUrl="weixinDrawDetailController.do?datagridBySql&hdid=${hdId}"
			idField="id" fit="true" queryMode="group">
			<t:dgCol title="id" field="id" hidden="false" queryMode="group"
				width="120"></t:dgCol>
			<t:dgCol title="活动Id" field="hdid" hidden="false" query="false"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="抽奖人ID" field="opendid" hidden="true" query="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="抽奖次数" field="counts" hidden="true" queryMode="group"
				width="120"></t:dgCol>
			<t:dgCol title="accountid" field="accountid" hidden="false"
				queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="150"></t:dgCol>
  			<t:dgFunOpt funname="showDetail(hdid,opendid)" title="详情" ></t:dgFunOpt>
		</t:datagrid>
	</div>
</div>
<script src="webpage/weixin/lottery/weixinDrawrecordList.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				//给时间控件加上样式
				$("#weixinDrawrecordListtb")
						.find("input[name='addtime_begin']").attr("class",
								"Wdate").attr("style",
								"height:20px;width:90px;").click(function() {
							WdatePicker({
								dateFmt : 'yyyy-MM-dd'
							});
						});
				$("#weixinDrawrecordListtb").find("input[name='addtime_end']")
						.attr("class", "Wdate").attr("style",
								"height:20px;width:90px;").click(function() {
							WdatePicker({
								dateFmt : 'yyyy-MM-dd'
							});
						});
			});

	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'weixinDrawrecordController.do?upload',
				"weixinDrawrecordList");
	}

	//导出
	function ExportXls() {
		JeecgExcelExport("weixinDrawrecordController.do?exportXls",
				"weixinDrawrecordList");
	}

	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("weixinDrawrecordController.do?exportXlsByT",
				"weixinDrawrecordList");
	}
	function showDetail(hdid,opendid){
  		var addurl = "weixinDrawDetailController.do?weixinDrawDetail&hdid="+hdid+"&opendid="+opendid;
  		createdetailwindow("抽奖记录详情", addurl, 750, 450);
  	}
</script>