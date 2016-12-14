<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<title>微信视频消息</title>
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
	$(function() {
		var type = "video";
		var destUrl = 'groupMessageNewsTemplateController.do?uploadMV&type='
				+ type;
		$("#fileupload").fileupload({
			url : destUrl,
			autoUpload : true,//是否自动上传  
			dataType : 'json',
			done : function(e, data) {
				var file = data.files[0];
				var delUrl = "<a class=\"js_removeCover\" onclick=\"return false;\" href=\"javascript:void(0);\">删除</a>";
				$("#imgName").text("").append(file.name);
				$("#progress").hide();
				var d = data.result;
				if (d.success) {
					
					//进度条关闭
					$.messager.progress('close');
					tip(d.msg);
					
					var link = $('<a>').attr('target', '_blank').prop(
							'href', d.attributes.viewhref);
					
					$("#imagePath").val(d.attributes.url);
					$("#media_id").val(d.attributes.media_id);
				} else {
					var error = $('<span class="text-danger"/>')
							.text(d.msg);
					$(data.context.children()[0]).append('<br>').append(
							error);
				}
			},
			progressall : function(e, data) {
				
				//tip("文件上传中，请耐心等待...");
				//进度条
				$.messager.progress();
				
				var progress = parseInt(data.loaded / data.total * 5, 10);
			  	$('#progress .progress-bar').css(
			            'width',
			            progress + '%'
			        );
			}
		}).on(
			'fileuploadfail',
			function(e, data) {
				$.each(data.files, function(index, file) {
					var error = $('<span class="text-danger"/>').text(
							'File upload failed.');
					$(data.context.children()[index]).append('<br>')
							.append(error);
				});
			}).prop('disabled', !$.support.fileInput).parent().addClass(
			$.support.fileInput ? undefined : 'disabled');
		;
	});

	function getCheckedNews() {
		var media_id = $("#media_id").val();
		var imagePath = $("#imagePath").val();
		var result = [ media_id, imagePath ];
		return result;
	}
</script>

</head>
<body>
	<div class="main_bd">
		<div class="media_edit_area" id="js_appmsg_editor">
			<div class="appmsg_editor" style="margin-top: 0px">
				<div class="inner" style="width: 400px">
					<form id="formobj"
						action="groupMessageNewsController.do?weixinDoAdd" name="formobj"
						method="post">
						<input type="hidden" id="btn_sub" class="btn_sub" /> <input
							type="hidden" name="media_id" id="media_id" />
						<table style="width: 400px;" cellpadding="0" cellspacing="1"
							class="formtable">
							<tr>
								<td align="right" width="100px"><label class="Validform_label">
										上传视频文件 : </label></td>
								<td class="value"><span
									class="btn btn-success fileinput-button"> <i
										class="glyphicon glyphicon-plus"></i> <span>浏览</span> <!-- The file input field used as target for the file upload widget -->
										<input id="fileupload" type="file" name="files[]" multiple>
										<input id="imagePath" name=imagePath type="hidden"
										datatype="*" nullmsg="请添加音频文件">
								</span> <span id="imgName"></span> <span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">视频文件
								</label></td>
							</tr>
							<tr>
								<td></td>
								<td>(上传视频文件有格式和大小限制: 10MB，支持MP4格式)</td>
							</tr>
						</table>

					</form>
				</div>

			</div>
		</div>
	</div>
</body>