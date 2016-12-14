package weixin.mailmanager.Service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import weixin.mailmanager.Service.MailManagerServiceI;
import weixin.mailmanager.entity.OperationData;
import java.io.Serializable;

/**
 * Created by aa on 2016/6/6.
 */
public class MailManagerServiceImpl extends CommonServiceImpl implements MailManagerServiceI {


    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((OperationData)entity);
        return t;
    }

    /**
     * 默认按钮-sql增强-新增操作
     * @param id
     * @return
     */
    public boolean doAddSql(OperationData t){
        return true;
    }


}
