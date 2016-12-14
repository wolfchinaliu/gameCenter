package weixin.liuliangbao.jsonbean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by GuoLiang on 2016/7/13 10:59.
 */
@Entity
@Table(name = "weixin_share_access")
public class WeixinShareAccess implements Serializable {
    private String id; // UUID主键',
    private String sharerOpenId; // 分享人石榴openId',
    private String viewerOpenId; // 查看人石榴openId',
    private String accountId; // 商户Id',
    private String shareId; // 分享文章Id',
    private Date createTime; // 创建时间/分享时间',
    private Date updateTime; // 更新时间
    private Double flowValue; // 流量币值',
    private String flowType; // 流量类型',
    private Integer status; // 流量领取状态',
    private Long accessTimes = 1L; // 此人访问分享页面次数

    @Id
    @GeneratedValue(generator = "shareAccesstableGenerator")
    @GenericGenerator(name = "shareAccesstableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "sharer_open_id", nullable = false, length = 36)
    public String getSharerOpenId() {
        return sharerOpenId;
    }

    public void setSharerOpenId(String sharerOpenId) {
        this.sharerOpenId = sharerOpenId;
    }

    @Column(name = "viewer_open_id", nullable = false, length = 36)
    public String getViewerOpenId() {
        return viewerOpenId;
    }

    public void setViewerOpenId(String viewerOpenId) {
        this.viewerOpenId = viewerOpenId;
    }

    @Column(name = "account_id", nullable = false, length = 36)
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Column(name = "share_id", nullable = false, length = 36)
    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "flow_value")
    public Double getFlowValue() {
        return flowValue;
    }

    public void setFlowValue(Double flowValue) {
        this.flowValue = flowValue;
    }

    @Column(name = "flow_type", length = 64)
    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    @Column(name = "status", length = 1)
    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "access_times", length = 20)
    public Long getAccessTimes() {
        return accessTimes;
    }

    public void setAccessTimes(Long accessTimes) {
        this.accessTimes = accessTimes;
    }
}
