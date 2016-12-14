<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script src="plug-in/layer-v2.1/layer/layer.js"></script>
<div class="easyui-layout" fit="true" style="border: 0px;">
    <div region="center" style="padding:1px;border: 0px;">
        <t:datagrid name="shareList" checkbox="true" fitColumns="false" title="分享内容管理" sortName="createDate" sortOrder="desc"
                    actionUrl="shareController.do?mydatagrid" idField="id" fit="true" queryMode="group">
            <t:dgCol title="ID" field="id" hidden="false" queryMode="single" width="0"></t:dgCol>
            <t:dgCol title="标题" field="title" query="true" queryMode="single" width="120"></t:dgCol>
            <%--<t:dgCol title="页面位置"  field="pageLocation"  queryMode="single" width="120"></t:dgCol>--%>
            <t:dgCol title="图像" field="imagepath" hidden="true" image="true" imageSize="40,40" queryMode="group"
                     width="120"></t:dgCol>
            <t:dgCol title="描述" field="description" queryMode="single" width="120"></t:dgCol>
            <%--<t:dgCol title="跳转类型" field="jumpType" queryMode="single" width="120"></t:dgCol>--%>
            <%--<t:dgCol title="内部页面" field="pageUrl" queryMode="single" width="120"></t:dgCol>--%>
            <%--<t:dgCol title="地址" field="jumpUrl" queryMode="single" width="120"></t:dgCol>--%>
            <t:dgCol title="时间" field="createDate" queryMode="group" query="true" width="200" formatter="yyyy-MM-dd hh:mm:ss"
                     sortable="true"  ></t:dgCol>

            <t:dgCol title="操作" field="opt" width="60"></t:dgCol>
            <%--<t:dgDelOpt title="删除"  funname="del(id)"/>--%>
            <t:dgFunOpt funname="delaaa(id)" title="删除"></t:dgFunOpt>
            <%--<t:dgToolBar title="预览" icon="icon-search" url="pptManagerController.do?goUpdate" funname="detail"></t:dgToolBar>--%>
            <t:dgToolBar title="编辑" height="500" icon="icon-edit" url="shareController.do?goUpdate" funname="update"></t:dgToolBar>
            <t:dgToolBar title="新增" height="500" icon="icon-add" url="shareController.do?goAdd" funname="add"></t:dgToolBar>

        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //给时间控件加上样式
        $("#shareListtb").find("input[name='createDate_begin']").attr("class", "Wdate").attr("style", "height:20px;width:90px;").click(function () {
            WdatePicker({dateFmt: 'yyyy-MM-dd'});
        });
        $("#shareListtb").find("input[name='createDate_end']").attr("class", "Wdate").attr("style", "height:20px;width:90px;").click(function () {
            WdatePicker({dateFmt: 'yyyy-MM-dd'});
        });
    });
    function delaaa(id) {
        $.messager.confirm('提示信息', '是否删除此文章', function (r) {
            if (r) {
                $.ajax({
                    type: 'POST',
                    url: "shareController.do?goDelete",
                    dataType: "json",
                    data: {
                        "id": id
                    },
                    cache: false,
                    error: function (error) {
                        //alert(error);
                    },
                    success: function (data) {
                        if (data.flag == true) {
                            tip("此文章被使用，不能删除。");
                        } else {
                            $.ajax({
                                type: 'POST',
                                url: "shareController.do?doDel",
                                dataType: "json",
                                data: {
                                    "id": id
                                },
                                cache: false,
                                error: function (error) {
                                    tip("删除失败，请稍后重试。")
                                },
                                success: function (data) {
                                    tip("文章删除成功。");
                                }
                            });
                            window.location.reload();
                        }
                    }
                });
            }

        });

    }
</script>