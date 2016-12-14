package weixin.shop.entity;

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
 * @Description: 收货地址管理
 * @author onlineGenerator
 * @date 2015-04-28 14:49:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_shop_address", schema = "")
@SuppressWarnings("serial")
public class WeixinShopAddressEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**粉丝ID*/
	@Excel(exportName="粉丝ID")
	private java.lang.String openId;
	/**收货人*/
	@Excel(exportName="收货人")
	private java.lang.String deliveryName;
	/**收货电话*/
	@Excel(exportName="收货电话")
	private java.lang.String deliveryPhone;
	/**收货省*/
	@Excel(exportName="收货省")
	private java.lang.String province;
	/**收货市*/
	@Excel(exportName="收货市")
	private java.lang.String city;
	/**收货区*/
	@Excel(exportName="收货区")
	private java.lang.String district;
	/**详细地址*/
	@Excel(exportName="详细地址")
	private java.lang.String address;
	/**邮编*/
	@Excel(exportName="邮编")
	private java.lang.String postcode;
	/**默认*/
	@Excel(exportName="默认")
	private java.lang.Integer isDefault;
	/**用户ID*/
	@Excel(exportName="用户ID")
	private java.lang.String userId;
	
	@Excel(exportName="所属公众号")
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
	 *@return: java.lang.String  粉丝ID
	 */
	@Column(name ="OPEN_ID",nullable=true,length=36)
	public java.lang.String getOpenId(){
		return this.openId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  粉丝ID
	 */
	public void setOpenId(java.lang.String openId){
		this.openId = openId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货人
	 */
	@Column(name ="DELIVERY_NAME",nullable=true,length=30)
	public java.lang.String getDeliveryName(){
		return this.deliveryName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货人
	 */
	public void setDeliveryName(java.lang.String deliveryName){
		this.deliveryName = deliveryName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货电话
	 */
	@Column(name ="DELIVERY_PHONE",nullable=true,length=30)
	public java.lang.String getDeliveryPhone(){
		return this.deliveryPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货电话
	 */
	public void setDeliveryPhone(java.lang.String deliveryPhone){
		this.deliveryPhone = deliveryPhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货省
	 */
	@Column(name ="PROVINCE",nullable=true,length=30)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货省
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货市
	 */
	@Column(name ="CITY",nullable=true,length=30)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货区
	 */
	@Column(name ="DISTRICT",nullable=true,length=30)
	public java.lang.String getDistrict(){
		return this.district;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货区
	 */
	public void setDistrict(java.lang.String district){
		this.district = district;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  详细地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=50)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  详细地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮编
	 */
	@Column(name ="POSTCODE",nullable=true,length=30)
	public java.lang.String getPostcode(){
		return this.postcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮编
	 */
	public void setPostcode(java.lang.String postcode){
		this.postcode = postcode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  默认
	 */
	@Column(name ="IS_DEFAULT",nullable=true,length=1)
	public java.lang.Integer getIsDefault(){
		return this.isDefault;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  默认
	 */
	public void setIsDefault(java.lang.Integer isDefault){
		this.isDefault = isDefault;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户ID
	 */
	@Column(name ="USER_ID",nullable=true,length=36)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户ID
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}

	@Column(name ="ACCOUNTID",nullable=true,length=36)
	public java.lang.String getAccountid() {
		return accountid;
	}

	public void setAccountid(java.lang.String accountid) {
		this.accountid = accountid;
	}
	
	
}
