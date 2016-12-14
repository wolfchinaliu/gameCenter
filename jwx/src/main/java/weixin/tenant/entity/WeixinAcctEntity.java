package weixin.tenant.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 商户管理表
 * @date 2015-04-25 21:14:50
 */
@Entity
@Table(name = "weixin_acct", schema = "")
@SuppressWarnings("serial")
public class WeixinAcctEntity implements java.io.Serializable {
    /**
     * 主键
     */
    private java.lang.String id;
    /**
     * 创建日期
     */
    private java.util.Date createDate;
    /**
     * 修改日期
     */
    private java.util.Date endDate;
    /**
     * 商户名称
     */
    @Excel(exportName = "商户名称")
    private java.lang.String acctName;
    /**
     * 企业名称
     */
    @Excel(exportName = "企业名称")
    private java.lang.String businessName;
    /**
     * 电话
     */
    @Excel(exportName = "电话")
    private java.lang.String mobilePhone;
    /**
     * 邮箱
     */
    @Excel(exportName = "邮箱")
    private java.lang.String email;
    /**
     * QQ
     */
    @Excel(exportName = "QQ")
    private java.lang.String qqNumber;
    /**
     * 群发次数
     */
    @Excel(exportName = "群发次数")
    private java.lang.Integer smsnum;
    /**
     * 图文次数
     */
    @Excel(exportName = "图文次数")
    private java.lang.Integer newsnum;
    /**
     * 请求次数
     */
    @Excel(exportName = "请求次数")
    private java.lang.Integer requestnum;
    /**
     * 员工账号个数
     */
    @Excel(exportName = "员工账号个数")
    private java.lang.Integer usernum;
    /**
     * 公众号个数
     */
    @Excel(exportName = "公众号个数")
    private java.lang.Integer accountnum;
    /**
     * 域名地址
     */
    @Excel(exportName = "域名地址")
    private java.lang.String domainurl;

    /**
     * 商戶編碼
     */
    @Excel(exportName = "商戶編碼")
    private java.lang.String acctCode;

    /**
     * 所屬商戶
     */
    @Excel(exportName = "所屬商戶")
    private java.lang.String belogAcct;


    /**
     * 商戶類型
     */
    @Excel(exportName = "商戶類型")
    private java.lang.String businessType;

    /**
     * 是否广告代理商
     * */
    private String adAgency;
    /**
     * 是否出让广告位
     * */
    private String sellAdpos;
    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 商业类型
     */
    @Column(name = "BUSINESS_TYPE", nullable = true, length = 30)
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 商业类型
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * 所屬省份
     */
    @Excel(exportName = "所屬省份")
    private java.lang.String province;
    /**
     * 所屬地市
     */
    @Excel(exportName = "所屬地市")
    private java.lang.String city;


    /**
     * 可開商戶數
     */
    @Excel(exportName = "可開商戶數")
    private java.lang.Integer totalAccount;

    /**
     * 已開商戶數
     */
    @Excel(exportName = "已開商戶數")
    private java.lang.Integer opendedAccount;

    /**
     * 商戶狀態
     */
    @Excel(exportName = "商戶狀態")
    private java.lang.String status;

    /**
     * 流量类型
     */
    @Excel(exportName = "流量类型")
    private java.lang.String flowtype;

    /**
     * 商户级别
     */
    @Excel(exportName = "商户级别")
    private java.lang.String acctLevel;


    /**
     * 商户名称
     */
    @Excel(exportName = "真正的商户名称")
    private java.lang.String acctForName;

    private java.lang.String callPhone;


