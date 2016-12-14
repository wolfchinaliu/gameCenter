<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>微信营销后台管理系统</title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<jsp:include page="/inc.jsp"></jsp:include>
<link rel="shortcut icon" href="images/favicon.ico">
<style type="text/css">
a {
	color: Black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}
/*update-start--Author:zhangguoming  Date:20140622 for：左侧树调整：加大宽度、更换节点图标、修改选中颜色*/
.tree-node-selected{
    background: #eaf2ff;
}
/*update-end--Author:zhangguoming  Date:20140622 for：左侧树调整：加大宽度、更换节点图标、修改选中颜色*/
.colors { list-style:none; margin:0px 0px 10px 0px;height: 100px;}
.colors li { float:left; margin:2px; }
.colors li a { display: block; width:15px; height:15px; cursor: pointer;} 
.colors li a.active { box-shadow:0 0 0 2px #404040 inset }
.green, .green-bg { background: #72b626; }
.blue, .blue-bg { background: #2db2ea; }
.orange, .orange-bg {background: #fa5b0f; }
.navy, .navy-bg { background: #495d7f; }
.yellow, .yellow-bg { background: #ffb400; }
.peach, .peach-bg { background :#fcab55; }
.red, .red-bg { background: #c62020; }
.beige, .beige-bg { background: #bfa980; }
.pink, .pink-bg { background: #c71c77; }
.cyan, .cyan-bg { background: #37b6bd;}
.celadon, .celadon-bg { background: #74aea1;}
.brown, .brown-bg { background: #784e3d; }
.cherry, .cherry-bg { background: #911938;}
.gray, .gray-bg { background: #f3f3f3;}
.purple, .purple-bg { background: #6957af; }
.olive, .olive-bg { background: #b3c211; }
.dark, .dark-bg { background: #404040; }
.dirty-green, .dirty-green-bg { background: #3b6e40; }
.metro{background: white;border-color: blue;border: 1;}
.metro-gray{background: #c7ccd1;}
.metro-blue{background: #daeef5;}
.metro-green{background: #c8d47b;}
.metro-orange{background: #f7cc8f;}
.metro-red{background: #f6c1bc;}
.bootstrap{background: #F2F2F2;}

</style>
<SCRIPT type="text/javascript">

	$(function() {
		$('#layout_jeecg_onlineDatagrid').datagrid({
			url : 'systemController.do?datagridOnline&field=ip,logindatetime,user.userName,',
			title : '',
			iconCls : '',
			fit : true,
			fitColumns : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10 ],
			nowarp : false,
			border : false,
			idField : 'id',
			sortName : 'logindatetime',
			sortOrder : 'desc',
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				title : '登录名',
				field : 'user.userName',
				width : 100,
				align : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return formatString('<span title="{0}">{1}</span>', value, value);
				}
			}, {
				title : 'IP',
				field : 'ip',
				width : 150,
				align : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return formatString('<span title="{0}">{1}</span>', value, value);
				}
			}, {
				title : '登录时间',
				field : 'logindatetime',
				width : 150,
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return formatString('<span title="{0}">{1}</span>', value, value);
				},
				hidden : true
			} ] ],
			onClickRow : function(rowIndex, rowData) {
			},
			onLoadSuccess : function(data) {
				$('#layout_jeecg_onlinePanel').panel('setTitle', '( ' + data.total + ' )人在线');
			},
			onLoadError : function(data) {
			}
		}).datagrid('getPager').pagination({
			showPageList : false,
			showRefresh : false,
			beforePageText : '',
			afterPageText : '/{pages}',
			displayMsg : ''
		});		
		
		$('#layout_jeecg_onlinePanel').panel({
			tools : [ {
				iconCls : 'icon-reload',
				handler : function() {
					$('#layout_jeecg_onlineDatagrid').datagrid('load', {});
				}
			} ]
		});
		
		$('#layout_east_calendar').calendar({
			fit : true,
			current : new Date(),
			border : false,
			onSelect : function(date) {
				$(this).calendar('moveTo', new Date());
			}
		});
		$(".layout-expand").click(function(){
			$('#layout_east_calendar').css("width","auto");
			$('#layout_east_calendar').parent().css("width","auto");
			$("#layout_jeecg_onlinePanel").find(".datagrid-view").css("max-height","200px");
			$("#layout_jeecg_onlinePanel .datagrid-view .datagrid-view2 .datagrid-body").css("max-height","180px").css("overflow-y","auto");
		});
	});
	var onlineInterval;
	
	function easyPanelCollapase(){
		window.clearTimeout(onlineInterval);
	}
	function easyPanelExpand(){
		onlineInterval = window.setInterval(function() {
			$('#layout_jeecg_onlineDatagrid').datagrid('load', {});
		}, 1000 * 20);
	}
	
	function changeAccount(){
    	$.dialog({
			width:700,
			height:250,
            id: 'LHG1976D',
            title: "切换公众帐号",
            max: false,
            min: false,
            resize: false,
            content: 'url:helpController.do?changeaccount',
            lock:true

        });
    }

	function checkAccount(){
		var url = "helpController.do?changeaccount";
		$.ajax({
    		url:url,
    		type:"GET",
    		dataType:"JSON",
    		success:function(data){
    			if(data.success){
    				//成功不需要处理任何事情
    			}else{
    				//失败时，判断失败的结果。
    				//如果为第一次登录，新用户，没有配置公众帐号的用户，直接进入公众帐号管理界面
    				if(data.msg=="0"){
    					alert("您当前没有配置公众帐号，请填写一个默认");
    					addOneTab("公众帐号管理","weixinAccountController.do?weixinAccount")
    				}
    				//如果存在多个公众帐号，弹窗，选择公众帐号
    				if(data.msg=="2"){
    					openwindow('选择默认公众帐号', "helpController.do?changeaccount",'', 800, 600);
    				}
    			}
    		}
    	});
	}

</SCRIPT>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<!-- 顶部
<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background: url(plug-in/weixin/logo/back-gradient.gif);">
-->
<div region="north" border="false" title="" style="BACKGROUND:; height: 65px; padding: 1px; overflow: hidden;">

<table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td align="left" style="vertical-align: text-bottom;"><img src="plug-in/weixin/logo/logo_weixin.png"></td>
		<td align="right" nowrap>
		<table>
			<tr>
				<td valign="top" height="60">
					<span style="color:">公众号：</span><span style="color: #666633">${WEIXIN_ACCOUNT.accountname } <a href="#" onclick="changeAccount()">[切换]</a>&nbsp;&nbsp;
					<span style="color:">用户：</span><span style="color: #666633">${userName }&nbsp;&nbsp;
					
					<span style="color:">版本</span>：<span style="color: #666633">${roleName}</span>&nbsp;
				</td>
			</tr>
			<tr>
			<td>
			<div style="position: absolute; right: 0px; bottom: 0px;">
				<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_bzMenu" iconCls="icon-help1">帮助</a>
				<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_pfMenu" iconCls="icon-color">换肤</a>	
				<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon_set">设置</a>				
				<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-out">注销</a>
			</div>
			<div id="layout_north_bzMenu" style="width: 100px; display: none;">
				
				<div><a href="helpController.do?list" target="_blank">使用指南</a></div>
				
				<%--<div class="menu-sep"></div>
				 <div><a href="helpController.do?contact" target="_blank">在线咨询</a></div>--%>
			</div>
			<div id="layout_north_pfMenu" style="width:50px; height:42px;display: none;background: white;">
				
				
				
				<div style="border:0;background: white;">
					<ul class="colors" id="color1">
				      <li><a href="#" class="metro-green" title="Green" onclick="changeTheme('metro-green');"></a></li>
				      <li><a href="#" class="blue" title="Blue" onclick="changeTheme('default');"></a></li>
				     
				      
				      <li><a href="#" class="metro-orange" title="metro-orange" onclick="changeTheme('metro-orange');"></a></li>
				      <li><a href="#" class="bootstrap" title="metro" onclick="changeTheme('metro');"></a></li>
				     
				    </ul>
				</div>
				<div style="border:0;background: white;">
					<ul class="colors" id="color1" >
						<li><a href="#" class="metro-red" title="metro-red" onclick="changeTheme('metro-red');"></a></li>
				      	<li><a href="#" class="gray" title="Gray" onclick="changeTheme('gray');"></a></li>
				       <li><a href="#" class="metro-blue" title="metro-blue" onclick="changeTheme('metro-blue');"></a></li>
				      <li><a href="#" class="metro-gray" title="metro-gray" onclick="changeTheme('metro-gray');"></a></li>
				      
				    </ul>
				</div>
				
				<%-- 
				<div onclick="changeTheme('gray');" style="background: #f3f3f3;"></div>
				
				<div onclick="changeTheme('metro');" style="background: #FFFFFF;"></div>
				
				<div onclick="changeTheme('bootstrap');" style="background: #F2F2F2;"></div>
				
				<div onclick="changeTheme('black');" style="background: #666666;"></div>
				
				<div onclick="changeTheme('metro-blue');" style="background: #daeef5;"></div>
				
				<div onclick="changeTheme('metro-gray');" style="background: #c7ccd1;"></div>
				
				<div onclick="changeTheme('metro-green');" style="background: #c8d47b;"></div>
				
				<div onclick="changeTheme('metro-orange');" style="background: #f7cc8f;"></div>
				
				<div onclick="changeTheme('metro-red');" style="background: #f6c1bc;"></div>
				--%>
			</div>
			<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
				<div onclick="openwindow('用户信息','userController.do?userinfo')">个人信息</div>
				<div class="menu-sep"></div>
				<div onclick="add('修改密码','userController.do?changepassword')">修改密码</div>
				<%-- <div class="menu-sep"></div>
				<div onclick="add('个性化设置','helpController.do?styleset')">个性化设置</div>
				
				<div class="menu-sep"></div>
				<div onclick="add('修改首页风格','userController.do?changestyle')">首页风格</div>
				--%>
			</div>
			<div id="layout_north_zxMenu" style="width: 100px; display: none;">
				
				<div onclick="exit('loginController.do?logout','确定退出该系统吗 ?',1);">退出系统</div>
			</div>
			
			</td>
			</tr>
		</table>
		</td>
		<td align="right"></td>
	</tr>
</table>
</div>
<!-- 左侧-->
<div region="west" split="true" href="loginController.do?menu" title="导航菜单" style="width: 200px; padding: 0px;"></div>
<!-- 中间-->
<div id="mainPanle" region="center" style="overflow: hidden;">
<div id="maintabs" class="easyui-tabs" fit="true" border="false">
<div class="easyui-tab" title="首页" href="loginController.do?home" style="padding: 2px;" ></div>
<c:if test="${map=='1'}">
	<div class="easyui-tab" title="地图" style="padding: 1px; overflow: hidden;"><iframe name="myMap" id="myMap" scrolling="no" frameborder="0" src="mapController.do?map"
		style="width: 100%; height: 99.5%;"></iframe></div>
</c:if></div>
</div>
<!-- 右侧 
<div collapsed="true" region="east" iconCls="icon-reload" title="辅助工具" split="true" style="width: 190px;"
	data-options="onCollapse:function(){easyPanelCollapase()},onExpand:function(){easyPanelExpand()}">
<div id="tabs" class="easyui-tabs" border="false" style="height: 240px">
<div title="日历" style="padding: 0px; overflow: hidden; color: red;">
<div id="layout_east_calendar"></div>
</div>
</div>
<div id="layout_jeecg_onlinePanel" data-options="fit:true,border:false" title="用户在线列表">
<table id="layout_jeecg_onlineDatagrid"></table>
</div>
-->
<!-- 底部 
<div region="south" border="false" style="height: 25px; overflow: hidden;">
<div align="center" style="color: #CC99FF; padding-top: 2px"> <span class="tip">WX (推荐谷歌浏览器，获得更快响应速度) 技术支持: qq785863737 </span></div>
</div>-->
<div id="mm" class="easyui-menu" style="width: 150px;">
<div id="mm-tabupdate">刷新</div>
<div id="mm-tabclose">关闭</div>
<div id="mm-tabcloseall">全部关闭</div>
<div id="mm-tabcloseother">除此之外全部关闭</div>
<div class="menu-sep"></div>
<div id="mm-tabcloseright">当前页右侧全部关闭</div>
<div id="mm-tabcloseleft">当前页左侧全部关闭</div>

</div>

</body>
</html>