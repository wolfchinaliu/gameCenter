package weixin.tenant.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2015/12/2.
 */
@Entity
@Table(name = "flowCardTradeRecords", schema = "")
@SuppressWarnings("serial")
public class FlowCardTradeRecordsEntity implements java.io.Serializable {
    /**
     * 主键
     */
    private java.lang.String id;
    /**
     * 充值商户id
     */
    @Excel(exportName = "充值商户id")
    private java.lang.String fromAcc_id;

    /**
     * 被充值商户id
     */
    @Excel(exportName = "被充值商户id")
    private java.lang.String toAcc_id;
    /**
     * 流量币值
     */
    @Excel(exportName = "流量币值")
    private java.lang.Double flowValue;

    /**
     * 交易日期
     */
    @Excel(exportName = "交易日期")
    private java.util.Date tradingDate;
    
    private String tradeType;
    private String des;

    /**
     * 后期优化查询需要添加的字段（--2016年4月21日--）
     */
    @Excel(exportName = "流量类型")
    private java.lang.String flowtype;
    @Excel(exportName = "流量来源")
    private java.lang.String flowSource;
    @Excel(exportName = "充值前全国余额")
    private java.lang.Double flowQChargeBerf;
    @Excel(exportName = "充值前省余额")
    private java.lang.Double flowPChargeBerf;
    @Excel(exportName = "充值后全国余额")
    private java.lang.Double flowQCharged;
    @Excel(exportName = "充值后省余额")
    private java.lang.Double flowPCharged;
    @Excel(exportName = "充值商户名称")
    private java.lang.String fromAccountname;

    @Excel(exportName = "被充值商户名称")
    private java.lang.String toAccountname;
    @Excel(exportName = "操作人")
    private java.lang.String curOperator;
    /**
     * 后期优化查询需要添加的字段（--2016年4月21日--）
     */


    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  充值商户id
     */
    @Column(name = "FROMACC_ID", nullable = true, length = 36)
    public String getFromAcc_id() {
        return fromAcc_id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  充值商户id
     */
    public void setFromAcc_id(String fromAcc_id) {
        this.fromAcc_id = fromAcc_id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  被充值商户id
     */
    @Column(name = "TOACC_ID", nullable = true, length = 36)
    public String getToAcc_id() {
        return toAcc_id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  被充值商户id
     */
    public void setToAcc_id(String toAcc_id) {
        this.toAcc_id = toAcc_id;
    }

    /**
     * 方法: 取得  java.lang.Double
     *
     * @return: java.lang.Double  充值数目
     */
    @Column(name = "FLOWVALUE", nullable = true, length = 12)
    public Double getFlowValue() {
        return flowValue;
    }

    /**
     * 方法: 取得  java.lang.Double
     *
     * @return: java.lang.Double  充值数目
     */
    public void setFlowValue(Double flowValue) {
        this.flowValue = flowValue;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  交易日期
     */
    @Column(name = "TRADINGDATE", nullable = true, length = 32)
    public Date getTradingDate() {
        return tradingDate;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  交易日期
     */
    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Column(name = "flowtype", nullable = true, length = 10)
    public String getFlowtype() {
        return flowtype;
    }

    public void setFlowtype(String flowtype) {
        this.flowtype = flowtype;
    }
    @Column(name = "flowSource", nullable = true, length = 10)
    public String getFlowSource() {
        return flowSource;
    }

    public void setFlowSource(String flowSource) {
        this.flowSource = flowSource;
    }
    @Column(name = "flowQChargeBerf", nullable = true, length = 12)
    public Double getFlowQChargeBerf() {
        return flowQChargeBerf;
    }

    public void setFlowQChargeBerf(Double flowQChargeBerf) {
        this.flowQChargeBerf = flowQChargeBerf;
    }
    @Column(name = "flowPChargeBerf", nullable = true, length = 12)
    public Double getFlowPChargeBerf() {
        return flowPChargeBerf;
    }

    public void setFlowPChargeBerf(Double flowPChargeBerf) {
        this.flowPChargeBerf = flowPChargeBerf;
    }
    @Column(name = "flowQCharged", nullable = true, length = 12)
    public Double getFlowQCharged() {
        return flowQCharged;
    }

    public void setFlowQCharged(Double flowQCharged) {
        this.flowQCharged = flowQCharged;
    }
    @Column(name = "flowPCharged", nullable = true, length = 12)
    public Double getFlowPCharged() {
        return flowPCharged;
    }

    public void setFlowPCharged(Double flowPCharged) {
        this.flowPCharged = flowPCharged;
    }
    @Column(name = "fromAccountname", nullable = true, length = 50)
    public String getFromAccountname() {
        return fromAccountname;
    }

    public void setFromAccountname(String fromAccountname) {
        this.fromAccountname = fromAccountname;
    }
    @Column(name = "toAccountname", nullable = true, length = 50)
    public String getToAccountname() {
        return toAccountname;
    }

    public void setToAccountname(String toAccountname) {
        this.toAccountname = toAccountname;
    }
    @Column(name = "curOperator", nullable = true, length = 20)
    public String getCurOperator() {
        return curOperator;
    }

    public void setCurOperator(String curOperator) {
        this.curOperator = curOperator;
    }
}
