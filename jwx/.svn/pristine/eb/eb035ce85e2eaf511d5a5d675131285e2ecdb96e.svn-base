function scrollTop() {
	
	var $box = $('#j-zhuanpan-list');
	var timer = null;
	var items = $box.children();
	var len = items.length;
	var i = 0;
	
	function animateScroll() {
		
	}
	
	if(len > 3) {
		setInterval(function() {
			if(i === (len - 3)) {
				$box.css("top", "0.4rem");
			}
			i = i >= (len - 3) ? 0 : (i + 1);
			$box.animate({ "top" : -i * 0.4 + "rem" }, 200);
		}, 3000);
	}
	
}

scrollTop();