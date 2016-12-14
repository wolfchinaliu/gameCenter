<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
    <div region="center" style="padding:1px;border: 0px;">
        <t:datagrid name="pptList" checkbox="true" fitColumns="false" title="幻灯片管理"
                    actionUrl="pptManagerController.do?mydatagrid" idField="id" fit="true" queryMode="group">
            <t:dgCol title="ID" field="id" hidden="false" queryMode="single" width="0"></t:dgCol>
            <t:dgCol title="标题"  field="title" query="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="页面位置"  field="pageLocation"  queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="图片标题" hidden="false" field="pictureName" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="跳转类型" field="jumpType" queryMode="single" width="120"></t:dgCol>
            <%--<t:dgCol title="内部页面" field="pageUrl" queryMode="single" width="120"></t:dgCol>--%>
            <t:dgCol title="地址" field="jumpUrl" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="时间" field="operatetime" hidden="false" queryMode="group" query="true" width="100"
                     sortable="false"></t:dgCol>

            <t:dgCol title="操作" field="opt" width="60"></t:dgCol>
            <t:dgDelOpt title="删除" url="pptManagerController.do?doDel&id={id}"/>
            <t:dgToolBar title="预览" icon="icon-search" url="pptManagerController.do?goUpdate" funname="detail"></t:dgToolBar>
            <t:dgToolBar title="编辑" icon="icon-edit" url="pptManagerController.do?goEditppt" funname="update"></t:dgToolBar>
            <t:dgToolBar title="新增" icon="icon-add" url="pptManagerController.do?goAdd"
                         funname="add"></t:dgToolBar>

        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        //给时间控件加上样式
        $("#pptListtb").find("input[name='operatetime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
        $("#pptListtb").find("input[name='operatetime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
    });
    //导出
    /*function ExportXls() {
     JeecgExcelExport("businessInterfaceController.do?exportXls","businessInterfaceList");
     }

     //模板下载
     function ExportXlsByT() {
     JeecgExcelExport("businessInterfaceController.do?exportXlsByT","businessInterfaceList");
     }*/
</script>