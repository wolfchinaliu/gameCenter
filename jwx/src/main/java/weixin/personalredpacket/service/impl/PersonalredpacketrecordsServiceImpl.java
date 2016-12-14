package weixin.personalredpacket.service.impl;

import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.personalredpacket.entity.PersonalredpacketrecordsEntity;
import weixin.personalredpacket.service.PersonalredpacketrecordsServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2016/1/26.
 */
@Service("personalredpacketrecordsService")
@Transactional
public class PersonalredpacketrecordsServiceImpl extends CommonServiceImpl implements PersonalredpacketrecordsServiceI {
    @Autowired
    private JdbcDao jdbcDao;

    public <T> void delete(T entity) {
        super.delete(entity);
        // 执行删除操作配置的sql增强
        this.doDelSql((PersonalredpacketrecordsEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        // 执行新增操作配置的sql增强
        this.doAddSql((PersonalredpacketrecordsEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        // 执行更新操作配置的sql增强
        this.doUpdateSql((PersonalredpacketrecordsEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @return
     */
    public boolean doAddSql(PersonalredpacketrecordsEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @return
     */
    public boolean doUpdateSql(PersonalredpacketrecordsEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @return
     */
    public boolean doDelSql(PersonalredpacketrecordsEntity t) {
        return true;
    }

    @Override
    public Integer getCount(String sql) {
        return jdbcDao.countByJdbc(sql, null);
    }

}
