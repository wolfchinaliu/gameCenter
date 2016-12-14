<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;">
    <t:datagrid name="weixinMainList" checkbox="true" fitColumns="false" title="微门户管理"
                actionUrl="weixinMainController.do?mydatagrid" idField="id" fit="true" queryMode="group">
      <t:dgCol title="ID" field="id" hidden="false" queryMode="single" width="0"></t:dgCol>
      <t:dgCol title="标题"  field="title" query="true" queryMode="single" width="120"></t:dgCol>
      <%--<t:dgCol title="页面位置"  field="pageLocation"  queryMode="single" width="120"></t:dgCol>--%>
      <t:dgCol title="图像"  field="imagepath"  hidden="true" image="true" imageSize="40,40" queryMode="group"  width="120"></t:dgCol>
      <%--<t:dgCol title="描述"  field="description" queryMode="single" width="120"></t:dgCol>--%>
      <%--<t:dgCol title="跳转类型" field="jumpType" queryMode="single" width="120"></t:dgCol>--%>
      <%--<t:dgCol title="内部页面" field="pageUrl" queryMode="single" width="120"></t:dgCol>--%>
      <%--<t:dgCol title="地址" field="jumpUrl" queryMode="single" width="120"></t:dgCol>--%>
      <t:dgCol title="时间" field="createDate"  formatter="yyyy-MM-dd hh:mm:ss" queryMode="group" query="true" width="200"
               sortable="false"></t:dgCol>

      <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
      <%--<t:dgDelOpt title="删除"  funname="del(id)"/>--%>
      <t:dgConfOpt title="删除" url="weixinMainController.do?doDel&id={id}" message="是否确定删除吗？"/>
      <t:dgFunOpt funname="popMenuLink(id)" title="链接"></t:dgFunOpt>
      <%--<t:dgFunOpt funname="del(id)" title="删除"></t:dgFunOpt>--%>
      <%--<t:dgToolBar title="预览" icon="icon-search" url="pptManagerController.do?goUpdate" funname="detail"></t:dgToolBar>--%>
      <t:dgToolBar title="编辑" icon="icon-edit" url="weixinMainController.do?goUpdate" funname="update" ></t:dgToolBar>
      <t:dgToolBar title="新增" icon="icon-add" url="weixinMainController.do?goAdd" funname="add" ></t:dgToolBar>
      <t:dgToolBar title="流量门户链接" icon="icon-edit"
                   funname="popMenuLinkMain"></t:dgToolBar>

    </t:datagrid>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function(){
    //给时间控件加上样式
    $("#pptListtb").find("input[name='operatetime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
    $("#pptListtb").find("input[name='operatetime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
  });


  function popMenuLink(id) {
//    alert(id);
    $.dialog({
      content: "url:weixinMainController.do?weixinMainAddress&id="+id,
      drag :false,
      lock : true,
      title:'链接',
      opacity : 0.3,
      width:650,
      height:80,drag:false,min:false,max:false
    }).zindex();
  }
  function popMenuLinkMain() {
//    alert(id);
    $.dialog({
      content: "url:weixinMainController.do?weixinMainAddress&id=",
      drag :false,
      lock : true,
      title:'链接',
      opacity : 0.3,
      width:650,
      height:80,drag:false,min:false,max:false
    }).zindex();
  }
</script>