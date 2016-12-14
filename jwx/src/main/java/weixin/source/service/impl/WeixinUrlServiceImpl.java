package weixin.source.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.source.entity.WeixinUrlEntity;
import weixin.source.service.WeixinUrlServiceI;

import java.io.Serializable;
import java.util.UUID;

@Service("weixinUrlService")
@Transactional
public class WeixinUrlServiceImpl extends CommonServiceImpl implements WeixinUrlServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinUrlEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinUrlEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinUrlEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinUrlEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinUrlEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinUrlEntity t){
	 	return true;
 	}
 	
// 	/**
//	 * 替换sql中的变量
//	 * @param sql
//	 * @return
//	 */
// 	public String replaceVal(String sql,WeixinSourceEntity t){
// 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
// 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
// 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
// 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
// 		sql  = sql.replace("#{media_id}",String.valueOf(t.getMediaId()));
// 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
// 		sql  = sql.replace("#{source_type}",String.valueOf(t.getSourceType()));
// 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
// 		return sql;
// 	}
}