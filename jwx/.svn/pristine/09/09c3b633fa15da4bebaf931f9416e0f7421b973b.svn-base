package weixin.tenant.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 商户流量账户
 * @date 2015-12-1 16:56:36
 * Created by aa on 2015/12/1.
 */
@Entity
@Table(name = "MerchantFlowAccount", schema = "")
@SuppressWarnings("serial")
public class weixinAcctFlowAccountEntity implements java.io.Serializable {
    /**
     */
    private java.lang.String id;


    /**
     * 微信账号id
     */
    private java.lang.String accountId;

    /**
     *
     * @return: java.lang.String  商户账号id
     */
    @Column(name = "ACCOUNTID", nullable = true, length = 36)
    public String getAccountId() {
        return accountId;
    }

    /**
     *
     * @return: java.lang.String  商户账号id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }


    @Excel(exportName = "商户名字")
    private java.lang.String accountName;


    @Excel(exportName = "全国流量币")
    private java.lang.Double countryFlowValue;


    @Excel(exportName = "本省流量币")
    private java.lang.Double provinceFlowValue;


    @Excel(exportName = "全国流量卡")
    private java.lang.Double countryFlowCardValue;


    @Excel(exportName = "本省流量卡")
    private java.lang.Double provinceFlowCardValue;

    @Excel(exportName = "全国流量币单位")
    private java.lang.String countryFlowUnit;


    @Excel(exportName = "本省流量币单位")
    private java.lang.String provinceFlowUnit;

    /**
     */
    @Excel(exportName = "全国流量卡单位")
    private java.lang.String countryFlowCardUnit;

    /**
     */
    @Excel(exportName = "本省流量卡单位")
    private java.lang.String provinceFlowCardUnit;

    /**
     */
    @Excel(exportName = "隶属商户id")
    private java.lang.String acct_id;
    /**
     */
    @Excel(exportName = "自己本身的租户id")
    private java.lang.String tenantId;


    @Column(name = "TENANTID", nullable = true, length = 36)
    public String getTenantId() {
        return tenantId;
    }


    @Column(name = "TENANTID", nullable = true, length = 36)
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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


    @Column(name = "ACCOUNTNAME", nullable = true, length = 200)
    public String getAccountName() {
        return accountName;
    }


    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    @Column(name = "COUNTRYFLOWVALUE", nullable = true, length = 12)
    public Double getCountryFlowValue() {
        return countryFlowValue;
    }

    public void setCountryFlowValue(Double countryFlowValue) {
        this.countryFlowValue = countryFlowValue;
    }


    @Column(name = "PROVINCEFLOWVALUE", nullable = true, length = 12)
    public Double getProvinceFlowValue() {
        return provinceFlowValue;
    }


    public void setProvinceFlowValue(Double provinceFlowValue) {
        this.provinceFlowValue = provinceFlowValue;
    }


    @Column(name = "COUNTRYFLOWCARDVALUE", nullable = true, length = 12)
    public Double getCountryFlowCardValue() {
        return countryFlowCardValue;
    }


    public void setCountryFlowCardValue(Double countryFlowCardValue) {
        this.countryFlowCardValue = countryFlowCardValue;
    }

    @Column(name = "PROVINCEFLOWCARDVALUE", nullable = true, length = 12)
    public Double getProvinceFlowCardValue() {
        return provinceFlowCardValue;
    }

    public void setProvinceFlowCardValue(Double provinceFlowCardValue) {
        this.provinceFlowCardValue = provinceFlowCardValue;
    }


    @Column(name = "COUNTRYFLOWUNIT", nullable = true, length = 1)
    public String getCountryFlowUnit() {
        return countryFlowUnit;
    }


    public void setCountryFlowUnit(String countryFlowUnit) {
        this.countryFlowUnit = countryFlowUnit;
    }


    @Column(name = "PROVINCEFLOWUNIT", nullable = true, length = 1)
    public String getProvinceFlowUnit() {
        return provinceFlowUnit;
    }


    public void setProvinceFlowUnit(String provinceFlowUnit) {
        this.provinceFlowUnit = provinceFlowUnit;
    }


    @Column(name = "COUNTRYFLOWCARDUNIT", nullable = true, length = 1)
    public String getCountryFlowCardUnit() {
        return countryFlowCardUnit;
    }


    public void setCountryFlowCardUnit(String countryFlowCardUnit) {
        this.countryFlowCardUnit = countryFlowCardUnit;
    }


    @Column(name = "PROVINCEFLOWCARDUNIT", nullable = true, length = 1)
    public String getProvinceFlowCardUnit() {
        return provinceFlowCardUnit;
    }


    public void setProvinceFlowCardUnit(String provinceFlowCardUnit) {
        this.provinceFlowCardUnit = provinceFlowCardUnit;
    }


    @Column(name = "ACCT_ID", nullable = true, length = 36)
    public String getAcct_id() {
        return acct_id;
    }


    public void setAcct_id(String acct_id) {
        this.acct_id = acct_id;
    }
}
