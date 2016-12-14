package weixin.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: 关注用户管理
 * @author onlineGenerator
 * @date 2015-01-21 17:37:26
 * @version V1.0
 *
 */
@Entity
@Table(name = "weixin_member", schema = "")
@SuppressWarnings("serial")
public class WeixinMemberEntity implements java.io.Serializable {

	/**用户的标识*/
	//@Excel(exportName="用户系统唯一标识")
	private java.lang.String id;

	//@Excel(exportName="用户openid")
	private String openId;//每个用户对每个公众号的OpenID是唯一的

	/**昵称*/
	@Excel(exportName="昵称")
	private java.lang.String nickName;
	/**性别1男2女0未知*/
	@Excel(exportName="性别")
	private java.lang.String sex;
	/**用户所在省份*/
	@Excel(exportName="用户所在省份")
	private java.lang.String province;
	/**用户所在国家*/
	@Excel(exportName="用户所在国家")
	private java.lang.String country;
	/**city*/
	@Excel(exportName="城市")
	private java.lang.String city;
	/**用户的语言*/
	//@Excel(exportName="用户的语言")
	private java.lang.String language;
	/**用户头像*/
//	@Excel(exportName="用户头像")
	private java.lang.String headImgUrl;

	/**手机号*/
//	@Excel(exportName="手机号")
//	private java.lang.String phoneNumber;

	@Excel(exportName="手机号所在城市")
	private java.lang.String cityName;

	@Excel(exportName="手机号所在省")
	private java.lang.String provinceName;


	/**关注时间*/
	@Excel(exportName="关注时间")
	private java.util.Date subscribeTime;
	
	/**取消关注时间**/
	@Excel(exportName="取消关注时间")
	private java.util.Date unsubscribeTime;
	
	/**unionid*/
	private java.lang.String unionid;

	/**是否订阅*/
	@Excel(exportName="是否关注 (1是0否)")
	private java.lang.String subscribe;

	/**所属公众号*/
	private java.lang.String accountId;
	@Excel(exportName="手机号")
	private String phoneNumber;
    private String shiliuOpenId;

    private Short isRealOpenid; 
   
	private java.lang.String msgId;
	@Column(name ="msg_id",nullable=true,length=255)
    public java.lang.String getMsgId() {
		return msgId;
	}

	public void setMsgId(java.lang.String msgId) {
		this.msgId = msgId;
	}

	@Column(name ="PhoneNumber",nullable=true,length=18)
    
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private WeixinGroupEntity weixinGroupEntity;
	

	@Excel(exportName="创建时间")
	private String shortSubscribeTime;


	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户在系统的唯一标识
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户的标识
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	

	@Column(name ="UNSUBSCRIBE_TIME",nullable=true)
	public java.util.Date getUnsubscribeTime() {
		return unsubscribeTime;
	}

	public void setUnsubscribeTime(java.util.Date unsubscribeTime) {
		this.unsubscribeTime = unsubscribeTime;
	}

	@Column(name ="OPEN_ID",nullable=true,length=255)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  昵称
	 */
	@Column(name ="NICK_NAME",nullable=true,length=100)
	public java.lang.String getNickName(){
		return this.nickName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  昵称
	 */
	public void setNickName(java.lang.String nickName){
		this.nickName = nickName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别1男2女0未知
	 */
	@Column(name ="SEX",nullable=true,length=1)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别1男2女0未知
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  city
	 */
	@Column(name ="CITY",nullable=true,length=100)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  city
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户所在省份
	 */
	@Column(name ="PROVINCE",nullable=true,length=100)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户所在省份
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户所在国家
	 */
	@Column(name ="COUNTRY",nullable=true,length=100)
	public java.lang.String getCountry(){
		return this.country;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户所在国家
	 */
	public void setCountry(java.lang.String country){
		this.country = country;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户的语言
	 */
	@Column(name ="LANGUAGE",nullable=true,length=20)
	public java.lang.String getLanguage(){
		return this.language;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户的语言
	 */
	public void setLanguage(java.lang.String language){
		this.language = language;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户头像
	 */
	@Column(name ="HEAD_IMG_URL",nullable=true,length=500)
	public java.lang.String getHeadImgUrl(){
		return this.headImgUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户头像
	 */
	public void setHeadImgUrl(java.lang.String headImgUrl){
		this.headImgUrl = headImgUrl;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  关注时间
	 */
	@Column(name ="SUBSCRIBE_TIME",nullable=true)
	public java.util.Date getSubscribeTime(){
		return this.subscribeTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  关注时间
	 */
	public void setSubscribeTime(java.util.Date subscribeTime){
		this.subscribeTime = subscribeTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  unionid
	 */
	@Column(name ="UNIONID",nullable=true,length=100)
	public java.lang.String getUnionid(){
		return this.unionid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  unionid
	 */
	public void setUnionid(java.lang.String unionid){
		this.unionid = unionid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否订阅
	 */
	@Column(name ="SUBSCRIBE",nullable=true,length=2)
	public java.lang.String getSubscribe(){
		return this.subscribe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否订阅
	 */
	public void setSubscribe(java.lang.String subscribe){
		this.subscribe = subscribe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公众号
	 */
	@Column(name ="ACCOUNT_ID",nullable=true,length=100)
	public java.lang.String getAccountId(){
		return this.accountId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公众号
	 */
	public void setAccountId(java.lang.String accountId){
		this.accountId = accountId;
	}

	@Column(name ="CITYNAME",nullable=true,length=255)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name ="PROVINCENAME",nullable=true,length=255)
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="GROUP_ID")
	public WeixinGroupEntity getWeixinGroupEntity() {
		return weixinGroupEntity;
	}

	public void setWeixinGroupEntity(WeixinGroupEntity weixinGroupEntity) {
		this.weixinGroupEntity = weixinGroupEntity;
	}

    @Column(name = "shiliu_open_id", nullable = true, length = 36)
    public String getShiliuOpenId() {
        return shiliuOpenId;
    }

    public void setShiliuOpenId(String shiliuOpenId) {
        this.shiliuOpenId = shiliuOpenId;
    }
	
    public void setShortSubscribeTime(String shortSubscribeTime) {
		this.shortSubscribeTime = shortSubscribeTime;
	}
	@Column(name = "is_real_openid" ,length = 36)
	public Short getIsRealOpenid() {
		return isRealOpenid;
	}

	public void setIsRealOpenid(Short isRealOpenid) {
		this.isRealOpenid = isRealOpenid;
	}

	@Transient
	public String getShortSubscribeTime() {
		return shortSubscribeTime;
	}
}
