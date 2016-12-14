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
			
            $("#saveBtn").click(function(){
            	var jsondata ;
    			var phoneNumber = $('#phoneNumber').val();
    			var message = $('#message').val();
    			if(1 == ${state}){
    				jsondata = {'id':phoneNumber,'state':2,'disableDec':message};
    			}else{
    				jsondata = {'id':phoneNumber,'state':1,'enabledDec':message}
    			}
            	$.ajax({
                    type : 'POST',
                    url : "weixinBlacklistController.do?doSave",
                    dataType : "json",
                    data : jsondata,
                    cache : false,
                    error : function() {
                    	alert("出现异常");
                    	frameElement.api.close();
                    },
                    success : function(result) {
                    	alert(result.msg);
                    	frameElement.api.close();
                      }
            });
        });
            $("#closeBtn").click(function(){
                frameElement.api.close();
            });
        });
    </script>
</head>
<body>
    <div>
        手机号：<input id='phoneNumber'  type='text' style="width:150px;" value="${phoneNumber }"/>
        <br/>
        <br/>
     	说明：<textarea rows="6" cols="50" id="message"></textarea>
    </div>
    <div class="ui_buttons" style="padding-left: 500px;">
        <input type="button" id="saveBtn" value="保存" class="ui_state_highlight">
        <input type="button" id="closeBtn" value="关闭" onclick='close();'>
    </div>
</body>
</html>