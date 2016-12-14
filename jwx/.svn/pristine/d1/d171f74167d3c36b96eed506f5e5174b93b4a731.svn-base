<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div data-options="region:'north',border:false" style="padding:10px;color:red">
	    <strong>温馨提示：数据量大时,由于验证的时间过长，请您耐心等待！</strong>
	</div>
    <div region="center" style="padding:1px;">
        <t:datagrid name="manualGivenRecords" checkbox="true" fitColumns="false" title="手工赠送列表" sortName="givenTime" sortOrder="desc"
                    actionUrl="manualGivenController.do?mgrdatagrid" fit="true" queryMode="group"> 
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="批次号" field="patchNo" hidden="true" query="true"  width="120"></t:dgCol>
            <t:dgCol title="赠送描述" field="des" hidden="true" query="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="流量类型" replace="全国流量_1,省内流量_2" field="flowType" hidden="true" query="false" 
                     width="120"></t:dgCol>
             <t:dgCol title="赠送流量值" field="flowValue" query="false" hidden="true" 
                     width="120"></t:dgCol> 
            <t:dgCol title="赠送总流量" field="totalFlow" query="false" hidden="true" 
                     width="120"></t:dgCol> 
            <t:dgCol title="赠送状态" field="result" replace="成功_1,失败_2,预定_3" query="true" hidden="true" 
                     width="120"></t:dgCol> 
            <t:dgCol title="状态" field="state" replace="正在处理_0,处理完毕_1,还未开始_2" query="false" hidden="false" 
                     width="120"></t:dgCol> 
           <t:dgCol title="原因" field="reason" replace="赠送成功_1,商户流量不足_2,取消赠送_3,未到赠送时间_4,异常_5,没有符合要求的手机号_6" query="true" hidden="true" 
                     width="120"></t:dgCol>      
            <t:dgCol title="结果"  field="isNow" replace="立即赠送_1,预定时间_2" query="false" hidden="false"
                     queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="赠送时间" field="givenTime" formatter="yyyy-MM-dd hh:mm" hidden="true"  query ="true"  queryMode="group"
                     width="120"></t:dgCol>
            <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
 			<t:dgFunOpt funname="showPrize(patchNo)" title="详情" exp="result#ne#3"></t:dgFunOpt>
 			<t:dgDelOpt title="取消赠送" url="manualGivenController.do?doupdate&id={id}" exp="result#eq#3&&isNow#eq#2&&state#eq#2"/>
            <t:dgToolBar title="新增" icon="icon-add" url="manualGivenController.do?goAddManual"
                         funname="add" width="600" height="500"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
	function showPrize(patchNo){
		var addurl = "manualGivenController.do?patchManualRecords&patchNo="+patchNo;
		createdetailwindow("批次赠送列表", addurl, 750, 450);
	}
	function cancel(patchNo){
		var addurl = "manualGivenController.do?cancelManual&patchNo="+patchNo;
		createdetailwindow("取消赠送", addurl, 750, 450);
	}
    $(document).ready(function(){
		//给时间控件加上样式
			$("#manualGivenRecordstb").find("input[name='givenTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
			$("#manualGivenRecordstb").find("input[name='givenTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
}); 
    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'weixinAcctController.do?upload', "weixinAcctList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("weixinAcctController.do?exportXls", "weixinAcctList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("weixinAcctController.do?exportXlsByT", "weixinAcctList");
    }
</script>