<%--
  Created by IntelliJ IDEA.
  User: zss
  Date: 2016/6/20
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
    <t:datagrid name="AcctList" checkbox="true" fitColumns="true" title="管理商家列表信息" actionUrl="acctListController.do?datagrid" idField="id" fit="true" queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="0"></t:dgCol>
      <t:dgCol title="名称"  field="acctlistName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="图片Url"  field="pictureUrl"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="地址" field="point" hidden="true" queryMode="singgle" width="120"></t:dgCol>
      <%-- <t:dgCol title="地图Url" field="point"  hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
      <t:dgCol title="商城Url" field="shoppAddress"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="联系电话"  field="phone"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="商家介绍"  field="description"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="商家id"  field="acctId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
      


      <t:dgToolBar title="新增" icon="icon-edit" url="acctListController.do?addAcctList" funname="add"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="icon-edit" url="acctListController.do?goEdit" funname="update"></t:dgToolBar>

    </t:datagrid>
  </div>
</div>