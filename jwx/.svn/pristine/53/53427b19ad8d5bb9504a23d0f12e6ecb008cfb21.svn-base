package weixin.acctlist.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.acctlist.entity.WeixinacctListEntity;

import java.io.Serializable;

/**
 * Created by aa on 2016/6/13.
 */
public interface WeixinacctListServiceI extends CommonService{
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     * @param t
     * @return
     */
    public boolean doAddSql(WeixinacctListEntity t);
    /**
     * 默认按钮-sql增强-更新操作
     * @param t
     * @return
     */
    public boolean doUpdateSql(WeixinacctListEntity t);
    /**
     * 默认按钮-sql增强-删除操作
     * @param t
     * @return
     */
    public boolean doDelSql(WeixinacctListEntity t);
}
