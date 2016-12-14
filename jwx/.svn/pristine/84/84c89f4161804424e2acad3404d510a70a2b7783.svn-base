package weixin.member.service.impl;
import weixin.member.service.WeixinCoinJournalServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import weixin.member.entity.WeixinCoinJournalEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinCoinJournalService")
@Transactional
public class WeixinCoinJournalServiceImpl extends CommonServiceImpl implements WeixinCoinJournalServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinCoinJournalEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinCoinJournalEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinCoinJournalEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinCoinJournalEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinCoinJournalEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinCoinJournalEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinCoinJournalEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{coin}",String.valueOf(t.getCoin()));
 		sql  = sql.replace("#{unique_code}",String.valueOf(t.getUniqueCode()));
 		sql  = sql.replace("#{notes}",String.valueOf(t.getNotes()));
 		sql  = sql.replace("#{openid}",String.valueOf(t.getMemberid()));
 		sql  = sql.replace("#{creater_name}",String.valueOf(t.getCreaterName()));
 		sql  = sql.replace("#{deal_type}",String.valueOf(t.getDealType()));
 		sql  = sql.replace("#{deal_date}",String.valueOf(t.getDealDate()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}