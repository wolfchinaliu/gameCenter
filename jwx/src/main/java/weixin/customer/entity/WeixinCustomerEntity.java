package weixin.customer.entity;

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
 * @Description: 客服表
 * @author onlineGenerator
 * @date 2015-08-25 11:03:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_customer", schema = "")
@SuppressWarnings("serial")
public class WeixinCustomerEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**优先顺序*/
	@Excel(exportName="优先顺序")
	private java.lang.Integer sorts;
	/**是否接收消息*/
	@Excel(exportName="是否接收消息")
	private java.lang.String receiveMessages;
	/**微信主表*/
	private java.lang.String accountid;
	/**微信唯一标识*/
	@Excel(exportName="微信唯一标识")
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  优先顺序
	 */
	@Column(name ="SORTS",nullable=true,length=32)
	public java.lang.Integer getSorts(){
		return this.sorts;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  优先顺序
	 */
	public void setSorts(java.lang.Integer sorts){
		this.sorts = sorts;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否接收消息
	 */
	@Column(name ="RECEIVE_MESSAGES",nullable=true,length=32)
	public java.lang.String getReceiveMessages(){
		return this.receiveMessages;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否接收消息
	 */
	public void setReceiveMessages(java.lang.String receiveMessages){
		this.receiveMessages = receiveMessages;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信主表
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=20)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信主表
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信唯一标识
	 */
	@Column(name ="OPEN_ID",nullable=true,length=32)
	public java.lang.String getOpenId(){
		return this.openId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信唯一标识
	 */
	public void setOpenId(java.lang.String openId){
		this.openId = openId;
	}
}
