<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>门店信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css" />
  
  <script type="text/javascript" src="webpage/weixin/business/category.js"></script>
  <script type="text/javascript" src="webpage/weixin/business/jsAddress.js"></script>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  

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
  
   <script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=OV3BZ-MHDCD-J6Z45-PYMY3-JZSLQ-VCFRT"></script>
  <script type="text/javascript">

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
          	$("#titleLogo").val(d.attributes.url);
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
      var name = "${weixinLocationPage.businessName}", titleLogo = "${weixinLocationPage.titleLogo}";
      if(name != ""){
          $("#imageTitle").html(name);
      }
      if(titleLogo != ""){
          $("#imageShow").html('<img src="${weixinLocationPage.titleLogo}" width="100%" height="160" />');
      }
  });
  function setimageTitle(obj){
	  $("#imageTitle").html($(obj).val());
  }
  </script>
 </head>
 <body onload="init(${weixinLocationPage.latitude},${weixinLocationPage.longitude})">
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
		 		<div class="inner" style="width:800px">
		 		
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinLocationController.do?doAdd" tiptype="1"  beforeSubmit="toValidateForm">
					<input id="id" name="id" type="hidden" value="${weixinLocationPage.id }">
					<input id="accountid" name="accountid" type="hidden" value="${weixinLocationPage.accountid }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinLocationPage.createDate }">
					<input id="status" name="status" type="hidden" value="${weixinLocationPage.status }">
					<input id="poiId" name="poiId" type="hidden" value="${weixinLocationPage.poiId }">
					<input id="category" name="category" type="hidden" value="${weixinLocationPage.category }">
		
		
		<table cellpadding="0" cellspacing="1" class="formtable">
				<tr height="35px">
					<td align="right" width="100px" height="35px">
						<label class="Validform_label">
							门店名:
						</label>
					</td>
					<td class="value">
					     	 ${weixinLocationPage.businessName}
						</td>
				</tr>
				
				
				<tr height="35px">
					<td align="right">
						<label class="Validform_label">
							分店名:
						</label>
					</td>
					<td class="value">
					     	${weixinLocationPage.branchName}
						</td>
				</tr>
				<tr height="35px">
					<td align="right">
						<label class="Validform_label">
							类目:
						</label>
					</td>
					<td class="value">
					     	
					     	${weixinLocationPage.category }
					     	
						</td>
				</tr>
			
				<tr height="35px">
					<td align="right" width="100px">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
							${weixinLocationPage.province}
							${weixinLocationPage.city}
							${weixinLocationPage.district}
							
						</td>
				</tr>
				<tr height="35px">
					<td align="right">
						<label class="Validform_label">
							街道:
						</label>
					</td>
					<td class="value">
					     	 ${weixinLocationPage.address}
						</td>
					</tr>
				
				
				
				<tr height="35px">
					<td align="right">
						<label class="Validform_label">
							定位:
						</label>
					</td>
					<td class="value">
							<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
						<script>
							var citylocation,map,marker = null;
							var init = function(a,b) {
							    var center = new qq.maps.LatLng(a,b);
							    var city = document.getElementById("city");
							    map = new qq.maps.Map(document.getElementById('container'),{
							        center: center,
							        zoom: 13
							    });
							    citylocation = new qq.maps.CityService({
							        complete : function(results){
							            map.setCenter(results.detail.latLng);
							            city.style.display = 'inline';
							            city.innerHTML = '所在位置: ' + results.detail.name;
										
							            if (marker != null) {
							                marker.setMap(null);
							            }
							            marker = new qq.maps.Marker({
							                map: map,
							                position: results.detail.latLng
							            });
							        }
							    });
							    
							    
							    var lat = parseFloat(a);
							    var lng = parseFloat(b);
							    var latLng = new qq.maps.LatLng(lat, lng);
							
							    citylocation.searchCityByLatLng(latLng);
							}
							
							function geolocation_latlng() {
							    var input = document.getElementById("latLng").value;
							    
							    var latlngStr = input.split(",",2);
							    var lat = parseFloat(latlngStr[0]);
							    var lng = parseFloat(latlngStr[1]);
							    var latLng = new qq.maps.LatLng(lat, lng);
							
							    citylocation.searchCityByLatLng(latLng);
							}
							</script>
							<span style="height:30px;display:none" id="city"></span>
							<div style="width:603px;height:300px" id="container"></div>
					     	
						</td>
				</tr>
				
			</table>
			<br>
			<table cellpadding="0" cellspacing="1" class="formtable">
				
				<tr height="35px">
					<td align="right" width="100px">
						<label class="Validform_label">
							电话:
						</label>
					</td>
					<td class="value">
					     	${weixinLocationPage.telephone}
						</td>
				</tr>
				<tr height="35px">
					<td align="right">
						<label class="Validform_label">
							人均价格:
						</label>
					</td>
					<td class="value">
					     	${weixinLocationPage.avgPrice}
						</td>
				</tr>
				<tr height="35px">
					<td align="right">
						<label class="Validform_label">
							营业时间:
						</label>
					</td>
					<td class="value">
					     	${weixinLocationPage.openTime}
						</td>
					</tr>
				<tr height="35px">
					<td align="right">
						<label class="Validform_label">
							新品推荐:
						</label>
					</td>
					<td class="value">
							${weixinLocationPage.recommend}
						</td>
				</tr>
				<tr height="35px">
					<td align="right">
						<label class="Validform_label">
							特色服务:
						</label>
					</td>
					<td class="value">
							${weixinLocationPage.special}
						</td>
					</tr>
				<tr height="35px">
					<td align="right">
						<label class="Validform_label">
							简介:
						</label>
					</td>
					<td class="value">
							${weixinLocationPage.introduction}
						</td>
				</tr>
				
				
				<%-- 
				<tr height="35px">
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
				<i class="arrow arrow_out" style="margin-top: 0px;"></i>
				<i class="arrow arrow_in" style="margin-top: 0px;"></i>
			</div>
		</div>
	</div>
 </body>
  <script src = "webpage/weixin/business/weixinLocation.js"></script>

<script type="text/javascript">
function toValidateForm(){
	
	var CategorySelect1 = $("#CategorySelect1").attr("value");
	var CategorySelect2 = $("#CategorySelect2").attr("value");
	
	$("#category").attr("value",CategorySelect1+','+CategorySelect2);
	
	alert($("#category").attr("value"));
	return false;
}
</script>