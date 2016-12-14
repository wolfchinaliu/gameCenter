package weixin.guanjia.account.service;

import java.io.Serializable;
import java.util.Map;

public class PageAuthCallback implements Serializable {
	// 用户ID
	private String openId;
	
	// 公众号appId
	private String accountId;
	
	// 自定义属性
	private Map<String, String> properties;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}
