<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
    <t:base type="jquery,easyui,tools"></t:base>
    <style type="text/css">
    .anniu{width:50px;height:40px;padding:6px 11px;font-size:14px;margin-right:10px;border-radius:5px;background:#1E97E5;color:white;}</style>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" refresh="false" dialog="true" action="userController.do?optSecretKey" usePlugin="password"
             layout="table" tiptype="1">
    <table style="width: 400px" cellpadding="0" cellspacing="1" class="formtable">
        <tbody>
        <tr>
            <td align="right" width="20%"><span class="filedzt">登陆密码:</span></td>
            <td class="value"><input id="password" type="password" value=""  name="password"
                                     class="inputxt" datatype="*"
                                     errormsg="请输入原密码"/> <span class="Validform_checktip"> 请输入原密码 </span></td>
        </tr>
        </tbody>
    </table>
    <div id="disSecret" style="height: 25px"></div>
    <div id="optButten" style="padding-top: 65px;text-align:right" >
    <input type="button" id="getkey" value="获取" class="anniu" onclick="toCheck(1)" style="width:70px;height:30px;padding:4px 4px;font-size:14px;margin-right:5px;border-radius:5px;background:#1E97E5;color:white;">
    <input type="button" id="restkey" value="重置" class="anniu" onclick="toCheck(2)" style="width:70px;height:30px;padding:4px 4px;font-size:14px;margin-right:5px;border-radius:5px;background:#1E97E5;color:white;">
        <input type="button" id="closeBtn" value="关闭" class="anniu" onclick='closedia();' style="width:70px;height:30px;padding:4px 4px;font-size:14px;margin-right:5px;border-radius:5px;background:#1E97E5;color:white;">
        </div>
</t:formvalid>
<script type="text/javascript">

	function closedia(){
		$('#dd').dialog('close');
	}
    function toCheck(code) {
//        alert("进入JS");
        if ($("#password").val() == "") {
            alert('请填写原始密码');
            $("#password").focus();

        } else {
        	if(code == 2){
        		var con = confirm("确定要重置密码，一旦重置 不可还原");
        		if(!con) return;
        	}
            $.ajax({
                url: "userController.do?optSecretKey",
                method: "POST",
                dataType: "JSON",
                data: {
                    "password": $("#password").val(),
                    "code":code
                },
                success: function (data) {
                    if (data.success) {
                    	$("#disSecret").css("color","green");
                    	$("#disSecret").css("font-size","25px");
                       $("#disSecret").html(data.msg);
                        $("#password").val("");
//                        return false;
                    }else{
                    	$("#disSecret").css("color","red");
                    	$("#disSecret").css("font-size","25px");
                    	 $("#disSecret").html(data.msg);
                    	 $("#password").val("");
                    }
                }
            })
        }
    }
</script>
</body>
