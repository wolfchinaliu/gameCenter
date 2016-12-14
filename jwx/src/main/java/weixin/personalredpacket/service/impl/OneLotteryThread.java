package weixin.personalredpacket.service.impl;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ApplicationContextUtil;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.lottery.service.WeixinLotteryServiceI;

import java.util.Date;
import java.util.List;

/**
 * Created by aa on 2016/3/2.
 */
public class OneLotteryThread extends Thread {
    public static final transient Logger LOGGER = Logger.getLogger(OneLotteryThread.class);
    @Override
    public  void run(){

        WeixinLotteryServiceI weixinLotteryService=(WeixinLotteryServiceI) ApplicationContextUtil.getContext().getBean("weixinLotteryService");

        while (!this.isInterrupted()){

            try{
                Thread.sleep(5000);

                List<WeixinLotteryEntity> weixinLotteryEntityList =weixinLotteryService.findHql("from WeixinLotteryEntity t where t.state is NULL");
                if(weixinLotteryEntityList.size()>0){
                    for(WeixinLotteryEntity weixinLotteryEntity :weixinLotteryEntityList){
                        Date date=new Date();
                        if(date.getTime()<weixinLotteryEntity.getEndtime().getTime()){
                            if(date.getTime()<weixinLotteryEntity.getStarttime().getTime()){
                                weixinLotteryEntity.setState("2");
                                weixinLotteryService.saveOrUpdate(weixinLotteryEntity);
                                LOGGER.info("之前数据状态由空改为尚未开始，修改成功，hdid="+weixinLotteryEntity.getId());
                            }else {
                                weixinLotteryEntity.setState("1");
                                weixinLotteryService.saveOrUpdate(weixinLotteryEntity);
                                LOGGER.info("之前数据状态由空改为进行中，修改成功，hdid=" + weixinLotteryEntity.getId());
                            }
                        }else{
                            weixinLotteryEntity.setState("0");
                            weixinLotteryService.saveOrUpdate(weixinLotteryEntity);
                            LOGGER.info("之前的数据状态由空改为已结束，修改成功，hdid="+weixinLotteryEntity.getId());
                        }
                    }
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }


    }
}
