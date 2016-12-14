package weixin.lottery.entity;

import javax.persistence.Entity;

/**   
 * @Title: Entity
 * @Description: 抽奖记录表
 * @author onlineGenerator
 * @date 2015-02-05 16:10:41
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class WeixinDrawrecordParam implements java.io.Serializable {

	private java.lang.String id;
	private java.lang.String hdid;
	private java.lang.String opendid;

	private java.lang.String accountid;
	
	private java.lang.Integer counts;

	
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getHdid() {
		return hdid;
	}

	public void setHdid(java.lang.String hdid) {
		this.hdid = hdid;
	}

	public java.lang.String getOpendid() {
		return opendid;
	}

	public void setOpendid(java.lang.String opendid) {
		this.opendid = opendid;
	}

	public java.lang.String getAccountid() {
		return accountid;
	}

	public void setAccountid(java.lang.String accountid) {
		this.accountid = accountid;
	}

	public java.lang.Integer getCounts() {
		return counts;
	}

	public void setCounts(java.lang.Integer counts) {
		this.counts = counts;
	}
	
}
