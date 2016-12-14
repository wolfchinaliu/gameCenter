package weixin.liuliangbao.flowcard.Service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.liuliangbao.flowcard.Entity.FlowCardTaskEntity;
import weixin.liuliangbao.flowcard.Service.FlowCardTaskServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/21.
 */
@Service("flowCardTaskService")
@Transactional
public class FlowCardTaskServiceImpl extends CommonServiceImpl implements FlowCardTaskServiceI {
    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((FlowCardTaskEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((FlowCardTaskEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((FlowCardTaskEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @return
     */
    public boolean doAddSql(FlowCardTaskEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @return
     */
    public boolean doUpdateSql(FlowCardTaskEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @return
     */
    public boolean doDelSql(FlowCardTaskEntity t) {
        return true;
    }
}
