﻿<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>微信单图消息</title>
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
  <script type="text/javascript"
	src="plug-in/jquery-plugs/form/jquery.form.js"></script>
	 <script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js">
    
  <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
  <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
  <link href="plug-in/weixin/button.css" type="text/css" rel="stylesheet"/>
  <script type="text/javascript">
  //编写自定义JS代码
/*jslint unparam: true, regexp: true */
/*global window, $ */
  function subcom(jumptype){
	  if(jumptype=="default" || jumptype=="默认页面"){
		  document.getElementById("innerurl").style.display="none";
		  document.getElementById("outurl").style.display="none";
		  document.getElementById("defaulteditor").style.display="block";
		  return
	  }
	  if(jumptype=="outurl" || jumptype=="外部页面"){
		  document.getElementById("innerurl").style.display="none";
		  document.getElementById("outurl").style.display="block";
		  document.getElementById("defaulteditor").style.display="none";
		  return
	  }
	  if(jumptype=="flowpage" || jumptype=="流量页面"){
		  document.getElementById("innerurl").style.display="block";
		  document.getElementById("outurl").style.display="none";
		  document.getElementById("defaulteditor").style.display="none";
		  $.ajax({
			  type: "POST",
			  url: 'weixinUrlController.do?weixinUrlFlow',
			  cache: false,
			  async: true,
			  dataType: "json",
			  success: function (data) {
				  $("#pagetype").combobox("loadData", data);
				  return;
			  }
		  });
		  return;
	  }
	  if(jumptype=="lottery" || jumptype=="活动页面"){
		  document.getElementById("innerurl").style.display="block";
		  document.getElementById("outurl").style.display="none";
		  document.getElementById("defaulteditor").style.display="none";
		  $.ajax({
			  type: "POST",
			  url: 'weixinUrlController.do?weixinUrlLottery',
			  cache: false,
			  async: true,
			  dataType: "json",
			  success: function (data) {
				  $("#pagetype").combobox("loadData", data);
				  return;
			  }
		  });
		  return
	  }
  }
