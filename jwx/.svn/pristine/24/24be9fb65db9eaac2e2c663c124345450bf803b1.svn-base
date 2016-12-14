package weixin.safetyRules.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeewx.api.core.util.WeiXinConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.safetyRules.entity.SafetyRulesEntity;
import weixin.safetyRules.entity.WeixinRuleRecordsEntity;
import weixin.safetyRules.service.SafetyRulesServiceI;
import weixin.util.DateUtils;
import weixin.util.WeiXinConstants;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by aa on 2016/3/21.
 */
@Service("safetyRulesService")
@Transactional
public class SafetyRulesServiceImpl extends CommonServiceImpl implements SafetyRulesServiceI {

    public <T> void delete(T entity) {
        super.delete(entity);
        // 执行删除操作配置的sql增强
        this.doDelSql((SafetyRulesEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        // 执行新增操作配置的sql增强
        this.doAddSql((SafetyRulesEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        // 执行更新操作配置的sql增强
        this.doUpdateSql((SafetyRulesEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(SafetyRulesEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(SafetyRulesEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(SafetyRulesEntity t) {
        return true;
    }

    @Override
    public boolean isComplyRules(String acctId, String ruleId, Date date, double flowValue) {
        SafetyRulesEntity ruleEntity = this.commonDao.get(SafetyRulesEntity.class, ruleId);
        if (ruleEntity == null) {
            return false;
        }
        String period = DateUtils.getPeriod(ruleEntity.getFrequencyUnit(), date);
        if (period == null) {
            return false;
        }
        WeixinRuleRecordsEntity exampleEntity = new WeixinRuleRecordsEntity();
        exampleEntity.setAcctId(acctId);
        exampleEntity.setRuleId(ruleId);
        exampleEntity.setPeriod(period);
        List<WeixinRuleRecordsEntity> list = this.commonDao.findByExample(null, exampleEntity);
        if (list == null || list.size() == 0) {
            //没有的话创建
        	exampleEntity.setCreateDate(new Date());
        	exampleEntity.setSurplusNum(ruleEntity.getFrequencyNum());
        	this.commonDao.saveOrUpdate(exampleEntity);
        	 if (exampleEntity.getSurplusNum() < 1) {
                 return false;
             }
             return true;
        }
        WeixinRuleRecordsEntity entity = list.get(0);
        if (entity.getSurplusNum() < 1) {
            return false;
        }
        return true;
    }

    @Override
    public void reduceNum(String acctId, String ruleId, Date date) {
        SafetyRulesEntity ruleEntity = this.commonDao.get(SafetyRulesEntity.class, ruleId);
        String period = DateUtils.getPeriod(ruleEntity.getFrequencyUnit(), date);
        this.commonDao.executeSql(
                "update weixin_rules_records e set e.surplusNum = e.surplusNum-1 where e.ruleId=? and e.period=? and e.acctId=?", ruleId,
                period, acctId);
    }

    @Override
    public SafetyRulesEntity getSafetyRuleById(String ruleId){
        SafetyRulesEntity ruleEntity = this.commonDao.get(SafetyRulesEntity.class, ruleId);
        return ruleEntity;
    }
}
