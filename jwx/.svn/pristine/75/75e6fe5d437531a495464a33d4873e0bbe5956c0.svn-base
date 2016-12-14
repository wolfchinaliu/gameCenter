<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;" id="j-table-box">
        <t:datagrid name="flowCardInfoList" checkbox="true" fitColumns="false" title="商户管理表"
                    actionUrl="flowCardInfoController.do?flowcardatagrid" idField="id" fit="true" queryMode="group" sortName="beginDate" sortOrder="desc">
            <t:dgCol title="主键" field="id" hidden="false"  width="120"></t:dgCol>
            <t:dgCol title="卡号" field="cardNumber" hidden="true" query="true" queryMode="single" width="160"></t:dgCol>
            <t:dgCol title="批次号" field="batchNo" hidden="true" query="true" queryMode="single" width="140"></t:dgCol>
            <t:dgCol title="流量值" field="flowValue" hidden="true" query="true" queryMode="single"
                     width="60"></t:dgCol>
            <t:dgCol title="流量类型" field="flowType" replace="全国流量_1,省内流量_2" hidden="true" query="true" queryMode="single"
                     width="60"></t:dgCol>
            <t:dgCol title="开始时间" field="beginDate" hidden="true" query="false" queryMode="single" formatter="yyyy-MM-dd 00:00:00" width="140"></t:dgCol>
            <t:dgCol title="结束日期" field="endDate" hidden="true" query="false" queryMode="single" formatter="yyyy-MM-dd 23:59:59" width="140"></t:dgCol>
            <t:dgCol title="有效" field="isValid" hidden="true" queryMode="single" query="true" width="60"></t:dgCol>
            <%--<t:dgCol title="市/区" field="city" hidden="true" queryMode="single" width="120"></t:dgCol>--%>
            <t:dgCol title="状态" field="statusLock" hidden="true" query="true" queryMode="single" width="60"></t:dgCol>
            <t:dgCol title="使用" field="isUse" hidden="true" query="true" queryMode="single" width="60"></t:dgCol>
            <t:dgCol title="充值号码" field="phoneNumber" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="充值时间" field="useDate" hidden="true" queryMode="single" formatter="yyyy-MM-dd hh:mm:ss" width="140"></t:dgCol>
            <t:dgCol title="二维码" field="QRcode" hidden="false" queryMode="single" width="160"></t:dgCol>

            <t:dgToolBar title="制卡" icon="icon-add" url="flowCardInfoController.do?goAdd"
                         funname="add"></t:dgToolBar>
            <t:dgToolBar title="激活" icon="icon-add" url="flowCardInfoController.do?doBatchAdd"
                         funname="jihuoState"></t:dgToolBar>
            <t:dgToolBar title="锁定" icon="icon-add" url="flowCardInfoController.do?doBatchAddTwo"
                         funname="suodingState"></t:dgToolBar>
            <t:dgToolBar title="导出二维码" icon="icon-add" url="weixinAcctController.do?goQRcode"
                         funname="ExportQRcode"></t:dgToolBar>
            <t:dgToolBar title="导出表格" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
            <t:dgToolBar title="活动链接" icon="icon-edit" funname="popMenuLink"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>
<script type="text/javascript">

	$("#flowCardInfoListtb").css("height", "60px");
    //导出二维码
    function ExportQRcode() {
        var cardNumber=$("#flowCardInfoListtb input[name=cardNumber]").val();
        var batchNo = $("#flowCardInfoListtb input[name=batchNo]").val();
        var flowValue=$("#flowCardInfoListtb input[name=flowValue]").val();
        var isValid=$("#flowCardInfoListtb input[name=isValid]").val();
        var isUse=$("#flowCardInfoListtb input[name=isUse]").val();
        var statusLock=$("#flowCardInfoListtb input[name=statusLock]").val();
        var flowType=$("#flowCardInfoListtb input[name=flowType]").val();
        /* //多选框被选中的个数
        var checkedRow = $('#flowCardInfoList').datagrid('getChecked');
        var checkedRowNum = checkedRow.length;
        var checkboxCount = checkbox.length;
        for(var i=0;i<checkedRow.length;i++){
        	QRcode+=checkedRow[i].QRcode+",";
        } */
        $.messager.confirm("导出", "导出查询的所有结果！", function (r) {
            if (r) {
            	var url = "flowCardInfoController.do?goQRcode";
                url += "&cardNumber=" + (cardNumber || '');
                url += "&flowValue=" + (flowValue || '');
                url += "&batchNo=" + (batchNo || '');
                url += "&isValid=" + (isValid || '');
                url += "&isUse=" + (isUse || '');
                url += "&statusLock=" + (statusLock || '');
                url += "&flowType=" + (flowType || '');
                window.open(url);
            }
        });
    }

    //激活所选定的状态
    function jihuoState() {
      	//多选框被选中的个数
        var checkedRow = $('#flowCardInfoList').datagrid('getChecked');
        if(checkedRow.length<=0){
            tip("请选中要操作的项目");
            return;
        }
        var ids = "";
        for(var i=0;i<checkedRow.length;i++){
        	ids+="'"+checkedRow[i].id+"',";
        }
        
        //进度条
        /* $.messager.progress(); */
        $.ajax({
            url: "flowCardInfoController.do?jihuoState",
            type: "post",
            data:  {"ids": ids},
            dataType: "json",
            success: function (data) {
                $.messager.progress('close');
                tip(data.msg);
                $('#flowCardInfoList').datagrid('reload')
                //reloadTable(0);
            },
            error: function () {
                tip("流量卡激活失败");
            }
        });
    }

    //锁定所选定的状态
    function suodingState() {
    	//多选框被选中的个数
        var checkedRow = $('#flowCardInfoList').datagrid('getChecked');
        if(checkedRow.length<=0){
            tip("请选中要锁定的项目");
            return;
        }
        var ids = "";
        for(var i=0;i<checkedRow.length;i++){
        	ids+="'"+checkedRow[i].id+"',";
        }
        
        //进度条
        /* $.messager.progress(); */
        $.ajax({
            url: "flowCardInfoController.do?suodingState",
            type: "post",
            data:  {"ids": ids},
            dataType: "json",
            success: function (data) {
                $.messager.progress('close');
                tip(data.msg);
                $('#flowCardInfoList').datagrid('reload')
                //reloadTable(0);
            },
            error: function () {
                tip("流量卡锁定失败");
            }
        });
    }

    function reloadTable(state) {
       setTimeout(function () {
           $('#flowCardInfoList').datagrid("reload");
        }, 60);
        var $tds = $('.datagrid-btable td[field=statusLock]');
        $tds.each(function(i) {
            var $div = $(this).find('div.datagrid-cell');
            var str = $div.text();
            if(state === 1) {
                $div.text('锁定');
            }
            else if(state === 0) {
                $div.text('激活');
            }
        });
    }

    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'weixinAcctController.do?upload', "weixinAcctList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("flowCardInfoController.do?exportXls", "flowCardInfoList");
    }

    function popMenuLink() {
        $.dialog({
            content: "url:flowCardController.do?showAddress",
            drag :false,
            lock : true,
            title:'活动链接',
            opacity : 0.3,
            width:650,
            height:80,drag:false,min:false,max:false
        }).zindex();
    }
</script>