<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>微信活动</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
//        var flag=true;
//        //编写自定义JS代码
//        function admire(){
//            if(flag==false){
//
//            }
//            var endtime=$('#endtime').val();
//            var myDate=new Date();
//            if(endtime<myDate.getMilliseconds()+431981103){
//                alert("温馨提示：活动时间不够五天哦！建议您设置的长一点")
//            }
//        }


    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password"
             layout="table" action="personalRedpacketSetController.do?doAdd" tiptype="1">
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <tr>
            <td align="right"><label class="Validform_label"> 活动名称:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" id="title" datatype="*"
                                                 name="title" type="text"> <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 活动描述:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt"
                                                 id="description" style="width: 350px" name="description"
                                                 ignore="ignore" type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">
                流量类型: </label></td>
            <td class="value" colspan="3">
                <input type="radio" value="1" name="flowtype" checked="checked"/>全国流量
                <input type="radio" value="2" name="flowtype"/>省内流量
            </td>
        </tr>
        <tr>

        <td align="right"><label class="Validform_label"> 开始时间:
        </label></td>
        <td class="value"><input class="Wdate"
                                 onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                 style="width: 150px" id="starttime" name="starttime"
                                 value="<fmt:formatDate value='${weixinLotteryPage.starttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
            <span class="Validform_checktip"></span></td>
            <td align="right"><label class="Validform_label"> 结束时间:
            </label></td>
            <td class="value"><input onblur="admire()" class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                     style="width: 150px" id="endtime" name="endtime"
                                     value="<fmt:formatDate value='${weixinLotteryPage.endtime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
                <span class="Validform_checktip"></span></td>
        </tr>

        <tr>
            <td align="right"><label class="Validform_label">
                每个红包有: </label></td>
            <td colspan="3"  class="value">
                <select id="redpacketValue" name="redpacketValue"  style="width: 150px">
                    <option value="300">300M</option>
                    <option value="400">400M</option>
                    <option value="500">500M</option>
                </select>
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">红包流量值</label>
                <label>用户每次发红包为此固定值，每个红包固定为20份</label>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">
                补贴流量: </label></td>
            <td colspan="3"  class="value"><input id="subsidyValue"
                                     name="subsidyValue" type="text" style="width: 150px"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">补贴流量</label></td>
        </tr>
        <tr>
        <tr>
            <td colspan="4">
                说明：
            </td>
        </tr>
            <tr>
                <td colspan="4">
                    &nbsp;&nbsp;&nbsp;&nbsp;*新创建的活动，用户可发送和领取的次数都为3次。
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    &nbsp;&nbsp;&nbsp;&nbsp;*到期后用户无法再从您的公众号发出和领取红包
                </td>
            </tr>
        <tr>
            <td colspan="4">
                &nbsp;&nbsp;&nbsp;&nbsp;*您将拿出流量补贴给用户发送红包，设定后会从您的流量币的账户里扣除相应的流量，如未用完，则会自动退回到您的账户里，剩余补贴流量小于您设定的每个红包值时，用户将不再能从您公众号里发出流量红包。
            </td>
        </tr>

    </table>
</t:formvalid>
</body>
