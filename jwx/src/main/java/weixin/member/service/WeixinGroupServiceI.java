package weixin.member.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import weixin.member.entity.WeixinGroupEntity;

public interface WeixinGroupServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinGroupEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinGroupEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinGroupEntity t);
 	
 	public WeixinGroupEntity getGroupEntityByParam(WeixinGroupEntity weixinGroupEntity);
 	
 	public void saveOrUpdateList(List<WeixinGroupEntity> entitys);
}
