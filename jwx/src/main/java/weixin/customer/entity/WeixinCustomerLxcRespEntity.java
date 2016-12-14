package weixin.customer.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xiaochun on 2015/12/7.
 */
@Entity
@Table(name = "weixin_customerlxcresp", schema = "")
public class WeixinCustomerLxcRespEntity implements java.io.Serializable {
    /**主键*/
    private String id;
    /**客服关键字*/
    private String keyWord;
    /**客服默认回复*/
    private String content;
    /**创建时间*/
    private java.util.Date addTime;
    /**所属账户*/
    private String accountId;

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

    @Column(name ="keyword",nullable=true,length=255)
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Column(name ="content",nullable=true,length=1024)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name ="addtime",nullable=true,length=20)
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Column(name ="accountid",nullable=true,length=36)
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
