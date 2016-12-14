package weixin.tenant.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.tenant.entity.WeixinAdminEntity;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/17.
 */
public interface WeixinAdminServiceI extends CommonService {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinAdminEntity t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinAdminEntity t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinAdminEntity t);
}
