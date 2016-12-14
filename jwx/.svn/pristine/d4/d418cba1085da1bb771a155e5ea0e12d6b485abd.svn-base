<%--
  Created by IntelliJ IDEA.
  User: xuxiaoguai
  Date: 2016/1/20
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid name="weixinShakeList"  fitColumns="false" sortName="starttime" sortOrder="desc" title="摇一摇" actionUrl="weixinShakeController.do?datagrid&lotteryType=4" idField="id" fit="true" queryMode="group">
            <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="活动名称"  field="title"  hidden="true" query='true'   width="120"></t:dgCol>
            <t:dgCol title="开始时间"  field="starttime"  hidden="true"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="single"  width="140"></t:dgCol>
            <t:dgCol title="结束时间"  field="endtime"  hidden="true"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="single"  width="140"></t:dgCol>
            <t:dgCol title="状态" field="state" hidden="true" replace="进行中_1,已结束_0,尚未开始_2" queryMode="single"
                     width="140"></t:dgCol>

            <t:dgCol title="已参与次数"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="已领取流量"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>

            <t:dgCol title="操作" field="opt" width="250"></t:dgCol>
            <t:dgFunOpt funname="showPrize(id)" title="中奖名单" ></t:dgFunOpt>
            <t:dgDelOpt url="weixinShakeController.do?doDel&id={id}" title="删除"></t:dgDelOpt>
            <t:dgToolBar title="录入" icon="icon-add" url="weixinShakeController.do?goAdd" funname="add"></t:dgToolBar>
            <t:dgToolBar title="编辑" icon="icon-edit" url="weixinShakeController.do?goUpdate" funname="update"></t:dgToolBar>
            <t:dgToolBar title="查看" icon="icon-search" url="weixinShakeController.do?goUpdate" funname="detail"></t:dgToolBar>

            <t:dgToolBar title="活动链接" icon="icon-edit"
                         funname="popMenuLink"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>
<script src="webpage/weixin/lottery/weixinLottery.js"></script>
<%--<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>--%>
<%--<script type="text/javascript" src="easyui/jquery/jquery-2.1.1.min.js"></script>--%>
<%--<script type="text/javascript" src="easyui/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>--%>
<script type="text/javascript" src="easyui/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
    //编辑
    function edit(id) {
        var url = "weixinShakeController.do?goUpdate&id=" + id;
//        createdetailwindow('编辑', 'weixinShakeController.do?goUpdate&id=' + id, 'weixinShakeList', '100%', '100%');
        add('编辑',url, 'weixinShakeList', 650, 350);
    }
    //查看获奖记录
    function showPrize(id) {
        var url = "weixinWinningrecordController.do?shakePrize&hdId=" + id;
//        createdetailwindow('获奖记录', 'weixinShakeController.do?showPrize&id=' + id, 'weixinShakeList', '100%', '100%');
        add('获奖记录', url, 'weixinShakeList',650, 350);
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
        /*if (rowsData[0].state == 0) {
            alertTip('该活动已结束，无法获取链接！');
            return;
        }*/
        $.dialog({
            content: "url:weixinShakeController.do?weixinShakeAddress&hdid="+rowsData[0].id,
            drag :false,
            lock : true,
            title:'活动链接',
            opacity : 0.3,
            width:650,
            height:80,drag:false,min:false,max:false
        }).zindex();
    }

</script>
