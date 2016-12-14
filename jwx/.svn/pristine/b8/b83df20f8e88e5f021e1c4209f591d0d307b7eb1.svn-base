package weixin.payment.service.impl;
import weixin.payment.service.WeixinPaymentConServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import weixin.payment.entity.WeixinPaymentConEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinPaymentConService")
@Transactional
public class WeixinPaymentConServiceImpl extends CommonServiceImpl implements WeixinPaymentConServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinPaymentConEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinPaymentConEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinPaymentConEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinPaymentConEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinPaymentConEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinPaymentConEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinPaymentConEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{pay_name}",String.valueOf(t.getPayName()));
 		sql  = sql.replace("#{pay_description}",String.valueOf(t.getPayDescription()));
 		sql  = sql.replace("#{pay_type}",String.valueOf(t.getPayType()));
 		sql  = sql.replace("#{app_id}",String.valueOf(t.getAppId()));
 		sql  = sql.replace("#{cert_file_name}",String.valueOf(t.getCertFileName()));
 		sql  = sql.replace("#{partner_key}",String.valueOf(t.getPartnerKey()));
 		sql  = sql.replace("#{partner_id}",String.valueOf(t.getPartnerId()));
 		sql  = sql.replace("#{partner}",String.valueOf(t.getPartner()));
 		sql  = sql.replace("#{seller_email}",String.valueOf(t.getSellerEmail()));
 		sql  = sql.replace("#{seller_key}",String.valueOf(t.getSellerKey()));
 		sql  = sql.replace("#{bargainor_id}",String.valueOf(t.getBargainorId()));
 		sql  = sql.replace("#{bargainor_key}",String.valueOf(t.getBargainorKey()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}