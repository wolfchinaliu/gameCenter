package weixin.shop.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;

import javax.xml.soap.Text;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

import weixin.member.entity.WeixinMemberEntity;

/**   
 * @Title: Entity
 * @Description: 订单
 * @author onlineGenerator
 * @date 2015-04-28 14:49:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_order", schema = "")
@SuppressWarnings("serial")
public class WeixinOrderEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**订单编号*/
	@Excel(exportName="订单编号")
	private String orderNo;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**支付人名称*/
	private java.lang.String checkName;
	/**支付日期*/
	private java.util.Date checkDate;
	/**所属公众号*/
	//@Excel(exportName="所属公众号")
	private java.lang.String accountid;
	/**订单金额*/
	@Excel(exportName="订单金额")
	private java.math.BigDecimal orderAmount;
	/**实收金额*/
	@Excel(exportName="实收金额")
	private java.math.BigDecimal payAmount;
	/**支付状态*/
	@Excel(exportName="支付状态")
	private java.lang.String status;//0:未付款、1：已付款、2：交易成功、3：退款中、4：退货中、5：已退款、9：已取消，8：已评价
	
	private String isShow;//是否显示：0:显示，1：隐藏
	
	/**发货状态*/
	@Excel(exportName="发货状态")
	private java.lang.String deliverStatus;//0:未发货、1：已发货、2：已收货
	
	
	private java.lang.String isAppraise;//0:未评价、1：已评价
	
	/**运费*/
	@Excel(exportName="运费")
	private java.math.BigDecimal freight;
