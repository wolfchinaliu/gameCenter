package weixin.tenant.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by aa on 2015/12/16.(管理员微信账号)
 */
@Entity
@Table(name = "WxAdmin", schema = "")
@SuppressWarnings("serial")
public class WeixinAdminEntity implements java.io.Serializable {
    private java.lang.String id;
    private java.lang.String accountCurId;
    private java.lang.String accountId;
    private java.lang.String status;

    @Column(name = "STATUS", nullable = false, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "ACCOUNTCURID", nullable = false, length = 36)
    public String getAccountCurId() {
        return accountCurId;
    }

    public void setAccountCurId(String accountCurId) {
        this.accountCurId = accountCurId;
    }

    @Column(name = "ACCOUNTID", nullable = false, length = 36)
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
