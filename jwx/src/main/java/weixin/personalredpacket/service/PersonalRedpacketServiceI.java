package weixin.personalredpacket.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.personalredpacket.entity.PersonalRedpacketEntity;
import weixin.personalredpacket.entity.PersonalRedpacketSetEntity;

import java.io.Serializable;

public interface PersonalRedpacketServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(PersonalRedpacketEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(PersonalRedpacketEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(PersonalRedpacketEntity t);

	/**
	 * 查询数量
	 * @param sql
	 * @return
	 */
	public Integer getCount(String sql);
}
