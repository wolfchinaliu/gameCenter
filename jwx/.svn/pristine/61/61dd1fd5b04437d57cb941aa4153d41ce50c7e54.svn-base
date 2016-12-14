package weixin.task;

import org.apache.log4j.Logger;
import org.jeecgframework.web.demo.service.test.SchedualableTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weixin.report.service.UserGiveFlowHistoryServiceI;

/**
 * 每天更新签到和分享人数为零
 * 
 *
 */
@Service("resetCurrentPeopleTask")
public class ResetCurrentPeopleTask implements SchedualableTask {
    private static final transient Logger LOGGER = Logger.getLogger(RemoveExpiredFlowTask.class);
   
    @Autowired 
    private UserGiveFlowHistoryServiceI userGiveFlowHistoryService;
	
	public void schedualJob() {
		String sql = "update merchantflowgiverules set currentPeople='0' where operateType='签到'";
       try {
           this.userGiveFlowHistoryService.updateBySqlString(sql);
       } catch (Exception e) {
           LOGGER.error("定时任务： 定时更新签到最大人数记录 - 执行失败", e);
       }
   
		
    }

}
