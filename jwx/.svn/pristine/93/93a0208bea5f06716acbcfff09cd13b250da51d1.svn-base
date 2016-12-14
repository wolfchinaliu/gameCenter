<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true" style="border: 0px;">
  <div region="center" style="padding:1px;border: 0px;" >
  <t:datagrid name="weixinAccountList" checkbox="true" fitColumns="true" title="管理微信公众帐号" actionUrl="weixinAccountController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="0"></t:dgCol>
   <t:dgCol title="公众帐号名称"  field="accountname" query="false" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众帐号TOKEN"  field="accounttoken"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="公众微信号"  field="accountnumber"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
	<t:dgCol title="原始ID" field="weixin_accountid" hidden="true" queryMode="singgle" width="120"></t:dgCol>
   <t:dgCol title="公众号类型"  dictionary="weixintype"  query="false" field="accounttype"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="电子邮箱"  field="accountemail"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="公众帐号描述"  field="accountdesc"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="公众帐号APPID"  field="accountappid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众帐号APPSECRET"  field="accountappsecret"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="ACCESS_TOKEN"  field="accountaccesstoken"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="TOKEN获取时间"  field="addtoekntime"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="授权方式"  field="authorizationType"  hidden="true"  replace="全功能授权_1,url+token_0,单网页授权_2,已取消授权_3" queryMode="single"  width="120"></t:dgCol>
 
   <%--
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinAccountController.do?doDel&id={id}" />
    --%>
   <%--<t:dgToolBar title="添加公众账号" icon="icon-add" url="weixinAccountController.do?goAdd" funname="myadd"></t:dgToolBar>--%>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinAccountController.do?goUpdate" funname="update"></t:dgToolBar>
   <%--<t:dgToolBar title="初始化数据" icon="icon-ok" url="weixinAccountController.do?doInit" funname="toInit"></t:dgToolBar>--%>
   <%--
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinAccountController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
     --%>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinAccountController.do?goUpdate" funname="detail"></t:dgToolBar>
   
   
   <t:dgToolBar title="全功能授权" icon="icon-putout" funname="weixinAuth"></t:dgToolBar>
   
   <t:dgToolBar title="单网页授权" icon="icon-putout" funname="weixinAuth2"></t:dgToolBar>
  
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/guanjia/account/weixinAccountList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
 function weixinAuth() {
	 window.open('${url}'+ "/weixinOpenPlatform.do?auth");
 }
 
 function weixinAuth2() {
     window.open('${url}'+ "/weixinOpenPlatform.do?auth2");
 }
 
 function myadd1(title,addurl,gname,width,height) {
		
		gridname=gname;
		
		var getData = $('#'+gridname).datagrid('getData');
		
		var url = "weixinAcctController.do?getTenant&fieldtype=createAccountIdNum";

		$.ajax({
			url:url,
			type:"GET",
			dataType:'json',
			success:function(data){
				if(data.success){
					
					var cnum = data.msg;
					if(getData.total < cnum){

						createwindow(title, addurl,width,height);
					}else{
						
						tip('您目前的版本只能创建'+cnum+'个公众账号，如需创建多个公众账号，请升级!');
						return;
					}
				}
			}
		});
	}
 
 function myadd(title,addurl,gname,width,height) {
		
		gridname=gname;
		
		var getData = $('#'+gridname).datagrid('getData');
		

					if(getData.total < 3){

						createwindow(title, addurl,width,height);
					}else{
						
						tip('您目前的版本只能创建3个公众账号，如需创建多个公众账号，请升级!');
						return;
					}

	}

 function toInit(){
		
		//获取datagrid中复选框值
		var checkedItems = $('#weixinAccountList').datagrid('getChecked');
		var checked_ids = [];
		$.each(checkedItems, function(index, item){
			checked_ids.push(item.id);
		});
			
		if(checked_ids.length<1){
			
			alert("请至少选择一条记录!");
			return;
		}
		
		if(checked_ids.length>1){
			
			alert("请选择一条记录!");
			return;
		}
		
	  	$.dialog.confirm("您确定要初始化数据?", function(){
	  		
	  		//AJAX异步执行批量操作
	  		$.ajax({
	            type: "POST",
	            url: "weixinAccountController.do?doInit",
	            data: {id:checked_ids.toString()},
	            dataType: "json",
	            success: function(data){
	            	
					alert(data.msg);
	            }        
	        });
	  		
		}, function(){
		}).zindex();
	}
 </script>