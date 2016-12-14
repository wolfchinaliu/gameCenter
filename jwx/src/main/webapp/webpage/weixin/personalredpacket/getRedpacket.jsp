<%@ taglib prefix="t" uri="/easyui-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,DatePicker"></t:base>
<%--<t:base type="jquery,easyui,tools,DatePicker"></t:base>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>流量分享</title>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/index.css">
    <script src="plug-in/liuliangbao/js/0201/lib/common.js"></script>

</head>
<body>
<article class="container">
    <!--页头-->
    <header class="header header-photo">
        <figure class="photo p140-140">
            <a href="#"><img src="${memberEntity.headImgUrl}" alt="" /></a>
        </figure>
        <div class="header-msg">
            ${memberEntity.nickName}联合${accountEntity.accountname}
                <br>给您发送了一个流量红包
        </div>
    </header>

    <section class="main">

        <p class="main-text main-text-center">
            祝您 : ${blessword}
        </p>

        <section class="hbbox">
            <a id="j-btn-chai" href="javascript:;"><img src="plug-in/liuliangbao/css/images/hb01.jpg" alt="" /></a>
        </section>

        <p class="main-msg">
            本红包的流量<span class="main-text-red ">${flowArea}</span>的手机可以领取，在${endtime}前还可领取<span class="main-text-red">${leftTime}</span>次从由此公众号发出的红包.
        </p>
        <p class="main-msg main-text-red">
            请在${endtime}前领取，否则红包就失效了哦
        </p>

    </section>

</article>


<!--拆红包-->
<div class="dialog-chai" id="j-dialog-chai">
    <div class="dialog-msg1">${memberEntity.nickName}联合${accountEntity.accountname}给您发送了一个流量红包</div>
    <div class="dialog-msg1">祝您 :${blessword}</div>
    <div class="dialog-msg2"><span style="font-size: 0.3rem;" id="getflow"></span>M${flowArea}流量已存入您的流量账户，可充值到您的手机。<a style="font-size: 0.3rem;" href="personFlowCenterController.do?goPersonCenter&accountid=${hdEntity.accountId}&openId=${openId}">去查看</a></div>
    <div class="dialog-btn">
        <a id="j-dialog-btn-1" href="javascript:;" class="dialog-btn-1">很开心</a>
        <a href="moreRedpacketController.do?moreRedpacketList&phoneNumber=${memberEntity.phoneNumber}" class="dialog-btn-2">我也要发</a>
    </div>
    <input type="hidden" name="openId" id="openId" value="${openId}">
    <input type="hidden" name="sendOpenId" id="sendOpenId" value="${hdEntity.openId}">
    <input type="hidden" name="accountid" id="accountid" value="${hdEntity.accountId}">
    <input type="hidden" name="hdid" id="hdid" value="${hdEntity.id}">
    <input type="hidden" name="nikename" id="nikename" value="${nikename}">
    <input type="hidden" name="headImg" id="headImg" value="${headImg}">
    <input type="hidden" name="blessword" id="blessword" value="${blessword}">
    <input type="hidden" name="provinceAccount" id="provinceAccount" value="${provinceAccount}">
    <input type="hidden" name="startime" id="startime" value="${startime}">
    <input type="hidden" name="endtime" id="endtime" value="${endtime}">
    <input type="hidden" name="first" id="first" value="${first}">
    <input type="hidden" name="accountname" id="accountname" value="${accountname}">
</div>



<!--红包制作成功 - 分享-->
<div id="j-dialog-share" class="dialog-share" style="display: none;"></div>
<!--弹框遮罩层-->
<%--<div class="dialog-zz" id="j-dialog-zz"></div>--%>
<div class="dialog-zz" id="j-dialog-zz" style="display: none;"></div>

<script type="text/javascript" src="plug-in/liuliangbao/js/0201/zepto/zepto-all-min.js"></script>
<script type="text/javascript" src="plug-in/liuliangbao/js/0201/car-popup.js"></script>

