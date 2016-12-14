<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>优惠券</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>

  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.mins.css" />
   <!--fileupload-->
  <script type="text/javascript" src="plug-in/weixin/js/vendor/jquery.ui.widget.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/load-image.min.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-process.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.fileupload-image.js"></script>
  <script type="text/javascript" src="plug-in/weixin/js/jquery.iframe-transport.js"></script>
  
  <script type="text/javascript">
  //编写自定义JS代码
  /*jslint unparam: true, regexp: true */
  /*global window, $ */
  $(function () {
      'use strict';
      // Change this to the location of your server-side upload handler:
      var url = 'uploadImgController.do?upload&imgType=category',
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
          	$("#logoUrl").val(d.attributes.url);
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
      var name = "${weixinMemberCardPage.title}", logoUrl = "${weixinMemberCardPage.logoUrl}";
      if(name != ""){
          $("#imageTitle").html(name);
      }
      if(logoUrl != ""){
          $("#imageShow").html('<img src="${weixinMemberCardPage.logoUrl}" width="100%" height="160" />');
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
							<i class="appmsg_thumb default" id="imageShow">会员卡LOGO</i>
						</div>
						 <div id="progress" class="progress">
					        <div class="progress-bar progress-bar-success"></div>
					    </div>
						<p class="appmsg_desc"></p>
				</div>
			</div>
		</div>
 		<div class="media_edit_area" id="js_appmsg_editor" style="background: white;">	
			<div class="appmsg_editor" style="margin-top: 0px;">
		 		<div class="inner">
		 		
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinMemberCardController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinMemberCardPage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinMemberCardPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinMemberCardPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${weixinMemberCardPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${weixinMemberCardPage.updateDate }">
					 <input id="accountid" name="accountid" type="hidden" value="${weixinMemberCardPage.accountid }">
					 <input id="cardType" name="cardType" type="hidden" value="MEMBER_CARD">
		<table cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="100px">
						<label class="Validform_label">
							卡LOGO:
						</label>
					</td>
					<td class="value">
					     	 <span class="btn btn-success fileinput-button">
							        <i class="glyphicon glyphicon-plus"></i>
							        <span>浏览</span>
							        
							        <input id="fileupload" type="file" name="files[]" multiple>
							 </span>
						    <input id="logoUrl" name="logoUrl" type="hidden" nullmsg="请添加图片" datatype="*">
						    <span id="imgName"></span> 
							<span class="Validform_checktip">像素要求必须为640*340像素，支持.jpg .jpeg .bmp .png格式，大小不超过2M</span>
							<label class="Validform_label" style="display: none;">图片链接</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商户名字:
						</label>
					</td>
					<td class="value">
					     	 <input id="brandName" name="brandName" type="text" style="width: 150px" class="inputxt"  
								               
								               value="${weixinMemberCardPage.brandName }">
							<span class="Validform_checktip">(必填)字数上限为12个汉字</span>
							<label class="Validform_label" style="display: none;">商户名字</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会员卡名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  
								               
								                datatype="*" value="${weixinMemberCardPage.title }">
							<span class="Validform_checktip">(必填)字数上限为9个汉字</span>
							<label class="Validform_label" style="display: none;">卡券名</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							副标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="subTitle" name="subTitle" type="text" style="width: 150px" class="inputxt"  
								               
								              value="${weixinMemberCardPage.subTitle }">
							<span class="Validform_checktip">(可必填)</span>
							<label class="Validform_label" style="display: none;">副标题</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							特权说明:
						</label>
					</td>
					<td class="value">
					     	 <input id="prerogative" name="prerogative" type="text" style="width: 150px" class="inputxt"  
								               
								              value="${weixinMemberCardPage.prerogative }"  datatype="*">
							<span class="Validform_checktip">(必填)</span>
							<label class="Validform_label" style="display: none;">特权说明</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							显示积分:
						</label>
					</td>
					<td class="value">
							<select id="supplyBonus" name="supplyBonus">
								<option value="true">是</option>
								<option value="false">否</option>
							</select>
					     	
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">显示积分</label>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							消费方式:
						</label>
					</td>
					<td class="value">
							<select id="codeType" name="codeType" datatype="*">
								<option value="CODE_TYPE_TEXT">卡券号</option>
								<option value="CODE_TYPE_QRCODE">二维码 </option>
								<option value="CODE_TYPE_BARCODE">条形码</option>
							</select>
					     	 
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">消费方式</label>
					</td>
				</tr>
				
				
				<tr>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							卡颜色:
						</label>
					</td>
					<td class="value">
					     	<select id="color" name="color" style="width: 200px" datatype="*">
								<option value="Color100">红色</option>
								<option value="Color010">绿色</option>
								<option value="Color080">橙黄</option>
							</select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">卡颜色</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							使用提醒:
						</label>
					</td>
					<td class="value">
					     	 <input id="notice" name="notice" type="text" style="width: 150px" class="inputxt"  
								               
								               value="${weixinMemberCardPage.notice }">
							<span class="Validform_checktip">(必填)字数上限为16个汉字</span>
							<label class="Validform_label" style="display: none;">使用提醒</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							使用说明:
						</label>
					</td>
					<td class="value">
					     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt"  
								               
								               value="${weixinMemberCardPage.description }">
							<span class="Validform_checktip">(必填)</span>
							<label class="Validform_label" style="display: none;">使用说明</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							客服电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="servicePhone" name="servicePhone" type="text" style="width: 150px" class="inputxt"  
								               
								               value="${weixinMemberCardPage.servicePhone }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客服电话</label>
						</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							启用时间:
						</label>
					</td>
					<td class="value">
						
					<input id="beginTimestamp" name="beginTimestamp" datatype="*" class="Wdate" onClick="WdatePicker()" style="width: 150px" value="<fmt:formatDate value='${weixinMemberCardPage.beginTimestamp }' type="date" pattern="yyyy-MM-dd"/>"
					errormsg="启用时间格式不正确!" ignore="ignore"> <span class="Validform_checktip"></span>
				
							<span class="Validform_checktip">(必填)</span>
							<label class="Validform_label" style="display: none;">启用时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							到期时间:
						</label>
					</td>
					<td class="value">
						<input id="endTimestamp" name="endTimestamp" datatype="*" class="Wdate" onClick="WdatePicker()" style="width: 150px" value="<fmt:formatDate value='${weixinMemberCardPage.endTimestamp }' type="date" pattern="yyyy-MM-dd"/>"
					errormsg="到期时间格式不正确!" ignore="ignore"> <span class="Validform_checktip"></span>
					
							<span class="Validform_checktip">(必填)</span>
							<label class="Validform_label" style="display: none;">到期时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							适用门店:
						</label>
					</td>
					<td class="value">
							
							<input name="locationIdList" name="locationIdList" type="hidden" value="${poiId}" id="locationIdList" datatype="*"> 
							<input name="businessName" class="inputxt" value="${businessName }" id="businessName" readonly="readonly" datatype="*" /> 
							<t:choose hiddenName="locationIdList" hiddenid="poiId" url="weixinLocationController.do?locations" name="locationList" icon="icon-search" title="门店列表" textname="businessName" isclear="true"></t:choose>
							<span class="Validform_checktip">门店可多选</span>
						</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							库存数量:
						</label>
					</td>
					<td class="value">
					     	 <input id="quantity" name="quantity" type="text" style="width: 150px" class="inputxt"  
								               
								               datatype="*" datatype="n" value="${weixinMemberCardPage.quantity }">
							<span class="Validform_checktip">(必填)填写整数数字，不支持填写0</span>
							<label class="Validform_label" style="display: none;">库存数量</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							每人限领:
						</label>
					</td>
					<td class="value">
					     	 <input id="getLimit" name="getLimit" type="text" style="width: 150px" class="inputxt"  
								               
								               datatype="*" datatype="n" value="${weixinMemberCardPage.getLimit }">张
							<span class="Validform_checktip">(必填)填写整数数字，不支持填写0</span>
							<label class="Validform_label" style="display: none;">每人限领数量限制</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否可转赠:
						</label>
					</td>
					<td class="value">
							<select id="canGiveFriend" name="canGiveFriend" datatype="*">
								<option value="true">是</option>
								<option value="false">否</option>
							</select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否可转赠</label>
					</td>
				</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/member/weixinMemberCard.js"></script>		