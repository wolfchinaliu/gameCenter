define(function (require, exports) {

    var $ = require("../lib/jquery"),
            CitySelect = require("../lib/cityselect");

    //设置JSON路径
    //$.cxSelect.defaults.url = Tools.url.static + "res/data/city_zh.json";
    //初始化
    $("#j-page-04").cxSelect({
        selects: ["province", "city", "area"],
        url: "../../../res/data/city_all.js",
        nodata: "none"
    });

});