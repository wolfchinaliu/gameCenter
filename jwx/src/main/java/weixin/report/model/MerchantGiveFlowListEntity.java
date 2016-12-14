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

public class MerchantGiveFlowListEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/** 用户的标识 */
	// @Excel(exportName="用户系统唯一标识")
	private java.lang.String id;
	private java.lang.String openId;
	private java.lang.String accountid;
	/** 用户昵称 */
	@Excel(exportName = "用户昵称")
	private java.lang.String operator;
	@Excel(exportName = "商户名", exportFieldWidth = 20)
	private java.lang.String accountname;
	@Excel(exportName = "商户等级")
	private java.lang.String level;
	@Excel(exportName = "所属商户")
	private java.lang.String belogAcct;
	@Excel(exportName = "手机号", exportFieldWidth = 20)
	private java.lang.String phoneNumber;
	/** 流量类型 */
	@Excel(exportName = "流量类型", exportConvertSign = 1)
	private java.lang.String flowType;
	@Excel(exportName = "运营商")
	private java.lang.String merchant;
	/** 活动类型 */
	@Excel(exportName = "活动类型")
	private java.lang.String reason;
	@Excel(exportName = "领取流量")
	private java.lang.Double flowValue;
	/** 领取时间 */
	@Excel(exportName = "领取时间", exportFieldWidth = 20)
	private java.util.Date operateDate;

	public String convertGetFlowType() {
		if (StringUtils.equals(FlowType.country.getCode(), flowType)) {
			return FlowType.country.getName();
		}
		if (StringUtils.equals(FlowType.province.getCode(), flowType)) {
			return FlowType.province.getName();
		}
		return flowType;
	}

	public java.lang.String getBelogAcct() {
		return belogAcct;
	}

	public void setBelogAcct(java.lang.String belogAcct) {
		this.belogAcct = belogAcct;
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

	/** 手机号 */

	/** 流量领取状态：0.未领取；1.已领取 */
	@Excel(exportName = "创建时间")
	private java.lang.String shortOperateDate;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public Double getFlowValue() {
		return flowValue;
	}

	public void setFlowValue(Double flowValue) {
		this.flowValue = flowValue;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

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
