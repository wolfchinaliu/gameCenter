package org.jeecgframework.web.system.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.service.TSbaseUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/4.
 */
@Service("tSbaseUserService")
@Transactional
public class TSbaseUserServiceImpl extends CommonServiceImpl implements TSbaseUserService {

    public <T> void delete(T entity) {
        super.delete(entity);
        this.doDelSql((TSBaseUser) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        this.doAddSql((TSBaseUser) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        this.doUpdateSql((TSBaseUser) entity);
    }

    /**
     * @return
     */
    public boolean doAddSql(TSBaseUser t) {
        return true;
    }

    /**
     * @return
     */
    public boolean doUpdateSql(TSBaseUser t) {
        return true;
    }

    /**
     * @return
     */
    public boolean doDelSql(TSBaseUser t) {
        return true;
    }
}
