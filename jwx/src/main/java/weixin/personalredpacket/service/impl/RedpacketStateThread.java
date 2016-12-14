package weixin.personalredpacket.service.impl;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import weixin.member.controller.ConnectionsManager;
import weixin.personalredpacket.entity.PersonalRedpacketSetEntity;
import weixin.personalredpacket.service.PersonalRedpacketSetServiceI;
import weixin.tenant.entity.FlowCardTradeRecordsEntity;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.FlowCardTradeRecordsServiceI;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.tenant.service.WeixinAcctServiceI;
import weixin.util.DataDictionaryUtil.FlowType;
import weixin.util.DataDictionaryUtil.MerchantFlowTradeType;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by xiaona on 2016/1/30.
 */
public class RedpacketStateThread extends Thread {
    public static final transient Logger LOGGER = Logger.getLogger(RedpacketStateThread.class);
    @Override
    public void run() {

        SystemService systemService = (SystemService) ApplicationContextUtil.getContext().getBean("systemService");
        PersonalRedpacketSetServiceI personalRedpacketSetService = (PersonalRedpacketSetServiceI) ApplicationContextUtil.getContext().getBean("personalRedpacketSetService");  //红包个人设置的service
        WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutService = (WeixinAcctFlowAccoutServiceI) ApplicationContextUtil.getContext().getBean("WeixinAcctFlowAccoutService");   //流量账户service
        FlowCardTradeRecordsServiceI flowCardTradeRecordsService = (FlowCardTradeRecordsServiceI) ApplicationContextUtil.getContext().getBean("flowCardTradeRecordsService");  //交易记录service〃
        WeixinAcctServiceI weixinAcctService = (WeixinAcctServiceI) ApplicationContextUtil.getContext().getBean("weixinAcctService");  //商户管理service

        while (!this.isInterrupted()) {
            try {

                Thread.sleep(5000);

//                LOGGER.info("线程启动了");

                List<PersonalRedpacketSetEntity> personalRedpacketSetEntities = new ArrayList<>();
                String hql = "from PersonalRedpacketSetEntity t where t.state='1' or t.state='2'";
                personalRedpacketSetEntities = personalRedpacketSetService.findHql(hql, null);
                if (personalRedpacketSetEntities.size() > 0) {
                    for (int i = 0; i < personalRedpacketSetEntities.size(); i++) {

                        switch (personalRedpacketSetEntities.get(i).getState()){
                            case "1":
                                if (personalRedpacketSetEntities.get(i).getEndtime().getTime() < System.currentTimeMillis()) {
                                    personalRedpacketSetEntities.get(i).setState("0");
                                    personalRedpacketSetService.saveOrUpdate(personalRedpacketSetEntities.get(i));

                                    LOGGER.info("线程更新成功,红包状态由进行中改为结束+hdid="+personalRedpacketSetEntities.get(i).getId());
                                    //查询剩余的流量制作红包的流量值

//                            根据活动cid查询商户的id
                                    String id = personalRedpacketSetEntities.get(i).getId();

                                    PersonalRedpacketSetEntity personalRedpacket = personalRedpacketSetService.getEntity(PersonalRedpacketSetEntity.class, id);
                                    //     查询商户的流量账户
                                    weixinAcctFlowAccountEntity weixinAcctFlowAccount = weixinAcctFlowAccoutService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", personalRedpacket.getAccountid());

                                    //
                                    WeixinAcctEntity weixinAcctEntity=weixinAcctService.getEntity(WeixinAcctEntity.class,weixinAcctFlowAccount.getTenantId());

                                    Double leftRedpacketset = personalRedpacket.getSubsidyValue() - personalRedpacket.getFlowSendValue();  //流量值
// 红包的剩余计量
//                        leftRecordSendFlow ll=new leftRecordSendFlow();
                                    Double sendFlowValue = null;
                                    try {
                                        sendFlowValue = leftRecordSendFlow.sendFlow(id, personalRedpacket.getAccountid());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
//                                   个人红包回收总数
                                    Double selfFlow = personalRedpacket.getFlowSendValue() - sendFlowValue;


                                    Double totalValue = leftRedpacketset + selfFlow;
                                    if (personalRedpacket.getFlowtype().equals(FlowType.country.getCode())) {
                                        weixinAcctFlowAccount.setCountryFlowValue(weixinAcctFlowAccount.getCountryFlowValue() + totalValue);
                                        weixinAcctFlowAccoutService.saveOrUpdate(weixinAcctFlowAccount);
                                    } else {
                                        weixinAcctFlowAccount.setProvinceFlowValue(weixinAcctFlowAccount.getProvinceFlowValue() + totalValue);
                                        weixinAcctFlowAccoutService.saveOrUpdate(weixinAcctFlowAccount);

                                    }
                                    /**
                                     * 保存的关于商户的流量币回收情况
                                     */
                                    FlowCardTradeRecordsEntity flowCardTradeRecordsEntity = new FlowCardTradeRecordsEntity();
                                    flowCardTradeRecordsEntity.setFlowValue(totalValue);
                                    flowCardTradeRecordsEntity.setFlowtype(personalRedpacket.getFlowtype());


//                                   充值前的流量值
                                    if(personalRedpacket.getFlowtype().equals(FlowType.country.getCode())){
                                        flowCardTradeRecordsEntity.setFlowQChargeBerf(weixinAcctFlowAccount.getCountryFlowValue()-totalValue);
                                    }else {
                                        flowCardTradeRecordsEntity.setFlowPChargeBerf(weixinAcctFlowAccount.getProvinceFlowValue()-totalValue);
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
                                    flowCardTradeRecordsEntity.setFlowSource("回收--个人红包");
                                    /**
                                     * 状态位的改变--xiaona
                                     */
                                    flowCardTradeRecordsEntity.setFromAcc_id(personalRedpacket.getAccountid());
                                    flowCardTradeRecordsEntity.setToAcc_id(personalRedpacket.getAccountid());
                                    flowCardTradeRecordsEntity.setTradingDate(new Date());
                                    flowCardTradeRecordsEntity.setTradeType(MerchantFlowTradeType.fallback_redpacket.getCode());
                                    flowCardTradeRecordsService.save(flowCardTradeRecordsEntity);


                                }
                                break;
                            case "2":
                                if (personalRedpacketSetEntities.get(i).getStarttime().getTime() < System.currentTimeMillis()) {
                                    personalRedpacketSetEntities.get(i).setState("1");
                                    personalRedpacketSetService.saveOrUpdate(personalRedpacketSetEntities.get(i));
                                    LOGGER.info("线程更新成功,红包状态由尚未开始中改为进行中+hdid="+personalRedpacketSetEntities.get(i).getId());
                                }
                                break;
                            default:
                                break;

                        }


                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


