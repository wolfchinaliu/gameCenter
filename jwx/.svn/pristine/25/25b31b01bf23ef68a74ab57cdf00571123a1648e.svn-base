<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商品信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css" />

  <!--fileupload-->
  <script type="text/javascript" src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/load-image.min.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-process.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-image.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.iframe-transport.js"></script>
  <!--UEditor-->
  <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
  <script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  /*jslint unparam: true, regexp: true */
  /*global window, $ */
  $(function () {
      'use strict';
      // Change this to the location of your server-side upload handler:
      var url = 'uploadImgController.do?upload&imgType=shopgoods',
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
          	$("#titleImg").val(d.attributes.url);
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
      var name = "${weixinShopGoodsPage.title}", titleImg = "${weixinShopGoodsPage.titleImg}";
      if(name != ""){
          $("#imageTitle").html(name);
      }
      if(titleImg != ""){
          $("#imageShow").html('<img src="${weixinShopGoodsPage.titleImg}" width="100%" height="160" />');
      }
  });
  function setimageTitle(obj){
	  $("#imageTitle").html($(obj).val());
  }
  </script>
 </head>
 <body>
<div class="main_bd">
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
							<i class="appmsg_thumb default" id="imageShow">商品图片</i>
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
		 		
		 		
		 		
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinShopGoodsController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinShopGoodsPage.id }">
					<input id="sellerId" name="sellerId" type="hidden" value="${weixinShopGoodsPage.sellerId}">
					<input id="createName" name="createName" type="hidden" value="${weixinShopGoodsPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinShopGoodsPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${weixinShopGoodsPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${weixinShopGoodsPage.updateDate }">
					<input id="sellCount" name="sellCount" type="hidden" value="${weixinShopGoodsPage.sellCount}">
					<input id="discussCount" name="discussCount" type="hidden" value="${weixinShopGoodsPage.discussCount }">
					<input id="goodCount" name="goodCount" type="hidden" value="${weixinShopGoodsPage.goodCount }">
					<input id="badCount" name="badCount" type="hidden" value="${weixinShopGoodsPage.badCount }">
					<input id="statement" name="statement" type="hidden" value="${weixinShopGoodsPage.statement }">
					<input id="shelveTime" name="shelveTime" type="hidden" value="${weixinShopGoodsPage.shelveTime }">
					<input id="removeTime" name="removeTime" type="hidden" value="${weixinShopGoodsPage.removeTime }">
		<table cellpadding="0" cellspacing="1" class="formtable">
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题信息:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt" datatype="*" value="${weixinShopGoodsPage.title}">
							<span class="Validform_checktip">请输入标题信息</span>
							<label class="Validform_label" style="display: none;">标题信息</label>
						</td>
				</tr>
				
				<tr>
							<td align="right">
								<label class="Validform_label">
									上传图片:
								</label>
							</td>
							<td class="value">
								<span class="btn btn-success fileinput-button">
							        <i class="glyphicon glyphicon-plus"></i>
							        <span>浏览</span>
							        <!-- The file input field used as target for the file upload widget -->
							        <input id="fileupload" type="file" name="files[]" multiple>
							    </span>
							    <input id="titleImg" name="titleImg" type="hidden" nullmsg="请添加图片" value="${weixinShopGoodsPage.titleImg}">
							    <span id="imgName"></span> 
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">图片链接</label>
							</td>
				</tr>
						
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品类型:
						</label>
					</td>
					<td class="value">
					     	 <select id="weixinShopCategoryEntity.id" name="weixinShopCategoryEntity.id">
					     	 	<c:forEach items="${goodsCategoryList }" var="goodsCategory">
					     	 		
					     	 		<option value="${goodsCategory.id }" <c:if test='${goodsCategory.id eq weixinShopGoodsPage.weixinShopCategoryEntity.id}'>selected="selected"</c:if>
					     	 		>${goodsCategory.name }</option>
					     	 	</c:forEach>
					     	 </select>
					     	              
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品类型</label>
						</td>
				</tr>		
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品原价:
						</label>
					</td>
					<td class="value">
					     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt" datatype="/^[0-9]+(.[0-9]{1,2})?$/" value="${weixinShopGoodsPage.price}">
							<span class="Validform_checktip">商品原价，最多保留两位</span>
							<label class="Validform_label" style="display: none;">商品原价</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品现价:
						</label>
					</td>
					<td class="value">
					     	 <input id="realPrice" name="realPrice" datatype="/^[0-9]+(.[0-9]{1,2})?$/" type="text" style="width: 150px" class="inputxt" value="${weixinShopGoodsPage.realPrice}">
							<span class="Validform_checktip">可不填，代表没有优惠,最多保留两位</span>
							<label class="Validform_label" style="display: none;">商品现价</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							新品推荐:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isNew" typeGroupCode="is_new" hasLabel="false" type="select" defaultVal="0"></t:dictSelect>
					    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">勾选代表新品推荐</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							热销推荐:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isHot" typeGroupCode="is_hot" hasLabel="false" type="select" defaultVal="0"></t:dictSelect>
					     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">勾选代表热销推荐</label>
						</td>
					</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							快递名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="expressName" name="expressName" type="text" style="width: 150px" datatype="*" class="inputxt" value="${weixinShopGoodsPage.expressName}">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">快递名称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							快递费用:
						</label>
					</td>
					<td class="value">
					     	 <input id="expressPrice" name="expressPrice" type="text" style="width: 150px" class="inputxt" datatype="/^[0-9]+(.[0-9]{1,2})?$/" value="${weixinShopGoodsPage.expressPrice}">
							<span class="Validform_checktip">快递费用，整数类型</span>
							<label class="Validform_label" style="display: none;">快递费用</label>
						</td>
				</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							商品详情:
						</label>
					</td>
					<td class="value">
					     	
							<textarea name="descriptions" id="descriptions" style="width:350px;height:300px">${weixinShopGoodsPage.descriptions}</textarea>
							<script type="text/javascript">
								var editor = UE.getEditor('descriptions');
							</script>
							
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
  <script src = "webpage/weixin/goods/weixinShopGoods.js"></script>		