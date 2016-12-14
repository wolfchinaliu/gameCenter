<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>商户管理表</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    
    <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
    <style type="text/css">
        table td {
            width: 170px;
        }
        table td label {
            width: 70px;
        }
    </style>

</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobjadd" dialog="true" usePlugin="password" layout="table"
             action="weixinAcctController.do?doAdd"
             tiptype="1">
    <input id="id" name="id" type="hidden" value="${weixinAcctPage.id }"/>
    <input id="pid" name="pid" type="hidden" value="${weixinAcctPage.pid }"/>
    <input type="hidden" name="selectedCities"/>
    <input type="hidden" name="leftcities"/>
    <input id="endDate" name="endDate" type="hidden" value="${weixinAcctPage.endDate }"/>
    <table style="width: 1000px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    登录名:
                </label>
            </td>
            <td class="value">
                <c:if test="${user.id!=null }">
                    ${user.userName }
                </c:if> <c:if test="${user.id==null }">
                <input id="loginName" class="inputxt" placeholder="用户名范围在2~10位字符" name="loginName"
                       validType="t_s_base_user,userName,id"
                       value="${user.userName }" datatype="s2-10"/>
                <span class="Validform_checktip"></span>
            </c:if>
                <label class="Validform_label" style="display: none;">登录名</label>
            </td>
            <td align="right">
                <label class="Validform_label">
                    密码:
                </label>
            </td>
            <td class="value">
                <input id="passwordName" name="passwordName" plugin="passwordStrength" datatype="*6-20"
                       errormsg="" type="text" placeholder="只能输入6-20个字母、数字、下划线" style="width: 150px;ime-mode:Disabled"
                       class="inputxt"
                       onkeyup="this.value=this.value.replace(/[\u4e00-\u9fa5]/g,'')"
                       onafterpaste="this.value=this.value.(/[\u4e00-\u9fa5]/g,'')"/>
                <span class="passwordStrength"
                      style="display: none;"><span>弱</span><span>中</span><span class="last">强</span> </span>
                <span class="Validform_checktip"></span>
                <i id="statu_user_password"></i>
                <label class="Validform_label" style="display: none;">密码</label>
            </td>

                <%--placeholder="只能输入6-20个字母、数字、下划线"--%>
            <td align="right">
                <label class="Validform_label">
                    商户名称:
                </label>
            </td>
            <td class="value">
                <input id="acctForName" name="acctForName" placeholder="必填" type="text" style="width: 150px"
                       class="inputxt"
                       datatype="*"/>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">商户名称</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    商户等级:
                </label>
            </td>
            <td class="value">
                    <%--<input id="accountLevel" name="accountLevel" type="text" style="width: 150px" class="inputxt"--%>

                    <%-->--%>
                <c:choose>
                    <c:when test="${weixinAcctPage.acctLevel=='0'}">
                        <t:dictSelect field="acctLevel" typeGroupCode="sAcclevel" hasLabel="false" extendJson="{'id':'acctLevel';'onchange':'changeAcctLevel()'}"
                                ></t:dictSelect>
                    </c:when>
                    <c:when test="${weixinAcctPage.acctLevel=='1'}">
                        <t:dictSelect field="acctLevel" typeGroupCode="aAcclevel" hasLabel="false" extendJson="{'id':'acctLevel';'onchange':'changeAcctLevel()'}"
                                ></t:dictSelect>
                    </c:when>
                    <c:when test="${weixinAcctPage.acctLevel=='2'}">
                        <t:dictSelect field="acctLevel" typeGroupCode="bAcclevel" hasLabel="false" extendJson="{'id':'acctLevel';'onchange':'changeAcctLevel()'}"
                                ></t:dictSelect>
                    </c:when>
                    <c:otherwise>
                        <t:dictSelect field="acctLevel" typeGroupCode="Acclevel" hasLabel="false" extendJson="{'id':'acctLevel';'onchange':'changeAcctLevel()'}"
                                ></t:dictSelect>
                    </c:otherwise>
                </c:choose>

                    <%--<t:dictSelect field="acctLevel" typeGroupCode="Acclevel" hasLabel="false"--%>
                    <%--defaultVal="${weixinAcctPage.acctLevel}"></t:dictSelect>--%>
                <label class="Validform_label" style="display: none;">商户等级</label>
            </td>

            <td align="right">
                <label class="Validform_label">
                    上级商户:
                </label>
            </td>
            <td class="value">
                    <%--获取当前登录用户即为新建的上级用户--%>
                <input id="belogAcct" name="belogAcct" type="text" style="width: 150px" disable="disable" class="inputxt"

                       value="${weixinAcctPage.acctName}"/>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">上级商户</label>
            </td>

            <td align="right">
                <label class="Validform_label">
                    商业类型:
                </label>
            </td>
            <td class="value">
                    <%--<input id="business_type" name="business_type" type="text" style="width: 150px" class="inputxt"--%>

                    <%-->--%>
                <t:dictSelect field="businessType" typeGroupCode="busiType" hasLabel="false"
                              defaultVal="${weixinAcctPage.businessType}"></t:dictSelect>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">商业类型</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    手机号:
                </label>
            </td>
            <td class="value">
                <input id="mobilePhone" name="mobilePhone" datatype="m" errormsg="手机号码不正确!" ignore="ignore" type="text"
                       style="width: 150px" class="inputxt"/>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">手机号</label>
            </td>

            <td align="right">
                <label class="Validform_label">
                    邮箱:
                </label>
            </td>
            <td class="value">
                <input id="email" name="email" datatype="e" errormsg="邮箱格式不正确!" ignore="ignore" type="text"
                       style="width: 150px" class="inputxt" />
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">邮箱</label>
            </td>

            <td align="right">
                <label class="Validform_label">
                    企业名称:
                </label>
            </td>
            <td class="value">
                <input id="businessName" name="businessName" type="text" style="width: 150px" class="inputxt" />
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">企业名称</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    省份:
                </label>
            </td>
            <td class="value">
                    <%--<input id="province" name="province" type="text" style="width: 150px" class="inputxt"--%>

                    <%--value="江西省">--%>

                    <%--<input class="easyui-combobox" style="width:50%;" id="province">--%>
                    <%--<t:comboBox name="province" url="weixinAcctController.do?addProvince" id="provinceID" text="provinceName"></t:comboBox>--%>
                <select name="province" class="select1" id="province" datatype="*" onchange="changeList();">
                    <option></option>
                    <option>--请选择--</option>
                    <c:forEach items="${lis}" var="itemeach">

                        <option value="${itemeach.provinceName}">${itemeach.provinceName}</option>
                    </c:forEach>
                </select>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">省份</label>
            </td>

            <td align="right">
                <label class="Validform_label">
                    地市:
                </label>
            </td>
            <td class="value">
                    <%--<input id="city" name="city" type="text" style="width: 150px" class="inputxt"--%>

                    <%-->--%>
                    <%--<t:dictSelect field="city" typeGroupCode="city_jx" hasLabel="false"--%>
                    <%--defaultVal="${weixinAcctPage.city}"></t:dictSelect>--%>
                <select name="city" class="select1" id="city">

                    <c:forEach items="${cityList}" var="itemeach"></c:forEach>
                </select>


                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">地市</label>
            </td>

            <td align="right">
                <label class="Validform_label">
                    电话:
                </label>
            </td>
            <td class="value">
                <input id="callPhone" name="callPhone" datatype="n10-11" errormsg="电话号码位数不对!" ignore="ignore"
                       type="text"
                       style="width: 150px" class="inputxt"/>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">电话</label>
            </td>

        </tr>
        <tr>

            <td align="right">
                <label class="Validform_label">
                    覆盖省份:
                </label>
            </td>
            <td class="value">
                    <%--<input id="province" name="province" type="text" style="width: 150px" class="inputxt"--%>

                    <%--value="江西省">--%>

                    <%--<input class="easyui-combobox" style="width:50%;" id="province">--%>
                    <%--<t:comboBox name="province" url="weixinAcctController.do?addProvince" id="provinceID" text="provinceName"></t:comboBox>--%>
                <select name="province1" class="select1" id="province1" datatype="*" onchange="changeList1();">
                    <option></option>
                    <option>--请选择--</option>
                    <c:forEach items="${lis}" var="itemeach">

                        <option value="${itemeach.provinceName}">${itemeach.provinceName}</option>
                    </c:forEach>
                </select>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">省份</label>
            </td>
            <td style="text-align: right">
                <label class="Validform_label">
                    商户管理员微信号:
                </label>
            </td>
            <td class="value">
                <div style="padding:5px 15px;color:#B7B7B7;">
                    <strong>温馨提示：多个管理员微信号以英文,间隔</strong>
                </div>
                <textarea id="accountCurId" name="accountCurId" style="width: 150px" class="inputxt"
                        ></textarea>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">商户管理员微信号</label>
            </td>

            <td style="text-align: right">
                <label class="Validform_label">
                   流量运营商:
                </label>
            </td>
            <td class="value">
               <select name="businessArea" id="businessArea" datatype="*" class="select">
                    <option>三网通</option>
                    <option>联通</option>
                    <option>电信</option>
                    <option>移动</option>
               </select>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">流量运营商</label>
            </td>

        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    覆盖地市:
                </label>
            </td>

            <td class="value" colspan="8">
                    <%--<input id="city" name="city" type="text" style="width: 150px" class="inputxt"--%>

                    <%-->--%>
                    <%--<t:dictSelect field="city" typeGroupCode="city_jx" hasLabel="false"--%>
                    <%--defaultVal="${weixinAcctPage.city}"></t:dictSelect>--%>
                <select name="city1" class="select1" id="city1" multiple="multiple" style="height: 200px;float: left;">

                </select>
                <div style=" float: left; width: 41px; align-items: center; margin: 0 7px 0 16px; height: 200px; line-height: 50px; ">
                    <input type="button" value=">" id="moveRight" style=" width: 34px; ">
                    <input type="button" value="<" id="moveLeft" style=" width: 34px; ">
                    <input type="button" value=">>" id="moveAllRight">
                    <input type="button" value="<<" id="moveAllLeft">
                </div>
                <select name="selectedCity" id="selectedCity" multiple="multiple" style="height: 200px;float: left;"></select>
                    <%--这是为了添加多个覆盖区域进行设置的--%>
                    <%--<select id="showNmber">
                    </select>
                    <a href="javascript:;" id="j-btn-make" class="ui-btn ui-btn-4">添加覆盖区域</a>--%>
                    <%--这是为了添加多个覆盖区域进行设置的--%>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">地市</label>
            </td>

        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    <strong>状态：</strong>
                </label>
            </td>
            <td class="value" colspan="8">
                激活： <input type="radio" checked="checked" name="status" id="jihuo" value="激活"/>
                &nbsp;&nbsp;&nbsp;
                锁定： <input type="radio" name="status" id="suoding" value="锁定"/>

                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">状态</label>
            </td>
        </tr>
        <tr style="display: none;" id="outerPr">
            <td align="right">
                <label class="Validform_label">
                    外部AppId:
                </label>
            </td>
            <td class="value">
                <input id="outAppId" name="outAppId"  type="text"
                       style="width: 150px" class="inputxt"/>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">外部AppId</label>
            </td>

            <td align="right" >
                <label class="Validform_label">
                    外部AppKey:
                </label>
            </td>
            <td class="value" colspan="6">
                <input id="outAppKey" name="outAppKey"   type="text"
                       style="width: 150px" class="inputxt"/>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;"> 外部AppKey</label>
            </td>

        </tr>
        <c:if test="${(weixinAcctPage.adAgency=='1' || weixinAcctPage.acctLevel=='0')}">
        <tr id="sellAdTr" style="display:none">
            <td align="right">
                <label class="Validform_label">
                    <strong>是否出让<br/>广告位：</strong>
                </label>
            </td>
            <td class="value" colspan="8">
                不出让： <input type="radio" checked="checked" name="sellAdpos" value="0"/>
                &nbsp;&nbsp;&nbsp;
                出让： <input type="radio" name="sellAdpos" value="1"/>
            </td>
        </tr>
        </c:if>
        <c:if test="${weixinAcctPage.acctLevel=='0'}">
        <tr id="adAgencyTr" style="display:none">
            <td align="right">
                <label class="Validform_label">
                    <strong>是否广告<br/>代理商：</strong>
                </label>
            </td>
            <td class="value" colspan="8">
                是： <input type="radio" checked="checked" name="adAgency" value="1"/>
                &nbsp;&nbsp;&nbsp;
                否： <input type="radio" name="adAgency" value="0"/>
            </td>
        </tr>
        </c:if>
    </table>
