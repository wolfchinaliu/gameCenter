package weixin.tenant.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.tenant.entity.FlowCardTradeRecordsEntity;
import weixin.tenant.service.FlowCardTradeRecordsServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/2.
 */
@Service("flowCardTradeRecordsService")
@Transactional
public class FlowCardTradeRecordsServiceImpl extends CommonServiceImpl implements FlowCardTradeRecordsServiceI {
    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((FlowCardTradeRecordsEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((FlowCardTradeRecordsEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((FlowCardTradeRecordsEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @return
     */
    public boolean doAddSql(FlowCardTradeRecordsEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @return
     */
    public boolean doUpdateSql(FlowCardTradeRecordsEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @return
     */
    public boolean doDelSql(FlowCardTradeRecordsEntity t) {
        return true;
    }
}
