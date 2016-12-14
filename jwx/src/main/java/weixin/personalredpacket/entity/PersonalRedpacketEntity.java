package weixin.personalredpacket.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 微信活动
 * @author onlineGenerator
 * @date 2015-02-05 14:26:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_personalredpacket", schema = "")
@SuppressWarnings("serial")
public class PersonalRedpacketEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**祝福语*/
	private String blessing;
	/**创建日期*/
	private Double flowvalue;
	/**活动名称*/
	private Integer redpacketNum;
	/**活动描述*/
	private String openId;
	/**一等奖*/
	private String redpacketsetId;
	/**一等奖数量*/
	private Date createTime;
	/**一等奖概率*/
	private String accountId;


	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}

	@Column(name ="blessing",nullable=true,length=255)
	public String getBlessing() {
		return blessing;
	}

	public void setBlessing(String blessing) {
		this.blessing = blessing;
	}

	@Column(name ="flowvalue",nullable=true,length=12)
	public Double getFlowvalue() {
		return flowvalue;
	}

	public void setFlowvalue(Double flowvalue) {
		this.flowvalue = flowvalue;
	}

	@Column(name ="redpacketNum",nullable=true,length=12)
	public Integer getRedpacketNum() {
		return redpacketNum;
	}

	public void setRedpacketNum(Integer redpacketNum) {
		this.redpacketNum = redpacketNum;
	}

	@Column(name ="openId",nullable=true,length=36)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name ="redpacketsetId",nullable=true,length=36)
	public String getRedpacketsetId() {
		return redpacketsetId;
	}

	public void setRedpacketsetId(String redpacketsetId) {
		this.redpacketsetId = redpacketsetId;
	}

	@Column(name ="createTime",nullable=true,length=20)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name ="accountId",nullable=true,length=36)
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
