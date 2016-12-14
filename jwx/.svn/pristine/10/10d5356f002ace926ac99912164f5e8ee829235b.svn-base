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
 * @Description: 订单明细
 * @author onlineGenerator
 * @date 2015-07-27 17:09:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_order_detail", schema = "")
@SuppressWarnings("serial")
public class WeixinOrderDetailEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**所属订单ID*/
	@Excel(exportName="所属订单ID")
	private java.lang.String orderId;
	/**商品ID*/
	@Excel(exportName="商品ID")
	//private java.lang.String goodsId;
	private WeixinShopGoodsEntity weixinShopGoodsEntity;
	
	/**购买数量*/
	@Excel(exportName="购买数量")
	private java.lang.Integer quantity;
	
	private BigDecimal price;
	
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
	 *@return: java.lang.String  所属订单ID
	 */
	@Column(name ="ORDER_ID",nullable=true,length=36)
	public java.lang.String getOrderId(){
		return this.orderId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属订单ID
	 */
	public void setOrderId(java.lang.String orderId){
		this.orderId = orderId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="GOODS_ID")
	public WeixinShopGoodsEntity getWeixinShopGoodsEntity() {
		return weixinShopGoodsEntity;
	}

	public void setWeixinShopGoodsEntity(WeixinShopGoodsEntity weixinShopGoodsEntity) {
		this.weixinShopGoodsEntity = weixinShopGoodsEntity;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  购买数量
	 */
	@Column(name ="QUANTITY",nullable=true,length=32)
	public java.lang.Integer getQuantity(){
		return this.quantity;
	}

	

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  购买数量
	 */
	public void setQuantity(java.lang.Integer quantity){
		this.quantity = quantity;
	}

	@Column(name ="PRICE",nullable=true,scale=2,length=12)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
}
