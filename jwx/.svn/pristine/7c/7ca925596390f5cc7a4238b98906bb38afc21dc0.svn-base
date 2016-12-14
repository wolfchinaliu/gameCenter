package weixin.liuliangbao.flowcard.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGridReturn;
import org.jeecgframework.core.common.service.CommonService;

import weixin.liuliangbao.flowcard.Entity.FlowCardBatchEntity;
import weixin.liuliangbao.flowcard.Entity.FlowCardInfoEntity;

/**
 * Created by aa on 2015/12/18.
 */
public interface FlowCardInfoServiceI extends CommonService {

    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(FlowCardInfoEntity t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(FlowCardInfoEntity t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(FlowCardInfoEntity t);

    /**
     * 将日期格式进行格式化为标准的
     * @param cq
     * @param isOffset
     * @return
     */
    public DataGridReturn getDataGridReturnConvert(final CriteriaQuery cq,
                                            final boolean isOffset);

    public List<FlowCardBatchEntity> queryExpiredCardBatch();

    public List<Map> queryExpiredCardInfo(Collection<String> expiredCardBatchId);

    public void expiredCardInfoByBatchId(Collection<String> batchIds);

}
