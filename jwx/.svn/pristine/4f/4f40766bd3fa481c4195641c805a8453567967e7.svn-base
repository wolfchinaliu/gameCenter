package weixin.report.service;

import net.sf.json.JSONObject;
import org.jeecgframework.core.common.service.CommonService;
import weixin.report.model.UserGiveFlowEntity;

import java.io.Serializable;

/**
 * Created by 晓春 on 2016/3/16.
 */
public interface UserGiveFlowServiceI extends CommonService {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(UserGiveFlowEntity t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(UserGiveFlowEntity t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(UserGiveFlowEntity t);

    /**
     * 赠送用户流量
     *
     * @return
     * @param openId
     * @param phoneNumber
     * @param accountId
     * @param operateType
     */
    public String giveUserFlow(String openId, String phoneNumber, String accountId, String operateType);

}
