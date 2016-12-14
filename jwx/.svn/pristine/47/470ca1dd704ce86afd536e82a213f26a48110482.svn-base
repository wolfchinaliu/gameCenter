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
 * @Description: 品牌管理
 * @author onlineGenerator
 * @date 2015-05-24 15:36:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "car_brand", schema = "")
@SuppressWarnings("serial")
public class CarBrandEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**品牌名称*/
	private java.lang.String brandName;
	/**logo标识*/
	private java.lang.String logo;
	/**显示顺序*/
	@Excel(exportName="显示顺序")
	private java.lang.Integer sorts;
	/**品牌介绍*/
	@Excel(exportName="品牌介绍")
	private java.lang.String brandIntroduction;
	/**微信公众表主键*/
	@Excel(exportName="微信公众表主键")
	private java.lang.String accountid;
	/**品牌官网地址*/
	@Excel(exportName="品牌官网地址")
	private java.lang.String url;
	
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
	 *@return: java.lang.String  品牌名称
	 */
	@Column(name ="BRAND_NAME",nullable=false,length=50)
	public java.lang.String getBrandName(){
		return this.brandName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌名称
	 */
	public void setBrandName(java.lang.String brandName){
		this.brandName = brandName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  logo标识
	 */
	@Column(name ="LOGO",nullable=true,length=20)
	public java.lang.String getLogo(){
		return this.logo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  logo标识
	 */
	public void setLogo(java.lang.String logo){
		this.logo = logo;
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
	 *@return: java.lang.String  品牌介绍
	 */
	@Column(name ="BRAND_INTRODUCTION",nullable=true,length=2000)
	public java.lang.String getBrandIntroduction(){
		return this.brandIntroduction;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌介绍
	 */
	public void setBrandIntroduction(java.lang.String brandIntroduction){
		this.brandIntroduction = brandIntroduction;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信公众表主键
	 */
	@Column(name ="ACCOUNTID",nullable=false,length=32)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信公众表主键
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品牌官网地址
	 */
	@Column(name ="URL",nullable=true,length=32)
	public java.lang.String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌官网地址
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
	}
}
