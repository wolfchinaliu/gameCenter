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
 * @Description: 门店信息
 * @author onlineGenerator
 * @date 2015-04-30 16:58:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_location", schema = "")
@SuppressWarnings("serial")
public class WeixinLocationEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**公众号*/
	private java.lang.String accountid;
	/**创建日期*/
	private java.util.Date createDate;
	/**门店名称*/
	@Excel(exportName="门店名称")
	private java.lang.String businessName;
	/**分店名*/
	@Excel(exportName="分店名")
	private java.lang.String branchName;
	/**所在省*/
	@Excel(exportName="所在省")
	private java.lang.String province;
	/**所在市*/
	@Excel(exportName="所在市")
	private java.lang.String city;
	/**所在区*/
	@Excel(exportName="所在区")
	private java.lang.String district;
	/**详细街道地址*/
	@Excel(exportName="详细街道地址")
	private java.lang.String address;
	/**门店的电话*/
	@Excel(exportName="门店的电话")
	private java.lang.String telephone;
	/**门店的类型*/
	@Excel(exportName="门店的类型")
	private java.lang.String category;
	/**经度*/
	@Excel(exportName="经度")
	private java.lang.String longitude;
	/**纬度*/
	@Excel(exportName="纬度")
	private java.lang.String latitude;
	/**新品推荐*/
	@Excel(exportName="新品推荐")
	private java.lang.String recommend;
	/**特色服务*/
	@Excel(exportName="特色服务")
	private java.lang.String special;
	/**简介*/
	@Excel(exportName="简介")
	private java.lang.String introduction;
	/**营业时间*/
	@Excel(exportName="营业时间")
	private java.lang.String openTime;
	/**人均消费*/
	@Excel(exportName="人均消费")
	private int avgPrice;
	/**门店图片*/
	@Excel(exportName="门店图片")
	private java.lang.String titleLogo;
	/**二维码*/
	@Excel(exportName="二维码")
	private java.lang.String qrcodeLogo;
	
	private String msg;
	
	private String status;//0：未审，1:已审, 4:已删
	
	private String poiId;//微信门店ID
	
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
	 *@return: java.lang.String  门店名称
	 */
	@Column(name ="BUSINESS_NAME",nullable=true,length=50)
	public java.lang.String getBusinessName(){
		return this.businessName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  门店名称
	 */
	public void setBusinessName(java.lang.String businessName){
		this.businessName = businessName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分店名
	 */
	@Column(name ="BRANCH_NAME",nullable=true,length=50)
	public java.lang.String getBranchName(){
		return this.branchName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分店名
	 */
	public void setBranchName(java.lang.String branchName){
		this.branchName = branchName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在省
	 */
	@Column(name ="PROVINCE",nullable=true,length=32)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所在省
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在市
	 */
	@Column(name ="CITY",nullable=true,length=32)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所在市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在区
	 */
	@Column(name ="DISTRICT",nullable=true,length=32)
	public java.lang.String getDistrict(){
		return this.district;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所在区
	 */
	public void setDistrict(java.lang.String district){
		this.district = district;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  详细街道地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=32)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  详细街道地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  门店的电话
	 */
	@Column(name ="TELEPHONE",nullable=true,length=32)
	public java.lang.String getTelephone(){
		return this.telephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  门店的电话
	 */
	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  门店的类型
	 */
	@Column(name ="CATEGORY",nullable=true,length=100)
	public java.lang.String getCategory(){
		return this.category;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  门店的类型
	 */
	public void setCategory(java.lang.String category){
		this.category = category;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经度
	 */
	@Column(name ="LONGITUDE",nullable=true,length=32)
	public java.lang.String getLongitude(){
		return this.longitude;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经度
	 */
	public void setLongitude(java.lang.String longitude){
		this.longitude = longitude;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  纬度
	 */
	@Column(name ="LATITUDE",nullable=true,length=32)
	public java.lang.String getLatitude(){
		return this.latitude;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  纬度
	 */
	public void setLatitude(java.lang.String latitude){
		this.latitude = latitude;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  新品推荐
	 */
	@Column(name ="RECOMMEND",nullable=true,length=50)
	public java.lang.String getRecommend(){
		return this.recommend;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  新品推荐
	 */
	public void setRecommend(java.lang.String recommend){
		this.recommend = recommend;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  特色服务
	 */
	@Column(name ="SPECIAL",nullable=true,length=100)
	public java.lang.String getSpecial(){
		return this.special;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  特色服务
	 */
	public void setSpecial(java.lang.String special){
		this.special = special;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  简介
	 */
	@Column(name ="INTRODUCTION",nullable=true,length=500)
	public java.lang.String getIntroduction(){
		return this.introduction;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  简介
	 */
	public void setIntroduction(java.lang.String introduction){
		this.introduction = introduction;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  营业时间
	 */
	@Column(name ="OPEN_TIME",nullable=true,length=32)
	public java.lang.String getOpenTime(){
		return this.openTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  营业时间
	 */
	public void setOpenTime(java.lang.String openTime){
		this.openTime = openTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人均消费
	 */
	@Column(name ="AVG_PRICE",nullable=true,length=8)
	public int getAvgPrice(){
		return this.avgPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人均消费
	 */
	public void setAvgPrice(int avgPrice){
		this.avgPrice = avgPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  门店图片
	 */
	@Column(name ="TITLE_LOGO",nullable=true,length=200)
	public java.lang.String getTitleLogo(){
		return this.titleLogo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  门店图片
	 */
	public void setTitleLogo(java.lang.String titleLogo){
		this.titleLogo = titleLogo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二维码
	 */
	@Column(name ="QRCODE_LOGO",nullable=true,length=100)
	public java.lang.String getQrcodeLogo(){
		return this.qrcodeLogo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二维码
	 */
	public void setQrcodeLogo(java.lang.String qrcodeLogo){
		this.qrcodeLogo = qrcodeLogo;
	}

	@Column(name ="STATUS",nullable=true,length=1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name ="POI_ID",nullable=true,length=36)
	public String getPoiId() {
		return poiId;
	}

	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}

	@Column(name ="MSG",nullable=true,length=100)
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
