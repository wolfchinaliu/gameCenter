package weixin.liuliangbao.jsonbean.MoreFlow;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by aa on 2015/12/15.
 */
public class gameFlow implements java.io.Serializable {

    @Excel(exportName = "微信公众号")
    private java.lang.String accountid;
    /**
     * 主键
     */
    private java.lang.String id;
    /**
     * 创建人名称
     */
    private java.lang.String createName;
    /**
     * 创建日期
     */
    private java.util.Date createDate;
    /**
     * 活动名称
     */
    private java.lang.String title;
    /**
     * 活动描述
     */
    private java.lang.String description;
    /**
     * 一定等奖
     */
    @Excel(exportName = "一定等奖")
    private java.lang.String firstprize;
    /**
     * 一等奖数量
     */
    @Excel(exportName = "一等奖数量")
    private java.lang.Integer firstprizetotal;
    /**
     * 一等奖概率
     */
    @Excel(exportName = "一等奖概率")
    private java.lang.Integer firstprizeProb;
    /**
     * 二等奖
     */
    @Excel(exportName = "二等奖")
    private java.lang.String secondprize;
    /**
     * 二等奖数量
     */
    @Excel(exportName = "二等奖数量")
    private java.lang.Integer secondtotal;
    /**
     * 二等奖概率
     */
    @Excel(exportName = "二等奖概率")
    private java.lang.Integer secondprizeProb;
    /**
     * 三等奖
     */
    @Excel(exportName = "三等奖")
    private java.lang.String thirdprize;
    /**
     * 三等奖数量
     */
    @Excel(exportName = "三等奖数量")
    private java.lang.Integer thirdprizetotal;
    /**
     * 三等奖概率
     */
    @Excel(exportName = "三等奖概率")
    private java.lang.Integer thirdprizeProb;
    /**
     * 启用其他奖项
     */
    @Excel(exportName = "启用其他奖项")
    private java.lang.String abledotherprize;
    /**
     * 开始时间
     */
    @Excel(exportName = "开始时间")
    private java.util.Date starttime;
    /**
     * 结束时间
     */
    @Excel(exportName = "结束时间")
    private java.util.Date endtime;
    /**
     * 每天抽奖次数
     */
    @Excel(exportName = "每天抽奖次数")
    private java.lang.Integer lotterynumberday;
    /**
     * 总抽奖次数
     */
    @Excel(exportName = "总抽奖次数")
    private java.lang.Integer lotterynumber;
//    /**
//     * 微信公众号
//     */
//    @Excel(exportName = "微信公众号")
//    private java.lang.String accountid;

    private String lotteryType = "1";//活动类型 1为大转盘 2为刮刮乐

    /**
     * 频次
     */
    private String frequency;   //1：天:2：周:3：月


    private java.lang.String logoAccount;   //企业logo显示
    private java.lang.String accountname;   //商户名称

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getLogoAccount() {
        return logoAccount;
    }

    public void setLogoAccount(String logoAccount) {
        this.logoAccount = logoAccount;
    }

