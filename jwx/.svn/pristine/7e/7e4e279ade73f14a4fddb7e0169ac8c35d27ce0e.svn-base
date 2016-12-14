package weixin.personalredpacket.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2016/1/26.
 */
@Entity
@Table(name = "weixin_personalredpacketrecords", schema = "")
@SuppressWarnings("serial")
public class PersonalredpacketrecordsEntity implements java.io.Serializable {
    /**
     * 主键
     */
    private String id;
    private Double redFlowValue;
    private String redpackId;
    private String redpacksetId;
    private String openId;
    private String accountId;
    private String phoneNumber;
    private String nickname;
    private Date createTime;

    @Column(name = "NICKNAME", nullable = false, length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "REDFLOWVALUE", nullable = false, length = 12)
    public Double getRedFlowValue() {
        return redFlowValue;
    }

    public void setRedFlowValue(Double redFlowValue) {
        this.redFlowValue = redFlowValue;
    }

    @Column(name = "REDPACKID", nullable = false, length = 36)
    public String getRedpackId() {
        return redpackId;
    }

    public void setRedpackId(String redpackId) {
        this.redpackId = redpackId;
    }

    @Column(name = "REDPACKSETID", nullable = false, length = 36)
    public String getRedpacksetId() {
        return redpacksetId;
    }

    public void setRedpacksetId(String redpacksetId) {
        this.redpacksetId = redpacksetId;
    }

    @Column(name = "OPENID", nullable = false, length = 36)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Column(name = "ACCOUNTID", nullable = false, length = 36)
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Column(name = "PHONENUMBER", nullable = false, length = 36)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "CREATETIME", nullable = false, length = 36)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
