/**
 * Created by aa on 2015/12/4.
 */
function send(){
    var phoneNumber=document.getElementById("phoneNumber").value;
    var myreg = /^(((106)|(13[0-9]{1})(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\d{8})$/;
    if(!myreg.test(phoneNumber))
    {
        alert('请输入有效的手机号码！');
        return false;

    }else{
        var param={"phoneNumber":phoneNumber};
        $.ajax({
            type:"post",
            url:"bindingController.do?sendMessage",
            dataType:"json",
            async:false,
            data:param,
            success:function(data) {
                alert("短信发送成功，请注意查收！");
            },error:function(error){
                alert("短信发送失败，请稍后重试！")
            },
            //},error:function(XMLHttpRequest ,textStatus,errorThrown){
            //    alert(XMLHttpRequest.status);
            //    alert(XMLHttpRequest.readyState);
            //    alert(textStatus);
            //}
        });
    }
}

function binding(){
    var phoneNumber=document.getElementById("phoneNumber").value;
    var phoneCode=document.getElementById("phoneCode").value;
    var openid=document.getElementById("openid").value;
    var accountid=document.getElementById("accountid").value;
    var nickname=document.getElementById("nickname").value;
    var myreg = /^(((106)|(13[0-9]{1})(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\d{8})$/;
    if(!myreg.test(phoneNumber))
    {
        alert('请输入有效的手机号码！');
        return false;

    }else{
        $.ajax({
            type:"post",
            url:"http://localhost:8080/jwx/userGetFlow/Banding",
            dataType:"json",
            async:false,
            data : {
                "openId" : openid,
                "accountid":accountid,
                "nickname":nickname,
                "phoneNumber":phoneNumber,
                "phoneCode":phoneCode
            },
            success:function(data) {
                alert(data.getAttribute("msg"));
            },error:function(error){
                alert(error.getattribute("msg"));
            }
        });
    }

}