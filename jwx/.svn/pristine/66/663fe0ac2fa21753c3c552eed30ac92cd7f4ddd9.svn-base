<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <title>扫描流量卡二维码</title>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="plug-in/jquery/jquery-1.10.1.min.js"></script>
    <script type="application/javascript">
        wx.config({
            debug: false,
            appId: '${map.appId}',
            timestamp: '${map.timestamp}',
            nonceStr: '${map.nonceStr}',
            signature: '${map.signature}',
            jsApiList: [
                'checkJsApi',
                'scanQRCode'
            ]
        });

        function startScanFlowCard() {
            if ('200' == '${code}') {
                wx.scanQRCode({
                    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                    scanType: ["qrCode"], // 可以指定扫二维码还是一维码，默认二者都有
                    success: function (res) {
                        var flowcarUrl = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                        if (!flowcarUrl || -1 == flowcarUrl.indexOf("flowCardController.do?startLoad")) {
                            if (confirm("请扫描流量卡的二维码!")) {
                                startScanFlowCard();
                            } else {
                                wx.closeWindow();
                            }
                            return;
                        }
                        window.location.href = flowcarUrl + "&openId=${openId}&accountId=${accountId}";
                    }
                });
            } else {
                alert('${message}');
                wx.closeWindow();
            }

        }
        wx.ready(function () {
            if ('200' == '${code}') {
                startScanFlowCard();
            } else {
                alert('${message}');
            }
        });
        wx.error(function (res) {
            alert("error: " + res.errMsg);
        });
    </script>
</head>
<body>
<section class="scan-flowcard-wrap">
    
</section>
</body>
</html>