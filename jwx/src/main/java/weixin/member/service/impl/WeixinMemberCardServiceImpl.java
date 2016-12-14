package weixin.member.service.impl;
import weixin.member.service.WeixinMemberCardServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import weixin.member.entity.WeixinMemberCardEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinMemberCardService")
@Transactional
public class WeixinMemberCardServiceImpl extends CommonServiceImpl implements WeixinMemberCardServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinMemberCardEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinMemberCardEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinMemberCardEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinMemberCardEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinMemberCardEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinMemberCardEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinMemberCardEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{card_type}",String.valueOf(t.getCardType()));
 		sql  = sql.replace("#{prerogative}",String.valueOf(t.getPrerogative()));
 		sql  = sql.replace("#{supply_bonus}",String.valueOf(t.getSupplyBonus()));
 		sql  = sql.replace("#{logo_url}",String.valueOf(t.getLogoUrl()));
 		sql  = sql.replace("#{code_type}",String.valueOf(t.getCodeType()));
 		sql  = sql.replace("#{brand_name}",String.valueOf(t.getBrandName()));
 		sql  = sql.replace("#{title}",String.valueOf(t.getTitle()));
 		sql  = sql.replace("#{sub_title}",String.valueOf(t.getSubTitle()));
 		sql  = sql.replace("#{color}",String.valueOf(t.getColor()));
 		sql  = sql.replace("#{notice}",String.valueOf(t.getNotice()));
 		sql  = sql.replace("#{description}",String.valueOf(t.getDescription()));
 		sql  = sql.replace("#{quantity}",String.valueOf(t.getQuantity()));
 		sql  = sql.replace("#{end_timestamp}",String.valueOf(t.getEndTimestamp()));
 		sql  = sql.replace("#{begin_timestamp}",String.valueOf(t.getBeginTimestamp()));
 		sql  = sql.replace("#{location_id_list}",String.valueOf(t.getLocationIdList()));
 		sql  = sql.replace("#{get_limit}",String.valueOf(t.getGetLimit()));
 		sql  = sql.replace("#{can_give_friend}",String.valueOf(t.getCanGiveFriend()));
 		sql  = sql.replace("#{service_phone}",String.valueOf(t.getServicePhone()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}