package weixin.customer.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.customer.entity.WeixinCustomerLxcRespEntity;
import weixin.customer.service.WeixinCustomerLxcRespServiceI;

import java.io.Serializable;

/**
 * Created by xiaochun on 2015/12/7.
 */
@Service("weixinCustomerLxcRespService")
@Transactional
public class WeixinCustomerLxcRespServiceImpl extends CommonServiceImpl implements WeixinCustomerLxcRespServiceI{
    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((WeixinCustomerLxcRespEntity)entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((WeixinCustomerLxcRespEntity)entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((WeixinCustomerLxcRespEntity)entity);
    }

    @Override
    public boolean doAddSql(WeixinCustomerLxcRespEntity t) {
        return false;
    }

    @Override
    public boolean doUpdateSql(WeixinCustomerLxcRespEntity t) {
        return false;
    }

    @Override
    public boolean doDelSql(WeixinCustomerLxcRespEntity t) {
        return false;
    }
}
