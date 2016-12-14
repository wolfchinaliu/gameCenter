package weixin.weicar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 订单
 * @author onlineGenerator
 * @date 2015-05-26 20:08:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "car_order", schema = "")
@SuppressWarnings("serial")
public class CarOrderEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**姓名*/
	private java.lang.String name;
	/**预约详细*/
	private java.lang.String detail;
	/**电话*/
	@Excel(exportName="电话")
	private java.lang.String phone;
	/**预约时间*/
	@Excel(exportName="预约时间")
	private java.lang.String orderTime;
	/**下单日期*/
	@Excel(exportName="下单日期")
	private java.util.Date orderDate;
	/**订单状态*/
	@Excel(exportName="订单状态")
	private java.lang.String orderStatus;
	/**订单备注*/
	@Excel(exportName="订单备注")
	private java.lang.String orderRemark;
	/**订单设置表ID*/
	@Excel(exportName="订单设置表ID")
	//private java.lang.String carOrderSettingId;
	private CarOrderSettingEntity carOrderSettingEntity;
	/**微信id*/
	@Excel(exportName="微信id")
	private java.lang.String openId;
	
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
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="NAME",nullable=true,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约详细
	 */
	@Column(name ="DETAIL",nullable=true,length=500)
	public java.lang.String getDetail(){
		return this.detail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约详细
	 */
	public void setDetail(java.lang.String detail){
		this.detail = detail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */
	@Column(name ="PHONE",nullable=true,length=32)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预约时间
	 */
	@Column(name ="ORDER_TIME",nullable=true,length=40)
	public java.lang.String getOrderTime(){
		return this.orderTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预约时间
	 */
	public void setOrderTime(java.lang.String orderTime){
		this.orderTime = orderTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  下单日期
	 */
	@Column(name ="ORDER_DATE",nullable=true,length=32)
	public java.util.Date getOrderDate(){
		return this.orderDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下单日期
	 */
	public void setOrderDate(java.util.Date orderDate){
		this.orderDate = orderDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单状态
	 */
	@Column(name ="ORDER_STATUS",nullable=true,length=32)
	public java.lang.String getOrderStatus(){
		return this.orderStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单状态
	 */
	public void setOrderStatus(java.lang.String orderStatus){
		this.orderStatus = orderStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单备注
	 */
	@Column(name ="ORDER_REMARK",nullable=true,length=200)
	public java.lang.String getOrderRemark(){
		return this.orderRemark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单备注
	 */
	public void setOrderRemark(java.lang.String orderRemark){
		this.orderRemark = orderRemark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单设置表ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR_ORDER_SETTING_ID", nullable = false)
	public CarOrderSettingEntity getCarOrderSettingEntity() {
		return carOrderSettingEntity;
	}

	public void setCarOrderSettingEntity(CarOrderSettingEntity carOrderSettingEntity) {
		this.carOrderSettingEntity = carOrderSettingEntity;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信id
	 */
	@Column(name ="OPEN_ID",nullable=true,length=32)
	public java.lang.String getOpenId(){
		return this.openId;
	}

	

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信id
	 */
	public void setOpenId(java.lang.String openId){
		this.openId = openId;
	}
}
