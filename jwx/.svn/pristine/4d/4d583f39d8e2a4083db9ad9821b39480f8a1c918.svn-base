<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding: 1px; border: 0px;">
    <t:datagrid name="materialAuditList" actionUrl="materialController.do?queryToAudit" fit="true"
      fitColumns="true" idField="id" queryMode="group">
      <t:dgCol title="ID" field="id" hidden="false" queryMode="single" width="0"></t:dgCol>
      <t:dgCol title="素材名称" field="title" query="true" width="100"></t:dgCol>
      <t:dgCol title="缩略图" field="pic" image="true" imageSize="40,40" width="100"></t:dgCol>
      <t:dgCol title="链接类型" field="urlType" width="100" replace="内部页面_inner,外部链接_outer"></t:dgCol>
      <t:dgCol title="链接地址" field="url" funname="" width="100"></t:dgCol>
      <t:dgCol title="素材类型" field="materialType" width="100" query="true" replace="酒店_1,餐饮_2,游戏_3,证券_4,地产_5,购物_6,旅游_7,银行_8,金融_9,其他_10"></t:dgCol>
      <t:dgCol title="制作商户" field="merchantName" width="100" query="false"></t:dgCol>
      <t:dgCol title="提交时间" field="commitDate" width="100" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
      <t:dgCol title="审核状态" field="status" width="100" replace="未提交_1,待审核_2,审核不通过_3,审核通过_4" query="true"></t:dgCol>
      <t:dgCol title="审核结果" field="auditInfo" width="100" query="true"></t:dgCol>
      <t:dgCol title="审核时间" field="auditDate" width="100" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>

      <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
      <t:dgFunOpt funname="goAudit(id)" title="审核" exp="status#ne#4"></t:dgFunOpt>

    </t:datagrid>
  </div>
</div>
<script type="text/javascript">
    function goAudit(id){
      var addurl = "materialController.do?goAudit&id="+id;
      createwindow("审核素材", addurl, 750, 450);
    }
</script>