<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>商户管理表</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <style type="text/css">
        table td {
            width: 170px;
        }
        table td label {
            width: 70px;
        }
    </style>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table"
             action="weixinAcctController.do?doUpdateMyacctInfo"
             tiptype="1">
    <input id="id" name="id" type="hidden" value="${weixinAcct.id}">
    <input id="pid" name="pid" type="hidden" value="${weixinAcctPage.pid }">
    <input type="hidden" name="selectedCities">
    <input type="hidden" name="leftcities">
    <input id="createDate" name="createDate" type="hidden" value="${weixinAcctPage.createDate }">
    <input id="endDate" name="endDate" type="hidden" value="${weixinAcctPage.endDate }">

    <table style="width: 800px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    商户名称:
                </label>
            </td>
            <td class="value" colspan="8">
                <input id="acctForName" name="acctForName" type="text" datatype="*" style="width: 150px" class="inputxt"
                       value='${weixinAcctPage.acctForName}'>
                <span class="Validform_checktip">必填</span>
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
                <t:dictSelect field="acctLevel" typeGroupCode="sAcclevel" hasLabel="false" defaultVal="${weixinAcctPage.acctLevel}" extendJson="{'disabled':'disabled'}"></t:dictSelect>
                    <%--下面的写法是可以进行商户等级操作的写法--%>
                    <%--<t:dictSelect field="acctLevel" typeGroupCode="Acclevel" hasLabel="false"--%>
                    <%--defaultVal="${weixinAcctPage.acctLevel}"></t:dictSelect>--%>
                     <%-- <input disabled="true"  id="acctLevel" name="acctLevel" type="text" style="width: 150px;background:#CCCCCC" class="inputxt"
                       value="${weixinOnAcctPage.acctLevel}"> --%>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">商户等级</label>
            </td>

            <td align="right">
                <label class="Validform_label">
                    上级商户:
                </label>
            </td>
            <td class="value">
                    <%--获取当前登录用户即为新建的上级用户--%>
                <input id="belogAcct" name="belogAcct" type="text" disabled="true" style="width: 150px;background:#CCCCCC" class="inputxt"

                       value="${weixinOnAcctPage.acctName}">
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
                    <%--<input id="businessType" name="businessType" type="text" style="width: 150px" class="inputxt"--%>

                    <%--value='${weixinAcctPage.businessType}'>--%>
                <t:dictSelect field="businessType" typeGroupCode="busiType" hasLabel="false"
                              defaultVal="${weixinAcctPage.businessType}"></t:dictSelect>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">商业类型</label>
            </td>

        </tr>
        <tr>

            <td align="right">
                <label class="Validform_label">
                    电话:
                </label>
            </td>
            <td class="value">
                <input id="mobilePhone" name="mobilePhone" datatype="m" errormsg="手机号码不正确!" ignore="ignore" type="text"
                       style="width: 150px" class="inputxt"

                       value='${weixinAcctPage.mobilePhone}'>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">电话</label>
            </td>

            <td align="right">
                <label class="Validform_label">
                    邮箱:
                </label>
            </td>
            <td class="value">
                <input id="email" name="email" datatype="e" errormsg="邮箱格式不正确!" ignore="ignore" type="text"
                       style="width: 150px" class="inputxt"

                       value='${weixinAcctPage.email}'>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">邮箱</label>
            </td>

            <td align="right">
                <label class="Validform_label">
                    企业名称:
                </label>
            </td>
            <td class="value">
                <input id="businessName" name="businessName" type="text" style="width: 150px" class="inputxt"

                       value='${weixinAcctPage.businessName}'>
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

                    <%--value='${weixinAcctPage.province}'>--%>
                <select name="province" class="select1" id="province" onchange="changeList();"
                        >
                        <%--<option selected>${weixinAcctPage.province}</option>--%>
                        <%--<option>请选择</option>--%>
                    <c:forEach items="${lis}" var="itemeach">
                        <c:set var="selectedString"></c:set>
                        <c:if test="${weixinAcctPage.province eq itemeach.provinceName}"
                                >
                            <c:set value="selected" var="selectedString"></c:set>
                        </c:if>
                        <option value="${itemeach.provinceName}" ${selectedString}>${itemeach.provinceName}</option>
                    </c:forEach>
                </select>
                    <%--<input class="easyui-combobox" style="width:50%;" id="province">--%>
                    <%--<t:comboBox name="province" url="weixinAcctController.do?addProvince" id="provinceID" text="provinceName"></t:comboBox>--%>
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

                    <%--value='${weixinAcctPage.city}'>--%>
                    <%--<input id="city" name="city" type="text" style="width: 150px" class="inputxt"--%>

                    <%--value='${weixinAcctPage.city}'>--%>
                    <%--会自动根据需要的进行加载--%>

                    <%--<t:dictSelect field="city" typeGroupCode="city_jx" hasLabel="false"--%>
                    <%--defaultVal="${weixinAcctPage.city}"></t:dictSelect>--%>

                <select name="city" class="select1" id="city">
                        <%--<option value="--%>
                        <%--${weixinAcctPage.city}">${weixinAcctPage.city}</option>--%>
                    <c:forEach items="${cityList}" var="itemeach">
                        <c:set var="selectedString"></c:set>
                        <c:if test="${weixinAcctPage.city eq itemeach.cityName}"
                                >
                            <c:set value="selected" var="selectedString"></c:set>
                        </c:if>
                        <option value="${itemeach.cityName}" ${selectedString}>${itemeach.cityName}</option>

                    </c:forEach>
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
                       style="width: 150px" class="inputxt"
                       value='${weixinAcctPage.callPhone}'>

                    <%--<input id="province" name="province" type="text" style="width: 150px" class="inputxt"--%>

                    <%--value='${weixinAcctPage.province}'>--%>
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

                    <%--value='${weixinAcctPage.province}'>--%>
                <select name="province1" class="select1" id="province1" onchange="changeList1();"
                        >
                        <%--<option selected>${weixinAcctPage.province}</option>--%>
                        <%--<option>请选择</option>--%>
                    <c:forEach items="${lis}" var="itemeach">
                        <c:set var="selectedString"></c:set>
                        <c:if test="${weixinMerchantCoverAreaPage.provincename eq itemeach.provinceName}"
                                >
                            <c:set value="selected" var="selectedString"></c:set>
                        </c:if>
                        <option value="${itemeach.provinceName}" ${selectedString}>${itemeach.provinceName}</option>
                    </c:forEach>
                </select>
                    <%--<input class="easyui-combobox" style="width:50%;" id="province">--%>
                    <%--<t:comboBox name="province" url="weixinAcctController.do?addProvince" id="provinceID" text="provinceName"></t:comboBox>--%>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">省份</label>
            </td>

            <td>
                <label class="Validform_label">
                    商户管理员微信号:
                </label>
            </td>
            <td class="value">
                <textarea id="accountCurId" name="accountCurId" style="width: 150px" class="inputxt"

                          readonly="true"><%=request.getAttribute("weixinList")%></textarea>
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
                    <c:choose>
                        <c:when test="${businessArea=='三网通'}">
                            <option selected="selected">三网通</option>
                            <option>联通</option>
                            <option>电信</option>
                            <option>移动</option>
                        </c:when>

                        <c:when test="${businessArea=='联通'}">
                            <option selected="selected">联通</option>
                            <option>三网通</option>
                            <option>电信</option>
                            <option>移动</option>
                        </c:when>

                        <c:when test="${businessArea=='电信'}">
                            <option selected="selected">电信</option>
                            <option>三网通</option>
                            <option>联通</option>
                            <option>移动</option>
                        </c:when>

                        <c:otherwise>
                            <option selected="selected">移动</option>
                            <option>三网通</option>
                            <option>联通</option>
                            <option>电信</option>
                        </c:otherwise>
                    </c:choose>
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

                <select name="city1" class="select1" id="city1" multiple="multiple" style="height: 200px;float: left;">

                </select>
                <div style=" float: left; width: 41px; align-items: center; margin: 0 7px 0 16px; height: 200px; line-height: 50px; ">
                    <input type="button" value=">" id="moveRight" style=" width: 34px; ">
                    <input type="button" value="<" id="moveLeft" style=" width: 34px; ">
                    <input type="button" value=">>" id="moveAllRight">
                    <input type="button" value="<<" id="moveAllLeft">
                </div>
                <select name="selectedCity" id="selectedCity" multiple="multiple" style="height: 200px;float: left;"></select>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">地市</label>
            </td>
        </tr>

        <tr>
            <td align="right">
                <strong>状态：</strong>
            </td>
            <td class="value" colspan="8">
                <c:if test="${weixinAcctPage.status=='激活'}">

                    激活： <input type="radio" checked="checked" name="status" id="jihuo" value="激活"/>
                    锁定： <input type="radio" name="status" id="suoding" value="锁定">
                </c:if>
                &nbsp;&nbsp;&nbsp;

                <c:if test="${weixinAcctPage.status=='锁定'}">
                    激活： <input type="radio" name="status" id="jihuo" value="激活"/>
                    锁定： <input type="radio" checked="checked" name="status" id="suoding" value="锁定">
                </c:if>
            </td>
        </tr>
        
        <c:if test="${(weixinOnAcctPage.adAgency=='1' || weixinOnAcctPage.acctLevel=='0') && weixinAcctPage.acctLevel =='3'}">
        <tr id="sellAdTr">
            <td align="right">
                <label class="Validform_label">
                    <strong>是否出让<br/>广告位：</strong>
                </label>
            </td>
            <td class="value" colspan="8">
                不出让： <input type="radio" <c:if test="${weixinAcctPage.sellAdpos=='0' }"> checked="checked" </c:if>name="sellAdpos" value="0"/>
                &nbsp;&nbsp;&nbsp;
                出让： <input type="radio" <c:if test="${weixinAcctPage.sellAdpos=='1' }"> checked="checked" </c:if> name="sellAdpos" value="1">
            </td>
        </tr>
        </c:if>
        <c:if test="${(weixinOnAcctPage.acctLevel=='0' && weixinAcctPage.acctLevel !='3')}">
        <tr id="adAgencyTr">
            <td align="right">
                <label class="Validform_label">
                    <strong>是否广告<br/>代理商：</strong>
                </label>
            </td>
            <td class="value" colspan="8">
                是： <input type="radio" <c:if test="${weixinAcctPage.adAgency=='1' }"> checked="checked" </c:if> name="adAgency" value="1"/>
                &nbsp;&nbsp;&nbsp;
                否： <input type="radio" <c:if test="${weixinAcctPage.adAgency=='0' }"> checked="checked" </c:if> name="adAgency" value="0">
            </td>
        </tr>
        </c:if>
    </table>
