package weixin.tenant.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 商户流量账户表
 * @date 2015-12-1 16:56:36
 * Created by aa on 2015/12/1.
 */
@Entity
@Table(name = "MerchantCoverArea", schema = "")
@SuppressWarnings("serial")
public class WeixinMerchantCoverAreaEntity implements java.io.Serializable {
    /**
     * 主键
     */
    private java.lang.String id;


    /**
     * 商户id
     */
    @Excel(exportName = "商户id")
    private java.lang.String accountID;


    /**
     * 省id
     */
    @Excel(exportName = "省id")
    private java.lang.String provinceID;

    /**
     * 地市id
     */
    @Excel(exportName = "地市id")
    private java.lang.String cityID;

    /**
     * 省名称
     */
    @Excel(exportName = "省名称")
    private java.lang.String provincename;


    /**
     * 地市名称
     */
    @Excel(exportName = "地市名称")
    private java.lang.String cityname;
    /**
     * 运营商------xiaona--2016年4月30日
     */
    @Excel(exportName = "运营商")
    private java.lang.String businessArea;

    @Column(name = "businessArea", nullable = false, length = 20)
    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  商户id
     */
    @Column(name = "ACCOUNTID", nullable = true, length = 36)
    public String getAccountID() {
        return accountID;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  商户id
     */
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  省id
     */
    @Column(name = "PROVINCEID", nullable = true, length = 36)
    public String getProvinceID() {
        return provinceID;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  省id
     */
    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  地市id
     */
    @Column(name = "CITYID", nullable = true, length = 36)
    public String getCityID() {
        return cityID;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  地市id
     */
    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  省名称
     */
    @Column(name = "PROVINCENAME", nullable = true, length = 36)
    public String getProvincename() {
        return provincename;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  省名称
     */
    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  地市名称
     */
    @Column(name = "CITYNAME", nullable = true, length = 36)
    public String getCityname() {
        return cityname;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  地市名称
     */
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
}
