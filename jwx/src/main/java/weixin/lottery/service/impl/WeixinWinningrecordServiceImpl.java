package weixin.lottery.service.impl;

import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.lottery.entity.WeixinWinningrecordEntity;
import weixin.lottery.service.WeixinWinningrecordServiceI;

@Service("weixinWinningrecordService")
@Transactional
public class WeixinWinningrecordServiceImpl extends CommonServiceImpl implements WeixinWinningrecordServiceI {

	@Autowired
	private JdbcDao jdbcDao;

	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((WeixinWinningrecordEntity) entity);
	}

	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		// 执行新增操作配置的sql增强
		this.doAddSql((WeixinWinningrecordEntity) entity);
		return t;
	}

	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((WeixinWinningrecordEntity) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(WeixinWinningrecordEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(WeixinWinningrecordEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(WeixinWinningrecordEntity t) {
		return true;
	}

	/**
	 * 替换sql中的变量
	 * 
	 * @param sql
	 * @return
	 */
	public String replaceVal(String sql, WeixinWinningrecordEntity t) {
		sql = sql.replace("#{id}", String.valueOf(t.getId()));
		sql = sql.replace("#{addtime}", String.valueOf(t.getAddtime()));
		sql = sql.replace("#{hdid}", String.valueOf(t.getHdid()));
		sql = sql.replace("#{mobile}", String.valueOf(t.getMobile()));
		sql = sql.replace("#{openid}", String.valueOf(t.getOpenid()));
		sql = sql.replace("#{prize}", String.valueOf(t.getPrize()));
		sql = sql.replace("#{accountid}", String.valueOf(t.getAccountid()));
		sql = sql.replace("#{province}", String.valueOf(t.getProvince()));
		sql = sql.replace("#{city}", String.valueOf(t.getCity()));
		sql = sql.replace("#{district}", String.valueOf(t.getDistrict()));
		sql = sql.replace("#{address}", String.valueOf(t.getAddress()));
		sql = sql.replace("#{zipcode}", String.valueOf(t.getZipcode()));
		sql = sql.replace("#{name}", String.valueOf(t.getName()));
		sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
		return sql;
	}

	public Integer getCount(String sql) {
		return jdbcDao.countByJdbc(sql, null);
	}

	public void detWeixinWinningrecordByHdid(String hdid) {

		String hql = " delete from  weixin_winningrecord where hdid='" + hdid + "' ";

		this.updateBySqlString(hql);
	}
}