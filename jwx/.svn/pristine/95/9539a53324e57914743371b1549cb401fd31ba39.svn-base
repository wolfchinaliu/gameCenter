package weixin.safetyRules.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.safetyRules.entity.SafetyRulesEntity;
import weixin.safetyRules.entity.WeixinRuleRecordsEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aa on 2016/3/21.
 */
public interface SafetyRulesServiceI extends CommonService {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(SafetyRulesEntity t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(SafetyRulesEntity t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(SafetyRulesEntity t);

    public boolean isComplyRules(String acctId, String ruleId, Date date,double flowValue);

    void reduceNum(String acctId, String ruleId, Date date);

    public SafetyRulesEntity getSafetyRuleById(String ruleId);
}
