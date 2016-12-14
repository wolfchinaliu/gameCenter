<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
    <div region="center" style="padding:1px;border: 0px;">
        <t:datagrid name="businessInterfaceList" checkbox="true" fitColumns="false" title="公众帐号管理"
                    actionUrl="businessInterfaceController.do?datagrid" idField="id" fit="true" queryMode="group">
            <t:dgCol title="接口ID" field="id" hidden="flase" queryMode="single" width="0"></t:dgCol>
            <t:dgCol title="接口名称" field="InterfaceName" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="接口描述" field="description" hidden="true" width="120"></t:dgCol>
            <t:dgCol title="地域" field="provinceName" query="true"  width="120"></t:dgCol>
            <t:dgCol title="运营商" field="businessName" query="true" width="120"></t:dgCol>
            <t:dgCol title="省Code" field="provinceCode" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="状态" field="state" hidden="true" query="true" replace="启用_1,停用_0" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="默认通道" field="isDefault" hidden="true" query="true" replace="是_1,否_0" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="优先级" field="dispose" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="运营商Code" field="businessCode" hidden="false" queryMode="single" width="120"></t:dgCol>

            <t:dgCol title="操作" field="opt" width="180"></t:dgCol>

            <t:dgConfOpt title="运行" url="businessInterfaceController.do?goRun&id={id}" message="确定运行该接口吗？"/>
            <t:dgConfOpt title="停止" url="businessInterfaceController.do?goQuit&id={id}" message="确定停止该接口吗？"/>
            <t:dgConfOpt title="默认" url="businessInterfaceController.do?goSetDefault&id={id}" message="确定设置该接口为默认接口吗？"/>
            <t:dgConfOpt title="非默认" url="businessInterfaceController.do?goSetNotDefault&id={id}" message="确定设置该接口为非默认接口吗？"/>


            <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>

        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
    //导出
    function ExportXls() {
        JeecgExcelExport("businessInterfaceController.do?exportXls","businessInterfaceList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("businessInterfaceController.do?exportXlsByT","businessInterfaceList");
    }
</script>