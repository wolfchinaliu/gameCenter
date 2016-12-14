
// 此文件两个方法的使用，需在调用方定义几个变量，如：goBind goUserCharge goMain goAcctList 
function showDialog2(message,ok,cancel){
	var dialog = Zepto('body').popup({
        title: '提示',
        message: message,
        id: 'pop-2',
        ok: ok != null? ok.text || "充值":"",
        cnacel: cancel != null ? cancel.text || "关闭" :"",
        closeOnOk: false,
        onCancel: cancel != null ?cancel.fun : null,
        onOk: ok != null? ok.fun : null
    });
}

function processResult(message,code,isBinding,leftTime){
	var msg = message;
	var ok;
	var cancel;
	if(!isBinding){
		msg += '<br><span style="color: red;  font-size: 12px;text-align:center;">(24小时内未验证手机，获得的流量清零)<span>';
		ok = goBind;
		cancel = goMain;
	}else {
		ok = goUserCharge;
		cancel = goMain;
	}

	if(code == null){
		
	} else if(code == '1' || code == '11' || code == '7'){
		if(leftTime!=null && leftTime > 0){
			cancel = goOn;
		} else {
			cancel = goMain;
		}
	} else if(code == "14" || code=="8" || code=="6"){
		cancel = goMain;
		ok = goAcctList;
	} else if(code =="100"){
		cancel = null;
		ok = goMain;
	} else {
		cancel = goMain;
	}
	showDialog2(msg,ok,cancel);
}