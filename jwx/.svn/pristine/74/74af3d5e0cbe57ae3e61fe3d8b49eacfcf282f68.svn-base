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
 * @Description: 会员卡
 * @author onlineGenerator
 * @date 2015-06-30 10:50:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_member_card", schema = "")
@SuppressWarnings("serial")
public class WeixinMemberCardEntity implements java.io.Serializable {
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
	/**会员卡类型*/
	@Excel(exportName="会员卡类型")
	private java.lang.String cardType;
	/**特权说明*/
	@Excel(exportName="特权说明")
	private java.lang.String prerogative;
	/**显示积分*/
	@Excel(exportName="显示积分")
	private java.lang.String supplyBonus;//显示积分，填写true或false，如填写true，积分相关字段均为必填
	
	@Excel(exportName="是否支持储值")
	private String supplyBalance;//是否支持储值，填写true或false。如填写true，储值相关字段均为必填。
	
	/**商户logo*/
	@Excel(exportName="商户logo")
	private java.lang.String logoUrl;
	/**展示类型*/
	@Excel(exportName="展示类型")
	private java.lang.String codeType;
	/**商户名字*/
	@Excel(exportName="商户名字")
	private java.lang.String brandName;
	/**卡券名*/
	@Excel(exportName="卡券名")
	private java.lang.String title;
	/**券名*/
	@Excel(exportName="券名")
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
	/**库存数量*/
	@Excel(exportName="库存数量")
	private java.lang.Integer quantity;
	/**到期时间*/
	@Excel(exportName="到期时间")
	private java.util.Date endTimestamp;
	/**启用时间*/
	@Excel(exportName="启用时间")
	private java.util.Date beginTimestamp;
	/**门店位置ID*/
	@Excel(exportName="门店位置ID")
	private java.lang.String locationIdList;
	/**每人数量限制*/
	@Excel(exportName="每人数量限制")
	private java.lang.Integer getLimit;
	/**是否可转赠*/
	@Excel(exportName="是否可转赠")
	private java.lang.String canGiveFriend;
	/**客服电话*/
	@Excel(exportName="客服电话")
	private java.lang.String servicePhone;
	/**所属公众号*/
	@Excel(exportName="所属公众号")
	private java.lang.String accountid;
	
	private String cardId;//卡券ID
	
	private String status;//0:未审核，1:已审核
	
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
	 *@return: java.lang.String  修改人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=30)
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
	 *@return: java.lang.String  会员卡类型
	 */
	@Column(name ="CARD_TYPE",nullable=true,length=36)
	public java.lang.String getCardType(){
		return this.cardType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会员卡类型
	 */
	public void setCardType(java.lang.String cardType){
		this.cardType = cardType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  特权说明
	 */
	@Column(name ="PREROGATIVE",nullable=true,length=200)
	public java.lang.String getPrerogative(){
		return this.prerogative;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  特权说明
	 */
	public void setPrerogative(java.lang.String prerogative){
		this.prerogative = prerogative;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  显示积分
	 */
	@Column(name ="SUPPLY_BONUS",nullable=true,length=20)
	public java.lang.String getSupplyBonus(){
		return this.supplyBonus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  显示积分
	 */
	public void setSupplyBonus(java.lang.String supplyBonus){
		this.supplyBonus = supplyBonus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户logo
	 */
	@Column(name ="LOGO_URL",nullable=true,length=200)
	public java.lang.String getLogoUrl(){
		return this.logoUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户logo
	 */
	public void setLogoUrl(java.lang.String logoUrl){
		this.logoUrl = logoUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  展示类型
	 */
	@Column(name ="CODE_TYPE",nullable=true,length=30)
	public java.lang.String getCodeType(){
		return this.codeType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  展示类型
	 */
	public void setCodeType(java.lang.String codeType){
		this.codeType = codeType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户名字
	 */
	@Column(name ="BRAND_NAME",nullable=true,length=20)
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
	 *@return: java.lang.String  卡券名
	 */
	@Column(name ="TITLE",nullable=true,length=20)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卡券名
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  券名
	 */
	@Column(name ="SUB_TITLE",nullable=true,length=20)
	public java.lang.String getSubTitle(){
		return this.subTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  券名
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
	@Column(name ="NOTICE",nullable=true,length=50)
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
	@Column(name ="DESCRIPTION",nullable=true,length=1000)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  库存数量
	 */
	@Column(name ="QUANTITY",nullable=true,length=8)
	public java.lang.Integer getQuantity(){
		return this.quantity;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  库存数量
	 */
	public void setQuantity(java.lang.Integer quantity){
		this.quantity = quantity;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  起用时间
	 */
	@Column(name ="END_TIMESTAMP",nullable=true,length=20)
	public java.util.Date getEndTimestamp(){
		return this.endTimestamp;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  起用时间
	 */
	public void setEndTimestamp(java.util.Date endTimestamp){
		this.endTimestamp = endTimestamp;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="BEGIN_TIMESTAMP",nullable=true,length=20)
	public java.util.Date getBeginTimestamp(){
		return this.beginTimestamp;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setBeginTimestamp(java.util.Date beginTimestamp){
		this.beginTimestamp = beginTimestamp;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  每人数量限制
	 */
	@Column(name ="GET_LIMIT",nullable=true,length=8)
	public java.lang.Integer getGetLimit(){
		return this.getLimit;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  每人数量限制
	 */
	public void setGetLimit(java.lang.Integer getLimit){
		this.getLimit = getLimit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否可转赠
	 */
	@Column(name ="CAN_GIVE_FRIEND",nullable=true,length=10)
	public java.lang.String getCanGiveFriend(){
		return this.canGiveFriend;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否可转赠
	 */
	public void setCanGiveFriend(java.lang.String canGiveFriend){
		this.canGiveFriend = canGiveFriend;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公众号
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=32)
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
	
	@Column(name ="CARD_ID",nullable=true,length=100)
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	@Column(name ="STATUS",nullable=true,length=2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name ="SUPPLY_BALANCE",nullable=true,length=10)
	public String getSupplyBalance() {
		return supplyBalance;
	}

	public void setSupplyBalance(String supplyBalance) {
		this.supplyBalance = supplyBalance;
	}
	
	
}
