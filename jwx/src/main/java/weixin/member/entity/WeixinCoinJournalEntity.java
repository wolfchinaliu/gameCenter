package weixin.member.entity;

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
 * @Description: 积分流水
 * @author onlineGenerator
 * @date 2015-06-03 12:31:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_coin_journal", schema = "")
@SuppressWarnings("serial")
public class WeixinCoinJournalEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**交易金额*/
	@Excel(exportName="交易金额")
	private java.math.BigDecimal coin;
	/**唯一码*/
	@Excel(exportName="唯一码")
	private java.lang.String uniqueCode;
	/**摘要*/
	@Excel(exportName="摘要")
	private java.lang.String notes;
	/**用户ID*/
	@Excel(exportName="用户ID")
	private java.lang.String memberid;
	/**创建人*/
	@Excel(exportName="创建人")
	private java.lang.String createrName;
	/**交易类别*/
	@Excel(exportName="交易类别")
	private java.lang.String dealType;
	/**交易时间*/
	@Excel(exportName="交易时间")
	private java.util.Date dealDate;
	/**公众号*/
	@Excel(exportName="公众号")
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
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  交易金额
	 */
	@Column(name ="COIN",nullable=true,length=12)
	public java.math.BigDecimal getCoin(){
		return this.coin;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  交易金额
	 */
	public void setCoin(java.math.BigDecimal coin){
		this.coin = coin;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  唯一码
	 */
	@Column(name ="UNIQUE_CODE",nullable=true,length=36)
	public java.lang.String getUniqueCode(){
		return this.uniqueCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  唯一码
	 */
	public void setUniqueCode(java.lang.String uniqueCode){
		this.uniqueCode = uniqueCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  摘要
	 */
	@Column(name ="NOTES",nullable=true,length=50)
	public java.lang.String getNotes(){
		return this.notes;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  摘要
	 */
	public void setNotes(java.lang.String notes){
		this.notes = notes;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户ID
	 */
	@Column(name ="OPENID",nullable=true,length=36)
	public java.lang.String getMemberid() {
		return memberid;
	}

	public void setMemberid(java.lang.String memberid) {
		this.memberid = memberid;
	}
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATER_NAME",nullable=true,length=32)
	public java.lang.String getCreaterName(){
		return this.createrName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreaterName(java.lang.String createrName){
		this.createrName = createrName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易类别
	 */
	@Column(name ="DEAL_TYPE",nullable=true,length=2)
	public java.lang.String getDealType(){
		return this.dealType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易类别
	 */
	public void setDealType(java.lang.String dealType){
		this.dealType = dealType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  交易时间
	 */
	@Column(name ="DEAL_DATE",nullable=true,length=20)
	public java.util.Date getDealDate(){
		return this.dealDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  交易时间
	 */
	public void setDealDate(java.util.Date dealDate){
		this.dealDate = dealDate;
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
}
