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
 * @Description: 微信活动
 * @author onlineGenerator
 * @date 2015-02-05 14:26:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_lotterylxc", schema = "")
@SuppressWarnings("serial")
public class WeixinLotteryEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**活动名称*/
	private java.lang.String title;
	/**活动描述*/
	private java.lang.String description;
	/**一等奖*/
	private java.lang.String firstprize;
	/**一等奖数量*/
	private java.lang.Integer firstprizetotal;
	/**一等奖概率*/
	private java.lang.Integer firstprizeProb;
	/**二等奖*/
	private java.lang.String secondprize;
	/**二等奖数量*/
	private java.lang.Integer secondprizetotal;
	/**二等奖概率*/
	private java.lang.Integer secondprizeProb;
    /**三等奖*/
    private java.lang.String thirdprize;
    /**三等奖数量*/
    private java.lang.Integer thirdprizetotal;
    /**三等奖概率*/
    private java.lang.Integer thirdprizeProb;
/**
 * 新增的奖项------晓娜
 */

 /**四等奖*/
    private java.lang.String   forthprize ;
    /**四等奖数量*/
    private java.lang.Integer  forthprizetotal;
    /**四等奖概率*/
    private java.lang.Integer  forthprizeProb;

    /**五等奖*/
    private java.lang.String fifthprize;
    /**五等奖数量*/
    private java.lang.Integer fifthprizetotal;
    /**五等奖概率*/
    private java.lang.Integer fifthprizeProb;

    /**六等奖*/
    private java.lang.String  sixthprize;
    /**六等奖数量*/
    private java.lang.Integer sixthprizetotal;
    /**六等奖概率*/
    private java.lang.Integer sixthprizeProb;

    /**
     * 新增的奖项------晓娜
     */

	/**启用其他奖项*/
	private java.lang.String abledotherprize;
	/**开始时间*/
	private java.util.Date starttime;
	/**结束时间*/
	private java.util.Date endtime;
	/**每天抽奖次数*/
	private java.lang.Integer lotterynumberday;
	/**总抽奖次数*/
	private java.lang.Integer lotterynumber;
	/**微信公众号*/
	private java.lang.String accountid;
	/**活动类型*/
	private String lotteryType="1";//活动类型 1为大转盘 2为刮刮乐 3红包
	/**频次*/
	private String frequency;   //1：天:2：周:3：月

	/**流量类型*/
	private String flowtype;   //1:全国流量 2：省内流量

	/**活动进行状态*/
	private String state; //0：结束，1：进行中，2：尚未开始

	@Column(name ="STATE",nullable=true,length=10)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name ="FLOWTYPE",nullable=true,length=32)
	public String getFlowtype() {
		return flowtype;
	}

	public void setFlowtype(String flowtype) {
		this.flowtype = flowtype;
	}

	@Column(name ="FREQUENCY",nullable=true,length=16)
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

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
	 *@return: java.lang.String  活动名称
	 */
	@Column(name ="TITLE",nullable=true,length=50)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动名称
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动描述
	 */
	@Column(name ="DESCRIPTION",nullable=true,length=4000)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动描述
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  一定等奖
	 */
	@Column(name ="FIRSTPRIZE",nullable=true,length=32)
	public java.lang.String getFirstprize(){
		return this.firstprize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  一定等奖
	 */
	public void setFirstprize(java.lang.String firstprize){
		this.firstprize = firstprize;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  一等奖数量
	 */
	@Column(name ="FIRSTPRIZETOTAL",nullable=true,length=32)
	public java.lang.Integer getFirstprizetotal(){
		return this.firstprizetotal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  一等奖数量
	 */
	public void setFirstprizetotal(java.lang.Integer firstprizetotal){
		this.firstprizetotal = firstprizetotal;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  一等奖概率
	 */
	@Column(name ="FIRSTPRIZE_PROB",nullable=true,length=32)
	public java.lang.Integer getFirstprizeProb(){
		return this.firstprizeProb;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  一等奖概率
	 */
	public void setFirstprizeProb(java.lang.Integer firstprizeProb){
		this.firstprizeProb = firstprizeProb;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二等奖
	 */
	@Column(name ="SECONDPRIZE",nullable=true,length=32)
	public java.lang.String getSecondprize(){
		return this.secondprize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二等奖
	 */
	public void setSecondprize(java.lang.String secondprize){
		this.secondprize = secondprize;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  二等奖数量
	 */
	@Column(name ="SECONDPRIZETOTAL",nullable=true,length=32)
	public java.lang.Integer getSecondprizetotal(){
		return this.secondprizetotal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  二等奖数量
	 */
	public void setSecondprizetotal(java.lang.Integer secondprizetotal){
		this.secondprizetotal = secondprizetotal;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  二等奖概率
	 */
	@Column(name ="SECONDPRIZE_PROB",nullable=true,length=32)
	public java.lang.Integer getSecondprizeProb(){
		return this.secondprizeProb;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  二等奖概率
	 */
	public void setSecondprizeProb(java.lang.Integer secondprizeProb){
		this.secondprizeProb = secondprizeProb;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  三等奖
	 */
	@Column(name ="THIRDPRIZE",nullable=true,length=32)
	public java.lang.String getThirdprize(){
		return this.thirdprize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  三等奖
	 */
	public void setThirdprize(java.lang.String thirdprize){
		this.thirdprize = thirdprize;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  三等奖数量
	 */
	@Column(name ="THIRDPRIZETOTAL",nullable=true,length=32)
	public java.lang.Integer getThirdprizetotal(){
		return this.thirdprizetotal;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  三等奖数量
	 */
	public void setThirdprizetotal(java.lang.Integer thirdprizetotal){
		this.thirdprizetotal = thirdprizetotal;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  三等奖概率
	 */
	@Column(name ="THIRDPRIZE_PROB",nullable=true,length=32)
	public java.lang.Integer getThirdprizeProb(){
		return this.thirdprizeProb;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  三等奖概率
	 */
	public void setThirdprizeProb(java.lang.Integer thirdprizeProb){
		this.thirdprizeProb = thirdprizeProb;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  启用其他奖项
	 */
	@Column(name ="ABLEDOTHERPRIZE",nullable=true,length=12)
	public java.lang.String getAbledotherprize(){
		return this.abledotherprize;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  启用其他奖项
	 */
	public void setAbledotherprize(java.lang.String abledotherprize){
		this.abledotherprize = abledotherprize;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="STARTTIME",nullable=true,length=32)
	public java.util.Date getStarttime(){
		return this.starttime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStarttime(java.util.Date starttime){
		this.starttime = starttime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="ENDTIME",nullable=true,length=32)
	public java.util.Date getEndtime(){
		return this.endtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndtime(java.util.Date endtime){
		this.endtime = endtime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  每天抽奖次数
	 */
	@Column(name ="LOTTERYNUMBERDAY",nullable=true,length=32)
	public java.lang.Integer getLotterynumberday(){
		return this.lotterynumberday;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  每天抽奖次数
	 */
	public void setLotterynumberday(java.lang.Integer lotterynumberday){
		this.lotterynumberday = lotterynumberday;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  总抽奖次数
	 */
	@Column(name ="LOTTERYNUMBER",nullable=true,length=32)
	public java.lang.Integer getLotterynumber(){
		return this.lotterynumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  总抽奖次数
	 */
	public void setLotterynumber(java.lang.Integer lotterynumber){
		this.lotterynumber = lotterynumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信公众号
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=32)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信公众号
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	@Column(name ="lottery_type",nullable=true,length=32)
	public String getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}

    @Column(name ="forthprize",nullable=true,length=12)
    public String getForthprize() {
        return forthprize;
    }

    public void setForthprize(String forthprize) {
        this.forthprize = forthprize;
    }
    @Column(name ="forthprizetotal",nullable=true,length=11)
    public Integer getForthprizetotal() {
        return forthprizetotal;
    }

    public void setForthprizetotal(Integer forthprizetotal) {
        this.forthprizetotal = forthprizetotal;
    }
    @Column(name ="forthprize_prob",nullable=true,length=11)
    public Integer getForthprizeProb() {
        return forthprizeProb;
    }

    public void setForthprizeProb(Integer forthprizeProb) {
        this.forthprizeProb = forthprizeProb;
    }



    @Column(name ="fifthprize",nullable=true,length=12)
    public String getFifthprize() {
        return fifthprize;
    }

    public void setFifthprize(String fifthprize) {
        this.fifthprize = fifthprize;
    }
    @Column(name ="fifthprizetotal",nullable=true,length=11)
    public Integer getFifthprizetotal() {
        return fifthprizetotal;
    }

    public void setFifthprizetotal(Integer fifthprizetotal) {
        this.fifthprizetotal = fifthprizetotal;
    }

    @Column(name ="fifthprize_prob",nullable=true,length=11)
    public Integer getFifthprizeProb() {
        return fifthprizeProb;
    }

    public void setFifthprizeProb(Integer fifthprizeProb) {
        this.fifthprizeProb = fifthprizeProb;
    }



    @Column(name ="sixthprize",nullable=true,length=12)
    public String getSixthprize() {
        return sixthprize;
    }

    public void setSixthprize(String sixthprize) {
        this.sixthprize = sixthprize;
    }
    @Column(name ="sixthprizetotal",nullable=true,length=11)
    public Integer getSixthprizetotal() {
        return sixthprizetotal;
    }

    public void setSixthprizetotal(Integer sixthprizetotal) {
        this.sixthprizetotal = sixthprizetotal;
    }
    @Column(name ="sixthprize_prob",nullable=true,length=11)
    public Integer getSixthprizeProb() {
        return sixthprizeProb;
    }

    public void setSixthprizeProb(Integer sixthprizeProb) {
        this.sixthprizeProb = sixthprizeProb;
    }
}
