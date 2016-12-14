<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>g</title>
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
    <!--UEditor-->
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>


    <script type="text/javascript">
        //编写自定义JS代码
        /*jslint unparam: true, regexp: true */
        /*global window, $ */
        $(function () {
            'use strict';
            // Change this to the location of your server-side upload handler:
//            var url = 'weixinArticleController.do?uploadthumbMike',
            var url = 'weixinSourceController.do?uploadthumbMike',
                    uploadButton = $('<a/>')
                            .addClass('btn btn-primary')
                            .prop('disabled', true)
                            .text('上传')
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
//            上传的是企业的二维码
            $('#fileupload').fileupload({
                url: url,
                dataType: 'json',
                autoUpload: false,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                maxFileSize: 2000000, // 2 MB
                disableImageResize: /Android(?!.*Chrome)|Opera/
                        .test(window.navigator.userAgent),
                previewMaxWidth: 150,
                previewMaxHeight: 150,
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
//            上传的是企业logo
            $('#fileupload1').fileupload({
                url: url,
                dataType: 'json',
                autoUpload: false,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                maxFileSize: 2000000, // 2 MB
                disableImageResize: /Android(?!.*Chrome)|Opera/
                        .test(window.navigator.userAgent),
                previewMaxWidth: 150,
                previewMaxHeight: 150,
                previewCrop: true
            }).on('fileuploadadd', function (e, data) {
                $("#files1").text("");
                data.context = $('<div/>').appendTo('#files1');
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
                $('#progress1 .progress-bar').css(
                        'width',
                        progress + '%'
                );
            }).on('fileuploaddone', function (e, data) {
                //console.info(data);
                var file = data.files[0];
                //var delUrl = "<a class=\"js_removeCover\" onclick=\"return false;\" href=\"javascript:void(0);\">删除</a>";
                $("#imgName1").text("").append(file.name);
                $("#progress1").hide();
                var d = data.result;
                if (d.success) {
                    var link = $('<a>').attr('target', '_blank').prop('href', d.attributes.viewhref);
                    $(data.context.children()[0]).wrap(link);
                    console.info(d.attributes.imagePath);
                    $("#imagePath1").val(d.attributes.imagePath);
                    $("#imageRelativeUrl1").val(d.attributes.imageRelativeUrl);
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
    <%--二维码图片的上传--%>
    <%--<div class="media_preview_area">--%>
    <%--<div class="appmsg editing">--%>
    <%--<div class="appmsg_content" id="js_appmsg_preview">--%>
    <%--<h4 class="appmsg_title">--%>
    <%--<a target="_blank" href="javascript:void(0);"--%>
    <%--onclick="return false;">二维码图片预览</a>--%>
    <%--</h4>--%>

    <%--<div class="appmsg_info">--%>
    <%--<em class="appmsg_date"></em>--%>
    <%--</div>--%>
    <%--<div id="files" class="files">--%>
    <%--<i class="appmsg_thumb default"><img src="${weixinAccountPage.QRcode}" width="100%"--%>
    <%--height="160"/></i>--%>

    <%--</div>--%>
    <%--<div id="progress" class="progress">--%>
    <%--<div class="progress-bar progress-bar-success"></div>--%>
    <%--</div>--%>
    <%--<p class="appmsg_desc"></p>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--商业加logo上传--%>
    <%--<div class="media_preview_area">--%>
    <%--<div class="appmsg editing">--%>
    <%--<div class="appmsg_content" id="js_appmsg_preview1">--%>
    <%--<h4 class="appmsg_title">--%>
    <%--<a target="_blank" href="javascript:void(0);"--%>
    <%--onclick="return false;">企业logo图片预览</a>--%>
    <%--</h4>--%>

    <%--<div class="appmsg_info">--%>
    <%--<em class="appmsg_date"></em>--%>
    <%--</div>--%>
    <%--<div id="files1" class="files">--%>
    <%--<i class="appmsg_thumb default"><img src="${weixinAccountPage.logoAccount}" width="100%"--%>
    <%--height="160"/></i>--%>

    <%--</div>--%>
    <%--<div id="progress1" class="progress">--%>
    <%--<div class="progress-bar progress-bar-success"></div>--%>
    <%--</div>--%>
    <%--<p class="appmsg_desc"></p>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>

    <div class="media_edit_area" id="js_appmsg_editor">
        <div class="appmsg_editor" style="margin-top: 0px;">
            <div class="inner">
                <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table"
                             action="weixinAccountController.do?doUpdate" tiptype="1">
                    <input id="id" name="id" type="hidden" value="${weixinAccountPage.id }">
                    <input id="addtoekntime" name="addtoekntime" type="hidden"
                           value="${weixinAccountPage.addtoekntime }"/>
                    <input id="accountaccesstoken" name="accountaccesstoken"
                           value="${weixinAccountPage.accountaccesstoken }" type="hidden"/>
                    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    公众帐号名称:
                                </label>
                            </td>
                            <td class="value">
                                <input id="accountname" name="accountname" type="text" style="width: 250px"
                                       class="inputxt"

                                       value='${weixinAccountPage.accountname}'><br>
                                <span class="Validform_checktip1" style="color:#999">此信息需登录 微信公众平台-公众号设置 获取</span>
                                <label class="Validform_label" style="display: none;">公众帐号名称</label>
                            </td>
                        </tr>

                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    原始ID:
                                </label>
                            </td>
                            <td class="value">
                                <input id="weixin_accountid" name="weixin_accountid" type="text" style="width: 250px"
                                       class="inputxt"

                                       value='${weixinAccountPage.weixin_accountid}'>
                                <br><span class="Validform_checktip1" style="color:#999">此信息需登录 微信公众平台-公众号设置 获取，请认真填写原始ID，填写后原始ID将不能修改</span>
                                <label class="Validform_label" style="display: none;">原始ID</label>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    公众号类型:
                                </label>
                            </td>
                            <td class="value">
                                <t:dictSelect field="accounttype" typeGroupCode="weixintype" hasLabel="false"
                                              defaultVal="${weixinAccountPage.accounttype}"></t:dictSelect>
                                <span class="Validform_checktip"></span>
                                <label class="Validform_label" style="display: none;">公众号类型</label>
                            </td>
                        </tr>

                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    公众帐号APPID:
                                </label>
                            </td>
                            <td class="value">
                                <input id="accountappid" name="accountappid" type="text" style="width: 250px"
                                       class="inputxt"

                                       value='${weixinAccountPage.accountappid}'>
                                <br><span class="Validform_checktip1 " style="color:#999">此信息需登录 微信公众平台-开发者中心 获取</span>
                                <label class="Validform_label" style="display: none;">公众帐号APPID</label>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    公众帐号APPSECRET:
                                </label>
                            </td>
                            <td class="value">
                                <input id="accountappsecret" name="accountappsecret" type="text" style="width: 250px"
                                       class="inputxt"

                                       value='${weixinAccountPage.accountappsecret}'>
                                <br><span class="Validform_checktip1" style="color:#999">此信息需登录 微信公众平台-开发者中心 获取</span>
                                <br><span class="Validform_checktip1" style="color:#999">如使用全功能收授权或单网页授权方式，APPSECRET可为空</span>
                                <label class="Validform_label" style="display: none;">公众帐号APPSECRET</label>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    公众号二维码:
                                </label>
                            </td>
                            <td class="value">
                                <div>

                                    <div class="appmsg_content" id="js_appmsg_preview">
                                        <div class="appmsg_info">
                                            <em class="appmsg_date"></em>
                                        </div>
                                        <div id="files" class="files">
                                            <i class="appmsg_thumb1"><img src="${weixinAccountPage.QRcode}"
                                                                          width="150px"
                                                                          height="150px"/></i>
                                        </div>
                                        <div id="progress" class="progress">
                                            <div class="progress-bar progress-bar-success"></div>
                                        </div>
                                    </div>
                                </div>

                                <br>

                                <div>
                                    <span class="btn btn-success fileinput-button">
							        <i class="glyphicon glyphicon-plus"></i>
							        <span>浏览</span>
                                    <!-- The file input field used as target for the file upload widget -->
							        <input id="fileupload" type="file" name="files[]" multiple>
							        <input id="imagePath" name=imagePath type="hidden">
							        <input id="imageRelativeUrl" name=imageRelativeUrl type="hidden" value="${weixinAccountPage.QRcode}">
							    </span>
                                    <span id="imgName"></span>
                                    <span class="Validform_checktip"></span>
                                    <label class="Validform_label" style="display: none;">图片链接</label>
                                </div>
                                <br><span class="Validform_checktip1" style="color:#999">建议图片规格:200像素*200像素；jpg、png</span>
                            </td>
                        </tr>

                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    企业logo:
                                </label>
                            </td>
                            <td class="value">
                                <div>

                                    <div class="appmsg_content" id="js_appmsg_preview1">
                                        <div class="appmsg_info">
                                            <em class="appmsg_date"></em>
                                        </div>
                                        <div id="files1" class="files">
                                            <i class="appmsg_thumb1"><img src="${weixinAccountPage.logoAccount}"
                                                                                 width="150px"
                                                                                 height="150px"/></i>
                                        </div>
                                        <div id="progress1" class="progress">
                                            <div class="progress-bar progress-bar-success"></div>
                                        </div>
                                    </div>
                                </div>
                                <br>

                                <div>
                                    <span class="btn btn-success fileinput-button">
							        <i class="glyphicon glyphicon-plus"></i>
							        <span>浏览</span>
                                    <!-- The file input field used as target for the file upload widget -->
							        <input id="fileupload1" type="file" name="files[]" multiple>
							        <input id="imagePath1" name=imagePath1 type="hidden">
							        <input id="imageRelativeUrl1" name=imageRelativeUrl1 type="hidden" value="${weixinAccountPage.logoAccount}">
							    </span>
                                    <span id="imgName1"></span>
                                    <span class="Validform_checktip"></span> 
                                    <label class="Validform_label" style="display: none;">图片链接</label>
                                </div>
                                <br><span class="Validform_checktip1" style="color:#999">建议图片规格:200像素*200像素；jpg、png</span>
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
<script src="webpage/weixin/source/weixinSource.js"></script>