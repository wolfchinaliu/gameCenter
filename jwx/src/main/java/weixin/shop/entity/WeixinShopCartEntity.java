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

import weixin.goods.entity.WeixinShopGoodsEntity;

/**   
 * @Title: Entity
 * @Description: 购物车
 * @author onlineGenerator
 * @date 2015-04-24 09:40:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_shop_cart", schema = "")
@SuppressWarnings("serial")
public class WeixinShopCartEntity implements java.io.Serializable {
	
	/**主键*/
	private java.lang.String id;
	/**粉丝ID*/
	@Excel(exportName="粉丝ID")
	private java.lang.String memberId;
	
	/**商品ID*/
//	@Excel(exportName="商品ID")
//	private java.lang.String goodsId;
	private WeixinShopGoodsEntity weixinShopGoodsEntity;
	
	private Integer quantity;//数量
	
	/**添加日期*/
	@Excel(exportName="添加日期")
	private java.util.Date createDate;
	
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
	 *@return: java.lang.String  粉丝ID
	 */
	@Column(name ="OPEN_ID",nullable=false,length=36)
	public java.lang.String getMemberId() {
		return memberId;
	}

	public void setMemberId(java.lang.String memberId) {
		this.memberId = memberId;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  添加日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=32)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  添加日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="GOODS_ID")
	public WeixinShopGoodsEntity getWeixinShopGoodsEntity() {
		return weixinShopGoodsEntity;
	}

	public void setWeixinShopGoodsEntity(WeixinShopGoodsEntity weixinShopGoodsEntity) {
		this.weixinShopGoodsEntity = weixinShopGoodsEntity;
	}

	@Column(name ="QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公众号
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=36)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公众号
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
}
