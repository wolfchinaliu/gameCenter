package weixin.lottery.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: 中奖记录
 * @author onlineGenerator
 * @date 2015-02-05 15:53:42
 * @version V1.0
 *
 */
@Entity
@Table(name = "weixin_winningrecord", schema = "")
@SuppressWarnings("serial")
public class WeixinWinningrecordEntity implements java.io.Serializable {
	/** id */
	private java.lang.String id;
	/** 添加时间 */
	@Excel(exportName = "添加时间")
	private java.util.Date addtime;
	/** 活动ID */
	@Excel(exportName = "活动ID")
	private java.lang.String hdid;
	/** 手机 */
	@Excel(exportName = "手机")
	private java.lang.String mobile;
	/** 中奖人 */
	@Excel(exportName = "中奖人")
	private java.lang.String openid;
	/** 奖品 */
	@Excel(exportName = "奖品")
	private java.lang.String prize;
	/** accountid */
	@Excel(exportName = "accountid")
	private java.lang.String accountid;
	/** 省份 */
	@Excel(exportName = "省份")
	private java.lang.String province;
	/** 城市 */
	@Excel(exportName = "城市")
	private java.lang.String city;
	/** 地区 */
	@Excel(exportName = "地区")
	private java.lang.String district;
	/** 地址 */
	@Excel(exportName = "地址")
	private java.lang.String address;
	/** 邮编 */
	@Excel(exportName = "邮编")
	private java.lang.String zipcode;
	/** 收货人 */
	@Excel(exportName = "收货人")
	private java.lang.String name;

	private java.lang.Integer angle;

	private java.lang.String nickName;

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 100)
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 添加时间
	 */
	@Column(name = "ADDTIME", nullable = true)
	public java.util.Date getAddtime() {
		return this.addtime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date
	 *             添加时间
	 */
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 活动ID
	 */
	@Column(name = "HDID", nullable = true, length = 100)
	public java.lang.String getHdid() {
		return this.hdid;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             活动ID
	 */
	public void setHdid(java.lang.String hdid) {
		this.hdid = hdid;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 手机
	 */
	@Column(name = "MOBILE", nullable = true, length = 100)
	public java.lang.String getMobile() {
		return this.mobile;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             手机
	 */
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 中奖人
	 */
	@Column(name = "OPENID", nullable = true, length = 255)
	public java.lang.String getOpenid() {
		return this.openid;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             中奖人
	 */
	public void setOpenid(java.lang.String openid) {
		this.openid = openid;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 奖品
	 */
	@Column(name = "PRIZE", nullable = true, length = 100)
	public java.lang.String getPrize() {
		return this.prize;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             奖品
	 */
	public void setPrize(java.lang.String prize) {
		this.prize = prize;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String accountid
	 */
	@Column(name = "ACCOUNTID", nullable = true, length = 100)
	public java.lang.String getAccountid() {
		return this.accountid;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             accountid
	 */
	public void setAccountid(java.lang.String accountid) {
		this.accountid = accountid;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 省份
	 */
	@Column(name = "PROVINCE", nullable = true, length = 100)
	public java.lang.String getProvince() {
		return this.province;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             省份
	 */
	public void setProvince(java.lang.String province) {
		this.province = province;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 城市
	 */
	@Column(name = "CITY", nullable = true, length = 100)
	public java.lang.String getCity() {
		return this.city;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             城市
	 */
	public void setCity(java.lang.String city) {
		this.city = city;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 地区
	 */
	@Column(name = "DISTRICT", nullable = true, length = 100)
	public java.lang.String getDistrict() {
		return this.district;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             地区
	 */
	public void setDistrict(java.lang.String district) {
		this.district = district;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 地址
	 */
	@Column(name = "ADDRESS", nullable = true, length = 100)
	public java.lang.String getAddress() {
		return this.address;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             地址
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 邮编
	 */
	@Column(name = "ZIPCODE", nullable = true, length = 100)
	public java.lang.String getZipcode() {
		return this.zipcode;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             邮编
	 */
	public void setZipcode(java.lang.String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 收货人
	 */
	@Column(name = "NAME", nullable = true, length = 100)
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             收货人
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Integer getAngle() {
		return angle;
	}

	public void setAngle(java.lang.Integer angle) {
		this.angle = angle;
	}

	@Column(name = "nick_name", nullable = true, length = 100)
	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

}
