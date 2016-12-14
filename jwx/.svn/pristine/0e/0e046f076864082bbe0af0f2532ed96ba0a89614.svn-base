package weixin.source.entity;

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
 * @Description: 素材管理
 * @author onlineGenerator
 * @date 2015-01-29 10:45:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_source", schema = "")
@SuppressWarnings("serial")
public class WeixinSourceEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**修改人名称*/
	private java.lang.String updateName;
	/**传至微信服务器媒体文件ID*/
	@Excel(exportName="传至微信服务器媒体文件ID")
	private java.lang.String mediaId;
	/**所属公众号*/
	@Excel(exportName="所属公众号")
	private java.lang.String accountid;
	/**素材类型*/
	@Excel(exportName="素材类型")
	private java.lang.String sourceType;//媒体文件类型，0:图文； 1:图片（image）; 2:语音（voice）; 3:视频（video）; 4:缩略图（thumb，主要用于视频与音乐格式的缩略图）
	
	private String sourcePath;//资源本地保存地址
	
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
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  传至微信服务器媒体文件ID
	 */
	@Column(name ="MEDIA_ID",nullable=true,length=255)
	public java.lang.String getMediaId(){
		return this.mediaId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  传至微信服务器媒体文件ID
	 */
	public void setMediaId(java.lang.String mediaId){
		this.mediaId = mediaId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公众号
	 */
	@Column(name ="ACCOUNTID",nullable=true,length=100)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公众号
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  素材类型
	 */
	@Column(name ="SOURCE_TYPE",nullable=true,length=50)
	public java.lang.String getSourceType(){
		return this.sourceType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  素材类型
	 */
	public void setSourceType(java.lang.String sourceType){
		this.sourceType = sourceType;
	}

	@Column(name ="SOURCE_PATH",nullable=true,length=255)
	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	
	
}
