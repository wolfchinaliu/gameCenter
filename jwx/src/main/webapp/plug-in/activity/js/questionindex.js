/**
 * 猜字谜
 * @author minghua
 * @description 依赖zepto, fullAvatarEditor
 * @date 20150218
 */

// 已经踩过灯谜，不能参加活动
function lotteryed() {
    if (code !=0 ) {
        Zepto('body').popup({
            title: '提示'
            , message: '<p>'+msg+'</p>'
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
        var y = 0;
        $('.question').each(function (i) {
            var str = $(this).attr('answer');
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
    
    $('.checkAnswer').click(function(){
    	var value = $(this).val();
    	$(this).parent().parent().attr('answer',value);
    	completeQuestion();
    });

    // 提交答案
    $btnSubmit.click(function () {
        if(flag==false){
            return;
        }
        if (code != 0) {
            Zepto('body').popup({
                title: '提示'
                , message: '<p>'+msg+'</p>'
                , id: 'pop-2'
            });
            //alert("您已经猜过灯谜，不能再猜了，请您期待下次活动！");
            return;
        }

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

    });

    // 获取谜题答案
    function getAnswer() {
        var mitiArr = [];
        $('.question').each(function (i) {

            var miti = {};
            var questionId = $(this).attr('questionId');
            var answer = $(this).attr('answer');
            miti.num = i;
            miti.questionId = questionId;
            miti.answer = answer;
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
                onCancel: function () {
                    continueAnswer(noAnswer);
                },
                cnacel2: "任性提交"
                ,
                onCancel2: function () {
                    continueSubmit(arr, noAnswer);
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
        if (flag == false) {
            return;
        }
        flag = false;
        //1、获取用户的答题情况
        var openId = $("#openId").val();
        var accountId = $("#accountid").val();
        var hdid = $("#activityId").val();

        var len = arr.length, lenNo = noAnswer.length;
        // 一道题都没有答，不能提交
        if (lenNo >= len) {
            Zepto('body').popup({
                title: '提示'
                , message: '<h2>尊敬的用户您好:</h2><p>请您至少回答一题！</p >'
                , id: 'pop-2'
                , ok: '确定'
                , onOk: function () {
                    flag1 = true;
                }
                , onCancel: function () {
                    flag1 = true;
                }
            });
            flag = true;
        }
        else {
        	var answerStr = '{';
        	for(var temp = 0; temp < arr.length ;temp++){
        		answerStr +='"'+arr[temp].questionId+'":"'+arr[temp].answer+'",';
        	}
        	answerStr = answerStr.substring(0,answerStr.length - 1);
        	answerStr += '}';
            $.ajax({
                type: 'POST',
                url: "activityController.do?subQuestionAnswer",
                data: {
                    "answer": answerStr,
                    "openId": openId,
                    "accountId": accountId,
                    "activityId": hdid
                },
                dataType: 'json',
                success: function (data) {
                    if (data.attributes.error == "invalid") {
                        Zepto('body').popup({
                            title: '提示'
                            , message: '<h2>尊敬的用户您好:</h2><p>'+data.attributes.msg+'</p>'
                            , id: 'pop-2'
                            , ok: anniuText
                            , onOk: function () {
                                // 确认按钮的回调函数
                            	if(hasPhone){
                            		window.location.href = "userChargeController.do?userChargeView&accountid="+accountId+"&openId="+openId;
                            		console.log('ok');
                            	}
                            	else
                            		bindPhoneNumber(accountId, openId, nickName, phoneNumber, "答题");
                            	 flag = true;
                            }
                            , cnacel2: "浪费表情"
                            , onCancel2: function () {
                            	 flag = true;
                                console.log('cancel');
                            }
                        });
                    } else {
                        Zepto('body').popup({
                            title: '提示'
                            ,
                            message: '<h2>共' + arr.length + '题</h2><p>已答' + (arr.length - noAnswer.length) + '题,答对' + data.attributes.right + '题，获得' + data.attributes.flow + 'M流量。</p>'
                            ,
                            id: 'pop-2'
                            ,
                            ok: anniuText
                            ,
                            onOk: function () {
                            	if(hasPhone){
                            		window.location.href = "userChargeController.do?userChargeView&accountid="+accountId+"&openId="+openId;
                            		console.log('ok');
                            	}
                            	else
                            		bindPhoneNumber(accountId, openId, nickName, phoneNumber, "答题");
                            	 flag = true;
                            }
                            ,
                            cnacel2: "取消"
                            ,
                            onCancel2: function () {
                            	 flag = true;
                            }
                            ,
                            onCancel: function () {
                            	 flag = true;
                            }
                        });
                    }
                },
                error: function () {
                    alert("请重试");
                    flag = true;
                },
                complete: function () {
                    flag = true;
                }
            });
        }

    }

};


function bindPhoneNumber(accountId, openId, nickname, phoneNumber, operateType) {
    var url ='bindingController.do?redirectBinding';
    url += "&openId=" + openId;
    url += "&merchantId=" + accountId;
    url += "&nickName=" + encodeURIComponent(nickname);
    url += "&phoneNumber=" + phoneNumber;
    url += "&operateType=" + encodeURIComponent(operateType);
    window.location.href = url;
}
$(function () {

    zimiApp();
    lotteryed();

});
