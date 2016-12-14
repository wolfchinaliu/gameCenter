<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>拾流科技后台管理系统</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="oa,oa系统,免费微信营销系统,微信营销"/>
    <meta name="description" content="强大的微信营销系统"/>

    <%--<link rel="stylesheet" href="plug-in/accordion/css/icons.css" type="text/css"></link>
     --%>
    <link rel="stylesheet" href="plug-in/accordion/css/accordion.css" type="text/css"></link>

    <link href="easyui/jquery-easyui-theme/icon.css" rel="stylesheet" type="text/css"/>

    <link href="easyui/jquery-easyui-theme/default/easyui.css" rel="stylesheet" type="text/css"/>

    <link href="easyui/icons/icon-all.css" rel="stylesheet" type="text/css"/>

    <script src="easyui/jquery/jquery-1.11.1.js" type="text/javascript"></script>
    <!--<script src="easyui/jquery/jquery-1.11.1.min.js"></script>-->

    <script src="easyui/jquery-easyui-1.3.6/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="easyui/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

    <script src="easyui/jquery.jdirk.js" type="text/javascript"></script>
    <!--<script src="easyui/release/jquery.jdirk.min.js"></script>-->

    <link href="easyui/jeasyui-extensions/jeasyui.extensions.css" rel="stylesheet" type="text/css"/>

    <script src="easyui/jeasyui-extensions/jeasyui.extensions.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.progressbar.js"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.slider.js"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.linkbutton.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.form.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.validatebox.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.combo.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.combobox.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.menu.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.searchbox.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.panel.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.window.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.dialog.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.layout.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.tree.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.datagrid.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.treegrid.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.combogrid.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.combotree.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.tabs.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.theme.js" type="text/javascript"></script>
    <!--<script src="easyui/release/jeasyui.extensions.all.min.js"></script>-->

    <script src="easyui/icons/jeasyui.icons.all.js" type="text/javascript"></script>
    <!--<script src="easyui/release/jeasyui.icons.all.min.js"></script>-->

    <script src="easyui/jeasyui-extensions/jeasyui.extensions.icons.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jeasyui.extensions.gridselector.js" type="text/javascript"></script>
    <script src="easyui/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

    <script src="easyui/jeasyui-extensions/jquery.toolbar.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jquery.comboicons.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jquery.comboselector.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jquery.my97.js" type="text/javascript"></script>
    <script src="easyui/jeasyui-extensions/jquery.portal.js" type="text/javascript"></script>

    <!--导入首页启动时需要的相应资源文件(首页相应功能的 js 库、css样式以及渲染首页界面的 js 文件)-->
    <link href="easyui/common/index.css" rel="stylesheet"/>
    <script src="easyui/common/index.js" type="text/javascript"></script>
    <script src="easyui/common/index-startup.js"></script>
    <script type="text/javascript">
        $(function () {
            window.onbeforeunload = null;
        })
    </script>
    <style type="text/css">
        a {
            color: Black;
            text-decoration: none;
        }

        a:hover {
            color: black;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div id="dd"></div>
<div id="maskContainer">
    <div class="datagrid-mask" style="display:block;"></div>
    <div class="datagrid-mask-msg" style="display: block; left: 50%; margin-left: -52.5px;">
        正在加载...
    </div>
</div>

<div id="mainLayout" class="easyui-layout hidden" data-options="fit: true">

    <div id="northPanel" data-options="region: 'north', border: false" style="height: 80px; overflow: hidden;">
        <div id="topbar" class="top-bar">
            <div class="top-bar-left">
                <h1 style="margin-left: 20px; margin-top: 10px;">
                    <img src="${WeixinIndividualization.logo }" style="height: 30px; vertical-align: top; " alt="" />
                    <span style="font-size: 18px; vertical-align: top; margin-left: 10px; line-height: 34px;">${WeixinIndividualization.name }管理平台</span>
                </h1>
            </div>
            <div class="top-bar-right">
                <div id="timerSpan"></div>
                <%--<div id="themeSpan">--%>

                    <%--<span>更换皮肤风格：</span>--%>
                    <%--<select id="themeSelector"></select>--%>

                    <%--<a id="btnHideNorth" class="easyui-linkbutton"--%>
                       <%--data-options="plain: true, iconCls: 'layout-button-up'"></a>--%>
                <%--</div>--%>
            </div>
        </div>
        <div id="toolbar" class="panel-header panel-header-noborder top-toolbar">
            <div id="infobar">
                    <span class="icon-hamburg-user" style="padding-left: 25px; background-position: left center;">
                        <%--${userName }-${roleName } [${WEIXIN_ACCOUNT.accountname }]--%>
                        <%--不显示商户等级--%>
                        ${userName }[${weixinAccount.accountname }] 
                    </span>
                    <span style="padding-left: 25px; background-position: left center;">
                       
                                               全国流量:${weixinAcctFlowChargePage.countryFlowValue}M 省内流量:${weixinAcctFlowChargePage.provinceFlowValue}M
                    </span>
            </div>
            <%--
            <div id="searchbar">
                <input id="topSearchbox" name="topSearchbox" class="easyui-searchbox" data-options="width: 350, height: 26, prompt: '请输入您要查找的内容关键词', menu: '#topSearchboxMenu'" />
                <div id="topSearchboxMenu" style="width: 85px;">
                    <div data-options="name:'0', iconCls: 'icon-hamburg-zoom'">查询类型</div>
                    <div data-options="name:'1'">测试类型1</div>
                    <div data-options="name:'2'">测试类型2</div>
                    <div data-options="name:'3'">测试类型3</div>
                    <div data-options="name:'4'">测试类型4</div>
                </div>
            </div>--%>
            <div id="buttonbar">
                <%--<a id="btn8" class="easyui-linkbutton"--%>
                   <%--data-options="plain: true, iconCls: 'icon-hamburg-contact', tooltip: '您有新消息!'">消息留言</a>--%>
                <%--<a id="btn2" class="easyui-linkbutton"--%>
                   <%--data-options="plain: true, iconCls: 'icon-hamburg-bug', tooltip: '前往帮助中心；可以进行问题反馈提交或留言操作。'">帮助文档</a>--%>
                <a id="btnKey" class="easyui-linkbutton"
                   data-options="plain: true, iconCls: 'icon-metro-key', tooltip: '前往查看、修改您的数据密钥。'">数据密钥</a>
                <a id="btn3" class="easyui-linkbutton"
                   data-options="plain: true, iconCls: 'icon-hamburg-archives', tooltip: '前往修改您的登录密码。'">修改密码</a>
                <%--<a id="btnFullScreen" class="easyui-linkbutton"--%>
                   <%--data-options="plain: true, iconCls: 'icon-standard-arrow-inout'">全屏切换</a>--%>
                <a id="btnExit" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-sign-out'">退出系统</a>
                <a id="btnShowNorth" class="easyui-linkbutton" data-options="plain: true, iconCls: 'layout-button-down'"
                   style="display: none;"></a>
            </div>
        </div>
    </div>

    <div data-options="region: 'west', title: '菜单导航栏', iconCls: 'icon-standard-map', split: true, minWidth: 200, maxWidth: 500"
         style="width: 200px; padding: 1px;">

            <div id="westLayout" class="easyui-layout" data-options="fit: true">
                <div data-options="region: 'center', border: false" style="border-bottom-width: 0px;">
                    <div id="westCenterLayout" class="easyui-layout" data-options="fit: true">
                        <div data-options="region: 'north', split: false, border: false" style="height: 0px;">
                        </div>
                        <div data-options="region: 'center', border: false" style="height: 275px;">
                            <ul id="navMenu_Tree" style="padding-top: 2px; padding-bottom: 2px;"></ul>
                        </div>
                    </div>
                </div>
                <div id="westSouthPanel"
                     data-options="region: 'south', border: false, split: true, minHeight: 0, maxHeight: 0"
                     style="height: 0px; border-top-width: 0px;">
                    <ul id="navMenu_list"></ul>
                </div>

            </div>

    </div>

    <div data-options="region: 'center'" style="padding: 1px;">
        <div id="mainTabs_tools" class="tabs-tool">
            <table>
                <tr>
                    <td><a id="mainTabs_jumpHome" class="easyui-linkbutton easyui-tooltip" title="跳转至主页选项卡"
                           data-options="plain: true, iconCls: 'icon-hamburg-home'"></a></td>
                    <td>
                        <div class="datagrid-btn-separator"></div>
                    </td>
                    <%--<td><a id="mainTabs_toggleAll" class="easyui-linkbutton easyui-tooltip" title="展开/折叠面板使选项卡最大化"--%>
                           <%--data-options="plain: true, iconCls: 'icon-standard-arrow-inout'"></a></td>--%>
                    <%--<td>--%>
                        <%--<div class="datagrid-btn-separator"></div>--%>
                    <%--</td>--%>
                    <%--
                    <td><a id="mainTabs_jumpTab" class="easyui-linkbutton easyui-tooltip" title="在新页面中打开该选项卡" data-options="plain: true, iconCls: 'icon-standard-shape-move-forwards'"></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td><a id="mainTabs_closeTab" class="easyui-linkbutton easyui-tooltip" title="关闭当前选中的选项卡" data-options="plain: true, iconCls: 'icon-standard-application-form-delete'"></a></td>
                    <td><a id="mainTabs_closeOther" class="easyui-linkbutton easyui-tooltip" title="关闭除当前选中外的其他所有选项卡" data-options="plain: true, iconCls: 'icon-standard-cancel'"></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td><a id="mainTabs_closeLeft" class="easyui-linkbutton easyui-tooltip" title="关闭左侧所有选项卡" data-options="plain: true, iconCls: 'icon-standard-tab-close-left'"></a></td>
                    <td><a id="mainTabs_closeRight" class="easyui-linkbutton easyui-tooltip" title="关闭右侧所有选项卡" data-options="plain: true, iconCls: 'icon-standard-tab-close-right'"></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    --%>
                    <td><a id="mainTabs_closeAll" class="easyui-linkbutton easyui-tooltip" title="关闭所有选项卡"
                           data-options="plain: true, iconCls: 'icon-standard-cross'"></a></td>
                </tr>
            </table>
        </div>

        <div id="mainTabs" class="easyui-tabs"
             data-options="fit: true, border: true, showOption: true, enableNewTabMenu: true, tools: '#mainTabs_tools', enableJumpTabMenu: true, onSelect: function (title, index) { window.mainpage.mainTabs.updateHash(index); }">
            <div id="homePanel" data-options="title: '主页', refreshable: false, iconCls: 'icon-hamburg-home'">
                <%--主页start --%>
                <div class="easyui-layout" data-options="fit: true">
                    <%--<div data-options="region: 'north', split: false, border: false" style="height: 33px;">--%>
                    <%--<div class="easyui-toolbar">--%>
                    <%--<a id="A1" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-standard-add'">添加公众账号</a>--%>
                    <%-- <span>-</span>
                    <a id="A2" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-standard-delete'">删除一列</a>
                    <span>-</span>
                    <a id="A3" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-for-job'">测试操作01</a>--%>
                    <%--<span>-</span>--%>
                    <%--<a id="A4" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-config'">配置公众账号</a>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <div data-options="region: 'center', border: false" style="overflow: hidden;">
                        <div id="portal" class="easyui-portal" data-options="fit: true, border: false">
                            <div style="width: 50%;">

                                <div data-options="title: '公众号配置操作指南', height: 220, collapsible: true, closable: true">
                                    <ul class="portlet-list">
                                        <li>公众账号：${weixinAccount.accountname}</li>
                                        <li>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：<c:if
                                                test="${weixinAccount.accounttype == 1}">服务号</c:if><c:if
                                                test="${weixinAccount.accounttype == 2}">订阅号</c:if></li>
                                        <%--<li>接口地址：<%=basePath%>/weixin.do?api&t=${weixinAccount.id }</li>--%>
                                        <li>接口地址：${domain}/weixin.do?api&t=${weixinAccount.id }</li>
                                        <li>TOKEN&nbsp;&nbsp;&nbsp;：${weixinAccount.accounttoken}</li>
                                        <li>商户acctId：${weixinAccount.acctId}</li>
                                        <li>1.【登录 微信公众平台-“开发者中心”，按要求填写接口配置信息】</li>
                                        <li>2.【请到"我的微管"-->"公众账号管理"进行公众号管理】</li>
                                    </ul>
                                </div>

                                <div data-options="title: '新功能发布', height: 220, collapsible: true, closable: true">

                                    <ul class="portlet-list" style="list-style-type: decimal;">
                                        <%--<li>--%>
                                        <%--<span>小马微信摇一摇功能上线了</span>--%>
                                        <%--<ul>--%>
                                        <%--<li>您只需在小马微信后台硬件中心摇一摇模块完成功能设置，再将您的小马微信iBeacon安放在合适的位置，就可以完美支持微信的摇一摇周边功能。</li>--%>
                                        <%--</ul>--%>
                                        <%--</li>--%>
                                        <li>
                                            <span>代理商管理功能升级</span>
                                            <ul>
                                                <li>代理商为下属代理商用户直接充流量</li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>


                            <div style="width: 50%;">
                                <div data-options="title: '关于石榴商盟', height: 220, collapsible: true, closable: true"
                                     style="padding: 10px;">
                                    石榴商盟是一个专门针对微信公众账号提供营销推广服务的第三方平台。主要功能是针对微信商家公众号提供与众不同的、有针对性的营销推广服务。
                                    <br><br>通过石榴商盟平台，用户可以轻松管理自己的微信各类信息，对微信公众账号进行维护、发放优惠劵、刮奖、打造微官网、开启微商城等多种活动，对微信营销实现有效监控，极大扩展潜在客户群，实现企业的运营目标。

                                </div>
                                <div id="donatePanel"
                                     data-options="title: '系统公告', height: 220, closable: true, collapsible: true, closable: true, tools: [{ iconCls: 'icon-hamburg-refresh', handler: function () { loadAnnounce();} }]"
                                     style="padding: 10px;">

                                    <%--
                                    <ul class="portlet-list">
                                        <c:forEach items="${weixinAnnouncementList}" var="announcement">
                                            <li><a href="#" onclick="toShowMessage('${announcement.id}');">${announcement.title}</a></li>
                                        </c:forEach>
                                    </ul>
                                    --%>
                                    <div class="c_announcement"></div>
                                    <script type="text/javascript">
                                        loadAnnounce();
                                        function loadAnnounce() {
                                            window.donate.reload();
                                            $.ajax({

                                                url: "weixinAnnouncementController.do?getAnnouncements",
                                                dataType: "JSON",
                                                method: "POST",
                                                success: function (data) {

                                                    var htmlContent = "<ul class=\"portlet-list\">";
                                                    var list = jQuery.parseJSON(data);
                                                    for (var i = 0; i < list.length; i++) {

                                                        htmlContent += "<li><a href=\"#\" onclick=\"toShowMessage('" + list[i].id + "');\">" + list[i].title + "</a></li>";
                                                    }

                                                    htmlContent += "</ul>";
                                                    $(".c_announcement").html(htmlContent);
                                                }
                                            });
                                        }
                                        function toShowMessage(id) {

                                            $('#dd').dialog({
                                                title: "新消息",
                                                width: 600,
                                                height: 400,
                                                href: 'weixinAnnouncementController.do?goShow&id=' + id,
                                                topMost: false,
                                                maximizable: true,
                                                modal: true,
                                                buttons: [{
                                                    text: '关闭',
                                                    iconCls: "icon-no",
                                                    handler: function () {
                                                        $('#dd').dialog('close');
                                                    }
                                                }]
                                            });
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%--主页end --%>
            </div>
        </div>
    </div>



    <div data-options="region: 'south', title: '关于微信营销', iconCls: 'icon-standard-information', collapsed: true, border: false"
         style="height: 70px;">
        <div style="color: #4e5766; padding: 6px 0px 0px 0px; margin: 0px auto; text-align: center; font-size:12px; font-family:微软雅黑;">
            @2015-2016 Copyright: 拾流科技.<br/>
            建议使用&nbsp;
            <a href="https://www.google.com/intl/zh-CN/chrome/browser/" target="_blank" style="text-decoration: none;">谷歌</a>/
            <a href="http://se.360.cn/" target="_blank" style="text-decoration: none;">360</a>/
            <a href="http://windows.microsoft.com/zh-CN/internet-explorer/products/ie/home" target="_blank"
               style="text-decoration: none;">IE(Version 9/10/11)</a>

            &nbsp;系列浏览器。

        </div>
    </div>


</div>
</body>
</html>
