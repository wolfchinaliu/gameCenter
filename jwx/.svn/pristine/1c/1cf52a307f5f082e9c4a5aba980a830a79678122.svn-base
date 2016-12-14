package weixin.member.service.impl;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.member.entity.WeixinGroupEntity;
import weixin.member.service.WeixinGroupServiceI;

@Service("weixinGroupService")
@Transactional
public class WeixinGroupServiceImpl extends CommonServiceImpl implements WeixinGroupServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinGroupEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinGroupEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinGroupEntity)entity);
 	}
 	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinGroupEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinGroupEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinGroupEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinGroupEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{group_id}",String.valueOf(t.getGroupId()));
 		sql  = sql.replace("#{group_name}",String.valueOf(t.getGroupName()));
 		sql  = sql.replace("#{synch_statu}",String.valueOf(t.getSynchStatu()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	
 	public WeixinGroupEntity getGroupEntityByParam(WeixinGroupEntity weixinGroupEntity){
 		StringBuffer hql=new StringBuffer();
 		hql.append(" from WeixinGroupEntity where 1=1");
 		if(StringUtil.isNotEmpty(weixinGroupEntity.getGroupId())){
 			hql.append(" and groupId=").append(weixinGroupEntity.getGroupId());
 		}
 		if(StringUtil.isNotEmpty(weixinGroupEntity.getAccountid())){
 			hql.append(" and accountid='").append(weixinGroupEntity.getAccountid()).append("'");
 		}
 		
		List<WeixinGroupEntity> groupWeixin =super.findByQueryString(hql.toString());
		if(groupWeixin!=null&&groupWeixin.size()>0){
			return groupWeixin.get(0);
		}
		return null;
 	}
 	
 	public  void saveOrUpdateList(List<WeixinGroupEntity> entitys){

 		for (WeixinGroupEntity entity : entitys) {
 			this.saveOrUpdate(entity);
		}
 	}

}