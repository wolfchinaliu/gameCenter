package weixin.weicar.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.weicar.entity.CarSeriesEntity;
import weixin.weicar.service.CarSeriesServiceI;

@Service("carSeriesService")
@Transactional
public class CarSeriesServiceImpl extends CommonServiceImpl implements CarSeriesServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CarSeriesEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CarSeriesEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CarSeriesEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CarSeriesEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CarSeriesEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CarSeriesEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CarSeriesEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{short_name}",String.valueOf(t.getShortName()));
 		sql  = sql.replace("#{picture}",String.valueOf(t.getPicture()));
 		sql  = sql.replace("#{sorts}",String.valueOf(t.getSorts()));
 		sql  = sql.replace("#{series_introduction}",String.valueOf(t.getSeriesIntroduction()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	public List<CarSeriesEntity> getCarSeriesByAccId(CarSeriesEntity entity){
 		StringBuffer hql=new StringBuffer();
 		hql.append("from CarSeriesEntity t where 1=1");
 		List<Object> paramList=new  ArrayList<Object>();
 		hql.append(" and t.accountid=? ");
 		paramList.add(entity.getAccountid());
 		return findHql(hql.toString(),paramList.toArray());
 	}
 	
}