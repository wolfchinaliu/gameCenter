package weixin.lottery.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2016/1/20.
 */
@Entity
@Table(name = "weixin_riddleWinningList", schema = "")
@SuppressWarnings("serial")
public class WeixinriddleWinningListEntity implements java.io.Serializable {
    /** 主键id */
    private String id;
    /** openID */
    private String openid;
    /** 昵称*/
    private String nickname;
    /** 中奖时间 */
    private Date addtime;
    /** 手机 */
    private String mobile;

    private Double winedFlow;

    /** 活动ID */
    private String hdid;

    /** accountid */
    private String accountid;
    private String iShareOr;   //答案是否正确

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
    @Column(name = "OPENID", nullable = false, length = 100)
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
    @Column(name = "NICKNAME", nullable = false, length = 50)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @Column(name = "ADDTIME", nullable = false, length = 36)
    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
    @Column(name = "MOBILE", nullable = false, length = 100)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @Column(name = "WINEDFLOW", nullable = false, length = 12)
    public Double getWinedFlow() {
        return winedFlow;
    }

    public void setWinedFlow(Double winedFlow) {
        this.winedFlow = winedFlow;
    }
    @Column(name = "HDID", nullable = false, length = 36)
    public String getHdid() {
        return hdid;
    }

    public void setHdid(String hdid) {
        this.hdid = hdid;
    }
    @Column(name = "ACCOUNTID", nullable = false, length = 36)
    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
    @Column(name = "ISHAREOR", nullable = false, length = 2)
    public String getiShareOr() {
        return iShareOr;
    }

    public void setiShareOr(String iShareOr) {
        this.iShareOr = iShareOr;
    }
}
