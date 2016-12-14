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
 * @Description: 客服消息表
 * @author onlineGenerator
 * @date 2015-09-01 17:58:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_customer_msg", schema = "")
@SuppressWarnings("serial")
public class WeixinCustomerMsgEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**微信主表*/
	@Excel(exportName="微信主表")
	private java.lang.String accountid;
	/**发送ID*/
	@Excel(exportName="发送ID")
	private java.lang.String sendOpenId;
	/**接收ID*/
	@Excel(exportName="接收ID")
	private java.lang.String receiveOpenId;
	/**消息内容*/
	@Excel(exportName="消息内容")
	private java.lang.String content;
	
	private String sendNickName;
	private String receiveNickName;
	
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
	 *@return: java.lang.String  微信主表
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=36)
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
	 *@return: java.lang.String  发送ID
	 */
	@Column(name ="SEND_OPEN_ID",nullable=true,length=32)
	public java.lang.String getSendOpenId(){
		return this.sendOpenId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发送ID
	 */
	public void setSendOpenId(java.lang.String sendOpenId){
		this.sendOpenId = sendOpenId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  接收ID
	 */
	@Column(name ="RECEIVE_OPEN_ID",nullable=true,length=32)
	public java.lang.String getReceiveOpenId(){
		return this.receiveOpenId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  接收ID
	 */
	public void setReceiveOpenId(java.lang.String receiveOpenId){
		this.receiveOpenId = receiveOpenId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  消息内容
	 */
	@Column(name ="CONTENT",nullable=true,length=200)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  消息内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	@Column(name ="SEND_NICK_NAME",nullable=true,length=50)
	public String getSendNickName() {
		return sendNickName;
	}

	public void setSendNickName(String sendNickName) {
		this.sendNickName = sendNickName;
	}
	@Column(name ="RECEIVE_NICK_NAME",nullable=true,length=50)
	public String getReceiveNickName() {
		return receiveNickName;
	}

	public void setReceiveNickName(String receiveNickName) {
		this.receiveNickName = receiveNickName;
	}
	
}
