<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<title>查看广告素材</title>
<t:base type="jquery,easyui,tools"></t:base>
<link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css" />
<link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css" />
<link type="text/css" rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="plug-in/weixin/button.css" />
<!--     <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script> -->

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
<script type="text/javascript" src="plug-in/jquery-plugs/form/jquery.form.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
        $(function () {
            $('#urlType').combobox({
        		url: 'dataDict.do?urlType',
        		editable: false, //不可编辑状态
        		cache: false,
        		valueField: 'code',
        		textField: 'name',
        		resizable:true,
        		onSelect: function () {
        			var urlType = $('#urlType').combobox('getValue');
        			if(urlType=="inner"){
        				$("#inner").css("display","block");
        				$("#outer").css("display","none");
        				return;
        			}
        			if(urlType=="outer"){
        				$("#inner").css("display","none");
        				$("#outer").css("display","block");
        				return
        			}
        		}
        	});
            $('#materialType').combobox({
        		url: 'dataDict.do?materialType',
        		editable: false, //不可编辑状态
        		cache: false,
        		valueField: 'code',
        		textField: 'name',
        		resizable:true
        	});
        $("#materialType").combobox("setValue", "${material.materialType}");
        $("#urlType").combobox("setValue", "${material.urlType}");

		var urlType = $('#urlType').combobox('getValue');
		if(urlType=="inner"){
			$("#inner").css("display","block");
			$("#outer").css("display","none");
		}
		if(urlType=="outer"){
			$("#inner").css("display","none");
			$("#outer").css("display","block");
		}

        var editor = UE.getEditor('content',{readonly:true});
        });
    </script>
</head>
<body>
  <div style="display: none;"></div>
  <div class="main_bd">
    <div class="media_edit_area" id="js_appmsg_editor">
      <div class="appmsg_editor" style="margin-top: 0px;">
        <div class="inner">
          <t:formvalid formid="formobjNew" dialog="true" usePlugin="password" layout="table" action="materialController.do?addOrUpdate"
            tiptype="1">
            <input id="id" name="id" type="hidden" value="${material.id }">
            <table style="width: 450px;" cellpadding="0" cellspacing="1" class="formtable">
              <tr>
                <td align="right"><label class="Validform_label">素材名称:</label></td>
                <td class="value"><input id="title" name="title" type="text" disable="disable"  value="${material.title }" style="width: 300px;height:30px;" class="inputxt" datatype="*"
                    nullmsg="素材名称不能为空！"> <span class="Validform_checktip"></span> <label class="Validform_label"
                    style="display: none;">素材名称</label></td>
              </tr>
              <tr>
                <td align="right"><label class="Validform_label">缩略图:</label></td>
                <td class="value"><img src="${material.pic}" width="300"></td>
              </tr>
              <td align="right"><label class="Validform_label"> 素材类型: </label></td>
              <td class="value"><input class="inputxt" id="materialType" name="materialType" disable="disable"  style="width: 305px; height: 30px;"
                  value=""> <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;">素材类型</label></td>
              <tr>
                <td align="right"><label class="Validform_label"> 跳转类型: </label></td>
                <td class="value"><input class="inputxt" id="urlType" disable="disable" name="urlType" style="width: 305px; height: 30px;" value="${material.urlType }">
                  <span class="Validform_checktip"></span> <label class="Validform_label" style="display: none;">跳转类型</label></td>
              </tr>
            </tbody>
          </table>
            <table style="width: 500px;" cellpadding="0" cellspacing="1" class="formtable">
            <tbody id="writeContent">
              <tr id="outer" style="display: none;">
                <td align="right" style="width: 60px"><label class="Validform_label"> 外部链接: </label></td>
                <td class="value"><input id="url" name="url" type="text" style="width: 305px;height:30px;" value="${material.url}" class="inputxt">
                  </td>
              </tr>
              <tr id="inner" >
                <td align="right" style="width: 60px"><label class="Validform_label"> 正文: </label></td>
                <td class="value"><textarea name="content" disable="disable" id="content">${material.content}</textarea></td>
              </tr>
              <tr>
                  <td align="left" style="width: 60px">
                      <strong>素材提交审核：</strong>
                  </td>
                  <td class="value">
                                                是： <input type="radio" checked="checked" name="toaudit" value="true"/>
                                                否： <input type="radio" name="toaudit" value="false"/>
                  </td>
              </tr>
              </tbody>
              </table>
          </t:formvalid>
        </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
	
</script>
<script src="webpage/weixin/guanjia/newstemplate/weixinArticle.js"></script>