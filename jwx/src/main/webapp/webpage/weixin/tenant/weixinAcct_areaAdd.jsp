<%--
  Created by IntelliJ IDEA.
  User: aa
  Date: 2015/11/26
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/context/mytags.jsp" %>
<html>
<head>
    <title>商户信息管理</title>
    <%--<t:base type="jquery,easyui,tools,DatePicker"></t:base>--%>
    <link rel="stylesheet" type="text/css" href="easyui/jquery-easyui-theme/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/jquery-easyui-theme/icon.css">
    <script type="text/javascript" src="easyui/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="easyui/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="easyui/drp.css">

    <script type="text/javascript">
        $(function () {
            // 下拉框选择控件，下拉框的内容是动态查询数据库信息
            $('#province').combobox({
                url: 'weixinAcctController.do?addProvince',
                editable: false, //不可编辑状态
                cache: false,
                panelHeight: '150',
                valueField: 'provinceID',
                textField: 'provinceName',
                onSelect: function () {
                    var id = $('#province').combobox('getValue');
                    $("#city").combobox("setValue", '');//清空课程
                    $.ajax({
                        type: "POST",
                        url: 'weixinAcctController.do?addCity',
                        data:{
                            id:id
                        },
                        cache: false,
                        async: true,
                        dataType: "json",
                        success: function (data) {
                            $("#city").combobox("loadData", data);
                        }
                    });

                }
            });
            //将上述查询的结果绑定在下面的combobox中
            $('#city').combobox({
                //url:'itemManage!categorytbl',
                editable: false, //不可编辑状态
                cache: false,
                panelHeight: '150',//自动高度适合
                valueField: 'cityID',
                textField: 'cityName'
            });

        });
    </script>
</head>
<%--<div id="dgquyu" closed="true" class="easyui-dialog" style="width: 300px;height:auto;padding: 10px 20px;left: 300px;top:500px;">--%>
    <span>省份：</span>
    <input class="easyui-combobox" style="width:50%;" id="province">
    <span>市区：</span>
    <input class="easyui-combobox" style="width:50%;" id="city"><br/>
<%--</div>--%>
</html>
