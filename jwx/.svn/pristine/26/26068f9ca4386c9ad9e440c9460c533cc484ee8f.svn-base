<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>添加手动赠送</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link type="text/css" rel="stylesheet"
	href="plug-in/weixin/css/appmsg_edit.css" />
<link type="text/css" rel="stylesheet"
	href="plug-in/weixin/css/jquery.fileupload.css" />
<link type="text/css" rel="stylesheet"
	href="plug-in/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>

<!--fileupload-->
<script type="text/javascript"
	src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="plug-in/weixin/js/load-image.min.js"></script>
<script type="text/javascript"
	src="plug-in/weixin/js/jquery.fileupload.js"></script>
<script type="text/javascript"
	src="plug-in/weixin/js/jquery.fileupload-process.js"></script>
<script type="text/javascript"
	src="plug-in/weixin/js/jquery.fileupload-image.js"></script>
<script type="text/javascript"
	src="plug-in/weixin/js/jquery.iframe-transport.js"></script>
<!--UEditor-->
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/ueditor.all.min.js"></script>

<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>


<script type="text/javascript">
	//编写自定义JS代码
	/*jslint unparam: true, regexp: true */
	/*global window, $ */
	$(function() {
		'use strict';
		// Change this to the location of your server-side upload handler:
		//            var url = 'weixinArticleController.do?uploadthumbMike',
		var url = 'manualGivenController.do?uploadtxt', uploadButton = $(
				'<a/>').addClass('btn btn-primary').prop('disabled', true)
				.text('上传').on('click', function() {
					var $this = $(this), data = $this.data();
					$this.off('click').text('正在上传...').on('click', function() {
						$this.remove();
						data.abort();
					});
					data.submit().always(function() {
						$this.remove();
					});
				});
		//            上传的是企业的二维码
		$('#fileupload')
				.fileupload(
						{
							url : url,
							dataType : 'json',
							autoUpload : false,
							acceptFileTypes : /(\.|\/)(txt)$/i,
							maxFileSize : 2000000, // 2 MB
							disableImageResize : /Android(?!.*Chrome)|Opera/
									.test(window.navigator.userAgent),
							previewMaxWidth : 150,
							previewMaxHeight : 150,
							previewCrop : true
						})
				.on(
						'fileuploadadd',
						function(e, data) {
							$("#files").text("");
							data.context = $('<div/>').appendTo('#files');
							$.each(data.files, function(index, file) {
								//var node = $('<p/>').append($('<span/>').text(file.name));
								//fileupload
								var node = $('<p/>');
								if (!index) {
									node.append('<br>')
											.append(
													uploadButton.clone(true)
															.data(data));
								}
								node.appendTo(data.context);
							});
						})
				.on(
						'fileuploadprocessalways',
						function(e, data) {
							var index = data.index, file = data.files[index], node = $(data.context
									.children()[index]);
							if (file.preview) {
								node.prepend('<br>').prepend(file.preview);
							}
							if (file.error) {
								node.append('<br>').append(
										$('<span class="text-danger"/>').text(
												file.error));
							}
							if (index + 1 === data.files.length) {
								data.context.find('button').text('上传').prop(
										'disabled', !!data.files.error);
							}
						}).on(
						'fileuploadprogressall',
						function(e, data) {
							var progress = parseInt(data.loaded / data.total
									* 100, 10);
							$('#progress .progress-bar').css('width',
									progress + '%');
						}).on(
						'fileuploaddone',
						function(e, data) {
							//console.info(data);
							var file = data.files[0];
							//var delUrl = "<a class=\"js_removeCover\" onclick=\"return false;\" href=\"javascript:void(0);\">删除</a>";
							$("#imgName").text("").append(file.name);
							$("#progress").hide();
							var d = data.result;
							if (d.success) {
								var link = $('<a>').attr('target', '_blank')
										.prop('href', d.attributes.viewhref);
								$(data.context.children()[0]).wrap(link);
								console.info(d.attributes.imagePath);
								$("#txtPath").val(d.attributes.txtPath);
								$("#txtRelativeUrl").val(
										d.attributes.txtRelativeUrl);
							} else {
								var error = $('<span class="text-danger"/>')
										.text(d.msg);
								$(data.context.children()[0]).append('<br>')
										.append(error);
							}
						}).on(
						'fileuploadfail',
						function(e, data) {
							$.each(data.files, function(index, file) {
								var error = $('<span class="text-danger"/>')
										.text('File upload failed.');
								$(data.context.children()[index])
										.append('<br>').append(error);
							});
						}).prop('disabled', !$.support.fileInput).parent()
				.addClass($.support.fileInput ? undefined : 'disabled');
		//            上传的是企业logo
		$('#fileupload1')
				.fileupload(
						{
							url : url,
							dataType : 'json',
							autoUpload : false,
							acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
							maxFileSize : 2000000, // 2 MB
							disableImageResize : /Android(?!.*Chrome)|Opera/
									.test(window.navigator.userAgent),
							previewMaxWidth : 150,
							previewMaxHeight : 150,
							previewCrop : true
						})
				.on(
						'fileuploadadd',
						function(e, data) {
							$("#files1").text("");
							data.context = $('<div/>').appendTo('#files1');
							$.each(data.files, function(index, file) {
								//var node = $('<p/>').append($('<span/>').text(file.name));
								//fileupload
								var node = $('<p/>');
								if (!index) {
									node.append('<br>')
											.append(
													uploadButton.clone(true)
															.data(data));
								}
								node.appendTo(data.context);
							});
						})
				.on(
						'fileuploadprocessalways',
						function(e, data) {
							var index = data.index, file = data.files[index], node = $(data.context
									.children()[index]);
							if (file.preview) {
								node.prepend('<br>').prepend(file.preview);
							}
							if (file.error) {
								node.append('<br>').append(
										$('<span class="text-danger"/>').text(
												file.error));
							}
							if (index + 1 === data.files.length) {
								data.context.find('button').text('上传').prop(
										'disabled', !!data.files.error);
							}
						}).on(
						'fileuploadprogressall',
						function(e, data) {
							var progress = parseInt(data.loaded / data.total
									* 100, 10);
							$('#progress1 .progress-bar').css('width',
									progress + '%');
						}).on(
						'fileuploaddone',
						function(e, data) {
							//console.info(data);
							var file = data.files[0];
							//var delUrl = "<a class=\"js_removeCover\" onclick=\"return false;\" href=\"javascript:void(0);\">删除</a>";
							$("#imgName1").text("").append(file.name);
							$("#progress1").hide();
							var d = data.result;
							if (d.success) {
								var link = $('<a>').attr('target', '_blank')
										.prop('href', d.attributes.viewhref);
								$(data.context.children()[0]).wrap(link);
								console.info(d.attributes.imagePath);
								$("#txtPath1").val(d.attributes.imagePath);
								$("#txtRelativeUrl1").val(
										d.attributes.imageRelativeUrl);
							} else {
								var error = $('<span class="text-danger"/>')
										.text(d.msg);
								$(data.context.children()[0]).append('<br>')
										.append(error);
							}
						}).on(
						'fileuploadfail',
						function(e, data) {
							$.each(data.files, function(index, file) {
								var error = $('<span class="text-danger"/>')
										.text('File upload failed.');
								$(data.context.children()[index])
										.append('<br>').append(error);
							});
						}).prop('disabled', !$.support.fileInput).parent()
				.addClass($.support.fileInput ? undefined : 'disabled');
	});
	
	//获取动作设置选中的项,用于初始化依赖dom元素
	// 动作设置选中项的值	
	setTimeout(function() {
		$("#type").on('change', function() {
			if("handgift" == $(this).val()){
				$("#sgzs").show();
				$("#wbzs").hide();
				$("#sdphone").attr("datatype", "*");
				 $("#txtPath").removeAttr("datatype");
			}else{
				$("#sgzs").hide();
				$("#wbzs").show();
				$("#txtPath").attr("datatype", "*");
				 $("#sdphone").removeAttr("datatype");
			}
		});
	}, 0);

	function setreserve(){
		 $("#alls").attr("style","display:none");
		}
		function setimmediate(){
		$("#alls").removeAttr("style");
		}
		
		 if($("#type1").val()=='reserve'){
	  		 setreserve();
		  }else{
			  setimmediate();
		  }
		//获取动作设置选中的项,用于初始化依赖dom元素
		// 动作设置选中项的值
		
		setTimeout(function() {
			$("#type1").on('change', function() {
				if("reserve" == $(this).val()){
					$("#alls").show();
				}else{
					$("#alls").hide();
				}
			});
		}, 0);
		function ckNum(obj,n){ // 值允许输入一个小数点和数字 
			obj.value = obj.value.replace(/[^\d.]/g,""); //先把非数字的都替换掉，除了数字和. 
			obj.value = obj.value.replace(/^\./g,""); //必须保证第一个为数字而不是. 
			obj.value = obj.value.replace(/\.{2,}/g,"."); //保证只有出现一个.而没有多个. 
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
			var dotIdx = obj.value.indexOf('.'), dotLeft, dotRight;
			if (dotIdx >= 0) {
		        dotLeft = obj.value.substring(0, dotIdx);
		        dotRight = obj.value.substring(dotIdx + 1);
		        if (dotRight.length > n) {
		            dotRight = dotRight.substring(0, n);
		        }
		        obj.value = dotLeft + '.' + dotRight; 
		    }
			}
	
