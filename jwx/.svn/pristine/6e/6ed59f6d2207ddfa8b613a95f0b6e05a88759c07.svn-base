package weixin.weicar.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.weicar.entity.CarOrderEntity;
import weixin.weicar.entity.CarOrderSettingEntity;
import weixin.weicar.service.CarOrderServiceI;

@Service("carOrderService")
@Transactional
public class CarOrderServiceImpl extends CommonServiceImpl implements
		CarOrderServiceI {

	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((CarOrderEntity) entity);
	}

	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		// 执行新增操作配置的sql增强
		this.doAddSql((CarOrderEntity) entity);
		return t;
	}

	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((CarOrderEntity) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(CarOrderEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(CarOrderEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(CarOrderEntity t) {
		return true;
	}

	/**
	 * 替换sql中的变量
	 * 
	 * @param sql
	 * @return
	 */
	public String replaceVal(String sql, CarOrderEntity t) {
		sql = sql.replace("#{id}", String.valueOf(t.getId()));
		sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
		sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
		sql = sql.replace("#{name}", String.valueOf(t.getName()));
		sql = sql.replace("#{detail}", String.valueOf(t.getDetail()));
		sql = sql.replace("#{phone}", String.valueOf(t.getPhone()));
		sql = sql.replace("#{order_time}", String.valueOf(t.getOrderTime()));
		sql = sql.replace("#{order_date}", String.valueOf(t.getOrderDate()));
		sql = sql
				.replace("#{order_status}", String.valueOf(t.getOrderStatus()));
		sql = sql
				.replace("#{order_remark}", String.valueOf(t.getOrderRemark()));
		sql = sql.replace("#{open_id}", String.valueOf(t.getOpenId()));
		sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
		return sql;
	}

	/**
	 * 
	 * 根据参数获取预约
	 * 
	 * @param entity
	 * @return
	 */
	public List<CarOrderEntity> getCarOrderByParam(CarOrderEntity entity) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from CarOrderEntity where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if (null != entity.getCarOrderSettingEntity()) {
			CarOrderSettingEntity settingEntity = entity
					.getCarOrderSettingEntity();
			if (StringUtil.isNotEmpty(settingEntity.getId())) {
				hql.append(" and carOrderSettingEntity.id=?");
				paramList.add(settingEntity.getId());
			}
		}
		if (StringUtil.isNotEmpty(entity.getOpenId())) {
			hql.append(" and openId=?");
			paramList.add(entity.getOpenId());
		}

		List<CarOrderEntity> carOrderList = this.findHql(hql.toString(),
				paramList.toArray());
		return carOrderList;
	}
	
	
	/**
	 * 
	 * 根据参数获取预约总数
	 * 
	 * @param entity
	 * @return
	 */
	public Long getCarOrderCount(CarOrderEntity entity) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from car_order where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if (null != entity.getCarOrderSettingEntity()) {
			CarOrderSettingEntity settingEntity = entity
					.getCarOrderSettingEntity();
			if (StringUtil.isNotEmpty(settingEntity.getId())) {
				sql.append(" and CAR_ORDER_SETTING_ID=?");
				paramList.add(settingEntity.getId());
			}
		}
		if (StringUtil.isNotEmpty(entity.getOpenId())) {
			sql.append(" and open_Id=?");
			paramList.add(entity.getOpenId());
		}
		return this.getCountForJdbcParam(sql.toString(),
				paramList.toArray());
	}
	
	
}