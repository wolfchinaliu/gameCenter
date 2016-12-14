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
 * @Description: 门店图片
 * @author onlineGenerator
 * @date 2015-06-12 17:15:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_locationphoto", schema = "")
@SuppressWarnings("serial")
public class WeixinLocationphotoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**图片地址*/
	@Excel(exportName="图片地址")
	private java.lang.String photoUrl;
	/**门店ID*/
	@Excel(exportName="门店ID")
	private java.lang.String locationId;
	
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
	 *@return: java.lang.String  图片地址
	 */
	@Column(name ="PHOTO_URL",nullable=true,length=100)
	public java.lang.String getPhotoUrl(){
		return this.photoUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片地址
	 */
	public void setPhotoUrl(java.lang.String photoUrl){
		this.photoUrl = photoUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  门店ID
	 */
	@Column(name ="LOCATION_ID",nullable=true,length=36)
	public java.lang.String getLocationId(){
		return this.locationId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  门店ID
	 */
	public void setLocationId(java.lang.String locationId){
		this.locationId = locationId;
	}
}