</t:formvalid>
</body>
<script src="webpage/weixin/tenant/weixinAcct.js"></script>
<script type="text/javascript">

    $(function() {
//        开始的时候根据前面的省份加载市区
        changeList1();
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
            var fullCityList = [];

            $("#city1 option").text(function (index, cityName) {
                addCity(fullCityList, cityName);
            });

            $("#selectedCity option").text(function (index, cityName) {
                addCity(fullCityList, cityName);
            });
            for (var i = 0; i < fullCityList.length; i++) {
                $("#city1").append("<option value='" + fullCityList[i] + "'>" + fullCityList[i] + "</option>");
            }
            $("#selectedCity").empty();
            refreshCitys();
            reloadCitys();
        });

        $("#moveAllRight").click(function () {
            // changeList1("#selectedCity");
            var fullCityList = [];

            $("#city1 option").text(function (index, cityName) {
                addCity(fullCityList, cityName);
            });

            $("#selectedCity option").text(function (index, cityName) {
                addCity(fullCityList, cityName);
            });
            for (var i = 0; i < fullCityList.length; i++) {
                $("#selectedCity").append("<option value='" + fullCityList[i] + "'>" + fullCityList[i] + "</option>");
            }
            $("#city1").empty();
            refreshCitys();
            reloadCitys();
        });

        function addCity(fullCity, cityName) {
            if (!fullCity) return;
            if (!cityName) return;
            if (cityName.trim().length <=  0) return;
            for (var i = 0; i < fullCity.length; i++) {
                if (fullCity[i] == cityName) {
                    return;
                }
            }
            fullCity.push(cityName);
        }

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
    });

    //    隶属省市
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
                $('#city').empty();
                for (var i = 0; i < data.length; i++) {
                    $('#city').append("<option value='" + data[i].cityName + "'>" + data[i].cityName + "</option>");
                }

            }
        })
    }

    //    覆盖省市
    function changeList1(target) {
        target = target || "#city1";
        var options = $("#province1 option:selected");  //获取选中的项

        $.ajax({
            url: "weixinAcctController.do?addCity1",
            method: "POST",
            dataType: "JSON",
            data: {
                "provinceName": options.val(),
            },
            success: function (data) {
                if (null == data) return;
                $(target).empty();
                $("#selectedCity").empty();
                /*if (data && data.length > 0) {
                    $(target).append("<option ></option>"
                    );
                }*/
                for (var i = 0; i < data.length; i++) {
                    $(target).append("<option value='" + data[i].cityName + "'>" + data[i].cityName + "</option>");
                }
                areaCities();   //因为js的代码加载存在顺序性，但是写到一起不代表就是按照书写的顺序进行执行，此时将其放到了上一个方法执行完毕之后，此时是比较好的
            }
        })
    }

//    /查询覆盖商户已经覆盖的区域
    function areaCities(){

        var options = $("#province1 option:selected");  //获取选中的项

        var id=$("#id").val();
        $.ajax({
            url: "weixinAcctController.do?queryAreaCity",
            method: "POST",
            dataType: "JSON",
            data: {
                "provinceName": options.val(),
                "accountId":id
            },
            success: function (data) {
                if (null == data) return;
                $("#selectedCity").empty();
                /*if (data && data.length > 0) {
                    $("#selectedCity").append("<option ></option>");
                }*/
                for (var i = 0; i < data.length; i++) {
                    $("#selectedCity").append("<option value='" + data[i].cityName + "'>" + data[i].cityName + "</option>");
                    $("#city1 option[value="+data[i].cityName+"]").remove();
                }
            }
        })
    }
</script>
