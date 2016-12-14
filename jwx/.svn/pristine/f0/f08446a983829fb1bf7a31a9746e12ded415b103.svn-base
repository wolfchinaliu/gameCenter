<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
	<!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <title>流量领取</title>

    <%-- <script src="plug-in/liuliangbao/js/1218/jquery-1.10.1.min.js"></script>
       <script src="plug-in/liuliangbao/js/1218/jquery.slides.min.js"></script>
       <script type="text/javascript" src="plug-in/liuliangbao/js/0104/jQueryRotate.js"></script>
       <script type="text/javascript" src="plug-in/liuliangbao/js/0203/util.js"></script>
       <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0104/style.css">--%>

    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0305/css/normalize.css">
    <script src="plug-in/liuliangbao/css/0305/js/common.js"></script>

    <script src="plug-in/liuliangbao/js/1218/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/css/0305/js/zepto/zepto-all-min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/css/0305/js/zepto/car-popup.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/css/0305/js/lib/util.js"></script>


    <script type="text/javascript">


        function loadMeal() {
            // alert("loadmeal");
            var objS = document.getElementById("showNmber");

            var grade = objS.options[objS.selectedIndex].value;
            $.ajax({
                url: "userChargeController.do?laodFlowMeal",
                type: "POST",
                data: {"phoneNumber": grade},
                dataType: "JSON",
                success: function (data) {

                    var i = 0, len = data.length, item = null, str = '';
                    var $menu = $("#top_menu");
                    for (; i < len; i++) {
                        item = data[i];
                        if (i === 0) {
                            str += '<p class="home-flow-item flowon" data-flowvalue="' + item.flowValue + '">\
                                            <i>流量</i>\
                                            <span>' + item.flowValue + 'M</span>\
                                    </p>';

                        }
                        else {
                            str += '<p class="home-flow-item" data-flowvalue="' + item.flowValue + '">\
                                            <i>流量</i>\
                                            <span>' + item.flowValue + 'M</span>\
                                    </p>';
                        }
                    }
                    $menu.html(str);
                    //alert($(".flowon").data('flowvalue'));
                    document.getElementById("flowvalueresult").value = $(".flowon").data('flowvalue');
                    bindTouchMove();

                },
                error: function () {
                    $.mobTips("error");
                }
            });
        }


        $(document).ready(function () {
            //给流量数的第一个li添加current class
            // $("#top_menu li:first").addClass('current');
            var flag = true;
            /*充值*/
            $('#j-btn-charge').on('click', function () {
            	// 限制全国流量的移动通道
            	/* if(${businessCode}=="1"){
            		if(!(document.getElementById("flowtyperesult").value=="省内" && document.getElementById("provinceCodeid").value=="14")){
		            	$.mobTips("受运营商限制。流量领取暂停服务1～2天，给您带来不便，敬请谅解");
		            	return;
            		}
            	} */
                if (flag == false) {
                    return;
                }
                flag = false;

                var getFlowValue = document.getElementById("flowvalueresult").value;
                var str1 = document.getElementById("provinceFlowValueid").value;
                var str2 = document.getElementById("countryFlowValueid").value;
                var str3 = document.getElementById("phoneNumber").value;
                var str4 = document.getElementById("businessCodeid").value;
                var str5 = document.getElementById("provinceCodeid").value;
                var familyNumber = document.getElementById("showNmber").value;
                var accountId = document.getElementById("accountId").value;

                //var FlowType = $('input[name="radioflowtype"]:checked').val();
                var FlowType = document.getElementById("flowtyperesult").value;
               // alert(FlowType);

                /* var finalFlowType;
                 if (typeof(FlowType) == 'undefined') {
                 finalFlowType = "省内";
                 } else {
                 finalFlowType = $('input[name="radioflowtype"]:checked').val();
                 }*/

                $.ajax({
                    url: "userChargeController.do?testChargeFlow",
                    type: "POST",
                    dataType: "JSON",
                    data: {
                        "provinceFlowValue": str1,
                        "countryFlowValue": str2,
                        "flowTrueValue": getFlowValue,
                        "phoneNumber": str3,
                        "businessCode": str4,
                        "provinceCode": str5,
                        "flowType": FlowType,
                        "familyNumber": familyNumber,
                        "accountId":accountId
                    },
                    success: function (data) {
                        if (data.returnMessage == 'flowNotEnough') {
                            Zepto('body').popup({
                                title: '提示'
                                , message: '<p>您的账户内流量不足，欢迎进入其他公众号赚取更多免费流量。</p>'
                                , id: 'pop-2'
                                , ok: "去赚流量"
                                , onOk: function () {
                                    // 确认按钮的回调函数
                                    window.location.href = "acctListController.do?weixinacctList&accountid=${accountId}&openId=${openid}";
                                    console.log('ok');
                                }
                                , cnacel2: "取消"
                                , onCancel2: function () {
                                    console.log('cancel');
                                }
                            });
                            flag = true;
                            return false;

                        } else {
                            $.mobTips(data.returnMessage);
                        }

                        window.setTimeout(function () {
                            javascript:history.go(-1);
                        }, 2500);
                        flag = true;
                    }, error: function () {
                        /* $('.flow-error').mobDialog();*/
                        flag = true;
                    }
                });


            });


            /*流量类型单选按钮*/
            $(".radio-wrap input").click(function () {
                //alert($(this).val());
                var getFlowType = $(this).val();
                document.getElementById("flowtyperesult").value = getFlowType;
            });


            $('.jq-cancle,.dialog-close').on('click', function () {
                //$('.overlay,.dialog-modal').removeClass('in');
                $('.dialog-modal').mobDialog('close');
                flag = true;

            });


            /*流量套餐选择事件*/
            $('#top_menu').on('click', 'p.home-flow-item', function (e) {
                $('.home-flow-item').removeClass('flowon');
                $(this).addClass('flowon');

                /*document.getElementById("flowvalueresult").value =$(".flowon").data('flowvalue');
                 alert($(".flowon").data('flowvalue'));*/
                var dataflowvalue = $(this).data('flowvalue');
                document.getElementById("flowvalueresult").value = dataflowvalue;
            });


            /*流量不足或者流量领取异常提示去赚流量按钮*/
            $('.hq_flow').on('click', function () {
                $.ajax({
                    type: 'POST',
                    url: "personFlowCenterController.do?goGetFlowCharge",
                    dataType: "json",
                    data: {
                        "phoneNumber": '${phoneNumber}'
                    },
                    cache: false,
                    error: function (error) {
                        //alert("error");

                    },
                    success: function (data) {
                        window.location.href = "earnFlowController.do?moreFlow&accountid=${accountId}";

                    }
                });
                $('.dialog-modal').mobDialog('close');
            });


            function showDialogValidMsg(msg, isEmpty) {
                var $msg = $("#j-add-number-valid-msg");
                $msg.text(msg);
            }

            /**加载领取手机号的option*/
            /**加载页面时，将option加载到输入框中 */
            $(function () {
                var phoneNumber = document.getElementById("phoneNumber").value;
                $.ajax({
                    url: "userChargeController.do?loadFamilyNumber",
                    type: "post",
                    data: {"phoneNumber": phoneNumber},
                    dataType: "json",
                    success: function (data) {
                        $("#showNmber").append("<option>" + phoneNumber + "</option>");
                        for (var i = 0; i < data.length; i++) {
                            $("#showNmber").append("<option>" + data[i].familyNumber + "</option>");
                        }
                        loadMeal();
                    },
                    error: function () {
                        $.mobTips("error");
                    }
                });


            });

        });


    </script>
