<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table"
             action="merchantAndGroupController.do?doAdd&id=${id}"
             tiptype="1">
    <%--<input id="id" name="id" type="hidden" value="${weixinAcctPage.id }">--%>
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    组名称:
                </label>
            </td>
            <td class="value">
                <input id="groupName" name="groupName" datatype="s2-18" type="text" style="width: 150px" class="inputxt"

                        >
                <span class="Validform_checktip">组名范围在2~18位字符</span>
                <label class="Validform_label" style="display: none;">组名称</label>
            </td>
        </tr>
    </table>
</t:formvalid>
<script src="webpage/weixin/tenant/weixinAcctList.js"></script>
<script type="text/javascript">
    $("#groupName").blur(function () {

        //检测用户名是否已注册
        $.ajax({
            url: "merchantAndGroupController.do?checkgroupName",
            method: "POST",
            dataType: "JSON",
            data: {
                "groupName": $("#groupName").val()
            },
            success: function (data) {
                if (data.msg != "操作成功") {
                    alert(data.msg);
                }


            }
        });
    });
</script>
