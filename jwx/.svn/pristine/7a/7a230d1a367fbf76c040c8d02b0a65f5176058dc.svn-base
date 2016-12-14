package weixin.payment.service.impl;
import weixin.payment.service.WeixinUsergetcardServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import weixin.payment.entity.WeixinUsergetcardEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinUsergetcardService")
@Transactional
public class WeixinUsergetcardServiceImpl extends CommonServiceImpl implements WeixinUsergetcardServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinUsergetcardEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinUsergetcardEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinUsergetcardEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinUsergetcardEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinUsergetcardEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinUsergetcardEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinUsergetcardEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{open_id}",String.valueOf(t.getOpenId()));
 		sql  = sql.replace("#{card_id}",String.valueOf(t.getCardId()));
 		sql  = sql.replace("#{user_card_code}",String.valueOf(t.getUserCardCode()));
 		sql  = sql.replace("#{by_friend}",String.valueOf(t.getByFriend()));
 		sql  = sql.replace("#{outer_id}",String.valueOf(t.getOuterId()));
 		sql  = sql.replace("#{friend_user}",String.valueOf(t.getFriendUser()));
 		sql  = sql.replace("#{old_card_code}",String.valueOf(t.getOldCardCode()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{consume_time}",String.valueOf(t.getConsumeTime()));
 		sql  = sql.replace("#{consume_source}",String.valueOf(t.getConsumeSource()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}