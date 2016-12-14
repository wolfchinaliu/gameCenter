package weixin.payment.entity;

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
 * @Description: 支付方式配置
 * @author onlineGenerator
 * @date 2015-06-03 12:45:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_payment_con", schema = "")
@SuppressWarnings("serial")
public class WeixinPaymentConEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**支付名称*/
	@Excel(exportName="支付名称")
	private java.lang.String payName;
	/**说明*/
	@Excel(exportName="说明")
	private java.lang.String payDescription;
	/**支付类型*/
	@Excel(exportName="支付类型")
	private java.lang.String payType;//1：微信支付, 2: 支付宝
	/**公众账号APP_ID*/
	@Excel(exportName="公众账号APP_ID")
	private java.lang.String appId;
	/**微信支付双向认证证书*/
	@Excel(exportName="微信支付双向认证证书")
	private java.lang.String certFileName;
	/**财付通密钥*/
	@Excel(exportName="财付通密钥")
	private java.lang.String partnerKey;
	/**财付通商户号*/
	@Excel(exportName="财付通商户号")
	private java.lang.String partnerId;
	/**合作身份者ID*/
	@Excel(exportName="合作身份者ID")
	private java.lang.String partner;
	/**卖家支付宝帐户*/
	@Excel(exportName="卖家支付宝帐户")
	private java.lang.String sellerEmail;
	/**支付宝key*/
	@Excel(exportName="支付宝key")
	private java.lang.String sellerKey;
	/**财付通商户号*/
	@Excel(exportName="财付通商户号")
	private java.lang.String bargainorId;
	/**财付通密钥*/
	@Excel(exportName="财付通密钥")
	private java.lang.String bargainorKey;
	/**公众号*/
	@Excel(exportName="公众号")
	private java.lang.String accountid;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付名称
	 */
	@Column(name ="PAY_NAME",nullable=true,length=30)
	public java.lang.String getPayName(){
		return this.payName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付名称
	 */
	public void setPayName(java.lang.String payName){
		this.payName = payName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  说明
	 */
	@Column(name ="PAY_DESCRIPTION",nullable=true,length=50)
	public java.lang.String getPayDescription(){
		return this.payDescription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  说明
	 */
	public void setPayDescription(java.lang.String payDescription){
		this.payDescription = payDescription;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付类型
	 */
	@Column(name ="PAY_TYPE",nullable=true,length=2)
	public java.lang.String getPayType(){
		return this.payType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付类型
	 */
	public void setPayType(java.lang.String payType){
		this.payType = payType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众账号APP_ID
	 */
	@Column(name ="APP_ID",nullable=true,length=50)
	public java.lang.String getAppId(){
		return this.appId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众账号APP_ID
	 */
	public void setAppId(java.lang.String appId){
		this.appId = appId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信支付双向认证证书
	 */
	@Column(name ="CERT_FILE_NAME",nullable=true,length=50)
	public java.lang.String getCertFileName(){
		return this.certFileName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信支付双向认证证书
	 */
	public void setCertFileName(java.lang.String certFileName){
		this.certFileName = certFileName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  财付通密钥
	 */
	@Column(name ="PARTNER_KEY",nullable=true,length=50)
	public java.lang.String getPartnerKey(){
		return this.partnerKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  财付通密钥
	 */
	public void setPartnerKey(java.lang.String partnerKey){
		this.partnerKey = partnerKey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  财付通商户号
	 */
	@Column(name ="PARTNER_ID",nullable=true,length=50)
	public java.lang.String getPartnerId(){
		return this.partnerId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  财付通商户号
	 */
	public void setPartnerId(java.lang.String partnerId){
		this.partnerId = partnerId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合作身份者ID
	 */
	@Column(name ="PARTNER",nullable=true,length=50)
	public java.lang.String getPartner(){
		return this.partner;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合作身份者ID
	 */
	public void setPartner(java.lang.String partner){
		this.partner = partner;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卖家支付宝帐户
	 */
	@Column(name ="SELLER_EMAIL",nullable=true,length=50)
	public java.lang.String getSellerEmail(){
		return this.sellerEmail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卖家支付宝帐户
	 */
	public void setSellerEmail(java.lang.String sellerEmail){
		this.sellerEmail = sellerEmail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付宝key
	 */
	@Column(name ="SELLER_KEY",nullable=true,length=50)
	public java.lang.String getSellerKey(){
		return this.sellerKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付宝key
	 */
	public void setSellerKey(java.lang.String sellerKey){
		this.sellerKey = sellerKey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  财付通商户号
	 */
	@Column(name ="BARGAINOR_ID",nullable=true,length=50)
	public java.lang.String getBargainorId(){
		return this.bargainorId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  财付通商户号
	 */
	public void setBargainorId(java.lang.String bargainorId){
		this.bargainorId = bargainorId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  财付通密钥
	 */
	@Column(name ="BARGAINOR_KEY",nullable=true,length=50)
	public java.lang.String getBargainorKey(){
		return this.bargainorKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  财付通密钥
	 */
	public void setBargainorKey(java.lang.String bargainorKey){
		this.bargainorKey = bargainorKey;
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
}
