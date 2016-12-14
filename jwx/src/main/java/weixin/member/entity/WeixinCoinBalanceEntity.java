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
 * @Description: 积分帐户
 * @author onlineGenerator
 * @date 2015-06-03 12:31:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_coin_balance", schema = "")
@SuppressWarnings("serial")
public class WeixinCoinBalanceEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**余额*/
	@Excel(exportName="余额")
	private java.math.BigDecimal balance;
	/**用户ID*/
	@Excel(exportName="用户ID")
	private java.lang.String memberid;
	/**积分类型*/
	@Excel(exportName="积分类型")
	private java.lang.Integer coinType;
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
	 *@return: java.math.BigDecimal  余额
	 */
	@Column(name ="BALANCE",nullable=true,length=12)
	public java.math.BigDecimal getBalance(){
		return this.balance;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  余额
	 */
	public void setBalance(java.math.BigDecimal balance){
		this.balance = balance;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  积分类型
	 */
	@Column(name ="COIN_TYPE",nullable=true,length=2)
	public java.lang.Integer getCoinType(){
		return this.coinType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  积分类型
	 */
	public void setCoinType(java.lang.Integer coinType){
		this.coinType = coinType;
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
