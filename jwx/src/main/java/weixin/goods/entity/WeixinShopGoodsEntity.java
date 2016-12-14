package weixin.goods.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
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
import org.jeecgframework.poi.excel.annotation.ExcelEntity;

/**   
 * @Title: Entity
 * @Description: 商品信息
 * @author onlineGenerator
 * @date 2015-01-20 17:31:31
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_shop_goods", schema = "")
@SuppressWarnings("serial")
public class WeixinShopGoodsEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**修改人名称*/
	private java.lang.String updateName;
	/**修改日期*/
	private java.util.Date updateDate;
	
	/**标题信息*/
	@Excel(exportName="标题信息")
	private java.lang.String title;
	
	/**标题图片*/
	//@Excel(exportName="标题图片")
	private java.lang.String titleImg;
	
	/**商品详情*/
	@Excel(exportName="商品文字详情")
	private java.lang.String descriptions;
	
	/**商品原价*/
	@Excel(exportName="商品价格")
	private BigDecimal price;
	
	/**商品现价*/
	private BigDecimal realPrice;
	
	/**折扣*/
	private BigDecimal sale;
	
	/**销售数量*/
	private java.lang.Integer sellCount;
	
	/**评价数量*/
	private java.lang.Integer discussCount;
	
	/**好评数量*/
	private java.lang.Integer goodCount;
	
	/**差评数量*/
	private java.lang.Integer badCount;
	
	/**状态*/
	private java.lang.String statement;//0:停售，1：销售
	
	private Integer isHot;//是否促销 1是
	
	private Integer isNew;//是否新品推荐 1是
	
	/**上架时间*/
	private java.util.Date shelveTime;
	
	/**下架时间*/
	private java.util.Date removeTime;
	
	/**快递名称*/
	private java.lang.String expressName;
	
	/**快递费用*/
	private BigDecimal expressPrice;
	
	/**卖家ID*/
	private java.lang.String sellerId;
	
	@ExcelEntity(exportName="类型名称")
	private WeixinShopCategoryEntity weixinShopCategoryEntity;
	
	@Excel(exportName="图片文件夹")
	private String folderName;
	
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
	 *@return: java.lang.String  修改人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题信息
	 */
	@Column(name ="TITLE",nullable=true,length=200)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题信息
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题图片
	 */
	@Column(name ="TITLE_IMG",nullable=true,length=100)
	public java.lang.String getTitleImg(){
		return this.titleImg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题图片
	 */
	public void setTitleImg(java.lang.String titleImg){
		this.titleImg = titleImg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品详情
	 */
	@Column(name ="DESCRIPTIONS",nullable=true,length=5000)
	public java.lang.String getDescriptions(){
		return this.descriptions;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品详情
	 */
	public void setDescriptions(java.lang.String descriptions){
		this.descriptions = descriptions;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  商品原价
	 */
	@Column(name ="PRICE",nullable=true,scale=2,length=18)
	public BigDecimal getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  商品原价
	 */
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  商品现价
	 */
	@Column(name ="REAL_PRICE",nullable=true,scale=2,length=18)
	public BigDecimal getRealPrice(){
		return this.realPrice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  商品现价
	 */
	public void setRealPrice(BigDecimal realPrice){
		this.realPrice = realPrice;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  折扣
	 */
	@Column(name ="SALE",nullable=true,scale=2,length=18)
	public BigDecimal getSale(){
		return this.sale;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  折扣
	 */
	public void setSale(BigDecimal sale){
		this.sale = sale;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  销售数量
	 */
	@Column(name ="SELL_COUNT",nullable=true,length=11)
	public java.lang.Integer getSellCount(){
		return this.sellCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  销售数量
	 */
	public void setSellCount(java.lang.Integer sellCount){
		this.sellCount = sellCount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  评价数量
	 */
	@Column(name ="DISCUSS_COUNT",nullable=true,length=11)
	public java.lang.Integer getDiscussCount(){
		return this.discussCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  评价数量
	 */
	public void setDiscussCount(java.lang.Integer discussCount){
		this.discussCount = discussCount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  好评数量
	 */
	@Column(name ="GOOD_COUNT",nullable=true,length=11)
	public java.lang.Integer getGoodCount(){
		return this.goodCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  好评数量
	 */
	public void setGoodCount(java.lang.Integer goodCount){
		this.goodCount = goodCount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  差评数量
	 */
	@Column(name ="BAD_COUNT",nullable=true,length=11)
	public java.lang.Integer getBadCount(){
		return this.badCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  差评数量
	 */
	public void setBadCount(java.lang.Integer badCount){
		this.badCount = badCount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATEMENT",nullable=true,length=32)
	public java.lang.String getStatement(){
		return this.statement;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatement(java.lang.String statement){
		this.statement = statement;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上架时间
	 */
	@Column(name ="SHELVE_TIME",nullable=true,length=32)
	public java.util.Date getShelveTime(){
		return this.shelveTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上架时间
	 */
	public void setShelveTime(java.util.Date shelveTime){
		this.shelveTime = shelveTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  下架时间
	 */
	@Column(name ="REMOVE_TIME",nullable=true,length=32)
	public java.util.Date getRemoveTime(){
		return this.removeTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下架时间
	 */
	public void setRemoveTime(java.util.Date removeTime){
		this.removeTime = removeTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  快递名称
	 */
	@Column(name ="EXPRESS_NAME",nullable=true,length=32)
	public java.lang.String getExpressName(){
		return this.expressName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  快递名称
	 */
	public void setExpressName(java.lang.String expressName){
		this.expressName = expressName;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  快递费用
	 */
	@Column(name ="EXPRESS_PRICE",nullable=true,scale=2,length=18)
	public BigDecimal getExpressPrice(){
		return this.expressPrice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  快递费用
	 */
	public void setExpressPrice(BigDecimal expressPrice){
		this.expressPrice = expressPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卖家ID
	 */
	@Column(name ="SELLER_ID",nullable=true,length=36)
	public java.lang.String getSellerId(){
		return this.sellerId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卖家ID
	 */
	public void setSellerId(java.lang.String sellerId){
		this.sellerId = sellerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CATEGORY_ID")
	public WeixinShopCategoryEntity getWeixinShopCategoryEntity() {
		return weixinShopCategoryEntity;
	}

	public void setWeixinShopCategoryEntity(
			WeixinShopCategoryEntity weixinShopCategoryEntity) {
		this.weixinShopCategoryEntity = weixinShopCategoryEntity;
	}

	@Column(name ="IS_HOT",length=1)
	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	@Column(name ="IS_NEW",length=1)
	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	
	
	public java.lang.String convertGetStatement(){
		return this.statement.equals("0")?"停售":"销售";
	}


	public void convertSetStatement(java.lang.String statement){
		this.statement = statement.equals("停售")?"0":"1";
	}

	@Column(name ="FOLDER_NAME",nullable=true,length=30)
	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

    @Override
    public String toString() {
        return "WeixinShopGoodsEntity{" +
                "id='" + id + '\'' +
                ", createName='" + createName + '\'' +
                ", createDate=" + createDate +
                ", updateName='" + updateName + '\'' +
                ", updateDate=" + updateDate +
                ", title='" + title + '\'' +
                ", titleImg='" + titleImg + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", price=" + price +
                ", realPrice=" + realPrice +
                ", sale=" + sale +
                ", sellCount=" + sellCount +
                ", discussCount=" + discussCount +
                ", goodCount=" + goodCount +
                ", badCount=" + badCount +
                ", statement='" + statement + '\'' +
                ", isHot=" + isHot +
                ", isNew=" + isNew +
                ", shelveTime=" + shelveTime +
                ", removeTime=" + removeTime +
                ", expressName='" + expressName + '\'' +
                ", expressPrice=" + expressPrice +
                ", sellerId='" + sellerId + '\'' +
                ", weixinShopCategoryEntity=" + weixinShopCategoryEntity +
                ", folderName='" + folderName + '\'' +
                '}';
    }
}
