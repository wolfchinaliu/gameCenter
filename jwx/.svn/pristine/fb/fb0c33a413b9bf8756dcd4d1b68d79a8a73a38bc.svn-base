package weixin.mailmanager;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.mailmanager.entity.VersionEntity;
import weixin.mailmanager.mailHelper.MailUitl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by xiaona on 2016/5/9.
 * 邮件管理之版本管理，日周月邮件发送，还有运维邮件的发送
 */
@Scope("prototype")
@Controller
@RequestMapping("/mailManagerController")
public class MailManagerController extends BaseController{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(MailManagerController.class);


    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(params = "goMailVersion")
    public ModelAndView goMailVersion(HttpServletRequest request,HttpServletResponse response) {
        return new ModelAndView("weixin/mailmanager/mail_versionManager");
    }

    @RequestMapping(params = "sendVersionMail")
    @ResponseBody
    public Boolean sendVersionMail(HttpServletRequest request,HttpServletResponse response){
        MailUitl mailUitl = new MailUitl();
        String emailTo=request.getParameter("emailTo");
        String mailSubject= request.getParameter("mailSubject");
        String versionnum=request.getParameter("versionNo");
        String publishtime=request.getParameter("publishDate");
        String addcontent= request.getParameter("addContent");
        String improvecontent= request.getParameter("improveContent");
        String deletecontent= request.getParameter("deleteContent");

        VersionEntity versionEntity= new VersionEntity();
        versionEntity.setMailTo(emailTo);
        versionEntity.setVersionNO(versionnum);
        versionEntity.setPublishDate(publishtime);
        versionEntity.setAddContent(addcontent);
        versionEntity.setImproveContent(improvecontent);
        versionEntity.setDeleteContent(deletecontent);
        versionEntity.setMainsubject(mailSubject);

        boolean result =mailUitl.sendVersionMail(versionEntity);

        return result;
    }

    @RequestMapping(params = "goDayMail")
    public ModelAndView goDayMail(HttpServletRequest request,HttpServletResponse response){
        return new ModelAndView("weixin/mailmanager/mail_dayManager");
    }
}

