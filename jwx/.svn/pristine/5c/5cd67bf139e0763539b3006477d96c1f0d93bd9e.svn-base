<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
  <title>添加个性化信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css"/>
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css"/>
  <link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css"/>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>

  <!--fileupload-->
  <script type="text/javascript" src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/load-image.min.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-process.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-image.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.iframe-transport.js"></script>
  <script type="text/javascript">
    $(function () {
      'use strict';
      // Change this to the location of your server-side upload handler:
      var url = 'weixinArticleController.do?upload',
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
          $("#imagepath").val(d.attributes.url);
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
    });

  </script>
</head>
<body>
<div class="main_bd">
  <div class="media_preview_area">
    <div class="appmsg editing">
      <div class="appmsg_content" id="js_appmsg_preview">
        <h4 class="appmsg_title">
          <a target="_blank" href="javascript:void(0);"
             onclick="return false;"></a>
        </h4>

        <div class="appmsg_info">
          <em class="appmsg_date"></em>
        </div>
        <div id="files" class="files">
          <i style="height:160px;overflow:hidden;display:inline-block;width:100%;"><img
                  src="" width="100%" height="160"/></i>
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
      <div class="inner" style="min-height: 100px;">
        <t:formvalid formid="formobjNew" dialog="true" usePlugin="password" layout="table"
                     action="weixinIndividualizationController.do?doAdd" tiptype="1">
          <%--<input type="hidden" value="${shareItem.id}" id="bid" name="bid">--%>
          <table style="width: 450px;" cellpadding="0" cellspacing="1" class="formtable">
            <tr>
              <td align="left">
                <label class="Validform_label">
                 名称:
                </label>
              </td>
              <td class="value">
                <input id="acctListName" name="name" type="text" value=""
                       style="width: 300px" class="inputxt" datatype="*" nullmsg="名称不能为空！">
                       <label class="Validform_checktip">比如：'石榴商盟'</label>
                <span class="Validform_checktip" style="display: none;"></span>
                <label class="Validform_label" style="display: none;">名称</label>
                
              </td>
            </tr>
            <tr>
              <td align="left">
                <label class="Validform_label">
         Logo上传:
                </label>
              </td>
              <td class="value">
								<span class="btn btn-success fileinput-button">
							        <i class="glyphicon glyphicon-plus"></i>
							        <span>浏览</span>
							        <!-- The file input field used as target for the file upload widget -->
							        <input id="fileupload" type="file" name="files[]" multiple>
							        <input id="imagepath" name="logo" type="hidden" value=""
                                           datatype="*" nullmsg="请添加图片">
							    </span>
                <span id="imgName"></span>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">Logo</label>
              </td>
            </tr>
            <tr>
              <td>
              </td>
              <td class="value">
                <label class="Validform_checktip">建议图片大小为40*40像素</label>
              </td>
            </tr> 
          </table>    
        </t:formvalid>
      </div>
      <i class="arrow arrow_out" style="margin-top: 0px;"></i>
      <i class="arrow arrow_in" style="margin-top: 0px;"></i>
    </div>
  </div>
</div>

</body>
<script src="webpage/weixin/guanjia/newstemplate/weixinArticle.js"></script>