//	/**粉丝ID*/
//	@Excel(exportName="粉丝ID")
//	private java.lang.String memberId;
	
	@Excel(exportName="粉丝")
	WeixinMemberEntity weixinMemberEntity;
	
	/**支付方式*/
	//@Excel(exportName="支付方式")
	private java.lang.String payType;
	/**收货人*/
	@Excel(exportName="收货人")
	private java.lang.String deliveryName;
	/**收货电话*/
	@Excel(exportName="收货电话")
	private java.lang.String deliveryPhone;
	/**收货省*/
	@Excel(exportName="收货省")
	private java.lang.String province;
	/**收货市*/
	@Excel(exportName="收货市")
	private java.lang.String city;
	/**收货区*/
	@Excel(exportName="收货区")
	private java.lang.String district;
	/**地址*/
	@Excel(exportName="地址")
	private java.lang.String address;
	/**邮编*/
	@Excel(exportName="邮编")
	private java.lang.String postcode;
	/**买家留言*/
	@Excel(exportName="买家留言")
	private java.lang.String leaveWord;
	
	/**快递公司*/
	@Excel(exportName="快递公司")
	private java.lang.String expressCompany;
	/**快递单号*/
	@Excel(exportName="快递单号")
	private java.lang.String expressNum;
	
	private java.util.Date expressDate;
	
	private Set<WeixinOrderDetailEntity> weixinOrderDetailList = new HashSet<WeixinOrderDetailEntity>();
	
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
	 *@return: java.lang.String  支付人名称
	 */
	@Column(name ="CHECK_NAME",nullable=true,length=50)
	public java.lang.String getCheckName(){
		return this.checkName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付人名称
	 */
	public void setCheckName(java.lang.String checkName){
		this.checkName = checkName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  支付日期
	 */
	@Column(name ="CHECK_DATE",nullable=true,length=20)
	public java.util.Date getCheckDate(){
		return this.checkDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  支付日期
	 */
	public void setCheckDate(java.util.Date checkDate){
		this.checkDate = checkDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公众号
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=36)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公众号
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  订单金额
	 */
	@Column(name ="ORDER_AMOUNT",nullable=true,length=16)
	public java.math.BigDecimal getOrderAmount(){
		return this.orderAmount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  订单金额
	 */
	public void setOrderAmount(java.math.BigDecimal orderAmount){
		this.orderAmount = orderAmount;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  实收金额
	 */
	@Column(name ="PAY_AMOUNT",nullable=true,length=16)
	public java.math.BigDecimal getPayAmount(){
		return this.payAmount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  实收金额
	 */
	public void setPayAmount(java.math.BigDecimal payAmount){
		this.payAmount = payAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付状态
	 */
	@Column(name ="STATUS",nullable=true,length=1)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发货状态
	 */
	@Column(name ="DELIVER_STATUS",nullable=true,length=1)
	public java.lang.String getDeliverStatus(){
		return this.deliverStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发货状态
	 */
	public void setDeliverStatus(java.lang.String deliverStatus){
		this.deliverStatus = deliverStatus;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  运费
	 */
	@Column(name ="FREIGHT",nullable=true,length=16)
	public java.math.BigDecimal getFreight(){
		return this.freight;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  运费
	 */
	public void setFreight(java.math.BigDecimal freight){
		this.freight = freight;
	}
//	/**
//	 *方法: 取得java.lang.String
//	 *@return: java.lang.String  粉丝ID
//	 */
//	@Column(name ="OPEN_ID",nullable=true,length=36)
//	public java.lang.String getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(java.lang.String memberId) {
//		this.memberId = memberId;
//	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="OPEN_ID")
	public WeixinMemberEntity getWeixinMemberEntity() {
		return weixinMemberEntity;
	}

	public void setWeixinMemberEntity(WeixinMemberEntity weixinMemberEntity) {
		this.weixinMemberEntity = weixinMemberEntity;
	}
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付方式
	 */
	@Column(name ="PAY_TYPE",nullable=true,length=32)
	public java.lang.String getPayType(){
		return this.payType;
	}

	

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付方式
	 */
	public void setPayType(java.lang.String payType){
		this.payType = payType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货人
	 */
	@Column(name ="DELIVERY_NAME",nullable=true,length=32)
	public java.lang.String getDeliveryName(){
		return this.deliveryName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货人
	 */
	public void setDeliveryName(java.lang.String deliveryName){
		this.deliveryName = deliveryName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货电话
	 */
	@Column(name ="DELIVERY_PHONE",nullable=true,length=32)
	public java.lang.String getDeliveryPhone(){
		return this.deliveryPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货电话
	 */
	public void setDeliveryPhone(java.lang.String deliveryPhone){
		this.deliveryPhone = deliveryPhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货省
	 */
	@Column(name ="PROVINCE",nullable=true,length=32)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货省
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货市
	 */
	@Column(name ="CITY",nullable=true,length=32)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货区
	 */
	@Column(name ="DISTRICT",nullable=true,length=32)
	public java.lang.String getDistrict(){
		return this.district;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货区
	 */
	public void setDistrict(java.lang.String district){
		this.district = district;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=50)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮编
	 */
	@Column(name ="POSTCODE",nullable=true,length=32)
	public java.lang.String getPostcode(){
		return this.postcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮编
	 */
	public void setPostcode(java.lang.String postcode){
		this.postcode = postcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  买家留言
	 */
	@Column(name ="LEAVE_WORD",nullable=true,length=50)
	public java.lang.String getLeaveWord(){
		return this.leaveWord;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  买家留言
	 */
	public void setLeaveWord(java.lang.String leaveWord){
		this.leaveWord = leaveWord;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  快递公司
	 */
	@Column(name ="EXPRESS_COMPANY",nullable=true,length=30)
	public java.lang.String getExpressCompany(){
		return this.expressCompany;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  快递公司
	 */
	public void setExpressCompany(java.lang.String expressCompany){
		this.expressCompany = expressCompany;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  快递单号
	 */
	@Column(name ="EXPRESS_NUM",nullable=true,length=30)
	public java.lang.String getExpressNum(){
		return this.expressNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  快递单号
	 */
	public void setExpressNum(java.lang.String expressNum){
		this.expressNum = expressNum;
	}

	@OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<WeixinOrderDetailEntity> getWeixinOrderDetailList() {
		return weixinOrderDetailList;
	}

	public void setWeixinOrderDetailList(
			Set<WeixinOrderDetailEntity> weixinOrderDetailList) {
		this.weixinOrderDetailList = weixinOrderDetailList;
	}

	@Column(name ="ORDER_NO",nullable=false,length=36)
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name ="IS_SHOW",nullable=true,length=2)
	public String getIsShow() {
		return isShow;
	}
	
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	@Column(name ="EXPRESS_DATE",nullable=true,length=20)
	public java.util.Date getExpressDate() {
		return expressDate;
	}

	public void setExpressDate(java.util.Date expressDate) {
		this.expressDate = expressDate;
	}

	@Column(name ="IS_APPRAISE",nullable=true,length=1)
	public java.lang.String getIsAppraise() {
		return isAppraise;
	}

	public void setIsAppraise(java.lang.String isAppraise) {
		this.isAppraise = isAppraise;
	}

	
	
}
