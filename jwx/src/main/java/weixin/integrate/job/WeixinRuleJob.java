package weixin.integrate.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import weixin.safetyRules.entity.SafetyRulesEntity;
import weixin.safetyRules.entity.WeixinRuleRecordsEntity;
import weixin.safetyRules.service.SafetyRulesServiceI;
import weixin.util.DateUtils;
import weixin.util.WeiXinConstants;

@Service("weixinRuleJob")
public class WeixinRuleJob {
    @Autowired
    private SafetyRulesServiceI ruleService;

//    @Scheduled(cron = "0 0 0 * * ?")
    public void beginDayRules() {
        this.beginRules(WeiXinConstants.FREQUENCYUNIT_DAY);
    }

//    @Scheduled(cron = "0 0 0 * * 1")
    public void beginWeekRules() {
        this.beginRules(WeiXinConstants.FREQUENCYUNIT_WEEK);
    }

//    @Scheduled(cron = "0 0 0 1 * ?")
    public void beginMonthRules() {
        this.beginRules(WeiXinConstants.FREQUENCYUNIT_MONTH);
    }

//    @Scheduled(cron = "0 0 0 1 1 ?")
    public void beginYearRules() {
        this.beginRules(WeiXinConstants.FREQUENCYUNIT_YEAR);
    }

    private void beginRules(String frequencyUnit) {
        List<SafetyRulesEntity> ruleList = this.ruleService.findByProperty(SafetyRulesEntity.class, "frequencyUnit", frequencyUnit);
        if (ruleList == null || ruleList.size() == 0) {
            return;
        }
        List<WeixinRuleRecordsEntity> recordList = new ArrayList<WeixinRuleRecordsEntity>();
        for (SafetyRulesEntity rule : ruleList) {
            WeixinRuleRecordsEntity record = new WeixinRuleRecordsEntity();
            Date now = new Date();
            record.setAcctId(rule.getAcctid());
            record.setRuleId(rule.getId());
            record.setSurplusNum(rule.getFrequencyNum());
            record.setPeriod(DateUtils.getPeriod(frequencyUnit, now));
            record.setCreateDate(now);
            recordList.add(record);
        }
        this.ruleService.batchSave(recordList);
    }
}
