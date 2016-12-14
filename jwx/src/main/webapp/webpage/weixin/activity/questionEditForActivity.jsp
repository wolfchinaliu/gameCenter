<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>

<head>
    <title>添加题目</title>
    <t:base type="jquery,easyui,tools"></t:base>
    <script type="text/javascript">
    var option = ${question.questionOption == null ? "{}":question.questionOption};
    var row = 0;
    var selectName = ['A','B','C','D','E','F','G'];
    $(document).ready(function(){
    	for(var i = 0; i < option.length;i++){
    		addRow(option[i]);
    	}
    });
    function addRow(value){
    	if(value == null){
    		if(row >= 6){
    			$.messager.alert("温馨提示！  ","最多只能有六个选项");
    			return ;
    		}
    		value = {'optionId':selectName[row],'optionText':''};
    	}
    	var newRow = '<tr><td align="right"><label class="Validform_label">选项号：</label></td><td class="value" >'+
            		'<input class="inputxt" id="optionId'+row+'" datatype="*" style="width: 50px" value="'+value.optionId+'" type="text"> <span class="Validform_checktip"></span></td>'+
            		'<td align="right"><label class="Validform_label">选项内容：</label></td><td class="value" >'+
            		'<input class="inputxt" id="optionText'+row+'" datatype="*" style="width: 350px" value="'+value.optionText+'" type="text"> <span class="Validform_checktip"></span></td></tr>';
    	$("#questionTable tr:last").after(newRow);
    	row++;
    }
    function deleteRow(){
    	if(row<= 0)
    		return;
    	$("#questionTable tr:last").remove();
    	row--;
    }
    function submit(){
    	var optionValue = "["
    	for(var i=0;i<row;i++){
    		optionValue+="{\"optionId\":\""+$("#optionId"+i).val()+"\",\"optionText\":\""+$("#optionText"+i).val()+"\"},"
    	}
    	optionValue =optionValue.substring(0,optionValue.length-1);
    	optionValue+="]";
    	$('#questionOption').val(optionValue);
    	return true;
    }

    </script>
        <style type="text/css">
        .anniu{
    	color:#01599d;
    	text-decoration: none;
    	padding:6px;
    	border-radius: 5px;
    	background: #C4C4C4;
    	margin: 1px;
    	line-height: 14px;
    	font-size: 12px;
    	cursor: pointer;
    }
    .anniu:hover{
    	color:#e2800c;
    	font-size: 13px;
    	text-decoration: none;}
        </style>
</head>
<body >
<t:formvalid formid="formid" dialog="true"
layout="div" action="weixinActivityController.do?doQuestionSave" tiptype="1" beforeSubmit="submit">
<div>

    <input id="activityId" name="activityId" type="hidden"
           value="${question.activityId}">
    <input id="questionOption" name="questionOption" type="hidden"
           value="${question.questionOption}">
    <input id="id" name="id" type="hidden"
           value="${question.id}">
    <table style="width: 400px;" cellpadding="0" cellspacing="1" id="questionTable"
           class="formtable">
       <tr>
            <td align="right"><label class="Validform_label"> 序号:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt"
                                                 id="serial" datatype="*" style="width: 350px" name="serial"
                                                 type="text" value="${question.serial }"> <span
                    class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 题目内容:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" id="questionText" datatype="*" style="width: 350px"
                                                 name="questionText" type="text" value="${question.questionText }"> <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 答案:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt"
                                                 id="answer" datatype="*" style="width: 350px" name="answer"
                                                 type="text" value="${question.answer }"> <span
                    class="Validform_checktip"></span></td>
        </tr>


    </table>
<a class="anniu" href="javascript:addRow();">添加选项</a><a class="anniu" href="javascript:deleteRow();">删除选项</a>
</div>
    </t:formvalid>
</body>
