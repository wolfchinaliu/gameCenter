package weixin.task;

import org.apache.log4j.Logger;
import org.jeecgframework.web.demo.service.test.SchedualableTask;
import org.springframework.stereotype.Service;
import weixin.report.service.UserGiveFlowHistoryServiceI;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 定时删除用户在未绑定手机号码之前的流量获取记录
 * 将流量获取记录移动到单独的一张表中，并物理删除原始的记录信息
 *
 */
@Service("removeExpiredFlowTask")
public class RemoveExpiredFlowTask implements SchedualableTask {
    private static final transient Logger LOGGER = Logger.getLogger(RemoveExpiredFlowTask.class);

    @Resource
    private UserGiveFlowHistoryServiceI userGiveFlowHistoryService;
	
	public void schedualJob() {
        long now = System.currentTimeMillis();
         long oneDay = 24 * 3600 * 1000;
//        long oneDay = 30 * 60 * 1000;
        long end = now - oneDay;
        long start = end - oneDay;
        try {
            this.userGiveFlowHistoryService.removeExpiredUserFlowGiveRecords(new Date(start), new Date(end));
        } catch (Exception e) {
            LOGGER.error("定时任务： 定时删除用户在未绑定手机号码之前的流量获取记录 - 执行失败", e);
        }
    }

}
