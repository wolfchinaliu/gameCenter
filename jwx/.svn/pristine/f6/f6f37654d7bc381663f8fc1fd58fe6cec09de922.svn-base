package weixin.member.view;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.poi.excel.annotation.Excel;

import weixin.util.DataDictionaryUtil.FlowType;

/**
 * Created by wangpeng on 2016/10/11.
 */
public class WeixinMemberBean implements java.io.Serializable {
    
	/**用户的标识*/
	//@Excel(exportName="用户系统唯一标识")
	private java.lang.String id;

	//@Excel(exportName="用户openid")
	private String openId;//每个用户对每个公众号的OpenID是唯一的

	
	/**昵称*/
	@Excel(exportName="昵称")
	private java.lang.String nickName;
	/**性别1男2女0未知*/
	@Excel(exportName="性别" , exportConvertSign = 1)
	private java.lang.String sex;
	/**用户所在省份*/
	@Excel(exportName="用户所在省份")
	private java.lang.String province;
	
	public String convertGetSex() {
		if (StringUtils.equals("1", sex)) {
			return "男";
		}
		if (StringUtils.equals("2", sex)) {
			return "女";
		}
		if (StringUtils.equals("0", sex)) {
			return "未知";
		}
		return sex;
	}
		
	/**用户所在国家*/
	private java.lang.String country;
	/**city*/
	@Excel(exportName="城市")
	private java.lang.String city;
	/**用户头像*/
//	@Excel(exportName="用户头像")
	private java.lang.String headImgUrl;

	@Excel(exportName="手机号所在城市")
	private java.lang.String cityName;

	@Excel(exportName="手机号所在省")
	private java.lang.String provinceName;
     
	/**创建时间*/
	@Excel(exportName="创建时间")
	private String created;
	
	/**关注时间*/
	@Excel(exportName="关注时间")
	private String subscribeTime;

	/**取消关注时间**/
	@Excel(exportName="取消关注时间")
	private String unsubscribeTime;
	
	/**是否订阅*/
	@Excel(exportName="是否关注" , exportConvertSign = 1)
	private java.lang.String subscribe;
	
	public String convertGetSubscribe() {
		if (StringUtils.equals("1", subscribe)) {
			return "是";
		}
		if (StringUtils.equals("0", subscribe)) {
			return "否";
		}
		return subscribe;
	}
	
	@Excel(exportName="手机号",exportFieldWidth=20 )
	private String phoneNumber;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

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

	public java.lang.String getCity() {
		return city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public java.lang.String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(java.lang.String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public java.lang.String getCityName() {
		return cityName;
	}

	public void setCityName(java.lang.String cityName) {
		this.cityName = cityName;
	}

	public java.lang.String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(java.lang.String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnsubscribeTime() {
		return unsubscribeTime;
	}

	public void setUnsubscribeTime(String unsubscribeTime) {
		this.unsubscribeTime = unsubscribeTime;
	}

	public java.lang.String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(java.lang.String subscribe) {
		this.subscribe = subscribe;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
