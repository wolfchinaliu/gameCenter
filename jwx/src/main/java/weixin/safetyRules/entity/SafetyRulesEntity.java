package weixin.safetyRules.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2016/3/21.
 */

@Entity
@Table(name = "weixin_safety_rules", schema = "")
@SuppressWarnings("serial")
public class SafetyRulesEntity implements java.io.Serializable {
    @Excel(exportName = "活动ID")
    private String id; // 主键id，也是活动id
    @Excel(exportName = "规则名称")
    private String ruleName; // 规则名称
    @Excel(exportName = "频率")
    private String frequencyUnit;// 频率单位
    @Excel(exportName = "频率内调用最大次数")
    private int frequencyNum;// 频率调用最大次数
    @Excel(exportName = "充值流量最大值M")
    private double charegeFlow;// 每次最大充值流量值
    private String acctid;// 商户id
    private Date createDate;// 创建时间
    private Date operateDate;// 操作时间

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

    @Column(name = "rule_name")
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @Column(name = "frequency_unit")
    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }

    @Column(name = "frequency_num")
    public int getFrequencyNum() {
        return frequencyNum;
    }

    public void setFrequencyNum(int frequencyNum) {
        this.frequencyNum = frequencyNum;
    }

    @Column(name = "charge_flow")
    public double getCharegeFlow() {
        return charegeFlow;
    }

    public void setCharegeFlow(double charegeFlow) {
        this.charegeFlow = charegeFlow;
    }

    @Column(name = "acctid")
    public String getAcctid() {
        return acctid;
    }

    public void setAcctid(String acctid) {
        this.acctid = acctid;
    }

    @Column(name = "createDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "operateDate")
    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }
}
