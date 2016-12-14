package weixin.guanjia.account.service.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import jodd.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Where;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinIndividualizationEntity;
import weixin.guanjia.account.entity.WeixinOpenPlatformEntity;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.service.WeixinOpenPlatformServiceI;
import weixin.guanjia.core.util.PageAuthRedisCache;
import weixin.guanjia.core.util.SignUtil;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.openplatform.controller.WeixinOpenPlatformController;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.util.CommonUtils;

import static weixin.guanjia.openplatform.controller.WeixinOpenPlatformController.*;

@Service("weixinAccountService")
@Transactional
public class WeixinAccountServiceImpl extends CommonServiceImpl implements
        WeixinAccountServiceI {
    
    private static final Logger LOGGER = Logger.getLogger(WeixinAccountServiceImpl.class);
    
    private static final String API_QUERY_AUTH = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=COMPONENT_ACCESS_TOKEN";
    
    private static final String API_AUTHORIZER_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=COMPONENT_ACCESS_TOKEN";
    
    private static final String API_GET_AUTHORIZER_INFO = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=COMPONENT_ACCESS_TOKEN";
    
    
    @Autowired
    private WeixinOpenPlatformServiceI weixinOpenPlatformService;
    
    @Autowired
    private WeixinMemberServiceI weixinMemberService;

    @Autowired
    private SystemService systemService;
    
    @Override
    public <T> void delete(T entity) {
        super.delete(entity);
        // 执行删除操作配置的sql增强
        this.doDelSql((WeixinAccountEntity) entity);
    }

    @Override
    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        // 执行新增操作配置的sql增强
        this.doAddSql((WeixinAccountEntity) entity);
        return t;
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        // 执行更新操作配置的sql增强
        this.doUpdateSql((WeixinAccountEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     * 
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinAccountEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     * 
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinAccountEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     * 
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinAccountEntity t) {
        return true;
    }
    
    
    public WeixinIndividualizationEntity getIndividualization(String acctId){
//    	String hql = "from WeixinIndividualizationEntity where accountid=?";
//    	List<WeixinIndividualizationEntity> weixinIndividualization = this.systemService.findHql(hql,accountid);
    	WeixinIndividualizationEntity weixinIndividualization = this.systemService.findUniqueByProperty(WeixinIndividualizationEntity.class, "acctId", acctId);
    	WeixinAcctEntity weixinAcct = this.systemService.findUniqueByProperty(WeixinAcctEntity.class, "id", acctId);
    	if(weixinIndividualization != null || "0".equals(weixinAcct.getAcctLevel())){
    		if(weixinIndividualization==null){
    			return null;
    		}
    		return weixinIndividualization;
    	}else{
//    		String hql1 = "from WeixinAcctEntity where id=?";
//    		List<WeixinAcctEntity> weixinAcct = this.systemService.findHql(hql1,accountid);
    		String pid = weixinAcct.getPid();
    		if(pid == null)
    		{
    			return null;
    		}
    		return getIndividualization(pid);
    	}
    }
    
    /**
     * 登陆页面　个性化logo查询
     * @param id
     * @return
     */
    public WeixinIndividualizationEntity getIndividualizationLogo(String id){

    	WeixinIndividualizationEntity weixinIndividualization = this.systemService.findUniqueByProperty(WeixinIndividualizationEntity.class, "id", id);
    	return(weixinIndividualization);
    }

    public String getAccessToken() {
        String token = "";
        WeixinAccountEntity account = findLoginWeixinAccount();
        if (!account.getAuthorizationType().equalsIgnoreCase("0")) {
        	return this.getAuthorizerAccessToken(account.getId());
        }
        
        token = account.getAccountaccesstoken();
        if (token != null && !"".equals(token)) {
            // 判断有效时间 是否超过2小时
            java.util.Date end = new java.util.Date();
            java.util.Date start = new java.util.Date(account.getAddtoekntime()
                    .getTime());
            if ((end.getTime() - start.getTime()) / 1000 / 3600 >= 2) {
                // 失效 重新获取
                String requestUrl = WeixinUtil.access_token_url.replace(
                        "APPID", account.getAccountappid()).replace(
                        "APPSECRET", account.getAccountappsecret());
                JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,
                        "GET", null);
                if (null != jsonObject) {
                    try {
                        token = jsonObject.getString("access_token");
                        // 重置token
                        account.setAccountaccesstoken(token);
                        // 重置事件
                        account.setAddtoekntime(new Date());
                        this.saveOrUpdate(account);
                    } catch (Exception e) {
                        token = null;
                        // 获取token失败
                        String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
                                + jsonObject.getInt("errcode")
                                + jsonObject.getString("errmsg");
                    }
                }
            } else {
                return account.getAccountaccesstoken();
            }
        } else {
            String requestUrl = WeixinUtil.access_token_url.replace("APPID",
                    account.getAccountappid()).replace("APPSECRET",
                    account.getAccountappsecret());
            JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET",
                    null);
            if (null != jsonObject) {
                try {
                    token = jsonObject.getString("access_token");
                    // 重置token
                    account.setAccountaccesstoken(token);
                    // 重置事件
                    account.setAddtoekntime(new Date());
                    this.saveOrUpdate(account);
                } catch (Exception e) {
                    token = null;
                    // 获取token失败
                    String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
                            + jsonObject.getInt("errcode")
                            + jsonObject.getString("errmsg");
                }
            }
        }
        return token;
    }
    
    
    public String getAccessToken(String id) {
        
        // WeixinAccountEntity weixinAccountEntity = this.findUniqueByProperty(WeixinAccountEntity.class, "weixin_accountid", id);
        WeixinAccountEntity weixinAccountEntity = this.getEntity(WeixinAccountEntity.class, id);
        if (!weixinAccountEntity.getAuthorizationType().equalsIgnoreCase("0")) {
        	return this.getAuthorizerAccessToken(weixinAccountEntity.getId());
        }
        
        String token = weixinAccountEntity.getAccountaccesstoken();
        if (token != null && !"".equals(token)) {
            // 判断有效时间 是否超过2小时
            java.util.Date end = new java.util.Date();
            java.util.Date start = new java.util.Date(weixinAccountEntity.getAddtoekntime()
                    .getTime());
            if ((end.getTime() - start.getTime()) / 1000 / 3600 >= 2) {
                // 失效 重新获取
                String requestUrl = WeixinUtil.access_token_url.replace(
                        "APPID", weixinAccountEntity.getAccountappid()).replace(
                        "APPSECRET", weixinAccountEntity.getAccountappsecret());
                JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,
                        "GET", null);
                if (null != jsonObject) {
                    try {
                        token = jsonObject.getString("access_token");
                        // 重置token
                        weixinAccountEntity.setAccountaccesstoken(token);
                        // 重置事件
                        weixinAccountEntity.setAddtoekntime(new Date());
                        this.saveOrUpdate(weixinAccountEntity);
                    } catch (Exception e) {
                        token = null;
                        // 获取token失败
                        String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
                                + jsonObject.getInt("errcode")
                                + jsonObject.getString("errmsg");
                    }
                }
            } else {
                return weixinAccountEntity.getAccountaccesstoken();
            }
        } else {
            String requestUrl = WeixinUtil.access_token_url.replace("APPID",
                    weixinAccountEntity.getAccountappid()).replace("APPSECRET",
                            weixinAccountEntity.getAccountappsecret());
            JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET",
                    null);
            if (null != jsonObject) {
                try {
                    token = jsonObject.getString("access_token");
                    // 重置token
                    weixinAccountEntity.setAccountaccesstoken(token);
                    // 重置事件
                    weixinAccountEntity.setAddtoekntime(new Date());
                    this.saveOrUpdate(weixinAccountEntity);
                } catch (Exception e) {
                    token = null;
                    // 获取token失败
                    String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
                            + jsonObject.getInt("errcode")
                            + jsonObject.getString("errmsg");
                }
            }
        }
        return token;
    }


    public String getSignature(String accountId){
        WeixinAccountEntity weixinAccountEntity = this.systemService.getEntity(WeixinAccountEntity.class,accountId);
        String ticket=weixinAccountEntity.getJsapiTicket();
        if (ticket != null && !"".equals(ticket)) {
            // 判断有效时间 是否超过2小时
            java.util.Date end = new java.util.Date();
            java.util.Date start = new java.util.Date(weixinAccountEntity.getJsapiTicketTime().getTime());
            if ((end.getTime() - start.getTime()) / 1000 / 3600 >= 2) {
                // 失效 重新获取
                String accesstoken=this.getAccessTokenByPrimaryKey(accountId);  //accesstoken
                String requestUrl=WeixinUtil.jsapi_ticket_url.replace("ACCESS_TOKEN",accesstoken);  //获取地址
                JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,"GET", null);  //请求微信服务器
                if (null != jsonObject) {
                    try {
                        ticket = jsonObject.getString("ticket");
                        // 重置ticket
                        weixinAccountEntity.setJsapiTicket(ticket);
                        // 重置时间
                        weixinAccountEntity.setJsapiTicketTime(end);
                        this.saveOrUpdate(weixinAccountEntity);
                    } catch (Exception e) {
                        ticket = null;
                        // 获取token失败
                        String wrongMessage = "获取jsticket失败 errcode:{} errmsg:{}"
                                + jsonObject.getInt("errcode")
                                + jsonObject.getString("errmsg");
                    }
                }
            }else{
                return weixinAccountEntity.getJsapiTicket();
            }
        }else{
            String accesstoken=this.getAccessTokenByPrimaryKey(accountId);  //accesstoken
            String requestUrl=WeixinUtil.jsapi_ticket_url.replace("ACCESS_TOKEN",accesstoken);  //获取地址
            JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,"GET", null);  //请求微信服务器
            if (null != jsonObject) {
                try {
                    ticket = jsonObject.getString("ticket");
                    // 重置ticket
                    weixinAccountEntity.setJsapiTicket(ticket);
                    // 重置时间
                    weixinAccountEntity.setJsapiTicketTime(new Date());
                    this.saveOrUpdate(weixinAccountEntity);
                } catch (Exception e) {
                    ticket = null;
                    // 获取token失败
                    String wrongMessage = "获取jsticket失败 errcode:{} errmsg:{}"
                            + jsonObject.getInt("errcode")
                            + jsonObject.getString("errmsg");
                }
            }
        }
        return ticket;
    }

    
    public String getAccessTokenByPrimaryKey(String id) {
        WeixinAccountEntity weixinAccountEntity =this.get(WeixinAccountEntity.class, id);
        if (!weixinAccountEntity.getAuthorizationType().equalsIgnoreCase("0")) {
        	return this.getAuthorizerAccessToken(id);
        }
        
        
        String token = weixinAccountEntity.getAccountaccesstoken();
        if (token != null && !"".equals(token)) {
            // 判断有效时间 是否超过2小时
            java.util.Date end = new java.util.Date();
            java.util.Date start = new java.util.Date(weixinAccountEntity.getAddtoekntime().getTime());
            if ((end.getTime() - start.getTime()) / 1000 / 3600 >= 2) {
                // 失效 重新获取
                String requestUrl = WeixinUtil.access_token_url.replace(
                        "APPID", weixinAccountEntity.getAccountappid()).replace(
                        "APPSECRET", weixinAccountEntity.getAccountappsecret());
                JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
                if (null != jsonObject) {
                    try {
                        token = jsonObject.getString("access_token");
                        // 重置token
                        weixinAccountEntity.setAccountaccesstoken(token);
                        // 重置事件
                        weixinAccountEntity.setAddtoekntime(new Date());
                        this.saveOrUpdate(weixinAccountEntity);
                    } catch (Exception e) {
                        token = null;
                        // 获取token失败
                        String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
                                + jsonObject.getInt("errcode")
                                + jsonObject.getString("errmsg");
                    }
                }
            } else {
                return weixinAccountEntity.getAccountaccesstoken();
            }
        } else {
            String requestUrl = WeixinUtil.access_token_url.replace("APPID",
                    weixinAccountEntity.getAccountappid()).replace("APPSECRET",
                            weixinAccountEntity.getAccountappsecret());
            JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET",
                    null);
            if (null != jsonObject) {
                try {
                    token = jsonObject.getString("access_token");
                    // 重置token
                    weixinAccountEntity.setAccountaccesstoken(token);
                    // 重置事件
                    weixinAccountEntity.setAddtoekntime(new Date());
                    this.saveOrUpdate(weixinAccountEntity);
                } catch (Exception e) {
                    token = null;
                    // 获取token失败
                    String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
                            + jsonObject.getInt("errcode")
                            + jsonObject.getString("errmsg");
                }
            }
        }
        return token;
    }


    public WeixinAccountEntity findLoginWeixinAccount() {
        
        //获取当前公众号ID
        String accountId = ResourceUtil.getWeiXinAccountId();
        if(StringUtil.isEmpty(accountId)){
            
            TSUser user = ResourceUtil.getSessionUserName();
            accountId = user.getAccountid();
        }
        
        //List<WeixinAccountEntity> acclst = this.findByProperty(WeixinAccountEntity.class, "userName", user.getUserName());
        
        WeixinAccountEntity weixinAccountEntity = this.getEntity(WeixinAccountEntity.class, accountId);
        
        //WeixinAccountEntity weixinAccountEntity = acclst.size() != 0 ? acclst.get(0) : null;
        
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

    public WeixinAcctEntity findLoginWeixinAcct() {
        
        //获取当前公众号ID
        String acctId = ResourceUtil.getWeiXinAcctId();
        if(StringUtil.isEmpty(acctId)){
            
            TSUser user = ResourceUtil.getSessionUserName();
            acctId = user.getTenantId();
        }
        
        //List<WeixinAccountEntity> acclst = this.findByProperty(WeixinAccountEntity.class, "userName", user.getUserName());
        
        WeixinAcctEntity acctEntity = this.getEntity(WeixinAcctEntity.class, acctId);
        
        //WeixinAccountEntity weixinAccountEntity = acclst.size() != 0 ? acclst.get(0) : null;
        
        if (acctEntity != null) {
            
            return acctEntity;
        } else {
            
            acctEntity = new WeixinAcctEntity();
            // 返回个临时对象，防止空指针
            acctEntity.setId("-1");
            return acctEntity;
        }
    }

    @Override
    public List<WeixinAccountEntity> findByUsername(String username) {
        List<WeixinAccountEntity> acclst = this.findByProperty(
                WeixinAccountEntity.class, "userName", username);
        return acclst;
    }

    @Override
    public WeixinAccountEntity findByToUsername(String toUserName) {
        return this.findUniqueByProperty(WeixinAccountEntity.class,
                "weixin_accountid", toUserName);

    }
    
    @Override
    public String getAuthorizerAccessToken() {
    	String ret = "";
        WeixinAccountEntity entity = findLoginWeixinAccount();
        if (entity.getId().equalsIgnoreCase("-1")) {
        	LOGGER.info("未找到当前登录微信账号");
        } else  {
        	ret = this.getAuthorizerAccessToken(entity.getId());
        }
        
        return ret;
    }

    @Override
    public String getAuthorizerAccessToken(String id) {
        String ret = "";
        if (StringUtils.isBlank(id)) {
            return ret;
        }
        
        WeixinAccountEntity account = this.getEntity(WeixinAccountEntity.class, id);
        if (account.getAuthorizationType().equalsIgnoreCase("0")) {
        	LOGGER.info(String.format("weixin account %s 没有使用第三方授权", id));
        } else {
        	String token = account.getAuthorizerAccessToken();
        	if (StringUtils.isNotBlank(token) && account.getAuthorizerAccessTokenExpireTime().after(new Date())) {
        		ret = token;
        	} else {
        		String platformId = account.getOpenPlatformId();
        		WeixinOpenPlatformEntity platform = weixinOpenPlatformService.getEntity(WeixinOpenPlatformEntity.class, platformId);
        		String componentAccessToken = weixinOpenPlatformService.getComponentAccessToken(platformId);
        		String componentAppId = platform.getAppId();
        		String authorizerAppId = account.getAccountappid();
        		String refreshToken = account.getAuthorizerRefreshToken();
        		String requestUrl = API_AUTHORIZER_TOKEN.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken);
        		JSONObject postData = new JSONObject();
        		postData.put("component_appid", componentAppId);
        		postData.put("authorizer_appid", authorizerAppId);
        		postData.put("authorizer_refresh_token", refreshToken);
        		JSONObject obj = WeixinUtil.httpRequest(requestUrl, "POST", postData.toString());
        		if (obj.containsKey("authorizer_access_token")) {
        			String authorizerAccessToken = obj.getString("authorizer_access_token");
        			String authorizerRefreshToken = obj.getString("authorizer_refresh_token");
        			int expiresIn = obj.getInt("expires_in");
        			Date expireTime = addTime(new Date(), expiresIn, Calendar.SECOND);
        			ret = authorizerAccessToken;
        			account.setAuthorizerAccessToken(authorizerAccessToken);
        			account.setAuthorizerRefreshToken(authorizerRefreshToken);
        			account.setAuthorizerAccessTokenExpireTime(expireTime);
        			this.saveOrUpdate(account);
        		} else {
        			LOGGER.info(String.format("获取authorizer_access_token失败: %s", obj.toString()));
        		}
        	}
        }
        
        return ret;
    }
    
    
    public WeixinAccountEntity findByEntity(WeixinAccountEntity accountEntity){
        StringBuffer buff=new StringBuffer();
        buff.append(" from WeixinAccountEntity t where 1=1");
        List<Object> listParam=new ArrayList<Object>();
        if(StringUtils.isNotBlank(accountEntity.getId())){
            buff.append(" and t.id=?");
            listParam.add(accountEntity.getId());
        }
        if(StringUtils.isNotBlank(accountEntity.getWeixin_accountid())){
            buff.append(" and t.weixin_accountid=?");
            listParam.add(accountEntity.getWeixin_accountid());
        }
        List<WeixinAccountEntity> list=super.findHql(buff.toString(), listParam.toArray());
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 替换sql中的变量
     * 
     * @param sql
     * @return
     */
    public String replaceVal(String sql, WeixinAccountEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{accountname}", String.valueOf(t.getAccountname()));
        sql = sql.replace("#{accounttoken}",
                String.valueOf(t.getAccounttoken()));
        sql = sql.replace("#{accountnumber}",
                String.valueOf(t.getAccountnumber()));
        sql = sql.replace("#{accounttype}", String.valueOf(t.getAccounttype()));
        sql = sql.replace("#{accountemail}",
                String.valueOf(t.getAccountemail()));
        sql = sql.replace("#{accountdesc}", String.valueOf(t.getAccountdesc()));
        sql = sql.replace("#{accountappid}",
                String.valueOf(t.getAccountappid()));
        sql = sql.replace("#{accountappsecret}",
                String.valueOf(t.getAccountappsecret()));
        sql = sql.replace("#{accountaccesstoken}",
                String.valueOf(t.getAccountaccesstoken()));
        sql = sql.replace("#{addtoekntime}",
                String.valueOf(t.getAddtoekntime()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }

    @Override
    public void fillWeixinAccountInfo(String appId, String platformId, String authorizationCode, int codeExpiresIn) {
    	WeixinOpenPlatformEntity platform = weixinOpenPlatformService.getEntity(WeixinOpenPlatformEntity.class, platformId);
        String componentAccessToken = weixinOpenPlatformService.getComponentAccessToken(platformId);
        String componentAppId = platform.getAppId();
        String requestUrl = API_QUERY_AUTH.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken);
        JSONObject postData = new JSONObject();
        postData.put("component_appid", componentAppId);
        postData.put("authorization_code", authorizationCode);
        JSONObject obj = WeixinUtil.httpRequest(requestUrl, "POST", postData.toString());
        if (obj.containsKey("authorization_info")) {
        	JSONObject authInfo = obj.getJSONObject("authorization_info");
        	String authorizerAppId = authInfo.getString("authorizer_appid");
        	String authorizerAccessToken = authInfo.getString("authorizer_access_token");
        	int expiresIn = authInfo.getInt("expires_in");
        	Date expireTime = addTime(new Date(), expiresIn, Calendar.SECOND);
        	String authorizerRefreshToken = authInfo.getString("authorizer_refresh_token");
        	WeixinAccountEntity account = this.getEntity(WeixinAccountEntity.class, appId);
        	if (account == null) {
        		LOGGER.info(String.format("微信账号不存在. id:%s", appId));
        	} else {
        		requestUrl = API_GET_AUTHORIZER_INFO.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken);
        		postData = new JSONObject();
                postData.put("component_appid", componentAppId);
                postData.put("authorizer_appid", authorizerAppId);
                JSONObject ret = WeixinUtil.httpRequest(requestUrl, "POST", postData.toString());
                if (ret.containsKey("authorizer_info")) {
                	JSONObject authorizerInfo = ret.getJSONObject("authorizer_info");
                	String nickName = authorizerInfo.getString("nick_name");
                	//授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
                	String serviceType = authorizerInfo.getJSONObject("service_type_info").getString("id");
                	//授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认
                	String verifyType = authorizerInfo.getJSONObject("verify_type_info").getString("id");
                	String userName = authorizerInfo.getString("user_name");
                	String alias = authorizerInfo.getString("alias");
                	String qrcode = authorizerInfo.getString("qrcode_url"); //公众号二维码
                	String headImg = authorizerInfo.getString("head_img"); //公众号用户头像
//                	if(qrcode.contains("/")){
//                		qrcode = qrcode.substring(qrcode.lastIndexOf("/")+1);
//                	}else if(qrcode.contains("\\")){
//                		qrcode = qrcode.substring(qrcode.lastIndexOf("\\")+1);
//                	}
//                	if(headImg.contains("/")){
//                		headImg = headImg.substring(headImg.lastIndexOf("/")+1);
//                	}else if(qrcode.contains("\\")){
//                		headImg = headImg.substring(headImg.lastIndexOf("\\")+1);
//                	}
                	account.setQRcode(qrcode);
                	account.setLogoAccount(headImg);
                	account.setAccountname(nickName);
                	account.setAccountnumber(alias);
                	account.setWeixin_accountid(userName);
                	
                	// 此处，要分别进行判断，是因为返回的是否验证的状态有多种，而我们目前至处理两个（认证，未认证），其他QQ认证、微博认证等不进行处理
                	boolean hasVerify = StringUtils.equals(verifyType, "0");
                	boolean notVerify = StringUtils.equals(verifyType, "-1");
                	if (serviceType.equalsIgnoreCase("2")) {
                		// 服务号
                		account.setAccounttype(hasVerify ? "1" : "3");
                	} else if (serviceType.equalsIgnoreCase("0") || serviceType.equalsIgnoreCase("1")){
                		// 订阅号
                		account.setAccounttype(notVerify ? "2" : "4");
                	}
                	
                } else {
                	LOGGER.info(String.format("API_GET_AUTHORIZER_INFO FAIL. %s", ret.toString()));
                }
                
        		account.setAccountappid(authorizerAppId);
        		account.setAuthorizationCode(authorizationCode);
        		account.setOpenPlatformId(platformId);
        		account.setAuthorizerAccessToken(authorizerAccessToken);
        		account.setAuthorizerAccessTokenExpireTime(expireTime);
        		account.setAuthorizerRefreshToken(authorizerRefreshToken);
        		account.setAuthorizationType(platform.getAuthorizationType());
        		account.setOpenPlatformAuthTime(new Date());
        		account.setAuthorizationCodeExpireTime(addTime(new Date(), codeExpiresIn, Calendar.SECOND));
        		this.saveOrUpdate(account);
        	}
        } else {
        	LOGGER.info(String.format("授权码换取公众号授权信息失败", obj.toString()));
        }
    }

    private Date addTime(Date current, int value, int unit) {
        Calendar c = Calendar.getInstance();
        c.setTime(current);
        c.add(unit, value);
        return c.getTime();
    }

	@Override
	public String pageAuth(String accountId, Map<String, String> properties, PageAuthHandler handler) {
        String redirectURL = ResourceUtil.getConfigByName("domain") + "/weixinOpenPlatform.do?pageAuth";
        return pageAuth(redirectURL, accountId, properties, handler);
	}

	@Override
	public String pageAuth(String accountId, Map<String, String> properties, PageAuthHandler handler, String openId) {
        // String localhosturl = ResourceUtil.getConfigByName("domain") + "/weixinOpenPlatform.do?pageAuth";
        String redirectURL = "weixinOpenPlatform.do?pageAuth&type=direct&state=";
        return pageAuth(redirectURL, accountId, properties, handler, openId);
	}

	@Override
	public String pageAuth2(String accountId, Map<String, String> properties, PageAuthHandler handler) {
        String redirectURL = ResourceUtil.getConfigByName("domain") + "/weixinOpenPlatform.do?pageAuth2";
        return pageAuth(redirectURL, accountId, properties, handler);
	}

	@Override
	public String pageAuth2(String accountId, Map<String, String> properties, PageAuthHandler handler, String openId) {
        String redirectURL = "weixinOpenPlatform.do?pageAuth2&type=direct&state=";
		return pageAuth(redirectURL, accountId, properties, handler, openId);
	}

    @Override
    public String pageAuth(String wechatRedirectURL, String accountId, Map<String, String> properties, PageAuthHandler handler) {
        WeixinAccountEntity account = findUniqueByProperty(WeixinAccountEntity.class, "id", accountId);
        if (null == account) {
            return null;
        }
        String key = UUID.randomUUID().toString();
        AccountIdCache accountIdCache = new AccountIdCache();
        accountIdCache.setLastUpdated(new Date());
        accountIdCache.setAccountId(account.getId());
        PageAuthPropertyCache propertyCache = new PageAuthPropertyCache();
        propertyCache.setLastUpdated(new Date());
        propertyCache.setProperties(properties);
        PageAuthHandlerCache handlerCache = new PageAuthHandlerCache();
        handlerCache.setLastUpdated(new Date());
        handlerCache.setHandler(handler);
        PageAuthRedisCache.setAccountIdCache(key, accountIdCache);
        PageAuthRedisCache.setPropertyCache(key, propertyCache);
        PageAuthRedisCache.setPageAuthHandlerCache(key, handlerCache);
        String redirectUrl = "";
        if (account.getAuthorizationType().equalsIgnoreCase("0")) {
            redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
            redirectUrl = redirectUrl.replace("APPID", account.getAccountappid());
            redirectUrl = redirectUrl.replace("SCOPE", "snsapi_base");
            redirectUrl = redirectUrl.replace("STATE", key);
            String url = CommonUtils.encodeURL(wechatRedirectURL);
            redirectUrl = redirectUrl.replace("REDIRECT_URI", url);
        } else {
            redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&component_appid=%s#wechat_redirect";
            WeixinOpenPlatformEntity platform = weixinOpenPlatformService.get(WeixinOpenPlatformEntity.class, account.getOpenPlatformId());
            String url = CommonUtils.encodeURL(wechatRedirectURL);
            redirectUrl = String.format(redirectUrl, account.getAccountappid(), url, "snsapi_base", key, platform.getAppId());
        }
        return redirectUrl;
    }

    @Override
    public String pageAuth(String wechatRedirectURL, String accountId, Map<String, String> properties, PageAuthHandler handler, String openId) {
        if (null == properties) {
            properties = new HashMap<>();
        }
        List<WeixinMemberEntity> member = this.weixinMemberService.findHql("from WeixinMemberEntity w where w.openId = ? and w.accountId = ?", openId, accountId);
        WeixinAccountEntity account = this.get(WeixinAccountEntity.class, accountId);
        // 如果是认证服务号，openId不属于该公众号, 直接网页授权
        if (account.getAccounttype().equalsIgnoreCase("1") && member != null && member.size() == 0) {
            return pageAuth(wechatRedirectURL, accountId, properties, handler);
        }

        String key = UUID.randomUUID().toString();
        AccountIdCache accountIdCache = new AccountIdCache();
        accountIdCache.setLastUpdated(new Date());
        accountIdCache.setAccountId(accountId);
        PageAuthPropertyCache propertyCache = new PageAuthPropertyCache();
        propertyCache.setLastUpdated(new Date());
        propertyCache.setProperties(properties);
        PageAuthHandlerCache handlerCache = new PageAuthHandlerCache();
        handlerCache.setLastUpdated(new Date());
        handlerCache.setHandler(handler);
        OpenIdCache openIdCache = new OpenIdCache();
        openIdCache.setLastUpdated(new Date());
        openIdCache.setOpenId(openId);
        PageAuthRedisCache.setAccountIdCache(key, accountIdCache);
        PageAuthRedisCache.setPropertyCache(key, propertyCache);
        PageAuthRedisCache.setPageAuthHandlerCache(key, handlerCache);
        PageAuthRedisCache.setOpenIdCache(key, openIdCache);
        return wechatRedirectURL + key;
    }

    @Override
    public Map<String, String> getAccountJsticket(HttpServletRequest request, String accountid) throws Exception {
        WeixinAccountEntity account = this.findUniqueByProperty(WeixinAccountEntity.class, "id", accountid);
        // 1 认证服务号 2 认证订阅号 3 未认证服务号  4 未认证订阅号
        String accounttype = account.getAccounttype();
        if (!"1".equals(accounttype)) {
        	return null;
        }

        String appId = account.getAccountappid();
        String ticket = this.getSignature(account.getId());

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        url = url + "?" + param;
        Map<String, String> ret = SignUtil.sign(ticket, url);
        ret.put("appId", appId);
        for (Map.Entry entry : ret.entrySet()) {
			LOGGER.info(entry.getKey() + ", " + entry.getValue());
		}
        return ret;
    }

}