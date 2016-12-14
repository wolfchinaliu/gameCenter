package weixin.mailmanager.entity;

import java.util.Date;

/**
 * Created by 晓春 on 2016/5/9.
 */
public class VersionEntity {

    private java.lang.String mailTo;
    private java.lang.String mainsubject;

    /**
     * 邮件的内容
     */
    private java.lang.String versionNO;//版本发布的版本号
    private java.lang.String publishDate;//版本发布的发布时间
    private java.lang.String addContent; //版本管理中内容的改进
    private java.lang.String improveContent;//版本管理中内容的改进
    private java.lang.String deleteContent;//版本管理中内容的改进

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMainsubject() {
        return mainsubject;
    }

    public void setMainsubject(String mainsubject) {
        this.mainsubject = mainsubject;
    }

    public String getVersionNO() {
        return versionNO;
    }

    public void setVersionNO(String versionNO) {
        this.versionNO = versionNO;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getAddContent() {
        return addContent;
    }

    public void setAddContent(String addContent) {
        this.addContent = addContent;
    }

    public String getImproveContent() {
        return improveContent;
    }

    public void setImproveContent(String improveContent) {
        this.improveContent = improveContent;
    }

    public String getDeleteContent() {
        return deleteContent;
    }

    public void setDeleteContent(String deleteContent) {
        this.deleteContent = deleteContent;
    }
}
