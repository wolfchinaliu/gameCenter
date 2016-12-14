package org.jeecgframework.core.util;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSUser;

import com.baidu.inf.iis.bcs.model.Resource;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.util.WeiXinConstants;

/**
 * 项目参数工具类
 * 
 */
public class ResourceUtil {
    private static final String env = StringUtils.defaultIfBlank(System.getProperty("env"), StringUtils.defaultIfBlank(System.getenv("env"), "pro"));

	private static final ResourceBundle sysConfig = java.util.ResourceBundle.getBundle(env+"/sysConfig");

	private static final ResourceBundle customer = java.util.ResourceBundle.getBundle(env+"/customerConfig");

	private static final ResourceBundle mediaFile = java.util.ResourceBundle.getBundle(env+"/mediaFile");
	
	private static final ResourceBundle dbConfig = java.util.ResourceBundle.getBundle(env+"/dbconfig");
	
	private static final ResourceBundle redis = java.util.ResourceBundle.getBundle(env + "/redis");

	private static final ResourceBundle system = java.util.ResourceBundle.getBundle(env + "/system");
	private static final Logger logger = Logger.getLogger(ResourceUtil.class);

	public static String getEnv(){
	    return env;
	}
	/**
	 * 获取session定义名称
	 * 
	 * @return
	 */
	public static final String getSessionattachmenttitle(String sessionName) {
		return sysConfig.getString(sessionName);
	}
	public static final TSUser getSessionUserName() {
		HttpSession session = ContextHolderUtils.getSession();
		if(ClientManager.getInstance().getClient(session.getId())!=null){
			return ClientManager.getInstance().getClient(session.getId()).getUser();
		}
		return null;
	}
	
	/**
	 * 获取登录用户微信账号信息
	 * @return
	 */
	public static final WeixinAccountEntity getWeiXinAccount() {
		HttpSession session = ContextHolderUtils.getSession();
		if(session.getAttribute(WeiXinConstants.WEIXIN_ACCOUNT)!=null){
			WeixinAccountEntity WeixinAccountEntity = (weixin.guanjia.account.entity.WeixinAccountEntity) session.getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
			return WeixinAccountEntity;
		}else{
			return null;
		}
	}
	
	/**
	 * 获取登录用户微信账号ID
	 * @return
	 */
	public static final String getWeiXinAccountId() {
		
		HttpSession session = ContextHolderUtils.getSession();
		
		if(session.getAttribute(WeiXinConstants.WEIXIN_ACCOUNT)!=null){
			
			WeixinAccountEntity weixinAccountEntity = (weixin.guanjia.account.entity.WeixinAccountEntity) session.getAttribute(WeiXinConstants.WEIXIN_ACCOUNT);
			return weixinAccountEntity.getId();
		}else{
			return null;
		}
	}

	public static final String getWeiXinAcctId(){
        HttpSession session = ContextHolderUtils.getSession();
        if(session.getAttribute(WeiXinConstants.WEIXIN_ACCT)!=null){
            WeixinAcctEntity weixinAcctEntity = (WeixinAcctEntity) session.getAttribute(WeiXinConstants.WEIXIN_ACCT);
            return weixinAcctEntity.getId();
        }else{
            return null;
        }
    }

	public static final WeixinAcctEntity getWeiXinAcct(){
        HttpSession session = ContextHolderUtils.getSession();
        if(session.getAttribute(WeiXinConstants.WEIXIN_ACCT)!=null){
            WeixinAcctEntity weixinAcctEntity = (WeixinAcctEntity) session.getAttribute(WeiXinConstants.WEIXIN_ACCT);
            return weixinAcctEntity;
        }else{
            return null;
        }
    }
	/**
	 * 获取浏览用户的openId
	 * @return
	 */
	public static final String getUserOpenId() {
		HttpSession session = ContextHolderUtils.getSession();
		Object userOpenId = session.getAttribute(WeiXinConstants.USER_OPENID);
		if(userOpenId!=null){
			return userOpenId.toString();
		}else{
			return null;
		}
	} 
	
	@Deprecated
	public static final List<TSRoleFunction> getSessionTSRoleFunction(String roleId) {
		HttpSession session = ContextHolderUtils.getSession();
		if (session.getAttributeNames().hasMoreElements()) {
			List<TSRoleFunction> TSRoleFunctionList = (List<TSRoleFunction>)session.getAttribute(roleId);
			if (TSRoleFunctionList != null) {
				return TSRoleFunctionList;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
	    String queryString = request.getQueryString();
	    if (StringUtils.isNotBlank(queryString) && queryString.indexOf("=") > -1) {// 去掉其他参数
	        queryString = queryString.substring(0, queryString.indexOf("="));
        }
		String requestPath = request.getRequestURI() + "?" + queryString;
		logger.debug("real request path:" + requestPath);
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}
	
	/**
	 * 没有登录，跳转到登陆界面，获得登录前的url
	 * @param request
	 * @return
	 */
	public static String getRedirUrl(HttpServletRequest request){
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}

	/**
	 * 获取配置文件参数
	 * 
	 * @param name
	 * @return
	 */
	private static final Map<Object, Object> getConfigMap(String path) {
		ResourceBundle bundle = ResourceBundle.getBundle(path);
		Set set = bundle.keySet();
		return oConvertUtils.SetToMap(set);
	}

	
	
	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
		return resultPath;
	}

	/**
	 * 获取项目根目录
	 * 
	 * @return
	 */
	public static String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}


	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static String getParameter(String field) {
		HttpServletRequest request = ContextHolderUtils.getRequest();
		return request.getParameter(field);
	}

