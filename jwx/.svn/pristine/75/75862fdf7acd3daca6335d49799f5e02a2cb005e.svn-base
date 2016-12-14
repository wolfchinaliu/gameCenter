package weixin.message.entity;

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
 * @Description: 群发消息
 * @author onlineGenerator
 * @date 2015-01-21 11:29:31
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_message_group", schema = "")
@SuppressWarnings("serial")
public class WeixinMessageGroupEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**消息内容*/
	@Excel(exportName="消息内容")
	private java.lang.String note;
	
	@Excel(exportName="发送类型")
	private java.lang.String sendType;//0:群发,1:按分组发
	
	/**创建时间*/
	@Excel(exportName="创建时间")
	private java.util.Date createTime;
	/**所属公众号*/
	@Excel(exportName="所属公众号")
	private java.lang.String accountid;
	
	private String msgType;//消息类型
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=100)
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
	 *@return: java.lang.String  消息内容
	 */
	@Column(name ="NOTE",nullable=false,length=4000)
	public java.lang.String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  消息内容
	 */
	public void setNote(java.lang.String note){
		this.note = note;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
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

	@Column(name ="SEND_TYPE",nullable=true,length=1)
	public java.lang.String getSendType() {
		return sendType;
	}

	public void setSendType(java.lang.String sendType) {
		this.sendType = sendType;
	}

	@Column(name ="MSG_TYPE",nullable=true,length=20)
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	
}
