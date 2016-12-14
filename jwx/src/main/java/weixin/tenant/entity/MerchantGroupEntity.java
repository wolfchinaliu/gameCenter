package weixin.tenant.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2015/12/4.
 */
@Entity
@Table(name = "MerchantGroup", schema = "")
@SuppressWarnings("serial")
@PrimaryKeyJoinColumn(name = "id")
public class MerchantGroupEntity extends MerchantAndGroupEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 组代码
     */
    @Excel(exportName = "组代码")
    private java.lang.String groupCode;
    /**
     * 组名称
     */
    @Excel(exportName = "组名称")
    private java.lang.String groupName;
    /**
     * 组类型
     */
    @Excel(exportName = "组类型")
    private java.lang.String groupType;

    /**
     * 创建人
     */
    @Excel(exportName = "创建人")
    private java.lang.String founder;
    /**
     * 创建时间
     */
    @Excel(exportName = "创建时间")
    private java.util.Date foundedTime;
    /**
     * 组状态
     */
    @Excel(exportName = "组状态")
    private java.lang.String groupstatus;

    /**
     * 商户id
     */
    @Excel(exportName = "租户id")
    private java.lang.String tenantId;


//    private Integer numAccount;
//
//    public Integer getNumAccount() {
//        return numAccount;
//    }
//
//    public void setNumAccount(Integer numAccount) {
//        this.numAccount = numAccount;
//    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  组代码
     */
    @Column(name = "GROUPCODE", nullable = true, length = 20)
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  组代码
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  组名字
     */
    @Column(name = "GROUPNAME", nullable = true, length = 20)
    public String getGroupName() {
        return groupName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  组名字
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  组名字
     */
    @Column(name = "GROUPTYPE", nullable = true, length = 10)
    public String getGroupType() {
        return groupType;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  组类型
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人
     */
    @Column(name = "FOUNDER", nullable = true, length = 20)
    public String getFounder() {
        return founder;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人
     */
    public void setFounder(String founder) {
        this.founder = founder;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  创建日期
     */
    @Column(name = "FOUNDEDTIME", nullable = true, length = 20)
    public Date getFoundedTime() {
        return foundedTime;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  创建日期
     */
    public void setFoundedTime(Date foundedTime) {
        this.foundedTime = foundedTime;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  组状态
     */
    @Column(name = "STATUS", nullable = true, length = 20)
    public String getGroupstatus() {
        return groupstatus;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  组状态
     */
    public void setGroupstatus(String groupstatus) {
        this.groupstatus = groupstatus;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  租户id
     */
    @Column(name = "TENANTID", nullable = true, length = 36)
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  租户id
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
