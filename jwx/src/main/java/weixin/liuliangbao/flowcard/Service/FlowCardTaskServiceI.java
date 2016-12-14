package weixin.liuliangbao.flowcard.Service;

import weixin.liuliangbao.flowcard.Entity.FlowCardTaskEntity;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/21.
 */
public interface FlowCardTaskServiceI {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(FlowCardTaskEntity t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(FlowCardTaskEntity t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(FlowCardTaskEntity t);
}
