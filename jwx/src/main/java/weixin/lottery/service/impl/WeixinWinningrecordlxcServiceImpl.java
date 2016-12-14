package weixin.lottery.service.impl;

import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.lottery.entity.WeixinWinningrecordEntity;
import weixin.lottery.entity.WeixinWinningrecordlxcEntity;
import weixin.lottery.service.WeixinWinningrecordServiceI;
import weixin.lottery.service.WeixinWinningrecordlxcServiceI;

import java.io.Serializable;
import java.util.UUID;

@Service("weixinWinningrecordlxcService")
@Transactional
public class WeixinWinningrecordlxcServiceImpl extends CommonServiceImpl implements WeixinWinningrecordlxcServiceI {

	@Autowired
	private JdbcDao jdbcDao;

	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((WeixinWinningrecordlxcEntity) entity);
	}

	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		// 执行新增操作配置的sql增强
		this.doAddSql((WeixinWinningrecordlxcEntity) entity);
		return t;
	}

	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((WeixinWinningrecordlxcEntity) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(WeixinWinningrecordlxcEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(WeixinWinningrecordlxcEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(WeixinWinningrecordlxcEntity t) {
		return true;
	}

	/**
	 * 替换sql中的变量
	 * 
	 * @param sql
	 * @return
	 */
	public String replaceVal(String sql, WeixinWinningrecordlxcEntity t) {

		return sql;
	}

	public Integer getCount(String sql) {
		return jdbcDao.countByJdbc(sql, null);
	}

	public void detWeixinWinningrecordByHdid(String hdid) {

		String hql = " delete from  weixin_winningrecordlxc where hdid='" + hdid + "' ";

		this.updateBySqlString(hql);
	}
}