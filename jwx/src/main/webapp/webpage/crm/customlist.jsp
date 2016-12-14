<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>灵动移动CRM</title>
</head>
<body>
	<div id="dlg1" class="easyui-dialog" style="padding:20px 6px;width:80%;" data-options="inline:true,modal:true,closed:true,title:'搜索'">
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" prompt="姓名" style="width:100%;height:30px">
			</div>
			<div style="margin-bottom:10px">
				<select class="textbox" prompt="级别" style="width:100%;height:30px;">
					<option>请选择客户级别</option>
					<option>重点</option>
					<option>普通</option>
				</select>	
			</div>
			<div style="margin-bottom:10px">
				<select class="textbox" prompt="来源" style="width:100%;height:30px">
					<option>请选择客户来源</option>
					<option>电话</option>
					<option>网络</option>
				</select>
			</div>
			<div style="margin-bottom:10px">
				<select class="textbox" prompt="类型" style="width:100%;height:30px">
					<option>请选择行业</option>
					<option>金融</option>
					<option>电信</option>
					<option>教育</option>
					<option>房地产</option>
					<option>基础能源</option>
					<option>农业</option>
					<option>服务业</option>
					<option>高科技</option>
					<option>IT</option>
					<option>原材料</option>
					<option>物流</option>
					<option>建材</option>
					<option>能源</option>
					<option>运输</option>
					<option>航空</option>
					<option>医药</option>
				</select>
			</div>

			<div class="dialog-button">
				<a href="javascript:void(0)" class="easyui-linkbutton" style="width:49%;height:35px" onclick="$('#dlg1').dialog('close')">搜索</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" style="width:49%;height:35px" onclick="$('#dlg1').dialog('close')">取消</a>
			</div>
	</div>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<div class="m-title">
					我的客户
				</div>
                <div class="m-left">
                    <a href="javascript:history.go(-1);" class="easyui-linkbutton m-back" plain="true" outline="false"></a>
                </div>
                <div class="m-right">
                    <a href="javascript:void(0)" class="easyui-menubutton" data-options="iconCls:'icon-more',plain:true,hasDownArrow:false,menu:'#mm',menuAlign:'right'"></a>
                </div>
				
			</div>
		</header>
		<div style="text-align:center;padding:10px">
			<span class="m-buttongroup">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:true" onclick="" style="width:80px;height:30px">综合排序</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1'" onclick="" style="width:80px;height:30px">按客户热度</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1'" onclick="" style="width:80px;height:30px">按客户等级</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1'" onclick="" style="width:80px;height:30px">按创建时间</a> 
			</span>
		</div>
		<ul class="easyui-datalist" data-options="
                
                lines: true,
                border: false,
                textFormatter: function(value){
                    return '<a href\'javascript:void(0)\' class=\'datalist-link\'>' + value + '</a>';
                },
                onClickRow: function(index,row){
                    $('#p2-title').html(row.text);
                    $.mobile.go('#p2');
                }
                ">
            <li>北京现代4S汽车服务有限公司</li>
            <li>浙江嘉业贸易有限公司</li>
            <li>广州白云山集团</li>
            <li>杭州客运有限公司</li>
            <li>长安之星企业</li>
            <li>广州电台</li>
            <li>江苏集群信息技术有限公司</li>
            <li>南方电网</li>
            <li>广州别克4S汽车销售有限公司</li>
            <li>香港永恒实业</li>
            <li>广州碧桂园有限公司</li>
            <li>东莞鞋业</li>
            <li>广州食品有限公司</li>
            <li>东山贸易公司</li>
            <li>浙江网新网络技术</li>
            <li>超凡实业</li>
        </ul>
        
        	<a href="javascript:void(0)" class="easyui-linkbutton m-next" plain="true" outline="true" 
        	style="width:80px;margin-top: 10px;margin-bottom: 10px;margin-right: 5px;float: right;">加载更多</a>
        
		
	</div>
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div onclick="$('#dlg1').dialog('open').dialog('center')" data-options="iconCls:'icon-search'">高级检索</div>
		<div class="menu-sep"></div>
		<div onclick="window.location.href='customController.do?addCustom'" data-options="iconCls:'icon-add'">创建客户</div>

	</div>



	<div id="p2" class="easyui-navpanel">
        <header>
            <div class="m-toolbar">
                <span id="p2-title" class="m-title">Detail</span>
                <div class="m-left">
                    <a href="javascript:void(0)" class="easyui-linkbutton m-back" plain="true" outline="true" style="width:50px" onclick="$.mobile.back()">返回</a>
                </div>
            </div>
        </header>
		<p style="font-size:14px">公司介绍</p>
		<ul>
			<li>企业规模：3000人以上</li>
			<li>经营范围：钢铁</li>
			<li>公司性质：民营企业</li>
			<li>企业法人：张三</li>
			<li>联系电话：020-90020229</li>
			<li>联系地址：北京市</li>
		</ul>
        <div style="margin:50px 0 0;text-align:center">
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100px;height:30px" onclick="$.mobile.back()">返回首页</a>
        </div>
    </div>
</body>
</html>