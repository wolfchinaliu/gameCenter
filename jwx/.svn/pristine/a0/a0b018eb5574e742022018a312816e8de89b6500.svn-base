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

/**   
 * @Title: Entity
 * @Description: 餐饮订单
 * @author onlineGenerator
 * @date 2015-05-22 13:47:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_food_order", schema = "")
@SuppressWarnings("serial")
public class WeixinFoodOrderEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**姓名*/
	@Excel(exportName="姓名")
	private java.lang.String userName;
	/**性别*/
	@Excel(exportName="性别")
	private java.lang.String sex;
	/**手机号码*/
	@Excel(exportName="手机号码")
	private java.lang.String mobilphone;
	/**就餐时间*/
	@Excel(exportName="就餐时间")
	private java.util.Date preDate;
	/**座位类别*/
	@Excel(exportName="座位类别")
	private java.lang.String type;
	/**预订人数*/
	@Excel(exportName="预订人数")
	private java.lang.Integer number;
	/**订单状态*/
	@Excel(exportName="订单状态")
	private java.lang.String status;
	/**支付状态*/
	@Excel(exportName="支付状态")
	private java.lang.String ispay;
	/**公众号*/
	@Excel(exportName="公众号")
	private java.lang.String accountid;
	/**备注*/
	@Excel(exportName="备注")
	private java.lang.String remark;
	
	private WeixinLocationEntity weixinLocationEntity; 
	
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
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="USER_NAME",nullable=true,length=30)
	public java.lang.String getUserName(){
		return this.userName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="SEX",nullable=true,length=1)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号码
	 */
	@Column(name ="MOBILPHONE",nullable=true,length=20)
	public java.lang.String getMobilphone(){
		return this.mobilphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号码
	 */
	public void setMobilphone(java.lang.String mobilphone){
		this.mobilphone = mobilphone;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  就餐时间
	 */
	@Column(name ="PRE_DATE",nullable=true,length=20)
	public java.util.Date getPreDate(){
		return this.preDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  就餐时间
	 */
	public void setPreDate(java.util.Date preDate){
		this.preDate = preDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  座位类别
	 */
	@Column(name ="TYPE",nullable=true,length=1)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  座位类别
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预订人数
	 */
	@Column(name ="NUMBER",nullable=true,length=8)
	public java.lang.Integer getNumber(){
		return this.number;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预订人数
	 */
	public void setNumber(java.lang.Integer number){
		this.number = number;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单状态
	 */
	@Column(name ="STATUS",nullable=true,length=1)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付状态
	 */
	@Column(name ="ISPAY",nullable=true,length=1)
	public java.lang.String getIspay(){
		return this.ispay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付状态
	 */
	public void setIspay(java.lang.String ispay){
		this.ispay = ispay;
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
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=200)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="locationid")
	public WeixinLocationEntity getWeixinLocationEntity() {
		return weixinLocationEntity;
	}

	public void setWeixinLocationEntity(WeixinLocationEntity weixinLocationEntity) {
		this.weixinLocationEntity = weixinLocationEntity;
	}
	
	
}
