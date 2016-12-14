package weixin.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auth popl
 * @Date 2016年10月24日 下午4:31:45
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.activity.entity.WeixinAcountOuterEntity
 * @dec  微信商户与外部对应关系表
 */
@Entity
@Table(name = "weixin_acount_outer", schema = "")
//@SuppressWarnings("serial")
public class WeixinAcountOuterEntity {

	private String accountId;
	
	private String wshAppid;
	
	private String wshAppKey;

	@Id
	@Column(name ="account_id",length=36)
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	@Column(name ="wsh_appid",length=36)
	public String getWshAppid() {
		return wshAppid;
	}

	public void setWshAppid(String wshAppid) {
		this.wshAppid = wshAppid;
	}
	@Column(name ="wsh_appkey",nullable=false,length=36)
	public String getWshAppKey() {
		return wshAppKey;
	}

	public void setWshAppKey(String wshAppKey) {
		this.wshAppKey = wshAppKey;
	}
	
	
}
