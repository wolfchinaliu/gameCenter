<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<title>新增广告素材</title>
<t:base type="jquery,easyui,tools"></t:base>
<link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
<link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css" />
<link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="plug-in/weixin/button.css" />
<!--     <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script> -->

<!--fileupload-->
<script type="text/javascript" src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="plug-in/weixin/js/load-image.min.js"></script>
<script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-process.js"></script>
<script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-image.js"></script>
<script type="text/javascript" src="plug-in/weixin/js/jquery.iframe-transport.js"></script>
<!--UEditor-->
<script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="plug-in/jquery-plugs/form/jquery.form.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">

        $(function () {
            'use strict';
            // Change this to the location of your server-side upload handler:
            var url = 'commonController.do?upload',
                    uploadButton = $('<button/>')
                            .addClass('btn btn-primary')
                            .prop('disabled', true)
                            .text('上传中...')
                            .on('click', function () {
                                var $this = $(this), data = $this.data();
                                $this.off('click').text('正在上传...').on('click', function () {
                                    $this.remove();
                                    data.abort();
                                });
                                data.submit().always(function () {
                                    $this.remove();
                                });
                            });
            $('#fileupload').fileupload({
                url: url,
                dataType: 'json',
                autoUpload: false,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                maxFileSize: 2000000, // 2 MB
                // Enable image resizing, except for Android and Opera,
                // which actually support image resizing, but fail to
                // send Blob objects via XHR requests:
                disableImageResize: /Android(?!.*Chrome)|Opera/
                        .test(window.navigator.userAgent),
                previewMaxWidth: 290,
                previewMaxHeight: 160,
                previewCrop: true
            }).on('fileuploadadd', function (e, data) {

                $("#files").text("");
                data.context = $('<div/>').appendTo('#files');
                $.each(data.files, function (index, file) {
                    //var node = $('<p/>').append($('<span/>').text(file.name));
                    //fileupload
                    var node = $('<p/>');
                    if (!index) {
                        node.append('<br>').append(uploadButton.clone(true).data(data));
                    }
                    node.appendTo(data.context);
                });
            }).on('fileuploadprocessalways', function (e, data) {

                var index = data.index,
                        file = data.files[index],
                        node = $(data.context.children()[index]);

                if (file.preview) {
                    node.prepend('<br>').prepend(file.preview);
                }

                if (file.error) {
                    node
                            .append('<br>')
                            .append($('<span class="text-danger"/>').text(file.error));
                }

                if (index + 1 === data.files.length) {
                    data.context.find('button')
                            .text('上传')
                            .prop('disabled', !!data.files.error);
                }
            }).on('fileuploadprogressall', function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css(
                        'width',
                        progress + '%'
                );
            }).on('fileuploaddone', function (e, data) {
                //console.info(data);
                var file = data.files[0];
                //var delUrl = "<a class=\"js_removeCover\" onclick=\"return false;\" href=\"javascript:void(0);\">删除</a>";
                $("#imgName").text("").append(file.name);
                $("#progress").hide();
                var d = data.result;
                if (d.success) {
                    var link = $('<a>').attr('target', '_blank').prop('href', d.attributes.viewhref);
                    $(data.context.children()[0]).wrap(link);
                    //console.info(d.attributes.viewhref);
                    $("#pic").val(d.attributes.url);
                } else {
                    var error = $('<span class="text-danger"/>').text(d.msg);
                    $(data.context.children()[0]).append('<br>').append(error);
                }
            }).on('fileuploadfail', function (e, data) {
                $.each(data.files, function (index, file) {
                    var error = $('<span class="text-danger"/>').text('File upload failed.');
                    $(data.context.children()[index])
                            .append('<br>')
                            .append(error);
                });
            }).prop('disabled', !$.support.fileInput)
                    .parent().addClass($.support.fileInput ? undefined : 'disabled');
            $('#urlType').combobox({
        		url: 'dataDict.do?urlType',
        		editable: false, //不可编辑状态
        		cache: false,
        		valueField: 'code',
        		textField: 'name',
        		resizable:true,
        		onSelect: function () {
        			var urlType = $('#urlType').combobox('getValue');
        			if(urlType=="inner"){
        				$("#inner").css("display","block");
        				//$("#innerLabel").css("display","block");
        				$("#outer").css("display","none");
        				$("#outerLabel").css("display","none");
        				return;
        			}
        			if(urlType=="outer"){
        				$("#inner").css("display","none");
        				//$("#innerLabel").css("display","none");
        				$("#outer").css("display","block");
        				$("#outerLabel").css("display","block");
        				return
        			}
        		}
        	});
            $('#materialType').combobox({
        		url: 'dataDict.do?materialType',
        		editable: false, //不可编辑状态
        		cache: false,
        		valueField: 'code',
        		textField: 'name',
        		resizable:true
        	});
        $("#materialType").combobox("setValue", "${material.materialType}");
        $("#urlType").combobox("setValue", "${material.urlType}");

		var urlType = $('#urlType').combobox('getValue');
		if(urlType=="inner"){
			$("#inner").css("display","block");
			$("#outer").css("display","none");
		}
		if(urlType=="outer"){
			$("#inner").css("display","none");
			$("#outer").css("display","block");
		}

        var editor = UE.getEditor('content');
        });
    </script>
