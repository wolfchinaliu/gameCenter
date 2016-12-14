package weixin.report.model;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by guoLiang on 2016-04-12
 * 记录商家赠送给用过的流量中过期的流量记录信息
 *
 */
@Entity
@Table(name = "userflowgiverecordsHistory", schema = "")
public class UserGiveFlowHistoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**用户的标识*/
    //@Excel(exportName="用户系统唯一标识")
    private String id;
    private String openId;
    private String accountid;
    /**用户昵称*/
    private String operator;
    /**流量类型*/
    private String flowType;
    /**活动类型*/
    private String reason;
    /**领取时间*/
    private java.util.Date operateDate;
    /**领取流量*/
    private java.lang.Double flowValue;
    /**手机号*/
    private String phoneNumber;
    /**流量领取状态：0.未领取；1.已领取*/
    private Integer status;

    @Id
    @Column(name ="ID",nullable=false,length=36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name ="OPERATEDATE",nullable=true,length=255)
    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }
    @Column(name ="FLOWVALUE",nullable=true,length=12)
    public Double getFlowValue() {
        return flowValue;
    }

    public void setFlowValue(Double flowValue) {
        this.flowValue = flowValue;
    }
    @Column(name ="OPERATOR",nullable=true,length=255)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    @Column(name ="FLOWTYPE",nullable=true,length=255)
    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }
    @Column(name ="PHONENUMBER",nullable=true,length=255)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Column(name ="MERCHANTID",nullable=true,length=255)
    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
    @Column(name ="OPENID",nullable=true,length=255)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    @Column(name ="REASON",nullable=true,length=255)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    @Column(name = "status", length = 1, nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