    @Column(name = "CALLPHONE", nullable = true, length = 30)
    public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone;
    }



    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 真正的商户名称
     */
    @Column(name = "ACCTFORNAME", nullable = true, length = 30)
    public String getAcctForName() {
        return acctForName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 真正的商户名称
     */
    public void setAcctForName(String acctForName) {
        this.acctForName = acctForName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 商户等级
     */
    @Column(name = "ACCT_LEVEL", nullable = true, length = 10)
    public String getAcctLevel() {
        return acctLevel;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 商户等级
     */
    public void setAcctLevel(String acctLevel) {
        this.acctLevel = acctLevel;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 流量类型
     */
    @Column(name = "FLOWTYPE", nullable = true, length = 30)
    public String getFlowtype() {
        return flowtype;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 流量类型
     */
    public void setFlowtype(String flowtype) {
        this.flowtype = flowtype;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 商戶編碼
     */
    @Column(name = "ACCT_CODE", nullable = true, length = 20)
    public String getAcctCode() {
        return acctCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 商戶編碼
     */
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 所屬商戶
     */
    @Column(name = "BELOGACCT", nullable = true, length = 20)
    public String getBelogAcct() {
        return belogAcct;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 所屬商戶
     */
    public void setBelogAcct(String belogAcct) {
        this.belogAcct = belogAcct;
    }

    /**
     * pid
     */
    @Excel(exportName = "pid")
    private java.lang.String pid;
    /**
     * 是否加入
     */
    @Excel(exportName = "joinOrNot")
    private java.lang.String joinOrNot;

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 是否加入组标识
     */
    @Column(name = "JOINORNOT", nullable = true, length = 2)
    public String getJoinOrNot() {
        return joinOrNot;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 是否加入组标识
     */
    public void setJoinOrNot(String joinOrNot) {
        this.joinOrNot = joinOrNot;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 父类
     */
    @Column(name = "PID", nullable = true, length = 36)
    public String getPid() {
        return pid;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return 方法: 取得java.lang.String 父类
     */
    public void setPid(String pid) {
        this.pid = pid;
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
    public java.lang.String getId() {
        return this.id;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  主键
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  创建日期
     */
    @Column(name = "CREATE_DATE", nullable = true, length = 20)
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  创建日期
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  修改日期
     */
    @Column(name = "END_DATE", nullable = true, length = 20)
    public java.util.Date getEndDate() {
        return this.endDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  修改日期
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  商户名称
     */
    @Column(name = "ACCT_NAME", nullable = true, length = 32)
    public java.lang.String getAcctName() {
        return this.acctName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  商户名称
     */
    public void setAcctName(java.lang.String acctName) {
        this.acctName = acctName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  电话
     */
    @Column(name = "MOBILE_PHONE", nullable = true, length = 32)
    public java.lang.String getMobilePhone() {
        return this.mobilePhone;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  电话
     */
    public void setMobilePhone(java.lang.String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  邮箱
     */
    @Column(name = "EMAIL", nullable = true, length = 32)
    public java.lang.String getEmail() {
        return this.email;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  邮箱
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  QQ
     */
    @Column(name = "QQ_NUMBER", nullable = true, length = 32)
    public java.lang.String getQqNumber() {
        return this.qqNumber;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  QQ
     */
    public void setQqNumber(java.lang.String qqNumber) {
        this.qqNumber = qqNumber;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  群发次数
     */
    @Column(name = "SMSNUM", nullable = true, length = 8)
    public java.lang.Integer getSmsnum() {
        return this.smsnum;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  群发次数
     */
    public void setSmsnum(java.lang.Integer smsnum) {
        this.smsnum = smsnum;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  图文次数
     */
    @Column(name = "NEWSNUM", nullable = true, length = 8)
    public java.lang.Integer getNewsnum() {
        return this.newsnum;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  图文次数
     */
    public void setNewsnum(java.lang.Integer newsnum) {
        this.newsnum = newsnum;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  请求次数
     */
    @Column(name = "REQUESTNUM", nullable = true, length = 8)
    public java.lang.Integer getRequestnum() {
        return this.requestnum;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  请求次数
     */
    public void setRequestnum(java.lang.Integer requestnum) {
        this.requestnum = requestnum;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  员工账号个数
     */
    @Column(name = "USERNUM", nullable = true, length = 8)
    public java.lang.Integer getUsernum() {
        return this.usernum;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  员工账号个数
     */
    public void setUsernum(java.lang.Integer usernum) {
        this.usernum = usernum;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  公众号个数
     */
    @Column(name = "ACCOUNTNUM", nullable = true, length = 8)
    public java.lang.Integer getAccountnum() {
        return this.accountnum;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  公众号个数
     */
    public void setAccountnum(java.lang.Integer accountnum) {
        this.accountnum = accountnum;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  域名地址
     */
    @Column(name = "DOMAINURL", nullable = true, length = 32)
    public java.lang.String getDomainurl() {
        return this.domainurl;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  域名地址
     */
    public void setDomainurl(java.lang.String domainurl) {
        this.domainurl = domainurl;
    }

//    /**
//     * 方法: 取得java.lang.String
//     *
//     * @return: java.lang.String  商業類型
//     */
//    @Column(name = "BUSINESS_TYPE", nullable = true, length = 30)
//    public String getBusinessType() {
//        return this.businessType;
//    }
//
//    /**
//     * 方法: 取得java.lang.String
//     *
//     * @return: java.lang.String  商業類型
//     */
//    public void setBusinessType(java.lang.String businessType) {
//        this.businessType = businessType;
//    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  所属省份
     */
    @Column(name = "PROVINCE", nullable = true, length = 30)
    public String getProvince() {
        return province;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  所属省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  所属地市
     */
    @Column(name = "CITY", nullable = true, length = 30)
    public String getCity() {
        return city;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  所属地市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  可开商户数
     */
    @Column(name = "TOTALACCOUNT", nullable = true, length = 11)
    public Integer getTotalAccount() {
        return totalAccount;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  可开商户数
     */
    public void setTotalAccount(Integer totalAccount) {
        this.totalAccount = totalAccount;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  已开商户数
     */
    @Column(name = "OPENDEDACCOUNT", nullable = true, length = 11)
    public Integer getOpendedAccount() {
        return opendedAccount;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  已开商户数
     */
    public void setOpendedAccount(Integer opendedAccount) {
        this.opendedAccount = opendedAccount;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  商户状态
     */
    @Column(name = "STATUS", nullable = true, length = 30)
    public String getStatus() {
        return status;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  商户状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  企业名称
     */
    @Column(name = "BUSINESSNAME", nullable = true, length = 20)
    public String getBusinessName() {
        return businessName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  企业名称
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    
    @Column(name = "ad_agency", nullable = true, length = 20)
    public String getAdAgency() {
        return adAgency;
    }

    public void setAdAgency(String adAgency) {
        this.adAgency = adAgency;
    }
    
    @Column(name = "sell_adpos", nullable = true, length = 20)
    public String getSellAdpos() {
        return sellAdpos;
    }

    public void setSellAdpos(String sellAdpos) {
        this.sellAdpos = sellAdpos;
    }
}
