<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script src="plug-in/layer-v2.1/layer/layer.js"></script>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid name="redpacketSetList" fitColumns="false" title="个人红包设置"
                    actionUrl="personalRedpacketSetController.do?datagridbysql" idField="id" fit="true"
                    queryMode="group">
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="活动主题" field="title" hidden="true" query='true' queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="补贴流量" field="subsidyValue" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="已发送红包" field="sendnum" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="已领取红包" field="getsum" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="新增粉丝数" field="membersum" hidden="true" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="已领取流量" field="getFlow" hidden="true" queryMode="single" width="120"></t:dgCol>
            <%--<t:dgCol title="状态"  field="state"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
            <%--<t:dgCol title="补贴流量"  field="subsidyValue"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
            <%--<t:dgCol title="补贴流量"  field="subsidyValue"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>


            <%--<t:dgCol title="流量类型"  field="flowtype"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
            <%--<t:dgCol title="红包大小"  field="redpacketValue"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>


            <%--<t:dgCol title="开始时间"  field="starttime"  hidden="true"  queryMode="single"  width="140"></t:dgCol>--%>
            <%--<t:dgCol title="结束时间"  field="endtime"  hidden="true"  queryMode="single"  width="140"></t:dgCol>--%>
            <t:dgCol title="状态" field="state" hidden="true" replace="进行中_1,已结束_0,尚未开始_2" queryMode="single"
                     width="140"></t:dgCol>


            <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
            <%--<t:dgFunOpt funname="showPrize(id)" title="中奖记录" ></t:dgFunOpt>--%>
            <%--<t:dgFunOpt funname="showRecord(id)" title="抽奖记录"></t:dgFunOpt>--%>
            <%--<t:dgFunOpt funname="updaLotettery(id)"  title="编辑"></t:dgFunOpt>--%>
            <%--<t:dgFunOpt funname="edithd(id)" title="延续编辑"></t:dgFunOpt>--%>
            <t:dgFunOpt funname="overhd(id,state)" title="结束活动"></t:dgFunOpt>

            <t:dgToolBar title="添加" icon="icon-add" url="personalRedpacketSetController.do?goAdd"
                         funname="myadd"></t:dgToolBar>
            <t:dgToolBar title="延续编辑" icon="icon-edit" url="personalRedpacketSetController.do?goUpdate"
                         funname="myupdate"></t:dgToolBar>
            <t:dgToolBar title="活动链接" icon="icon-edit"
                         funname="popMenuLink"></t:dgToolBar>
            <%--   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinLotteryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
            <%--<t:dgToolBar title="查看" icon="icon-search" url="weixinLotteryController.do?goUpdate" funname="detail"></t:dgToolBar>--%>
            <%--<t:dgToolBar title="活动链接" icon="icon-edit"--%>
            <%--funname="popMenuLink"	></t:dgToolBar>--%>
        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //给时间控件加上样式
    });
</script>
<script type="text/javascript">
    function myadd(title, addurl, gname, width, height) {
//		 "personalRedpacketSetController.do?goAdd"
        gridname = gname;

        var getData = $('#' + gridname).datagrid('getData');

        var url = "personalRedpacketSetController.do?queryHdByState";

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

    function overhd(id, state) {
//   var rowsData = $('#' + id).datagrid('getSelections');
        if (state == 0) {
            alertTip('该活动已结束！');
            return;
        }

        layer.confirm('确定要结束当前活动吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function (index) {
                    $.ajax({
                        url: "personalRedpacketSetController.do?stopHd&id=" + id,
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
            content: "url:personalRedpacketSetController.do?weixinRedPacketAddress&hdid="+rowsData[0].id,
            drag :false,
            lock : true,
            title:'活动链接',
            opacity : 0.3,
            width:650,
            height:80,drag:false,min:false,max:false
        }).zindex();
    }
</script>