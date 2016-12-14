package weixin.weicar.service.impl;
import weixin.weicar.service.CarOrderSettingServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;

import weixin.weicar.entity.CarOrderSettingEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.io.Serializable;

@Service("carOrderSettingService")
@Transactional
public class CarOrderSettingServiceImpl extends CommonServiceImpl implements CarOrderSettingServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CarOrderSettingEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CarOrderSettingEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CarOrderSettingEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CarOrderSettingEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CarOrderSettingEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CarOrderSettingEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CarOrderSettingEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{title}",String.valueOf(t.getTitle()));
 		sql  = sql.replace("#{keyword}",String.valueOf(t.getKeyword()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{longitude}",String.valueOf(t.getLongitude()));
 		sql  = sql.replace("#{latitude}",String.valueOf(t.getLatitude()));
 		sql  = sql.replace("#{tel}",String.valueOf(t.getTel()));
 		sql  = sql.replace("#{headpic}",String.valueOf(t.getHeadpic()));
 		sql  = sql.replace("#{order_desc}",String.valueOf(t.getOrderDesc()));
 		sql  = sql.replace("#{order_type}",String.valueOf(t.getOrderType()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	/**
 	 * 
 	 * 根据参数获取预约对象
 	 * @param entity
 	 * @return
 	 */
 	public CarOrderSettingEntity getCarOrderSettingByParam(CarOrderSettingEntity entity){
 		StringBuffer hql=new StringBuffer();
 		hql.append(" from CarOrderSettingEntity where 1=1");
 		if(StringUtil.isNotEmpty(entity.getOrderType())){
 			hql.append(" and orderType=").append(entity.getOrderType());
 		}
 		if(StringUtil.isNotEmpty(entity.getAccountid())){
 			hql.append(" and accountid='").append(entity.getAccountid()).append("'");
 		}
 		
		List<CarOrderSettingEntity> settingList =super.findByQueryString(hql.toString());
		if(settingList!=null&&settingList.size()>0){
			return settingList.get(0);
		}
		return null;
 	}
}