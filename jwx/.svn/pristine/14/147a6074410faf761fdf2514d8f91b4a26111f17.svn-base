package weixin.tenant.entity;

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
 * @Description: 套餐类型
 * @author onlineGenerator
 * @date 2015-03-05 12:59:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_product", schema = "")
@SuppressWarnings("serial")
public class WeixinProductEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String productName;
	/**价格*/
	@Excel(exportName="价格")
	private java.lang.Integer price;
	
	private Integer groupSMSNum;//群发次数
	
	private Integer newsTemplateNum;//图文素材上限
	
	private Integer textTemplateNum;//文本素材上限
	
	private Integer requestNum;//请求上限
	
	/**描述*/
	@Excel(exportName="描述")
	private java.lang.String remark;
	
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
	@Column(name ="PRODUCT_NAME",nullable=true,length=50)
	public java.lang.String getProductName(){
		return this.productName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setProductName(java.lang.String productName){
		this.productName = productName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  价格
	 */
	@Column(name ="PRICE",nullable=true,length=8)
	public java.lang.Integer getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  价格
	 */
	public void setPrice(java.lang.Integer price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="REMARK",nullable=true,length=200)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	
	@Column(name ="REQUEST_NUM",nullable=true,length=8)
	public Integer getRequestNum() {
		return requestNum;
	}

	public void setRequestNum(Integer requestNum) {
		this.requestNum = requestNum;
	}
	
	@Column(name ="TEXT_TEMPLATE_NUM",nullable=true,length=8)
	public Integer getTextTemplateNum() {
		return textTemplateNum;
	}

	public void setTextTemplateNum(Integer textTemplateNum) {
		this.textTemplateNum = textTemplateNum;
	}
	
	@Column(name ="NEWS_TEMPLATE_NUM",nullable=true,length=8)
	public Integer getNewsTemplateNum() {
		return newsTemplateNum;
	}

	public void setNewsTemplateNum(Integer newsTemplateNum) {
		this.newsTemplateNum = newsTemplateNum;
	}
	
	@Column(name ="GROUP_SMS_NUM",nullable=true,length=8)
	public Integer getGroupSMSNum() {
		return groupSMSNum;
	}

	public void setGroupSMSNum(Integer groupSMSNum) {
		this.groupSMSNum = groupSMSNum;
	}
}
