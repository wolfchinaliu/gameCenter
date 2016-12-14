package weixin.lottery.service.impl;
import weixin.lottery.service.WeixinDrawDetailServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import weixin.customer.entity.WeixinCustomerTempEntity;
import weixin.lottery.entity.WeixinDrawDetailEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinDrawDetailService")
@Transactional
public class WeixinDrawDetailServiceImpl extends CommonServiceImpl implements WeixinDrawDetailServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinDrawDetailEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinDrawDetailEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinDrawDetailEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinDrawDetailEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinDrawDetailEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinDrawDetailEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinDrawDetailEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{addtime}",String.valueOf(t.getAddtime()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{opendid}",String.valueOf(t.getOpendid()));
 		sql  = sql.replace("#{angle}",String.valueOf(t.getAngle()));
 		sql  = sql.replace("#{msg}",String.valueOf(t.getMsg()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
	public void deteWeixinDrawDetailByHdid(String hdid) {

		String hql = " delete from  weixin_draw_detail where hdid='" + hdid + "' ";

		this.updateBySqlString(hql);
	}
}