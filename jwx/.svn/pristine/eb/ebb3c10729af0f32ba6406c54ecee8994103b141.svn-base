package weixin.safetyRules.service.impl;

import java.util.Date;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.safetyRules.entity.SafetyRulesEntity;
import weixin.safetyRules.entity.WeixinRuleRecordsEntity;
import weixin.safetyRules.service.SafetyRulesRecordsServiceI;
import weixin.util.DateUtils;

@Service("safetyRulesRecordsService")
@Transactional
public class SafetyRulesRecordsServiceImpl  extends CommonServiceImpl implements SafetyRulesRecordsServiceI {
    @Override
    public WeixinRuleRecordsEntity createRecords(SafetyRulesEntity entity){
        WeixinRuleRecordsEntity record = new WeixinRuleRecordsEntity();
        Date now = new Date();
        record.setAcctId(entity.getAcctid());
        record.setRuleId(entity.getId());
        record.setSurplusNum(entity.getFrequencyNum());
        record.setPeriod(DateUtils.getPeriod(entity.getFrequencyUnit(), now));
        record.setCreateDate(now);
        this.commonDao.save(record);
        return record;
    }
}
