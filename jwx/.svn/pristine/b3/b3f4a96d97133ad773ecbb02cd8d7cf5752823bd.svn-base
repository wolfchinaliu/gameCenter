<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script src="plug-in/layer-v2.1/layer/layer.js"></script>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid name="weixinGuessRiddlesList" fitColumns="false" title="猜灯谜"
                    actionUrl="weixinGuessRiddlesController.do?datagridbysql" idField="id" fit="true" queryMode="group">
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="创建人名称" field="createName" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="创建日期" field="createDate" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="活动名称" field="title" hidden="true" query='true' queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="活动描述" field="description" hidden="false" queryMode="single" width="120"></t:dgCol>

            <t:dgCol title="开始时间" field="starttime" hidden="true" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single" width="140"></t:dgCol>
            <t:dgCol title="结束时间" field="endtime" hidden="true" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single" width="140"></t:dgCol>
            <t:dgCol title="赠送总流量" field="allFlow" hidden="true" queryMode="single" width="140"></t:dgCol>
            <t:dgCol title="每道题可获取流量" field="riddleFlow" hidden="true" queryMode="single" width="140"></t:dgCol>
            <t:dgCol title="已领取流量" field="getFlow" hidden="true" replace="0_null" queryMode="single" width="140"></t:dgCol>
            <t:dgCol title="新增粉丝数" field="num" hidden="true" replace="0_null" queryMode="single" width="140"></t:dgCol>
            <t:dgCol title="状态" field="state" hidden="true" replace="进行中_1,已结束_0,尚未开始_2" queryMode="single"
                     width="140"></t:dgCol>

            <t:dgCol title="操作" field="opt" width="250"></t:dgCol>

            <t:dgFunOpt funname="addRiddles(id,state)" title="增加题目"></t:dgFunOpt>
            <t:dgFunOpt funname="showPrize(id)" title="猜中名单"></t:dgFunOpt>
            <t:dgFunOpt funname="overhd(id,state)" title="结束活动"></t:dgFunOpt>
            <%--<t:dgFunOpt funname="showRecord(id)" title="抽奖记录"></t:dgFunOpt>--%>


            <t:dgToolBar title="新增" icon="icon-add" url="weixinGuessRiddlesController.do?goAdd"
                         funname="add"></t:dgToolBar>
            <%--   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinLotteryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
            <t:dgToolBar title="延续编辑" icon="icon-edit" url="weixinGuessRiddlesController.do?goUpdate"
                         funname="myupdate"></t:dgToolBar>
            <t:dgToolBar title="活动链接" icon="icon-edit"
                         funname="popMenuLink"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>
<script src="webpage/weixin/lottery/weixinLotteryList.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //给时间控件加上样式
    });

    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'weixinLotteryController.do?upload', "weixinLotteryList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("weixinLotteryController.do?exportXls", "weixinLotteryList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("weixinLotteryController.do?exportXlsByT", "weixinLotteryList");
    }
</script>
<script type="text/javascript">

    function addRiddles(id,state) {
//        alert(id);
        if (state==0){
            alertTip('该活动已结束！');
            return;
        }
        $.ajax({
            url: "weixinGuessRiddlesController.do?checkRiddle&id=" + id,
            type: "GET",
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    tip('活动已添加题目！');
                    reloadTable();
                    return;
                }
//                tip('活动结束是失败！');
                addGroupMember('增加题目', 'weixinGuessRiddlesController.do?riddleListPage&id=' + id, 'merchantAndGroupList', 800, 800);
            }
        });

    }

    function overhd(id, state) {
//   var rowsData = $('#' + id).datagrid('getSelections');

        if (state==0){
            alertTip('该活动已结束！');
            return;
        }

        layer.confirm('确定要结束当前活动吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function (index) {
                    $.ajax({
                        url: "weixinGuessRiddlesController.do?stopHd&id=" + id,
                        type: "GET",
                        dataType: 'json',
                        success: function (data) {
                            if (data.success) {
                                tip('活动结束成功！');
                                reloadTable();
                                return;
                            }
                            tip('活动结束是失败！');
                        }
                    });

                    layer.close(index);
                }
                ,
                function () {
                })
    }

    function add(title, addurl, gname, width, height) {
//		 "personalRedpacketSetController.do?goAdd"
        gridname = gname;

        var getData = $('#' + gridname).datagrid('getData');

        var url = "weixinGuessRiddlesController.do?queryHdByState";

        $.ajax({
            url: url,
            type: "GET",
            dataType: 'json',
            success: function (data) {
                if (!data.success) {
//					tip('目前有正在进行的活动，请先停止后再开始新的活动！');
                    alertTip('目前有正在进行或尚未开始的活动，请先停止后再创建新的活动！');
                    return;
                }
                createwindow(title, addurl, width, height);
            }
        });
    }

    function myupdate(title, url, id, width, height) {
        gridname = id;
        var rowsData = $('#' + id).datagrid('getSelections');
        if (!rowsData || rowsData.length == 0) {
            tip('请选择编辑项目');
            return;
        }
        if (rowsData.length > 1) {
            tip('请选择一条记录再编辑');
            return;
        }
        if (rowsData[0].state == 0) {
            alertTip('该活动已结束，无法编辑！');
            return;
        }

        url += '&id=' + rowsData[0].id;
        createwindow(title, url, width, height);
    }

    function updateLottery(id) {

    }

    function showPrize(id) {
        var addurl = "weixinGuessRiddlesController.do?goPrizeRecord&hdId=" + id;
        createdetailwindow("中奖记录列表", addurl, 750, 450);
    }

//    function showRecord(id) {
//        var addurl = "weixinDrawDetailController.do?hdRecord&hdId=" + id;
//        createdetailwindow("抽奖记录列表", addurl, 650, 450);
//    }


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
        if (rowsData[0].state == 0) {
            alertTip('该活动已结束，无法获取链接！');
            return;
        }
        $.dialog({
            content: "url:weixinGuessRiddlesController.do?weixinRiddleAddress&hdid="+rowsData[0].id,
            drag :false,
            lock : true,
            title:'活动链接',
            opacity : 0.3,
            width:650,
            height:80,drag:false,min:false,max:false
        }).zindex();
    }




</script>