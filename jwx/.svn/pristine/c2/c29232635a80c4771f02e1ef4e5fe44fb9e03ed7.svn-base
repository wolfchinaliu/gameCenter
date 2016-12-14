package weixin.tenant.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.tenant.entity.MerchantAndGroupEntity;
import weixin.tenant.service.MerchantAndGroupEntityServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/4.
 */
@Service("merchantAndGroupEntityService")
@Transactional
public class MerchantAndGroupEntityServiceImpl extends CommonServiceImpl implements MerchantAndGroupEntityServiceI {
    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((MerchantAndGroupEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((MerchantAndGroupEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((MerchantAndGroupEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(MerchantAndGroupEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(MerchantAndGroupEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(MerchantAndGroupEntity t) {
        return true;
    }
}
