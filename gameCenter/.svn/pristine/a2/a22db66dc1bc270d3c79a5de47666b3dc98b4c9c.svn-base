(function(a) {
	var _minTopHeight = 180;//dom元素在iframe最底部，弹出的jbox往上移动像素
	function calcTopPos(obj, mheight) {
		var X = $(obj).offset().top;
		var documentHeight = $(document).height(); // 浏览器当前窗口文档的高度
		if (mheight == null || mheight == undefined) {
			mheight=0;
		}
		if (documentHeight - X < _minTopHeight) {
		    //【iframe高度】-【触发元素的top值】<【_minTopHeight】，jbox不能完全在浏览器中完全显示
		    // jbox的top位置需要往上调整到可显示完全的位置
		    if(mheight>_minTopHeight){
		        return X-mheight;
		    }
			return X - _minTopHeight;
		}
		return X - mheight;
	};
	function getOptions(obj, options, mheight) {
		if(window.self.frameElement&&window.self.frameElement.tagName=="IFRAME"){//页面是否在iframe内
			var returnOptions = a.extend({}, {}, options);
			var top = calcTopPos(obj, mheight);
			if (top) {
				returnOptions = a.extend({},returnOptions, {
							top : top
						});
			}
			return returnOptions;
		}
		return options;
	};

	a.jBox.alert_iframe = function(obj, e, t, n, mheight) {
		var options = getOptions(obj, n, mheight);
		a.jBox.alert(e, t, options);
	}, a.jBox.info_iframe = function(obj, e, t, n, mheight) {
		var options = getOptions(obj, n, mheight);
		a.jBox.info(e, t, options)
	}, a.jBox.success_iframe = function(obj, e, t, n, mheight) {
		var options = getOptions(obj, n, mheight);
		a.jBox.success(e, t, options);
	}, a.jBox.error_iframe = function(obj, e, t, n, mheight) {
		var options = getOptions(obj, n, mheight);
		a.jBox.error(e, t, options);
	}, a.jBox.confirm_iframe = function(obj, b, c, d, e, mheight) {
		debugger;
		var options = getOptions(obj, e, mheight);
		a.jBox.confirm(b, c, d, options);
	}, a.jBox.warning_iframe = function(obj, b, c, d, e, mheight) {
		var options = getOptions(obj, e, mheight);
		a.jBox.warning(b, c, d, options);
	}, a.jBox.tip_iframe = function(obj, e, t, n, mheight) {
		var options = getOptions(obj, n, mheight);
		a.jBox.tip(e, t, options);
	}, a.jBox.messager_iframe = function(obj, e, t, n, r, mheight) {
		var options = getOptions(obj, r, mheight);
		a.jBox.messager(e, t, n, options);
	}, a.jBox.jbox_iframe = function(obj, e, t, mheight) {
		var options = getOptions(obj, t, mheight);
		a.jBox(e, options);
	};
})(jQuery);