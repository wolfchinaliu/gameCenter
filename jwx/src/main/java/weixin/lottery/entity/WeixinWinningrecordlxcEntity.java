package weixin.lottery.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**
 * 中奖纪录-刘晓春-2015年12月11日
 */
@Entity
@Table(name = "weixin_winningrecordlxc", schema = "")
@SuppressWarnings("serial")
public class WeixinWinningrecordlxcEntity implements java.io.Serializable {
	/** 主键id */
	private String id;
	/** openID */
	private String openid;
	/** 昵称*/
	private String nickname;

	@Column(name = "NICKNAME", nullable = true, length = 50)
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/** 手机 */
	private String mobile;
	/** 获奖等级 */
	private String prizelevel;
	/** 获奖流量 */
	private String prizevalue;
	/** 角度*/
	private Integer angle;
	/** 中奖时间 */
	private Date addtime;
	/** 活动ID */
	private String hdid;
	/** accountid */
	private String accountid;
	/** 状态 见 weixinConstants*/
	private String status;

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

    /**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String
	 *             id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 方法: 取得java.util.Date
	 *
	 * @return: java.util.Date 添加时间
	 */
	@Column(name = "ADDTIME", nullable = true)
	public Date getAddtime() {
		return this.addtime;
	}

	/**
	 * 方法: 设置java.util.Date
	 *
	 * @param: java.util.Date
	 *             添加时间
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 活动ID
	 */
	@Column(name = "HDID", nullable = true, length = 100)
	public String getHdid() {
		return this.hdid;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String
	 *             活动ID
	 */
	public void setHdid(String hdid) {
		this.hdid = hdid;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 手机
	 */
	@Column(name = "MOBILE", nullable = true, length = 100)
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String
	 *             手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 中奖人
	 */
	@Column(name = "OPENID", nullable = true, length = 255)
	public String getOpenid() {
		return this.openid;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String
	 *             中奖人
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String accountid
	 */
	@Column(name = "ACCOUNTID", nullable = true, length = 100)
	public String getAccountid() {
		return this.accountid;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String
	 *             accountid
	 */
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	@Column(name = "PRIZELEVEL", nullable = true, length = 100)
	public String getPrizelevel() {
		return prizelevel;
	}

	public void setPrizelevel(String prizelevel) {
		this.prizelevel = prizelevel;
	}

	@Column(name = "PRIZEVALUE", nullable = true, length = 11)
	public String getPrizevalue() {
		return prizevalue;
	}

	public void setPrizevalue(String prizevalue) {
		this.prizevalue = prizevalue;
	}

	@Column(name = "ANGLE", nullable = true, length = 11)
	public Integer getAngle() {
		return angle;
	}

	public void setAngle(Integer angle) {
		this.angle = angle;
	}

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
