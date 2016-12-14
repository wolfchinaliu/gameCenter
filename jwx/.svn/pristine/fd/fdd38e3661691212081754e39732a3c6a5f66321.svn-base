package weixin.lottery.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.lottery.entity.WeixinanswerRecordforRiddlesEntity;
import weixin.lottery.service.WeixinanswerRecordforRiddlesI;

import java.io.Serializable;

/**
 * Created by aa on 2016/1/21.
 */
@Service("weixinanswerRecordforRiddles")
@Transactional
public class WeixinanswerRecordforRiddlesImpl extends CommonServiceImpl implements WeixinanswerRecordforRiddlesI {
    public <T> void delete(T entity) {
        super.delete(entity);
        // 执行删除操作配置的sql增强
        this.doDelSql((WeixinanswerRecordforRiddlesEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        // 执行新增操作配置的sql增强
        this.doAddSql((WeixinanswerRecordforRiddlesEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        // 执行更新操作配置的sql增强
        this.doUpdateSql((WeixinanswerRecordforRiddlesEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinanswerRecordforRiddlesEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinanswerRecordforRiddlesEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinanswerRecordforRiddlesEntity t) {
        return true;
    }
}
