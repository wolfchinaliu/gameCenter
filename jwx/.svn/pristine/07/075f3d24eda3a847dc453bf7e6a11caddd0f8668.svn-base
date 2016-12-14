package weixin.guanjia.account.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import sun.font.TrueTypeFont;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 微信公众帐号信息
 * @date 2014-05-21 00:53:47
 */
@Entity
@Table(name = "weixin_account", schema = "")
@SuppressWarnings("serial")
public class WeixinAccountEntity implements java.io.Serializable {

    /**
     * 主键
     */
    private java.lang.String id;

    /**
     * 公众帐号名称
     */
    private java.lang.String accountname;
    /**
     * 公众帐号TOKEN
     */
    private java.lang.String accounttoken;
    /**
     * 公众微信号
     */
    private java.lang.String accountnumber;
    /**
     * 公众原始ID
     */
    private java.lang.String weixin_accountid;
    /**
     * 公众号类型
     */
    private java.lang.String accounttype;
    /**
     * 电子邮箱
     */
    private java.lang.String accountemail;
    /**
     * 公众帐号描述
     */
    private java.lang.String accountdesc;
    /**
     * 公众帐号APPID
     */
    private java.lang.String accountappid;
    /**
     * 公众帐号APPSECRET
     */
    private java.lang.String accountappsecret;
    /**
     * ACCESS_TOKEN
     */
    private java.lang.String accountaccesstoken;
    /**
     * TOKEN获取时间
     */
    private java.util.Date addtoekntime;
    /**
     * 所属系统用户
     **/
    private java.lang.String userName;

    private String pid;//上级公众号ID

    private Integer level;//公众号级别，0:顶级

    private Integer autoId;

    private String QRcode;
    private String authorizationType;

    private String authorizerAccessToken;

    private String authorizerRefreshToken;

    private String authorizationCode;

    private Date authorizerAccessTokenExpireTime;

    private String openPlatformId;

    private Date authorizationCodeExpireTime;

    private Date openPlatformAuthTime;

    private Date unauthorizeTime;

    private java.lang.String logoAccount;

    private String jsapiTicket;  //jsapi_ticket
    private Date jsapiTicketTime; //

