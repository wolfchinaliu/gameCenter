<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="softWenSpread" checkbox="true" fitColumns="false"
			title="软文传播"
			actionUrl="softWenSpreadController.do?softWenSpreadDatagrid"
			idField="id" fit="true" queryMode="group">
			<t:dgCol title="主键" field="id" hidden="false" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="日期" field="createtime" hidden="true" query="true"
				queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="商户名称" field="acctForName" hidden="true" query="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="商户等级" field="level" width="100"
				replace="A级_1,B级_2,C级_3" query="true" hidden="true"></t:dgCol>
			<t:dgCol title="所属商户" field="belogAcct" hidden="true"
				queryMode="single" query="true" width="120"></t:dgCol>
			<t:dgCol title="软文(标题)" field="title" hidden="true"
				queryMode="single" width="120" query="true"></t:dgCol>
			<t:dgCol title="分享转发次数" field="shareNumber" width="100" query="flase"
				hidden="true"></t:dgCol>
			<t:dgCol title="阅读量" field="readNumber" width="100" query="flase"
				hidden="true"></t:dgCol>
			<t:dgToolBar title="excel导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>
<script type="text/javascript"> 
    $(document).ready(function(){
		//给时间控件加上样式
			$("#softWenSpreadtb").find("input[name='createtime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
			$("#softWenSpreadtb").find("input[name='createtime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
			$('#softWenSpreadtb').find("input[name='belogAcct']").combobox({
		  		url: 'weixinAcctController.do?NotCAllSubAcct',
		  		editable: false, //不可编辑状态
		  		cache: false,
		  		valueField: 'code',
		  		textField: 'name',
		  		resizable:true,
		  		multiple:false
		  	});
		 
    }); 
    function editPWD(id) {
        var url = "weixinAcctController.do?goUpdateMyacct&id=" + id;
//        createdetailwindow('密码重置页面', 'weixinAcctFlowAccountController.do?goCharge&id=' + id, 'weixinAcctFlowAccountList', '100%', '100%');
        add('密码重置页面', 'weixinAcctController.do?Acctchangepassword&id=' + id, 'weixinAcctList', '100%', '100%');
    }
    //    }
    $(document).ready(function () {
        //给时间控件加上样式
        //$("[name=merchant] option:eq(0)").remove();
        $("[name=status] option:eq(0)").remove();
       
    });
    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'softWenSpreadController.do?upload', "softWenSpread");
    }

    //excel导出
    function ExportXls() {
        JeecgExcelExport("softWenSpreadController.do?exportXls", "softWenSpread");
    }


    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("softWenSpreadController.do?exportXlsByT", "softWenSpread");
    }
</script>