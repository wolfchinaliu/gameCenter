var getType = 1;
var TimeInterval;
$(document).ready(function () {
    $(window).bind("scroll", function () {
        if ($(document).scrollTop() <= 0) {
            var oldheight = document.body.scrollHeight;
            //var page = $("#pageNumber").val();
            //onLoadMessage(page);
            $(document).scrollTop(document.body.scrollHeight - oldheight)
        }

    });
    scrollTop();
    //imgonload();
    //SetAudioEventLister();
    //SocketMessage(1, 2);
    //GetPolling();
});


var scrollTop = function () {
    TscrollTop();
    setTimeout(TscrollTop, 100);
    setTimeout(TscrollTop, 1000);
}

function TscrollTop() {
    $(window).scrollTop(document.body.scrollHeight);
}


//发送消息
function sendMessage(nAId, sWeimobId, msgType, Content) {
    var type;
    var extension;
    $("#sendtext").val("");
    $("#sendtext").html("");
    scrollTop();
    $("#btnsend").addClass("on");

    var date = new Date();
    var time = date.getFullYear() + "" + (date.getMonth() + 1) + "" + date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();

    var r = Math.floor(Math.random() * 100000);
    var id = time + "n" + r;

    var liHtml;
    var voiceId = "";
    var spanId = "";
    switch (msgType) {
        case 'text':
            liHtml = '<ul class="ul_talk reply" id="spanId_'+id+'"><li class="tbox"><div><span class="head"><img src="' + CustomerHeadUrl + '" /></span></div><div><span class="arrow" ><svg><path d="M18,40 A9,5,0,0,0,2,37 L0,23" stroke-width="1" stroke="#2792ff" fill="#2792ff" /></svg></span></div><div><article class="content">' + FormatFaceImage(Content) + '</article></div></li></ul>';
            break;
        default:
        	break;
    }
    spanId = "spanId_" + id;
    $("#containertop").append(liHtml);

    if (voiceId.trim().length > 0) {
        var audio = $("#" + voiceId);
        audio.bind("loadedmetadata", function () {
            showSeconds(this);
        });
    }
    $.ajax({
        type: 'post',
        url: 'weixinCustomerMsgController.do?sendmessage',
        data: {
            weimobid: sWeimobId,
            AId: nAId,
            msgType: msgType,
            receiveOpenId:receiveOpenId,
            nickName:nickName,
            content: Content,
            sendFlag:sendFlag
        },
        dataType: 'json',
        success: function (json) {
        	 if (json.success) {
        		 $("#" + spanId).attr("style", "display: block;");
             }else{
            	 $("#" + spanId).attr("style", "display: none;");
            	 alert(json.msg);
             }
        },
        error: function () {
            $("#" + spanId).attr("style", "display: block;");
        }
    });
}

$.FormatDateTime = function (obj, IsMi) {
    var myDate = new Date(obj);
    var year = myDate.getFullYear();
    var month = ("0" + (myDate.getMonth() + 1)).slice(-2);
    var day = ("0" + myDate.getDate()).slice(-2);
    var h = ("0" + myDate.getHours()).slice(-2);
    var m = ("0" + myDate.getMinutes()).slice(-2);
    var s = ("0" + myDate.getSeconds()).slice(-2);
    var mi = ("00" + myDate.getMilliseconds()).slice(-3);
    if (IsMi == true) {
        return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
    }
    else {
        return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s + "." + mi;
    }
};

//show seconds
function showSeconds(thi) {
    var id = thi.getAttribute("data-id");
    var trueseconds = Math.floor(thi.duration);
    var seconds = trueseconds;
    if (seconds > 90) {
        seconds = 90;
    }
    var fromType = thi.getAttribute("data-value");
    if (fromType == 2) {
        $("#VoiceSecond" + id).attr("style", "margin-left: " + seconds + "px;");
        $("#VoiceSecond" + id).html(trueseconds + "”");
    }
    else {
        $("#replyvoice" + id).attr("style", "margin-left: " + seconds + "px;");
        $("#replysecond" + id).html(trueseconds + "”");
    }
}
function FormatFaceImage(contentFace) {

    var arr = ["smiley001", "smiley002", "smiley003", "smiley004", "smiley005", "smiley006", "smiley007", "smiley008", "smiley009", "smiley010", "smiley011", "smiley012", "smiley013", "smiley014", "smiley015", "smiley016", "smiley017", "smiley018", "smiley019", "smiley020", "smiley021", "smiley022", "smiley023", "smiley024", "smiley025", "smiley026", "smiley027"];

    try {
        for (var i = 0; i < arr.length; i++) {
            var face = arr[i];
            contentFace = contentFace.replace('[', '').replace(']', '').replace(face, "<img src='http://hs-net-img.oss-cn-hangzhou.aliyuncs.com/Face/images/smiley/" + face + ".png' style='-webkit-background-size: auto 24px;width:24px;height:24px'/>");
        }
    } catch (e) {
        alert(e.message);
    }
    return contentFace;
}
