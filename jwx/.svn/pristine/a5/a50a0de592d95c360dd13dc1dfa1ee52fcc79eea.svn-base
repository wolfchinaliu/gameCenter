<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<script type="text/javascript">
	$("#A1").click(function () {
	    //portal.portal("addColumn");
	});
	$("#A2").click(function () {
	    portal.portal("removeColumn", portal.portal("columns") - 1);
	});
	
	$("#A3").click(function () {
	    $.easyui.loading();
	    $.util.exec(function () { $.easyui.loaded(); }, 1000);
	});
	
	$("#A4").click(function () {
		$.easyui.showDialog({
	        title: "切换公众号",
	        href: "helpController.do?changeaccount",
	        topMost: false
	});
    //$.easyui.showDialog({
    //    href: "http://www.baidu.com", iniframe: true
    //});
    //var str = "jqext/common/index-startup.js", url = $.util.resolveUrl(str);
    //window.mainpage.util.getUrlResponse(url, function (text) {
    //    alert(text);
    //});
	}); 
	function a(){
		
		$.ajax({ 
	        type: 'POST', 
	        url: 'tFavoMenuController.do?addFavoMenu', 
	        data: {menu_id:node.id} , 
	        dataType:'json',
	        success:function(data) {
	        	
	        	$.easyui.messager.show(data.msg);
	        }
	    });
	}
	
	
	function toShowMessage(id){
		
		$('#dd').dialog({
            title: "新消息",
            width: 600,
            height: 400,
            href: 'weixinAnnouncementController.do?goShow&id='+id,
            topMost: false,
            modal:true,
            buttons:[{
				text:'关闭',
				iconCls: "icon-no",
				handler:function(){
					$('#dd').dialog('close');
				}
			}]
        });
		//createdetailwindow('详细内容','weixinAnnouncementController.do?goShow&id='+id,null,'100%', '100%');
	}
</script>
<div class="easyui-layout" data-options="fit: true">
                        <div data-options="region: 'north', split: false, border: false" style="height: 33px;">
                            <div class="easyui-toolbar">
                                <%--<a id="A1" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-standard-add'">添加公众账号</a>
                                 <span>-</span>
                                <a id="A2" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-standard-delete'">删除一列</a>
                                <span>-</span>
                                <a id="A3" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-for-job'">测试操作01</a>
                                <span>-</span>
                                <a id="A4" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-config'">配置公众账号</a>--%>
                            </div>
                        </div>
                        
                        <div data-options="region: 'center', border: false" style="overflow: hidden;">
                            <div id="portal" class="easyui-portal" data-options="fit: true, border: false">
                                <div style="width: 50%;">
                                
                                	<div data-options="title: '公众配置信息', height: 220, collapsible: true, closable: true">
                                        <ul class="portlet-list">
                                            <li>公众账号：${weixinAccount.accountname}</li>
                                            <li>类型：<c:if test="${weixinAccount.accounttype == 1}">服务号</c:if><c:if test="${weixinAccount.accounttype == 2}">订阅号</c:if></li>
                                            <li>接口地址：${localhosturl}/weixin.do?api&t=${weixinAccount.id }</li>
                                            <li>TOKEN：${weixinAccount.accounttoken}</li>
                                            <li>商户acctId：${weixinAccount.acctId}</li>
                                            <li>(登录 微信公众平台-“开发者中心”，按要求填写接口配置信息)</li>
                                        </ul>
                                    </div>
                                    
                                    <div data-options="title: '新功能简介', height: 220, collapsible: true, closable: true">
                                        
                                        <ul class="portlet-list" style="list-style-type: decimal;">
                                            <li>
                                                <span>小马微信摇一摇功能上线了</span>
                                                <ul>
                                                    <li>您只需在小马微信后台硬件中心摇一摇模块完成功能设置，再将您的小马微信iBeacon安放在合适的位置，就可以完美支持微信的摇一摇周边功能。</li>
                                                </ul>
                                            </li>
                                            <li>
                                                <span>会员交易升级</span>
                                                <ul>
                                                    <li>会员通过后台消费, “余额”, “积分”, ”现金”可以自由选择, 任意搭配. 还可以使用优惠券.</li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>   
                                </div>
                                
                                
                                <div style="width: 50%;">
                                    <div data-options="title: '关于WeChat CMS', height: 220, collapsible: true, closable: true" style="padding: 10px;">
                                        	WeChat CMS是打造的一个专门针对微信公众账号提供营销推广服务的第三方平台。主要功能是针对微信商家公众号提供与众不同的、有针对性的营销推广服务。
                                        	<br><br>通过WeChat CMS平台，用户可以轻松管理自己的微信各类信息，对微信公众账号进行维护、开展智能机器人、在线发优惠劵、抽奖、刮奖、派发会员卡、打造微官网、开启微团购等多种活动，对微信营销实现有效监控，极大扩展潜在客户群和实现企业的运营目标。
                                        	
                                    </div>
                                    <div id="donatePanel" data-options="title: '版本更新', height: 220, closable: true, collapsible: true, closable: true, tools: [{ iconCls: 'icon-hamburg-refresh', handler: function () { window.donate.reload(); } }]" style="padding: 10px;">
                                        
                                        <%-- 
                                        <ul class="portlet-list">
                                        	<c:forEach items="${weixinAnnouncementList}" var="announcement">
                                        		<li><a href="#" onclick="toShowMessage('${announcement.id}');">${announcement.title}</a></li>
                                        	</c:forEach>
                                        </ul>
                                        --%>
                                        <div class="c_announcement"></div>
                                        <script type="text/javascript">
                                        	$.ajax({
                                        		
                                        		url : "weixinAnnouncementController.do?getAnnouncements",
                                        		dataType : "JSON",
            									method : "POST",
            									success : function(data) {
            										
            										var htmlContent = "<ul class=\"portlet-list\">";
            										var list=jQuery.parseJSON(data);
            										for (var i = 0; i < list.length; i++) {
            											
            											htmlContent += "<li><a href=\"#\" onclick=\"toShowMessage('"+list[i].id+"');\">"+list[i].title+"</a></li>";
            										}
            										
            										htmlContent += "</ul>";
            										$(".c_announcement").html(htmlContent);
            									}
                                        	});
                                        </script>
                                    </div>
                                </div>
                                
                                
                                
                                
                            </div>
                        </div>
                        
                     </div>