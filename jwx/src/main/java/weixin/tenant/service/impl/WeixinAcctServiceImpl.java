package weixin.tenant.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stringtemplate.v4.compiler.CodeGenerator.list_return;

import weixin.guanjia.core.util.SubAcctRedisCache;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctServiceI;

@Service("weixinAcctService")
@Transactional
public class WeixinAcctServiceImpl extends CommonServiceImpl implements WeixinAcctServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinAcctEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinAcctEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinAcctEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinAcctEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinAcctEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinAcctEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinAcctEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{end_date}",String.valueOf(t.getEndDate()));
 		sql  = sql.replace("#{acct_name}",String.valueOf(t.getAcctName()));
 		sql  = sql.replace("#{mobile_phone}",String.valueOf(t.getMobilePhone()));
 		sql  = sql.replace("#{email}",String.valueOf(t.getEmail()));
 		sql  = sql.replace("#{qq_number}",String.valueOf(t.getQqNumber()));
 		sql  = sql.replace("#{smsnum}",String.valueOf(t.getSmsnum()));
 		sql  = sql.replace("#{newsnum}",String.valueOf(t.getNewsnum()));
 		sql  = sql.replace("#{requestnum}",String.valueOf(t.getRequestnum()));
 		sql  = sql.replace("#{usernum}",String.valueOf(t.getUsernum()));
 		sql  = sql.replace("#{accountnum}",String.valueOf(t.getAccountnum()));
 		sql  = sql.replace("#{domainurl}",String.valueOf(t.getDomainurl()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

 	/**
 	 * 广告业务用，查询下级出让广告位的商户
 	 * */
 	public List<WeixinAcctEntity> getSubAcctForAd(String acctId){
 	   WeixinAcctEntity weixinAcctEntity = this.commonDao.getEntity(WeixinAcctEntity.class, acctId);
 	   if(weixinAcctEntity==null){
 	       return null;
 	   }
 	   WeixinAcctEntity example = new WeixinAcctEntity();
 	   example.setPid(acctId);
 	   example.setSellAdpos("1");
 	   List<WeixinAcctEntity> list = this.commonDao.findByExample(WeixinAcctEntity.class.getName(), example);
 	   return list;
 	}

 	/**
 	 * 查询指定商户list的所有直接下级
 	 * */
    private List<WeixinAcctEntity> getSubAcct(List<String> acctIds){
        if(CollectionUtils.isEmpty(acctIds)){
            return null;
        }
        String hql = "from WeixinAcctEntity a where a.pid in(:pids)";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pids", acctIds);
       List<WeixinAcctEntity> subAccts = this.commonDao.findByQueryString(hql, param);
       return subAccts;
    }

    /**
     * 查询指定商户id的所有下级商户
     * */
    public List<WeixinAcctEntity> getAllSubAcct(String acctId){
        List<WeixinAcctEntity> allSubs = null;
        allSubs = SubAcctRedisCache.getAllSubAcct(acctId);
        if(allSubs!=null){
            return allSubs;
        }
        allSubs = new ArrayList<WeixinAcctEntity>();
        List<String> directSubIds = new ArrayList<String>();
        directSubIds.add(acctId);
        while(CollectionUtils.isNotEmpty(directSubIds)){
            List<WeixinAcctEntity> sub = this.getSubAcct(directSubIds);
            directSubIds = new ArrayList<String>();
            if(CollectionUtils.isNotEmpty(sub)){
                for(WeixinAcctEntity entity : sub){
                    allSubs.add(entity);
                    if(!"3".equals(entity.getAcctLevel())){
                        directSubIds.add(entity.getId());
                    }
                }
            }
        }
        SubAcctRedisCache.setAllSubAcct(acctId, allSubs);
        return allSubs;
    }
    
    /**
     * 查询指定商户id的所有有下级商户的商户(所有非C等级的商户)
     * */
    public List<WeixinAcctEntity> getNotCAllSubAcct(String acctId){
        List<WeixinAcctEntity> allSubs = null;
        allSubs = SubAcctRedisCache.getNotCAllSubAcct(acctId);
        if(allSubs!=null){
            return allSubs;
        }
        allSubs = new ArrayList<WeixinAcctEntity>();
        List<String> directSubIds = new ArrayList<String>();
        directSubIds.add(acctId);
        while(CollectionUtils.isNotEmpty(directSubIds)){
            List<WeixinAcctEntity> sub = this.getSubAcct(directSubIds);
            directSubIds = new ArrayList<String>();
            if(CollectionUtils.isNotEmpty(sub)){
                for(WeixinAcctEntity entity : sub){
                    if(!"3".equals(entity.getAcctLevel())){
                        allSubs.add(entity);
                        directSubIds.add(entity.getId());
                    }
                }
            }
        }
        SubAcctRedisCache.setNotCAllSubAcct(acctId, allSubs);
        return allSubs;
    }
}