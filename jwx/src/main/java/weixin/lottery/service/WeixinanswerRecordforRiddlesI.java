package weixin.lottery.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.lottery.entity.WeixinanswerRecordforRiddlesEntity;

import java.io.Serializable;

/**
 * Created by aa on 2016/1/21.
 */
public interface WeixinanswerRecordforRiddlesI extends CommonService {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinanswerRecordforRiddlesEntity t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinanswerRecordforRiddlesEntity t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinanswerRecordforRiddlesEntity t);


}