<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>

    $(function () {
        if($("#first").val()=="true"){
            var $dialogzz = $("#j-dialog-zz");
            var $dialogShare = $("#j-dialog-share");
            $dialogzz.show();
            $dialogShare.show();
            $dialogShare.click(function(){
                $dialogzz.hide();
                $dialogShare.hide();
            });
            $("#j-btn-chai").click(function () {
                first();
            });
        }else{
            if ($("#openId").val() == $("#sendOpenId").val()) {
                $("#j-btn-chai").click(function () {
                    isMyself();
                });
            } else {
                $("#j-btn-chai").click(function () {
                    drawRedpacket();
                });
            }
        }

    });

    function first(){
        var $dialogzz = $("#j-dialog-zz");
        var $dialogShare = $("#j-dialog-share");
        $dialogzz.show();
        $dialogShare.show();
    }

    function drawRedpacket() {

        <%--var openId =${openId};--%>
        <%--var accountId =${hdEntity.accountid};--%>
        var openId = $("#openId").val();
        var accountId = $("#accountid").val();
        var hdid = $("#hdid").val();
        var sendOpenId = $("#sendOpenId").val();

        <%--&lt;%&ndash;window.location.href = "getRedpacketController.do?gotRedpacket?openId=${openId}&accountId=${hdEntity.accountid}";&ndash;%&gt;--%>

//            window.location.href = "getRedpacketController.do?openRedPacket&openId=" + openId + "&accountId=" + accountId + "&hdid=" + hdid + "";

        var $dialogzz = $("#j-dialog-zz");
        var $dialog = $("#j-dialog-chai");
        var $btn01 = $("#j-dialog-btn-1");
        $btn01.click(function(){
            $dialogzz.hide();
            $dialog.hide();
        });
        $.ajax({
            type: 'POST',
            url: "getRedpacketController.do?openRedPacket",
            dataType: "json",
            data: {
                "openId": openId,
                "accountId": accountId,
                "hdid": hdid,
                "sendOpenId": sendOpenId,
            },
            cache: false,
            error: function () {
                alert("请重试");
            },
            success: function (data) {
//                data = JSON.parse(data);

                if (data.msg == "normal") {
                    var redpacket = data.attributes.redpacket;
                    $('#getflow').html(redpacket);
                    $dialogzz.show();
                    $dialog.show();

                } else if(data.msg == "illegal"){
                    Zepto('body').popup({
                        title:'提示'
                        ,message:'<h2>尊敬的用户您好:</h2><p>非常抱歉！该红包只包含'+$('#provinceAccount').val()+'内流量，您的手机号码不在该省范围内，无法领取。</p>'
                        ,id:'pop-2'
                        ,ok:"看自已省的"
                        ,onOk:function(){
                            window.location.href ="moreRedpacketController.do?moreRedpacketList&phoneNumber=${memberEntity.phoneNumber}";
                        }
                        ,cnacel:"浪费表情"
                        ,onCancel: function(){
                            console.log('cancel');
                        }
                    });
                } else if (data.msg == "already") {
//                    alert("您已经抢过红包了，等下次有活动再来吧");
                    Zepto('body').popup({
                        title:'提示'
                        ,message:'<h2>尊敬的用户您好:</h2><p>您已经抢过红包了，等下次有活动再来吧</p>'
                        ,id:'pop-2'
                        ,ok:"确定"
                        ,onOk:function(){
                            console.log('cancel');
                        }
//                        ,cnacel:"确定"
//                        ,onCancel: function(){
//                            console.log('cancel');
//                        }
                    });
                }else if(data.msg == "noArea"){
                    Zepto('body').popup({
                        title:'提示'
                        ,message:'<h2>根据您的手机号判断并不在商户的覆盖区域内以及所属运营商内，不予赠与流量</p>'
                        ,id:'pop-2'
                        ,ok:"确定"
                        ,onOk:function(){
                            console.log('cancel');
                        }
//                        ,cnacel:"确定"
//                        ,onCancel: function(){
//                            console.log('cancel');
//                        }
                    });
                } else {
//                    var r = confirm("在活动时间" + $('#startime').val() + "至" + $('#endtime').val() + "之间，您可以从领取三个红包，您的领取次数已经用完。您也可以去赚取更多免费流量发红包哦");
//                    if (r == true) {
//                        alert("赚取更多流量");
//                        window.location.href = "earnFlowController.do?moreFlow&accountid=" + accountId + "&openId=" + openId + "";
//                    }
                    Zepto('body').popup({
                        title:'提示'
                        ,message:'<h2>尊敬的用户您好:</h2><p>在活动时间'+$('#startime').val()+'至'+$('#endtime').val()+'之间，您可以从'+$('#accountname').val()+'领取三个红包，您的领取次数已用完,您也可以去赚免费流量发红包哦</p>'
                        ,id:'pop-2'
                        ,ok:"去赚流量"
                        ,onOk:function(){
                            // 确认按钮的回调函数
                            window.location.href = "earnFlowController.do?moreFlow&accountid=" + accountId + "&openId=" + openId + "";
                            console.log('ok');
                        }
                        ,cnacel:"取消"
                        ,onCancel: function(){
                            console.log('cancel');
                        }
                    });
                }
            }
        })
    }

    function isMyself() {
        var openId = $("#openId").val();
        var accountId = $("#accountid").val();
        var hdid = $("#hdid").val();
        var sendOpenId = $("#sendOpenId").val();
        window.location.href = "getRedpacketController.do?senderOpenPacket&openId=" + openId + "&accountId=" + accountId + "&hdid=" + hdid + "&sendOpenId=" + sendOpenId + "";
    }

    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${ret.appId}', // 必填，公众号的唯一标识
        timestamp:${ret.timestamp}, // 必填，生成签名的时间戳
        nonceStr: '${ret.nonceStr}', // 必填，生成签名的随机串
        signature: '${ret.signature}',// 必填，签名，见附录1
        jsApiList: [
            'onMenuShareTimeline',
            'onMenuShareAppMessage'
        ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function () {
        wx.onMenuShareTimeline({
            title: '${hdEntity.blessing} 您的朋友${memberEntity.nickName}联合${accountEntity.accountname}给您发来一个流量分享，快打开领取吧。', // 分享标题
            link: '${link}', // 分享链接
            imgUrl: '${memberEntity.headImgUrl}', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        wx.onMenuShareAppMessage({
            title: '${hdEntity.blessing}', // 分享标题
            desc: '您的朋友${memberEntity.nickName}联合${accountEntity.accountname}给您发来一个流量分享，快打开领取吧。', // 分享描述
            link: '${link}', // 分享链接
            imgUrl: '${memberEntity.headImgUrl}', // 分享图标
            type: '', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    });
    wx.error(function (res) {

    });
</script>

</body>
</html>