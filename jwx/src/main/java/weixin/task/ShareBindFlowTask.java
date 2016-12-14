package weixin.task;

import org.apache.log4j.Logger;
import org.jeecgframework.web.demo.service.test.SchedualableTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import weixin.liuliangbao.service.flowcontroller.ShareService;
import weixin.report.service.UserGiveFlowHistoryServiceI;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 定时删除用户在未绑定手机号码之前的流量获取记录
 * 将流量获取记录移动到单独的一张表中，并物理删除原始的记录信息
 *
 */
@Service("shareBindFlowTask")
public class ShareBindFlowTask implements SchedualableTask {
    private static final transient Logger LOGGER = Logger.getLogger(ShareBindFlowTask.class);
    @Autowired
    private ShareService shareService;

	public void schedualJob() {
        try {
            LOGGER.debug("定时任务： 处理分享页面访问记录, 赠送分享人流量 - 开始");
            this.shareService.handleShareBindRecords();
        } catch (Exception e) {
            LOGGER.debug("定时任务： 处理分享页面访问记录, 赠送分享人流量 - 执行失败", e);
        }
        LOGGER.debug("定时任务： 处理分享页面访问记录, 赠送分享人流量 - 结束");
    }

}
