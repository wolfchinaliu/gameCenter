<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta content="yes" name="apple-mobile-web-app-capable"></meta>
<meta content="black" name="apple-mobile-web-app-status-bar-style"></meta>
<meta name="format-detection" content="telephone=no"></meta>
<meta content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport"></meta>
<meta content="yes" name="full-screen"></meta>
<meta content="true" name="x5-fullscreen"></meta>
<meta content="application" name="browsermode"></meta>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<link href="${pageContext.request.contextPath}/css/base.css" type="text/css" rel="stylesheet"/>
<link id="skin" rel="stylesheet" href="${pageContext.request.contextPath}/css/jbox/jbox.css"/>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery-migrate-1.4.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jbox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jbox/jquery.jBox-zh-CN.js"></script>
<link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet"/>
<title>投票</title>
<script type="text/javascript">
$(function(){
	//alert($('.wrap').width());
	var contes=$('.progressList');
	for(var i=0;i<contes.length;i++){
		var dataWidth=parseInt((contes.find('span').eq(i).text())*2);
		$('.progress').eq(i).width(dataWidth);
		if($('.progress').eq(i).width()==$('.wrap').width()){
			$('.progress').eq(i).css('background','red');
			contes.find('span').eq(i).append('<span>爆票中……</span>');
		}
	}
});

var voteId = '${vote.voteId}';
var openid = '${bean.openId}';
var voteEndTime = '${vote.voteEndTimeF}';
var userGameId = ${userGame.userGameId};
var playTimes = ${userGame.playTimes};
var canPlayTimes = ${userGame.totalTimes};
var max_num = ${vote.voteNum};
var canPlay = true;
var optionsIds =[ ]; 
$(document).ready(
		function() {
			//判断是否结束
			
			if(canPlayTimes != 0 && canPlayTimes <= playTimes)
				canPlay = false;
		});
function voteSub(id){
	if(!canPlay){
		return ;
	}
	var index=optionsIds.indexOf(id);
	if(index==-1){
		if(optionsIds.length>=max_num){
			$.jBox.tip('你的投票已经超过最大张数', 'warn');
		}
		else{
			optionsIds.push(id);
			$('#'+id).html('取消投票').css('background','#F24D4F');
		}
	}
	else{
		optionsIds.splice(index,1);
		$('#'+id).html('为我投票').css('background','#FFB24A');
	}
}
function submitSelect(){
	if(!canPlay){
		$.jBox.tip('你已参加过票了', 'warn');
		return;
	}
	if(optionsIds.length < 1){
		$.jBox.tip('你有选择投票', 'warn');
		return;
	}
	var str = "";
	for (var i = 0; i < optionsIds.length ;i++){
		str += optionsIds[i]+",";
	}
	str = str.substring(0,str.length -1);
	jQuery.ajax({
		url:'${pageContext.request.contextPath}/vote/submitSelect.htm',
		data:{'openid':openid,'voteId':'${vote.voteId}','options':str,'userGameId':userGameId},
		dataType:'json',
	 	type:'post',
	 	contentType: "application/x-www-form-urlencoded;charset=utf-8",
	 	success:function(data){
    	 	if(data.code==0){
    	 		var submit = function (v, h, f) {
    	 		    if (v == true)
    	 		    	recharge();
    	 		    else
    	 		    	bingPhoneNumber();
    	 		    return true;
    	 		};

    	 		// 自定义按钮

    	 		$.jBox.confirm("天使，做我女朋友吧？", "表白提示", submit, { buttons: { '恩': true, '好吖': false} });
    	 	}else{
    	 		$.jBox.tip('提交失败。', 'error');
    	 	}
        }
	});
}
function bingPhoneNumber(){
	parent.bindPhoneNumber();
}
function recharge(){
	parent.drawFlow();
}
</script>
<style type="text/css">
.header_banner{width:100%;height:11em;background:url(${pageContext.request.contextPath}/${vote.imgUrl}) 0 center no-repeat;background-size:cover;}
.pressboard{width:60px;background:red;}
</style>
</head>
<body>
	<div class="Nqqx_wrapper">
  		<!--ceonter-->
	    <div class="Nqqx_conter">
			<section class="header_banner"> </section>
			
			<section class="vote_state">  <!--${pageContext.request.contextPath}${vote.imgUrl}-->
				<p class="vote_origin"> <img src="${pageContext.request.contextPath}/img/tips.png"/> </p>
				<div class="state_main">
					<h5 class="state_title">投票说明 </h5>
					<ul class="state_content">
						<li>参与投票可获取流量</li>
						<li>每次投票可获得${vote.voteFlow}M 每次可投${vote.voteNum}张</li>
						<li>${vote.text }</li>
					</ul>
				</div>
			</section>

			<ul class="vote_list">
				<c:forEach items="${options }" var="option">
				<li class="vote_item">
					<p class="head_icon">
						<img src="${pageContext.request.contextPath}/${option.imgUrl}" />
					</p>
					<ul class="list_right">
						<li>${option.text }</li>
						<li class="progressList">
							<!--<ul class="pressboard">
								<li>七点</li>
								<li>20</li>
								<li>30</li>
							</ul>-->
							
							<div class="wrap">
								<div class="progress"></div>
							</div>
							<span>${option.voteNum}</span> 票
						</li>
						<li class="vote_btn" id="${option.optionId }" onclick="voteSub('${option.optionId}')">
							为我投票
						</li>
					</ul>
					</li>
				</c:forEach>
				<li class="btn_submit"> 
					<a id="submit" href="javascript:submitSelect()">
						提交投票
					</a>
				</li>
			</ul>

			<%-- <section class="give_state">
				<p>赠送说明</p>
				<ul>
					<li>1.赠送流量的主要部分内容是投票获取</li>
					<li>2.每次投票可获得${vote.voteFlow}M</li>
				</ul>
			</section> --%>
			
	    </div>
	<!--Nqqx_conter end-->
	</div>
</body>
</html>