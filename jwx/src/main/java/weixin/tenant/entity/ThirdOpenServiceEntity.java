package weixin.tenant.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description:
 * @date 2015-04-25 21:14:50
 */
@Entity
@Table(name = "ThirdOpenService", schema = "")
public class ThirdOpenServiceEntity implements java.io.Serializable {
    /**
     * 主键
     */
    private java.lang.String id;

    private java.lang.String accountId;

    private java.lang.String apiId;

    private java.lang.String apiKey;

    private java.lang.String accountIp;

    @Column(name = "ACCOUNTID", nullable = false, length = 40)
    public String getAccountId() {
        return accountId;
    }

    @Column(name = "ACCOUNTIP", nullable = true, length = 100)
    public String getAccountIp() {
        return accountIp;
    }

    @Column(name = "APIID", nullable = false, length = 20)
    public String getApiId() {
        return apiId;
    }

    @Column(name = "APIKEY", nullable = false, length = 100)
    public String getApiKey() {
        return apiKey;
    }

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setAccountIp(String accountIp) {
        this.accountIp = accountIp;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setId(String id) {
        this.id = id;
    }




}
