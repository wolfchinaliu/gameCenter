package weixin.personalredpacket.service.impl;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ApplicationContextUtil;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.lottery.service.WeixinLotteryServiceI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aa on 2016/3/2.
 */
public class LotteryThread extends Thread {
    public static final transient Logger LOGGER = Logger.getLogger(LotteryThread.class);

    @Override
    public void run(){

        WeixinLotteryServiceI weixinLotteryService=(WeixinLotteryServiceI) ApplicationContextUtil.getContext().getBean("weixinLotteryService");

        while (!this.isInterrupted()){

                try{
                    Thread.sleep(5000);

                    List<WeixinLotteryEntity> lotteryEntityList=new ArrayList<WeixinLotteryEntity>();
                    lotteryEntityList=weixinLotteryService.findHql("from WeixinLotteryEntity t where t.state='1' or t.state='2'");
                    if(lotteryEntityList.size()>0){

                        for (WeixinLotteryEntity lotteryEntity :lotteryEntityList){
                            Date date = new Date();
                            switch (lotteryEntity.getState()){
                                case "1":
                                    if(date.getTime()>lotteryEntity.getEndtime().getTime()){
                                        lotteryEntity.setState("0");
                                        weixinLotteryService.saveOrUpdate(lotteryEntity);
                                        LOGGER.info("lottery 活动由进行中改成已结束。hdid="+lotteryEntity.getId());
                                    }
                                    break;
                                case "2":
                                    if(date.getTime()>lotteryEntity.getStarttime().getTime()){
                                        lotteryEntity.setState("1");
                                        weixinLotteryService.saveOrUpdate(lotteryEntity);
                                        LOGGER.info("lottery 活动由尚未开始改成进行中。hdid="+lotteryEntity.getId());
                                    }
                                    break;
                                default:
                                    break;

                            }

                        }

                    }


                }catch (InterruptedException e){
                    e.printStackTrace();
                }
        }

    }
}
