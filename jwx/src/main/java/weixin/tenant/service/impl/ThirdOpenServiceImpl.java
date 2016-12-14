package weixin.tenant.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.tenant.entity.ThirdOpenServiceEntity;
import weixin.tenant.service.ThirdOpenServiceI;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by aa on 2015/12/23.
 */
@Service("thirdOpenService")
@Transactional
public class ThirdOpenServiceImpl extends CommonServiceImpl implements ThirdOpenServiceI {

    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((ThirdOpenServiceEntity)entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((ThirdOpenServiceEntity)entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((ThirdOpenServiceEntity)entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     * @param id
     * @return
     */
    public boolean doAddSql(ThirdOpenServiceEntity t){
        return true;
    }
    /**
     * 默认按钮-sql增强-更新操作
     * @param id
     * @return
     */
    public boolean doUpdateSql(ThirdOpenServiceEntity t){
        return true;
    }
    /**
     * 默认按钮-sql增强-删除操作
     * @param id
     * @return
     */
    public boolean doDelSql(ThirdOpenServiceEntity t){
        return true;
    }




}
