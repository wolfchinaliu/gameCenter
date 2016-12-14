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
var regulation = ${activity.activityRule == null ? "{'rankRule':{},'gradeRule':{},'evenFrequency':''}":activity.activityRule};
var rankRule = regulation.rankRule;
var gradeRule = regulation.gradeRule;
var evenFrequency = regulation.evenFrequency;
var rankNames = ['第一名','第二名','第三名','第四名','第五名','第六名','第七名','第八名','第九名','第十名'];
var rankRow = 0;
var gradeRow = 0;
var j=0;
$(document).ready(function(){
	for(var i in rankRule){
		addRankRow(i,rankRule[i]);
	}
	for(var i in gradeRule){
		addGradeRow(i,gradeRule[i]);
	}
	//$('#evenFrequency').val(evenFrequency);
	$('#evenFrequency').attr("value",evenFrequency);
	<c:if test="${code == 0}">
	$('input').attr("readonly","readonly");
	</c:if>
});
function addRankRow(key,flowValue){
		if(rankRow >= 10){
			$.messager.alert("温馨提示！  ","最多只能有十个名次名额");
			return ;
		}
		if(rankRow > 0){
			//校验上条
			var value = $('#rankFlow'+(rankRow-1)).val();
			if(!/^[0-9]+$/.test(value) ){
				$.messager.alert("温馨提示！  ",$('#rankName'+(rankRow-1)).html() +" 的流量值输入有误");
				return ;
			}
		}
	var newRow = "<tr><td id=\"rankName"+rankRow+"\">"+rankNames[rankRow]+"</td>\
				<td><input id=\"rankFlow"+rankRow+"\" value=\""+flowValue+"\"/></td>\
				</tr>";
	$("#tableRankRule tr:last").after(newRow);
	rankRow++;
}
function addGradeRow(key,flowValue){
	if(gradeRow >= 10){
		$.messager.alert("温馨提示！  ","最多只能有十个分数段名额");
		return ;
	}
	if(gradeRow > 0){
		//校验上条
		var value = $('#gradeFlow'+(gradeRow-1)).val();
		var score = $('#gradeScore'+(gradeRow-1)).val();
		if(!/^[0-9]+$/.test(value) ){
			$.messager.alert("温馨提示！  "," 上一条的流量值输入有误 " + value);
			return ;
		}
		if(!/^[0-9]+$/.test(score) ){
			$.messager.alert("温馨提示！  ","上一条 的分数值输入有误 " + score);
			return ;
		}
	}
var newRow = "<tr><td><input id=\"gradeScore"+gradeRow+"\" value=\""+key+"\"/></td>\
			<td><input id=\"gradeFlow"+gradeRow+"\" value=\""+flowValue+"\"/></td>\
			</tr>";
$("#tableGradeRule tr:last").after(newRow);
gradeRow++;
}
function deleteRankRow(){
	if(rankRow == 0)return;
	$("#tableRankRule tr:last").remove();
	if(j>0)j--;
	rankRow--;
}
function deleteGradeRow(){
	if(gradeRow == 0 )return ;
	$("#tableGradeRule tr:last").remove();
	if(j>0)j--;
	gradeRow--;
}
function submit(){
	var code = ${code};
	if(code == 0){
		frameElement.api.close();
		return false;
	}
	
	if(rankRow < 1 && gradeRow < 1){
		$.messager.alert("温馨提示！  ","请添加奖项");
		return false;
	}
	//校验
	var probabilitys = 0;
	for(var i = 0; i< rankRow;i++){
		var value = $('#rankFlow'+i).val();
		if(!/^[0-9]+$/.test(value) ){
			$.messager.alert("温馨提示！  ",$('#rankName'+i).html() +" 的流量值输入有误");
			return false;
		}
	}
	for(var i = 0; i< gradeRow;i++){
		var value = $('#gradeFlow'+i).val();
		var score = $('#gradeScore'+i).val();
		if(!/^[0-9]+$/.test(value) ){
			$.messager.alert("温馨提示！  ", "第"+i +"条分数奖的流量值输入有误");
			return false;
		}
		if(!/^[0-9]+$/.test(score) ){
			$.messager.alert("温馨提示！  ","第"+ i +"条分数奖的分数值输入有误");
			return false;
		}
	}
	
	var regulationSave = "{'rankRule':["
		var i=0;
	for(;i<rankRow;i++){
		regulationSave+=$('#rankFlow'+i).val()+","
	}
	if (i > 0)
		regulationSave = regulationSave.substring(0,regulationSave.length -1);
	regulationSave+="],'gradeRule':{";
	var j = 0;
	for(; j < gradeRow; j++){
		regulationSave+=$('#gradeScore'+j).val() +":" + $("#gradeFlow"+j).val() + ","
	}
	if(j > 0)
		regulationSave = regulationSave.substring(0,regulationSave.length -1);
	regulationSave+="},'evenFrequency':"+$('#evenFrequency').val()+"}";
	$('#activityRule').val(regulationSave);
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
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password"
             layout="table" action="weixinActivityController.do?doSave" tiptype="1" beforeSubmit="submit">
    <input id="id" name="id" type="hidden"
           value="${activity.id }"/>
    <input id="type" name="type" type="hidden"
           value='${activity.type == null?201:activity.type }'/>
           <input id="activityRule" name="activityRule" type="hidden"/>
    <table style="width: 600px;" cellpadding="0" cellspacing="1"
           class="formtable">
        <tr>
            <td align="right"><label class="Validform_label"> 活动名称:
            </label></td>
            <td class="value" ><input class="inputxt" id="title" datatype="*" value="${activity.title }"
                                                 name="title" type="text"> <span class="Validform_checktip"></span></td>
                                                  <td align="right"><label class="Validform_label">
                流量类型: </label></td>
            <td class="value">
                <input type="radio" value="1" name="flowType" <c:if test="${activity.flowType eq'1' || activity.flowType == null}">checked="checked"</c:if> /> 全国流量
                <input type="radio" value="2" name="flowType" <c:if test="${activity.flowType eq '2'}">checked="checked"</c:if>/>省内流量
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 活动描述:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" value="${activity.description }"
                                                 id="description" style="width: 350px" name="description"
                                                 ignore="ignore" type="text"> <span
                    class="Validform_checktip"></span></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 开始时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                     style="width: 150px" id="startTime" name="startTime"
                                     value="<fmt:formatDate value='${activity.startTime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
                <span class="Validform_checktip"></span></td>
            <td align="right"><label class="Validform_label"> 结束时间:
            </label></td>
            <td class="value"><input class="Wdate"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                     style="width: 150px" id="endTime" name="endTime"
                                     value="<fmt:formatDate value='${activity.endTime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
                <span class="Validform_checktip"></span></td>
        </tr>
        <tr>
         <td align="right"><label class="Validform_label"> 参与频次:
            </label></td>

            <td class="value"><t:dictSelect field="frequency" typeGroupCode="frequency" hasLabel="false"
                                            defaultVal="${activity.frequency == null ? 1 :activity.frequency }"></t:dictSelect>
                <span class="Validform_checktip"></span></td>
            <td align="right"><label class="Validform_label">
                每人玩游戏次数: </label></td>
            <td class="value"><input id="evenNumber"
                                     name="evenNumber" type="text" style="width: 150px" value="${activity.evenNumber }"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">每人抽奖次数</label></td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label">
                总玩游戏次数: </label></td>
            <td class="value"><input id="totalNumber"
                                     name="totalNumber" type="text" style="width: 150px" value="${activity.totalNumber }"
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">总抽奖次数</label></td>
                         <td align="right"><label class="Validform_label">
                每周期获奖人数: </label></td>
            <td class="value"><input id="evenFrequency"
                                     name="evenFrequency" type="text" style="width: 150px" 
                                     class="easyui-numberbox inputxt" datatype="n" precision="0">
                <span class="Validform_checktip"></span> <label
                        class="Validform_label" style="display: none;">每周期获奖人数 </label></td>
        </tr>
         <tr>
            <td align="right"><label class="Validform_label"> 时间段设置:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" value="${activity.timePart }"
                                                 id="timePart" style="width: 300px" name="timePart"
                                                 ignore="ignore" type="text"> <span>9:00-13:00;18:20-20:05/1,2,3</span></td>
        </tr>
         <tr>
            <td align="right"><label class="Validform_label"> 自定义图片:
            </label></td>
            <td class="value" colspan="3"><input class="inputxt" value="${activity.imagePath }"
                                                 id="imagePath" style="width: 150px" name="imagePath"
                                                 ignore="ignore" type="text"> 自定义图片文件夹名，<span style="color: red;">请提前告知相关人员上传图片</span></td>
        </tr>
        <tr><td colspan="2">
        <table id="tableRankRule" style="width: 300px;" cellpadding="0" cellspacing="1" class="formtable">
         <tr><th>名次</th><th>流量值(M)</th></tr>
        </table>
        <br/>
        <c:if test="${code == 1 }">
        <a class="anniu" href="javascript:addRankRow('','');">添加名次奖</a><a class="anniu" href="javascript:deleteRankRow('','');">删除名次奖</a>
        </c:if>
        </td>
        <td colspan="2">
        <table id="tableGradeRule" style="width: 300px;" cellpadding="0" cellspacing="1" class="formtable">
         <tr><th>分值</th><th>流量值(M)</th></tr>
        </table>
        <br/>
        <c:if test="${code == 1 }">
        <a class="anniu" href="javascript:addGradeRow('','');">添加分值奖</a><a class="anniu" href="javascript:deleteGradeRow();">删除分值奖</a>
        </c:if>
        </td>
        </tr>
        </table >
</t:formvalid>
</body>
</html>