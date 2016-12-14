<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<title>门店信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link type="text/css" rel="stylesheet"
	href="plug-in/weixin/css/appmsg_edit.css" />
<link type="text/css" rel="stylesheet"
	href="plug-in/weixin/css/jquery.fileupload.css" />
<link type="text/css" rel="stylesheet"
	href="plug-in/bootstrap/css/bootstrap.min.css" />

<script type="text/javascript" src="webpage/weixin/business/category.js"></script>
<script type="text/javascript"
	src="webpage/weixin/business/jsAddress.js"></script>

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
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8"
	src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>

<script charset="utf-8"
	src="http://map.qq.com/api/js?v=2.exp&key=OV3BZ-MHDCD-J6Z45-PYMY3-JZSLQ-VCFRT"></script>

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

body, button, input, select, textarea {
	font: 12px/16px Verdana, Helvetica, Arial, sans-serif;
}

#container {
	min-width: 300px;
	min-height: 467px;
}
</style>
<script type="text/javascript">
	$(function() {
		'use strict';
		// Change this to the location of your server-side upload handler:
		var url = 'uploadImgController.do?upload&imgType=shopgoods', uploadButton = $(
				'<button/>').addClass('btn btn-primary').prop('disabled', true)
				.text('上传中...').on('click', function() {
					var $this = $(this), data = $this.data();
					$this.off('click').text('正在上传...').on('click', function() {
						$this.remove();
						data.abort();
					});
					data.submit().always(function() {
						$this.remove();
					});
				});
		$('#fileupload')
				.fileupload(
						{
							url : url,
							dataType : 'json',
							autoUpload : false,
							acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
							maxFileSize : 2000000, // 2 MB
							disableImageResize : /Android(?!.*Chrome)|Opera/
									.test(window.navigator.userAgent),
							previewMaxWidth : 290,
							previewMaxHeight : 160,
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
							$("#imageName").val(file.name);
							$("#progress").hide();
							var d = data.result;
							if (d.success) {
								var link = $('<a>').attr('target', '_blank')
										.prop('href', d.attributes.viewhref);
								$(data.context.children()[0]).wrap(link);
								//console.info(d.attributes.viewhref);
								$("#titleLogo").val(d.attributes.url);
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

		//编辑时初始化图片预览
		var name = "${weixinLocationPage.businessName}", titleLogo = "${weixinLocationPage.titleLogo}";
		if (name != "") {
			$("#imageTitle").html(name);
		}
		if (titleLogo != "") {
			$("#imageShow")
					.html(
							'<img src="${weixinLocationPage.titleLogo}" width="100%" height="160" />');
		}
	});
	function setimageTitle(obj) {
		$("#imageTitle").html($(obj).val());
	}
</script>
</head>
<body onload="init()">
	<div class="main_bd">
		<div class="media_preview_area">
			<div class="appmsg editing">
				<div class="appmsg_content" id="js_appmsg_preview">
					<h4 class="appmsg_title">
						<a target="_blank" href="javascript:void(0);"
							onclick="return false;" id="imageTitle">标题</a>
					</h4>
					<div class="appmsg_info">
						<em class="appmsg_date"></em>
					</div>
					<div id="files" class="files">
						<i class="appmsg_thumb default" id="imageShow">门店图片</i>
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
				<div class="inner" style="width: 800px">

					<t:formvalid formid="formobj" dialog="true" usePlugin="password"
						layout="table" action="weixinLocationController.do?doAdd"
						tiptype="1" beforeSubmit="toValidateForm">
						<input id="id" name="id" type="hidden"
							value="${weixinLocationPage.id }">
						<input id="accountid" name="accountid" type="hidden"
							value="${weixinLocationPage.accountid }">
						<input id="createDate" name="createDate" type="hidden"
							value="${weixinLocationPage.createDate }">
						<input id="status" name="status" type="hidden"
							value="${weixinLocationPage.status }">
						<input id="poiId" name="poiId" type="hidden"
							value="${weixinLocationPage.poiId }">
						<input id="category" name="category" type="hidden"
							value="${weixinLocationPage.category }">

						<div
							style="border: solid #f7cc8f 1px; background: #FFEFCE; margin: 10px 1px 10px 1px;; vertical-align: middle; height: 30px;"
							id="point_title">
							<p
								style="padding-left: 15px; height: 30px; vertical-align: middle; padding-top: 5px;">
								<b>基本信息</b> 基本信息提交后不可修改
							</p>
						</div>
						
						<table cellpadding="0" cellspacing="1" class="formtable">
							<tr>
								<td align="right" width="100px"><label
									class="Validform_label"> 门店图片: </label></td>
								<td class="value"><span
									class="btn btn-success fileinput-button"> <i
										class="glyphicon glyphicon-plus"></i> <span>浏览</span> <!-- The file input field used as target for the file upload widget -->
										<input id="fileupload" type="file" name="files[]" multiple>
								</span> <input id="titleLogo" name="titleLogo" type="hidden"
									nullmsg="请添加图片" value="${weixinLocationPage.titleLogo}"
									datatype="*"> <span id="imgName"></span> <span
									class="Validform_checktip">必须，像素要求必须为640*340像素，支持.jpg
										.jpeg .bmp .png格式，大小不超过2M</span> <label class="Validform_label"
									style="display: none;">图片链接</label></td>
							</tr>
							<tr>
								<td align="right" width="100px"><label
									class="Validform_label"> 门店名: </label></td>
								<td class="value"><input id="businessName"
									name="businessName" type="text" style="width: 300px"
									class="inputxt" value="${weixinLocationPage.businessName}"
									datatype="*" maxlength="30"> <span
									class="Validform_checktip">必填，仅为商户名，如：麦当劳，不包含地区、地址</span> <label
									class="Validform_label" style="display: none;">门店名</label></td>
							</tr>


							<tr>
								<td align="right"><label class="Validform_label">
										分店名: </label></td>
								<td class="value"><input id="branchName" name="branchName"
									type="text" style="width: 300px" class="inputxt" datatype="*">
									<span class="Validform_checktip">必填，不应与门店名有重复，不包含地区、地址</span> <label
									class="Validform_label" style="display: none;">分店名</label></td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										类目: </label></td>
								<td class="value"><select id="CategorySelect1"
									name="CategorySelect1"></select> <select id="CategorySelect2"
									name="CategorySelect2"></select> <script type="text/javascript">
										locationCategoryInit('CategorySelect1',
												'CategorySelect2');
									</script> <span class="Validform_checktip">必填</span> <label
									class="Validform_label" style="display: none;">类目</label></td>
							</tr>

							<tr>
								<td align="right" width="100px"><label
									class="Validform_label"> 地区: </label></td>
								<td class="value"><select id="province" name="province"
									style="width: 100px"></select> <select id="city" name="city"
									style="width: 100px"></select> <select id="district"
									name="district" style="width: 100px"></select> <script
										type="text/javascript">
										addressInit('province', 'city',
												'district');
									</script> <span class="Validform_checktip">必填</span> <label
									class="Validform_label" style="display: none;">地址</label></td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										街道: </label></td>
								<td class="value"><input id="address" name="address"
									type="text" style="width: 300px" class="inputxt" value=''
									datatype="*"> <span class="Validform_checktip">必填</span>
									<label class="Validform_label" style="display: none;">街道</label><input
									type="button" value="搜索标注" onclick="locationPoint();" /></td>
							</tr>
							<tr>
								<td>地图</td>
								<td>
									<div id="container" style="width: 580px;"></div>
								</td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										定位: </label></td>
								<td class="value"><input type="text" id="longitude"
									datatype="*" readonly="readonly" name="longitude"
									style="width: 145px" class="inputxt" onclick="locationPoint()" />
									<input type="text" id="latitude" name="latitude"
									style="width: 145px" class="inputxt" onclick="locationPoint()"
									readonly="readonly" /> <%-- 
					     	<input id="longitude" name="longitude" type="text" style="width: 100px" readonly="readonly" class="inputxt" value='${weixinLocationPage.longitude}' datatype="*">（经度）&nbsp;&nbsp;
							<input id="latitude" name="latitude" type="text" style="width: 100px" readonly="readonly" class="inputxt"  value='${weixinLocationPage.latitude}' datatype="*">（纬度）
							--%> <span class="Validform_checktip">必填，门店所在地理位置</span> <label
									class="Validform_label" style="display: none;">坐标定位</label></td>
							</tr>
							<script language="JavaScript">
								var map = null, marker = null, geocoder = null;
								var init = function() {
									var center = new qq.maps.LatLng(23.180349,
											113.480326);
									map = new qq.maps.Map(document
											.getElementById('container'), {
										center : center,
										zoom : 13
									});
									//调用地址解析类
									geocoder = new qq.maps.Geocoder(
											{
												complete : function(result) {
													map
															.setCenter(result.detail.location);
													marker = new qq.maps.Marker(
															{
																map : map,
																position : result.detail.location
															});
													var lo = marker
															.getPosition();
													$("#latitude").val(lo.lat);
													$("#longitude").val(lo.lng);
												}
											});
								}
								function locationPoint() {
									if($("#address").val()==''){
										alert("请填写街道地址");
										return;
									}
									var addr = $("#province").val()
											+ $("#city").val()
											+ $("#district").val()
											+ $("#address").val();
									geocoder.getLocation(addr);
								}
							</script>

						</table>
						<div
							style="border: solid #f7cc8f 1px; background: #FFEFCE; margin: 10px 1px 10px 1px; height: 30px;"
							id="point_title">
							<p style="padding-left: 15px; padding-top: 5px;">
								<b>服务信息</b> 该部分为公共编辑信息，每个添加了该门店的商户均可提交修改意见，并在审核后选择性采纳
							</p>
						</div>
						<table cellpadding="0" cellspacing="1" class="formtable">
							<tr>
								<td align="right" width="100px"><label
									class="Validform_label"> 门店电话: </label></td>
								<td class="value"><input id="telephone" name="telephone"
									type="text" style="width: 300px" class="inputxt"
									value='${weixinLocationPage.telephone}'
									datatype="/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$)/">
									<span class="Validform_checktip">必填，格式如,
										020-88888888，或手机号码</span> <label class="Validform_label"
									style="display: none;">门店电话</label></td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										人均价格: </label></td>
								<td class="value"><input id="avgPrice" name="avgPrice"
									type="text" style="width: 300px" class="inputxt"
									value='${weixinLocationPage.avgPrice}' datatype="n"> <span
									class="Validform_checktip">必填，大于零的整数，须如实填写，默认单位为人民币</span> <label
									class="Validform_label" style="display: none;">人均消费</label></td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										营业时间: </label></td>
								<td class="value"><input id="openTime" name="openTime"
									type="text" style="width: 300px" class="inputxt"
									errormsg="请输入正确的格式" value='${weixinLocationPage.openTime}'
									datatype="/^[0-9]{2}:[0-9]{2}-[0-9]{2}:[0-9]{2}$/"> <span
									class="Validform_checktip">必填，格式如，10:00-21:00</span> <label
									class="Validform_label" style="display: none;">营业时间</label></td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										特色服务: </label></td>
								<td class="value"><input id="special" name="special"
									type="text" style="width: 300px" class="inputxt"
									value='${weixinLocationPage.special}' datatype="*"> <span
									class="Validform_checktip">必填，如：免费停车，免费wifi，送货上门</span> <label
									class="Validform_label" style="display: none;">特色服务</label></td>
							</tr>
							<tr>
								<td align="right"><label class="Validform_label">
										新品推荐: </label></td>
								<td class="value"><input id="recommend" name="recommend"
									type="text" style="width: 300px" class="inputxt"
									value='${weixinLocationPage.recommend}'> <span
									class="Validform_checktip">选填，如，推荐菜，推荐景点，推荐房间</span> <label
									class="Validform_label" style="display: none;">新品推荐</label></td>
							</tr>

							<tr>
								<td align="right"><label class="Validform_label">
										简介: </label></td>
								<td class="value"><textarea name="introduction"
										id="introduction" style="width: 300px; height: 100px">${weixinLocationPage.introduction}</textarea>
									<span class="Validform_checktip">选填，对品牌或门店的简要介绍</span> <label
									class="Validform_label" style="display: none;">简介</label></td>
							</tr>


							<%-- 
				<tr>
					<td align="right">
						<label class="Validform_label">
							二维码:
						</label>
					</td>
					<td class="value">
					     	 <input id="qrcodeLogo" name="qrcodeLogo" type="text" style="width: 300px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">二维码</label>
						</td>
				
					</tr>
					--%>
						</table>
					</t:formvalid>
				</div>
				<i class="arrow arrow_out" style="margin-top: 0px;"></i> <i
					class="arrow arrow_in" style="margin-top: 0px;"></i>
			</div>
		</div>
	</div>

</body>
<script src="webpage/weixin/business/weixinLocation.js"></script>

<script type="text/javascript">
	function toValidateForm() {

		var CategorySelect1 = $("#CategorySelect1").attr("value");
		var CategorySelect2 = $("#CategorySelect2").attr("value");

		$("#category").attr("value", CategorySelect1 + ',' + CategorySelect2);

		return true;
	}
</script>