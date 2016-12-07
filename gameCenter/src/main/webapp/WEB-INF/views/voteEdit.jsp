<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link id="skin" rel="stylesheet" href="${pageContext.request.contextPath}/css/jbox/jbox.css"/>
<link id="skin" rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"/>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-migrate-1.4.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jbox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jbox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<meta content="yes" name="apple-mobile-web-app-capable"></meta>
<meta content="black" name="apple-mobile-web-app-status-bar-style"></meta>
<meta name="format-detection" content="telephone=no"></meta>
<meta content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport"></meta>
<meta content="yes" name="full-screen"></meta>
<meta content="true" name="x5-fullscreen"></meta>
<meta content="application" name="browsermode"></meta>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">

<title>投票编辑</title>

<script type="text/javascript">
$(document).ready(
		function() {
			var a = '<c:if test="${vote.voteName != null }">';
			$('#voteName').val('${vote.voteName}');
			$('#frequency').val('${vote.frequency}');
			$('#times').val('${vote.times}');
			$('#flowType').val('${vote.flowType}');
			$('#voteNum').val('${vote.voteNum}');
			$('#voteFlow').val('${vote.voteFlow}');
			$('#voteEndTime').val('${vote.voteEndTimeF}');
			$('#voteText').val('${vote.text}');
			$('#votelocalImag').html("<img alt=\"image\" width=\"80px\" height=\"80px\" id=\"voteImg\" src=\"${pageContext.request.contextPath}/${vote.imgUrl}\"/>");
			var b = '</c:if>';
		});
	
	function saveVote() {
		var formData = new FormData($("#editVoteFrom")[0]);
		$.ajax({
			url : '${pageContext.request.contextPath}/vote/save.htm',
			type : 'POST',
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(returndata) {
				$.jBox.tip('保存成功。', 'success');
			},
			error : function(returndata) {
				$.jBox.tip('保存失败。', 'error');
			} 
		});
	}
	function setImagePreview(str) {
		var docObj = document.getElementById(str + "Url");
		var imgObjPreview = document.getElementById(str + "Img");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '80px';
			imgObjPreview.style.height = '80px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();

			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式  
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);

		} else {
			//IE下，使用滤镜
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById(str+"localImag");
			//必须设置初始大小
			localImagId.style.width = '80px';
			localImagId.style.height = '80px';
			//图片异常的捕捉，防止用户修改后缀来伪造图片
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}
	
	function createTable(data){
		options = $.parseJSON(data);
		var str = "";
		for(var i = 0; i < options.length;i++){
			str += "<tr higth='50px'><td>"+options[i].orderId+"</td><td width='500px'><div class=\"testStyle\">"+options[i].text+"</div></td>"
				+"<td class=\"preview\"><img  width=50 height=50 alt='图片' src='${pageContext.request.contextPath}/"+options[i].imgUrl+"'/></td><td>"+options[i].voteNum+"</td>"
				+"<td><a class=\"btn_edit\" href=\"javascript:editOption('"+options[i].optionId +"','"+options[i].text+"','"+options[i].orderId +"','"+options[i].imgUrl +"')\">修改</a>"
				+"<a class=\"btn_edit\" href=\"javascript:deleteOption('"+options[i].optionId +"');\">删除</a></td></tr>" ;
		}
		$("#optionsMessage").html(str);
		$('#showImage').html('');
		for(var i = 0; i < options.length;i++){
			$('#showImage').append("<div class=\"preview_box\"><img src=\"${pageContext.request.contextPath}/"+options[i].imgUrl+"\"/></div>");
		}
		$('.preview').each(function(i){
			$('.preview').eq(i).hover(function(){
				$('.preview_box').eq(i).show();
			},function(){
				$('.preview_box').eq(i).hide();
			})
		})
	}
	
	function saveOption(){
		var formData = new FormData($("#editOptionFrom")[0]);
		$.ajax({
			url : '${pageContext.request.contextPath}/vote/saveoption.htm',
			type : 'POST',
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(returndata) {
				$.jBox.tip('保存成功。', 'success');
				createTable(returndata);
				closeDiv();
			},
			error : function(returndata) {
				$.jBox.tip('保存失败。', 'error');
				closeDiv();
			}
		});
	}
	
	function editOption(optionId,text,orderId,imgUrl){
		var width = $(document).width();
		var height = $(document).height();
		$('#locktab').width(width).height(height).css('display','block');
		/*var mask='<div id="locktab"></div>';
		$('#locktab').width(width).height(height);
		$('body').append(mask);*/
		$('#optionId').val(optionId);
		$('#orderId').val(orderId);
		$('#optionText').val(text);
		$('#optionUrl').val("");
		
		if(imgUrl != null && imgUrl != '')
			$('#optionlocalImag').html("<img alt=\"image\" width=\"80px\" height=\"80px\" id=\"optionImg\" src=\"${pageContext.request.contextPath}/"+imgUrl+"\"/>");
		else
			$('#optionlocalImag').html('<img alt="图片" width=80 height=80 style="diplay: none" id="voteImg"/>');
		var screenTop=document.documentElement.scrollTop||document.body.scrollTop;
		var clentLeft=($(window).width()-$('#editOption').outerWidth(true))/2;
		var clentTop=($(window).height()-$('#editOption').outerHeight(true))/2+screenTop;
		$('#editOption').css({
			'left':clentLeft+'px',
			'top':clentTop+'px'
		}).slideDown(400);
	}
	function closeDiv(){
		$('#editOption').css('display','none');
		$('#locktab').hide();
	}
	
	function deleteOption(optionId){
		var submit = function (v, h, f) {
		    if (v == 'ok'){
		 		$.jBox.tip("正在删除数据...", 'loading');
		        jQuery.ajax({
		    		url:'${pageContext.request.contextPath}/vote/deleteoption.htm',
		    		data:{'optionId':optionId,'voteId':'${vote.voteId}'},
		    		dataType:'json',
		    	 	type:'post',
		    	 	contentType: "application/x-www-form-urlencoded; charset=utf-8",
		    	 	success:function(data){
			    	 	if(data==0){
			    	 		$.jBox.tip('删除失败,请稍候再试。', 'error');
			    	 	}else{
			    	 		$.jBox.tip('删除成功。', 'success');
			    	 		createTable(data);
			    	 	}
			        }
				});
		    }
		    return true; //close
		};
	    $.jBox.confirm("你确定要该选项？", "提示", submit);
	}
