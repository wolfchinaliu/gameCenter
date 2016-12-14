define(function (require, exports) {

    var $ = require("../lib/jquery"),
            datepicker = require("../lib/datepicker/jquery.ui.datepicker");


    $(function () {

        $("#j-date-start").datepicker({
            //defaultDate: "+1w",
            changeMonth: true,
            changeYear: true,
            numberOfMonths: 1,
            onClose: function (selectedDate) {
                $("#j-date-end").datepicker("option", "minDate", selectedDate);
            },
            onSelect: function (selectedDate) {//选择日期后执行的操作  
                console.log(selectedDate);
            }
        });
        $("#j-date-end").datepicker({
            //defaultDate: "+1w",
            changeMonth: true,
            changeYear: true,
            numberOfMonths: 1,
            onClose: function (selectedDate) {
                $("#j-date-start").datepicker("option", "maxDate", selectedDate);
            },
            onSelect: function (selectedDate) {//选择日期后执行的操作  
                console.log(selectedDate);
            }
        });

        $("#j-date-birdth").datepicker({
            //defaultDate: "+1w",
            changeMonth: true,
            changeYear: true,
            numberOfMonths: 1,
            minDate: "1960-01-01",
            maxDate: "2010-12-31",
            yearRange: "-70:+10"
        });

    });

});