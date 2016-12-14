package weixin.liuliangbao.flowcard.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2015/12/18.
 */
@Entity
@Table(name = "FlowCardBatch", schema = "")
@SuppressWarnings("serial")
public class FlowCardBatchEntity implements java.io.Serializable {
    private java.lang.String id;                 // varchar(36)                    not null,
    private java.lang.String batchNo;             //varchar(20)                    not null,
    private java.lang.String acctId;            //  varchar(36)                    null,
    private java.lang.Double putFlowTotal;      //  decimal(12,1)                  null,
    private java.lang.Integer putFlowCardCount; //  int                            null,
    private java.util.Date disabledDate;      // date                           null,
    private java.util.Date createTime;       // date                     null,
    private java.util.Date enableDate;       // date                     null,
    private java.lang.String status;
    private java.lang.String flowType;
    private java.lang.String isValid;  //当前批次是否
    @Column(name = "isValid", nullable = true, length = 20)
    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    @Column(name = "enableDate", nullable = true, length = 20)
    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    @Column(name = "STATUS", nullable = true, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "BATCHNO", nullable = true, length = 20)
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    @Column(name = "ACCTID", nullable = true, length = 36)
    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    @Column(name = "PUTFLOWTOTAL", nullable = true, length = 20)
    public Double getPutFlowTotal() {
        return putFlowTotal;
    }

    public void setPutFlowTotal(Double putFlowTotal) {
        this.putFlowTotal = putFlowTotal;
    }

    @Column(name = "PUTFLOWCARDCOUNT", nullable = true, length = 20)
    public Integer getPutFlowCardCount() {
        return putFlowCardCount;
    }

    public void setPutFlowCardCount(Integer putFlowCardCount) {
        this.putFlowCardCount = putFlowCardCount;
    }

    @Column(name = "DISABLEDDATE", nullable = true, length = 20)
    public Date getDisabledDate() {
        return disabledDate;
    }

    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }

    @Column(name = "CREATETIME", nullable = true, length = 20)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "FLOWTYPE", nullable = true, length = 20)
    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }
}
