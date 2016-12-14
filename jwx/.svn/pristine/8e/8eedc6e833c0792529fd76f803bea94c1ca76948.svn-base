package weixin.weicar.entity;

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
 * @Description: 汽车首页
 * @author onlineGenerator
 * @date 2015-05-26 20:40:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "car_index_manager", schema = "")
@SuppressWarnings("serial")
public class CarIndexManagerEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**触发关键词*/
	private java.lang.String keyword;
	/**图文标题*/
	private java.lang.String title;
	/**标题图片*/
	@Excel(exportName="标题图片")
	private java.lang.String headPic;
	/**图文外链*/
	@Excel(exportName="图文外链")
	private java.lang.String url;
	/**模版*/
	@Excel(exportName="模版")
	private java.lang.String selectTemplate;
	/**经销车型*/
	@Excel(exportName="经销车型")
	private java.lang.String carSeriesTitle;
	/**经销车型图片*/
	@Excel(exportName="经销车型图片")
	private java.lang.String carSeriesPic;
	/**经销车外链*/
	@Excel(exportName="经销车外链")
	private java.lang.String carSeriesUrl;
	/**销售顾问*/
	@Excel(exportName="销售顾问")
	private java.lang.String carSalesTitle;
	/**销售图片*/
	@Excel(exportName="销售图片")
	private java.lang.String carSalesPic;
	/**外链*/
	@Excel(exportName="外链")
	private java.lang.String carSalesUrl;
	/**预约*/
	@Excel(exportName="预约")
	private java.lang.String carOrderTitle;
	/**预约图片*/
	@Excel(exportName="预约图片")
	private java.lang.String carOrderPic;
	/**预约外链*/
	@Excel(exportName="预约外链")
	private java.lang.String carOrderUrl;
	/**车主关怀*/
	@Excel(exportName="车主关怀")
	private java.lang.String carCareTitle;
	/**车主关怀图片*/
	@Excel(exportName="车主关怀图片")
	private java.lang.String carCarePic;
	/**车主关怀外链*/
	@Excel(exportName="车主关怀外链")
	private java.lang.String carCareUrl;
	/**实用工具*/
	@Excel(exportName="实用工具")
	private java.lang.String carToolsTitle;
	/**实用工具图片*/
	@Excel(exportName="实用工具图片")
	private java.lang.String carToolsPic;
	/**外链*/
	@Excel(exportName="外链")
	private java.lang.String carToolsUrl;
	/**车型标题*/
	@Excel(exportName="车型标题")
	private java.lang.String carTypeTitle;
	/**车型图片*/
	@Excel(exportName="车型图片")
	private java.lang.String carTypePic;
	/**车型外链*/
	@Excel(exportName="车型外链")
	private java.lang.String carTypeUrl;
	/**微信主表主键*/
	@Excel(exportName="微信主表主键")
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
	 *@return: java.lang.String  触发关键词
	 */
	@Column(name ="KEYWORD",nullable=true,length=50)
	public java.lang.String getKeyword(){
		return this.keyword;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  触发关键词
	 */
	public void setKeyword(java.lang.String keyword){
		this.keyword = keyword;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图文标题
	 */
	@Column(name ="TITLE",nullable=true,length=20)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图文标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题图片
	 */
	@Column(name ="HEAD_PIC",nullable=true,length=100)
	public java.lang.String getHeadPic(){
		return this.headPic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题图片
	 */
	public void setHeadPic(java.lang.String headPic){
		this.headPic = headPic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图文外链
	 */
	@Column(name ="URL",nullable=true,length=100)
	public java.lang.String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图文外链
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  模版
	 */
	@Column(name ="SELECT_TEMPLATE",nullable=true,length=32)
	public java.lang.String getSelectTemplate(){
		return this.selectTemplate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模版
	 */
	public void setSelectTemplate(java.lang.String selectTemplate){
		this.selectTemplate = selectTemplate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经销车型
	 */
	@Column(name ="CAR_SERIES_TITLE",nullable=true,length=32)
	public java.lang.String getCarSeriesTitle(){
		return this.carSeriesTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经销车型
	 */
	public void setCarSeriesTitle(java.lang.String carSeriesTitle){
		this.carSeriesTitle = carSeriesTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经销车型图片
	 */
	@Column(name ="CAR_SERIES_PIC",nullable=true,length=100)
	public java.lang.String getCarSeriesPic(){
		return this.carSeriesPic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经销车型图片
	 */
	public void setCarSeriesPic(java.lang.String carSeriesPic){
		this.carSeriesPic = carSeriesPic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经销车外链
	 */
	@Column(name ="CAR_SERIES_URL",nullable=true,length=100)
	public java.lang.String getCarSeriesUrl(){
		return this.carSeriesUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经销车外链
	 */
	public void setCarSeriesUrl(java.lang.String carSeriesUrl){
		this.carSeriesUrl = carSeriesUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售顾问
	 */
	@Column(name ="CAR_SALES_TITLE",nullable=true,length=32)
	public java.lang.String getCarSalesTitle(){
		return this.carSalesTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售顾问
	 */
	public void setCarSalesTitle(java.lang.String carSalesTitle){
		this.carSalesTitle = carSalesTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售图片
	 */
	@Column(name ="CAR_SALES_PIC",nullable=true,length=100)
	public java.lang.String getCarSalesPic(){
		return this.carSalesPic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售图片
	 */
	public void setCarSalesPic(java.lang.String carSalesPic){
		this.carSalesPic = carSalesPic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外链
	 */
	@Column(name ="CAR_SALES_URL",nullable=true,length=100)
	public java.lang.String getCarSalesUrl(){
		return this.carSalesUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外链
	 */
	public void setCarSalesUrl(java.lang.String carSalesUrl){
		this.carSalesUrl = carSalesUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约
	 */
	@Column(name ="CAR_ORDER_TITLE",nullable=true,length=32)
	public java.lang.String getCarOrderTitle(){
		return this.carOrderTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约
	 */
	public void setCarOrderTitle(java.lang.String carOrderTitle){
		this.carOrderTitle = carOrderTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约图片
	 */
	@Column(name ="CAR_ORDER_PIC",nullable=true,length=100)
	public java.lang.String getCarOrderPic(){
		return this.carOrderPic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约图片
	 */
	public void setCarOrderPic(java.lang.String carOrderPic){
		this.carOrderPic = carOrderPic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约外链
	 */
	@Column(name ="CAR_ORDER_URL",nullable=true,length=100)
	public java.lang.String getCarOrderUrl(){
		return this.carOrderUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约外链
	 */
	public void setCarOrderUrl(java.lang.String carOrderUrl){
		this.carOrderUrl = carOrderUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车主关怀
	 */
	@Column(name ="CAR_CARE_TITLE",nullable=true,length=32)
	public java.lang.String getCarCareTitle(){
		return this.carCareTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车主关怀
	 */
	public void setCarCareTitle(java.lang.String carCareTitle){
		this.carCareTitle = carCareTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车主关怀图片
	 */
	@Column(name ="CAR_CARE_PIC",nullable=true,length=100)
	public java.lang.String getCarCarePic(){
		return this.carCarePic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车主关怀图片
	 */
	public void setCarCarePic(java.lang.String carCarePic){
		this.carCarePic = carCarePic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车主关怀外链
	 */
	@Column(name ="CAR_CARE_URL",nullable=true,length=100)
	public java.lang.String getCarCareUrl(){
		return this.carCareUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车主关怀外链
	 */
	public void setCarCareUrl(java.lang.String carCareUrl){
		this.carCareUrl = carCareUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实用工具
	 */
	@Column(name ="CAR_TOOLS_TITLE",nullable=true,length=32)
	public java.lang.String getCarToolsTitle(){
		return this.carToolsTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实用工具
	 */
	public void setCarToolsTitle(java.lang.String carToolsTitle){
		this.carToolsTitle = carToolsTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实用工具图片
	 */
	@Column(name ="CAR_TOOLS_PIC",nullable=true,length=100)
	public java.lang.String getCarToolsPic(){
		return this.carToolsPic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实用工具图片
	 */
	public void setCarToolsPic(java.lang.String carToolsPic){
		this.carToolsPic = carToolsPic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外链
	 */
	@Column(name ="CAR_TOOLS_URL",nullable=true,length=100)
	public java.lang.String getCarToolsUrl(){
		return this.carToolsUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外链
	 */
	public void setCarToolsUrl(java.lang.String carToolsUrl){
		this.carToolsUrl = carToolsUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型标题
	 */
	@Column(name ="CAR_TYPE_TITLE",nullable=true,length=32)
	public java.lang.String getCarTypeTitle(){
		return this.carTypeTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型标题
	 */
	public void setCarTypeTitle(java.lang.String carTypeTitle){
		this.carTypeTitle = carTypeTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型图片
	 */
	@Column(name ="CAR_TYPE_PIC",nullable=true,length=100)
	public java.lang.String getCarTypePic(){
		return this.carTypePic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型图片
	 */
	public void setCarTypePic(java.lang.String carTypePic){
		this.carTypePic = carTypePic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型外链
	 */
	@Column(name ="CAR_TYPE_URL",nullable=true,length=100)
	public java.lang.String getCarTypeUrl(){
		return this.carTypeUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型外链
	 */
	public void setCarTypeUrl(java.lang.String carTypeUrl){
		this.carTypeUrl = carTypeUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信主表主键
	 */
	@Column(name ="ACCOUNTID",nullable=false,length=32)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信主表主键
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
}
