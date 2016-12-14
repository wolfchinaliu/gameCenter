package weixin.customer.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.customer.entity.WeixinCustomerLxcEntity;
import weixin.customer.service.WeixinCustomerLxcServiceI;

import java.io.Serializable;

/**
 * Created by xiaochun on 2015/12/7.
 */
@Service("weixinCustomerLxcService")
@Transactional
public class WeixinCustomerLxcServiceImpl  extends CommonServiceImpl implements WeixinCustomerLxcServiceI{

    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((WeixinCustomerLxcEntity)entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((WeixinCustomerLxcEntity)entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((WeixinCustomerLxcEntity)entity);
    }


    @Override
    public boolean doAddSql(WeixinCustomerLxcEntity t) {
        return false;
    }

    @Override
    public boolean doUpdateSql(WeixinCustomerLxcEntity t) {
        return false;
    }

    @Override
    public boolean doDelSql(WeixinCustomerLxcEntity t) {
        return false;
    }
}
