<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>客服表</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <style type="text/css">
        .field {
            padding-top: 10px;
            padding-bottom: 5px;
        }

        .field label {
            float: left;
            width: 100px;
            margin-top: 6px;
        }

        .field input {
            width: 250px;
            height: 30px;
        }

        .reg_form {
            padding-left: 300px;
            padding-top: 50px;
            border: 1px;
            border-color: green;
            padding-bottom: 10px;
        }
    </style>
</head>
<body>
<form accept-charset="UTF-8" class="register-form" id="new_user">
    <input hidden="true" id="id" name="id" value="${weixinCustomer.id}">

    <div class="field">
        <label for="keyWord">开始关键字:</label>
        <input id="keyWord" name="keyWord" value="${weixinCustomer.keyWord}" style="width: 150px;">
    </div>
    &nbsp;
    <div class="field">
        <label for="content">开始内容：</label>
        <input id="content" name="content" value="${weixinCustomer.content}" style="width:150px; ">
    </div>
    <input type="button" value="保存" onclick="save()">
</form>
<script>
    function save() {
        var id = document.getElementById('id').value;
        var keyWord = document.getElementById('keyWord').value;
        var content = document.getElementById('content').value;
        $.ajax({
            url: "weixinCustomerLxcRespController.do?doAddOrUpdate",
            type: 'POST',
            data: {id: id, keyWord: keyWord, content: content},
            success: function (data) {
                alert("客服关键字保存成功！")
//        if(data=="ok"){
//          alert("客服关键字保存成功！")
//        }else{
//          alert("客服关键字保存失败！")
//        }
            }
        });
    }

</script>
</body>
</html>