</script>

<style>
body {
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none; /*IE10*/
	-khtml-user-select: none; /*早期浏览器*/
	user-select: none;
	-webkit-tap-highlight-color: rgba(255, 255, 255, 0);
	-webkit-focus-ring-color: rgba(0, 0, 0, 0);
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	padding: 0;
	margin: 0;
	position: relative;
	font-family: "微软雅黑";
	background: #F8F8F8;
}

img {
	border: medium none;
	vertical-align: middle;
}

* {
	margin: 0;
	padding: 0;
}

ul {
	list-style: outside none none;
}

li {
	list-style-type: none;
}

a {
	color: #333;
	text-decoration: none;
}
td,th{padding:6px 6px;}
.section{padding-left:30px;margin-top:20px;}
.datalist{width:900px;height:320px;border-collapse:collapse;margin:0 auto;border:rgb(200,200,200) 1px solid;}
.edit_dataTab{width:740px;height:320px;border-collapse:collapse;margin:0 auto;}
.datalist th{font-size:15px;text-align:center;}
.datalist td{padding-left:16px;color:#333}
.datalist td input{height:20px;padding-left:5px;width:280px;}
.vote_option{width:100px;border-bottom:blue 2px solid;margin:0 auto;padding-bottom:5px;text-align:center;}
.Tab_vote{width:900px;text-align:center;font-size:15px;border-collapse: collapse;margin:8px auto 30px auto;border:rgb(180,180,180) 1px solid;}
.anniu{width:100px;height:40px;padding:6px 11px;font-size:14px;margin-right:10px;border-radius:5px;background:#1E97E5;color:white;}
.btn_edit{margin-right:6px;color:blue;}
.btn_addOption{margin-left:210px;}
.btn_edit:hover{color:black;text-decoration:underline;}
.preview_box{width:320px;height:240px;border:#ccc 1px solid;box-shadow:2px 1px 8px #999;cursor:pointer;position:fixed;top:60%;left:40%;background:white;z-index:589;display:none;}
.preview_box img{width:320px;height:240px;}
#editOption{position:absolute;display: none;border-radius:6px;background:white;z-index:999;box-shadow:2px 1px 10px black;padding:20px;}
#locktab{position:absolute;left:0;top:0;background:rgba(0,0,0,0.4);z-index:100;display:none;}
.closeDiv{width:30px;height:30px;position:absolute;line-height:30px;border-radius:30px;top:10px;right:10px;background:red;}
.testStyle{width:100%;height:60px;overflow-x:hidden;overflow-y:scroll;}
</style>
</head>
<body>

	<div class="section">
		<form id="editVoteFrom" enctype="multipart/form-data">
			<input type="hidden" name='voteId' id="voteId" value="${vote.voteId }" />
			<input type="hidden" name='type' id="type" value="${vote.type }"/>
			<h4 class="vote_option">活动编辑</h4>
			<table class="datalist" border="1">
				<tr>
					<th>投票名称：</th>
					<td><input type="text" id="voteName" name="voteName" /></td>
				</tr>
				<tr>
					<th>周期：</th>
					<td>
						<select id="frequency" name="frequency">
							<option selected="selected" value="0">无</option>
							<option value="1">每天</option>
							<option value="2">每周</option>
							<option value="3">每月</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>次数：</th>
					<td><input type="text" id="times" name="times" /></td>
				</tr>
				<tr>
					<th>每次可投票数：</th>
					<td><input type="text" id="voteNum" name="voteNum" /></td>
				</tr>
				<tr>
					<th>每次投票获得流量(M)：</th>
					<td><input type="text" id="voteFlow" name="voteFlow" /></td>
				</tr>
				<tr>
					<th>投票结束时间：</th>
					<td><input type="text" id="voteEndTime" name="voteEndTime" class="sang_Calender"/></td>
				</tr>
				
				<tr>
					<th>流量类型：</th>
					<td>
						<select id="flowType" name="flowType">
							<option selected="selected" value="national">全国</option>
							<option value="provincial">省内</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>投票说明：</th>
					<td><textarea cols="45" rows="8" id="voteText" name="text" ></textarea></td>
				</tr>
				<tr>
					<th>上传图片:(建议640px*250px)</th>
					<td>
						<input type="file" id="voteUrl" name="file" onchange="setImagePreview('vote')" />
					</td>
				</tr>
				<tr>
					<th>当前图片：</th>
					<td>
						<div id="votelocalImag">
							<img alt="image" width=10 height=10 style="diplay: none" id="voteImg"/>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" height="44px">
						<a class="anniu" href="javascript:saveVote();">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="section">
		<h4 class="vote_option">投票项</h4>
		<a class="anniu btn_addOption" href="javascript:editOption()">新增选项</a>
		<table border="1" class="Tab_vote">
			<thead>
			<tr>
				<th>显示序号</th>
				<th>内容</th>
				<th>图片</th>
				<th>当前票数</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody id="optionsMessage">
			<c:forEach items="${options}" var="option">
				<tr height="50px">
					<td>${option.orderId }</td>
					<td width="500px"><div class="testStyle">${option.text}</div></td>

					<td class="preview"><img  width=50 height=50 alt="图片" src="${pageContext.request.contextPath}/${option.imgUrl}"/></td>

					<td>${option.voteNum}</td>
					<td>
						<a class="btn_edit" href="javascript:editOption('${option.optionId }','${option.text}','${option.orderId }','${option.imgUrl }')">修改</a>
						<a class="btn_edit" href="javascript:deleteOption('${option.optionId }');">删除</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div id = "showImage">
		<c:forEach items="${options}" var="option">
		<div class="preview_box"><img src="${pageContext.request.contextPath}/${option.imgUrl}"/></div>
		</c:forEach>
		</div>
		<script type="text/javascript">
	
			
				$('.preview').each(function(i){
					$('.preview').eq(i).hover(function(){
						$('.preview_box').eq(i).show();
					},function(){
						$('.preview_box').eq(i).hide();
					})
				})
		</script>
	</div>
	<div id="locktab"></div>
	<div id = "editOption" align="center">
		<div class="closeDiv"><a style="color:white;" href="javascript:closeDiv();">X</a></div>
		<div class = "editBody">
			<form id="editOptionFrom" enctype="multipart/form-data">
				<input type="hidden" name='optionId' id="optionId" />
				<input type="hidden" name='voteId' id="voteId" value="${vote.voteId}"/>
				<table class="edit_dataTab">
					<tr>
						<th>选项内容：</th>
						<td>
							<textarea cols="45" rows="8" id="optionText" name="text"></textarea>
						</td>
					</tr>
					<tr>
						<th>显示序号：</th>
						<td>
							<input type="text" id="orderId" name='orderId' style="height:20px;"/>
						</td>
					</tr>
					<tr>
						<th>上传选项图片:</th>
						<td>
							<input type="file" id = "optionUrl" name="file" onchange="setImagePreview('option')"/>
						</td>
					</tr>
					<tr>
						<th>当前图片：</th>
						<td>
							<div id="optionlocalImag"><img alt="图片" width=40 height=40 style="diplay:none" id="optionImg"></img></div>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<a class="anniu" href="javascript:closeDiv();">取消</a>
							<a class="anniu" href="javascript:saveOption();">保存</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datetime.js"></script>
</body>
</html>