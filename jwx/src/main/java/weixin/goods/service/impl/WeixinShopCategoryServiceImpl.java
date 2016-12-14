package weixin.goods.service.impl;
import weixin.goods.service.WeixinShopCategoryServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import weixin.goods.entity.WeixinShopCategoryEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinShopCategoryService")
@Transactional
public class WeixinShopCategoryServiceImpl extends CommonServiceImpl implements WeixinShopCategoryServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinShopCategoryEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinShopCategoryEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinShopCategoryEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinShopCategoryEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinShopCategoryEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinShopCategoryEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinShopCategoryEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{imgurl}",String.valueOf(t.getImgurl()));
 		sql  = sql.replace("#{parentid}",String.valueOf(t.getParentid()));
 		sql  = sql.replace("#{seller_id}",String.valueOf(t.getSellerId()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	public WeixinShopCategoryEntity getWeixinShopCategoryEntity(WeixinShopCategoryEntity t){
 		StringBuffer hql=new StringBuffer();
 		hql.append("from WeixinShopCategoryEntity t where 1=1");
 		List<Object> paramList=new  ArrayList<Object>();
 		hql.append(" and t.sellerId=? ");
 		paramList.add(t.getSellerId());
 		hql.append(" and t.name=?");
 		paramList.add(t.getName());
 		List<WeixinShopCategoryEntity> list=findHql(hql.toString(),paramList.toArray());
 		if(list!=null&&list.size()>0){
 			return list.get(0);
 		}
 		return null;
 		
 	}
}