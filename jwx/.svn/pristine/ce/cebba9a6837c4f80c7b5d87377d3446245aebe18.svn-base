package weixin.guanjia.menu.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: 微信菜单组
 * @author onlineGenerator
 * @date 2015-01-24 17:53:27
 * @version V1.0
 *
 */
@Entity
@Table(name = "weixin_menu_group", schema = "")
@SuppressWarnings("serial")
public class WeixinMenuGroupEntity implements java.io.Serializable {
	/** 主键 */
	private java.lang.String id;
	/** 同步日期 */
	private java.util.Date synchDate;
	/** 组名称 */
	@Excel(exportName = "组名称")
	private java.lang.String groupName;
	/** 公众帐号ID */
	@Excel(exportName = "公众帐号ID")
	private java.lang.String accountid;

	private List<MenuEntity> menuList;

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
	 * 方法: 取得java.util.Date
	 *
	 * @return: java.util.Date 同步日期
	 */
	@Column(name = "SYNCH_DATE", nullable = true, length = 20)
	public java.util.Date getSynchDate() {
		return this.synchDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 *
	 * @param: java.util.Date 同步日期
	 */
	public void setSynchDate(java.util.Date synchDate) {
		this.synchDate = synchDate;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 组名称
	 */
	@Column(name = "GROUP_NAME", nullable = true, length = 32)
	public java.lang.String getGroupName() {
		return this.groupName;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 组名称
	 */
	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String 公众帐号ID
	 */
	@Column(name = "ACCOUNTID", nullable = true, length = 32)
	public java.lang.String getAccountid() {
		return this.accountid;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String 公众帐号ID
	 */
	public void setAccountid(java.lang.String accountid) {
		this.accountid = accountid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "weixinMenuGroupEntity")
	public List<MenuEntity> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuEntity> menuList) {
		this.menuList = menuList;
	}

	

}
