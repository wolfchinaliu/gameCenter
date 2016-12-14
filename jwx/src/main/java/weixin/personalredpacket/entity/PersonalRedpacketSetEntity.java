package weixin.personalredpacket.entity;

import org.hibernate.annotations.GenericGenerator;
import weixin.lottery.entity.WeixinCommonforhdEntity;

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
@Table(name = "weixin_personalredpacketset", schema = "")
@PrimaryKeyJoinColumn(name = "id")
public class PersonalRedpacketSetEntity extends WeixinCommonforhdEntity implements java.io.Serializable {
	/**主键*/
//	private String id;
	/**活动名称*/
//	private String name;
	/**活动描述*/
//	private String description;
	/**流量类型*/
//	private String flowType;   //1:全国流量 2：省内流量
	/**开始时间*/
//	private Date startTime;
	/**结束时间*/
//	private Date endTime;
	/**红包大小*/
	private Double redpacketValue;
	/**补贴大小*/
	private Double subsidyValue;
	/**红包数量*/
	private Integer redpacketNum;
	/**补贴比例*/
	private Double subsidyProportion;
	/**累计发出流量值*/
	private Double flowSendValue;
	/**微信公众号*/
//	private String accountid;
	/**创建时间*/
//	private Date createTime;
	/**状态位，标记活动是否正在进行 1，正在进行  0，停止*/
	private String state;


	@Column(name ="redpacketValue",nullable=false,length=36)
	public Double getRedpacketValue() {
		return redpacketValue;
	}

	public void setRedpacketValue(Double redpacketValue) {
		this.redpacketValue = redpacketValue;
	}

	@Column(name ="subsidyValue",nullable=false,length=36)
	public Double getSubsidyValue() {
		return subsidyValue;
	}

	public void setSubsidyValue(Double subsidyValue) {
		this.subsidyValue = subsidyValue;
	}

	@Column(name ="redpacketNum",nullable=false,length=36)
	public Integer getRedpacketNum() {
		return redpacketNum;
	}

	public void setRedpacketNum(Integer redpacketNum) {
		this.redpacketNum = redpacketNum;
	}

	@Column(name ="subsidyProportion",nullable=false,length=36)
	public Double getSubsidyProportion() {
		return subsidyProportion;
	}

	public void setSubsidyProportion(Double subsidyProportion) {
		this.subsidyProportion = subsidyProportion;
	}

	@Column(name ="flowSendValue",nullable=false,length=36)
	public Double getFlowSendValue() {
		return flowSendValue;
	}

	public void setFlowSendValue(Double flowSendValue) {
		this.flowSendValue = flowSendValue;
	}

	@Column(name ="state",nullable=false,length=8)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
