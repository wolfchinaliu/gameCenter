package weixin.weicar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: 车系
 * @author onlineGenerator
 * @date 2015-05-24 15:38:18
 * @version V1.0
 *
 */
@Entity
@Table(name = "car_series", schema = "")
@SuppressWarnings("serial")
public class CarSeriesEntity implements java.io.Serializable {
	/** 主键 */
	private java.lang.String id;
	/** 创建人名称 */
	private java.lang.String createName;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 所属品牌 */
	// private java.lang.String carBrandId;
	private CarBrandEntity carBrandEntity;
	/** 车系名称 */
	private java.lang.String name;
	/** 车系简称 */
	@Excel(exportName = "车系简称")
	private java.lang.String shortName;
	/** 图片 */
	@Excel(exportName = "图片")
	private java.lang.String picture;
	/** 显示顺序 */
	@Excel(exportName = "显示顺序")
	private java.lang.Integer sorts;
	/** 车系介绍 */
	@Excel(exportName = "车系介绍")
	private java.lang.String seriesIntroduction;
	/** 微信主表主键 */
	@Excel(exportName = "微信主表主键")
	private java.lang.String accountid;

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 36)
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 主键
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 创建人名称
	 */
	@Column(name = "CREATE_NAME", nullable = true, length = 50)
	public java.lang.String getCreateName() {
		return this.createName;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 创建人名称
	 */
	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}

	/**
	 * 方法: 取得java.util.Date
	 *
	 * @return: java.util.Date 创建日期
	 */
	@Column(name = "CREATE_DATE", nullable = true, length = 20)
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 *
	 * @param: java.util.Date 创建日期
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 方法: 所属品牌
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR_BRAND_ID", nullable = false)
	public CarBrandEntity getCarBrandEntity() {
		return carBrandEntity;
	}

	public void setCarBrandEntity(CarBrandEntity carBrandEntity) {
		this.carBrandEntity = carBrandEntity;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 车系名称
	 */
	@Column(name = "NAME", nullable = false, length = 50)
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 车系名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 车系简称
	 */
	@Column(name = "SHORT_NAME", nullable = false, length = 32)
	public java.lang.String getShortName() {
		return this.shortName;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 车系简称
	 */
	public void setShortName(java.lang.String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 图片
	 */
	@Column(name = "PICTURE", nullable = true, length = 32)
	public java.lang.String getPicture() {
		return this.picture;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 图片
	 */
	public void setPicture(java.lang.String picture) {
		this.picture = picture;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 *
	 * @return: java.lang.Integer 显示顺序
	 */
	@Column(name = "SORTS", nullable = false, length = 32)
	public java.lang.Integer getSorts() {
		return this.sorts;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 *
	 * @param: java.lang.Integer 显示顺序
	 */
	public void setSorts(java.lang.Integer sorts) {
		this.sorts = sorts;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 车系介绍
	 */
	@Column(name = "SERIES_INTRODUCTION", nullable = true, length = 200)
	public java.lang.String getSeriesIntroduction() {
		return this.seriesIntroduction;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 车系介绍
	 */
	public void setSeriesIntroduction(java.lang.String seriesIntroduction) {
		this.seriesIntroduction = seriesIntroduction;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 微信主表主键
	 */
	@Column(name = "ACCOUNTID", nullable = true, length = 32)
	public java.lang.String getAccountid() {
		return this.accountid;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 微信主表主键
	 */
	public void setAccountid(java.lang.String accountid) {
		this.accountid = accountid;
	}
}
