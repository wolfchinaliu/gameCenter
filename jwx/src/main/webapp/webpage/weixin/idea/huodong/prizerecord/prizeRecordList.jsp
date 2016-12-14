<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
 <t:base type="jquery,easyui,tools"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="prizeRecordList" title="中奖纪录" actionUrl="prizeRecordController.do?datagrid&hdid=${hdId}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="活动主键" field="hdid" hidden="false"></t:dgCol>
   <t:dgCol title="唯一用户编号" field="openid" ></t:dgCol>
   <t:dgCol title="收货人" field="name" ></t:dgCol>
   <t:dgCol title="收货地址" field="address" hidden="true"></t:dgCol>
   <t:dgCol title="活动主键" field="hdid" hidden="false"></t:dgCol>
   <t:dgCol title="手机号码" field="mobile" width="100" query="true"></t:dgCol>
   <t:dgCol title="奖项" field="prize" replace="一等奖_1,二等奖_2,三等奖_3" query="true" width="100"></t:dgCol>
   <t:dgCol title="时间" field="addtime" formatter="yyyy-MM-dd hh:mm:ss"  width="100"></t:dgCol>
  </t:datagrid>
  </div>
 </div>