    @Column(name = "FREQUENCY", nullable = true, length = 16)
    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }


    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public java.lang.String getId() {
        return this.id;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  主键
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人名称
     */
    @Column(name = "CREATE_NAME", nullable = true, length = 50)
    public java.lang.String getCreateName() {
        return this.createName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  创建人名称
     */
    public void setCreateName(java.lang.String createName) {
        this.createName = createName;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  创建日期
     */
    @Column(name = "CREATE_DATE", nullable = true, length = 20)
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  创建日期
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  活动名称
     */
    @Column(name = "TITLE", nullable = true, length = 50)
    public java.lang.String getTitle() {
        return this.title;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  活动名称
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  活动描述
     */
    @Column(name = "DESCRIPTION", nullable = true, length = 4000)
    public java.lang.String getDescription() {
        return this.description;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  活动描述
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  一定等奖
     */
    @Column(name = "FIRSTPRIZE", nullable = true, length = 32)
    public java.lang.String getFirstprize() {
        return this.firstprize;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  一定等奖
     */
    public void setFirstprize(java.lang.String firstprize) {
        this.firstprize = firstprize;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  一等奖数量
     */
    @Column(name = "FIRSTPRIZETOTAL", nullable = true, length = 32)
    public java.lang.Integer getFirstprizetotal() {
        return this.firstprizetotal;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  一等奖数量
     */
    public void setFirstprizetotal(java.lang.Integer firstprizetotal) {
        this.firstprizetotal = firstprizetotal;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  一等奖概率
     */
    @Column(name = "FIRSTPRIZE_PROB", nullable = true, length = 32)
    public java.lang.Integer getFirstprizeProb() {
        return this.firstprizeProb;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  一等奖概率
     */
    public void setFirstprizeProb(java.lang.Integer firstprizeProb) {
        this.firstprizeProb = firstprizeProb;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  二等奖
     */
    @Column(name = "SECONDPRIZE", nullable = true, length = 32)
    public java.lang.String getSecondprize() {
        return this.secondprize;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  二等奖
     */
    public void setSecondprize(java.lang.String secondprize) {
        this.secondprize = secondprize;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  二等奖数量
     */
    @Column(name = "SECONDTOTAL", nullable = true, length = 32)
    public java.lang.Integer getSecondtotal() {
        return this.secondtotal;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  二等奖数量
     */
    public void setSecondtotal(java.lang.Integer secondtotal) {
        this.secondtotal = secondtotal;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  二等奖概率
     */
    @Column(name = "SECONDPRIZE_PROB", nullable = true, length = 32)
    public java.lang.Integer getSecondprizeProb() {
        return this.secondprizeProb;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  二等奖概率
     */
    public void setSecondprizeProb(java.lang.Integer secondprizeProb) {
        this.secondprizeProb = secondprizeProb;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  三等奖
     */
    @Column(name = "THIRDPRIZE", nullable = true, length = 32)
    public java.lang.String getThirdprize() {
        return this.thirdprize;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  三等奖
     */
    public void setThirdprize(java.lang.String thirdprize) {
        this.thirdprize = thirdprize;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  三等奖数量
     */
    @Column(name = "THIRDPRIZETOTAL", nullable = true, length = 32)
    public java.lang.Integer getThirdprizetotal() {
        return this.thirdprizetotal;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  三等奖数量
     */
    public void setThirdprizetotal(java.lang.Integer thirdprizetotal) {
        this.thirdprizetotal = thirdprizetotal;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  三等奖概率
     */
    @Column(name = "THIRDPRIZE_PROB", nullable = true, length = 32)
    public java.lang.Integer getThirdprizeProb() {
        return this.thirdprizeProb;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  三等奖概率
     */
    public void setThirdprizeProb(java.lang.Integer thirdprizeProb) {
        this.thirdprizeProb = thirdprizeProb;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  启用其他奖项
     */
    @Column(name = "ABLEDOTHERPRIZE", nullable = true, length = 32)
    public java.lang.String getAbledotherprize() {
        return this.abledotherprize;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  启用其他奖项
     */
    public void setAbledotherprize(java.lang.String abledotherprize) {
        this.abledotherprize = abledotherprize;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  开始时间
     */
    @Column(name = "STARTTIME", nullable = true, length = 32)
    public java.util.Date getStarttime() {
        return this.starttime;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  开始时间
     */
    public void setStarttime(java.util.Date starttime) {
        this.starttime = starttime;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  结束时间
     */
    @Column(name = "ENDTIME", nullable = true, length = 32)
    public java.util.Date getEndtime() {
        return this.endtime;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  结束时间
     */
    public void setEndtime(java.util.Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  每天抽奖次数
     */
    @Column(name = "LOTTERYNUMBERDAY", nullable = true, length = 32)
    public java.lang.Integer getLotterynumberday() {
        return this.lotterynumberday;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  每天抽奖次数
     */
    public void setLotterynumberday(java.lang.Integer lotterynumberday) {
        this.lotterynumberday = lotterynumberday;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  总抽奖次数
     */
    @Column(name = "LOTTERYNUMBER", nullable = true, length = 32)
    public java.lang.Integer getLotterynumber() {
        return this.lotterynumber;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  总抽奖次数
     */
    public void setLotterynumber(java.lang.Integer lotterynumber) {
        this.lotterynumber = lotterynumber;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  微信公众号
     */
    @Column(name = "ACCOUNTID", nullable = true, length = 32)
    public java.lang.String getAccountid() {
        return this.accountid;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  微信公众号
     */
    public void setAccountid(java.lang.String accountid) {
        this.accountid = accountid;
    }

    @Column(name = "lottery_type", nullable = true, length = 32)
    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    //游戏
    private java.lang.Double flowValue;

    public Double getFlowValue() {
        return flowValue;
    }

    public void setFlowValue(Double flowValue) {
        this.flowValue = flowValue;
    }


    @Override
    public boolean equals(Object obj) {
        gameFlow s = (gameFlow) obj;
        return id.equals(s.id) && accountid.equals(s.accountid);
    }

    @Override
    public int hashCode() {
        String in = id + accountid;
        return in.hashCode();
    }
}
