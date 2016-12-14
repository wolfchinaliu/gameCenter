<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>素材管理</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_pptaddedit.css"/>
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
    <!--UEditor-->
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#supplyPage').removeAttr("disabled");
            $('#inputsupplyPage').removeAttr("disabled");

            $('#description').removeAttr("disabled");
            $('#pptTile').removeAttr("disabled");

            $('#supplyBonus').removeAttr("disabled");
            $('#jumpType').removeAttr("disabled");

            $('#pageUrl').removeAttr("disabled");
        });
    </script>

    <%--<script type="text/javascript">
        //编写自定义JS代码
        /*jslint unparam: true, regexp: true */
        /*global window, $ */
        $(function () {
            'use strict';
            // Change this to the location of your server-side upload handler:
            var url = 'pptManagerController.do?uploadthumbMike',
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
                    console.info(d.attributes.imagePath);
                    $("#imagePath").val(d.attributes.imagePath);
                    $("#imageRelativeUrl").val(d.attributes.imageRelativeUrl);
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
    </script>--%>
</head>
<body>

<t:formvalid formid="messagefrom" dialog="true" usePlugin="password" layout="table"
             action="pptManagerController.do?doAdddd">

    <table style="width:800px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr class="appmst_tr">
            <td align="right" class="appmsg_firsttd" style="padding-bottom: 40px;"><label>幻灯片页面:</label></td>
            <td class="value" style="padding-bottom: 40px;">
                <select id="supplyPage" name="supplyPage" onchange="selectPageChange()" >
                    <option value="门户">门户</option>
                    <option value="更多赚取流量">更多赚取流量</option>
                </select>
                <input id="inputsupplyPage" type="hidden" class="inputxt" name="inputsupplyPage" datatype="*"
                       value="门户">
                <input id="pptID" type="hidden" class="inputxt" name="pptID" datatype="*" value="${weidoorppt.id}">
            </td>
        </tr>

        <tr class="appmst_tr">
            <td align="right" class="appmsg_firsttd" style="padding-bottom: 40px;"><label>标题:</label></td>
            <td class="value" style="padding-bottom: 40px;">
                <input id="pptTile" class="inputxt" name="pptTile" datatype="*" value="${weidoorppt.title}">
            </td>
        </tr>

        <tr class="appmst_tr">
            <td class="appmsg_firsttd" align="right" style="padding-bottom: 40px;">
                图片：
            </td>
            <td style="padding-bottom: 40px;">
                <div class="media_preview_area">
                    <div class="appmsg editing">
                        <div class="appmsg_content" id="js_appmsg_preview">
                            <div class="appmsg_info">
                                <em class="appmsg_date"></em>
                            </div>
                            <div id="files" class="files">
                                <i class="appmsg_thumb default"><img src="${weidoorppt.pictureUrl}" width="100%" height="160"/></i>
                                <input id="imagePath" name=imagePath type="hidden" value="${weidoorppt.pictureUrl}*">
                                <input id="imageRelativeUrl" name=imageRelativeUrl type="hidden">
                            </div>
                           <div id="progress" class="progress">
                                <div class="progress-bar progress-bar-success"></div>
                            </div>
                            <p class="appmsg_desc"></p>
                       </div>
                    </div>
                </div>
                &lt;%&ndash;<div class="media_edit_area" id="js_appmsg_editor">
                    <div class="appmsg_editor" style="margin-top: 0px;">
                        <div class="inner">
                            <input type="hidden" id="id" name="id" value="${weixinArticlePage.id }">
                            <input type="hidden" id="templateId" name="templateId" value="${templateId}">
                            <input type="hidden" id="createDate" name="createDate"
                                   value="${weixinArticlePage.createDate }">
                            <table style="width: 500px;" cellpadding="0" cellspacing="1" class="formtable">
                                <tr>
                                    <td align="right">
                                        <label class="Validform_label">图片链接:</label>
                                    </td>
                                    <td class="value">
								<span class="btn btn-success fileinput-button">
							        <i class="glyphicon glyphicon-plus"></i>
							        <span>浏览</span>
                                    <!-- The file input field used as target for the file upload widget -->
							        <input id="fileupload" type="file" name="files[]" multiple>
							        <input id="imagePath" name=imagePath type="hidden" datatype="*" nullmsg="请添加图片">
							        <input id="imageRelativeUrl" name=imageRelativeUrl type="hidden">
							    </span>
                                        <span id="imgName"></span>
                                        <span class="Validform_checktip"></span>
                                        <label class="Validform_label" style="display: none;">图片链接</label>
                                    </td>
                                </tr>

                            </table>
                        </div>
                        <i class="arrow arrow_out" style="margin-top: 0px;"></i>
                        <i class="arrow arrow_in" style="margin-top: 0px;"></i>
                    </div>
                </div>&ndash;%&gt;
            </td>


        </tr>

            &lt;%&ndash;描述&ndash;%&gt;
        <tr class="appmst_tr">
            <td align="right" style="width:100px;padding-bottom: 40px;" class="appmsg_firsttd"><label>描述:</label></td>
            <td class="value" style="padding-bottom: 40px;">
                    &lt;%&ndash;<textarea rows="3" cols="180" name="content" id="description" style="width:500px;"></textarea><br>&ndash;%&gt;
                <input id="description" class="inputxt" name="description" datatype="*"
                       style="width:500px;height: 200px;" value="${weidoorppt.description}">
            </td>
        </tr>

            &lt;%&ndash;链接类型&ndash;%&gt;
        <tr class="appmst_tr appmsg_firsttd">
            <td align="right" style="width:100px;padding-bottom: 40px;"><label>跳转类型:</label></td>
            <td class="value" style="padding-bottom: 40px;">
                    &lt;%&ndash; <t:dictSelect field="city" typeGroupCode="city_jx" hasLabel="false"
                                   defaultVal=""></t:dictSelect>&ndash;%&gt;
                <select id="supplyBonus" name="supplyBonus" onchange="selectChange()">
                    <option value="固定页面">固定页面</option>
                    <option value="外部页面">外部页面</option>
                </select>
                <input id="jumpType" type="hidden" class="inputxt" name="jumpType" datatype="*" style="width:500px;"
                       value="固定页面">
            </td>
        </tr>

            &lt;%&ndash;页面地址&ndash;%&gt;
        <tr class="appmst_tr appmsg_firsttd">
            <td align="right"  style="width:100px;padding-bottom: 40px;"><label>页面地址:</label></td>
            <td class="value" style="padding-bottom: 40px;">
                <input id="pageUrl" class="inputxt" name="pageUrl" datatype="*" style="width:500px;"
                       value="${weidoorppt.jumpUrl}">
            </td>
        </tr>

    </table>
</t:formvalid>

</body>
