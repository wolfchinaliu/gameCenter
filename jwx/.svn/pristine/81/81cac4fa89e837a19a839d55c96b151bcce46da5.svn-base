<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script src="plug-in/layer-v2.1/layer/layer.js"></script>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding: 1px; border: 0px;">
    <t:datagrid name="materialList" actionUrl="materialController.do?query" fit="true" fitColumns="true" idField="id" queryMode="group">
      <t:dgCol title="ID" field="id" hidden="false" queryMode="single" width="0"></t:dgCol>
      <t:dgCol title="素材名称" field="title" query="true" width="100"></t:dgCol>
      <t:dgCol title="缩略图" field="pic" image="true" imageSize="40,40" width="100"></t:dgCol>
      <t:dgCol title="链接类型" field="urlType" width="100" replace="内部页面_inner,外部链接_outer"></t:dgCol>
      <t:dgCol title="素材类型" field="materialType" width="100" query="true" replace="酒店_1,餐饮_2,游戏_3,证券_4,地产_5,购物_6,旅游_7,银行_8,金融_9,其他_10"></t:dgCol>
      <t:dgCol title="提交时间" field="commitDate" width="100" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
      <t:dgCol title="审核状态" field="status" width="100" replace="未提交_1,待审核_2,审核不通过_3,审核通过_4" query="true"></t:dgCol>
      <t:dgCol title="审核结果" field="auditInfo" width="100" query="true"></t:dgCol>
      <t:dgCol title="审核时间" field="auditDate" width="100" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>

      <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
      <t:dgDelOpt title="删除" url="materialController.do?del&id={id}" exp="status#eq#1,2,3"/>
      <t:dgFunOpt title="编辑" funname="update(id)" exp="status#eq#1,2,3"/>
      <t:dgConfOpt title="提交审核" url="materialController.do?commit&id={id}" message="提交审核此广告素材？" exp="status#eq#1"/>

      <t:dgToolBar title="新增" icon="icon-add" url="materialController.do?goAdd" funname="add" width="1000" height="500"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="icon-search" url="materialController.do?view" funname="detail"></t:dgToolBar>

    </t:datagrid>
  </div>
</div>
<script type="text/javascript">
  	function update(id){
  		var editUrl = "materialController.do?goUpdate&id="+id;
  		createwindow("编辑素材", editUrl, 750, 450);
  	}
</script>