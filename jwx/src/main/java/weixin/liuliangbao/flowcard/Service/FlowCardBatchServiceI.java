package weixin.liuliangbao.flowcard.Service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.liuliangbao.flowcard.Entity.FlowCardBatchEntity;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/18.
 */
public interface FlowCardBatchServiceI extends CommonService {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(FlowCardBatchEntity t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(FlowCardBatchEntity t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(FlowCardBatchEntity t);

}
