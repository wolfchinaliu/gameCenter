<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;">
    <t:datagrid name="weixinIplimitList" checkbox="true" fitColumns="false" title="Ip名单设置"
                actionUrl="ipLimitController.do?mydatagrid" idField="id" fit="true" queryMode="group">


      <t:dgCol title="ID" field="id" hidden="false" queryMode="single" width="240"></t:dgCol>

      <t:dgCol title="Ip地址"  field="ip" query="true" queryMode="single" width="120"></t:dgCol>

      <t:dgCol title="创建日期" field="createDate" formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"
               width="150"></t:dgCol>
      <t:dgCol title="修改日期"  field="operateDate"  formatter="yyyy-MM-dd hh:mm:ss" hidden="true" queryMode="group"  width="150"></t:dgCol>
      <%--<t:dgCol title="充值流量最大值M"  field="charegeFlow" queryMode="single" width="120"></t:dgCol>--%>

      <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
      <t:dgConfOpt title="删除" url="ipLimitController.do?doDel&id={id}" message="是否确定删除吗？"/>

      <t:dgToolBar title="编辑" icon="icon-edit" url="ipLimitController.do?goUpdate" funname="update" ></t:dgToolBar>
      <t:dgToolBar title="新增" icon="icon-add" url="ipLimitController.do?goAdd" funname="add" ></t:dgToolBar>

    </t:datagrid>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function(){
    //给时间控件加上样式
    $("#pptListtb").find("input[name='operatetime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
    $("#pptListtb").find("input[name='operatetime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
  });


</script>