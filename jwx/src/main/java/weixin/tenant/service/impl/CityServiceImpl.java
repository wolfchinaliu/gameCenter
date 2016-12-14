package weixin.tenant.service.impl;

import org.jeecgframework.core.common.service.impl.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.tenant.entity.CityEntity;
import weixin.tenant.service.CityServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2016/1/4.
 */
@Service("cityService")
@Transactional
public class CityServiceImpl extends org.jeecgframework.core.common.service.impl.CommonServiceImpl implements CityServiceI {
    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((CityEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((CityEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((CityEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(CityEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(CityEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(CityEntity t) {
        return true;
    }
}
