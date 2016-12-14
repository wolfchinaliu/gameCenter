package weixin.guanjia.account.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.service.CommonService;

import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinIndividualizationEntity;
import weixin.tenant.entity.WeixinAcctEntity;

public interface WeixinAccountServiceI extends CommonService{
	@Override
 	public <T> void delete(T entity);
 	@Override
 	public <T> Serializable save(T entity);
 	@Override
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinAccountEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinAccountEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinAccountEntity t);
 	/**
 	 * 获取上级商户id
 	 * @param accountid
 	 * @return
 	 */
 	public WeixinIndividualizationEntity getIndividualization(String accountid); 	
 	/**
 	 * 登陆页 个性化logo查询
 	 * @param accountid
 	 * @return
 	 */
 	public WeixinIndividualizationEntity getIndividualizationLogo(String id);
 	
 	public String getAccessToken();
 	
 	public String getAccessToken(String accountId);
 	
 	public String getAuthorizerAccessToken();
 	
 	public String getAuthorizerAccessToken(String id);
 	
 	public void fillWeixinAccountInfo(String accountId, String platformId, String authorizationCode, int codeExpiresIn);
 	
 	public String pageAuth(String accountId, Map<String, String> properties, PageAuthHandler handler);

 	public String pageAuth(String accountId, Map<String, String> properties, PageAuthHandler handler, String openId);

 	public String pageAuth2(String accountId, Map<String, String> properties, PageAuthHandler handler);

 	public String pageAuth2(String accountId, Map<String, String> properties, PageAuthHandler handler, String openId);

    public String pageAuth(String wechatRedirectURL, String accountId, Map<String, String> properties, PageAuthHandler handler);
    public String pageAuth(String wechatRedirectURL, String accountId, Map<String, String> properties, PageAuthHandler handler, String openId);

	public String getSignature(String accountId);

 	@Deprecated
 	public WeixinAccountEntity findLoginWeixinAccount();
 	public WeixinAcctEntity findLoginWeixinAcct();
 	public List<WeixinAccountEntity> findByUsername(String username);
 	/**
 	 * 按微信的toUsername获取对象
 	 * @param toUserName
 	 * @return
 	 */
 	public WeixinAccountEntity findByToUsername(String toUserName);
 	
 	/**
 	 * 根据主键和toUserName匹配对象
 	 * 防止注入模式的发送消息
 	 * @param accountEntity
 	 * @return
 	 */
 	public WeixinAccountEntity findByEntity(WeixinAccountEntity accountEntity);
 	
 	/**
 	 * 根据主键获取Token
 	 * @param id
 	 * @return
 	 */
 	public String getAccessTokenByPrimaryKey(String id);
 	
 	/**
 	 * 根据公众号id和request，获取jsticket
 	 * @param id
 	 * @return
 	 */
	public Map<String, String> getAccountJsticket(HttpServletRequest request, String accountid) throws Exception;
}
