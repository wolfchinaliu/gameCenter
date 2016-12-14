package weixin.report.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.report.model.UserGiveFlowEntity;
import weixin.report.model.UserGiveFlowHistoryEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by guoliang on 2016/4/12.
 */
public interface UserGiveFlowHistoryServiceI extends CommonService {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     * @param entity
     * @return
     */
    public boolean doAddSql(UserGiveFlowHistoryEntity entity);
    /**
     * 默认按钮-sql增强-更新操作
     * @param entity
     * @return
     */
    public boolean doUpdateSql(UserGiveFlowHistoryEntity entity);
    /**
     * 默认按钮-sql增强-删除操作
     * @param entity
     * @return
     */
    public boolean doDelSql(UserGiveFlowHistoryEntity entity);

    /**
     * 定时删除用户赠送的流量记录到历史纪录表中
     *
     * @return
     * @param start
     * @param end
     */
    public void removeExpiredUserFlowGiveRecords(Date start, Date end);
}
