package weixin.weicar.service.impl;
import weixin.weicar.service.CarIndexManagerServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import weixin.weicar.entity.CarIndexManagerEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("carIndexManagerService")
@Transactional
public class CarIndexManagerServiceImpl extends CommonServiceImpl implements CarIndexManagerServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CarIndexManagerEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CarIndexManagerEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CarIndexManagerEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CarIndexManagerEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CarIndexManagerEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CarIndexManagerEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CarIndexManagerEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{keyword}",String.valueOf(t.getKeyword()));
 		sql  = sql.replace("#{title}",String.valueOf(t.getTitle()));
 		sql  = sql.replace("#{head_pic}",String.valueOf(t.getHeadPic()));
 		sql  = sql.replace("#{url}",String.valueOf(t.getUrl()));
 		sql  = sql.replace("#{select_template}",String.valueOf(t.getSelectTemplate()));
 		sql  = sql.replace("#{car_series_title}",String.valueOf(t.getCarSeriesTitle()));
 		sql  = sql.replace("#{car_series_pic}",String.valueOf(t.getCarSeriesPic()));
 		sql  = sql.replace("#{car_series_url}",String.valueOf(t.getCarSeriesUrl()));
 		sql  = sql.replace("#{car_sales_title}",String.valueOf(t.getCarSalesTitle()));
 		sql  = sql.replace("#{car_sales_pic}",String.valueOf(t.getCarSalesPic()));
 		sql  = sql.replace("#{car_sales_url}",String.valueOf(t.getCarSalesUrl()));
 		sql  = sql.replace("#{car_order_title}",String.valueOf(t.getCarOrderTitle()));
 		sql  = sql.replace("#{car_order_pic}",String.valueOf(t.getCarOrderPic()));
 		sql  = sql.replace("#{car_order_url}",String.valueOf(t.getCarOrderUrl()));
 		sql  = sql.replace("#{car_care_title}",String.valueOf(t.getCarCareTitle()));
 		sql  = sql.replace("#{car_care_pic}",String.valueOf(t.getCarCarePic()));
 		sql  = sql.replace("#{car_care_url}",String.valueOf(t.getCarCareUrl()));
 		sql  = sql.replace("#{car_tools_title}",String.valueOf(t.getCarToolsTitle()));
 		sql  = sql.replace("#{car_tools_pic}",String.valueOf(t.getCarToolsPic()));
 		sql  = sql.replace("#{car_tools_url}",String.valueOf(t.getCarToolsUrl()));
 		sql  = sql.replace("#{car_type_title}",String.valueOf(t.getCarTypeTitle()));
 		sql  = sql.replace("#{car_type_pic}",String.valueOf(t.getCarTypePic()));
 		sql  = sql.replace("#{car_type_url}",String.valueOf(t.getCarTypeUrl()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}