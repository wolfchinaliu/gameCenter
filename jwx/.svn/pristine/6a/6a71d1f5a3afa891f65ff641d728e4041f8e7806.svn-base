<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinMemberList" checkbox="true" fitColumns="false" title="" sortName="subscribeTime" sortOrder="desc" actionUrl="weixinMemberController.do?datagrid"  
  	idField="id" fit="true" queryMode="group"  >
   <t:dgCol title="用户的标识"  field="id" hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户openId"  field="openId"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="粉丝昵称"  field="nickName"  hidden="true"  query="true" width="120" ></t:dgCol>
   <t:dgCol title="头像"  field="headImgUrl"  hidden="true" image="true" imageSize="40,40" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="手机号"  field="phoneNumber"  hidden="true"  queryMode="single" query="true" width="120"></t:dgCol>

   <t:dgCol title="省份"  field="province"  hidden="true" width="120"></t:dgCol>
   <t:dgCol title="城市"  field="city"  hidden="true" width="120"></t:dgCol>

   <t:dgCol title="性别"  field="sex"  hidden="true" dictionary="sex" query="true" width="120"></t:dgCol>
   <%--<t:dgCol title="国家"  field="country"  hidden="true" width="120"></t:dgCol>--%>
   <%--<t:dgCol title="省份"  field="province"  hidden="true"  query="false" width="120"></t:dgCol>--%>
   <%--<t:dgCol title="城市"  field="city"  hidden="true"  query="true"  width="120"></t:dgCol>--%>

   <%--<t:dgCol title="语言"  field="language"  hidden="false"  queryMode="group"  width="120"></t:dgCol>--%>


   <t:dgCol title="所在分组"  field="weixinGroupEntity.id"  hidden="true" query="true" replace="${groupList}" width="120"></t:dgCol>
   
   <t:dgCol title="关注时间"  field="subscribeTime" formatter="yyyy-MM-dd" hidden="true"  queryMode="group" query="true" width="120" sortable="true"></t:dgCol> 
   <%--<t:dgCol title="是否订阅"  field="subscribe"  hidden="true" replace="是_1,否_0" query="true"  width="120"></t:dgCol>--%>
   <t:dgCol title="所属公众号"  field="accountId"  hidden="false"  queryMode="group"  width="120"></t:dgCol>
   <%--
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   
   <t:dgDelOpt title="删除" url="weixinMemberController.do?doDel&id={id}" /> --%>
      
   <t:dgToolBar title="同步微信粉丝" icon="icon-reload" url="weixinMemberController.do?loadMembers" funname="loadMembers"></t:dgToolBar>
   
   <%-- <t:dgToolBar title="编辑" icon="icon-edit" url="weixinMemberController.do?goUpdate" funname="update"></t:dgToolBar>
   		<t:dgToolBar title="删除"  icon="icon-remove" url="weixinMemberController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   --%>
   
   <t:dgToolBar title="移动分组" icon="icon-edit" url="weixinMemberController.do?goUpdateGroup" funname="goUpdate"></t:dgToolBar>  
   
   <%--<t:dgToolBar title="刷新" icon="icon-reload" funname="goReload"></t:dgToolBar>
   --%>
   <t:dgToolBar title="excel导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinMemberController.do?goView" funname="detail"></t:dgToolBar>
   
   <t:dgToolBar title="发送消息" icon="icon-redo" url="weixinMessageController.do?goAdd" funname="toSendMessage"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/member/weixinMemberList.js"></script>		
 <script type="text/javascript">

 
 $(document).ready(function(){
		//给时间控件加上样式
		$("#weixinMemberListtb").css("height", "60px");
			$("#weixinMemberListtb").find("input[name='subscribeTime_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
			$("#weixinMemberListtb").find("input[name='subscribeTime_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
}); 
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinMemberController.do?upload', "weixinMemberList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinMemberController.do?exportXls","weixinMemberList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinMemberController.do?exportXlsByT","weixinMemberList");
}
function loadMembers(){
	
	$.dialog.confirm("您确定要同步微信平台上面的关注用户?", function(){
  		
		//进度条
		$.messager.progress();
  		$.ajax({
            type: "POST",
            url: "weixinMemberController.do?loadMembers",
            dataType: "json",
            success: function(data){
            	$.messager.progress('close');
            	$('#weixinMemberList').datagrid('reload');
            	tip(data.msg);
            }
        });
  		
	}, function(){
	}).zindex();
}
function goReload(){
	
	$('#weixinMemberList').datagrid('reload');
}

function toSendMessage(){
	
	var checkedItems = $('#weixinMemberList').datagrid('getChecked');
	var checked_ids = [];
	$.each(checkedItems, function(index, item){
		checked_ids.push(item.openId);
	});
	
	if(checked_ids.length<2){
		
		alert("请至少选择两条记录!");
		return;
	}
	
	var checked_names = [];
	$.each(checkedItems, function(index, item){
		checked_names.push(item.nickName);
	});
	
	
	createdetailwindow('消息发送','weixinMessageController.do?goAdd&openids='+checked_ids+'&nickNames='+encodeURI(encodeURI(checked_names)),'weixinMemberList','100%', '100%');
}

 function goUpdate(){
     var checkedItems = $('#weixinMemberList').datagrid('getChecked');

     var checked_ids = [];
     $.each(checkedItems, function(index, item){
         checked_ids.push(item.id);
     });
     if(checked_ids.length<1){

         alert("请至少选择一条记录!");
         return;
     }
     var checked_openIds = [];
     $.each(checkedItems, function(index, item){
         checked_openIds.push(item.openId);
     });
     var checked_names = [];
     $.each(checkedItems, function(index, item){
         checked_names.push(item.nickName);
     });

     createwindow('移动分组','weixinMemberController.do?goUpdateGroup&openIds='+checked_openIds+'&nickNames='+encodeURI(encodeURI(checked_names)),'weixinMemberList','100%', '100%');
 }

 </script>