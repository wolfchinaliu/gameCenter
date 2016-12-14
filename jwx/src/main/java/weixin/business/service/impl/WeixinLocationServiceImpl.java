package weixin.business.service.impl;
import weixin.business.service.WeixinLocationServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import weixin.business.entity.WeixinLocationEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinLocationService")
@Transactional
public class WeixinLocationServiceImpl extends CommonServiceImpl implements WeixinLocationServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinLocationEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinLocationEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinLocationEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinLocationEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinLocationEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinLocationEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinLocationEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{business_name}",String.valueOf(t.getBusinessName()));
 		sql  = sql.replace("#{branch_name}",String.valueOf(t.getBranchName()));
 		sql  = sql.replace("#{province}",String.valueOf(t.getProvince()));
 		sql  = sql.replace("#{city}",String.valueOf(t.getCity()));
 		sql  = sql.replace("#{district}",String.valueOf(t.getDistrict()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{telephone}",String.valueOf(t.getTelephone()));
 		sql  = sql.replace("#{category}",String.valueOf(t.getCategory()));
 		sql  = sql.replace("#{longitude}",String.valueOf(t.getLongitude()));
 		sql  = sql.replace("#{latitude}",String.valueOf(t.getLatitude()));
 		sql  = sql.replace("#{recommend}",String.valueOf(t.getRecommend()));
 		sql  = sql.replace("#{special}",String.valueOf(t.getSpecial()));
 		sql  = sql.replace("#{introduction}",String.valueOf(t.getIntroduction()));
 		sql  = sql.replace("#{open_time}",String.valueOf(t.getOpenTime()));
 		sql  = sql.replace("#{avg_price}",String.valueOf(t.getAvgPrice()));
 		sql  = sql.replace("#{title_logo}",String.valueOf(t.getTitleLogo()));
 		sql  = sql.replace("#{qrcode_logo}",String.valueOf(t.getQrcodeLogo()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}