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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 优惠券
 * @author onlineGenerator
 * @date 2015-06-02 16:29:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_card", schema = "")
@SuppressWarnings("serial")
public class WeixinCardEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**公众号*/
	@Excel(exportName="公众号")
	private java.lang.String accountid;
	
	@Excel(exportName="优惠券类型")
	private String cardType;
	
	/**卡券的商户logo*/
	@Excel(exportName="卡券的商户logo")
	private java.lang.String logoUrl;
	/**码展示类型*/
	@Excel(exportName="码展示类型")
	private java.lang.String codeType;
	/**商户名字*/
	@Excel(exportName="商户名字")
	private java.lang.String brandName;
	/**券名*/
	@Excel(exportName="券名")
	private java.lang.String title;
	/**券名的副标题*/
	@Excel(exportName="券名的副标题")
	private java.lang.String subTitle;
	/**券颜色*/
	@Excel(exportName="券颜色")
	private java.lang.String color;
	/**使用提醒*/
	@Excel(exportName="使用提醒")
	private java.lang.String notice;
	/**使用说明*/
	@Excel(exportName="使用说明")
	private java.lang.String description;
	/**门店ID*/
	@Excel(exportName="门店ID")
	private java.lang.String locationIdList;
	
	private String businessName;
	
	
	/**启用时间*/
	@Excel(exportName="启用时间")
	private java.util.Date beginTimestamp;
	/**到期时间*/
	@Excel(exportName="到期时间")
	private java.util.Date endTimestamp;
	/**客服电话*/
	@Excel(exportName="客服电话")
	private java.lang.String servicePhone;
	/**卡券数量*/
	@Excel(exportName="卡券数量")
	private java.lang.Integer quantity;
	
	@Excel(exportName="卡券当前数量")
	private java.lang.Integer nowquantity;
	
	/**面值*/
	@Excel(exportName="面值")
	private java.math.BigDecimal cost;
	
	private Integer getLimit;//每人可领券的数量限制。不填默认与quantity等值
	
	private String cardId;//卡券ID
	
	private String dealDetail;//团购券专用，团购详情
	
	private Integer least_cost;//代金券专用，表示起用金额。（单位为分）
	
	private Integer reduce_cost;//代金券专用，表示减免金额。（单位为分）
	
	private Integer discount;//折扣券专用，表示打折额度（百分比）。填30就是七折。
	
	private String gift;//礼品券专用，填写礼品的名称。
	
	private String default_detail;//优惠券专用，填写优惠详情
	
	private String status;//0:未审核，1:已审核，4：已过期
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
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
	@Column(name ="CREATE_NAME",nullable=true,length=30)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卡券的商户logo
	 */
	@Column(name ="LOGO_URL",nullable=true,length=100)
	public java.lang.String getLogoUrl(){
		return this.logoUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卡券的商户logo
	 */
	public void setLogoUrl(java.lang.String logoUrl){
		this.logoUrl = logoUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  码展示类型
	 */
	@Column(name ="CODE_TYPE",nullable=true,length=20)
	public java.lang.String getCodeType(){
		return this.codeType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  码展示类型
	 */
	public void setCodeType(java.lang.String codeType){
		this.codeType = codeType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户名字
	 */
	@Column(name ="BRAND_NAME",nullable=true,length=30)
	public java.lang.String getBrandName(){
		return this.brandName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户名字
	 */
	public void setBrandName(java.lang.String brandName){
		this.brandName = brandName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  券名
	 */
	@Column(name ="TITLE",nullable=true,length=30)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  券名
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  券名的副标题
	 */
	@Column(name ="SUB_TITLE",nullable=true,length=50)
	public java.lang.String getSubTitle(){
		return this.subTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  券名的副标题
	 */
	public void setSubTitle(java.lang.String subTitle){
		this.subTitle = subTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  券颜色
	 */
	@Column(name ="COLOR",nullable=true,length=20)
	public java.lang.String getColor(){
		return this.color;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  券颜色
	 */
	public void setColor(java.lang.String color){
		this.color = color;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用提醒
	 */
	@Column(name ="NOTICE",nullable=true,length=30)
	public java.lang.String getNotice(){
		return this.notice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用提醒
	 */
	public void setNotice(java.lang.String notice){
		this.notice = notice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用说明
	 */
	@Column(name ="DESCRIPTION",nullable=true,length=200)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用说明
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  门店位置ID
	 */
	@Column(name ="LOCATION_ID_LIST",nullable=true,length=200)
	public java.lang.String getLocationIdList(){
		return this.locationIdList;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  门店位置ID
	 */
	public void setLocationIdList(java.lang.String locationIdList){
		this.locationIdList = locationIdList;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  启用时间
	 */
	@Column(name ="BEGIN_TIMESTAMP",nullable=true,length=20)
	public java.util.Date getBeginTimestamp(){
		return this.beginTimestamp;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  启用时间
	 */
	public void setBeginTimestamp(java.util.Date beginTimestamp){
		this.beginTimestamp = beginTimestamp;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到期时间
	 */
	@Column(name ="END_TIMESTAMP",nullable=true,length=20)
	public java.util.Date getEndTimestamp(){
		return this.endTimestamp;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到期时间
	 */
	public void setEndTimestamp(java.util.Date endTimestamp){
		this.endTimestamp = endTimestamp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客服电话
	 */
	@Column(name ="SERVICE_PHONE",nullable=true,length=20)
	public java.lang.String getServicePhone(){
		return this.servicePhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客服电话
	 */
	public void setServicePhone(java.lang.String servicePhone){
		this.servicePhone = servicePhone;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  卡券数量
	 */
	@Column(name ="QUANTITY",nullable=true,length=8)
	public java.lang.Integer getQuantity(){
		return this.quantity;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  卡券数量
	 */
	public void setQuantity(java.lang.Integer quantity){
		this.quantity = quantity;
	}
	
	
	
	
	@Column(name ="NOWQUANTITY",nullable=true,length=8)
	public java.lang.Integer getNowquantity() {
		return nowquantity;
	}

	public void setNowquantity(java.lang.Integer nowquantity) {
		this.nowquantity = nowquantity;
	}

	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  面值
	 */
	@Column(name ="COST",nullable=true,length=12)
	public java.math.BigDecimal getCost(){
		return this.cost;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  面值
	 */
	public void setCost(java.math.BigDecimal cost){
		this.cost = cost;
	}

	@Column(name ="CARD_TYPE",nullable=true,length=20)
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Column(name ="DEAL_DETAIL",nullable=true,length=1000)
	public String getDealDetail() {
		return dealDetail;
	}

	public void setDealDetail(String dealDetail) {
		this.dealDetail = dealDetail;
	}

	@Column(name ="LEAST_COST",nullable=true,length=12)
	public Integer getLeast_cost() {
		return least_cost;
	}

	public void setLeast_cost(Integer least_cost) {
		this.least_cost = least_cost;
	}

	@Column(name ="REDUCE_COST",nullable=true,length=12)
	public Integer getReduce_cost() {
		return reduce_cost;
	}

	public void setReduce_cost(Integer reduce_cost) {
		this.reduce_cost = reduce_cost;
	}

	@Column(name ="DISCOUNT",nullable=true,length=12)
	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	@Column(name ="GIFT",nullable=true,length=1000)
	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	@Column(name ="DEFAULT_DETAIL",nullable=true,length=1000)
	public String getDefault_detail() {
		return default_detail;
	}

	public void setDefault_detail(String default_detail) {
		this.default_detail = default_detail;
	}

	@Column(name ="CARD_ID",nullable=true,length=100)
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Column(name ="GET_LIMIT",nullable=true,length=12)
	public Integer getGetLimit() {
		return getLimit;
	}

	public void setGetLimit(Integer getLimit) {
		this.getLimit = getLimit;
	}

	@Column(name ="STATUS",nullable=true,length=2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name ="BUSINESS_NAME",nullable=true,length=100)
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}


	
}
