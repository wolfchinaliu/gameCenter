package weixin.member.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auth popl
 * @Date 2016年8月4日 下午5:32:10
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.member.entity.WeixinBlackListEntity
 * @dec  黑名单
 */
@Entity
@Table(name = "weixin_blacklist", schema = "")
@SuppressWarnings("serial")
public class WeixinBlackListEntity implements Serializable{

	private String id;
	
	private Integer state;
	
	private Date addTime;
	
	private String enabledDec;
	
	private String disableDec;
	
	private Date enabledTime;
	
	private Date disableTime;
	
	@Id
	@Column(name ="PHONE_NUMBER",nullable=false,length=15)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Column(name ="STATE",nullable=true,length=4)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	@Column(name ="ADD_TIME",nullable=true)
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name ="ENABLED_DEC",nullable=true,length=500)
	public String getEnabledDec() {
		return enabledDec;
	}

	public void setEnabledDec(String enabledDec) {
		this.enabledDec = enabledDec;
	}

	@Column(name ="DISABLE_DEC",nullable=true,length=500)
	public String getDisableDec() {
		return disableDec;
	}

	public void setDisableDec(String disableDec) {
		this.disableDec = disableDec;
	}

	@Column(name ="ENABLED_TIME",nullable=true)
	public Date getEnabledTime() {
		return enabledTime;
	}

	public void setEnabledTime(Date enabledTime) {
		this.enabledTime = enabledTime;
	}

	@Column(name ="DISABLE_TIME",nullable=true)
	public Date getDisableTime() {
		return disableTime;
	}

	public void setDisableTime(Date disableTime) {
		this.disableTime = disableTime;
	}
	
	
}
