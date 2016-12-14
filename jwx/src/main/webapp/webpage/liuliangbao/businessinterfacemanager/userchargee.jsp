<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <t:base type="jquery"></t:base>
    <link href="index/home.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="plug-in/jquery/jquery.form.min.js"></script>
    <style type="text/css">
        .field {
            padding-top: 10px;
            padding-bottom: 20px;
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
<div class="reg_form">
    <form accept-charset="UTF-8" action="userChargeController.do?testValidate" id="new_user"
          method="post" <%--onsubmit="return checkForm();return false;"--%>>
        <div><label>用户</label></div>
        <div><label>省内流量</label><label>&nbsp;&nbsp;&nbsp;</label><input id="provinceFlow" name="provinceFlow" size="30">
        </div>

        <div><label>全国流量</label><label>&nbsp;&nbsp;&nbsp;</label><input id="countryFlow" name="quanguoFlow" size="30">
        </div>

        <div id="wrap">
            <input type="radio" name="FlowType" style="width:20px" value="countryFlowValue" onblur="IsHaveInterface()">全国流量
            <input type="radio" name="FlowType" style="width: 20px" value="provinceFlowValue"
                   onblur="IsHaveInterface()">省内流量
        </div>


        <div class="field inline">
            <label for="mobilePhone">手机号码</label>
            <input id="mobilePhone" name="mobilePhone" placeholder="请输入您的手机号码" required="required" size="30" type="text"
                   value="">
            <i id="statu_user_mobilePhone"></i>
        </div>

        <tr>
            <th>手机号码归属地：</th>
        </tr>
        <input type="text" id="phoneresult" value="">
        <input type="text" id="businessCode" hidden="hidden" value="">
        <input type="text" id="provinceCode" hidden="hidden" value="">

        <div class="field inline">
            <tr>
                <th>运营商流量套餐：</th>
            </tr>
            <input type="text" id="valueMeal" value="">
        </div>

        <div class="field inline">

        </div>

        <div class="field inline">
            <label for="flowTrueValue">请输入您想提现的流量数：</label>
            <input id="flowTrueValue" name="flowTrueValue" placeholder="请输入您想提现的流量数" required="required" size="30"
                   type="text"
                   value="">
        </div>

        <div class="field inline">
            <label>&nbsp;&nbsp;&nbsp;</label>
            <input class="btn_login" id="submitbtn2" name="commit" value="提 现" onclick="getFlowtoUser()">
        </div>

    </form>
</div>

<script type="text/javascript">
    function IsHaveInterface() {
        //alert("hehe");
        var flowTypeValue = document.getElementsByName("FlowType");
         /*vfor (var i = 0; i < flowTypeValue.length; i++) {
            if (flowTypeValue[i].checked == true) {
                valuethree = flowTypeValue[i].value;
                break;
            }
        }
        var flowType=$("#flowTrueValue").val();
        alert(flowType);*/
        //alert($("#flowTrueValue").val());
        if(flowTypeValue[1].checked){
            alert("省内");
            $.ajax({
                url: "userChargeController.do?validateIsHaveInterface",
                type: "POST",
                dataType: "JSON",
                data: {
                    "mobilePhone": $("#mobilePhone").val()
                },

                success: function (data) {
                    alert(data);
                }, error: function () {
                    alert("没有省内接口可用，请选择全国接口");
                }
            });
        }
    }


    $("#mobilePhone").blur(function () {
        //alert("123");
        alert(document.getElementById("mobilePhone").value);
        /*var type1=document.getElementsByName("FlowType")[1].value;
         alert(type1);*/

/*        var flowTypeValue = document.getElementsByName("FlowType");
        for (var i = 0; i < flowTypeValue.length; i++) {
            if (flowTypeValue[i].checked == true) {
                valuethree = flowTypeValue[i].value;
                break;
            }
        }
        alert(valuethree);*/

        $.ajax({
            url: "userChargeController.do?testValidate",
            type: "POST",
            dataType: "JSON",
            data: {
                "phoneNumber": $("#mobilePhone").val(),
                "businessCode": $("#businessCode").val(),
                "provinceCode": $("#provinceCode").val()
            },
            success: function (data) {
                alert(data.provinceName);
                document.getElementById("phoneresult").value = data.provinceName;
                document.getElementById("valueMeal").value = data.flowValue;
                document.getElementById("businessCode").value = data.businessCode;
                document.getElementById("provinceCode").value = data.provinceCode;
            }, error: function () {
                alert("error");
            }
        });
    });

    function getFlowtoUser() {
        //alert("123");
        var flowTypeValue = document.getElementsByName("FlowType");
        for (var i = 0; i < flowTypeValue.length; i++) {
            if (flowTypeValue[i].checked == true) {
                valuethree = flowTypeValue[i].value;
                break;
            }
        }
        alert($("#flowTrueValue").val());
        $.ajax({
            url: "userChargeController.do?testChargeFlow",
            type: "POST",
            dataType: "JSON",
            data: {
                "flowTrueValue": $("#flowTrueValue").val(),
                "phoneNumber": $("#mobilePhone").val(),
                "businessCode": $("#businessCode").val(),
                "provinceCode": $("#provinceCode").val(),
                "flowType": valuethree
            },
            success: function (data) {
                //alert(data);
                alert("充值成功！");
            }, error: function () {
                alert("充值失败！");
            }
        });
    }

    /*$("#flowTrueValue").blur(function () {
     var provinceFlow=document.getElementById("provinceFlow").value;
     var countryFlow=document.getElementById("countryFlow").value;
     var trueFlow=document.getElementById("flowTrueValue").value;

     var flowType=document.getElementsByName("FlowType");
     for(var i=0;i<flowType.length;i++){
     if(flowType[i].checked==true){
     valuethree=flowType[i].value;
     break;
     }
     }
     alert(valuethree);

     if(valuethree=="provinceFlowValue"){
     if(provinceFlow<trueFlow){
     alert("您的省内流量不足，请选择其他套餐！");
     }
     }else{
     if(countryFlow<trueFlow){
     alert("您的全国流量不足，请选择其他套餐！");
     }
     }


     });*/
</script>


</body>
</html>