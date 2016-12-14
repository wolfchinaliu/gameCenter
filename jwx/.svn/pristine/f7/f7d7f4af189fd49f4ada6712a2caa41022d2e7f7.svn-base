package weixin.safetyRules.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by dyt on 2016/3/21.
 */

@Entity
@Table(name = "weixin_rules_records", schema = "")
@SuppressWarnings("serial")
public class WeixinRuleRecordsEntity implements java.io.Serializable {
    private String id; // 主键id
    private String ruleId; // 活动id
    private String period;// 计时期间
    private Integer surplusNum;// 剩余调用次数
    private Double surplusFlow;// 剩余流量
    private String acctId;// 商户id
    private Date createDate;// 创建时间

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

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }

    public Double getSurplusFlow() {
        return surplusFlow;
    }

    public void setSurplusFlow(Double surplusFlow) {
        this.surplusFlow = surplusFlow;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}
