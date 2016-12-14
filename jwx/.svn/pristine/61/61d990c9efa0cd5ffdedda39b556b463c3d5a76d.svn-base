<%--
  Created by IntelliJ IDEA.
  User: xiaochun
  Date: 2015/12/23
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" type="text/css" href="easyui/jquery-easyui-theme/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="easyui/jquery-easyui-theme/icon.css">
  <script type="text/javascript" src="easyui/jquery/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="easyui/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="easyui/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
  <%--<link rel="stylesheet" type="text/css" href="easyui/drp.css">--%>
  <script type="text/javascript">
    $(function () {
      // 下拉框选择控件，下拉框的内容是动态查询数据库信息
      $('#jumptype').combobox({
        url: 'weixinUrlController.do?weixinUrlFirst',
        editable: false, //不可编辑状态
        cache: false,
//        panelHeight: '150',
        valueField: 'url',
        textField: 'name',
        resizable:true,
        onSelect: function () {
          var jumptypevalue = $('#jumptype').combobox('getValue');
          if(jumptypevalue=="default"){
            document.getElementById("innerurl").style.display="none";
            document.getElementById("outurl").style.display="none";
            document.getElementById("defaulteditor").style.display="block";
            return
          }
          if(jumptypevalue=="outurl"){
            document.getElementById("innerurl").style.display="none";
            document.getElementById("outurl").style.display="block";
            document.getElementById("defaulteditor").style.display="none";
            return
          }
          if(jumptypevalue=="flowpage"){
            document.getElementById("innerurl").style.display="block";
            document.getElementById("outurl").style.display="none";
            document.getElementById("defaulteditor").style.display="none";
            $("#pagetype").combobox("setValue", '');
            $.ajax({
              type: "POST",
              url: 'weixinUrlController.do?weixinUrlFlow',
              cache: false,
              async: true,
              dataType: "json",
              success: function (data) {
                $("#pagetype").combobox("loadData", data);
                return;
              }
            });
            return;
          }
          if(jumptypevalue=="lottery"){
            document.getElementById("innerurl").style.display="block";
            document.getElementById("outurl").style.display="none";
            document.getElementById("defaulteditor").style.display="none";
            $("#pagetype").combobox("setValue", '');
            $.ajax({
              type: "POST",
              url: 'weixinUrlController.do?weixinUrlLottery',
              cache: false,
              async: true,
              dataType: "json",
              success: function (data) {
                $("#pagetype").combobox("loadData", data);
                return;
              }
            });
            return
          }
        }
      });
      //将上述查询的结果绑定在下面的combobox中
      $('#pagetype').combobox({
        editable: false, //不可编辑状态
        cache: false,
        valueField: 'url',
        resizable:true,
        textField: 'name',
        itemIndex:0
      });
    });
  </script>
</head>
<body>
<div>
  <span>跳转类型：</span><input class="easyui-combobox" id="jumptype">
</div>
<div id="innerurl" style="display:none;">
  <span>页面类型：</span><input class="easyui-combobox" id="pagetype">
</div>
<div id="outurl" style="display:none;">
  <span>页面地址：</span><input>
</div>
<div id="defaulteditor" style="display:none;">
  假如这是一个百度编辑器
</div>
</body>
</html>
