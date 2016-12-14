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
    <title>提取流量</title>
    <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="${cdnHost}/plug-in/liuliangbao/20160701/css/receive/index.css">
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/common.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/zepto-all-min.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zepto/car-popup.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/lib/util.js"></script>
    <script type="text/javascript" src="${cdnHost}/plug-in/liuliangbao/20160701/js/zimi/index.js"></script> 
</script> 
<style>
		#modal {
			width: 90%;
			height: 235px;
			background: url("static/img/confirm_modal.png") no-repeat center;
			background-size: 100% 235px;
		}
		#modal .modal-title {
			width: 100%;
			height: 50px;
			font-size: 16px;
			line-height: 50px;
			text-align: center;
		}
		#modal .modal-prompt {
			font-size: 15px;
			color: #878787;
			padding: 10px 20px;
			text-indent: 24px;
			letter-spacing: 1px;
			line-height: 1.6;
		}

		#modal .modal-form,
		#modal .modal-form-input {
			width: 84%;
			height: 25px;
			display: inline-block;
		}

		#modal .modal-form {
			margin: 0 20px;
			padding-bottom: 5px;
			border-bottom: 1px solid #DCDCDC;
		}

		#modal .modal-form-input input {
			width: 100%;
			height: 25px;
			border: none;
		}

		#modal .modal-form-eye {
			width: 15%;
			height: 100%;
			display: inline-block;
		}

		#modal .eye-open{
			background: url("static/img/eye_open.png") no-repeat center;
			background-size: 70%;
		}

		#modal .eye-close{
			background: url("static/img/eye_close.png") no-repeat center;
			background-size: 70%;
		}

		#modal .modal-btn{
			width: 100%;
			height: 45px;
			line-height: 45px;
		}

		#modal .modal-btn a {
			display: block;
			font-size: 17px;
			text-align: center;
			width: 50%;
			height: 100%;
			text-decoration: none;
		}

		#modal .modal-btn-left{
			background-color: #EDEDED;
			color: #676767;
			-webkit-border-radius: 0 0 0 4px;
			-moz-border-radius: 0 0 0 4px;
			border-radius: 0 0 0 4px;
		}

		#modal .modal-btn-right{
			background-color: #FA6958;
			color: #fff;
			-webkit-border-radius: 0 0 4px 0;
			-moz-border-radius: 0 0 4px 0;
			border-radius: 0 0 4px 0;
		}

		#modal .modal-psd{
			color: #FA6958;
			text-decoration: none;
		}
	</style>
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
            <input id="businessCodeid" type="hidden" value="${bussinessCode}">
            <input id="provinceCodeid" type="hidden" value="${strProvinceCode}">
            <input id="accountId" type="hidden" value="${accountId}">

            <input id="flowvalueresult" type="hidden" value="">
            <input id="flowtyperesult" type="hidden" value="1">
		</header>

		<section class="main">
			<section class="form">
				<form method="post" action="#">
					<p class="flow-sel clx">
						<img src="static/img/phone.png" style="width: 14px;">
						 <span id="showNmber" class="header-msg style="font-size: 18px;color: #555555;position: relative;top: -3px;left: 25px;">${phoneNumber}</span>
					
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
						<%--  <a href="integrate.do?appUserCharge&accountid=${accountId}&phoneNumber=${phoneNumber}&flowType=${FlowType}&provinceFlowValue
						=${provinceFlowValue}&countryFlowValue=${countryFlowValue}&businessCode=${businessCode}&provinceCode=${provinceCode}&flowTrueValue=getFlowValue" id="" target="_blank" class="ui-btn">确认充值</a>  --%>
						<a href="javascript:;" id="j-btn-make" class="ui-btn">确认提取</a>
						<a href="integrate.do?app_userChargedFlow_getRecords&acctId=${acctId}&data=${userData}" class="btn-receive2">流量充值记录</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="integrate.do?app_userGiveFlow_getRecords&acctId=${acctId}&data=${userData}" class="btn-receive2">流量领取记录</a>
						<%-- <a href="mainController.do?load&accountid=${accountId}&openId=${openId}" class="btn-receive2">返回流量门户&gt;&gt;</a> --%>
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
	
	<div id="modal" style="display: none;">
			<div class="modal-title">输入密码</div>
			<div class="modal-prompt">为确保您的账户安全，请输入提取密码：</div>
			<div class="modal-form clx">
				<div class="modal-form-input fl"><input id="psd" type="password" placeholder="请输入提取密码"></div>
				<i class="modal-form-eye fr eye-close" id="eye"></i>
			</div>
			<p class="clx" style="margin: 10px 20px 15px;"><a href="integrate.do?findPwd&phoneNumber=${phoneNumber}" class="modal-psd fr">忘记密码？</a></p>
			<div class="modal-btn clx">
				<a href="javascript:;" class="fl modal-btn-left">取消</a>
				<a href="javascript:;" id="j-btn-charge" class="fr modal-btn-right">确认</a>
			</div>
		</div>
	</div>
		
	
	<script src="plug-in/liuliangbao/20160701/js/lib/jquery-3.0.0.js"></script>
	<script src="plug-in/liuliangbao/20160701/js/lib/jquery.easing.js"></script>
	<script src="plug-in/liuliangbao/20160701/js/lib/moaModal.minified.js"></script>
	<script type="text/javascript">

        $(document).ready(function () {
        	 $('#j-btn-charge').on('click',function(){
             	var pwd=$('#psd').val();            	
         		var patrn= /^([0-9a-zA-Z]){6}$/;
        			if (!patrn.exec(pwd)){
        				alert("密码错误!只能输入6位密码");
        				return false;
        			}
            var flag = true;
				/*充值*/
                if (flag == false) {
                    return;
                }
                flag = false;
                
                var getFlowValue = document.getElementById("flowvalueresult").value;
                var str1 = document.getElementById("provinceFlowValueid").value;
                var str2 = document.getElementById("countryFlowValueid").value;
                var str3 = document.getElementById("phoneNumber").value;
                var familyNumber = document.getElementById("phoneNumber").value;
                var str4 = document.getElementById("businessCodeid").value;
                var str5 = document.getElementById("provinceCodeid").value;
                var pwd = document.getElementById("psd").value;
             
                var accountId = document.getElementById("accountId").value;
                var FlowType = document.getElementById("flowtyperesult").value;

                $.ajax({
                    url: "integrate.do?appUserGetTrueFlow",
                    type: "GET",
                    dataType: "JSON",
                    data: {
                        "provinceFlowValue": str1,
                        "countryFlowValue": str2,
                        "flowTrueValue": getFlowValue,
                        "phoneNumber": str3,
                        "familyNumber": familyNumber,
                        "businessCode": str4,
                        "provinceCode": str5,
                        "flowType": FlowType,
                        "accountId":accountId,
                        "pwd":pwd
                    },
                    success: function (data) {
                 	if(data.returnMessage == 'sucess'){
                    	$('#modal').trigger('close.modal');
                    	 Zepto('body').popup({
                             title: '提示'
                             , message: '<p>充值请求已发送，请耐心等待</p>'
                             , id: 'pop-2'
                             , onCancel2: function () {
                                 console.log('cancel');
                             }
                         });
                    	 
                 	} if(data.returnMessage == 'fail'){
                 		$('#modal').trigger('close.modal');
                   	 Zepto('body').popup({
                            title: '提示'
                            , message: '<p>密码错误，请重新输入</p>'
                            , id: 'pop-2'
                            , onCancel2: function () {
                                console.log('cancel');
                            }
                        });
                 	}if(data.returnMessage == 'flowNotEnough'){
                 		$('#modal').trigger('close.modal');
                      	 Zepto('body').popup({
                               title: '提示'
                               , message: '<p>账户余额不足</p>'
                               , id: 'pop-2'
                               , onCancel2: function () {
                                   console.log('cancel');
                               }
                           });
                 	}
                    	 
                        window.setTimeout(function () {
                        	 window.location.reload();
                        }, 2500);
                        flag = true;
                    }, error: function () {
                        flag = true;
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
		
		$(function(){
			//console.log($('#j-btn-charge'));
			// 显示弹窗
			$('#j-btn-make').modal({
				target : '#modal',
				speed : 250,
				easing : 'easeOutSine',
				animation : 'top',
				position: 'center',
				overlayClose : true,
				overlayOpacity : .6,
				close : '.modal-btn-left',
				on : 'click'
			});

			// 眼睛关闭
			var psd = $('#psd');
			$('#eye').click(function(){
				var isOpen = $(this).hasClass('eye-open');
				$(this).removeClass(isOpen ? 'eye-open' : 'eye-close').addClass(isOpen ? 'eye-close' : 'eye-open');
				psd.attr('type', isOpen ? 'password' : 'text');
			});
		});
	</script>
	
	<script>
		$(function () {
		    
		    (
		    	function(){
			    	var listBox = document.getElementById('j-form-flow-list');
			    	var parentBox = listBox.parentNode;
				    var startX = 0;
				    var left = 0;
				    var items = listBox.getElementsByTagName('p');
				    var item = items[0];
				    var itemWidth = items[1].getBoundingClientRect().left - item.getBoundingClientRect().left;
				    var marginRight = itemWidth - item.offsetWidth;
				    listBox.style.width = items.length * itemWidth + 'px';
				    parentBox.style.width = 3 * itemWidth - marginRight + 'px';
				    var boxWidth = listBox.offsetWidth - parentBox.offsetWidth - marginRight;
				    
				    
				    var prev = document.getElementById('j-prev');
				    var next = document.getElementById('j-next');
				    
				    var num = 0;
				    
				    listBox.addEventListener('touchstart', function(){
				    	//console.log(2222222);
				    	// 如果只有一个手指
				    	if(event.targetTouches.length == 1) {
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
				    		//event.preventDefault();
//				    		var touchBtn = event.targetTouches[0];
//				    		var x = touchBtn.pageX;
//				    		startX = x;				    		
				    	}
				    });
				    
				    prev.addEventListener('click', function() {
				    	
				    	num = num + itemWidth;
				    	setNumAndBtnState();
			    		listBox.style.left = num + 'px';
				    });
				    
				    next.addEventListener('click', function() {
				    	num = num - itemWidth;
				    	setNumAndBtnState();
			    		listBox.style.left = num + 'px';
				    });
				    
				    function setNumAndBtnState() {
				    	$(next).addClass('on');
				    	$(prev).addClass('on');
				    	//console.log(num);
				    	if(num > 0){
			    			num = 0;
			    			$(prev).removeClass('on');
			    		}
			    		if(num < -boxWidth) {
			    			num = -boxWidth;
			    			$(next).removeClass('on');
			    		}
				    }
				    
			    }
		    )();
		    
		    var $items = $("#j-form-flow-list > p");
		    $items.each(function(i) {
		    	$items.eq(i).click(function() {
		    		$items.removeClass('on');
		    		$(this).addClass('on');
		    	});
		    });

		});
	</script>
	<script type="text/javascript">
	var d = document.getElementById("showNmber").innerHTML;  
    $.ajax({
        url: "userChargeController.do?laodFlowMeal",
        type: "POST",
        data: {"phoneNumber": d
        },
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
 </script>
</body>
</html>