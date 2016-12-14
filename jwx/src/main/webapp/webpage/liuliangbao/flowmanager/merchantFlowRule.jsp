<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>流量规则设置</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/appmsg_edit.css"/>
    <link type="text/css" rel="stylesheet" href="plug-in/weixin/css/jquery.fileupload.css"/>
    <link type="text/css" rel="stylesheet" href="plug-in/liuliangbao/css/1228/bootstrap.min.css"/>
    <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>

    <!--UEditor-->
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="plug-in/jquery-plugs/form/jquery.form.js"></script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
    <link href="plug-in/weixin/button.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript">
        function save() {
            var subscribe = document.getElementById("subscribe").value;
            var sign = document.getElementById("sign").value;
            var subCount=document.getElementById("subscribeCount").value;
            var signCount=document.getElementById("signCount").value;
            var share=document.getElementById("share").value;
            var shareCount= 0;
            if (document.getElementById("shareCount")) {
                document.getElementById("shareCount").value;
            }

            var shareBind=document.getElementById("shareBind").value;
            var shareBindCount=document.getElementById("shareBindCount").value;

            var myreg =  /^(0|0.[0-9]{0,1}|([1-9][0-9]*)+(\.{0,1}[0-9]{0,1}))$/;
            var myreg1= /^(0|[1-9][0-9]*)$/;
            var subscribeFlowType=$('input:radio[name="subscribeFlowType"]:checked').val();
            var signFlowType=$('input:radio[name="signFlowType"]:checked').val();
            var shareFlowType=$('input:radio[name="shareFlowType"]:checked').val();
            var shareBindFlowType=$('input:radio[name="shareBindFlowType"]:checked').val();

           if (!myreg.test(sign)) {
                $.messager.show({title: '提示信息',msg: '签到送流量值不合法!',showType: 'show'});
                return false;
           } else if (!myreg.test(subscribe)) {
                $.messager.show({title: '提示信息',msg: '关注送流量值不合法!',showType: 'show'});
                return false;
           } else if (!myreg.test(share)) {
                $.messager.show({title: '提示信息',msg: '分享立即送流量值不合法!',showType: 'show'});
                return false;
           } else if (!myreg.test(subCount)) {
                $.messager.show({title: '提示信息',msg: '关注最大次数值不合法!',showType: 'show'});
                return false;
           } else if (!myreg.test(signCount)) {
                $.messager.show({title: '提示信息',msg: '签到最大次数值不合法!',showType: 'show'});
                return false;
           } /*else if (!myreg.test(shareCount)) {
                $.messager.show({title: '提示信息',msg: '分享立即最大次数值不合法',showType: 'show'});
                return false;
           } */else if (!myreg.test(shareBindCount)) {
                $.messager.show({title: '提示信息',msg: '分享绑定最大次数值不合法!',showType: 'show'});
                return false;
           }


            {
                if(subscribeFlowType!=null && signFlowType!=null && shareFlowType!=null && shareBindFlowType!=null){


                $.ajax({
                    type: 'POST',
                    url: "merchantFlowController.do?saveMerchantFlowRule",
                    dataType: "JSON",
                    data: {
                        "subscribe": subscribe,
                        "sign": sign,
                        "accountid": '${accountid}',
                        "subCount":subCount,
                        "signCount":signCount,

                        "share":share,
                        "shareCount":shareCount,
                        "shareFlowType":shareFlowType,

                        "shareBind":shareBind,
                        "shareBindCount":shareBindCount,
                        "shareBindFlowType":shareBindFlowType,

                        "subscribeFlowType":subscribeFlowType,
                        "signFlowType":signFlowType,
                    },
                    cache: false,
                    error: function () {
                        $.messager.show({
                            title: '提示信息',
                            msg: '保存失败，请稍后重试！',
                            showType: 'show'
                        });
                        return false;
                    },
                    success: function (data) {
//                        alert(data.msg);
                        $.messager.show({
                            title: '提示信息',
                            msg: data.msg,
                            showType: 'show'
                        });

                    }
                });

                }else{
                    $.messager.show({
                        title: '提示信息',
                        msg: '请选择流量类型',
                        showType: 'show'
                    });
                }
            }
        }
    </script>

</head>
<body>
<div style="display: none;">

    <input type="hidden" id="accountid" name="accountid" value="${accountid}">
