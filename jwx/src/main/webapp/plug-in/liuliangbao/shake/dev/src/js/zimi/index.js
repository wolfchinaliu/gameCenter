/**
 * 猜字谜
 * @author minghua
 * @description 依赖zepto, fullAvatarEditor
 * @date 20150218
 */

// 已经踩过灯谜，不能参加活动
function lotteryed() {
    var ishave;
    //ishave = '${ishave}';
    ishave = $("#ishave").val();
    if (ishave == "1") {
        Zepto('body').popup({
            title: '提示'
            , message: '<p>您已经猜过灯谜，不能再猜了，请您期待下次活动！</p>'
            , id: 'pop-2'
        });
        //alert("您已经猜过灯谜，不能再猜了，请您期待下次活动！");
    }
}

var zimiApp = function () {

    var $header = $("#j-zimi-header");
    var $headerChild = $header.children();
    var $box = $("#j-zimi-box");
    var $items = $box.children();
    var $btnPrev = $("#j-btn-prev");
    var $btnNext = $("#j-btn-next");
    var $btnSubmit = $("#j-btn-submit");
    var $span = $("#j-zimi-yida");
    var headerNum = $headerChild.length;
    var i = 0;
    var x = 0;

    // 防止重复提交
    var flag = true;


    var flag1=true;
    // 初始化头部标签的长度
    function initHeaderWidth() {
        // 初始化头部标签的长度
        $header.css("width", 1.5 * headerNum + "rem");
        // 显示第一项
        $headerChild.eq(0).addClass("on");
        $items.eq(0).show();
    }

    initHeaderWidth();

    // 动画效果
    function animateUL() {
        // 选中项
        $headerChild.removeClass("on").eq(i).addClass("on");
        // 移动距离
        x = Math.floor(i / 4);
        $header.animate({ "left": -x * 6 + "rem" }, 200);
        // 显示选项卡
        $items.hide().eq(i).show();
        // 显示已答题数量
        completeQuestion();
    }

    // 已答多少题
    function completeQuestion() {

        var $inputs = $box.find("input[type=text]");
        var y = 0;
        $inputs.each(function (i) {
            var str = $(this).val();
            if (!isEmpty(str)) {
                y = y + 1;
            }
        });
        $span.text(y);

    }

    // 下一题
    $btnNext.click(function () {

        i = i >= (headerNum - 1) ? 0 : i + 1;
        animateUL();

    });

    // 上一题
    $btnPrev.click(function () {

        i = i <= 0 ? (headerNum - 1) : i - 1;
        animateUL();

    });

    // 点击事件
    $headerChild.each(function (y) {
        $(this).click(function () {
            i = y;
            animateUL();
        });
    });

    // 向左滑动
    Zepto("#j-zimi-header").parent().swipeLeft(function () {

        var maxTab = Math.floor(headerNum / 4);
        if ((headerNum % 4) > 0) {
            x = x >= (Math.floor(headerNum / 4)) ? 0 : x + 1;
        }
        else {
            x = x >= (Math.floor(headerNum / 4) - 1) ? 0 : x + 1;
        }

        $header.animate({ "left": -x * 6 + "rem" }, 200);

    });

    // 向右滑动
    Zepto("#j-zimi-header").parent().swipeRight(function () {

        var maxTab = Math.floor(headerNum / 4);
        if ((headerNum % 4) > 0) {
            x = x <= 0 ? Math.floor(headerNum / 4) : x - 1;
        }
        else {
            x = x <= 0 ? (Math.floor(headerNum / 4) - 1) : x - 1;
        }

        $header.animate({ "left": -x * 6 + "rem" }, 200);

    });

    // 提交谜题
    $btnSubmit.click(function () {
        if(flag1==false){
            return;
        }
        var ishave;
        ishave = $("#ishave").val();
        if (ishave == "1") {
            Zepto('body').popup({
                title: '提示'
                , message: '<p>您已经猜过灯谜，不能再猜了，请您期待下次活动！</p>'
                , id: 'pop-2'
            });
            //alert("您已经猜过灯谜，不能再猜了，请您期待下次活动！");
            return;
        }

        if (flag) {
            flag = false;
            var mitiArr = getAnswer();
            var isAnswer = isNotAnswer(mitiArr); // true为有未答完题目；false为全部答完。
            if (isAnswer) {
                console.log("有未答完题目");
            }
            else {
                console.log("全部答完");
                var noAnswerArr = getNoAnswer(mitiArr);
                continueSubmit(mitiArr, noAnswerArr);
            }
        }
        else {

        }

    });

    // 获取谜题答案
    function getAnswer() {
        var mitiArr = [];
        $items.each(function (i) {

            var miti = {}, $input = $(this).find("input[type=text]");
            miti.num = i;
            miti.answer = $input.val();
            miti.id = $input.attr("data-id");
            mitiArr.push(miti);

        });
        return mitiArr;
    }

    // 获取未答谜题
    function getNoAnswer(arr) {

        var noAnswer = [], x = 0, len = arr.length, item;

        for (; x < len; x++) {
            item = arr[x];
            if (!isEmpty(item.answer)) {
                //isAnswerNum++;
            }
            else {
                noAnswer.push(item.num + 1);
                result = true;
            }
        }

        return noAnswer;

    }

    // 判定未答谜题
    function isNotAnswer(arr) {

        var result = false, x = 0, len = arr.length, item, isAnswerNum = 0;
        var noAnswer = [];

        for (; x < len; x++) {
            item = arr[x];
            if (!isEmpty(item.answer)) {
                isAnswerNum++;
            }
            else {
                noAnswer.push(item.num + 1);
                result = true;
            }
        }

        if (result) {
            Zepto('body').popup({
                title: '提示'
                ,
                message: '<h2>尊敬的用户您好:</h2><p>共' + len + '题，您已答' + isAnswerNum + '题，还有' + (len - isAnswerNum) + '道题没答完，每答对一道题都送流量的哦，建议答完了再提交吧。您还有第' + noAnswer.join(',') + '题没答。</p>'
                ,
                id: 'pop-1'
                ,
                ok: "继续答题"
                ,
                onOk: function () {
                    // 继续答题
                    continueAnswer(noAnswer);

                }
                ,
                cnacel2: "任性提交"
                ,
                onCancel2: function () {
                    continueSubmit(arr, noAnswer);
                }
                ,
                cnacel3: "浪费表情"
                ,
                onCancel3: function () {
                    console.log(111);
                }
            });
        }

        return result;

    }

    // 判定是否为空
    function isEmpty(str) {
        if (str == null) {
            return true;
        }
        else if (str === "") {
            return true;
        }
        else {
            return false;
        }
    }

    // 继续答题
    function continueAnswer(arr) {

        var x = 0, len = arr.length, item;
        if (len > 0) {
            i = arr[0] - 1;
            animateUL();
        }
        flag = true;

    }

    // 任性提交
    function continueSubmit(arr, noAnswer) {

        //alert(flag1);
        //alert("hello");
        if (flag1 == false) {
            return;
        }
        flag1 = false;
        //1、获取用户的答题情况
        var openId = $("#openId").val();
        var accountId = $("#accountid").val();
        var hdid = $("#hdid").val();
        var phoneNumber = $("#phoneNumber").val();
        var everyFlow = $("#everyFlow").val();
        var totalRiddles = $("#totalRiddles").val();
        var nickName = $("#nickName").val();

        var len = arr.length, lenNo = noAnswer.length;
        // 一道题都没有答，不能提交
        if (lenNo >= len) {
            Zepto('body').popup({
                title: '提示'
                , message: '<h2>尊敬的用户您好:</h2><p>请您至少回答一个谜题！</p>'
                , id: 'pop-2'
            });
            flag = true;
        }
        else {
            //var answer = [
            //    {
            //        num: 0,
            //        id: 'asdfasdfasdf',
            //        answer: '111'
            //    },
            //    {
            //        num: 1,
            //        answer: ''
            //    },
            //    {
            //        num: 2,
            //        answer: '111'
            //    }
            //];
            $.ajax({
                type: 'POST',
                url: "guessRiddlesController.do?submitRiddleAnswer",
                data: {
                    "answer": JSON.stringify(arr),
                    "openId": openId,
                    "accountId": accountId,
                    "hdid": hdid,
                    "phoneNumber": phoneNumber,
                    "everyFlow": everyFlow,
                    "nickName": nickName,
                    "totalRiddles": totalRiddles
                },
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    //var totalRiddles = data.attributes.totalRiddles;
                    //                    var succeed = data.attributes.succeed;
                    //                    var numRiddle = data.attributes.numRiddle;
                    //                data = JSON.parse(data);
                    if (data.msg == "no") {
                        Zepto('body').popup({
                            title: '提示'
                            , message: '<h2>尊敬的用户您好:</h2><p>亲，不好意思，你的答题速度过慢，流量已经被领完了</p>'
                            , id: 'pop-2'
                            , ok: "确定"
                            , onOk: function () {
                                // 确认按钮的回调函数
                                window.location.href = "earnFlowController.do?moreFlow&accountid=" + accountId + "&openId=" + openId + "";
                                console.log('ok');
                            }
                            , cnacel2: "浪费表情"
                            , onCancel2: function () {
                                console.log('cancel');
                            }
                        });
                    } else if (data.msg == "illegal") {
                        Zepto('body').popup({
                            title: '提示'
                            ,
                            message: '<h2>共' + data.attributes.totalRiddles1 + '题</h2><p>已答' + data.attributes.numRiddle + '题,答对' + data.attributes.succeed + '题，获得0M流量。您的手机归属地不是' + $('#provinceAccount').val() + '，不能获得流量，我们提供了一大波免费流量，等你来拿</p>'
                            ,
                            id: 'pop-2'
                            ,
                            ok: "去赚流量"
                            ,
                            onOk: function () {
                                window.location.href = "moreFlowSelfController.do?moreFlowList&phoneNumber=" + phoneNumber + "&accountid=" + accountId + "&openId=" + openId + "";
                            }
                            ,
                            cnacel2: "浪费表情"
                            ,
                            onCancel2: function () {
                                console.log('cancel2');
                            }
                            ,
                            cnacel3: "查看答案"
                            ,
                            onCancel3: function () {
                                console.log('cancel3');
                            }
                        });
                    } else {
                        Zepto('body').popup({
                            title: '提示'
                            ,
                            message: '<h2>共' + data.attributes.totalRiddles1 + '题</h2><p>已答' + data.attributes.numRiddle + '题,答对' + data.attributes.succeed + '题，获得' + data.attributes.totalFlowGet + 'M流量。</p>'
                            ,
                            id: 'pop-2'
                            ,
                            ok: "查看答案"
                            ,
                            onOk: function () {
                                // 确认按钮的回调函数
                                window.location.href = "guessRiddlesController.do?checkAnswers&accountid=" + accountId + "&hdid=" + hdid + "&openId=" + openId + "";
                                console.log('ok');
                            }
                            ,
                            cnacel2: "取消"
                            ,
                            onCancel2: function () {
                                console.log('cancel');
                            }
                        });
                    }
                },
                error: function () {
                    alert("请重试");
                },
                complete: function () {
                    flag = true;
                }
            });
        }

    }

};

$(function () {

    zimiApp();
    lotteryed();

});