</head>
<body>
  <div style="display: none;"></div>
  <div class="main_bd">
    <div class="media_preview_area">
      <div class="appmsg editing">
        <div class="appmsg_content" id="js_appmsg_preview">
          <h4 class="appmsg_title">
            <a target="_blank" href="javascript:void(0);" onclick="return false;"></a>
          </h4>
          <div class="appmsg_info">
            <em class="appmsg_date"></em>
          </div>
          <div id="files" class="files">
            <i style="overflow: hidden; display: inline-block; width: 100%;"><img src="${mediaUrlPrefix }/${material.pic}" width="100%" height="160" /></i>
          </div>
          <div id="progress" class="progress">
            <div class="progress-bar progress-bar-success"></div>
          </div>
          <p class="appmsg_desc"></p>
        </div>
      </div>
    </div>
    <div class="media_edit_area" id="js_appmsg_editor">
      <div class="appmsg_editor" style="margin-top: 0px;">
        <div class="inner">
          <t:formvalid formid="formobjNew" dialog="true" usePlugin="password" layout="table" action="materialController.do?addOrUpdate"
            tiptype="1">
            <input id="id" name="id" type="hidden" value="${material.id }">
            <table style="width: 450px;" cellpadding="0" cellspacing="1" class="formtable">
              <tr>
                <td align="right"><label class="Validform_label">素材名称:</label></td>
                <td class="value"><input id="title" name="title" type="text" value="${material.title }" style="width: 300px;height:30px;" class="inputxt" datatype="*"
                    nullmsg="素材名称不能为空！"> <span class="Validform_checktip"></span> <label class="Validform_label"
                    style="display: none;">素材名称</label></td>
              </tr>
              <td align="right"><label class="Validform_label"> 素材类型: </label></td>
              <td class="value"><input class="inputxt" id="materialType" name="materialType" style="width: 305px; height: 30px;"
                  value=""> <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;">素材类型</label></td>
              <tr>
                <td align="right"><label class="Validform_label"> 图片链接: </label></td>
                <td class="value"><span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span>浏览</span>
                    <input id="fileupload" type="file" name="files[]" multiple> <input id="pic" name="pic" type="hidden"
                      value="${material.pic}" datatype="*" nullmsg="请添加图片">
                </span> <span id="imgName"></span> <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;">图片链接</label>
                </td>
              </tr>
              <tr>
                <td></td>
                <td class="value"><label>建议图片大小为200*200像素, 六角拼拼为300*70</label></td>
              </tr>
              <tr>
                <td align="right"><label class="Validform_label"> 跳转类型: </label></td>
                <td class="value"><input class="inputxt" id="urlType" name="urlType" style="width: 305px; height: 30px;" value="${material.urlType }">
                  <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;">跳转类型</label></td>
              </tr>
            </tbody>
          </table>
            <table style="width: 500px;" cellpadding="0" cellspacing="1" class="formtable">
            <tbody id="writeContent">
              <tr id="outer" style="display: none;">
                <td align="right" style="width: 60px"><label class="Validform_label"> 外部链接: </label></td>
                <td class="value"><input id="url" name="url" type="text" style="width: 305px;height:30px;" value="${material.url}" class="inputxt">
                  </td>
              </tr>
              <tr id="outerLabel" style="display: none;">
                <td align="right" style="width: 60px"><label class="Validform_label"></label></td>
                <td class="value"><label style="font-size: 10px;color:red;">提示：外部链接请输入以http或https开头的网址</label></td>
              </tr>
              <tr id="inner" >
                <td align="right" style="width: 60px"><label class="Validform_label"> 正文: </label></td>
                <td class="value"><textarea name="content" id="content">${material.content}</textarea></td>
              </tr>
              <!-- <tr id="innerLabel">
                <td align="right" style="width: 60px"><label class="Validform_label"></label></td>
                <td class="value"><label><input type="button" style="font-size: 18px;" target="_blank" href="http://www.baidu.com">预览图文</a></label></td>
              </tr> -->
              <tr>
                  <td align="left" style="width: 60px">
                      <strong>素材提交审核：</strong>
                  </td>
                  <td class="value">
                                                是： <input type="radio" checked="checked" name="toaudit" value="true"/>
                                                否： <input type="radio" name="toaudit" value="false"/>
                  </td>
              </tr>
              </tbody>
              </table>
          </t:formvalid>
        </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
	
</script>
<script src="webpage/weixin/guanjia/newstemplate/weixinArticle.js"></script>