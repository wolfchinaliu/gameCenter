(function () {

    // 初始化页面的html标签的font-size属性；使用rem单位
    function initHtmlFontSize() {
        var oHtml = document.getElementsByTagName('html')[0];
        var winWidth;

        function initWidth() {
            //winWidth = screen.width;
            winWidth = (window.innerWidth > 0) ? window.innerWidth : screen.width;
            if (winWidth < 640) {
                oHtml.style.fontSize = 100 * winWidth / 640 + 'px';
            }
        }
        initWidth();

        if (window.addEventListener) {
            window.addEventListener('resize', initWidth);
        }
        else {
            window.addEventListener('onresize', initWidth);
        }
    }
    initHtmlFontSize();

})();