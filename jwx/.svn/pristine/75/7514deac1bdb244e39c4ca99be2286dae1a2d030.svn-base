package weixin.liuliangbao.jsonbean.Riddles;

import java.util.Date;

/**
 * Created by aa on 2016/1/20.
 */
//猜灯谜的接收bean
public class RiddleBean implements java.io.Serializable {
    /**主键*/
    private java.lang.String id;
    /**创建人名称*/
    private java.lang.String createName;
    /**创建日期*/
    private java.util.Date createDate;
    /**活动名称*/
    private java.lang.String title;
    /**活动描述*/
    private java.lang.String description;
    private java.lang.String status;   //状态表示活动的状态

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private java.lang.Double getFlow;    //等于总的减去剩余的流量
    private java.lang.Double allFlow;   //活动流量值，回答正确的时候，提交的时候不仅提交用户获取流量值并且进行相关的存储
    //    private java.lang.Double shareFlow;   //分享流量
    private java.lang.Double riddleFlow;  //每道题的流量
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
    private String flowtype;   //1:省内流量 2：全国流量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGetFlow() {
        return getFlow;
    }

    public void setGetFlow(Double getFlow) {
        this.getFlow = getFlow;
    }

    public Double getAllFlow() {
        return allFlow;
    }

    public void setAllFlow(Double allFlow) {
        this.allFlow = allFlow;
    }

    public Double getRiddleFlow() {
        return riddleFlow;
    }

    public void setRiddleFlow(Double riddleFlow) {
        this.riddleFlow = riddleFlow;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getFlowtype() {
        return flowtype;
    }

    public void setFlowtype(String flowtype) {
        this.flowtype = flowtype;
    }
}
