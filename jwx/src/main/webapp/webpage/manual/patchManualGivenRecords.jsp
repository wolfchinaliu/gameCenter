<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div data-options="region:'north',border:false" style="padding:10px; ">
    成功${sucnum }条，失败${failnum }条
</div>
  <div region="center" style="padding:1px;">
  <t:datagrid name="patchManualGivenRecords" fitColumns="false" title="批次赠送记录列表" actionUrl="manualGivenController.do?datagrid&patchNo=${patchNo}" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgToolBar title="excel导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgCol title="批次号"  field="patchNo"  hidden="false"  width="120" query="false"></t:dgCol>
   <t:dgCol title="手机号"  field="phoneNum"  hidden="true"  width="120" query="true"></t:dgCol>
   <t:dgCol title="流量值"  field="flowValue"  hidden="true" width="120" query="false"></t:dgCol>
   <t:dgCol title="流量类型"  field="flowType" replace="全国流量_1,省内流量_2" hidden="false" width="120" query="false"></t:dgCol>
   <t:dgCol title="赠送时间"  field="givenTime"  hidden="false" width="120" query="false"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  hidden="false" width="120" query="false"></t:dgCol>
   <t:dgCol title="结果"  field="result" replace="成功_1,失败_2,预定_3" hidden="false" width="120" query="false"></t:dgCol>
   <t:dgCol title="赠送反馈" field="reason" replace= "赠送成功_1,商户流量不足_2,取消赠送_3,未到赠送时间_4,手机不合法_5,手机黑名单_6,不在覆盖区域_7,异常_8" hidden="true" width="120" query="true"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript"> 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinWinningrecordController.do?upload', "weixinWinningrecordList");
}

//导出
function ExportXls() { 
	JeecgExcelExport("manualGivenController.do?exportXls&patchNo=${patchNo}","patchManualGivenRecords");
}


//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinWinningrecordController.do?exportXlsByT","weixinWinningrecordList");
}


 </script>