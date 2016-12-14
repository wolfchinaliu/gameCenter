<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
    <div region="center" style="padding:1px;border: 0px;">


        <t:datagrid name="merchantAndGroupList" checkbox="true" fitColumns="false" title="商户流量账户表管理"
                    actionUrl="merchantAndGroupController.do?datagridBySql" idField="id" fit="true" queryMode="group">
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <%--<t:dgCol title="组名" field="groupName" hidden="true" query="true" queryMode="single"--%>
                     <%--width="120"></t:dgCol>--%>
            <t:dgCol title="组名" field="groupName" hidden="true" query="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="商户数目" field="counts" hidden="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="操作" field="opt" width="200"></t:dgCol>

            <t:dgFunOpt funname="addAccount(id)" title="添加成员"></t:dgFunOpt>
            <t:dgFunOpt funname="edit(id)" title="编辑"></t:dgFunOpt>
            <t:dgDelOpt title="删除" url="merchantAndGroupController.do?del&id={id}"/>
            <%--<t:dgFunOpt funname="delete(id)" title="删除"></t:dgFunOpt>--%>

            <t:dgToolBar title="新增" icon="icon-add" url="merchantAndGroupController.do?goAdd"
                         funname="add"></t:dgToolBar>
            <%--<t:dgToolBar title="新增" icon="icon-add" url="merchantAndGroupController.do?goAdd"--%>
            <%--funname="add" height="100%" width="100%"></t:dgToolBar>--%>
            <%--可以将小框设置为大框-----属性的设置：height="100%" width="100%"--%>
        </t:datagrid>
    </div>
</div>
<script src="webpage/weixin/tenant/weixinProductList.js"></script>
<script type="text/javascript">
    function addAccount(id) {
        var url = "merchantAndGroupController.do?goAdd"
        addGroupMember('添加成员', 'merchantAndGroupController.do?maccdatagridAllList&id=' + id, 'merchantAndGroupList', '100%', '100%');
    }

    function edit(id) {
        addGroupMember('编辑成员', 'merchantAndGroupController.do?maccdatagridJoinedList&id=' + id, 'merchantAndGroupList', '100%', '100%');
    }
</script>