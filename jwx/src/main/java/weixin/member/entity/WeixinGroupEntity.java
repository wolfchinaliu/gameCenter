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
 * @Description: 微信组
 * @author onlineGenerator
 * @date 2015-01-16 16:17:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_group", schema = "")
@SuppressWarnings("serial")
public class WeixinGroupEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**分组编号*/
	@Excel(exportName="分组编号")
	private java.lang.Integer groupId;
	/**分组名称*/
	@Excel(exportName="分组名称")
	private java.lang.String groupName;
	/**同步状态*/
	@Excel(exportName="同步状态")
	private java.lang.String synchStatu;
	
	/**分组编号*/
	@Excel(exportName="用户数量")
	private java.lang.Integer count;
	
	/**微信Id*/
	@Excel(exportName="微信Id")
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  分组编号
	 */
	@Column(name ="GROUP_ID",nullable=true,length=32)
	public java.lang.Integer getGroupId(){
		return this.groupId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  分组编号
	 */
	public void setGroupId(java.lang.Integer groupId){
		this.groupId = groupId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分组名称
	 */
	@Column(name ="GROUP_NAME",nullable=true,length=32)
	public java.lang.String getGroupName(){
		return this.groupName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分组名称
	 */
	public void setGroupName(java.lang.String groupName){
		this.groupName = groupName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  同步状态
	 */
	@Column(name ="SYNCH_STATU",nullable=true,length=32)
	public java.lang.String getSynchStatu(){
		return this.synchStatu;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  同步状态
	 */
	public void setSynchStatu(java.lang.String synchStatu){
		this.synchStatu = synchStatu;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信Id
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=32)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信Id
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}

	@Column(name ="COUNT",nullable=true,length=8)
	public java.lang.Integer getCount() {
		return count;
	}

	public void setCount(java.lang.Integer count) {
		this.count = count;
	}
	
	
}
