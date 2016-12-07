var clientHeight = $(document).height();
var omask = '<div class="mask"></div>';
var clentLeft = ($(window).width() - $('.activities_that').outerWidth(true)) / 2;
var clentTop = ($(window).height() - $('.activities_that').outerHeight(true)) / 4;
$('.activities_btn').click(function(ev) {
	var even = ev || event;
	$('body').append(omask);
	$('.mask').height(clientHeight);
	$('.activities_that').css({
		'display' : 'block',
		'top' : clentTop + 'px',
		'left' : clentLeft + 'px',
	}).addClass('bounceIn');
	return false;
	if (omask) {
		stopImmediatePropagation;
	}
});

function closeWindow(classname) {
	$(classname).remove();
	$('.mask').remove();
}

function popup(title, message, url, button1Name, button2Name, ev) {
	var comTip = '<div class="popup_msg">\
				<p class="msg_title">'
			+ title
			+ '</p>\
				<div class="msg_content">\
					<p class="msg">'
			+ message
			+ '</p>\
					<p class="msg_btn">\
						<a href="javascript:;" onclick="closeWindow(\'.popup_msg\')">'
			+ (button1Name ? button1Name : "取消")
			+ '</a>\
						<a href="'+url+'">'
			+ (button2Name ? button2Name : "确认")
			+ '</a>\
					</p>\
				</div>\
			</div>';
	$('body').append(omask);
	$('body').append(comTip);
	var clentLeft = ($(window).width() - $('.popup_msg').outerWidth(true)) / 2;
	var clentTop = ($(window).height() - $('.popup_msg').outerHeight(true)) / 3;
	var even = ev || event;
	$('.mask').height(clientHeight);
	$('.popup_msg').css({
		'display' : 'block',
		'top' : clentTop + 'px',
		'left' : clentLeft + 'px',
	}).addClass('zoomInDown');
	even.stopPropagation();
	return false;
	if (omask) {
		stopImmediatePropagation;
	}
}
function tipMessage(message, ev) {
	var tipDiv = '<div class="tip_msg"><div class="tipmsg_content">\
		<p class="tipmsg">'
			+ message + '</p></div></div>';
//	$('body').append(omask);
	$('body').append(tipDiv);
	var clentLeft = ($(window).width() - $('.tip_msg').outerWidth(true)) / 2;
	var clentTop = ($(window).height() - $('.tip_msg').outerHeight(true)) / 3;
	var even = ev || event;
	$('.mask').height(clientHeight);
	$('.tip_msg').css({
		'display' : 'block',
		'top' : clentTop + 'px',
		'left' : clentLeft + 'px',
	}).addClass('zoomInDown');
	even.stopPropagation();
	window.setTimeout(function() {
		$('.tip_msg').remove();
//		$('.mask').remove();
	}, 3000);

	return false;
	if (omask) {
		stopImmediatePropagation;
	}

}