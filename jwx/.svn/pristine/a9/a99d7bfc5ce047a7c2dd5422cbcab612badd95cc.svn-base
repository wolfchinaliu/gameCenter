package weixin.subActivityAnalysis.view;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.poi.excel.annotation.Excel;

import weixin.util.DataDictionaryUtil.FlowType;

/**
 * Created by Wangpeng on 2016/10/18.
 * 
 */
public class SubActivityAnalysisBean implements java.io.Serializable {
	@Excel(exportName="日期")
    private java.lang.String operateDate;
	@Excel(exportName="商户名称",exportFieldWidth=15)
    private java.lang.String subAcctForName;
	@Excel(exportName="商户等级",exportConvertSign=1)
    private java.lang.String acctLevel;
	@Excel(exportName="所属商户",exportFieldWidth=15)
    private java.lang.String acctForName;
	@Excel(exportName="活动类型")
	private java.lang.String reason;
	@Excel(exportName="参与人次")
	private java.lang.String sumId;
    @Excel(exportName="赠送流量")
    private java.lang.Double sumValue;
    public String convertGetAcctLevel(){
    	if(StringUtils.equals("0", acctLevel)){
            return "S级";
        }
        if(StringUtils.equals("1", acctLevel)){
            return "A级";
        }
        if(StringUtils.equals("2", acctLevel)){
            return "B级";
        }
        if(StringUtils.equals("3", acctLevel)){
            return "c级";
        }
        return acctLevel;
    }
    public java.lang.String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(java.lang.String operateDate) {
		this.operateDate = operateDate;
	}
	public java.lang.String getAcctForName() {
		return acctForName;
	}
	public void setAcctForName(java.lang.String acctForName) {
		this.acctForName = acctForName;
	}
	public java.lang.String getAcctLevel() {
		return acctLevel;
	}
	public void setAcctLevel(java.lang.String acctLevel) {
		this.acctLevel = acctLevel;
	}
	public java.lang.String getSubAcctForName() {
		return subAcctForName;
	}
	public void setSubAcctForName(java.lang.String subAcctForName) {
		this.subAcctForName = subAcctForName;
	}
	public java.lang.String getReason() {
		return reason;
	}
	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}
	public java.lang.String getSumId() {
		return sumId;
	}
	public void setSumId(java.lang.String sumId) {
		this.sumId = sumId;
	}
	public java.lang.Double getSumValue() {
		return sumValue;
	}
	public void setSumValue(java.lang.Double sumValue) {
		this.sumValue = sumValue;
	}
	public java.lang.Double getAvgValue() {
		return avgValue;
	}
	public void setAvgValue(java.lang.Double avgValue) {
		this.avgValue = avgValue;
	}
	public java.lang.String getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(java.lang.String merchantID) {
		this.merchantID = merchantID;
	}
	@Excel(exportName="平均赠送流量",exportFieldWidth=15)
    private java.lang.Double avgValue;
    private java.lang.String merchantID;
	
}
