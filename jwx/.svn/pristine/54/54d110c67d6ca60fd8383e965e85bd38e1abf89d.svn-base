package weixin.customer.service;

import weixin.customer.entity.WeixinCustomerTempEntity;
import weixin.member.entity.WeixinMemberEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface WeixinCustomerTempServiceI extends CommonService {

	public <T> void delete(T entity);

	public <T> Serializable save(T entity);

	public <T> void saveOrUpdate(T entity);

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(WeixinCustomerTempEntity t);

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(WeixinCustomerTempEntity t);

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(WeixinCustomerTempEntity t);

	public WeixinCustomerTempEntity getWeixinCustomerTempEntityByShortAndAccountId(String templateIdShort,
			String accountId);

}
