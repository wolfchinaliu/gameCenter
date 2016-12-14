/**
 * Created by aa on 2015/12/3.
 */


//领取流量按钮
function validate  (){
    //alert("validate");
    var phoneNumber=document.getElementById("phoneNumber").value;
    alert(phoneNumber);

    $.ajax({
        type:"get",
        url:"userChargeController/testValidate",
        data:{phoneNumber:phoneNumber}
        /*success:function(msg){
            alert( "123" );
        },
        error: function () {//请求失败处理函数
            console.log("请求失败，无法获取分组数据");
        }*/
    });
    alert("haha");



    req.open("GET","/testValidate?phoneNumber="+phoneNumber,true);
}