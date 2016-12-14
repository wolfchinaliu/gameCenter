<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>测试接口</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>

    <script type="text/javascript">
        var flag=true;
        $(function() {
            $("#recharge").click(function() {
                recharge();
            });
        });
        function recharge(){
            if(flag==false){
               return;
            }
            flag=false;
            var phoneNumber=document.getElementById("phoneNumber").value;
            $.ajax({
                type : 'POST',
                url : "externalRecharge.do?rechargeExternal",
                dataType : "json",
                data : {
                    "phoneNumber" : phoneNumber,
                    "flowValue":"100",
                    "accountId":"2c90808b52637dd701526daf722b003c"
                },
                cache : false,
                error : function() {
                    alert("充值失败");
                    flag=true;
                    return false;
                },
                success : function(result) {
                    flag=true;
                    alert("充值成功")
                }
            });
        }
    </script>
</head>
<body>
<form>
<div>测试充值</div>
<div>请输入手机号：<input id="phoneNumber"></div>
<div>流量值：100M</div>
<div><input id="recharge" type="button" value="充值"></div>
</form>
</body>


