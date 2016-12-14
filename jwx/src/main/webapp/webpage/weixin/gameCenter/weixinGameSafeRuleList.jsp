<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinGameSafeRuleList"   fitColumns="false" title="更多游戏" actionUrl="weixinGameSafeRuleController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="规则ID"  field="id"  hidden="true"  queryMode="single"  width="280"></t:dgCol>
   <t:dgCol title="每次最大流量值"  field="maxFlow"  hidden="true"  queryMode="group"  width="140"></t:dgCol>
   <t:dgCol title="每人每周期最大次数"  field="everyoneTimes"  hidden="true"  queryMode="single"  width="160" query='true'></t:dgCol>
   <t:dgCol title="每周期总最大次数"  field="maxTimes"  hidden="true"  queryMode="single" replace="无_0" width="160"></t:dgCol>
   <t:dgCol title="周期" field="frequency" hidden="true" replace="无_0,天_1,周_2,月_3" queryMode="single"
               width="80"></t:dgCol>

   <t:dgCol title="操作" field="opt" width="110"></t:dgCol>
      <t:dgDelOpt title="删除" url="weixinGameSafeRuleController.do?doDel&id={id}" />
     

   <t:dgToolBar title="录入" icon="icon-add" url="weixinGameSafeRuleController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinGameSafeRuleController.do?goAdd" funname="detail" ></t:dgToolBar>
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
 
	function createwindow(title, addurl, width, height) {
		 
	    width = width ? width : 700;
	    height = height ? height : 400;
	    if (width == "100%" || height == "100%") {
	        width = window.top.document.body.offsetWidth;
	        height = window.top.document.body.offsetHeight - 100;
	    }
	    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
	    if (typeof(windowapi) == 'undefined') {
	        $.dialog({
	            content: 'url:' + addurl,
	            lock: true,
	            //zIndex:1990,
	            width: width,
	            height: height,
	            title: title,
	            opacity: 0.3,
	            cache: false,
	            ok: function () {
	            	this.iframe.contentWindow.fatherPag(window);
	            	 $('#btn_sub', this.iframe.contentWindow.document).click();
	                return false;
	            },
	            cancelVal: '关闭',
	            cancel: true /*为true等价于function(){}*/
	        }).zindex();
	    } else {
	        W.$.dialog({
	            content: 'url:' + addurl,
	            lock: true,
	            width: width,
	            //zIndex:1990,
	            height: height,
	            parent: windowapi,
	            title: title,
	            opacity: 0.3,
	            cache: false,
	            ok: function () {
	            	this.iframe.contentWindow.fatherPag(window);
	                $('#btn_sub', this.iframe.contentWindow.document).click();
	                return false;
	            },
	            cancelVal: '关闭',
	            cancel: true /*为true等价于function(){}*/
	        }).zindex();
	    }
	    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数

	}
 </script>
 <script type="text/javascript">
  </script>