
var zimiApp = function () {

	var $num = $("#j-zimi-num");
    var $box = $("#j-zimi-box");
    var $items = $box.children();
    var $btnPrev = $("#j-btn-prev");
    var $btnNext = $("#j-btn-next");
    var i = 0;
    var x = 0;
    var len = $items.length;

    // 动画效果
    function animateUL() {
    	// 按钮状态
    	if(i == 0) {
    		$btnPrev.removeClass('red').addClass('c666');
    		$btnNext.removeClass('c666').addClass('red');
    	}
    	else if(i == 7) {
    		$btnNext.removeClass('red').addClass('c666');
    		$btnPrev.removeClass('c666').addClass('red');
    	}
    	else {
    		$btnNext.removeClass('c666').addClass('red');
    		$btnPrev.removeClass('c666').addClass('red');
    	}
        // 选中项
        $num.text(i + 1);
        // 移动距离
        $box.animate({ "left": -i * 6.1 + "rem" }, 200);
    }

    // 下一题
    $btnNext.click(function () {

        i = i >= (len - 1) ? (len - 1) : i + 1;
        animateUL();

    });

    // 上一题
    $btnPrev.click(function () {

        i = i <= 0 ? 0 : i - 1;
        animateUL();

    });

  }

$(function () {

    zimiApp();

});
