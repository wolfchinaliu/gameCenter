<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script src="plug-in/layer-v2.1/layer/layer.js"></script>

<div class="easyui-layout" fit="true" style="border: 0px;height: 400px;">
    <div region="center" style="padding:1px;border: 0px;">

        <t:datagrid  name="questionList" checkbox="true" fitColumns="false" title="试题列表"
                    actionUrl="weixinActivityController.do?questionDatagrid&activityId=${hdId}" idField="id" fit="true"
                    queryMode="group" sortName="serial">
            <input type="hidden" id="activityId" name="activityId" value="${hdId}">
            <t:dgCol title="ID" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="序号" field="serial" hidden="true" query="false" queryMode="single"
                     width="80"></t:dgCol>
            <t:dgCol title="题目" field="questionText" hidden="true" query="false" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="答案" field="answer" hidden="true" query="false" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
            <%--<t:dgDelOpt title="删除"  funname="del(id)"/>--%>

            <t:dgFunOpt funname="update(id)" title="编辑"></t:dgFunOpt>
            <t:dgFunOpt funname="del(id)" title="删除"></t:dgFunOpt>
            <%--<t:dgConfOpt title="删除" url="weixinGuessRiddlesController.do?doDel&id={id}" message="是否确定删除吗？" />--%>
            <%--<t:dgFunOpt funname="del(id)" title="删除"></t:dgFunOpt>--%>
            <%--<t:dgToolBar title="预览" icon="icon-search" url="pptManagerController.do?goUpdate" funname="detail"></t:dgToolBar>--%>
            <%--<t:dgToolBar title="编辑" icon="icon-edit"  funname="update(id)" ></t:dgToolBar>--%>
            <%--<t:dgToolBar title="新增" icon="icon-add" url="weixinGuessRiddlesController.do?goAddRiddle&id=${id}" funname="add" ></t:dgToolBar>--%>
            <t:dgToolBar title="新增" icon="icon-add"  funname="add" ></t:dgToolBar>



            <%--<t:dgToolBar title="增加题目" icon="icon-add" url="weixinLotteryController.do?goAdd&lotteryType=${lotteryType }"--%>
                         <%--funname="add"></t:dgToolBar>--%>
            <%-- <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
            <t:dgToolBar title="导入" icon="icon-putout" funname="ImportXls"></t:dgToolBar> --%>

        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("weixinGuessRiddlesController.do?exportXls", "riddleList");
    }

    //导入
    function ImportXls() {
        $("table.ui_border").hide();
        openuploadwin('Excel导入', 'weixinGuessRiddlesController.do?upload&activityId=${hdId}', "riddleList");
    }

    //添加题目
    function add(){
        $("table.ui_border").hide();
        <%--createwindow('添加题目',  'weixinGuessRiddlesController.do?goAddRiddle&id=${id}', 600, 400);--%>
        <%--addGroupMember('添加题目', 'weixinGuessRiddlesController.do?goAddRiddle&id=${id}', 600, 400);--%>
        openupload('添加题目', 'weixinActivityController.do?goQuestionEdit&activityId=${hdId}');
    }
    function openupload(title, url, name, width, height) {
        gridname = name;
        $.dialog({
            content: 'url:' + url,
            cache: false,
            zIndex: 2000,
            height: 350,
            width:700,
            button: [
                {
                    name: '确定',
                    callback: function () {
                        iframe = this.iframe.contentWindow;
                        $('#btn_sub', iframe.document).click();
//                        reloadTable();
						isopen = false;
                        $('#questionList').datagrid('reload');
                    },
                    focus: true
                },
                {
                    name: '取消',
                    callback: function () {
                        iframe = this.iframe.contentWindow;
//                        iframe.cancel();
                        iframe.close();
                    }
                }
            ]
        }).zindex();
    }

    function submit(){
        alert(1);
    }

    function update(id){
        openupload('编辑题目', 'weixinActivityController.do?goQuestionEdit&id='+id);
    }


    //删除题目
    function del(id){
        layer.confirm('确定要删除此题目吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function (index) {
                    $.ajax({
                        url: "weixinActivityController.do?doQuestionDel&id=" + id,
                        type: "GET",
                        dataType: 'json',
                        success: function (data) {
                            if (data.success) {
                                tip('题目删除成功！');
                                reloadTable();
                                return;
                            }
                            tip('题目删除失败！');
                        }
                    });

                    layer.close(index);
                }
                ,
                function () {
                })
    }
</script>