$(function () {
	// 下拉框选择控件，下拉框的内容是动态查询数据库信息
	$('#jumptype').combobox({
		url: 'weixinUrlController.do?weixinUrlFirst',
		editable: false, //不可编辑状态
		cache: false,
//        panelHeight: '150',
		valueField: 'url',
		textField: 'name',
		resizable:true,
		onSelect: function () {
			var jumptypevalue = $('#jumptype').combobox('getValue');
			if(jumptypevalue=="default"){
//				document.all.innerurl.style.display='none'
				document.getElementById("innerurl").style.display="none";
				document.getElementById("outurl").style.display="none";
				document.getElementById("defaulteditor").style.display="block";
				return
			}
			if(jumptypevalue=="outurl"){
				document.getElementById("innerurl").style.display="none";
				document.getElementById("outurl").style.display="block";
				document.getElementById("defaulteditor").style.display="none";
				return
			}
			if(jumptypevalue=="flowpage"){
				document.getElementById("innerurl").style.display="block";
				document.getElementById("outurl").style.display="none";
				document.getElementById("defaulteditor").style.display="none";
				$("#pagetype").combobox("setValue", '');
				$.ajax({
					type: "POST",
					url: 'weixinUrlController.do?weixinUrlFlow',
					cache: false,
					async: true,
					dataType: "json",
					success: function (data) {
						$("#pagetype").combobox("loadData", data);
						return;
					}
				});
				return;
			}
			if(jumptypevalue=="lottery"){
				document.getElementById("innerurl").style.display="block";
				document.getElementById("outurl").style.display="none";
				document.getElementById("defaulteditor").style.display="none";
				$("#pagetype").combobox("setValue", '');
				$.ajax({
					type: "POST",
					url: 'weixinUrlController.do?weixinUrlLottery',
					cache: false,
					async: true,
					dataType: "json",
					success: function (data) {
						$("#pagetype").combobox("loadData", data);
						return;
					}
				});
				return
			}
		}
	});
	//将上述查询的结果绑定在下面的combobox中
	$('#pagetype').combobox({
		editable: false, //不可编辑状态
		cache: false,
		valueField: 'url',
		resizable:true,
		textField: 'name',
		itemIndex:0
	});


	var jump='${weixinArticle.urlType}';

	switch (jump) {
		case "outurl":
			$("#jumptype").combobox("setValue", "外部页面");
			document.getElementById("outurl").style.display = "block";
			break;
		case"default":
			$("#jumptype").combobox("setValue", "默认页面");
			document.getElementById("defaulteditor").style.display = "block";
			break;
		case"flowpage":
			$("#jumptype").combobox("setValue", "流量页面");
			document.getElementById("innerurl").style.display = "block";
			break;
		case"lottery":
			$("#jumptype").combobox("setValue", "活动页面");
			document.getElementById("innerurl").style.display = "block";
			break;
	}

	var jumptype = $('#jumptype').combobox('getValue');
	subcom(jumptype);
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
        var  file = data.files[0];
        //var delUrl = "<a class=\"js_removeCover\" onclick=\"return false;\" href=\"javascript:void(0);\">删除</a>";
        $("#imgName").text("").append(file.name);
        $("#progress").hide();
		var d = data.result;
		if (d.success) {
			var link = $('<a>').attr('target', '_blank').prop('href', d.attributes.viewhref);
        	$(data.context.children()[0]).wrap(link);
        	//console.info(d.attributes.viewhref);
        	$("#imagePath").val(d.attributes.url);
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
    var submit=function(){
		return true;
	}
	var showResponse=function(data){
		if (data.success) {
			tip("保存成功");
			setTimeout(function(){
				window.location.reload();
			},3000);
a
		} else {
			tip("保存失败");
			setTimeout(function(){
				window.location.reload();
			},3000);
		}
	}    	
    var options = {  
	        beforeSubmit:  submit,  //提交前处理 
	        success:       showResponse,  //处理完成 
	        resetForm: true,  
	        url: 'weixinArticleController.do?doUpdate',
	        dataType:  'json'  
	   };  
	    $("#saveButton").click(function () {
	           $("#formobjNew").ajaxSubmit(options);
				//window.location.reload();
	           return false;
	     });

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
									onclick="return false;">${weixinArticle.title}</a>
							</h4>
							<div class="appmsg_info">
								<em class="appmsg_date"></em>
							</div>
							<div id="files" class="files">
								<i style="height:160px;overflow:hidden;display:inline-block;width:100%;"><img src="${mediaurl}/${weixinArticle.imagePath}" width="100%" height="160" /></i>
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
		  		<t:formvalid formid="formobjNew" dialog="true" usePlugin="password" layout="table" action="weixinArticleController.do?doUpdate" tiptype="1">
							<input id="id" name="id" type="hidden" value="${weixinArticle.id }">
				<table style="width: 500px;" cellpadding="0" cellspacing="1" class="formtable">
						<tr>
							<td align="right">
								<label class="Validform_label">
									标题:
								</label>
							</td>
							<td class="value">
							     	 <input id="title" name="title" type="text" value="${weixinArticle.title}" style="width: 300px" class="inputxt"  datatype="*">
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">标题</label>
								</td>
						</tr>
						<tr>
							<td align="right">
								<label class="Validform_label">
									作者:
								</label>
							</td>
							<td class="value">
							     	 <input id="author" name="author" value="${weixinArticle.author}" type="text" style="width: 300px" class="inputxt" >
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">作者</label>
								</td>
						</tr>
						<tr>
							<td align="right">
								<label class="Validform_label">
									图片链接:
								</label>
							</td>
							<td class="value">
								<span class="btn btn-success fileinput-button">
							        <i class="glyphicon glyphicon-plus"></i>
							        <span>浏览</span>
							        <!-- The file input field used as target for the file upload widget -->
							        <input id="fileupload" type="file" name="files[]" multiple>
							        <input id="imagePath" name=imagePath type="hidden" value="${mediaurl}/${weixinArticle.imagePath}" datatype="*" nullmsg="请添加图片">
							    </span>
							    <span id="imgName"></span> 
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">图片链接</label>
								<br><span class="Validform_checktip1" style="color:#999">建议图片规格:第一个图文：900像素*500像素,其他：200像素*200像素；jpg、png</span>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="Validform_label">
									摘要:
								</label>
							</td>
							<td class="value">
							     	 <input id="description" name="description" value="${weixinArticle.description}" type="text" style="width: 300px" class="inputxt" >
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">摘要</label>
								</td>
						</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								顺序:
							</label>
						</td>
						<td class="value">
							<input id="orders" name="orders" type="text" style="width: 150px" class="inputxt" value="${weixinArticle.orders}" datatype="*" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">顺序</label>
						</td>
					</tr>



						<%--<tr>--%>
							<%--<td align="right">--%>
								<%--<label class="Validform_label">--%>
									<%--直接访问地址:--%>
								<%--</label>--%>
							<%--</td>--%>
							<%--<td class="value">--%>
							      <%--<input title="复制后可做微信菜单直接访问" id="dizhi" name="dizhi" type="text" style="width: 250px" value="${webRoot}/newsItemController.do?newscontent&id=${weixinArticle.id}" ><a target="_blank" href="${webRoot}/newsItemController.do?newscontent&id=${weixinArticle.id}">访问</a>--%>
							<%--</td>--%>
						<%--</tr>--%>

					<tr>
						<td align="right">
							<label class="Validform_label">
								跳转类型:
							</label>
						</td>
						<td class="value">
							<input  class="inputxt" id="jumptype" name="jumptype" style="width: 305px;height:30px;"  value="${weixinArticle.urlType}" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">跳转类型</label>
						</td>
					</tr>
					</table>
					<table style="width: 500px;" cellpadding="0" cellspacing="1" class="formtable">
						<tr id="innerurl" style="display: none;">
							<td align="right" style="width:69px;">
								<label class="Validform_label">
									页面类型:
								</label>
							</td>
							<td class="value"  style="width:432px;">
								<input  class="inputxt" id="pagetype" name="pagetype" style="width: 305px;height:30px;"  value="${weixinArticle.url}" >
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">页面类型</label>
							</td>
						</tr>


						<tr  id="outurl"  style="display: none;">
							<td align="right">
								<label class="Validform_label">
									外部链接:
								</label>
							</td>
							<td class="value">
								<input id="url" name="url" type="text" style="width: 300px" class="inputxt" value="${weixinArticle.url}" >
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">外部链接</label>
							</td>
						</tr>
						<tr id="defaulteditor" style="display: none;" >
							<td align="right">
								<label class="Validform_label">
									正文:
								</label>
							</td>
							<td class="value">
								<textarea name="content" id="content">${weixinArticle.content}</textarea>
								<script type="text/javascript">
									var editor = UE.getEditor('content');
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
  <div class="tool_area">
	<div class="tool_bar border tc">
		<span class="btn btn-success fileinput-button" id="saveButton">
	        <i class="glyphicon glyphicon-plus"></i>
	        <span>保存</span>		      
		</span>
	</div>
  </div>
 </body>
  <script src = "webpage/weixin/guanjia/newstemplate/weixinArticle.js"></script>		