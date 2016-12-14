package weixin.customer.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 客服
 * Created by xiaochun on 2015/12/7.
 */
@Entity
@Table(name = "weixin_customerlxc", schema = "")
public class WeixinCustomerLxcEntity  implements java.io.Serializable {
    /**主键*/
    private String id;
    /**头像*/
    private String headImage;
    /**客服账号名称*/
    private String kfAccount;
    /**客服昵称*/
    private String nickName;
    /**密码*/
    private String password;
    /**所属账户*/
    private String accountId;
    /**创建人*/
    private String createName;
    /**创建时间*/
    private java.util.Date createTime;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="id",nullable=false,length=36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name ="headimage",nullable=true,length=1000)
    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    @Column(name ="kfaccount",nullable=true,length=255)
    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    @Column(name ="nickname",nullable=true,length=255)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name ="password",nullable=true,length=255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name ="accoutid",nullable=true,length=36)
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Column(name ="createname",nullable=true,length=255)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Column(name ="createtime",nullable=true,length=20)
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
}
