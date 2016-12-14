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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

import weixin.business.entity.WeixinCardEntity;
import weixin.member.entity.WeixinMemberEntity;

/**   
 * @Title: Entity
 * @Description: 卡券领取记录
 * @author onlineGenerator
 * @date 2015-06-17 14:54:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_usergetcard", schema = "")
@SuppressWarnings("serial")
public class WeixinUsergetcardEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**公众号*/
	@Excel(exportName="公众号")
	private java.lang.String accountid;
	/**领券方帐号用户*/
	@Excel(exportName="领券方帐号用户")
	private java.lang.String openId;
	/**优惠券ID*/
	@Excel(exportName="优惠券ID")
	private java.lang.String cardId;
	/**卡券code*/
	@Excel(exportName="卡券code")
	private java.lang.String userCardCode;
	/**是否为转赠*/
	@Excel(exportName="是否为转赠")
	private java.lang.String byFriend;
	/**领取场景值*/
	@Excel(exportName="领取场景值")
	private java.lang.String outerId;
	/**赠送方账号*/
	@Excel(exportName="赠送方账号")
	private java.lang.String friendUser;
	/**转赠前的code*/
	@Excel(exportName="转赠前的code")
	private java.lang.String oldCardCode;
	/**状态*/
	@Excel(exportName="状态")
	private java.lang.String status;//核销状态：0:未消费，1：已消费,3:已删除
	/**消费时间*/
	@Excel(exportName="消费时间")
	private java.util.Date consumeTime;
	/**核销来源*/
	@Excel(exportName="核销来源")
	private java.lang.String consumeSource;
	
	private WeixinCardEntity weixinCardEntity;//所属卡券ID
	
	private WeixinMemberEntity weixinMemberEntity;//领券用户
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  领券方帐号用户
	 */
	@Column(name ="OPEN_ID",nullable=true,length=36)
	public java.lang.String getOpenId(){
		return this.openId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领券方帐号用户
	 */
	public void setOpenId(java.lang.String openId){
		this.openId = openId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  优惠券ID
	 */
	@Column(name ="CARD_ID",nullable=true,length=50)
	public java.lang.String getCardId(){
		return this.cardId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  优惠券ID
	 */
	public void setCardId(java.lang.String cardId){
		this.cardId = cardId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卡券code
	 */
	@Column(name ="USER_CARD_CODE",nullable=true,length=50)
	public java.lang.String getUserCardCode(){
		return this.userCardCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卡券code
	 */
	public void setUserCardCode(java.lang.String userCardCode){
		this.userCardCode = userCardCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否为转赠
	 */
	@Column(name ="BY_FRIEND",nullable=true,length=2)
	public java.lang.String getByFriend(){
		return this.byFriend;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否为转赠
	 */
	public void setByFriend(java.lang.String byFriend){
		this.byFriend = byFriend;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  领取场景值
	 */
	@Column(name ="OUTER_ID",nullable=true,length=2)
	public java.lang.String getOuterId(){
		return this.outerId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  领取场景值
	 */
	public void setOuterId(java.lang.String outerId){
		this.outerId = outerId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  赠送方账号
	 */
	@Column(name ="FRIEND_USER",nullable=true,length=36)
	public java.lang.String getFriendUser(){
		return this.friendUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  赠送方账号
	 */
	public void setFriendUser(java.lang.String friendUser){
		this.friendUser = friendUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  转赠前的code
	 */
	@Column(name ="OLD_CARD_CODE",nullable=true,length=50)
	public java.lang.String getOldCardCode(){
		return this.oldCardCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  转赠前的code
	 */
	public void setOldCardCode(java.lang.String oldCardCode){
		this.oldCardCode = oldCardCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=2)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  消费时间
	 */
	@Column(name ="CONSUME_TIME",nullable=true,length=20)
	public java.util.Date getConsumeTime(){
		return this.consumeTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  消费时间
	 */
	public void setConsumeTime(java.util.Date consumeTime){
		this.consumeTime = consumeTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  核销来源
	 */
	@Column(name ="CONSUME_SOURCE",nullable=true,length=32)
	public java.lang.String getConsumeSource(){
		return this.consumeSource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  核销来源
	 */
	public void setConsumeSource(java.lang.String consumeSource){
		this.consumeSource = consumeSource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sid")
	public WeixinCardEntity getWeixinCardEntity() {
		return weixinCardEntity;
	}

	public void setWeixinCardEntity(WeixinCardEntity weixinCardEntity) {
		this.weixinCardEntity = weixinCardEntity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mid")
	public WeixinMemberEntity getWeixinMemberEntity() {
		return weixinMemberEntity;
	}

	public void setWeixinMemberEntity(WeixinMemberEntity weixinMemberEntity) {
		this.weixinMemberEntity = weixinMemberEntity;
	}
	
	
}
