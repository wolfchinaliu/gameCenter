package weixin.liuliangbao.flowcard.Entity;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import weixin.util.DataDictionaryUtil.FlowType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2015/12/18.
 */
@Entity
@Table(name = "FlowCardInfo", schema = "")
@SuppressWarnings("serial")
public class FlowCardInfoEntity implements java.io.Serializable {
    @Excel(exportName = "id")
    private java.lang.String id;
    @Excel(exportName = "卡编码")
    private java.lang.String cardCode;
    @Excel(exportName = "卡号")// varchar(20)
    private java.lang.String cardNumber;

    private java.lang.String openId;
    @Excel(exportName = "所属商户id")
    private java.lang.String belongAcctId;      // varchar(36)                    null,
    @Excel(exportName = "现所属商户id")
    private java.lang.String batchId;           // varchar(36)                    null,
    @Excel(exportName = "批次编码")
    private java.lang.String batchNo;         //  varchar(20)                    null,
    @Excel(exportName = "商户id")
    private java.lang.String acctId;           // varchar(36)                    null,
    @Excel(exportName = "每张卡流量值")
    private java.lang.Double flowValue;
    @Excel(exportName = "流量单位")// decimal(12,1)                  null,
    private java.lang.String flowUnit;
    @Excel(exportName = "有效开始时间")//  varchar(2)                     null,
    private java.util.Date beginDate;
    @Excel(exportName = "有效结束时间")
    private java.util.Date endDate;
    @Excel(exportName = "是否使用")
    private java.lang.String isUse;
    @Excel(exportName = "使用日期")//  varchar(2)                 ;
    private java.util.Date useDate;
    @Excel(exportName = "提取码")
    private java.lang.String extractionCode;      // varchar(10)
    @Excel(exportName = "是否锁定")
    private java.lang.String statusLock;         // varchar(10)                    null,
    @Excel(exportName = "是否有效")
    private java.lang.String isValid;           // varchar(2)                     null,
    @Excel(exportName = "二维码图片")
    private java.lang.String QRcode;
    @Excel(exportName = "充值号码")// varchar(20)                    null,
    private java.lang.String phoneNumber;
    @Excel(exportName = "流量类型",exportConvertSign=1,orderNum="20")// varchar(20)
    private java.lang.String flowType;

    public String convertGetFlowType(){
        if(StringUtils.equals(FlowType.country.getCode(), flowType)){
            return FlowType.country.getName();
        }
        if(StringUtils.equals(FlowType.province.getCode(), flowType)){
            return FlowType.province.getName();
        }
        return flowType;
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

    @Column(name = "CARDCODE", nullable = true, length = 20)
    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    @Column(name = "CARDNUMBER", nullable = true, length = 60)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Column(name = "BELONGACCTID", nullable = true, length = 36)
    public String getBelongAcctId() {
        return belongAcctId;
    }

    public void setBelongAcctId(String belongAcctId) {
        this.belongAcctId = belongAcctId;
    }

    @Column(name = "BATCHID", nullable = true, length = 36)
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
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
    
    public java.lang.String getOpenId() {
        return openId;
    }

    public void setOpenId(java.lang.String openId) {
        this.openId = openId;
    }

    @Column(name = "FLOWVALUE", nullable = true, length = 12)
    public Double getFlowValue() {
        return flowValue;
    }

    public void setFlowValue(Double flowValue) {
        this.flowValue = flowValue;
    }

    @Column(name = "FLOWUNIT", nullable = true, length = 2)
    public String getFlowUnit() {
        return flowUnit;
    }

    public void setFlowUnit(String flowUnit) {
        this.flowUnit = flowUnit;
    }

    @Column(name = "BEGINDATE", nullable = true, length = 20)
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Column(name = "ENDDATE", nullable = true, length = 20)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "ISUSE", nullable = true, length = 2)
    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    @Column(name = "USEDATE", nullable = true, length = 20)
    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    @Column(name = "EXTRACTIONCODE", nullable = true, length = 10)
    public String getExtractionCode() {
        return extractionCode;
    }

    public void setExtractionCode(String extractionCode) {
        this.extractionCode = extractionCode;
    }

    @Column(name = "STATUSLOCK", nullable = true, length = 10)
    public String getStatusLock() {
        return statusLock;
    }

    public void setStatusLock(String statusLock) {
        this.statusLock = statusLock;
    }

    @Column(name = "ISVALID", nullable = true, length = 2)
    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    @Column(name = "QRCODE", nullable = true, length = 20)
    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }

    @Column(name = "PHONENUMBER", nullable = true, length = 20)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "FLOWTYPE", nullable = true, length = 11)
    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }
}
