(function($) {
	$.dialog = function(opts) {
		var header = '<header class="dialog-header">' + opts.header + '</header>';
		var main = '<section class="dialog-main">' + opts.main + '</section>';
		var html = '<article class="dialog"><a href="javascript:;" class="dialog-close">close</a>' + header + main + '</article>';
		var $dialog = $(html).appendTo('body');
		var $overlay = $.mobOverwhole();
		$dialog.fadeIn(function() {
			$dialog.find('a.dialog-close').on('click', function() {
				closeDialog();
			});
			$overlay.on('click', function() {
				closeDialog();
			});
		});
		function closeDialog() {
			$dialog.fadeOut(function() {
				$dialog.remove();
			});
			$overlay.fadeOut(function() {
				$overlay.remove()
			});
		}
	},
	$.mobAlert = function(msg) {
		var $pop = $('<div class="tips-pop"></div>').appendTo('body');
		$pop.html("<p>" + msg + "</p>").css({
			zIndex: 501
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
	$.mobTipsAlert = function(obj) {
		var $zz = $('<div id="car-pop-mask" style="opacity:0.6;"></div>').appendTo('body');
		var $pop = $('<div class="flowcard-dialog"></div>').appendTo('body');
		
		var header = '<header><p>' + obj.name + '</p></header>';
		if(obj.logo) {
			header = '<header><a class="flowcard-dialog-logo" href="javascript:;"><img src="' + obj.logo + '" /></a><span>' + obj.name + '</span></header>';
		}
		
		$pop.append(header);
		
		var content = '<section><p>' + obj.msg + '</p></section>';
		if(obj.title) {
			content = '<section><h2>' + obj.title + '</h2><p>' + obj.msg + '</p></section>'
		}
		
		$pop.append(content);
		
		$pop.append('<footer><img src="../../src/images/flowcard/xian.png" alt="" /></footer>').css({
			zIndex: 501
		});
		
		$pop.fadeIn(function() {
			setTimeout(function(){
				$pop.remove();
				$zz.remove();
			},3000);
		});
	};
	$.mobOverwhole = function() {
		$('#jq-overlay-whole').remove();
		var $overlay = $('<div id="jq-overlay-whole" class="overlay"></div>').appendTo('body');
		$overlay.css({
			zIndex: 500,
			opacity: 0.57
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