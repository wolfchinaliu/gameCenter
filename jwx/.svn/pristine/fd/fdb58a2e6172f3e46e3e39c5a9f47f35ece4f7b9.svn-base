package weixin.weicar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: 车型
 * @author onlineGenerator
 * @date 2015-05-24 19:26:15
 * @version V1.0
 *
 */
@Entity
@Table(name = "car_vehicle_type", schema = "")
@SuppressWarnings("serial")
public class CarVehicleTypeEntity implements java.io.Serializable {
	/** 主键 */
	private java.lang.String id;
	/** 创建人名称 */
	private java.lang.String createName;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 车型名称 */
	private java.lang.String name;
	/** 所属车系 */
	// private java.lang.String carSeriesId;
	private CarSeriesEntity carSeriesEntity;
	/** 年款 */
	@Excel(exportName = "年款")
	private java.lang.String carOfYear;
	/** 指导价 */
	@Excel(exportName = "指导价")
	private java.lang.String price;
	/** 经销商报价 */
	@Excel(exportName = "经销商报价")
	private java.lang.String quote;
	/** 排量 */
	@Excel(exportName = "排量")
	private java.lang.Double carCc;
	@Excel(exportName = "排量单位")
	private java.lang.String carCcType;
	/** 档位个数 */
	@Excel(exportName = "档位个数")
	private java.lang.Integer carGearNum;
	/** 变速箱 */
	@Excel(exportName = "变速箱")
	private java.lang.String gearBox;
	private java.lang.Integer sorts;
	/** 微信主表ID */
	@Excel(exportName = "微信主表ID")
	private java.lang.String accountid;

	private List<CarVehicleTypePicEntity> listPic = new ArrayList<CarVehicleTypePicEntity>();

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
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 车型名称
	 */
	@Column(name = "NAME", nullable = true, length = 50)
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 车型名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR_SERIES_ID", nullable = false)
	public CarSeriesEntity getCarSeriesEntity() {
		return carSeriesEntity;
	}

	public void setCarSeriesEntity(CarSeriesEntity carSeriesEntity) {
		this.carSeriesEntity = carSeriesEntity;
	}
	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 年款
	 */
	@Column(name = "CAR_OF_YEAR", nullable = true, length = 32)
	public java.lang.String getCarOfYear() {
		return this.carOfYear;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 年款
	 */
	public void setCarOfYear(java.lang.String carOfYear) {
		this.carOfYear = carOfYear;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 指导价
	 */
	@Column(name = "PRICE", nullable = true, length = 32)
	public java.lang.String getPrice() {
		return this.price;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 指导价
	 */
	public void setPrice(java.lang.String price) {
		this.price = price;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 经销商报价
	 */
	@Column(name = "QUOTE", nullable = true, length = 32)
	public java.lang.String getQuote() {
		return this.quote;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 经销商报价
	 */
	public void setQuote(java.lang.String quote) {
		this.quote = quote;
	}

	/**
	 * 方法: 取得java.lang.Double
	 *
	 * @return: java.lang.Double 排量
	 */
	@Column(name = "CAR_CC", nullable = true, length = 32)
	public java.lang.Double getCarCc() {
		return this.carCc;
	}

	/**
	 * 方法: 设置java.lang.Double
	 *
	 * @param: java.lang.Double 排量
	 */
	public void setCarCc(java.lang.Double carCc) {
		this.carCc = carCc;
	}
	
	@Column(name = "CAR_CC_TYPE", nullable = true, length = 32)
	public java.lang.String getCarCcType() {
		return carCcType;
	}

	public void setCarCcType(java.lang.String carCcType) {
		this.carCcType = carCcType;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 *
	 * @return: java.lang.Integer 档位个数
	 */
	@Column(name = "CAR_GEAR_NUM", nullable = true, length = 32)
	public java.lang.Integer getCarGearNum() {
		return this.carGearNum;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 *
	 * @param: java.lang.Integer 档位个数
	 */
	public void setCarGearNum(java.lang.Integer carGearNum) {
		this.carGearNum = carGearNum;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 变速箱
	 */
	@Column(name = "GEAR_BOX", nullable = true, length = 32)
	public java.lang.String getGearBox() {
		return this.gearBox;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 变速箱
	 */
	public void setGearBox(java.lang.String gearBox) {
		this.gearBox = gearBox;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 微信主表ID
	 */
	@Column(name = "ACCOUNTID", nullable = false, length = 32)
	public java.lang.String getAccountid() {
		return this.accountid;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 微信主表ID
	 */
	public void setAccountid(java.lang.String accountid) {
		this.accountid = accountid;
	}
	
	
	public java.lang.Integer getSorts() {
		return sorts;
	}

	public void setSorts(java.lang.Integer sorts) {
		this.sorts = sorts;
	}

	@OneToMany(mappedBy = "carVehicleTypeEntity", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	@NotFound(action = NotFoundAction.IGNORE)
	public List<CarVehicleTypePicEntity> getListPic() {
		return listPic;
	}

	public void setListPic(List<CarVehicleTypePicEntity> listPic) {
		this.listPic = listPic;
	}

}
