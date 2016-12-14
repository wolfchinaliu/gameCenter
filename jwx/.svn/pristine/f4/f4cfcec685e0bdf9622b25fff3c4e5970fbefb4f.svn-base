package weixin.gameCenter.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auth popl
 * @Date 2016年9月9日 上午11:40:41
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.service.resultCode
 * @dec 返回码的枚举
 */
public enum ResultCode {
	noGame(101,"游戏不存在"),noip(102,"IP鉴权失败"),noGameTime(103,"不在游戏时间内"),safeFail(104,"安全规则校验失败"),praDecError(105,"参数解密失败"),
	parMiss(106,"参数缺失"),parError(107,"参数错误"),pwdError(201,"用户密码错误"),flowError(108,"流量赠送失败"),
	noSafe(109,"不符合安全规则"),noArea(2,"手机号不在活动区"),noBinding(1,"未绑定手机"),success(0,"成功"),findEx(500,"出现异常");
	private String msg;
	private int code;
	
	private ResultCode(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	public Map<String,Object> getThisMap(){
		Map<String, Object> resMap = new HashMap<String,Object>();
		resMap.put("code", this.code);
		resMap.put("msg", this.msg);
		return resMap;
	}
	public int getCode(){
		return this.code;
	}
	public String getMsg(){
		return this.msg;
	}
	
	public static String getMsg(int code){
		for(ResultCode res : ResultCode.values()){
			if(res.getCode() == code){
				return res.getMsg();
			}
		}
		return null;
	}
}
