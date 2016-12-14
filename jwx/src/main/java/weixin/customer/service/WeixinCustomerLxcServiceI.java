package weixin.customer.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.customer.entity.WeixinCustomerLxcEntity;

import java.io.Serializable;

/**
 * Created by xiaochun on 2015/12/7.
 */
public interface WeixinCustomerLxcServiceI  extends CommonService {

    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinCustomerLxcEntity t);
    /**
     * 默认按钮-sql增强-更新操作
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinCustomerLxcEntity t);
    /**
     * 默认按钮-sql增强-删除操作
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinCustomerLxcEntity t);
}
