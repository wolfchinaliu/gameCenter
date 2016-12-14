package weixin.tenant.entity;

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
 * @Description: 快捷菜单
 * @author onlineGenerator
 * @date 2015-08-07 11:09:25
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_favo_menu", schema = "")
@SuppressWarnings("serial")
public class TFavoMenuEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**用户ID*/
	@Excel(exportName="用户ID")
	private java.lang.String userId;
	/**菜单ID*/
	@Excel(exportName="菜单ID")
	private java.lang.String menuId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户ID
	 */
	@Column(name ="USER_ID",nullable=true,length=36)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户ID
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  菜单ID
	 */
	@Column(name ="MENU_ID",nullable=true,length=36)
	public java.lang.String getMenuId(){
		return this.menuId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  菜单ID
	 */
	public void setMenuId(java.lang.String menuId){
		this.menuId = menuId;
	}
}