</head>


<body>
<article class="container">
    <!--页头-->
    <header class="header header-makered">
        <figure class="photo p140-140">
            <a href="#"><img src="${headImgUrl}" alt=""/></a>
        </figure>
        <ul class="header-msg-makered">
            <li>昵称：${nickName}</li>
            <li>手机号：${phoneNumber}</li>
            <li>省内流量：${provinceFlowValue}M</li>
            <li>全国流量：${countryFlowValue}M</li>
        </ul>
    </header>

    <input id="provinceFlowValueid" type="hidden" value="${provinceFlowValue}">
    <input id="countryFlowValueid" type="hidden" value="${countryFlowValue}">
    <input type="hidden" id="phoneNumber" name="phoneNumber" value="${phoneNumber}" readonly="true">
    <input id="businessCodeid" type="hidden" value="${businessCode}">
    <input id="provinceCodeid" type="hidden" value="${provinceCode}">
    <input id="accountId" type="hidden" value="${accountId}">

    <input id="flowvalueresult" type="hidden" value="">
    <input id="flowtyperesult" type="hidden" value="2">


    <section class="main">
        <section class="form form-home">
            <form method="post" action="#">

                <p class="flow-sel">
                    <label class="radio-wrap" style="margin-right: 5px;"><span class="radio"></span><input type="radio"
                                                                                                           name="radioflowtype"
                                                                                                           value="1"/><span>国内</span></label>
                    <label class="radio-wrap selected"><span class="radio"></span><input type="radio" checked="checked"
                                                                                         name="radioflowtype"
                                                                                         value="2"/><span>省内</span></label>
                </p>

                <ul class="form-list form-list-home">
                    <li>请输入手机号码：</li>
                    <li>
                        <select id="showNmber" onchange="changflow()">
                        </select>
                        <a href="javascript:;" id="j-btn-make" class="ui-btn ui-btn-4">添加亲情号码</a>
                    </li>
                    <li>流量包领取 - 滑动选取更多流量值</li>
                    <%--<li class="form-list-home-flow clx" id="top_menu">--%>
                    <li class="flow-box">
                        <div class="form-list-home-flow clx" id="top_menu">
                        <p class="home-flow-item flowon">
                    <i>省内流量</i>
                    <span>60M</span>
                  </p>
                  <p class="home-flow-item flowon">
                    <i>省内流量</i>
                    <span>60M</span>
                  </p>
                        </div>

                    </li>
                </ul>
                <div class="form-btn">
                    <a href="javascript:;" disable="disable" id="j-btn-charge" class="ui-btn ui-btn-4">确认领取</a>
                </div>
            </form>
        </section>
        <p class="main-msg main-text-red">
            如果当前没有适合您领取的流量包，请继续关注我们的活动并赚取更多流量。
        </p>
        <dl class="home-prompt">
            <dt>温馨提示：</dt>
            <dd>1.流量领取后，24小时内如未到账，将会回退回本账户。</dd>
            <dd>2.未办理实名认证或欠费、停机手机用户不能领取。</dd>
            <dd>3.单用户每个月同一规格流量包最多领取5次。</dd>
            <dd>4.因运营商流量仅当月有效、不结转且不抵扣流量到账前已发生的流量。所以，建议您于月初或套餐内流量未超过之前领取流量。</dd>
        </dl>
    </section>
