var clientHeight=$(document).height();
var clientWidth=$(document).width();
var omask = '<div class="mask"></div>'
//关闭游戏规则
$(document).click(function(){
	$('#c17_').slideUp();
	$('#c18_').slideUp();
	$('div').remove('.mask');
});
function load_page_1(){	
	$("#c1").css("display","block");
	$("#clogo").css("display","block");
	$("#c2").css("display","block");
	$("#c2").addClass("imgElementClick");
	$("#c15_").css("display", "block"); //活动标题的悬浮层
	$("#c16_").css("display", "block"); //活动标题的悬浮层
	$("").css("display","block");
	$("#c4").css("display","block");
	
	setTimeout(function(){
		if(code != 0)
          	 var dialog = Zepto('body').popup({
                   title:'提示',
                   message:errorMsg,
                   id:'pop-2',
                   ok:anniuText,
                   cnacel:"关闭",
                   closeOnOk: false,
                   onCancel: function() {
                     flag = true;
                   },
                   onOk:function(){
                     flag=true;
                     if ('${member.phoneNumber}' == '') {
                       bindPhoneNumber();
                       return;
                     }
                     window.location.href = rediceUrl;
                   }
          	 });
		$('#c18_').slideDown();
		$('body').append(omask);
		$('.mask').height(clientHeight).width(clientWidth);
	},800);
}
//游戏规则事件
function game_rules(ev){
	var even=ev||event;
	if ($('#c17_').is(':hidden')){
		$('#c17_').slideDown();
		$('body').append(omask);
		$('.mask').height(clientHeight).width(clientWidth);
	} else {
		$('#c17_').slideUp();
		$('div').remove('.mask');
	}
	even.stopPropagation();
}
function load_page_2(){
	$("#c1").css("display","block");
	$("#cclogo").css("display","block");
	$("#c2").css("display","none");
	$("#c3").css("display","none");
	$("#c4").css("display","none");
	$("#c15_").css("display", "none");  //活动标题的悬浮层
	$("#c16_").css("display", "none");  //活动标题的悬浮层
	$("#c29").css("display","block");
	$("#c29").addClass("imgElementClick");
	$("").addClass("imgElementClick");
	$("#c19").addClass("imgElementClick");
	$("#c30").css("display","block");
	$("#c21").css("display","block");
	$("#c25").css("display","block");
	$("#c13").css("display","block");
	$("#c14").css("display","block");
	$("#c14_").css("display","block");
	$("#c16").css("display","block");
	$("#c17").css("display","block");
	$("#c5").css("display","block");
	$("#c6").css("display","block")
}
var baozi_max=50;
var eat_max_time=15;
var first_eat=true;
var finish_eat=false;
var eat_count=0;
var anim_count=0;
var timer=null;
var match_count=0;
var maxScore = Math.floor(Math.random()*(7)+24);
var animArr_1=["#c28","#c26","#c27","#c26","#c27"];
var animArr_2=["#c34","#c31","#c32","#c33","#c35"];
var animArr_3=["#c35m","#c34m","#c31m","#c32m","#c33m"];
function tmpFn(){
	if(anim_count>4){
		anim_count=0
	}
	if(anim_count==2||anim_count==4){
		eat_count++;$("#c14_").html("x "+eat_count)
	}
	if(eat_count>baozi_max*0.33&&eat_count<baozi_max*0.66){
		$("#c6").css("display","none");
		$("#c7").css("display","block");
	}
	else{
		if(eat_count>baozi_max*0.66){
			$("#c7").css("display","none");
			$("#c8").css("display","block");
		}
	}
	animArr_1.forEach(function(a){$(a).css("display","none")});
	$(animArr_1[anim_count]).css("display","block");
	animArr_2.forEach(function(a){$(a).css("display","none")});
	$(animArr_2[anim_count]).css("display","block");
	animArr_3.forEach(function(a){$(a).css("display","none")});
	$(animArr_3[anim_count]).css("display","block");
	anim_count++;
}
function winORlose(){
	if(finish_eat==true){return}
	else{finish_eat=true}
	$("#c29").css("display","none");
	$("#c10").css("display","block");
	animArr_1.forEach(function(a){$(a).css("display","none")});
	animArr_2.forEach(function(a){$(a).css("display","none")});
	animArr_3.forEach(function(a){$(a).css("display","none")});
	if(eat_count<=25){$("#c22").css("display","block")}
	else if(eat_count>25&&eat_count<=50){
		$("#c23").css("display","block")
	}
	else if(eat_count>50){
		$("#c24").css("display","block");
	}
		//TODO 提交数据
	subscore(eat_count);
	clearInterval(timer);
}
	$(document).on("click touchstart","#c2",function(a){
		if(code != 0){
          	 var dialog = Zepto('body').popup({
                   title:'提示',
                   message:errorMsg,
                   id:'pop-2',
                   ok:anniuText,
                   cnacel:"关闭",
                   closeOnOk: false,
                   onCancel: function() {
                     flag = true;
                   },
                   onOk:function(){
                     flag=true;
                     if (!hasPhone) {
                       bindPhoneNumber();
                       return;
                     }
                     window.location.href = rediceUrl;
                   }
          	 });
          	 return false;}
		a.preventDefault();a.stopPropagation();load_page_2()});
	$(document).on("click touchstart", "#c29", function (a) {
	    a.preventDefault();
	    a.stopPropagation();  
	    if (first_eat == true) {
	        $("#c17").animate({width: 0}, eat_max_time * 1000, winORlose);
			timer=setInterval(function(){
				if(eat_count - match_count > maxScore){
				eat_count = 0;
				is_worng = true;
				tmpFn();
				winORlose();
				}
				match_count = eat_count;
			},3000);
	        first_eat = false
	    }
	    $("#c25").css("display", "none");
	    tmpFn();    
	});
	$(document).on("click touchstart","#c19",function(a){
		a.preventDefault();a.stopPropagation();
		window.location.href=link+"&openId="+openId;});
	$(function(){});
	
/*  |xGv00|64afc39a02a4fd356fdf7a63851d2f26 */