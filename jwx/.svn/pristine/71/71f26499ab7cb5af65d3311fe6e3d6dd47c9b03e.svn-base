function scrollTop() {
	
	var $box = $('#j-zhuanpan-list');
	var timer = null;
	var items = $box.children();
	var len = items.length;
	var i = 0;
	
	function animateScroll() {
		
	}
	
	if(len > 1) {
		setInterval(function() {
			if(i === (len - 1)) {
				$box.css("top", "0.44rem");
			}
			i = i >= (len - 1) ? 0 : (i + 1);
			$box.animate({ "top" : -i * 0.44 + "rem" }, 200);
		}, 3000);
	}
	
}

scrollTop();

$('#j-zhuanpan-xq').click(function() {
	
	var header = '<h1 class="dialog-header-title1"><img src="../../res/images/dialog/title2.png" alt="" /></h1>\
		<h2 class="dialog-header-title2">活动详情</h2>';
	var main = '<h3><span>奖项设置</span></h3>\
				<ul class="dialog-list clx">\
					<li style="width: 100%;">\
						<span class="left">一等奖</span>\
						<span class="right">100M</span>\
					</li>\
					<li style="width: 100%;">\
						<span class="left">二等奖</span>\
						<span class="right">50M</span>\
					</li>\
					<li style="width: 100%;">\
						<span class="left">三等奖</span>\
						<span class="right">20M</span>\
					</li>\
				</ul>\
				<h3><span>温馨提示</span></h3>\
				<p class="dialog-msg">每人每天仅有一次机会哦！</p>';
	$.dialog({
		header: header,
		main: main
	});
});
