<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微信商城</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css" />
  <style type="text/css">
  .input{
  	vertical-align:middle;}
  </style>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type=text/javascript src="plug-in/clipboard/ZeroClipboard.js"></script>
  <!--fileupload-->
  <script type="text/javascript" src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/load-image.min.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-process.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-image.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.iframe-transport.js"></script>

  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  
  <script type="text/javascript">
  //编写自定义JS代码
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
          var  file = data.files[0];
          //var delUrl = "<a class=\"js_removeCover\" onclick=\"return false;\" href=\"javascript:void(0);\">删除</a>";
          $("#imgName").text("").append(file.name);
          $("#imageName").val(file.name);
          $("#progress").hide();
  		var d =data.result;
  		if (d.success) {
  			var link = $('<a>').attr('target', '_blank').prop('href', d.attributes.viewhref);
          	$(data.context.children()[0]).wrap(link);
          	//console.info(d.attributes.viewhref);
          	$("#shopLogo").val(d.attributes.url);
  		}else{
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
      
      //编辑时初始化图片预览
      var name = "${weixinShopPage.shopLogo}", imageHref = "${cdnHost}/plug-in/liuliangbao/20160701/${weixinShopPage.shopLogo}";
      if(name != ""){
          $("#imageTitle").html(name);
      }
      if(imageHref != ""){
          $("#imageShow").html('<img src="${cdnHost}/plug-in/liuliangbao/20160701/${weixinShopPage.shopLogo}" width="100%" height="160" />');
      }
  });
  function setimageTitle(obj){
	  $("#imageTitle").html($(obj).val());
  }
  </script>
 </head>
 <body>
  <div class="media_preview_area">
 <div class="appmsg editing">
				<div class="appmsg_content" id="js_appmsg_preview">
						<h4 class="appmsg_title">
							<a target="_blank" href="javascript:void(0);" onclick="return false;" id="imageTitle">标题</a>
						</h4>
						<div class="appmsg_info">
							<em class="appmsg_date"></em>
						</div>
						<div id="files" class="files">
							<i class="appmsg_thumb default" id="imageShow">商城logo</i>
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
		 		<div class="inner" style="width:90%">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinShopController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinShopPage.id }">
					<input id="accountid" name="accountid" type="hidden" value="${weixinShopPage.accountid }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								商城地址:
							</label>
						</td>
						<td class="value">
						     	
							<%=basePath%>/shopController.do?shopindex&accountid=${weixinShopPage.accountid }							
						</td>
					</tr>
					<tr>
						<td align="right" width="100px">
							<label class="Validform_label">
								商城名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="shopName" name="shopName" type="text" style="width: 300px" class="inputxt"  
									               
									                 value='${weixinShopPage.shopName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商城名称</label>
						</td>
					</tr>
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								商城logo:
							</label>
						</td>
						<td class="value">
							<span class="btn btn-success fileinput-button">
							        <i class="glyphicon glyphicon-plus"></i>
							        <span>浏览</span>
							        <!-- The file input field used as target for the file upload widget -->
							        <input id="fileupload" type="file" name="files[]" multiple>
							</span>
					     	<input id="shopLogo" name="shopLogo" type="hidden" value="${weixinShopPage.shopLogo}" style="width: 150px" class="inputxt">
							<span id="imgName"></span> 
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商城logo</label>
						</td>
					</tr>
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="telephone" name="telephone" type="text" style="width: 300px" class="inputxt"  
									               
									                 value='${weixinShopPage.telephone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="address" name="address" type="text" style="width: 300px" class="inputxt"  
									               
									                 value='${weixinShopPage.address}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址</label>
						</td>
					</tr>
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								介绍:
							</label>
						</td>
						<td class="value">
						     	 <input id="introduction" name="introduction" type="text" style="width: 300px" class="inputxt"  
									               
									                 value='${weixinShopPage.introduction}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">介绍</label>
						</td>
					</tr>
					
			</table>
		</t:formvalid>
		</div>
		</div>
	</div>
 </body>
  <script src = "webpage/weixin/shop/weixinShop.js"></script>		