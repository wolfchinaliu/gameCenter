<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>邮件版本管理</title>
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
<t:formvalid formid="formobjadd" dialog="true" usePlugin="password" layout="table"
             action="weixinAcctController.do?doAdd"
             tiptype="1">
    <table style="width: 1000px;border-top: none" cellpadding="0" cellspacing="1" class="formtable">
        <tr style="border-top: none;border-bottom: none">
            <td align="right" style="">
                <label >
                    收件人:
                </label>
            </td>
            <td class="value" >
                <input id="emailTo" name="emailTo" datatype="e" errormsg="邮箱格式不正确!" ignore="ignore" type="text"
                       style="width: 600px;" class="inputxt"
                        >
            </td>
        </tr>
        <tr style="border-top: none;border-bottom: none">
            <td align="right">
                <label >
                   邮件标题:
                </label>
            </td>
            <td class="value">
                <input id="mailSubject" name="mailSubject" value="石榴管理平台版本发布"  type="text" style="width: 150px;"
                       >
            </td>
        </tr>
         <tr>
            <td align="right">
                <label>版本号：</label>
            </td>
             <td class="value">
                 <textarea id="versionNo" name="versionNo" style=" width: 600px;"></textarea>
             </td>
         </tr>

        <tr >
            <td align="right">
                <label>发布时间：</label>
            </td>
            <td class="value">
                <textarea id="publishDate" name="publishDate" style=" width: 600px;" ></textarea>
            </td>
        </tr>

        <tr >
            <td align="right">
                <label>新增：</label>
            </td>
            <td class="value">
                <textarea id="addContent" name="addContent" style=" width: 600px;"></textarea>
            </td>
        </tr>

        <tr >
            <td align="right">
                <label>优化：</label>
            </td>
            <td class="value">
                <textarea id="improveContent" name="improveContent" style=" width: 600px;"></textarea>
            </td>
        </tr>

        <tr >
            <td align="right">
                <label>删除：</label>
            </td>
            <td class="value">
                <textarea id="deleteContent" name="deleteContent" style=" width:600px;"></textarea>
            </td>
        </tr>
    </table>
</t:formvalid>
</html>