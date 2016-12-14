<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html >
<html>
<head>
<title>角色集合</title>
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="plug-in/tools/dataformat.js"></script>
<link id="easyuiTheme" rel="stylesheet"
	href="plug-in/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="plug-in/easyui/themes/icon.css"
	type="text/css"></link>
<link rel="stylesheet" type="text/css"
	href="plug-in/accordion/css/accordion.css">
<script type="text/javascript"
	src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>
<script type="text/javascript"
	src="plug-in/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="plug-in/tools/syUtil.js"></script>
<script type="text/javascript"
	src="plug-in/easyui/extends/datagrid-scrollview.js"></script>
<link rel="stylesheet" href="plug-in/tools/css/common.css"
	type="text/css"></link>
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="plug-in/tools/curdtools.js"></script>
<script type="text/javascript" src="plug-in/tools/easyuiextend.js"></script>
<script type="text/javascript"
	src="plug-in/jquery-plugs/hftable/jquery-hftable.js"></script>
</head>
<body style="overflow-y: hidden" scroll="no">
	<script type="text/javascript">
		$(function() {
			$('#categoryList')
					.datagrid(
							{
								idField : 'id',
								title : '选择分类',
								url : 'weixinMemberController.do?datagrid&field=id,openId,nickName,headImgUrl',
								fit : true,
								loadMsg : '数据加载中...',
								pageSize : 10,
								pagination : true,
								pageList : [ 10, 20, 30 ],
								sortOrder : 'asc',
								rownumbers : true,
								singleSelect : true,
								fitColumns : true,
								showFooter : true,
								frozenColumns : [ [] ],
								columns : [ [
										{
											field : 'id',
											title : '主键',
											width : 120,
											hidden : true,
											sortable : true
										},
										{
											field : 'openId',
											title : 'openId',
											query:true,
											width : 120,
											sortable : true
										},
										{
											field : 'nickName',
											title : '粉丝昵称',
											query:true,
											width : 120,
											sortable : true
										},
										{
											field : 'headImgUrl',
											title : '头像',
											sortable : true,
											formatter : function(value, rec,
													index) {
												return '<img border="0" src="'+value+'"/>';
											},
											formatter : function(value, rec,
													index) {
												return '<img width="40" height="50" border="0" src="'+value+'"/>';
											}
										} ] ],
								onLoadSuccess : function(data) {
									$("#categoryList").datagrid(
											"clearSelections");
								},
								onClickRow : function(rowIndex, rowData) {
									rowid = rowData.id;
									gridname = 'categoryList';
								}
							});
			$('#categoryList').datagrid('getPager').pagination({
				beforePageText : '',
				afterPageText : '/{pages}',
				displayMsg : '{from}-{to}共{total}条',
				showPageList : true,
				showRefresh : false
			});
			$('#categoryList').datagrid('getPager').pagination({
				onBeforeRefresh : function(pageNumber, pageSize) {
					$(this).pagination('loading');
					$(this).pagination('loaded');
				}
			});
		});
		function reloadTable() {
			try {
				$('#' + gridname).datagrid('reload');
				$('#' + gridname).treegrid('reload');
			} catch (ex) {
			}
		}
		function reloadcategoryList() {
			$('#categoryList').datagrid('reload');
		}
		function getcategoryListSelected(field) {
			return getSelected(field);
		}
		function getSelected(field) {
			var row = $('#' + gridname).datagrid('getSelected');
			if (row != null) {
				value = row[field];
			} else {
				value = '';
			}
			return value;
		}
		function getcategoryListSelections(field) {
			var ids = [];
			var rows = $('#categoryList').datagrid('getSelections');
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i][field]);
			}
			ids.join(',');
			return ids
		};
		function getSelectRows() {
			return $('#categoryList').datagrid('getChecked');
		}
		function categoryListsearch() {
			var queryParams = $('#categoryList').datagrid('options').queryParams;
			$('#categoryListtb').find('*').each(function() {
				queryParams[$(this).attr('name')] = $(this).val();
			});
			$('#categoryList')
					.datagrid(
							{
								url : 'weixinShopCategoryController.do?datagrid&field=id,name,picture,',
								pageNumber : 1
							});
		}
		function dosearch(params) {
			var jsonparams = $.parseJSON(params);
			$('#categoryList')
					.datagrid(
							{
								url : 'weixinShopCategoryController.do?datagrid&field=id,name,picture,',
								queryParams : jsonparams
							});
		}
		function categoryListsearchbox(value, name) {
			var queryParams = $('#categoryList').datagrid('options').queryParams;
			queryParams[name] = value;
			queryParams.searchfield = name;
			$('#categoryList').datagrid('reload');
		}
		$('#categoryListsearchbox').searchbox({
			searcher : function(value, name) {
				categoryListsearchbox(value, name);
			},
			menu : '#categoryListmm',
			prompt : '请输入查询关键字'
		});
		function EnterPress(e) {
			var e = e || window.event;
			if (e.keyCode == 13) {
				categoryListsearch();
			}
		}
		function searchReset(name) {
			$("#" + name + "tb").find(":input").val("");
			categoryListsearch();
		}
	</script>
	<table width="100%" id="categoryList" toolbar="#categoryListtb"></table>
	<div id="categoryListtb" style="padding: 3px; height: auto">
		<div style="height: 0px;">
			<span style="float: left;"></span>
		</div>
</body>
</html>
