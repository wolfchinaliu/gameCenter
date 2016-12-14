<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <link rel="stylesheet" type="text/css" href="plug-in/lhgDialog/skins/default.css">
    <script type=text/javascript src="plug-in/clipboard/ZeroClipboard.js"></script>
    <script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
    <script>
        $(function(){
            var clip = new ZeroClipboard.Client();
            clip.setHandCursor( true );
            clip.addEventListener('complete', function(client, text){
                alert("复制成功");
                frameElement.api.close();
            });
            var url = "<%=basePath%>/gameController.do?startGame&gameId=${gameId}";
            clip.setText(url);
            $("#menuLink").val(url);
            clip.glue('copyBtn');
            $("#closeBtn").click(function(){
                frameElement.api.close();
            });
        });
    </script>
</head>
<body>
    <div>
        <input id='menuLink'  type='text' style="width:630px;" readonly="readonly"/>
    </div>
    <div class="ui_buttons" style="padding-left: 500px;">
        <input type="button" id="copyBtn" value="复制" class="ui_state_highlight">
        <input type="button" id="closeBtn" value="关闭" onclick='close();'>
    </div>
</body>
</html>