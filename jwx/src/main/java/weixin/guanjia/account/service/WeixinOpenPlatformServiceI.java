package weixin.guanjia.account.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinOpenPlatformEntity;

public interface WeixinOpenPlatformServiceI extends CommonService{
	@Override
 	public <T> void delete(T entity);
	
 	@Override
 	public <T> Serializable save(T entity);
 	
 	@Override
 	public <T> void saveOrUpdate(T entity);
 	
 	public String getComponentAccessToken(String platformId);
 	
 	public String getComponentVerifyTicket(String platformId);
 	
 	public String getPreAuthCode(String platformId);
 	
 	public String getComponentAccessToken();
 	
 	public String getComponentVerifyTicket();
 	
 	public String getPreAuthCode();
 	
 	public String getComponentValidateToken(String platformId);
 	
 	public String getComponentSymmetricKey(String platformId);
 	
 	public String getComponentValidateToken();
 	
 	public String getComponentSymmetricKey();
 	
 	public WeixinOpenPlatformEntity findByAppId(String appId);
 	
 	public void updateComponentVerifyTicket(String appId, String componentVerifyTicket, Date updateTime);

}
