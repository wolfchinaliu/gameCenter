package weixin.idea.huodong.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 活动中奖纪录
 * @author zhangdaihao
 * @date 2014-06-08 14:28:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_prizerecord", schema = "")
@SuppressWarnings("serial")
public class PrizeRecordEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**活动主键*/
	private java.lang.String hdid;
	/**手机号码*/
	private java.lang.String mobile;
	/**奖项*/
	private java.lang.String prize;
	/**时间*/
	private java.util.Date addtime;
	//openId
	private String openid;
	private String accountid;
	
	private String province;
	private String city;
	private String district;
	private String address;
	private String zipcode;
	private String name;
	
	
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=100)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动主键
	 */
	@Column(name ="HDID",nullable=true,length=100)
	public java.lang.String getHdid(){
		return this.hdid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动主键
	 */
	public void setHdid(java.lang.String hdid){
		this.hdid = hdid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号码
	 */
	@Column(name ="MOBILE",nullable=true,length=100)
	public java.lang.String getMobile(){
		return this.mobile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号码
	 */
	public void setMobile(java.lang.String mobile){
		this.mobile = mobile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  奖项
	 */
	@Column(name ="PRIZE",nullable=true,length=100)
	public java.lang.String getPrize(){
		return this.prize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  奖项
	 */
	public void setPrize(java.lang.String prize){
		this.prize = prize;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  时间
	 */
	@Column(name ="ADDTIME",nullable=true)
	public java.util.Date getAddtime(){
		return this.addtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  时间
	 */
	public void setAddtime(java.util.Date addtime){
		this.addtime = addtime;
	}
	
	@Column(name ="province",nullable=true)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name ="openid",nullable=true)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name ="city",nullable=true)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@Column(name ="district",nullable=true)
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	@Column(name ="address",nullable=true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name ="zipcode",nullable=true)
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name ="name",nullable=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	
}
