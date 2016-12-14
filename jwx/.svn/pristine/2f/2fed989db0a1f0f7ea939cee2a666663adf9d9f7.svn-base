<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<%-- 
<c:if test="${isShowpoint == '0'}">
<div style="border: solid #f7cc8f 1px;background: #FFEFCE;margin:1px 1px 1px 1px;" id="point_title">
 	
 	<p style="padding-left: 15px;">微门店自定义菜单链接地址：<%=basePath%>/shopController.do?location&accountid=${WEIXIN_ACCOUNT.id}</p>
 	
</div>
</c:if>--%>



<div class="easyui-layout" fit="true" style="border: 0px;">

  <div region="center" style="padding:1px;border: 0px;">

  <t:datagrid name="weixinLocationList" checkbox="true" fitColumns="true" title="" actionUrl="weixinLocationController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="门店名称"  field="businessName" query="true" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="分店名"  field="branchName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所在省"  field="province"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所在市"  field="city"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所在区"  field="district"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="道街地址"  field="address"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="门店的电话"  field="telephone"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="门店的类型"  field="category"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="经度"  field="longitude"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="纬度"  field="latitude"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="新品推荐"  field="recommend"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="特色服务"  field="special"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="简介"  field="introduction"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="营业时间"  field="openTime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="人均消费"  field="avgPrice"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="二维码"  field="qrcodeLogo"  hidden="false" queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="状态"  field="status"  hidden="true" replace="生效_1,审核中_0,失效_3" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核信息"  field="msg"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="门店编号"  field="poiId"  hidden="false"  width="120"></t:dgCol>
   
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <%-- <t:dgFunOpt title="上传" funname="doUpload(id)" exp="status#eq#0"/>--%>
   <t:dgFunOpt title="修改" funname="toUpdate(id)" exp="status#eq#1"/>
   <t:dgDelOpt title="删除" url="weixinLocationController.do?doDel&id={id}"/>
   
   <t:dgToolBar title="添加门店" icon="icon-add" url="weixinLocationController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="编辑" icon="icon-edit" url="weixinLocationController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>--%>
   
   <t:dgToolBar title="查看" icon="icon-search" url="weixinLocationController.do?goUpdate" funname="toView" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="同步门店" icon="icon-reload" url="weixinLocationController.do?loadWeixinLocationBatch" funname="loadWeixinLocationBatch"></t:dgToolBar>
   
  
   
   <%-- <t:dgToolBar title="链接地址" icon="icon-cut" url="weixinLocationController.do?showCateUrl" funname="showCateUrl" width="70%" height="20%"></t:dgToolBar>
   
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinLocationController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> 
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/business/weixinLocationList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 function toUpdate(id){

		
		var url = "weixinLocationController.do?toUpdate&id="+id;
		createwindow('修改门店信息',url,'weixinLocationList','100%', '100%');
	}
 function doUpload(id){

		//进度条
		$.ajax({
         type: "POST",
         url: "weixinLocationController.do?doUpload&id="+id,
         dataType: "json",
         success: function(data){
         	
         	$('#weixinLocationList').datagrid('reload');            	
         	tip(data.msg);
         }        
     });
}
 function loadWeixinLocationBatch(){

		//进度条
		$.messager.progress();
  		$.ajax({
            type: "POST",
            url: "weixinLocationController.do?loadWeixinLocationBatch",
            dataType: "json",
            success: function(data){
            	
            	$.messager.progress('close');
            	$('#weixinLocationList').datagrid('reload');            	
            	tip(data.msg);
            }        
        });
}
 
 function showCateUrl(){

		$.dialog({
			content: "url:weixinLocationController.do?showCateUrl",
			drag :false,
			lock : true,
			title:'微门店链接地址',
			opacity : 0.3,
			width:650,
			height:80,drag:false,min:false,max:false
		}).zindex();
}
function toView(){
	
	//获取datagrid中复选框值
	var checkedItems = $('#weixinLocationList').datagrid('getChecked');
	var checked_ids = [];
	$.each(checkedItems, function(index, item){
		checked_ids.push(item.id);
	});   
		
	if(checked_ids.length != 1){
		
		tip("请选择一条记录!");
		return;
	}
	
	var url = "weixinLocationController.do?goView&id="+checked_ids;
	createdetailwindow('门店详情',url,'weixinLocationList','100%', '100%');
	
	
}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinLocationController.do?upload', "weixinLocationList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinLocationController.do?exportXls","weixinLocationList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinLocationController.do?exportXlsByT","weixinLocationList");
}
 </script>