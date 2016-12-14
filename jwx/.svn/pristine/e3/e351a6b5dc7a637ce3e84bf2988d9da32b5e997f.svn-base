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
 * @Description: 微信商城
 * @author onlineGenerator
 * @date 2015-04-23 20:17:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_shop", schema = "")
@SuppressWarnings("serial")
public class WeixinShopEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**商城名称*/
	@Excel(exportName="商城名称")
	private java.lang.String shopName;
	/**商城logo*/
	@Excel(exportName="商城logo")
	private java.lang.String shopLogo;
	/**电话*/
	@Excel(exportName="电话")
	private java.lang.String telephone;
	/**地址*/
	@Excel(exportName="地址")
	private java.lang.String address;
	/**所属公众号ID*/
	@Excel(exportName="所属公众号ID")
	private java.lang.String accountid;
	/**介绍*/
	@Excel(exportName="介绍")
	private java.lang.String introduction;
	
	//首页访问链接地址
	private String linkUrl;
	
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
	 *@return: java.lang.String  商城名称
	 */
	@Column(name ="SHOP_NAME",nullable=true,length=50)
	public java.lang.String getShopName(){
		return this.shopName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商城名称
	 */
	public void setShopName(java.lang.String shopName){
		this.shopName = shopName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商城logo
	 */
	@Column(name ="SHOP_LOGO",nullable=true,length=32)
	public java.lang.String getShopLogo(){
		return this.shopLogo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商城logo
	 */
	public void setShopLogo(java.lang.String shopLogo){
		this.shopLogo = shopLogo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */
	@Column(name ="TELEPHONE",nullable=true,length=32)
	public java.lang.String getTelephone(){
		return this.telephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=100)
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
	 *@return: java.lang.String  所属公众号ID
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=36)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公众号ID
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  介绍
	 */
	@Column(name ="INTRODUCTION",nullable=true,length=500)
	public java.lang.String getIntroduction(){
		return this.introduction;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  介绍
	 */
	public void setIntroduction(java.lang.String introduction){
		this.introduction = introduction;
	}
	
	@Column(name ="LINK_URL",nullable=true,length=200)
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
