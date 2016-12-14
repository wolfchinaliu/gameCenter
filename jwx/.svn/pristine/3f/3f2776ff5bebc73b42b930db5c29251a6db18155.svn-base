package weixin.personalredpacket.service.impl;

import org.apache.log4j.Logger;
import org.jeecgframework.core.extend.swftools.DocConverter;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.system.service.SystemService;
import weixin.liuliangbao.flowcard.Entity.FlowCardBatchEntity;
import weixin.liuliangbao.flowcard.Entity.FlowCardInfoEntity;
import weixin.liuliangbao.flowcard.Service.FlowCardBatchServiceI;
import weixin.liuliangbao.flowcard.Service.FlowCardInfoServiceI;
import weixin.tenant.entity.FlowCardTradeRecordsEntity;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.FlowCardTradeRecordsServiceI;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.tenant.service.WeixinAcctServiceI;
import weixin.util.DataDictionaryUtil.FlowType;
import weixin.util.DataDictionaryUtil.MerchantFlowTradeType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xiaona on 2016/4/22.
 * 流量卡到期后自动回收--只是回收到期后的流量值
 */
public class FlowCardThread extends Thread {
    public static final transient Logger LOGGER = Logger.getLogger(FlowCardThread.class);
    @Override
    public void run() {
        SystemService systemService = (SystemService) ApplicationContextUtil.getContext().getBean("systemService");  //使用这种方式获取到注入的服务
        FlowCardInfoServiceI flowCardInfoService=(FlowCardInfoServiceI)ApplicationContextUtil.getContext().getBean("flowCardInfoService"); //流量卡的相关的信息
        FlowCardBatchServiceI flowCardBatchService=(FlowCardBatchServiceI)ApplicationContextUtil.getContext().getBean("flowCardBatchService"); //流量卡批次获取

        WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutService = (WeixinAcctFlowAccoutServiceI) ApplicationContextUtil.getContext().getBean("WeixinAcctFlowAccoutService");   //流量账户service
        FlowCardTradeRecordsServiceI flowCardTradeRecordsService = (FlowCardTradeRecordsServiceI) ApplicationContextUtil.getContext().getBean("flowCardTradeRecordsService");  //交易记录service〃
        WeixinAcctServiceI weixinAcctService = (WeixinAcctServiceI) ApplicationContextUtil.getContext().getBean("weixinAcctService");  //商户管理service


        while (!this.isInterrupted()) {

            try {
                Thread.sleep(5000);
                List<FlowCardBatchEntity> flowCardInfoEntityList=new ArrayList<FlowCardBatchEntity>();
                String hql="from FlowCardBatchEntity f where f.isValid='0'";
                flowCardInfoEntityList=flowCardBatchService.findHql(hql,null);
                if(flowCardInfoEntityList.size()>0){
                    for (int i = 0; i <flowCardInfoEntityList.size() ; i++) {
                        FlowCardBatchEntity f=flowCardInfoEntityList.get(i);
                        Date taday=new Date();
                            if(taday.getTime()>f.getDisabledDate().getTime()){
                                LOGGER.info("过期了");
                                f.setIsValid("1");  //表示当前的批次都已经过期了

//                                此时需要将商户的剩余流量进行回收
                                String batchNo=f.getBatchNo();

                                String flowType=f.getFlowType();
                                //     查询商户的流量账户
                                weixinAcctFlowAccountEntity weixinAcctFlowAccount = weixinAcctFlowAccoutService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", f.getAcctId());

                                //
                                WeixinAcctEntity weixinAcctEntity=weixinAcctService.getEntity(WeixinAcctEntity.class,weixinAcctFlowAccount.getTenantId());

                                try {
                                    Double sendFlowCard=leftRecordSendFlow.sendFlowCard(batchNo);  //已经被领取的流量值
//                                    剩余流量值
                                    Double leftFlow=f.getPutFlowTotal()-sendFlowCard;

                                    Double countryValue=weixinAcctFlowAccount.getCountryFlowValue();
                                    Double provinceValue=weixinAcctFlowAccount.getProvinceFlowValue();

                                    if(FlowType.country.getCode().equals(flowType)){
                                        weixinAcctFlowAccount.setCountryFlowValue(countryValue+leftFlow);
                                    }else {
                                        weixinAcctFlowAccount.setCountryFlowValue(provinceValue+leftFlow);
                                    }

                                    weixinAcctFlowAccoutService.saveOrUpdate(weixinAcctFlowAccount);  //更新账户

                                    //添加流量币交易记录表
                                    FlowCardTradeRecordsEntity flowCardTradeRecordsEntity = new FlowCardTradeRecordsEntity();
                                    flowCardTradeRecordsEntity.setFlowValue(leftFlow);
                                    flowCardTradeRecordsEntity.setFlowtype(f.getFlowType());

                                    //                                   充值前的流量值
                                    if(f.getFlowType().equals(FlowType.country.getCode())){
                                        flowCardTradeRecordsEntity.setFlowQChargeBerf(weixinAcctFlowAccount.getCountryFlowValue()-leftFlow);
                                    }else {
                                        flowCardTradeRecordsEntity.setFlowPChargeBerf(weixinAcctFlowAccount.getProvinceFlowValue()-leftFlow);
                                    }
                                    //                                   充值后流量值
                                    flowCardTradeRecordsEntity.setFlowQCharged(weixinAcctFlowAccount.getCountryFlowValue());
                                    flowCardTradeRecordsEntity.setFlowPCharged(weixinAcctFlowAccount.getProvinceFlowValue());

                                    //                                    商户名字
                                    flowCardTradeRecordsEntity.setFromAccountname(weixinAcctEntity.getAcctForName());
                                    flowCardTradeRecordsEntity.setToAccountname(weixinAcctEntity.getAcctForName());
                                    flowCardTradeRecordsEntity.setCurOperator("到期后自动回收");
                                    /**
                                     * 状态位的改变--xiaona
                                     */
                                    flowCardTradeRecordsEntity.setFlowSource("回收--流量卡");

                                    /**
                                     * 状态位的改变--xiaona
                                     */
                                    flowCardTradeRecordsEntity.setFromAcc_id(f.getAcctId());
                                    flowCardTradeRecordsEntity.setToAcc_id(f.getAcctId());
                                    flowCardTradeRecordsEntity.setTradingDate(new Date());
                                    flowCardTradeRecordsEntity.setTradeType(MerchantFlowTradeType.fallback_flowcard.getCode());
                                    flowCardTradeRecordsService.save(flowCardTradeRecordsEntity);


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
