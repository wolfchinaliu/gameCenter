(function($) {
	$.mobAlert = function(msg) {
		var $pop = $('<div class="tips-pop"></div>').appendTo('body');
		$pop.html("<p>" + msg + "</p>").css({
			zIndex: 501,
			opacity: 0.85
		});
		var $overlay = $.mobOverwhole();
		$pop.fadeIn(function() {
			$overlay.bind('click', function() {
				$pop.fadeOut(function() {
					$pop.remove();
				});
				$overlay.fadeOut(function(){
					$overlay.remove();
				});
			});
		});
	};
	$.mobTips = function(msg) {
		var $pop = $('<div class="tips-pop"></div>').appendTo('body');
		$pop.html("<p>" + msg + "</p>").css({
			zIndex: 501,
			opacity: 0.85
		});
		$pop.fadeIn(function() {
			setTimeout(function(){
				$pop.remove();
			},3000);
		});
	};
	$.mobOverwhole = function() {
		$('#jq-overlay-whole').remove();
		var $overlay = $('<div id="jq-overlay-whole" class="overlay"></div>').appendTo('body');
		$overlay.css({
			zIndex: 500,
			opacity: 0.85
		});
		$overlay.fadeIn();
		return $overlay;
	};
	$.fn.mobDialog = function(opt) {
		var that = this;
		if(typeof opt == 'string') {
			$(that).trigger(opt);
			return;
		}
		$(that).unbind('close').bind('close', function() {
			$(that).removeClass("in");
			!!that.overlay && that.overlay.fadeOut(function(){
				that.overlay.remove();
			});
		}).unbind('open').bind('open', function() {
			that.overlay = $.mobOverwhole();
			$(that).addClass('in');
			that.overlay.bind('click', function() {
				$(that).trigger('close');
			});
		}).trigger("open");
	};
})(jQuery);