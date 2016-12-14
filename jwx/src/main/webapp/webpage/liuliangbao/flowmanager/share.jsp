<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <title>${newsItem.accountName}</title>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="plug-in/jquery/jquery-1.10.1.min.js"></script>
    <script src="plug-in/liuliangbao/20160701/js/lib/util.js"></script>
    <script>
        wx.config({
            debug: false,
            appId: '${map.appId}',
            timestamp: '${map.timestamp}',
            nonceStr: '${map.nonceStr}',
            signature: '${map.signature}',
            jsApiList: [
                'checkJsApi',
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'showOptionMenu',
             // 'onMenuShareQQ', 'onMenuShareWeibo', 'hideMenuItems', 'showMenuItems', 'hideAllNonBaseMenuItem', 'showAllNonBaseMenuItem', 'translateVoice', 'startRecord', 'stopRecord', 'onRecordEnd', 'playVoice', 'pauseVoice', 'stopVoice', 'uploadVoice', 'downloadVoice', 'chooseImage', 'previewImage', 'uploadImage', 'downloadImage', 'getNetworkType', 'openLocation', 'getLocation', 'hideOptionMenu', 'showOptionMenu', 'closeWindow', 'scanQRCode', 'chooseWXPay', 'openProductSpecificView', 'addCard', 'chooseCard', 'openCard'
            ]
        });
        
        function share(shareWay) {
            $.ajax({
                type: "post",
                url: "shareController.do?share",
                dataType: "json",
                async: false,
                data: {
                    "openId": '${openId}',
                    "shiliuOpenId": '${shiliuOpenId}',
                    "accountid": '${accountid}',
                    "shareId": '${shareId}',
                    "nickname": '${nickname}',
                    "accountname": '${newsItem.accountName}',
                    "title": '${newsItem.title}',
                    "shareWay": shareWay
                },
                success: function (data) {
                    $.mobTips(data.message);
                }, error: function (error) {
                    $.mobTips('分享失败:' + error);
                }
            })
        }
        wx.ready(function () {
            wx.onMenuShareAppMessage({
                title: '${newsItem.title}',
                desc: '${newsItem.description}',
                link: '${url}',
                imgUrl: '${shareImgUrl}',
                trigger: function (res) {
                    // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
                    // alert('用户点击发送给朋友');
                },
                success: function (res) {
                    share("1");
                },
                fail: function (res) {
                    alert(JSON.stringify(res));
            }
        });

            wx.onMenuShareTimeline({
                title: '${newsItem.title}',
                desc: '${newsItem.description}',
                link: '${url}',
                imgUrl: '${shareImgUrl}',
                success: function (res) {
                    share("2");
                }
            });
            wx.showOptionMenu();
        });
        wx.error(function (res) {
            alert("error: " + res.errMsg);
        });
    </script>

    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0112/style.css">
</head>
<body>
<section class="share-wrap">
    <header>
        <div class="share-title" style="text-align: center;"> ${newsItem.title}</div>
        <p>${date}　${newsItem.accountName}</p>
      <!--   <a class="btn btn-primary share-link">点击送${subscribeFlowValue}M流量</a>  -->  
        <div class="text" style="word-break: break-all" id="js_content">
            ${newsItem.content}
        </div>
        <div class="text" id="pageurlll">
            <iframe src="${newsItem.pageurl}" style="height :300px;"></iframe>
            <input id="inpageurl" type="text" style="display:none;" value="${newsItem.pageurl}">
        </div>
        <div class="text" style="margin-bottom: 10px;">
            <p> 阅读：${readNumber}</p>
        </div>
    </header>
</section>
<script type="text/javascript">
    $(document).ready(function () {
        $('.share-link').on('click', function () {
            // alert("${phoneNumber}" + "," + '${openId}' + ',' + '${subscribeStatus}')
            if ('true' != '${subscribeStatus}' || '' == '${openId}') {
                window.location.href="shareController.do?QRcode&accountid=${accountid}";
            } else if ('' == '${phoneNumber}' ) {
                var url ='bindingController.do?redirectBinding';
                url += "&openId=${openId}";
                url += "&shiliuOpenId=${shiliuOpenId}";
                url += "&merchantId=${accountid}";
                url += "&nickName=${nickname}";
                url += "&phoneNumber=${phoneNumber}";
                url += "&operateType=关注";
                window.location.href = url;
            } else {
                $.mobTips("您已验证手机， 让好友看到页面可以获更多流量。");
                wx.showOptionMenu();
            }

        });

    });

    window.onload = function() {
        var a = document.getElementById("inpageurl").value;
        if (a == null || a == "" || a == undefined) {
            document.getElementById("pageurlll").style.display = "none";
        } else {
            document.getElementById("js_content").style.display = "none";
        }
    }

</script>
</body>
</html>