<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script src="plug-in/layer-v2.1/layer/layer.js"></script>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding: 1px; border: 0px;">
    <t:datagrid name="materialList" actionUrl="advertisementController.do?query" fit="true" fitColumns="false" idField="id" queryMode="group">
      <t:dgCol title="ID" field="id" hidden="false" queryMode="single" width="0"></t:dgCol>
      <t:dgCol title="广告名称" field="title" query="true" width="100"></t:dgCol>
      <t:dgCol title="素材名称" field="materialTitle" query="true" width="100"></t:dgCol>
      <t:dgCol title="缩略图" field="pic" image="true" imageSize="40,40" width="100"></t:dgCol>
      <t:dgCol title="链接地址" field="url" width="100" replace="内部页面_inner,外部链接_outer"></t:dgCol>
      <t:dgCol title="素材类型" field="materialType" width="100" query="true" replace="酒店_1,餐饮_2,游戏_3,证券_4,地产_5,购物_6,旅游_7,银行_8,金融_9,其他_10"></t:dgCol>
      <t:dgCol title="广告位置" dictionary="ad_pos" field="position" query="true" hidden="true" queryMode="single" width="120"></t:dgCol>
      <t:dgCol title="开始时间" field="periodBegin" width="150" formatter="yyyy-MM-dd hh:mm:ss" query="false"></t:dgCol>
      <t:dgCol title="结束时间" field="periodEnd" width="150" formatter="yyyy-MM-dd hh:mm:ss" query="false"></t:dgCol>
      <t:dgCol title="终止时间" field="finishDate" width="150" formatter="yyyy-MM-dd hh:mm:ss" query="false"></t:dgCol>
      <t:dgCol title="发布状态" field="status" width="100" replace="未发布_1,发布中_2,发布结束_3,终止发布_4,被动终止_5" query="true"></t:dgCol>

      <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
      <t:dgFunOpt title="查看覆盖商户" funname="viewMerchant(id)" />
      <t:dgConfOpt title="终止广告" url="advertisementController.do?terminate&id={id}" exp="status#eq#2" message="确认终止此条广告?" />
      <t:dgDelOpt title="删除" url="advertisementController.do?del&id={id}" exp="status#eq#1"/>
      <t:dgFunOpt title="编辑" funname="update(id)" exp="status#eq#1"/>

      <t:dgToolBar title="新增广告" icon="icon-add" url="advertisementController.do?goAdd" funname="add"></t:dgToolBar>

    </t:datagrid>
  </div>
</div>
<script type="text/javascript">
  	function update(id){
  		var editUrl = "advertisementController.do?goUpdate&id="+id;
  		createwindow("编辑素材", editUrl, 750, 450);
  	}
  	function viewMerchant(id){
  		var editUrl = "advertisementController.do?viewMerchant&id="+id;
  		openwindow("查看覆盖商户", editUrl,"查看覆盖商户", 750, 450);
  	}
</script>