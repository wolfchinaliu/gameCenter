package weixin.report.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.poi.excel.annotation.Excel;

import weixin.util.DataDictionaryUtil.FlowType;

/**
 * @author parallel_line
 * @version 2016年9月26日 下午9:01:11
 */

public class MerchantCharge implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(exportName = "商户名称",orderNum="1")
    private java.lang.String acctName;
    @Excel(exportName = "商户等级",orderNum="10",exportConvertSign=1)
    private java.lang.String acctLevel;
    @Excel(exportName = "流量类型",exportConvertSign=1,orderNum="20")
    private java.lang.String flowType;
    @Excel(exportName = "流量值",orderNum="30")
    private java.lang.Double flowValue;
    @Excel(exportName = "充值时间",exportFieldWidth=20,orderNum="40",exportFormat="yyyy-MM-dd HH:mm:ss")
    private Date chargetime;
    @Excel(exportName = "描述",orderNum="50")
    private java.lang.String des;
    
    public String convertGetFlowType(){
        if(StringUtils.equals(FlowType.country.getCode(), flowType)){
            return FlowType.country.getName();
        }
        if(StringUtils.equals(FlowType.province.getCode(), flowType)){
            return FlowType.province.getName();
        }
        return flowType;
    }

    public String convertGetAcctLevel(){
        if(StringUtils.equals("0",acctLevel)){
            return "S级";
        }
        if(StringUtils.equals("1",acctLevel)){
            return "A级";
        }
        if(StringUtils.equals("2",acctLevel)){
            return "B级";
        }
        if(StringUtils.equals("3",acctLevel)){
            return "C级";
        }
        return acctLevel;
    }

    public java.lang.String getAcctName() {
        return acctName;
    }

    public void setAcctName(java.lang.String acctName) {
        this.acctName = acctName;
    }

    public java.lang.String getAcctLevel() {
        return acctLevel;
    }

    public void setAcctLevel(java.lang.String acctLevel) {
        this.acctLevel = acctLevel;
    }

    public java.lang.String getFlowType() {
        return flowType;
    }

    public void setFlowType(java.lang.String flowType) {
        this.flowType = flowType;
    }

    public java.lang.Double getFlowValue() {
        return flowValue;
    }

    public void setFlowValue(java.lang.Double flowValue) {
        this.flowValue = flowValue;
    }

    public Date getChargetime() {
        return chargetime;
    }

    public void setChargetime(Date chargetime) {
        this.chargetime = chargetime;
    }

    public java.lang.String getDes() {
        return des;
    }

    public void setDes(java.lang.String des) {
        this.des = des;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