    @Column(name = "jsapi_ticket", nullable = true, length = 255)
    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }

    @Column(name = "jsapi_ticket_time", nullable = true, length = 20)
    public Date getJsapiTicketTime() {
        return jsapiTicketTime;
    }

    public void setJsapiTicketTime(Date jsapiTicketTime) {
        this.jsapiTicketTime = jsapiTicketTime;
    }
    @Column(name = "LOGOACCOUNT", nullable = true, length = 20)
    public String getLogoAccount() {
        return logoAccount;
    }

    public void setLogoAccount(String logoAccount) {
        this.logoAccount = logoAccount;
    }

    /**
     * 租户id(商户id)
     */
    private java.lang.String acctId;
    /**
     * 微信公众账号编码
     */
    private java.lang.String accountCode;

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  微信公众账号编码
     */
    @Column(name = "ACCOUNT_CODE", nullable = true, length = 20)
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  微信公众账号编码
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String   租户id(商户id)
     */
    @Column(name = "ACCT_ID", nullable = true, length = 36)
    public String getAcctId() {
        return acctId;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String   租户id(商户id)
     */
    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    @Column(name = "QRcode", nullable = true, length = 1000)
    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
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
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  公众帐号名称
     */
    @Column(name = "ACCOUNTNAME", nullable = true, length = 200)
    public java.lang.String getAccountname() {
        return this.accountname;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  公众帐号名称
     */
    public void setAccountname(java.lang.String accountname) {
        this.accountname = accountname;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  公众帐号TOKEN
     */
    @Column(name = "ACCOUNTTOKEN", nullable = true, length = 200)
    public java.lang.String getAccounttoken() {
        return this.accounttoken;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  公众帐号TOKEN
     */
    public void setAccounttoken(java.lang.String accounttoken) {
        this.accounttoken = accounttoken;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  公众微信号
     */
    @Column(name = "ACCOUNTNUMBER", nullable = true, length = 200)
    public java.lang.String getAccountnumber() {
        return this.accountnumber;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  公众微信号
     */
    public void setAccountnumber(java.lang.String accountnumber) {
        this.accountnumber = accountnumber;
    }

    @Column(name = "weixin_accountid", nullable = true, length = 50)
    public java.lang.String getWeixin_accountid() {
        return weixin_accountid;
    }

    public void setWeixin_accountid(java.lang.String weixin_accountid) {
        this.weixin_accountid = weixin_accountid;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  公众号类型
     */
    @Column(name = "ACCOUNTTYPE", nullable = true, length = 50)
    public java.lang.String getAccounttype() {
        return this.accounttype;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  公众号类型
     */
    public void setAccounttype(java.lang.String accounttype) {
        this.accounttype = accounttype;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  电子邮箱
     */
    @Column(name = "ACCOUNTEMAIL", nullable = true, length = 200)
    public java.lang.String getAccountemail() {
        return this.accountemail;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  电子邮箱
     */
    public void setAccountemail(java.lang.String accountemail) {
        this.accountemail = accountemail;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  公众帐号描述
     */
    @Column(name = "ACCOUNTDESC", nullable = true, length = 500)
    public java.lang.String getAccountdesc() {
        return this.accountdesc;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  公众帐号描述
     */
    public void setAccountdesc(java.lang.String accountdesc) {
        this.accountdesc = accountdesc;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  公众帐号APPID
     */
    @Column(name = "ACCOUNTAPPID", nullable = true, length = 200)
    public java.lang.String getAccountappid() {
        return this.accountappid;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  公众帐号APPID
     */
    public void setAccountappid(java.lang.String accountappid) {
        this.accountappid = accountappid;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  公众帐号APPSECRET
     */
    @Column(name = "ACCOUNTAPPSECRET", nullable = true, length = 500)
    public java.lang.String getAccountappsecret() {
        return this.accountappsecret;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  公众帐号APPSECRET
     */
    public void setAccountappsecret(java.lang.String accountappsecret) {
        this.accountappsecret = accountappsecret;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  ACCESS_TOKEN
     */
    @Column(name = "ACCOUNTACCESSTOKEN", nullable = true, length = 1000)
    public java.lang.String getAccountaccesstoken() {
        return this.accountaccesstoken;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  ACCESS_TOKEN
     */
    public void setAccountaccesstoken(java.lang.String accountaccesstoken) {
        this.accountaccesstoken = accountaccesstoken;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  TOKEN获取时间
     */
    @Column(name = "ADDTOEKNTIME", nullable = true, length = 100)
    public java.util.Date getAddtoekntime() {
        return this.addtoekntime;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  TOKEN获取时间
     */
    public void setAddtoekntime(java.util.Date addtoekntime) {
        this.addtoekntime = addtoekntime;
    }

    @Column(name = "USERNAME", nullable = true, length = 50)
    public java.lang.String getUserName() {
        return userName;
    }

    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    @Column(name = "pid", length = 36)
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Column(name = "level", length = 1)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "authorization_type", nullable = true, length = 255)
    public String getAuthorizationType() {
        return authorizationType;
    }

    public void setAuthorizationType(String authorizationType) {
        this.authorizationType = authorizationType;
    }

    @Column(name = "authorizer_access_token", nullable = true, length = 255)
    public String getAuthorizerAccessToken() {
        return authorizerAccessToken;
    }

    public void setAuthorizerAccessToken(String authorizerAccessToken) {
        this.authorizerAccessToken = authorizerAccessToken;
    }

    @Column(name = "authorizer_refresh_token", nullable = true, length = 255)
    public String getAuthorizerRefreshToken() {
        return authorizerRefreshToken;
    }

    public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
        this.authorizerRefreshToken = authorizerRefreshToken;
    }

    @Column(name = "authorization_code", nullable = true, length = 255)
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    @Column(name = "authorizer_access_token_expire_time", nullable = true)
    public Date getAuthorizerAccessTokenExpireTime() {
        return authorizerAccessTokenExpireTime;
    }

    public void setAuthorizerAccessTokenExpireTime(Date authorizerAccessTokenExpireTime) {
        this.authorizerAccessTokenExpireTime = authorizerAccessTokenExpireTime;
    }

    @Column(name = "open_platform_id", nullable = true, length = 255)
    public String getOpenPlatformId() {
        return openPlatformId;
    }

    public void setOpenPlatformId(String openPlatformId) {
        this.openPlatformId = openPlatformId;
    }

    @Column(name = "authorization_code_expire_time", nullable = true)
    public Date getAuthorizationCodeExpireTime() {
        return authorizationCodeExpireTime;
    }

    public void setAuthorizationCodeExpireTime(Date authorizationCodeExpireTime) {
        this.authorizationCodeExpireTime = authorizationCodeExpireTime;
    }

    @Column(name = "open_platform_auth_time", nullable = true)
    public Date getOpenPlatformAuthTime() {
        return openPlatformAuthTime;
    }

    public void setOpenPlatformAuthTime(Date openPlatformAuthTime) {
        this.openPlatformAuthTime = openPlatformAuthTime;
    }

    @Column(name = "unauthorize_time", nullable = true)
    public Date getUnauthorizeTime() {
        return unauthorizeTime;
    }

    public void setUnauthorizeTime(Date unauthorizeTime) {
        this.unauthorizeTime = unauthorizeTime;
    }



    ///////////////////////xiaoguai add account secretkey
    /**
     * 微信公众账号编码
     */
    private java.lang.String accountIdentifyKey;

    @Column(name = "accountIdentifyKey", nullable = true)
    public String getAccountIdentifyKey() {
        return accountIdentifyKey;
    }

    public void setAccountIdentifyKey(String accountIdentifyKey) {
        this.accountIdentifyKey = accountIdentifyKey;
    }
}
