package weixin.liuliangbao.jsonbean.AccountGroup;

import java.util.Date;

/**
 * Created by aa on 2015/12/16.
 */
public class acctAndGroup implements java.io.Serializable {
    private java.lang.String id;
    private java.lang.Integer counts;
    private java.lang.String groupName;
    private java.lang.String groupType;
    private java.lang.String tenantId;   //创建的租户id
    private java.util.Date foundedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Date getFoundedTime() {
        return foundedTime;
    }

    public void setFoundedTime(Date foundedTime) {
        this.foundedTime = foundedTime;
    }
}
