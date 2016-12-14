package weixin.memberStructure.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @Title: Entity
 * @Description: 关注用户详细
 * @author WangPeng
 * @date 2016-11-03 17:37:26
 *
 */


@Entity
@Table(name = "log_member_datailed", schema = "")
@SuppressWarnings("serial")
public class LogMemberDatailedEntity implements Serializable {
	private String id; 
	private String openId;
	private String shiliuOpenId;
	private String nickName;
	private String sex;
	private String city;
	private String province;
	private String country;
	private Date time;
	private String subscribe;
	private String accountId;
	private String acctId;
	private String phoneNumber;
	private Date created;
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=100)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "open_id", nullable = true, length = 255)
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Column(name = "shiliu_open_id", nullable = true, length = 255)
	public String getShiliuOpenId() {
		return shiliuOpenId;
	}
	public void setShiliuOpenId(String shiliuOpenId) {
		this.shiliuOpenId = shiliuOpenId;
	}
	@Column(name = "nick_name", nullable = true, length = 100)
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	@Column(name = "account_id", nullable = true, length = 100)
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	@Column(name = "acct_id", nullable = true, length = 100)
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	 
	    
}