package weixin.mailmanager.Service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.mailmanager.entity.OperationData;

import java.io.Serializable;

/**
 * Created by aa on 2016/6/6.
 */
public interface MailManagerServiceI extends CommonService {

    public <T> Serializable save(T entity);

   public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     * @param id
     * @return
     */
    public boolean doAddSql(OperationData t);

}
