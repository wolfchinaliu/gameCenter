package weixin.weicar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 预约管理
 * @author onlineGenerator
 * @date 2015-05-25 17:46:35
 * @version V1.0   
 *
 */
@Entity
@Table(name = "car_order_setting", schema = "")
@SuppressWarnings("serial")
public class CarOrderSettingEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**图文消息标题*/
	private java.lang.String title;
	/**触发关键字*/
	private java.lang.String keyword;
	/**预约地址*/
	@Excel(exportName="预约地址")
	private java.lang.String address;
	/**经度*/
	@Excel(exportName="经度")
	private java.lang.Double longitude;
	/**纬度*/
	@Excel(exportName="纬度")
	private java.lang.Double latitude;
	/**预约电话*/
	@Excel(exportName="预约电话")
	private java.lang.String tel;
	/**订单页头部图片*/
	@Excel(exportName="订单页头部图片")
	private java.lang.String headpic;
	/**订单详情*/
	@Excel(exportName="订单详情")
	private java.lang.String orderDesc;
	/**类型10试驾20保养*/
	@Excel(exportName="类型10试驾20保养")
	private java.lang.String orderType;
	/**微信主表ID*/
	@Excel(exportName="微信主表ID")
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
	 *@return: java.lang.String  图文消息标题
	 */
	@Column(name ="TITLE",nullable=true,length=50)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图文消息标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	
	public java.lang.String getKeyword() {
		return keyword;
	}

	public void setKeyword(java.lang.String keyword) {
		this.keyword = keyword;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=100)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  经度
	 */
	@Column(name ="LONGITUDE",nullable=true,length=32)
	public java.lang.Double getLongitude(){
		return this.longitude;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  经度
	 */
	public void setLongitude(java.lang.Double longitude){
		this.longitude = longitude;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  纬度
	 */
	@Column(name ="LATITUDE",nullable=true,length=32)
	public java.lang.Double getLatitude(){
		return this.latitude;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  纬度
	 */
	public void setLatitude(java.lang.Double latitude){
		this.latitude = latitude;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约电话
	 */
	@Column(name ="TEL",nullable=true,length=32)
	public java.lang.String getTel(){
		return this.tel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约电话
	 */
	public void setTel(java.lang.String tel){
		this.tel = tel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单页头部图片
	 */
	@Column(name ="HEADPIC",nullable=true,length=100)
	public java.lang.String getHeadpic(){
		return this.headpic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单页头部图片
	 */
	public void setHeadpic(java.lang.String headpic){
		this.headpic = headpic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单详情
	 */
	@Column(name ="ORDER_DESC",nullable=true,length=2000)
	public java.lang.String getOrderDesc(){
		return this.orderDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单详情
	 */
	public void setOrderDesc(java.lang.String orderDesc){
		this.orderDesc = orderDesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型10试驾20保养
	 */
	@Column(name ="ORDER_TYPE",nullable=true,length=32)
	public java.lang.String getOrderType(){
		return this.orderType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型10试驾20保养
	 */
	public void setOrderType(java.lang.String orderType){
		this.orderType = orderType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信主表ID
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=32)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信主表ID
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
}
