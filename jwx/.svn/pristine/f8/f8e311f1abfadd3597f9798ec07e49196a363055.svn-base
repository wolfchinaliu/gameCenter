<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户信息</title>
    <t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" refresh="false" dialog="true" action="weixinAcctController.do?savenewpwd"
             usePlugin="password" layout="table">
    <input id="id" name="id" type="hidden" value="${user.accountid}">
    <input id="username" name="username" type="hidden" value="${user.userName}">
    <input id="pwd" name="pwd" type="hidden" value="${user.password}">
    <table style="width: 550px" cellpadding="0" cellspacing="1" class="formtable">
        <tbody>
        <!-- <tr>
            <td align="right" width="10%"><span class="filedzt">原密码:</span></td>
            <td class="value"><input id="password" type="password" value="" onblur="checkpwd()" name="password"
                                     datatype="*" class="inputxt"
                                     errormsg="请输入原密码"/> <span class="Validform_checktip"> 请输入原密码 </span></td>
        </tr> -->
        <tr>
            <td align="right"><span class="filedzt">输入新密码:</span></td>
            <td class="value"><input type="password" value="" name="newpassword" class="inputxt"
                                     plugin="passwordStrength" datatype="*6-18" errormsg="密码至少6个字符,最多18个字符！"/> <span
                    class="Validform_checktip"> 密码至少6个字符,最多18个字符！ </span> <span class="passwordStrength"
                                                                                style="display: none;"> <b>密码强度：</b> <span>弱</span><span>中</span><span
                    class="last">强</span> </span></td>
        </tr>
        <tr>
            <td align="right"><span class="filedzt">确认密码:</span></td>
            <td class="value"><input id="newpassword" type="password" recheck="newpassword" datatype="*6-18"
                                     errormsg="两次输入的密码不一致！"> <span class="Validform_checktip"></span></td>
        </tr>
        </tbody>
    </table>
</t:formvalid>
</body>
<script>
    function checkpwd() {
        if ($("#password").val() == "") {
            alert('请填写原始密码');
            $("#password").focus();
        } else {
            $.ajax({
                url: "weixinAcctController.do?checkPWD",
                method: "POST",
                dataType: "JSON",
                data: {
                    "password": $("#password").val(),
                    "pwd": $("#pwd").val(),
                    "username": $("#username").val()

                },
                success: function (data) {
                    if (data.msg == "原密码输入错误，请重新输入") {
                        alert(data.msg);

                    }
                }
            })
        }
        <%--if ($("#password").val() == "") {--%>
        <%--alert('请填写原始密码');--%>
        <%--$("#password").focus();--%>
        <%--} else {--%>
        <%--//检测用户名是否已注册--%>
        <%--$.ajax({--%>
        <%--url: "weixinAcctController.do?checkPWD",--%>
        <%--method: "POST",--%>
        <%--dataType: "JSON",--%>
        <%--data: {--%>
        <%--"password": $("#password1").val(),--%>
        <%--"accountId":${user.accountid}--%>
        <%--},--%>
        <%--success: function (data) {--%>
        <%--if (data.msg == "此用户已经存在，请重新输入！") {--%>
        <%--alert(data.msg);--%>
        <%--return false;--%>
        <%--} else {--%>
        <%--return true;--%>
        <%--}--%>


        <%--}--%>
        <%--});--%>
        <%--}--%>

    }
</script>