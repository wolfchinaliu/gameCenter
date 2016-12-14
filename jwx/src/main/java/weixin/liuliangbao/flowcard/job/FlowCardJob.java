package weixin.liuliangbao.flowcard.job;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.liuliangbao.flowcard.Entity.FlowCardBatchEntity;
import weixin.liuliangbao.flowcard.Service.FlowCardInfoServiceI;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.util.DataDictionaryUtil.FlowType;

@Service("flowCardJob")
public class FlowCardJob {
    private static final Logger logger = Logger.getLogger(FlowCardJob.class);
    @Autowired
    private FlowCardInfoServiceI flowCardInfoService;
    @Autowired
    private SystemService systemService;

    public void expireFlowCard() {
        // 查询过期的流量卡批次
        List<FlowCardBatchEntity> expiredCardBatch = this.flowCardInfoService.queryExpiredCardBatch();
        if (expiredCardBatch != null && expiredCardBatch.size() != 0) {
            Map<String, FlowCardBatchEntity> expiredCardBatchMap = new HashMap<String, FlowCardBatchEntity>();
            for (FlowCardBatchEntity batch : expiredCardBatch) {
                batch.setIsValid("否");
                expiredCardBatchMap.put(batch.getId(), batch);
            }
            // 根据批次查询过期的流量卡的流量值，回退流量卡流量到商户账户流量上
            List<Map> expiredFlowCardInfo = this.flowCardInfoService.queryExpiredCardInfo(expiredCardBatchMap.keySet());
            if (expiredFlowCardInfo != null && expiredFlowCardInfo.size() != 0) {
                for (Map m : expiredFlowCardInfo) {
                    String batchId = MapUtils.getString(m, "batchId");
                    Double flowValue = MapUtils.getDouble(m, "flowvalue");
                    FlowCardBatchEntity batch = expiredCardBatchMap.get(batchId);
                    weixinAcctFlowAccountEntity merchantFlowAccount = this.systemService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId",
                            batch.getAcctId());

                    if (batch.getFlowType().equals(FlowType.country.getCode())) {
                        if (merchantFlowAccount.getCountryFlowCardValue() < flowValue) {
                            logger.error(MessageFormat.format("流量卡账户有误:merchantId{0}-流量类型{1}-需要回退流量{2}-流量卡账户余额{3}", batch.getAcctId(), batch.getFlowType(),
                                    flowValue, merchantFlowAccount.getCountryFlowCardValue()));
                        }
                        merchantFlowAccount.setCountryFlowCardValue(merchantFlowAccount.getCountryFlowCardValue() - flowValue);
                        merchantFlowAccount.setCountryFlowValue(merchantFlowAccount.getCountryFlowValue() + flowValue);
                    } else if(batch.getFlowType().equals(FlowType.province.getCode())){
                        if (merchantFlowAccount.getProvinceFlowCardValue() < flowValue) {
                            logger.error(MessageFormat.format("流量卡账户有误:merchantId{0}-流量类型{1}-需要回退流量{2}-流量卡账户余额{3}", batch.getAcctId(), batch.getFlowType(),
                                    flowValue, merchantFlowAccount.getCountryFlowCardValue()));
                        }
                        merchantFlowAccount.setProvinceFlowCardValue(merchantFlowAccount.getProvinceFlowCardValue() - flowValue);
                        merchantFlowAccount.setProvinceFlowValue(merchantFlowAccount.getProvinceFlowValue() + flowValue);
                    }
                    this.systemService.saveOrUpdate(merchantFlowAccount);
                }
            }
            this.flowCardInfoService.batchSave(expiredCardBatch);
            this.flowCardInfoService.expiredCardInfoByBatchId(expiredCardBatchMap.keySet());
        }
    }
}
