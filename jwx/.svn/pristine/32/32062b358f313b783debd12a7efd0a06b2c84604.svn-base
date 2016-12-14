package weixin.lottery.entity;

import org.jeecgframework.core.common.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统活动父类表
 * Created by aa on 2016/1/21.
 */
@Entity
@Table(name = "weixin_commonforhd")
@Inheritance(strategy = InheritanceType.JOINED)
public class WeixinCommonforhdEntity extends IdEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

//    /**
//     * 主键
//     */
//    private java.lang.String id;
    /**
     * 创建人名称
     */
    private java.lang.String createName;
    /**
     * 创建日期
     */
    private java.util.Date createDate;
    /**
     * 活动名称
     */
    private java.lang.String title;
    /**
     * 活动描述
     */
    private java.lang.String description;

    /**
     * 开始时间
     */
    private java.util.Date starttime;
    /**
     * 结束时间
     */
    private java.util.Date endtime;
    /**
     * 微信公众号
     */
    private java.lang.String accountid;
    /**
     * 流量类型
     */
    private String flowtype;   //1:全国流量 2：省内流量

    @Column(name = "CREATENAME", nullable = false, length = 50)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Column(name = "CREATEDATE", nullable = false, length = 36)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "TITLE", nullable = false, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "DESCRIPTION", nullable = false, length = 4000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "STARTTIME", nullable = false, length = 36)
    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    @Column(name = "ENDTIME", nullable = false, length = 36)
    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    @Column(name = "ACCOUNTID", nullable = false, length = 36)
    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    @Column(name = "FLOWTYPE", nullable = false, length = 36)
    public String getFlowtype() {
        return flowtype;
    }

    public void setFlowtype(String flowtype) {
        this.flowtype = flowtype;
    }

}
