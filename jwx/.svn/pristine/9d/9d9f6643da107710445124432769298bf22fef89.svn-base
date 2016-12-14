package weixin.activity.entity;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.util.DataUtils;
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
@Table(name = "weixin_game_detail", schema = "")
@SuppressWarnings("serial")
public class WeixinGameDetailEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**抽奖时间*/
	@Excel(exportName="抽奖时间")
	private java.util.Date addtime;

	/**accountid*/
	@Excel(exportName="accountid")
	private java.lang.String accountid;
	/**抽奖人ID*/
	@Excel(exportName="参与人ID")
	private java.lang.String opendid;
	/**抽奖角度*/
	@Excel(exportName="得分")
	private java.lang.Integer score;
	/**提示信息*/
	@Excel(exportName="参与人昵称")
	private java.lang.String nickname;
	
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
	
	@Column(name ="nickname",nullable=true,length=255)
	public java.lang.String getNickname(){
		return this.nickname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抽奖人ID
	 */
	public void setNickname(java.lang.String nickname){
		this.nickname = nickname;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  抽奖角度
	 */
	@Column(name ="SCORE",nullable=true,length=32)
	public java.lang.Integer getScore(){
		return this.score;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  抽奖角度
	 */
	public void setScore(java.lang.Integer score){
		this.score = score;
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
