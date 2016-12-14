package weixin.mailmanager;

import org.apache.log4j.Logger;
import weixin.mailmanager.mailHelper.MailUitl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.jeecgframework.web.demo.service.test.SchedualableTask;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xiaona on 2016/4/27.
 */
@Service("TimeFixedSend")
public class TimeFixedSend{
    public static final transient Logger LOGGER = Logger.getLogger(TimeFixedSend.class);

    MailUitl mailUitl = new MailUitl();


    public void schedualJob(){
        LOGGER.info("开始执行指定任务.");
        //调用发送邮件的功能
        mailUitl.sendMail("dayMail");
        LOGGER.info("上一次的任务还未结束");
    }



    public void weekSchedualJob(){
        LOGGER.info("开始执行指定任务.");
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;//每周

        String type = "";
        if (week == 1) {
            type = "weekMail";
            //调用发送邮件的功能
            mailUitl.sendMail(type);
        }
        LOGGER.info("上一次的任务还未结束");
    }



    public void monthSchedualJob(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        LOGGER.info("开始执行指定任务.");
        String type = "";
        if (day == 1) {
            type = "monthMail";
            //调用发送邮件的功能
            mailUitl.sendMail(type);
        }
        LOGGER.info("上一次的任务还未结束");
    }

}
