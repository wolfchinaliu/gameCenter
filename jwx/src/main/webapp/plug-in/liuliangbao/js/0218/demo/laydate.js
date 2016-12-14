define(function (require, exports) {

    var $ = require("../lib/jquery"),
            Laydate = require("../lib/laydate/laydate");


    $(function () {

        var dateMin = new Date();
        var dateMax = new Date();
        dateMax.setDate(dateMax.getDate() + 30);
        // 绑定日期选择事件
        var weddingDate01 = Laydate({
            // 触发元素
            elem: '#j-laydate',
            // 触发事件
            event: 'focus',
            // 最小日期
            min: dateMin.toLocaleDateString(),
            // 最大日期
            max: dateMax.toLocaleDateString(),
            // 选中日期后回调函数，date为当前选中的日期
            choose: function (date) {
                console.log(date);
            }
        });

    });

});