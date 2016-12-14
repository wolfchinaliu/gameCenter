package weixin.personalredpacket.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.personalredpacket.entity.PersonalRedpacketSetEntity;

import java.io.Serializable;

public interface PersonalRedpacketSetServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(PersonalRedpacketSetEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(PersonalRedpacketSetEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(PersonalRedpacketSetEntity t);

	public Integer getCount(String sql);
}
