(function () {

    function initHtmlFontSize() {
        var oHtml = document.getElementsByTagName('html')[0];
        var winWidth;

        function initWidth() {
            //winWidth = screen.width;
            winWidth = (window.innerWidth > 0) ? window.innerWidth : screen.width;
            if (winWidth < 750) {
                oHtml.style.fontSize = 100 * winWidth / 750 + 'px';
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