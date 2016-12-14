package weixin.liuliangbao.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.liuliangbao.jsonbean.WeixinMainEntity;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/14.
 */
public interface WeixinMainServiceI extends CommonService {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinMainEntity t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinMainEntity t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinMainEntity t);
}
