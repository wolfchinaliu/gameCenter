package weixin.liuliangbao.flowcard.Service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.liuliangbao.flowcard.Entity.FlowCardDeliveryInfoEntity;
import weixin.liuliangbao.flowcard.Service.FlowCardDeliveryInfoServiceI;
import weixin.tenant.entity.FlowCardTradeRecordsEntity;
import weixin.tenant.service.FlowCardTradeRecordsServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/18.
 */
@Service("flowCardDeliveryInfoService")
@Transactional
public class FlowCardDeliveryInfoServiceImpl extends CommonServiceImpl implements FlowCardDeliveryInfoServiceI {
    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((FlowCardDeliveryInfoEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((FlowCardDeliveryInfoEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((FlowCardDeliveryInfoEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @return
     */
    public boolean doAddSql(FlowCardDeliveryInfoEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @return
     */
    public boolean doUpdateSql(FlowCardDeliveryInfoEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @return
     */
    public boolean doDelSql(FlowCardDeliveryInfoEntity t) {
        return true;
    }
}
