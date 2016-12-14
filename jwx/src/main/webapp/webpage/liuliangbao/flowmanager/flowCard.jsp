<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <title>流量卡</title>
    <script type="text/javascript" src="plug-in/jquery/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="plug-in/liuliangbao/js/1216/util.js"></script>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/1218/style.css">
</head>
<body>
<section class="flow-card">
    <p class="tips">${accountName}商家流量卡流量值：${flowValue}M</p>
    <form class="form-wrap">
        <div class="row">
            <input id="phoneNumber" type="text" name="tel" placeholder="请输入手机号码"/>
        </div>
        <p class="btn-wrap"><span class="btn btn-primary jq-comfirm">确认充值</span></p>
        <p class="note">温馨提示：流量将充值到您的流量银行，您可进入商家公众号进行领取</p>
    </form>
</section>
<div class="overlay"></div>
<div class="tips-pop">
    <p></p>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $('.jq-comfirm').on('click',function(){
            var myreg = /^(((106)|(13[0-9]{1})(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\d{8})$/;
            var phoneNumber=document.getElementById("phoneNumber").value;
            if(!myreg.test(phoneNumber))
            {
                $('.tips-pop p').text('请输入有效的手机号码！')
                $('.tips-pop').addClass('in');
                $('.dialog-modal').removeClass('in');
                window.setTimeout(function(){
                    $('.overlay,.tips-pop').removeClass('in');
                },1500);
                return false;}else {
//                $('.overlay,.dialog-modal').addClass('in');

                $.ajax({
                    type: "POST",
                    url: "flowCardController.do?goGetFlow",
                    dataType: "JSON",
                    async: false,
                    data: {
                        "phoneNumber": phoneNumber
                    },
                    success: function (data) {
                        alert(1);

                    }, error: function (error) {

                    },
                });

            }
        });

    });
</script>
</body>
</html>