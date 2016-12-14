package weixin.business.entity;

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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 微信WIFI
 * @author onlineGenerator
 * @date 2015-07-08 13:47:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_bizwifi", schema = "")
@SuppressWarnings("serial")
public class WeixinBizwifiEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**公众号*/
	@Excel(exportName="公众号")
	private java.lang.String accountid;
//	/**门店ID*/
//	@Excel(exportName="门店ID")
//	private java.lang.String shopId;
	/**无线ssid*/
	@Excel(exportName="无线ssid")
	private java.lang.String ssid;
	/**无线密码*/
	@Excel(exportName="无线密码")
	private java.lang.String password;
	/**mac地址*/
	@Excel(exportName="mac地址")
	private java.lang.String bssid;
	/**二维码*/
	@Excel(exportName="二维码")
	private java.lang.String qrcodeImg0;
	/**桌贴二维码*/
	@Excel(exportName="桌贴二维码")
	private java.lang.String qrcodeImg1;
	
	//门店
	private WeixinLocationEntity weixinLocationEntity;
	
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
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
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
//	/**
//	 *方法: 取得java.lang.String
//	 *@return: java.lang.String  门店ID
//	 */
//	@Column(name ="SHOP_ID",nullable=true,length=36)
//	public java.lang.String getShopId(){
//		return this.shopId;
//	}
//
//	/**
//	 *方法: 设置java.lang.String
//	 *@param: java.lang.String  门店ID
//	 */
//	public void setShopId(java.lang.String shopId){
//		this.shopId = shopId;
//	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SHOP_ID")
	public WeixinLocationEntity getWeixinLocationEntity() {
		return weixinLocationEntity;
	}

	public void setWeixinLocationEntity(WeixinLocationEntity weixinLocationEntity) {
		this.weixinLocationEntity = weixinLocationEntity;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  无线ssid
	 */
	@Column(name ="SSID",nullable=true,length=50)
	public java.lang.String getSsid(){
		return this.ssid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  无线ssid
	 */
	public void setSsid(java.lang.String ssid){
		this.ssid = ssid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  无线密码
	 */
	@Column(name ="PASSWORD",nullable=true,length=32)
	public java.lang.String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  无线密码
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  mac地址
	 */
	@Column(name ="BSSID",nullable=true,length=50)
	public java.lang.String getBssid(){
		return this.bssid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  mac地址
	 */
	public void setBssid(java.lang.String bssid){
		this.bssid = bssid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二维码
	 */
	@Column(name ="QRCODE_IMG0",nullable=true,length=100)
	public java.lang.String getQrcodeImg0(){
		return this.qrcodeImg0;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二维码
	 */
	public void setQrcodeImg0(java.lang.String qrcodeImg0){
		this.qrcodeImg0 = qrcodeImg0;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  桌贴二维码
	 */
	@Column(name ="QRCODE_IMG1",nullable=true,length=100)
	public java.lang.String getQrcodeImg1(){
		return this.qrcodeImg1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  桌贴二维码
	 */
	public void setQrcodeImg1(java.lang.String qrcodeImg1){
		this.qrcodeImg1 = qrcodeImg1;
	}
}
