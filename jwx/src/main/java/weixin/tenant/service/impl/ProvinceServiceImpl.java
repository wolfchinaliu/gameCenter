package weixin.tenant.service.impl;

import org.jeecgframework.core.common.service.impl.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.tenant.entity.ProvinceEntity;
import weixin.tenant.service.ProvinceServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2016/1/3.
 */
@Service("provinceService")
@Transactional
public class ProvinceServiceImpl extends org.jeecgframework.core.common.service.impl.CommonServiceImpl implements ProvinceServiceI {
    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((ProvinceEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((ProvinceEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((ProvinceEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(ProvinceEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(ProvinceEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(ProvinceEntity t) {
        return true;
    }
}
