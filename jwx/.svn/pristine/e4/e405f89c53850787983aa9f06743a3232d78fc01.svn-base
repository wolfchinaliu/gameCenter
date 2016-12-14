function scrollTop() {
	
	var $box = $('#j-shake-list');
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
