<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
	<!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <title>流量充值</title>
    <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/receive/index.css">
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/car-popup.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/util.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zimi/index.js"></script>
    
    <script type="text/javascript">
        $(document).ready(function () {
            var flag = true;

            var phoneNumber = document.getElementById("phoneNumber").value;
            $.ajax({
                url: "userChargeController.do?loadFamilyNumber",
                type: "post",
                data: {"phoneNumber": phoneNumber},
                dataType: "json",
                success: function (data) {
                    $("#showNmber").append("<option value='"+phoneNumber +"'>" + phoneNumber + "</option>");
                    for (var i = 0; i < data.length; i++) {
                        $("#showNmber").append("<option value='"+data[i].familyNumber +"'>" + data[i].familyNumber + "</option>");
                    }
                    loadMeal();
                },
                error: function () {
                    $.mobTips("error");
                }
            });

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

                var FlowType = document.getElementById("flowtyperesult").value;

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
                        "accountId":accountId,
                        "openid":'${openid}'
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
                        flag = true;
                    }, error: function () {
                        flag = true;
                    }
                });
            });
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
                            return;
                        }
                        //如果标志位为0，表示可以添加亲情号码
                        if (data.status == 0) {
                            var dialog = Zepto('body').popup({
                                title: '提示',
                                message: '<p>您可以将账户内流量充值到好友手机上，目前只能添加四个亲情号，添加后不可修改。</p><div class="dialog-text">添加手机号码：<input id="familyNumber" type="text" class="ui-text" /></div>',
                                id: 'pop-2',
                                ok: "确认",
                                closeOnOk: false,
                                onOk: function () {
                                    // 确认按钮的回调函数
                                    /* console.log('ok');*/
                                    var $familyNumber = $("#familyNumber");
                                    var familyNumber = $.trim($familyNumber.val());

                                    var phoneNumber = document.getElementById("phoneNumber").value;
                                    var patrn = /^(((106)|(13[0-9]{1})|(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\d{8})$/;
                                    if (!patrn.exec(familyNumber)) {
                                        $.mobTips('您输入的号码不符合规范，请重新输入！');
                                        return false;
                                    }
                                    if (phoneNumber == familyNumber) {
                                        $.mobTips('亲情号不能是自己的手机号！');
                                        return false;
                                    }
                                    console.log($familyNumber);
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
                                        },
                                        error: function () {
                                            $.mobTips("error");
                                        }
                                    });
                                    return false;
                                },
                                cnacel2: "取消",
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

            /*流量类型单选按钮*/
            $(".radio-wrap input").click(function () {
                var getFlowType = $(this).val();
                document.getElementById("flowtyperesult").value = getFlowType;
            });

            function showDialogValidMsg(msg, isEmpty) {
                var $msg = $("#j-add-number-valid-msg");
                $msg.text(msg);
            }
        });
        
        var changFamilyNumber = function() {
            loadMeal();
        }
        var loadMeal = function() {
            var objS = document.getElementById("showNmber");

            var grade = objS.options[objS.selectedIndex].value;
            $.ajax({
                url: "userChargeController.do?laodFlowMeal",
                type: "POST",
                data: {"phoneNumber": grade},
                dataType: "JSON",
                success: function (data) {
                    var i = 0, len = data.length, item = null, str = '';
                    var $menu = $("#j-form-flow-list");
                    for (; i < len; i++) {
                        item = data[i];

                        if (i === 0) {
                            str += '<p class="home-flow-item on" data-flowvalue="' + item.flowValue + '">\
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
                    document.getElementById("flowvalueresult").value = data[0].flowValue;
                    reDrawFlowMeal();
                    //bindTouchMove();
                },
                error: function () {
                    $.mobTips("error");
                }
            });
        };

        function reDrawFlowMeal(){
	    	var listBox = document.getElementById('j-form-flow-list');
	    	var parentBox = listBox.parentNode;
		    var startX = 0;
		    var left = 0;
		    var items = listBox.getElementsByTagName('p');
		    var len = items.length;
		    var item = items[0];
		    var itemWidth = len <=1 ? item.offsetWidth : (items[1].getBoundingClientRect().left - item.getBoundingClientRect().left);
		    var marginRight = itemWidth - item.offsetWidth;
		    listBox.style.width = items.length * itemWidth + 'px';
		    parentBox.style.width = 3 * itemWidth - marginRight + 'px';
		    var boxWidth = listBox.offsetWidth - parentBox.offsetWidth - marginRight;
		    
		    var numWidth = itemWidth * (len - 1);
		    var prev = document.getElementById('j-prev');
		    var next = document.getElementById('j-next');
		    var num = 0;
		    
		    listBox.addEventListener('touchstart', function(){
		    	// 如果只有一个手指
		    	if(event.targetTouches.length == 1) {
		    		if(len <= 3) {
			    		return;
			    	}
		    		//event.preventDefault();
		    		var touchBtn = event.targetTouches[0];
		    		var x = touchBtn.pageX;
		    		startX = x;	
		    		left = listBox.offsetLeft;
		    	}
		    });
		    listBox.addEventListener('touchmove', function(event) {
		    	// 如果只有一个手指
		    	if(event.targetTouches.length == 1) {
		    		if(len <= 3) {
			    		return;
			    	}
		    		//event.preventDefault();
		    		var touchBtn = event.targetTouches[0];
		    		var x = touchBtn.pageX;
		    		
		    		num = left + x - startX;
		    		
		    		setNumAndBtnState();
		    		listBox.style.left = num + 'px';
		    		
		    	}
		    });
		    listBox.addEventListener('touchend', function(event){
		    	// 如果只有一个手指
		    	if(event.targetTouches.length == 1) {
		    	}
		    });

		    prev.addEventListener('click', function() {
		    	
		    	if(len <= 3) {
		    		return;
		    	}
		    	
		    	num = num + itemWidth;
		    	if(num >= numWidth) {
		    		num = numWidth;
		    	}
		    	setNumAndBtnState();
	    		listBox.style.left = num + 'px';
		    });
		    
		    next.addEventListener('click', function() {
		    	
		    	if(len <= 3) {
		    		return;
		    	}
		    	
		    	num = num - itemWidth;
		    	if(num <= -numWidth) {
		    		num = -numWidth;
		    	}
		    	setNumAndBtnState();
	    		listBox.style.left = num + 'px';
		    });
		    
		    function setNumAndBtnState() {
		    	$(next).addClass('on');
		    	$(prev).addClass('on');
		    	console.log(num);
		    	
		    	if(num >= 0){
	    			num = 0;
	    			$(prev).removeClass('on');
	    		}
	    		if(num <= -boxWidth) {
	    			num = -boxWidth;
	    			$(next).removeClass('on');
	    		}
	    		if(len <= 3) {
		    		$(prev).removeClass('on');
		    		$(next).removeClass('on');
		    	}
		    }
		    setNumAndBtnState();
		    var $items = $("#j-form-flow-list > p");
		    $items.each(function(i) {
		    	$items.eq(i).click(function() {
		    		$items.removeClass('on');
		    		$(this).addClass('on');
		    		var dataflowvalue = $(this).data('flowvalue');
	                document.getElementById("flowvalueresult").value = dataflowvalue;
		    	});
		    });
	    };

    </script>
    <script>
 function onBridgeReady(){
WeixinJSBridge.call('hideOptionMenu');
}

if (typeof WeixinJSBridge == "undefined"){
if( document.addEventListener ){
document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
}else if (document.attachEvent){
document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
}
}else{
onBridgeReady();
} 
</script> 
</head>
<body>
	<div class="container">
		<header class="header">
			<div class="header-user">
				<figure>
					<a href="javascript:;"><img src="${headImgUrl}" alt="" /></a>
				</figure>
				<dl>
					<dt>${nickName}</dt>
					<dd>${phoneNumber}</dd>
				</dl>
			</div>
			<div class="header-msg">
				账户-全国流量：${countryFlowValue}M<span>|</span>账户-省内流量：${provinceFlowValue}M
			</div>
            <input id="provinceFlowValueid" type="hidden" value="${provinceFlowValue}">
            <input id="countryFlowValueid" type="hidden" value="${countryFlowValue}">
            <input type="hidden" id="phoneNumber" name="phoneNumber" value="${phoneNumber}" readonly="true">
            <input id="businessCodeid" type="hidden" value="${businessCode}">
            <input id="provinceCodeid" type="hidden" value="${provinceCode}">
            <input id="accountId" type="hidden" value="${accountId}">

            <input id="flowvalueresult" type="hidden" value="">
            <input id="flowtyperesult" type="hidden" value="1">
		</header>

		<section class="main">
			<section class="form">
				<form method="post" action="#">
					<p class="flow-sel clx">
						<select class="ui-select" id="showNmber" onchange="changFamilyNumber()">
						</select>
						<a href="javascript:;" class="btn-receive" id="j-btn-make">添加亲情号码</a>
                	</p>
					<ul class="form-list">
						<li>请选择流量充值类型：</li>
						<li>
							<label class="radio-wrap selected">
                    			<span class="radio"></span>
                    			<input type="radio" checked="checked" name="radioflowtype" value="1"/>
                    			<span>全国</span>
							</label >
                    		<label class="radio-wrap">
                    			<span class="radio"></span>
                    			<input type="radio" name="radioflowtype" value="2"/>
                    			<span>省内</span>
                    		</label>
						</li>
						<li>请选择流量充值包：</li>
						<li class="flow-container">
							<a href="javascript:;" class="prev" id="j-prev">上一页</a>
							<a href="javascript:;" class="next on" id="j-next">下一页</a>
							<div class="flow-box">
								<div class="form-list-home-flow clx" id="j-form-flow-list">
								</div>
							</div>
						</li>
					</ul>
					<div class="form-btn">
						<a href="javascript:;" id="j-btn-charge" class="ui-btn">确认充值</a>
						<a href="mainController.do?load&accountid=${accountId}&openId=${openId}" class="btn-receive2">返回流量门户&gt;&gt;</a>
					</div>
				</form>
			</section>
			<dl class="home-prompt">
				<dt>温馨提示：</dt>
				<dd>1、流量充值后，24小时内如未到账，将会回退回本账户； </dd>
				<dd>2、未办理实名认证或欠费、停机用户不能充值； </dd>
				<dd>3、单用户每个月同一规格流量包最多充值5次；</dd>
				<dd>4、流量充值会遇到因运营商维护而导致的充值失败，请大家稍后再</dd>
				<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;试！充值失败的流量会24小时内返还到账户；</dd>
				<dd>5、因运营商流量仅当月有效、不结转且不抵扣流量到账前已发生的流</dd>
				<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量。所以建议您于月初或套餐内流量未超过之前充值流量。</dd>
			</dl>
	</div>
</body>
</html>