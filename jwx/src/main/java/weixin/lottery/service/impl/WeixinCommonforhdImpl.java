package weixin.lottery.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.lottery.entity.WeixinCommonforhdEntity;
import weixin.lottery.service.WeixinCommonforhdI;

import java.io.Serializable;

/**
 * Created by aa on 2016/1/21.
 */
@Service("weixinCommonforhd")
@Transactional
public class WeixinCommonforhdImpl extends CommonServiceImpl implements WeixinCommonforhdI {
    public <T> void delete(T entity) {
        super.delete(entity);
        // 执行删除操作配置的sql增强
        this.doDelSql((WeixinCommonforhdEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        // 执行新增操作配置的sql增强
        this.doAddSql((WeixinCommonforhdEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        // 执行更新操作配置的sql增强
        this.doUpdateSql((WeixinCommonforhdEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinCommonforhdEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinCommonforhdEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinCommonforhdEntity t) {
        return true;
    }
}
