package weixin.tenant.service;
import weixin.tenant.entity.WeixinAcctEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface WeixinAcctServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinAcctEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinAcctEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinAcctEntity t);
 	
 	/**
 	 * 广告业务用，查询下级出让广告位的商户
 	 * */ 
    public List<WeixinAcctEntity> getSubAcctForAd(String tenantId);
    /**
     * 查询指定商户id的所有下级商户
     * */
    public List<WeixinAcctEntity> getAllSubAcct(String acctId);
    /**
     * 查询指定商户id的所有有下级商户的商户(所有非C等级的商户)
     * */
    public List<WeixinAcctEntity> getNotCAllSubAcct(String acctId);
}
