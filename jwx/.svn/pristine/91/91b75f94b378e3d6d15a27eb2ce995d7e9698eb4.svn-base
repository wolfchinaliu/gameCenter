<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinGameTypeList"   fitColumns="false" title="更多游戏" actionUrl="weixinGameTypeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属商户"  field="accountId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="图标"  field="logoPath"  hidden="true" image="true" imageSize="50,50" queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="名称"  field="gameName"  hidden="true"  queryMode="single"  width="120" query='true'></t:dgCol>
   <t:dgCol title="活动描述"  field="description"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="加入时间"  field="addTime"  hidden="true"  formatter="yyyy-MM-dd hh:mm:ss" queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="状态" field="status" hidden="true" replace="可用_0,待审核_1,测试中_2,下架_3" queryMode="single"
               width="140"></t:dgCol>

   <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
     <t:dgFunOpt title="进入我的游戏"  funname="gomygame(id,gameName)"></t:dgFunOpt>
   <t:dgConfOpt  url="weixinGameTypeController.do?updateStatus&id={id}" title="提交审核" exp="status#eq#2" message="确认提交审核"></t:dgConfOpt>
   <t:dgConfOpt  url="weixinGameTypeController.do?updateStatus&id={id}" title="通过审核" exp="status#eq#1" message="确认审核通过"></t:dgConfOpt>
      <t:dgDelOpt title="删除" url="weixinGameTypeController.do?doDel&id={id}" exp="status#eq#2,3"/>
     

   <t:dgToolBar title="录入" icon="icon-add" url="weixinGameTypeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinGameTypeController.do?goAdd" funname="update" ></t:dgToolBar>
 <%--   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinLotteryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <%-- <t:dgToolBar title="查看" icon="icon-search" url="weixinActivityController.do?goEdit&type=${type }" funname="detail"></t:dgToolBar> --%>
   <t:dgToolBar title="活动链接" icon="icon-edit"
			 funname="popMenuLink"	></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
 </script>
 <script type="text/javascript">
  	
  	function popMenuLink(title,url, id,width,height) {
  		gridname=id;
  		var rowsData = $('#'+id).datagrid('getSelections');
  		if (!rowsData || rowsData.length==0) {
  			tip('请选择相关活动');
  			return;
  		}
  		if (rowsData.length>1) {
  			tip('请选择相关活动');
  			return;
  		}
  		$.dialog({
 			content: "url:weixinActivityController.do?weixinActivityAddress&hdid="+rowsData[0].id,
 			drag :false,
 			lock : true,
 			title:'活动链接',
 			opacity : 0.3,
 			width:650,
 			height:80,drag:false,min:false,max:false
 		});
  	}
  	
  	function update(title, url, id, width, height) {
  	    gridname = id;
  	    var rowsData = $('#' + id).datagrid('getSelections');
  	    if (!rowsData || rowsData.length == 0) {
  	        tip('请选择查看项目');
  	        return;
  	    }
  	    if (rowsData.length > 1) {
  	        tip('请选择一条记录再查看');
  	        return;
  	    }
  	    //判断是否有查看权限
  	    $.ajax({
  	    	url:"weixinGameTypeController.do?isCanCat&id="+rowsData[0].id,
  	    	type:"POST",
  	    	dataType:"JSON",
  	    	success:function(data){
  	    		if(data.success){
  	    			url += '&id=' + rowsData[0].id;
  	    	  	    createwindow(title, url, width, height);
  	    		}else{
  	    			tip('没有查看权限');
  	  	       		return;
  	    		}
  	    	}
  	    });
  	}
  	 function gomygame(id,title){
  		 var url = "weixinOtherGameController.do?otherGameList&gameType="+id;
  		 var jq = top.jQuery; 
  		 if (!jq("#mainTabs").tabs('exists', title)) {
  	        var allTabs = jq("#mainTabs").tabs("tabs");
  	      	jq("#mainTabs").tabs('add', {
  	            title: title,
  	            content:  '<iframe frameborder="0" scrolling="true" src="' + url + '" style="width:100%;height:100%;position:relative;"></iframe>',
  	            closable: true,
  	            icon: 'icon-standard-controller'
  	         });
  	    } else {
  	    	jq("#mainTabs").tabs('select', title);
  	    }
  	 }
  </script>