</article>


<script>

    function changflow() {
        loadMeal();
    }


    <%--添加亲情号码--%>
    $(function () {
        $('#j-btn-make').click(function () {
            var phoneNumber = document.getElementById("phoneNumber").value;

            $.ajax({
                url: "userChargeController.do?testFamilyNumber",
                type: "POST",
                data: {"phoneNumber": phoneNumber},
                dataType: "JSON",
                success: function (data) {
                    //如果标志位为1，表示亲情号码超过4个
                    if (data.status == 1) {
                        $.mobTips(data.msg);
                       /* window.setTimeout(function () {
                            javascript:history.go(0);
                        }, 2500);*/
                        return;
                    }
                    //如果标志位为0，表示可以添加亲情号码
                    if (data.status == 0) {
                        var dialog = Zepto('body').popup({
                            title: '提示'
                            ,
                            message: '<p>您可以将账户内流量充值到好友手机上，目前只能添加四个亲情号，添加后不可修改。</p><div class="dialog-text">添加手机号码：<input id="familyNumber" type="text" class="ui-text" /></div>'
                            ,
                            id: 'pop-2'
                            ,
                            ok: "确认"
                            ,
                            closeOnOk: false
                            ,
                            onOk: function () {
                                // 确认按钮的回调函数
                                /* console.log('ok');*/
                                var $familyNumber = $("#familyNumber");
                                var familyNumber = $.trim($familyNumber.val());

                                var phoneNumber = document.getElementById("phoneNumber").value;
                                var patrn = /^(((106)|(13[0-9]{1})|(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\d{8})$/;
                                if (!patrn.exec(familyNumber)) {
                                    $.mobTips('您输入的号码不符合规范，请重新输入！');
                                    //showDialogValidMsg('您输入的号码不符合规范，请重新输入！');
//                    $.mobAlert("您输入的号码不符合规范，请重新输入");
//                    $('.dialog-modal').mobDialog('close');
                                    return false;
                                }
                                if (phoneNumber == familyNumber) {
//                    $.mobAlert("亲情号不能是自己的手机号");
                                    $.mobTips('亲情号不能是自己的手机号！');
                                    // showDialogValidMsg('亲情号不能是自己的手机号！');
//                    $('.dialog-modal').mobDialog('close');
                                    return false;
                                }
                                console.log($familyNumber);
                                //showDialogValidMsg('');
                                /*  $('.dialog-modal').mobDialog('close');*/
                                $.ajax({
                                    url: "userChargeController.do?bindFamilyNumber",
                                    type: "post",
                                    data: {"familyNumber": familyNumber, "phoneNumber": phoneNumber},
                                    dataType: "json",
                                    success: function (data) {
                                        $.mobTips(data.msg);
                                        if (data.msg == "该亲情号已存在") {
                                        } else {
                                            $("#showNmber").append("<option>" + familyNumber + "</option>");
                                        }
                                        dialog.hide();
//                        loadMeal();
                                    },
                                    error: function () {
                                        $.mobTips("error");
                                    }
                                });
                                return false;
                            }
                            ,
                            cnacel2: "取消"
                            ,
                            onCancel2: function () {
                                console.log('cancel');
                            }
                        });
                        return false;
                    }
                },
                error: function () {
                    $.mobTips("error");
                }
            });


        });





    });


    function bindTouchMove () {
        var listBox = document.getElementById('top_menu');
        var parentBox = listBox.parentNode;
        var startX = 0;
        var left = 0;
        var items = listBox.getElementsByTagName('p');
        var item = items[0];
        var itemWidth = item.offsetWidth;
        listBox.style.width = items.length * 1.92 + 'rem';
        var boxWidth = listBox.offsetWidth - parentBox.offsetWidth;
        listBox.addEventListener('touchstart', function () {
            // 如果只有一个手指
            if (event.targetTouches.length == 1) {
//                event.preventDefault();
                var touchBtn = event.targetTouches[0];
                var x = touchBtn.pageX;
                startX = x;
                left = listBox.offsetLeft;
            }
        });
        listBox.addEventListener('touchmove', function (event) {
            // 如果只有一个手指
            if (event.targetTouches.length == 1) {
                event.preventDefault();
                var touchBtn = event.targetTouches[0];
                var x = touchBtn.pageX;

                var num = left + x - startX;

                if (num > 0) {
                    num = 0;
                }
                if (num < -boxWidth) {
                    num = -boxWidth;
                }
                listBox.style.left = num + 'px';

            }
        });
        listBox.addEventListener('touchend', function (event) {
            // 如果只有一个手指
            if (event.targetTouches.length == 1) {
                event.preventDefault();
                //				    		var touchBtn = event.targetTouches[0];
                //				    		var x = touchBtn.pageX;
                //				    		startX = x;
            }
        });
    }


</script>


</body>
</html>
