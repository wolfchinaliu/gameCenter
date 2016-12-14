package weixin.customer.service.impl;

import weixin.customer.service.WeixinCustomerTempServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import weixin.customer.entity.WeixinCustomerTempEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinCustomerTempService")
@Transactional
public class WeixinCustomerTempServiceImpl extends CommonServiceImpl implements WeixinCustomerTempServiceI {

	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((WeixinCustomerTempEntity) entity);
	}

	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		// 执行新增操作配置的sql增强
		this.doAddSql((WeixinCustomerTempEntity) entity);
		return t;
	}

	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((WeixinCustomerTempEntity) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(WeixinCustomerTempEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(WeixinCustomerTempEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(WeixinCustomerTempEntity t) {
		return true;
	}

	/**
	 * 替换sql中的变量
	 * 
	 * @param sql
	 * @return
	 */
	public String replaceVal(String sql, WeixinCustomerTempEntity t) {
		sql = sql.replace("#{id}", String.valueOf(t.getId()));
		sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
		sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
		sql = sql.replace("#{accountid}", String.valueOf(t.getAccountid()));
		sql = sql.replace("#{template_id}", String.valueOf(t.getTemplateId()));
		sql = sql.replace("#{template_id_short}", String.valueOf(t.getTemplateIdShort()));
		sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
		return sql;
	}

	public WeixinCustomerTempEntity getWeixinCustomerTempEntityByShortAndAccountId(String templateIdShort,
			String accountId) {

		String hql = " from WeixinCustomerTempEntity where templateIdShort='" + templateIdShort + "' and accountid='"
				+ accountId + "' order by createDate desc ";

		List<WeixinCustomerTempEntity> entityList = this.findByQueryString(hql);

		if (entityList != null && entityList.size() > 0) {

			return (WeixinCustomerTempEntity) entityList.get(0);
		}

		return null;
	}
}