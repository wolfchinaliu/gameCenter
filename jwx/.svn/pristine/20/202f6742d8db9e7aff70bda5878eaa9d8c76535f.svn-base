package weixin.personalredpacket.service.impl;

import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.personalredpacket.entity.PersonalRedpacketEntity;
import weixin.personalredpacket.entity.PersonalRedpacketSetEntity;
import weixin.personalredpacket.service.PersonalRedpacketServiceI;
import weixin.personalredpacket.service.PersonalRedpacketSetServiceI;

import java.io.Serializable;

@Service("personalRedpacketService")
@Transactional
public class PersonalRedpacketServiceImpl extends CommonServiceImpl implements PersonalRedpacketServiceI {
	@Autowired
	private JdbcDao jdbcDao;

	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((PersonalRedpacketEntity) entity);
	}

	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		// 执行新增操作配置的sql增强
		this.doAddSql((PersonalRedpacketEntity) entity);
		return t;
	}

	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((PersonalRedpacketEntity) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(PersonalRedpacketEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(PersonalRedpacketEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(PersonalRedpacketEntity t) {
		return true;
	}

	@Override
	public Integer getCount(String sql) {
		return jdbcDao.countByJdbc(sql, null);
	}


}