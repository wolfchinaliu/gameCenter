package weixin.tenant.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2015/12/4.
 */
@Entity
@Table(name = "MerchantAndGroup", schema = "")
@SuppressWarnings("serial")
@Inheritance(strategy = InheritanceType.JOINED)
public class MerchantAndGroupEntity extends IdEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 组id
     */
    @Excel(exportName = "组id")
    private java.lang.String groupId;
    /**
     * 商户id
     */
    @Excel(exportName = "商户id")
    private java.lang.String acctId;
    /**
     * 状态
     */
    @Excel(exportName = "状态")
    private java.lang.String status;
    /**
     * 加入时间
     */
    @Excel(exportName = "加入时间")
    private java.util.Date joinDate;

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String 组id
     */
    @Column(name = "GROUPID", nullable = true, length = 36)
    public String getGroupId() {
        return groupId;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String 组id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String 商户id
     */
    @Column(name = "ACCTID", nullable = true, length = 36)
    public String getAcctId() {
        return acctId;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String 商户id
     */
    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String 状态（是否加入）
     */
    @Column(name = "STATUS", nullable = true, length = 6)
    public String getStatus() {
        return status;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String 状态（是否加入）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  加入时间
     */
    @Column(name = "JOINDATE", nullable = true, length = 20)
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  加入时间
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
