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
 * @Description: 商品评价
 * @author onlineGenerator
 * @date 2015-04-28 14:49:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_shop_appraise", schema = "")
@SuppressWarnings("serial")
public class WeixinShopAppraiseEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**内容*/
	@Excel(exportName="内容")
	private java.lang.String notes;
	/**商品ID*/
	@Excel(exportName="商品ID")
	private WeixinShopGoodsEntity weixinShopGoodsEntity;
	//private java.lang.String goodsId;
	/**粉丝ID*/
	@Excel(exportName="粉丝ID")
	private java.lang.String openId;
	/**粉丝昵称*/
	@Excel(exportName="粉丝昵称")
	private java.lang.String openName;
	/**审核状态*/
	@Excel(exportName="审核状态")
	private java.lang.Integer status;
	/**星级*/
	@Excel(exportName="星级")
	private java.lang.Integer star;
	
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
	 *@return: java.lang.String  内容
	 */
	@Column(name ="NOTES",nullable=true,length=200)
	public java.lang.String getNotes(){
		return this.notes;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  内容
	 */
	public void setNotes(java.lang.String notes){
		this.notes = notes;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品ID
	 */
//	@Column(name ="GOODS_ID",nullable=true,length=36)
//	public java.lang.String getGoodsId(){
//		return this.goodsId;
//	}
//
//	/**
//	 *方法: 设置java.lang.String
//	 *@param: java.lang.String  商品ID
//	 */
//	public void setGoodsId(java.lang.String goodsId){
//		this.goodsId = goodsId;
//	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="GOODS_ID")
	public WeixinShopGoodsEntity getWeixinShopGoodsEntity() {
		return weixinShopGoodsEntity;
	}

	public void setWeixinShopGoodsEntity(WeixinShopGoodsEntity weixinShopGoodsEntity) {
		this.weixinShopGoodsEntity = weixinShopGoodsEntity;
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
	 *@return: java.lang.String  粉丝昵称
	 */
	@Column(name ="OPEN_NAME",nullable=true,length=30)
	public java.lang.String getOpenName(){
		return this.openName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  粉丝昵称
	 */
	public void setOpenName(java.lang.String openName){
		this.openName = openName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  审核状态
	 */
	@Column(name ="STATUS",nullable=true,length=1)
	public java.lang.Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  审核状态
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  星级
	 */
	@Column(name ="STAR",nullable=true,length=4)
	public java.lang.Integer getStar(){
		return this.star;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  星级
	 */
	public void setStar(java.lang.Integer star){
		this.star = star;
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
