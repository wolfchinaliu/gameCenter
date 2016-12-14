package weixin.personalredpacket.service.impl;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.system.service.SystemService;
import weixin.lottery.entity.WeixinGuessRiddleEntity;
import weixin.lottery.service.WeixinGuessRiddleServiceI;
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
 * Created by aa on 2016/2/17.
 */
public class RiddleThread extends Thread {
    public static final transient Logger LOGGER = Logger.getLogger(RiddleThread.class);
    @Override
    public void run() {

        SystemService systemService = (SystemService) ApplicationContextUtil.getContext().getBean("systemService");
        WeixinGuessRiddleServiceI weixinGuessRiddleService = (WeixinGuessRiddleServiceI) ApplicationContextUtil.getContext().getBean("weixinGuessRiddleService");  //灯谜设置service
        WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutService = (WeixinAcctFlowAccoutServiceI) ApplicationContextUtil.getContext().getBean("WeixinAcctFlowAccoutService");   //商户账户service
        FlowCardTradeRecordsServiceI flowCardTradeRecordsService = (FlowCardTradeRecordsServiceI) ApplicationContextUtil.getContext().getBean("flowCardTradeRecordsService");  //充值记录保存表
        WeixinAcctServiceI weixinAcctService = (WeixinAcctServiceI) ApplicationContextUtil.getContext().getBean("weixinAcctService");  //充值记录保存表


        while (!this.isInterrupted()) {
            try {

                Thread.sleep(5000);

                List<WeixinGuessRiddleEntity> listRiddle = new ArrayList<>();
                String hql = "from WeixinGuessRiddleEntity t where t.state='1' or t.state='2'";
                listRiddle = weixinGuessRiddleService.findHql(hql, null);
                if (listRiddle.size() > 0) {
                    for (int i = 0; i < listRiddle.size(); i++) {

                        switch (listRiddle.get(i).getState()){
                            case "1":
                                if (listRiddle.get(i).getEndtime().getTime() < System.currentTimeMillis()) {
                                    listRiddle.get(i).setState("0");
                                    weixinGuessRiddleService.saveOrUpdate(listRiddle.get(i));

                                    LOGGER.info("灯谜线程更新成功，状态由进行中改为结束。");
                                    //查询剩余的流量制作红包的流量值

//                            根据活动cid查询商户的id
                                    String id = listRiddle.get(i).getId();

                                    WeixinGuessRiddleEntity riddleEntity = weixinGuessRiddleService.getEntity(WeixinGuessRiddleEntity.class, id);
                                    //     查询商户的流量账户
                                    weixinAcctFlowAccountEntity weixinAcctFlowAccount = weixinAcctFlowAccoutService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", riddleEntity.getAccountid());

//                                    根据id查询当前商户的商户名称
                                    WeixinAcctEntity weixinAcctEntity=weixinAcctService.getEntity(WeixinAcctEntity.class,weixinAcctFlowAccount.getTenantId());


                                    double flow=listRiddle.get(i).getGetFlow()==null ? 0 : listRiddle.get(i).getGetFlow();
                                    Double leftRiddleFlow = listRiddle.get(i).getAllFlow() - flow;  //剩余的猜灯谜的流量


                                    if (listRiddle.get(i).getFlowtype().equals(FlowType.country.getCode())) {
                                        weixinAcctFlowAccount.setCountryFlowValue(weixinAcctFlowAccount.getCountryFlowValue() + leftRiddleFlow);
                                        weixinAcctFlowAccoutService.saveOrUpdate(weixinAcctFlowAccount);
                                    } else {
                                        weixinAcctFlowAccount.setProvinceFlowValue(weixinAcctFlowAccount.getProvinceFlowValue() + leftRiddleFlow);
                                        weixinAcctFlowAccoutService.saveOrUpdate(weixinAcctFlowAccount);

                                    }
                                    /**
                                     * 保存的关于商户的流量币回收情况
                                     */
                                    FlowCardTradeRecordsEntity flowCardTradeRecordsEntity = new FlowCardTradeRecordsEntity();
                                    flowCardTradeRecordsEntity.setFlowValue(leftRiddleFlow);
                                    /**
                                     * 新增的状态位--2016年4月21日--xiaona
                                     */
                                    flowCardTradeRecordsEntity.setFlowSource("回收--灯谜");
                                    flowCardTradeRecordsEntity.setFlowtype(listRiddle.get(i).getFlowtype());
//                                    充值前流量值
                                    if(listRiddle.get(i).getFlowtype().equals(FlowType.country.getCode())){
                                        flowCardTradeRecordsEntity.setFlowQChargeBerf(weixinAcctFlowAccount.getCountryFlowValue()-leftRiddleFlow);
                                    }else {
                                        flowCardTradeRecordsEntity.setFlowPChargeBerf(weixinAcctFlowAccount.getProvinceFlowValue()-leftRiddleFlow);
                                    }
//                                    充值后流量值
                                    flowCardTradeRecordsEntity.setFlowQCharged(weixinAcctFlowAccount.getCountryFlowValue());
                                    flowCardTradeRecordsEntity.setFlowPCharged(weixinAcctFlowAccount.getProvinceFlowValue());
//                                    对应的相关的商户
                                     flowCardTradeRecordsEntity.setFromAccountname(weixinAcctEntity.getAcctForName());
                                     flowCardTradeRecordsEntity.setToAccountname(weixinAcctEntity.getAcctForName());
                                     flowCardTradeRecordsEntity.setCurOperator("自动到期回收");
                                     /**
                                     * 新增的状态位--2016年4月21日--xiaona
                                     */
                                    flowCardTradeRecordsEntity.setFromAcc_id(listRiddle.get(i).getAccountid());
                                    flowCardTradeRecordsEntity.setToAcc_id(listRiddle.get(i).getAccountid());
                                    flowCardTradeRecordsEntity.setTradingDate(new Date());
                                    flowCardTradeRecordsEntity.setTradeType(MerchantFlowTradeType.fallback_riddle.getCode());
                                    flowCardTradeRecordsService.save(flowCardTradeRecordsEntity);


                                }
                                break;
                            case "2":
                                if(listRiddle.get(i).getStarttime().getTime()<System.currentTimeMillis()){
                                    listRiddle.get(i).setState("1");
                                    weixinGuessRiddleService.saveOrUpdate(listRiddle.get(i));
                                    LOGGER.info("灯谜线程更新成功,状态由尚未开始变成进行中");
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
