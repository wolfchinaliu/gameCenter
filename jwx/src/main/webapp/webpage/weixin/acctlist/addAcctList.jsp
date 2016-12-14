<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <title>添加商户信息</title>
    <!-- 地图 -->
  <script type="text/javascript" src="http://lbsyun.baidu.com/skins/MySkin/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GmI3dTdWX1S4U37qLqAIcpHzYwRlC4Rt"></script>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css" />
  <link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css" />
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <link rel="stylesheet" type="text/css" href="easyui/jquery-easyui-theme/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="easyui/jquery-easyui-theme/icon.css">
  <script type="text/javascript" src="easyui/jquery/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="easyui/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="easyui/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>

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

<div id="acct_add_area">
  <t:formvalid formid="messagefrom" dialog="true" usePlugin="password" layout="table"
               action="acctListController.do?doAdd">

    <table>
          <tr>
            <td align="right" style="width:112px"><label class="Validform_label">名称</label></td>
            <td >
                <input id="acctListName" name="acctListName" style="width:300px" >
                <span class="Validform_checktip">必填</span>
                <label class="Validform_label" style="display: none;">名称</label>
            </td>
          </tr>
          <tr>
            <td align="right" style="width:112px"><label class="Validform_label">图片</label></td>

            <td class="value">
              <div>

                <div class="appmsg_content" id="js_appmsg_preview">
                  <div class="appmsg_info">
                    <em class="appmsg_date"></em>
                  </div>
                  <div id="files" class="files">
                    <i class="appmsg_thumb1">
                      <%--<img src="${weixinAccountPage.QRcode}"  width="150px" height="150px"/>--%>
                        <i class="appmsg_thumb default" width="150px" height="150px">logo图片</i>
                    </i>
                  </div>
                  <div id="progress" class="progress">--%>
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
                      <input id="imagePath" name=imagePath type="hidden" datatype="*" nullmsg="请添加图片">
                      <input id="imageRelativeUrl" name=imageRelativeUrl type="hidden">
                  </span>
                  <span id="imgName"></span>
                  <span class="Validform_checktip"></span>
                  <label class="Validform_label" style="display: none;">图片链接</label>
              </div>
              <br><span class="Validform_checktip1" style="color:#999">建议图片规格:750像素*200像素；jpg、png</span>
            </td>
          </tr>
          <tr>
               <td align="right" style="width:112px"><label class="Validform_label">地址</label></td>
          <td>  
      <input id="text_" type="text"  name="point"style="margin-right:100px;" onblur="searchByStationName()" />
                   
                    <input type="hidden" name="coordinates" id="result_">
                     <div id="container" style="width:600px;height:200px"></div>
            </td>
          </tr>
 
           <tr>
            <td align="right" style="width:112px"><label class="Validform_label">商城地址</label></td>
            <td><input type="text" id="address" name="shoppAddress"/>
            <span class="Validform_checktip1" style="color:#999">商城地址链接</span>
            </td>
          </tr>
         <%--  <tr>
           

            <td class="value">
              <div>
                <div class="appmsg_content" id="js_appmsg_preview1">
                  <div class="appmsg_info">
                    <em class="appmsg_date"></em>
                  </div>
                  <div id="files1" class="files">
                    <i class="appmsg_thumb1">
                      <img src="${weixinAccountPage.logoAccount}" width="150px"  height="150px"/>
                        <!-- <i class="appmsg_thumb default" width="150px",height="150px">点击添加商家地图</i> -->
                    <input type="text" value='${address}' name="coordinates"/><a href="${pageContext.request.contextPath}/redirect.action">点击添加商家地图</a>
                    </i>
                  </div>
                  <div id="progress1" class="progress">
                    <div class="progress-bar progress-bar-success"></div>
                  </div>
                </div>
              </div>
              <br>

              <div>
               <!--  <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>浏览</span>
                    The file input field used as target for the file upload widget
                    <input id="fileupload1" type="file" name="files[]" multiple>
                    <input id="imagePath1" name=imagePath1 type="hidden" datatype="*" nullmsg="请添加图片">
                    <input id="imageRelativeUrl1" name=imageRelativeUrl1 type="hidden">
                </span>
                <span id="imgName1"></span>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">图片链接</label> -->
              </div>
<!--               <br><span class="Validform_checktip1" style="color:#999">建议图片规格:200像素*200像素；jpg、png</span>
 -->            </td>
          </tr> --%>
        　<tr>
            <td align="right"><label class="Validform_label"> 联系电话 </label></td>
            <td class="value"><input class="text" name="phone"  datatype="/^(1\d{10})$|^(\d{3,4}-?\d{7,8})$/" errormsg="联系电话不正确!" ignore="ignore"> <span class="Validform_checktip"></span></td>
        　</tr>
          <tr id="defaulteditor" >
            <td align="right" style="width:112px;">
                <label class="Validform_label">
                    商家介绍
                </label>
            </td>
            <td class="value">
                <textarea name="description" id="description"></textarea>
                <script type="text/javascript">
                    var editor = UE.getEditor('description');
                </script>
            </td>
          </tr>
    </table>
  </t:formvalid>
</div>

</html>

<%--<script type="text/javascript">
  function addAcctList(){
    var acctlistName=$("#acctListName").val();
    var imageRelativeUrl=$("#imageRelativeUrl").val();
    var address=$("#address").val();
    var phone=$("#phone").val();
    var description=$("#description").val();
    var url="AcctListController.do?doAdd";


    $.ajax({
      url : url,
      type :"GET",
      dataType : "json",
      contentType : "application/json; charset=utf-8",
      data:{
        "acctlistName":acctlistName,
        "imageRelativeUrl":imageRelativeUrl,
        "address":address,
        "phone":phone,
        "description":description,
      },
      success:function(data){
        /*alert(data.msg);
         },error:function(error){
         alert(error.msg);*/
        if(data.success){

          tip(data);
        }
      }
    });
  }

</script>--%>

<script type="text/javascript"> 


var map = new BMap.Map("container");
map.centerAndZoom("宁波", 12);
map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
//map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开

var localSearch = new BMap.LocalSearch(map);
localSearch.enableAutoViewport(); //允许自动调节窗体大小
function searchByStationName() {
map.clearOverlays();//清空原来的标注
var keyword = document.getElementById("text_").value;
localSearch.setSearchCompleteCallback(function (searchResult) {
    var poi = searchResult.getPoi(0);
    document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat;
    map.centerAndZoom(poi.point, 13);
    var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
    map.addOverlay(marker);
    var content = document.getElementById("text_").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
    var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
    marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
    // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
});
localSearch.search(keyword);
} 
</script>