	/**
	 * 获取数据库类型
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static final String getDBType() {
		return DBTypeUtil.getDBType().toLowerCase();
	}

    public static final String getConfigByName(String name) {
        return sysConfig.getString(name);
    }
    /**
     * 获取随机码的长度
     *
     * @return 随机码的长度
     */
    public static String getRandCodeLength() {
        return sysConfig.getString("randCodeLength");
    }
    /**
     * 获取随机码的类型
     *
     * @return 随机码的类型
     */
    public static String getRandCodeType() {
        return sysConfig.getString("randCodeType");
    }

    public static String getDomain(){
        return sysConfig.getString("domain");
    }
    public static String getDomain1(){
        return sysConfig.getString("domain1");
    }
    public static String getSqlModel(){
        return sysConfig.getString("sqlReadMode");
    }
    public static String getShiliuSecret(){
        return sysConfig.getString("shiliuSecret");
    }
    public static String getShiliuAppId(){
        return sysConfig.getString("shiliuAppId");
    }
    public static String getShiliuAccountId(){
        return sysConfig.getString("shiliuAccountId");
    }
    public static String getFilebrowserBrowseUrl(){
        return sysConfig.getString("filebrowserBrowseUrl");
    }
    public static String getFilebrowserImageBrowseUrl(){
        return sysConfig.getString("filebrowserImageBrowseUrl");
    }
    public static String getFilebrowserFlashBrowseUrl(){
        return sysConfig.getString("filebrowserFlashBrowseUrl");
    }
    public static String getFilebrowserUploadUrl(){
        return sysConfig.getString("filebrowserUploadUrl");
    }
    public static String getFilebrowserImageUploadUrl(){
        return sysConfig.getString("filebrowserImageUploadUrl");
    }
    public static String getFilebrowserFlashUploadUrl(){
        return sysConfig.getString("filebrowserFlashUploadUrl");
    }
    public static String getMailServerhost(){
        return sysConfig.getString("mail_serverhost");
    }
    public static String getMailServerport(){
        return sysConfig.getString("mail_serverport");
    }
    public static String getMailFromaddress(){
        return sysConfig.getString("mail_fromaddress");
    }
    public static String getMailFromaddressPassword(){
        return sysConfig.getString("mail_fromaddress_password");
    }
    public static String getMailFromaddressNickname(){
        return sysConfig.getString("mail_fromaddress_nickname");
    }
    public static String getMailSubject(){
        return sysConfig.getString("mail_subject");
    }
    public static String getMailContentVersion(){
        return sysConfig.getString("mail_content_version");
    }
    public static String getMailToaddress(){
        return sysConfig.getString("mail_toaddress");
    }
    public static String getTxtPathPrefix(){
        return sysConfig.getString("media.txt.prefix");
    }

    /**
     * 获取配置文件参数
     * 
     * @param name
     * @return
     */
    public static final String getCuConfigByName(String name) {
        return customer.getString(name);
    }
    public static final String getLog_format(){
        return customer.getString("log_format");
    }

    public static final ResourceBundle getSystemResource(){
        return system;
    }
    public static String getMediaUrlPrefix(){
        return mediaFile.getString("media.url.prefix");
    }
    public static String getMediaPathPrefix(){
        return mediaFile.getString("media.path.prefix");
    }
    public static String getMediaTxtPrefix(){
        return mediaFile.getString("media.txt.prefix");
    }

    // 静态资源路径  js,css
    public static String getResourcePath(){
        return mediaFile.getString("resourcePath");
    }
    // 静态资源路径  js,css
    public static String getCdnHost(){
        return mediaFile.getString("cdnHost");
    }
    
    //dbconfig.properties
    public static String getJdbcUrl(){
        return dbConfig.getString("jdbc.url.jeecg");
    }
    public static String getJdbcUsername(){
        return dbConfig.getString("jdbc.username.jeecg");
    }
    public static String getJdbcPassword(){
        return dbConfig.getString("jdbc.password.jeecg");
    }
    
    // redis.properties
    public static final ResourceBundle getRedisConfigs(){
        return redis;
    }
    public static String getRedisTimeout(){
        return redis.getString("redis.timeout");
    }
    public static String getRedisMaxRedirections(){
        return redis.getString("redis.maxRedirections");
    }
    public static String getRedisMaxTotal(){
        return redis.getString("redis.maxTotal");
    }
    public static String getRedisMaxIdle(){
        return redis.getString("redis.maxIdle");
    }
    public static String getRedisHosts(){
        return redis.getString("redis.hosts");
    }
}