</div>
<div class="main_bd">

    <div class="media_edit_area" id="js_appmsg_editor">
        <div class="appmsg_editor" style="margin-top: 0px;">
            <div class="inner">
                <t:formvalid formid="formobjNew" dialog="true" usePlugin="password" layout="table"
                              tiptype="1">
                    <%--<input type="hidden" value="${shareItem.id}" id="bid" name="bid">--%>
                    <input id="id" name="id" type="hidden" value="${shareItem.id}">
                    <table style="width: 450px;" cellpadding="0" cellspacing="1" class="formtable">
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    关注送流量:
                                </label>
                            </td>
                            <td class="value">
                                <input id="subscribe" name="subscribe" type="text" value="${subsribeFlow}"
                                       style="width: 300px" class="inputxt" datatype="*" maxlength="9"
                                       onkeyup="value=value.replace(/[^\d.]/g,'')">
                                <span class="Validform_checktip"></span>M
                                <label class="Validform_label" style="display: none;">关注送流量</label>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    流量类型:
                                </label>
                            </td>
                            <td class="value" colspan="2">
                                <input type="radio" value="1" name="subscribeFlowType" id="subscribeFlowType" datatype="*"  <c:if test="${subscribeFlowType=='1'}">checked="checked"</c:if>/>全国流量
                                <input type="radio" value="2" name="subscribeFlowType"   <c:if test="${subscribeFlowType=='2'}">checked="checked"</c:if>/>省内流量
                                <span class="Validform_checktip">请选择流量类型</span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    关注最大次数:
                                </label>
                            </td>
                            <td class="value">
                                <input id="subscribeCount" name="subscribeCount" value="${subsribeCount}" type="text"
                                       style="width: 300px" maxlength="9"
                                       class="inputxt" datatype="*" onkeyup="value=value.replace(/[^\d]/g,'')">
                                <span class="Validform_checktip"></span>
                                <label class="Validform_label" style="display: none;">关注最大次数</label>
                            </td>
                        </tr>
                        <tr >
                            <td>
                            </td>
                            <td class="value">
                                <font color="red">填0代表最大关注人数不受限制</font>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    签到送流量:
                                </label>
                            </td>
                            <td class="value">
                                <input id="sign" name="sign" type="text" value="${signFlow}"
                                       style="width: 300px" class="inputxt" datatype="*" maxlength="9"
                                       onkeyup="value=value.replace(/[^\d.]/g,'')">
                                <span class="Validform_checktip"></span>M
                                <label class="Validform_label" style="display: none;">签到送流量</label>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    流量类型:
                                </label>
                            </td>
                            <td class="value" colspan="2">
                                <input type="radio" value="1" name="signFlowType" id="signFlowType" datatype="*"  <c:if test="${signFlowType=='1'}">checked="checked"</c:if>/>全国流量
                                <input type="radio" value="2" name="signFlowType"  <c:if test="${signFlowType=='2'}">checked="checked"</c:if>/>省内流量
                                <span class="Validform_checktip">请选择流量类型</span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    每天签到最大次数:
                                </label>
                            </td>
                            <td class="value">
                                <input id="signCount" name="signCount" value="${signCount}" type="text"
                                style="width: 300px" maxlength="9"
                                class="inputxt" datatype="*" onkeyup="value=value.replace(/[^\d]/g,'')">
                                <span class="Validform_checktip"></span>
                                <label class="Validform_label" style="display: none;">签到最大次数</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td class="value">
                                <font color="red">填0代表最大签到人数不受限制</font>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    分享立即送流量:
                                </label>
                            </td>
                            <td class="value">
                                <input id="share" name="share" type="text" value="${shareFlow}"
                                       style="width: 300px" class="inputxt" datatype="*" maxlength="9"
                                       onkeyup="value=value.replace(/[^\d.]/g,'')">
                                <span class="Validform_checktip"></span>M
                                <label class="Validform_label" style="display: none;">分享送流量</label>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    流量类型:
                                </label>
                            </td>
                            <td class="value" colspan="2">
                                <input type="radio" value="1" name="shareFlowType" id="shareFlowType" datatype="*"  <c:if test="${shareFlowType=='1'}">checked="checked"</c:if>/>全国流量
                                <input type="radio" value="2" name="shareFlowType"  <c:if test="${shareFlowType=='2'}">checked="checked"</c:if>/>省内流量
                                <span class="Validform_checktip">请选择流量类型</span>
                            </td>
                        </tr>

                        <%-- 暂时没什么作用, 先去掉.<tr>
                            <td align="right">
                                <label class="Validform_label">
                                    分享最大次数:
                                </label>
                            </td>
                            <td class="value">
                                <input id="shareCount" name="shareCount" value="${shareCount}" type="text"
                                       style="width: 300px" maxlength="9"
                                       class="inputxt" datatype="*" onkeyup="value=value.replace(/[^\d]/g,'')">
                                <span class="Validform_checktip"></span>
                                <label class="Validform_label" style="display: none;">分享最大次数</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td class="value">
                                <font color="red">填0代表最大分享人数不受限制</font>
                            </td>
                        </tr>--%>
                        <%-- 绑定送分享流量 --%>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    分享绑定送流量:
                                </label>
                            </td>
                            <td class="value">
                                <input id="shareBind" name="shareBind" type="text" value="${shareBindFlow}"
                                       style="width: 300px" class="inputxt" datatype="*" maxlength="9"
                                       onkeyup="value=value.replace(/[^\d.]/g,'')">
                                <span class="Validform_checktip"></span>M
                                <label class="Validform_label" style="display: none;">分享绑定送流量</label>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    流量类型:
                                </label>
                            </td>
                            <td class="value" colspan="2">
                                <input type="radio" value="1" name="shareBindFlowType" id="shareBindFlowType" datatype="*"  <c:if test="${shareBindFlowType=='1'}">checked="checked"</c:if>/>全国流量
                                <input type="radio" value="2" name="shareBindFlowType"  <c:if test="${shareBindFlowType=='2'}">checked="checked"</c:if>/>省内流量
                                <span class="Validform_checktip">请选择流量类型:</span>
                            </td>
                        </tr>

                        <tr>
                            <td align="right">
                                <label class="Validform_label">
                                    分享绑定最大次数:
                                </label>
                            </td>
                            <td class="value">
                                <input id="shareBindCount" name="shareBindCount" value="${shareBindCount}" type="text"
                                       style="width: 300px" maxlength="9"
                                       class="inputxt" datatype="*" onkeyup="value=value.replace(/[^\d]/g,'')">
                                <span class="Validform_checktip"></span>
                                <label class="Validform_label" style="display: none;">分享绑定最大次数</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td class="value">
                                <font color="red">填0代表最大分享绑定人数不受限制</font>
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
		<span class="btn btn-success fileinput-button" id="saveButton" onclick="save()">
	        <i class="glyphicon glyphicon-plus"></i>
	        <span>保存</span>
		</span>
    </div>
</div>
</body>