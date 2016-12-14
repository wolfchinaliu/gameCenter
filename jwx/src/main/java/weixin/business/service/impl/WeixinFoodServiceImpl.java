package weixin.business.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.hibernate.Query;

import weixin.business.entity.WeixinFoodEntity;
import weixin.business.service.WeixinFoodServiceI;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinFoodService")
@Transactional
public class WeixinFoodServiceImpl extends CommonServiceImpl implements WeixinFoodServiceI {

	/**
 	 * 促销商品
 	 * @param accountid
 	 * @return
 	 */
	public List getHotList(String accountid){
		
		String hql = "from WeixinFoodEntity t where t.sellerId='"+accountid+"' and t.isHot=1 order by t.sellCount desc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(6);
		
		List list = query.list();
		if(null != list){
			return list;
		}
		return null;
	}
	
	/**
 	 * 新品推荐
 	 * @param accountid
 	 * @return
 	 */
	public List getNewList(String accountid){
			
		String hql = "from WeixinFoodEntity t where t.sellerId='"+accountid+"' and t.isNew=1 order by t.sellCount desc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(6);
		
		List list = query.list();
		if(null != list){
			return list;
		}
		return null;
	}
	
	/**
 	 * 热销商品
 	 * @param accountid
 	 * @return
 	 */
	public List getHotSaleList(String accountid){

		String hql = "from WeixinFoodEntity t where t.sellerId='"+accountid+"' order by t.sellCount desc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(6);
		
		List list = query.list();
		if(null != list){
			return list;
		}
		return null;
	}
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinFoodEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinFoodEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinFoodEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinFoodEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinFoodEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinFoodEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinFoodEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{title}",String.valueOf(t.getTitle()));
 		sql  = sql.replace("#{title_img}",String.valueOf(t.getTitleImg()));
 		sql  = sql.replace("#{descriptions}",String.valueOf(t.getDescriptions()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{real_price}",String.valueOf(t.getRealPrice()));
 		sql  = sql.replace("#{sale}",String.valueOf(t.getSale()));
 		sql  = sql.replace("#{sell_count}",String.valueOf(t.getSellCount()));
 		sql  = sql.replace("#{discuss_count}",String.valueOf(t.getDiscussCount()));
 		sql  = sql.replace("#{good_count}",String.valueOf(t.getGoodCount()));
 		sql  = sql.replace("#{bad_count}",String.valueOf(t.getBadCount()));
 		sql  = sql.replace("#{statement}",String.valueOf(t.getStatement()));
 		sql  = sql.replace("#{shelve_time}",String.valueOf(t.getShelveTime()));
 		sql  = sql.replace("#{remove_time}",String.valueOf(t.getRemoveTime()));
 		sql  = sql.replace("#{express_name}",String.valueOf(t.getExpressName()));
 		sql  = sql.replace("#{express_price}",String.valueOf(t.getExpressPrice()));
 		sql  = sql.replace("#{seller_id}",String.valueOf(t.getSellerId()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}