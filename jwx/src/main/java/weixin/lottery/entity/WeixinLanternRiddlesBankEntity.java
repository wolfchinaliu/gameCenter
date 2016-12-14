package weixin.lottery.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2016/1/21.
 */
@Entity
@Table(name = "lanternRiddlesBank", schema = "")
@SuppressWarnings("serial")
public class WeixinLanternRiddlesBankEntity implements java.io.Serializable {

    private java.lang.String id;
    @Excel(exportName="谜面")
    private java.lang.String lanternRon;

    @Excel(exportName="谜底")
    private java.lang.String lanternRdown;

    @Excel(exportName="谜目")
    private java.lang.String lanternReyes;

    private java.lang.String operator;
    private java.lang.String accountId;
    private java.lang.String hdid;
    private java.util.Date addTime;
    @Column(name = "HDID", nullable = false, length = 36)
    public String getHdid() {
        return hdid;
    }

    public void setHdid(String hdid) {
        this.hdid = hdid;
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

    @Column(name = "LANTERNRON", nullable = false, length = 100)
    public String getLanternRon() {
        return lanternRon;
    }

    public void setLanternRon(String lanternRon) {
        this.lanternRon = lanternRon;
    }

    @Column(name = "LANTERNRDOWN", nullable = false, length = 100)
    public String getLanternRdown() {
        return lanternRdown;
    }

    public void setLanternRdown(String lanternRdown) {
        this.lanternRdown = lanternRdown;
    }

    @Column(name = "LANTERNREYES", nullable = false, length = 100)
    public String getLanternReyes() {
        return lanternReyes;
    }

    public void setLanternReyes(String lanternReyes) {
        this.lanternReyes = lanternReyes;
    }

    @Column(name = "OPERATOR", nullable = false, length = 20)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(name = "ACCOUNTID", nullable = false, length = 36)
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Column(name = "ADDTIME", nullable = false, length = 36)
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
