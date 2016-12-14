package weixin.guanjia.account.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "weixin_open_platform", schema = "")
@SuppressWarnings("serial")
public class WeixinOpenPlatformEntity implements java.io.Serializable {
    
    private String id;    

    private String appId;

    private String appSecret;

    private String componentAccessToken;
    
    private String componentVerifyTicket;
    
    private String preAuthCode;
    
    private String componentValidateToken;
    
    private String componentSymmetricKey;
    
    private Date tokenExpireTime;
    
    private Date ticketUpdateTime;
    
    private Date authCodeExpireTime;
    
    private String authorizationType;
    
    
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="ID",nullable=false,length=36)
    public String getId(){
        return this.id;
    }

    public void setId(java.lang.String id){
        this.id = id;
    }

    @Column(name ="app_id",nullable=true,length=36)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Column(name ="app_secret",nullable=true,length=255)
    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    @Column(name ="component_access_token",nullable=true,length=255)
    public String getComponentAccessToken() {
        return componentAccessToken;
    }

    public void setComponentAccessToken(String componentAccessToken) {
        this.componentAccessToken = componentAccessToken;
    }

    @Column(name ="component_verify_ticket",nullable=true,length=255)
    public String getComponentVerifyTicket() {
        return componentVerifyTicket;
    }

    public void setComponentVerifyTicket(String componentVerifyTicket) {
        this.componentVerifyTicket = componentVerifyTicket;
    }

    @Column(name ="pre_auth_code",nullable=true,length=255)
    public String getPreAuthCode() {
        return preAuthCode;
    }

    public void setPreAuthCode(String preAuthCode) {
        this.preAuthCode = preAuthCode;
    }

    @Column(name ="token_expire_time",nullable=true)
    public Date getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Date tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    @Column(name ="ticket_update_time",nullable=true)
    public Date getTicketUpdateTime() {
        return ticketUpdateTime;
    }

    public void setTicketUpdateTime(Date ticketUpdateTime) {
        this.ticketUpdateTime = ticketUpdateTime;
    }

    @Column(name ="auth_code_expire_time",nullable=true)
    public Date getAuthCodeExpireTime() {
        return authCodeExpireTime;
    }

    public void setAuthCodeExpireTime(Date authCodeExpireTime) {
        this.authCodeExpireTime = authCodeExpireTime;
    }

    @Column(name ="component_validate_token",nullable=true,length=255)
    public String getComponentValidateToken() {
        return componentValidateToken;
    }

    public void setComponentValidateToken(String componentValidateToken) {
        this.componentValidateToken = componentValidateToken;
    }

    @Column(name ="component_symmetric_key",nullable=true,length=255)
    public String getComponentSymmetricKey() {
        return componentSymmetricKey;
    }

    public void setComponentSymmetricKey(String componentSymmetricKey) {
        this.componentSymmetricKey = componentSymmetricKey;
    }

    @Column(name ="authorization_type",nullable=true,length=20)
    public String getAuthorizationType() {
        return authorizationType;
    }

    public void setAuthorizationType(String authorizationType) {
        this.authorizationType = authorizationType;
    }

}
