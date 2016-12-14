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
 * @Description: 销售管理
 * @author onlineGenerator
 * @date 2015-05-25 17:25:46
 * @version V1.0   
 *
 */
@Entity
@Table(name = "car_sales", schema = "")
@SuppressWarnings("serial")
public class CarSalesEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**销售名称*/
	private java.lang.String name;
	/**头像*/
	private java.lang.String picture;
	/**显示顺序*/
	@Excel(exportName="显示顺序")
	private java.lang.Integer sorts;
	/**销售类型*/
	@Excel(exportName="销售类型")
	private java.lang.String salesType;
	/**销售介绍*/
	@Excel(exportName="销售介绍")
	private java.lang.String salesIntroduction;
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
	 *@return: java.lang.String  销售名称
	 */
	@Column(name ="NAME",nullable=false,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像
	 */
	@Column(name ="PICTURE",nullable=true,length=200)
	public java.lang.String getPicture(){
		return this.picture;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  头像
	 */
	public void setPicture(java.lang.String picture){
		this.picture = picture;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  显示顺序
	 */
	@Column(name ="SORTS",nullable=false,length=32)
	public java.lang.Integer getSorts(){
		return this.sorts;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  显示顺序
	 */
	public void setSorts(java.lang.Integer sorts){
		this.sorts = sorts;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售类型
	 */
	@Column(name ="SALES_TYPE",nullable=true,length=32)
	public java.lang.String getSalesType(){
		return this.salesType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售类型
	 */
	public void setSalesType(java.lang.String salesType){
		this.salesType = salesType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售介绍
	 */
	@Column(name ="SALES_INTRODUCTION",nullable=true,length=200)
	public java.lang.String getSalesIntroduction(){
		return this.salesIntroduction;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售介绍
	 */
	public void setSalesIntroduction(java.lang.String salesIntroduction){
		this.salesIntroduction = salesIntroduction;
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
