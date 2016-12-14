package org.jeewx.api.wxplatform.model;

import java.util.List;

public class AuthorizationInfo {
	/** 授权方appid **/
	private String appid;
	/** 公众号授权给开发者的权限集列表 **/
	private List<FuncInfo> func_info;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public List<FuncInfo> getFunc_info() {
		return func_info;
	}

	public void setFunc_info(List<FuncInfo> func_info) {
		this.func_info = func_info;
	}

}