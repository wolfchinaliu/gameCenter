package weixin.oauth2;

import org.jeecgframework.poi.excel.annotation.Excel;

public class SNSUserInfo {
	private String openId;// 每个用户对每个公众号的OpenID是唯一的

	private java.lang.String nickName;
	/** 性别1男2女0未知 */
	private java.lang.String sex;
	/** city */
	private java.lang.String city;
	/** 用户所在省份 */
	private java.lang.String province;
	/** 用户所在国家 */
	private java.lang.String country;
	/** 用户的语言 */
	private java.lang.String language;
	/** 用户头像 */
	private java.lang.String headImgUrl;
	private java.util.Date subscribeTime;
	/** unionid */
	private java.lang.String unionid;
	/** 是否订阅 */
	private java.lang.String subscribe;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public java.lang.String getSex() {
		return sex;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public java.lang.String getCity() {
		return city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public java.lang.String getProvince() {
		return province;
	}

	public void setProvince(java.lang.String province) {
		this.province = province;
	}

	public java.lang.String getCountry() {
		return country;
	}

	public void setCountry(java.lang.String country) {
		this.country = country;
	}

	public java.lang.String getLanguage() {
		return language;
	}

	public void setLanguage(java.lang.String language) {
		this.language = language;
	}

	public java.lang.String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(java.lang.String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public java.util.Date getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(java.util.Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public java.lang.String getUnionid() {
		return unionid;
	}

	public void setUnionid(java.lang.String unionid) {
		this.unionid = unionid;
	}

	public java.lang.String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(java.lang.String subscribe) {
		this.subscribe = subscribe;
	}

}