</script>
</head>
<body>
    
	<div class="main_bd">
		<div class="media_edit_area" id="js_appmsg_editor">
			<div class="appmsg_editor" style="margin-top: 0px;">
				<div class="inner">
					<t:formvalid formid="formobj" dialog="true" usePlugin="password"
						layout="table" action="manualGivenController.do?doManualGiven"
						tiptype="1">
						<table style="width:100%" cellpadding="0" cellspacing="1"
							class="formtable">
							<tr>
								<td align="right" style="width:65px">
									<label class="Validform_label">赠送描述: </label>
								</td>
								<td class="value">
								    <input id="describe" name="describe" type="text" style="width: 250px" class="inputxt" maxlength="5" datatype="*">
									<span class="Validform_checktip1" style="color: #999">5个字符以内</span>
									<label class="Validform_label" style="display: none;">赠送描述</label>
								</td>
							</tr>
							<tr>
								<td align="right" style="width:65px">
									<label class="Validform_label">流量类型: </label>
								</td>
								<td>
									<select name="flowType" id="flowType">
								      		<option value="1"  >全国流量</option>
								      		<option value="2" >省内流量</option>	
								      </select>
							      </td>
							</tr>
							<tr>
								<td align="right" style="width:65px">
									<label class="Validform_label">赠送流量值: </label>
								</td>
								<td class="value">
									<input id="flowValue" name="flowValue" type="text" style="width: 250px" onkeyup ="ckNum(this,1)" datatype="*">M 
									<span class="Validform_checktip1" style="color: #999">最小值为0.1,精确到0.1</span>
									<label class="Validform_label" style="display: none;">赠送流量值</label>
								</td>
							</tr>
							
	<tr>
     <td align="right" style="width:65px">
      <label class="Validform_label">
      赠送方式:
      </label>
     </td>
     <td colspan="3" class="value">
      <select name="giveType" id="type">
      	<option value="handgift"  <c:if test="${type=='handgift'}">selected="selected"</c:if>>手动输入</option>
      	<option value="textgift"  <c:if test="${type=='textgift'}">selected="selected"</c:if>>文本导入</option>
      </select>
      <span class="Validform_checktip">请设置动作</span>
     </td>
    </tr>
    
   							<tr id="sgzs">
								<td align="right" style="width:65px">
									<label class="Validform_label">手动输入: </label>
								</td>
								<td class="value">
								    <!-- <input id="phoneNum" name="phoneNum" type="text" style="width: 250px" class="inputxt"><br>  -->
								   <textarea id="sdphone" class="sgzs" placeholder='请输入手机号,多个手机号之间用英文","分隔' name="phoneNum"  datatype="*"></textarea>
									<span class="Validform_checktip1" style="color: #999"></span>
									<label class="Validform_label" style="display: none;">手动输入</label>
								</td>
							</tr>
    
							 <tr id="wbzs" style="display: none;">
								<td align="right" style="width:65px">
									<label class="Validform_label">赠送手机号: </label>
								</td>
								<td class="value">
									<div>
										<div class="appmsg_content" id="js_appmsg_preview">
											<div class="appmsg_info">
												<em class="appmsg_date"></em>
											</div>
											<div id="files" class="files">
												<i class="appmsg_thumb1"></i>
											</div>
											<div id="progress" class="progress">
												<div class="progress-bar progress-bar-success"></div>
											</div>
										</div>
									</div> <br>

									<div>
										<span class="btn btn-success fileinput-button"> 
											<i class="glyphicon glyphicon-plus"></i> 
											<span>浏览</span> 
											<!-- The file input field used as target for the file upload widget -->
											<input id="fileupload" type="file" name="files[]" multiple>
											<input id="txtPath" name=txtPath type="hidden"> 
											<input id="txtRelativeUrl" name=txtRelativeUrl type="hidden">
										</span> 
										<span id="imgName"></span> 
										<span class="Validform_checktip"></span>
										<label class="Validform_label" style="display: none;">文件链接</label>
									</div> <br>
									<span class="Validform_checktip1" style="color: #999">只能选择txt文本文档，且每行一个手机号</span>
								</td>
							</tr>
							
							<tr>
							     <td align="right" style="width:65px">
							      <label class="Validform_label">
							      赠送时间:
							      </label>
							     </td>
							     <td colspan="3" class="value">
							      <select name="giveTime" id="type1">
							      	<option value="immediate"  <c:if test="${type1=='immediate'}">selected="selected"</c:if>>立即赠送</option>
							      	<option value="reserve"  <c:if test="${type1=='reserve'}">selected="selected"</c:if>>预定时间</option>
							      </select>
							      <span class="Validform_checktip">请设置动作</span>
							     </td>
							    </tr>
										
										<tr id="alls" style="display: none;">
							            <td align="right"><label class="Validform_label"> 预定时间:
							            </label></td>
							            <td class="value">
							                    	<input  id="scheduledTime" class="Wdate" type="text" 
							                                    onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							                                    style="width: 150px" name="scheduledTime"
							                                    >
							                		<span class="Validform_checktip"></span>
							                </td>
							       	</tr>
							   	<tr>
						</table>
					</t:formvalid>
				</div>
				<i class="arrow arrow_out" style="margin-top: 0px;"></i> <i
					class="arrow arrow_in" style="margin-top: 0px;"></i>
			</div>
		</div>
	</div>
</body>
<script src="webpage/weixin/source/weixinSource.js"></script>