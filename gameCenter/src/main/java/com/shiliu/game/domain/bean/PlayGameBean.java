package com.shiliu.game.domain.bean; 
/** 
* @author popl 
* @version 1.0
* @createDate 2016年7月11日 下午5:42:22 
* @description
*/
public class PlayGameBean {
	
	/** 用户的唯一标识  */
	private String openId;
	/** 用户昵称  */
	private String nickName;
	/** 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */
	private String sex;
	/** 用户个人资料填写的省份 */
	private String province;
	/** 普通用户个人资料填写的城市 */
	private String city;
	/** 国家，如中国为CN */
	private String country;
	/** 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 */
	private String headimgurl;
	/**游戏Id*/	
	private String gameId;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickname(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	
}
