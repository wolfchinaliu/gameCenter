package weixin.acctlist.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.acctlist.entity.WeixinacctListEntity;
import weixin.acctlist.service.WeixinacctListServiceI;

import java.io.Serializable;

/**
 * Created by aa on 2016/6/13.
 */
@Service("WeixinacctListServiceImpl")
@Transactional
public class WeixinacctListServiceImpl extends CommonServiceImpl implements WeixinacctListServiceI{
    public <T> void delete(T entity){
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((WeixinacctListEntity)entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((WeixinacctListEntity)entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((WeixinacctListEntity)entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     * @param t
     * @return
     */
    public boolean doAddSql(WeixinacctListEntity t){
        return true;
    }
    /**
     * 默认按钮-sql增强-更新操作
     * @param t
     * @return
     */
    public boolean doUpdateSql(WeixinacctListEntity t){
        return true;
    }
    /**
     * 默认按钮-sql增强-删除操作
     * @param t
     * @return
     */
    public boolean doDelSql(WeixinacctListEntity t){
        return true;
    }
}
