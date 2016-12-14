package weixin.payment.entity;

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
 * @Description: 支付记录
 * @author onlineGenerator
 * @date 2015-08-18 18:44:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_payment_log", schema = "")
@SuppressWarnings("serial")
public class WeixinPaymentLogEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	@Excel(exportName="创建人名称")
	private java.lang.String createName;
	/**创建日期*/
	@Excel(exportName="创建日期")
	private java.util.Date createDate;
	/**订单号*/
	@Excel(exportName="订单号")
	private java.lang.String orderId;
	/**支付类型*/
	@Excel(exportName="支付类型")
	private java.lang.String payType;
	/**公众号*/
	@Excel(exportName="公众号")
	private java.lang.String accountid;
	/**支付金额*/
	@Excel(exportName="支付金额")
	private BigDecimal amount;
	/**微信订单号*/
	@Excel(exportName="微信订单号")
	private java.lang.String transactionId;
	/**付款银行*/
	@Excel(exportName="付款银行")
	private java.lang.String bankType;
	/**粉丝ID*/
	@Excel(exportName="粉丝ID")
	private java.lang.String openid;
	
	
	private java.lang.String mchId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
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
	@Column(name ="CREATE_DATE",nullable=true)
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
	 *@return: java.lang.String  订单号
	 */
	@Column(name ="ORDER_ID",nullable=true,length=50)
	public java.lang.String getOrderId(){
		return this.orderId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单号
	 */
	public void setOrderId(java.lang.String orderId){
		this.orderId = orderId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付类型
	 */
	@Column(name ="PAY_TYPE",nullable=true,length=2)
	public java.lang.String getPayType(){
		return this.payType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付类型
	 */
	public void setPayType(java.lang.String payType){
		this.payType = payType;
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  支付金额
	 */
	@Column(name ="AMOUNT",nullable=true,length=22)
	public BigDecimal getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  支付金额
	 */
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信订单号
	 */
	@Column(name ="TRANSACTION_ID",nullable=true,length=36)
	public java.lang.String getTransactionId(){
		return this.transactionId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信订单号
	 */
	public void setTransactionId(java.lang.String transactionId){
		this.transactionId = transactionId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款银行
	 */
	@Column(name ="BANK_TYPE",nullable=true,length=32)
	public java.lang.String getBankType(){
		return this.bankType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款银行
	 */
	public void setBankType(java.lang.String bankType){
		this.bankType = bankType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  粉丝ID
	 */
	@Column(name ="OPENID",nullable=true,length=36)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  粉丝ID
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
	}

	@Column(name ="MCH_ID",nullable=true,length=50)
	public java.lang.String getMchId() {
		return mchId;
	}

	public void setMchId(java.lang.String mchId) {
		this.mchId = mchId;
	}
	
	
}