</t:formvalid>
</body>
<script src="webpage/weixin/tenant/weixinAcct.js"></script>
<script type="text/javascript">
    $(function() {
        $("[name=acctLevel]").val(3);
        // 初始化添加覆盖城市列表功能
        $("#city1").dblclick(function () {
            $("#moveRight").click();
        });
        $("#selectedCity").dblclick(function () {
            $("#moveLeft").click();
        });
        $("#moveRight").click(function () {
            var selected = $("#city1").val();
            if (!selected || !selected.length) {
                return;
            }
            for(var i = 0; i < selected.length; i++) {
                var newOption = $("<option>");
                newOption.html(selected[i]);
                newOption.val(selected[i]);
                $("#selectedCity").append(newOption);
                $("#city1 option").remove("[value=" + selected[i] + "]");
            }
            refreshCitys();
            reloadCitys();
        });

        $("#moveLeft").click(function () {
            var selected = $("#selectedCity").val();
            if (!selected || !selected.length) {
                return;
            }
            for(var i = 0; i < selected.length; i++) {
                var newOption = $("<option>");
                newOption.html(selected[i]);
                newOption.val(selected[i]);
                $("#city1").append(newOption);
                $("#selectedCity option").remove("[value=" + selected[i] + "]");
            }
            refreshCitys();
            reloadCitys();

        });

        $("#moveAllLeft").click(function () {
            $("#selectedCity").empty();
            changeList1();
            refreshCitys();
            reloadCitys();
        });

        $("#moveAllRight").click(function () {
            $("#city1").empty();
            changeList1("#selectedCity");
            refreshCitys();
            reloadCitys();
        });

        function refreshCitys() {
            $("[name=selectedCities]").val("");
            $("#selectedCity option").text(function (index, cityName) {
                if (0 == index) {
                    $("[name=selectedCities]").val(cityName);
                } else {
                    $("[name=selectedCities]").val($("[name=selectedCities]").val() + "," + cityName);
                }
            });
        }

        function reloadCitys() {
            $("[name=leftcities]").val("");
            $("#city1 option").text(function (index, cityName) {
                if (0 == index) {
                    $("[name=leftcities]").val(cityName);
                } else {
                    $("[name=leftcities]").val($("[name=leftcities]").val() + "," + cityName);
                }
            });
        }
        
        changeAcctLevel();
    });

    $("#loginName").blur(function () {
        if ($("#loginName").val() == "") {
//            alert('登录名不能为空');
            $("#loginName").focus();
        } else {
            //检测用户名是否已注册
            $.ajax({
                url: "weixinAcctController.do?checkRegisterName",
                method: "POST",
                dataType: "JSON",
                data: {
                    "loginName": $("#loginName").val()
                },
                success: function (data) {
                }
            });
        }

    });
    //隶属的省市
    function changeList() {
        var options = $("#province option:selected");  //获取选中的项

//        alert(options.val());   //拿到选中项的值

//        alert(options.text());   //拿到选中项的文本

        $.ajax({
            url: "weixinAcctController.do?addCity1",
            method: "POST",
            dataType: "JSON",
            data: {
                "provinceName": options.val(),
            },
            success: function (data) {
                $("#city").empty();
                for (var i = 0; i < data.length; i++) {
                    $("#city").append("<option value='" + data[i].cityName + "'>" + data[i].cityName + "</option>");
                }

//                eval("ps=" + data.json);
//                $("#city").empty();
//                $("#city").append("<option value='请选择'>请选择</option>");
//                for (i = 0; i < ps.length; i++) {
//                    $(".a").append(
//                            "<option value='" + ps[i].id + "'>" + ps[i].cityName
//                            + "</option>");
//
//                }
            }
        })
    }

	var changeAcctLevel = function(){
		var acctLevel = $("#acctLevel").val();
		if(acctLevel!='3' && acctLevel!='4'){
			$("#sellAdTr").hide();
			$("#adAgencyTr").show();
		}else{
			$("#sellAdTr").show();
			$("#adAgencyTr").hide();
		}
		if(acctLevel == '4'){
			$("#outerPr").show();
		}else{
			$("#outerPr").hide();
		}
	}

    //覆盖的省市
    function changeList1(target) {
        target = target || "#city1";
        var options = $("#province1 option:selected");  //获取选中的项

//        alert(options.val());   //拿到选中项的值

//        alert(options.text());   //拿到选中项的文本

        $.ajax({
            url: "weixinAcctController.do?addCity1",
            method: "POST",
            dataType: "JSON",
            data: {
                "provinceName": options.val(),
            },
            success: function (data) {
                $(target).empty();
                if (!target && data.length > 0) {
                    $(target).append("<option ></option>"
                    );
                }

                ;
                for (var i = 0; i < data.length; i++) {
                    $(target).append("<option value='" + data[i].cityName + "'>" + data[i].cityName + "</option>");
                }

            }
        })
    }



    <%--添加商户覆盖区域--%>
    $(function () {
        $('#j-btn-make').click(function () {
            var provincename=$("#province1 option:selected").text();  //获取选中的项
            $.ajax({
                url: "weixinAcctController.do?addAreaCity",
                type: "POST",
                data: {"provincename": provincename},
                dataType: "JSON",
                success: function (data) {
                    //如果标志位为2，表示尚未选择省份
                    if (data.status == 2) {
                        alert(data.msg);
                        return;
                    }
                    //如果标志位为1，表示该省份或者市是不存在对应的市集合的
                    if (data.status == 1) {
                       alert(data.msg);
                        return;
                    }
                    //如果标志位为0，表示该省份是存在多个市区的
                    if (data.status == 0) {
                        var optionList = "";
                        if (data && data.cityList) {
                            var cityList = data.cityList;
                            for (var i = 0; i < cityList.length; i++) {
                                optionList += '<option value=\"' + cityList[i].id + '\">' + cityList[i].cityName + '</option>';
                            }
                        }
                        var dialog = Zepto('body').popup({
                            title: '提示'
                            ,
                            message: '<p>您可以为'+data.provincename+',添加覆盖区域，请选择后添加</p><div class="dialog-text">添加覆盖区域：<select id="cityname" name="cityname" class="select"><option>--请选择--</option>' +
                            optionList +
                            '</select></div>'
                            ,
                            id: 'pop-2'
                            ,
                            ok: "确认"
                            ,
                            closeOnOk: false
                            ,
                            onOk: function () {
                                // 确认按钮的回调函数
                                /* console.log('ok');*/
                                var $familyNumber = $("#familyNumber");
                                var familyNumber = $.trim($familyNumber.val());

                                var phoneNumber = document.getElementById("phoneNumber").value;
                                $.ajax({
                                    url: "userChargeController.do?bindFamilyNumber",
                                    type: "post",
                                    data: {"familyNumber": familyNumber, "phoneNumber": phoneNumber},
                                    dataType: "json",
                                    success: function (data) {
                                        $.mobTips(data.msg);
                                        if (data.msg == "该亲情号已存在") {
                                        } else {
                                            $("#showNmber").append("<option>" + familyNumber + "</option>");
                                        }
                                        dialog.hide();
//                        loadMeal();
                                    },
                                    error: function () {
                                        $.mobTips("error");
                                    }
                                });
                                return false;
                            }
                            ,
                            cnacel2: "取消"
                            ,
                            onCancel2: function () {
                                console.log('cancel');
                            }
                        });
                        return false;
                    }
                },
                error: function () {
                    $.mobTips("error");
                }
            });


        });





    });
</script>
