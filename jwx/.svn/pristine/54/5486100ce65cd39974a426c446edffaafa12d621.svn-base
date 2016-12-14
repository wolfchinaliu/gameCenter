package weixin.lottery.entity;

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
 * @Description: 抽奖记录表
 * @author onlineGenerator
 * @date 2015-02-07 13:33:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_draw_detail", schema = "")
@SuppressWarnings("serial")
public class WeixinDrawDetailEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**抽奖时间*/
	@Excel(exportName="抽奖时间")
	private java.util.Date addtime;

	/**accountid*/
	@Excel(exportName="accountid")
	private java.lang.String accountid;
	/**抽奖人ID*/
	@Excel(exportName="抽奖人ID")
	private java.lang.String opendid;
	/**抽奖角度*/
	@Excel(exportName="抽奖角度")
	private java.lang.Integer angle;
	/**提示信息*/
	@Excel(exportName="提示信息")
	private java.lang.String msg;
	/**活动Id*/
	@Excel(exportName="活动Id")
	private java.lang.String hdid;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=100)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  抽奖时间
	 */
	@Column(name ="ADDTIME",nullable=true)
	public java.util.Date getAddtime(){
		return this.addtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  抽奖时间
	 */
	public void setAddtime(java.util.Date addtime){
		this.addtime = addtime;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  accountid
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=100)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  accountid
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抽奖人ID
	 */
	@Column(name ="OPENDID",nullable=true,length=100)
	public java.lang.String getOpendid(){
		return this.opendid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抽奖人ID
	 */
	public void setOpendid(java.lang.String opendid){
		this.opendid = opendid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  抽奖角度
	 */
	@Column(name ="ANGLE",nullable=true,length=32)
	public java.lang.Integer getAngle(){
		return this.angle;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  抽奖角度
	 */
	public void setAngle(java.lang.Integer angle){
		this.angle = angle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提示信息
	 */
	@Column(name ="MSG",nullable=true,length=32)
	public java.lang.String getMsg(){
		return this.msg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提示信息
	 */
	public void setMsg(java.lang.String msg){
		this.msg = msg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动Id
	 */
	@Column(name ="HDID",nullable=true,length=32)
	public java.lang.String getHdid(){
		return this.hdid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动Id
	 */
	public void setHdid(java.lang.String hdid){
		this.hdid = hdid;
	}
}
