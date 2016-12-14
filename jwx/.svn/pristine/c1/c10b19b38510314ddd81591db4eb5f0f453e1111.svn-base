<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<%
    String para1 = (String) request.getAttribute("id");
    System.out.println(para1);
%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script src="plug-in/layer-v2.1/layer/layer.js"></script>
<div class="easyui-layout" fit="true">

    <%--String id1=<%=para1%>;--%>

    <div region="center" style="padding:1px;">
        <t:datagrid name="maccdatagridJoinedList" checkbox="true" fitColumns="false" title="商户管理表"
                    actionUrl="merchantAndGroupController.do?maccdatagridJoined&id=${id}" idField="id"
                    fit="true" queryMode="group">
            <%--String id1=<%=para1%>;--%>
            <t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="创建日期" field="createDate" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="修改日期" field="endDate" hidden="false" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="商户名称" field="acctForName" hidden="true" query="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="所属商户" field="belogAcct" hidden="true" query="true" queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="商业类型" dictionary="busiType" field="businessType" query="true" hidden="true"
                     queryMode="single"
                     width="120"></t:dgCol>
            <t:dgCol title="省份" field="province" hidden="true" queryMode="single" query="true" width="120"></t:dgCol>
            <t:dgCol title="市名" field="city" hidden="true" queryMode="single" query="true" width="120"></t:dgCol>
            <%--<t:dgCol title="市名" dictionary="city_jx" field="city" hidden="true" query="false" queryMode="single"--%>
            <%--width="120"></t:dgCol>--%>

            <%--<t:dgToolBar title="删除" icon="icon-remove" url="merchantAndGroupController.do?doBatchDelete&id=${id}"--%>
            <%--funname="deleteALLGroupMember"></t:dgToolBar> --%>
            <t:dgToolBar title="删除" icon="icon-remove" url="merchantAndGroupController.do?doBatchDelete&id=${id}"
                         funname="deleteALL"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>
<%--<div class="ui_buttons" style="padding-left: 500px;">--%>
<%--<input type="button" id="copyBtn" value="复制" class="ui_state_highlight">--%>
<%--<input type="button" id="closeBtn" value="关闭" onclick='close();'>--%>
<%--</div>--%>
<%--actionUrl="merchantAndGroupController.do?maccdatagridJoined&id=<%=para1%>" idField="id"--%>
<script type="text/javascript">
    function deleteALL(title, url, gname) {

        gridname = gname;
        var ids = [];
        var rows = $("#" + gname).datagrid('getSelections');
        if (rows.length > 0) {
            layer.confirm('确定删除该数据吗？', {
                        btn: ['确定', '取消'] //按钮
                    }, function (index) {
                        //$.dialog.confirm('你确定永久删除该数据吗?', function (r) {
                        //    if (r) {
//                    if (rows.length > 0) {
                        for (var i = 0; i < rows.length; i++) {
                            ids.push(rows[i].id);
                        }
                        $.ajax({
                            url: url,
                            type: 'post',
                            data: {
                                ids: ids.join(',')
                            },
                            cache: false,
                            success: function (data) {
                                var d = $.parseJSON(data);
                                if (d.success) {
                                    var msg = d.msg;
                                    tip(msg);
                                    reloadTable();
                                    $("#" + gname).datagrid('unselectAll');
                                    ids = '';
                                }
                            }
                        });
                        
                        layer.close(index);
//                    } else {
//                        tip("请选择需要删除的数据");
//                    }
                    }
                    ,
                    function () {
                    }
            )
            ;
        }else{
            layer.alert('请选择至少一条数据');
        }
    }


</script>