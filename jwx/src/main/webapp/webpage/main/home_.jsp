<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<% 
String httpurl = request.getScheme()+"://"+request.getServerName();
%>
<style type="text/css">
    #demo.panel-container {
        padding: 0;
    }

    .button-mini {
        width: 18px;
        height: 18px;
        cursor: pointer;
        border-width: 1px;
        border-color: #FFFFFF;
        border-style: solid;
        display: inline-block;
    }
    .button-mini:hover {
        border-color: red;
    }
    .button-mini-icon {
        line-height: 16px;
        padding-left: 16px;
        display: inline-block;
    }
</style>

<div id="ll" class="easyui-layout" data-options="fit: true">
    <div data-options="region: 'north', border: false" style="height: 33px; ">
        <div class="easyui-toolbar">
        <div style="float: left;">
        <a id="btn3" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-add'">添加人员</a>
            
            <a id="btn4" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-edit'">编辑信息</a>
            
            <a id="btn5" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-remove'">批量删除</a>
            
         </div>   
             
            <div style="float: right;text-align: right;">
           <input class="easyui-validatebox" data-options="prompt: '拟定企业名称'" style="width: 100px;" />
           
            <input class="easyui-validatebox" data-options="prompt: '企业类型'" style="width: 100px;" />
            
            <input class="easyui-validatebox" data-options="prompt: '法人'" style="width: 100px;" />
           <select style="color:#999;width:100px;height:22px;">
           	<option value="脸谱"  style="color: #999;">请选择</option>
			<option style="color: black;">是</option>
			<option style="color: black;">否</option>
           </select>
            <input class="easyui-datebox" data-options="prompt: '核查有效期', width: 100" />
            
            
            <a id="btn1" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-search'">查询</a>
            <a id="btn2" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-reload'">重置</a>
           </div>
            
        </div>
    </div>
    <div data-options="region: 'center', border: false">
        <table id="t1"></table>
    </div>

    <script type="text/javascript">
        $(function () {
            var t = $("#t1").datagrid({
                fit: true,
                border: false,
                width: 1120,
                height: 400,
                method: "get",
                url: "datagrid-data.json",
                singleSelect: true,
                idField: 'ID',
                remoteSort: false,
                rownumbers: true,
                pagination: true,
                frozenColumns: [[
                    { field: 'ck', checkbox: true },
                    { field: 'ID', title: 'ID', width: 80, sortable: true }
                ]],
                columns: [[
                    { field: 'Code', title: '编号(Code)', width: 120, sortable: true },
                    { field: 'Name', title: '名称(Name)', width: 140, sortable: true },
                    { field: 'Age', title: '年龄(Age)', width: 120, sortable: true },
                    { field: 'Height', title: '身高(Height)', width: 140, sortable: true },
                    { field: 'Weight', title: '体重(Weight)', width: 140, sortable: true },
                    { field: 'CreateDate', title: '创建日期(CreateDate)', width: 180, sortable: true },
                    {
                        field: 'operator', title: '测试(不存在的字段)', width: 140, formatter: function (val, row, index) {
                            var html = '<div title="取消" class="button-mini" onclick="javascript: return window.editEmployee(' + row.ID + ');"><span class="button-mini-icon icon-edit">&nbsp;</span></div>';
                            return html;
                        }
                    }
                ]],
                rowContextMenu: [
                    {
                        text: "编辑", iconCls: "icon-edit", handler: function (e, i, row) {
                            window.editEmployee(row.ID);
                        }
                    },
                    {
                        text: "平均身高", iconCls: "", handler: function (e, index, row) {
                            var val = t.datagrid("getRows").avg(function (r) { return r.Height; }).round(2);
                            $.easyui.messager.show("平均身高为：" + val);
                        }
                    },
                    {
                        text: "体重总和", iconCls: "icon-standard-sum", handler: function (e, index, row) {
                            var val = t.datagrid("getRows").sum(function (r) { return r.Weight; }).round(2);
                            $.easyui.messager.show("体重总和为：" + val);
                        }
                    },
                ]
            });

            window.editEmployee = function (id) {
                var row = t.datagrid("getRowData", id);
                $.easyui.showOption(row);
            };

            $("#btn1").click(function () {
                $.easyui.messager.show("...");
            });

            $.util.exec(function () {
                $("#ll").currentTabs().tabs("select", 0);
            });
        });
    </script>

</div>