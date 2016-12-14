<%--
  Created by IntelliJ IDEA.
  User: aa
  Date: 2015/12/23
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<html>
<head>
    <title></title>
</head>
<body>
<%--<label>公众号id：</label>
<input id="accountid" name="accountid" class="inputxt"><br><br>

<a id="btnGetIDKey" class="EasyUI-linkbutton" onclick="goProduceIdKey()">生成apiId和apiKey</a><br><br><br>

<input id="apiId" name="apiId" class="inputxt" value="">
<input id="apiKey" name="apiKey" class="inputxt" style="width: 300px;" value=""><br><br><br><br><br><br>

<a id="btnSave" class="EasyUI-linkbutton" onclick="goSave()">保存</a>--%>


<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinAcctController.do?doProduceIdKey"
             tiptype="1">
    <label>公众号id：</label>
    <input id="id" name="id" type="hidden" value="${openService.id }">
    <input id="accountid" name="accountid" class="inputxt" value="${openService.id }">

    <a id="btnGetIDKey" class="EasyUI-linkbutton" onclick="goProduceIdKey()">生成apiId和apiKey</a><br><br><br>

    <label>apiId：</label>
    <input id="apiId" name="apiId" class="inputxt" disabled="true" value="${openService.apiId }"><br><br><br>
    <input id="apiIdhidden" type="hidden" name="apiIdhidden"  class="inputxt" value="${openService.apiId }"><br><br><br>
    <label>apiKey：</label>
    <input id="apiKey" name="apiKey" class="inputxt" style="width: 300px;" disabled="true" value="${openService.apiKey }"><br><br><br><br><br><br>
    <input id="apiKeyhidden" name="apiKeyhidden" type="hidden" class="inputxt" style="width: 300px;" value="${openService.apiKey }"><br><br><br><br><br><br>


</t:formvalid>

<script type="text/javascript">
    function goProduceIdKey() {
        var strAccountId=document.getElementById("accountid").value;

        //随机生成apiId和apiKey
        var test1=randomString(10);
        var test2=randomString(32);

        document.getElementById("apiId").value=test1;
        document.getElementById("apiIdhidden").value=test1;
        document.getElementById("apiKey").value=test2;
        document.getElementById("apiKeyhidden").value=test2;

    }

    function randomString(len){
        len=len || 32;
        var $chars='abcdefhijkmnprstwxyz23456789';
        var maxPos=$chars.length;
        var pwd="";
        for(i=0;i<len;i++){
            pwd+=$chars.charAt(Math.floor(Math.random()*maxPos));
        }
        return pwd;
    }
</script>
</body>
</html>
