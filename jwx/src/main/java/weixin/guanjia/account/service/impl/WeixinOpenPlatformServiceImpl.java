package weixin.guanjia.account.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jodd.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.xml.sax.helpers.ParserAdapter;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinOpenPlatformEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.service.WeixinOpenPlatformServiceI;
import weixin.guanjia.core.util.WeixinUtil;

@Service("weixinOpenPlatformService")
@Transactional
public class WeixinOpenPlatformServiceImpl extends CommonServiceImpl implements
        WeixinOpenPlatformServiceI {

	private static final Logger LOGGER = Logger.getLogger(WeixinOpenPlatformServiceImpl.class);

    private static final String API_COMPONENT_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
    
    private static final String API_CREATE_PREAUTHCODE = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=COMPONENT_ACCESS_TOKEN";
    
    @Override
    public <T> void delete(T entity) {
        super.delete(entity);
    }

    @Override
    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        return t;
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
    }
    


    @Override
    public String getComponentAccessToken(String platformId) {
        String ret = "";
        if (StringUtils.isBlank(platformId)) {
            return ret;
        }
        
        WeixinOpenPlatformEntity entity = this.get(WeixinOpenPlatformEntity.class, platformId);
        Date now = new Date();
        if (StringUtils.isNotBlank(entity.getComponentAccessToken()) && entity.getTokenExpireTime().after(now)) {
            ret = entity.getComponentAccessToken();
        } else {
            JSONObject postData = new JSONObject();
            postData.put("component_appid", entity.getAppId());
            postData.put("component_appsecret", entity.getAppSecret());
            postData.put("component_verify_ticket", entity.getComponentVerifyTicket());
            LOGGER.info("获取component_token,参数列表如下:" + postData);
            JSONObject obj = WeixinUtil.httpRequest(API_COMPONENT_TOKEN, "POST", postData.toString());
            if (obj.containsKey("component_access_token")) {
                String token = obj.getString("component_access_token");
                Integer expiresIn = obj.getInt("expires_in");
                ret = token;
                Date expireTime = addTime(new Date(), expiresIn, Calendar.SECOND);
                entity.setComponentAccessToken(token);
                entity.setTokenExpireTime(expireTime);
                this.save(entity);
            } else {
                LOGGER.info(String.format("获取component_access_token失败. %s", obj.toString()));
            }
        }
        
        return ret;
    }

    @Override
    public String getComponentVerifyTicket(String platformId) {
        String ret = "";
        if (StringUtils.isBlank(platformId)) {
            return ret;
        }
        
        WeixinOpenPlatformEntity entity = this.get(WeixinOpenPlatformEntity.class, platformId);
        if (StringUtils.isNotBlank(entity.getComponentVerifyTicket())) {
            ret = entity.getComponentVerifyTicket();
        }
        
        return ret;
    }

    @Override
    public String getPreAuthCode(String platformId) {
        String ret = "";
        if (StringUtils.isBlank(platformId)) {
            return ret;
        }
        
        WeixinOpenPlatformEntity entity = this.get(WeixinOpenPlatformEntity.class, platformId);
        String token = this.getComponentAccessToken(platformId);
        if (StringUtils.isNotBlank(token)) {
            String appId = entity.getAppId();
            String requestUrl = API_CREATE_PREAUTHCODE.replace("COMPONENT_ACCESS_TOKEN", token);
            JSONObject postData = new JSONObject();
            postData.put("component_appid", appId);
            JSONObject obj = WeixinUtil.httpRequest(requestUrl, "POST", postData.toString());
            if (obj.containsKey("pre_auth_code")) {
                String authCode = obj.getString("pre_auth_code");
                Integer expiresIn = obj.getInt("expires_in");
                Date expireTime = addTime(new Date(), expiresIn, Calendar.SECOND);
                entity.setPreAuthCode(authCode);
                entity.setAuthCodeExpireTime(expireTime);
                this.saveOrUpdate(entity);
                ret = authCode;
            } else {
                LOGGER.info(String.format("获取pre_auth_code失败. %s", obj.toString()));
            }
        } else {
            LOGGER.info(String.format("component_access_token为空. platformId:%s", platformId));
        }
        
        return ret;
    }

    @Override
    public String getComponentAccessToken() {
        String ret = "";
        WeixinAccountEntity account = findLoginWeixinAccount();
        if (account.getId().equalsIgnoreCase("-1")) {
            LOGGER.info("找不到当前登录微信公众号");
            return ret;
        } else {
            if (account.getAuthorizationType().equalsIgnoreCase("1") || account.getAuthorizationType().equalsIgnoreCase("2")) {
                String platformId = account.getOpenPlatformId();
                ret = this.getComponentAccessToken(platformId);
            } else {
                LOGGER.info(String.format("公众号未采用第三方授权. id:%s, authorizationType:%s, accountId:%s", account.getId(), account.getAuthorizationType(), account.getWeixin_accountid()));
            }
        }
        
        return ret;
    }

    @Override
    public String getComponentVerifyTicket() {
        String ret = "";
        WeixinAccountEntity account = findLoginWeixinAccount();
        if (account.getId().equalsIgnoreCase("-1")) {
            LOGGER.info("找不到当前登录微信公众号");
            return ret;
        } else {
            if (account.getAuthorizationType().equalsIgnoreCase("1") || account.getAuthorizationType().equalsIgnoreCase("2")) {
                String platformId = account.getOpenPlatformId();
                ret = this.getComponentVerifyTicket(platformId);
            } else {
                LOGGER.info(String.format("公众号未采用第三方授权. id:%s, authorizationType:%s, accountId:%s", account.getId(), account.getAuthorizationType(), account.getWeixin_accountid()));
            }
        }
        
        return ret;   
    }

    @Override
    public String getPreAuthCode() {
        String ret = "";
        WeixinAccountEntity account = findLoginWeixinAccount();
        if (account.getId().equalsIgnoreCase("-1")) {
            LOGGER.info("找不到当前登录微信公众号");
            return ret;
        } else {
        	if (account.getAuthorizationType().equalsIgnoreCase("1") || account.getAuthorizationType().equalsIgnoreCase("2")) {
                String platformId = account.getOpenPlatformId();
                ret = this.getPreAuthCode(platformId);
            } else {
                LOGGER.info(String.format("公众号未采用第三方授权. id:%s, authorizationType:%s, accountId:%s", account.getId(), account.getAuthorizationType(), account.getWeixin_accountid()));
            }
        }
        
        return ret;
    }
    
    @Override
    public String getComponentValidateToken(String platformId) {
        String ret = "";
        if (StringUtils.isBlank(platformId)) {
            return ret;
        }
        
        WeixinOpenPlatformEntity entity = this.get(WeixinOpenPlatformEntity.class, platformId);
        if (StringUtils.isNotBlank(entity.getComponentValidateToken())) {
            ret = entity.getComponentValidateToken();
        }
        
        return ret;
    }

    @Override
    public String getComponentSymmetricKey(String platformId) {
        String ret = "";
        if (StringUtils.isBlank(platformId)) {
            return ret;
        }
        
        WeixinOpenPlatformEntity entity = this.get(WeixinOpenPlatformEntity.class, platformId);
        if (StringUtils.isNotBlank(entity.getComponentSymmetricKey())) {
            ret = entity.getComponentSymmetricKey();
        }
        
        return ret;
    }

    @Override
    public String getComponentValidateToken() {
        String ret = "";
        WeixinAccountEntity account = findLoginWeixinAccount();
        if (account.getId().equalsIgnoreCase("-1")) {
            LOGGER.info("找不到当前登录微信公众号");
            return ret;
        } else {
        	if (account.getAuthorizationType().equalsIgnoreCase("1") || account.getAuthorizationType().equalsIgnoreCase("2")) {
                String platformId = account.getOpenPlatformId();
                ret = this.getComponentValidateToken(platformId);
            } else {
                LOGGER.info(String.format("公众号未采用第三方授权. id:%s, authorizationType:%s, accountId:%s", account.getId(), account.getAuthorizationType(), account.getWeixin_accountid()));
            }
        }
        
        return ret;
    }

    @Override
    public String getComponentSymmetricKey() {
        String ret = "";
        WeixinAccountEntity account = findLoginWeixinAccount();
        if (account.getId().equalsIgnoreCase("-1")) {
            LOGGER.info("找不到当前登录微信公众号");
            return ret;
        } else {
        	if (account.getAuthorizationType().equalsIgnoreCase("1") || account.getAuthorizationType().equalsIgnoreCase("2")) {
                String platformId = account.getOpenPlatformId();
                ret = this.getComponentSymmetricKey(platformId);
            } else {
                LOGGER.info(String.format("公众号未采用第三方授权. id:%s, authorizationType:%s, accountId:%s", account.getId(), account.getAuthorizationType(), account.getWeixin_accountid()));
            }
        }
        
        return ret;
    }
    
    @Override
	public WeixinOpenPlatformEntity findByAppId(String appId) {
		return this.findUniqueByProperty(WeixinOpenPlatformEntity.class, "appId", appId);
	}
    
	@Override
	public void updateComponentVerifyTicket(String appId, String componentVerifyTicket, Date updateTime) {
		WeixinOpenPlatformEntity platform = this.findByAppId(appId);
		platform.setComponentVerifyTicket(componentVerifyTicket);
		platform.setTicketUpdateTime(updateTime);
		this.saveOrUpdate(platform);
	}
    
    private WeixinAccountEntity findLoginWeixinAccount() {        
        //获取当前公众号ID
        String accountId = ResourceUtil.getWeiXinAccountId();
        if(StringUtil.isEmpty(accountId)){
            
            TSUser user = ResourceUtil.getSessionUserName();
            accountId = user.getAccountid();
        }
                
        WeixinAccountEntity weixinAccountEntity = this.getEntity(WeixinAccountEntity.class, accountId);                
        if (weixinAccountEntity != null) {    
            return weixinAccountEntity;
        } else {
            weixinAccountEntity = new WeixinAccountEntity();
            // 返回个临时对象，防止空指针
            weixinAccountEntity.setWeixin_accountid("-1");
            weixinAccountEntity.setId("-1");
            return weixinAccountEntity;
        }
    }
    
    private Date addTime(Date current, int value, int unit) {
        Calendar c = Calendar.getInstance();
        c.setTime(current);
        c.add(unit, value);
        return c.getTime();
    }
}
