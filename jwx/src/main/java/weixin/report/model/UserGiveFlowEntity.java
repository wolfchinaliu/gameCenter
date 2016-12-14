package weixin.report.model;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import weixin.util.DataDictionaryUtil.FlowType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xiaona on 2016/3/16.
 */
@Entity
@Table(name = "userflowgiverecords", schema = "")
@SuppressWarnings("serial")
public class UserGiveFlowEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /**用户的标识*/
    //@Excel(exportName="用户系统唯一标识")
    private java.lang.String id;
    private java.lang.String openId;
    private java.lang.String accountid;
    /**用户昵称*/
    @Excel(exportName="用户昵称")
    private java.lang.String operator;
    /**流量类型*/
    @Excel(exportName = "流量类型",exportConvertSign=1,orderNum="20")
    private java.lang.String flowType;
    /**活动类型*/
    @Excel(exportName="活动类型")
    private java.lang.String reason;
    /**领取时间*/
    @Excel(exportName="领取时间")
    private java.util.Date operateDate;
    /**领取流量*/
    @Excel(exportName="领取流量")
    private java.lang.Double flowValue;
    
    
    private java.lang.String accountname;
    
    private java.lang.String level;
    
    private java.lang.String merchant;
    
    @Transient 
    public String convertGetFlowType(){
        if(StringUtils.equals(FlowType.country.getCode(), flowType)){
            return FlowType.country.getName();
        }
        if(StringUtils.equals(FlowType.province.getCode(), flowType)){
            return FlowType.province.getName();
        }
        return flowType;
    }
    
    @Transient
    public java.lang.String getAccountname() {
		return accountname;
	}

	public void setAccountname(java.lang.String accountname) {
		this.accountname = accountname;
	}
	@Transient
	public java.lang.String getLevel() {
		return level;
	}

	public void setLevel(java.lang.String level) {
		this.level = level;
	}
	@Transient
	public java.lang.String getMerchant() {
		return merchant;
	}

	public void setMerchant(java.lang.String merchant) {
		this.merchant = merchant;
	}

	
    
    /**手机号*/
    @Excel(exportName="手机号")
    private java.lang.String phoneNumber;
    /**流量领取状态：0.未领取；1.已领取*/
    @Excel(exportName="创建时间")
    private java.lang.String shortOperateDate;
    
    private String description;
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Integer status;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
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
    public void setShortOperateDate(String shortOperateDate) {
		this.shortOperateDate = shortOperateDate;
	}
	
	@Transient
	public String getShortOperateDate() {
		return shortOperateDate;
	}
}
