package weixin.weicar.service;

import weixin.weicar.entity.CarOrderEntity;
import weixin.weicar.entity.CarOrderSettingEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface CarOrderServiceI extends CommonService {

	public <T> void delete(T entity);

	public <T> Serializable save(T entity);

	public <T> void saveOrUpdate(T entity);

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(CarOrderEntity t);

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(CarOrderEntity t);

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(CarOrderEntity t);
	
	public Long getCarOrderCount(CarOrderEntity entity);
	/**
	 * 
	 * 根据参数获取预约
	 * 
	 * @param entity
	 * @return
	 */
	public List<CarOrderEntity> getCarOrderByParam(CarOrderEntity entity);
	
}
