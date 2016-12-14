package weixin.member.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 会员信息
 * @author onlineGenerator
 * @date 2015-06-03 13:20:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_membership", schema = "")
@SuppressWarnings("serial")
public class WeixinMembershipEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**公众号*/
	@Excel(exportName="公众号")
	private java.lang.String accountid;
	/**手机号码*/
	@Excel(exportName="手机号码")
	private java.lang.String mobilePhone;
	/**姓名*/
	@Excel(exportName="姓名")
	private java.lang.String fullName;
	/**用户名*/
	@Excel(exportName="用户名")
	private java.lang.String loginName;
	/**密码*/
	@Excel(exportName="密码")
	private java.lang.String loginPassword;
	/**性别*/
	@Excel(exportName="性别")
	private java.lang.String sex;
	/**邮箱*/
	@Excel(exportName="邮箱")
	private java.lang.String email;
	/**身份证*/
	@Excel(exportName="身份证")
	private java.lang.String cardid;
	/**会员类型*/
	@Excel(exportName="会员类型")
	private java.lang.String cardType;
	/**地址*/
	@Excel(exportName="地址")
	private java.lang.String address;
	/**状态*/
	@Excel(exportName="状态")
	private java.lang.String status;
	/**积分*/
	@Excel(exportName="积分")
	private java.math.BigDecimal coin;
	/**加入时间*/
	@Excel(exportName="加入时间")
	private java.util.Date joinTime;
	/**电话*/
	@Excel(exportName="电话")
	private java.lang.String phone;
	/**存款*/
	@Excel(exportName="存款")
	private java.math.BigDecimal balance;
	/**描述*/
	@Excel(exportName="描述")
	private java.lang.String description;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
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
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众号
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=36)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众号
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号码
	 */
	@Column(name ="MOBILE_PHONE",nullable=true,length=20)
	public java.lang.String getMobilePhone(){
		return this.mobilePhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号码
	 */
	public void setMobilePhone(java.lang.String mobilePhone){
		this.mobilePhone = mobilePhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="FULL_NAME",nullable=true,length=32)
	public java.lang.String getFullName(){
		return this.fullName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setFullName(java.lang.String fullName){
		this.fullName = fullName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户名
	 */
	@Column(name ="LOGIN_NAME",nullable=true,length=32)
	public java.lang.String getLoginName(){
		return this.loginName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名
	 */
	public void setLoginName(java.lang.String loginName){
		this.loginName = loginName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  密码
	 */
	@Column(name ="LOGIN_PASSWORD",nullable=true,length=32)
	public java.lang.String getLoginPassword(){
		return this.loginPassword;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  密码
	 */
	public void setLoginPassword(java.lang.String loginPassword){
		this.loginPassword = loginPassword;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="SEX",nullable=true,length=1)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮箱
	 */
	@Column(name ="EMAIL",nullable=true,length=32)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮箱
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证
	 */
	@Column(name ="CARDID",nullable=true,length=32)
	public java.lang.String getCardid(){
		return this.cardid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证
	 */
	public void setCardid(java.lang.String cardid){
		this.cardid = cardid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会员类型
	 */
	@Column(name ="CARD_TYPE",nullable=true,length=36)
	public java.lang.String getCardType(){
		return this.cardType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会员类型
	 */
	public void setCardType(java.lang.String cardType){
		this.cardType = cardType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=32)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=2)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  积分
	 */
	@Column(name ="COIN",nullable=true,length=12)
	public java.math.BigDecimal getCoin(){
		return this.coin;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  积分
	 */
	public void setCoin(java.math.BigDecimal coin){
		this.coin = coin;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  加入时间
	 */
	@Column(name ="JOIN_TIME",nullable=true,length=20)
	public java.util.Date getJoinTime(){
		return this.joinTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  加入时间
	 */
	public void setJoinTime(java.util.Date joinTime){
		this.joinTime = joinTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */
	@Column(name ="PHONE",nullable=true,length=18)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  存款
	 */
	@Column(name ="BALANCE",nullable=true,length=12)
	public java.math.BigDecimal getBalance(){
		return this.balance;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  存款
	 */
	public void setBalance(java.math.BigDecimal balance){
		this.balance = balance;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="DESCRIPTION",nullable=true,length=200)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
}
